package com.hudong.wemedia.basiccomponent.bean;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:39
 * 概况:
 */

public class VerificationCode {
    String code;
    String time;
    String mobile;


    public VerificationCode() {
    }


    public VerificationCode(String code, String time,String mobile) {
        this.code = code;
        this.time = time;
        this.mobile=mobile;
    }

    @Override
    public String toString() {
        return "VerificationCode{" +
                "code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
