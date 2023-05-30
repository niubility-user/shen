package com.jd.lib.cashier.sdk.pay.aac.impl.f;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.aura.IAura;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.e0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.l;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.f;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.DfPriceInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class d implements f, ITitleThemeChangeListener {

    /* renamed from: g  reason: collision with root package name */
    private View f3758g;

    /* renamed from: h  reason: collision with root package name */
    private ViewGroup f3759h;

    /* renamed from: i  reason: collision with root package name */
    private final CashierPayActivity f3760i;

    /* renamed from: j  reason: collision with root package name */
    private ITitleThemeChangeEvent f3761j;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (d.this.f3760i != null) {
                d.this.f3760i.onBackPressed();
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ boolean f3763j;

        b(boolean z) {
            this.f3763j = z;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            if (this.f3763j) {
                com.jd.lib.cashier.sdk.h.e.a.d().r(d.this.f3760i, d.this.f3760i.x().b().f3511e);
                d.this.f3760i.w().i(d.this.f3760i, d.this.f3760i.x().b().f3511e);
                return;
            }
            com.jd.lib.cashier.sdk.h.e.a.d().s(d.this.f3760i, d.this.f3760i.x().b().f3511e);
            d.this.f3760i.w().b(d.this.f3760i);
            d.this.f3760i.finish();
        }
    }

    public d(CashierPayActivity cashierPayActivity) {
        this.f3760i = cashierPayActivity;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.f
    public void d() {
        TopFloor topFloor;
        DfPriceInfo dfPriceInfo;
        if (g0.a(this.f3760i)) {
            CashierPayEntity cashierPayEntity = this.f3760i.x().b().K;
            if (cashierPayEntity != null && (topFloor = cashierPayEntity.topFloor) != null && (dfPriceInfo = topFloor.dfPriceInfo) != null && !TextUtils.isEmpty(dfPriceInfo.customerName)) {
                e0.r(this.f3758g, this.f3760i.getString(R.string.lib_cashier_sdk_pay_df_title));
            }
            if (!TextUtils.equals("1", this.f3760i.x().b().D) || this.f3760i.x().b().e() || this.f3758g == null) {
                return;
            }
            IAura aura = DependInitializer.getAura();
            boolean z = !TextUtils.isEmpty(this.f3760i.x().b().f3511e) && this.f3760i.x().b().f() && aura != null && aura.isBundlePrepared("com.jd.lib.orderinfocard");
            e0.n(this.f3758g, 0);
            e0.m(this.f3758g, this.f3760i.getString(z ? R.string.lib_cashier_sdk_pay_order_detail_txt : R.string.lib_cashier_sdk_pay_right_btn_txt));
            e0.l(this.f3758g, new b(z));
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        TextView b2;
        ITitleChangeEventCreator titleChangeEventCreator = DependInitializer.getTitleChangeEventCreator();
        if (titleChangeEventCreator != null) {
            this.f3761j = titleChangeEventCreator.instanceTitleThemeChangeEvent();
        }
        this.f3758g = e0.c(this.f3760i);
        this.f3759h = (ViewGroup) window.findViewById(R.id.lib_cashier_pay_title_root);
        View view = this.f3758g;
        if (view != null) {
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            e0.i(this.f3758g);
            e0.p(this.f3758g, true);
            e0.r(this.f3758g, this.f3760i.getString(R.string.lib_cashier_sdk_pay_title));
            e0.k(this.f3758g, new a());
            e0.e(this.f3758g);
        }
        View view2 = this.f3758g;
        if (view2 != null && (b2 = e0.b(view2)) != null) {
            b2.setTextSize(l.a(this.f3760i, b2.getTextSize()));
        }
        TextView a2 = e0.a(this.f3758g);
        if (a2 != null) {
            a2.setTextSize(l.a(this.f3760i, a2.getTextSize()));
        }
        ViewGroup viewGroup = this.f3759h;
        if (viewGroup != null && this.f3758g != null) {
            viewGroup.removeAllViews();
            this.f3759h.addView(this.f3758g);
        }
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f3761j;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.registerTitleThemeChangeEvent(this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        View view = this.f3758g;
        if (view != null) {
            e0.e(view);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        ViewGroup viewGroup = this.f3759h;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.f3759h = null;
        }
        if (this.f3758g != null) {
            this.f3758g = null;
        }
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f3761j;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.unRegisterTitleThemeChangeEvent(this);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener
    public void onThemeChange(boolean z, String str) {
        View view = this.f3758g;
        if (view != null) {
            e0.e(view);
        }
    }
}
