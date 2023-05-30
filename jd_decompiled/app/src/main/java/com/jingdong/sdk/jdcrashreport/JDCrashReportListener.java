package com.jingdong.sdk.jdcrashreport;

import com.jingdong.sdk.jdcrashreport.common.CrashInfo;

/* loaded from: classes7.dex */
public interface JDCrashReportListener {
    void onEnd(int i2, String str, CrashInfo crashInfo);

    void onError(int i2, String str, CrashInfo crashInfo);

    void onStart(CrashInfo crashInfo);
}
