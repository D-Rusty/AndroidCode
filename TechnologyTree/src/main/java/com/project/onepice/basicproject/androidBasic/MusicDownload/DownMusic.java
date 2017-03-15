package com.project.onepice.basicproject.androidBasic.MusicDownload;

import android.content.Context;
import android.os.AsyncTask;

import com.project.onepice.basicproject.androidBasic.MusicDownload.vo.MusicFileDownInfo;

/**
 * Created by onepice2015 on 2017/2/17.
 */

public class DownMusic {
    public static void downMusic(final Context context, final String song_id, final String name, final String artist) {
        new AsyncTask<String,String,MusicFileDownInfo>(){

            @Override
            protected MusicFileDownInfo doInBackground(String... strings) {
                return null;
            }
        };
    }
}
