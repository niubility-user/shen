package com.jd.dynamic.entity;

import androidx.annotation.Keep;
import com.jd.dynamic.base.DynamicMtaUtil;

@Keep
/* loaded from: classes13.dex */
public class MtaTimePair {
    public long endTime;
    public long startTime;

    public static MtaTimePair newInstance() {
        return new MtaTimePair();
    }

    public void endRecord() {
        this.endTime = System.nanoTime();
    }

    public boolean isValid() {
        long j2 = this.startTime;
        if (j2 > 0) {
            long j3 = this.endTime;
            if (j3 > 0 && j3 >= j2) {
                return true;
            }
        }
        return false;
    }

    public void startRecord() {
        this.startTime = System.nanoTime();
    }

    public String useTime() {
        try {
            return DynamicMtaUtil.getCurMicroseconds(this.endTime - this.startTime);
        } catch (Exception unused) {
            return "0";
        }
    }
}
