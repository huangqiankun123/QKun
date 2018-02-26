package com.qiankun.qkun.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.kun.qian.baselibrary.api.ApiService;
import com.kun.qian.baselibrary.base.BaseFragment;
import com.kun.qian.baselibrary.bean.HomeBean;
import com.kun.qian.baselibrary.bean.LoginBean;
import com.kun.qian.baselibrary.core.response.BaseResponse;
import com.kun.qian.baselibrary.core.retrofit.RetrofitHelper;
import com.kun.qian.baselibrary.core.rxjava.BaseObserver;
import com.kun.qian.baselibrary.core.rxjava.RxSchedulers;
import com.kun.qian.baselibrary.utils.LogUtils;
import com.kun.qian.baselibrary.utils.ToastUtils;
import com.kun.qian.baselibrary.wiget.dialog.IProgressDialog;
import com.qiankun.qkun.R;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/1/18.
 */

public class OneFragment extends BaseFragment {
    public static final String ARGUMENT = "one";
    private TextView mText;

    public static OneFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);
        return oneFragment;
    }


    @Override
    protected void baseInit() {
        initdata();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    private void initdata() {
        LogUtils.i("我第一次进入");
        ToastUtils.showShort("我第一次进入");
        login();
    }

    private void login() {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "13191614");
        param.put("password", "123456");
        IProgressDialog progressDialog = new IProgressDialog().init(mContext).setDialogMsg(R.string.user_login);
        RetrofitHelper.createApi(ApiService.class).loginToApp(param)
                .compose(RxSchedulers.<BaseResponse<LoginBean>>compose())
                .doOnNext(new Consumer<BaseResponse<LoginBean>>() {//doOnNext 允许我们在每次输出一个元素之前做一些额外的事情 去保存/缓存网络结果
                    @Override
                    public void accept(BaseResponse<LoginBean> loginBeanBaseResponse) throws Exception {
                        //缓存起来
//                        String token = loginBeanBaseResponse.getRows().getToken();
                    }
                }).observeOn(Schedulers.io())
                .flatMap(new Function<BaseResponse<LoginBean>, ObservableSource<BaseResponse<HomeBean>>>() {
                    @Override
                    public ObservableSource<BaseResponse<HomeBean>> apply(BaseResponse<LoginBean> loginBeanBaseResponse) throws Exception {
                        Map<String, Object> param = new HashMap<>();
                        param.put("token", loginBeanBaseResponse.getRows().getToken());
                        param.put("studentid", loginBeanBaseResponse.getRows().getStudent().getStudentid());
                        return RetrofitHelper.createApi(ApiService.class).getHome(param);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .throttleFirst(2, TimeUnit.SECONDS)  //2s内只会执行一次 防抖动效果
                .subscribe(new BaseObserver<HomeBean>(progressDialog) {
                    @Override
                    protected void onSuccess(HomeBean homeBean) {
                        if (null != homeBean) {
                            LogUtils.i(homeBean.getStudentname());
                        }
//                        homeBean.getSocial().getContent();
//                        LogUtils.i(homeBean.getSocial().getContent());
//                        mText.setText(homeBean.getSocial().getContent());
                    }
                });

    }
}
