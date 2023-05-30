package com.jingdong.app.mall.bundle.updownload.download;

import android.os.Bundle;

/* loaded from: classes3.dex */
public interface IDownloadListener {

    /* loaded from: classes3.dex */
    public interface ERROR_CODE {
        public static final int BODY_NULL = -2;
        public static final int DATA_PROCESS_ERROR = -1000;
        public static final int DEFAULT = -1;
        public static final int RESPONSE_NULL = -3;
    }

    void onCancel(Object obj, Bundle bundle);

    void onComplete(Object obj, String str, Bundle bundle);

    void onException(Object obj, Exception exc, Bundle bundle);

    void onFailure(Object obj, int i2, String str, Bundle bundle);

    void onProgress(Object obj, long j2, long j3, Bundle bundle);
}
