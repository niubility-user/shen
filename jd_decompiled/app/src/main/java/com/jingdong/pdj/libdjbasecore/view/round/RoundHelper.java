package com.jingdong.pdj.libdjbasecore.view.round;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.pdj.libdjbasecore.R;

/* loaded from: classes7.dex */
public class RoundHelper {
    private boolean isCircle;
    private boolean isNeedDraw;
    private Context mContext;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;
    private float[] mRadii;
    private float mRadiusBottomLeft;
    private float mRadiusBottomRight;
    private float mRadiusTopLeft;
    private float mRadiusTopRight;
    private RectF mRectF;
    private int mStrokeColor;
    private float[] mStrokeRadii;
    private RectF mStrokeRectF;
    private float mStrokeWidth;
    private Path mTempPath;
    private View mView;
    private int mWidth;
    private Xfermode mXfermode;

    private void refresh() {
        if (this.mView != null) {
            this.isNeedDraw = true;
            setRadius();
            onSizeChanged(this.mWidth, this.mHeight);
            this.mView.invalidate();
        }
    }

    private void setRadius() {
        float[] fArr = this.mRadii;
        float f2 = this.mRadiusTopLeft;
        float f3 = this.mStrokeWidth;
        float f4 = f2 - f3;
        fArr[1] = f4;
        fArr[0] = f4;
        float f5 = this.mRadiusTopRight;
        float f6 = f5 - f3;
        fArr[3] = f6;
        fArr[2] = f6;
        float f7 = this.mRadiusBottomRight;
        float f8 = f7 - f3;
        fArr[5] = f8;
        fArr[4] = f8;
        float f9 = this.mRadiusBottomLeft;
        float f10 = f9 - f3;
        fArr[7] = f10;
        fArr[6] = f10;
        float[] fArr2 = this.mStrokeRadii;
        fArr2[1] = f2;
        fArr2[0] = f2;
        fArr2[3] = f5;
        fArr2[2] = f5;
        fArr2[5] = f7;
        fArr2[4] = f7;
        fArr2[7] = f9;
        fArr2[6] = f9;
    }

    public void drawPath(Canvas canvas) {
        if (this.isNeedDraw) {
            this.mPaint.reset();
            this.mPath.reset();
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setXfermode(this.mXfermode);
            this.mPath.addRoundRect(this.mRectF, this.mRadii, Path.Direction.CCW);
            if (Build.VERSION.SDK_INT >= 23) {
                this.mTempPath.reset();
                this.mTempPath.addRect(this.mRectF, Path.Direction.CCW);
                this.mTempPath.op(this.mPath, Path.Op.DIFFERENCE);
                canvas.drawPath(this.mTempPath, this.mPaint);
            } else {
                canvas.drawPath(this.mPath, this.mPaint);
            }
            this.mPaint.setXfermode(null);
            canvas.restore();
            if (this.mStrokeWidth > 0.0f) {
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(this.mStrokeWidth);
                this.mPaint.setColor(this.mStrokeColor);
                this.mPath.reset();
                this.mPath.addRoundRect(this.mStrokeRectF, this.mStrokeRadii, Path.Direction.CCW);
                canvas.drawPath(this.mPath, this.mPaint);
            }
        }
    }

    public void init(Context context, AttributeSet attributeSet, View view) {
        if ((view instanceof ViewGroup) && view.getBackground() == null) {
            view.setBackgroundColor(0);
        }
        this.mContext = context;
        this.mView = view;
        this.mRadii = new float[8];
        this.mStrokeRadii = new float[8];
        this.mPaint = new Paint();
        this.mRectF = new RectF();
        this.mStrokeRectF = new RectF();
        this.mPath = new Path();
        this.mTempPath = new Path();
        this.mXfermode = new PorterDuffXfermode(Build.VERSION.SDK_INT >= 23 ? PorterDuff.Mode.DST_OUT : PorterDuff.Mode.DST_IN);
        this.mStrokeColor = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCorner);
        if (obtainStyledAttributes == null) {
            return;
        }
        float dimension = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rLeftRadius, dimension);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rRightRadius, dimension);
        float dimension4 = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rTopRadius, dimension);
        float dimension5 = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rBottomRadius, dimension);
        this.isNeedDraw = obtainStyledAttributes.getBoolean(R.styleable.RoundButton_rNeedDraw, false);
        this.mRadiusTopLeft = obtainStyledAttributes.getDimension(R.styleable.RoundCorner_rTopLeftRadius, dimension4 > 0.0f ? dimension4 : dimension2);
        int i2 = R.styleable.RoundCorner_rTopRightRadius;
        if (dimension4 <= 0.0f) {
            dimension4 = dimension3;
        }
        this.mRadiusTopRight = obtainStyledAttributes.getDimension(i2, dimension4);
        int i3 = R.styleable.RoundCorner_rBottomLeftRadius;
        if (dimension5 > 0.0f) {
            dimension2 = dimension5;
        }
        this.mRadiusBottomLeft = obtainStyledAttributes.getDimension(i3, dimension2);
        int i4 = R.styleable.RoundCorner_rBottomRightRadius;
        if (dimension5 > 0.0f) {
            dimension3 = dimension5;
        }
        this.mRadiusBottomRight = obtainStyledAttributes.getDimension(i4, dimension3);
        this.mStrokeWidth = obtainStyledAttributes.getDimension(R.styleable.RoundButton_rStrokeWidth, 0.0f);
        this.mStrokeColor = obtainStyledAttributes.getColor(R.styleable.RoundButton_rStrokeColor, this.mStrokeColor);
        obtainStyledAttributes.recycle();
        if (this.isCircle) {
            return;
        }
        setRadius();
    }

    public void onSizeChanged(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
        if (this.isCircle) {
            float min = ((Math.min(i3, i2) * 1.0f) / 2.0f) - this.mStrokeWidth;
            this.mRadiusTopLeft = min;
            this.mRadiusTopRight = min;
            this.mRadiusBottomRight = min;
            this.mRadiusBottomLeft = min;
            setRadius();
        }
        RectF rectF = this.mRectF;
        if (rectF != null) {
            rectF.set(0.0f, 0.0f, i2, i3);
        }
        RectF rectF2 = this.mStrokeRectF;
        if (rectF2 != null) {
            float f2 = this.mStrokeWidth;
            rectF2.set(f2 / 2.0f, f2 / 2.0f, i2 - (f2 / 2.0f), i3 - (f2 / 2.0f));
        }
    }

    public void preDraw(Canvas canvas) {
        if (this.isNeedDraw) {
            canvas.saveLayer(this.mRectF, null, 31);
            float f2 = this.mStrokeWidth;
            if (f2 > 0.0f) {
                int i2 = this.mWidth;
                int i3 = this.mHeight;
                canvas.scale((i2 - (f2 * 2.0f)) / i2, (i3 - (f2 * 2.0f)) / i3, i2 / 2.0f, i3 / 2.0f);
            }
        }
    }

    public void setCircle(boolean z) {
        this.isCircle = z;
        this.isNeedDraw = true;
    }

    public void setRadiusBottom(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusBottomLeft = f2;
        this.mRadiusBottomRight = f2;
        refresh();
    }

    public void setRadiusBottomLeft(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusBottomLeft = f2;
        refresh();
    }

    public void setRadiusBottomRight(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusBottomRight = f2;
        refresh();
    }

    public void setRadiusLeft(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopLeft = f2;
        this.mRadiusBottomLeft = f2;
        refresh();
    }

    public void setRadiusRight(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopRight = f2;
        this.mRadiusBottomRight = f2;
        refresh();
    }

    public void setRadiusTop(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopLeft = f2;
        this.mRadiusTopRight = f2;
        refresh();
    }

    public void setRadiusTopLeft(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopLeft = f2;
        refresh();
    }

    public void setRadiusTopRight(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopRight = f2;
        refresh();
    }

    public void setStrokeColor(int i2) {
        this.mStrokeColor = i2;
        refresh();
    }

    public void setStrokeWidth(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mStrokeWidth = f2;
        refresh();
    }

    public void setStrokeWidthColor(float f2, int i2) {
        if (this.mContext == null) {
            return;
        }
        this.mStrokeWidth = f2;
        this.mStrokeColor = i2;
        refresh();
    }

    public void setRadius(float f2) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopLeft = f2;
        this.mRadiusTopRight = f2;
        this.mRadiusBottomLeft = f2;
        this.mRadiusBottomRight = f2;
        refresh();
    }

    public void setRadius(float f2, float f3, float f4, float f5) {
        if (this.mContext == null) {
            return;
        }
        this.mRadiusTopLeft = f2;
        this.mRadiusTopRight = f3;
        this.mRadiusBottomLeft = f4;
        this.mRadiusBottomRight = f5;
        refresh();
    }
}
