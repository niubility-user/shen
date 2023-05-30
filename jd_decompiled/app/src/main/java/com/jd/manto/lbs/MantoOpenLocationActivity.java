package com.jd.manto.lbs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.jd.dynamic.DYConstants;
import com.jd.manto.map.R;
import com.jd.manto.map.Tools;
import com.jingdong.a;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;

/* loaded from: classes17.dex */
public class MantoOpenLocationActivity extends Activity implements View.OnClickListener, TencentLocationListener {
    private String address;
    private ImageView gotoMyPosition;
    private double kwebmap_lng;
    private double kwebmap_slat;
    private TencentLocationManager locationManager;
    private TencentLocation mLocation;
    private MapView mMapView;
    private TencentMap mTencentMap;
    private Marker myDirectionMarker;
    private String poiName;
    private TencentLocationRequest request;

    private float computeRotation(float f2) {
        return f2;
    }

    private void initMapView() {
        findViewById(R.id.map_back).setOnClickListener(this);
        findViewById(R.id.map_navigate_btn).setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.map_goto_my_position);
        this.gotoMyPosition = imageView;
        imageView.setOnClickListener(this);
        MapView mapView = (MapView) findViewById(R.id.mapviewOverlay);
        this.mMapView = mapView;
        this.mTencentMap = mapView.getMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLocation() {
        TencentLocationManager tencentLocationManager = this.locationManager;
        if (tencentLocationManager != null) {
            tencentLocationManager.requestSingleFreshLocation(this.request, this, Looper.getMainLooper());
        }
    }

    private void showMarker() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        this.kwebmap_slat = intent.getDoubleExtra("kwebmap_slat", 0.0d);
        this.kwebmap_lng = intent.getDoubleExtra("kwebmap_lng", 0.0d);
        int intExtra = intent.getIntExtra("kwebmap_scale", 17);
        this.poiName = intent.getStringExtra("kPoiName");
        this.address = intent.getStringExtra("Kwebmap_location");
        ((TextView) findViewById(R.id.map_location_name)).setText(this.poiName);
        ((TextView) findViewById(R.id.map_location_address)).setText(this.address);
        if (Tools.isLatLngValid(this.kwebmap_slat, this.kwebmap_lng)) {
            LatLng latLng = new LatLng(this.kwebmap_slat, this.kwebmap_lng);
            this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, intExtra)));
            this.mTencentMap.addMarker(new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_location_anchor)));
            this.locationManager = TencentLocationManager.getInstance(getApplicationContext());
            this.request = TencentLocationRequest.create().setInterval(1000L).setAllowDirection(true);
            if (MantoPermission.hasLocationPermissionWithScene("locService", Tools.JD_LOCATION_ID)) {
                requestLocation();
            } else {
                MantoPermission.requestLocationPermissionWithScene(this, new IPermission.PermissionCallBack() { // from class: com.jd.manto.lbs.MantoOpenLocationActivity.1
                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onDenied() {
                    }

                    @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                    public void onGranted() {
                        MantoOpenLocationActivity.this.requestLocation();
                    }
                }, "locService", Tools.JD_LOCATION_ID, null);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.map_back) {
            finish();
        } else if (view.getId() == R.id.map_navigate_btn) {
            if (this.mLocation != null) {
                String str = "" + this.mLocation.getName();
                int i2 = 1;
                final String str2 = this.mLocation.getCoordinateType() == 1 ? "gcj02" : "wgs84";
                final Dialog dialog = new Dialog(this, R.style.MantoBottomDialog);
                LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                linearLayout.setBackgroundColor(-1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                int dip2pixel = MantoDensityUtils.dip2pixel(getApplicationContext(), 20);
                layoutParams.width = MantoDensityUtils.getDMWidthPixels();
                linearLayout.setOrientation(1);
                if (MantoMapHelper.isQQMapInstalled(this)) {
                    TextView textView = new TextView(getApplicationContext());
                    textView.setText("\u817e\u8baf\u5730\u56fe");
                    textView.setTextColor(-13750995);
                    textView.setPadding(dip2pixel, dip2pixel, dip2pixel, dip2pixel);
                    textView.setTextSize(2, 16.0f);
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.manto.lbs.MantoOpenLocationActivity.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            dialog.dismiss();
                            MantoOpenLocationActivity mantoOpenLocationActivity = MantoOpenLocationActivity.this;
                            MantoMapHelper.startQQMapNavigate(mantoOpenLocationActivity, mantoOpenLocationActivity.mLocation.getName(), MantoOpenLocationActivity.this.mLocation.getLatitude() + DYConstants.DY_REGEX_COMMA + MantoOpenLocationActivity.this.mLocation.getLongitude(), MantoOpenLocationActivity.this.poiName, MantoOpenLocationActivity.this.kwebmap_slat + DYConstants.DY_REGEX_COMMA + MantoOpenLocationActivity.this.kwebmap_lng);
                        }
                    });
                    linearLayout.addView(textView, layoutParams2);
                } else {
                    i2 = 0;
                }
                if (MantoMapHelper.isBaiDuMapInstalled(this)) {
                    TextView textView2 = new TextView(getApplicationContext());
                    textView2.setText("\u767e\u5ea6\u5730\u56fe");
                    textView2.setTextColor(-13750995);
                    textView2.setPadding(dip2pixel, dip2pixel, dip2pixel, dip2pixel);
                    textView2.setTextSize(2, 16.0f);
                    textView2.setOnClickListener(new View.OnClickListener() { // from class: com.jd.manto.lbs.MantoOpenLocationActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            dialog.dismiss();
                            MantoOpenLocationActivity mantoOpenLocationActivity = MantoOpenLocationActivity.this;
                            MantoMapHelper.startBaiDuMapNavigate(mantoOpenLocationActivity, mantoOpenLocationActivity.mLocation.getName(), MantoOpenLocationActivity.this.mLocation.getLatitude() + DYConstants.DY_REGEX_COMMA + MantoOpenLocationActivity.this.mLocation.getLongitude(), MantoOpenLocationActivity.this.poiName, MantoOpenLocationActivity.this.kwebmap_slat + DYConstants.DY_REGEX_COMMA + MantoOpenLocationActivity.this.kwebmap_lng, str2);
                        }
                    });
                    linearLayout.addView(textView2, layoutParams2);
                    i2++;
                }
                if (MantoMapHelper.isAmapMapInstalled(this)) {
                    TextView textView3 = new TextView(getApplicationContext());
                    textView3.setText("\u9ad8\u5fb7\u5730\u56fe");
                    textView3.setTextColor(-13750995);
                    textView3.setPadding(dip2pixel, dip2pixel, dip2pixel, dip2pixel);
                    textView3.setTextSize(2, 16.0f);
                    textView3.setOnClickListener(new View.OnClickListener() { // from class: com.jd.manto.lbs.MantoOpenLocationActivity.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            dialog.dismiss();
                            MantoOpenLocationActivity mantoOpenLocationActivity = MantoOpenLocationActivity.this;
                            MantoMapHelper.startAmapMapNavigate(mantoOpenLocationActivity, mantoOpenLocationActivity.mLocation.getName(), MantoOpenLocationActivity.this.mLocation.getLatitude(), MantoOpenLocationActivity.this.mLocation.getLongitude(), MantoOpenLocationActivity.this.poiName, MantoOpenLocationActivity.this.kwebmap_slat, MantoOpenLocationActivity.this.kwebmap_lng);
                        }
                    });
                    linearLayout.addView(textView3, layoutParams2);
                    i2++;
                }
                if (i2 == 0) {
                    Toast.makeText(getApplicationContext(), "\u672a\u627e\u5230\u652f\u6301\u7684\u5bfc\u822a\u5e94\u7528", 0).show();
                    return;
                }
                dialog.setContentView(linearLayout);
                linearLayout.getLayoutParams().width = MantoDensityUtils.getDMWidthPixels();
                dialog.getWindow().setGravity(80);
                dialog.getWindow().setTitle(null);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.getWindow().setWindowAnimations(R.style.MantoBottomDialog_Animation);
                dialog.show();
                return;
            }
            Toast.makeText(getApplicationContext(), "\u5b9a\u4f4d\u5931\u8d25\uff0c\u8bf7\u786e\u8ba4\u6743\u9650", 0).show();
        } else if (view.getId() != R.id.map_goto_my_position || this.mLocation == null) {
        } else {
            this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(this.mLocation.getLatitude(), this.mLocation.getLongitude()), 17.0f)));
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        setContentView(R.layout.map_open_location_activity);
        initMapView();
        showMarker();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mMapView.onDestroy();
        super.onDestroy();
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
        if (i2 == 0) {
            this.mLocation = tencentLocation;
            if (Tools.isLatLngValid(tencentLocation.getLatitude(), tencentLocation.getLongitude())) {
                LatLng latLng = new LatLng(tencentLocation.getLatitude(), tencentLocation.getLongitude());
                double d = tencentLocation.getExtra().getDouble("direction");
                Marker marker = this.myDirectionMarker;
                if (marker == null) {
                    this.myDirectionMarker = this.mTencentMap.addMarker(new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_direction)));
                } else {
                    marker.setPosition(latLng);
                }
                this.myDirectionMarker.setRotation(computeRotation((float) d));
                if (MantoMapHelper.isSamePOIByDistance(latLng, this.kwebmap_slat, this.kwebmap_lng)) {
                    this.gotoMyPosition.setImageResource(R.drawable.map_my_pos);
                } else {
                    this.gotoMyPosition.setImageResource(R.drawable.map_other_position_normal);
                }
            }
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        this.mMapView.onPause();
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        IPermission iPermission = (IPermission) a.n(IPermission.class);
        if (iPermission != null) {
            iPermission.onRequestPermissionsResult(this, i2, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        this.mMapView.onResume();
        super.onResume();
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onStatusUpdate(String str, int i2, String str2) {
    }

    @Override // android.app.Activity
    protected void onStop() {
        this.mMapView.onStop();
        super.onStop();
    }
}
