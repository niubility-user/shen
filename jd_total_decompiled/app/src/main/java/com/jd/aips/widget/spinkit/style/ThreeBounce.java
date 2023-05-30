package com.jd.aips.widget.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;
import com.jd.aips.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.jd.aips.widget.spinkit.sprite.CircleSprite;
import com.jd.aips.widget.spinkit.sprite.Sprite;
import com.jd.aips.widget.spinkit.sprite.SpriteContainer;

/* loaded from: classes12.dex */
public class ThreeBounce extends SpriteContainer {

    /* loaded from: classes12.dex */
    private class Bounce extends CircleSprite {
        Bounce() {
            setScale(0.0f);
        }

        @Override // com.jd.aips.widget.spinkit.sprite.CircleSprite, com.jd.aips.widget.spinkit.sprite.Sprite
        public ValueAnimator onCreateAnimation() {
            float[] fArr = {0.0f, 0.4f, 0.8f, 1.0f};
            SpriteAnimatorBuilder spriteAnimatorBuilder = new SpriteAnimatorBuilder(this);
            Float valueOf = Float.valueOf(0.0f);
            return spriteAnimatorBuilder.scale(fArr, valueOf, Float.valueOf(1.0f), valueOf, valueOf).duration(1400L).easeInOut(fArr).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.aips.widget.spinkit.sprite.SpriteContainer, com.jd.aips.widget.spinkit.sprite.Sprite, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        Rect clipSquare = clipSquare(rect);
        int width = clipSquare.width() / 8;
        int centerY = clipSquare.centerY() - width;
        int centerY2 = clipSquare.centerY() + width;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            int width2 = ((clipSquare.width() * i2) / 3) + clipSquare.left;
            getChildAt(i2).setDrawBounds(width2, centerY, (width * 2) + width2, centerY2);
        }
    }

    @Override // com.jd.aips.widget.spinkit.sprite.SpriteContainer
    public void onChildCreated(Sprite... spriteArr) {
        super.onChildCreated(spriteArr);
        spriteArr[1].setAnimationDelay(160);
        spriteArr[2].setAnimationDelay(320);
    }

    @Override // com.jd.aips.widget.spinkit.sprite.SpriteContainer
    public Sprite[] onCreateChild() {
        return new Sprite[]{new Bounce(), new Bounce(), new Bounce()};
    }
}
