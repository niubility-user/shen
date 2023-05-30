package com.jingdong.app.mall.home.tips;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.recommend.PerRecRouterImpl;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class RecommendTips extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Context f10890g;

    /* renamed from: h  reason: collision with root package name */
    private c f10891h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10892i;

    /* renamed from: j  reason: collision with root package name */
    private View f10893j;

    /* renamed from: k  reason: collision with root package name */
    private View f10894k;

    /* renamed from: l  reason: collision with root package name */
    private View f10895l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {
        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (httpResponse == null) {
                onError(null);
                return;
            }
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                onError(null);
                return;
            }
            String string = fastJsonObject.getString("msg");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            ToastUtils.showToastInCenter(RecommendTips.this.f10890g, string, 0);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (TextUtils.isEmpty(httpError.getMessage())) {
                return;
            }
            ToastUtils.showToastInCenter(RecommendTips.this.f10890g, httpError.getMessage(), 0);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f10897g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ SimpleDraweeView f10898h;

        b(RecommendTips recommendTips, String str, SimpleDraweeView simpleDraweeView) {
            this.f10897g = str;
            this.f10898h = simpleDraweeView;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            e.l(this.f10897g, this.f10898h);
        }
    }

    public RecommendTips(Context context) {
        super(context);
        this.f10890g = context;
    }

    private void c(String str, SimpleDraweeView simpleDraweeView) {
        f.E0(new b(this, str, simpleDraweeView));
    }

    private void e() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setListener(new a());
        httpSetting.setFunctionId("advRecommendSwitch");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public boolean b(c cVar) {
        this.f10891h = cVar;
        if (cVar == null || TextUtils.isEmpty(cVar.d()) || this.f10891h.getJump() == null) {
            return false;
        }
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(R2.attr.decimalNumber, R2.anim.lib_cashier_sdk_fragment_right_out);
        SimpleDraweeView simpleDraweeView = this.f10892i;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.f10890g);
            this.f10892i = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            View view = this.f10892i;
            addView(view, fVar.u(view));
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(simpleDraweeView, fVar);
        }
        c(cVar.d(), this.f10892i);
        this.f10892i.setOnClickListener(null);
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(98, 44);
        fVar2.E(60, 70, 0, 0);
        View view2 = this.f10893j;
        if (view2 == null) {
            View view3 = new View(this.f10890g);
            this.f10893j = view3;
            addView(view3, fVar2.u(view3));
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(view2, fVar2);
        }
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(144, 56);
        fVar3.E(0, 48, 36, 0);
        View view4 = this.f10894k;
        if (view4 == null) {
            View view5 = new View(this.f10890g);
            this.f10894k = view5;
            RelativeLayout.LayoutParams u = fVar3.u(view5);
            u.addRule(11);
            addView(this.f10894k, u);
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(view4, fVar3);
        }
        com.jingdong.app.mall.home.floor.common.f fVar4 = new com.jingdong.app.mall.home.floor.common.f(32, 32);
        fVar4.E(0, 0, 20, 0);
        View view6 = this.f10895l;
        if (view6 == null) {
            View view7 = new View(this.f10890g);
            this.f10895l = view7;
            RelativeLayout.LayoutParams u2 = fVar4.u(view7);
            u2.addRule(11);
            addView(this.f10895l, u2);
            return true;
        }
        com.jingdong.app.mall.home.floor.common.f.c(view6, fVar4);
        return true;
    }

    public void d() {
        if (this.f10891h == null || l.h()) {
            return;
        }
        if (this.f10891h.i() == 13) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("prstate", "0");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            PerRecRouterImpl.savePerRecStatus(jSONObject);
            ToastUtils.showToastInCenter(this.f10890g, "\u5df2\u5f00\u542f", 0);
        } else if (this.f10891h.i() == 14) {
            e();
        }
    }

    public void f(View.OnClickListener onClickListener) {
        View view = this.f10894k;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }

    public void g(View.OnClickListener onClickListener) {
        View view = this.f10895l;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }

    public void h(View.OnClickListener onClickListener) {
        View view = this.f10893j;
        if (view == null || onClickListener == null) {
            return;
        }
        view.setOnClickListener(onClickListener);
    }
}
