package com.jingdong.common.jdreactFramework.utils.video;

import android.content.Context;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jingdong.common.jdreactFramework.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes5.dex */
public class TapRecordView extends View {
    private static final int COUNT_DOWN_INTERVAL = 100;
    private static final int RECORD_ERROR_OFFSET_TIME = 900;
    private static final String TAG = VideoRecordActivity.TAG;
    private RectF mArea;
    private CountDownTimer mCountDownTimer;
    private TapRecordListener mListener;
    private long startTime;
    private TapState state;

    /* loaded from: classes5.dex */
    public interface TapRecordListener {
        void onFinishRecord(long j2);

        void onRecordingTime(long j2);

        void onStartRecord();
    }

    /* loaded from: classes5.dex */
    public enum TapState {
        READY,
        RECORDING
    }

    public TapRecordView(Context context) {
        super(context);
        this.state = TapState.READY;
        this.startTime = 0L;
        init();
    }

    public void finishRecord() {
        if (OKLog.D) {
            OKLog.d(TAG, "finishRecord");
        }
        if (this.state != TapState.RECORDING) {
            return;
        }
        this.state = TapState.READY;
        setBackgroundResource(R.drawable.jdreact_video_record_start);
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (this.mListener != null) {
            long currentTimeMillis = (System.currentTimeMillis() - this.startTime) - 900;
            TapRecordListener tapRecordListener = this.mListener;
            if (currentTimeMillis < 0) {
                currentTimeMillis = 0;
            }
            tapRecordListener.onFinishRecord(currentTimeMillis);
        }
    }

    private void init() {
        this.mCountDownTimer = new CountDownTimer(VideoRecordActivity.MAX_RECORD_TIME + 900, 100L) { // from class: com.jingdong.common.jdreactFramework.utils.video.TapRecordView.1
            {
                TapRecordView.this = this;
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                TapRecordView.this.finishRecord();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                if (TapRecordView.this.mListener != null) {
                    long j3 = (VideoRecordActivity.MAX_RECORD_TIME + R2.attr.behavior_hideable) - j2;
                    TapRecordListener tapRecordListener = TapRecordView.this.mListener;
                    if (j3 < 0) {
                        j3 = 0;
                    }
                    tapRecordListener.onRecordingTime(j3);
                }
            }
        };
        post(new Runnable() { // from class: com.jingdong.common.jdreactFramework.utils.video.TapRecordView.2
            {
                TapRecordView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                int min = Math.min(TapRecordView.this.getWidth(), TapRecordView.this.getHeight());
                if (min > 0) {
                    TapRecordView.this.mArea = new RectF();
                    TapRecordView.this.mArea.left = 0.0f;
                    TapRecordView.this.mArea.top = 0.0f;
                    float f2 = min;
                    TapRecordView.this.mArea.right = f2;
                    TapRecordView.this.mArea.bottom = f2;
                }
            }
        });
        setBackgroundResource(R.drawable.jdreact_video_record_start);
    }

    private boolean isTouchPointInView(MotionEvent motionEvent) {
        if (this.mArea == null) {
            return true;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return ((float) Math.sqrt(Math.pow((double) Math.abs(this.mArea.centerX() - x), 2.0d) + Math.pow((double) Math.abs(this.mArea.centerY() - y), 2.0d))) <= this.mArea.width() / 2.0f;
    }

    private void startRecord() {
        if (OKLog.D) {
            OKLog.d(TAG, "startRecord click a");
        }
        this.state = TapState.RECORDING;
        TapRecordListener tapRecordListener = this.mListener;
        if (tapRecordListener != null) {
            tapRecordListener.onStartRecord();
        }
        if (OKLog.D) {
            OKLog.d(TAG, "startRecord click b");
        }
        setBackgroundResource(R.drawable.jdreact_video_record_start_press);
        this.startTime = System.currentTimeMillis();
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.start();
        }
        if (OKLog.D) {
            OKLog.d(TAG, "startRecord click c");
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            if (isTouchPointInView(motionEvent)) {
                startRecord();
            }
            return true;
        } else if (action != 1 && action != 3) {
            return super.onTouchEvent(motionEvent);
        } else {
            finishRecord();
            return true;
        }
    }

    public void setTapRecordListener(TapRecordListener tapRecordListener) {
        this.mListener = tapRecordListener;
    }

    public TapRecordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.state = TapState.READY;
        this.startTime = 0L;
        init();
    }

    public TapRecordView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.state = TapState.READY;
        this.startTime = 0L;
        init();
    }
}
