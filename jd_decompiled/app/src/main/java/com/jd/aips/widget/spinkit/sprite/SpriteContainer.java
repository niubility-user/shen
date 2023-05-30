package com.jd.aips.widget.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.jd.aips.widget.spinkit.animation.AnimationUtils;

/* loaded from: classes12.dex */
public abstract class SpriteContainer extends Sprite {
    private Sprite[] t = onCreateChild();
    private int u;

    public SpriteContainer() {
        a();
        onChildCreated(this.t);
    }

    private void a() {
        Sprite[] spriteArr = this.t;
        if (spriteArr != null) {
            for (Sprite sprite : spriteArr) {
                sprite.setCallback(this);
            }
        }
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    protected void a(Canvas canvas) {
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawChild(canvas);
    }

    public void drawChild(Canvas canvas) {
        Sprite[] spriteArr = this.t;
        if (spriteArr != null) {
            for (Sprite sprite : spriteArr) {
                int save = canvas.save();
                sprite.draw(canvas);
                canvas.restoreToCount(save);
            }
        }
    }

    public Sprite getChildAt(int i2) {
        Sprite[] spriteArr = this.t;
        if (spriteArr == null) {
            return null;
        }
        return spriteArr[i2];
    }

    public int getChildCount() {
        Sprite[] spriteArr = this.t;
        if (spriteArr == null) {
            return 0;
        }
        return spriteArr.length;
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public int getColor() {
        return this.u;
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public boolean isRunning() {
        return AnimationUtils.isRunning(this.t) || super.isRunning();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        for (Sprite sprite : this.t) {
            sprite.setBounds(rect);
        }
    }

    public void onChildCreated(Sprite... spriteArr) {
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public ValueAnimator onCreateAnimation() {
        return null;
    }

    public abstract Sprite[] onCreateChild();

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite
    public void setColor(int i2) {
        this.u = i2;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).setColor(i2);
        }
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public void start() {
        super.start();
        AnimationUtils.start(this.t);
    }

    @Override // com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Animatable
    public void stop() {
        super.stop();
        AnimationUtils.stop(this.t);
    }
}
