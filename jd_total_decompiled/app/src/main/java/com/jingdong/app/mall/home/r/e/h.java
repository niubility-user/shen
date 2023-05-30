package com.jingdong.app.mall.home.r.e;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes4.dex */
public class h extends b {
    public String A;
    public String B;
    public String C;
    public JumpEntity D;
    public int E;
    public String F;
    public int G;
    public int H;
    public String I;
    public String J;
    public String[] K;
    public String L;
    public boolean M;
    public boolean N;
    public String O;
    public String P;
    public String Q;
    public int R;
    public int S;
    public int T;
    public long U;
    public int V;
    public int W;
    public int X;
    public long Y;
    public boolean Z;
    private JDJSONObject a;
    public boolean a0;
    public int b;
    public boolean b0;

    /* renamed from: c */
    public int f10692c;
    public boolean c0;
    public int d;
    public int d0;

    /* renamed from: e */
    public int f10693e;

    /* renamed from: f */
    public int f10694f;

    /* renamed from: g */
    public int f10695g;

    /* renamed from: h */
    public int f10696h;

    /* renamed from: i */
    public int f10697i;

    /* renamed from: j */
    public String f10698j;

    /* renamed from: k */
    public String f10699k;

    /* renamed from: l */
    public String f10700l;

    /* renamed from: m */
    public String f10701m;

    /* renamed from: n */
    public String f10702n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public int[] w;
    public String x;
    public String y;
    public String z;

    public h(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.c0 = true;
        this.d0 = 0;
        if (jDJSONObject == null) {
            return;
        }
        int jsonInt = getJsonInt("showSubTitle", 0);
        this.d0 = jsonInt;
        if (jsonInt < 0 || jsonInt > 2) {
            this.d0 = 0;
        }
        this.f10698j = getJsonString("type");
        this.f10699k = getJsonString("biz");
        this.f10700l = getJsonString(DataCompassUtils.MODULE_TYPE_HEAD);
        this.N = getJsonString("jxppAsynFloor").equals("1");
        this.S = getJsonInt(ReportConstant.CommonInfo.PLAY_TYPE);
        this.T = getJsonInt("playTimes");
        this.f10701m = getJsonString("headType");
        this.f10702n = getJsonString(DYConstants.SHOW_NAME);
        this.o = getJsonString(DYConstants.DY_TEXT_COLOR);
        this.p = getJsonString("logoImage");
        this.q = getJsonString("darkModeImg");
        this.r = getJsonString("rightCorner");
        this.s = getJsonString("rightCornerColor");
        this.t = getJsonString("rightCornerImg");
        this.b = getJsonInt("bottomMargin");
        this.f10692c = getJsonInt("marginTop");
        this.d = getJsonInt("marginHorizontal");
        this.u = getJsonString("cornerDegree");
        this.v = getJsonString("bottomMarginColor");
        this.w = m.p(getJsonString("marginColor"), 0, true);
        this.x = getJsonString("bottomColor");
        this.f10693e = getJsonInt("bottomMarginWidth");
        this.f10694f = getJsonInt("innnerInterval");
        this.f10695g = getJsonInt("verticalInterval", -1);
        this.y = getJsonString("img");
        this.z = getJsonString("sourceValue");
        this.A = getJsonString("floorId");
        this.f10696h = getJsonInt("floorOrder");
        this.B = getJsonString("ceilingId");
        if (!"banner".equals(this.f10698j)) {
            this.a = getJsonObject("content");
        }
        this.C = getJsonString("expo");
        this.f10697i = getJsonInt("isNewStyle");
        this.E = getJsonInt("animationType");
        this.F = getJsonString("floorDisplayVersion");
        this.H = getJsonInt("closeButton");
        this.I = getJsonString("closeButtonImg");
        this.J = getJsonString("closeLog");
        this.O = getJsonString("asynSwitch", "");
        this.P = getJsonString("siteType", "");
        this.Q = getJsonString("siteId", "");
        this.R = getJsonInt("lbsDistance", 0);
        JDJSONArray jsonArr = getJsonArr("closeReason");
        if (jsonArr != null && jsonArr.size() > 0) {
            int min = Math.min(jsonArr.size(), 3);
            this.K = new String[min];
            for (int i2 = 0; i2 < min; i2++) {
                this.K[i2] = jsonArr.getString(i2);
            }
        }
        this.L = getJsonString("closeTips");
        try {
            JDJSONObject jsonObject = getJsonObject("jump");
            this.D = jsonObject == null ? null : (JumpEntity) jsonObject.toJavaObject(JumpEntity.class);
        } catch (Exception e2) {
            com.jingdong.app.mall.home.o.a.f.s0(this, e2);
        }
        JumpEntity jumpEntity = this.D;
        if (jumpEntity != null) {
            this.z = jumpEntity.getSrv();
        }
    }

    public JDJSONObject a() {
        return this.a;
    }

    public String b(String str, String str2) {
        try {
            return this.a.optString(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    public JDJSONObject c(int i2, int i3) {
        JDJSONObject i4 = i(i2);
        JDJSONArray jSONArray = i4 != null ? i4.getJSONArray("data") : null;
        if (jSONArray == null || i3 >= jSONArray.size()) {
            return null;
        }
        return jSONArray.getJSONObject(i3);
    }

    public JDJSONObject d(int i2) {
        JDJSONArray jSONArray;
        try {
            JDJSONObject jDJSONObject = this.a;
            if (jDJSONObject != null && (jSONArray = jDJSONObject.getJSONArray("data")) != null && jSONArray.size() > i2) {
                return jSONArray.getJSONObject(i2);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int e() {
        if (m()) {
            return 0;
        }
        return this.w[0];
    }

    public String f() {
        return getJsonString("expoJson", "");
    }

    public String g() {
        return this.F;
    }

    public JumpEntity getJump() {
        return this.D;
    }

    public String h() {
        JumpEntity jumpEntity = this.D;
        return jumpEntity == null ? "" : jumpEntity.srv;
    }

    public JDJSONObject i(int i2) {
        JDJSONArray j2 = j();
        if (j2 == null || j2.size() <= i2) {
            return null;
        }
        return j2.getJSONObject(i2);
    }

    public JDJSONArray j() {
        JDJSONObject jDJSONObject = this.a;
        if (jDJSONObject == null) {
            return null;
        }
        return jDJSONObject.getJSONArray("subFloors");
    }

    public boolean k() {
        return TextUtils.equals(this.f10699k, "prom");
    }

    public boolean l() {
        int[] iArr = this.w;
        return iArr != null && iArr.length > 1;
    }

    public boolean m() {
        return l() || this.b0;
    }

    public boolean n() {
        return k();
    }
}
