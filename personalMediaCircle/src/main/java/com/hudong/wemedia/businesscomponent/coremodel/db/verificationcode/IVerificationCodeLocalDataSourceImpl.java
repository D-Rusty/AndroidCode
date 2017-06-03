package com.hudong.wemedia.businesscomponent.coremodel.db.verificationcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.basiccomponent.bean.VerificationCode;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;


/**
 * 作者: 方天文
 * 日期: 2017/4/20:下午9:37
 * 概况:
 */

public class IVerificationCodeLocalDataSourceImpl implements IVerificationCodeLocalDataSource {

    private DBHelper dbHelper = null;

    private final String VERCODE_ADD = "insert into "
            + TABLE_NAME + "("
            + CODE + ","
            + MOBILE + ","
            + TIME + ")"
            + " values (? ,? ,?) ";


    public IVerificationCodeLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
    }

    @Override
    public void saveVerCode(VerificationCode code) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);

        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE, code.getCode());
        contentValues.put(MOBILE, code.getMobile());
        contentValues.put(TIME, code.getTime());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    @Override
    public VerificationCode getVerCode() {
        VerificationCode code = new VerificationCode();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                code.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
                code.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
                code.setTime(cursor.getString(cursor.getColumnIndex(TIME)));
            }
            cursor.close();
        }
        db.close();
        return code;
    }


}
