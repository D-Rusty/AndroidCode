package com.hudong.wemedia.businesscomponent.coremodel.network.retrofit;

/**
 * 作者: 方天文
 * 日期: 2017/5/3:下午7:42
 * 概况:
 */

public class ServerException extends Throwable {

    public String getCode() {
        return code;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    private String code;

    public ServerException(String msg, String code) {
        this.code = code;
        this.msg = msg;
    }

}
