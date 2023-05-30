package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.view.b.c;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.linefloor.base.BaseLineLayout;
import com.jingdong.app.mall.home.floor.view.linefloor.base.a;
import com.jingdong.app.mall.home.floor.view.widget.FitTopImage;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.r.f.a.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class MallFloorLineMore extends BaseMallFloor<p> {
    public static final int CHILD_RADII = 24;
    public static final int HALF_SUM = 2;
    public static final int NORMAL_HEIGHT = 288;
    public static final int WEIGHT_SUM = 4;
    private boolean isV911;
    private float[] mBgPositions;
    private f mBgSize;
    protected List<BaseLineLayout> mCacheViewList;
    protected Context mContext;
    private Paint mDividerPaint;
    private f mDividerSize;
    private View mDividerView;
    private FitTopImage mFloorBg;
    protected LinearLayout mFloorContent;
    private BaseLineLayout mLeftLayout;
    private c mLineType;
    private Paint mMaskPaint;
    private Path mMaskPath;
    private float mMaskSize;
    private BaseLineLayout mRightLayout;
    private int preDividerHeight;
    private String preTypeTag;
    private static int[] sDefBgColors = {-1};
    private static final float[] BG_RADII = new float[4];

    public MallFloorLineMore(Context context, c cVar) {
        super(context);
        this.mCacheViewList = new ArrayList();
        this.mDividerPaint = new Paint(1);
        this.mBgPositions = new float[]{0.0f, 0.2f, 0.25f, 1.0f};
        this.mMaskPaint = new Paint(1);
        this.mMaskPath = new Path();
        this.mBgSize = new f(-1, -1);
        this.mContext = context;
        this.mLineType = cVar;
        this.mMaskPaint.setColor(-1);
        this.mMaskPaint.setAntiAlias(true);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mFloorContent = linearLayout;
        linearLayout.setOrientation(0);
        this.mFloorContent.setWeightSum(4.0f);
        addView(this.mFloorContent, new RelativeLayout.LayoutParams(-1, -1));
        if (cVar.getDividerWidth() > 0) {
            this.mDividerSize = new f(cVar.getDividerWidth(), -1);
        }
    }

    private void draw911Divider(Canvas canvas) {
        int height = getHeight();
        if (height != this.preDividerHeight) {
            this.preDividerHeight = height;
            this.mDividerPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), 0, (int) IconFloorEntity.BGCOLOR_DEFAULT, Shader.TileMode.CLAMP));
        }
        float d = d.d(2);
        float width = (getWidth() >> 1) - d;
        canvas.drawRect(width, 0.0f, width + d, getHeight(), this.mDividerPaint);
    }

    private void draw911Mask(Canvas canvas) {
        float max = Math.max(d.d(12), getMaxRadii());
        if (this.mMaskSize != max || this.mMaskPath.isEmpty()) {
            this.mMaskSize = max;
            float h2 = g.h(max);
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            this.mMaskPath.reset();
            float f2 = paddingLeft;
            this.mMaskPath.moveTo(f2, 0.0f);
            this.mMaskPath.lineTo(f2, max);
            g.c(f2, 0.0f, max, h2, this.mMaskPath);
            float width = getWidth() - paddingRight;
            this.mMaskPath.lineTo(width - max, 0.0f);
            g.f(0.0f, width, max, h2, this.mMaskPath);
            float f3 = -5;
            this.mMaskPath.lineTo(width, f3);
            this.mMaskPath.lineTo(f2, f3);
            this.mMaskPath.close();
        }
        canvas.drawPath(this.mMaskPath, this.mMaskPaint);
    }

    private String getBgUrl() {
        if (this.mFloorBindElement.mParentModel.getJsonInt("showSubTitle", 0) == 1) {
            return this.mFloorBindElement.getJsonString("bgImg1");
        }
        return this.mFloorBindElement.getJsonString("bgImg");
    }

    private void resetClip() {
        e.d(this.mFloorBg, 0);
        e.d(this.mFloorContent, 0);
    }

    private void set911BgColor(Paint paint) {
        int[] n2 = m.n(this.mFloorBindElement.getJsonString("subBgColor"), sDefBgColors, true);
        int[] iArr = new int[4];
        iArr[0] = n2[0];
        iArr[1] = n2.length > 1 ? n2[1] : -1;
        iArr[2] = -1;
        iArr[3] = -1;
        paint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), iArr, this.mBgPositions, Shader.TileMode.CLAMP));
    }

    private void setFloorBg() {
        String bgUrl = getBgUrl();
        FitTopImage fitTopImage = this.mFloorBg;
        if (fitTopImage == null) {
            FitTopImage fitTopImage2 = new FitTopImage(this.mContext);
            this.mFloorBg = fitTopImage2;
            addView(fitTopImage2, 0, this.mBgSize.u(fitTopImage2));
        } else {
            f.d(fitTopImage, this.mBgSize, true);
        }
        if (!TextUtils.isEmpty(bgUrl)) {
            this.mFloorBg.setVisibility(0);
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.mFloorBg, bgUrl);
            return;
        }
        this.mFloorBg.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void afterDrawBgMarginColor(Canvas canvas, Paint paint) {
        super.afterDrawBgMarginColor(canvas, paint);
        if (this.mDividerSize == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        if (isFirstLineFloor()) {
            canvas.drawRect(getPaddingLeft(), 0.0f, width - getPaddingRight(), d.d(24), paint);
        }
        if (isLastLineFloor()) {
            canvas.drawRect(getPaddingLeft(), height - d.d(24), width, height, paint);
        }
        canvas.drawRect((width - this.mDividerSize.v()) >> 1, 0.0f, r0 + this.mDividerSize.v(), height, paint);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void checkFloorClip(float[] fArr) {
        super.checkFloorClip(fArr);
        if (fArr == null || fArr.length < 4) {
            resetClip();
            return;
        }
        int max = isFirstLineFloor() ? (int) Math.max(fArr[0], fArr[1]) : 0;
        int max2 = isLastLineFloor() ? (int) Math.max(fArr[2], fArr[3]) : 0;
        if (max != 0 && max2 != 0) {
            e.d(this.mFloorBg, max - 1);
            e.d(this.mFloorContent, max);
        } else if (max != 0) {
            e.h(this.mFloorBg, max - 1);
            e.h(this.mFloorContent, max);
        } else if (max2 != 0) {
            e.a(this.mFloorBg, max2 - 1);
            e.a(this.mFloorContent, max2);
        } else {
            resetClip();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void cleanUIOnMainThread() {
        LinearLayout linearLayout = this.mFloorContent;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        Iterator<BaseLineLayout> it = this.mCacheViewList.iterator();
        while (it.hasNext()) {
            it.next().w();
        }
        this.mCacheViewList.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (!this.isV911 || isFirstLineFloor()) {
            return;
        }
        draw911Mask(canvas);
    }

    public float getMaxRadii() {
        float[] shapedFloorRadii = getShapedFloorRadii();
        return Math.max(shapedFloorRadii[0], shapedFloorRadii[2]);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public float[] getShapedFloorRadii() {
        float[] r = ((p) this.mPresenter).r();
        if (r.length == 4) {
            float[] fArr = BG_RADII;
            float max = Math.max(r[0], r[1]);
            fArr[1] = max;
            fArr[0] = max;
            float max2 = Math.max(r[2], r[3]);
            fArr[3] = max2;
            fArr[2] = max2;
        } else {
            float[] fArr2 = BG_RADII;
            float d = d.d(24);
            fArr2[3] = d;
            fArr2[2] = d;
            fArr2[1] = d;
            fArr2[0] = d;
        }
        return BG_RADII;
    }

    public int getTopOverlay() {
        if (this.mLineType.useOverlay() && ((p) this.mPresenter).Z()) {
            return ((p) this.mPresenter).U();
        }
        return 0;
    }

    protected void initLineItem() {
        BaseLineLayout lineViewByCache;
        String V = ((p) this.mPresenter).V();
        List<a> T = ((p) this.mPresenter).T();
        if (!V.equals(this.preTypeTag)) {
            cleanUIOnMainThread();
        }
        this.preTypeTag = V;
        int size = T.size();
        int size2 = this.mCacheViewList.size();
        boolean z = size == size2;
        if (!z && size2 > 0) {
            cleanUIOnMainThread();
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            a aVar = T.get(i4);
            if (z) {
                lineViewByCache = this.mCacheViewList.get(i4);
            } else {
                if (this.mDividerSize != null && i2 == 2) {
                    View view = new View(this.mContext);
                    this.mDividerView = view;
                    this.mFloorContent.addView(view, this.mDividerSize.i(view));
                }
                com.jingdong.app.mall.home.floor.view.b.a k2 = aVar.k();
                i2 += k2.getWeight();
                lineViewByCache = k2.getLineViewByCache(this.mContext, this);
                this.mCacheViewList.add(lineViewByCache);
                this.mFloorContent.addView(lineViewByCache);
            }
            if (i3 == 0) {
                this.mLeftLayout = lineViewByCache;
            } else if (i3 == 2) {
                this.mRightLayout = lineViewByCache;
            }
            lineViewByCache.setTranslationX(0.0f);
            lineViewByCache.q(aVar, this, i4, i3);
            i3 += lineViewByCache.u();
        }
        f.c(this.mDividerView, this.mDividerSize);
    }

    public boolean isFirstLineFloor() {
        return ((p) this.mPresenter).W();
    }

    public boolean isLastLineFloor() {
        return ((p) this.mPresenter).X();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        boolean z = this.mLineType == c.NORMAL;
        this.isV911 = z && m.E(this.mFloorBindElement);
        if (z) {
            com.jingdong.app.mall.home.floor.minitop.opendoor.a.n().j(this, this.mFloorBindElement);
        }
        setFloorBg();
        initLineItem();
    }

    public boolean openDoorTranslation(float f2) {
        boolean z;
        BaseLineLayout baseLineLayout = this.mLeftLayout;
        if (baseLineLayout == null || this.mRightLayout == null) {
            f2 = 0.0f;
            z = false;
        } else {
            z = true;
        }
        if (baseLineLayout != null) {
            baseLineLayout.setTranslationX(-f2);
        }
        BaseLineLayout baseLineLayout2 = this.mRightLayout;
        if (baseLineLayout2 != null) {
            baseLineLayout2.setTranslationX(f2);
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void setBgColor(Paint paint) {
        if (this.isV911) {
            set911BgColor(paint);
            return;
        }
        paint.setShader(null);
        super.setBgColor(paint);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useRoundBgColor() {
        c cVar = c.OTHERS;
        c cVar2 = this.mLineType;
        return (cVar == cVar2 || c.FULL == cVar2 || !super.useRoundBgColor()) ? false : true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public p createPresenter() {
        return new p();
    }
}
