package com.jingdong.app.util.image.display;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

@Deprecated
/* loaded from: classes4.dex */
public class JDFadeInBitmapDisplayer implements JDBitmapDisplayer {
    private final boolean animateFromDisk;
    private final boolean animateFromMemory;
    private final boolean animateFromNetwork;
    public final int durationMillis;

    public JDFadeInBitmapDisplayer(int i2) {
        this(i2, true, true, true);
    }

    public JDFadeInBitmapDisplayer(int i2, boolean z, boolean z2, boolean z3) {
        this.durationMillis = i2;
        this.animateFromNetwork = z;
        this.animateFromDisk = z2;
        this.animateFromMemory = z3;
    }

    public static void animate(View view, int i2) {
        if (view != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(i2);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            view.startAnimation(alphaAnimation);
        }
    }

    @Override // com.jingdong.app.util.image.display.JDBitmapDisplayer
    public void display(Bitmap bitmap, ImageView imageView, int i2) {
        if (imageView == null || bitmap == null) {
            return;
        }
        imageView.setImageBitmap(bitmap);
        if ((this.animateFromNetwork && i2 == 0) || ((this.animateFromDisk && i2 == 1) || (this.animateFromMemory && i2 == 2))) {
            animate(imageView, this.durationMillis);
        }
    }
}
