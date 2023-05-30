package com.jingdong.manto.sdk.api;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface IFaceDetect extends IMantoSdkBase {

    /* loaded from: classes16.dex */
    public interface FaceDetectCallback extends IMantoSdkBase {
        void onFailed(Bundle bundle);

        void onResult(Bundle bundle);
    }

    /* loaded from: classes16.dex */
    public interface FaceInitCallback extends IMantoSdkBase {
        void onFailed(String str);

        void onSuccess();
    }

    void getJfaceTracker(byte[] bArr, int i2, int i3, FaceDetectCallback faceDetectCallback);

    void init(Context context, FaceInitCallback faceInitCallback);

    void release();
}
