package com.kun.qian.baselibrary.core.config;

/**
 * Created by Administrator on 2017/8/28.
 */

public class Config {
    public static final boolean DEBUG = Boolean.parseBoolean("true");

    int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间


    public static final String BASE_URL = "http://116.196.95.169:80/";    //京东云正式 地址

//    public static final String BASE_URL = "http://192.168.100.158:80/";    //本地服务器 地址

//    public static final String BASE_URL = "http://192.168.100.120:8080/";    //张敏服务器地址

//     public static final String BASE_URL = "http://192.168.100.114:8080/";    //周俊服务器地址

    public static final String IMG_BASE_URL = BASE_URL + "image";
}
