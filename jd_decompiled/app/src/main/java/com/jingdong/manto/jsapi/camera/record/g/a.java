package com.jingdong.manto.jsapi.camera.record.g;

import android.graphics.Bitmap;
import android.hardware.Camera;

/* loaded from: classes15.dex */
public interface a {
    void a(Bitmap bitmap);

    void a(String str);

    void onPreviewFrame(byte[] bArr, Camera camera);
}
