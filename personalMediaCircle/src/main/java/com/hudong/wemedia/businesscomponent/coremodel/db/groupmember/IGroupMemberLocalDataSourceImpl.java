package com.hudong.wemedia.businesscomponent.coremodel.db.groupmember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.GroupMember;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * 作者: 方天文
 * 日期: 2017/4/23:下午4:51
 * 概况:
 */

public class IGroupMemberLocalDataSourceImpl implements IGroupMemberLocalDataSource {
    private String loginUserId = null;
    private DBHelper dbHelper = null;

    public IGroupMemberLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        if (MediaCircleApplication.loginUser != null) {
            loginUserId = MediaCircleApplication.loginUser.getId();
        }
    }

    @Override
    public void saveGroupList(List<GroupMember> list, String groupId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        for (int i = 0; i < list.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, list.get(i).getId());
            contentValues.put(MID, list.get(i).getMid());
            contentValues.put(INTROPENID, list.get(i).getIntropenid());
            contentValues.put(HEADIMG, list.get(i).getHeadimg());
            contentValues.put(STATUS, list.get(i).getStatus());
            contentValues.put(MANAGE, list.get(i).getManage());
            contentValues.put(REALNAME, list.get(i).getRealname());
            contentValues.put(GROUP_ID, groupId);
            contentValues.put(LOGIN_USER_ID, loginUserId);
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();


    }

    @Override
    public List<Connections> getConnectionsNotjoinedCompany(List<Connections> friendList, String groupId) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> fList = new ArrayList<>();
        for (int i = 0; i < friendList.size(); i++) {
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + GROUP_ID + " = ? and " + LOGIN_USER_ID + " =? and " + MID + " =? ", new String[]{groupId, loginUserId, friendList.get(i).getId()});
            if (cursor == null || cursor.getCount() <= 0) {
                fList.add(friendList.get(i));
            } else {
                cursor.close();
            }

        }
        return fList;
    }


}

