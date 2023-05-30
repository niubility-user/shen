package com.jingdong.manto.utils;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;

/* loaded from: classes16.dex */
public class MantoStringUtils {
    private static final String TAG = "MantoStringUtils";

    public static String convertByte2HexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(String.format("%02x", Integer.valueOf(b & 255)));
            }
        }
        return sb.toString();
    }

    public static byte[] convertHexString2Bytes(String str) {
        if (str == null || str.length() <= 0) {
            return new byte[0];
        }
        try {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) (Integer.parseInt(str.substring(i3, i3 + 2), 16) & 255);
            }
            return bArr;
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static String convertStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[4096];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            try {
                int read = inputStreamReader.read(cArr);
                if (-1 == read) {
                    t.a(inputStreamReader);
                    t.a(inputStream);
                    return stringWriter.toString();
                }
                stringWriter.write(cArr, 0, read);
            } catch (Exception unused) {
                t.a(inputStreamReader);
                t.a(inputStream);
                return "";
            } catch (Throwable th) {
                t.a(inputStreamReader);
                t.a(inputStream);
                throw th;
            }
        }
    }

    public static String deleteLeftSlash(String str) {
        if (isEmpty(str)) {
            return str;
        }
        int i2 = 0;
        while (i2 < str.length() && '/' == str.charAt(i2)) {
            i2++;
        }
        return str.substring(i2);
    }

    public static SpannableString getForegroundSpannable(String str, String str2, int i2) {
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(str);
        if (indexOf >= 0) {
            spannableString.setSpan(new ForegroundColorSpan(i2), indexOf, str.length() + indexOf, 17);
        }
        return spannableString;
    }

    public static long getLong(String str, long j2) {
        if (str != null) {
            try {
                return str.length() > 0 ? Long.decode(str).longValue() : j2;
            } catch (Throwable th) {
                MantoLog.e(TAG, String.format("%s", throwable2String(th)));
                return j2;
            }
        }
        return j2;
    }

    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    public static boolean isEquals(String str, String str2) {
        if (isEmpty(str) && isEmpty(str2)) {
            return true;
        }
        if (!isEmpty(str) || isEmpty(str2)) {
            if (isEmpty(str) || !isEmpty(str2)) {
                return str.equals(str2);
            }
            return false;
        }
        return false;
    }

    public static String optional(String str, String str2) {
        return str != null ? str : str2;
    }

    public static String throwable2String(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            th.printStackTrace(printStream);
            String obj = byteArrayOutputStream.toString();
            printStream.close();
            byteArrayOutputStream.close();
            return obj;
        } catch (Exception unused) {
            return "";
        }
    }
}
