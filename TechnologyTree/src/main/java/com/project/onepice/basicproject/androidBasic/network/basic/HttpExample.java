package com.project.onepice.basicproject.androidBasic.network.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by onepice2015 on 2016/11/28.
 */

public class HttpExample {
    /**
     * HttpPost
     * */
    public void httpUpLoadFile() throws IOException {
       String uploadUrl="http://192.168.17.100/upload/UploadServlet";
        String end="\r\n";
        String boundary="******";
        try {
            URL uri =new URL(uploadUrl);
            HttpURLConnection httpURLConnection= (HttpURLConnection) uri.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("post");
            httpURLConnection.setRequestProperty("Connection","Keep-Alive");
            httpURLConnection.setRequestProperty("Charset","UTF-8");

            DataOutputStream dataOutputStream=new DataOutputStream(httpURLConnection.getOutputStream());
//            dataOutputStream.writeBytes(two);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}


