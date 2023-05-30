package com.jingdong.app.mall.home.category.floor.floorsub;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.view.CaContentLayout;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.n.g.v.d;
import com.jingdong.app.mall.home.n.g.x.c;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class CaCouponSubFloor extends BaseCaRecycleItem<c> {
    public static JDDisplayImageOptions G;
    public static JDDisplayImageOptions H;
    private TextView A;
    private f B;
    private TextView C;
    private f D;
    private SimpleDraweeView E;
    private AtomicBoolean F;
    private RelativeLayout q;
    private f r;
    private SimpleDraweeView s;
    private f t;
    private RelativeLayout u;
    private f v;
    private TextView w;
    private f x;
    private TextView y;
    private f z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c f8700g;

        a(c cVar) {
            this.f8700g = cVar;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            CaCouponSubFloor.this.w(this.f8700g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements HttpGroup.CustomOnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ c f8702g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ CategoryEntity.CaItem f8703h;

        b(c cVar, CategoryEntity.CaItem caItem) {
            this.f8702g = cVar;
            this.f8703h = caItem;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject;
            CaCouponSubFloor.this.F.set(false);
            if (httpResponse == null || (fastJsonObject = httpResponse.getFastJsonObject()) == null) {
                return;
            }
            String optString = fastJsonObject.optString("resultCode");
            this.f8702g.J(optString, fastJsonObject.optString(CartConstant.KEY_CART_RESULTMSG));
            com.jingdong.app.mall.home.n.h.a.c(this.f8703h);
            CaCouponSubFloor.this.x(this.f8702g);
            CaCouponSubFloor.this.u(this.f8702g, fastJsonObject, optString);
            Object tag = CaCouponSubFloor.this.getTag();
            c cVar = this.f8702g;
            if (tag != cVar) {
                return;
            }
            CaCouponSubFloor.this.w(cVar);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            CaCouponSubFloor.this.F.set(false);
            this.f8702g.J("-1", "\u6d3b\u52a8\u592a\u706b\u7206\uff0c\u4f11\u606f\u4e00\u4f1a\u518d\u6765\u54df~");
            CaCouponSubFloor.this.x(this.f8702g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    static {
        JDDisplayImageOptions resetViewBeforeLoading = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false);
        int i2 = R.drawable.home_webp_empty;
        G = resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
        H = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false).showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
    }

    public CaCouponSubFloor(Context context) {
        super(context);
        this.F = new AtomicBoolean(false);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.E = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.E, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.q = relativeLayout;
        relativeLayout.setId(R.id.homefloor_child_item1);
        f fVar = new f(120, 120);
        this.r = fVar;
        fVar.F(new Rect(12, 23, 0, 0));
        View view = this.q;
        addView(view, this.r.u(view));
        this.s = new HomeDraweeView(context);
        f fVar2 = new f(98, 98);
        this.t = fVar2;
        fVar2.F(new Rect(11, 11, 0, 0));
        RelativeLayout relativeLayout2 = this.q;
        SimpleDraweeView simpleDraweeView = this.s;
        relativeLayout2.addView(simpleDraweeView, this.t.u(simpleDraweeView));
        this.u = new RelativeLayout(context);
        f fVar3 = new f(-1, R2.anim.pop_left_top_out);
        this.v = fVar3;
        RelativeLayout.LayoutParams u = fVar3.u(this.u);
        u.addRule(1, this.q.getId());
        addView(this.u, u);
        TextView textView = new TextView(context);
        this.w = textView;
        v(textView, -907508);
        f fVar4 = new f(-1, 60);
        this.x = fVar4;
        fVar4.F(new Rect(12, 27, R2.anim.slide_in_from_bottom_medium_time, 0));
        RelativeLayout relativeLayout3 = this.u;
        TextView textView2 = this.w;
        relativeLayout3.addView(textView2, this.x.u(textView2));
        TextView textView3 = new TextView(context);
        this.y = textView3;
        v(textView3, -14277082);
        this.y.getPaint().setFakeBoldText(true);
        f fVar5 = new f(280, 30);
        this.z = fVar5;
        fVar5.F(new Rect(12, 79, 0, 0));
        RelativeLayout relativeLayout4 = this.u;
        TextView textView4 = this.y;
        relativeLayout4.addView(textView4, this.z.u(textView4));
        TextView textView5 = new TextView(context);
        this.A = textView5;
        v(textView5, -907508);
        f fVar6 = new f(-1, 32);
        this.B = fVar6;
        fVar6.F(new Rect(12, 108, R2.anim.slide_in_from_bottom_medium_time, 0));
        RelativeLayout relativeLayout5 = this.u;
        TextView textView6 = this.A;
        relativeLayout5.addView(textView6, this.B.u(textView6));
        TextView textView7 = new TextView(context);
        this.C = textView7;
        textView7.setTextColor(-1);
        this.C.setGravity(17);
        this.C.setSingleLine(true);
        this.C.setEllipsize(TextUtils.TruncateAt.END);
        f fVar7 = new f(R2.anim.popup_center_enter, R2.anim.pop_left_top_out);
        this.D = fVar7;
        fVar7.K(new Rect(24, 0, 24, 0));
        RelativeLayout.LayoutParams u2 = this.D.u(this.C);
        u2.addRule(11);
        this.u.addView(this.C, u2);
    }

    private void t(String str) {
        M m2 = this.f8679m;
        if (m2 == 0) {
            return;
        }
        com.jingdong.app.mall.home.n.g.v.c k2 = ((c) m2).k();
        d B = ((c) this.f8679m).B();
        if (k2 != null) {
            com.jingdong.app.mall.home.n.g.v.b.c("Category_Main_Coupon_Get", k2.e(B, "returnCode", str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(c cVar, JDJSONObject jDJSONObject, String str) {
        d B = cVar.B();
        if (B == null) {
            return;
        }
        d c2 = d.c(B.toString());
        JSONObject optJSONObject = c2.optJSONObject("gwc_coupon");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        try {
            if (cVar.I()) {
                optJSONObject.put("couponId", jDJSONObject.optString("couponId"));
                optJSONObject.put("getType", "1");
            } else {
                optJSONObject.put("code", str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        c2.a("gwc_coupon", optJSONObject);
        if (cVar.I()) {
            com.jingdong.app.mall.home.n.g.v.b.g("Category_Main_CouponGWC_Success", c2.toString());
        } else {
            com.jingdong.app.mall.home.n.g.v.b.g("Category_Main_CouponGWC_Fail", c2.toString());
        }
    }

    private void v(TextView textView, int i2) {
        textView.setTypeface(FontsUtil.getTypeFace(getContext(), 4099));
        textView.setTextColor(i2);
        textView.setGravity(16);
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(c cVar) {
        t(cVar.w());
        String E = cVar.E();
        if (TextUtils.isEmpty(E) || com.jingdong.app.mall.home.n.c.e()) {
            return;
        }
        if (cVar.I()) {
            ToastUtils.showToastInCenter(getContext(), R.drawable.home_category_coupon_get_success, E, 0);
        } else {
            ToastUtils.showToastInCenter(getContext(), R.drawable.home_category_coupon_get_empty, E, 0);
        }
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    protected boolean i() {
        M m2 = this.f8679m;
        if (m2 != 0 && !((c) m2).G()) {
            if (!LoginUserBase.hasLogin()) {
                l.l(getContext());
                t("5");
                return true;
            } else if (((c) this.f8679m).I()) {
                return false;
            } else {
                s((c) this.f8679m);
                return true;
            }
        }
        t("5");
        return true;
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: q  reason: merged with bridge method [inline-methods] */
    public void e(@NotNull c cVar) {
        this.F.set(false);
        w(cVar);
        this.z.Q(cVar.H() ? 258 : 280);
        f.c(this.y, this.z);
        this.r.Q(cVar.H() ? 120 : 0);
        f.c(this.q, this.r);
        this.w.setText(cVar.x());
        this.y.setText(cVar.z());
        this.A.setText(cVar.y());
        if (cVar.H()) {
            e.l(cVar.i(), this.s);
            if (TextUtils.isEmpty(cVar.u())) {
                e.a(this.E, "2623");
            } else {
                e.f(cVar.u(), this.E, G);
            }
        } else if (TextUtils.isEmpty(cVar.u())) {
            e.a(this.E, "2622");
        } else {
            e.f(cVar.u(), this.E, H);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem
    /* renamed from: r  reason: merged with bridge method [inline-methods] */
    public void f(@NotNull c cVar) {
        com.jingdong.app.mall.home.n.h.e.d(this.s, com.jingdong.app.mall.home.floor.common.d.d(12));
        f.c(this.u, this.v);
        f.c(this.s, this.t);
        f.c(this.w, this.x);
        f.c(this.A, this.B);
        f.c(this.C, this.D);
        f.O(this.w, 48);
        f.O(this.y, 20);
        f.O(this.A, 20);
        f.O(this.C, 22);
    }

    void s(@NotNull c cVar) {
        if (this.F.get()) {
            return;
        }
        this.F.set(true);
        setTag(cVar);
        CategoryEntity.CaItem i2 = CaContentLayout.i();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setListener(new b(cVar, i2));
        httpSetting.putJsonParam("encryptedKey", cVar.A());
        httpSetting.putJsonParam("pageClickKey", com.jingdong.app.mall.home.n.g.v.b.a);
        httpSetting.putJsonParam("shshshfpb", JMA.getSoftFingerprint(getContext()));
        httpSetting.putJsonParam("ruleId", cVar.C());
        httpSetting.setFunctionId("getSkuCoupon");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    void w(@NotNull c cVar) {
        if (!com.jingdong.app.mall.home.o.a.f.o0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new a(cVar));
            return;
        }
        this.C.setText(cVar.v());
        if (cVar.G()) {
            e.a(this.C, "2618");
        } else {
            e.a(this.C, "2619");
        }
    }
}
