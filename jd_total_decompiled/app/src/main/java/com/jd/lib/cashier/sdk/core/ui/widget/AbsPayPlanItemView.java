package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public abstract class AbsPayPlanItemView extends FrameLayout implements Checkable, b {
    public static final String o = AbsPayPlanItemView.class.getSimpleName();

    /* renamed from: g */
    private ViewGroup f3010g;

    /* renamed from: h */
    protected LinearLayout f3011h;

    /* renamed from: i */
    protected TextView f3012i;

    /* renamed from: j */
    protected TextView f3013j;

    /* renamed from: k */
    protected TextView f3014k;

    /* renamed from: l */
    protected ImageView f3015l;

    /* renamed from: m */
    private LinearLayout f3016m;

    /* renamed from: n */
    private boolean f3017n;

    public AbsPayPlanItemView(Context context) {
        super(context);
        this.f3017n = false;
        h(context);
    }

    private void h(Context context) {
        View inflate = LayoutInflater.from(context).inflate(g(), (ViewGroup) this, false);
        this.f3010g = (ViewGroup) inflate.findViewById(R.id.container_plan_item_view);
        this.f3011h = (LinearLayout) inflate.findViewById(R.id.lib_cashier_container_total_root);
        this.f3012i = (TextView) inflate.findViewById(R.id.tv_check_top_info);
        this.f3013j = (TextView) inflate.findViewById(R.id.tv_check_bottom_info);
        this.f3016m = (LinearLayout) inflate.findViewById(R.id.lib_cashier_plan_item_flag_container);
        this.f3014k = (TextView) inflate.findViewById(R.id.lib_cashier_tv_plan_item_body_flag);
        this.f3015l = (ImageView) inflate.findViewById(R.id.lib_cashier_plan_item_bottom_flag);
        addView(inflate);
    }

    private void k() {
        j(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_pay_plan_item_disable_bg_dark : R.drawable.lib_cashier_sdk_pay_plan_item_disable_bg);
    }

    private void l() {
        TextView textView = this.f3012i;
        if (textView == null || this.f3013j == null) {
            return;
        }
        textView.setTextColor(JDDarkUtil.getDarkColor("#D4D4D4", JDDarkUtil.COLOR_848383));
        this.f3013j.setTextColor(JDDarkUtil.getDarkColor("#D4D4D4", JDDarkUtil.COLOR_848383));
    }

    public void d(String str) {
        if (this.f3014k != null) {
            if (!TextUtils.isEmpty(str)) {
                this.f3016m.setVisibility(0);
                this.f3014k.setText(str);
                this.f3014k.setContentDescription(str);
                return;
            }
            this.f3016m.setVisibility(8);
            this.f3014k.setText("");
        }
    }

    public void e(String str) {
        if (this.f3012i == null || this.f3013j == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f3013j.setVisibility(0);
            m0.a(this.f3013j, (byte) 3);
            this.f3013j.setText(str);
            this.f3013j.setContentDescription(str);
            return;
        }
        this.f3013j.setVisibility(8);
        this.f3013j.setText("");
    }

    public void f(String str) {
        if (this.f3012i == null || this.f3013j == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f3012i.setVisibility(0);
            m0.a(this.f3012i, (byte) 3);
            this.f3012i.setText(str);
            this.f3012i.setContentDescription(str);
            return;
        }
        this.f3012i.setVisibility(8);
        this.f3012i.setText("");
    }

    public abstract int g();

    public void i(int i2, int i3, int i4, int i5) {
        LinearLayout linearLayout = this.f3011h;
        if (linearLayout != null) {
            linearLayout.setPadding(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3017n;
    }

    public void j(@DrawableRes int i2) {
        ViewGroup viewGroup = this.f3010g;
        if (viewGroup != null) {
            viewGroup.setBackgroundResource(i2);
        }
    }

    public void m(boolean z) {
        String str;
        String str2;
        String str3;
        if (!z) {
            setFocusable(false);
            if (Build.VERSION.SDK_INT >= 16) {
                setImportantForAccessibility(2);
                return;
            }
            return;
        }
        setFocusable(true);
        if (Build.VERSION.SDK_INT >= 16) {
            setImportantForAccessibility(1);
        }
        String str4 = "";
        if (this.f3012i.getVisibility() == 0) {
            str = ((Object) this.f3012i.getContentDescription()) + "";
        } else {
            str = "";
        }
        if (this.f3013j.getVisibility() == 0) {
            str2 = ((Object) this.f3013j.getContentDescription()) + "";
        } else {
            str2 = "";
        }
        if (this.f3014k.getVisibility() == 0) {
            str4 = ((Object) this.f3014k.getContentDescription()) + "";
        }
        if (isChecked()) {
            str3 = "\u5df2\u9009\u4e2d" + str + str2 + str4;
        } else {
            str3 = "\u672a\u9009\u4e2d" + str + str2 + str4;
        }
        setContentDescription(str3);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        r.b(o, "onInitializeAccessibilityEvent = " + accessibilityEvent.toString());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        r.b(o, "onInitializeAccessibilityNodeInfo = " + accessibilityNodeInfo.toString());
    }

    @Override // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        r.b(o, "AccessibilityEvent = " + accessibilityEvent.toString());
        if (4 == accessibilityEvent.getEventType()) {
            accessibilityEvent.getText().add("\u5df2\u9009\u4e2d");
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        b(z);
        c(z);
        a(z);
        if (this.f3017n == z) {
            return;
        }
        this.f3017n = z;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        k();
        l();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f3017n);
    }
}
