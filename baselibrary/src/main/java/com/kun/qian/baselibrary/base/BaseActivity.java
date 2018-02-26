package com.kun.qian.baselibrary.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kun.qian.baselibrary.R;
import com.kun.qian.baselibrary.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by xcy on 2018/1/16.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected BaseActivity mContext;
    private LinearLayout parentLinearLayout;
    private TextView mTvTitle;
    private TextView mTvRight;
    private Toolbar mToolbar;
    //兼容SVG图片
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private Unbinder mBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView(R.layout.activity_base);
        setContentView(getLayoutId());
        mBind = ButterKnife.bind(this);
        initToolBar();
        init();
        mContext = this;
    }

    protected abstract int getLayoutId();

    protected abstract void initToolBar();

    protected abstract void init();



    private void initContentView(@LayoutRes int layoutResID) {
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        parentLinearLayout = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        //  add parentLinearLayout in viewGroup
        viewGroup.addView(parentLinearLayout);
        //  add the layout of BaseActivity in parentLinearLayout
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
        mBind = ButterKnife.bind(this);
    }



    /**
     * is show back icon,default is none。
     * you can override the function in subclass and return to true show the back icon
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    private void setBackIcon(){
        if (null != getToolbar() && isShowBacking()) {
            getToolbar().setNavigationIcon(R.drawable.ic_vector_back);
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.onBackPressed();
                }
            });
        }
    }

    /**
     * @return TextView in center
     */
    public TextView getToolbarTitle() {
        return mTvTitle;
    }

    /**
     * @return TextView on the right
     */
    public TextView getSubTitle() {
        return mTvRight;
    }

    /**
     * set Title
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mTvTitle != null) {
            mTvTitle.setText(title);
        } else {
            getToolbar().setTitle(title);
            setSupportActionBar(getToolbar());
        }
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
