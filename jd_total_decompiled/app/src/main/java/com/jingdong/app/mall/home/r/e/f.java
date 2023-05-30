package com.jingdong.app.mall.home.r.e;

import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class f extends b {
    private static final String m0 = "f";
    private String A;
    private String B;
    private int C;
    private String D;
    private String E;
    private com.jingdong.app.mall.home.r.c.b F;
    private JDJSONArray G;
    private String H;
    private int I;
    private String J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private String P;
    private String Q;
    private int R;
    private String S;
    private String T;
    private String U;
    private int V;
    private int W;
    private int X;
    private String Y;
    private String Z;
    public h a;
    private String a0;
    private String b;
    private String b0;

    /* renamed from: c */
    private String f10671c;
    private String c0;
    private String d;
    private String d0;

    /* renamed from: e */
    private String f10672e;
    private String e0;

    /* renamed from: f */
    private JDJSONObject f10673f;
    private String f0;

    /* renamed from: g */
    private String f10674g;
    private Long g0;

    /* renamed from: h */
    private String f10675h;
    private String h0;

    /* renamed from: i */
    private String f10676i;
    private String i0;

    /* renamed from: j */
    private int f10677j;
    private String j0;

    /* renamed from: k */
    private String f10678k;
    private String k0;

    /* renamed from: l */
    private String f10679l;
    private int l0;

    /* renamed from: m */
    private String f10680m;

    /* renamed from: n */
    private String f10681n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private JumpEntity u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public f(JDJSONObject jDJSONObject) {
        this(jDJSONObject, 0);
    }

    private void o0() {
        try {
            JDJSONObject jsonObject = getJsonObject("jump");
            if (jsonObject != null) {
                this.u = (JumpEntity) jsonObject.toJavaObject(JumpEntity.class);
            }
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
        JumpEntity jumpEntity = this.u;
        if (jumpEntity != null) {
            this.p = jumpEntity.getSrv();
        }
    }

    public static ArrayList<ArrayList<f>> q0(JDJSONArray jDJSONArray, int i2, ArrayList<f> arrayList) {
        ArrayList<f> arrayList2 = null;
        if (jDJSONArray == null) {
            return null;
        }
        ArrayList<ArrayList<f>> arrayList3 = new ArrayList<>();
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                if (i3 % 4 == 0) {
                    arrayList2 = new ArrayList<>();
                }
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i3);
                if (jSONObject != null && jSONObject.toString().length() > 10) {
                    arrayList2.add(new f(jSONObject, i2));
                } else if (arrayList != null && arrayList.size() >= 4) {
                    arrayList2.add(arrayList.get(i3 % 4));
                }
                if (i3 == jDJSONArray.size() - 1) {
                    int i4 = i3 + 1;
                    if (i4 % 4 != 0) {
                        int i5 = 4 - (i4 % 4);
                        for (int i6 = 0; i6 < i5; i6++) {
                            if (arrayList != null && arrayList.size() > i5) {
                                arrayList2.add(arrayList.get((4 - i5) + i6));
                            }
                        }
                    }
                }
                if (arrayList2.size() == 4) {
                    arrayList3.add(arrayList2);
                }
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0(m0, e2);
            }
        }
        return arrayList3;
    }

    public static ArrayList<f> r0(g gVar, JDJSONArray jDJSONArray) {
        return s0(gVar, jDJSONArray, 0);
    }

    public static ArrayList<f> s0(g gVar, JDJSONArray jDJSONArray, int i2) {
        ArrayList<f> arrayList;
        Exception e2;
        if (jDJSONArray == null) {
            return null;
        }
        try {
            arrayList = new ArrayList<>();
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    if (jDJSONArray.getJSONObject(i3) != null) {
                        arrayList.add(new f(jDJSONArray.getJSONObject(i3), i2));
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    com.jingdong.app.mall.home.o.a.f.s0(m0, e2);
                    return arrayList;
                }
            }
        } catch (Exception e4) {
            arrayList = null;
            e2 = e4;
        }
        return arrayList;
    }

    public JumpEntity A(String str) {
        try {
            JDJSONObject jsonObject = getJsonObject(str);
            if (jsonObject != null) {
                return (JumpEntity) jsonObject.toJavaObject(JumpEntity.class);
            }
            return null;
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
            return null;
        }
    }

    public int B() {
        return this.L;
    }

    public String C() {
        return D(true);
    }

    public String D(boolean z) {
        return this.f10676i;
    }

    public String E() {
        return this.c0;
    }

    public String F() {
        return this.T;
    }

    public String G() {
        return this.f10681n;
    }

    public String H() {
        return this.o;
    }

    public String I() {
        return this.Z;
    }

    public String J() {
        return this.a0;
    }

    public String K() {
        return this.J;
    }

    public String L() {
        return this.b0;
    }

    public int M() {
        return this.N;
    }

    public String N() {
        return this.f10671c;
    }

    public String O() {
        return this.f10674g;
    }

    public String P() {
        return this.f10675h;
    }

    public String Q() {
        return R(false);
    }

    public String R(boolean z) {
        return (z && com.jingdong.app.mall.home.state.dark.a.h()) ? this.w : this.v;
    }

    public int S() {
        return this.O;
    }

    public int T() {
        return this.M;
    }

    public String U(int i2) {
        JDJSONArray jDJSONArray = this.G;
        return (jDJSONArray == null || i2 < 0 || jDJSONArray.size() <= i2) ? "" : this.G.getString(i2);
    }

    public JDJSONArray V() {
        return this.G;
    }

    public String W() {
        return this.q;
    }

    public String X() {
        return this.s;
    }

    public String Y() {
        return this.Y;
    }

    public String Z() {
        return this.r;
    }

    public String a() {
        return this.A;
    }

    public int a0() {
        return this.C;
    }

    public int b() {
        return this.K;
    }

    public String b0() {
        return this.p;
    }

    public int c() {
        return this.V;
    }

    public String c0() {
        return this.S;
    }

    public String d() {
        return this.f10672e;
    }

    public String d0() {
        return this.f10678k;
    }

    public String e() {
        return this.t;
    }

    public String e0() {
        return this.f10679l;
    }

    public String f() {
        return this.e0;
    }

    public String f0() {
        return this.f10680m;
    }

    public String g() {
        return this.f0;
    }

    public Long g0() {
        return this.g0;
    }

    public JumpEntity getJump() {
        return this.u;
    }

    public JDJSONObject h() {
        return this.f10673f;
    }

    public String h0() {
        return this.h0;
    }

    public String i() {
        return this.j0;
    }

    public String i0() {
        return this.U;
    }

    public String j() {
        return this.D;
    }

    public int j0() {
        return this.l0;
    }

    public com.jingdong.app.mall.home.r.c.b k() {
        if (this.F == null) {
            this.F = com.jingdong.app.mall.home.r.c.b.c(this.E);
        }
        return this.F;
    }

    public String k0() {
        return this.i0;
    }

    public String l() {
        return this.E;
    }

    public String l0() {
        return this.P;
    }

    public String m() {
        return this.d0;
    }

    public int m0() {
        return this.R;
    }

    public String n() {
        return this.H;
    }

    public String n0() {
        return this.Q;
    }

    public String o() {
        return this.B;
    }

    public int p() {
        return this.X;
    }

    public void p0(String str) {
        this.d = str;
    }

    public int q() {
        return this.W;
    }

    public int r() {
        return this.I;
    }

    public String s() {
        return this.b;
    }

    public int t() {
        return this.f10677j;
    }

    public String u() {
        return this.d;
    }

    public String v() {
        return this.x;
    }

    public String w() {
        return this.y;
    }

    public String x() {
        return this.z;
    }

    public String[] y(int i2) {
        return 1 == i2 ? new String[]{this.d} : 2 == i2 ? new String[]{this.d, this.x} : 3 == i2 ? new String[]{this.d, this.x, this.y} : 4 == i2 ? new String[]{this.d, this.x, this.y, this.z} : new String[0];
    }

    public String z() {
        return this.k0;
    }

    public f(JDJSONObject jDJSONObject, int i2) {
        super(jDJSONObject);
        this.b = getJsonString("id");
        this.f10671c = getJsonString("rightCorner");
        this.d = getJsonString("img");
        this.f10672e = getJsonString("bgImg");
        this.f10673f = getJsonObject("content");
        this.f10674g = getJsonString(DYConstants.SHOW_NAME);
        this.f10675h = getJsonString("showName2");
        this.q = getJsonString("slogan");
        this.r = getJsonString("sloganIcon");
        this.s = getJsonString("sloganAreaColor");
        this.f10676i = getJsonString("maintitleColor");
        this.f10677j = getJsonInt("imageType", 0);
        this.f10678k = getJsonString("subtitle");
        this.f10679l = getJsonString("subtitle2");
        this.f10680m = getJsonString("subtitleColor");
        this.Y = getJsonString("sloganColor");
        this.f10681n = getJsonString("moduleBgImg");
        this.o = getJsonString("param");
        this.p = getJsonString("sourceValue");
        this.t = getJsonString("clickUrl");
        this.v = getJsonString("showNameImg");
        this.w = getJsonString("darkModeImg");
        this.x = getJsonString("img2");
        this.y = getJsonString("img3");
        this.z = getJsonString("img4");
        this.A = getJsonString(JDMtaUtils.ABTKEY);
        this.B = getJsonString("extension_id");
        this.C = getJsonInt("source", -1);
        this.D = getJsonString("expo");
        this.E = getJsonString("expoJson");
        this.G = getJsonArr("skuList");
        this.H = getJsonString("exposalUrl");
        this.I = getJsonInt("hasMaskImg", 0);
        this.J = getJsonString(CartPromotion.KEY_PRICECOLOR);
        this.K = getJsonInt("align", 0);
        this.L = getJsonInt("loopPlay");
        this.M = getJsonInt("showTimesDaily");
        this.N = getJsonInt("reqShowTimes");
        this.O = getJsonInt("showTimes");
        this.P = getJsonString("videoId");
        this.Q = getJsonString("videoUrl");
        this.R = getJsonInt("videoPosition", 1);
        this.S = getJsonString("subTitleIcon");
        this.T = getJsonString("maskImg");
        this.U = getJsonString("titleIcon");
        this.V = getJsonInt("mAnimation", 0);
        this.W = getJsonInt(ViewProps.FONT_SIZE, 1);
        this.X = getJsonInt("fontBold", 1);
        this.Z = getJsonString("price1");
        this.a0 = getJsonString("price2");
        this.b0 = getJsonString("promotionTagColor");
        this.c0 = getJsonString("maskColor");
        this.d0 = getJsonString("expoLog");
        this.e0 = getJsonString("clkLog");
        this.f0 = getJsonString("closeLog");
        this.g0 = Long.valueOf(getJsonLong("timeRemain"));
        this.h0 = getJsonString("timeRemainColor");
        this.i0 = getJsonString("underwayText");
        this.j0 = getJsonString("endText");
        this.k0 = getJsonString("jdPriceBgImg");
        this.l0 = getJsonInt("toplogoType", -1);
        o0();
    }
}
