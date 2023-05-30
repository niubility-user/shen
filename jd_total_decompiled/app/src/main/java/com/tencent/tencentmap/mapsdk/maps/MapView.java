package com.tencent.tencentmap.mapsdk.maps;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.map.tools.Callback;
import com.tencent.mapsdk.internal.li;
import com.tencent.mapsdk.internal.t;
import com.tencent.mapsdk.internal.u;
import com.tencent.tencentmap.mapsdk.maps.BaseMapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;

/* loaded from: classes9.dex */
public class MapView extends BaseMapView {
    private TencentMap mMap;
    public BaseMapView.MapViewProxy mMapDelegate;
    private TencentMapOptions mMapOptions;

    /* loaded from: classes9.dex */
    public class a implements Callback<BaseMapView.MapViewProxy> {
        public final /* synthetic */ Callback a;

        public a(Callback callback) {
            this.a = callback;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(BaseMapView.MapViewProxy mapViewProxy) {
            MapView.this.mMapDelegate = mapViewProxy;
            if (mapViewProxy != null) {
                mapViewProxy.onResume();
                this.a.callback(mapViewProxy.getMap());
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Callback<TencentMap> {
        public final /* synthetic */ Callback a;

        /* loaded from: classes9.dex */
        public class a implements TencentMap.OnMapLoadedCallback {
            public final /* synthetic */ TencentMap a;

            public a(TencentMap tencentMap) {
                this.a = tencentMap;
            }

            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLoadedCallback
            public void onMapLoaded() {
                Callback callback = b.this.a;
                if (callback != null) {
                    callback.callback(this.a);
                }
                this.a.removeOnMapLoadedCallback(this);
            }
        }

        public b(Callback callback) {
            this.a = callback;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(TencentMap tencentMap) {
            tencentMap.addOnMapLoadedCallback(new a(tencentMap));
        }
    }

    static {
        System.loadLibrary(li.a);
    }

    public MapView(@NonNull Context context) {
        this(context, new TencentMapOptions());
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, new TencentMapOptions());
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, new TencentMapOptions());
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, TencentMapOptions tencentMapOptions) {
        super(context, attributeSet, i2);
        this.mMap = getMap(tencentMapOptions);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet, TencentMapOptions tencentMapOptions) {
        this(context, attributeSet, 0, tencentMapOptions);
    }

    public MapView(@NonNull Context context, TencentMapOptions tencentMapOptions) {
        super(context);
        this.mMap = getMap(tencentMapOptions);
    }

    private <T extends TencentMap> void getMapSync(TencentMapOptions tencentMapOptions, Callback<T> callback) {
        tencentMapOptions.setGetMapAsync(new b(callback));
        initMap(tencentMapOptions);
    }

    private void initMap(TencentMapOptions tencentMapOptions) {
        BaseMapView.MapViewProxy mapViewProxy;
        TencentMapOptions tencentMapOptions2;
        if (this.mMap == null || !((tencentMapOptions2 = this.mMapOptions) == tencentMapOptions || tencentMapOptions2.equals(tencentMapOptions))) {
            if (tencentMapOptions == null) {
                tencentMapOptions = new TencentMapOptions();
            }
            if (tencentMapOptions.getMapViewType() == null) {
                tencentMapOptions.setMapViewType(getViewType());
            }
            if (tencentMapOptions.getMapKernel() == null) {
                tencentMapOptions.setMapKernel(getMapKernel());
            }
            setClickable(true);
            if (this.mMap != null && (mapViewProxy = this.mMapDelegate) != null) {
                mapViewProxy.onPause();
                this.mMapDelegate.onStop();
                this.mMapDelegate.onDestroy();
                this.mMapDelegate = null;
                this.mMap = null;
            }
            Callback<TencentMap> mapAsyncCallback = tencentMapOptions.getMapAsyncCallback();
            if (this.mMapDelegate == null) {
                t tVar = new t(getContext().getApplicationContext());
                if (mapAsyncCallback != null) {
                    tVar.a(this, tencentMapOptions, new a(mapAsyncCallback));
                } else {
                    this.mMapDelegate = tVar.a(this, tencentMapOptions);
                }
            }
            this.mMapOptions = tencentMapOptions;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        BaseMapView.MapViewProxy mapViewProxy;
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (dispatchTouchEvent || (mapViewProxy = this.mMapDelegate) == null || !mapViewProxy.isTouchable()) {
            return dispatchTouchEvent;
        }
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(this.mMapOptions.isDisallowInterceptTouchEvent());
        }
        return this.mMapDelegate.onTouchEvent(motionEvent);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public TencentMap getMap() {
        TencentMap tencentMap = this.mMap;
        return tencentMap != null ? tencentMap : getMap(this.mMapOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public TencentMap getMap(TencentMapOptions tencentMapOptions) {
        if (TencentMapInitializer.getAgreePrivacy()) {
            initMap(tencentMapOptions);
            BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
            if (mapViewProxy != null) {
                return mapViewProxy.getMap();
            }
            return null;
        }
        return null;
    }

    public int[] getMapPadding() {
        return new int[]{getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom()};
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onDestroy() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onDestroy();
            this.mMapDelegate = null;
        }
        this.mMapOptions = null;
        this.mMap = null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onPause() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onPause();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onRestart() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onRestart();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onResume() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onResume();
        }
        u.f().c();
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onSizeChanged(i2, i3, i4, i5);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onStart() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onStart();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView
    public void onStop() {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.onStop();
        }
        u.f().close();
    }

    public void onSurfaceChanged(Object obj, int i2, int i3) {
        BaseMapView.MapViewProxy mapViewProxy;
        if (((obj instanceof Surface) || (obj instanceof SurfaceTexture) || (obj instanceof SurfaceHolder)) && (mapViewProxy = this.mMapDelegate) != null) {
            mapViewProxy.onSurfaceChanged(obj, i2, i3);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public void setMapPadding(int i2, int i3, int i4, int i5) {
        setPadding(i2, i3, i4, i5);
    }

    public void setOnTop(boolean z) {
        BaseMapView.MapViewProxy mapViewProxy = this.mMapDelegate;
        if (mapViewProxy != null) {
            mapViewProxy.setOnTop(z);
        }
    }
}
