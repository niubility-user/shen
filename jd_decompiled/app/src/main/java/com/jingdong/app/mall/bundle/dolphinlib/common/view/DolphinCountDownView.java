package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.countdown.JDMiaoShaUtil;
import com.jd.lib.babel.ifloor.utils.countdown.MyCountdownTimer;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;

/* loaded from: classes19.dex */
public class DolphinCountDownView extends LinearLayout {
    private CountDownSimpleListener countDownListener;
    private Context mContext;
    private TextView mFirstColon;
    private TextView mHourTime;
    private TextView mMinuteTime;
    private TextView mSecondColon;
    private TextView mSecondTime;
    private JDMiaoShaUtil miaoShaUtil;
    private long timeMillis;
    private long timeRemain;

    /* loaded from: classes19.dex */
    public interface CountDownSimpleListener {
        void finish();

        void oneHourFinish();
    }

    public DolphinCountDownView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.dolphin_count_down_view, this);
        this.mHourTime = (TextView) findViewById(R.id.hour_time);
        this.mMinuteTime = (TextView) findViewById(R.id.minute_time);
        this.mSecondTime = (TextView) findViewById(R.id.second_time);
        this.mFirstColon = (TextView) findViewById(R.id.first_colon);
        this.mSecondColon = (TextView) findViewById(R.id.second_colon);
    }

    private void initJDMiaoShaUtil(long j2, long j3) {
        if (j2 < 0) {
            j2 = Math.abs(j2);
        }
        long currentTimeMillis = System.currentTimeMillis() - j3;
        long j4 = 0 - currentTimeMillis;
        long j5 = j2 - currentTimeMillis;
        try {
            JDMiaoShaUtil jDMiaoShaUtil = this.miaoShaUtil;
            if (jDMiaoShaUtil != null) {
                jDMiaoShaUtil.countdownCancel();
            }
            this.miaoShaUtil = new JDMiaoShaUtil();
            JDMiaoShaUtil.CountDownListener countDownListener = new JDMiaoShaUtil.CountDownListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinCountDownView.1
                @Override // com.jd.lib.babel.ifloor.utils.countdown.JDMiaoShaUtil.CountDownListener
                public void changed(MyCountdownTimer myCountdownTimer, long j6, long[] jArr, int i2) {
                    if (DolphinCountDownView.this.getVisibility() == 0) {
                        DolphinCountDownView.this.setDate(jArr);
                    }
                }

                @Override // com.jd.lib.babel.ifloor.utils.countdown.JDMiaoShaUtil.CountDownListener
                public boolean finish(MyCountdownTimer myCountdownTimer, long j6, int i2) {
                    if (DolphinCountDownView.this.countDownListener != null) {
                        DolphinCountDownView.this.countDownListener.finish();
                        return true;
                    }
                    return true;
                }
            };
            JDMiaoShaUtil jDMiaoShaUtil2 = this.miaoShaUtil;
            if (jDMiaoShaUtil2 == null || !jDMiaoShaUtil2.isStop()) {
                return;
            }
            this.miaoShaUtil.setCountdown(j4, j5, countDownListener);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDate(long[] jArr) {
        try {
            String str = (jArr[0] / 24) + "";
            String str2 = jArr[0] + "";
            String str3 = jArr[1] + "";
            String str4 = jArr[2] + "";
            if (str.length() <= 1) {
                String str5 = "0" + str;
            }
            if (str2.length() <= 1) {
                str2 = "0" + str2;
            }
            if (str3.length() <= 1) {
                str3 = "0" + str3;
            }
            if (str4.length() <= 1) {
                str4 = "0" + str4;
            }
            this.mHourTime.setText(str2);
            this.mMinuteTime.setText(str3);
            this.mSecondTime.setText(str4);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setCountDownListener(CountDownSimpleListener countDownSimpleListener) {
        this.countDownListener = countDownSimpleListener;
    }

    public void setStyle(int i2, int i3, String str, int i4, String str2, int i5) {
        if (i2 > 0 && i3 > 0) {
            setLayoutParams(new ViewGroup.LayoutParams(i2, i3));
        }
        float f2 = i5;
        float[] fArr = {f2, f2, f2, f2, f2, f2, f2, f2};
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadii(fArr);
        gradientDrawable.setColor(ColorUtil.parseColor(str2, Color.parseColor("#FFFFFF")));
        this.mHourTime.setBackgroundDrawable(gradientDrawable);
        this.mMinuteTime.setBackgroundDrawable(gradientDrawable);
        this.mSecondTime.setBackgroundDrawable(gradientDrawable);
        FontUtil.changeTextFont(this.mHourTime, 4099);
        FontUtil.changeTextFont(this.mMinuteTime, 4099);
        FontUtil.changeTextFont(this.mSecondTime, 4099);
        FontUtil.changeTextFont(this.mFirstColon, 4097);
        FontUtil.changeTextFont(this.mSecondColon, 4097);
        float f3 = i4;
        this.mHourTime.setTextSize(0, f3);
        this.mMinuteTime.setTextSize(0, f3);
        this.mSecondTime.setTextSize(0, f3);
        this.mHourTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mMinuteTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mSecondTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mFirstColon.setTextSize(0, f3);
        this.mSecondColon.setTextSize(0, f3);
        this.mFirstColon.setTextColor(ColorUtil.parseColor(str2, Color.parseColor("#FFFFFF")));
        this.mSecondColon.setTextColor(ColorUtil.parseColor(str2, Color.parseColor("#FFFFFF")));
    }

    public void update(long j2, long j3) {
        this.timeRemain = j2;
        this.timeMillis = j3;
        initJDMiaoShaUtil(j2, j3);
    }

    public DolphinCountDownView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public DolphinCountDownView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }

    public void setStyle(int i2, int i3, String str, int i4, int i5) {
        if (i2 > 0 && i3 > 0) {
            setLayoutParams(new ViewGroup.LayoutParams(i2, i3));
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(0);
        this.mHourTime.setBackgroundDrawable(gradientDrawable);
        this.mMinuteTime.setBackgroundDrawable(gradientDrawable);
        this.mSecondTime.setBackgroundDrawable(gradientDrawable);
        FontUtil.changeTextFont(this.mHourTime, 4099);
        FontUtil.changeTextFont(this.mMinuteTime, 4099);
        FontUtil.changeTextFont(this.mSecondTime, 4099);
        FontUtil.changeTextFont(this.mFirstColon, 4097);
        FontUtil.changeTextFont(this.mSecondColon, 4097);
        float f2 = i4;
        this.mHourTime.setTextSize(0, f2);
        this.mMinuteTime.setTextSize(0, f2);
        this.mSecondTime.setTextSize(0, f2);
        this.mHourTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mMinuteTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mSecondTime.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DPIUtil.dip2px(this.mContext, 3.0f), -1);
        this.mFirstColon.setLayoutParams(layoutParams);
        this.mSecondColon.setLayoutParams(layoutParams);
        this.mFirstColon.setPadding(0, DPIUtil.dip2px(this.mContext, 1.0f), 0, 0);
        this.mSecondColon.setPadding(0, DPIUtil.dip2px(this.mContext, 1.0f), 0, 0);
        this.mFirstColon.setGravity(17);
        this.mSecondColon.setGravity(17);
        this.mFirstColon.setTextSize(0, f2);
        this.mSecondColon.setTextSize(0, f2);
        this.mFirstColon.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
        this.mSecondColon.setTextColor(ColorUtil.parseColor(str, Color.parseColor("#FFFFFF")));
    }
}
