package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.k;

/* loaded from: classes14.dex */
public class CashierBNoLogoItemView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    protected LinearLayout f2970g;

    /* renamed from: h  reason: collision with root package name */
    protected LinearLayout f2971h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f2972i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2973j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f2974k;

    /* renamed from: l  reason: collision with root package name */
    protected ImageView f2975l;

    /* renamed from: m  reason: collision with root package name */
    protected ImageView f2976m;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2977j;

        a(CashierBNoLogoItemView cashierBNoLogoItemView, View.OnClickListener onClickListener) {
            this.f2977j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2977j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2978j;

        b(CashierBNoLogoItemView cashierBNoLogoItemView, View.OnClickListener onClickListener) {
            this.f2978j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2978j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public CashierBNoLogoItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    private void c(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_b_no_logo_item_pay_view, (ViewGroup) this, true);
        this.f2970g = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_root);
        this.f2972i = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_name);
        this.f2973j = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_more_pay_tips);
        this.f2971h = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_right_view);
        this.f2974k = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_more_info_tip);
        this.f2975l = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_list_icon_1);
        this.f2976m = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_list_icon_2);
    }

    private void f() {
        this.f2970g.setClickable(false);
        this.f2971h.setEnabled(false);
        this.f2971h.setClickable(false);
        this.f2975l.setClickable(false);
        this.f2976m.setClickable(false);
        if (this.f2975l.getVisibility() == 0) {
            this.f2975l.setColorFilter(Color.parseColor("#aaC2C2C2"));
        }
        if (this.f2976m.getVisibility() == 0) {
            this.f2976m.setColorFilter(Color.parseColor("#aaC2C2C2"));
        }
        this.f2972i.setTextColor(Color.parseColor("#c2c2c2"));
        this.f2974k.setTextColor(Color.parseColor("#c2c2c2"));
    }

    private void g() {
        this.f2970g.setClickable(true);
        this.f2971h.setEnabled(true);
        this.f2971h.setClickable(true);
        this.f2975l.setClickable(true);
        this.f2976m.setClickable(true);
        if (this.f2975l.getVisibility() == 0) {
            this.f2975l.setColorFilter(Color.parseColor("#00FFFFFF"));
        }
        if (this.f2976m.getVisibility() == 0) {
            this.f2976m.setColorFilter(Color.parseColor("#00FFFFFF"));
        }
        this.f2972i.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        this.f2974k.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public void a(boolean z) {
        if (z) {
            f();
        } else {
            g();
        }
    }

    public void b() {
        TextView textView = this.f2973j;
        if (textView != null) {
            textView.setVisibility(8);
        }
        ImageView imageView = this.f2975l;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = this.f2976m;
        if (imageView2 != null) {
            imageView2.setVisibility(8);
        }
    }

    public void d(int i2, int i3) {
        ImageView imageView = this.f2975l;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), i2);
            layoutParams.height = DpiUtil.dip2px(getContext(), i3);
            this.f2975l.setLayoutParams(layoutParams);
        }
    }

    public void e() {
        ImageView imageView = this.f2975l;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 13.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 13.0f);
            this.f2975l.setLayoutParams(layoutParams);
        }
    }

    public void h(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2974k.setVisibility(0);
            this.f2974k.setText(str);
            this.f2974k.setContentDescription(str);
            return;
        }
        this.f2974k.setVisibility(8);
    }

    public void i(String str) {
        if (this.f2972i == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f2972i.setVisibility(0);
            this.f2972i.setText(str);
            this.f2972i.setContentDescription(str);
            return;
        }
        this.f2972i.setVisibility(8);
    }

    public void j(String str, boolean z, View.OnClickListener onClickListener) {
        if (this.f2973j == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.f2973j.setContentDescription("");
            this.f2973j.setVisibility(8);
            return;
        }
        this.f2973j.setVisibility(0);
        this.f2973j.setText(str);
        this.f2973j.setContentDescription(str);
        if (z) {
            this.f2973j.setClickable(true);
            Context context = getContext();
            if (context != null) {
                this.f2973j.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow), (Drawable) null);
                this.f2973j.setCompoundDrawablePadding(DpiUtil.dip2px(context, 1.0f));
                this.f2973j.setOnClickListener(new b(this, onClickListener));
                return;
            }
            return;
        }
        this.f2973j.setClickable(false);
        this.f2973j.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void k() {
        j0.b(this.f2973j);
    }

    public void l(String str) {
        m(str, "");
    }

    public void m(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f2975l, str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.f2975l.setContentDescription(str2);
            return;
        }
        this.f2975l.setVisibility(8);
    }

    public void n(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.f2975l.setOnClickListener(onClickListener);
        }
    }

    public void o() {
        this.f2975l.setVisibility(8);
    }

    public void p() {
        this.f2975l.setVisibility(8);
        this.f2976m.setVisibility(8);
    }

    public void q(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f2976m, str);
        } else {
            this.f2976m.setVisibility(8);
        }
    }

    public void r() {
        this.f2976m.setVisibility(8);
    }

    public void s(View.OnClickListener onClickListener) {
        LinearLayout linearLayout = this.f2971h;
        if (linearLayout == null) {
            return;
        }
        linearLayout.setOnClickListener(new a(this, onClickListener));
    }

    public void t() {
        this.f2974k.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public void u(boolean z) {
        LinearLayout linearLayout = this.f2971h;
        if (linearLayout == null) {
            return;
        }
        if (z) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    public CashierBNoLogoItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        c(context);
    }
}
