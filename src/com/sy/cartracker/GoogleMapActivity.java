package com.sy.cartracker;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sy.testapp.BaseActivity;
import com.sy.testapp.R;
import com.sy.testapp.TestApp;

public class GoogleMapActivity extends BaseActivity implements OnSeekBarChangeListener,
OnMarkerDragListener, OnMapLongClickListener, OnMapReadyCallback {
    
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng CHENGDU = new LatLng(30.68387, 104.04227);
    private static final double DEFAULT_RADIUS = 1000;
    private static final double MIN_RADIUS = 500;
    private static final double MAX_RADIUS = 1500;
    public static final double RADIUS_OF_EARTH_METERS = 6371009;

    private static float DEF_ZOOM_LEVEL = 15f;
    
    private GoogleMap mMap;

    private List<DraggableCircle> mCircles = new ArrayList<DraggableCircle>(1);

    private SeekBar mRadiusBar;
    private int mStrokeColor;
    private int mFillColor;
    private double mRadius;
    private float mDensity = TestApp.getInstance().getResources().getDisplayMetrics().density;

    /********** 可拖动圆圈类 *********/
    private class DraggableCircle {
        private final Marker centerMarker;
        private final Circle circle;
        private double radius;
        /**
         * 创建一个新的实例 DraggableCircle.    
         */
        public DraggableCircle(LatLng center, double radius) {
            this.radius = radius;
            centerMarker = mMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));
            /**
             * Maker标识文档详见：
             * http://api.amap.com/Public/reference/Android%20API%20v2/com/amap/api/maps/model/BitmapDescriptorFactory.html
             */
            circle = mMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    // TODO 设置边线宽度
                    .strokeWidth(mDensity * 1)
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));
        }
        /**
         * 创建一个新的实例 DraggableCircle.    
         */
        public DraggableCircle(LatLng center, LatLng radiusLatLng) {
            this.radius = toRadiusMeters(center, radiusLatLng);
            centerMarker = mMap.addMarker(new MarkerOptions()
                    .position(center)
                    .draggable(true));
            circle = mMap.addCircle(new CircleOptions()
                    .center(center)
                    .radius(radius)
                    .strokeWidth(mDensity * 1)
                    .strokeColor(mStrokeColor)
                    .fillColor(mFillColor));
        }
        public boolean onMarkerMoved(Marker marker) {
            if (marker.equals(centerMarker)) {
                circle.setCenter(marker.getPosition());
                return true;
            }
            return false;
        }
        public void onStyleChange() {
            circle.setRadius(mRadius);
        }
    }
    /********** 可拖动圆圈类结束 *********/

    private static double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude,
                radius.latitude, radius.longitude, result);
        return result[0];
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        
        mRadiusBar = (SeekBar) findViewById(R.id.sb_map_geofence_radius);
        mRadiusBar.setMax(100);
        mRadiusBar.setProgress(50);

        SupportMapFragment mapFragment =
            (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        map.setContentDescription("Google Map with circles.");

        mRadiusBar.setOnSeekBarChangeListener(this);
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMapLongClickListener(this);

        mFillColor = 0x4c28aae5;
        mStrokeColor = mFillColor;

//        DraggableCircle circle = new DraggableCircle(SYDNEY, DEFAULT_RADIUS);
        DraggableCircle circle = new DraggableCircle(CHENGDU, DEFAULT_RADIUS);
        mCircles.add(circle);

        // Move the map so that it is centered on the initial circle
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, 4.0f));
        // TODO 放大级别确定
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CHENGDU, DEF_ZOOM_LEVEL));
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // Don't do anything here.
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == mRadiusBar) {
            // TODO 改变半径
            double tmpR = MAX_RADIUS * progress / 100;
            if (tmpR < MIN_RADIUS || tmpR > MAX_RADIUS) {
                return;
            }
            mRadius = tmpR;
        } 

        for (DraggableCircle draggableCircle : mCircles) {
            draggableCircle.onStyleChange();
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        onMarkerMoved(marker);
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        onMarkerMoved(marker);
    }

    private void onMarkerMoved(Marker marker) {
        for (DraggableCircle draggableCircle : mCircles) {
            if (draggableCircle.onMarkerMoved(marker)) {
                break;
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng point) {
        // We know the center, let's place the outline at a point 3/4 along the view.
        View view = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getView();
        LatLng radiusLatLng = mMap.getProjection().fromScreenLocation(new Point(
            view.getHeight() * 3 / 4, view.getWidth() * 3 / 4));

        // ok create it
        DraggableCircle circle = new DraggableCircle(point, radiusLatLng);
        mCircles.add(circle);
    }
}
