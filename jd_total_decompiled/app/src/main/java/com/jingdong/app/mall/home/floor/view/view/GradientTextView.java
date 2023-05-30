package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes4.dex */
public class GradientTextView extends AppCompatTextView {
    private Paint bgPaint;
    protected LinearGradient mBgGradient;
    protected GradientParam mBgGradientParam;
    protected LinearGradient mTextGradient;
    protected GradientParam mTextGradientParam;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.floor.view.view.GradientTextView$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType;

        static {
            int[] iArr = new int[GradientType.values().length];
            $SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType = iArr;
            try {
                iArr[GradientType.LeftToRight.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType[GradientType.TopToBottom.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType[GradientType.LeftTopToRightBottom.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType[GradientType.LeftBottomToRightTop.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public class GradientParam {
        int[] gradientColors;
        GradientType gradientType;
        float[] positions;

        public GradientParam(GradientType gradientType, int[] iArr, float[] fArr) {
            this.gradientType = null;
            this.gradientColors = null;
            this.positions = null;
            this.gradientType = gradientType;
            this.gradientColors = (int[]) iArr.clone();
            this.positions = (float[]) fArr.clone();
        }
    }

    /* loaded from: classes4.dex */
    public enum GradientType {
        LeftToRight,
        TopToBottom,
        LeftTopToRightBottom,
        LeftBottomToRightTop
    }

    public GradientTextView(Context context) {
        super(context);
        this.mTextGradient = null;
        this.mBgGradient = null;
        this.mTextGradientParam = null;
        this.mBgGradientParam = null;
        this.bgPaint = new Paint();
        setTextColor(-16777216);
    }

    private LinearGradient checkGradient(GradientParam gradientParam, LinearGradient linearGradient) {
        GradientType gradientType;
        if (gradientParam == null || (gradientType = gradientParam.gradientType) == null || linearGradient != null) {
            return linearGradient;
        }
        int[] gradientOrientalByGradientType = getGradientOrientalByGradientType(gradientType);
        return new LinearGradient(gradientOrientalByGradientType[0], gradientOrientalByGradientType[1], gradientOrientalByGradientType[2], gradientOrientalByGradientType[3], gradientParam.gradientColors, gradientParam.positions, Shader.TileMode.CLAMP);
    }

    private float[] getAveragePosition(int[] iArr) {
        int length = iArr.length;
        float[] fArr = new float[length];
        fArr[0] = 0.0f;
        int i2 = 1;
        while (true) {
            int i3 = length - 1;
            if (i2 < i3) {
                fArr[i2] = i2 / i3;
                i2++;
            } else {
                fArr[i3] = 1.0f;
                return fArr;
            }
        }
    }

    private int[] getGradientOrientalByGradientType(GradientType gradientType) {
        int[] iArr = new int[4];
        int width = getWidth();
        int height = getHeight();
        int i2 = AnonymousClass1.$SwitchMap$com$jingdong$app$mall$home$floor$view$view$GradientTextView$GradientType[gradientType.ordinal()];
        if (i2 == 1) {
            iArr[0] = 0;
            iArr[1] = height >> 1;
            iArr[2] = width;
            iArr[3] = iArr[1];
        } else if (i2 == 2) {
            iArr[0] = width >> 1;
            iArr[1] = 0;
            iArr[2] = iArr[0];
            iArr[3] = height;
        } else if (i2 == 3) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = width;
            iArr[3] = height;
        } else if (i2 == 4) {
            iArr[0] = 0;
            iArr[1] = height;
            iArr[2] = width;
            iArr[3] = 0;
        }
        return iArr;
    }

    private void resetBackgroundGradient() {
        this.mBgGradientParam = null;
        this.mBgGradient = null;
    }

    private void resetTextGradient() {
        this.mTextGradientParam = null;
        this.mTextGradient = null;
    }

    private boolean useGradient(Paint paint, GradientParam gradientParam, LinearGradient linearGradient) {
        if (gradientParam != null && gradientParam.gradientType != null && linearGradient != null) {
            paint.setShader(linearGradient);
            return true;
        }
        paint.setShader(null);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawTextBg(Canvas canvas) {
        this.mTextGradient = checkGradient(this.mTextGradientParam, this.mTextGradient);
        this.mBgGradient = checkGradient(this.mBgGradientParam, this.mBgGradient);
        useGradient(getPaint(), this.mTextGradientParam, this.mTextGradient);
        if (useGradient(this.bgPaint, this.mBgGradientParam, this.mBgGradient)) {
            canvas.drawPaint(this.bgPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas canvas) {
        drawTextBg(canvas);
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        resetBackgroundGradient();
        super.setBackground(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        resetBackgroundGradient();
        super.setBackgroundColor(i2);
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        resetBackgroundGradient();
        super.setBackgroundDrawable(drawable);
    }

    public void setBgGradient(GradientType gradientType, int i2, int i3) {
        setBgGradient(gradientType, new int[]{i2, i3}, new float[0]);
    }

    @Override // android.widget.TextView
    public void setTextColor(int i2) {
        resetTextGradient();
        super.setTextColor(i2);
    }

    public void setTextGradient(GradientType gradientType, int i2, int i3) {
        setTextGradient(gradientType, new int[]{i2, i3}, new float[0]);
    }

    public void setTextGradientWithDefault(GradientType gradientType, int[] iArr, int i2) {
        if (iArr != null && iArr.length > 0) {
            setTextGradient(gradientType, iArr);
        } else {
            setTextColor(i2);
        }
    }

    public void setBgGradient(GradientType gradientType, int[] iArr) {
        int length;
        if (iArr != null && (length = iArr.length) > 0) {
            if (length == 1) {
                setBackgroundColor(iArr[0]);
            } else {
                setBgGradient(gradientType, iArr, getAveragePosition(iArr));
            }
        }
    }

    public void setTextGradient(GradientType gradientType, int[] iArr) {
        int length;
        if (iArr != null && (length = iArr.length) > 0) {
            if (length == 1) {
                setTextColor(iArr[0]);
            } else {
                setTextGradient(gradientType, iArr, getAveragePosition(iArr));
            }
        }
    }

    @Override // android.widget.TextView
    public void setTextColor(ColorStateList colorStateList) {
        resetTextGradient();
        super.setTextColor(colorStateList);
    }

    public void setBgGradient(GradientType gradientType, int[] iArr, float[] fArr) {
        this.mBgGradientParam = new GradientParam(gradientType, iArr, fArr);
        this.mBgGradient = null;
    }

    public void setTextGradient(GradientType gradientType, int[] iArr, float[] fArr) {
        this.mTextGradientParam = new GradientParam(gradientType, iArr, fArr);
        this.mTextGradient = null;
    }

    public GradientTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextGradient = null;
        this.mBgGradient = null;
        this.mTextGradientParam = null;
        this.mBgGradientParam = null;
        this.bgPaint = new Paint();
    }

    public GradientTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTextGradient = null;
        this.mBgGradient = null;
        this.mTextGradientParam = null;
        this.mBgGradientParam = null;
        this.bgPaint = new Paint();
    }
}
