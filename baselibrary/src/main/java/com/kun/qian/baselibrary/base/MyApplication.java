package com.kun.qian.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.kun.qian.baselibrary.utils.Utils;
import com.kun.qian.baselibrary.wiget.GlideImageLoader2;
import com.lzy.ninegrid.NineGridView;

/**
 * Created by QKun on 2018/2/24.
 */

public class MyApplication extends Application {
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        //工具类提供上下文
        Utils.init(this);
        NineGridView.setImageLoader(new GlideImageLoader2());
    }
}
