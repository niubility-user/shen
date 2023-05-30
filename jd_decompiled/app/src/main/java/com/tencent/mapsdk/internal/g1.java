package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LocationCompass;
import com.tencent.tencentmap.mapsdk.maps.model.LocationNavigationGravityline;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;

/* loaded from: classes9.dex */
public class g1 implements i0 {
    private a0 a;
    private LocationSource.OnLocationChangedListener b;

    /* renamed from: c  reason: collision with root package name */
    private LocationSource f16565c = null;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private Circle f16566e = null;

    /* renamed from: f  reason: collision with root package name */
    private TencentMap.OnMyLocationChangeListener f16567f = null;

    /* renamed from: g  reason: collision with root package name */
    private MyLocationStyle f16568g = new MyLocationStyle();

    /* renamed from: h  reason: collision with root package name */
    private int f16569h = Color.argb(102, 0, (int) R2.anim.pop_center_out, 255);

    /* renamed from: i  reason: collision with root package name */
    private Location f16570i = null;

    /* renamed from: j  reason: collision with root package name */
    private BitmapDescriptor f16571j;

    /* renamed from: k  reason: collision with root package name */
    private BitmapDescriptor f16572k;

    /* renamed from: l  reason: collision with root package name */
    private qc f16573l;

    /* renamed from: m  reason: collision with root package name */
    private int f16574m;

    /* renamed from: n  reason: collision with root package name */
    private TencentMap.OnMyLocationClickListener f16575n;
    private BitmapDescriptor o;

    /* loaded from: classes9.dex */
    public class a implements LocationSource.OnLocationChangedListener {
        public a() {
        }

        @Override // com.tencent.tencentmap.mapsdk.maps.LocationSource.OnLocationChangedListener
        public void onLocationChanged(Location location) {
            if (location == null) {
                return;
            }
            if (g1.this.f16570i == null) {
                g1.this.f16570i = new Location(location);
            } else {
                g1.this.f16570i.setLongitude(location.getLongitude());
                g1.this.f16570i.setLatitude(location.getLatitude());
                g1.this.f16570i.setAccuracy(location.getAccuracy());
                g1.this.f16570i.setProvider(location.getProvider());
                g1.this.f16570i.setTime(location.getTime());
                g1.this.f16570i.setSpeed(location.getSpeed());
                g1.this.f16570i.setAltitude(location.getAltitude());
            }
            g1.this.b(location);
            if (g1.this.f16567f != null) {
                g1.this.f16567f.onMyLocationChange(location);
            }
        }
    }

    public g1(qc qcVar, a0 a0Var) {
        this.a = null;
        this.b = null;
        this.f16573l = qcVar;
        this.a = a0Var;
        this.b = h();
    }

    private void a(Location location) {
        BitmapDescriptor compassImage;
        Bitmap bitmap;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (this.f16566e == null) {
            CircleOptions circleOptions = new CircleOptions();
            circleOptions.radius(location.getAccuracy()).center(latLng).fillColor(this.f16568g.getFillColor()).strokeColor(this.f16568g.getStrokeColor()).strokeWidth(this.f16568g.getStrokeWidth());
            this.f16566e = this.f16573l.a(circleOptions);
        }
        if (this.f16574m == 0) {
            BitmapDescriptor myLocationIcon = this.f16568g.getMyLocationIcon();
            if (myLocationIcon == null) {
                myLocationIcon = f();
            }
            Bitmap bitmap2 = myLocationIcon.getBitmap(this.f16573l.getContext());
            if (bitmap2 != null && !bitmap2.isRecycled()) {
                this.f16571j = myLocationIcon;
                this.f16574m = this.f16573l.a(myLocationIcon.getFormater().getBitmapId(), this.f16568g.getAnchorU(), this.f16568g.getAnchorV());
            }
            LocationCompass locationCompass = this.f16568g.getLocationCompass();
            if (locationCompass != null && locationCompass.getCompassImage() != null && (bitmap = (compassImage = locationCompass.getCompassImage()).getBitmap(this.f16573l.getContext())) != null && !bitmap.isRecycled()) {
                this.f16572k = compassImage;
                this.f16573l.e(compassImage.getFormater().getBitmapId());
                BitmapDescriptor[] compassGroupImages = locationCompass.getCompassGroupImages();
                if (compassGroupImages != null && compassGroupImages.length == 4) {
                    String[] strArr = new String[compassGroupImages.length];
                    for (int i2 = 0; i2 < compassGroupImages.length; i2++) {
                        if (compassGroupImages[i2] != null) {
                            compassGroupImages[i2].getBitmap(this.f16573l.getContext());
                            strArr[i2] = compassGroupImages[i2].getFormater().getBitmapId();
                        } else {
                            strArr[i2] = "";
                        }
                    }
                    this.f16573l.a(compassImage.getFormater().getBitmapId(), strArr[0], strArr[1], strArr[2], strArr[3]);
                }
            }
            LocationNavigationGravityline locationNavigationGravityline = this.f16568g.getLocationNavigationGravityline();
            if (locationNavigationGravityline != null) {
                this.f16573l.a(locationNavigationGravityline.getWidth(), locationNavigationGravityline.getColor(), locationNavigationGravityline.getDestination());
            }
        }
    }

    private void a(MyLocationStyle myLocationStyle, Location location) {
        if (location == null || myLocationStyle == null) {
            return;
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        Circle circle = this.f16566e;
        if (circle != null) {
            circle.setCenter(latLng);
            this.f16566e.setRadius(location.getAccuracy());
        }
        this.f16573l.a(GeoPoint.from(latLng), 0.0f, 0.0f, false);
        int myLocationType = myLocationStyle.getMyLocationType();
        if (myLocationType == 1) {
            this.f16573l.a(location.getBearing());
        } else if (myLocationType != 2) {
            if (myLocationType != 3) {
                this.f16573l.a(location.getBearing());
                a0 a0Var = this.a;
                if (a0Var != null) {
                    a0Var.a(CameraUpdateFactory.newLatLng(latLng));
                    return;
                }
                return;
            }
            a0 a0Var2 = this.a;
            if (a0Var2 != null) {
                this.a.a(CameraUpdateFactory.rotateTo(location.getBearing(), a0Var2.b().tilt));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Location location) {
        if (location == null) {
            return;
        }
        a(location);
        a(this.f16568g, location);
    }

    private BitmapDescriptor f() {
        if (this.o == null) {
            this.o = BitmapDescriptorFactory.fromAsset(this.f16573l, "navi_marker_location.png");
        }
        return this.o;
    }

    private LocationSource.OnLocationChangedListener h() {
        return new a();
    }

    @Override // com.tencent.mapsdk.internal.i0
    public void a() {
        Circle circle = this.f16566e;
        if (circle != null) {
            circle.setVisible(false);
            this.f16566e.remove();
            this.f16566e = null;
        }
    }

    public void a(TencentMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.f16567f = onMyLocationChangeListener;
    }

    public void a(TencentMap.OnMyLocationClickListener onMyLocationClickListener) {
        this.f16575n = onMyLocationClickListener;
    }

    public boolean a(float f2, float f3) {
        boolean d = this.f16573l.g().d(f2, f3);
        if (!d || this.f16575n == null) {
            return d;
        }
        LatLng latLng = new LatLng();
        Location location = this.f16570i;
        if (location != null) {
            latLng.setAltitude(location.getAltitude());
            latLng.setLongitude(this.f16570i.getLongitude());
            latLng.setLatitude(this.f16570i.getLatitude());
        }
        return this.f16575n.onMyLocationClicked(latLng);
    }

    @Override // com.tencent.mapsdk.internal.i0
    public void b() {
        if (this.d) {
            return;
        }
        this.d = true;
        if (this.b == null) {
            this.b = h();
        }
        this.f16573l.n(false);
        this.f16573l.m(false);
        this.f16573l.o(false);
        Circle circle = this.f16566e;
        if (circle != null) {
            circle.setVisible(true);
        }
        LocationSource locationSource = this.f16565c;
        if (locationSource != null) {
            locationSource.activate(this.b);
        }
    }

    @Override // com.tencent.mapsdk.internal.i0
    public void c() {
        Circle circle = this.f16566e;
        if (circle != null) {
            circle.setVisible(false);
            this.f16566e.remove();
            this.f16566e = null;
        }
        if (this.d) {
            this.d = false;
            this.f16573l.n(true);
            this.f16573l.m(true);
            this.f16573l.o(true);
            this.f16574m = 0;
            this.b = null;
            LocationSource locationSource = this.f16565c;
            if (locationSource != null) {
                locationSource.deactivate();
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.i0
    public boolean d() {
        return this.d;
    }

    public void e() {
        c();
        this.a = null;
    }

    public BitmapDescriptor g() {
        return this.f16571j;
    }

    @Override // com.tencent.mapsdk.internal.i0
    public Location getMyLocation() {
        if (this.f16570i == null) {
            return null;
        }
        return new Location(this.f16570i);
    }

    @Override // com.tencent.mapsdk.internal.i0
    public void setLocationSource(LocationSource locationSource) {
        this.f16565c = locationSource;
        if (!this.d || locationSource == null) {
            return;
        }
        locationSource.activate(this.b);
    }

    @Override // com.tencent.mapsdk.internal.i0
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        BitmapDescriptor compassImage;
        Bitmap bitmap;
        if (myLocationStyle == null) {
            return;
        }
        this.f16568g = myLocationStyle;
        Circle circle = this.f16566e;
        if (circle != null) {
            circle.setFillColor(myLocationStyle.getFillColor());
            this.f16566e.setStrokeColor(myLocationStyle.getStrokeColor());
            this.f16566e.setStrokeWidth(myLocationStyle.getStrokeWidth());
        }
        if (this.f16574m == 0 || this.f16571j == null) {
            return;
        }
        BitmapDescriptor myLocationIcon = myLocationStyle.getMyLocationIcon();
        if (myLocationIcon == null) {
            myLocationIcon = f();
        }
        Bitmap bitmap2 = myLocationIcon.getBitmap(this.f16573l.getContext());
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            String bitmapId = myLocationIcon.getFormater().getBitmapId();
            if (!this.f16571j.getFormater().getBitmapId().equals(bitmapId)) {
                this.f16571j = myLocationStyle.getMyLocationIcon();
                this.f16574m = this.f16573l.a(bitmapId, myLocationStyle.getAnchorU(), myLocationStyle.getAnchorV());
            }
        }
        LocationCompass locationCompass = myLocationStyle.getLocationCompass();
        if (locationCompass != null && locationCompass.getCompassImage() != null && (bitmap = (compassImage = locationCompass.getCompassImage()).getBitmap(this.f16573l.getContext())) != null && !bitmap.isRecycled()) {
            String bitmapId2 = compassImage.getFormater().getBitmapId();
            BitmapDescriptor bitmapDescriptor = this.f16572k;
            if (!(bitmapDescriptor != null ? bitmapDescriptor.getFormater().getBitmapId() : "").equals(bitmapId2)) {
                this.f16572k = compassImage;
                this.f16573l.e(bitmapId2);
            }
            BitmapDescriptor[] compassGroupImages = locationCompass.getCompassGroupImages();
            if (compassGroupImages != null && compassGroupImages.length == 4) {
                String[] strArr = new String[compassGroupImages.length];
                for (int i2 = 0; i2 < compassGroupImages.length; i2++) {
                    if (compassGroupImages[i2] != null) {
                        compassGroupImages[i2].getBitmap(this.f16573l.getContext());
                        strArr[i2] = compassGroupImages[i2].getFormater().getBitmapId();
                    } else {
                        strArr[i2] = "";
                    }
                }
                this.f16573l.a(bitmapId2, strArr[0], strArr[1], strArr[2], strArr[3]);
            }
        }
        LocationNavigationGravityline locationNavigationGravityline = myLocationStyle.getLocationNavigationGravityline();
        if (locationNavigationGravityline != null) {
            this.f16573l.a(locationNavigationGravityline.getWidth(), locationNavigationGravityline.getColor(), locationNavigationGravityline.getDestination());
        }
    }
}
