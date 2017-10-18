package com.youqd.kind.ckind.activity;

import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;

/**
 * Created by Chenxiu on 2016/7/24.
 */
public class BaiduActivity extends KingActivity {
    BitmapDescriptor mCurrentMarker;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private static final int accuracyCircleFillColor = 0x4400A57C;
    private static final int accuracyCircleStrokeColor = 0x00000000;

    @Override
    protected int initLayout() {
        return R.layout.activity_baidu;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.top_title)).setText("校车定位");
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(80f)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(39.963175)
                .longitude(116.400244).build();

        mBaiduMap.setMyLocationData(locData);

        LatLng ll = new LatLng(39.963175,
                116.400244);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);

        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_bas);

        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker,
                        accuracyCircleFillColor, accuracyCircleStrokeColor));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }
}
