package com.jd.stat.common.relinker;

import com.jd.stat.common.relinker.b;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes18.dex */
public class a implements b.a {
    /* JADX WARN: Code restructure failed: missing block: B:113:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r8 != null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        r23.a("FATAL! Couldn't find application APK!");
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r8 == null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0034, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        r5 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0104, code lost:
        if (r5 != null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0106, code lost:
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x010a, code lost:
        throw r0;
     */
    @Override // com.jd.stat.common.relinker.b.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r19, java.lang.String[] r20, java.lang.String r21, java.io.File r22, com.jd.stat.common.relinker.c r23) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.relinker.a.a(android.content.Context, java.lang.String[], java.lang.String, java.io.File, com.jd.stat.common.relinker.c):void");
    }

    private long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                outputStream.flush();
                return j2;
            }
            outputStream.write(bArr, 0, read);
            j2 += read;
        }
    }

    private void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
