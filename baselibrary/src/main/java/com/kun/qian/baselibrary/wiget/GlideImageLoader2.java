package com.kun.qian.baselibrary.wiget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kun.qian.baselibrary.utils.imageload.ImgLoadUtils;
import com.lzy.ninegrid.NineGridView;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by xcy on 2018/2/27.
 */

public class GlideImageLoader2 implements NineGridView.ImageLoader {


    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url) {
        ImgLoadUtils.loadImage(context,url,imageView);
    }

    @Override
    public Bitmap getCacheImage(String url) {
        return null;
    }
}
