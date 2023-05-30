package com.jdjr.acr;

import androidx.annotation.WorkerThread;

/* loaded from: classes18.dex */
public interface IntegrityCheckCallback {
    public static final int RESULT_FAIL = 2;
    public static final int RESULT_SIG_ERROR = 3;
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_UNKNOWN = 0;

    @WorkerThread
    void onResult(int i2);
}
