package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes12.dex */
public class JDLiveStatusTagView extends View {
    public static final int STATE_LIVING = 1;
    public static final int STATE_PREDICT = 0;
    public static final int STATE_REPLAY = 3;
    private int COLOR_LIVING_BG;
    private int COLOR_LIVING_TEXT;
    private int COLOR_PREDICT_BG;
    private int COLOR_PREDICT_TEXT;
    private int COLOR_REPLAY_BG;
    private int COLOR_REPLAY_TEXT;
    private int COLOR_RIGHT_BG;
    private int COLOR_RIGHT_TEXT;
    private Bitmap bgMap;
    private Handler handler;
    private int height;
    private Bitmap leftBgBmp;
    private int leftBgColor;
    private RectF leftRect;
    private String leftText;
    private int leftTextColor;
    private Context mContext;
    private Paint mDotPaint;
    private Paint mLeftBgPaint;
    private Paint mLeftTextPaint;
    private Paint mRightBgPaint;
    private Paint mRightTextPaint;
    private int padding;
    private ViewGroup.LayoutParams params;
    private PorterDuffXfermode porterDuffXfermode;
    private int radius;
    private Bitmap rightBgBmp;
    private int rightBgColor;
    private RectF rightRect;
    private String rightText;
    private int rightTextColor;
    private int state;
    private int textSize;
    private int width;

    public JDLiveStatusTagView(Context context) {
        this(context, null);
    }

    private Bitmap getLeftDrawBitmap() {
        RectF rectF = this.leftRect;
        if (rectF == null || rectF.width() == 0.0f || this.leftRect.height() == 0.0f) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) this.leftRect.width(), (int) this.leftRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF2 = this.leftRect;
        int i2 = this.radius;
        canvas.drawRoundRect(rectF2, i2, i2, this.mLeftBgPaint);
        return createBitmap;
    }

    private Bitmap getRightDrawBitmap() {
        RectF rectF = this.rightRect;
        if (rectF == null || rectF.width() == 0.0f || this.rightRect.height() == 0.0f) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) this.rightRect.width(), (int) this.rightRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF2 = new RectF(0.0f, 0.0f, this.rightRect.width(), this.rightRect.height());
        int i2 = this.radius;
        canvas.drawRoundRect(rectF2, i2, i2, this.mRightBgPaint);
        return createBitmap;
    }

    private void measureWidth() {
        float f2;
        if ("".equals(this.rightText)) {
            f2 = 0.0f;
        } else {
            f2 = this.mRightTextPaint.measureText(this.rightText);
            if (f2 < this.textSize * (this.rightText.length() >> 1)) {
                f2 = this.textSize * this.rightText.length();
            }
        }
        int i2 = this.state;
        if (i2 == 1) {
            String string = getContext().getResources().getString(R.string.jdlive_status_tag_live);
            this.leftText = string;
            float measureText = this.mLeftTextPaint.measureText(string) + (this.radius * 2) + this.padding;
            this.leftTextColor = this.COLOR_LIVING_TEXT;
            this.leftBgColor = this.COLOR_LIVING_BG;
            this.leftRect = new RectF(0.0f, 0.0f, measureText, this.height);
            this.rightRect = null;
        } else if (i2 == 0) {
            String string2 = getContext().getResources().getString(R.string.jdlive_status_tag_predict);
            this.leftText = string2;
            float measureText2 = this.mLeftTextPaint.measureText(string2) + (this.radius * 4);
            this.leftTextColor = this.COLOR_PREDICT_TEXT;
            this.leftBgColor = this.COLOR_PREDICT_BG;
            this.leftRect = new RectF(0.0f, 0.0f, measureText2, this.height);
            if (f2 == 0.0f) {
                f2 = measureText2;
            }
            int i3 = this.radius;
            this.rightRect = new RectF(measureText2 - (i3 * 2), 0.0f, (measureText2 - (i3 * 2)) + f2 + (i3 * 2), this.height);
        } else if (i2 == 3) {
            String string3 = getContext().getResources().getString(R.string.jdlive_status_tag_replay);
            this.leftText = string3;
            float measureText3 = this.mLeftTextPaint.measureText(string3) + (this.radius * 4);
            this.leftTextColor = this.COLOR_REPLAY_TEXT;
            this.leftBgColor = this.COLOR_REPLAY_BG;
            this.leftRect = new RectF(0.0f, 0.0f, measureText3, this.height);
            if (f2 == 0.0f) {
                f2 = measureText3;
            }
            int i4 = this.radius;
            this.rightRect = new RectF(measureText3 - (i4 * 2), 0.0f, (measureText3 - (i4 * 2)) + f2 + (i4 * 2), this.height);
        } else {
            setVisibility(8);
            if (OKLog.D) {
                throw new RuntimeException("live state error");
            }
        }
        this.mLeftTextPaint.setColor(this.leftTextColor);
        this.mLeftBgPaint.setColor(this.leftBgColor);
        RectF rectF = this.leftRect;
        int width = rectF == null ? 0 : (int) rectF.width();
        RectF rectF2 = this.rightRect;
        this.width = width + (rectF2 != null ? (int) rectF2.width() : 0);
    }

    public Bitmap getCombineLeftBitmap() {
        if (this.width == 0 || this.height == 0) {
            return null;
        }
        this.leftBgBmp = getLeftDrawBitmap();
        this.rightBgBmp = getRightDrawBitmap();
        Bitmap createBitmap = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        Bitmap bitmap = this.leftBgBmp;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mLeftBgPaint);
        }
        if (this.rightBgBmp != null) {
            this.mRightBgPaint.setXfermode(this.porterDuffXfermode);
            Bitmap bitmap2 = this.rightBgBmp;
            RectF rectF = this.rightRect;
            canvas.drawBitmap(bitmap2, rectF.left, rectF.top, this.mRightBgPaint);
            this.mRightBgPaint.setXfermode(null);
        }
        canvas.restoreToCount(saveLayer);
        return createBitmap;
    }

    public int getState() {
        return this.state;
    }

    public void init(int i2, String str) {
        this.state = i2;
        if (str == null) {
            str = "";
        }
        this.rightText = str;
        measureWidth();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup.LayoutParams layoutParams = this.params;
        if (layoutParams != null) {
            layoutParams.width = this.width;
            layoutParams.height = this.height;
            setLayoutParams(layoutParams);
        }
        if (this.state == 1) {
            Handler handler = this.handler;
            if (handler == null) {
                this.handler = new Handler() { // from class: com.jingdong.common.widget.JDLiveStatusTagView.1
                    @Override // android.os.Handler
                    public void handleMessage(Message message) {
                        JDLiveStatusTagView.this.invalidate();
                        JDLiveStatusTagView.this.handler.sendEmptyMessageDelayed(0, 500L);
                        super.handleMessage(message);
                    }
                };
            } else {
                handler.removeCallbacksAndMessages(null);
            }
            this.handler.sendEmptyMessageDelayed(0, 500L);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.params = getLayoutParams();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.handler = null;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        RectF rectF;
        RectF rectF2;
        super.onDraw(canvas);
        Bitmap combineLeftBitmap = getCombineLeftBitmap();
        this.bgMap = combineLeftBitmap;
        if (combineLeftBitmap != null) {
            int i2 = this.state;
            if (i2 == 1) {
                this.mLeftBgPaint.setAlpha(229);
            } else if (i2 == 0) {
                this.mLeftBgPaint.setAlpha(229);
            } else if (i2 == 3) {
                this.mLeftBgPaint.setAlpha(127);
            }
            canvas.drawBitmap(this.bgMap, 0.0f, 0.0f, this.mLeftBgPaint);
            this.mLeftBgPaint.setAlpha(255);
            if (this.rightBgBmp != null) {
                this.mRightBgPaint.setAlpha(178);
                Bitmap bitmap = this.rightBgBmp;
                RectF rectF3 = this.rightRect;
                canvas.drawBitmap(bitmap, rectF3.left, rectF3.top, this.mRightBgPaint);
                this.mRightBgPaint.setAlpha(255);
            }
        }
        String str = this.leftText;
        if (str != null && (rectF2 = this.leftRect) != null) {
            if (this.state == 1) {
                canvas.drawCircle(rectF2.left + this.radius, rectF2.height() / 2.0f, DPIUtil.dip2px(2.0f), this.mDotPaint);
                Paint paint = this.mDotPaint;
                int color = paint.getColor();
                int i3 = this.COLOR_LIVING_BG;
                if (color == i3) {
                    i3 = this.COLOR_RIGHT_TEXT;
                }
                paint.setColor(i3);
                String str2 = this.leftText;
                RectF rectF4 = this.leftRect;
                canvas.drawText(str2, rectF4.left + this.radius + this.padding, rectF4.bottom - ((r5 * 3) >> 1), this.mLeftTextPaint);
            } else {
                canvas.drawText(str, rectF2.left + this.radius, rectF2.bottom - ((this.padding * 3) >> 1), this.mLeftTextPaint);
            }
        }
        String str3 = this.rightText;
        if (str3 == null || (rectF = this.rightRect) == null) {
            return;
        }
        canvas.drawText(str3, rectF.left + this.radius, this.leftRect.bottom - ((this.padding * 3) >> 1), this.mRightTextPaint);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(this.width, this.height);
    }

    public JDLiveStatusTagView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.COLOR_LIVING_TEXT = -1;
        this.COLOR_LIVING_BG = -1037525;
        this.COLOR_PREDICT_TEXT = -1;
        this.COLOR_PREDICT_BG = -13580226;
        this.COLOR_REPLAY_TEXT = -1644826;
        this.COLOR_REPLAY_BG = -16777216;
        this.COLOR_RIGHT_TEXT = -1;
        this.COLOR_RIGHT_BG = -16777216;
        this.state = 1;
        this.padding = DPIUtil.getWidthByDesignValue720(6);
        this.textSize = DPIUtil.getWidthByDesignValue720(23);
        int widthByDesignValue720 = DPIUtil.getWidthByDesignValue720(36);
        this.height = widthByDesignValue720;
        this.radius = widthByDesignValue720 >> 1;
        this.leftTextColor = this.COLOR_LIVING_TEXT;
        this.rightTextColor = this.COLOR_RIGHT_TEXT;
        this.leftBgColor = this.COLOR_LIVING_BG;
        this.rightBgColor = this.COLOR_RIGHT_BG;
        this.handler = null;
        this.porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        this.mContext = context;
        Paint paint = new Paint();
        this.mLeftTextPaint = paint;
        paint.setAntiAlias(true);
        this.mLeftTextPaint.setDither(false);
        this.mLeftTextPaint.setStyle(Paint.Style.STROKE);
        this.mLeftTextPaint.setTextSize(this.textSize);
        Paint paint2 = new Paint();
        this.mLeftBgPaint = paint2;
        paint2.setAntiAlias(true);
        this.mLeftBgPaint.setDither(true);
        this.mLeftBgPaint.setStyle(Paint.Style.FILL);
        this.mLeftBgPaint.setFilterBitmap(true);
        Paint paint3 = new Paint(this.mLeftBgPaint);
        this.mRightBgPaint = paint3;
        paint3.setColor(this.rightBgColor);
        Paint paint4 = new Paint(this.mLeftTextPaint);
        this.mRightTextPaint = paint4;
        paint4.setColor(this.rightTextColor);
        Paint paint5 = new Paint(this.mLeftBgPaint);
        this.mDotPaint = paint5;
        paint5.setColor(this.COLOR_LIVING_TEXT);
        this.mDotPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mDotPaint.setStrokeJoin(Paint.Join.ROUND);
    }
}
