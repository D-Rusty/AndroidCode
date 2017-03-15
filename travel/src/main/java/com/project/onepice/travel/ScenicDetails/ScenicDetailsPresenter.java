package com.project.onepice.travel.ScenicDetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.amap.api.location.AMapLocation;
import com.project.onepice.travel.data.Scenic;
import com.project.onepice.travel.data.source.remote.RemoteDataRepository;
import com.project.onepice.travel.data.source.remote.RemoteDataSource;
import com.project.onepice.travel.util.Utils;

/**
 * Created by onepice2015 on 2016/10/15.
 */

public class ScenicDetailsPresenter implements ScenicDetailsContact.Presenter {

    private final Context context;
    private final RemoteDataSource remoteDataSource;
    private final ScenicDetailsContact.View scenicDetailsPage;
    private Scenic scenic;

    public ScenicDetailsPresenter(Context context, RemoteDataRepository remoteDataRepository, ScenicDetailsContact.View scenicDetailsPage, Scenic scenic) {
        this.context = context;
        this.remoteDataSource = remoteDataRepository;
        this.scenicDetailsPage = scenicDetailsPage;
        this.scenic = scenic;
        scenicDetailsPage.setPresenter(this);
    }

    @Override
    public Scenic getScenic() {
        return scenic;
    }


    @Override
    public void startNavigation(String mapName) {
        AMapLocation location = Utils.getMapCityName(context);
        location.getLatitude();
        location.getLongitude();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri;
        if (mapName.equals("gaode")) {
            uri = Uri.parse("androidamap://route?sourceApplication=travel&lat=" + location.getLatitude() + "&lon=" +
                    location.getLongitude() + "&dlat=" + scenic.getLat() + "&dlon=" + scenic.getLon() + "&dname=" + scenic.getScenicName_zh() + "&dev=0&t=1");
        } else {
            uri = Uri.parse("baidumap://map/direction?origin=" + location.getLatitude() + "," + location.getLongitude() + "&destination="
                    + scenic.getLat() + "," + scenic.getLon() + "&mode=transit&sy=3&index=0&target=1");
        }

        intent.setData(uri);
        context.startActivity(intent);

    }

    @Override
    public boolean isVaildLocalApp(String appName) {

        return Utils.getAppInfoByPak(context, appName);
    }


}
