package com.jingdong.manto.q;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.hms.actions.SearchIntents;
import com.jingdong.manto.R;
import com.jingdong.manto.i.a;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.q.l;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.CircleImageView;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public abstract class j extends LinearLayout {
    public l a;
    private ViewGroup b;

    /* renamed from: c */
    public boolean f14037c;
    public boolean d;

    /* renamed from: e */
    public m f14038e;

    /* loaded from: classes16.dex */
    public class a implements View.OnClickListener {
        final /* synthetic */ l.w a;

        a(j jVar, l.w wVar) {
            this.a = wVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.a();
        }
    }

    public j(Context context, l lVar) {
        super(context);
        this.f14037c = false;
        this.a = lVar;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        ViewGroup b = b();
        this.b = b;
        b.addView(getContentView(), new LinearLayout.LayoutParams(-1, -1));
        addView(this.b, new LinearLayout.LayoutParams(-1, -1));
    }

    private void a(String str, m mVar) {
        Map<String, Object> extraMap;
        HashMap hashMap = new HashMap();
        hashMap.put("path", com.jingdong.manto.utils.t.b(j()));
        Map<String, Object> a2 = com.jingdong.manto.utils.t.a(j());
        if (m.APP_LAUNCH.equals(mVar) && (extraMap = getExtraMap()) != null) {
            a2.putAll(extraMap);
        }
        hashMap.put(SearchIntents.EXTRA_QUERY, a2);
        if (mVar != null) {
            hashMap.put("openType", mVar.toString());
        }
        a(hashMap);
        i().a(str, new JSONObject(hashMap).toString(), (int[]) null);
    }

    private void a(String str, m mVar, String str2) {
        Map<String, Object> extraMap;
        HashMap hashMap = new HashMap();
        hashMap.put("path", com.jingdong.manto.utils.t.b(j()));
        Map<String, Object> a2 = com.jingdong.manto.utils.t.a(j());
        if (m.APP_LAUNCH.equals(mVar) && (extraMap = getExtraMap()) != null) {
            a2.putAll(extraMap);
        }
        hashMap.put(SearchIntents.EXTRA_QUERY, a2);
        if (mVar != null) {
            hashMap.put("openType", mVar.toString());
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("pipMode", str2);
        }
        a(hashMap);
        i().a(str, new JSONObject(hashMap).toString(), (int[]) null);
    }

    public static void a(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if ((key instanceof String) && (value instanceof Map)) {
                Map map2 = (Map) value;
                a(map2);
                map.put(key, new JSONObject(map2));
            }
        }
    }

    public static boolean a(int[] iArr, int i2) {
        if (iArr == null || iArr.length == 0) {
            return true;
        }
        for (int i3 : iArr) {
            if (i3 == i2) {
                return true;
            }
        }
        return false;
    }

    private ViewGroup b() {
        boolean z;
        Context context;
        com.jingdong.manto.f fVar;
        l lVar = this.a;
        if (lVar == null || (fVar = lVar.a) == null) {
            z = false;
            context = null;
        } else {
            z = fVar.u();
            context = this.a.a.i();
        }
        if (z) {
            return new FrameLayout(getContext());
        }
        if (context == null) {
            context = getContext();
        }
        return new com.jingdong.manto.widget.a(context);
    }

    private Map<String, Object> getExtraMap() {
        com.jingdong.manto.f fVar;
        com.jingdong.manto.i.c cVar;
        try {
            l lVar = this.a;
            if (lVar == null || (fVar = lVar.a) == null || (cVar = fVar.r) == null || TextUtils.isEmpty(cVar.f13090m)) {
                return null;
            }
            return MantoUtils.jsonToMap(new JSONObject(this.a.a.r.f13090m));
        } catch (Throwable unused) {
            return null;
        }
    }

    public View a(l.w wVar) {
        int i2;
        try {
            l lVar = this.a;
            PkgDetailEntity pkgDetailEntity = lVar.a.f13016h;
            String str = pkgDetailEntity.logo;
            String str2 = pkgDetailEntity.name;
            View inflate = LayoutInflater.from(lVar.getContext()).inflate(R.layout.manto_ui_subpkg_fail, (ViewGroup) null);
            CircleImageView circleImageView = (CircleImageView) inflate.findViewById(R.id.manto_subpkg_fail_logo);
            IImageLoader iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
            if (iImageLoader != null) {
                iImageLoader.loadImage(circleImageView, str);
            }
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            circleImageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            TextView textView = (TextView) inflate.findViewById(R.id.manto_subpkg_fail_name);
            if (wVar != null && !TextUtils.isEmpty(wVar.b())) {
                textView.setText(wVar.b());
            }
            Button button = (Button) inflate.findViewById(R.id.manto_subpkg_reload);
            int a2 = com.jingdong.manto.k.a.b().a();
            l lVar2 = this.a;
            boolean g2 = lVar2 != null ? lVar2.g() : false;
            if (a2 == 1 && g2) {
                inflate.setBackgroundColor(getResources().getColor(R.color.manto_dark_background_weight));
                Resources resources = getResources();
                i2 = R.color.manto_dark_text_weight;
                textView.setTextColor(resources.getColor(i2));
            } else {
                inflate.setBackgroundColor(getResources().getColor(R.color.manto_day_background_weight));
                Resources resources2 = getResources();
                i2 = R.color.manto_day_text_weight;
                textView.setTextColor(resources2.getColor(i2));
            }
            button.setTextColor(getResources().getColor(i2));
            button.setOnClickListener(new a(this, wVar));
            return inflate;
        } catch (Throwable unused) {
            return null;
        }
    }

    public void a() {
    }

    public final void a(m mVar, String str) {
        a("onAppRoute", mVar, str);
        com.jingdong.manto.r.d.f14135f = System.currentTimeMillis();
    }

    public abstract void a(String str, String str2, int[] iArr);

    public abstract boolean a(String str);

    public abstract void b(String str);

    public final void c() {
        if (this.d) {
            setVisibility(4);
        }
    }

    public final void d() {
        a("onAppRouteDone", (m) null);
        if (com.jingdong.manto.s.a.f().d() || com.jingdong.manto.s.b.e().c()) {
            i().a("remoteDebugOnShow", (String) null, i().hashCode());
        }
    }

    public void e() {
        this.d = true;
    }

    public void f() {
    }

    public void g() {
        this.d = false;
        setVisibility(0);
    }

    public abstract View getContentView();

    public a.j getWindowConfig() {
        return this.a.a.t.b(com.jingdong.manto.utils.t.b(j()));
    }

    public final void h() {
        k();
        a("onPageNotFound", this.f14038e);
    }

    public abstract n i();

    public abstract String j();

    public void k() {
        Resources resources;
        int i2;
        try {
            String m2 = this.a.a.m();
            if (j() == null || m2 == null || TextUtils.equals(com.jingdong.manto.utils.t.b(m2), com.jingdong.manto.utils.t.b(j()))) {
                l lVar = this.a;
                PkgDetailEntity pkgDetailEntity = lVar.a.f13016h;
                String str = pkgDetailEntity.logo;
                String str2 = pkgDetailEntity.name;
                View inflate = LayoutInflater.from(lVar.getContext()).inflate(R.layout.manto_ui_not_found, (ViewGroup) null);
                CircleImageView circleImageView = (CircleImageView) inflate.findViewById(R.id.manto_not_found_logo);
                IImageLoader iImageLoader = (IImageLoader) com.jingdong.a.n(IImageLoader.class);
                if (iImageLoader != null) {
                    iImageLoader.loadImage(circleImageView, str);
                }
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setSaturation(0.0f);
                circleImageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                TextView textView = (TextView) inflate.findViewById(R.id.manto_not_found_desc);
                textView.setText(String.format(getResources().getString(R.string.manto_page_not_found_desc), str2));
                TextView textView2 = (TextView) inflate.findViewById(R.id.manto_not_found_name);
                int a2 = com.jingdong.manto.k.a.b().a();
                l lVar2 = this.a;
                boolean g2 = lVar2 != null ? lVar2.g() : false;
                if (a2 == 1 && g2) {
                    inflate.setBackgroundColor(getResources().getColor(R.color.manto_dark_background_weight));
                    textView.setTextColor(getResources().getColor(R.color.manto_dark_text_light));
                    resources = getResources();
                    i2 = R.color.manto_dark_text_weight;
                } else {
                    inflate.setBackgroundColor(getResources().getColor(R.color.manto_day_background_weight));
                    textView.setTextColor(getResources().getColor(R.color.manto_day_text_light));
                    resources = getResources();
                    i2 = R.color.manto_day_text_weight;
                }
                textView2.setTextColor(resources.getColor(i2));
                ((ViewGroup) this.a.getFirstPage().i().o()).addView(inflate);
                this.a.getFirstPage().i().H();
            }
        } catch (Throwable unused) {
        }
    }

    public void l() {
        a.j windowConfig = getWindowConfig();
        i().a(windowConfig.f13064e, windowConfig.f13066g);
        i().e(windowConfig.d);
        i().f(windowConfig.b);
    }

    public void m() {
        a.j windowConfig = getWindowConfig();
        i().x();
        i().a(windowConfig.f13065f);
        i().h(windowConfig.a);
        i().d(windowConfig.f13071l);
        l();
    }
}
