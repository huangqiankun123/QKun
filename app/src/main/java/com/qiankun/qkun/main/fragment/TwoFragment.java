package com.qiankun.qkun.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kun.qian.baselibrary.base.BaseFragment;
import com.qiankun.qkun.R;


/**
 * Created by QKun on 2018/1/18.
 */

public class TwoFragment extends BaseFragment {

    public static final String ARGUMENT = "two";

    public static TwoFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        TwoFragment twoFragment = new TwoFragment();
        twoFragment.setArguments(bundle);
        return twoFragment;
    }


    @Override
    protected void baseInit() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_two;
    }
}
