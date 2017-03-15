package com.project.onepice.basicproject.androidBasic.MusicDownload.net;

/**
 * Created by onepice2015 on 2017/2/17.
 *
 *
 *
 */

public class BMA {
    public static final String FORMATE = "json";
    public static final String BASE = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.6&format=" + FORMATE;

    /**
     * 歌曲基本信息
     * @param songId 歌曲id
     * @return
     */

    public static String songBaseInfo(String songId){
        StringBuffer stringBuffer = new StringBuffer(BASE);
        stringBuffer.append("&method=").append("baidu.ting.song.baseInfos")
                .append("&song_id=").append(songId);
        return stringBuffer.toString();
    }





//歌曲下载列表:

    //http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.6.5.6&
    //  format=json&method=baidu.ting.billboard.billList&type=1&size=10&offset=0

}
