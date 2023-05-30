package com.jingdong.app.mall.home.floor.view.baseui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.h.c;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.n;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.b;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class BaseMallColorFloor<P extends b> extends RelativeLayout implements IMallFloorUI, IMallFloorTop, com.jingdong.app.mall.home.widget.b, c {
    private static final String TAG = BaseMallColorFloor.class.getSimpleName();
    private Paint dividerPaint;
    private List<Path> dividerPaths;
    private boolean isAttachWindow;
    private AtomicBoolean isFloorRecycle;
    protected AtomicBoolean isFloorStatic;
    private Drawable mBackground;
    private float[] mBgRadii;
    private final com.jingdong.app.mall.home.floor.common.h.b mClientClickInfo;
    protected Context mContext;
    protected d mFloorBindElement;
    private int mHomeBgColor;
    @NotNull
    protected P mPresenter;
    private ShapeDrawable mRoundBg;
    private LinkedBlockingQueue<BaseMallColorFloor<P>.WaitMainThreadParams> mainThreadParamsQueue;
    private Paint paddingPaint;
    private Path paddingPath;
    private int preHeight;
    private Rect prePadding;
    private List<Path> roundPaths;

    /* loaded from: classes4.dex */
    public class WaitMainThreadParams {
        Object[] args;
        Class[] argsClass;
        String strMethodName;

        WaitMainThreadParams(String str, Class[] clsArr, Object[] objArr) {
            BaseMallColorFloor.this = r1;
            this.strMethodName = "";
            this.argsClass = null;
            this.args = null;
            this.strMethodName = str;
            if (clsArr != null) {
                this.argsClass = (Class[]) clsArr.clone();
            }
            if (objArr != null) {
                this.args = (Object[]) objArr.clone();
            }
        }
    }

    public BaseMallColorFloor(Context context) {
        super(context);
        this.paddingPath = new Path();
        this.dividerPaths = new CopyOnWriteArrayList();
        this.roundPaths = new CopyOnWriteArrayList();
        this.prePadding = new Rect();
        this.preHeight = 0;
        this.paddingPaint = new Paint(1);
        this.dividerPaint = new Paint(1);
        this.isFloorRecycle = new AtomicBoolean(false);
        this.mBgRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        this.isAttachWindow = false;
        this.isFloorStatic = new AtomicBoolean(true);
        this.mClientClickInfo = new com.jingdong.app.mall.home.floor.common.h.b();
        this.mainThreadParamsQueue = new LinkedBlockingQueue<>();
        this.mContext = context;
        P createPresenter = createPresenter();
        this.mPresenter = createPresenter;
        if (createPresenter != null) {
            createPresenter.b(this);
            return;
        }
        throw new NullPointerException("presenter is null ! you should create a mPresenter.");
    }

    private void drawBgMarginColor(Canvas canvas) {
        if (!useBgMarginColor() || getLayoutHeight() <= 0) {
            return;
        }
        Paint paint = this.paddingPaint;
        Paint paint2 = this.mFloorBindElement.s;
        Paint paint3 = paint2 != null ? paint2 : paint;
        if ((((paint3.getColor() & (-16777216)) == 0 || (paint3.getColor() ^ this.mHomeBgColor) == 0) ? false : true) || paint3.getShader() != null) {
            canvas.drawRect(0.0f, 0.0f, com.jingdong.app.mall.home.floor.common.d.f9279g, getHeight(), paint3);
        }
    }

    private void drawDividers(Canvas canvas) {
        Paint paint;
        if (isShowItemDivider()) {
            Paint paint2 = this.dividerPaint;
            if (((paint2.getColor() & (-16777216)) == 0) && (paint = this.mFloorBindElement.s) != null) {
                paint2 = paint;
            }
            Iterator<Path> it = this.dividerPaths.iterator();
            while (it.hasNext()) {
                canvas.drawPath(it.next(), paint2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:104:0x0051, code lost:
        if (r6 == r9.right) goto L106;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void drawMarginColor(Canvas canvas) {
        if (getLayoutHeight() <= 0) {
            return;
        }
        Paint paint = this.paddingPaint;
        Paint paint2 = this.mFloorBindElement.s;
        if (paint2 != null) {
            paint = paint2;
        }
        boolean z = (paint.getShader() == null && ((paint.getColor() & (-16777216)) == 0 || (paint.getColor() ^ this.mHomeBgColor) == 0)) ? false : true;
        if (hasPadding()) {
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int height = getHeight();
            int i2 = com.jingdong.app.mall.home.floor.common.d.f9279g;
            if (this.preHeight == height) {
                Rect rect = this.prePadding;
                if (paddingLeft == rect.left) {
                }
            }
            this.prePadding.set(paddingLeft, 0, paddingRight, 0);
            this.preHeight = height;
            this.paddingPath.reset();
            this.paddingPath.addRect(0.0f, 0.0f, this.prePadding.left, this.preHeight, Path.Direction.CW);
            this.paddingPath.addRect(i2 - this.prePadding.right, 0.0f, i2, this.preHeight, Path.Direction.CW);
            if (z) {
                canvas.drawPath(this.paddingPath, paint);
                afterDrawBgMarginColor(canvas, paint);
            }
        } else {
            this.prePadding.set(0, 0, 0, 0);
        }
        if (useRoundMarginColor() && z) {
            Iterator<Path> it = this.roundPaths.iterator();
            while (it.hasNext()) {
                canvas.drawPath(it.next(), paint);
            }
        }
    }

    private void drawRoundColorBg(Canvas canvas) {
        if (useRoundBgColor()) {
            if (this.mRoundBg == null) {
                ShapeDrawable shapeDrawable = new ShapeDrawable();
                this.mRoundBg = shapeDrawable;
                shapeDrawable.getPaint().setAntiAlias(true);
            }
            setBgColor(this.mRoundBg.getPaint());
            this.mRoundBg.setBounds(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight());
            this.mRoundBg.setShape(new RoundRectShape(this.mBgRadii, null, null));
            this.mRoundBg.draw(canvas);
        }
    }

    private Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        if (Log.D) {
            Log.i(TAG, "getMethod-" + str + ";" + cls + ";" + clsArr);
        }
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException e2) {
            if (cls.getSuperclass() == null) {
                e2.printStackTrace();
            }
            return getMethod(cls.getSuperclass(), str, clsArr);
        }
    }

    private Rect getPaddingRect() {
        d dVar = this.mFloorBindElement;
        if (dVar == null) {
            return null;
        }
        return dVar.mPaddingRect;
    }

    private boolean hasPadding() {
        return ((getPaddingLeft() + getPaddingTop()) + getPaddingRight()) + getPaddingBottom() > 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addChildrenForAccessibility(ArrayList<View> arrayList) {
        try {
            super.addChildrenForAccessibility(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        try {
            super.addView(view, i2, layoutParams);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    public void afterDrawBgMarginColor(Canvas canvas, Paint paint) {
    }

    public void beforeViewBind() {
    }

    public boolean checkAndReportExpo() {
        boolean isFloorDisplay = isFloorDisplay();
        if (isFloorDisplay) {
            a.i().f(true, getFloorPos(), this.mPresenter.i(), getSubFloorPos());
            a.i().e(true, this.mPresenter.g(), this.mPresenter.h());
        }
        return isFloorDisplay;
    }

    public void checkCircularDependencies(View view, int i2) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            int[] rules = layoutParams2.getRules();
            if (rules.length > 1 && rules[1] == i2) {
                layoutParams2.addRule(1, 0);
            }
            if (rules.length > 0 && rules[0] == i2) {
                layoutParams2.addRule(0, 0);
            }
            if (rules.length > 3 && rules[3] == i2) {
                layoutParams2.addRule(3, 0);
            }
            if (rules.length > 2 && rules[2] == i2) {
                layoutParams2.addRule(2, 0);
            }
            view.setLayoutParams(layoutParams2);
        }
    }

    public void checkFloorClip(float[] fArr) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void cleanUI() {
        if (isMainThread()) {
            cleanUIOnMainThread();
        } else {
            postToMainThread("cleanUIOnMainThread", null, new Object[0]);
        }
    }

    public void cleanUIOnMainThread() {
        removeAllViews();
    }

    public void createFloorCornerShapedPath(float[] fArr, List<Path> list) {
        int layoutLeftRightMargin;
        float f2;
        list.clear();
        this.mBgRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        if (this.mFloorBindElement == null || fArr == null || fArr.length < 4 || (layoutLeftRightMargin = getLayoutLeftRightMargin()) <= 0) {
            return;
        }
        float f3 = fArr[0];
        if (!this.mFloorBindElement.isSubFirst()) {
            f3 = 0.0f;
        }
        float[] fArr2 = this.mBgRadii;
        fArr2[1] = f3;
        fArr2[0] = f3;
        float f4 = fArr[1];
        if (!this.mFloorBindElement.isSubFirst()) {
            f4 = 0.0f;
        }
        float[] fArr3 = this.mBgRadii;
        fArr3[3] = f4;
        fArr3[2] = f4;
        float f5 = fArr[2];
        if (!this.mFloorBindElement.isSubLast()) {
            f5 = 0.0f;
        }
        float[] fArr4 = this.mBgRadii;
        fArr4[5] = f5;
        fArr4[4] = f5;
        float f6 = fArr[3];
        if (!this.mFloorBindElement.isSubLast()) {
            f6 = 0.0f;
        }
        float[] fArr5 = this.mBgRadii;
        fArr5[7] = f6;
        fArr5[6] = f6;
        if (useRoundMarginColor()) {
            int layoutHeight = getLayoutHeight();
            int i2 = com.jingdong.app.mall.home.floor.common.d.f9279g;
            if (f3 > 0.0f) {
                Path path = new Path();
                path.moveTo((float) (layoutLeftRightMargin - 1), -1.0f);
                float f7 = layoutLeftRightMargin;
                float f8 = (f3 + 2.0f) * 2.0f;
                path.arcTo(new RectF(f7, 0.0f, f7 + f8, f8), 180.0f, 90.0f, false);
                path.close();
                list.add(path);
            }
            if (f4 > 0.0f) {
                f2 = 2.0f;
                Path path2 = new Path();
                path2.moveTo(r13 + 1, -1.0f);
                float f9 = i2 - layoutLeftRightMargin;
                float f10 = (f4 + 2.0f) * 2.0f;
                path2.arcTo(new RectF(f9 - f10, 0.0f, f9, f10), 270.0f, 90.0f, false);
                path2.close();
                list.add(path2);
            } else {
                f2 = 2.0f;
            }
            if (f5 > 0.0f) {
                float f11 = f5 + f2;
                Path path3 = new Path();
                path3.moveTo(r10 + 1, layoutHeight + 1);
                float f12 = i2 - layoutLeftRightMargin;
                float f13 = f11 * 2.0f;
                float f14 = layoutHeight;
                path3.arcTo(new RectF(f12 - f13, f14 - f13, f12, f14), 0.0f, 90.0f, false);
                path3.close();
                list.add(path3);
            }
            if (f6 > 0.0f) {
                Path path4 = new Path();
                path4.moveTo((float) (layoutLeftRightMargin - 1), layoutHeight + 1);
                float f15 = layoutLeftRightMargin;
                float f16 = layoutHeight;
                float f17 = (f6 + 2.0f) * 2.0f;
                path4.arcTo(new RectF(f15, f16 - f17, f17 + f15, f16), 90.0f, 90.0f, false);
                path4.close();
                list.add(path4);
            }
        }
    }

    protected abstract P createPresenter();

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        try {
            drawBackground(canvas);
            if (!com.jingdong.app.mall.home.state.dark.a.h()) {
                drawBgMarginColor(canvas);
                drawDividers(canvas);
                drawMarginColor(canvas);
            }
        } catch (Exception e2) {
            f.s0(this, e2);
        }
        try {
            super.dispatchDraw(canvas);
        } catch (Exception e3) {
            f.s0(this, e3);
        }
    }

    protected void drawBackground(Canvas canvas) {
        try {
            if (com.jingdong.app.mall.home.state.dark.a.h() && unUseSelfBg()) {
                drawRoundColorBg(canvas);
                return;
            }
            Drawable drawable = this.mBackground;
            if (drawable == null) {
                drawRoundColorBg(canvas);
                return;
            }
            drawable.setBounds(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight());
            drawable.draw(canvas);
        } catch (Exception e2) {
            f.s0(this, e2);
        }
    }

    public final d getBindItem() {
        return this.mFloorBindElement;
    }

    @Override // com.jingdong.app.mall.home.floor.common.h.c
    public com.jingdong.app.mall.home.floor.common.h.b getClickInfo() {
        try {
            this.mClientClickInfo.f(getWidth());
            this.mClientClickInfo.d(getHeight());
            this.mClientClickInfo.e(getLayoutTop());
            return this.mClientClickInfo;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public View getContentView() {
        return this;
    }

    protected int getDarkBgColor() {
        return com.jingdong.app.mall.home.state.dark.a.g(false);
    }

    public abstract String getFloorId();

    public final h getFloorNewModel() {
        d dVar = this.mFloorBindElement;
        if (dVar == null) {
            return null;
        }
        return dVar.mParentModel;
    }

    public final int getFloorPos() {
        h floorNewModel = getFloorNewModel();
        if (floorNewModel == null) {
            return 0;
        }
        return floorNewModel.f10696h;
    }

    public final int getFloorPosition() {
        d dVar = this.mFloorBindElement;
        if (dVar == null) {
            return 0;
        }
        return dVar.t;
    }

    public int getItemDividerColor() {
        return this.mPresenter.k();
    }

    public List<Path> getItemDividerPaths() {
        return this.mPresenter.l();
    }

    public int getLayoutHeight() {
        d dVar = this.mFloorBindElement;
        if (dVar == null || !dVar.isShowFloor()) {
            return 0;
        }
        int i2 = this.mFloorBindElement.mFloorHeight;
        return i2 > 0 ? i2 : this.mPresenter.n();
    }

    public int getLayoutLeftRightMargin() {
        return this.mPresenter.o();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop
    public int getLayoutMaxHeight() {
        return getLayoutHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorTop
    public final int getLayoutTop() {
        h hVar;
        d dVar = this.mFloorBindElement;
        if (dVar == null || (hVar = dVar.mParentModel) == null) {
            return 0;
        }
        return hVar.X + dVar.mTopParent;
    }

    @NotNull
    public P getPresenter() {
        return this.mPresenter;
    }

    public float[] getShapedFloorRadii() {
        return this.mPresenter.r();
    }

    public final int getSubFloorPos() {
        d dVar = this.mFloorBindElement;
        if (dVar == null) {
            return 0;
        }
        return dVar.mSubPosition;
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void hideProgress() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public Object initFloorViewItem(com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, Object obj) {
        com.jingdong.app.mall.home.a.u.e(fVar.s());
        if (unUsePostWaitThread()) {
            initFloorViewItemOnMainThread(fVar, i2, i3, i4, obj);
            return null;
        }
        Class cls = Integer.TYPE;
        postToWaitMainThread("initFloorViewItemOnMainThread", new Class[]{com.jingdong.app.mall.home.r.e.f.class, cls, cls, cls, Object.class}, fVar, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), obj);
        return null;
    }

    public Object initFloorViewItemOnMainThread(com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, Object obj) {
        return null;
    }

    protected boolean isAttachTopFloor() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public final boolean isAttachWindow() {
        return this.isAttachWindow;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public final boolean isCurrentData() {
        d dVar = this.mFloorBindElement;
        return dVar != null && dVar.isLastData();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public final boolean isFloorDisplay() {
        if (isFloorRecycle()) {
            return false;
        }
        return m.I(this, com.jingdong.app.mall.home.a.f8560i + (isAttachTopFloor() ? 0 : com.jingdong.app.mall.home.a.j()), com.jingdong.app.mall.home.a.f8562k, false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public boolean isFloorRecycle() {
        return this.isFloorRecycle.get();
    }

    public final boolean isFloorStatic() {
        return this.isFloorStatic.get();
    }

    public final boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public boolean isRetain() {
        return false;
    }

    public boolean isShowItemDivider() {
        return true;
    }

    public void notifyLayoutParamsChange() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            setLayoutParams(layoutParams);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachWindow = true;
        this.mPresenter.b(this);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onFloorInitEnd() {
        float[] shapedFloorRadii = getShapedFloorRadii();
        createFloorCornerShapedPath(shapedFloorRadii, this.roundPaths);
        this.dividerPaint.setColor(getItemDividerColor());
        this.dividerPaths.clear();
        this.dividerPaths.addAll(getItemDividerPaths());
        checkFloorClip(shapedFloorRadii);
        postInvalidate();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onLoadingBgComplete(String str, Bitmap bitmap) {
        if (isMainThread()) {
            onLoadingBgCompleteOnMainThread(str, bitmap);
        } else {
            postToMainThread("onLoadingBgCompleteOnMainThread", new Class[]{String.class, Bitmap.class}, str, bitmap);
        }
    }

    protected void onLoadingBgCompleteOnMainThread(String str, Bitmap bitmap) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onLoadingBgFailed(String str, JDFailReason jDFailReason) {
        if (isMainThread()) {
            onLoadingBgFailedOnMainThread(str, jDFailReason);
        } else {
            postToMainThread("onLoadingBgFailedOnMainThread", new Class[]{String.class, JDFailReason.class}, str, jDFailReason);
        }
    }

    protected void onLoadingBgFailedOnMainThread(String str, JDFailReason jDFailReason) {
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (l.q()) {
            super.onMeasure(i2, i3);
            return;
        }
        try {
            setMeasuredDimension(i2, i3);
            super.onMeasure(i2, i3);
        } catch (Throwable unused) {
            String format = String.format("home floor %s onMeasure error. floor id: %s. sub floor pos:%d", getClass().getName(), getFloorId(), Integer.valueOf(getSubFloorPos()));
            l.i(format);
            System.err.println(format);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onParseEnd() {
        int f2 = this.mPresenter.f();
        int i2 = com.jingdong.app.mall.home.a.x;
        this.mHomeBgColor = i2;
        setPaddingColor(f2, i2);
        this.mFloorBindElement.l();
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public final void onPreInitView(d dVar, boolean z) {
        this.mPresenter.N(dVar, z);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onRefreshView() {
        if (isMainThread()) {
            onRefreshViewOnMainThread();
        } else {
            postToMainThread("onRefreshViewOnMainThread", null, new Object[0]);
        }
    }

    public void onRefreshViewOnMainThread() {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public final void onReleaseView() {
        this.mPresenter.e();
        this.mPresenter = null;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onSetVisible(boolean z) {
        if (isMainThread()) {
            onSetVisibleOnMainThread(z);
        } else {
            postToMainThread("onSetVisibleOnMainThread", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    protected void onSetVisibleOnMainThread(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public final void onUseView() {
        this.mPresenter.b(this);
        this.mPresenter.d();
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public final void onViewBind(d dVar) {
        this.isFloorRecycle.set(false);
        com.jingdong.app.mall.home.v.c.a.a(this);
        beforeViewBind();
        if (dVar == null) {
            return;
        }
        if (this.mFloorBindElement != dVar || dVar.isNeedRefresh || dVar.getAsyncTime() > 0) {
            setFloorBindElements(dVar);
            dVar.isNeedRefresh = false;
            onViewBindData(dVar);
        }
    }

    public void onViewBindData(d dVar) {
    }

    @Override // com.jingdong.app.mall.home.widget.b
    public void onViewRecycle() {
        this.isFloorRecycle.set(true);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            this.isAttachWindow = true;
        }
    }

    public final void postToMainThread(String str, Class[] clsArr, final Object... objArr) {
        if (Log.D) {
            Log.i(TAG, "postToMainThread-" + str);
        }
        getMethod(getClass(), str, clsArr);
        f.E0(new com.jingdong.app.mall.home.o.a.b
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0027: INVOKE 
              (wrap: com.jingdong.app.mall.home.o.a.b : 0x0024: CONSTRUCTOR 
              (r3v0 'this' com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor<P extends com.jingdong.app.mall.home.r.f.a.b> A[IMMUTABLE_TYPE, THIS])
              (r4 I:java.lang.reflect.Method A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'this' com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor<P extends com.jingdong.app.mall.home.r.f.a.b> A[DONT_INLINE, IMMUTABLE_TYPE, THIS])
              (r6v0 'objArr' java.lang.Object[] A[DONT_INLINE])
             A[MD:(com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, java.lang.reflect.Method, java.lang.Object, java.lang.Object[]):void (m), WRAPPED] (LINE:5) call: com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor.1.<init>(com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, java.lang.reflect.Method, java.lang.Object, java.lang.Object[]):void type: CONSTRUCTOR)
             type: STATIC call: com.jingdong.app.mall.home.o.a.f.E0(com.jingdong.app.mall.home.o.a.b):void A[MD:(com.jingdong.app.mall.home.o.a.b):void (m)] (LINE:5) in method: com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor.postToMainThread(java.lang.String, java.lang.Class[], java.lang.Object[]):void, file: classes4.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            this = this;
            boolean r0 = com.jingdong.corelib.utils.Log.D
            if (r0 == 0) goto L1a
            java.lang.String r0 = com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "postToMainThread-"
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.jingdong.corelib.utils.Log.i(r0, r1)
        L1a:
            java.lang.Class r0 = r3.getClass()
            java.lang.reflect.Method r4 = r3.getMethod(r0, r4, r5)
            com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor$1 r5 = new com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor$1
            r5.<init>()
            com.jingdong.app.mall.home.o.a.f.E0(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor.postToMainThread(java.lang.String, java.lang.Class[], java.lang.Object[]):void");
    }

    public final void postToWaitMainThread(String str, Class[] clsArr, Object... objArr) {
        if (!n.k() && !n.e()) {
            if (Log.D) {
                Log.i(TAG, "postToWaitMainThread-" + str);
            }
            this.mainThreadParamsQueue.offer(new WaitMainThreadParams(str, clsArr, objArr));
            return;
        }
        postToMainThread(str, clsArr, objArr);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public final void postWaitMainThreadQue() {
        f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor.2
            {
                BaseMallColorFloor.this = this;
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                if (BaseMallColorFloor.this.mainThreadParamsQueue == null) {
                    return;
                }
                while (BaseMallColorFloor.this.mainThreadParamsQueue.size() > 0) {
                    WaitMainThreadParams waitMainThreadParams = (WaitMainThreadParams) BaseMallColorFloor.this.mainThreadParamsQueue.poll();
                    if (waitMainThreadParams != null) {
                        BaseMallColorFloor.this.postToMainThread(waitMainThreadParams.strMethodName, waitMainThreadParams.argsClass, waitMainThreadParams.args);
                    }
                }
            }
        });
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (usePaddingDrawable()) {
            if (drawable == this.mBackground) {
                return;
            }
            this.mBackground = drawable;
            postInvalidate();
            return;
        }
        super.setBackgroundDrawable(drawable);
    }

    public void setBgColor(Paint paint) {
        paint.setColor(getDarkBgColor());
    }

    public final void setFloorBindElements(d dVar) {
        this.mFloorBindElement = dVar;
    }

    public final void setFloorPos(int i2) {
    }

    @Override // android.view.View
    @Deprecated
    public final void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            return;
        }
        f.n(layoutParams);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        if (getVisibility() == 8) {
            marginLayoutParams.height = 0;
            unUseMargins(marginLayoutParams);
            setMinimumHeight(0);
        } else {
            int layoutLeftRightMargin = getLayoutLeftRightMargin();
            Rect paddingRect = getPaddingRect();
            int layoutHeight = getLayoutHeight();
            if (layoutHeight < 0) {
                layoutHeight = -2;
            }
            marginLayoutParams.height = layoutHeight;
            if (paddingRect == null) {
                setPadding(layoutLeftRightMargin, 0, layoutLeftRightMargin, 0);
            } else {
                setPadding(paddingRect.left, paddingRect.top, paddingRect.right, paddingRect.bottom);
            }
            marginLayoutParams.width = -1;
            unUseMargins(marginLayoutParams);
        }
        super.setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    @Deprecated
    public final void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
    }

    public void setPaddingColor(int i2, int i3) {
        this.paddingPaint.setShader(null);
        Paint paint = this.paddingPaint;
        if (i2 == 0) {
            i2 = i3;
        }
        paint.setColor(i2);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (getVisibility() != i2) {
            super.setVisibility(i2);
            notifyLayoutParamsChange();
        }
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showProgress() {
    }

    @Override // com.jingdong.cleanmvp.presenter.IBaseUI
    public void showToast(String str) {
        ToastUtils.shortToast(getContext(), str);
    }

    protected void unUseMargins(ViewGroup.MarginLayoutParams marginLayoutParams) {
        if (marginLayoutParams != null) {
            marginLayoutParams.setMargins(0, 0, 0, 0);
        }
    }

    public boolean unUsePostWaitThread() {
        return !n.d() && isMainThread();
    }

    protected boolean unUseSelfBg() {
        return true;
    }

    protected boolean useBgMarginColor() {
        return false;
    }

    protected boolean usePaddingDrawable() {
        return true;
    }

    public boolean useRoundBgColor() {
        d dVar = this.mFloorBindElement;
        return dVar != null && dVar.useRoundBg;
    }

    protected boolean useRoundMarginColor() {
        d dVar = this.mFloorBindElement;
        return dVar != null && dVar.useRoundBg;
    }
}
