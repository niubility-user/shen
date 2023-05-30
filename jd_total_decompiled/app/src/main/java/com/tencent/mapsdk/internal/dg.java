package com.tencent.mapsdk.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.mapsdk.internal.eg;
import com.tencent.mapsdk.internal.o4;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorLevel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class dg extends m4 implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, eg.a {
    private static final String A = "VIEW_TAG_HEADER";
    private static final String B = "VIEW_TAG_FOOTER";
    private static final float C = 2.7f;
    private static final float D = 44.0f;
    private static final float E = 13.0f;
    private static final float F = 26.0f;
    private static final float G = 9.5f;
    private static final float H = 37.0f;
    private static final float I = 4.0f;
    private static final float J = 45.0f;
    private static final float K = 15.0f;
    private static final int L = -1;
    private static final int N = -1;
    private bg d;

    /* renamed from: e  reason: collision with root package name */
    private eg f16418e;

    /* renamed from: f  reason: collision with root package name */
    private ag f16419f;

    /* renamed from: i  reason: collision with root package name */
    private d f16422i;

    /* renamed from: j  reason: collision with root package name */
    private Context f16423j;

    /* renamed from: k  reason: collision with root package name */
    private ViewGroup f16424k;

    /* renamed from: n  reason: collision with root package name */
    private IndoorBuilding f16427n;
    private String o;
    private boolean p;
    private b0 s;
    private xi t;
    private int u;
    private int v;
    private boolean w;
    private zf x;
    private zf y;
    private static final int M = Color.parseColor("#333333");
    private static final int O = Color.parseColor("#979797");

    /* renamed from: g  reason: collision with root package name */
    private float f16420g = 1.0f;

    /* renamed from: h  reason: collision with root package name */
    private int f16421h = 0;

    /* renamed from: l  reason: collision with root package name */
    private int f16425l = -1;

    /* renamed from: m  reason: collision with root package name */
    private int f16426m = 0;
    public boolean q = false;
    private boolean r = false;
    private d.a z = null;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) dg.this.d.getLayoutParams();
            marginLayoutParams.bottomMargin = dg.this.u;
            dg.this.d.setLayoutParams(marginLayoutParams);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (dg.this.d == null || dg.this.d.getVisibility() != 0) {
                return;
            }
            dg.this.d.setVisibility(8);
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (dg.this.d == null || dg.this.f16418e == null) {
                return;
            }
            ViewGroup.LayoutParams layoutParams = dg.this.f16418e.getLayoutParams();
            if (layoutParams.height != dg.this.f16421h) {
                layoutParams.height = dg.this.f16421h;
                dg.this.f16418e.setLayoutParams(layoutParams);
            }
            if (dg.this.d.getVisibility() != 0) {
                dg.this.d.setVisibility(0);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d extends BaseAdapter {
        private Context a;
        private List<IndoorLevel> b;

        /* loaded from: classes9.dex */
        public class a {
            public TextView a;
            public View b;

            public a(TextView textView, View view) {
                this.a = textView;
                this.b = view;
            }
        }

        public d(Context context, List<IndoorLevel> list) {
            this.a = context;
            this.b = list;
        }

        public void a(List<IndoorLevel> list) {
            this.b = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<IndoorLevel> list = this.b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            List<IndoorLevel> list = this.b;
            if (list == null) {
                return null;
            }
            return list.get(i2);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.Adapter
        @NonNull
        public View getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
            View view2;
            TextView textView;
            FrameLayout frameLayout;
            List<IndoorLevel> list = this.b;
            if (list == null || list.size() == 0) {
                return null;
            }
            if (view != null) {
                a aVar = (a) view.getTag();
                textView = aVar.a;
                view2 = aVar.b;
                frameLayout = view;
            } else {
                FrameLayout frameLayout2 = new FrameLayout(this.a);
                view2 = new View(this.a);
                double d = dg.this.f16420g;
                Double.isNaN(d);
                double d2 = dg.this.f16420g;
                Double.isNaN(d2);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (d * 26.5d), (int) (d2 * 26.5d));
                if (dg.this.f16419f == null) {
                    dg.this.f16419f = new ag();
                    dg.this.f16419f.setBounds(0, 0, layoutParams.width, layoutParams.height);
                }
                if (Build.VERSION.SDK_INT < 16) {
                    view2.setBackgroundDrawable(dg.this.f16419f);
                } else {
                    view2.setBackground(dg.this.f16419f);
                }
                layoutParams.gravity = 17;
                frameLayout2.addView(view2, layoutParams);
                textView = new TextView(this.a);
                textView.setIncludeFontPadding(false);
                textView.setSingleLine();
                textView.setGravity(17);
                textView.setTextSize(2, dg.E);
                double d3 = dg.this.f16420g;
                Double.isNaN(d3);
                int i3 = (int) (d3 * 10.0d);
                textView.setPadding(0, i3, 0, i3);
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, (int) (dg.this.f16420g * 37.5f));
                layoutParams2.gravity = 17;
                frameLayout2.addView(textView, layoutParams2);
                frameLayout2.setTag(new a(textView, view2));
                frameLayout = frameLayout2;
            }
            textView.setText(this.b.get(i2).getName());
            if (i2 != dg.this.f16425l) {
                textView.setTextColor(dg.this.w ? dg.O : dg.M);
                view2.setVisibility(4);
            } else {
                boolean unused = dg.this.w;
                textView.setTextColor(-1);
                view2.setVisibility(0);
            }
            return frameLayout;
        }
    }

    public dg(e1 e1Var) {
        this.p = false;
        xi xiVar = (xi) e1Var.j();
        this.t = xiVar;
        this.f16424k = xiVar.F();
        this.w = e1Var.a();
        this.f16423j = this.f16424k.getContext().getApplicationContext();
        this.p = true;
    }

    private int a(Adapter adapter) {
        double d2 = this.f16420g;
        Double.isNaN(d2);
        int i2 = (int) (d2 * 44.5d);
        int count = adapter.getCount();
        View view = null;
        for (int i3 = 0; i3 < count; i3++) {
            view = adapter.getView(i3, view, this.f16418e);
            view.measure(0, 0);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    private void a(Context context) {
        this.f16420g = context.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    private void a(Context context, d dVar) {
        this.f16418e = new eg(context);
        this.f16418e.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.f16418e.setChoiceMode(1);
        this.f16418e.setAdapter((ListAdapter) dVar);
        this.f16418e.setOnItemClickListener(this);
        this.f16418e.setVerticalScrollBarEnabled(false);
        this.f16418e.setHorizontalScrollBarEnabled(false);
        this.f16418e.setOverScrollMode(2);
        this.f16418e.setDivider(null);
        this.f16418e.setDividerHeight(0);
        this.f16418e.setOnDataChangedListener(this);
        this.d.addView(this.f16418e);
        this.f16418e.setOnScrollListener(this);
    }

    private void a(List<IndoorLevel> list) {
        if (this.f16424k == null) {
            return;
        }
        l();
        d dVar = this.f16422i;
        if (dVar != null) {
            dVar.a(list);
        }
    }

    private void b(Context context) {
        zf zfVar = new zf(context);
        this.y = zfVar;
        zfVar.setDarkStyle(this.w);
        this.y.setTag(B);
        this.y.setRotation(180.0f);
        double d2 = this.f16420g;
        Double.isNaN(d2);
        int i2 = (int) (d2 * 5.900000095367432d);
        double d3 = this.f16420g;
        Double.isNaN(d3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, (int) (d3 * 3.200000047683716d));
        layoutParams.setMargins(0, i2, 0, i2);
        this.y.setLayoutParams(layoutParams);
        this.d.addView(this.y);
    }

    @TargetApi(9)
    private void b(Context context, d dVar) {
        d(context);
        c(context);
        a(context, dVar);
        b(context);
        a(this.f16424k, (Bundle) null);
        this.d.setVisibility(8);
    }

    private void c(Context context) {
        zf zfVar = new zf(context);
        this.x = zfVar;
        zfVar.setDarkStyle(this.w);
        this.x.setTag(A);
        double d2 = this.f16420g;
        Double.isNaN(d2);
        int i2 = (int) (d2 * 5.900000095367432d);
        double d3 = this.f16420g;
        Double.isNaN(d3);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, (int) (d3 * 3.200000047683716d));
        layoutParams.setMargins(0, i2, 0, i2);
        this.x.setLayoutParams(layoutParams);
        this.d.addView(this.x);
    }

    private void d(Context context) {
        this.d = new bg(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 83;
        layoutParams.leftMargin = (int) (this.f16420g * K);
        layoutParams.bottomMargin = this.u;
        this.d.setDarkStyle(this.w);
        this.d.setLayoutParams(layoutParams);
        this.d.setWillNotDraw(false);
        this.d.setOrientation(1);
        this.d.setGravity(1);
        this.d.setVisibility(8);
    }

    private void g() {
        if (this.d == null) {
            this.f16422i = new d(this.f16423j, new ArrayList());
            a(this.f16423j);
            this.u = (int) (this.f16420g * J);
            b(this.f16423j, this.f16422i);
            xi xiVar = this.t;
            if (xiVar != null) {
                this.s = xiVar.i();
            }
        }
    }

    private void i() {
        eg egVar = this.f16418e;
        if (egVar != null) {
            egVar.getLayoutParams().width = a(this.f16422i);
            this.f16418e.requestLayout();
        }
    }

    private void j() {
        if (this.f16424k == null || this.d == null) {
            return;
        }
        double d2 = this.f16420g;
        Double.isNaN(d2);
        int i2 = (this.u * 2) + ((int) (d2 * 16.700000762939453d));
        if (li.f16848c.equals(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE) && this.f16424k.getMeasuredHeight() > this.v) {
            int measuredHeight = this.f16424k.getMeasuredHeight() - this.v;
            int i3 = this.u;
            double d3 = this.f16420g;
            Double.isNaN(d3);
            i2 = ((int) (d3 * 16.700000762939453d)) + i3 + (measuredHeight - i3);
        }
        float f2 = this.f16426m;
        if (f2 >= 4.0f) {
            double d4 = this.f16420g;
            Double.isNaN(d4);
            this.f16421h = (int) (d4 * 148.5d);
            if (this.f16424k.getMeasuredHeight() > this.f16421h + i2) {
                this.r = false;
                return;
            }
            double d5 = this.f16420g;
            Double.isNaN(d5);
            this.f16421h = (int) (d5 * 111.5d);
            if (this.f16424k.getMeasuredHeight() > i2 + this.f16421h) {
                this.r = false;
                return;
            }
        } else {
            double d6 = f2 * H;
            Double.isNaN(d6);
            double d7 = this.f16420g;
            Double.isNaN(d7);
            this.f16421h = (int) ((d6 + 0.5d) * d7);
            if (this.f16424k.getMeasuredHeight() > i2 + this.f16421h) {
                this.r = false;
                return;
            }
        }
        this.r = true;
    }

    private void k() {
        boolean a2 = this.t.A().a();
        ma.a(la.f16819f, "updateIndoorStyle isDark: cur[" + a2 + "]|old[" + this.w + "]");
        if (a2 != this.w) {
            this.d.setDarkStyle(a2);
            this.x.setDarkStyle(a2);
            this.y.setDarkStyle(a2);
            this.f16422i.notifyDataSetChanged();
            this.w = a2;
        }
    }

    private void l() {
        j();
        bg bgVar = this.d;
        if (bgVar == null || this.f16418e == null) {
            return;
        }
        bgVar.post((!this.p || this.r || this.f16426m < 1) ? new b() : new c());
    }

    private void m() {
        IndoorBuilding indoorBuilding = this.f16427n;
        if (indoorBuilding == null) {
            return;
        }
        int activeLevelIndex = indoorBuilding.getActiveLevelIndex();
        List<IndoorLevel> levels = this.f16427n.getLevels();
        if (levels == null || activeLevelIndex >= levels.size() || activeLevelIndex == -1 || levels.get(activeLevelIndex) == null) {
            return;
        }
        String str = this.o;
        if (str != null && this.q && str.equals(this.f16427n.getBuidlingId())) {
            this.f16418e.setItemChecked(activeLevelIndex, true);
            this.q = false;
        } else {
            eg egVar = this.f16418e;
            double d2 = this.f16420g * H;
            Double.isNaN(d2);
            egVar.setSelectionFromTop(activeLevelIndex, ((int) (d2 + 0.5d)) * 2);
        }
        this.f16425l = activeLevelIndex;
        this.o = this.f16427n.getBuidlingId();
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a() {
    }

    public void a(int i2) {
        this.u = i2;
        bg bgVar = this.d;
        if (bgVar != null) {
            bgVar.post(new a());
        }
        ViewGroup viewGroup = this.f16424k;
        if (viewGroup != null) {
            this.v = viewGroup.getMeasuredHeight();
        }
        xi xiVar = this.t;
        if (xiVar == null || xiVar.getMap() == null || this.t.getMap().M() == null || this.t.getMap().M().b() == null) {
            return;
        }
        this.v = (((int) this.t.getMap().M().b().b) - i2) * 2;
        l();
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
        if (this.d == null || this.f16418e == null) {
            return;
        }
        l();
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a(o4.b bVar) {
    }

    public void a(IndoorBuilding indoorBuilding) {
        if (indoorBuilding == null) {
            this.f16427n = indoorBuilding;
            this.f16426m = 0;
            l();
            return;
        }
        if (this.d == null) {
            g();
        }
        IndoorBuilding indoorBuilding2 = this.f16427n;
        if (indoorBuilding2 != null && indoorBuilding2.getBuidlingId().equals(indoorBuilding.getBuidlingId()) && this.f16427n.getActiveLevelIndex() == indoorBuilding.getActiveLevelIndex()) {
            return;
        }
        this.f16427n = indoorBuilding;
        this.f16426m = indoorBuilding.getLevels().size();
        a(indoorBuilding.getLevels());
    }

    public void a(boolean z) {
        if (this.f16424k == null || this.t == null) {
            return;
        }
        this.p = z;
        b(this.p);
    }

    @Override // com.tencent.mapsdk.internal.o4
    public boolean a(ViewGroup viewGroup, Bundle bundle) {
        bg bgVar = this.d;
        if (bgVar == null) {
            return false;
        }
        if (viewGroup.indexOfChild(bgVar) < 0) {
            viewGroup.addView(this.d);
        }
        k();
        i();
        return true;
    }

    @Override // com.tencent.mapsdk.internal.eg.a
    public void b() {
        i();
        m();
    }

    public void b(boolean z) {
        b0 b0Var;
        if (this.f16424k == null || this.t == null) {
            return;
        }
        if (this.d == null) {
            if (!z) {
                return;
            }
            g();
        }
        VectorMap map = this.t.getMap();
        if (this.p && z && (b0Var = this.s) != null && b0Var.l()) {
            a(map.L().c());
            return;
        }
        a((IndoorBuilding) null);
        if (this.d.getVisibility() != 8) {
            this.d.setVisibility(8);
        }
    }

    @Override // com.tencent.mapsdk.internal.m4
    public View[] c() {
        return new View[]{this.d};
    }

    @Override // com.tencent.mapsdk.internal.o4
    public o4.b getPosition() {
        return null;
    }

    public boolean h() {
        return this.p;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        VectorMap map = this.t.getMap();
        if (map == null) {
            return;
        }
        d.a aVar = this.z;
        if (aVar != null) {
            aVar.a.setTextColor(-16777216);
            this.z.b.setVisibility(4);
        }
        d.a aVar2 = (d.a) view.getTag();
        aVar2.a.setTextColor(-1);
        aVar2.b.setVisibility(0);
        this.z = aVar2;
        this.f16425l = i2;
        this.q = true;
        map.setIndoorFloor(i2);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        bg bgVar = this.d;
        if (bgVar == null) {
            return;
        }
        zf zfVar = (zf) bgVar.findViewWithTag(A);
        zf zfVar2 = (zf) this.d.findViewWithTag(B);
        if (zfVar == null || zfVar2 == null) {
            return;
        }
        if (i3 == i4) {
            zfVar.setActivate(false);
        } else {
            if (i2 == 0) {
                zfVar.setActivate(false);
            } else {
                zfVar.setActivate(true);
            }
            if (i2 + i3 < i4) {
                zfVar2.setActivate(true);
                return;
            }
        }
        zfVar2.setActivate(false);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i2) {
    }
}
