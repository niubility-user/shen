package com.jd.lib.cashier.sdk.core.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.widget.b;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.k;

/* loaded from: classes14.dex */
public class CashierBNewCardItemView extends FrameLayout implements Checkable, b {

    /* renamed from: g  reason: collision with root package name */
    LinearLayout f2962g;

    /* renamed from: h  reason: collision with root package name */
    LinearLayout f2963h;

    /* renamed from: i  reason: collision with root package name */
    ImageView f2964i;

    /* renamed from: j  reason: collision with root package name */
    TextView f2965j;

    /* renamed from: k  reason: collision with root package name */
    private LinearLayout f2966k;

    /* renamed from: l  reason: collision with root package name */
    protected TextView f2967l;

    /* renamed from: m  reason: collision with root package name */
    private ImageView f2968m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f2969n;

    public CashierBNewCardItemView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2969n = false;
        e(context);
    }

    private void e(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_b_item_new_card_view, (ViewGroup) this, true);
        this.f2962g = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_container_new_card_item_view);
        this.f2963h = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_container_new_card_item_root_view);
        this.f2964i = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_new_card_pay_icon);
        this.f2965j = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_new_card_pay_name);
        this.f2966k = (LinearLayout) inflate.findViewById(R.id.lib_cashier_sdk_b_new_card_item_flag_container);
        this.f2967l = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_b_new_card_item_body_flag);
        this.f2968m = (ImageView) inflate.findViewById(R.id.lib_cashier_sdk_b_new_card_item_bottom_flag);
    }

    private void i() {
        h(R.drawable.lib_cashier_sdk_pay_plan_item_disable_bg);
    }

    private void j() {
        TextView textView = this.f2965j;
        if (textView == null) {
            return;
        }
        textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_757575));
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.b
    public void a(boolean z) {
        TextView textView = this.f2967l;
        if (textView == null || this.f2968m == null) {
            return;
        }
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        if (z) {
            this.f2967l.setBackgroundResource(R.drawable.lib_cashier_sdk_bg_pay_item_view_flag_select);
            this.f2968m.setImageResource(R.drawable.lib_cashier_sdk_pay_plan_item_flag_01);
            return;
        }
        this.f2967l.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
        this.f2967l.setBackgroundResource(R.drawable.lib_cashier_sdk_bg_pay_item_view_flag_normal);
        this.f2968m.setImageResource(R.drawable.lib_cashier_sdk_pay_plan_item_flag_00);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.b
    public void b(boolean z) {
        if (z) {
            h(R.drawable.lib_cashier_sdk_pay_plan_item_select_bg);
        } else {
            h(R.drawable.lib_cashier_sdk_pay_plan_item_normal_bg);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.b
    public void c(boolean z) {
        TextView textView = this.f2965j;
        if (textView == null) {
            return;
        }
        if (z) {
            textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
        } else {
            textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_757575));
        }
    }

    public void d(String str) {
        if (this.f2967l != null) {
            if (!TextUtils.isEmpty(str)) {
                this.f2966k.setVisibility(0);
                this.f2967l.setText(str);
                this.f2967l.setContentDescription(str);
                return;
            }
            this.f2966k.setVisibility(8);
            this.f2967l.setText("");
        }
    }

    public void f(String str) {
        if (this.f2965j == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f2965j.setVisibility(0);
            this.f2965j.setText(str);
            this.f2965j.setContentDescription(str);
            return;
        }
        this.f2965j.setVisibility(8);
    }

    public void g(String str) {
        if (this.f2964i == null) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            this.f2964i.setVisibility(0);
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 3;
            k.a(R.id.lib_cashier_sdk_payment_icon_tag, str, this.f2964i, imageLoaderOptions, true, null);
            return;
        }
        this.f2964i.setVisibility(4);
    }

    protected void h(@DrawableRes int i2) {
        LinearLayout linearLayout = this.f2962g;
        if (linearLayout != null) {
            linearLayout.setBackgroundResource(i2);
        }
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2969n;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        b(z);
        c(z);
        a(z);
        if (this.f2969n == z) {
            return;
        }
        this.f2969n = z;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            return;
        }
        i();
        j();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        LinearLayout linearLayout = this.f2963h;
        if (linearLayout != null) {
            linearLayout.setPadding(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f2969n);
    }

    public CashierBNewCardItemView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2969n = false;
        e(context);
    }
}
