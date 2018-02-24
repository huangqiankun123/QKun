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

public class ThreeFragment extends BaseFragment {

    public static final String ARGUMENT = "three";

    public static ThreeFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        ThreeFragment threeFragment = new ThreeFragment();
        threeFragment.setArguments(bundle);
        return threeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_three, container, false);
        return rootView;
    }
}
