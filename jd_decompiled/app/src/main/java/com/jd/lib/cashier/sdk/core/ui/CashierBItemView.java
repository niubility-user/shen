package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.k;

/* loaded from: classes14.dex */
public class CashierBItemView extends FrameLayout {

    /* renamed from: g  reason: collision with root package name */
    protected RelativeLayout f2952g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f2953h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f2954i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2955j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f2956k;

    /* renamed from: l  reason: collision with root package name */
    private CheckBox f2957l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f2958m;

    /* renamed from: n  reason: collision with root package name */
    protected ImageView f2959n;
    protected ImageView o;
    private TextView p;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2960j;

        a(CashierBItemView cashierBItemView, View.OnClickListener onClickListener) {
            this.f2960j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2960j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2961j;

        b(CashierBItemView cashierBItemView, View.OnClickListener onClickListener) {
            this.f2961j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2961j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public CashierBItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    private void d(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_b_item_pay_view, (ViewGroup) this, true);
        this.f2953h = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_icon);
        this.f2952g = (RelativeLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_item_view);
        this.f2954i = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_name);
        this.f2955j = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_more_pay_tips);
        this.f2956k = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_more_info_tip);
        this.f2957l = (CheckBox) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_check);
        this.p = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_name_desc);
        this.f2958m = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_pay_status_desc);
        this.f2959n = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_list_icon_1);
        this.o = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_list_icon_2);
    }

    private void h() {
        this.f2952g.setClickable(false);
        this.f2957l.setEnabled(false);
        this.f2957l.setClickable(false);
        this.f2959n.setClickable(false);
        this.o.setClickable(false);
        if (this.f2959n.getVisibility() == 0) {
            this.f2959n.setColorFilter(Color.parseColor("#aaC2C2C2"));
        }
        if (this.o.getVisibility() == 0) {
            this.o.setColorFilter(Color.parseColor("#aaC2C2C2"));
        }
        this.f2953h.setColorFilter(Color.parseColor("#aaC2C2C2"));
        this.p.setTextColor(Color.parseColor("#c2c2c2"));
        this.f2958m.setTextColor(Color.parseColor("#c2c2c2"));
        this.f2954i.setTextColor(Color.parseColor("#c2c2c2"));
        this.f2956k.setTextColor(Color.parseColor("#c2c2c2"));
    }

    private void i() {
        this.f2952g.setClickable(true);
        this.f2957l.setEnabled(true);
        this.f2957l.setClickable(false);
        this.f2959n.setClickable(true);
        this.o.setClickable(true);
        if (this.f2959n.getVisibility() == 0) {
            this.f2959n.setColorFilter(Color.parseColor("#00FFFFFF"));
        }
        if (this.o.getVisibility() == 0) {
            this.o.setColorFilter(Color.parseColor("#00FFFFFF"));
        }
        this.f2953h.setColorFilter(Color.parseColor("#00FFFFFF"));
        this.f2954i.setTextColor(Color.parseColor(JDDarkUtil.COLOR_1A1A1A));
        this.f2956k.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
        this.p.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
        this.f2958m.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public void A() {
        this.f2957l.setVisibility(0);
    }

    public void a(boolean z) {
        this.f2957l.setBackgroundResource(R.drawable.lib_cashier_sdk_button_i_new);
        if (z) {
            h();
        } else {
            i();
        }
    }

    public void b() {
        this.f2955j.setVisibility(8);
        this.f2959n.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void c() {
        this.f2957l.setVisibility(8);
    }

    public void e(boolean z) {
        this.f2957l.setChecked(z);
    }

    public void f(int i2, int i3) {
        ImageView imageView = this.f2959n;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), i2);
            layoutParams.height = DpiUtil.dip2px(getContext(), i3);
            this.f2959n.setLayoutParams(layoutParams);
        }
    }

    public void g() {
        ImageView imageView = this.f2959n;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 13.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 13.0f);
            this.f2959n.setLayoutParams(layoutParams);
        }
    }

    public void j(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2956k.setVisibility(0);
            this.f2956k.setText(str);
            this.f2956k.setContentDescription(str);
            return;
        }
        this.f2956k.setVisibility(8);
    }

    public void k(View.OnClickListener onClickListener) {
        this.f2952g.setOnClickListener(new b(this, onClickListener));
    }

    public void l(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2954i.setVisibility(0);
            this.f2954i.setText(str);
            this.f2954i.setContentDescription(str);
            return;
        }
        this.f2954i.setVisibility(8);
    }

    public void m(String str, boolean z, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            this.f2955j.setContentDescription("");
            this.f2955j.setVisibility(8);
            return;
        }
        this.f2955j.setVisibility(0);
        this.f2955j.setText(str);
        this.f2955j.setContentDescription(str);
        if (z) {
            this.f2955j.setClickable(true);
            Context context = getContext();
            if (context != null) {
                this.f2955j.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow), (Drawable) null);
                this.f2955j.setCompoundDrawablePadding(DpiUtil.dip2px(context, 1.0f));
                this.f2955j.setOnClickListener(new a(this, onClickListener));
                return;
            }
            return;
        }
        this.f2955j.setClickable(false);
        this.f2955j.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void n() {
        j0.b(this.f2955j);
    }

    public void o(String str) {
        p(str, "");
    }

    public void p(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f2959n, str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.f2959n.setContentDescription(str2);
            return;
        }
        this.f2959n.setVisibility(8);
    }

    public void q(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.f2959n.setOnClickListener(onClickListener);
        }
    }

    public void r() {
        this.f2959n.setVisibility(8);
    }

    public void s() {
        this.f2959n.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void t(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.o, str);
        } else {
            this.o.setVisibility(8);
        }
    }

    public void u() {
        this.o.setVisibility(8);
    }

    public void v(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2953h.setVisibility(0);
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 3;
            k.a(R.id.lib_cashier_sdk_payment_icon_tag, str, this.f2953h, imageLoaderOptions, true, null);
            return;
        }
        this.f2953h.setVisibility(4);
    }

    public void w(String str) {
        if (this.f2958m == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f2958m.setVisibility(0);
            this.f2958m.setText(str);
            return;
        }
        this.f2958m.setVisibility(8);
    }

    public void x(String str) {
        if (this.p == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.p.setVisibility(0);
            this.p.setText(str);
            this.p.setContentDescription(str);
            return;
        }
        this.p.setVisibility(8);
    }

    public void y(String str, View.OnClickListener onClickListener) {
        TextView textView = this.p;
        if (textView != null && j0.a(textView)) {
            if (!TextUtils.isEmpty(str)) {
                Drawable drawable = getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light);
                this.p.setCompoundDrawablePadding(DpiUtil.dip2px(getContext(), 4.0f));
                this.p.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
                this.p.setOnClickListener(onClickListener);
                return;
            }
            this.p.setOnClickListener(null);
            this.p.setCompoundDrawables(null, null, null, null);
        }
    }

    public void z() {
        this.f2956k.setTextColor(Color.parseColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public CashierBItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        d(context);
    }
}
