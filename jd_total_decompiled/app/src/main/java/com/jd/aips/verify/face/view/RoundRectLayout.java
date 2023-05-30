package com.jd.aips.verify.face.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes12.dex */
public class RoundRectLayout extends RelativeLayout {
    public static final int MODE_ALL = 1;
    public static final int MODE_BOTTOM = 5;
    public static final int MODE_LEFT = 2;
    public static final int MODE_NONE = 0;
    public static final int MODE_RIGHT = 4;
    public static final int MODE_TOP = 3;
    private int mHeight;
    private int mLastRadius;
    private Path mPath;
    private int mRadius;
    private int mRoundMode;
    private int mWidth;

    public RoundRectLayout(Context context) {
        super(context);
        this.mRoundMode = 1;
        init();
    }

    private void checkPathChanged() {
        if (getWidth() == this.mWidth && getHeight() == this.mHeight && this.mLastRadius == this.mRadius) {
            return;
        }
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        this.mLastRadius = this.mRadius;
        this.mPath.reset();
        int i2 = this.mRoundMode;
        if (i2 == 1) {
            Path path = this.mPath;
            RectF rectF = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            float f2 = this.mRadius;
            path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        } else if (i2 == 2) {
            Path path2 = this.mPath;
            RectF rectF2 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            float f3 = this.mRadius;
            path2.addRoundRect(rectF2, new float[]{f3, f3, 0.0f, 0.0f, 0.0f, 0.0f, f3, f3}, Path.Direction.CW);
        } else if (i2 == 3) {
            Path path3 = this.mPath;
            RectF rectF3 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            float f4 = this.mRadius;
            path3.addRoundRect(rectF3, new float[]{f4, f4, f4, f4, 0.0f, 0.0f, 0.0f, 0.0f}, Path.Direction.CW);
        } else if (i2 == 4) {
            Path path4 = this.mPath;
            RectF rectF4 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            float f5 = this.mRadius;
            path4.addRoundRect(rectF4, new float[]{0.0f, 0.0f, f5, f5, f5, f5, 0.0f, 0.0f}, Path.Direction.CW);
        } else if (i2 != 5) {
        } else {
            Path path5 = this.mPath;
            RectF rectF5 = new RectF(0.0f, 0.0f, this.mWidth, this.mHeight);
            float f6 = this.mRadius;
            path5.addRoundRect(rectF5, new float[]{0.0f, 0.0f, 0.0f, 0.0f, f6, f6, f6, f6}, Path.Direction.CW);
        }
    }

    private void init() {
        setBackgroundDrawable(new ColorDrawable(16711680));
        Path path = new Path();
        this.mPath = path;
        path.setFillType(Path.FillType.EVEN_ODD);
        setCornerRadius(R2.attr.counterOverflowTextColor);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mRoundMode != 0) {
            int save = canvas.save();
            checkPathChanged();
            canvas.clipPath(this.mPath);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    public void setCornerRadius(int i2) {
        this.mRadius = i2;
    }

    public void setRoundMode(int i2) {
        this.mRoundMode = i2;
    }

    public RoundRectLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRoundMode = 1;
        init();
    }
}
