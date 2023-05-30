package com.jdpay.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes18.dex */
public class RoundImageView extends AppCompatImageView {
    private final RectF bounds;
    private final DrawFilter drawFilter;
    private final Path path;
    private final float[] radii;

    public RoundImageView(@NonNull Context context) {
        super(context);
        this.bounds = new RectF();
        this.radii = new float[8];
        this.path = new Path();
        this.drawFilter = createDrawFilter();
    }

    private int clipCanvas(Canvas canvas) {
        int save = canvas.save();
        DrawFilter drawFilter = canvas.getDrawFilter();
        DrawFilter drawFilter2 = this.drawFilter;
        if (drawFilter2 != drawFilter) {
            canvas.setDrawFilter(drawFilter2);
        }
        RectF rectF = this.bounds;
        rectF.left = 0.0f;
        rectF.top = 0.0f;
        rectF.right = getWidth();
        this.bounds.bottom = getHeight();
        this.path.addRoundRect(this.bounds, this.radii, Path.Direction.CCW);
        canvas.clipPath(this.path);
        return save;
    }

    private void resumeCanvas(Canvas canvas, int i2) {
        this.path.reset();
        canvas.restoreToCount(i2);
    }

    protected DrawFilter createDrawFilter() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new PaintFlagsDrawFilter(0, R2.attr.line_width_v);
        }
        return new PaintFlagsDrawFilter(0, 259);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        int clipCanvas = clipCanvas(canvas);
        super.onDraw(canvas);
        resumeCanvas(canvas, clipCanvas);
    }

    public void setCorner(int i2, int i3, int i4, int i5) {
        float[] fArr = this.radii;
        float f2 = i2;
        fArr[1] = f2;
        fArr[0] = f2;
        float f3 = i3;
        fArr[3] = f3;
        fArr[2] = f3;
        float f4 = i4;
        fArr[5] = f4;
        fArr[4] = f4;
        float f5 = i5;
        fArr[7] = f5;
        fArr[6] = f5;
    }

    public void setTopCorner(int i2) {
        float[] fArr = this.radii;
        float f2 = i2;
        fArr[3] = f2;
        fArr[2] = f2;
        fArr[1] = f2;
        fArr[0] = f2;
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.bounds = new RectF();
        this.radii = new float[8];
        this.path = new Path();
        this.drawFilter = createDrawFilter();
    }

    public RoundImageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.bounds = new RectF();
        this.radii = new float[8];
        this.path = new Path();
        this.drawFilter = createDrawFilter();
    }
}
