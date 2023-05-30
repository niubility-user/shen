package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.text.style.ImageSpan;
import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes15.dex */
public abstract class PdClickableImageSpan extends ImageSpan {
    public PdClickableImageSpan(@NonNull Context context, int i2, int i3) {
        super(context, i2, i3);
    }

    public abstract void onClick(View view);
}
