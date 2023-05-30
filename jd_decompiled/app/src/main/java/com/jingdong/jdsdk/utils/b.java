package com.jingdong.jdsdk.utils;

import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes14.dex */
public class b {
    private static int a = 16384;

    /* loaded from: classes14.dex */
    public interface a {
        void a(int i2, int i3);
    }

    public static void a(InputStream inputStream, FileOutputStream fileOutputStream, a aVar, HttpGroup.StopController stopController) throws Exception {
        try {
            try {
                byte[] bArr = new byte[a];
                int i2 = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1 || stopController.isStop()) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    i2 += read;
                    if (aVar != null) {
                        aVar.a(read, i2);
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception e2) {
                throw e2;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }
}
