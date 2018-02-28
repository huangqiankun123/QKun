package com.qiankun.qkun.three;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kun.qian.baselibrary.base.Constant;
import com.kun.qian.baselibrary.bean.SocialAllListBean;
import com.kun.qian.baselibrary.core.config.Config;
import com.kun.qian.baselibrary.utils.imageload.ImgLoadUtils;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;
import com.qiankun.qkun.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 社交adapter
 * Created by QKun on 2017/12/26.
 */

public class SocialAdapter extends BaseQuickAdapter<SocialAllListBean.RowsBean, BaseViewHolder> {

    public SocialAdapter(int layoutResId, @Nullable List<SocialAllListBean.RowsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SocialAllListBean.RowsBean item) {
        //
        int mStudentId = Constant.studentID;
        int studentid = item.getStudentid();
        if (mStudentId != studentid) {
            AppCompatImageView iv_detlet = helper.getView(R.id.iv_delete);
            iv_detlet.setVisibility(View.GONE);
        } else {
            helper.getView(R.id.iv_delete).setVisibility(View.VISIBLE);
            helper.addOnClickListener(R.id.iv_delete);
        }


        ImgLoadUtils.loadCircleImg(Config.IMG_BASE_URL + item.getTouxiangurl(), (ImageView) helper.getView(R.id.iv_user_head));

        if (!TextUtils.isEmpty(item.getStudentname())) {
            helper.setText(R.id.tv_social_name, item.getStudentname());
        }

        if (!TextUtils.isEmpty(item.getSenddate())) {
            helper.setText(R.id.tv_social_time, item.getSenddate());
        }

        if (!TextUtils.isEmpty(item.getContent())) {
            helper.getView(R.id.tv_social_content).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_social_content, item.getContent());
        } else {
            helper.getView(R.id.tv_social_content).setVisibility(View.GONE);
        }


//        ImgLoadUtils.loadImage(item.getImageurl(),)
        //点赞状态
        int islike = item.getIslike();
        if (islike == 1) {//此条已经被赞过
            helper.setImageDrawable(R.id.iv_praise, mContext.getResources().getDrawable(R.mipmap.icon_praise_selected));
        } else {
            helper.setImageDrawable(R.id.iv_praise, mContext.getResources().getDrawable(R.mipmap.icon_praise_normal));
        }

        helper.setText(R.id.tv_praise_num, item.getLikenumber() + "");

        helper.setText(R.id.comment_num, item.getSonnumber() + "");

        helper.addOnClickListener(R.id.ll_praise); //点赞

        helper.addOnClickListener(R.id.ll_comment); //评论


        //当我们数据获取时
        NineGridView nineGridView = helper.getView(R.id.nine_grid_image);
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        List<String> imageurlList = item.getImageurlList();
        if (imageurlList != null) {
            for (String s : imageurlList) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setBigImageUrl(Config.IMG_BASE_URL + s);
                imageInfo.setThumbnailUrl(Config.IMG_BASE_URL + s);
                imageInfos.add(imageInfo);
            }
        }
        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfos));


    }
}
