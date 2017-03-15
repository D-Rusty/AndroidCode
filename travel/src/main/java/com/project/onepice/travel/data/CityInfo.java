package com.project.onepice.travel.data;

/**
 * Created by onepice2015 on 2016/11/8.
 */

public class CityInfo {
    private String Province;
    private String city_id;
    private String cityName_zh;
    private String cityName_en;
    private String district;


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCityName_zh() {
        return cityName_zh;
    }

    public void setCityName_zh(String cityName_zh) {
        this.cityName_zh = cityName_zh;
    }

    public String getCityName_en() {
        return cityName_en;
    }

    public void setCityName_en(String cityName_en) {
        this.cityName_en = cityName_en;
    }
}
