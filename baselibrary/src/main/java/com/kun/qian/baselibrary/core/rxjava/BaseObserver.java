package com.kun.qian.baselibrary.core.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.base.MyApplication;
import com.kun.qian.baselibrary.core.exception.ServerException;
import com.kun.qian.baselibrary.core.response.BaseResponse;
import com.kun.qian.baselibrary.utils.NetworkUtils;
import com.kun.qian.baselibrary.utils.ToastUtils;
import com.kun.qian.baselibrary.wiget.dialog.CommonDialog;
import com.kun.qian.baselibrary.wiget.dialog.IProgressDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by xcy on 2018/1/17.
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private Disposable disposed;
    private IProgressDialog progressDialog;
    private CommonDialog dialog;

    public BaseObserver() {
    }

    public BaseObserver(IProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
        init();
    }

    private void init() {
        if (null == progressDialog) return;
        dialog = progressDialog.getDialog();
        if (null == dialog) return;

        dialog.setDialogList(new CommonDialog.DialogDismissListner() {
            @Override
            public void onDismiss() {
                disposed.dispose();
            }
        });
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposed = d;
        if (null != progressDialog) progressDialog.show();
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        if (tBaseResponse.isSuccess()) {
            T data = tBaseResponse.getRows();
            onSuccess(data);
        } else {
            onHandleError(tBaseResponse.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        Throwable throwable = e;
        /**
         * 获取根源 异常
         */
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络不可用");
        } else if (e instanceof ServerException) {
            if (e.getMessage().equals("token失效，请重新登录")) {//token 失效 进入登录页面
                try {
                    //com.hjy.sports.login.LoginActivity 进入登录界面
                    Constant.token = "";
                    Class cla = Class.forName("com.qiankun.qkun.login.LoginActivity");
                    Context context = MyApplication.AppContext;
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("untoken", true);
                    Intent intent = new Intent(context, cla);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("请求超时，请稍后再试...");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("服务器响应超时，请稍后再试...");
        } else {
            ToastUtils.showShort("请求超时，请稍后再试...");
        }
        dismissProgress();
    }

    @Override
    public void onComplete() {
        dismissProgress();
    }

    protected abstract void onSuccess(T t);

    protected void onHandleError(String msg) {
        ToastUtils.showShort(msg);
    }


    /**
     * 取消进度框
     */
    private void dismissProgress() {
        if (null != progressDialog) {
            progressDialog.close();
        }
    }
}
