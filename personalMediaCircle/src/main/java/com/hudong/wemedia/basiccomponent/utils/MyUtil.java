package com.hudong.wemedia.basiccomponent.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudong.wemedia.R;
import com.hudong.wemedia.basiccomponent.bean.PhoneContact;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MyUtil {

    public static void showSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        //imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hideSoftInput(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void setTitle(final Activity activity, boolean isShowBack, String titleName, boolean isShowSearch){
        Button back = (Button)activity.findViewById(R.id.btn_title_back);
        TextView title = (TextView)activity.findViewById(R.id.tv_title_name);
        ImageView search = (ImageView)activity.findViewById(R.id.iv_search);
        title.setText(titleName);

        if (isShowBack){
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();
                }
            });
        }else {
            back.setVisibility(View.GONE);
        }

        if (isShowSearch){
            search.setVisibility(View.VISIBLE);
        }else {
            search.setVisibility(View.GONE);
        }

    }

    public static void setTitle(View view, boolean isShowBack, String titleName, boolean isShowSearch ){
        Button back = (Button)view.findViewById(R.id.btn_title_back);
        TextView title = (TextView)view.findViewById(R.id.tv_title_name);
        ImageView search = (ImageView)view.findViewById(R.id.iv_search);

        if (isShowBack){
            back.setVisibility(View.VISIBLE);
        }else {
            back.setVisibility(View.GONE);
        }

        if (isShowSearch){
            search.setVisibility(View.VISIBLE);
        }else {
            search.setVisibility(View.GONE);
        }

        title.setText(titleName);

    }

    //打电话
    public static void callPhone(Activity activity, String phoneNum){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        activity.startActivity(intent);
    }

    //获取手机联系人
    public static List<PhoneContact> getPhoneContacts(Context context) {
        try {
            Uri uri = Uri.parse("content://com.android.contacts/contacts");
            //获得一个ContentResolver数据共享的对象
            ContentResolver reslover = context.getContentResolver();
            //取得联系人中开始的游标，通过content://com.android.contacts/contacts这个路径获得
            Cursor cursor = reslover.query(uri, null, null, null, null);
            //上边的所有代码可以由这句话代替：Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            //Uri.parse("content://com.android.contacts/contacts") == ContactsContract.Contacts.CONTENT_URI
            List<PhoneContact> numList = new ArrayList<>();
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
//                    PhoneContact contact = new PhoneContact();
                    //获得联系人ID
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //获得联系人姓名
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //获得联系人手机号码
                    Cursor phone = reslover.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                    if (phone != null && phone.getCount() > 0) {
                        while (phone.moveToNext()) { //取得电话号码(可能存在多个号码)
                            PhoneContact contact = new PhoneContact();
                            int phoneFieldColumnIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                            String num = phone.getString(phoneFieldColumnIndex);
                            String phoneNumber = replaceBlank(num);
                            String checkedNum = phoneNumFilter(phoneNumber);
                            if (checkedNum != null && checkedNum.length() > 0) {
                                contact.setId(id);
                                contact.setName(name);
                                contact.setMobileNum(checkedNum);
                                numList.add(contact);
                            }


                        }
                        phone.close();
                    }

                }
                cursor.close();
            }
            return numList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //手机号码过滤
    private static String phoneNumFilter(String phoneNum) {
        if (phoneNum.length() >= 11 && !phoneNum.startsWith("0")) {
            String secondNum = phoneNum.substring(1, 2);
            if (!secondNum.equals("0")) {
                if (phoneNum.startsWith("+86")){
                    String forthNum = phoneNum.substring(3, 4);
                    if (forthNum.equals("1")){
                        return phoneNum;
                    }

                }else {
                    return phoneNum;
                }

            }
        }
        return null;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|-");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
