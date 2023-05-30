package com.tencent.mapsdk.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.tencent.mm.opensdk.constants.ConstantsAPI;

/* loaded from: classes9.dex */
public class ag extends Drawable {
    private static final int b = -12028419;
    private Paint a;

    public ag() {
        Paint paint;
        int i2;
        Paint paint2 = new Paint();
        this.a = paint2;
        paint2.setAntiAlias(true);
        if (li.f16848c.equals(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE)) {
            paint = this.a;
            i2 = -16531104;
        } else {
            paint = this.a;
            i2 = b;
        }
        paint.setColor(i2);
    }

    public void a(int i2) {
        this.a.setColor(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(getBounds().width() / 2.0f, getBounds().height() / 2.0f, getBounds().width() / 2.0f, this.a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return getBounds().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.a.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }
}
