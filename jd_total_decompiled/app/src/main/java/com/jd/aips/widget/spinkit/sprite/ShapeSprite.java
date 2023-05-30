package com.jd.aips.widget.spinkit.sprite;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;

/* loaded from: classes12.dex */
public abstract class ShapeSprite extends Sprite {
    private Paint t;
    private int u;
    private int v;

    public ShapeSprite() {
        setColor(-1);
        Paint paint = new Paint();
        this.t = paint;
        paint.setAntiAlias(true);
        this.t.setColor(this.u);
    }

    private void a() {
        int alpha = getAlpha();
        int i2 = this.v;
        this.u = ((((i2 >>> 24) * (alpha + (alpha >> 7))) >> 8) << 24) | ((i2 << 8) >>> 8);
    }

    public abstract void drawShape(Canvas canvas, Paint paint);

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public int getColor() {
        return this.v;
    }

    public int getUseColor() {
        return this.u;
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        super.setAlpha(i2);
        a();
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public void setColor(int i2) {
        this.v = i2;
        a();
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.t.setColorFilter(colorFilter);
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    protected final void a(Canvas canvas) {
        this.t.setColor(this.u);
        drawShape(canvas, this.t);
    }
}
