package com.jd.dynamic.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes13.dex */
public class SpanView extends AppCompatTextView {
    public String newLine;
    public String onClick;
    public String selectedSrc;
    public String src;
    public String subtype;
    public float textColor;
    public float textSize;
    public String type;

    public SpanView(Context context) {
        super(context);
        this.textColor = Float.NaN;
        this.textSize = -1.0f;
    }

    public SpanView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textColor = Float.NaN;
        this.textSize = -1.0f;
    }

    public SpanView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.textColor = Float.NaN;
        this.textSize = -1.0f;
    }

    public void onParseFinish() {
        if (getParent() instanceof RichTextViewContainer) {
            ((RichTextViewContainer) getParent()).parseAttribute();
        }
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        this.textColor = i2;
        super.setTextColor(i2);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextSize(int i2, float f2) {
        this.textSize = f2;
        super.setTextSize(i2, f2);
    }
}
