package com.jd.manto.map;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;

/* loaded from: classes17.dex */
final class CustomMarkerInfoView extends LinearLayout {
    final TextView tvTitle;

    public CustomMarkerInfoView(Context context) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        setOrientation(1);
        setGravity(17);
        TextView textView = new TextView(context);
        this.tvTitle = textView;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        addView(textView);
    }

    public final void setGradient(int i2, int i3, int i4, int i5) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setStroke(i3, i4);
        gradientDrawable.setColor(i5);
        this.tvTitle.setBackgroundDrawable(gradientDrawable);
    }

    public final void setGravity(String str) {
        if (str.equals("left")) {
            this.tvTitle.setGravity(3);
        } else if (str.equals("right")) {
            this.tvTitle.setGravity(5);
        } else {
            str.equals(DYConstants.DY_CENTER);
            this.tvTitle.setGravity(17);
        }
    }
}
