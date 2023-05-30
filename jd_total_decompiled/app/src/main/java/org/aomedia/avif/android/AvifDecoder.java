package org.aomedia.avif.android;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* loaded from: classes11.dex */
public class AvifDecoder {

    /* loaded from: classes11.dex */
    public static class Info {
        public int depth;
        public int height;
        public int width;
    }

    static {
        try {
            System.loadLibrary("avif_android");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
    }

    private AvifDecoder() {
    }

    public static boolean a(ByteBuffer byteBuffer) {
        return isAvifImage(byteBuffer, byteBuffer.remaining());
    }

    public static native boolean decode(ByteBuffer byteBuffer, int i2, Bitmap bitmap);

    public static native boolean getInfo(ByteBuffer byteBuffer, int i2, Info info);

    private static native boolean isAvifImage(ByteBuffer byteBuffer, int i2);
}
