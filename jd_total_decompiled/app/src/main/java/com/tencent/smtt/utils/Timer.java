package com.tencent.smtt.utils;

/* loaded from: classes9.dex */
public class Timer {
    public final int timeOut;

    public Timer(int i2) {
        if (i2 >= 0) {
            this.timeOut = i2;
            return;
        }
        throw new IllegalArgumentException("time out can not < 0, current is " + i2);
    }

    public void onTimeOut() {
    }
}
