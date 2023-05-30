package com.jingdong.app.mall.bundle.jdbrotli;

import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public class Dictionary {
    private static volatile ByteBuffer data;

    /* loaded from: classes2.dex */
    private static class DataLoader {
        static final boolean OK;

        static {
            boolean z;
            try {
                Class.forName(Dictionary.class.getPackage().getName() + ".DictionaryData");
                z = true;
            } catch (Throwable unused) {
                z = false;
            }
            OK = z;
        }

        private DataLoader() {
        }
    }

    public static ByteBuffer getData() {
        if (data != null) {
            return data;
        }
        if (DataLoader.OK) {
            return data;
        }
        throw new BrotliRuntimeException("brotli dictionary is not set");
    }

    public static void setData(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect() && byteBuffer.isReadOnly()) {
            data = byteBuffer;
            return;
        }
        throw new BrotliRuntimeException("data must be a direct read-only byte buffer");
    }
}
