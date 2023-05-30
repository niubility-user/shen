package com.jingdong.app.mall.home.floor.view.b.g;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLine1To4GroupBuying;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.cart.CartPromotion;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class f extends com.jingdong.app.mall.home.floor.view.linefloor.base.a {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private int I;
    private int J;
    private boolean K;
    private boolean L;
    private a x;
    private String y;
    private String z;

    /* loaded from: classes4.dex */
    public static class a {
        public com.jingdong.app.mall.home.floor.common.f a;
        public com.jingdong.app.mall.home.floor.common.f b;

        /* renamed from: c  reason: collision with root package name */
        public com.jingdong.app.mall.home.floor.common.f f9780c;
        public com.jingdong.app.mall.home.floor.common.f d;

        /* renamed from: e  reason: collision with root package name */
        public com.jingdong.app.mall.home.floor.common.f f9781e;

        /* renamed from: f  reason: collision with root package name */
        public com.jingdong.app.mall.home.floor.common.f f9782f;

        /* renamed from: g  reason: collision with root package name */
        public com.jingdong.app.mall.home.floor.common.f f9783g;

        /* renamed from: h  reason: collision with root package name */
        public com.jingdong.app.mall.home.floor.common.f f9784h;

        /* renamed from: i  reason: collision with root package name */
        public int f9785i;

        /* renamed from: j  reason: collision with root package name */
        public int f9786j;

        /* renamed from: k  reason: collision with root package name */
        public int f9787k;
    }

    public f(com.jingdong.app.mall.home.floor.view.b.c cVar, com.jingdong.app.mall.home.r.e.f fVar, com.jingdong.app.mall.home.floor.view.b.a aVar) {
        super(cVar, fVar, aVar);
        this.x = new a();
    }

    private void X(int i2) {
        int jsonInt = this.f9856j.getJsonInt("imageType", 0);
        this.I = jsonInt;
        if (!this.K && !this.L) {
            this.I = 1;
        } else {
            if (jsonInt == 0) {
                String jsonString = this.f9856j.getJsonString("img");
                this.y = jsonString;
                this.y = TextUtils.isEmpty(jsonString) ? "https://imgEmpty" : this.y;
            }
            String jsonString2 = this.f9856j.getJsonString("slogan");
            this.C = jsonString2;
            if (this.K) {
                this.C = com.jingdong.app.mall.home.o.a.f.j(5, jsonString2);
            }
            if (this.L) {
                this.H = this.f9854h.getJsonString("clkLog");
            }
            this.D = this.f9856j.getJsonString("sloganBottomColor");
        }
        this.z = this.f9856j.getJsonString("img");
        this.A = this.f9856j.getJsonString("slogan");
        this.F = this.f9856j.getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.G = this.L ? this.f9856j.getJsonString(CartPromotion.KEY_PRICECOLOR) : "#fa2c19";
        this.B = this.f9856j.getJsonString("sloganColor");
        this.E = this.f9856j.getJsonBoolean("freePostage", false) ? "\u5305\u90ae" : "";
    }

    private void Y(int i2) {
        if (this.K) {
            h0();
        } else if (this.L) {
            f0();
        } else {
            g0();
        }
    }

    public static boolean b0(com.jingdong.app.mall.home.r.e.g gVar) {
        if (gVar == null) {
            return false;
        }
        return TextUtils.equals("6", gVar.getJsonString("displayUIStyle"));
    }

    public static boolean c0(com.jingdong.app.mall.home.r.e.d dVar) {
        return TextUtils.equals("4", dVar.getJsonString("displayUIStyle"));
    }

    public static boolean d0(com.jingdong.app.mall.home.r.e.d dVar) {
        String jsonString = dVar.getJsonString("displayUIStyle");
        return TextUtils.equals("4", jsonString) || TextUtils.equals("5", jsonString) || TextUtils.equals("6", jsonString);
    }

    private void f0() {
        this.x.a = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pop_right_top_in, R2.anim.slide_out_to_top);
        this.x.b = new com.jingdong.app.mall.home.floor.common.f(144, 180);
        this.x.f9780c = new com.jingdong.app.mall.home.floor.common.f(144, 144);
        this.x.f9780c.E(0, 0, 0, 0);
        this.x.d = new com.jingdong.app.mall.home.floor.common.f(-2, 30);
        this.x.d.E(0, 115, 0, 0);
        this.x.d.J(12, 0, 12, 0);
        a aVar = this.x;
        aVar.f9786j = 20;
        aVar.f9787k = 140;
        aVar.f9781e = new com.jingdong.app.mall.home.floor.common.f(144, 52);
        this.x.f9781e.E(0, R2.anim.manto_translate_dialog_out, 0, 0);
        this.x.f9783g = new com.jingdong.app.mall.home.floor.common.f(-2, 52);
        this.x.f9785i = com.jingdong.app.mall.home.floor.common.d.d(144);
    }

    private void g0() {
        this.x.a = new com.jingdong.app.mall.home.floor.common.f(170, 240);
        this.x.b = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pickerview_dialog_scale_in, 228);
        this.x.f9780c = new com.jingdong.app.mall.home.floor.common.f(152, 152);
        this.x.f9780c.E(0, 12, 0, 0);
        this.x.f9781e = new com.jingdong.app.mall.home.floor.common.f(-1, 44);
        this.x.f9781e.E(0, R2.anim.pop_center_out, 0, 0);
        this.x.f9783g = new com.jingdong.app.mall.home.floor.common.f(-2, -1);
        this.x.f9783g.E(0, 3, 4, 0);
        if (!TextUtils.isEmpty(this.E)) {
            this.x.f9785i = com.jingdong.app.mall.home.floor.common.d.d(120);
            this.x.f9784h = new com.jingdong.app.mall.home.floor.common.f(-2, 24);
            this.x.f9784h.J(4, 0, 4, 0);
        } else {
            this.x.f9785i = com.jingdong.app.mall.home.floor.common.d.d(160);
            this.x.f9784h = null;
        }
        this.x.f9782f = new com.jingdong.app.mall.home.floor.common.f(-1, 46);
        this.x.f9782f.E(0, R2.anim.slide_out_to_bottom, 0, 0);
        this.J = 20;
    }

    private void h0() {
        this.x.a = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pop_left_top_out, 240);
        this.x.b = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pickerview_dialog_scale_in, 228);
        this.x.f9780c = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pickerview_dialog_scale_in, R2.anim.pickerview_dialog_scale_in);
        this.x.f9780c.E(0, 0, 0, 0);
        this.x.d = new com.jingdong.app.mall.home.floor.common.f(-2, 38);
        this.x.d.E(0, R2.anim.lib_cashier_sdk_fragment_right_out, 0, 0);
        this.x.d.J(12, 0, 12, 0);
        a aVar = this.x;
        aVar.f9786j = 22;
        aVar.f9787k = R2.anim.pickerview_dialog_scale_in;
        aVar.f9781e = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pickerview_dialog_scale_in, 83);
        this.x.f9781e.E(0, R2.anim.message_center_dialog_out, 0, 0);
        this.x.f9783g = new com.jingdong.app.mall.home.floor.common.f(-2, 52);
        this.x.f9783g.E(0, 15, 0, 0);
        this.x.f9785i = com.jingdong.app.mall.home.floor.common.d.d(160);
    }

    public int N() {
        return this.J;
    }

    public a O() {
        return this.x;
    }

    public String P() {
        return this.F;
    }

    public String Q() {
        return this.G;
    }

    public String R() {
        return this.C;
    }

    public String S() {
        return this.D;
    }

    public String T() {
        return this.z;
    }

    public String U() {
        return this.A;
    }

    public String V() {
        return this.B;
    }

    public String W() {
        return this.E;
    }

    public boolean Z() {
        return this.L;
    }

    public boolean a0() {
        return this.K;
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public void c(int i2, int i3) {
        this.K = c0(this.f9855i);
        this.L = b0(this.f9855i);
        X(i2);
        Y(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.linefloor.base.a
    public String e() {
        return this.y;
    }

    public void e0(Context context) {
        JumpEntity jump;
        if (context == null || l.k() || (jump = this.f9856j.getJump()) == null) {
            return;
        }
        if (this.L) {
            MallFloorLine1To4GroupBuying.postOnceLog(this.H, false);
        }
        String jSONObject = f(0).toString();
        l.c(jump, this.z);
        l.onClickJsonEvent(context, jump, "", jump.getSrv(), jSONObject, 1);
    }
}
