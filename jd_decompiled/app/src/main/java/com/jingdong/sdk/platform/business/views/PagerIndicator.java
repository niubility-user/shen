package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.utils.BusinessTools;

/* loaded from: classes10.dex */
public class PagerIndicator extends View {
    private int indicatorCount;
    private int indicatorHeight;
    private int indicatorHorizontalSpace;
    private int indicatorIndex;
    private int indicatorWidth;
    private float offSet;
    private Paint p;
    private RectF[] rectFs;
    private int selectedIndicatorColor;
    private int selectedIndicatorHeight;
    private int selectedIndicatorWidth;
    private int unSelectedIndicatorColor;

    public PagerIndicator(Context context) {
        this(context, null);
    }

    private void calculateRectFs() {
        int i2;
        int i3;
        int i4;
        RectF[] rectFArr = this.rectFs;
        if (rectFArr == null || rectFArr.length != this.indicatorCount) {
            this.rectFs = new RectF[this.indicatorCount];
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
        int i5 = 0;
        while (true) {
            i2 = this.indicatorCount;
            if (i5 >= i2) {
                break;
            }
            if (i5 == this.indicatorIndex) {
                i3 = (height2 - this.selectedIndicatorHeight) / 2;
                i4 = this.selectedIndicatorWidth;
            } else {
                i3 = (height2 - this.indicatorHeight) / 2;
                i4 = this.indicatorWidth;
            }
            RectF rectF = new RectF(paddingLeft, paddingTop + i3, paddingLeft + i4, height - i3);
            paddingLeft += i4 + this.indicatorHorizontalSpace;
            this.rectFs[i5] = rectF;
            i5++;
        }
        int i6 = this.indicatorIndex;
        if (i6 != i2 - 1) {
            RectF[] rectFArr2 = this.rectFs;
            RectF rectF2 = rectFArr2[i6];
            RectF rectF3 = rectFArr2[i6 + 1];
            float f2 = rectF3.right;
            float f3 = rectF2.right;
            float f4 = this.offSet;
            float f5 = (f2 - f3) * f4;
            float f6 = rectF2.left;
            float f7 = (f6 - rectF3.left) * f4;
            rectF2.left = f6 + f5;
            rectF2.right = f3 + f5;
            rectF3.left += f7;
            rectF3.right += f7;
        }
    }

    private void init(Context context) {
        this.p.setColor(-16711936);
        this.p.setAntiAlias(true);
        this.indicatorWidth = BusinessTools.dip2px(this.indicatorWidth);
        this.indicatorHeight = BusinessTools.dip2px(this.indicatorHeight);
        this.selectedIndicatorWidth = BusinessTools.dip2px(this.selectedIndicatorWidth);
        this.selectedIndicatorHeight = BusinessTools.dip2px(this.selectedIndicatorHeight);
    }

    private void readAttr(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagerIndicator);
        this.indicatorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerIndicator_indicatorWidth, this.indicatorWidth);
        this.indicatorHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerIndicator_indicatorHeight, this.indicatorHeight);
        this.indicatorHorizontalSpace = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerIndicator_indicatorSpace, this.indicatorHorizontalSpace);
        this.selectedIndicatorWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerIndicator_selectedIndicatorWidth, this.selectedIndicatorWidth);
        this.selectedIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PagerIndicator_selectedIndicatorHeight, this.selectedIndicatorHeight);
        this.unSelectedIndicatorColor = obtainStyledAttributes.getColor(R.styleable.PagerIndicator_indicatorColor, this.unSelectedIndicatorColor);
        this.selectedIndicatorColor = obtainStyledAttributes.getColor(R.styleable.PagerIndicator_selectedIndicatorColor, this.selectedIndicatorColor);
        int i2 = obtainStyledAttributes.getInt(R.styleable.PagerIndicator_count, 0);
        this.indicatorCount = i2;
        if (i2 < 0) {
            this.indicatorCount = 0;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.PagerIndicator_index, 0);
        this.indicatorIndex = i3;
        if (i3 < 0) {
            this.indicatorIndex = 0;
        }
        int i4 = this.indicatorIndex;
        int i5 = this.indicatorCount;
        if (i4 > i5 - 1) {
            this.indicatorIndex = i5 - 1;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calculateRectFs();
        if (this.rectFs == null) {
            return;
        }
        this.p.setColor(this.unSelectedIndicatorColor);
        int i2 = 0;
        while (true) {
            RectF[] rectFArr = this.rectFs;
            if (i2 >= rectFArr.length) {
                break;
            }
            if (this.indicatorIndex != i2) {
                RectF rectF = rectFArr[i2];
                Math.min(Math.abs((rectF.bottom - rectF.top) / 2.0f), Math.abs((rectF.right - rectF.left) / 2.0f));
                canvas.drawRoundRect(rectF, 0.0f, 0.0f, this.p);
            }
            i2++;
        }
        int i3 = this.indicatorIndex;
        if (i3 < 0 || i3 >= this.indicatorCount) {
            return;
        }
        this.p.setColor(this.selectedIndicatorColor);
        RectF rectF2 = this.rectFs[this.indicatorIndex];
        Math.min(Math.abs((rectF2.bottom - rectF2.top) / 2.0f), Math.abs((rectF2.right - rectF2.left) / 2.0f));
        canvas.drawRoundRect(rectF2, 0.0f, 0.0f, this.p);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int i4 = this.indicatorCount;
        int i5 = (this.indicatorWidth * i4) + ((i4 - 1) * this.indicatorHorizontalSpace);
        int max = Math.max(this.indicatorHeight, this.selectedIndicatorHeight);
        int i6 = this.indicatorIndex;
        if (i6 >= 0 && i6 < this.indicatorCount) {
            i5 += this.selectedIndicatorWidth - this.indicatorWidth;
        }
        setMeasuredDimension(i5 + getPaddingLeft() + getPaddingRight(), max + getPaddingTop() + getPaddingBottom());
    }

    public void setIndicatorCount(int i2) {
        this.indicatorCount = i2;
        this.offSet = 0.0f;
        int i3 = this.indicatorIndex;
        if (i3 > i2 - 1) {
            this.indicatorCount = i2 - 1;
        }
        if (i3 < 0) {
            this.indicatorIndex = 0;
        }
    }

    public void setIndicatorHeight(int i2) {
        this.indicatorHeight = i2;
    }

    public void setIndicatorHorizontalSpace(int i2) {
        this.indicatorHorizontalSpace = i2;
    }

    public void setIndicatorIndex(int i2) {
        this.indicatorIndex = i2;
        this.offSet = 0.0f;
        int i3 = this.indicatorCount;
        if (i2 > i3 - 1) {
            this.indicatorCount = i3 - 1;
        }
        if (i2 < 0) {
            this.indicatorIndex = 0;
        }
        postInvalidate();
    }

    public void setIndicatorWidth(int i2) {
        this.indicatorWidth = i2;
    }

    public void setSelectedIndicatorColor(int i2) {
        this.selectedIndicatorColor = i2;
    }

    public void setSelectedIndicatorHeight(int i2) {
        this.selectedIndicatorHeight = i2;
    }

    public void setSelectedIndicatorWidth(int i2) {
        this.selectedIndicatorWidth = i2;
    }

    public void setUnSelectedIndicatorColor(int i2) {
        this.unSelectedIndicatorColor = i2;
    }

    public void setUpViewPager(@NonNull ViewPager viewPager) {
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter != null) {
            this.indicatorCount = adapter.getCount();
        }
        this.indicatorIndex = viewPager.getCurrentItem();
    }

    public PagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerIndicator(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.indicatorWidth = 10;
        this.indicatorHeight = 4;
        this.selectedIndicatorWidth = 20;
        this.selectedIndicatorHeight = 4;
        this.indicatorHorizontalSpace = 0;
        this.selectedIndicatorColor = Color.parseColor("#ED5C55");
        this.unSelectedIndicatorColor = -1;
        this.indicatorCount = 0;
        this.indicatorIndex = 0;
        this.offSet = 0.0f;
        this.p = new Paint();
        init(context);
        readAttr(context, attributeSet);
        if (this.indicatorCount < 0) {
            this.indicatorCount = 0;
        }
    }
}
