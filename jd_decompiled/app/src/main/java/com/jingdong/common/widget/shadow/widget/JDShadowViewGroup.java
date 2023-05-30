package com.jingdong.common.widget.shadow.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.common.widget.shadow.ShadowLayout;

/* loaded from: classes12.dex */
public class JDShadowViewGroup<T extends View> extends ShadowLayout {
    public JDShadowViewGroup(Context context) {
        this(context, null);
    }

    public JDShadowViewGroup(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDShadowViewGroup(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
