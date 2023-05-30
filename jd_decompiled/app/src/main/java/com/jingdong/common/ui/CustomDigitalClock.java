package com.jingdong.common.ui;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.internal.view.SupportMenu;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.UnLog;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class CustomDigitalClock extends TextView implements IThemeChange {
    public static final int MIAOSHA_BEGINING = 2;
    public static final int MIAOSHA_FINISH = 3;
    public static final int MIAOSHA_WILLBEGIN = 1;
    private static final String M_12 = "h:mm aa";
    private static final String M_24 = "k:mm";
    private static final String TAG = "CustomDigitalClock";
    private static final String TAG_HH = "\u65f6";
    private static final String TAG_MM = "\u5206";
    private GlobalThemeController controller;
    private long endTime;
    private boolean isScaleTextSize;
    private Calendar mCalendar;
    private String mFormat;
    private FormatChangeObserver mFormatChangeObserver;
    private Handler mHandler;
    private CountDownListener mListener;
    private Runnable mTicker;
    private boolean mTickerStopped;
    private boolean noneText;
    private final CharacterStyle span_hh;
    private final CharacterStyle span_mm;
    private final CharacterStyle span_ss;
    private String tag_ss;
    private String tag_start;
    private int what;

    /* loaded from: classes6.dex */
    public interface CountDownListener {
        void changed(CustomDigitalClock customDigitalClock, long j2, long[] jArr, SpannableStringBuilder spannableStringBuilder, int i2);

        boolean finish(CustomDigitalClock customDigitalClock, int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class FormatChangeObserver extends ContentObserver {
        public FormatChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            CustomDigitalClock.this.setFormat();
        }
    }

    public CustomDigitalClock(Context context) {
        super(context);
        this.mTickerStopped = false;
        this.isScaleTextSize = false;
        this.span_hh = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.span_mm = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.span_ss = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.tag_start = "\u8fd8\u6709";
        this.tag_ss = "\u79d2";
        init();
        initClock(context);
    }

    public static long[] dealTime(long j2) {
        long j3 = j2 % TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC;
        long j4 = j3 / 3600;
        long j5 = j3 % 3600;
        long j6 = j5 / 60;
        long j7 = j5 % 60;
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }

    private boolean get24HourMode() {
        return DateFormat.is24HourFormat(getContext());
    }

    private void init() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    private void initClock(Context context) {
        if (this.mCalendar == null) {
            this.mCalendar = Calendar.getInstance();
        }
        this.mFormatChangeObserver = new FormatChangeObserver();
        getContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.mFormatChangeObserver);
        setFormat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFormat() {
        if (get24HourMode()) {
            this.mFormat = M_24;
        } else {
            this.mFormat = M_12;
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        setTextColor(this.controller.getThemeConfig().e());
        setBackgroundColor(this.controller.getThemeConfig().a());
    }

    public String format(long j2) {
        String str = "" + j2;
        if (str.length() == 1) {
            return "0" + str;
        }
        return str;
    }

    public SpannableStringBuilder hmsToString(long[] jArr) {
        String format = format(jArr[0]);
        String format2 = format(jArr[1]);
        String format3 = format(jArr[2]);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.tag_start + format + TAG_HH + format2 + TAG_MM + format3 + this.tag_ss);
        if (isScaleTextSize()) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), this.tag_start.length(), this.tag_start.length() + format.length(), 33);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), this.tag_start.length() + format.length() + 1, this.tag_start.length() + format.length() + 1 + format2.length(), 33);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), this.tag_start.length() + format.length() + 1 + format2.length() + 1, this.tag_start.length() + format.length() + 1 + format2.length() + 1 + format3.length(), 33);
        }
        spannableStringBuilder.setSpan(this.span_hh, this.tag_start.length(), this.tag_start.length() + format.length(), 33);
        spannableStringBuilder.setSpan(this.span_mm, this.tag_start.length() + format.length() + 1, this.tag_start.length() + format.length() + 1 + format2.length(), 33);
        spannableStringBuilder.setSpan(this.span_ss, this.tag_start.length() + format.length() + 1 + format2.length() + 1, this.tag_start.length() + format.length() + 1 + format2.length() + 1 + format3.length(), 33);
        return spannableStringBuilder;
    }

    public boolean isScaleTextSize() {
        return this.isScaleTextSize;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        this.mTickerStopped = false;
        super.onAttachedToWindow();
        this.mHandler = new Handler();
        Runnable runnable = new Runnable() { // from class: com.jingdong.common.ui.CustomDigitalClock.1
            @Override // java.lang.Runnable
            public void run() {
                if (CustomDigitalClock.this.mTickerStopped) {
                    return;
                }
                long currentTimeMillis = CustomDigitalClock.this.endTime - System.currentTimeMillis();
                long[] hms = CustomDigitalClock.this.toHMS(currentTimeMillis);
                SpannableStringBuilder hmsToString = CustomDigitalClock.this.hmsToString(hms);
                if (CustomDigitalClock.this.mListener != null) {
                    CountDownListener countDownListener = CustomDigitalClock.this.mListener;
                    CustomDigitalClock customDigitalClock = CustomDigitalClock.this;
                    countDownListener.changed(customDigitalClock, currentTimeMillis, hms, hmsToString, customDigitalClock.what);
                }
                if (currentTimeMillis > 0) {
                    if (!CustomDigitalClock.this.noneText) {
                        CustomDigitalClock.this.setText(hmsToString);
                    }
                } else {
                    if (UnLog.D) {
                        UnLog.d(CustomDigitalClock.TAG, " -->> \u8ba1\u65f6\u7ed3\u675f");
                    }
                    if (!CustomDigitalClock.this.noneText) {
                        CustomDigitalClock customDigitalClock2 = CustomDigitalClock.this;
                        customDigitalClock2.setText(customDigitalClock2.hmsToString(customDigitalClock2.toHMS(0L)));
                    }
                    if (CustomDigitalClock.this.mListener != null) {
                        CustomDigitalClock customDigitalClock3 = CustomDigitalClock.this;
                        CountDownListener countDownListener2 = customDigitalClock3.mListener;
                        CustomDigitalClock customDigitalClock4 = CustomDigitalClock.this;
                        customDigitalClock3.mTickerStopped = countDownListener2.finish(customDigitalClock4, customDigitalClock4.what);
                    }
                    if (CustomDigitalClock.this.mTickerStopped) {
                        CustomDigitalClock.this.onDetachedFromWindow();
                    }
                }
                CustomDigitalClock.this.invalidate();
                long uptimeMillis = SystemClock.uptimeMillis();
                CustomDigitalClock.this.mHandler.postAtTime(CustomDigitalClock.this.mTicker, uptimeMillis + (1000 - (uptimeMillis % 1000)));
            }
        };
        this.mTicker = runnable;
        runnable.run();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mTickerStopped = true;
    }

    public void setCountDownListener(CountDownListener countDownListener) {
        this.mListener = countDownListener;
    }

    public void setEndTime(long j2, int i2) {
        this.what = i2;
        setEndTime(j2);
    }

    public void setNoneText(boolean z) {
        this.noneText = z;
    }

    public void setPrefixText(String str) {
        this.tag_start = str;
    }

    public void setScaleTextSize(boolean z) {
        this.isScaleTextSize = z;
    }

    public void setSuffixText(String str) {
        this.tag_ss = str;
    }

    public void setTickerStopped(boolean z) {
        this.mTickerStopped = z;
    }

    public long[] toHMS(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }

    public void setEndTime(long j2) {
        this.endTime = j2 + System.currentTimeMillis();
    }

    public CustomDigitalClock(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTickerStopped = false;
        this.isScaleTextSize = false;
        this.span_hh = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.span_mm = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.span_ss = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
        this.tag_start = "\u8fd8\u6709";
        this.tag_ss = "\u79d2";
        initClock(context);
    }
}
