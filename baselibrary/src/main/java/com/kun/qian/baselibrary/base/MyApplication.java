package com.kun.qian.baselibrary.base;

import android.app.Application;
import android.content.Context;

import com.kun.qian.baselibrary.utils.Utils;

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
    }
}
