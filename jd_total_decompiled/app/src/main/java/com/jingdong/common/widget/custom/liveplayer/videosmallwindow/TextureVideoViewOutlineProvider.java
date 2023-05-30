package com.jingdong.common.widget.custom.liveplayer.videosmallwindow;

import android.graphics.Outline;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 21)
/* loaded from: classes12.dex */
public class TextureVideoViewOutlineProvider extends ViewOutlineProvider {
    private float mRadius;

    public TextureVideoViewOutlineProvider(float f2) {
        this.mRadius = f2;
    }

    @Override // android.view.ViewOutlineProvider
    @RequiresApi(api = 21)
    public void getOutline(View view, Outline outline) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect(0, 0, (rect.right - rect.left) - 0, (rect.bottom - rect.top) - 0);
        outline.setRoundRect(rect2, this.mRadius);
        outline.setRoundRect(rect2, this.mRadius);
    }
}
