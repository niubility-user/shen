package com.jdjr.risk.util.b;

import android.content.Context;
import com.jdjr.wsm4.Wsm4Manager;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* loaded from: classes18.dex */
public class d {
    private static String a = "JDT-RISK-BIOMETRIC";

    public static void a(Context context, String str, String str2) {
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bytes = Wsm4Manager.newInstance(context).wsm4Encrypt(str).getBytes();
            if (bytes != null) {
                fileOutputStream = context.openFileOutput(str2, 0);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
            }
            if (fileOutputStream == null) {
                return;
            }
        } catch (Throwable unused) {
            if (fileOutputStream == null) {
                return;
            }
        }
        try {
            fileOutputStream.close();
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r4 == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
        if (r4 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0024, code lost:
        r4.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static byte[] a(Context context, String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = context.openFileInput(str);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                r0 = fileInputStream.read(bArr) > 0 ? Wsm4Manager.newInstance(context).wsm4Decrypt(new String(bArr)).getBytes() : null;
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
    }
}
