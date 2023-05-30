package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class ze extends ye<n0> implements n0, q4 {
    public static final float O = 5.0f;
    private static AtomicInteger P = new AtomicInteger(0);
    private hg B;
    private Bitmap C;
    private int D;
    private int E;
    private GeoPoint F;
    private xi G;
    private o0 H;
    private boolean I;
    private float J;
    private float K;
    private boolean L;
    private View M;
    private int N;

    public ze(xi xiVar, o0 o0Var) {
        super(xiVar);
        this.C = null;
        this.D = 0;
        this.E = 0;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = false;
        this.J = 0.5f;
        this.K = 0.5f;
        this.L = false;
        this.G = xiVar;
        this.H = o0Var;
        K();
    }

    private void K() {
        o0 o0Var = this.H;
        if (this.G == null || o0Var == null || o0Var.getOptions() == null) {
            return;
        }
        L();
        if (this.G.getMap().F() == null) {
            return;
        }
        this.B = new hg(this, this.G, b(o0Var.getOptions()));
        ma.c("create InfoWindowView:" + this.M);
        b(a7.a(this.M));
    }

    private void L() {
        xi xiVar = this.G;
        if (xiVar == null || xiVar.getContext() == null) {
            return;
        }
        o0 o0Var = this.H;
        View a = a(xiVar.getContext(), xiVar.getMapContext(), this, o0Var != null ? o0Var.t() : null, o0Var);
        this.M = a;
        if (a != null) {
            a.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            this.D = this.M.getMeasuredWidth();
            this.E = this.M.getMeasuredHeight();
            View view = this.M;
            view.layout(0, 0, view.getMeasuredWidth(), this.M.getMeasuredHeight());
        }
    }

    public static View a(Context context, e1 e1Var, q4 q4Var, TencentMap.InfoWindowAdapter infoWindowAdapter, Marker marker) {
        if (context == null || q4Var == null || marker == null || e1Var == null) {
            return null;
        }
        String title = marker.getTitle();
        String snippet = marker.getSnippet();
        if (infoWindowAdapter == null) {
            if (e7.b(title) && e7.b(snippet)) {
                return null;
            }
            return a(context, e1Var, q4Var, title, snippet);
        }
        LinearLayout linearLayout = (LinearLayout) q4Var.o();
        if (linearLayout == null) {
            linearLayout = a(context);
        }
        w0 w0Var = (w0) e1Var.g().c().a(marker.getId(), w0.class);
        if (w0Var == null) {
            return null;
        }
        View infoWindow = infoWindowAdapter.getInfoWindow(w0Var);
        if (infoWindow != null) {
            if (infoWindow.getParent() == linearLayout) {
                return linearLayout;
            }
            if (infoWindow.getParent() instanceof ViewGroup) {
                ((ViewGroup) infoWindow.getParent()).removeAllViews();
            }
            linearLayout.setBackgroundDrawable(null);
            linearLayout.removeAllViews();
            linearLayout.addView(infoWindow);
            return linearLayout;
        }
        View infoContents = infoWindowAdapter.getInfoContents(w0Var);
        if (infoContents != null) {
            if (infoContents.getParent() == linearLayout) {
                return linearLayout;
            }
            if (infoContents.getParent() instanceof ViewGroup) {
                ((ViewGroup) infoContents.getParent()).removeAllViews();
            }
            a(linearLayout, "marker_infowindow.9.png");
            linearLayout.removeAllViews();
            linearLayout.addView(infoContents);
            return linearLayout;
        } else if (e7.b(title) && e7.b(snippet)) {
            return null;
        } else {
            a(linearLayout, "marker_infowindow.9.png");
            linearLayout.removeAllViews();
            a(e1Var, linearLayout, q4.d, title);
            a(e1Var, linearLayout, q4.f17017e, snippet);
            return linearLayout;
        }
    }

    public static View a(Context context, TencentMapContext tencentMapContext, q4 q4Var, String str, String str2) {
        LinearLayout linearLayout = (LinearLayout) q4Var.o();
        if (linearLayout == null) {
            linearLayout = a(context);
        } else {
            linearLayout.removeAllViews();
        }
        a(linearLayout, "marker_infowindow.9.png");
        a(tencentMapContext, linearLayout, q4.d, str);
        a(tencentMapContext, linearLayout, q4.f17017e, str2);
        return linearLayout;
    }

    public static LinearLayout a(Context context) {
        if (context == null) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setTag(q4.f17016c);
        linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        return linearLayout;
    }

    private static void a(ViewGroup viewGroup, String str) {
        if (TextUtils.isEmpty(str)) {
            viewGroup.setBackgroundDrawable(null);
        }
        Bitmap c2 = a7.c(viewGroup.getContext(), str);
        if (c2 != null) {
            viewGroup.setBackgroundDrawable(new NinePatchDrawable(viewGroup.getContext().getResources(), c2, c2.getNinePatchChunk(), new Rect(10, 10, 10, 30), null));
        }
    }

    private static void a(TencentMapContext tencentMapContext, LinearLayout linearLayout, String str, String str2) {
        if (linearLayout == null || linearLayout.getContext() == null) {
            return;
        }
        Context context = linearLayout.getContext();
        TextView textView = (TextView) linearLayout.findViewWithTag(str);
        if (TextUtils.isEmpty(str2)) {
            if (textView != null) {
                linearLayout.removeView(textView);
                return;
            }
            return;
        }
        if (textView == null) {
            textView = new nc(context, tencentMapContext);
            textView.setTag(str);
            textView.setGravity(3);
            textView.setTextColor(-16777216);
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        textView.setText(str2);
    }

    private ig b(MarkerOptions markerOptions) {
        int infoWindowOffsetX = markerOptions.getInfoWindowOffsetX();
        int infowindowOffsetY = markerOptions.getInfowindowOffsetY();
        w();
        k();
        return new ig().a(GeoPoint.from(markerOptions.getPosition())).a(markerOptions.getAlpha()).a(this.J - ((infoWindowOffsetX * 1.0f) / this.D), this.K - ((infowindowOffsetY * 1.0f) / this.E)).f(false).d((int) markerOptions.getZIndex()).b(markerOptions.getLevel()).e(this.I).d(true);
    }

    private void b(Bitmap bitmap) {
        this.C = bitmap;
        if (bitmap == null) {
            return;
        }
        ma.c("setBitmapAssist:" + bitmap);
        String str = (bitmap.hashCode() + P.getAndIncrement()) + "";
        hg hgVar = this.B;
        if (hgVar != null) {
            hgVar.b(str, bitmap);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        hg hgVar;
        if (this.f17514n && (hgVar = this.B) != null) {
            hgVar.E();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void G() {
        super.G();
        hg hgVar = this.B;
        if (hgVar == null || hgVar.N() == this.N) {
            return;
        }
        ma.a(la.f16819f, "\u8bbe\u7f6e\u4e3b\u4ece\u7ed1\u5b9a\u5173\u7cfb\uff1a" + this.H.a() + "|" + this.B.N());
        this.N = this.B.N();
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        releaseData();
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: J */
    public n0 x() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        hg hgVar = this.B;
        return hgVar != null ? hgVar.getBound(s4Var) : new Rect(0, 0, 0, 0);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void a(MarkerOptions markerOptions) {
        if (markerOptions == null || this.B == null) {
            return;
        }
        setVisible(markerOptions.isVisible());
        setLevel(markerOptions.getLevel());
        L();
        ig b = b(markerOptions);
        if (b == null) {
            return;
        }
        this.B.a(b);
        b(a7.a(this.M));
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        hg hgVar = this.B;
        if (hgVar == null) {
            return null;
        }
        return hgVar.getScreenBound(s4Var);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void e(boolean z) {
        this.L = z;
        xi xiVar = this.G;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.G.getMap().v0();
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void k() {
        o0 o0Var = this.H;
        xi xiVar = this.G;
        if (o0Var == null || xiVar == null || xiVar.getContext() == null) {
            return;
        }
        int height = (int) (o0Var.getHeight(xiVar.getContext()) * o0Var.getAnchorV());
        int i2 = this.E;
        float infoWindowAnchorV = o0Var.getOptions() != null ? o0Var.getOptions().getInfoWindowAnchorV() : 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        float f2 = height;
        float f3 = i2;
        this.K = (f2 + (infoWindowAnchorV * f3)) / f3;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public void n() {
    }

    @Override // com.tencent.mapsdk.internal.q4
    public View o() {
        return this.M;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        hg hgVar = this.B;
        if (hgVar == null) {
            return false;
        }
        return hgVar.onTap(f2, f3);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public boolean r() {
        return this.L && this.C != null;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
        Bitmap bitmap = this.C;
        if (bitmap != null) {
            bitmap.recycle();
            this.C = null;
        }
        this.H = null;
        this.G = null;
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setAnchor(float f2, float f3) {
        int i2;
        o0 o0Var = this.H;
        int i3 = 0;
        if (o0Var == null || o0Var.getOptions() == null) {
            i2 = 0;
        } else {
            i3 = o0Var.getOptions().getInfoWindowOffsetX();
            i2 = o0Var.getOptions().getInfowindowOffsetY();
        }
        w();
        k();
        float f4 = this.J - ((i3 * 1.0f) / this.D);
        float f5 = this.K - ((i2 * 1.0f) / this.E);
        hg hgVar = this.B;
        if (hgVar != null) {
            hgVar.setAnchor(f4, f5);
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setFixingPoint(int i2, int i3) {
        if (this.B != null) {
            setFixingPointEnable(true);
            this.B.a(new GeoPoint(i3, i2));
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setFixingPointEnable(boolean z) {
        this.I = z;
        hg hgVar = this.B;
        if (hgVar != null) {
            hgVar.h(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            return;
        }
        GeoPoint geoPoint = this.F;
        if (geoPoint == null) {
            this.F = GeoPoint.from(latLng);
        } else {
            geoPoint.setLatitudeE6((int) (latLng.latitude * 1000000.0d));
            this.F.setLongitudeE6((int) (latLng.longitude * 1000000.0d));
        }
        hg hgVar = this.B;
        if (hgVar != null) {
            hgVar.a(this.F);
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void u() {
        o0 o0Var = this.H;
        if (o0Var == null) {
            return;
        }
        a(o0Var.getOptions());
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void w() {
        o0 o0Var = this.H;
        xi xiVar = this.G;
        if (o0Var == null || xiVar == null || xiVar.getContext() == null) {
            return;
        }
        int width = o0Var.getWidth(xiVar.getContext());
        float infoWindowAnchorU = o0Var.getOptions() != null ? o0Var.getOptions().getInfoWindowAnchorU() : 0.5f;
        int i2 = this.D;
        if (i2 == 0) {
            i2 = 1;
        }
        this.J = infoWindowAnchorU + ((width * (o0Var.getAnchorU() - 0.5f)) / i2);
    }
}
