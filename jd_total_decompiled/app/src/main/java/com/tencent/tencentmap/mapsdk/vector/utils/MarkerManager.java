package com.tencent.tencentmap.mapsdk.vector.utils;

import android.view.View;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class MarkerManager implements TencentMap.OnInfoWindowClickListener, TencentMap.OnMarkerClickListener, TencentMap.OnMarkerDragListener, TencentMap.InfoWindowAdapter {
    public final TencentMap a;
    public final Map<String, Collection> b = new HashMap();

    /* renamed from: c */
    public final Map<Marker, Collection> f18000c = new HashMap();

    /* loaded from: classes9.dex */
    public class Collection {
        public final Set<Marker> a = new HashSet();
        public TencentMap.OnInfoWindowClickListener b;

        /* renamed from: c */
        public TencentMap.OnMarkerClickListener f18001c;
        public TencentMap.OnMarkerDragListener d;

        /* renamed from: e */
        public TencentMap.InfoWindowAdapter f18002e;

        public Collection() {
            MarkerManager.this = r1;
        }

        public java.util.Collection<Marker> getMarkers() {
            return Collections.unmodifiableCollection(this.a);
        }

        public Marker a(MarkerOptions markerOptions) {
            Marker addMarker = MarkerManager.this.a.addMarker(markerOptions);
            this.a.add(addMarker);
            MarkerManager.this.f18000c.put(addMarker, this);
            return addMarker;
        }

        public boolean a(Marker marker) {
            if (this.a.remove(marker)) {
                MarkerManager.this.f18000c.remove(marker);
                marker.remove();
                return true;
            }
            return false;
        }

        public void a() {
            for (Marker marker : this.a) {
                marker.remove();
                MarkerManager.this.f18000c.remove(marker);
            }
            this.a.clear();
        }

        public void a(TencentMap.OnInfoWindowClickListener onInfoWindowClickListener) {
            this.b = onInfoWindowClickListener;
        }

        public void a(TencentMap.OnMarkerClickListener onMarkerClickListener) {
            this.f18001c = onMarkerClickListener;
        }

        public void a(TencentMap.InfoWindowAdapter infoWindowAdapter) {
            this.f18002e = infoWindowAdapter;
        }
    }

    public MarkerManager(TencentMap tencentMap) {
        this.a = tencentMap;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
    public View getInfoContents(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.f18002e == null) {
            return null;
        }
        return collection.f18002e.getInfoContents(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.InfoWindowAdapter
    public View getInfoWindow(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.f18002e == null) {
            return null;
        }
        return collection.f18002e.getInfoWindow(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
    public void onInfoWindowClick(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.b == null) {
            return;
        }
        collection.b.onInfoWindowClick(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnInfoWindowClickListener
    public void onInfoWindowClickLocation(int i2, int i3, int i4, int i5) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.f18001c == null) {
            return false;
        }
        return collection.f18001c.onMarkerClick(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDrag(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.d == null) {
            return;
        }
        collection.d.onMarkerDrag(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDragEnd(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.d == null) {
            return;
        }
        collection.d.onMarkerDragEnd(marker);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMarkerDragListener
    public void onMarkerDragStart(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        if (collection == null || collection.d == null) {
            return;
        }
        collection.d.onMarkerDragStart(marker);
    }

    public Collection a() {
        return new Collection();
    }

    public boolean a(Marker marker) {
        Collection collection = this.f18000c.get(marker);
        return collection != null && collection.a(marker);
    }
}
