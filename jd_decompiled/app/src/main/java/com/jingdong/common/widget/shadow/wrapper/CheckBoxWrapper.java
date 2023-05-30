package com.jingdong.common.widget.shadow.wrapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes12.dex */
public class CheckBoxWrapper extends CheckBox {
    public CheckBoxWrapper(Context context) {
        this(context, null);
    }

    public CheckBoxWrapper(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckBoxWrapper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setFocusable(true);
        setClickable(true);
    }
}
