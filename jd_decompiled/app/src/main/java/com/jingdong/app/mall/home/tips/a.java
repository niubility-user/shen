package com.jingdong.app.mall.home.tips;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.o.a.n;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.log.Log;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: n */
    private static int f10899n;
    private boolean a;
    private final Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    private final AtomicBoolean f10900c = new AtomicBoolean(false);
    private final RelativeLayout d;

    /* renamed from: e */
    private final Context f10901e;

    /* renamed from: f */
    private Runnable f10902f;

    /* renamed from: g */
    private boolean f10903g;

    /* renamed from: h */
    private BaseFloatPriority f10904h;

    /* renamed from: i */
    private com.jingdong.app.mall.home.tips.c f10905i;

    /* renamed from: j */
    private com.jingdong.app.mall.home.tips.b f10906j;

    /* renamed from: k */
    private RecommendTips f10907k;

    /* renamed from: l */
    private PermissionTips f10908l;

    /* renamed from: m */
    private boolean f10909m;

    /* renamed from: com.jingdong.app.mall.home.tips.a$a */
    /* loaded from: classes4.dex */
    public class C0328a extends BaseFloatPriority {

        /* renamed from: i */
        final /* synthetic */ View f10910i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0328a(String str, int i2, View view) {
            super(str, i2);
            a.this = r1;
            this.f10910i = view;
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        protected void g(int i2) {
            if (a.this.f10903g) {
                a.this.l();
            }
        }

        @Override // com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority
        public void h() {
            a.this.y(this.f10910i);
        }
    }

    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ View f10912g;

        b(View view) {
            a.this = r1;
            this.f10912g = view;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            a.this.d.removeAllViews();
            com.jingdong.app.mall.home.v.c.a.a(this.f10912g);
            m.b(a.this.d, this.f10912g, -1);
            a.this.d.setVisibility(0);
            com.jingdong.app.mall.home.o.a.d.g();
        }
    }

    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            a.this.l();
        }
    }

    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (a.this.k()) {
                int i2 = a.this.f10905i.i();
                if (i2 == 8) {
                    a.this.B();
                } else if (i2 == 15) {
                    a.this.z();
                }
            }
        }
    }

    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
            a.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (a.this.d != null) {
                a.this.d.setVisibility(8);
                a.this.d.removeAllViews();
            }
        }
    }

    /* loaded from: classes4.dex */
    public class f implements View.OnClickListener {
        f() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            n.e((Activity) a.this.f10901e);
            a.this.m(true);
            a.this.f10905i.m(a.this.f10901e);
        }
    }

    /* loaded from: classes4.dex */
    public class g implements View.OnClickListener {
        g() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.m(true);
            a.this.f10905i.o(a.this.f10901e);
        }
    }

    /* loaded from: classes4.dex */
    public class h implements View.OnClickListener {
        h() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.floor.common.i.l.e(a.this.f10901e, a.this.f10905i.getJump());
            a.this.l();
            com.jingdong.app.mall.home.tips.c cVar = a.this.f10905i;
            Context context = a.this.f10901e;
            a aVar = a.this;
            cVar.n(context, aVar.j(aVar.f10905i.h(), 0));
        }
    }

    /* loaded from: classes4.dex */
    public class i implements View.OnClickListener {
        i() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.f10907k.d();
            a.this.l();
            com.jingdong.app.mall.home.tips.c cVar = a.this.f10905i;
            Context context = a.this.f10901e;
            a aVar = a.this;
            cVar.n(context, aVar.j(aVar.f10905i.h(), 1));
        }
    }

    /* loaded from: classes4.dex */
    public class j implements View.OnClickListener {
        j() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.l();
            a.this.f10905i.o(a.this.f10901e);
        }
    }

    /* loaded from: classes4.dex */
    public class k implements View.OnClickListener {
        k() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.floor.common.i.l.e(a.this.f10901e, a.this.f10905i.getJump());
            a.this.l();
            a.this.f10905i.m(a.this.f10901e);
        }
    }

    /* loaded from: classes4.dex */
    public class l implements View.OnClickListener {
        l() {
            a.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.l();
            a.this.f10905i.o(a.this.f10901e);
        }
    }

    public a(RelativeLayout relativeLayout) {
        this.d = relativeLayout;
        this.f10901e = relativeLayout.getContext();
    }

    private void A() {
        JDHomeFragment z0;
        if (com.jingdong.app.mall.home.i.i() || (z0 = JDHomeFragment.z0()) == null || !z0.isAdded() || this.f10905i == null || this.a || !k()) {
            return;
        }
        RecommendTips recommendTips = new RecommendTips(this.f10901e);
        this.f10907k = recommendTips;
        if (recommendTips.b(this.f10905i)) {
            this.f10907k.h(new h());
            this.f10907k.f(new i());
            this.f10907k.g(new j());
            x(this.f10907k);
        }
    }

    public void B() {
        com.jingdong.app.mall.home.tips.c cVar = this.f10905i;
        if (cVar == null || this.a) {
            return;
        }
        com.jingdong.app.mall.home.tips.b bVar = new com.jingdong.app.mall.home.tips.b(this.f10901e, cVar);
        this.f10906j = bVar;
        View b2 = bVar.b();
        if (b2 == null || this.f10905i == null) {
            return;
        }
        b2.setOnClickListener(new k());
        v(b2);
        x(b2);
    }

    public String j(String str, int i2) {
        boolean z;
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(str);
        try {
            c2.getJSONObject("ext").put("clickpos", i2);
            z = true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            z = false;
        }
        if (!z) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("clickpos", (Object) Integer.valueOf(i2));
            c2.put("ext", jDJSONObject);
        }
        return c2.toString();
    }

    private synchronized void u() {
        if (!this.f10900c.get() && !com.jingdong.app.mall.home.i.i()) {
            JDHomeFragment z0 = JDHomeFragment.z0();
            if (z0 != null && z0.isAdded() && this.f10905i != null && k()) {
                if (this.f10905i.e() == 0) {
                    if (f10899n > 0 || this.f10905i.a() < 0) {
                        return;
                    }
                } else if (this.f10905i.e() == 1 && (this.f10905i.a() < 0 || !this.f10905i.k())) {
                    return;
                }
                if (this.f10902f == null) {
                    this.f10902f = new d();
                }
                this.b.removeCallbacks(this.f10902f);
                this.b.postDelayed(this.f10902f, this.f10905i.a() == 0 ? 800L : this.f10905i.a() * 1000);
            }
        }
    }

    private void v(View view) {
        View findViewById;
        if (view == null || this.f10905i == null || (findViewById = view.findViewById(R.id.home_tips_close)) == null) {
            return;
        }
        findViewById.setOnClickListener(new l());
    }

    private void x(View view) {
        if (this.f10900c.get() || com.jingdong.app.mall.home.floor.ctrl.b.f9375f.get()) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.b.f9376g.set(true);
        C0328a c0328a = new C0328a("Tips", 18, view);
        this.f10904h = c0328a;
        c0328a.j();
        this.f10904h.l();
    }

    public void y(View view) {
        this.f10903g = true;
        com.jingdong.app.mall.home.o.a.f.E0(new b(view));
        new com.jingdong.app.mall.home.q.a("Tips\u66dd\u5149", true, this.f10905i.c()).b();
        String jsonString = this.f10905i.getJsonString("expoFunctionId");
        if (!TextUtils.isEmpty(jsonString)) {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(Configuration.getPortalHost());
            httpSetting.setFunctionId(jsonString);
            httpSetting.setPost(false);
            httpSetting.setType(6000);
            httpSetting.setCacheMode(2);
            httpSetting.setEffect(0);
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        }
        if (this.f10905i.i() != 15) {
            this.b.postDelayed(new c(), this.f10905i.f());
        }
        com.jingdong.app.mall.home.r.c.a.y("Home_TipsExpo", "", this.f10905i.b());
        com.jingdong.app.mall.home.o.a.f.x0("home tips show date", com.jingdong.app.mall.home.tips.c.f10927n.format(new Date(System.currentTimeMillis())));
        com.jingdong.app.mall.home.o.a.f.w0("show times", com.jingdong.app.mall.home.o.a.f.t("show times", 0) + 1);
        f10899n++;
        this.a = true;
    }

    public void z() {
        if (n.b()) {
            return;
        }
        PermissionTips permissionTips = new PermissionTips(this.f10901e);
        this.f10908l = permissionTips;
        if (permissionTips.a(this.f10905i)) {
            this.f10908l.c(new f());
            this.f10908l.d(new g());
            x(this.f10908l);
        }
    }

    protected boolean k() {
        throw null;
    }

    public void l() {
        m(false);
    }

    public void m(boolean z) {
        if (this.f10900c.get()) {
            return;
        }
        this.f10900c.set(z);
        if (Log.D) {
            Log.d("HomeTips", "===>>> closeTask");
        }
        Runnable runnable = this.f10902f;
        if (runnable != null) {
            this.b.removeCallbacks(runnable);
        }
        BaseFloatPriority baseFloatPriority = this.f10904h;
        if (baseFloatPriority != null) {
            baseFloatPriority.b(true);
        }
        RelativeLayout relativeLayout = this.d;
        if (relativeLayout == null || relativeLayout.getVisibility() == 0) {
            com.jingdong.app.mall.home.o.a.f.E0(new e());
        }
    }

    public void n() {
        if (this.f10900c.get()) {
            return;
        }
        this.d.setVisibility(8);
    }

    public void o(com.jingdong.app.mall.home.tips.c cVar, boolean z) {
        if (cVar == null) {
            return;
        }
        if (z && this.f10903g) {
            return;
        }
        if (!z) {
            this.f10903g = false;
        }
        this.f10905i = cVar;
        this.a = false;
        this.f10909m = false;
    }

    public boolean p() {
        com.jingdong.app.mall.home.tips.c cVar = this.f10905i;
        return cVar == null || cVar.i() != 15;
    }

    public void q() {
        com.jingdong.app.mall.home.tips.c cVar = this.f10905i;
        if (cVar == null || cVar.e() == 2) {
            return;
        }
        if (this.f10905i.g() == 1) {
            l();
        } else if (this.f10905i.g() == 2) {
            m(true);
        }
    }

    public void r() {
        com.jingdong.app.mall.home.tips.c cVar = this.f10905i;
        if (cVar != null && cVar.g() == 1) {
            t();
        }
    }

    public void s(HomeRecycleView homeRecycleView, int i2) {
        com.jingdong.app.mall.home.tips.c cVar = this.f10905i;
        if (cVar == null || this.a) {
            return;
        }
        boolean z = false;
        if (cVar.e() == 2 && com.jingdong.app.mall.home.floor.ctrl.a.g(homeRecycleView, com.jingdong.app.mall.home.a.f8560i) && (this.f10905i.i() == 13 || this.f10905i.i() == 14)) {
            z = true;
        }
        if (this.f10909m ^ z) {
            this.f10909m = z;
            if (z) {
                A();
            }
        }
    }

    public void t() {
        try {
            u();
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
    }

    public void w() {
        if (this.f10900c.get()) {
            return;
        }
        this.d.setVisibility(0);
    }
}
