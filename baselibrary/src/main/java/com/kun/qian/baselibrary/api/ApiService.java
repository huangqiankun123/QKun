package com.kun.qian.baselibrary.api;

import com.kun.qian.baselibrary.bean.HomeBean;
import com.kun.qian.baselibrary.bean.LoginBean;
import com.kun.qian.baselibrary.core.response.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 通用的的api接口 </p>
 * Created by fangs on 2017/8/28.
 */
public interface ApiService {

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("sys/loginToApp")
    Observable<BaseResponse<LoginBean>> loginToApp(@FieldMap Map<String, Object> options);

//    /**
//     * 退出登录接口
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("sys/logout")
//    Observable<BeanModule<Object>> loginOut(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 学生个人信息
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("student/infoToApp")
//    Observable<BeanModule<StudentInfo>> infoToApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 学生个人信息修改
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("student/updateToApp")
//    Observable<BeanModule<Object>> updateInfo(@FieldMap Map<String, Object> options);
//
//    /**
//     * 体质信息
//     */
//    @Headers({"url_name:user"})
//    @GET("measurements/studentItemToApp")
//    Observable<BeanModule<TotalHealthInfoBean>> getItemToApp(@QueryMap Map<String, Object> options);
//
//
//    /**
//     * 各项体质信息
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("measurements/singleItemScToApp")
//    Observable<BeanModule<ArrayList<EachHealthInfoBean>>> getItemScToApp(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 体质与所有学生平均值比较
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("measurements/studentCompareAllToApp")
//    Observable<BeanModule<StudentCompareBean>> getCompareAllToApp(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 推荐菜谱
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("dietary/listToApp")
//    Observable<BeanModule<MealsBean>> getMenuList(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 多图片上传
//     *
//     * @param token
//     * @return
//     */
//    @Multipart
//    @Headers({"url_name:user"})
//    @POST("file/uploadImages")
////    @POST("http://192.168.100.123/hfs/")
//    Observable<BeanModule<String>> uploadPostFile(@Part("token") RequestBody token,
//                                                  @Part("type") RequestBody type,
//                                                  @Part List<MultipartBody.Part> files);
//
//    /**
//     * 所有帖子列表
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/listAllByApp")
//    Observable<BeanModule<SocialAllListBean>> getlistAllByApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 我的发帖列表
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/listMyByApp")
//    Observable<BeanModule<SocialAllListBean>> getlistMyByApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 新增帖子
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/addToApp")
//    Observable<BeanModule<Object>> addToApp(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 某个帖子回复列表
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/listSonByApp")
//    Observable<BeanModule<ReplyListBean>> getlistSonByApp(@FieldMap Map<String, Object> options);
//
//
//    /**
//     * 删除帖子
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/deleteToApp")
//    Observable<BeanModule<Object>> deleteToApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 回复帖子
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/addSonToApp")
//    Observable<BeanModule<Object>> addSonToApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 某个评论回复列表
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/listSonByApp")
//    Observable<BeanModule<SecondRecommendBean>> getlistSecondSonByApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 点赞
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/addLikeToApp")
//    Observable<BeanModule<Object>> addLikeToApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 取消赞
//     */
//    @FormUrlEncoded
//    @Headers({"url_name:user"})
//    @POST("social/deleteLikeToApp")
//    Observable<BeanModule<Object>> deleteLikeToApp(@FieldMap Map<String, Object> options);
//
//    /**
//     * 断点下载指定URL的文件
//     *
//     * @param range
//     * @param fileUrl
//     * @return
//     */
//    @Streaming
//    @GET
//    Observable<ResponseBody> downLoadFile(@Header("Range") String range, @Url String fileUrl);
//
//    /**
//     * 获取下载的文件的大小
//     *
//     * @param fileUrl
//     * @return
//     */
//    @Streaming
//    @GET
//    Observable<ResponseBody> downLoadFileSize(@Url String fileUrl);
//
//
    /**
     * 首页信息
     */
    @GET("student/getHome")
    Observable<BaseResponse<HomeBean>> getHome(@QueryMap Map<String, Object> options);



}
