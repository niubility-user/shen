package com.jingdong.app.mall.home.n.g.x;

import android.os.SystemClock;
import android.text.SpannableString;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes4.dex */
public final class e extends com.jingdong.app.mall.home.n.g.u.e {
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    private String f10408k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    private String f10409l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    private String f10410m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    private String f10411n;
    private long o;
    private int p;
    private JDJSONArray q;
    @NotNull
    private final ArrayList<a> r;

    /* loaded from: classes4.dex */
    public final class a extends com.jingdong.app.mall.home.r.e.b {
        @NotNull
        private String a;
        @Nullable
        private SpannableString b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        private SpannableString f10412c;
        @NotNull
        private JumpEntity d;
        @NotNull

        /* renamed from: e  reason: collision with root package name */
        private com.jingdong.app.mall.home.n.g.v.c f10413e;

        public a(@Nullable e eVar, @Nullable JDJSONObject jDJSONObject, com.jingdong.app.mall.home.n.g.u.c cVar, int i2) {
            super(jDJSONObject);
            String jsonString = getJsonString("img");
            Intrinsics.checkExpressionValueIsNotNull(jsonString, "getJsonString(\"img\")");
            this.a = jsonString;
            Object object = getObject("jump", JumpEntity.class);
            Intrinsics.checkExpressionValueIsNotNull(object, "getObject(\"jump\", JumpEntity::class.java)");
            this.d = (JumpEntity) object;
            String sprice = getJsonString("sprice");
            Intrinsics.checkExpressionValueIsNotNull(sprice, "sprice");
            g(sprice);
            if (!com.jingdong.app.mall.home.n.h.c.d(sprice)) {
                String jsonString2 = getJsonString(JshopConst.JSKEY_PRODUCT_JDPRICE);
                Intrinsics.checkExpressionValueIsNotNull(jsonString2, "getJsonString(\"jdPrice\")");
                f(jsonString2);
            }
            com.jingdong.app.mall.home.n.g.v.c cVar2 = new com.jingdong.app.mall.home.n.g.v.c();
            this.f10413e = cVar2;
            cVar2.q("Category_Flashbuy_Product", cVar, this.d.getSrvJson(), i2);
        }

        private final void f(String str) {
            if (com.jingdong.app.mall.home.n.h.c.d(str)) {
                return;
            }
            this.b = com.jingdong.app.mall.home.category.floor.feedssub.a.d(str, OrderISVUtil.MONEY_DECIMAL, 1.0f);
        }

        private final void g(String str) {
            if (com.jingdong.app.mall.home.n.h.c.d(str)) {
                this.f10412c = new SpannableString("");
            } else {
                this.f10412c = com.jingdong.app.mall.home.category.floor.feedssub.a.d(str, OrderISVUtil.MONEY_DECIMAL, 0.71f);
            }
        }

        @NotNull
        public final String a() {
            return this.a;
        }

        @Nullable
        public final SpannableString b() {
            return this.b;
        }

        @NotNull
        public final JumpEntity c() {
            return this.d;
        }

        @NotNull
        public final com.jingdong.app.mall.home.n.g.v.c d() {
            return this.f10413e;
        }

        @NotNull
        public final CharSequence e() {
            CharSequence a = com.jingdong.app.mall.home.n.h.c.a(this.f10412c);
            Intrinsics.checkExpressionValueIsNotNull(a, "CaCommonUtil.getPriceWithDef(sPriceSpan)");
            return a;
        }
    }

    public e(@Nullable JDJSONObject jDJSONObject, @Nullable com.jingdong.app.mall.home.n.b bVar) {
        super(jDJSONObject, bVar);
        this.r = new ArrayList<>();
    }

    private final void B() {
        JDJSONArray jDJSONArray = this.q;
        if (jDJSONArray == null) {
            Intrinsics.throwNpe();
        }
        if (jDJSONArray.size() < 3) {
            return;
        }
        for (int i2 = 0; i2 < 3; i2++) {
            JDJSONArray jDJSONArray2 = this.q;
            if (jDJSONArray2 == null) {
                Intrinsics.throwNpe();
            }
            JDJSONObject jSONObject = jDJSONArray2.getJSONObject(i2);
            if (jSONObject != null) {
                this.r.add(new a(this, jSONObject, this.f10369h, i2));
            }
        }
    }

    @NotNull
    public final ArrayList<a> A() {
        return this.r;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public void a(boolean z) {
        com.jingdong.app.mall.home.n.g.u.c mParentModel = this.f10369h;
        if (mParentModel == null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(mParentModel, "mParentModel");
        com.jingdong.app.mall.home.n.g.v.c e2 = mParentModel.e();
        if (e2 != null) {
            Iterator<a> it = this.r.iterator();
            while (it.hasNext()) {
                e2.a(it.next().d());
            }
        }
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    public boolean m() {
        return this.r.size() > 2;
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void n(@NotNull com.jingdong.app.mall.home.n.g.v.c cVar) {
        cVar.p("Category_Flashbuy_Activity");
    }

    @Override // com.jingdong.app.mall.home.n.g.u.e
    protected void p() {
        long elapsedRealtime;
        this.f10408k = getJsonString("img");
        this.f10409l = getJsonString("brandLogo");
        this.f10410m = getJsonString("bseckillTitle");
        this.f10411n = getJsonString("bseckillSubTitle");
        this.p = getJsonInt("remainTime");
        this.q = getJsonArr("skuList");
        com.jingdong.app.mall.home.n.g.u.c mParentModel = this.f10369h;
        if (mParentModel != null) {
            Intrinsics.checkExpressionValueIsNotNull(mParentModel, "mParentModel");
            elapsedRealtime = mParentModel.c();
        } else {
            elapsedRealtime = SystemClock.elapsedRealtime();
        }
        this.o = elapsedRealtime;
        B();
    }

    @Nullable
    public final String u() {
        return this.f10409l;
    }

    @Nullable
    public final String v() {
        return this.f10408k;
    }

    @Nullable
    public final a w(int i2) {
        if (i2 < this.r.size()) {
            return this.r.get(i2);
        }
        return null;
    }

    public final long x() {
        return this.p - ((SystemClock.elapsedRealtime() - this.o) / 1000);
    }

    @Nullable
    public final String y() {
        return this.f10411n;
    }

    @Nullable
    public final String z() {
        return this.f10410m;
    }
}
