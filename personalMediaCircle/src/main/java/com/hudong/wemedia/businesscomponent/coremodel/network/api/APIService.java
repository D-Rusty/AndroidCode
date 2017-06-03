package com.hudong.wemedia.businesscomponent.coremodel.network.api;

import com.hudong.wemedia.basiccomponent.bean.LoginUser;
import com.hudong.wemedia.businesscomponent.coremodel.network.retrofit.HttpResult;

import java.util.HashMap;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 作者: 方天文
 * 日期: 2017/4/11:下午2:43
 * 概况: API集成类，暂时未考虑到是否需要对API进行分类，可以依据模块分，也可以按照其它维度分
 */

public interface APIService {

    @FormUrlEncoded
    @POST("login")
    Observable<HttpResult<LoginUser>> loginService(@FieldMap HashMap<String, String> map);

    /**
     * 对好友设置为特别关心
     *
     * @param action
     * @param id
     * @param friendID
     * @param special
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<HttpResult> starFriend(@Field("action") String action, @Field("id") String id, @Field("friendid") String friendID, @Field("special") String special);

    /**
     * 转移好友到其它分组
     *
     * @param action
     * @param id
     * @param friendID
     * @param groupid
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<HttpResult> friend2Customer(@Field("action") String action, @Field("id") String id, @Field("friendid") String friendID, @Field("groupid") String groupid);

    /**
     * 获取好友信息
     *
     * @param action
     * @param connnectionId
     * @return
     */
    @FormUrlEncoded
    @POST
    Observable<HttpResult> getServerConnection(@Field("action") String action, @Field("id") String connnectionId);
}
