package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.text.TextUtils;
import android.view.View;
import androidx.collection.ArrayMap;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.floor.view.b.h.a;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public abstract class SeckillModel<V extends CoreBaseView> extends CoreModel<V> {
    protected static final int[] Q = {-381927, -381927, -381927};
    private String A;
    private String B;
    private long E;
    private boolean F;
    private boolean G;
    private String H;
    private JumpEntity I;
    protected boolean J;
    protected String K;
    protected int L;
    protected h M;
    private IconImageText.Info N;
    protected IconLabel.Info O;
    private String v;
    private int w;
    protected String x;
    private String z;
    private final ArrayMap<String, JDJSONObject> r = new ArrayMap<>();
    private final b.a s = new b.a();
    private final int[] t = new int[2];
    private JSONArray u = com.jingdong.app.mall.home.r.c.b.d();
    private int y = 0;
    protected final ArrayList<Product> C = new ArrayList<>();
    private boolean D = false;
    protected boolean P = true;

    private void U0(JDJSONObject jDJSONObject) {
        if (Q0(jDJSONObject, false)) {
            return;
        }
        T0(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void Y0(int r10, int r11, android.view.View r12, boolean r13) {
        /*
            r9 = this;
            java.lang.String r0 = ""
            java.util.ArrayList<com.jingdong.common.entity.Product> r1 = r9.C
            int r1 = r1.size()
            if (r10 < r1) goto Lb
            return
        Lb:
            com.jingdong.common.entity.Product r1 = r9.J0(r10)
            if (r1 == 0) goto L8b
            com.jingdong.common.entity.JumpEntity r2 = r1.jump
            if (r2 != 0) goto L17
            goto L8b
        L17:
            org.json.JSONArray r2 = r9.u     // Catch: org.json.JSONException -> L28
            java.lang.Object r10 = r2.get(r10)     // Catch: org.json.JSONException -> L28
            java.lang.String r10 = r10.toString()     // Catch: org.json.JSONException -> L28
            java.lang.String r10 = com.jingdong.app.mall.home.floor.common.h.a.b(r12, r10)     // Catch: org.json.JSONException -> L26
            goto L2d
        L26:
            r2 = move-exception
            goto L2a
        L28:
            r2 = move-exception
            r10 = r0
        L2a:
            r2.printStackTrace()
        L2d:
            r5 = r10
            if (r13 != 0) goto L36
            com.jingdong.common.entity.JumpEntity r10 = r1.jump
            com.jingdong.app.mall.home.floor.common.i.l.a(r10)
            goto L3b
        L36:
            com.jingdong.common.entity.JumpEntity r10 = r1.jump
            com.jingdong.app.mall.home.floor.common.i.l.t(r10)
        L3b:
            com.jingdong.common.entity.JumpEntity r10 = r1.jump
            java.lang.String r13 = r1.getImageUrl()
            com.jingdong.app.mall.home.floor.common.i.l.c(r10, r13)
            android.content.Context r10 = r12.getContext()
            com.jingdong.common.entity.JumpEntity r12 = r1.jump
            com.jingdong.app.mall.home.floor.common.i.l.e(r10, r12)
            r7 = 0
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = com.jingdong.app.mall.home.floor.common.i.l.a
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            java.lang.String r13 = "_"
            if (r12 != 0) goto L6e
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = com.jingdong.app.mall.home.floor.common.i.l.a
            r12.append(r0)
            r12.append(r13)
            java.lang.String r0 = r12.toString()
        L6e:
            r10.append(r0)
            r10.append(r11)
            r10.append(r13)
            java.lang.Long r11 = r1.getId()
            r10.append(r11)
            java.lang.String r8 = r10.toString()
            java.lang.String r3 = "Home_HandSeckill"
            java.lang.String r4 = ""
            java.lang.String r6 = "Home_Main"
            com.jingdong.app.mall.home.r.c.a.u(r3, r4, r5, r6, r7, r8)
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel.Y0(int, int, android.view.View, boolean):void");
    }

    @Override // com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel, com.jingdong.app.mall.home.deploy.view.base.BaseModel
    public void C() {
        super.C();
        if (this.f8920m == null) {
            return;
        }
        this.M = this.f8877g.d();
        IconImageText.Info a = IconImageText.Info.a();
        a.d(i0(), 32, h0());
        a.e(0, 0, 8, 0);
        a.h(m.n(this.f8920m.C(), CoreModel.p, true), com.jingdong.app.mall.home.o.a.m.b(this.f8920m));
        a.g(this.f8920m.getJsonString(DYConstants.SHOW_NAME));
        a.c(true);
        a.f(k0(), 32, j0());
        this.N = a;
        int[] n2 = m.n(this.f8920m.getJsonString("sloganAreaColor"), Q, true);
        IconLabel.Info a2 = IconLabel.Info.a();
        a2.d(20, 20, this.f8920m.getJsonString("sloganIcon"));
        a2.b(n2, this.f8920m.getJsonString("sloganTagImg"));
        a2.g(CoreModel.o, 22);
        this.O = a2;
        this.H = this.f8920m.getJsonString("skuTagImg");
        this.f8920m.getJsonInt("priceStyle");
        this.G = com.jingdong.app.mall.home.o.a.m.c(this.f8920m);
        a.h(a.e(this.f8920m.getJsonString(CartPromotion.KEY_PRICECOLOR), -381927), this.t);
        f fVar = this.f8920m;
        fVar.a = this.M;
        U0(fVar.h());
    }

    public int C0() {
        return this.y;
    }

    public IconLabel.Info D0() {
        return this.O;
    }

    public JDJSONObject E0() {
        return this.r.get(this.z);
    }

    public long F0() {
        return this.s.a();
    }

    public JumpEntity G0() {
        return this.I;
    }

    public int H0() {
        return this.w;
    }

    public int[] I0() {
        return this.t;
    }

    public Product J0(int i2) {
        try {
            if (i2 < this.C.size()) {
                return this.C.get(i2);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String K0() {
        return this.H;
    }

    public long L0() {
        return Math.max(this.E * 1000, 3000L);
    }

    public long M0() {
        return this.s.b();
    }

    public long N0() {
        return this.s.c();
    }

    public String O0() {
        return TextUtils.isEmpty(this.B) ? this.A : this.B;
    }

    public IconImageText.Info P0() {
        return this.N;
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0161 A[Catch: Exception -> 0x01ad, TryCatch #0 {Exception -> 0x01ad, blocks: (B:106:0x0014, B:110:0x003c, B:112:0x0077, B:117:0x009c, B:119:0x00b0, B:123:0x00d6, B:127:0x00fc, B:128:0x00fe, B:130:0x0106, B:132:0x010a, B:137:0x0116, B:141:0x0123, B:144:0x0137, B:145:0x0139, B:151:0x0145, B:152:0x015b, B:154:0x0161, B:159:0x0171, B:160:0x0175, B:162:0x018b, B:164:0x0191, B:165:0x0196, B:113:0x007c, B:115:0x008b, B:116:0x009a), top: B:180:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x018b A[Catch: Exception -> 0x01ad, TryCatch #0 {Exception -> 0x01ad, blocks: (B:106:0x0014, B:110:0x003c, B:112:0x0077, B:117:0x009c, B:119:0x00b0, B:123:0x00d6, B:127:0x00fc, B:128:0x00fe, B:130:0x0106, B:132:0x010a, B:137:0x0116, B:141:0x0123, B:144:0x0137, B:145:0x0139, B:151:0x0145, B:152:0x015b, B:154:0x0161, B:159:0x0171, B:160:0x0175, B:162:0x018b, B:164:0x0191, B:165:0x0196, B:113:0x007c, B:115:0x008b, B:116:0x009a), top: B:180:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:193:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean Q0(com.jd.framework.json.JDJSONObject r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel.Q0(com.jd.framework.json.JDJSONObject, boolean):boolean");
    }

    protected boolean R0() {
        return false;
    }

    public void S0(JDJSONObject jDJSONObject) {
    }

    public void T0(final boolean z) {
        if (this.D || this.f8876f != null) {
            return;
        }
        this.D = true;
        com.jingdong.app.mall.home.n.h.h.a(this.v, new HttpGroup.CustomOnAllListener() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel.2
            {
                SeckillModel.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                SeckillModel.this.D = false;
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    return;
                }
                if (z) {
                    SeckillModel.this.r.put(SeckillModel.this.z, fastJsonObject);
                } else {
                    SeckillModel.this.Q0(fastJsonObject, true);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                SeckillModel.this.D = false;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        }, z);
    }

    public boolean V0() {
        return this.M.Z;
    }

    public boolean W0() {
        return this.F;
    }

    public boolean X0() {
        return this.G && !t();
    }

    public void Z0(int i2, View view) {
        Y0(i2, i2 + 1, view, true);
    }

    public void a1(View view) {
        Y0(0, 0, view, false);
    }

    public void b1(View view) {
        JumpEntity jumpEntity = this.I;
        if (jumpEntity != null) {
            com.jingdong.app.mall.home.floor.common.h.a.c(view, jumpEntity);
            com.jingdong.app.mall.home.r.c.a.s("Home_SeckillOperaWord", "", this.I.getSrvJson());
        }
        JumpEntity jumpEntity2 = this.I;
        if (jumpEntity2 != null) {
            l.a(jumpEntity2);
            l.e(view.getContext(), this.I);
            return;
        }
        a1(view);
    }

    public void c1() {
        JDJSONObject E0 = E0();
        if (E0 != null) {
            if (!Q0(E0, true)) {
                T0(false);
            }
            this.r.clear();
            return;
        }
        T0(false);
    }

    public void d1() {
        this.u = com.jingdong.app.mall.home.r.c.b.d();
        for (int i2 = 0; i2 < 4; i2++) {
            Product J0 = J0(i2);
            if (J0 != null) {
                com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
                JDJSONObject jDJSONObject = J0.prdObject;
                if (jDJSONObject != null) {
                    String optString = jDJSONObject.optString("seckillSrv");
                    if (!TextUtils.isEmpty(optString)) {
                        bVar.a("seckillSrv", com.jingdong.app.mall.home.r.c.b.c(optString));
                    }
                }
                bVar.e(com.jingdong.app.mall.home.r.c.b.c(this.f8920m.l()));
                JumpEntity jumpEntity = J0.jump;
                if (jumpEntity != null) {
                    bVar.e(com.jingdong.app.mall.home.r.c.b.c(jumpEntity.srv));
                }
                bVar.a(PdMtaUtil.PARAM_KEY_SKUID, Long.valueOf(J0.getId() == null ? 0L : J0.getId().longValue()));
                bVar.a("guideanimation", Integer.valueOf(R0() ? 1 : 0));
                this.u.put(bVar);
            }
        }
    }

    public void e1() {
        com.jingdong.app.mall.home.r.c.a.y("Home_SeckillExpo", "", this.u.toString());
    }
}
