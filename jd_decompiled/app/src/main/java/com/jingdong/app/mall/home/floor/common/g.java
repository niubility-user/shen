package com.jingdong.app.mall.home.floor.common;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class g {
    private TextView a;

    public g(Context context, boolean z) {
        if (z) {
            this.a = new GradientTextView(context);
        } else {
            this.a = new TextView(context);
        }
        this.a.setIncludeFontPadding(false);
    }

    public static void k(TextView textView, boolean z) {
        if (textView != null) {
            textView.getPaint().setFakeBoldText(z);
            textView.postInvalidate();
        }
    }

    public static void o(TextView textView, int i2) {
        if (textView != null) {
            textView.setTextSize(0, d.d(i2));
        }
    }

    public TextView a() {
        return this.a;
    }

    public GradientTextView b() {
        TextView textView = this.a;
        com.jingdong.app.mall.home.o.a.f.n(textView);
        return (GradientTextView) textView;
    }

    public g c(boolean z) {
        if (z) {
            this.a.setEllipsize(TextUtils.TruncateAt.END);
        } else {
            this.a.setEllipsize(null);
        }
        return this;
    }

    public g d(int i2) {
        this.a.setGravity(i2);
        return this;
    }

    public g e(int i2) {
        this.a.setMaxEms(i2);
        return this;
    }

    public g f(int i2) {
        this.a.setMaxLines(i2);
        return this;
    }

    public g g(int i2, int i3, int i4, int i5) {
        this.a.setPadding(i2, i3, i4, i5);
        return this;
    }

    public g h() {
        this.a.setSingleLine();
        return this;
    }

    public g i(CharSequence charSequence) {
        this.a.setText(charSequence);
        return this;
    }

    public g j(boolean z) {
        if (z) {
            this.a.getPaint().setFakeBoldText(true);
        }
        return this;
    }

    public g l(@ColorInt int i2) {
        this.a.setTextColor(i2);
        return this;
    }

    public g m(int i2) {
        o(this.a, i2);
        return this;
    }

    public g n(int i2, float f2) {
        this.a.setTextSize(i2, f2);
        return this;
    }

    public g p(Context context) {
        this.a.setTypeface(FontsUtil.getTypeFace(context));
        return this;
    }

    public g q(Context context, int i2) {
        this.a.setTypeface(FontsUtil.getTypeFace(context, i2));
        return this;
    }
}
