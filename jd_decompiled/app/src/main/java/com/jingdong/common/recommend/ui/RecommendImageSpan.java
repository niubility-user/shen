package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.jingdong.common.search.view.CenterImageSpan;

/* loaded from: classes6.dex */
public class RecommendImageSpan extends CenterImageSpan {
    public int endPo;
    public int startPo;

    public RecommendImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        super(context, bitmap);
        this.startPo = 0;
        this.endPo = 0;
    }

    public void setStartPo(int i2, int i3) {
        this.startPo = i2;
        this.endPo = i3;
    }

    public RecommendImageSpan(@NonNull Drawable drawable) {
        super(drawable);
        this.startPo = 0;
        this.endPo = 0;
    }
}
