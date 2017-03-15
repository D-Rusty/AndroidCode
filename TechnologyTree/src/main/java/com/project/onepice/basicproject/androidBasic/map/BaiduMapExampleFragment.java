package com.project.onepice.basicproject.androidBasic.map;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.project.onepice.basicproject.BaseFragment;
import com.project.onepice.basicproject.R;
import com.project.onepice.basicproject.androidBasic.map.baiduMap.BaiDuUtils;
import com.project.onepice.basicproject.androidBasic.map.baiduMap.DrivingRouteOverlay;
import com.project.onepice.basicproject.androidBasic.map.baiduMap.OverlayManager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by onepice2015 on 2017/2/20.
 */

public class BaiduMapExampleFragment extends BaseFragment implements OnGetRoutePlanResultListener {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.bmapView)
    MapView bmapView;

    @Override
    public int getViewId() {
        return R.layout.fragment_baidu_map_item;
    }

    @Override
    public void initView() {
        title.setText("BaiduMap");
        initMap();
    }

    @OnClick(R.id.back)
    public void onClick(){
        Intent intent = new Intent("packFragment");
        intent.putExtra("currentFragment", BaiduMapExampleFragment.class.getName());
        getContext().sendBroadcast(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        bmapView.onResume();
        addInfosOverlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    @Override
    public void relecyResource() {
        if (bmapView != null) {
            bmapView.onDestroy();
        }
    }


    /**
     * 地图定位的模式
     */
    private String[] mStyles = new String[]{"地图模式【正常】", "地图模式【跟随】", "地图模式【罗盘】"};
    private int mCurrentStyle = 0;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    private BitmapDescriptor mIconMaker_blue;
    private BitmapDescriptor mIconMaker_red;
    private BitmapDescriptor mIconMaker_green;
    private BaiduMap baiduMap;
    /**
     * 最新一次的经纬度
     */
    private double mCurrentLantitude;
    private double mCurrentLongitude;

    /**
     * 定位的客户端
     */
    private LocationClient mLocationClient;
    /**
     * 定位的监听器
     */
    public MyLocationListener myLocationListener;
    /**
     * 方向传感器X方向的值
     */
    private int mXDirection;
    /**
     * 当前的精度
     */
    private float mCurrentAccracy;

    private double mLocLantitude;
    private double mLocLongitude;
    // 最新地址
    private String mCurrentAddr;

    // 搜索相关
    private RoutePlanSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用


    private void initMap() {
        baiduMap = bmapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mIconMaker_blue = BitmapDescriptorFactory.fromResource(R.mipmap.android);
        mIconMaker_green = BitmapDescriptorFactory.fromResource(R.mipmap.ci);
        mIconMaker_red = BitmapDescriptorFactory.fromResource(R.mipmap.java);
        hideZoomView(bmapView);
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(BaiDuUtils.MAP_5KM);
        baiduMap.animateMapStatus(mapStatusUpdate);
        baiduMap.setOnMapStatusChangeListener(maplistener);
        //初始化定位
        initMyLocation();
        initMarkerClickEvent();
        initMapClickEvent();
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }

    private void initMapClickEvent() {
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                baiduMap.hideInfoWindow();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private float currentZoom = BaiDuUtils.MAP_5KM;

    private void initMarkerClickEvent() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (null == marker.getExtraInfo()) {
                    return true;
                }
                final LatLng latLan = marker.getPosition();
                currentZoom = BaiDuUtils.MAP_SINGLE;
                // 在缩放等级15下以标注点上方0.01维度处为地图中心点
                double upCenterlat = (double) (latLan.latitude + BaiDuUtils.CalcMapAround2(currentZoom) * 2);
                double upTiplat = (double) (latLan.latitude + BaiDuUtils.CalcMapAround2(currentZoom));
                LatLng upCenterpoint = new LatLng(upCenterlat, latLan.longitude);
                LatLng upTipoint = new LatLng(upTiplat, latLan.longitude);
                // 设置地图中心的坐标点
                setBDLocationCenter(upCenterpoint, currentZoom);
                return true;
            }
        });
    }

    private void initMyLocation() {
        mLocationClient = new LocationClient(getActivity());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(5000);
        mLocationClient.setLocOption(option);
    }

    private void hideZoomView(MapView mapView) {
        int childCount = mapView.getChildCount();
        View zoom = null;
        for (int i = 0; i < childCount; i++) {
            View child = mapView.getChildAt(i);
            if (child instanceof ZoomControls) {
                zoom = child;
                break;
            }
        }
        if (zoom != null) {
            zoom.setVisibility(View.GONE);
        }
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.GONE);
        }

        mapView.showScaleControl(true);
        mapView.showZoomControls(false);
    }

    private BaiduMap.OnMapStatusChangeListener maplistener = new BaiduMap.OnMapStatusChangeListener() {
        /** 手势操作地图，设置地图状态等操作导致地图状态开始改变。
         *
         * @param mapStatus 地图状态改变开始时的地图状态
         *
         * */
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        /** 地图状态变化中
         *
         * @param mapStatus 当前地图状态
         *
         * */

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        /** 地图状态改变结束
         *
         * @param mapStatus 地图状态改变结束后的地图状态
         *
         * */
        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            //获取当前最新的经纬度
            mCurrentLongitude = mapStatus.bound.getCenter().longitude;
            mCurrentLantitude = mapStatus.bound.getCenter().latitude;
        }
    };

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation == null || bmapView == null) {
                return;
            }

            MyLocationData locData = new MyLocationData.Builder().accuracy(bdLocation.getRadius())
                    .direction(mXDirection).latitude(bdLocation.getLatitude()).longitude(bdLocation.getLongitude()).build();
            mCurrentAccracy = bdLocation.getRadius();
            baiduMap.setMyLocationData(locData);
            mLocLantitude = bdLocation.getLatitude();
            mLocLongitude = bdLocation.getLongitude();
            mCurrentAddr = bdLocation.getAddress().address;

            mCurrentLantitude = mLocLantitude;
            mCurrentLongitude = mLocLongitude;

            LatLng latlng = new LatLng(mCurrentLantitude, mCurrentLongitude);
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latlng);
            baiduMap.animateMapStatus(mapStatusUpdate);
            addInfosOverlay();
        }

//        @Override
//        public void onConnectHotSpotMessage(String s, int i) {
//
//        }
    }

    private void setBDLocationCenter(LatLng cenpt, float bb) {
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(cenpt, bb);
        // 改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate);
    }

    private double totallat;
    private double totallong;
    int tempindex = 0;

    private void addInfosOverlay() {
        baiduMap.clear();
        LatLng latLng = new LatLng(113.979962,22.595819);
        OverlayOptions overlayOptions = null;
        Marker marker = null;
        overlayOptions = new MarkerOptions().position(latLng).icon(mIconMaker_blue).zIndex(0);
        marker = (Marker) baiduMap.addOverlay(overlayOptions);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 开启图层定位
        baiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
//        // 开启方向传感器
//        if (myOrientationListener != null) {
//            myOrientationListener.start();
//        }
    }


    /**
     * 路径规划
     */

    RouteLine mRoute = null;
    OverlayManager routeOverlay = null;

    private void BDNav(LatLng start, LatLng end) {
        // 重置浏览节点的路线数据
        if (mRoute != null)
            mRoute = null;
        if (routeOverlay != null)
            routeOverlay.removeFromMap();
        baiduMap.clear();


        PlanNode stNode = PlanNode.withLocation(start);
        PlanNode enNode = PlanNode.withLocation(end);
        mSearch.drivingSearch((new DrivingRoutePlanOption()).from(stNode).to(enNode));

    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            // mBtnPre.setVisibility(View.VISIBLE);

            // mBtnNext.setVisibility(View.VISIBLE);
            mRoute = result.getRouteLines().get(0);
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(baiduMap);
            routeOverlay = overlay;
            baiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }
    }
    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
                return BitmapDescriptorFactory.fromResource(R.mipmap.ci);
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
                return BitmapDescriptorFactory.fromResource(R.mipmap.java);
        }
    }
}


































