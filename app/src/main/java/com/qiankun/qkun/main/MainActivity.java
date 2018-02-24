package com.qiankun.qkun.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.kun.qian.baselibrary.base.BaseActivity;
import com.qiankun.qkun.R;
import com.qiankun.qkun.main.fragment.FourFragment;
import com.qiankun.qkun.main.fragment.OneFragment;
import com.qiankun.qkun.main.fragment.ThreeFragment;
import com.qiankun.qkun.main.fragment.TwoFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.fl_content)
    FrameLayout mFlContent;
    @BindView(R.id.main_bottom)
    BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomBar();
    }

    private void initBottomBar() {
//        BottomNavigationBar bottomNavigationBar = findViewById(R.id.main_bottom);
        //设置模式（MODE_DEFAULT:如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式。MODE_FIXED 是填充模式，未选中的Item会显示文字，没有移位动画。MODE_SHIFTING:
        //移位模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像移位的动画）
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //设置背景样式（ BACKGROUND_STYLE_STATIC 点击的时候没有水波纹效果，BACKGROUND_STYLE_RIPPLE 点击的时候有水波纹效果，如果设置的Mode为MODE_FIXED，将使用BACKGROUND_STYLE_STATIC 。如果Mode为MODE_SHIFTING将使用BACKGROUND_STYLE_RIPPLE。）
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );

        // 添加Item,设置选中的Item,初始化
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.icon_news, "one").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.icon_wechat, "two").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.icon_find, "three").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.icon_my, "four").setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        //设置默认的Item
        setDefaultFragment();
        //监听实现的方法
        bottomNavigationBar.setTabSelectedListener(this);

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(OneFragment.newInstance("one"));
        fragments.add(TwoFragment.newInstance("two"));
        fragments.add(ThreeFragment.newInstance("three"));
        fragments.add(FourFragment.newInstance("four"));
        return fragments;
    }


    /**
     * 设置默认的Item
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_content, fragments.get(0));
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //当前的fragment
                Fragment from = fm.findFragmentById(R.id.fl_content);
                //点击即将跳转的fragment
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    // 隐藏当前的fragment，显示下一个
                    ft.hide(from).show(fragment);
                } else {
                    ft.hide(from).add(R.id.fl_content, fragment);
                    if (fragment.isHidden()) {
                        ft.show(fragment);

                    }
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        //这儿也要操作隐藏，否则Fragment会重叠
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                // 隐藏当前的fragment
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
