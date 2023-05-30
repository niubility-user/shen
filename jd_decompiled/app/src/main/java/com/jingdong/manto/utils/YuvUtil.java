package com.jingdong.manto.utils;

/* loaded from: classes16.dex */
public class YuvUtil {
    static {
        System.loadLibrary("yuvutil");
    }

    public static native void yuvCompress(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5, int i6, int i7, boolean z);

    public static native void yuvCropI420(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5, int i6, int i7);

    public static native void yuvI420ToARGB(byte[] bArr, int i2, int i3, int i4, byte[] bArr2);

    public static native void yuvI420ToNV21(byte[] bArr, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToRGB24(byte[] bArr, int i2, int i3, byte[] bArr2);

    public static native void yuvI420ToRGB565(byte[] bArr, int i2, int i3, byte[] bArr2);

    public static native void yuvNV21ToI420(byte[] bArr, int i2, int i3, byte[] bArr2);

    public static native void yuvRotateI420(byte[] bArr, int i2, int i3, byte[] bArr2, int i4);

    public static native void yuvScaleI420(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, int i5, int i6);
}
