package com.jd.lib.cashier.sdk.pay.adapter;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
final class c extends RecyclerView.ViewHolder {
    private final TextView a;
    private final TextView b;

    /* renamed from: c  reason: collision with root package name */
    private final TextView f3856c;
    private final TextView d;

    /* renamed from: e  reason: collision with root package name */
    private final View f3857e;

    /* renamed from: f  reason: collision with root package name */
    private final CheckBox f3858f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    private final View f3859g;

    public c(@NotNull View view) {
        super(view);
        this.f3859g = view;
        View findViewById = view.findViewById(R.id.tv_disable_item_pay_coupon_type);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "couponItemView.findViewB\u2026ble_item_pay_coupon_type)");
        this.a = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.tv_disable_item_pay_coupon_title_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "couponItemView.findViewB\u2026em_pay_coupon_title_name)");
        this.b = (TextView) findViewById2;
        View findViewById3 = view.findViewById(R.id.tv_disable_item_pay_coupon_sub_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "couponItemView.findViewB\u2026tem_pay_coupon_sub_title)");
        this.f3856c = (TextView) findViewById3;
        View findViewById4 = view.findViewById(R.id.tv_disable_item_pay_coupon_extra_info);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "couponItemView.findViewB\u2026em_pay_coupon_extra_info)");
        this.d = (TextView) findViewById4;
        View findViewById5 = view.findViewById(R.id.line_disable_item_pay_coupon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "couponItemView.findViewB\u2026_disable_item_pay_coupon)");
        this.f3857e = findViewById5;
        View findViewById6 = view.findViewById(R.id.check_disable_item_pay_coupon_selector);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "couponItemView.findViewB\u2026item_pay_coupon_selector)");
        this.f3858f = (CheckBox) findViewById6;
    }

    public final void b(@NotNull String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.setVisibility(4);
            this.a.setText("");
            return;
        }
        this.a.setText(str);
        this.a.setVisibility(0);
    }

    public final void c(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.d);
            return;
        }
        this.d.setText(str);
        j0.d(this.d);
    }

    public final void d(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.f3856c);
            return;
        }
        this.f3856c.setText(str);
        j0.d(this.f3856c);
    }

    public final void e(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.b);
            return;
        }
        this.b.setText(str);
        j0.d(this.b);
    }

    public final void f() {
        this.a.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bg_pay_coupon_disable_type_dark : R.drawable.lib_cashier_sdk_bg_pay_coupon_disable_type);
        this.a.setTextColor(JDDarkUtil.isDarkMode() ? JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC) : Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        this.b.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
        this.f3856c.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
        this.d.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_CCCCCC));
        this.f3857e.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        this.f3858f.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_03_dark : R.drawable.lib_cashier_sdk_button_i_new_03);
    }

    public final void g() {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3859g.setImportantForAccessibility(2);
        } else {
            this.f3859g.setContentDescription(null);
        }
    }

    public final void h(int i2, int i3) {
        this.f3857e.setVisibility(i2 == i3 + (-1) ? 8 : 0);
    }
}
