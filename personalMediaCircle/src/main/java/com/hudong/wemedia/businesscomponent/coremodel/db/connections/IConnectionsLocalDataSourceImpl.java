package com.hudong.wemedia.businesscomponent.coremodel.db.connections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hudong.wemedia.MediaCircleApplication;
import com.hudong.wemedia.basiccomponent.bean.Connections;
import com.hudong.wemedia.basiccomponent.bean.ConnectionsParseBean;
import com.hudong.wemedia.basiccomponent.bean.FriendGroupParseBean;
import com.hudong.wemedia.basiccomponent.bean.ILabelParse;
import com.hudong.wemedia.basiccomponent.bean.LabelAddParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelDeleteParseBean;
import com.hudong.wemedia.basiccomponent.bean.LabelTransferParseBean;
import com.hudong.wemedia.businesscomponent.coremodel.db.DBHelper;
import com.hudong.wemedia.businesscomponent.coremodel.db.group.GroupLocalDataSourceImpl;
import com.hudong.wemedia.businesscomponent.coremodel.db.group.IGroupLocalDataSource;
import com.hudong.wemedia.basiccomponent.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2016/12/3.
 */
public class IConnectionsLocalDataSourceImpl implements IConnectionsLocalDataSource{
    private DBHelper dbHelper = null;
    private String loginUserId = null;
    private IGroupLocalDataSource iGroupLocalDataSource;

    public IConnectionsLocalDataSourceImpl(Context context) {
        dbHelper = new DBHelper(context);
        loginUserId = MediaCircleApplication.loginUser.getId();
        iGroupLocalDataSource = new GroupLocalDataSourceImpl(context);
    }

    @Override
    public int deleteConnetion(String deletefriendid) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        return db.delete(TABLE_NAME, "id=?", new String[]{deletefriendid});
    }
    @Override
    public void saveConnectionsList(List<ConnectionsParseBean.MsgBean.ContentBean> list) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(ID, list.get(i).getId());
            values.put(REALNAME, list.get(i).getRealname());
            values.put(SEX, list.get(i).getSex());
            values.put(BIRTH, list.get(i).getBirth() + "");
            values.put(MOBILE, list.get(i).getMobile());
            values.put(COMPANY, list.get(i).getCompany());
            values.put(INDUSTRY, list.get(i).getIndustry());
            values.put(DEPARTMENT, list.get(i).getDepartment());
            values.put(COMPANY_ADDRESS, list.get(i).getCompanyaddress());
            values.put(HEADIMG, list.get(i).getHeadimg());
            values.put(HOME_ADDRESS, list.get(i).getHomeaddress());
            values.put(NOW_ADDRESS, list.get(i).getNowaddress());
            values.put(TOP_IDENTITY, list.get(i).getTopidentity() + "");
            values.put(BOTTOM_IDENTITY, list.get(i).getBottomidentity() + "");
            values.put(JOB, list.get(i).getJob());
            values.put(GROUP_ID, list.get(i).getGroupid());
            values.put(GROUP_NAME, list.get(i).getGroupname());
            values.put(LABLE_ID, list.get(i).getLabelid());
            values.put(NOW_PROVINCE, list.get(i).getNowprovince());
            values.put(NOW_PROVINCEC, list.get(i).getNowprovincec());
            values.put(NOW_CITY, list.get(i).getNowcity());
            values.put(NOW_CITYC, list.get(i).getNowcityc());
            values.put(MEMBER_RECRUIT, "");
            values.put(MEMBER_SEEK, "");
            values.put(MEMBER_FOR, "");
            values.put(LOGIN_USERID, loginUserId);
            values.put(SPECIAL, list.get(i).getSpecial());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }
    @Override
    public List<Connections> getConnectionsList() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + LOGIN_USERID + " =?", new String[]{loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    @Override
    public List<Connections> findConnection(String labelId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + " lable_id=?", new String[]{labelId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    @Override
    public List<Connections> getConnectionsList(String params) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + INDUSTRY + " LIKE %?% or " + REALNAME + " LIKE %?% or " + NOW_CITY + " LIKE %?% or " + " and " + LOGIN_USERID + " =?", new String[]{params, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    @Override
    public List<Connections> getConnsListByCity(String city) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from connections where login_username = ? and (now_province = ? or now_city = ?)", new String[]{loginUserId, city, city});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();

        }
        db.close();
        return list;
    }
    @Override
    public Connections getConnectionsById(String id) {
        Connections conn = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = ? and " + LOGIN_USERID + " = ?", new String[]{id, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                conn = createConnections(cursor);
            }
            cursor.close();
        }
        db.close();
        return conn;
    }
    @Override
    public List<Connections> findGroupConnection(String groupId, String labelId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + GROUP_ID + " =? and " + LABLE_ID + " =? and " + LOGIN_USERID + " =?", new String[]{groupId, labelId, loginUserId});
        Log.d("TAG", "findGroupConnection: " + cursor.getCount());
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    @Override
    public int updateSpecial(String connectionId, String specialValue) {
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues values = new ContentValues();
        values.put(SPECIAL, specialValue);
        int result = db.update(TABLE_NAME, values, ID + "=?", new String[]{connectionId});
        db.close();
        return result;
    }
    @Override
    public List<Connections> findGroupList(String groupName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Connections> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + GROUP_NAME + " =?" + " and " + LOGIN_USERID + " =?", new String[]{groupName, loginUserId});
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                list.add(createConnections(cursor));
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    @Override
    public HashMap<String, Integer> totalCloumSize(List<String> params) {
        if (null == params) {
            return null;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        HashMap<String, Integer> totalList = new HashMap<>();
        for (String groupName : params) {
            Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " where group_name=?", new String[]{groupName});
            cursor.moveToFirst();
            totalList.put(groupName, cursor.getInt(0));
            cursor.close();
        }
        return totalList;
    }
    @Override
    public int updateConnectionLabel(String operation, ILabelParse labelParse) {

        int result = 0;

        String editfrienddId = null;

        ContentValues contentValues = new ContentValues();


        ContentValues contentValuesDititle = new ContentValues();


        if (operation.equals("deteleLabel")) {
            contentValues.put(LABLE_ID, ((LabelDeleteParseBean.MsgBean) labelParse).getCureentlabelid());
        } else if (operation.equals("addLabel")) {
            contentValues.put(LABLE_ID, ((LabelAddParseBean.MsgBean) labelParse).getLabelid());
        } else if (operation.equals("transferLabel")) {
            contentValues.put(LABLE_ID, ((LabelTransferParseBean.MsgBean) labelParse).getLabelid());
        }

        SQLiteDatabase db = dbHelper.getWritableDataBase();

        if (labelParse.getFriend().contains(",")) {
            for (String friend : labelParse.getFriend().split(",")) {
                result = db.update(IConnectionsLocalDataSourceImpl.TABLE_NAME, contentValues, "id=?", new String[]{friend});
            }
        } else {
            result = db.update(IConnectionsLocalDataSourceImpl.TABLE_NAME, contentValues, "id=?", new String[]{labelParse.getFriend()});
        }


        if (operation.equals("transferLabel")) {
            contentValuesDititle.put(LABLE_ID, ((LabelTransferParseBean.MsgBean) labelParse).getDefaultlabelid());
            editfrienddId = ((LabelTransferParseBean.MsgBean) labelParse).getEditfriendid();
        }

        if (StringUtils.isEmpty(editfrienddId)) {
            if (editfrienddId.contains(",")) {
                for (String editfriend : editfrienddId.split(",")) {
                    result = db.update(IConnectionsLocalDataSourceImpl.TABLE_NAME, contentValuesDititle, "id=?", new String[]{editfriend});
                }
            } else {
                result = db.update(IConnectionsLocalDataSourceImpl.TABLE_NAME, contentValuesDititle, "id=?", new String[]{editfrienddId});
            }

        }


        return result;
    }
    @Override
    public int updateConnectionGroupLabel(LabelAddParseBean.MsgBean bean) {
        int reslut = 0;
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUP_ID, bean.getGroupid());
        if (bean.getLabelid().length() > 0) {
            contentValues.put(LABLE_ID, bean.getLabelid());
        }
        for (String friend : bean.getFriendid().split(",")) {
            reslut = db.update(TABLE_NAME, contentValues, "id=?", new String[]{friend});
        }
        return reslut;
    }
    @Override
    public int modifyConnectionLabel(String labelId, String labelName, String connectionId) {
        int reslut = 0;
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LABLE_ID, labelId);
        for (String friend : connectionId.split(",")) {
            reslut = db.update(TABLE_NAME, contentValues, "id=?", new String[]{friend});
        }
        return reslut;
    }
    @Override
    public int updateConnectionGroup(FriendGroupParseBean bean) {
        int reslut = 0;
        SQLiteDatabase db = dbHelper.getWritableDataBase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUP_NAME, iGroupLocalDataSource.getGroupName(bean.getMsg().getGroupid()));
        contentValues.put(GROUP_ID, bean.getMsg().getGroupid());
        contentValues.put(LABLE_ID, bean.getMsg().getLableid());
        if (bean.getMsg().getFriendid().contains(",")) {
            for (String friend : bean.getMsg().getFriendid().split(",")) {
                reslut = db.update(TABLE_NAME, contentValues, "id=?", new String[]{friend});
            }

        } else {
            reslut = db.update(TABLE_NAME, contentValues, "id=?", new String[]{bean.getMsg().getFriendid()});
        }

        return reslut;
    }
    @Override
    public String getConnectionLabelId(String id) {
        String labelId = null;
        SQLiteDatabase db = dbHelper.getReadableDataBase();
        Cursor cursor = db.rawQuery("select " + LABLE_ID + " from " + TABLE_NAME + " where " + "id=?", new String[]{id});
        cursor.moveToFirst();
        labelId = cursor.getString(0);
        db.close();
        return labelId;
    }

    private Connections createConnections(Cursor cursor) {
        Connections conn = new Connections();
        conn.setId(cursor.getString(cursor.getColumnIndex(ID)));
        conn.setRealname(cursor.getString(cursor.getColumnIndex(REALNAME)));
        conn.setSex(cursor.getString(cursor.getColumnIndex(SEX)));
        conn.setBirth(cursor.getString(cursor.getColumnIndex(BIRTH)));
        conn.setMobile(cursor.getString(cursor.getColumnIndex(MOBILE)));
        conn.setCompany(cursor.getString(cursor.getColumnIndex(COMPANY)));
        conn.setIndustry(cursor.getString(cursor.getColumnIndex(INDUSTRY)));
        conn.setDepartment(cursor.getString(cursor.getColumnIndex(DEPARTMENT)));
        conn.setCompanyaddress(cursor.getString(cursor.getColumnIndex(COMPANY_ADDRESS)));
        conn.setHeadimg(cursor.getString(cursor.getColumnIndex(HEADIMG)));
        conn.setHomeaddress(cursor.getString(cursor.getColumnIndex(HOME_ADDRESS)));
        conn.setNowaddress(cursor.getString(cursor.getColumnIndex(NOW_ADDRESS)));
        conn.setTopidentity(cursor.getString(cursor.getColumnIndex(TOP_IDENTITY)));
        conn.setBottomidentity(cursor.getString(cursor.getColumnIndex(BOTTOM_IDENTITY)));
        conn.setJob(cursor.getString(cursor.getColumnIndex(JOB)));
        conn.setGroup_id(cursor.getString(cursor.getColumnIndex(GROUP_ID)));
        conn.setGroup_name(cursor.getString(cursor.getColumnIndex(GROUP_NAME)));
        conn.setLable_id(cursor.getString(cursor.getColumnIndex(LABLE_ID)));
        conn.setNowprovince(cursor.getString(cursor.getColumnIndex(NOW_PROVINCE)));
        conn.setNowprovincec(cursor.getString(cursor.getColumnIndex(NOW_PROVINCEC)));
        conn.setNowcity(cursor.getString(cursor.getColumnIndex(NOW_CITY)));
        conn.setNowcityc(cursor.getString(cursor.getColumnIndex(NOW_CITYC)));
        conn.setIsAccept(cursor.getString(cursor.getColumnIndex(MEMBER_RECRUIT)));
        conn.setIsAccept(cursor.getString(cursor.getColumnIndex(MEMBER_SEEK)));
        conn.setIsAccept(cursor.getString(cursor.getColumnIndex(MEMBER_FOR)));
        conn.setLoginUserName(cursor.getString(cursor.getColumnIndex(LOGIN_USERID)));
        conn.setSpecial(cursor.getString(cursor.getColumnIndex(SPECIAL)));
        return conn;
    }








}
