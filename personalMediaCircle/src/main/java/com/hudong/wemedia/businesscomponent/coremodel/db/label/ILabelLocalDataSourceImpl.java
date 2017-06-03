package com.hudong.wemedia.businesscomponent.coremodel.db.label;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.LabelAddParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelDeleteParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelModifyParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.connections.IConnectionsLocalDataSourceImpl;
import com.hudong.wemedia.businesscomponent.coremodel.db.connections.IConnectionsLocalDataSource;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6.
 */
public class ILabelLocalDataSourceImpl implements ILabelLocalDataSource {


    private DBHelper dbHelper = null;

    private IConnectionsLocalDataSource iConnectionsLocalDataSource = null;

    public ILabelLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        iConnectionsLocalDataSource = new IConnectionsLocalDataSourceImpl(context);
    }

    @Override
    public void saveLabelList(List<LabelParseBean.MsgBean> labelList) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        for (int i = 0; i < labelList.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, labelList.get(i).getId());
            contentValues.put(MID, labelList.get(i).getMid());
            contentValues.put(LABELNAME, labelList.get(i).getLabelname());
            contentValues.put(GROUPID, labelList.get(i).getGroupid());
            long insert = db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
    }

    @Override
    public String getLabelId(String groupId, String labelName) {
        String labelId = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select " + ID + " from " + TABLE_NAME + " where " + "label_name=? and groupid=?", new String[]{labelName, groupId});
        cursor.moveToFirst();
        labelId = cursor.getString(0);
        db.close();
        return labelId;
    }

    @Override
    public String getLabelname(String labelId) {
        String labelName = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select " + LABELNAME + " from " + TABLE_NAME + " where " + "id=?", new String[]{labelId});
        cursor.moveToFirst();
        labelName = cursor.getString(0);
        db.close();
        return labelName;
    }

    @Override
    public List<LabelParseBean.MsgBean> getLabelList(String groupId) {
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        List<LabelParseBean.MsgBean> labelList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + " groupid=?", new String[]{groupId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                LabelParseBean.MsgBean label = new LabelParseBean.MsgBean();
                label.setId(cursor.getString(cursor.getColumnIndex(ID)));
                label.setGroupid(cursor.getString(cursor.getColumnIndex(GROUPID)));
                label.setMid(cursor.getString(cursor.getColumnIndex(MID)));
                label.setLabelname(cursor.getString(cursor.getColumnIndex(LABELNAME)));
                labelList.add(label);
            }
            cursor.close();
        }
        db.close();
        return labelList;
    }

    @Override
    public long addLabel(LabelAddParseBean.MsgBean bean) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();

        for (String friend : bean.getFriendid().split(",")) {
            ContentValues contentValuesConnection = new ContentValues();
            contentValuesConnection.put(IConnectionsLocalDataSourceImpl.LABLE_ID, bean.getLabelid());
            db.update(IConnectionsLocalDataSourceImpl.TABLE_NAME, contentValuesConnection, "id=?", new String[]{friend});
        }
        ContentValues contentValuesLabel = new ContentValues();
        contentValuesLabel.put(ID, bean.getLabelid());
        contentValuesLabel.put(MID, MediaCircleApplication.loginUser.getId());
        contentValuesLabel.put(LABELNAME, bean.getLabelname());
        contentValuesLabel.put(GROUPID, bean.getGroupid());
        long reslut = db.insert(TABLE_NAME, null, contentValuesLabel);
        db.close();
        return reslut;
    }

    @Override
    public int deleteLabelConnection(LabelDeleteParseBean.MsgBean bean) {
        if (bean.getFriendid().trim().length() > 0) {
            iConnectionsLocalDataSource.updateConnectionLabel("deteleLabel", bean);
        }
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        return delteLabel(bean.getDeletelabelid());
    }

    @Override
    public void modifyLabel(LabelModifyParseBean.MsgBean bean) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LABELNAME, bean.getLabelname());
        db.update(TABLE_NAME, contentValues, "id=?", new String[]{bean.getLabelid()});
        db.close();
    }

    @Override
    public int delteLabel(String labelId) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        int result = db.delete(ILabelLocalDataSourceImpl.TABLE_NAME, ILabelLocalDataSourceImpl.ID + "=? ", new String[]{labelId});
        db.close();
        return result;
    }

    @Override
    public int delteBatchLabel(String groupId) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        int result = db.delete(ILabelLocalDataSourceImpl.TABLE_NAME, ILabelLocalDataSourceImpl.GROUPID + "=? ", new String[]{groupId});
        db.close();
        return result;
    }
}
