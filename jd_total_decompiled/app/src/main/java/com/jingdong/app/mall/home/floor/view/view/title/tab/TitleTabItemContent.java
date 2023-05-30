package com.jingdong.app.mall.home.floor.view.view.title.tab;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.widget.LinearLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.h.g;

/* loaded from: classes4.dex */
public class TitleTabItemContent extends LinearLayout {
    public static final int MIN_TAB_WIDTH = 92;
    public static final int SPREAD_TAB_WIDTH = 96;
    public static final int sMaxCount = 4;
    private int currentOffset;
    private int currentWidth;
    private final boolean isUseSpread;
    private final Paint mBgPaint;
    private final Path mBgPath;
    private final Paint mLinePaint;
    private final Paint mSelectPaint;
    private final Path mSelectPath;
    private final float[] mSelectPositions;
    private final Paint mSelectStrokePaint;
    private final Path mSelectStrokePath;
    private final Paint mStrokePaint;
    private final Path mStrokePath;
    private final TitleTabLayout mTabLayout;
    private final f[] mTabSizes;
    private final TitleTabItemView[] mTabViews;
    private int mTabWidth;
    private int maxOffset;
    private int minOffset;
    private ValueAnimator offsetAnimator;
    private int pathOffset;
    private int prePosition;
    private int selectPosition;
    private int showTabCount;

    public TitleTabItemContent(Context context, TitleTabLayout titleTabLayout, boolean z) {
        super(context);
        this.mSelectPositions = new float[]{0.0f, 0.73f, 1.0f};
        this.mLinePaint = new Paint(1);
        this.mStrokePaint = new Paint(1);
        this.mStrokePath = new Path();
        this.mBgPaint = new Paint(1);
        this.mBgPath = new Path();
        this.mSelectPaint = new Paint(1);
        this.mSelectPath = new Path();
        this.mSelectStrokePaint = new Paint(1);
        this.mSelectStrokePath = new Path();
        this.mTabWidth = 96;
        this.mTabViews = new TitleTabItemView[4];
        this.mTabSizes = new f[4];
        this.isUseSpread = z;
        this.mTabLayout = titleTabLayout;
        initPaint();
        this.mTabWidth = z ? 96 : 92;
        for (int i2 = 0; i2 < 4; i2++) {
            TitleTabItemView titleTabItemView = new TitleTabItemView(context, z) { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemView
                public void onTabClick(TitleTabItem titleTabItem) {
                    super.onTabClick(titleTabItem);
                    TitleTabItemContent.this.checkTabClick(titleTabItem.getSelectPosition(), true);
                }
            };
            f fVar = new f(this.mTabWidth, -1);
            addView(titleTabItemView, fVar.i(titleTabItemView));
            this.mTabViews[i2] = titleTabItemView;
            this.mTabSizes[i2] = fVar;
        }
    }

    private void drawBg(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.minOffset = getPaddingLeft();
        int max = Math.max(this.mTabViews[0].getWidth(), this.mTabViews[1].getWidth());
        this.maxOffset = (width - this.minOffset) - max;
        int i2 = this.selectPosition;
        if (this.currentWidth != width) {
            setStyle(height);
            this.currentWidth = width;
            int i3 = this.minOffset;
            this.mSelectPath.reset();
            float f2 = i3;
            float f3 = max;
            int i4 = height - i3;
            float f4 = i4;
            float f5 = (i4 - i3) >> 1;
            g.g(0.0f, f2, f3, f4, f5, this.mSelectPath);
            int left = getLeft(i2);
            this.currentOffset = left;
            this.pathOffset = left;
            this.mSelectPath.offset(left, 0.0f);
            float d = d.d(1) + 0.4f;
            this.mStrokePaint.setStrokeWidth(d);
            float f6 = d / 2.0f;
            this.mStrokePath.reset();
            g.g(f6, f6, width - f6, height - f6, height >> 1, this.mStrokePath);
            this.mSelectStrokePaint.setStrokeWidth(d);
            this.mSelectStrokePath.reset();
            g.g(-f6, f2 - f6, f3 + f6, f4 + f6, f5, this.mSelectStrokePath);
            this.mSelectStrokePath.offset(this.pathOffset, 0.0f);
        }
        int min = Math.min(this.maxOffset, Math.max(this.currentOffset, this.minOffset));
        int i5 = min - this.pathOffset;
        this.pathOffset = min;
        float f7 = i5;
        this.mSelectPath.offset(f7, 0.0f);
        this.mSelectStrokePath.offset(f7, 0.0f);
        this.mBgPath.reset();
        g.g(0.0f, 0.0f, width, height, height >> 1, this.mBgPath);
        canvas.drawPath(this.mBgPath, this.mBgPaint);
        canvas.drawPath(this.mSelectPath, this.mSelectPaint);
        canvas.drawPath(this.mStrokePath, this.mStrokePaint);
        canvas.drawPath(this.mSelectStrokePath, this.mSelectStrokePaint);
    }

    private int getLeft(int i2) {
        int paddingLeft = getPaddingLeft();
        for (int i3 = 0; i3 < 4; i3++) {
            TitleTabItemView titleTabItemView = this.mTabViews[i3];
            if (titleTabItemView.matchView(i2)) {
                return Math.min(titleTabItemView.getLeft(), paddingLeft);
            }
            paddingLeft += titleTabItemView.getVisibility() == 0 ? this.mTabSizes[i3].v() : 0;
        }
        return 0;
    }

    private void initPaint() {
        this.mLinePaint.setColor(872033305);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setColor(0);
        this.mSelectStrokePaint.setStyle(Paint.Style.STROKE);
        this.mSelectStrokePaint.setColor(167772160);
        this.mBgPaint.setColor(284830745);
        this.mSelectPaint.setColor(536870911);
    }

    private void resetSelectPaint(float f2) {
        int[] tabSelectColor = TitleTabManager.getInstance().getTitleTabInfo().getTabSelectColor();
        if (tabSelectColor.length == 1) {
            this.mSelectPaint.setColor(tabSelectColor[0]);
            this.mSelectPaint.setShader(null);
        } else if (tabSelectColor.length > 1) {
            this.mSelectPaint.setAlpha(255);
            float[] fArr = this.mSelectPositions;
            float[] fArr2 = fArr.length != tabSelectColor.length ? null : fArr;
            int i2 = this.minOffset;
            this.mSelectPaint.setShader(new LinearGradient(0.0f, i2, 0.0f, f2 - i2, tabSelectColor, fArr2, Shader.TileMode.CLAMP));
        }
    }

    private void setTextInfo(int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.mTabViews[i3].refreshTextInfo(i2);
        }
    }

    public void changeSelect(final int i2, boolean z) {
        ValueAnimator valueAnimator = this.offsetAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.isUseSpread) {
            if (this.selectPosition != i2) {
                this.selectPosition = i2;
                onTabChanged(i2);
            }
            setTextInfo(this.selectPosition);
        } else if (i2 == getSelectPosition()) {
            this.selectPosition = i2;
            this.currentOffset = getLeft(i2);
            setTextInfo(i2);
            postInvalidate();
        } else {
            this.prePosition = this.selectPosition;
            if (z) {
                this.minOffset = 0;
                this.maxOffset = 0;
                this.currentWidth = 0;
            } else {
                int i3 = this.maxOffset;
                int i4 = this.minOffset;
                if (i3 != i4) {
                    int min = Math.min(i3, Math.max(this.currentOffset, i4));
                    this.currentOffset = min;
                    ValueAnimator ofInt = ValueAnimator.ofInt(min, getLeft(i2));
                    this.offsetAnimator = ofInt;
                    ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent.2
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                            TitleTabItemContent.this.currentOffset = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                            TitleTabItemContent.this.postInvalidate();
                        }
                    });
                    this.offsetAnimator.addListener(new com.jingdong.app.mall.home.r.a.d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent.3
                        @Override // com.jingdong.app.mall.home.r.a.d
                        protected void onEnd(Animator animator, boolean z2) {
                            if (z2) {
                                return;
                            }
                            TitleTabItemContent titleTabItemContent = TitleTabItemContent.this;
                            titleTabItemContent.selectPosition = titleTabItemContent.onTabChanged(JDHomeFragment.Q0() ? i2 : TitleTabItemContent.this.prePosition);
                            TitleTabItemContent titleTabItemContent2 = TitleTabItemContent.this;
                            titleTabItemContent2.changeSelect(titleTabItemContent2.selectPosition, false);
                        }
                    });
                    this.offsetAnimator.setDuration(200L);
                    this.offsetAnimator.start();
                }
            }
            postInvalidate();
        }
    }

    public void changeTabText() {
        this.showTabCount = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            TitleTabItem tabAt = TitleTabManager.getInstance().getTitleTabInfo().getTabAt(i2);
            int initCount = TitleTabManager.getInstance().getTitleTabInfo().getInitCount();
            TitleTabItemView titleTabItemView = this.mTabViews[i2];
            f fVar = this.mTabSizes[i2];
            if (tabAt.isCanShow()) {
                titleTabItemView.bindTabItem(tabAt);
                titleTabItemView.setVisibility(0);
                if (this.isUseSpread && initCount == 2 && this.showTabCount == 0) {
                    fVar.E(0, 0, 8, 0);
                } else {
                    fVar.E(0, 0, 0, 0);
                }
                f.d(titleTabItemView, fVar, true);
                this.showTabCount++;
            } else {
                titleTabItemView.setVisibility(8);
            }
        }
    }

    public void checkSize(int i2) {
        changeSelect(i2, false);
        for (int i3 = 0; i3 < 4; i3++) {
            f.d(this.mTabViews[i3], this.mTabSizes[i3], true);
        }
    }

    public void checkTabClick(int i2, boolean z) {
        checkTabClick(i2, z, false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (!this.isUseSpread) {
            drawBg(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public PointF getHourlyPointF(int i2) {
        TitleTabItemView titleTabItemView;
        int i3 = 0;
        while (true) {
            if (i3 >= 4) {
                titleTabItemView = null;
                break;
            }
            titleTabItemView = this.mTabViews[i3];
            if (titleTabItemView.matchView(i2)) {
                break;
            }
            i3++;
        }
        if (com.jingdong.app.mall.home.o.a.f.W(titleTabItemView) == null) {
            return null;
        }
        return new PointF((r6.left + r6.right) / 2.0f, r6.bottom + d.d(4));
    }

    protected int getSelectPosition() {
        return 0;
    }

    public int getTabWidth() {
        return d.d(this.mTabWidth);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean interceptTabClick(int i2) {
        return false;
    }

    public void notifyHourlyGoName() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.mTabViews[i2].refreshText();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int onTabChanged(int i2) {
        return 0;
    }

    public void setStyle(int i2) {
        resetSelectPaint(i2);
        this.mBgPaint.setColor(TitleTabManager.getInstance().getTitleTabInfo().getTabUnSelectColor());
        this.mStrokePaint.setColor(TitleTabManager.getInstance().getTitleTabInfo().getTabStrokeColor());
        this.mLinePaint.setColor(TitleTabManager.getInstance().getTitleTabInfo().getTabLineColor());
    }

    public void checkTabClick(int i2, boolean z, boolean z2) {
        if (z && interceptTabClick(i2)) {
            return;
        }
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null && z2 && i2 == -1) {
            if (!JDHomeFragment.Q0()) {
                return;
            }
            setAlpha(1.0f);
            z0.k1();
        }
        if (this.mTabLayout.isVisible()) {
            if (i2 == -1) {
                TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().setSelectType(z0, z2);
            }
            changeSelect(i2, false);
        }
    }
}
