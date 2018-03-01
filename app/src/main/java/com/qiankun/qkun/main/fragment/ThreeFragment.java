package com.qiankun.qkun.main.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kun.qian.baselibrary.api.ApiService;
import com.kun.qian.baselibrary.base.BaseFragment;
import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.bean.LoginBean;
import com.kun.qian.baselibrary.bean.SocialAllListBean;
import com.kun.qian.baselibrary.core.config.Config;
import com.kun.qian.baselibrary.core.response.BaseResponse;
import com.kun.qian.baselibrary.core.retrofit.RetrofitHelper;
import com.kun.qian.baselibrary.core.rxjava.BaseObserver;
import com.kun.qian.baselibrary.core.rxjava.RxSchedulers;
import com.kun.qian.baselibrary.utils.CacheUtils;
import com.kun.qian.baselibrary.utils.imageload.ImgLoadUtils;
import com.qiankun.qkun.R;
import com.qiankun.qkun.three.SocialAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by QKun on 2018/1/18.
 */

public class ThreeFragment extends BaseFragment {

    public static final String ARGUMENT = "three";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;

    private SocialAdapter mAdapter;
    private int mPageNo;

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
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.simple_tool_bar_menu);

        initRefresh();

        LoginBean.StudentBean studentBean = (LoginBean.StudentBean) CacheUtils.getInstance().getSerializable("studentBean");

        initRecycler(studentBean);

        getlistAllByApp(1);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }

    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPageNo += 1;
                getlistAllByApp(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getlistAllByApp(1);
            }
        });
    }

    private void initRecycler(LoginBean.StudentBean studentBean) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        View header_view = LayoutInflater.from(mContext).inflate(R.layout.social_header_view,
                (ViewGroup) mRecyclerView.getParent(), false);
        AppCompatImageView mImgHeader = header_view.findViewById(R.id.social_header_photo);
        TextView mUser_name = header_view.findViewById(R.id.tv_user_name);
        if (null != studentBean) {
            if (!TextUtils.isEmpty(studentBean.getName())) {
                mUser_name.setText(studentBean.getName());
            }
            if (!TextUtils.isEmpty(studentBean.getTouxiangurl()))
                ImgLoadUtils.loadCircleImg(Config.IMG_BASE_URL + studentBean.getTouxiangurl(), mImgHeader);
        }

        mAdapter = new SocialAdapter(R.layout.item_my_social_contact, new ArrayList<SocialAllListBean.RowsBean>());

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setHeaderView(header_view);
    }


    private void getlistAllByApp(int pageNo) {
        Map<String, Object> param = new HashMap<>();
        param.put("token", Constant.token);
        param.put("studentid", Constant.studentID);
        param.put("pageNo", pageNo);
        param.put("pageSize", 10);
        RetrofitHelper.createApi(ApiService.class).getlistAllByApp(param)
                .compose(RxSchedulers.<BaseResponse<SocialAllListBean>>compose())
                .subscribe(new BaseObserver<SocialAllListBean>() {
                    @Override
                    protected void onSuccess(SocialAllListBean socialAllListBean) {
                        if (null != socialAllListBean) {
                            mPageNo = socialAllListBean.getPageNo();
                            List<SocialAllListBean.RowsBean> rows = socialAllListBean.getRows();
                            if (!rows.isEmpty()) {
                                if (mRefreshLayout.isRefreshing()) {
                                    mAdapter.setNewData(rows);
                                    mRefreshLayout.finishRefresh();
                                } else if (mRefreshLayout.isLoading()) {
                                    mAdapter.getData().addAll(rows);
                                    mRefreshLayout.finishLoadmore();
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    mAdapter.setNewData(rows);
                                }
                            } else {
                                if (mRefreshLayout.isRefreshing()) {
                                    mRefreshLayout.finishRefresh();
                                }
                                if (mRefreshLayout.isLoading()) {
                                    mRefreshLayout.finishLoadmore();
                                }
                            }
                        }
                    }
                });
    }


}
