package com.kun.qian.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kun.qian.baselibrary.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by xcy on 2018/1/16.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected BaseActivity mContext;
    private LinearLayout parentLinearLayout;
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;
    //兼容SVG图片
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private Unbinder mBind;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        init();
        mContext = this;
    }

    protected abstract int getLayoutId();



    protected abstract void init();

    @Override
    public void onClick(View view) {

    }



    public boolean isSaveInstanceState = false;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isSaveInstanceState = true;
        LogUtils.i("Activity", "onSaveInstanceState");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null == mBind) {
            mBind.unbind();
        }
    }
}
