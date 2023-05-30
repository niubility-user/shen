package com.tencent.map.lib;

/* loaded from: classes9.dex */
public interface JNIInterfaceCallback {
    Object callback(int i2, int i3, String str, byte[] bArr, Object obj);

    int callbackGetGLContext();

    boolean onJniCallbackRenderMapFrame(int i2);

    void onMapCameraChangeStopped();

    void onMapCameraChanged();

    void onMapLoaded();

    void onVisualLayerClickResult(float f2, float f3, long j2, String str, String str2);
}
