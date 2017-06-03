package com.hudong.wemedia.businesscomponent.coremodel.db.verificationcode;

import com.hudong.wemedia.basiccomponent.bean.VerificationCode;

/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:37
 * 概况:
 */

public interface IVerificationCodeLocalDataSource {
    String TABLE_NAME = "verificationcode";
    String CODE = "code";
    String TIME = "time";
    String MOBILE = "mobile";

    void saveVerCode(VerificationCode code);

    VerificationCode getVerCode();

}
