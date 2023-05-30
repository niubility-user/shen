package com.jingdong.manto.jsapi.refact.rec;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public class RecorderCtrlView extends View {
    private Region circleRegion;
    private int innerPadding;
    boolean isGoingRecord;
    boolean isGoingStop;
    private boolean isPressed;
    private boolean isRecording;
    private CtrlListener listener;
    private Paint mPaint;
    private int minRecordTime;
    private int originalRecordTime;
    private int pressedColor;
    private float progress;
    private final int progressColor;
    private RectF progressRectF;
    private int recordRadius;
    private String recordText;
    private int remainTimeInSecond;
    private RectF stopRect;
    private int strokeWidth;
    private CountDownTimer timer;

    /* loaded from: classes15.dex */
    public interface CtrlListener {
        void onProgress(long j2);

        void onRecordTimeTooShort();

        void onStart();

        void onStop(long j2);
    }

    public RecorderCtrlView(Context context) {
        super(context);
        this.recordText = "\u70b9\u51fb\u62cd";
        this.isRecording = false;
        this.progressColor = Color.parseColor("#fff0250f");
        this.minRecordTime = 1;
        this.isPressed = false;
        this.isGoingRecord = false;
        this.isGoingStop = false;
        init(context);
    }

    public RecorderCtrlView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.recordText = "\u70b9\u51fb\u62cd";
        this.isRecording = false;
        this.progressColor = Color.parseColor("#fff0250f");
        this.minRecordTime = 1;
        this.isPressed = false;
        this.isGoingRecord = false;
        this.isGoingStop = false;
        init(context);
    }

    public RecorderCtrlView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.recordText = "\u70b9\u51fb\u62cd";
        this.isRecording = false;
        this.progressColor = Color.parseColor("#fff0250f");
        this.minRecordTime = 1;
        this.isPressed = false;
        this.isGoingRecord = false;
        this.isGoingStop = false;
        init(context);
    }

    static /* synthetic */ int access$120(RecorderCtrlView recorderCtrlView, int i2) {
        int i3 = recorderCtrlView.remainTimeInSecond - i2;
        recorderCtrlView.remainTimeInSecond = i3;
        return i3;
    }

    private void init(Context context) {
        this.recordText = context.getString(R.string.manto_record_start);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        int dip2pixel = MantoDensityUtils.dip2pixel(getContext(), 4);
        this.strokeWidth = dip2pixel;
        this.mPaint.setStrokeWidth(dip2pixel);
        this.mPaint.setTextSize(MantoDensityUtils.dip2pixel(getContext(), 13));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setColor(this.progressColor);
        this.pressedColor = MantoVideoUtil.getDarkColor(getContext(), this.progressColor, 0.88f);
    }

    private void normalStatus(Canvas canvas, int i2, int i3) {
        this.mPaint.setColor(this.progressColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        if (this.isPressed) {
            this.mPaint.setColor(this.pressedColor);
        }
        float f2 = i2;
        float f3 = i3;
        canvas.drawCircle(f2, f3, this.recordRadius, this.mPaint);
        this.mPaint.setColor(-1);
        this.mPaint.setTextSize(MantoDensityUtils.dip2pixel(getContext(), 12));
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        canvas.drawText(this.recordText, f2, f3 - ((fontMetrics.ascent + fontMetrics.descent) / 2.0f), this.mPaint);
    }

    private void onPressState(boolean z) {
        this.isPressed = z;
        invalidate();
    }

    private void start() {
        if (this.originalRecordTime < 1) {
            return;
        }
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        final long j2 = (this.originalRecordTime * 1000) + 500;
        final float f2 = 360.0f / ((float) j2);
        this.timer = new CountDownTimer(j2, 25L) { // from class: com.jingdong.manto.jsapi.refact.rec.RecorderCtrlView.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                RecorderCtrlView.this.isRecording = false;
                RecorderCtrlView.this.remainTimeInSecond = 0;
                if (RecorderCtrlView.this.listener != null) {
                    RecorderCtrlView.this.listener.onProgress(RecorderCtrlView.this.originalRecordTime * 1000);
                    RecorderCtrlView.this.listener.onStop(RecorderCtrlView.this.originalRecordTime * 1000);
                }
                RecorderCtrlView.this.progress = 360.0f;
                RecorderCtrlView.this.postInvalidate();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j3) {
                long j4 = j2 - j3;
                if (RecorderCtrlView.this.listener != null) {
                    RecorderCtrlView.this.listener.onProgress(j4);
                }
                RecorderCtrlView.this.postInvalidate();
                if (j4 > 1000 && j3 % 1000 <= 25) {
                    RecorderCtrlView.access$120(RecorderCtrlView.this, 1);
                    if (RecorderCtrlView.this.remainTimeInSecond < 1) {
                        RecorderCtrlView.this.remainTimeInSecond = 0;
                    }
                }
                RecorderCtrlView.this.progress = 360.0f - (f2 * ((float) j3));
            }
        }.start();
        this.isRecording = true;
        CtrlListener ctrlListener = this.listener;
        if (ctrlListener != null) {
            ctrlListener.onStart();
        }
    }

    private void stop() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CtrlListener ctrlListener = this.listener;
        if (ctrlListener != null) {
            if (this.originalRecordTime - this.remainTimeInSecond < this.minRecordTime) {
                ctrlListener.onRecordTimeTooShort();
            } else {
                ctrlListener.onStop((int) (this.progress / (360.0f / (r1 * 1000))));
            }
        }
        this.isRecording = false;
        invalidate();
    }

    private void workStatus(Canvas canvas, int i2, int i3) {
        Paint paint;
        int i4;
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.progressColor);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawArc(this.progressRectF, -90.0f, this.progress, false, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        if (this.isPressed) {
            paint = this.mPaint;
            i4 = this.pressedColor;
        } else {
            paint = this.mPaint;
            i4 = this.progressColor;
        }
        paint.setColor(i4);
        RectF rectF = this.stopRect;
        float f2 = this.strokeWidth;
        canvas.drawRoundRect(rectF, f2, f2, this.mPaint);
        this.mPaint.setTextSize(MantoDensityUtils.dip2pixel(getContext(), 20));
        this.mPaint.setColor(-1);
        canvas.drawText("" + this.remainTimeInSecond, getWidth() / 2, this.innerPadding - this.strokeWidth, this.mPaint);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = (getHeight() / 2) + (this.innerPadding / 2);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(Color.parseColor("#edeef1"));
        canvas.drawCircle(width, height, this.recordRadius + (this.strokeWidth * 1.5f), this.mPaint);
        if (this.isRecording) {
            workStatus(canvas, width, height);
        } else {
            normalStatus(canvas, width, height);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.innerPadding = MantoDensityUtils.dip2pixel(getContext(), 24);
        this.recordRadius = ((getHeight() / 2) - getPaddingBottom()) - this.innerPadding;
        int width = getWidth() / 2;
        int height = (getHeight() / 2) + (this.innerPadding / 2);
        this.circleRegion = new Region();
        Path path = new Path();
        float f2 = width;
        float f3 = height;
        path.addCircle(f2 * 1.0f, 1.0f * f3, this.recordRadius, Path.Direction.CCW);
        Region region = this.circleRegion;
        int i6 = this.recordRadius;
        region.setPath(path, new Region(width - i6, height - i6, width + i6, i6 + height));
        float f4 = ((int) (this.recordRadius + (this.strokeWidth * 1.5f))) / 2.5f;
        this.progressRectF = new RectF(width - r10, height - r10, width + r10, height + r10);
        this.stopRect = new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        boolean z = false;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                if (this.isGoingRecord && this.circleRegion.contains(x, y)) {
                    start();
                } else if (this.isGoingStop && this.stopRect.contains(x, y)) {
                    this.isRecording = false;
                    stop();
                }
                onPressState(false);
                this.isGoingRecord = false;
                this.isGoingStop = false;
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    this.isGoingRecord = false;
                    this.isGoingStop = false;
                    onPressState(false);
                }
            }
            if (z) {
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }
        if (this.isRecording || !this.circleRegion.contains(x, y)) {
            if (this.isRecording && this.stopRect.contains(x, y)) {
                this.isGoingStop = true;
            }
            if (z) {
            }
        } else {
            this.isGoingRecord = true;
        }
        onPressState(true);
        z = true;
        if (z) {
        }
    }

    public void reset() {
        this.progress = 0.0f;
        this.isRecording = false;
        invalidate();
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.timer = null;
    }

    public void setCtrlListener(CtrlListener ctrlListener) {
        this.listener = ctrlListener;
    }

    public void setMinRecordTime(int i2) {
        this.minRecordTime = i2;
    }

    public void setOriginalRecordTime(int i2) {
        this.originalRecordTime = i2;
        this.remainTimeInSecond = i2;
    }
}
