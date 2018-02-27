package com.qiankun.qkun.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kun.qian.baselibrary.base.BaseFragment;
import com.kun.qian.baselibrary.core.config.Config;
import com.qiankun.qkun.R;
import com.qiankun.qkun.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/1/18.
 */

public class OneFragment extends BaseFragment {
    public static final String ARGUMENT = "one";
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.iv_student_photo)
    AppCompatImageView mIvStudentPhoto;
    @BindView(R.id.tv_student_name)
    TextView mTvStudentName;
    @BindView(R.id.tv_student_grade)
    TextView mTvStudentGrade;
    @BindView(R.id.tv_student_class)
    TextView mTvStudentClass;
    @BindView(R.id.tv_total_grade)
    TextView mTvTotalGrade;
    @BindView(R.id.ll_personal_data)
    LinearLayout mLlPersonalData;
    @BindView(R.id.tv_figure)
    TextView mTvFigure;
    @BindView(R.id.tv_stamina)
    TextView mTvStamina;
    @BindView(R.id.tv_standard)
    TextView mTvStandard;
    @BindView(R.id.tv_sensory_integration)
    TextView mTvSensoryIntegration;

    private List<String> imagesList = new ArrayList<>();
    private String[] images = {
            Config.BASE_URL + "images/banner/android/mipmap-xhdpi/banner1.png"
    };


    public static OneFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);
        return oneFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one;
    }

    @Override
    protected void baseInit() {
        for (String image : images) {
            imagesList.add(image);
        }
        mBanner.setImages(imagesList).setImageLoader(new GlideImageLoader()).start();
    }

    @OnClick({R.id.tv_figure, R.id.tv_stamina, R.id.tv_standard, R.id.tv_sensory_integration,
            R.id.ll_sport, R.id.ll_diet, R.id.ll_self_test, R.id.ll_traffic_signal,
            R.id.ll_expanded_one, R.id.ll_expanded_two, R.id.ll_expanded_three, R.id.ll_expanded_four,
            R.id.ll_personal_data, R.id.fl_title})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

}
