package com.jd.aips.widget.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

/* loaded from: classes12.dex */
public class CircleSprite extends ShapeSprite {
    @Override // com.jd.aips.widget.spinkit.sprite.ShapeSprite
    public void drawShape(Canvas canvas, Paint paint) {
        if (getDrawBounds() != null) {
            canvas.drawCircle(getDrawBounds().centerX(), getDrawBounds().centerY(), Math.min(getDrawBounds().width(), getDrawBounds().height()) / 2, paint);
        }
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public ValueAnimator onCreateAnimation() {
        return null;
    }
}
