package com.jd.lib.cashier.sdk.core.ui.fontsize;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.lib.cashier.sdk.core.utils.l;
import com.jd.lib.cashier.sdk.core.utils.m;

/* loaded from: classes14.dex */
public class FontTextView extends AppCompatTextView {
    public FontTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        try {
            float textSize = getTextSize();
            if (!TextUtils.equals("2", m.f().j())) {
                textSize = l.a(context, textSize);
            }
            if (textSize < 14.0f || textSize > 22.0f) {
                return;
            }
            setTextSize(textSize);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public FontTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }
}
