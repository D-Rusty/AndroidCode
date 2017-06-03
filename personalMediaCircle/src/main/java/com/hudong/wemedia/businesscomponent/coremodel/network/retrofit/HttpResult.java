package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 作者: 方天文
 * 日期: 2017/4/13:下午6:21
 * 概况:
 */

public class HttpResult<T> implements Serializable {

    private static final long EXPIRE_LIMIT = 60 * 60 * 1000;
    private long createTime; //本次结果数据缓存的创建时间
    private String code;//服务器返回状态
    private String msg;//服务器返回状态的描述
    private String action;//本次操作
    private T content;// 经过解析后的具体数据类

    public HttpResult() {
        createTime = System.currentTimeMillis();
    }

    /**
     * @return 打印HttpResult 返回结果
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * @return 是否超过缓存期，是否需要重新刷选缓存
     */
    public boolean isExpire() {
        return System.currentTimeMillis() - createTime > EXPIRE_LIMIT;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean success() {
        return code.equals("1");
    }

}
