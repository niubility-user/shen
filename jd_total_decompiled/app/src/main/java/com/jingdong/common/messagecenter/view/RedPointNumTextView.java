package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes5.dex */
public class RedPointNumTextView extends View {
    private static final int COLOR_RED = Color.parseColor("#F23030");
    private static final int COLOR_WHITE = Color.parseColor("#FFFFFF");
    private static final String TAG = "RedPointTextView";
    private int bgColor;
    private RectF bgRectF;
    private int cornerRadius;
    private int curNum;
    private float density;
    private boolean hasWindowFocus;
    private int heightCenter;
    private boolean isStartAnim;
    private boolean isUseAnim;
    private int mCharWidth;
    private Paint mPaint;
    private Rect mTextBounds;
    private int mTextColor;
    private int mTextHeight;
    private int mTextSize;
    private int num0Height;
    private int num0Width;
    private int num1Height;
    private int num1Width;
    private int num2Height;
    private int num2Width;
    private int num3Height;
    private int num3Width;
    private int redPointRingWidth;
    private String sCurNum;
    private String sTarNum;
    private int space;
    private int speed;
    private int step;
    private int stepCount;
    private int tarNum;

    public RedPointNumTextView(Context context) {
        this(context, null);
    }

    private boolean canAnim() {
        return this.isUseAnim && this.hasWindowFocus && getVisibility() == 0;
    }

    private void changeTextSize() {
        int i2 = this.mTextSize;
        this.cornerRadius = i2;
        float f2 = this.density;
        this.num0Width = (int) (i2 - (f2 * 2.0f));
        this.num0Height = (int) (i2 - (f2 * 2.0f));
        this.num1Width = (i2 * 4) / 3;
        this.num1Height = (i2 * 4) / 3;
        this.num2Width = (int) ((i2 * 2) - (f2 * 2.0f));
        this.num2Height = (i2 * 4) / 3;
        this.num3Width = i2 * 2;
        this.num3Height = (i2 * 4) / 3;
    }

    private void drawBg(Canvas canvas) {
        this.mPaint.setColor(this.bgColor);
        int i2 = this.curNum;
        if (i2 < 0) {
            if (this.redPointRingWidth > 0) {
                this.bgRectF.left = (getMeasuredWidth() / 2) - (this.num0Width / 2);
                this.bgRectF.right = (getMeasuredWidth() / 2) + (this.num0Width / 2);
                this.bgRectF.top = (getMeasuredHeight() / 2) - (this.num0Height / 2);
                this.bgRectF.bottom = (getMeasuredHeight() / 2) + (this.num0Height / 2);
                this.mPaint.setColor(this.mTextColor);
                canvas.drawOval(this.bgRectF, this.mPaint);
            }
            int i3 = this.redPointRingWidth;
            if (i3 >= this.num0Width / 2 || i3 >= this.num0Height / 2) {
                return;
            }
            this.bgRectF.left = ((getMeasuredWidth() / 2) - (this.num0Width / 2)) + this.redPointRingWidth;
            this.bgRectF.right = ((getMeasuredWidth() / 2) + (this.num0Width / 2)) - this.redPointRingWidth;
            this.bgRectF.top = ((getMeasuredHeight() / 2) - (this.num0Height / 2)) + this.redPointRingWidth;
            this.bgRectF.bottom = ((getMeasuredHeight() / 2) + (this.num0Height / 2)) - this.redPointRingWidth;
            this.mPaint.setColor(this.bgColor);
            canvas.drawOval(this.bgRectF, this.mPaint);
        } else if (i2 == 0) {
        } else {
            if (i2 < 10) {
                this.bgRectF.left = (getMeasuredWidth() / 2) - (this.num1Width / 2);
                this.bgRectF.right = (getMeasuredWidth() / 2) + (this.num1Width / 2);
                this.bgRectF.top = (getMeasuredHeight() / 2) - (this.num1Height / 2);
                this.bgRectF.bottom = (getMeasuredHeight() / 2) + (this.num1Height / 2);
                canvas.drawOval(this.bgRectF, this.mPaint);
            } else if (i2 <= 99) {
                this.bgRectF.left = (getMeasuredWidth() / 2) - (this.num2Width / 2);
                this.bgRectF.right = (getMeasuredWidth() / 2) + (this.num2Width / 2);
                this.bgRectF.top = (getMeasuredHeight() / 2) - (this.num2Height / 2);
                this.bgRectF.bottom = (getMeasuredHeight() / 2) + (this.num2Height / 2);
                RectF rectF = this.bgRectF;
                int i4 = this.cornerRadius;
                canvas.drawRoundRect(rectF, i4, i4, this.mPaint);
            } else {
                this.bgRectF.left = (getMeasuredWidth() / 2) - (this.num3Width / 2);
                this.bgRectF.right = (getMeasuredWidth() / 2) + (this.num3Width / 2);
                this.bgRectF.top = (getMeasuredHeight() / 2) - (this.num3Height / 2);
                this.bgRectF.bottom = (getMeasuredHeight() / 2) + (this.num3Height / 2);
                RectF rectF2 = this.bgRectF;
                int i5 = this.cornerRadius;
                canvas.drawRoundRect(rectF2, i5, i5, this.mPaint);
            }
        }
    }

    private void drawNum(Canvas canvas) {
        this.mPaint.setColor(this.mTextColor);
        if (canAnim()) {
            if (this.isStartAnim) {
                int i2 = this.curNum;
                if (i2 == this.tarNum) {
                    this.isStartAnim = false;
                    invalidate();
                    return;
                }
                int i3 = i2 / 10;
                int i4 = i2 % 10;
                if (i3 == 0) {
                    this.mPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(i4 + "", getMeasuredWidth() / 2, (getMeasuredHeight() * this.step) / this.stepCount, this.mPaint);
                } else if (i4 == 0) {
                    this.mPaint.setTextAlign(Paint.Align.LEFT);
                    this.mPaint.getTextBounds("00", 0, 2, this.mTextBounds);
                    canvas.drawText(i3 + "", (getMeasuredWidth() / 2) - (this.mTextBounds.width() / 2), (getMeasuredHeight() * this.step) / this.stepCount, this.mPaint);
                    canvas.drawText(i4 + "", ((getMeasuredWidth() / 2) - (this.mTextBounds.width() / 2)) + this.mCharWidth + this.space, this.heightCenter, this.mPaint);
                } else {
                    this.mPaint.setTextAlign(Paint.Align.LEFT);
                    this.mPaint.getTextBounds("00", 0, 2, this.mTextBounds);
                    canvas.drawText(i3 + "", (getMeasuredWidth() / 2) - (this.mTextBounds.width() / 2), this.heightCenter, this.mPaint);
                    canvas.drawText(i4 + "", ((getMeasuredWidth() / 2) - (this.mTextBounds.width() / 2)) + this.mCharWidth + this.space, (getMeasuredHeight() * this.step) / this.stepCount, this.mPaint);
                }
                int i5 = this.curNum;
                if (i5 < this.tarNum) {
                    int i6 = this.step - 1;
                    this.step = i6;
                    if (i6 < 0) {
                        this.step = this.stepCount;
                        this.curNum = i5 + 1;
                    }
                } else {
                    int i7 = this.step + 1;
                    this.step = i7;
                    if (i7 > this.stepCount) {
                        this.step = 0;
                        this.curNum = i5 - 1;
                    }
                }
                postInvalidateDelayed(this.speed);
                return;
            }
            this.isStartAnim = false;
            this.curNum = this.tarNum;
            this.sCurNum = this.sTarNum;
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.sTarNum, getMeasuredWidth() / 2, this.heightCenter, this.mPaint);
            return;
        }
        this.curNum = this.tarNum;
        this.sCurNum = this.sTarNum;
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.sTarNum, getMeasuredWidth() / 2, this.heightCenter, this.mPaint);
    }

    private void init(AttributeSet attributeSet) {
        this.mPaint = new Paint();
        this.density = getResources().getDisplayMetrics().density;
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RedPointNumTextView);
        if (obtainStyledAttributes != null) {
            this.isUseAnim = obtainStyledAttributes.getBoolean(R.styleable.RedPointNumTextView_redPoint_isAnim, false);
            this.tarNum = obtainStyledAttributes.getInt(R.styleable.RedPointNumTextView_redPoint_num, 0);
            this.bgColor = obtainStyledAttributes.getColor(R.styleable.RedPointNumTextView_redPoint_bgColor, COLOR_RED);
            this.mTextColor = obtainStyledAttributes.getColor(R.styleable.RedPointNumTextView_redPoint_textColor, COLOR_WHITE);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_textSize, (int) (this.density * 9.0f));
            this.mTextSize = dimensionPixelSize;
            this.cornerRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_cornerRadius, dimensionPixelSize);
            this.num0Width = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n0Width, (int) (this.mTextSize - (this.density * 2.0f)));
            this.num0Height = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n0Height, (int) (this.mTextSize - (this.density * 2.0f)));
            this.num1Width = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n1Width, (this.mTextSize * 4) / 3);
            this.num1Height = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n1Height, (this.mTextSize * 4) / 3);
            this.num2Width = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n2Width, (int) ((this.mTextSize * 2) - (this.density * 2.0f)));
            this.num2Height = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n2Height, (this.mTextSize * 4) / 3);
            this.num3Width = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n3Width, this.mTextSize * 2);
            this.num3Height = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RedPointNumTextView_redPoint_n3Height, (this.mTextSize * 4) / 3);
            obtainStyledAttributes.recycle();
        }
        this.redPointRingWidth = (int) (this.density / 2.0f);
        initPaint();
        setNum(this.tarNum);
    }

    private void initPaint() {
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setTypeface(FontsUtil.getTypeFace(getContext()));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        try {
            drawBg(canvas);
            drawNum(canvas);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        this.mPaint.getTextBounds("00+", 0, 3, this.mTextBounds);
        this.mCharWidth = this.mTextBounds.width() / 3;
        this.mTextHeight = this.mTextBounds.height();
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            size = this.mTextSize * 2;
        }
        if (View.MeasureSpec.getMode(i3) != 1073741824) {
            size2 = (this.mTextSize * 4) / 3;
        }
        setMeasuredDimension(size, size2);
        this.heightCenter = (getMeasuredHeight() + this.mTextHeight) / 2;
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.hasWindowFocus = z;
        String str = "onWindowFocusChanged:" + z;
    }

    public void setBgColor(int i2) {
        if (this.bgColor == i2) {
            return;
        }
        this.bgColor = i2;
        invalidate();
    }

    public void setCornerRadius(int i2) {
        if (this.cornerRadius == i2) {
            return;
        }
        this.cornerRadius = i2;
        invalidate();
    }

    public void setIsUseAnim(boolean z) {
        this.isUseAnim = z;
    }

    public void setNum(int i2) {
        if (i2 < 0) {
            this.sTarNum = "";
            this.tarNum = -1;
            this.curNum = -1;
        } else if (i2 == 0) {
            this.sTarNum = "";
            this.tarNum = 0;
            this.curNum = 0;
        } else if (i2 > 99) {
            this.sTarNum = "99+";
            this.tarNum = 100;
        } else {
            this.sTarNum = i2 + "";
            this.tarNum = i2;
        }
        if (this.sCurNum.equals(this.sTarNum)) {
            if ("".equals(this.sTarNum)) {
                invalidate();
            }
        } else if (canAnim()) {
            if (this.isStartAnim) {
                return;
            }
            this.isStartAnim = true;
            invalidate();
        } else {
            this.sCurNum = this.sTarNum;
            this.curNum = this.tarNum;
            invalidate();
        }
    }

    public void setNum0Size(int i2, int i3) {
        this.num0Width = i2;
        this.num0Height = i3;
    }

    public void setNum1Size(int i2, int i3) {
        this.num1Width = i2;
        this.num1Height = i3;
    }

    public void setNum2Size(int i2, int i3) {
        this.num2Width = i2;
        this.num2Height = i3;
    }

    public void setNum3Size(int i2, int i3) {
        this.num3Width = i2;
        this.num3Height = i3;
    }

    public void setRedPointWidth(int i2) {
        if (this.redPointRingWidth == i2) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.redPointRingWidth = i2;
        invalidate();
    }

    public void setTextColor(int i2) {
        if (this.mTextColor == i2) {
            return;
        }
        this.mTextColor = i2;
        this.mPaint.setColor(i2);
        invalidate();
    }

    public void setTextSize(int i2) {
        if (this.mTextSize == i2) {
            return;
        }
        this.mTextSize = i2;
        changeTextSize();
        this.mPaint.setTextSize(this.mTextSize);
        requestLayout();
    }

    public void setTypeface(Typeface typeface) {
        this.mPaint.setTypeface(typeface);
        invalidate();
    }

    public RedPointNumTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RedPointNumTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTextBounds = new Rect();
        this.hasWindowFocus = false;
        this.mTextHeight = 0;
        this.mCharWidth = 0;
        this.isStartAnim = false;
        this.space = 5;
        this.curNum = 0;
        this.tarNum = 0;
        this.sTarNum = "";
        this.sCurNum = "";
        this.speed = 16;
        this.step = 0;
        this.stepCount = 2;
        this.heightCenter = 0;
        this.bgRectF = new RectF();
        this.isUseAnim = false;
        init(attributeSet);
    }
}
