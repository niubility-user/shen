package com.jingdong.app.mall.home.floor.view.b.g;

import android.content.Context;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import androidx.collection.ArrayMap;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.s;
import com.jingdong.app.mall.home.floor.view.b.d;
import com.jingdong.app.mall.home.floor.view.linefloor.widget.LadySecKillTitle;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public class b extends com.jingdong.app.mall.home.floor.view.linefloor.base.a {
    private static int[] T = {-1023452874, -381927};
    private static int[] U = {-381669, -49343, -49304};
    private CopyOnWriteArrayList<Product> A;
    private InterfaceC0300b B;
    private c C;
    private JSONArray D;
    private int E;
    private int F;
    private int G;
    private boolean H;
    private String I;
    private String J;
    private String K;
    private String L;
    private JumpEntity M;
    private String N;
    private String O;
    private int P;
    private String Q;
    private int R;
    private long S;
    private com.jingdong.app.mall.home.floor.view.b.d x;
    private a y;
    private ArrayMap<String, JDJSONObject> z;

    /* loaded from: classes4.dex */
    public static class a {

        /* renamed from: e */
        private static com.jingdong.app.mall.home.x.f f9770e = new com.jingdong.app.mall.home.x.f();
        private long a;
        private long b;

        /* renamed from: c */
        private long f9771c;
        private long d;

        private long d() {
            long j2 = this.b;
            if (j2 > 0) {
                return j2;
            }
            return 0L;
        }

        public long a() {
            return this.d;
        }

        public long b() {
            return (d() + SystemClock.elapsedRealtime()) - this.a;
        }

        public long c() {
            return this.f9771c;
        }

        public void e(boolean z, long j2, long j3, long j4) {
            this.a = SystemClock.elapsedRealtime();
            this.d = j3;
            f(z, j4);
            this.f9771c = (j4 <= 0 || !z) ? j2 : f9770e.a(j2, j3, j4);
            if (!z || j4 > 0) {
                return;
            }
            f9770e.c(j2, j3);
        }

        public void f(boolean z, long j2) {
            if (z && (j2 <= 0 || f9770e.b(this.d))) {
                this.b = this.a - s.f9357c;
            } else {
                this.b = 0L;
            }
        }
    }

    /* renamed from: com.jingdong.app.mall.home.floor.view.b.g.b$b */
    /* loaded from: classes4.dex */
    public interface InterfaceC0300b {
        void b(b bVar);
    }

    public b(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(cVar, fVar, aVar);
        this.y = new a();
        this.z = new ArrayMap<>();
        this.A = new CopyOnWriteArrayList<>();
        this.D = com.jingdong.app.mall.home.r.c.b.d();
        this.E = 0;
        this.G = 0;
        this.K = "";
        this.L = "";
        this.N = "";
        this.C = new c(this);
        this.x = new com.jingdong.app.mall.home.floor.view.b.d();
    }

    public void A0(String str) {
        this.O = str;
    }

    public void B0(int i2) {
        if (i2 < 0) {
            this.E = 0;
        } else {
            this.E = i2;
        }
    }

    public void C0(String str) {
        String g2 = m.g(str);
        if (g2.length() < 2) {
            this.J = "00";
            return;
        }
        String substring = g2.substring(0, g2.length() - 2);
        if (substring.length() == 1) {
            substring = "0" + substring;
        }
        this.J = substring;
    }

    public void D0(String str) {
        this.I = str;
    }

    public void E0(JDJSONObject jDJSONObject) {
        this.z.put(this.I, jDJSONObject);
    }

    public void F0(JumpEntity jumpEntity) {
        this.M = jumpEntity;
    }

    public void G0(String str) {
        this.L = str;
    }

    public void H0(String str) {
        this.K = m.g(str);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void I(int i2, int i3) {
        super.I(i2, i3);
        com.jingdong.app.mall.home.r.e.f g2 = g(0);
        if (g2 != null && 9 == g2.a0()) {
            this.C.e(g2.h());
        }
        this.N = getJsonString(CartPromotion.KEY_PRICECOLOR, "#FFF02B2B");
        this.P = getJsonInt("priceStyle", 0);
        this.Q = getJsonString("skuTagImg");
        this.R = m.j(getJsonString("interestPointColor"), -381927);
        z0(getJsonString("maskColor"));
        A0(getJsonString("maskImg"));
    }

    public void I0(int i2) {
        this.G = i2;
    }

    public void J0(ArrayList<Product> arrayList) {
        this.A.clear();
        if (arrayList != null) {
            this.A.addAll(arrayList);
        }
    }

    public void K0(boolean z) {
        this.H = z;
    }

    public void L0(boolean z, Long l2, long j2) {
        this.y.e(z, l2.longValue(), j2, this.f9854h.U);
    }

    public void M0(int i2) {
    }

    public void N() {
        this.z.clear();
    }

    public void N0(LadySecKillTitle ladySecKillTitle) {
        this.C.i(ladySecKillTitle);
    }

    public long O() {
        return this.S;
    }

    public com.jingdong.app.mall.home.floor.common.f P() {
        return this.x.d;
    }

    public int Q() {
        return this.F;
    }

    public String R(int i2) {
        return "Breath_Key";
    }

    public a S() {
        return this.y;
    }

    public int T() {
        return this.R;
    }

    public Product U(int i2) {
        if (i2 < this.A.size()) {
            return this.A.get(i2);
        }
        return null;
    }

    public String V() {
        return this.O;
    }

    public int W() {
        return this.E;
    }

    public String X() {
        return this.J;
    }

    public JDJSONObject Y() {
        return this.z.get(this.I);
    }

    public JumpEntity Z() {
        return this.M;
    }

    public String a0() {
        return this.L;
    }

    public String b0() {
        return this.K;
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void c(int i2, int i3) {
        super.c(i2, i3);
        d.b(this.x, this, i3);
    }

    public int c0() {
        return this.G;
    }

    public String d0() {
        return this.N;
    }

    public SpannableString e0(String str) {
        SpannableString spannableString = new SpannableString(com.jingdong.app.mall.home.category.floor.feedssub.a.e(str));
        spannableString.setSpan(new AbsoluteSizeSpan(com.jingdong.app.mall.home.floor.common.d.d(20)), 0, 1, 18);
        return spannableString;
    }

    public int f0() {
        return this.P;
    }

    public JSONArray g0() {
        return this.D;
    }

    public d.a[] h0() {
        return this.x.a;
    }

    public int[] i0() {
        return com.jingdong.app.mall.home.r.e.b.getColor(this.f9856j.getJsonString("skuLabelAreaColor", ""), T);
    }

    public String j0() {
        return this.Q;
    }

    public int[] k0() {
        return com.jingdong.app.mall.home.r.e.b.getColor(this.f9856j.C(), com.jingdong.app.mall.home.floor.view.linefloor.base.a.t);
    }

    public String l0() {
        return g(0).Q();
    }

    public int m0() {
        return com.jingdong.app.mall.home.n.h.c.h(g(0).getJsonString("showNameImgWidth"), 0);
    }

    public String n0() {
        return this.f9856j.getJsonString("sloganTagImg");
    }

    public int[] o0() {
        return com.jingdong.app.mall.home.r.e.b.getColor(this.f9856j.getJsonString("sloganAreaColor"), U);
    }

    public com.jingdong.app.mall.home.floor.common.f p0() {
        return this.x.b;
    }

    public String q0() {
        return getJsonString(DYConstants.SHOW_NAME);
    }

    public boolean r0() {
        return this.H;
    }

    public void s0() {
        InterfaceC0300b interfaceC0300b = this.B;
        if (interfaceC0300b != null) {
            interfaceC0300b.b(this);
        }
    }

    public void t0(int i2, Context context, boolean z) {
        Product U2;
        String str;
        String str2 = "";
        if (i2 >= this.A.size() || (U2 = U(i2)) == null || U2.jump == null) {
            return;
        }
        try {
            str = this.D.get(i2).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            str = "";
        }
        if (!z) {
            l.a(U2.jump);
        } else {
            l.t(U2.jump);
        }
        l.c(U2.jump, U2.getImageUrl());
        l.e(context, U2.jump);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(l.a)) {
            str2 = l.a + CartConstant.KEY_YB_INFO_LINK;
        }
        sb.append(str2);
        sb.append(i2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(U2.getId());
        com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str, RecommendMtaUtils.Home_PageId, null, sb.toString());
    }

    public void u0() {
        this.D = com.jingdong.app.mall.home.r.c.b.d();
        for (int i2 = 0; i2 < 4; i2++) {
            Product U2 = U(i2);
            if (U2 != null) {
                com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
                JDJSONObject jDJSONObject = U2.prdObject;
                if (jDJSONObject != null) {
                    String optString = jDJSONObject.optString("seckillSrv");
                    if (!TextUtils.isEmpty(optString)) {
                        bVar.a("seckillSrv", com.jingdong.app.mall.home.r.c.b.c(optString));
                    }
                }
                bVar.e(com.jingdong.app.mall.home.r.c.b.c(this.f9856j.l()));
                JumpEntity jumpEntity = U2.jump;
                if (jumpEntity != null) {
                    bVar.e(com.jingdong.app.mall.home.r.c.b.c(jumpEntity.srv));
                }
                bVar.a(DeeplinkProductDetailHelper.LAYER_STYLE, h().a);
                bVar.a(PdMtaUtil.PARAM_KEY_SKUID, Long.valueOf(U2.getId() == null ? 0L : U2.getId().longValue()));
                this.D.put(bVar);
            }
        }
    }

    public void v0(int i2) {
        this.C.h(this.L, i2);
    }

    public void w0(long j2) {
        this.S = j2;
    }

    public void x0(InterfaceC0300b interfaceC0300b) {
        this.B = interfaceC0300b;
    }

    public void y0(int i2) {
        this.F = i2;
    }

    public void z0(String str) {
    }
}
