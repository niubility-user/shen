package com.jd.manto.map;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.manto.map.Beans;
import com.jd.manto.map.Listeners;
import com.jd.manto.map.TranslateMarkerAnim;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes17.dex */
public final class MapProcessCenter implements TencentLocationListener {
    private final WeakReference<Activity> activityRef;
    private final Context context;
    private boolean hasSetCenter;
    private boolean isPermissionRequesting;
    private TencentLocationManager locationManager;
    private Beans.MapInfo mapInfo;
    private final MapView mapView;
    private Marker myDirectionMarker;
    private TencentLocationRequest request;
    private final FrameLayout viewGroup;
    private final AbstractMantoViewManager viewManager;
    private final ConcurrentHashMap<Integer, Beans.MapInfo> mapInfoMap = new ConcurrentHashMap<>();
    private final Map<String, Pair<Marker, Beans.MarkInfo>> markerMap = new ConcurrentHashMap();
    private final List<ImageView> controlViews = new ArrayList();
    private final List<CustomMarkerLabelView> labelViews = Collections.synchronizedList(new LinkedList());
    private final List<BubbleView> infoWindowView = Collections.synchronizedList(new LinkedList());
    private final List<Polyline> polylines = Collections.synchronizedList(new ArrayList());
    private final List<Circle> circles = Collections.synchronizedList(new ArrayList());
    private final MapParamParseHelper paramParseHelper = new MapParamParseHelper();
    private AtomicBoolean dragByUser = new AtomicBoolean(false);
    private int[] patterns = {20, 20, 20};

    public MapProcessCenter(Activity activity, FrameLayout frameLayout, MapView mapView, AbstractMantoViewManager abstractMantoViewManager) {
        this.activityRef = new WeakReference<>(activity);
        this.viewManager = abstractMantoViewManager;
        this.viewGroup = frameLayout;
        this.mapView = mapView;
        this.context = frameLayout.getContext().getApplicationContext();
        onStart();
        onResume();
    }

    private void drawCircle(Context context, TencentMap tencentMap, Beans.CircleInfo circleInfo) {
        int sizeByPx = Tools.getSizeByPx(context, Integer.valueOf(circleInfo.radius));
        int sizeByPx2 = Tools.getSizeByPx(context, Float.valueOf(circleInfo.strokeWidth));
        if (Tools.isLatLngValid(circleInfo.latitude, circleInfo.longitude)) {
            Circle addCircle = tencentMap.addCircle(new CircleOptions().center(new LatLng(circleInfo.latitude, circleInfo.longitude)).radius(sizeByPx).fillColor(circleInfo.fillColor).strokeColor(circleInfo.strokeColor).strokeWidth(sizeByPx2));
            if (addCircle != null) {
                this.circles.add(addCircle);
            }
        }
    }

    private void drawLine(Context context, TencentMap tencentMap, Beans.LineInfo lineInfo, Bitmap bitmap) {
        ArrayList arrayList = new ArrayList(8);
        int sizeByPx = Tools.getSizeByPx(context, Integer.valueOf(lineInfo.width));
        for (int i2 = 0; i2 < lineInfo.points.size(); i2++) {
            if (Tools.isLatLngValid(lineInfo.points.get(i2).latitude, lineInfo.points.get(i2).longitude)) {
                arrayList.add(new LatLng(lineInfo.points.get(i2).latitude, lineInfo.points.get(i2).longitude));
            }
        }
        PolylineOptions color = new PolylineOptions().addAll(arrayList).color(lineInfo.color);
        if (lineInfo.dottedLine) {
            color.lineType(2);
            color.pattern(patternLine(this.patterns));
        }
        if (lineInfo.arrowLine) {
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.map_arrow);
            }
            BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(rotateBitmap(bitmap, -90.0f));
            color.arrow(true);
            color.arrowSpacing(50);
            color.arrowTexture(fromBitmap);
        }
        color.borderWidth(Tools.getSizeByPx(context, Integer.valueOf(lineInfo.borderWidth)));
        color.borderColor(lineInfo.borderColor);
        Polyline addPolyline = tencentMap.addPolyline(color);
        if (addPolyline != null) {
            this.polylines.add(addPolyline);
        }
        if (sizeByPx > 0) {
            addPolyline.setWidth(sizeByPx);
        }
    }

    private void drawMark(TencentMap tencentMap, Beans.MarkInfo markInfo, Bitmap bitmap) {
        CustomMarkerLabelView remove;
        if (bitmap == null && (bitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.map_mypos_marker)) == null) {
            return;
        }
        Bitmap bitmap2 = bitmap;
        if (Tools.isLatLngValid(markInfo.latitude, markInfo.longitude)) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(markInfo.latitude, markInfo.longitude));
            if (!TextUtils.isEmpty(markInfo.title) && markInfo.callOut == null) {
                markerOptions.title(markInfo.title);
            }
            markerOptions.rotation(markInfo.rotate);
            markerOptions.alpha(markInfo.alpha);
            FrameLayout frameLayout = (FrameLayout) ((LayoutInflater) this.viewGroup.getContext().getSystemService("layout_inflater")).inflate(R.layout.manto_map_marker, (ViewGroup) null);
            ImageView imageView = (ImageView) frameLayout.findViewById(R.id.iv_marker);
            float dip2pixel = MantoDensityUtils.dip2pixel(markInfo.width);
            float dip2pixel2 = MantoDensityUtils.dip2pixel(markInfo.height);
            if (dip2pixel == 0.0f || dip2pixel2 == 0.0f) {
                dip2pixel = bitmap2.getWidth();
                dip2pixel2 = bitmap2.getHeight();
            }
            if (dip2pixel > 0.0f && dip2pixel2 > 0.0f && (dip2pixel != bitmap2.getWidth() || dip2pixel2 != bitmap2.getHeight())) {
                Matrix matrix = new Matrix();
                matrix.postScale(dip2pixel / bitmap2.getWidth(), dip2pixel2 / bitmap2.getHeight());
                imageView.setImageBitmap(Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true));
            } else {
                imageView.setImageBitmap(bitmap2);
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(loadBitmapFromView(frameLayout))));
            Beans.Anchor anchor = markInfo.anchor;
            markerOptions.anchor(anchor.x, anchor.y);
            markerOptions.tag("" + markInfo.id);
            markerOptions.visible(true);
            Marker addMarker = tencentMap.addMarker(markerOptions);
            this.markerMap.put("" + markInfo.id, new Pair<>(addMarker, markInfo));
            Beans.CallOut callOut = markInfo.callOut;
            if (callOut != null && callOut.display == 1) {
                addMarker.showInfoWindow();
            }
            if (markInfo.label != null) {
                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(new LatLng(markInfo.latitude, markInfo.longitude));
                markerOptions2.alpha(markInfo.alpha);
                if (this.labelViews.isEmpty()) {
                    remove = new CustomMarkerLabelView(this.context);
                } else {
                    remove = this.labelViews.remove(0);
                    Tools.unbindParent(remove);
                }
                this.labelViews.add(remove);
                remove.setText("");
                remove.setTextSize(12);
                remove.setTextColor(Color.parseColor(JDDarkUtil.COLOR_0000000));
                remove.setTextPadding(0);
                remove.setGravity(DYConstants.DY_CENTER);
                remove.setGradient(0, 0, Color.parseColor(JDDarkUtil.COLOR_0000000), Color.parseColor(JDDarkUtil.COLOR_0000000));
                remove.setTextColor(markInfo.label.color);
                remove.setTextSize(markInfo.label.fontSize);
                remove.setText(markInfo.label.content);
                remove.setTextPadding(Tools.getSizeByPx(this.context, Integer.valueOf(markInfo.label.padding)));
                remove.setGravity(markInfo.label.textAlign);
                int sizeByPx = Tools.getSizeByPx(this.context, Integer.valueOf(markInfo.label.borderRadius));
                int sizeByPx2 = Tools.getSizeByPx(this.context, Integer.valueOf(markInfo.label.borderWidth));
                Beans.Label label = markInfo.label;
                remove.setGradient(sizeByPx, sizeByPx2, label.borderColor, label.bgColor);
                remove.setX(markInfo.label.anchorX);
                remove.setY(markInfo.label.anchorY);
                remove.measure(0, 0);
                markerOptions2.anchor(remove.getAnchorX(), remove.getAnchorY());
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(Bitmap.createBitmap(loadBitmapFromView(remove))));
                markerOptions2.visible(true);
                markerOptions2.tag(markInfo.id + "#label");
                Marker addMarker2 = tencentMap.addMarker(markerOptions2);
                this.markerMap.put(markInfo.id + "#label", new Pair<>(addMarker2, null));
            }
        }
    }

    private Beans.CircleInfo getCircleInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getCircleInfo(jSONObject);
    }

    private Beans.ControlInfo getControlInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getControlInfo(jSONObject);
    }

    private Beans.LineInfo getLineInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getLineInfo(jSONObject);
    }

    private Beans.MarkInfo getMarkerInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getMarkerInfo(jSONObject);
    }

    private Beans.Point getPoint(JSONObject jSONObject) {
        return this.paramParseHelper.getPoint(jSONObject);
    }

    private Bitmap loadBitmapFromView(View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            measuredWidth = view.getMeasuredWidth();
            measuredHeight = view.getMeasuredHeight();
        }
        if (measuredWidth <= 0 || measuredHeight <= 0) {
            measuredWidth = 10;
            measuredHeight = 10;
        }
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        view.layout(0, 0, measuredWidth, measuredHeight);
        view.draw(canvas);
        return createBitmap;
    }

    private List<Integer> patternLine(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        if (iArr == null) {
            return arrayList;
        }
        for (int i2 : iArr) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestLocation() {
        if (this.locationManager == null) {
            this.locationManager = TencentLocationManager.getInstance(this.context);
            this.request = TencentLocationRequest.create().setInterval(1200L).setAllowDirection(true);
        }
        this.locationManager.requestSingleFreshLocation(this.request, this, Looper.getMainLooper());
    }

    private Bitmap rotateBitmap(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f2);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (createBitmap.equals(bitmap)) {
        }
        return createBitmap;
    }

    private void showLocation() {
        Beans.MapInfo mapInfo = this.mapInfo;
        if (mapInfo == null || this.mapView == null) {
            return;
        }
        if (mapInfo.showLocation) {
            if (Tools.hasLocationPermission()) {
                requestLocation();
                return;
            } else if (this.activityRef.get() == null || this.activityRef.get().isFinishing() || this.isPermissionRequesting) {
                return;
            } else {
                this.isPermissionRequesting = true;
                Tools.requireLocationPermission(this.activityRef.get(), new MantoResultCallBack() { // from class: com.jd.manto.map.MapProcessCenter.1
                    @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
                    public void onCancel(Bundle bundle) {
                        MapProcessCenter.this.isPermissionRequesting = false;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
                    public void onFailed(Bundle bundle) {
                        MapProcessCenter.this.isPermissionRequesting = false;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.MantoResultCallBack
                    public void onSuccess(Bundle bundle) {
                        MapProcessCenter.this.requestLocation();
                        MapProcessCenter.this.isPermissionRequesting = false;
                    }
                });
                return;
            }
        }
        Marker marker = this.myDirectionMarker;
        if (marker != null) {
            marker.remove();
        }
        this.hasSetCenter = false;
        this.locationManager = null;
        Marker marker2 = this.myDirectionMarker;
        if (marker2 != null) {
            marker2.remove();
            this.myDirectionMarker = null;
        }
    }

    final void clearCircles() {
        Iterator<Circle> it = this.circles.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        this.circles.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearControlView() {
        if (this.viewGroup != null) {
            Iterator<ImageView> it = this.controlViews.iterator();
            while (it.hasNext()) {
                this.viewGroup.removeView(it.next());
            }
            this.controlViews.clear();
        }
    }

    final void clearLines() {
        Iterator<Polyline> it = this.polylines.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        this.polylines.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void clearMarker() {
        Iterator<Map.Entry<String, Pair<Marker, Beans.MarkInfo>>> it = this.markerMap.entrySet().iterator();
        while (it.hasNext()) {
            Object obj = it.next().getValue().first;
            if (obj != null) {
                ((Marker) obj).remove();
            }
        }
        this.labelViews.clear();
        this.markerMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void drawCircles(TencentMap tencentMap, List<Beans.CircleInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        clearCircles();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Beans.CircleInfo circleInfo = list.get(i2);
            if (circleInfo != null) {
                drawCircle(this.context, tencentMap, circleInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void drawLines(MantoCore mantoCore, TencentMap tencentMap, List<Beans.LineInfo> list) {
        List<Beans.Point> list2;
        if (list == null || list.size() <= 0) {
            return;
        }
        clearLines();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Beans.LineInfo lineInfo = list.get(i2);
            if (lineInfo != null && (list2 = lineInfo.points) != null && list2.size() > 0) {
                drawLine(this.context, tencentMap, lineInfo, TextUtils.isEmpty(lineInfo.arrowIconPath) ? null : this.viewManager.getBitmap(mantoCore, lineInfo.arrowIconPath));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void drawMap(MantoCore mantoCore, MapView mapView, Beans.MapInfo mapInfo, int i2, int i3) {
        this.mapInfoMap.put(Integer.valueOf(i2), mapInfo);
        this.mapInfo = mapInfo;
        UiSettings uiSettings = mapView.getMap().getUiSettings();
        TencentMap map = mapView.getMap();
        if (uiSettings == null || map == null) {
            return;
        }
        uiSettings.setZoomGesturesEnabled(mapInfo.enableZoom);
        uiSettings.setScrollGesturesEnabled(mapInfo.enableScroll);
        map.setSatelliteEnabled(mapInfo.enableSatellite);
        map.setTrafficEnabled(mapInfo.enableTraffic);
        map.setSatelliteEnabled("satellite".equals(mapInfo.theme));
        map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(map.getCameraPosition()).zoom(mapInfo.scale).build()));
        if (Math.abs(mapInfo.centerLatitude) <= 90.0d && Math.abs(mapInfo.centerLongitude) <= 180.0d) {
            if (Tools.isLatLngValid(mapInfo.centerLatitude, mapInfo.centerLongitude)) {
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mapInfo.centerLatitude, mapInfo.centerLongitude)));
            }
            String.format("insert map, set center: %s,%s", Double.valueOf(mapInfo.centerLatitude), Double.valueOf(mapInfo.centerLongitude));
        }
        setInfoWindowAdapter(map);
        drawMarkers(mantoCore, map, mapInfo.markers);
        drawLines(mantoCore, map, mapInfo.polylines);
        drawCircles(map, mapInfo.circleInfos);
        drawsControls(mantoCore, mapInfo.controlInfos, i2, i3);
        showLocation();
        includePoints(map, mapInfo.includePoints, 0.0d);
    }

    final void drawMapCtrl(Context context, final Beans.ControlInfo controlInfo, final Listeners.MapCtrlClickListener mapCtrlClickListener) {
        final ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(controlInfo.icon);
        int i2 = controlInfo.position.width;
        if (i2 == 0) {
            i2 = controlInfo.icon.getWidth();
        }
        int sizeByPx = Tools.getSizeByPx(context, Integer.valueOf(i2));
        int i3 = controlInfo.position.height;
        if (i3 == 0) {
            i3 = controlInfo.icon.getHeight();
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(sizeByPx, Tools.getSizeByPx(context, Integer.valueOf(i3)));
        layoutParams.setMargins(Tools.getSizeByPx(context, Integer.valueOf(controlInfo.position.left)), Tools.getSizeByPx(context, Integer.valueOf(controlInfo.position.top)), 0, 0);
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.manto.map.MapProcessCenter.9
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (controlInfo.clickable) {
                    int action = motionEvent.getAction();
                    if (action == 0) {
                        imageView.setColorFilter(Color.parseColor("#88888888"));
                        return false;
                    } else if (action == 3 || action == 1) {
                        imageView.clearColorFilter();
                        return false;
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.manto.map.MapProcessCenter.10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                Listeners.MapCtrlClickListener mapCtrlClickListener2;
                Beans.ControlInfo controlInfo2 = controlInfo;
                if (!controlInfo2.clickable || (mapCtrlClickListener2 = mapCtrlClickListener) == null) {
                    return;
                }
                mapCtrlClickListener2.onDataBack(controlInfo2.data);
            }
        });
        FrameLayout frameLayout = this.viewGroup;
        if (frameLayout != null) {
            frameLayout.addView(imageView, layoutParams);
            synchronized (this.controlViews) {
                this.controlViews.add(imageView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void drawMarkers(MantoCore mantoCore, TencentMap tencentMap, List<Beans.MarkInfo> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            Beans.MarkInfo markInfo = list.get(i2);
            if (markInfo != null) {
                drawMark(tencentMap, markInfo, TextUtils.isEmpty(markInfo.iconPath) ? null : this.viewManager.getBitmap(mantoCore, markInfo.iconPath));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void drawsControls(final MantoCore mantoCore, List<Beans.ControlInfo> list, final int i2, final int i3) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            Beans.ControlInfo controlInfo = list.get(i4);
            if (controlInfo != null) {
                Bitmap bitmap = !TextUtils.isEmpty(controlInfo.iconPath) ? this.viewManager.getBitmap(mantoCore, controlInfo.iconPath) : null;
                if (bitmap == null) {
                    return;
                }
                controlInfo.icon = bitmap;
                drawMapCtrl(this.context, controlInfo, controlInfo.clickable ? new Listeners.MapCtrlClickListener() { // from class: com.jd.manto.map.MapProcessCenter.8
                    @Override // com.jd.manto.map.Listeners.MapCtrlClickListener
                    public void onDataBack(String str) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("mapId", i2);
                            jSONObject.put("data", str);
                            MapProcessCenter.this.viewManager.dispatchEvent(mantoCore, Events.EVENT_ON_MAP_CTRL_CLICK, jSONObject, i3);
                        } catch (JSONException unused) {
                        }
                    }
                } : null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.CircleInfo> getCircleInfoList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("circles")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("circles");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(getCircleInfo(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.ControlInfo> getControlInfoList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("controls")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("controls");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(getControlInfo(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.LineInfo> getLineInfoList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("lines")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("lines");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(getLineInfo(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.MapInfo getMapInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getMapInfo(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.MarkInfo> getMarkInfoList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("markers")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("markers");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(getMarkerInfo(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.Point> getPointList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("points")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("points");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(getPoint(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<Beans.TranslateMarkerInfo> getTranslateMarkerList(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("keyFrames")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("keyFrames");
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(this.paramParseHelper.getTranslateMarkerInfo(optJSONArray.optJSONObject(i2)));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.UpdateMapInfo getUpdateMapInfo(JSONObject jSONObject) {
        return this.paramParseHelper.getUpdateMapInfo(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void includePoints(TencentMap tencentMap, List<Beans.Point> list, double d) {
        if (list == null || list.size() <= 1) {
            return;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        ArrayList arrayList = new ArrayList();
        for (Beans.Point point2 : list) {
            if (Tools.isLatLngValid(point2.latitude, point2.longitude)) {
                arrayList.add(new LatLng(point2.latitude, point2.longitude));
            }
        }
        builder.include(arrayList);
        this.dragByUser.set(false);
        tencentMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), Tools.getSizeByPx(this.context, Double.valueOf(d))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onDestroy() {
        clearControlView();
        clearMarker();
        clearLines();
        clearCircles();
        MapView mapView = this.mapView;
        if (mapView != null) {
            if (mapView.getMap() != null) {
                this.mapView.getMap().setOnCameraChangeListener(null);
            }
            this.mapView.onDestroy();
        }
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onLocationChanged(TencentLocation tencentLocation, int i2, String str) {
        TencentMap map = this.mapView.getMap();
        if (i2 == 0 && map != null) {
            if (Tools.isLatLngValid(tencentLocation.getLatitude(), tencentLocation.getLongitude())) {
                LatLng latLng = new LatLng(tencentLocation.getLatitude(), tencentLocation.getLongitude());
                if (!this.hasSetCenter) {
                    this.hasSetCenter = true;
                }
                double d = tencentLocation.getExtra().getDouble("direction");
                Marker marker = this.myDirectionMarker;
                if (marker == null) {
                    this.myDirectionMarker = map.addMarker(new MarkerOptions().position(latLng).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_direction)));
                } else {
                    marker.setPosition(latLng);
                }
                this.myDirectionMarker.setRotation((float) d);
                return;
            }
            return;
        }
        Toast.makeText(this.context, "\u5b9a\u4f4d\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u6743\u9650", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onPause() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onPause();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onResume() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onResume();
            if (Tools.hasLocationPermission()) {
                showLocation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onStart() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onStart();
        }
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onStatusUpdate(String str, int i2, String str2) {
        String.format("onStatusUpdate:%s, %s, %s", str, Integer.valueOf(i2), str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onStop() {
        MapView mapView = this.mapView;
        if (mapView != null) {
            mapView.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeMarker(String str) {
        if (this.markerMap.containsKey(str)) {
            ((Marker) this.markerMap.get(str).first).remove();
        }
        if (this.markerMap.containsKey(str + "#label")) {
            ((Marker) this.markerMap.get(str + "#label").first).remove();
        }
        this.labelViews.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setEventInAddView(final MantoCore mantoCore, final int i2, final int i3) {
        MapView mapView = this.mapView;
        if (mapView == null || mapView.getMap() == null) {
            return;
        }
        this.mapView.setOnTouchListener(new View.OnTouchListener() { // from class: com.jd.manto.map.MapProcessCenter.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(final View view, MotionEvent motionEvent) {
                MapProcessCenter.this.dragByUser.set(true);
                int action = motionEvent.getAction();
                if (action == 1 || action == 3) {
                    view.postDelayed(new Runnable() { // from class: com.jd.manto.map.MapProcessCenter.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (MapProcessCenter.this.mapView == null || view == null) {
                                return;
                            }
                            MapProcessCenter.this.dragByUser.set(false);
                        }
                    }, 1200L);
                    return false;
                }
                return false;
            }
        });
        TencentMap map = this.mapView.getMap();
        map.setOnInfoWindowClickListener(new TencentMap.OnInfoWindowClickListener() { // from class: com.jd.manto.map.MapProcessCenter.3
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClick(Marker marker) {
                if (marker == null || marker.getTag() == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                Pair pair = (Pair) MapProcessCenter.this.markerMap.get((String) marker.getTag());
                try {
                    jSONObject.put("mapId", i2);
                    jSONObject.put("data", ((Beans.MarkInfo) pair.second).data);
                    MapProcessCenter.this.viewManager.dispatchEvent(mantoCore, Events.EVENT_ON_MAP_CALLOUT_CLICK, jSONObject, i3);
                } catch (JSONException unused) {
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
            public void onInfoWindowClickLocation(int i4, int i5, int i6, int i7) {
            }
        });
        map.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() { // from class: com.jd.manto.map.MapProcessCenter.4
            /* JADX WARN: Removed duplicated region for block: B:25:0x005e A[Catch: JSONException -> 0x0076, TryCatch #0 {JSONException -> 0x0076, blocks: (B:9:0x0023, B:12:0x0030, B:15:0x0038, B:17:0x0045, B:19:0x0049, B:21:0x0050, B:23:0x0058, B:25:0x005e, B:28:0x0067, B:27:0x0064, B:16:0x0040), top: B:31:0x0023 }] */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0062  */
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onMarkerClick(com.tencent.tencentmap.mapsdk.maps.model.Marker r7) {
                /*
                    r6 = this;
                    r0 = 0
                    if (r7 == 0) goto L76
                    java.lang.Object r1 = r7.getTag()
                    if (r1 != 0) goto La
                    goto L76
                La:
                    org.json.JSONObject r1 = new org.json.JSONObject
                    r1.<init>()
                    com.jd.manto.map.MapProcessCenter r2 = com.jd.manto.map.MapProcessCenter.this
                    java.util.Map r2 = com.jd.manto.map.MapProcessCenter.access$400(r2)
                    java.lang.Object r3 = r7.getTag()
                    java.lang.String r3 = (java.lang.String) r3
                    java.lang.Object r2 = r2.get(r3)
                    android.util.Pair r2 = (android.util.Pair) r2
                    if (r2 == 0) goto L76
                    java.lang.String r3 = "mapId"
                    int r4 = r2     // Catch: org.json.JSONException -> L76
                    r1.put(r3, r4)     // Catch: org.json.JSONException -> L76
                    java.lang.Object r3 = r2.second     // Catch: org.json.JSONException -> L76
                    java.lang.String r4 = "data"
                    if (r3 == 0) goto L40
                    r5 = r3
                    com.jd.manto.map.Beans$MarkInfo r5 = (com.jd.manto.map.Beans.MarkInfo) r5     // Catch: org.json.JSONException -> L76
                    java.lang.String r5 = r5.data     // Catch: org.json.JSONException -> L76
                    if (r5 != 0) goto L38
                    goto L40
                L38:
                    com.jd.manto.map.Beans$MarkInfo r3 = (com.jd.manto.map.Beans.MarkInfo) r3     // Catch: org.json.JSONException -> L76
                    java.lang.String r3 = r3.data     // Catch: org.json.JSONException -> L76
                    r1.put(r4, r3)     // Catch: org.json.JSONException -> L76
                    goto L45
                L40:
                    java.lang.String r3 = ""
                    r1.put(r4, r3)     // Catch: org.json.JSONException -> L76
                L45:
                    java.lang.Object r2 = r2.second     // Catch: org.json.JSONException -> L76
                    if (r2 == 0) goto L57
                    r3 = r2
                    com.jd.manto.map.Beans$MarkInfo r3 = (com.jd.manto.map.Beans.MarkInfo) r3     // Catch: org.json.JSONException -> L76
                    com.jd.manto.map.Beans$CallOut r3 = r3.callOut     // Catch: org.json.JSONException -> L76
                    if (r3 == 0) goto L57
                    com.jd.manto.map.Beans$MarkInfo r2 = (com.jd.manto.map.Beans.MarkInfo) r2     // Catch: org.json.JSONException -> L76
                    com.jd.manto.map.Beans$CallOut r2 = r2.callOut     // Catch: org.json.JSONException -> L76
                    int r2 = r2.display     // Catch: org.json.JSONException -> L76
                    goto L58
                L57:
                    r2 = 0
                L58:
                    boolean r3 = r7.isInfoWindowShown()     // Catch: org.json.JSONException -> L76
                    if (r3 != 0) goto L62
                    r7.showInfoWindow()     // Catch: org.json.JSONException -> L76
                    goto L67
                L62:
                    if (r2 != 0) goto L67
                    r7.hideInfoWindow()     // Catch: org.json.JSONException -> L76
                L67:
                    com.jd.manto.map.MapProcessCenter r7 = com.jd.manto.map.MapProcessCenter.this     // Catch: org.json.JSONException -> L76
                    com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager r7 = com.jd.manto.map.MapProcessCenter.access$500(r7)     // Catch: org.json.JSONException -> L76
                    com.jingdong.manto.MantoCore r2 = r3     // Catch: org.json.JSONException -> L76
                    java.lang.String r3 = "onMapMarkerClick"
                    int r4 = r4     // Catch: org.json.JSONException -> L76
                    r7.dispatchEvent(r2, r3, r1, r4)     // Catch: org.json.JSONException -> L76
                L76:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.MapProcessCenter.AnonymousClass4.onMarkerClick(com.tencent.tencentmap.mapsdk.maps.model.Marker):boolean");
            }
        });
        map.setOnMapClickListener(new TencentMap.OnMapClickListener() { // from class: com.jd.manto.map.MapProcessCenter.5
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapClickListener
            public void onMapClick(LatLng latLng) {
                if (latLng == null) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("mapId", i2);
                    jSONObject.put(PdLVBody.LATITUDE, latLng.getLatitude());
                    jSONObject.put(PdLVBody.LONGITUDE, latLng.getLongitude());
                    MapProcessCenter.this.viewManager.dispatchEvent(mantoCore, Events.EVENT_ON_MAP_CLICK, jSONObject, i3);
                } catch (JSONException unused) {
                }
            }
        });
        map.setOnCameraChangeListener(new TencentMap.OnCameraChangeListener() { // from class: com.jd.manto.map.MapProcessCenter.6
            float zoom;
            final JSONObject jsonObject = new JSONObject();
            final AtomicBoolean changeed = new AtomicBoolean(false);

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
            public void onCameraChange(CameraPosition cameraPosition) {
                float f2 = cameraPosition.zoom;
                if (this.changeed.compareAndSet(false, true)) {
                    try {
                        this.jsonObject.remove("mapId");
                        this.jsonObject.put("mapId", i2);
                        this.jsonObject.remove("type");
                        this.jsonObject.put("type", "begin");
                        this.jsonObject.remove("causedBy");
                        this.jsonObject.put("causedBy", MapProcessCenter.this.dragByUser.get() ? "gesture" : "update");
                        this.jsonObject.remove("centerCoordinates");
                        this.zoom = f2;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    MapProcessCenter.this.viewManager.dispatchEvent(mantoCore, Events.EVENT_ON_MAP_REGION_CHANGE, this.jsonObject, i3);
                }
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
            public void onCameraChangeFinished(CameraPosition cameraPosition) {
                LatLng latLng = cameraPosition.target;
                if (latLng == null) {
                    return;
                }
                float f2 = cameraPosition.zoom;
                String str = "finish->scale:" + cameraPosition.zoom;
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (this.changeed.compareAndSet(true, false)) {
                    try {
                        LatLngBounds latLngBounds = MapProcessCenter.this.mapView.getMap().getProjection().getVisibleRegion().latLngBounds;
                        LatLng southWest = latLngBounds.getSouthWest();
                        LatLng northEast = latLngBounds.getNorthEast();
                        JSONObject jSONObject3 = new JSONObject();
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject3.put(PdLVBody.LONGITUDE, southWest.getLongitude());
                        jSONObject3.put(PdLVBody.LATITUDE, southWest.getLatitude());
                        jSONObject4.put(PdLVBody.LONGITUDE, northEast.getLongitude());
                        jSONObject4.put(PdLVBody.LATITUDE, northEast.getLatitude());
                        jSONObject2.put("northeast", jSONObject4);
                        jSONObject2.put("southwest", jSONObject3);
                        jSONObject.put(PdLVBody.LATITUDE, latLng.getLatitude());
                        jSONObject.put(PdLVBody.LONGITUDE, latLng.getLongitude());
                        this.jsonObject.remove("mapId");
                        this.jsonObject.put("mapId", i2);
                        this.jsonObject.remove("type");
                        this.jsonObject.put("type", "end");
                        this.jsonObject.remove("causedBy");
                        this.jsonObject.put("centerCoordinates", jSONObject);
                        this.jsonObject.remove("scale");
                        this.jsonObject.put("scale", f2);
                        this.jsonObject.remove("region");
                        this.jsonObject.put("region", jSONObject2);
                        this.jsonObject.remove("skew");
                        this.jsonObject.put("skew", 0);
                        this.jsonObject.remove("rotate");
                        this.jsonObject.put("rotate", 0);
                        if (MapProcessCenter.this.dragByUser.get()) {
                            if (this.zoom != f2) {
                                this.jsonObject.put("causedBy", "scale");
                            } else {
                                this.jsonObject.put("causedBy", "drag");
                            }
                        } else {
                            this.jsonObject.put("causedBy", "update");
                        }
                        MapProcessCenter.this.viewManager.dispatchEvent(mantoCore, Events.EVENT_ON_MAP_REGION_CHANGE, this.jsonObject, i3);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                MapProcessCenter.this.dragByUser.set(false);
            }
        });
    }

    final void setInfoWindowAdapter(TencentMap tencentMap) {
        tencentMap.setInfoWindowAdapter(new TencentMap.InfoWindowAdapter() { // from class: com.jd.manto.map.MapProcessCenter.7
            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                return null;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                Object obj;
                BubbleView bubbleView;
                if (TextUtils.isEmpty((String) marker.getTag()) || ((String) marker.getTag()).endsWith("#label")) {
                    return null;
                }
                Pair pair = (Pair) MapProcessCenter.this.markerMap.get(marker.getTag());
                if (pair.first == null || (obj = pair.second) == null || ((Beans.MarkInfo) obj).callOut == null) {
                    return null;
                }
                Beans.CallOut callOut = ((Beans.MarkInfo) obj).callOut;
                if (!MapProcessCenter.this.infoWindowView.isEmpty()) {
                    bubbleView = (BubbleView) MapProcessCenter.this.infoWindowView.remove(0);
                    Tools.unbindParent(bubbleView);
                } else {
                    bubbleView = new BubbleView(MapProcessCenter.this.context);
                }
                bubbleView.tvTitle.setText("");
                bubbleView.tvTitle.setTextSize(12.0f);
                bubbleView.tvTitle.setTextColor(Color.parseColor(JDDarkUtil.COLOR_0000000));
                bubbleView.tvTitle.setPadding(0, 0, 0, 0);
                bubbleView.setGravity(DYConstants.DY_CENTER);
                bubbleView.setBorderWidth(1);
                bubbleView.setBorderColor(Color.parseColor(JDDarkUtil.COLOR_0000000));
                bubbleView.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                bubbleView.setCornerRadius(2);
                int sizeByPx = Tools.getSizeByPx(MapProcessCenter.this.context, Integer.valueOf(callOut.padding));
                bubbleView.tvTitle.setText(callOut.content);
                bubbleView.tvTitle.setTextSize(callOut.fontSize);
                bubbleView.tvTitle.setTextColor(callOut.color);
                bubbleView.tvTitle.setPadding(sizeByPx, sizeByPx, sizeByPx, sizeByPx);
                bubbleView.setGravity(callOut.textAlign);
                bubbleView.setBorderColor(callOut.borderColor);
                bubbleView.setBackgroundColor(callOut.bgColor);
                bubbleView.setBorderWidth(Tools.getSizeByPx(MapProcessCenter.this.context, Integer.valueOf(callOut.borderWidth)));
                bubbleView.setCornerRadius(Tools.getSizeByPx(MapProcessCenter.this.context, Integer.valueOf(callOut.borderRadius)));
                return bubbleView;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void translateMarker(String str, List<Beans.TranslateMarkerInfo> list, MapView mapView, final TranslateMarkerAnim.TranslateMarkerCallback translateMarkerCallback) {
        Object obj;
        Pair<Marker, Beans.MarkInfo> pair = this.markerMap.get(str);
        if (pair != null && (obj = pair.first) != null) {
            Marker marker = (Marker) obj;
            Beans.TranslateMarkerInfo translateMarkerInfo = list.get(0);
            translateMarkerInfo.oldlatitude = marker.getPosition().getLatitude();
            translateMarkerInfo.oldlongitude = marker.getPosition().getLongitude();
            for (int i2 = 1; i2 < list.size(); i2++) {
                Beans.TranslateMarkerInfo translateMarkerInfo2 = list.get(i2 - 1);
                Beans.TranslateMarkerInfo translateMarkerInfo3 = list.get(i2);
                if (translateMarkerInfo2.rotate == 0.0f) {
                    translateMarkerInfo3.oldlongitude = translateMarkerInfo2.longitude;
                    translateMarkerInfo3.oldlatitude = translateMarkerInfo2.latitude;
                } else {
                    translateMarkerInfo3.oldlongitude = marker.getPosition().getLongitude();
                    translateMarkerInfo3.oldlatitude = marker.getPosition().getLatitude();
                }
            }
            TranslateMarkerAnim translateMarkerAnim = new TranslateMarkerAnim(list, marker, mapView);
            translateMarkerAnim.animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.jd.manto.map.MapProcessCenter.11
                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    TranslateMarkerAnim.TranslateMarkerCallback translateMarkerCallback2 = translateMarkerCallback;
                    if (translateMarkerCallback2 != null) {
                        translateMarkerCallback2.onTranslate(true);
                    }
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                }

                @Override // android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                }
            });
            translateMarkerAnim.animatorSet.start();
            return;
        }
        translateMarkerCallback.onTranslate(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateMap(MantoCore mantoCore, MapView mapView, Beans.MapInfo mapInfo, Beans.UpdateMapInfo updateMapInfo, int i2, int i3) {
        Beans.MapInfo mapInfo2 = this.mapInfoMap.get(Integer.valueOf(i2));
        if (mapInfo2 == null || mapInfo == null || updateMapInfo == null) {
            return;
        }
        this.mapInfo = this.paramParseHelper.diffMapInfo(mapInfo2, mapInfo, updateMapInfo);
        UiSettings uiSettings = mapView.getMap().getUiSettings();
        TencentMap map = mapView.getMap();
        if (uiSettings == null || map == null) {
            return;
        }
        if (updateMapInfo.updateZoom) {
            uiSettings.setZoomGesturesEnabled(this.mapInfo.enableZoom);
        }
        if (updateMapInfo.updateScroll) {
            uiSettings.setScrollGesturesEnabled(this.mapInfo.enableScroll);
        }
        if (updateMapInfo.updateSatellite) {
            map.setSatelliteEnabled(this.mapInfo.enableSatellite);
        }
        if (updateMapInfo.updateTraffic) {
            map.setTrafficEnabled(this.mapInfo.enableTraffic);
        }
        if (updateMapInfo.updateTheme) {
            map.setSatelliteEnabled("satellite".equals(this.mapInfo.theme));
        }
        if (updateMapInfo.updateZoom) {
            map.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder(map.getCameraPosition()).zoom(this.mapInfo.scale).build()));
        }
        if (updateMapInfo.updateCoordinate && Math.abs(this.mapInfo.centerLatitude) <= 90.0d && Math.abs(this.mapInfo.centerLongitude) <= 180.0d) {
            Beans.MapInfo mapInfo3 = this.mapInfo;
            if (Tools.isLatLngValid(mapInfo3.centerLatitude, mapInfo3.centerLongitude)) {
                Beans.MapInfo mapInfo4 = this.mapInfo;
                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mapInfo4.centerLatitude, mapInfo4.centerLongitude)));
            }
            String.format("update map, set center: %s,%s", Double.valueOf(this.mapInfo.centerLatitude), Double.valueOf(this.mapInfo.centerLongitude));
        }
        if (updateMapInfo.updateMarkers) {
            drawMarkers(mantoCore, map, this.mapInfo.markers);
        }
        if (updateMapInfo.updateLine) {
            drawLines(mantoCore, map, this.mapInfo.polylines);
        }
        if (updateMapInfo.updateCircles) {
            drawCircles(map, this.mapInfo.circleInfos);
        }
        if (updateMapInfo.updateCtrls) {
            drawsControls(mantoCore, this.mapInfo.controlInfos, i2, i3);
        }
        if (updateMapInfo.updateLoc) {
            showLocation();
        }
        if (updateMapInfo.updatePoints) {
            includePoints(map, this.mapInfo.includePoints, 0.0d);
        }
    }
}
