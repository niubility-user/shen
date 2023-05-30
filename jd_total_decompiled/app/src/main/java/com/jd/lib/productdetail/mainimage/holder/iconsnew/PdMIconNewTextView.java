package com.jd.lib.productdetail.mainimage.holder.iconsnew;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class PdMIconNewTextView extends AppCompatTextView {
    public PdMIconNewTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (getPaint() != null) {
            try {
                getPaint().setShader(new LinearGradient(0.0f, 0.0f, getWidth(), getHeight(), Color.parseColor("#E6B08A"), Color.parseColor("#FFE6CC"), Shader.TileMode.CLAMP));
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }
}
