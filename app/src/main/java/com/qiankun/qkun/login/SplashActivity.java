package com.qiankun.qkun.login;

import android.content.Intent;
import android.text.TextUtils;

import com.kun.qian.baselibrary.base.BaseActivity;
import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.utils.ActivityUtils;
import com.qiankun.qkun.R;
import com.qiankun.qkun.main.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/2/26.
 */

public class SplashActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }



    @Override
    protected void init() {
        //定时2秒进入登录界面或者是主界面
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
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
                });
    }

}
