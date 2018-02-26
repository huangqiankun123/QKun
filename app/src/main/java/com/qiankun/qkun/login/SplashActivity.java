package com.qiankun.qkun.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.kun.qian.baselibrary.base.BaseActivity;
import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.utils.ActivityUtils;
import com.kun.qian.baselibrary.utils.BarUtils;
import com.kun.qian.baselibrary.utils.LogUtils;
import com.kun.qian.baselibrary.utils.ToastUtils;
import com.qiankun.qkun.R;
import com.qiankun.qkun.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by QKun on 2018/2/26.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initToolBar() {
        BarUtils.setStatusBarVisibility(SplashActivity.this, false);
        getToolbar().setVisibility(View.GONE);
    }

    @Override
    protected void init() {
        //定时2秒进入登录界面或者是主界面
        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        //当第一个进入的时候 token都是空的
                        if (TextUtils.isEmpty(Constant.token)) {
                            //进入登录界面
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            ActivityUtils.startActivity(intent);
                            finish();
                        } else {
                            //进入主页
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            ActivityUtils.startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
