package com.tencent.mapsdk.engine.jni.models;

import androidx.annotation.Keep;
import com.tencent.mapsdk.internal.fc;
import java.util.Arrays;

@Keep
/* loaded from: classes9.dex */
public class TextBitmapInfo {
    public boolean bold;
    public float density;
    public int height;
    public byte[] mData = new byte[4];
    public int pitch;
    public int width;

    public void fill(byte[] bArr) {
        Arrays.fill(this.mData, (byte) 0);
        System.arraycopy(bArr, 0, this.mData, 0, 4);
        this.density = fc.d(this.mData);
        System.arraycopy(bArr, 4, this.mData, 0, 4);
        this.width = fc.e(this.mData);
        System.arraycopy(bArr, 8, this.mData, 0, 4);
        this.height = fc.e(this.mData);
        System.arraycopy(bArr, 12, this.mData, 0, 4);
        this.pitch = fc.e(this.mData);
        System.arraycopy(bArr, 16, this.mData, 0, 1);
        this.bold = fc.b(this.mData);
    }
}
