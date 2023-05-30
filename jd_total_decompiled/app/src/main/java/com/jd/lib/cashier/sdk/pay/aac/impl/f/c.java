package com.jd.lib.cashier.sdk.pay.aac.impl.f;

import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.impl.e.f;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class c implements f, com.jd.lib.cashier.sdk.core.aac.f {

    /* renamed from: g  reason: collision with root package name */
    private boolean f3750g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f3751h;

    /* renamed from: i  reason: collision with root package name */
    private View f3752i;

    /* renamed from: j  reason: collision with root package name */
    private View f3753j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f3754k;

    /* renamed from: l  reason: collision with root package name */
    private final CashierPayActivity f3755l;

    /* renamed from: m  reason: collision with root package name */
    private final AlphaAnimation f3756m = new AlphaAnimation(0.0f, 1.0f);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Animation.AnimationListener {
        a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (c.this.f3752i == null || c.this.f3753j == null) {
                return;
            }
            c.this.f3753j.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            c.this.f3752i.setBackgroundColor(0);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            if (c.this.f3752i != null) {
                c.this.f3752i.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_7F000000));
            }
        }
    }

    public c(CashierPayActivity cashierPayActivity) {
        this.f3755l = cashierPayActivity;
    }

    private void g() {
        if (this.f3752i != null) {
            this.f3756m.setDuration(200L);
            this.f3756m.setAnimationListener(new a());
            this.f3752i.startAnimation(this.f3756m);
        }
    }

    private void i() {
        if (this.f3752i == null || this.f3753j == null) {
            return;
        }
        this.f3756m.cancel();
        this.f3756m.setAnimationListener(null);
        this.f3753j.setBackgroundColor(0);
        this.f3752i.setBackgroundColor(0);
    }

    private void m() {
        ImageView imageView = this.f3751h;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.aac.impl.f.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    c.this.o(view);
                }
            });
        }
        View view = this.f3752i;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.cashier.sdk.pay.aac.impl.f.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    c.this.q(view2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(View view) {
        if (g0.a(this.f3755l)) {
            this.f3755l.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void q(View view) {
        if (g0.a(this.f3755l)) {
            this.f3755l.onBackPressed();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.f
    public void a() {
        if (this.f3750g) {
            i();
            this.f3750g = false;
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.f
    public void c() {
        if (this.f3750g) {
            return;
        }
        g();
        this.f3750g = true;
    }

    @Override // com.jd.lib.cashier.sdk.pay.aac.impl.e.f
    public void d() {
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.c
    public void h(Window window) {
        this.f3753j = window.findViewById(R.id.lib_cashier_pop_page_fragment_root);
        this.f3754k = (LinearLayout) window.findViewById(R.id.lib_cashier_pop_page_top_layout_root);
        this.f3751h = (ImageView) window.findViewById(R.id.lib_cashier_pop_page_fragment_close);
        this.f3752i = window.findViewById(R.id.lib_cashier_pay_blank);
        onChangeSkin();
        m();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        int i2 = JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_pop_top_corner_dark_bg_grey : R.drawable.lib_cashier_sdk_pop_top_corner_bg_grey;
        LinearLayout linearLayout = this.f3754k;
        if (linearLayout != null) {
            linearLayout.setBackgroundResource(i2);
        }
        int i3 = JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_a_common_dialog_close_dark : R.drawable.lib_cashier_sdk_a_common_dialog_close;
        ImageView imageView = this.f3751h;
        if (imageView != null) {
            imageView.setBackgroundResource(i3);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3754k != null) {
            this.f3754k = null;
        }
        if (this.f3751h != null) {
            this.f3751h = null;
        }
        if (this.f3752i != null) {
            this.f3752i = null;
        }
    }
}
