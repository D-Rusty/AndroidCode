package com.hudong.wemedia.businesscomponent.coremodel.db.group;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.basiccomponent.bean.GroupParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;
import com.hudong.wemedia.businesscomponent.coremodel.db.label.ILabelLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.label.ILabelLocalDataSourceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public class GroupLocalDataSourceImpl implements IGroupLocalDataSource {

    private DBHelper dbHelper = null;
    private ILabelLocalDataSource labelLocalDataSource;

    public GroupLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        labelLocalDataSource = new ILabelLocalDataSourceImpl(context);
    }

    @Override
    public long saveGroupList(List<GroupParseBean.MsgBean> groupList) {
        long result = 0;
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        db.execSQL("delete from " + TABLE_NAME);
        for (int i = 0; i < groupList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MID, groupList.get(i).getMid());
            contentValues.put(GROUPID, groupList.get(i).getId());
            contentValues.put(NAME, groupList.get(i).getName());
            contentValues.put(SORT, groupList.get(i).getSort());
            contentValues.put(ALIAS, groupList.get(i).getAlias());
            result = db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return result;
    }

    @Override
    public long saveGroup(GroupParseBean.MsgBean groupBean) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MID, groupBean.getMid());
        contentValues.put(GROUPID, groupBean.getId());
        contentValues.put(NAME, groupBean.getName());
        contentValues.put(SORT, groupBean.getSort());
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result;
    }

    @Override
    public int modifyGroup(String groupOldName, String groupNewName) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, groupNewName);
        int result = db.update(TABLE_NAME, contentValues, "name=?", new String[]{groupOldName});
        db.close();
        return result;
    }

    @Override
    public int deleteGroup(String groupName) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        int result = db.delete(TABLE_NAME, "name=?", new String[]{groupName});
        labelLocalDataSource.delteLabel(getGroupId(groupName));
        db.close();
        return result;
    }

    @Override
    public List<GroupParseBean.MsgBean> getGroupList() {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<GroupParseBean.MsgBean> groupList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                GroupParseBean.MsgBean group = new GroupParseBean.MsgBean();
                group.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                group.setId(cursor.getString(cursor.getColumnIndex(GROUPID)));
                group.setAlias(cursor.getString(cursor.getColumnIndex(ALIAS)));
                group.setMid(cursor.getString(cursor.getColumnIndex(MID)));
                group.setSort(cursor.getString(cursor.getColumnIndex(SORT)));
                groupList.add(group);
            }
            cursor.close();
        }
        db.close();
        return groupList;
    }

    @Override
    public List<String> getGroupNameList(String group) {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<String> groupNameList = new ArrayList<>();

        String groupWheres = group.equals("9999") ? " where " + SORT + "=" + group : "";

        Cursor cursor = db.rawQuery("select " + NAME + " from " + TABLE_NAME + groupWheres, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                groupNameList.add(cursor.getString(cursor.getColumnIndex(NAME)));
            }
            cursor.close();
        }
        db.close();
        return groupNameList;
    }

    @Override
    public List<GroupParseBean.MsgBean> getOtherGroupList() {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<GroupParseBean.MsgBean> groupList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + SORT + "=9999", null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                GroupParseBean.MsgBean group = new GroupParseBean.MsgBean();
                group.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                group.setId(cursor.getString(cursor.getColumnIndex(GROUPID)));
                group.setAlias(cursor.getString(cursor.getColumnIndex(ALIAS)));
                group.setMid(cursor.getString(cursor.getColumnIndex(MID)));
                group.setSort(cursor.getString(cursor.getColumnIndex(SORT)));
                groupList.add(group);
            }
            cursor.close();
        }
        db.close();
        return groupList;
    }

    @Override
    public GroupParseBean.MsgBean getGroup(String alias) {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        GroupParseBean.MsgBean group = null;
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + ALIAS + " = ?", new String[]{alias});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                group = new GroupParseBean.MsgBean();
                group.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                group.setAlias(cursor.getString(cursor.getColumnIndex(ALIAS)));
                group.setMid(cursor.getString(cursor.getColumnIndex(MID)));
                group.setId(cursor.getString(cursor.getColumnIndex(GROUPID)));
                group.setSort(cursor.getString(cursor.getColumnIndex(SORT)));
            }
            cursor.close();
        }
        db.close();
        return group;
    }

    @Override
    public String getGroupId(String groupName) {
        String labelId = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select " + GROUPID + " from " + TABLE_NAME + " where " + "name=?", new String[]{groupName});
        cursor.moveToFirst();
        labelId = cursor.getString(0);
        db.close();
        return labelId;
    }

    @Override
    public String getGroupName(String groupId) {
        String groupid = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select " + NAME + " from " + TABLE_NAME + " where " + "groupid=?", new String[]{groupId});
        cursor.moveToFirst();
        groupid = cursor.getString(0);
        db.close();
        return groupid;
    }
}
