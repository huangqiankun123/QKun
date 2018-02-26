package com.qiankun.qkun.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kun.qian.baselibrary.base.BaseFragment;
import com.qiankun.qkun.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by QKun on 2018/1/18.
 */

public class ThreeFragment extends BaseFragment {

    public static final String ARGUMENT = "three";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    public static ThreeFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        ThreeFragment threeFragment = new ThreeFragment();
        threeFragment.setArguments(bundle);
        return threeFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected void baseInit() {

    }


}
