package com.jingdong.manto.utils;

import android.net.Uri;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class t {
    public static Map<String, Object> a(String str) {
        HashMap hashMap = new HashMap();
        String encodedQuery = Uri.parse("file:///" + str).getEncodedQuery();
        if (MantoStringUtils.isEmpty(encodedQuery)) {
            return hashMap;
        }
        int i2 = 0;
        int length = encodedQuery.length();
        while (true) {
            int indexOf = encodedQuery.indexOf(38, i2);
            int i3 = indexOf != -1 ? indexOf : length;
            int indexOf2 = encodedQuery.indexOf(61, i2);
            if (indexOf2 > i3 || indexOf2 == -1) {
                indexOf2 = i3;
            }
            hashMap.put(Uri.decode(encodedQuery.substring(i2, indexOf2)), indexOf2 == i3 ? "" : encodedQuery.substring(indexOf2 + 1, i3));
            if (indexOf == -1) {
                return hashMap;
            }
            i2 = indexOf + 1;
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static byte[] a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return new byte[0];
        }
        if (byteBuffer.isDirect()) {
            int position = byteBuffer.position();
            byteBuffer.position(0);
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.position(position);
            return bArr;
        }
        return byteBuffer.array();
    }

    public static String b(InputStream inputStream) {
        try {
            try {
            } catch (IOException e2) {
                MantoLog.e("ioUtils", e2);
            }
            if (inputStream != null) {
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                return new String(bArr);
            }
            MantoLog.e("ioUtils", "inputStream is null.");
            a(inputStream);
            return "";
        } finally {
            a(inputStream);
        }
    }

    @NonNull
    public static String b(String str) {
        String path = Uri.parse("file:///" + str).getPath();
        return (path == null || !path.startsWith("/")) ? path == null ? "" : path : path.substring(1);
    }

    public static String c(String str) {
        String b = b(str);
        return b != null ? b.replace(".html", "") : "";
    }

    public static boolean d(String str) {
        return !MantoStringUtils.isEmpty(str) && (URLUtil.isHttpsUrl(str) || URLUtil.isHttpUrl(str));
    }
}
