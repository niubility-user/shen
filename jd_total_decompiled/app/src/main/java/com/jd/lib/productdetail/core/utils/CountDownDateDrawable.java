package com.jd.lib.productdetail.core.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.utils.JDMiaoShaUtil;
import com.jingdong.jdsdk.utils.MyCountdownTimer;

/* loaded from: classes15.dex */
public class CountDownDateDrawable extends Drawable {
    static final int DAY_MIN_SECONDES = 86400000;
    static final int HOUR_MIN_SECONDS = 3600000;
    static final int MINUTE_MIN_SECONDS = 60000;
    static final int SECOND_MIN_SECONDS = 1000;
    private boolean isEnd;
    DateDrawableTenth mDrawable;
    private ICountListener mListener;
    private JDMiaoShaUtil miaoShaUtil;

    /* loaded from: classes15.dex */
    public interface ICountListener {
        void onFinish(CountDownDateDrawable countDownDateDrawable);

        void onUpdate(CountDownDateDrawable countDownDateDrawable, long j2, long j3, long j4, long j5);
    }

    public CountDownDateDrawable(DateDrawableTenth dateDrawableTenth) {
        this.mDrawable = dateDrawableTenth;
        dateDrawableTenth.setBounds(new Rect(0, 0, dateDrawableTenth.getIntrinsicWidth(), dateDrawableTenth.getIntrinsicHeight()));
        this.miaoShaUtil = new JDMiaoShaUtil();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTime(long[] jArr) {
        String str = jArr[1] + "";
        String str2 = jArr[2] + "";
        String str3 = jArr[3] + "";
        DateDrawableTenth dateDrawableTenth = this.mDrawable;
        if (str.length() <= 1) {
            str = "0" + str;
        }
        dateDrawableTenth.setHour(str);
        DateDrawableTenth dateDrawableTenth2 = this.mDrawable;
        if (str2.length() <= 1) {
            str2 = "0" + str2;
        }
        dateDrawableTenth2.setMinute(str2);
        DateDrawableTenth dateDrawableTenth3 = this.mDrawable;
        if (str3.length() <= 1) {
            str3 = "0" + str3;
        }
        dateDrawableTenth3.setSecond(str3);
        invalidateSelf();
    }

    public static long[] toDHM(long j2) {
        return new long[]{j2 / 86400000, (j2 % 86400000) / 3600000, (j2 % 3600000) / 60000, (j2 % 60000) / 1000};
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    public void release() {
        stop();
        this.miaoShaUtil = null;
        this.mListener = null;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mDrawable.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(@NonNull Rect rect) {
        this.mDrawable.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }

    public boolean start(long j2, long j3, ICountListener iCountListener) {
        JDMiaoShaUtil jDMiaoShaUtil = this.miaoShaUtil;
        if (jDMiaoShaUtil == null) {
            return false;
        }
        this.mListener = iCountListener;
        this.isEnd = false;
        jDMiaoShaUtil.setCountdown(j2, j3, new JDMiaoShaUtil.CountDownListener() { // from class: com.jd.lib.productdetail.core.utils.CountDownDateDrawable.1
            @Override // com.jd.lib.productdetail.core.utils.JDMiaoShaUtil.CountDownListener
            public void changed(MyCountdownTimer myCountdownTimer, long j4, long[] jArr, int i2) {
                if (CountDownDateDrawable.this.isEnd) {
                    return;
                }
                try {
                    long[] dhm = CountDownDateDrawable.toDHM(j4);
                    CountDownDateDrawable.this.setTime(dhm);
                    if (CountDownDateDrawable.this.mListener != null) {
                        CountDownDateDrawable.this.mListener.onUpdate(CountDownDateDrawable.this, dhm[0], dhm[1], dhm[2], dhm[3]);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            @Override // com.jd.lib.productdetail.core.utils.JDMiaoShaUtil.CountDownListener
            public boolean finish(MyCountdownTimer myCountdownTimer, long j4, int i2) {
                if (CountDownDateDrawable.this.isEnd) {
                    return false;
                }
                CountDownDateDrawable.this.mDrawable.setHour("00");
                CountDownDateDrawable.this.mDrawable.setMinute("00");
                CountDownDateDrawable.this.mDrawable.setSecond("00");
                CountDownDateDrawable.this.invalidateSelf();
                if (CountDownDateDrawable.this.mListener != null) {
                    CountDownDateDrawable.this.mListener.onFinish(CountDownDateDrawable.this);
                }
                return false;
            }
        });
        return true;
    }

    public void stop() {
        if (this.isEnd) {
            return;
        }
        this.isEnd = true;
        this.mDrawable.setHour("00");
        this.mDrawable.setMinute("00");
        this.mDrawable.setSecond("00");
        JDMiaoShaUtil jDMiaoShaUtil = this.miaoShaUtil;
        if (jDMiaoShaUtil != null) {
            jDMiaoShaUtil.countdownCancel();
        }
    }
}
