package com.jd.manto.lbs;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.manto.map.R;
import com.jd.manto.map.Tools;
import com.jingdong.a;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationError;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.manto.jsapi.refact.lbs.MapAddress;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoCommonHttpHandler;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoPermission;
import com.jingdong.manto.widget.MantoStatusBarUtil;
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
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class MantoChooseLocationActivity extends Activity implements View.OnClickListener, TencentMap.OnCameraChangeListener, TencentMap.OnMapClickListener, AdapterView.OnItemClickListener {
    private View centerAnchor;
    private LatLng chooseLatLng;
    private boolean firstItemVisible;
    private View footer;
    private ImageView gotoMyPosition;
    private boolean hasMorePoi;
    private boolean hasResizeMap;
    private boolean hasSetCenter;
    private boolean isLoadingMore;
    private ListView listView;
    private View loadingView;
    private TencentLocationManager locationManager;
    private MapView mMapView;
    private TencentMap mTencentMap;
    private View mapContainer;
    private Marker marker;
    private LatLng myCenterLatLng;
    private MantoChooseLocationPOIAdapter poiAdapter;
    private float poiListMovementStartY;
    private LatLng previousLatLng;
    private int previousVisibleItemIndex;
    private TencentLocationRequest request;
    private int resizeHeight;
    private boolean updateFromItemClick;
    private boolean canUpdatePOIS = true;
    private int pageIndex = 1;
    private volatile boolean acceptTouchEvent = true;

    static /* synthetic */ int access$1108(MantoChooseLocationActivity mantoChooseLocationActivity) {
        int i2 = mantoChooseLocationActivity.pageIndex;
        mantoChooseLocationActivity.pageIndex = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMyPosition() {
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId(Tools.JD_LOCATION_ID);
        jDLocationOption.setSceneId("locService");
        JDLocationManager.getInstance().getLatLng(jDLocationOption, new JDLocationListener() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.4
            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onFail(JDLocationError jDLocationError) {
                MantoLog.e("onFail", jDLocationError.getMsg());
            }

            @Override // com.jingdong.common.lbs.jdlocation.JDLocationListener
            public void onSuccess(JDLocation jDLocation) {
                if (Tools.isLatLngValid(jDLocation.getLat(), jDLocation.getLng())) {
                    MantoChooseLocationActivity.this.myCenterLatLng = new LatLng(jDLocation.getLat(), jDLocation.getLng());
                    if (!MantoChooseLocationActivity.this.hasSetCenter) {
                        MantoChooseLocationActivity.this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(MantoChooseLocationActivity.this.myCenterLatLng, 17.0f)));
                        MantoChooseLocationActivity.this.hasSetCenter = true;
                    }
                    if (MantoChooseLocationActivity.this.marker != null) {
                        MantoChooseLocationActivity.this.marker.setPosition(MantoChooseLocationActivity.this.myCenterLatLng);
                    } else {
                        MantoChooseLocationActivity mantoChooseLocationActivity = MantoChooseLocationActivity.this;
                        mantoChooseLocationActivity.marker = mantoChooseLocationActivity.mTencentMap.addMarker(new MarkerOptions().position(MantoChooseLocationActivity.this.myCenterLatLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_mypos_marker)));
                    }
                    MantoChooseLocationActivity.this.updatePosition();
                }
            }
        });
    }

    private void initMapView() {
        this.mapContainer = findViewById(R.id.map_choose_position_map_container);
        this.loadingView = findViewById(R.id.map_choose_position_loading);
        this.listView = (ListView) findViewById(R.id.map_choose_location_listview);
        View inflate = LayoutInflater.from(this).inflate(R.layout.map_poi_list_footer, (ViewGroup) null);
        this.footer = inflate;
        this.listView.addFooterView(inflate);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ViewGroup.LayoutParams layoutParams = MantoChooseLocationActivity.this.mapContainer.getLayoutParams();
                if (layoutParams != null && layoutParams.height >= MantoChooseLocationActivity.this.resizeHeight) {
                    if (motionEvent.getAction() == 0) {
                        if (!MantoChooseLocationActivity.this.acceptTouchEvent) {
                            return false;
                        }
                        MantoChooseLocationActivity.this.poiListMovementStartY = motionEvent.getY();
                    } else if (motionEvent.getAction() == 2) {
                        if (!MantoChooseLocationActivity.this.acceptTouchEvent) {
                            return false;
                        }
                        float y = motionEvent.getY();
                        if (y - MantoChooseLocationActivity.this.poiListMovementStartY >= -30.0f || MantoChooseLocationActivity.this.hasResizeMap) {
                            if (y - MantoChooseLocationActivity.this.poiListMovementStartY > 100.0f && MantoChooseLocationActivity.this.hasResizeMap && MantoChooseLocationActivity.this.firstItemVisible) {
                                MantoLog.i("loc", "onTouch: resize: large");
                                MantoChooseLocationActivity.this.hasResizeMap = false;
                                MantoChooseLocationActivity.this.acceptTouchEvent = false;
                                layoutParams.height += MantoChooseLocationActivity.this.resizeHeight;
                                MantoChooseLocationActivity.this.mapContainer.setLayoutParams(layoutParams);
                                MantoChooseLocationActivity.this.listView.smoothScrollByOffset(-MantoChooseLocationActivity.this.resizeHeight);
                                MantoChooseLocationActivity.this.poiListMovementStartY = -10000.0f;
                                return true;
                            }
                        } else {
                            MantoLog.i("loc", "onTouch: resize:small");
                            MantoChooseLocationActivity.this.hasResizeMap = true;
                            MantoChooseLocationActivity.this.acceptTouchEvent = false;
                            layoutParams.height -= MantoChooseLocationActivity.this.resizeHeight;
                            MantoChooseLocationActivity.this.mapContainer.setLayoutParams(layoutParams);
                            MantoChooseLocationActivity.this.listView.smoothScrollByOffset(MantoChooseLocationActivity.this.resizeHeight);
                            return false;
                        }
                    } else if (motionEvent.getAction() == 1) {
                        MantoLog.i("loc", "onTouch: ACTION_UP: " + motionEvent.getY());
                        MantoChooseLocationActivity.this.acceptTouchEvent = true;
                    }
                }
                return false;
            }
        });
        this.listView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                MantoChooseLocationActivity.this.firstItemVisible = i2 <= 0;
                if (MantoChooseLocationActivity.this.previousVisibleItemIndex > i2) {
                    MantoChooseLocationActivity.this.acceptTouchEvent = true;
                }
                MantoChooseLocationActivity.this.previousVisibleItemIndex = i2;
                if (MantoChooseLocationActivity.this.isLoadingMore || MantoChooseLocationActivity.this.poiAdapter == null || !MantoChooseLocationActivity.this.hasMorePoi || MantoChooseLocationActivity.this.poiAdapter.getCount() % 20 != 0 || i2 + i3 < i4) {
                    return;
                }
                MantoChooseLocationActivity.this.isLoadingMore = true;
                MantoChooseLocationActivity.access$1108(MantoChooseLocationActivity.this);
                MantoLog.i("loc", "onScroll: pageIndex=" + MantoChooseLocationActivity.this.pageIndex);
                MantoChooseLocationActivity.this.updatePoi(true);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.map_goto_my_position);
        this.gotoMyPosition = imageView;
        imageView.setOnClickListener(this);
        this.centerAnchor = findViewById(R.id.map_location_center);
        MapView mapView = (MapView) findViewById(R.id.mapviewOverlay);
        this.mMapView = mapView;
        TencentMap map = mapView.getMap();
        this.mTencentMap = map;
        map.setOnMapClickListener(this);
        this.mTencentMap.setOnCameraChangeListener(this);
        this.locationManager = TencentLocationManager.getInstance(getApplicationContext());
        this.request = TencentLocationRequest.create().setInterval(10000L);
        if (MantoPermission.hasLocationPermissionWithScene("locService", Tools.JD_LOCATION_ID)) {
            getMyPosition();
        } else {
            MantoPermission.requestLocationPermissionWithScene(this, new IPermission.PermissionCallBack() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.3
                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onDenied() {
                }

                @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
                public void onGranted() {
                    MantoChooseLocationActivity.this.getMyPosition();
                }
            }, "locService", Tools.JD_LOCATION_ID, null);
        }
    }

    private void locateToMyPosition() {
        this.hasSetCenter = false;
        this.canUpdatePOIS = true;
        getMyPosition();
    }

    private void moveToChoosedPOI(LatLng latLng) {
        this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, 17.0f)));
        updateMyPositionIconStatus();
    }

    private void showCenterAnim() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.centerAnchor, "translationY", 0.0f, 0.0f, -40.0f);
        ofFloat.setInterpolator(new MantoBounceInterpolator(0.2d, 20.0d));
        ofFloat.setStartDelay(100L);
        ofFloat.setDuration(1500L);
        ofFloat.start();
    }

    private void updateMyPositionIconStatus() {
        this.chooseLatLng = this.mMapView.getMap().getCameraPosition().target;
        MantoLog.d("loc", "updatePosition center : " + this.mMapView.getMap().getCameraPosition().target);
        if (MantoMapHelper.isSamePOIByDistance(this.myCenterLatLng, this.chooseLatLng)) {
            this.gotoMyPosition.setImageResource(R.drawable.map_my_pos);
        } else {
            this.gotoMyPosition.setImageResource(R.drawable.map_other_position_normal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePoi(final boolean z) {
        LatLng latLng = this.chooseLatLng;
        if (latLng == null) {
            return;
        }
        if (!z) {
            if (MantoMapHelper.isSamePOIByDistance(this.previousLatLng, latLng)) {
                MantoLog.d("loc", "updatePoi: receive from position callback , but they are same position, do not update pois");
                return;
            }
            MantoLog.v("loc", "updatePoi: receive from position callback , new position, reload from page 1");
            this.pageIndex = 1;
            this.loadingView.setVisibility(0);
        }
        final int i2 = this.pageIndex;
        MantoCommonHttpHandler.getInstance().commit(new MantoPoiRequest(this.chooseLatLng.getLatitude(), this.chooseLatLng.getLongitude(), i2, MantoMapHelper.getQqMapApiKey(this)), new IMantoHttpListener() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.5
            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onError(JSONObject jSONObject, Throwable th) {
                super.onError(jSONObject, th);
                MantoLog.i("loc", "onError: " + jSONObject);
                MantoLog.v("loc", "onError: " + th);
                MantoChooseLocationActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        MantoChooseLocationActivity.this.isLoadingMore = false;
                        Toast.makeText(MantoChooseLocationActivity.this.getApplicationContext(), "\u83b7\u53d6\u6570\u636e\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u518d\u8bd5", 0).show();
                        if (MantoChooseLocationActivity.this.loadingView.getVisibility() == 0) {
                            MantoChooseLocationActivity.this.loadingView.setVisibility(8);
                        }
                        if (MantoChooseLocationActivity.this.poiAdapter != null) {
                            MantoChooseLocationActivity.this.listView.smoothScrollToPosition(MantoChooseLocationActivity.this.poiAdapter.getCount());
                        }
                    }
                });
            }

            @Override // com.jingdong.manto.network.common.IMantoHttpListener
            public void onSuccess(final JSONObject jSONObject) {
                if (jSONObject.optInt("status", -1) != 0) {
                    MantoChooseLocationActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MantoChooseLocationActivity.this.isLoadingMore = false;
                            if (MantoChooseLocationActivity.this.loadingView.getVisibility() == 0) {
                                MantoChooseLocationActivity.this.loadingView.setVisibility(8);
                            }
                            Context applicationContext = MantoChooseLocationActivity.this.getApplicationContext();
                            JSONObject jSONObject2 = jSONObject;
                            Toast.makeText(applicationContext, jSONObject2.optString("message", jSONObject2.toString()), 0).show();
                        }
                    });
                    return;
                }
                final JSONObject optJSONObject = jSONObject.optJSONObject("result");
                int optInt = optJSONObject.optInt("poi_count");
                JSONArray optJSONArray = optJSONObject.optJSONArray("pois");
                if (optJSONArray.length() == 0) {
                    MantoLog.d("loc", "onSuccess: no pois:" + jSONObject);
                } else {
                    MantoLog.i("loc", "onSuccess: pois:" + optJSONArray);
                }
                if (MantoChooseLocationActivity.this.poiAdapter == null || 1 == i2 || !z) {
                    MantoChooseLocationActivity.this.hasMorePoi = optInt > i2 * 20;
                } else {
                    MantoChooseLocationActivity mantoChooseLocationActivity = MantoChooseLocationActivity.this;
                    mantoChooseLocationActivity.hasMorePoi = optInt > mantoChooseLocationActivity.poiAdapter.getCount() && optInt > i2 * 20;
                }
                MantoLog.v("loc", "onSuccess: hasMorePoi=" + MantoChooseLocationActivity.this.hasMorePoi + ", page=" + i2 + ", poiCount=" + optInt);
                final ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i3);
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                    MapAddress mapAddress = new MapAddress();
                    mapAddress.name = optJSONObject2.optString("title");
                    mapAddress.address = optJSONObject2.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
                    mapAddress.latitude = optJSONObject3.optDouble("lat");
                    mapAddress.longitude = optJSONObject3.optDouble(HybridSDK.LNG);
                    arrayList.add(mapAddress);
                }
                MantoChooseLocationActivity.this.runOnUiThread(new Runnable() { // from class: com.jd.manto.lbs.MantoChooseLocationActivity.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (arrayList.size() > 0) {
                            if (MantoChooseLocationActivity.this.poiAdapter == null) {
                                MantoChooseLocationActivity.this.poiAdapter = new MantoChooseLocationPOIAdapter(MantoChooseLocationActivity.this.getApplicationContext());
                                MantoChooseLocationActivity.this.poiAdapter.setData(arrayList);
                                MantoChooseLocationActivity.this.listView.setAdapter((ListAdapter) MantoChooseLocationActivity.this.poiAdapter);
                            } else {
                                AnonymousClass5 anonymousClass5 = AnonymousClass5.this;
                                if (z) {
                                    MantoChooseLocationActivity.this.poiAdapter.appendData(arrayList);
                                    MantoChooseLocationActivity.this.poiAdapter.notifyDataSetChanged();
                                } else {
                                    MantoChooseLocationActivity.this.poiAdapter.setData(arrayList);
                                    MantoChooseLocationActivity.this.poiAdapter.notifyDataSetChanged();
                                    MantoChooseLocationActivity.this.listView.smoothScrollToPosition(0);
                                }
                            }
                        } else if (!z) {
                            JSONObject optJSONObject4 = optJSONObject.optJSONObject(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                            JSONObject optJSONObject5 = optJSONObject.optJSONObject("formatted_addresses");
                            MapAddress mapAddress2 = new MapAddress();
                            if (optJSONObject5 != null) {
                                mapAddress2.name = optJSONObject5.optString("title");
                            }
                            mapAddress2.address = optJSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
                            if (TextUtils.isEmpty(mapAddress2.name)) {
                                mapAddress2.name = mapAddress2.address;
                            }
                            mapAddress2.latitude = optJSONObject4.optDouble("lat");
                            mapAddress2.longitude = optJSONObject4.optDouble(HybridSDK.LNG);
                            arrayList.add(mapAddress2);
                            if (MantoChooseLocationActivity.this.poiAdapter == null) {
                                MantoChooseLocationActivity.this.poiAdapter = new MantoChooseLocationPOIAdapter(MantoChooseLocationActivity.this.getApplicationContext());
                            }
                            MantoChooseLocationActivity.this.poiAdapter.setData(arrayList);
                            MantoChooseLocationActivity.this.listView.setAdapter((ListAdapter) MantoChooseLocationActivity.this.poiAdapter);
                            MantoChooseLocationActivity.this.listView.smoothScrollToPosition(0);
                        }
                        if (!MantoChooseLocationActivity.this.hasMorePoi || MantoChooseLocationActivity.this.poiAdapter.getCount() % 20 != 0) {
                            if (MantoChooseLocationActivity.this.listView.getFooterViewsCount() > 0) {
                                MantoChooseLocationActivity.this.listView.removeFooterView(MantoChooseLocationActivity.this.footer);
                            }
                        } else if (MantoChooseLocationActivity.this.listView.getFooterViewsCount() == 0) {
                            MantoChooseLocationActivity.this.listView.addFooterView(MantoChooseLocationActivity.this.footer);
                        }
                        MantoChooseLocationActivity.this.isLoadingMore = false;
                        if (MantoChooseLocationActivity.this.loadingView.getVisibility() == 0) {
                            MantoChooseLocationActivity.this.loadingView.setVisibility(8);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePosition() {
        updateMyPositionIconStatus();
        if (this.canUpdatePOIS) {
            updatePoi(false);
        }
        this.previousLatLng = this.chooseLatLng;
    }

    public int getLayoutId() {
        return R.layout.map_choose_position_activity;
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        MapAddress mapAddress;
        super.onActivityResult(i2, i3, intent);
        if (1 != i2 || i3 != -1 || intent == null || (mapAddress = (MapAddress) intent.getParcelableExtra("key_search_choosed_addr")) == null) {
            return;
        }
        this.canUpdatePOIS = true;
        if (Tools.isLatLngValid(mapAddress.latitude, mapAddress.longitude)) {
            this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(mapAddress.latitude, mapAddress.longitude), 17.0f)));
            updatePosition();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChangeFinished(CameraPosition cameraPosition) {
        MantoLog.d("loc", "onCameraChangeFinish: " + cameraPosition + ", updateFromItemClick:" + this.updateFromItemClick);
        if (this.updateFromItemClick) {
            this.updateFromItemClick = false;
            this.canUpdatePOIS = false;
        } else {
            this.canUpdatePOIS = true;
        }
        updatePosition();
        showCenterAnim();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.map_choose_position_cancel) {
            setResult(0);
            finish();
        } else if (view.getId() == R.id.map_choose_position_sure && this.poiAdapter != null) {
            Intent intent = new Intent();
            MapAddress choosedPOI = this.poiAdapter.getChoosedPOI();
            if (choosedPOI != null) {
                intent.putExtra("key_pick_addr", choosedPOI);
            }
            setResult(-1, intent);
            finish();
        } else if (view.getId() == R.id.map_goto_my_position) {
            locateToMyPosition();
        } else if (view.getId() == R.id.map_choose_position_search) {
            Intent intent2 = new Intent(this, MantoMapSearchActivity.class);
            LatLng latLng = this.myCenterLatLng;
            if (latLng != null) {
                intent2.putExtra("lat", latLng.getLatitude());
                intent2.putExtra(HybridSDK.LNG, this.myCenterLatLng.getLongitude());
            }
            startActivityForResult(intent2, 1);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        MantoStatusBarUtil.setStatusBarColor(this, -1, true);
        this.resizeHeight = MantoDensityUtils.dip2pixel(getApplicationContext(), 130);
        initMapView();
        findViewById(R.id.map_choose_position_cancel).setOnClickListener(this);
        findViewById(R.id.map_choose_position_sure).setOnClickListener(this);
        findViewById(R.id.map_choose_position_search).setOnClickListener(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.mMapView.onDestroy();
        super.onDestroy();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        this.canUpdatePOIS = false;
        this.poiAdapter.setSelectPosition(i2);
        this.poiAdapter.notifyDataSetChanged();
        this.updateFromItemClick = true;
        MapAddress choosedPOI = this.poiAdapter.getChoosedPOI();
        if (Tools.isLatLngValid(choosedPOI.latitude, choosedPOI.longitude)) {
            moveToChoosedPOI(new LatLng(choosedPOI.latitude, choosedPOI.longitude));
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
        this.canUpdatePOIS = true;
        this.chooseLatLng = latLng;
        this.myCenterLatLng = latLng;
        this.mTencentMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, 17.0f)));
        updatePosition();
        showCenterAnim();
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
        getMyPosition();
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStop() {
        this.mMapView.onStop();
        super.onStop();
    }
}
