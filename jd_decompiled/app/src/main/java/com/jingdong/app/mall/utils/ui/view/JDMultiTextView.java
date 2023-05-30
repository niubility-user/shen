package com.jingdong.app.mall.utils.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jingdong.jdsdk.utils.FontsUtil;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes4.dex */
public class JDMultiTextView extends TextView {
    public JDMultiTextView(Context context) {
        this(context, null);
    }

    private void init() {
        FontsUtil.changeTextFont(this);
    }

    public Typeface getMultiTypeFace() {
        return FontsUtil.getTypeFace(getContext());
    }

    public void setMultiTypeFace(int i2) {
        FontsUtil.changeTextFont(this, i2);
    }

    public JDMultiTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Typeface getMultiTypeFace(int i2) {
        return FontsUtil.getTypeFace(getContext(), i2);
    }

    public void setMultiTypeFace() {
        FontsUtil.changeTextFont(this);
    }

    public JDMultiTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
