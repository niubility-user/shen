package com.jingdong.common.XView2.layer.flexcube.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.common.XView2.layer.timer.ICountdown;
import com.jingdong.common.XView2.layer.timer.MyCountDownTimer;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.Locale;

/* loaded from: classes5.dex */
public class CountDownTextView extends AppCompatTextView implements ICountdown {
    private static final String TAG = CountDownTextView.class.getSimpleName();
    private static final String TIME_FORMAT_D_H_M_S = "%1$02d:%2$02d:%3$02d:%4$02d";
    private static final String TIME_FORMAT_H_M_S = "%1$02d:%2$02d:%3$02d";
    private static final String TIME_FORMAT_M_S = "%1$02d:%2$02d";
    private static final String TIME_FORMAT_S = "%1$02d";
    public static final String TIME_SHOW_D_H_M_S = "3";
    public static final String TIME_SHOW_H_M_S = "0";
    public static final String TIME_SHOW_M_S = "1";
    public static final String TIME_SHOW_S = "2";
    private MyCountDownTimer mCountDownTimer;
    private String mFormatType;
    private OnCountDownListener mOnCountDownListener;

    /* loaded from: classes5.dex */
    public interface OnCountDownListener {
        void onCountDownFinish();
    }

    public CountDownTextView(@NonNull Context context) {
        this(context, null);
    }

    private String getTimeFormat(long j2) {
        long j3;
        long j4 = j2 / 1000;
        int i2 = (int) (j4 % 60);
        int i3 = (int) ((j4 / 60) % 60);
        if (SwitchQueryFetcher.getSwitchBooleanValue("xview_support_48", true)) {
            j3 = (j4 / 3600) % 48;
        } else {
            j3 = (j4 / 3600) % 24;
        }
        int i4 = (int) j3;
        String str = this.mFormatType;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c2 = 0;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    c2 = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return String.format(Locale.CHINA, TIME_FORMAT_H_M_S, Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2));
            case 1:
                return String.format(Locale.CHINA, TIME_FORMAT_M_S, Integer.valueOf(i3), Integer.valueOf(i2));
            case 2:
                return String.format(Locale.CHINA, TIME_FORMAT_S, Integer.valueOf(i2));
            default:
                return null;
        }
    }

    public void destroy() {
        MyCountDownTimer myCountDownTimer = this.mCountDownTimer;
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }

    @Override // com.jingdong.common.XView2.layer.timer.ICountdown
    public void onFinish() {
        OnCountDownListener onCountDownListener = this.mOnCountDownListener;
        if (onCountDownListener != null) {
            onCountDownListener.onCountDownFinish();
        }
    }

    @Override // com.jingdong.common.XView2.layer.timer.ICountdown
    public void onStart() {
    }

    @Override // com.jingdong.common.XView2.layer.timer.ICountdown
    public void onTick(long j2) {
        setText(getTimeFormat(j2));
    }

    public void showCountTime(long j2, String str, OnCountDownListener onCountDownListener) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        this.mFormatType = str;
        setWidth((int) getPaint().measureText(getTimeFormat(0L)));
        this.mOnCountDownListener = onCountDownListener;
        MyCountDownTimer myCountDownTimer = this.mCountDownTimer;
        if (myCountDownTimer != null) {
            myCountDownTimer.cancel();
            this.mCountDownTimer = null;
        }
        MyCountDownTimer myCountDownTimer2 = new MyCountDownTimer(1000L);
        this.mCountDownTimer = myCountDownTimer2;
        myCountDownTimer2.start(j2, this);
    }

    public CountDownTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
