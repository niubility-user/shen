package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes6.dex */
public class JDSignCircleProgressView extends View {
    private static final float MAX_PROGRESS = 100.0f;
    private Bitmap backGroundBitmap;
    private LinearGradient colorShaderLeft;
    private LinearGradient colorShaderRight;
    int curState;
    public boolean drawArc;
    private Bitmap foreGroundBitmapNormal;
    private Bitmap foreGroundBitmapPressed;
    boolean isWaterEnabled;
    private int mAngleStep;
    private RectF mArcRectF;
    private RectF mBackgroundRectF;
    private int[] mColorScheme1;
    private int[] mColorScheme2;
    private Context mContext;
    private int mCpbForegroundColor;
    private int mCpbMaxAngle;
    private Paint mCpbProgressPaint1;
    private Paint mCpbProgressPaint2;
    private int mCpbStartAngle;
    private int mCpbStrokeWidth;
    private int mCurrentProgress;
    private RectF mForegroundRectF;
    private int marginToBackGroundBitmap;
    private int wholeHeight;
    private int wholeWidth;

    /* loaded from: classes6.dex */
    public enum State {
        NONE,
        INIT,
        PRESSED,
        PRESSED_BACK
    }

    public JDSignCircleProgressView(Context context) {
        super(context);
        this.isWaterEnabled = false;
        this.mCurrentProgress = 0;
        this.drawArc = false;
        this.mAngleStep = 0;
        this.marginToBackGroundBitmap = DPIUtil.dip2px(17.0f);
        this.mCpbStrokeWidth = DPIUtil.dip2px(8.0f);
        this.mCpbStartAngle = -90;
        this.mCpbMaxAngle = R2.attr.additionBottom;
        this.mCpbForegroundColor = -15860867;
        this.mColorScheme1 = new int[]{-961709, -961555, -11690001};
        this.mColorScheme2 = new int[]{-11690001, -15222550, -961709};
        this.mContext = context;
        init();
    }

    private void drawArcColorfulProgress(Canvas canvas) {
        int i2 = this.mAngleStep + this.mCpbStartAngle;
        int i3 = (int) ((this.mCurrentProgress / 100.0f) * this.mCpbMaxAngle);
        if (i3 <= 180) {
            this.mCpbProgressPaint1.setShader(this.colorShaderLeft);
            canvas.drawArc(this.mArcRectF, i2, i3, false, this.mCpbProgressPaint1);
            return;
        }
        this.mCpbProgressPaint2.setShader(this.colorShaderRight);
        canvas.drawArc(this.mArcRectF, -90.0f, 180.0f, false, this.mCpbProgressPaint1);
        canvas.drawArc(this.mArcRectF, 90.0f, i3 - 180, false, this.mCpbProgressPaint2);
    }

    private void drawCpbForeGroundNormalBitmap(Canvas canvas) {
        Bitmap bitmap = this.foreGroundBitmapNormal;
        RectF rectF = this.mForegroundRectF;
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, (Paint) null);
    }

    private void drawCpbForeGroundPressedBitmap(Canvas canvas) {
        Bitmap bitmap = this.foreGroundBitmapPressed;
        RectF rectF = this.mForegroundRectF;
        canvas.drawBitmap(bitmap, rectF.left, rectF.top, (Paint) null);
    }

    private void init() {
        this.backGroundBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.jshop_sign_base);
        this.foreGroundBitmapNormal = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.jshop_sign_butn_normal);
        this.foreGroundBitmapPressed = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.jshop_sign_butn_pressed);
        this.curState = 2004;
        this.mForegroundRectF = new RectF();
        this.mArcRectF = new RectF();
        this.mBackgroundRectF = new RectF();
        initProgressForegroundPaint();
    }

    private void initProgressForegroundPaint() {
        this.mCpbProgressPaint1 = new Paint();
        this.mCpbProgressPaint2 = new Paint();
        this.mCpbProgressPaint1.setAntiAlias(true);
        this.mCpbProgressPaint1.setDither(true);
        this.mCpbProgressPaint1.setStyle(Paint.Style.STROKE);
        this.mCpbProgressPaint1.setStrokeCap(Paint.Cap.ROUND);
        this.mCpbProgressPaint1.setStrokeWidth(this.mCpbStrokeWidth);
        this.mCpbProgressPaint1.setColor(this.mCpbForegroundColor);
        this.mCpbProgressPaint2.setAntiAlias(true);
        this.mCpbProgressPaint2.setDither(true);
        this.mCpbProgressPaint2.setStyle(Paint.Style.STROKE);
        this.mCpbProgressPaint2.setStrokeCap(Paint.Cap.ROUND);
        this.mCpbProgressPaint2.setStrokeWidth(this.mCpbStrokeWidth);
        this.mCpbProgressPaint2.setColor(this.mCpbForegroundColor);
    }

    private void initShader() {
        int[] iArr;
        int[] iArr2 = this.mColorScheme1;
        if (iArr2 == null || iArr2.length == 0 || (iArr = this.mColorScheme2) == null || iArr.length == 0) {
            return;
        }
        float centerX = this.mArcRectF.centerX();
        RectF rectF = this.mArcRectF;
        this.colorShaderLeft = new LinearGradient(centerX, rectF.top, rectF.centerX(), this.mArcRectF.bottom, this.mColorScheme1, (float[]) null, Shader.TileMode.CLAMP);
        float centerX2 = this.mArcRectF.centerX();
        RectF rectF2 = this.mArcRectF;
        this.colorShaderRight = new LinearGradient(centerX2, rectF2.bottom, rectF2.centerX(), this.mArcRectF.top, this.mColorScheme2, (float[]) null, Shader.TileMode.CLAMP);
    }

    public void invalidateUi() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        Log.d("JshopSignCircleProgressView", "onDetachedFromWindow()");
        super.onAttachedToWindow();
    }

    public void onDestroy() {
        Bitmap bitmap = this.backGroundBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.backGroundBitmap.recycle();
        }
        Bitmap bitmap2 = this.foreGroundBitmapNormal;
        if (bitmap2 != null && bitmap2.isRecycled()) {
            this.foreGroundBitmapNormal.recycle();
        }
        Bitmap bitmap3 = this.foreGroundBitmapPressed;
        if (bitmap3 == null || !bitmap3.isRecycled()) {
            return;
        }
        this.foreGroundBitmapPressed.recycle();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Log.d("JshopSignCircleProgressView", "onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.curState != 2001) {
            drawCpbForeGroundNormalBitmap(canvas);
        } else {
            drawCpbForeGroundPressedBitmap(canvas);
        }
        if (this.drawArc) {
            drawArcColorfulProgress(canvas);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (z) {
            this.wholeWidth = getWidth();
            this.wholeHeight = getHeight();
            this.mBackgroundRectF.set((this.wholeWidth - this.backGroundBitmap.getWidth()) >> 1, (this.wholeHeight - this.backGroundBitmap.getHeight()) >> 1, (this.wholeWidth + this.backGroundBitmap.getWidth()) >> 1, (this.wholeHeight + this.backGroundBitmap.getHeight()) >> 1);
            this.mForegroundRectF.set((this.wholeWidth - this.foreGroundBitmapPressed.getWidth()) >> 1, (this.wholeHeight - this.foreGroundBitmapNormal.getHeight()) >> 1, (this.wholeWidth + this.foreGroundBitmapNormal.getWidth()) >> 1, (this.wholeHeight + this.foreGroundBitmapNormal.getHeight()) >> 1);
            this.mCpbStrokeWidth = DPIUtil.dip2px(16.0f);
            this.mArcRectF.set(((this.wholeWidth - this.backGroundBitmap.getWidth()) >> 1) + this.marginToBackGroundBitmap, ((this.wholeHeight - this.backGroundBitmap.getHeight()) >> 1) + this.marginToBackGroundBitmap, ((this.wholeWidth + this.backGroundBitmap.getWidth()) >> 1) - this.marginToBackGroundBitmap, ((this.wholeHeight + this.backGroundBitmap.getHeight()) >> 1) - this.marginToBackGroundBitmap);
            initShader();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(this.backGroundBitmap.getWidth() * 1, this.backGroundBitmap.getHeight() * 1);
    }

    public void setProgress(int i2) {
        if (i2 > 100.0f) {
            i2 = 100;
        }
        this.mCurrentProgress = i2;
        Log.d("JDSignCircleProgressView", "current progress " + this.mCurrentProgress);
        invalidateUi();
    }

    public JDSignCircleProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isWaterEnabled = false;
        this.mCurrentProgress = 0;
        this.drawArc = false;
        this.mAngleStep = 0;
        this.marginToBackGroundBitmap = DPIUtil.dip2px(17.0f);
        this.mCpbStrokeWidth = DPIUtil.dip2px(8.0f);
        this.mCpbStartAngle = -90;
        this.mCpbMaxAngle = R2.attr.additionBottom;
        this.mCpbForegroundColor = -15860867;
        this.mColorScheme1 = new int[]{-961709, -961555, -11690001};
        this.mColorScheme2 = new int[]{-11690001, -15222550, -961709};
        this.mContext = context;
        init();
    }
}
