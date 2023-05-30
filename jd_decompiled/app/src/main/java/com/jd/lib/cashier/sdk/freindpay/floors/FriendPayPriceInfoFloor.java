package com.jd.lib.cashier.sdk.freindpay.floors;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView;
import com.jd.lib.cashier.sdk.core.ui.widget.SecondCountDownView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.s;
import com.jd.lib.cashier.sdk.f.f.g;

/* loaded from: classes14.dex */
public class FriendPayPriceInfoFloor extends AbstractFloor<com.jd.lib.cashier.sdk.f.c.a, g> {

    /* renamed from: i */
    private View f3382i;

    /* renamed from: j */
    private SecondCountDownView f3383j;

    /* renamed from: k */
    private LinearLayout f3384k;

    /* renamed from: l */
    private LinearLayout f3385l;

    /* renamed from: m */
    private TextView f3386m;

    /* renamed from: n */
    private TextView f3387n;
    private TextView o;

    /* loaded from: classes14.dex */
    public class a implements AbsCountdownView.a {
        final /* synthetic */ CashierCommonPopConfig a;

        a(CashierCommonPopConfig cashierCommonPopConfig) {
            FriendPayPriceInfoFloor.this = r1;
            this.a = cashierCommonPopConfig;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView.a
        public void onFinish() {
            FriendPayPriceInfoFloor.this.i(this.a);
        }
    }

    /* loaded from: classes14.dex */
    public class b implements com.jd.lib.cashier.sdk.b.d.b.b {
        final /* synthetic */ Context a;

        b(FriendPayPriceInfoFloor friendPayPriceInfoFloor, Context context) {
            this.a = context;
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.b
        public void a(String str) {
            p.a(this.a, str);
            PayTaskStackManager.removeAllCashierTask();
        }
    }

    public FriendPayPriceInfoFloor(View view) {
        super(view);
    }

    private void d() {
        try {
            this.f3386m.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
            this.f3387n.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            this.o.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            if (JDDarkUtil.isDarkMode()) {
                this.f3382i.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_dark_bg);
            } else {
                this.f3382i.setBackgroundResource(R.drawable.lib_cashier_sdk_top_corner_bg);
            }
            this.f3385l.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            this.f3383j.onChangeSkin();
        } catch (Exception e2) {
            r.b("FriendPayPriceInfoFloor", e2.getMessage());
        }
    }

    private void f(String str, CashierCommonPopConfig cashierCommonPopConfig) {
        if (this.f3384k == null || this.f3383j == null) {
            return;
        }
        double b2 = s.b(str);
        if (b2 > 0.0d) {
            this.f3384k.setVisibility(0);
            this.f3383j.m((long) (b2 * 1000.0d));
            this.f3383j.n(new a(cashierCommonPopConfig));
            this.f3383j.o();
            return;
        }
        this.f3384k.setVisibility(8);
    }

    private void g(String str, String str2) {
        Context context;
        if (this.f3386m == null || (context = getConvertView().getContext()) == null) {
            return;
        }
        this.f3386m.setText(h0.a(context, str2, str));
        m0.a(this.f3386m, (byte) 3);
    }

    private void h(String str) {
        TextView textView = this.f3387n;
        if (textView == null) {
            return;
        }
        textView.setText(str);
    }

    public void i(CashierCommonPopConfig cashierCommonPopConfig) {
        Context context = getConvertView().getContext();
        if (cashierCommonPopConfig == null || !(context instanceof FragmentActivity)) {
            return;
        }
        com.jd.lib.cashier.sdk.b.d.a.b((FragmentActivity) context, cashierCommonPopConfig, new b(this, context));
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: e */
    public void b(com.jd.lib.cashier.sdk.f.c.a aVar, g gVar) {
        if (gVar != null) {
            h(gVar.a);
            g(gVar.d, gVar.b);
            f(gVar.f3357c, gVar.f3358e);
            d();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3386m = (TextView) getView(R.id.lib_cashier_friend_pay_price_label);
        this.f3387n = (TextView) getView(R.id.lib_cashier_friend_pay_title_label);
        this.f3382i = getView(R.id.lib_cashier_friend_pay_price_top_corner);
        this.f3383j = (SecondCountDownView) getView(R.id.lib_cashier_friend_pay_count_down_label);
        this.f3385l = (LinearLayout) getView(R.id.lib_cashier_friend_pay_price_container);
        this.f3384k = (LinearLayout) getView(R.id.lib_cashier_friend_pay_count_down_layout);
        this.o = (TextView) getView(R.id.lib_cashier_friend_pay_count_down_sub_title);
    }
}
