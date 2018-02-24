package com.kun.qian.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.kun.qian.baselibrary.utils.LogUtils;


/**
 * Created by xcy on 2018/1/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected BaseActivity mContext;

    //兼容SVG图片
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public boolean isSaveInstanceState = false;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isSaveInstanceState = true;
        LogUtils.i("Activity", "onSaveInstanceState");
    }


}
