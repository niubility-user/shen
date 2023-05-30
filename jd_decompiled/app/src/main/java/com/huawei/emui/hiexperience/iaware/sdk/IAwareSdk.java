package com.huawei.emui.hiexperience.iaware.sdk;

import android.os.Parcel;
import android.rms.iaware.IAwareSdkCore;

/* loaded from: classes12.dex */
public class IAwareSdk {
    private static final int FIRST_ASYNC_CALL_TRANSACTION = 10001;
    private static final int FIRST_SYNC_CALL_TRANSACTION = 1;
    private static final int LAST_ASYNC_CALL_TRANSACTION = 16777215;
    private static final int LAST_SYNC_CALL_TRANSACTION = 10000;
    private static final int TRANSACTION_ASYNC_REPORT_DATA = 10001;
    private static final int TRANSACTION_REPORT_DATA = 1;

    public static void asyncReportData(int i2, String str, long j2) {
        reportData(i2, str, true);
    }

    private static void reportData(int i2, String str, boolean z) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        obtain.writeInt(i2);
        obtain.writeLong(System.currentTimeMillis());
        obtain.writeString(str);
        IAwareSdkCore.handleEvent(z ? 10001 : 1, obtain, obtain2, i2);
        obtain2.recycle();
        obtain.recycle();
    }

    public static void reportData(int i2, String str, long j2) {
        reportData(i2, str, false);
    }
}
