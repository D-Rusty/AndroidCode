package com.project.onepice.travel.data;

import java.io.Serializable;

/**
 * Created by onepice2015 on 2016/11/9.
 */
public class Scenic implements Serializable{


    private String scenicSn;
    private String city_id;
    private String scenicName_zh;
    private String province;
    private String city;
    private String sceniclogo;
    private String scenicTicket;
    private String lat;
    private String lon;


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }



    public String getScenicSn() {
        return scenicSn;
    }

    public void setScenicSn(String scenicSn) {
        this.scenicSn = scenicSn;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getScenicName_zh() {
        return scenicName_zh;
    }

    public void setScenicName_zh(String scenicName_zh) {
        this.scenicName_zh = scenicName_zh;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSceniclogo() {
        return sceniclogo;
    }

    public void setSceniclogo(String sceniclogo) {
        this.sceniclogo = sceniclogo;
    }

    public String getScenicTicket() {
        return scenicTicket;
    }

    public void setScenicTicket(String scenicTicket) {
        this.scenicTicket = scenicTicket;
    }
}
