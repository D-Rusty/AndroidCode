package com.project.onepice.travel.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by onepice2015 on 16/9/29.
 */

public class TicketDetail {
    public String errMsg;
    public List<RetData> retData;


    public static class RetData{
        public String scenicSn;
        public String scenicName;
        public String ticket;
        public String scenicIntro;
        public String lat;
        public String lon;
        public String address;
        public String picSrc;
        public List<voiceList> voiceList;
        public String errNum;

        public static class voiceList implements Serializable {
            public String scenicVoiceName;
            public String styleName;
            public String languageName;
            public String mp3Url;
        }

    }
}
