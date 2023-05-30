package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.d.b.a;

/* loaded from: classes14.dex */
public class CashierSupervisionItemView extends FrameLayout {
    private static final String x = CashierSupervisionItemView.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private LinearLayout f2995g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f2996h;

    /* renamed from: i  reason: collision with root package name */
    private TextView f2997i;

    /* renamed from: j  reason: collision with root package name */
    private TextView f2998j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f2999k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f3000l;

    /* renamed from: m  reason: collision with root package name */
    private TextView f3001m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f3002n;
    private ImageView o;
    private CheckBox p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private RelativeLayout u;
    private LinearLayout v;
    private c w;

    /* loaded from: classes14.dex */
    class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f3003j;

        a(CashierSupervisionItemView cashierSupervisionItemView, View.OnClickListener onClickListener) {
            this.f3003j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f3003j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ View.OnClickListener f3004j;

        b(CashierSupervisionItemView cashierSupervisionItemView, View.OnClickListener onClickListener) {
            this.f3004j = onClickListener;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(View view) {
            View.OnClickListener onClickListener = this.f3004j;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    /* loaded from: classes14.dex */
    public interface c {
        void a();

        void b();
    }

    public CashierSupervisionItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        k(context, attributeSet);
    }

    private void k(Context context, AttributeSet attributeSet) {
        l(context);
    }

    private void l(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_item_pay_supervision_view, (ViewGroup) this, true);
        this.f2995g = (LinearLayout) inflate.findViewById(R.id.pay_item_view);
        this.f2996h = (ImageView) inflate.findViewById(R.id.pay_icon);
        this.f2997i = (TextView) inflate.findViewById(R.id.pay_name);
        this.f2998j = (TextView) inflate.findViewById(R.id.pay_name_desc);
        this.f2999k = (ImageView) inflate.findViewById(R.id.list_icon_1);
        this.f3000l = (ImageView) inflate.findViewById(R.id.list_icon_2);
        this.f3001m = (TextView) inflate.findViewById(R.id.more_pay_tips);
        this.f3002n = (TextView) inflate.findViewById(R.id.more_info_tip);
        this.o = (ImageView) inflate.findViewById(R.id.to_bank_arrow);
        this.p = (CheckBox) inflate.findViewById(R.id.pay_check);
        this.q = (ImageView) inflate.findViewById(R.id.icon_i);
        this.r = (TextView) inflate.findViewById(R.id.pay_channel_available_balance);
        this.s = (TextView) inflate.findViewById(R.id.pay_channel_investor_doc);
        this.t = (TextView) inflate.findViewById(R.id.pay_channel_unavailable_tip);
        this.u = (RelativeLayout) inflate.findViewById(R.id.pay_channel_expand_root);
        this.v = (LinearLayout) inflate.findViewById(R.id.coupon_tip_root);
    }

    private void p() {
        this.f2995g.setClickable(false);
        this.p.setEnabled(false);
        this.p.setClickable(false);
        this.f2999k.setClickable(false);
        this.f3000l.setClickable(false);
        this.q.setClickable(false);
        this.f2996h.setAlpha(0.3f);
        if (this.f2999k.getVisibility() == 0) {
            this.f2999k.setAlpha(0.3f);
        }
        if (this.f3000l.getVisibility() == 0) {
            this.f3000l.setAlpha(0.3f);
        }
        this.f2998j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
        this.f3002n.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
        this.f2997i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
        this.r.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
        this.s.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
        this.t.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_555353));
    }

    private void q() {
        this.f2995g.setClickable(true);
        this.p.setEnabled(true);
        this.p.setClickable(false);
        this.f2999k.setClickable(true);
        this.f3000l.setClickable(true);
        this.q.setClickable(true);
        this.f2996h.setAlpha(1.0f);
        if (this.f2999k.getVisibility() == 0) {
            this.f2999k.setAlpha(1.0f);
        }
        if (this.f3000l.getVisibility() == 0) {
            this.f3000l.setAlpha(1.0f);
        }
        this.f2998j.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.f3002n.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.f2997i.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A, JDDarkUtil.COLOR_ECECEC));
        this.r.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_ECECEC));
        this.s.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_ECECEC));
        this.t.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080, JDDarkUtil.COLOR_ECECEC));
    }

    public void A(String str) {
        B(str, "");
    }

    public void B(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f2999k, str);
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.f2999k.setContentDescription(str2);
            return;
        }
        this.f2999k.setVisibility(8);
    }

    public void C() {
        this.f2999k.setVisibility(8);
    }

    public void D() {
        this.f2999k.setVisibility(8);
        this.f3000l.setVisibility(8);
    }

    public void E(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.c(this.f3000l, str);
        } else {
            this.f3000l.setVisibility(8);
        }
    }

    public void F() {
        this.f3000l.setVisibility(8);
    }

    public void G(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2996h.setVisibility(0);
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 3;
            k.a(R.id.lib_cashier_sdk_payment_icon_tag, str, this.f2996h, imageLoaderOptions, true, null);
            return;
        }
        this.f2996h.setVisibility(4);
    }

    public void H() {
        j0.b(this.f3002n);
    }

    public void I(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2998j.setVisibility(0);
            this.f2998j.setText(str);
            this.f2998j.setContentDescription(str);
            return;
        }
        this.f2998j.setVisibility(8);
    }

    public void J(String str) {
        if (str == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            j0.b(this.t);
            return;
        }
        this.t.setText(str);
        this.t.setContentDescription(str);
        j0.d(this.t);
    }

    public void K(boolean z) {
        LinearLayout linearLayout = this.v;
        if (linearLayout == null) {
            return;
        }
        if (z) {
            j0.d(linearLayout);
        } else {
            j0.b(linearLayout);
        }
    }

    public void L(a.b bVar) {
        if (JDDarkUtil.isDarkMode()) {
            this.f2995g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_bottom_corner_dark_bg);
        } else {
            this.f2995g.setBackgroundResource(R.drawable.lib_cashier_sdk_regulator_top_bottom_corner_bg);
        }
    }

    public void M(String str) {
        this.f2995g.setFocusable(true);
        if (Build.VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(1);
        }
        this.f2995g.setContentDescription(str);
    }

    public void N() {
        this.f3002n.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
    }

    public void O() {
        this.o.setImageResource(R.drawable.lib_cashier_sdk_pay_channel_right_arrow);
        this.o.setVisibility(0);
        c cVar = this.w;
        if (cVar != null) {
            cVar.b();
        }
    }

    public void P() {
        this.p.setVisibility(0);
        c cVar = this.w;
        if (cVar != null) {
            cVar.a();
        }
    }

    public void Q(boolean z, String str, View.OnClickListener onClickListener) {
        ImageView imageView = this.q;
        if (imageView == null) {
            return;
        }
        if (z) {
            j0.d(imageView);
        } else {
            j0.b(imageView);
        }
        if (onClickListener != null) {
            this.q.setOnClickListener(onClickListener);
        } else {
            this.q.setOnClickListener(null);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.q.setContentDescription(str);
    }

    public void a(boolean z) {
        this.p.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_dark : R.drawable.lib_cashier_sdk_button_i_new);
        if (z) {
            p();
        } else {
            q();
        }
    }

    public CheckBox b() {
        return this.p;
    }

    public boolean c() {
        return g() || j0.a(this.f2998j);
    }

    public ImageView d() {
        return this.q;
    }

    public String e(String str, boolean z) {
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.f2997i.getContentDescription());
        String str6 = "";
        sb.append("");
        String sb2 = sb.toString();
        if (z) {
            sb2 = com.jd.lib.cashier.sdk.core.utils.c.b(sb2);
        }
        if (this.f2998j.getVisibility() != 0 || TextUtils.isEmpty(this.f2998j.getContentDescription())) {
            str2 = "";
        } else {
            str2 = ((Object) this.f2998j.getContentDescription()) + "";
        }
        if (this.f2999k.getVisibility() != 0 || TextUtils.isEmpty(this.f2999k.getContentDescription())) {
            str3 = "";
        } else {
            str3 = ((Object) this.f2999k.getContentDescription()) + "";
        }
        if (this.f3001m.getVisibility() != 0 || TextUtils.isEmpty(this.f3001m.getContentDescription())) {
            str4 = "";
        } else {
            str4 = ((Object) this.f3001m.getContentDescription()) + "";
        }
        if (this.f3002n.getVisibility() != 0 || TextUtils.isEmpty(this.f3002n.getContentDescription())) {
            str5 = "";
        } else {
            str5 = ((Object) this.f3002n.getContentDescription()) + "";
        }
        if (this.p.getVisibility() == 0) {
            str6 = this.p.isChecked() ? "\u5df2\u9009\u4e2d" : "\u672a\u9009\u4e2d";
        }
        if (!"3".equals(str) && !"7".equals(str)) {
            return (str6 + sb2 + str2 + str3 + str4 + str5).trim();
        }
        return (sb2 + "\u5de5\u5177\u4e0d\u53ef\u7528" + str2).trim();
    }

    public TextView f() {
        return this.f2997i;
    }

    public boolean g() {
        return j0.a(this.f3001m) || j0.a(this.f3002n);
    }

    public void h() {
        this.f2999k.setVisibility(8);
        this.f3000l.setVisibility(8);
        this.f3001m.setVisibility(8);
    }

    public void i() {
        this.o.setVisibility(8);
    }

    public void j() {
        this.p.setVisibility(8);
    }

    public void m(boolean z) {
        this.p.setChecked(z);
    }

    public void n(boolean z) {
        if (z) {
            this.u.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bottom_corner_grey_dark_bg : R.drawable.lib_cashier_sdk_bottom_corner_grey_bg);
            j0.d(this.u);
            return;
        }
        j0.b(this.u);
    }

    public void o(int i2) {
        if (this.u == null) {
            return;
        }
        DisplayMetrics i3 = y.i();
        ViewGroup.LayoutParams layoutParams = this.u.getLayoutParams();
        if (layoutParams.height == i2) {
            return;
        }
        if (i3 == null) {
            layoutParams.height = i2;
            this.u.setLayoutParams(layoutParams);
            return;
        }
        layoutParams.height = (int) TypedValue.applyDimension(0, i2, i3);
        this.u.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        r.b(x, "AccessibilityEvent = " + accessibilityEvent.toString());
        if (4 == accessibilityEvent.getEventType()) {
            accessibilityEvent.setChecked(true);
            accessibilityEvent.getText().add("\u5df2\u9009\u4e2d");
        }
    }

    public void r(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f3002n.setVisibility(0);
            this.f3002n.setText(str);
            this.f3002n.setContentDescription(str);
            return;
        }
        this.f3002n.setVisibility(8);
    }

    public void s(View.OnClickListener onClickListener) {
        this.f2995g.setOnClickListener(new b(this, onClickListener));
    }

    public void t(String str) {
        if (this.r == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            j0.b(this.r);
            return;
        }
        this.r.setText(str);
        this.r.setContentDescription(str);
        j0.d(this.r);
    }

    public void u() {
        TextView textView = this.r;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void v(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2997i.setVisibility(0);
            this.f2997i.setText(str);
            this.f2997i.setContentDescription(str);
            return;
        }
        this.f2997i.setVisibility(8);
    }

    public void w(String str, boolean z, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            this.f3001m.setContentDescription("");
            this.f3001m.setVisibility(8);
            return;
        }
        this.f3001m.setVisibility(0);
        this.f3001m.setText(str);
        this.f3001m.setContentDescription(str);
        if (z) {
            this.f3001m.setClickable(true);
            Context context = getContext();
            if (context != null) {
                this.f3001m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.lib_cashier_sdk_pay_channel_coupon_arrow), (Drawable) null);
                this.f3001m.setCompoundDrawablePadding(DpiUtil.dip2px(context, 2.0f));
                this.f3001m.setOnClickListener(new a(this, onClickListener));
                return;
            }
            return;
        }
        this.f3001m.setClickable(false);
        this.f3001m.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void x() {
        j0.b(this.f3001m);
    }

    public void y(String str) {
        if (this.s == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            j0.b(this.s);
            return;
        }
        this.s.setText(str);
        this.s.setContentDescription(str);
        j0.d(this.s);
    }

    public void z() {
        TextView textView = this.s;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public CashierSupervisionItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        k(context, attributeSet);
    }
}
