package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.permission.PermissionConstants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdreact.plugin.utils.FrescoUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.jsapi.refact.lbs.JsApiLocation;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDReactMapView extends MapView implements TencentMap.OnMarkerDragListener, LifecycleEventListener {
    private static final String TAG = "JDReactMapView";
    private ViewAttacherGroup attacherGroup;
    private final int baseMapPadding;
    private LatLngBounds boundsToMove;
    private String businessId;
    private boolean cacheEnabled;
    private ImageView cacheImageView;
    private final ThemedReactContext context;
    private boolean destroyed;
    private final EventDispatcher eventDispatcher;
    private final List<JDReactMapFeature> features;
    private final GestureDetectorCompat gestureDetector;
    private boolean handlePanDrag;
    private boolean initialRegionSet;
    private Boolean isMapLoaded;
    private boolean isMonitoringRegion;
    private boolean isScrollGesturesEnabled;
    private boolean isTouchDown;
    private LatLngBounds lastBoundsEmitted;
    private LifecycleEventListener lifecycleListener;
    private Integer loadingBackgroundColor;
    private Integer loadingIndicatorColor;
    private boolean mHasRequestPermission;
    private boolean mLastLocationPermission;
    private LatLng mLatLng;
    private Bitmap mLocationIconBitmap;
    private Marker mLocationMarker;
    private String mLocationTitle;
    private boolean mShowLocation;
    private boolean mTrackLocation;
    public TencentMap map;
    private RelativeLayout mapLoadingLayout;
    private ProgressBar mapLoadingProgressBar;
    private final Map<Marker, JDReactMapMarker> markerMap;
    private final Runnable measureAndLayout;
    private boolean moveOnMarkerPress;
    private boolean paused;
    private final Map<Polygon, JDReactMapPolygon> polygonMap;
    private final Map<Polyline, JDReactMapPolyline> polylineMap;
    private final ScaleGestureDetector scaleDetector;
    private String sceneId;
    Handler timerHandler;
    Runnable timerRunnable;

    public JDReactMapView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.map = getMap();
        this.isMapLoaded = Boolean.FALSE;
        this.loadingBackgroundColor = null;
        this.loadingIndicatorColor = null;
        this.baseMapPadding = 50;
        this.paused = false;
        this.destroyed = false;
        this.handlePanDrag = false;
        this.moveOnMarkerPress = true;
        this.isMonitoringRegion = false;
        this.isTouchDown = false;
        this.cacheEnabled = false;
        this.initialRegionSet = false;
        this.features = new ArrayList();
        this.markerMap = new HashMap();
        this.polylineMap = new HashMap();
        this.polygonMap = new HashMap();
        this.isScrollGesturesEnabled = true;
        this.sceneId = "locService";
        this.businessId = PermissionConstants.LBS_JD_TO_HOME;
        this.mLocationIconBitmap = null;
        this.timerHandler = new Handler();
        this.timerRunnable = new Runnable() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.12
            @Override // java.lang.Runnable
            public void run() {
                TencentMap tencentMap = JDReactMapView.this.map;
                if (tencentMap != null) {
                    Projection projection = tencentMap.getProjection();
                    VisibleRegion visibleRegion = projection != null ? projection.getVisibleRegion() : null;
                    LatLngBounds latLngBounds = visibleRegion != null ? visibleRegion.latLngBounds : null;
                    if (latLngBounds != null && (JDReactMapView.this.lastBoundsEmitted == null || latLngBounds.equals(JDReactMapView.this.lastBoundsEmitted))) {
                        LatLng latLng = JDReactMapView.this.map.getCameraPosition().target;
                        JDReactMapView.this.lastBoundsEmitted = latLngBounds;
                        JDReactMapView.this.eventDispatcher.dispatchEvent(new RegionChangeEvent(JDReactMapView.this.getId(), latLngBounds, latLng, true));
                    }
                }
                JDReactMapView.this.timerHandler.postDelayed(this, 100L);
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.14
            @Override // java.lang.Runnable
            public void run() {
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.measure(View.MeasureSpec.makeMeasureSpec(jDReactMapView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(JDReactMapView.this.getHeight(), 1073741824));
                JDReactMapView jDReactMapView2 = JDReactMapView.this;
                jDReactMapView2.layout(jDReactMapView2.getLeft(), JDReactMapView.this.getTop(), JDReactMapView.this.getRight(), JDReactMapView.this.getBottom());
            }
        };
        this.mShowLocation = false;
        this.mHasRequestPermission = false;
        this.mLastLocationPermission = false;
        this.mTrackLocation = false;
        themedReactContext.addLifecycleEventListener(this);
        this.context = themedReactContext;
        this.map.setSatelliteEnabled(false);
        super.onResume();
        prepareLocationIconBitmap(themedReactContext);
        this.scaleDetector = new ScaleGestureDetector(themedReactContext, new ScaleGestureDetector.SimpleOnScaleGestureListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.1
            @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                this.startMonitoringRegion();
                return true;
            }
        });
        this.gestureDetector = new GestureDetectorCompat(themedReactContext, new GestureDetector.SimpleOnGestureListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                this.startMonitoringRegion();
                return false;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
                if (JDReactMapView.this.handlePanDrag) {
                    JDReactMapView.this.onPanDrag(motionEvent2);
                }
                this.startMonitoringRegion();
                return false;
            }
        });
        addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.3
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                if (JDReactMapView.this.paused) {
                    return;
                }
                JDReactMapView.this.cacheView();
            }
        });
        this.eventDispatcher = ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        this.map.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.4
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                JDReactMapMarker jDReactMapMarker = (JDReactMapMarker) JDReactMapView.this.markerMap.get(marker);
                WritableMap makeClickEventData = JDReactMapView.this.makeClickEventData(marker.getPosition());
                makeClickEventData.putString("action", "marker-press");
                if (jDReactMapMarker != null && jDReactMapMarker.getIdentifier() != null) {
                    makeClickEventData.putString("id", jDReactMapMarker.getIdentifier());
                }
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.pushEvent(jDReactMapView.context, this, "onMarkerPress", makeClickEventData);
                WritableMap makeClickEventData2 = JDReactMapView.this.makeClickEventData(marker.getPosition());
                makeClickEventData2.putString("action", "marker-press");
                if (jDReactMapMarker != null && jDReactMapMarker.getIdentifier() != null) {
                    makeClickEventData2.putString("id", jDReactMapMarker.getIdentifier());
                }
                JDReactMapView jDReactMapView2 = JDReactMapView.this;
                jDReactMapView2.pushEvent(jDReactMapView2.context, (View) JDReactMapView.this.markerMap.get(marker), "onPress", makeClickEventData2);
                if (this.moveOnMarkerPress) {
                    JDReactMapView.this.map.moveCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                    return false;
                }
                return true;
            }
        });
        this.map.setOnInfoWindowClickListener(new TencentMap.OnInfoWindowClickListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.5
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClick(Marker marker) {
                WritableMap makeClickEventData = JDReactMapView.this.makeClickEventData(marker.getPosition());
                makeClickEventData.putString("action", "callout-press");
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.pushEvent(jDReactMapView.context, this, "onCalloutPress", makeClickEventData);
                JDReactMapMarker jDReactMapMarker = (JDReactMapMarker) JDReactMapView.this.markerMap.get(marker);
                if (jDReactMapMarker == null) {
                    return;
                }
                WritableMap makeClickEventData2 = JDReactMapView.this.makeClickEventData(marker.getPosition());
                makeClickEventData2.putString("action", "callout-press");
                JDReactMapView jDReactMapView2 = JDReactMapView.this;
                jDReactMapView2.pushEvent(jDReactMapView2.context, jDReactMapMarker, "onCalloutPress", makeClickEventData2);
                WritableMap makeClickEventData3 = JDReactMapView.this.makeClickEventData(marker.getPosition());
                makeClickEventData3.putString("action", "callout-press");
                JDReactMapCallout calloutView = jDReactMapMarker.getCalloutView();
                if (calloutView != null) {
                    JDReactMapView jDReactMapView3 = JDReactMapView.this;
                    jDReactMapView3.pushEvent(jDReactMapView3.context, calloutView, "onPress", makeClickEventData3);
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClickLocation(int i2, int i3, int i4, int i5) {
            }
        });
        this.map.setOnMapClickListener(new TencentMap.OnMapClickListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.6
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapClickListener
            public void onMapClick(LatLng latLng) {
                WritableMap makeClickEventData = JDReactMapView.this.makeClickEventData(latLng);
                makeClickEventData.putString("action", "press");
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.pushEvent(jDReactMapView.context, this, "onPress", makeClickEventData);
            }
        });
        this.map.setOnCameraChangeListener(new TencentMap.OnCameraChangeListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.7
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
            public void onCameraChange(CameraPosition cameraPosition) {
                if (JDReactMapView.this.isTouchDown) {
                    LatLngBounds latLngBounds = JDReactMapView.this.map.getProjection().getVisibleRegion().latLngBounds;
                    LatLng latLng = cameraPosition.target;
                    JDReactMapView.this.lastBoundsEmitted = latLngBounds;
                    JDReactMapView.this.eventDispatcher.dispatchEvent(new RegionChangeEvent(JDReactMapView.this.getId(), latLngBounds, latLng, JDReactMapView.this.isTouchDown));
                    this.stopMonitoringRegion();
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
            public void onCameraChangeFinished(CameraPosition cameraPosition) {
                LatLngBounds latLngBounds = JDReactMapView.this.map.getProjection().getVisibleRegion().latLngBounds;
                JDReactMapView.this.lastBoundsEmitted = latLngBounds;
                JDReactMapView.this.eventDispatcher.dispatchEvent(new RegionChangeEvent(JDReactMapView.this.getId(), latLngBounds, JDReactMapView.this.map.getCameraPosition().target, false));
                this.stopMonitoringRegion();
            }
        });
        this.map.setOnMapLongClickListener(new TencentMap.OnMapLongClickListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.8
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLongClickListener
            public void onMapLongClick(LatLng latLng) {
                JDReactMapView.this.makeClickEventData(latLng).putString("action", "long-press");
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.pushEvent(jDReactMapView.context, this, "onLongPress", JDReactMapView.this.makeClickEventData(latLng));
            }
        });
        this.map.setOnMapLoadedCallback(new TencentMap.OnMapLoadedCallback() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.9
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLoadedCallback
            public void onMapLoaded() {
                JDReactMapView.this.isMapLoaded = Boolean.TRUE;
                JDReactMapView.this.cacheView();
                JDReactMapView jDReactMapView = JDReactMapView.this;
                jDReactMapView.pushEvent(jDReactMapView.context, this, "onMapReady", new WritableNativeMap());
            }
        });
        this.map.setInfoWindowAdapter(new TencentMap.InfoWindowAdapter() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.10
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                return null;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                JDReactMapMarker jDReactMapMarker = (JDReactMapMarker) JDReactMapView.this.markerMap.get(marker);
                if (jDReactMapMarker == null) {
                    return null;
                }
                return jDReactMapMarker.getCallout();
            }
        });
        LifecycleEventListener lifecycleEventListener = new LifecycleEventListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.11
            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostDestroy() {
                JDReactMapView.this.doDestroy();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostPause() {
                synchronized (JDReactMapView.this) {
                    if (!JDReactMapView.this.destroyed) {
                        JDReactMapView.this.onPause();
                    }
                    JDReactMapView.this.paused = true;
                }
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostResume() {
                synchronized (JDReactMapView.this) {
                    if (!JDReactMapView.this.destroyed) {
                        JDReactMapView.this.onResume();
                    }
                    JDReactMapView.this.paused = false;
                }
            }
        };
        this.lifecycleListener = lifecycleEventListener;
        themedReactContext.addLifecycleEventListener(lifecycleEventListener);
        this.attacherGroup = new ViewAttacherGroup(themedReactContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0);
        layoutParams.width = 0;
        layoutParams.height = 0;
        layoutParams.leftMargin = 99999999;
        layoutParams.topMargin = 99999999;
        this.attacherGroup.setLayoutParams(layoutParams);
        addView(this.attacherGroup);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheView() {
        if (this.cacheEnabled) {
            final ImageView cacheImageView = getCacheImageView();
            final RelativeLayout mapLoadingLayoutView = getMapLoadingLayoutView();
            cacheImageView.setVisibility(4);
            mapLoadingLayoutView.setVisibility(0);
            if (this.isMapLoaded.booleanValue()) {
                this.map.snapshot(new TencentMap.SnapshotReadyCallback() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.13
                    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.SnapshotReadyCallback
                    public void onSnapshotReady(Bitmap bitmap) {
                        cacheImageView.setImageBitmap(bitmap);
                        cacheImageView.setVisibility(0);
                        mapLoadingLayoutView.setVisibility(4);
                    }
                });
                return;
            }
            return;
        }
        removeCacheImageView();
        if (this.isMapLoaded.booleanValue()) {
            removeMapLoadingLayoutView();
        }
    }

    private static boolean contextHasBug(Context context) {
        return context == null || context.getResources() == null || context.getResources().getConfiguration() == null;
    }

    private ImageView getCacheImageView() {
        if (this.cacheImageView == null) {
            ImageView imageView = new ImageView(getContext());
            this.cacheImageView = imageView;
            addView(imageView, new ViewGroup.LayoutParams(-1, -1));
            this.cacheImageView.setVisibility(4);
        }
        return this.cacheImageView;
    }

    private RelativeLayout getMapLoadingLayoutView() {
        if (this.mapLoadingLayout == null) {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            this.mapLoadingLayout = relativeLayout;
            relativeLayout.setBackgroundColor(-3355444);
            addView(this.mapLoadingLayout, new ViewGroup.LayoutParams(-1, -1));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mapLoadingLayout.addView(getMapLoadingProgressBar(), layoutParams);
            this.mapLoadingLayout.setVisibility(4);
        }
        setLoadingBackgroundColor(this.loadingBackgroundColor);
        return this.mapLoadingLayout;
    }

    private ProgressBar getMapLoadingProgressBar() {
        if (this.mapLoadingProgressBar == null) {
            ProgressBar progressBar = new ProgressBar(getContext());
            this.mapLoadingProgressBar = progressBar;
            progressBar.setIndeterminate(true);
        }
        Integer num = this.loadingIndicatorColor;
        if (num != null) {
            setLoadingIndicatorColor(num);
        }
        return this.mapLoadingProgressBar;
    }

    private static Context getNonBuggyContext(ThemedReactContext themedReactContext, ReactApplicationContext reactApplicationContext) {
        if (!contextHasBug(reactApplicationContext.getCurrentActivity())) {
            return reactApplicationContext.getCurrentActivity();
        }
        if (contextHasBug(themedReactContext)) {
            if (contextHasBug(themedReactContext.getCurrentActivity())) {
                return !contextHasBug(themedReactContext.getApplicationContext()) ? themedReactContext.getApplicationContext() : themedReactContext;
            }
            return themedReactContext.getCurrentActivity();
        }
        return themedReactContext;
    }

    private void performRequestLocation() {
        try {
            if (TextUtils.isEmpty(this.sceneId)) {
                this.sceneId = "locService";
            }
            if (TextUtils.isEmpty(this.businessId)) {
                this.businessId = PermissionConstants.LBS_JD_TO_HOME;
            }
            JDReactHelper.newInstance().getLocationLatLng(this.sceneId, this.businessId, new JDReactHelper.AddressCallBack() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.15
                @Override // com.jingdong.common.jdreactFramework.JDReactHelper.AddressCallBack
                public void suc(HashMap<String, Object> hashMap) {
                    try {
                        if (hashMap == null) {
                            JLog.d(JDReactMapView.TAG, "locationLatLng is null");
                            return;
                        }
                        JDReactMapView.this.mLatLng = new LatLng(((Double) hashMap.get(LocManager.LAT_KEY)).doubleValue(), ((Double) hashMap.get(LocManager.LNG_KEY)).doubleValue());
                        if (JDReactMapView.this.mShowLocation) {
                            if (JDReactMapView.this.mLocationMarker != null) {
                                JDReactMapView.this.mLocationMarker.setPosition(JDReactMapView.this.mLatLng);
                                if (JDReactMapView.this.mTrackLocation) {
                                    JDReactMapView.this.moveToLocation();
                                }
                            } else {
                                JDReactMapView jDReactMapView = JDReactMapView.this;
                                jDReactMapView.mLocationMarker = jDReactMapView.getMap().addMarker(new MarkerOptions().position(JDReactMapView.this.mLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.jdreact_map_marker)).anchor(0.5f, 0.5f));
                                JDReactMapView.this.moveToLocation();
                            }
                            JDReactMapView.this.updateLocationTitle();
                            JDReactMapView.this.emitLocationChangeEvent(hashMap);
                        }
                    } catch (Exception unused) {
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    private void prepareLocationIconBitmap(Context context) {
        FrescoUtil.loadImage(context, "https://img30.360buyimg.com/njmobilecms/jfs/t1/21882/3/5039/1954/5c36fc13Ecb38e5ff/908d50072deae8a2.png", new FrescoUtil.ImageLoadListener() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapView.16
            @Override // com.jingdong.jdreact.plugin.utils.FrescoUtil.ImageLoadListener
            public void onLoadFailed(String str, Throwable th) {
            }

            @Override // com.jingdong.jdreact.plugin.utils.FrescoUtil.ImageLoadListener
            public void onLoadSuccess(String str, Bitmap bitmap) {
                String str2 = "get LocationIconBitmap success:" + str;
                JDReactMapView.this.mLocationIconBitmap = bitmap;
            }
        });
    }

    private void removeCacheImageView() {
        ImageView imageView = this.cacheImageView;
        if (imageView != null) {
            ((ViewGroup) imageView.getParent()).removeView(this.cacheImageView);
            this.cacheImageView = null;
        }
    }

    private void removeMapLoadingLayoutView() {
        removeMapLoadingProgressBar();
        RelativeLayout relativeLayout = this.mapLoadingLayout;
        if (relativeLayout != null) {
            ((ViewGroup) relativeLayout.getParent()).removeView(this.mapLoadingLayout);
            this.mapLoadingLayout = null;
        }
    }

    private void removeMapLoadingProgressBar() {
        ProgressBar progressBar = this.mapLoadingProgressBar;
        if (progressBar != null) {
            ((ViewGroup) progressBar.getParent()).removeView(this.mapLoadingProgressBar);
            this.mapLoadingProgressBar = null;
        }
    }

    private void requestLocation() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLocationTitle() {
        updateLocationTitle(this.mLocationTitle);
    }

    public void addFeature(View view, int i2) {
        if (view instanceof JDReactMapMarker) {
            JDReactMapMarker jDReactMapMarker = (JDReactMapMarker) view;
            jDReactMapMarker.addToMap(this.map);
            this.features.add(i2, jDReactMapMarker);
            int visibility = jDReactMapMarker.getVisibility();
            jDReactMapMarker.setVisibility(4);
            ViewGroup viewGroup = (ViewGroup) jDReactMapMarker.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(jDReactMapMarker);
            }
            this.attacherGroup.addView(jDReactMapMarker);
            jDReactMapMarker.setVisibility(visibility);
            this.markerMap.put((Marker) jDReactMapMarker.getFeature(), jDReactMapMarker);
        } else if (view instanceof JDReactMapPolyline) {
            JDReactMapPolyline jDReactMapPolyline = (JDReactMapPolyline) view;
            jDReactMapPolyline.addToMap(this.map);
            this.features.add(i2, jDReactMapPolyline);
            this.polylineMap.put((Polyline) jDReactMapPolyline.getFeature(), jDReactMapPolyline);
        } else if (view instanceof JDReactMapPolygon) {
            JDReactMapPolygon jDReactMapPolygon = (JDReactMapPolygon) view;
            jDReactMapPolygon.addToMap(this.map);
            this.features.add(i2, jDReactMapPolygon);
            this.polygonMap.put((Polygon) jDReactMapPolygon.getFeature(), jDReactMapPolygon);
        } else if (view instanceof JDReactMapCircle) {
            JDReactMapCircle jDReactMapCircle = (JDReactMapCircle) view;
            jDReactMapCircle.addToMap(this.map);
            this.features.add(i2, jDReactMapCircle);
        } else if (view instanceof JDReactMapUrlTile) {
            JDReactMapUrlTile jDReactMapUrlTile = (JDReactMapUrlTile) view;
            jDReactMapUrlTile.addToMap(this.map);
            this.features.add(i2, jDReactMapUrlTile);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) view;
            for (int i3 = 0; i3 < viewGroup2.getChildCount(); i3++) {
                addFeature(viewGroup2.getChildAt(i3), i2);
            }
        }
    }

    public void animateToCoordinate(LatLng latLng, int i2) {
        if (this.map != null) {
            startMonitoringRegion();
            this.map.animateCamera(CameraUpdateFactory.newLatLng(latLng), i2, null);
        }
    }

    public void animateToRegion(LatLngBounds latLngBounds, int i2) {
        if (this.map != null) {
            startMonitoringRegion();
            this.map.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0), i2, null);
        }
    }

    public void cancelLocation() {
        Marker marker = this.mLocationMarker;
        if (marker != null) {
            marker.remove();
            this.mLocationMarker = null;
        }
        this.mLatLng = null;
        this.mLocationTitle = null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.MapView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.scaleDetector.onTouchEvent(motionEvent);
        this.gestureDetector.onTouchEvent(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        boolean z = false;
        if (actionMasked == 0) {
            ViewParent parent = getParent();
            if (this.map != null && this.isScrollGesturesEnabled) {
                z = true;
            }
            parent.requestDisallowInterceptTouchEvent(z);
            this.isTouchDown = true;
        } else if (actionMasked == 1) {
            getParent().requestDisallowInterceptTouchEvent(false);
            this.isTouchDown = false;
        } else if (actionMasked == 2) {
            startMonitoringRegion();
        }
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public synchronized void doDestroy() {
        ThemedReactContext themedReactContext;
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        LifecycleEventListener lifecycleEventListener = this.lifecycleListener;
        if (lifecycleEventListener != null && (themedReactContext = this.context) != null) {
            themedReactContext.removeLifecycleEventListener(lifecycleEventListener);
            this.lifecycleListener = null;
        }
        if (!this.paused) {
            onPause();
            this.paused = true;
        }
        onDestroy();
    }

    public void emitLocationChangeEvent(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("name", (String) hashMap.get("detailAddress"));
        writableNativeMap.putString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, ((String) hashMap.get("provinceName")) + ((String) hashMap.get("cityName")) + ((String) hashMap.get("districtName")) + ((String) hashMap.get("townName")) + ((String) hashMap.get("detailAddress")));
        writableNativeMap.putDouble(PdLVBody.LATITUDE, ((Double) hashMap.get(LocManager.LAT_KEY)).doubleValue());
        writableNativeMap.putDouble(PdLVBody.LONGITUDE, ((Double) hashMap.get(LocManager.LNG_KEY)).doubleValue());
        writableNativeMap.putDouble("altitude", ((Double) hashMap.get("altitude")).doubleValue());
        pushEvent(this.context, this, JsApiLocation.LOC_CHANGE_EVNET_NAME, writableNativeMap);
    }

    public void enableMapLoading(boolean z) {
        if (!z || this.isMapLoaded.booleanValue()) {
            return;
        }
        getMapLoadingLayoutView().setVisibility(0);
    }

    public void fitToCoordinates(ReadableArray readableArray, ReadableMap readableMap, boolean z) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            ReadableMap map = readableArray.getMap(i2);
            builder.include(new LatLng(Double.valueOf(map.getDouble(PdLVBody.LATITUDE)).doubleValue(), Double.valueOf(map.getDouble(PdLVBody.LONGITUDE)).doubleValue()));
        }
        CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
        if (z) {
            startMonitoringRegion();
            this.map.animateCamera(newLatLngBounds);
            return;
        }
        this.map.moveCamera(newLatLngBounds);
    }

    public void fitToElements(boolean z) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        boolean z2 = false;
        for (JDReactMapFeature jDReactMapFeature : this.features) {
            if (jDReactMapFeature instanceof JDReactMapMarker) {
                builder.include(((Marker) jDReactMapFeature.getFeature()).getPosition());
                z2 = true;
            }
        }
        if (z2) {
            CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
            if (z) {
                startMonitoringRegion();
                this.map.animateCamera(newLatLngBounds);
                return;
            }
            this.map.moveCamera(newLatLngBounds);
        }
    }

    public void fitToSuppliedMarkers(ReadableArray readableArray, boolean z) {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        String[] strArr = new String[readableArray.size()];
        boolean z2 = false;
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            strArr[i2] = readableArray.getString(i2);
        }
        List asList = Arrays.asList(strArr);
        for (JDReactMapFeature jDReactMapFeature : this.features) {
            if (jDReactMapFeature instanceof JDReactMapMarker) {
                String identifier = ((JDReactMapMarker) jDReactMapFeature).getIdentifier();
                Marker marker = (Marker) jDReactMapFeature.getFeature();
                if (asList.contains(identifier)) {
                    builder.include(marker.getPosition());
                    z2 = true;
                }
            }
        }
        if (z2) {
            CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(builder.build(), 50);
            if (z) {
                startMonitoringRegion();
                this.map.animateCamera(newLatLngBounds);
                return;
            }
            this.map.moveCamera(newLatLngBounds);
        }
    }

    public View getFeatureAt(int i2) {
        return this.features.get(i2);
    }

    public int getFeatureCount() {
        return this.features.size();
    }

    public WritableMap makeClickEventData(LatLng latLng) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble(PdLVBody.LATITUDE, latLng.getLatitude());
        writableNativeMap2.putDouble(PdLVBody.LONGITUDE, latLng.getLongitude());
        writableNativeMap.putMap("coordinate", writableNativeMap2);
        Point screenLocation = this.map.getProjection().toScreenLocation(latLng);
        WritableNativeMap writableNativeMap3 = new WritableNativeMap();
        writableNativeMap3.putDouble(JshopConst.JSHOP_PROMOTIO_X, screenLocation.x);
        writableNativeMap3.putDouble(JshopConst.JSHOP_PROMOTIO_Y, screenLocation.y);
        writableNativeMap.putMap("position", writableNativeMap3);
        return writableNativeMap;
    }

    public void moveToLocation() {
        if (getMap() == null || this.mLatLng == null) {
            return;
        }
        getMap().animateCamera(CameraUpdateFactory.newLatLng(this.mLatLng));
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        cancelLocation();
        doDestroy();
        Bitmap bitmap = this.mLocationIconBitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.mLocationIconBitmap.recycle();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        cancelLocation();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        setShowLocationEnable(this.mShowLocation);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDrag(Marker marker) {
        pushEvent(this.context, this, "onMarkerDrag", makeClickEventData(marker.getPosition()));
        WritableMap makeClickEventData = makeClickEventData(marker.getPosition());
        pushEvent(this.context, this.markerMap.get(marker), "onDrag", makeClickEventData);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDragEnd(Marker marker) {
        pushEvent(this.context, this, "onMarkerDragEnd", makeClickEventData(marker.getPosition()));
        WritableMap makeClickEventData = makeClickEventData(marker.getPosition());
        pushEvent(this.context, this.markerMap.get(marker), "onDragEnd", makeClickEventData);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDragStart(Marker marker) {
        pushEvent(this.context, this, "onMarkerDragStart", makeClickEventData(marker.getPosition()));
        WritableMap makeClickEventData = makeClickEventData(marker.getPosition());
        pushEvent(this.context, this.markerMap.get(marker), "onDragStart", makeClickEventData);
    }

    public void onPanDrag(MotionEvent motionEvent) {
        pushEvent(this.context, this, "onPanDrag", makeClickEventData(this.map.getProjection().fromScreenLocation(new Point((int) motionEvent.getX(), (int) motionEvent.getY()))));
    }

    void pushEvent(ThemedReactContext themedReactContext, View view, String str, WritableMap writableMap) {
        if (view == null || themedReactContext == null) {
            return;
        }
        ((RCTEventEmitter) themedReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), str, writableMap);
    }

    public void removeFeatureAt(int i2) {
        JDReactMapFeature remove = this.features.remove(i2);
        if (remove instanceof JDReactMapMarker) {
            this.markerMap.remove(remove.getFeature());
        }
        remove.removeFromMap(this.map);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public void setCacheEnabled(boolean z) {
        this.cacheEnabled = z;
        cacheView();
    }

    public void setHandlePanDrag(boolean z) {
        this.handlePanDrag = z;
    }

    public void setInitialRegion(ReadableMap readableMap) {
        if (this.initialRegionSet || readableMap == null) {
            return;
        }
        setRegion(readableMap);
        this.initialRegionSet = true;
    }

    public void setLbsParam(ReadableMap readableMap) {
        if (readableMap != null) {
            this.sceneId = !TextUtils.isEmpty(readableMap.getString("sceneId")) ? readableMap.getString("sceneId") : this.sceneId;
            this.businessId = !TextUtils.isEmpty(readableMap.getString("businessId")) ? readableMap.getString("businessId") : this.businessId;
        }
    }

    public void setLoadingBackgroundColor(Integer num) {
        this.loadingBackgroundColor = num;
        RelativeLayout relativeLayout = this.mapLoadingLayout;
        if (relativeLayout != null) {
            if (num == null) {
                relativeLayout.setBackgroundColor(-1);
            } else {
                relativeLayout.setBackgroundColor(num.intValue());
            }
        }
    }

    public void setLoadingIndicatorColor(Integer num) {
        this.loadingIndicatorColor = num;
        if (this.mapLoadingProgressBar != null) {
            Integer valueOf = num == null ? Integer.valueOf(Color.parseColor("#606060")) : num;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                ColorStateList valueOf2 = ColorStateList.valueOf(num.intValue());
                ColorStateList valueOf3 = ColorStateList.valueOf(num.intValue());
                ColorStateList valueOf4 = ColorStateList.valueOf(num.intValue());
                this.mapLoadingProgressBar.setProgressTintList(valueOf2);
                this.mapLoadingProgressBar.setSecondaryProgressTintList(valueOf3);
                this.mapLoadingProgressBar.setIndeterminateTintList(valueOf4);
                return;
            }
            PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
            if (i2 <= 10) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (this.mapLoadingProgressBar.getIndeterminateDrawable() != null) {
                this.mapLoadingProgressBar.getIndeterminateDrawable().setColorFilter(valueOf.intValue(), mode);
            }
            if (this.mapLoadingProgressBar.getProgressDrawable() != null) {
                this.mapLoadingProgressBar.getProgressDrawable().setColorFilter(valueOf.intValue(), mode);
            }
        }
    }

    public void setMoveOnMarkerPress(boolean z) {
        this.moveOnMarkerPress = z;
    }

    public void setRegion(ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        Double valueOf = Double.valueOf(readableMap.getDouble(PdLVBody.LONGITUDE));
        Double valueOf2 = Double.valueOf(readableMap.getDouble(PdLVBody.LATITUDE));
        Double valueOf3 = Double.valueOf(readableMap.getDouble("longitudeDelta"));
        Double valueOf4 = Double.valueOf(readableMap.getDouble("latitudeDelta"));
        LatLngBounds latLngBounds = new LatLngBounds(new LatLng(valueOf2.doubleValue() - (valueOf4.doubleValue() / 2.0d), valueOf.doubleValue() - (valueOf3.doubleValue() / 2.0d)), new LatLng(valueOf2.doubleValue() + (valueOf4.doubleValue() / 2.0d), valueOf.doubleValue() + (valueOf3.doubleValue() / 2.0d)));
        if (super.getHeight() > 0 && super.getWidth() > 0) {
            this.map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
            this.boundsToMove = null;
            return;
        }
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(valueOf2.doubleValue(), valueOf.doubleValue()), 10.0f));
        this.boundsToMove = latLngBounds;
    }

    public void setScrollValue(boolean z) {
        this.isScrollGesturesEnabled = z;
    }

    public void setShowLocationEnable(boolean z) {
        this.mShowLocation = z;
        if (z) {
            performRequestLocation();
        } else {
            cancelLocation();
        }
    }

    public void setTrackEnable(boolean z) {
        this.mTrackLocation = z;
    }

    public void setZoomIn() {
        float f2 = this.map.getCameraPosition().zoom;
        if (f2 < this.map.getMaxZoomLevel()) {
            this.map.moveCamera(CameraUpdateFactory.zoomTo(f2 + 1.0f));
        }
    }

    public void setZoomOut() {
        float f2 = this.map.getCameraPosition().zoom;
        if (f2 > this.map.getMinZoomLevel()) {
            this.map.moveCamera(CameraUpdateFactory.zoomTo(f2 - 1.0f));
        }
    }

    public void startMonitoringRegion() {
        if (this.map == null || this.isMonitoringRegion) {
            return;
        }
        this.timerHandler.postDelayed(this.timerRunnable, 100L);
        this.isMonitoringRegion = true;
    }

    public void stopMonitoringRegion() {
        if (this.map == null || !this.isMonitoringRegion) {
            return;
        }
        this.timerHandler.removeCallbacks(this.timerRunnable);
        this.isMonitoringRegion = false;
    }

    public void updateExtraData(Object obj) {
        if (this.boundsToMove != null) {
            HashMap hashMap = (HashMap) obj;
            ((Float) hashMap.get("width")).floatValue();
            ((Float) hashMap.get("height")).floatValue();
            this.map.moveCamera(CameraUpdateFactory.newLatLngBounds(this.boundsToMove, 0));
            this.boundsToMove = null;
        }
    }

    public void updateLocationTitle(String str) {
        this.mLocationTitle = str;
        if (this.mLocationMarker == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (this.mLocationMarker.isInfoWindowShown()) {
                this.mLocationMarker.hideInfoWindow();
                return;
            }
            return;
        }
        this.mLocationMarker.setTitle(this.mLocationTitle);
        this.mLocationMarker.setSnippet(null);
        if (this.mLocationMarker.isInfoWindowShown()) {
            return;
        }
        this.mLocationMarker.showInfoWindow();
    }
}
