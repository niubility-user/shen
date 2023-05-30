package com.jingdong.common.XView2.common;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 21)
/* loaded from: classes5.dex */
public class XView2OutlineProvider extends ViewOutlineProvider {
    private float mRadius;

    public XView2OutlineProvider(float f2) {
        this.mRadius = f2;
    }

    @Override // android.view.ViewOutlineProvider
    @RequiresApi(api = 21)
    public void getOutline(View view, Outline outline) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        outline.setRoundRect(new Rect(0, 0, (rect.right - rect.left) - 0, (rect.bottom - rect.top) - 0), this.mRadius);
    }
}
