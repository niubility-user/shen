package com.facebook.common.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* loaded from: classes.dex */
public class Files {
    private Files() {
    }

    static byte[] readFile(InputStream inputStream, long j2) {
        if (j2 <= 2147483647L) {
            return j2 == 0 ? ByteStreams.toByteArray(inputStream) : ByteStreams.toByteArray(inputStream, (int) j2);
        }
        throw new OutOfMemoryError("file is too large to fit in a byte array: " + j2 + " bytes");
    }

    public static byte[] toByteArray(File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] readFile = readFile(fileInputStream, fileInputStream.getChannel().size());
            fileInputStream.close();
            return readFile;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }
}
