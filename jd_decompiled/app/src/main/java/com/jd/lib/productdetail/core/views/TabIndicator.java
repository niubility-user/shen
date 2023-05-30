package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class TabIndicator extends View {
    private static final Interpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private boolean isBitmap;
    private boolean isTenthRevision;
    private Bitmap mBitmap;
    private int mHeight;
    private final Paint mPaint;
    private int mRadius;
    private List<TextView> mTextViews;
    private int mWidth;
    private boolean needChangeWidth;
    private int position;
    private RectF rectF;
    View selectIcon1;
    View selectIcon2;
    View selectIcon3;
    View selectIcon4;

    public TabIndicator(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isShowSelectIcon(TextView textView, boolean z) {
        Object tag = textView.getTag();
        if (tag instanceof View) {
            ((View) tag).setVisibility(z ? 0 : 8);
        }
    }

    private void setWuZhangAi(int i2) {
        TextView textView = this.mTextViews.get(this.position);
        if (textView != null) {
            textView.setSelected(false);
        }
        TextView textView2 = this.mTextViews.get(i2);
        if (textView2 != null) {
            textView2.setSelected(true);
        }
    }

    public void addAllTitle(TextView... textViewArr) {
        List<TextView> list = this.mTextViews;
        if (list != null && !list.isEmpty()) {
            this.mTextViews.clear();
        }
        for (TextView textView : textViewArr) {
            ViewCompat.setAccessibilityDelegate(textView, new AccessibilityDelegateCompat() { // from class: com.jd.lib.productdetail.core.views.TabIndicator.2
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                    super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                    int eventType = accessibilityEvent.getEventType();
                    if (eventType == 32768 || eventType == 1) {
                        accessibilityEvent.setChecked(view.isSelected());
                    }
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                    accessibilityNodeInfoCompat.setSelected(view.isSelected());
                }
            });
            this.mTextViews.add(textView);
        }
    }

    public int getPosition() {
        return this.position;
    }

    public void isTenthRevision(boolean z) {
        this.isTenthRevision = z;
        if (z) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
        int dip2px = PDUtils.dip2px(4.0f);
        for (TextView textView : this.mTextViews) {
            if (this.isTenthRevision) {
                textView.setPadding(0, 0, dip2px, 0);
            } else {
                textView.setPadding(0, 0, 0, 0);
            }
        }
    }

    public void onChange() {
        resetRectFrame(this.position);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        super.onDraw(canvas);
        if (this.isBitmap && (bitmap = this.mBitmap) != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(this.mBitmap, (Rect) null, this.rectF, this.mPaint);
            return;
        }
        RectF rectF = this.rectF;
        int i2 = this.mRadius;
        canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        this.position = i2;
        TextView textView = this.mTextViews.get(i2);
        if (textView.getVisibility() == 8) {
            return;
        }
        TextView textView2 = this.mTextViews.size() + (-1) > i2 ? this.mTextViews.get(i2 + 1) : textView;
        this.rectF.left = textView.getLeft() + textView.getPaddingLeft() + (textView2.getWidth() * ACCELERATE_INTERPOLATOR.getInterpolation(f2));
        this.rectF.right = (textView.getRight() - textView.getPaddingRight()) + (textView2.getWidth() * DECELERATE_INTERPOLATOR.getInterpolation(f2));
        this.rectF.top = getPaddingTop();
        this.rectF.bottom = getHeight() - getPaddingBottom();
        invalidate();
    }

    public void onTabSelected(final int i2, final int i3, final int i4) {
        post(new Runnable() { // from class: com.jd.lib.productdetail.core.views.TabIndicator.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (TabIndicator.this.mTextViews != null && !TabIndicator.this.mTextViews.isEmpty()) {
                        TextView textView = (TextView) TabIndicator.this.mTextViews.get(TabIndicator.this.position);
                        TextView textView2 = (TextView) TabIndicator.this.mTextViews.get(i2);
                        if (TabIndicator.this.isTenthRevision) {
                            TabIndicator.this.isShowSelectIcon(textView, false);
                            TabIndicator.this.isShowSelectIcon(textView2, true);
                        } else {
                            TabIndicator.this.isShowSelectIcon(textView, false);
                            TabIndicator.this.isShowSelectIcon(textView2, false);
                        }
                        if (textView != null) {
                            textView.setTextSize(2, 14.0f);
                            textView.getPaint().setFakeBoldText(false);
                            textView.setTextColor(i3);
                        }
                        if (textView2 != null) {
                            textView2.setTextSize(2, 15.0f);
                            textView2.getPaint().setFakeBoldText(true);
                            textView2.setTextColor(i4);
                        }
                        TabIndicator.this.resetRectFrame(i2);
                        TabIndicator.this.position = i2;
                    }
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
        });
    }

    public void onTabSelected(View view, View view2) {
    }

    public void resetRectFrame(int i2) {
        List<TextView> list = this.mTextViews;
        if (list != null && !list.isEmpty()) {
            setWuZhangAi(i2);
            TextView textView = this.mTextViews.get(i2);
            if (textView != null) {
                ViewParent parent = textView.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    this.rectF.left = viewGroup.getLeft() + viewGroup.getPaddingLeft();
                    this.rectF.right = viewGroup.getRight() - viewGroup.getPaddingRight();
                    this.rectF.top = getPaddingTop();
                    this.rectF.bottom = getHeight() - getPaddingBottom();
                }
            }
        }
        invalidate();
    }

    public void setColor(int i2) {
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColor(i2);
            requestLayout();
        }
    }

    public void setNeedChangeWidth(boolean z) {
        this.needChangeWidth = z;
    }

    public void setTabSelectBitmap(Bitmap bitmap) {
        this.isBitmap = true;
        this.mBitmap = bitmap;
        resetRectFrame(this.position);
    }

    public void setTabSelectColor(int i2) {
        this.isBitmap = false;
        this.mPaint.setColor(i2);
        resetRectFrame(this.position);
    }

    public void setTabWidth(int i2) {
        if (i2 < 0) {
            return;
        }
        this.mWidth = i2;
        invalidate();
    }

    public TabIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.rectF = new RectF();
        this.mTextViews = new ArrayList();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        this.mHeight = 3;
        this.mWidth = 84;
        this.mRadius = 0;
        this.needChangeWidth = false;
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PDTabIndicator);
        this.mWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PDTabIndicator_PDTabIndicatorWidth, -1);
        this.mHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PDTabIndicator_PDTabIndicatorHeight, 3);
        this.mRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.PDTabIndicator_PDTabIndicatorRadius, 0);
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.PDTabIndicator_PDTabIndicatorSelectColor, -14474458));
        obtainStyledAttributes.recycle();
    }
}
