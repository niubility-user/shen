package com.jingdong.common.unification.uniutil;

import com.jingdong.sdk.oklog.OKLog;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

/* loaded from: classes6.dex */
public class CRCUtils {
    private static final String TAG = "CRCUtils";

    public static long checksumBytes(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return -1L;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return crc32.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0024: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:16:0x0024 */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long checksumInputStream(String str) {
        FileInputStream fileInputStream;
        IOException e2;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
        } catch (IOException e3) {
            OKLog.e(TAG, e3);
        }
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    str = new CRC32();
                    while (true) {
                        try {
                            int read = fileInputStream.read();
                            if (read == -1) {
                                break;
                            }
                            str.update(read);
                        } catch (IOException e4) {
                            e2 = e4;
                            OKLog.e(TAG, e2);
                            if (fileInputStream != null) {
                                fileInputStream.close();
                                str = str;
                            }
                            if (str != 0) {
                            }
                        }
                    }
                    fileInputStream.close();
                    str = str;
                } catch (IOException e5) {
                    e2 = e5;
                    str = 0;
                }
            } catch (IOException e6) {
                fileInputStream = null;
                e2 = e6;
                str = 0;
            } catch (Throwable th) {
                th = th;
                if (inputStream2 != null) {
                }
                throw th;
            }
            if (str != 0) {
                return -1L;
            }
            return str.getValue();
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStream;
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (IOException e7) {
                    OKLog.e(TAG, e7);
                }
            }
            throw th;
        }
    }
}
