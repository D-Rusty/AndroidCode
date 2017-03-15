package com.project.onepice.basicproject.androidBasic.map.baiduMap;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onepice2015 on 2017/2/20.
 */

public abstract class OverlayManager implements BaiduMap.OnMarkerClickListener,BaiduMap.OnPolylineClickListener{

    BaiduMap baiduMap=null;
    private List<OverlayOptions> mOverlayOptionList=null;
    List<Overlay> mOverlaylist=null;

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public boolean onPolylineClick(Polyline polyline) {
        return false;
    }

    public OverlayManager(BaiduMap baiduMap) {
        baiduMap = baiduMap;
        // mBaiduMap.setOnMarkerClickListener(this);
        if (mOverlayOptionList == null) {
            mOverlayOptionList = new ArrayList<OverlayOptions>();
        }
        if (mOverlaylist == null) {
            mOverlaylist = new ArrayList<Overlay>();
        }
    }

    public abstract List<OverlayOptions> getOverlayOptions();
    /**
     * 将所有Overlay 添加到地图上
     */
    public final void addToMap() {
        if (baiduMap == null) {
            return;
        }

        removeFromMap();
        List<OverlayOptions> overlayOptions = getOverlayOptions();
        if (overlayOptions != null) {
            mOverlayOptionList.addAll(getOverlayOptions());
        }

        for (OverlayOptions option : mOverlayOptionList) {
            mOverlaylist.add(baiduMap.addOverlay(option));
        }
    }

    /**
     * 将所有Overlay 从 地图上消除
     */
    public final void removeFromMap() {
        if (baiduMap == null) {
            return;
        }
        for (Overlay marker : mOverlaylist) {
            marker.remove();
        }
        mOverlayOptionList.clear();
        mOverlaylist.clear();

    }

    /**
     * 缩放地图，使所有Overlay都在合适的视野内
     * <p>
     * 注： 该方法只对Marker类型的overlay有效
     * </p>
     *
     */
    public void zoomToSpan() {
        if (baiduMap == null) {
            return;
        }
        if (mOverlaylist.size() > 0) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Overlay overlay : mOverlaylist) {
                // polyline 中的点可能太多，只按marker 缩放
                if (overlay instanceof Marker) {
                    builder.include(((Marker) overlay).getPosition());
                }
            }
            baiduMap.setMapStatus(MapStatusUpdateFactory
                    .newLatLngBounds(builder.build()));
        }
    }
}
