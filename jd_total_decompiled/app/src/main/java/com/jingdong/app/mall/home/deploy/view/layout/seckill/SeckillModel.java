package com.jingdong.app.mall.home.deploy.view.layout.seckill;

import android.text.TextUtils;
import android.view.View;
import androidx.collection.ArrayMap;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.app.mall.home.deploy.view.base.BaseModel;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreBaseView;
import com.jingdong.app.mall.home.deploy.view.layout.core.CoreModel;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconImageText;
import com.jingdong.app.mall.home.deploy.view.layout.widget.IconLabel;
import com.jingdong.app.mall.home.floor.common.h.a;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.b.g.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

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

    /* JADX WARN: Removed duplicated region for block: B:86:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void Y0(int i2, int i3, View view, boolean z) {
        Product J0;
        String str;
        String str2 = "";
        if (i2 >= this.C.size() || (J0 = J0(i2)) == null || J0.jump == null) {
            return;
        }
        try {
            str = this.u.get(i2).toString();
        } catch (JSONException e2) {
            e = e2;
            str = "";
        }
        try {
            str = a.b(view, str);
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            String str3 = str;
            if (z) {
            }
            l.c(J0.jump, J0.getImageUrl());
            l.e(view.getContext(), J0.jump);
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(l.a)) {
            }
            sb.append(str2);
            sb.append(i3);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(J0.getId());
            com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str3, RecommendMtaUtils.Home_PageId, null, sb.toString());
        }
        String str32 = str;
        if (z) {
            l.a(J0.jump);
        } else {
            l.t(J0.jump);
        }
        l.c(J0.jump, J0.getImageUrl());
        l.e(view.getContext(), J0.jump);
        StringBuilder sb2 = new StringBuilder();
        if (!TextUtils.isEmpty(l.a)) {
            str2 = l.a + CartConstant.KEY_YB_INFO_LINK;
        }
        sb2.append(str2);
        sb2.append(i3);
        sb2.append(CartConstant.KEY_YB_INFO_LINK);
        sb2.append(J0.getId());
        com.jingdong.app.mall.home.r.c.a.u("Home_HandSeckill", "", str32, RecommendMtaUtils.Home_PageId, null, sb2.toString());
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
        com.jingdong.app.mall.home.floor.view.b.h.a.h(com.jingdong.app.mall.home.floor.view.b.h.a.e(this.f8920m.getJsonString(CartPromotion.KEY_PRICECOLOR), -381927), this.t);
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

    /* JADX WARN: Removed duplicated region for block: B:237:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0161 A[Catch: Exception -> 0x01ad, TryCatch #0 {Exception -> 0x01ad, blocks: (B:204:0x0014, B:208:0x003c, B:210:0x0077, B:215:0x009c, B:217:0x00b0, B:221:0x00d6, B:225:0x00fc, B:226:0x00fe, B:228:0x0106, B:230:0x010a, B:235:0x0116, B:239:0x0123, B:242:0x0137, B:243:0x0139, B:249:0x0145, B:250:0x015b, B:252:0x0161, B:257:0x0171, B:258:0x0175, B:260:0x018b, B:262:0x0191, B:263:0x0196, B:211:0x007c, B:213:0x008b, B:214:0x009a), top: B:278:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:260:0x018b A[Catch: Exception -> 0x01ad, TryCatch #0 {Exception -> 0x01ad, blocks: (B:204:0x0014, B:208:0x003c, B:210:0x0077, B:215:0x009c, B:217:0x00b0, B:221:0x00d6, B:225:0x00fc, B:226:0x00fe, B:228:0x0106, B:230:0x010a, B:235:0x0116, B:239:0x0123, B:242:0x0137, B:243:0x0139, B:249:0x0145, B:250:0x015b, B:252:0x0161, B:257:0x0171, B:258:0x0175, B:260:0x018b, B:262:0x0191, B:263:0x0196, B:211:0x007c, B:213:0x008b, B:214:0x009a), top: B:278:0x0014 }] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x019b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean Q0(JDJSONObject jDJSONObject, boolean z) {
        boolean z2;
        int i2;
        Iterator<Product> it;
        String jSONStringWithDefault;
        IconImageText.Info info;
        boolean z3 = false;
        if (jDJSONObject == null || jDJSONObject.size() == 0) {
            return false;
        }
        try {
            S0(jDJSONObject);
            this.v = jDJSONObject.getString("functionId");
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("indexMiaoSha");
            this.y = Math.max(com.jingdong.app.mall.home.o.a.f.t0(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "miaoshaAdvance", "0"), 0), 0);
            this.s.e(!z, jDJSONObject.getLongValue("timeRemain"), jDJSONObject.getLongValue("nextStartTime"), this.M.U);
            String g2 = m.g(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "name", ""));
            this.z = g2 + jDJSONObject.getString("nextRoundKey");
            if (g2.length() < 2) {
                this.A = "00";
            } else {
                String substring = g2.substring(0, g2.length() - 2);
                if (substring.length() == 1) {
                    substring = "0" + substring;
                }
                this.A = substring;
            }
            this.A = this.A.concat("\u70b9\u573a");
            this.x = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "operateWord", "");
            if (!z) {
                JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "dynamicCount", 0);
                JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "seckillAnimationTime", 0);
                this.E = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "sloganIntervalTime", 0);
                this.B = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "countDownWord", "");
                this.J = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "guideAnimationSwitch", 0) == 1;
                this.K = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "guideAnimationWord", "");
                this.L = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "guideAnimationCount", Integer.MAX_VALUE);
                JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "skuTagSwitch", 0);
                this.P = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "seckillRotateSwitch", 1) == 1;
            }
            if (1 != jDJSONObject.optInt("operateWordFix") && ((info = this.N) == null || info.b() <= 130)) {
                z2 = false;
                this.F = false;
                boolean z4 = TextUtils.isEmpty(this.x);
                this.I = (JumpEntity) JDJSON.parseObject(jDJSONObject.optString("wordJump", ""), JumpEntity.class);
                if (z2 && z4) {
                    this.F = true;
                }
                if (!this.M.Z && z4) {
                    i2 = 1;
                    this.w = i2;
                    IconLabel.Info info2 = this.O;
                    info2.f(this.x);
                    info2.c(false);
                    ArrayList<Product> list = Product.toList(jSONArray, 17);
                    it = list.iterator();
                    while (it.hasNext()) {
                        int i3 = it.next().msItemType;
                        if (i3 != 1 && i3 != 2 && i3 != 8) {
                            it.remove();
                        }
                    }
                    this.C.clear();
                    this.C.addAll(list);
                    jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", "");
                    if (!this.M.Z && !TextUtils.isEmpty(jSONStringWithDefault)) {
                        com.jingdong.app.mall.home.r.c.a.y("Home_SeckillFloorExpo", jSONStringWithDefault, "");
                    }
                    d1();
                    if (z) {
                        return true;
                    }
                    try {
                        if (this.f8882l instanceof BaseSeckill) {
                            com.jingdong.app.mall.home.o.a.f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.deploy.view.layout.seckill.SeckillModel.1
                                {
                                    SeckillModel.this = this;
                                }

                                @Override // com.jingdong.app.mall.home.o.a.b
                                protected void safeRun() {
                                    ((BaseSeckill) ((BaseModel) SeckillModel.this).f8882l).y();
                                }
                            });
                            return true;
                        }
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        z3 = true;
                        if (Log.E) {
                            e.printStackTrace();
                        }
                        return z3;
                    }
                }
                i2 = 0;
                this.w = i2;
                IconLabel.Info info22 = this.O;
                info22.f(this.x);
                info22.c(false);
                ArrayList<Product> list2 = Product.toList(jSONArray, 17);
                it = list2.iterator();
                while (it.hasNext()) {
                }
                this.C.clear();
                this.C.addAll(list2);
                jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", "");
                if (!this.M.Z) {
                    com.jingdong.app.mall.home.r.c.a.y("Home_SeckillFloorExpo", jSONStringWithDefault, "");
                }
                d1();
                if (z) {
                }
            }
            z2 = true;
            this.F = false;
            if (TextUtils.isEmpty(this.x)) {
            }
            this.I = (JumpEntity) JDJSON.parseObject(jDJSONObject.optString("wordJump", ""), JumpEntity.class);
            if (z2) {
                this.F = true;
            }
            if (!this.M.Z) {
                i2 = 1;
                this.w = i2;
                IconLabel.Info info222 = this.O;
                info222.f(this.x);
                info222.c(false);
                ArrayList<Product> list22 = Product.toList(jSONArray, 17);
                it = list22.iterator();
                while (it.hasNext()) {
                }
                this.C.clear();
                this.C.addAll(list22);
                jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", "");
                if (!this.M.Z) {
                }
                d1();
                if (z) {
                }
            }
            i2 = 0;
            this.w = i2;
            IconLabel.Info info2222 = this.O;
            info2222.f(this.x);
            info2222.c(false);
            ArrayList<Product> list222 = Product.toList(jSONArray, 17);
            it = list222.iterator();
            while (it.hasNext()) {
            }
            this.C.clear();
            this.C.addAll(list222);
            jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "algorithmFrom", "");
            if (!this.M.Z) {
            }
            d1();
            if (z) {
            }
        } catch (Exception e3) {
            e = e3;
        }
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
            a.c(view, jumpEntity);
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
