package com.jingdong.common.jdreactFramework.utils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class AresCommonUtils {
    static final String TAG = "AresCommonUtils";
    private static SimpleDateFormat sDateFormat;

    public static String DateFormatStr(long j2, String str) {
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat(str);
        }
        try {
            return sDateFormat.format(new Date(j2));
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return null;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static long dateStrToTimestamp(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sDateFormat.parse(str).getTime();
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return 0L;
        }
    }

    public static void decompress(File file, String str) throws Exception {
        ZipUtils.decompress(file, str);
    }

    public static String fileToBase64(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        String str = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            closeQuietly(fileInputStream2);
            throw th;
        }
        try {
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                str = android.util.Base64.encodeToString(bArr, 0, fileInputStream.read(bArr), 0);
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                closeQuietly(fileInputStream2);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            JLog.e(TAG, e.toString());
            closeQuietly(fileInputStream);
            return str;
        }
        closeQuietly(fileInputStream);
        return str;
    }

    public static int hexStrToInt(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return new BigInteger(str, 16).intValue();
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return i2;
        }
    }

    public static void invokeCallback(JDCallback jDCallback, Object... objArr) {
        if (jDCallback != null) {
            try {
                jDCallback.invoke(objArr);
            } catch (Exception e2) {
                JLog.e(TAG, e2.toString());
            }
        }
    }

    public static Bundle jsonStr2Bundle(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            for (String str2 : parseObject.keySet()) {
                Object obj = parseObject.get(str2);
                if (obj instanceof Boolean) {
                    bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    bundle.putString(str2, String.valueOf(obj));
                } else if (obj instanceof Byte) {
                    bundle.putByte(str2, ((Byte) obj).byteValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str2, ((Float) obj).floatValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(str2, ((Integer) obj).intValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str2, ((Short) obj).shortValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str2, ((Double) obj).doubleValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str2, ((Long) obj).longValue());
                } else {
                    bundle.putString(str2, String.valueOf(obj));
                }
            }
            return bundle;
        } catch (Exception e2) {
            JLog.e(TAG, String.valueOf(e2));
            return bundle;
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String timestampToDateStr(long j2) {
        if (sDateFormat == null) {
            sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return sDateFormat.format(new Date(j2));
        } catch (Exception e2) {
            JLog.e(TAG, e2.toString());
            return null;
        }
    }
}
