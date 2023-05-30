package com.jd.aips.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* loaded from: classes12.dex */
public class FsImageUtils {
    public static String buildRiskImageData(Context context, String str, byte[] bArr, String str2, String str3) {
        String encryptAndBase64 = AksUtils.encryptAndBase64(context, bArr);
        return str + "#" + MD5Utils.md5_32bit(encryptAndBase64) + ":" + str2 + ":" + str3 + ":" + encryptAndBase64;
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * FsBaseInfoUtils.getDensity(context)) + 0.5f);
    }

    public static byte[] toJpeg(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr = null;
        bArr = null;
        bArr = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception unused) {
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (Exception unused2) {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream2 = byteArrayOutputStream;
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (IOException unused4) {
        }
        return bArr;
    }
}
