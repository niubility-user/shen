package com.jd.lib.cashier.sdk.pay.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
final class d extends RecyclerView.ViewHolder {
    private final TextView a;
    private final TextView b;

    /* renamed from: c  reason: collision with root package name */
    private final TextView f3860c;
    private final TextView d;

    /* renamed from: e  reason: collision with root package name */
    private final View f3861e;

    /* renamed from: f  reason: collision with root package name */
    private final CheckBox f3862f;

    /* renamed from: g  reason: collision with root package name */
    private final TextView f3863g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    private final View f3864h;

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Function1 f3865g;

        a(Function1 function1) {
            this.f3865g = function1;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View it) {
            Function1 function1 = this.f3865g;
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            function1.invoke(it);
        }
    }

    public d(@NotNull View view) {
        super(view);
        this.f3864h = view;
        View findViewById = view.findViewById(R.id.tv_item_pay_coupon_type);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "availableItemView.findVi\u2026.tv_item_pay_coupon_type)");
        this.a = (TextView) findViewById;
        View findViewById2 = view.findViewById(R.id.tv_item_pay_coupon_title_name);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "availableItemView.findVi\u2026em_pay_coupon_title_name)");
        this.b = (TextView) findViewById2;
        View findViewById3 = view.findViewById(R.id.tv_item_pay_coupon_sub_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "availableItemView.findVi\u2026tem_pay_coupon_sub_title)");
        this.f3860c = (TextView) findViewById3;
        View findViewById4 = view.findViewById(R.id.tv_item_pay_coupon_extra_info);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "availableItemView.findVi\u2026em_pay_coupon_extra_info)");
        this.d = (TextView) findViewById4;
        View findViewById5 = view.findViewById(R.id.line_item_pay_coupon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "availableItemView.findVi\u2026.id.line_item_pay_coupon)");
        this.f3861e = findViewById5;
        View findViewById6 = view.findViewById(R.id.check_item_pay_coupon_selector);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "availableItemView.findVi\u2026item_pay_coupon_selector)");
        this.f3862f = (CheckBox) findViewById6;
        View findViewById7 = view.findViewById(R.id.check_item_pay_multi_coupon_tag);
        Intrinsics.checkExpressionValueIsNotNull(findViewById7, "availableItemView.findVi\u2026tem_pay_multi_coupon_tag)");
        this.f3863g = (TextView) findViewById7;
    }

    public final void b(@Nullable Boolean bool) {
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            this.f3863g.setVisibility(0);
        } else {
            this.f3863g.setVisibility(8);
        }
    }

    public final void c(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            this.a.setVisibility(4);
            this.a.setText("");
        } else {
            this.a.setText(str);
            this.a.setVisibility(0);
        }
        this.a.setContentDescription(str);
    }

    public final void d(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.d);
            return;
        }
        this.d.setText(str);
        this.d.setContentDescription(str);
        j0.d(this.d);
    }

    public final void e(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.f3860c);
            return;
        }
        this.f3860c.setText(str);
        this.f3860c.setContentDescription(str);
        j0.d(this.f3860c);
    }

    public final void f(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            j0.b(this.b);
            return;
        }
        this.b.setText(str);
        this.b.setContentDescription(str);
        j0.d(this.b);
    }

    public final void g() {
        this.b.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
        this.f3860c.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.d.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_8C8C8C));
        this.f3861e.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
        this.f3862f.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_button_i_new_dark : R.drawable.lib_cashier_sdk_button_i_new);
    }

    public final void h(boolean z) {
        this.f3862f.setChecked(z);
        this.f3862f.setContentDescription(z ? "\u5df2\u9009\u5b9a" : "\u672a\u9009\u5b9a");
    }

    public final void i(@NotNull Function1<? super View, Unit> function1) {
        this.f3864h.setOnClickListener(new a(function1));
    }

    public final void j() {
        String a2 = com.jd.lib.cashier.sdk.pay.adapter.a.a(this.f3862f);
        String a3 = com.jd.lib.cashier.sdk.pay.adapter.a.a(this.b);
        String a4 = com.jd.lib.cashier.sdk.pay.adapter.a.a(this.f3860c);
        String a5 = com.jd.lib.cashier.sdk.pay.adapter.a.a(this.d);
        this.f3864h.setContentDescription(a2 + a3 + a4 + a5);
    }

    public final void k(int i2, int i3) {
        this.f3861e.setVisibility(i2 == i3 + (-1) ? 8 : 0);
    }
}
