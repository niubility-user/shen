package com.jd.aips.widget.spinkit;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.jd.aips.verify.identity.R;
import com.jd.aips.widget.spinkit.sprite.Sprite;
import com.jd.aips.widget.spinkit.style.ThreeBounce;

/* loaded from: classes12.dex */
public class SpinKitView extends ProgressBar {
    private int a;
    private Sprite b;

    public SpinKitView(Context context) {
        this(context, null);
    }

    private void a() {
        ThreeBounce threeBounce = new ThreeBounce();
        threeBounce.setColor(this.a);
        setIndeterminateDrawable((Sprite) threeBounce);
    }

    @Override // android.view.View
    public void onScreenStateChanged(int i2) {
        Sprite sprite;
        super.onScreenStateChanged(i2);
        if (i2 != 0 || (sprite = this.b) == null) {
            return;
        }
        sprite.stop();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.b != null && getVisibility() == 0) {
            this.b.start();
        }
    }

    public void setColor(int i2) {
        this.a = i2;
        Sprite sprite = this.b;
        if (sprite != null) {
            sprite.setColor(i2);
        }
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable instanceof Sprite) {
            setIndeterminateDrawable((Sprite) drawable);
            return;
        }
        throw new IllegalArgumentException("this d must be instanceof Sprite");
    }

    @Override // android.view.View
    public void unscheduleDrawable(Drawable drawable) {
        super.unscheduleDrawable(drawable);
        if (drawable instanceof Sprite) {
            ((Sprite) drawable).stop();
        }
    }

    public SpinKitView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.ProgressBar
    public Sprite getIndeterminateDrawable() {
        return this.b;
    }

    public SpinKitView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, R.style.Aips_SpinKitView);
    }

    @TargetApi(21)
    public SpinKitView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.a = -1;
        a();
        setIndeterminate(true);
    }

    public void setIndeterminateDrawable(Sprite sprite) {
        super.setIndeterminateDrawable((Drawable) sprite);
        this.b = sprite;
        if (sprite.getColor() == 0) {
            this.b.setColor(this.a);
        }
        onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
        if (getVisibility() == 0) {
            this.b.start();
        }
    }
}
