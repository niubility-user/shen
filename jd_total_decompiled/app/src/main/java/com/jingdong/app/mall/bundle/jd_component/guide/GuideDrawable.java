package com.jingdong.app.mall.bundle.jd_component.guide;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes2.dex */
public class GuideDrawable extends Drawable {
    private Drawable innerDrawable;
    private Paint srcPaint;
    private Path srcPath = new Path();

    public GuideDrawable(Drawable drawable) {
        this.innerDrawable = drawable == null ? new ColorDrawable(Color.argb(178, 0, 0, 0)) : drawable;
        this.srcPath.addRect(300.0f, 300.0f, 600.0f, 600.0f, Path.Direction.CW);
        Paint paint = new Paint(1);
        this.srcPaint = paint;
        paint.setColor(-1);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.innerDrawable.setBounds(getBounds());
        Path path = this.srcPath;
        if (path != null && !path.isEmpty()) {
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.srcPaint, 31);
            this.innerDrawable.draw(canvas);
            this.srcPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPath(this.srcPath, this.srcPaint);
            this.srcPaint.setXfermode(null);
            canvas.restoreToCount(saveLayer);
            return;
        }
        this.innerDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.innerDrawable.getOpacity();
    }

    public Path getSrcPath() {
        return this.srcPath;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.innerDrawable.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.innerDrawable.setColorFilter(colorFilter);
    }

    public void setSrcPath(Path path) {
        this.srcPath = path;
    }
}
