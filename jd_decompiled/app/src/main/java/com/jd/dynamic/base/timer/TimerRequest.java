package com.jd.dynamic.base.timer;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes13.dex */
public class TimerRequest {
    private long a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f2057c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private TimerType f2058e;

    /* renamed from: f  reason: collision with root package name */
    private String f2059f;

    /* renamed from: g  reason: collision with root package name */
    private String f2060g;

    /* loaded from: classes13.dex */
    public static class Builder {
        private long a;
        private long b;

        /* renamed from: c  reason: collision with root package name */
        private long f2061c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private TimerType f2062e;

        /* renamed from: f  reason: collision with root package name */
        private String f2063f;

        /* renamed from: g  reason: collision with root package name */
        private String f2064g;

        public Builder() {
            this.f2061c = 1000L;
            this.f2062e = TimerType.TIME;
        }

        public Builder(long j2, long j3, long j4, int i2, TimerType timerType, String str, String str2) {
            this.f2061c = 1000L;
            this.f2062e = TimerType.TIME;
            this.a = j2;
            this.b = j3;
            this.f2061c = j4;
            this.d = i2;
            this.f2062e = timerType;
            this.f2063f = str;
            this.f2064g = str2;
        }

        public TimerRequest build() {
            return new TimerRequest(this.a, this.b, this.f2061c, this.d, this.f2062e, this.f2063f, this.f2064g);
        }

        public Builder setEndValue(long j2) {
            this.b = j2;
            return this;
        }

        public Builder setFormatType(String str) {
            this.f2063f = str;
            return this;
        }

        public Builder setInterval(long j2) {
            this.f2061c = j2;
            return this;
        }

        public Builder setStartValue(long j2) {
            this.a = j2;
            return this;
        }

        public Builder setStep(int i2) {
            this.d = i2;
            return this;
        }

        public Builder setTimerId(String str) {
            this.f2064g = str;
            return this;
        }

        public Builder setType(TimerType timerType) {
            this.f2062e = timerType;
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public enum TimerType {
        NORMAL,
        TIME,
        END_TIME
    }

    public TimerRequest() {
        this.f2057c = 1000L;
        this.f2058e = TimerType.TIME;
    }

    public TimerRequest(long j2, long j3, long j4, int i2, TimerType timerType, String str, String str2) {
        this.f2057c = 1000L;
        this.f2058e = TimerType.TIME;
        this.a = j2;
        this.b = j3;
        this.f2057c = j4;
        this.d = i2;
        this.f2058e = timerType;
        this.f2059f = str;
        this.f2060g = str2;
    }

    public static String getTimerId(@NonNull String str, DynamicTemplateEngine dynamicTemplateEngine) {
        if (TextUtils.isEmpty(str) || dynamicTemplateEngine == null || TextUtils.isEmpty(dynamicTemplateEngine.getSystemCode()) || TextUtils.isEmpty(dynamicTemplateEngine.getBizField())) {
            return str;
        }
        return dynamicTemplateEngine.getEngineHashCode() + CartConstant.KEY_YB_INFO_LINK + dynamicTemplateEngine.getSystemCode() + CartConstant.KEY_YB_INFO_LINK + dynamicTemplateEngine.getBizField() + CartConstant.KEY_YB_INFO_LINK + str;
    }

    public static TimerType getTimerType(String str) {
        char c2;
        int hashCode = str.hashCode();
        if (hashCode == -1607243192) {
            if (str.equals("endTime")) {
                c2 = 0;
            }
            c2 = '\uffff';
        } else if (hashCode != -1039745817) {
            if (hashCode == 3560141 && str.equals("time")) {
                c2 = 1;
            }
            c2 = '\uffff';
        } else {
            if (str.equals("normal")) {
                c2 = 2;
            }
            c2 = '\uffff';
        }
        return c2 != 0 ? c2 != 1 ? TimerType.NORMAL : TimerType.TIME : TimerType.END_TIME;
    }

    public long getEndValue() {
        return this.b;
    }

    public String getFormatType() {
        return this.f2059f;
    }

    public long getInterval() {
        return this.f2057c;
    }

    public long getStartValue() {
        return this.a;
    }

    public int getStep() {
        return this.d;
    }

    public String getTimerId() {
        return this.f2060g;
    }

    public TimerType getType() {
        return this.f2058e;
    }

    public String toString() {
        return "TimerRequest{startValue=" + this.a + ", endValue=" + this.b + ", interval=" + this.f2057c + ", step=" + this.d + ", type=" + this.f2058e + ", formatType='" + this.f2059f + "', timerId='" + this.f2060g + "'}";
    }
}
