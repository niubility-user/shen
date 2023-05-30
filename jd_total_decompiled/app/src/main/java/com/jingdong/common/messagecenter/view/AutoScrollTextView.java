package com.jingdong.common.messagecenter.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.common.DpiUtil;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes5.dex */
public class AutoScrollTextView extends View {
    private static Handler handler;
    private boolean Auto;
    private float Fraction;
    private final Handler UIhandler;
    private int mCurNum;
    private Runnable mDownRunnable;
    private float mFraction;
    private Mode mMode;
    private int mNextNum;
    private Paint mPaint;
    private int mPreNum;
    private RedNumChangeListener mRedNumChangeListener;
    private int mSpeed;
    private int mTargetNum;
    private Rect mTextBounds;
    private int mTextCenterX;
    private int mTextColor;
    private int mTextHeight;
    private int mTextSize;
    private Runnable mUpRunnable;
    private HandlerThread myhandlerthread;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.messagecenter.view.AutoScrollTextView$5  reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode = iArr;
            try {
                iArr[Mode.DOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode[Mode.UP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode[Mode.KEEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public enum Mode {
        DOWN,
        UP,
        DOWN_AND_UP,
        KEEP
    }

    /* loaded from: classes5.dex */
    public interface RedNumChangeListener {
        void OnRedNumMore();

        void OnRedNumless();

        void onFinish();
    }

    public AutoScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTargetNum = 0;
        this.mSpeed = 10;
        this.mTextBounds = new Rect();
        this.mTextSize = DpiUtil.dip2px(getContext(), 9.0f);
        this.mTextColor = -1;
        this.Fraction = 0.1f;
        this.mMode = Mode.DOWN_AND_UP;
        this.myhandlerthread = new HandlerThread("handle_thread");
        this.UIhandler = new Handler() { // from class: com.jingdong.common.messagecenter.view.AutoScrollTextView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (AutoScrollTextView.this.mRedNumChangeListener != null) {
                    AutoScrollTextView.this.mRedNumChangeListener.onFinish();
                }
            }
        };
        this.mUpRunnable = new Runnable() { // from class: com.jingdong.common.messagecenter.view.AutoScrollTextView.3
            @Override // java.lang.Runnable
            public void run() {
                String str = "mCurNum:TargetNum" + AutoScrollTextView.this.mCurNum + ":" + AutoScrollTextView.this.mTargetNum;
                if (AutoScrollTextView.this.mCurNum == AutoScrollTextView.this.mTargetNum || !AutoScrollTextView.this.Auto) {
                    AutoScrollTextView.handler.removeCallbacks(AutoScrollTextView.this.mUpRunnable);
                    AutoScrollTextView.this.UIhandler.sendEmptyMessage(1);
                    AutoScrollTextView.this.Auto = false;
                    return;
                }
                AutoScrollTextView autoScrollTextView = AutoScrollTextView.this;
                AutoScrollTextView.access$424(autoScrollTextView, autoScrollTextView.Fraction);
                AutoScrollTextView.this.postInvalidate();
            }
        };
        this.mDownRunnable = new Runnable() { // from class: com.jingdong.common.messagecenter.view.AutoScrollTextView.4
            @Override // java.lang.Runnable
            public void run() {
                String str = "mCurNum:TargetNum" + AutoScrollTextView.this.mCurNum + ":" + AutoScrollTextView.this.mTargetNum;
                if (!(AutoScrollTextView.this.Auto & (AutoScrollTextView.this.mCurNum != AutoScrollTextView.this.mTargetNum))) {
                    AutoScrollTextView.handler.removeCallbacks(AutoScrollTextView.this.mDownRunnable);
                    AutoScrollTextView.this.UIhandler.sendEmptyMessage(1);
                    AutoScrollTextView.this.Auto = false;
                    return;
                }
                AutoScrollTextView autoScrollTextView = AutoScrollTextView.this;
                AutoScrollTextView.access$416(autoScrollTextView, autoScrollTextView.Fraction);
                AutoScrollTextView.this.postInvalidate();
            }
        };
        initHandler();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTypeface(FontsUtil.getTypeFace(context));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setColor(this.mTextColor);
        measureTextHeight();
    }

    static /* synthetic */ float access$416(AutoScrollTextView autoScrollTextView, float f2) {
        float f3 = autoScrollTextView.mFraction + f2;
        autoScrollTextView.mFraction = f3;
        return f3;
    }

    static /* synthetic */ float access$424(AutoScrollTextView autoScrollTextView, float f2) {
        float f3 = autoScrollTextView.mFraction - f2;
        autoScrollTextView.mFraction = f3;
        return f3;
    }

    private void drawNext(Canvas canvas) {
        int measuredHeight = ((getMeasuredHeight() * 3) / 2) + (this.mTextHeight / 4);
        canvas.drawText(this.mNextNum + "", this.mTextCenterX, measuredHeight + (this.mTextHeight / 2), this.mPaint);
    }

    private void drawPre(Canvas canvas) {
        int i2 = this.mTextHeight;
        canvas.drawText(this.mPreNum + "", this.mTextCenterX, (((getMeasuredHeight() / 2) - (i2 / 2)) * (-1)) - (i2 / 4), this.mPaint);
    }

    private void drawSelf(Canvas canvas) {
        canvas.drawText(this.mCurNum + "", this.mTextCenterX, (getMeasuredHeight() / 2) + (this.mTextHeight / 2), this.mPaint);
    }

    private void initHandler() {
        this.myhandlerthread.start();
        handler = new Handler(this.myhandlerthread.getLooper()) { // from class: com.jingdong.common.messagecenter.view.AutoScrollTextView.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
            }
        };
    }

    private void initNum(int i2) {
        RedNumChangeListener redNumChangeListener;
        if (i2 == -1) {
            RedNumChangeListener redNumChangeListener2 = this.mRedNumChangeListener;
            if (redNumChangeListener2 != null) {
                redNumChangeListener2.OnRedNumless();
            }
        } else if (i2 == 10 && (redNumChangeListener = this.mRedNumChangeListener) != null) {
            redNumChangeListener.OnRedNumMore();
        }
        if (i2 == -1) {
            i2 = 9;
        }
        if (i2 == 10) {
            i2 = 0;
        }
        this.mCurNum = i2;
        String str = this.mCurNum + " , " + this.mFraction;
        int i3 = AnonymousClass5.$SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode[this.mMode.ordinal()];
        if (i3 == 1) {
            int i4 = i2 + 1;
            this.mNextNum = i4 != 10 ? i4 : 0;
            int i5 = i2 - 1;
            this.mPreNum = i5 != -1 ? i5 : 9;
        } else if (i3 != 2) {
            int i6 = i2 + 1;
            this.mNextNum = i6 != 10 ? i6 : 0;
            int i7 = i2 - 1;
            this.mPreNum = i7 != -1 ? i7 : 9;
        } else {
            int i8 = i2 + 1;
            this.mNextNum = i8 != 10 ? i8 : 0;
            int i9 = i2 - 1;
            this.mPreNum = i9 != -1 ? i9 : 9;
        }
    }

    private int measureHeight(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int i3 = 0;
        if (mode == Integer.MIN_VALUE || mode == 0) {
            this.mPaint.getTextBounds("0", 0, 1, this.mTextBounds);
            i3 = this.mTextBounds.height();
        } else if (mode == 1073741824) {
            i3 = size;
        }
        if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(i3, size);
        }
        return i3 + getPaddingTop() + getPaddingBottom();
    }

    private void measureTextHeight() {
        this.mPaint.getTextBounds(this.mCurNum + "", 0, 1, this.mTextBounds);
        this.mTextHeight = this.mTextBounds.height();
    }

    private int measureWidth(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int i3 = 0;
        if (mode == Integer.MIN_VALUE || mode == 0) {
            this.mPaint.getTextBounds("0", 0, 1, this.mTextBounds);
            i3 = this.mTextBounds.width();
        } else if (mode == 1073741824) {
            i3 = size;
        }
        if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(i3, size);
        }
        return i3 + getPaddingLeft() + getPaddingRight();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mPaint.setColor(this.mTextColor);
        String str = "mcurnm :" + this.mCurNum;
        int i2 = AnonymousClass5.$SwitchMap$com$jingdong$common$messagecenter$view$AutoScrollTextView$Mode[this.mMode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    int i3 = this.mCurNum;
                    int i4 = this.mTargetNum;
                    if (i3 > i4) {
                        handler.postDelayed(this.mDownRunnable, this.mSpeed);
                        if (this.mFraction >= 1.0f) {
                            this.mFraction = 0.0f;
                            initNum(this.mCurNum - 1);
                        }
                    } else if (i3 < i4) {
                        handler.postDelayed(this.mUpRunnable, this.mSpeed);
                        if (this.mFraction <= -1.0f) {
                            this.mFraction = 0.0f;
                            initNum(this.mCurNum + 1);
                        }
                    }
                } else {
                    drawSelf(canvas);
                    return;
                }
            } else if (this.mCurNum != this.mTargetNum) {
                handler.postDelayed(this.mUpRunnable, this.mSpeed);
                if (this.mFraction <= -1.0f) {
                    this.mFraction = 0.0f;
                    initNum(this.mCurNum + 1);
                }
            }
        } else if (this.mCurNum != this.mTargetNum) {
            handler.postDelayed(this.mDownRunnable, this.mSpeed);
            if (this.mFraction >= 1.0f) {
                this.mFraction = 0.0f;
                initNum(this.mCurNum - 1);
            }
        }
        canvas.translate(0.0f, this.mFraction * getMeasuredHeight());
        drawPre(canvas);
        drawSelf(canvas);
        drawNext(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(measureWidth(i2), measureHeight(i3) + 1);
        this.mTextCenterX = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) / 2;
    }

    public void onRemoveTask() {
        handler.removeCallbacks(this.mDownRunnable);
        handler.removeCallbacks(this.mUpRunnable);
    }

    public void setAutoNumChangeListener(RedNumChangeListener redNumChangeListener) {
        this.mRedNumChangeListener = redNumChangeListener;
    }

    public void setMode(Mode mode) {
        this.mMode = mode;
        if (mode == Mode.KEEP) {
            onRemoveTask();
        }
    }

    public void setNumber(int i2) {
        if (i2 >= 0 && i2 <= 9) {
            this.mCurNum = i2;
            onRemoveTask();
            initNum(i2);
            this.mFraction = 0.0f;
            invalidate();
            return;
        }
        throw new RuntimeException("invalidate number , should in [0,9]");
    }

    public void setSpeed(int i2) {
        float f2 = i2 < 10 ? 700.0f : 2000.0f;
        if (i2 > 0) {
            this.Fraction = ((i2 * 2) / f2) * this.mSpeed;
        } else {
            this.Fraction = 0.1f;
        }
    }

    public void setTargetNumber(int i2) {
        this.Auto = true;
        String str = "\u7ea2\u70b9\u6eda\u52a8\u8bbe\u7f6e\u76ee\u6807\u503c:" + i2;
        this.mTargetNum = i2;
        invalidate();
    }

    public void setTextColor(int i2) {
        this.mTextColor = i2;
        invalidate();
    }

    public void setTextSize(int i2) {
        this.mTextSize = DpiUtil.dip2px(getContext(), i2);
        requestLayout();
        invalidate();
    }
}
