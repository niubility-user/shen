package com.facebook.common.util;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
public class StreamUtil {
    public static byte[] getBytesFromStream(InputStream inputStream) {
        return getBytesFromStream(inputStream, inputStream.available());
    }

    public static byte[] getBytesFromStream(InputStream inputStream, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i2) { // from class: com.facebook.common.util.StreamUtil.1
            @Override // java.io.ByteArrayOutputStream
            public byte[] toByteArray() {
                int i3 = ((ByteArrayOutputStream) this).count;
                byte[] bArr = ((ByteArrayOutputStream) this).buf;
                return i3 == bArr.length ? bArr : super.toByteArray();
            }
        };
        ByteStreams.copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static long skip(InputStream inputStream, long j2) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkArgument(j2 >= 0);
        long j3 = j2;
        while (j3 > 0) {
            long skip = inputStream.skip(j3);
            if (skip <= 0) {
                if (inputStream.read() == -1) {
                    return j2 - j3;
                }
                skip = 1;
            }
            j3 -= skip;
        }
        return j2;
    }
}
