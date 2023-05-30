package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
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
import com.jd.lib.cashier.sdk.core.utils.c;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public class CashierItemView extends FrameLayout {
    private static final String v = CashierItemView.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    protected RelativeLayout f2984g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f2985h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f2986i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2987j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f2988k;

    /* renamed from: l  reason: collision with root package name */
    private TextView f2989l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f2990m;

    /* renamed from: n  reason: collision with root package name */
    protected ImageView f2991n;
    protected ImageView o;
    public TextView p;
    public TextView q;
    private ImageView r;
    private CheckBox s;
    private View t;
    private View u;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2992j;

        a(CashierItemView cashierItemView, View.OnClickListener onClickListener) {
            this.f2992j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2992j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f2993j;

        b(CashierItemView cashierItemView, View.OnClickListener onClickListener) {
            this.f2993j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f2993j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public CashierItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        j(context);
    }

    private void Q() {
        j0.d(this.u);
    }

    private void h() {
        j0.b(this.u);
    }

    private void j(Context context) {
        View inflate = LayoutInflater.from(context).inflate(b(), (ViewGroup) this, true);
        this.f2984g = (RelativeLayout) inflate.findViewById(R.id.pay_item_view);
        this.f2985h = (ImageView) inflate.findViewById(R.id.pay_icon);
        this.f2986i = (TextView) inflate.findViewById(R.id.pay_name);
        this.f2987j = (TextView) inflate.findViewById(R.id.pay_channel_name);
        this.f2988k = (TextView) inflate.findViewById(R.id.pay_channel_tail);
        this.f2989l = (TextView) inflate.findViewById(R.id.pay_name_desc);
        this.f2990m = (TextView) inflate.findViewById(R.id.pay_status_desc);
        this.f2991n = (ImageView) inflate.findViewById(R.id.list_icon_1);
        this.o = (ImageView) inflate.findViewById(R.id.list_icon_2);
        this.p = (TextView) inflate.findViewById(R.id.more_pay_tips);
        this.q = (TextView) inflate.findViewById(R.id.more_info_tip);
        this.r = (ImageView) inflate.findViewById(R.id.to_bank_arrow);
        this.s = (CheckBox) inflate.findViewById(R.id.pay_check);
        this.t = inflate.findViewById(R.id.lib_cashier_item_pay_split_line);
        this.u = inflate.findViewById(R.id.lib_cashier_item_pay_split_line_floor);
    }

    private void n() {
        this.f2984g.setClickable(false);
        this.s.setEnabled(false);
        this.s.setClickable(false);
        this.f2991n.setClickable(false);
        this.o.setClickable(false);
        this.f2985h.setColorFilter(JDDarkUtil.getDarkColor("#aaC2C2C2", "#aa535353"));
        if (this.f2991n.getVisibility() == 0) {
            this.f2991n.setColorFilter(JDDarkUtil.getDarkColor("#aaC2C2C2", "#aa535353"));
        }
        if (this.o.getVisibility() == 0) {
            this.o.setColorFilter(JDDarkUtil.getDarkColor("#aaC2C2C2", "#aa535353"));
        }
        this.f2989l.setTextColor(JDDarkUtil.getDarkColor("#c2c2c2", JDDarkUtil.COLOR_555353));
        this.q.setTextColor(JDDarkUtil.getDarkColor("#c2c2c2", JDDarkUtil.COLOR_555353));
        this.f2990m.setTextColor(JDDarkUtil.getDarkColor("#c2c2c2", JDDarkUtil.COLOR_555353));
        this.f2986i.setTextColor(JDDarkUtil.getDarkColor("#c2c2c2", JDDarkUtil.COLOR_555353));
        this.f2987j.setTextColor(JDDarkUtil.getDarkColor("#BFBFBF", false));
        this.f2987j.setBackgroundResource(R.drawable.lib_cashier_sdk_channel_btn_disable);
    }

    private void q() {
        this.t.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
    }

    public void A() {
        this.f2991n.setVisibility(8);
    }

    public void B() {
        this.f2991n.setVisibility(8);
        this.o.setVisibility(8);
    }

    public void C(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.o, str);
        } else {
            this.o.setVisibility(8);
        }
    }

    public void D() {
        this.o.setVisibility(8);
    }

    public void E(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2985h.setVisibility(0);
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 3;
            k.a(R.id.lib_cashier_sdk_payment_icon_tag, str, this.f2985h, imageLoaderOptions, true, null);
            return;
        }
        this.f2985h.setVisibility(4);
    }

    public void F(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2990m.setVisibility(0);
            this.f2990m.setText(str);
            return;
        }
        this.f2990m.setVisibility(8);
    }

    public void G(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2989l.setVisibility(0);
            this.f2989l.setText(str);
            this.f2989l.setContentDescription(str);
            return;
        }
        this.f2989l.setVisibility(8);
    }

    public void H(String str, View.OnClickListener onClickListener) {
        TextView textView = this.f2989l;
        if (textView != null && j0.a(textView)) {
            if (!TextUtils.isEmpty(str)) {
                Drawable drawable = getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light);
                this.f2989l.setCompoundDrawablePadding(DpiUtil.dip2px(getContext(), 4.0f));
                this.f2989l.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
                this.f2989l.setOnClickListener(onClickListener);
                return;
            }
            this.f2989l.setOnClickListener(null);
            this.f2989l.setCompoundDrawables(null, null, null, null);
        }
    }

    public void I() {
        i();
        Q();
    }

    public void J(String str) {
        this.f2984g.setFocusable(true);
        if (Build.VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(1);
        }
        this.f2984g.setContentDescription(str);
    }

    public void K() {
        this.q.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public void L() {
        R();
        h();
        q();
    }

    public void M() {
        i();
        Q();
    }

    public void N() {
        R();
        h();
        q();
    }

    public void O() {
        this.r.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
        this.r.setVisibility(0);
    }

    public void P() {
        this.s.setVisibility(0);
    }

    public void R() {
        j0.d(this.t);
    }

    public void a(boolean z) {
        this.s.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_dark : R.drawable.lib_cashier_sdk_button_i_new);
        q();
        if (z) {
            n();
        } else {
            o();
        }
    }

    public int b() {
        return R.layout.lib_cashier_sdk_item_pay_view;
    }

    public String c(String str, boolean z) {
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.f2986i.getContentDescription());
        String str6 = "";
        sb.append("");
        String sb2 = sb.toString();
        if (z) {
            sb2 = c.b(sb2);
        }
        if (this.f2989l.getVisibility() != 0 || TextUtils.isEmpty(this.f2989l.getContentDescription())) {
            str2 = "";
        } else {
            str2 = ((Object) this.f2989l.getContentDescription()) + "";
        }
        if (this.f2991n.getVisibility() != 0 || TextUtils.isEmpty(this.f2991n.getContentDescription())) {
            str3 = "";
        } else {
            str3 = ((Object) this.f2991n.getContentDescription()) + "";
        }
        if (this.p.getVisibility() != 0 || TextUtils.isEmpty(this.p.getContentDescription())) {
            str4 = "";
        } else {
            str4 = ((Object) this.p.getContentDescription()) + "";
        }
        if (this.q.getVisibility() != 0 || TextUtils.isEmpty(this.q.getContentDescription())) {
            str5 = "";
        } else {
            str5 = ((Object) this.q.getContentDescription()) + "";
        }
        if (this.s.getVisibility() == 0) {
            str6 = this.s.isChecked() ? "\u5df2\u9009\u4e2d" : "\u672a\u9009\u4e2d";
        }
        if (!"3".equals(str) && !"7".equals(str)) {
            return (str6 + sb2 + str2 + str3 + str4 + str5).trim();
        }
        return (sb2 + "\u5de5\u5177\u4e0d\u53ef\u7528" + str2).trim();
    }

    public void d() {
        this.f2991n.setVisibility(8);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        this.f2988k.setVisibility(8);
        this.f2987j.setVisibility(8);
    }

    public void e() {
        i();
        h();
    }

    public void f() {
        this.r.setVisibility(8);
    }

    public void g() {
        this.s.setVisibility(8);
    }

    public void i() {
        j0.b(this.t);
    }

    public void k(boolean z) {
        this.s.setChecked(z);
    }

    public void l(int i2, int i3) {
        ImageView imageView = this.f2991n;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), i2);
            layoutParams.height = DpiUtil.dip2px(getContext(), i3);
            this.f2991n.setLayoutParams(layoutParams);
        }
    }

    public void m() {
        ImageView imageView = this.f2991n;
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.width = DpiUtil.dip2px(getContext(), 13.0f);
            layoutParams.height = DpiUtil.dip2px(getContext(), 13.0f);
            this.f2991n.setLayoutParams(layoutParams);
        }
    }

    public void o() {
        this.f2984g.setClickable(true);
        this.s.setEnabled(true);
        this.s.setClickable(false);
        this.f2991n.setClickable(true);
        this.o.setClickable(true);
        this.f2985h.setColorFilter(JDDarkUtil.getDarkColor("#00FFFFFF"));
        if (this.f2991n.getVisibility() == 0) {
            this.f2991n.setColorFilter(JDDarkUtil.getDarkColor("#00FFFFFF"));
        }
        if (this.o.getVisibility() == 0) {
            this.o.setColorFilter(JDDarkUtil.getDarkColor("#00FFFFFF"));
        }
        this.f2989l.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.q.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.f2990m.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.f2986i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        this.f2988k.setTextColor(JDDarkUtil.getDarkColor("#FF000000", JDDarkUtil.COLOR_FFFFFFF));
        this.f2987j.setTextColor(JDDarkUtil.getDarkColor("#FD241B", false));
        this.f2987j.setBackgroundResource(R.drawable.lib_cashier_sdk_channel_btn);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        r.b(v, "AccessibilityEvent = " + accessibilityEvent.toString());
        if (4 == accessibilityEvent.getEventType()) {
            accessibilityEvent.setChecked(true);
            accessibilityEvent.getText().add("\u5df2\u9009\u4e2d");
        }
    }

    public void p(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.q.setVisibility(0);
            this.q.setText(str);
            this.q.setContentDescription(str);
            return;
        }
        this.q.setVisibility(8);
    }

    public void r(View.OnClickListener onClickListener) {
        this.f2984g.setOnClickListener(new b(this, onClickListener));
    }

    public void s(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2986i.setVisibility(0);
            this.f2986i.setText(str);
            this.f2986i.setContentDescription(str);
            return;
        }
        this.f2986i.setVisibility(8);
    }

    public void t(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2987j.setVisibility(0);
            this.f2987j.setText(str);
            return;
        }
        this.f2987j.setText("");
        this.f2987j.setVisibility(8);
    }

    public void u(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2988k.setVisibility(0);
            this.f2988k.setText(str);
            return;
        }
        this.f2988k.setVisibility(8);
    }

    public void v(String str, boolean z, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            this.p.setContentDescription("");
            this.p.setVisibility(8);
            return;
        }
        this.p.setVisibility(0);
        this.p.setText(str);
        this.p.setContentDescription(str);
        if (z) {
            this.p.setClickable(true);
            Context context = getContext();
            if (context != null) {
                this.p.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow), (Drawable) null);
                this.p.setCompoundDrawablePadding(DpiUtil.dip2px(context, 2.0f));
                this.p.setOnClickListener(new a(this, onClickListener));
                return;
            }
            return;
        }
        this.p.setClickable(false);
        this.p.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void w() {
        j0.b(this.p);
    }

    public void x(String str) {
        y(str, "");
    }

    public void y(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f2991n, str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.f2991n.setContentDescription(str2);
            return;
        }
        this.f2991n.setVisibility(8);
    }

    public void z(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.f2991n.setOnClickListener(onClickListener);
        }
    }

    public CashierItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        j(context);
    }
}
