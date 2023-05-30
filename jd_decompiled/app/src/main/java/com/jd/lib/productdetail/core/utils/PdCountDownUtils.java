package com.jd.lib.productdetail.core.utils;

import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.jdsdk.utils.MyCountdownTimer;

/* loaded from: classes15.dex */
public class PdCountDownUtils {
    private static int COUNT_TIMEER = 1000;
    private int backGroundColor;
    private int conner;
    private int height;
    private DateDrawable mDateDrawable;
    private SimpleDraweeView mDraweeView;
    private CountDownListener mListener;
    private MyCountdownTimer myCountdownTimer;
    private int pointColor;
    private int textColor;
    private int textSize;
    private int width;

    /* loaded from: classes15.dex */
    public static class Builder {
        PdCountDownUtils countDownUtils;

        public Builder(SimpleDraweeView simpleDraweeView) {
            this.countDownUtils = new PdCountDownUtils(simpleDraweeView);
        }

        public PdCountDownUtils create() {
            this.countDownUtils.initView();
            return this.countDownUtils;
        }

        public Builder setBackGroundColor(int i2) {
            this.countDownUtils.backGroundColor = i2;
            return this;
        }

        public Builder setConner(int i2) {
            this.countDownUtils.conner = i2;
            return this;
        }

        public Builder setHeight(int i2) {
            this.countDownUtils.height = i2;
            return this;
        }

        public Builder setPointerColor(int i2) {
            this.countDownUtils.pointColor = i2;
            return this;
        }

        public Builder setTextColor(int i2) {
            this.countDownUtils.textColor = i2;
            return this;
        }

        public Builder setTextSize(int i2) {
            this.countDownUtils.textSize = i2;
            return this;
        }

        public Builder setWidth(int i2) {
            this.countDownUtils.width = i2;
            return this;
        }
    }

    /* loaded from: classes15.dex */
    public interface CountDownListener {
        void changed(long j2, long[] jArr);

        boolean finish();
    }

    public PdCountDownUtils(SimpleDraweeView simpleDraweeView) {
        this.mDraweeView = simpleDraweeView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTimer(long[] jArr) {
        if (jArr == null) {
            return;
        }
        String str = jArr[1] + "";
        String str2 = jArr[2] + "";
        String str3 = jArr[3] + "";
        DateDrawable dateDrawable = this.mDateDrawable;
        if (str.length() <= 1) {
            str = "0" + str;
        }
        dateDrawable.setHour(str);
        DateDrawable dateDrawable2 = this.mDateDrawable;
        if (str2.length() <= 1) {
            str2 = "0" + str2;
        }
        dateDrawable2.setMinute(str2);
        DateDrawable dateDrawable3 = this.mDateDrawable;
        if (str3.length() <= 1) {
            str3 = "0" + str3;
        }
        dateDrawable3.setSecond(str3);
        this.mDateDrawable.invalidateSelf();
    }

    public void cancelTimer() {
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer != null) {
            myCountdownTimer.cancel(COUNT_TIMEER);
        }
    }

    public void initView() {
        DateDrawable dateDrawable = new DateDrawable(true);
        this.mDateDrawable = dateDrawable;
        dateDrawable.setBackgroundWidth(this.width);
        this.mDateDrawable.setBackgroundHeight(this.height);
        int i2 = this.pointColor;
        if (i2 != 0) {
            this.mDateDrawable.setPointColor(i2);
        }
        int i3 = this.backGroundColor;
        if (i3 != 0) {
            this.mDateDrawable.setBackgroundColor(i3);
        }
        int i4 = this.textColor;
        if (i4 != 0) {
            this.mDateDrawable.setTextColor(i4);
        }
        int i5 = this.textSize;
        if (i5 != 0) {
            this.mDateDrawable.setTextSize(i5);
        }
        int i6 = this.conner;
        if (i6 != 0) {
            this.mDateDrawable.setConner(i6);
        }
        this.mDateDrawable.setHour("00");
        this.mDateDrawable.setMinute("00");
        this.mDateDrawable.setSecond("00");
        this.mDraweeView.setImageDrawable(this.mDateDrawable);
        ViewGroup.LayoutParams layoutParams = this.mDraweeView.getLayoutParams();
        layoutParams.width = (this.width * 3) + (PDUtils.dip2px(2.0f) * 10);
        layoutParams.height = this.height + (PDUtils.dip2px(2.0f) * 2);
        this.mDraweeView.setLayoutParams(layoutParams);
    }

    public void setmCountDownListener(CountDownListener countDownListener) {
        this.mListener = countDownListener;
    }

    public void startCountDown(long j2) {
        MyCountdownTimer myCountdownTimer = this.myCountdownTimer;
        if (myCountdownTimer == null) {
            this.myCountdownTimer = new MyCountdownTimer(j2, 1000L, COUNT_TIMEER) { // from class: com.jd.lib.productdetail.core.utils.PdCountDownUtils.1
                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onFinish(int i2) {
                    if (PdCountDownUtils.this.mListener != null) {
                        PdCountDownUtils.this.mListener.finish();
                    }
                    PdCountDownUtils.this.myCountdownTimer.cancel(i2);
                }

                @Override // com.jingdong.jdsdk.utils.MyCountdownTimer
                public void onTick(long j3, int i2) {
                    long[] dhm = PDUtils.toDHM(j3);
                    PdCountDownUtils.this.showTimer(dhm);
                    if (PdCountDownUtils.this.mListener != null) {
                        PdCountDownUtils.this.mListener.changed(j3, dhm);
                    }
                }
            }.start();
        } else {
            myCountdownTimer.reset(j2, 1000L, COUNT_TIMEER);
        }
    }
}
