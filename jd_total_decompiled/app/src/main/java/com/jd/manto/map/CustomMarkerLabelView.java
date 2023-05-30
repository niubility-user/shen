package com.jd.manto.map;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;

/* loaded from: classes17.dex */
final class CustomMarkerLabelView extends FrameLayout {
    private float anchorX;
    private float anchorY;
    private TextView tvTitle;
    private int useHeight;
    private int useWidth;
    public int x;
    public int y;

    public CustomMarkerLabelView(Context context) {
        super(context);
        this.anchorX = 0.0f;
        this.anchorY = 0.0f;
        this.x = 0;
        this.y = 0;
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        TextView textView = new TextView(context);
        this.tvTitle = textView;
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        addView(this.tvTitle);
    }

    public final float getAnchorX() {
        return this.anchorX;
    }

    public final float getAnchorY() {
        return this.anchorY;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getChildAt(0).getLayoutParams();
        int i6 = this.x;
        if (i6 >= 0 && this.y >= 0) {
            layoutParams.gravity = 85;
        } else if (i6 >= 0 && this.y <= 0) {
            layoutParams.gravity = 53;
        } else if (i6 <= 0 && this.y >= 0) {
            layoutParams.gravity = 83;
        } else if (i6 <= 0 && this.y <= 0) {
            layoutParams.gravity = 51;
        }
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int i4 = -measuredWidth;
        int i5 = this.x;
        if (i4 < i5 && i5 < 0) {
            this.useWidth = measuredWidth;
            this.anchorX = Math.abs(i5) / (measuredWidth * 1.0f);
        } else if (i5 >= 0) {
            this.useWidth = measuredWidth + Math.abs(i5);
            this.anchorX = 0.0f;
        } else {
            this.useWidth = Math.abs(i5);
            this.anchorX = 1.0f;
        }
        int i6 = -measuredHeight;
        int i7 = this.y;
        if (i6 < i7 && i7 < 0) {
            this.useHeight = measuredHeight;
            this.anchorY = Math.abs(i7) / (measuredHeight * 1.0f);
        } else if (i7 >= 0) {
            this.useHeight = measuredHeight + Math.abs(i7);
            this.anchorY = 0.0f;
        } else {
            this.useHeight = Math.abs(i7);
            this.anchorY = 1.0f;
        }
        setMeasuredDimension(this.useWidth, this.useHeight);
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

    public final void setText(String str) {
        this.tvTitle.setText(str);
    }

    public final void setTextColor(int i2) {
        this.tvTitle.setTextColor(i2);
    }

    public final void setTextPadding(int i2) {
        this.tvTitle.setPadding(i2, i2, i2, i2);
    }

    public final void setTextSize(int i2) {
        this.tvTitle.setTextSize(i2);
    }

    public final void setX(int i2) {
        this.x = i2;
    }

    public final void setY(int i2) {
        this.y = i2;
    }
}
