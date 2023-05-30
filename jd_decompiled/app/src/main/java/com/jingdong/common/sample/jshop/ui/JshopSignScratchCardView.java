package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class JshopSignScratchCardView extends View {
    public static final int SWIPE_PAINT_WIDTH = 40;
    private final int INNER_TEXT_SIZE;
    public int STATE;
    private final int TEXT_SIZE;
    private int cardHeight;
    private RectF cardSize;
    private int cardWidth;
    public boolean happened;
    int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Bitmap mInnerBitmap;
    private String mInnerText;
    private Rect mInnerTextBound;
    private Paint mInnerTextPaint;
    private int mLastX;
    private int mLastY;
    private OnCompleteListener mOnCompleteListener;
    private Bitmap mOutterBitmap;
    private Paint mOutterPaint;
    private Path mPath;
    private Runnable mRunnable;
    private Bitmap mSadBitmap;
    private Bitmap mSignedBitmap;
    private String mText;
    private Rect mTextBound;
    private Paint mTextPaint;
    private int mTextSize;
    private String mTipsText;
    private Rect mTipsTextBound;
    private Paint mTipsTextPaint;
    int width;

    /* loaded from: classes6.dex */
    public interface OnCompleteListener {
        void complete();
    }

    public JshopSignScratchCardView(Context context) {
        this(context, null);
    }

    private void drawPath() {
        Log.d("zhudewei", "path path path");
        this.mOutterPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        this.mCanvas.drawPath(this.mPath, this.mOutterPaint);
    }

    private void initView() {
        this.STATE = 3002;
        this.happened = false;
        this.mOutterBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jshop_scratch_cover_bg);
        this.mInnerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jshop_scratch_card_bg);
        this.mSadBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jshop_scratch_sad_bg);
        this.mSignedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jshop_scratch_signature_bg);
        try {
            Bitmap bitmap = this.mOutterBitmap;
            if (bitmap != null) {
                this.cardWidth = bitmap.getWidth();
                this.cardHeight = this.mOutterBitmap.getHeight();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.mPath = new Path();
        this.mOutterPaint = new Paint();
        this.mText = "\u522e\u5f00\u67e5\u770b\u62bd\u5956\u7ed3\u679c";
        this.mInnerText = "\u677e\u5f00\u624b\u6307\u67e5\u770b\u7ed3\u679c";
        this.mTipsText = "\u6bcf\u5929\u62bd\u5956\u540e\u9996\u6b21\u5206\u4eab\u52a0\u6b21\u62bd\u5956\u673a\u4f1a";
        this.mTextBound = new Rect();
        this.mInnerTextBound = new Rect();
        this.mTipsTextBound = new Rect();
        this.cardSize = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        this.mTextPaint = new Paint(1);
        this.mInnerTextPaint = new Paint(1);
        this.mTipsTextPaint = new Paint(1);
        this.mTextSize = this.TEXT_SIZE;
    }

    private void setInnerTextPaint() {
        this.mInnerTextPaint.setColor(-9742511);
        this.mInnerTextPaint.setStyle(Paint.Style.FILL);
        this.mInnerTextPaint.setTextSize(this.INNER_TEXT_SIZE);
        this.mInnerTextPaint.setAntiAlias(true);
        Paint paint = this.mInnerTextPaint;
        String str = this.mInnerText;
        paint.getTextBounds(str, 0, str.length(), this.mInnerTextBound);
    }

    private void setOutterPaint() {
        this.mOutterPaint.setColor(-3947581);
        this.mOutterPaint.setAntiAlias(true);
        this.mOutterPaint.setDither(true);
        this.mOutterPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mOutterPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mOutterPaint.setStyle(Paint.Style.STROKE);
        this.mOutterPaint.setAlpha(0);
        this.mOutterPaint.setStrokeWidth(40.0f);
    }

    private void setTextPaint() {
        this.mTextPaint.setColor(-1);
        this.mTextPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        Paint paint = this.mTextPaint;
        String str = this.mText;
        paint.getTextBounds(str, 0, str.length(), this.mTextBound);
    }

    private void setTipsTextPaint() {
        this.mTipsTextPaint.setColor(-855638017);
        this.mTipsTextPaint.setStyle(Paint.Style.FILL);
        this.mTipsTextPaint.setTextSize(this.INNER_TEXT_SIZE);
        this.mTipsTextPaint.setAntiAlias(true);
        Paint paint = this.mTipsTextPaint;
        String str = this.mTipsText;
        paint.getTextBounds(str, 0, str.length(), this.mTipsTextBound);
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
        Log.d("SignScratchView", "onAttachedFromWindow()");
        super.onAttachedToWindow();
    }

    public void onDestroy() {
        Bitmap bitmap = this.mInnerBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mInnerBitmap.recycle();
        }
        Bitmap bitmap2 = this.mSadBitmap;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.mSadBitmap.recycle();
        }
        Bitmap bitmap3 = this.mSignedBitmap;
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            this.mSignedBitmap.recycle();
        }
        Bitmap bitmap4 = this.mBitmap;
        if (bitmap4 != null && !bitmap4.isRecycled()) {
            this.mBitmap.recycle();
        }
        Bitmap bitmap5 = this.mOutterBitmap;
        if (bitmap5 == null || bitmap5.isRecycled()) {
            return;
        }
        this.mOutterBitmap.recycle();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        Log.d("SignScratchView", "onDetachedFromWindow()");
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        OnCompleteListener onCompleteListener;
        canvas.drawBitmap(this.mInnerBitmap, 0.0f, 0.0f, (Paint) null);
        int i2 = this.STATE;
        if (i2 == 3004 || i2 == 3005) {
            canvas.drawBitmap(this.mSignedBitmap, 0.0f, 0.0f, (Paint) null);
        } else if (i2 == 3006) {
            canvas.drawBitmap(this.mSadBitmap, 0.0f, 0.0f, (Paint) null);
        }
        if (this.STATE == 3003 && (onCompleteListener = this.mOnCompleteListener) != null && !this.happened) {
            onCompleteListener.complete();
            this.happened = true;
        }
        if (this.STATE == 3008) {
            canvas.drawText(this.mInnerText, (getWidth() - this.mInnerTextBound.width()) >> 1, (getHeight() + this.mInnerTextBound.height()) >> 1, this.mInnerTextPaint);
        }
        int i3 = this.STATE;
        if (i3 != 3005 && i3 != 3006 && i3 != 3004) {
            if (i3 == 3008) {
                drawPath();
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
            } else if (i3 == 3002) {
                RectF rectF = this.cardSize;
                rectF.right = this.cardWidth;
                rectF.bottom = this.cardHeight;
                this.mCanvas.drawBitmap(this.mOutterBitmap, (Rect) null, rectF, (Paint) null);
                canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
            }
        }
        if (this.STATE == 3002) {
            canvas.drawText(this.mText, (getWidth() - this.mTextBound.width()) >> 1, (getHeight() + this.mTextBound.height()) >> 1, this.mTextPaint);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        Log.d("cardview", "width : " + this.width + " height : " + this.height);
        try {
            this.mBitmap = Bitmap.createBitmap(this.cardWidth, this.cardHeight, Bitmap.Config.ARGB_4444);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        setMeasuredDimension(this.cardWidth, this.cardHeight);
        setOutterPaint();
        Canvas canvas = new Canvas(this.mBitmap);
        this.mCanvas = canvas;
        canvas.drawBitmap(this.mOutterBitmap, (Rect) null, new RectF(0.0f, 0.0f, this.cardWidth, this.cardHeight), (Paint) null);
        setTextPaint();
        setInnerTextPaint();
        setTipsTextPaint();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int i2 = this.STATE;
        if (i2 != 3004 && i2 != 3006 && i2 != 3005) {
            if (action == 0) {
                getParent().requestDisallowInterceptTouchEvent(true);
                Path path = this.mPath;
                if (path != null) {
                    path.reset();
                }
                if (this.STATE != 3003) {
                    this.mLastX = x;
                    this.mLastY = y;
                    this.mPath.moveTo(x, y);
                }
            } else if (action != 1) {
                if (action == 2 && i2 != 3003) {
                    this.STATE = 3008;
                    int abs = Math.abs(x - this.mLastX);
                    int abs2 = Math.abs(y - this.mLastY);
                    if (abs > 3 || abs2 > 3) {
                        this.mPath.lineTo(x, y);
                    }
                    this.mLastX = x;
                    this.mLastY = y;
                }
            } else if (i2 != 3003) {
                this.STATE = 3003;
            }
            invalidate();
        }
        return true;
    }

    public void setCanShare(boolean z) {
    }

    public void setFailWinBmp() {
        this.STATE = 3006;
        invalidateUi();
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener) {
        this.mOnCompleteListener = onCompleteListener;
    }

    public void setSignFailedBmp() {
        this.STATE = 3002;
        invalidateUi();
    }

    public void setStartBmp() {
        this.STATE = 3002;
        this.happened = false;
        postInvalidate();
    }

    public void setSuccessOrWinBmp() {
        this.STATE = 3005;
        invalidateUi();
    }

    public JshopSignScratchCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JshopSignScratchCardView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TEXT_SIZE = DPIUtil.dip2px(20.0f);
        this.INNER_TEXT_SIZE = DPIUtil.dip2px(16.0f);
        this.happened = false;
        this.mRunnable = new Runnable() { // from class: com.jingdong.common.sample.jshop.ui.JshopSignScratchCardView.1
            @Override // java.lang.Runnable
            public void run() {
                int width = JshopSignScratchCardView.this.getWidth();
                int height = JshopSignScratchCardView.this.getHeight();
                int i3 = width * height;
                float f2 = i3;
                Bitmap bitmap = JshopSignScratchCardView.this.mBitmap;
                if (bitmap == null || bitmap.isRecycled()) {
                    return;
                }
                int[] iArr = new int[i3];
                bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
                float f3 = 0.0f;
                for (int i4 = 0; i4 < width; i4++) {
                    for (int i5 = 0; i5 < height; i5++) {
                        if (iArr[(i5 * width) + i4] == 0) {
                            f3 += 1.0f;
                        }
                    }
                }
                if (f3 <= 0.0f || f2 <= 0.0f) {
                    return;
                }
                int i6 = (int) ((f3 * 100.0f) / f2);
                Log.v("zhudewei", "percent=" + i6);
                if (i6 > 70) {
                    JshopSignScratchCardView.this.STATE = 3003;
                }
            }
        };
        initView();
    }
}
