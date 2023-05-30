package com.tencent.smtt.utils;

import android.os.Build;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* loaded from: classes9.dex */
public class g {

    /* loaded from: classes9.dex */
    public interface a {
        void a(int i2);
    }

    public static String a(String str, byte[] bArr, a aVar, boolean z) {
        String str2;
        String str3;
        try {
            str3 = str + (z ? i.a().c() : h.a().b());
            try {
                bArr = z ? i.a().a(bArr) : h.a().a(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
                TbsLog.e("HttpUtils", "rsaKey exception #2: " + e2);
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            str2 = "rsaKey exception #1: " + e3;
        }
        if (bArr == null) {
            str2 = "postData is null";
            TbsLog.e("HttpUtils", str2);
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        hashMap.put(HttpHeaders.CONTENT_LENGTH, String.valueOf(bArr.length));
        HttpURLConnection a2 = a(str3, hashMap);
        if (a2 != null) {
            a(a2, bArr);
            return a(a2, aVar, z);
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(6:8|(6:(9:(1:46)|14|15|16|17|18|(2:19|(1:21)(1:22))|(1:24)(1:29)|25)(1:12)|17|18|(3:19|(0)(0)|21)|(0)(0)|25)|13|14|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007a, code lost:
        r4 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007b, code lost:
        r0 = r5;
        r5 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004d A[Catch: all -> 0x0075, LOOP:0: B:22:0x0046->B:24:0x004d, LOOP_END, TryCatch #1 {all -> 0x0075, blocks: (B:21:0x0044, B:22:0x0046, B:24:0x004d, B:26:0x0054, B:28:0x0063), top: B:47:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0054 A[Catch: all -> 0x0075, TryCatch #1 {all -> 0x0075, blocks: (B:21:0x0044, B:22:0x0046, B:24:0x004d, B:26:0x0054, B:28:0x0063), top: B:47:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063 A[Catch: all -> 0x0075, TRY_LEAVE, TryCatch #1 {all -> 0x0075, blocks: (B:21:0x0044, B:22:0x0046, B:24:0x004d, B:26:0x0054, B:28:0x0063), top: B:47:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0052 A[EDGE_INSN: B:51:0x0052->B:25:0x0052 BREAK  A[LOOP:0: B:22:0x0046->B:24:0x004d], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(HttpURLConnection httpURLConnection, a aVar, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        String str;
        InputStream inflaterInputStream;
        byte[] bArr;
        int read;
        InputStream inputStream = null;
        try {
            int responseCode = httpURLConnection.getResponseCode();
            if (aVar != null) {
                aVar.a(responseCode);
            }
            if (responseCode == 200) {
                InputStream inputStream2 = httpURLConnection.getInputStream();
                String contentEncoding = httpURLConnection.getContentEncoding();
                try {
                    if (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip")) {
                        if (contentEncoding != null && contentEncoding.equalsIgnoreCase("deflate")) {
                            inflaterInputStream = new InflaterInputStream(inputStream2, new Inflater(true));
                        }
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                        bArr = new byte[128];
                        while (true) {
                            read = inputStream2.read(bArr);
                            if (read != -1) {
                                break;
                            }
                            byteArrayOutputStream2.write(bArr, 0, read);
                        }
                        inputStream = inputStream2;
                        str = !z ? new String(byteArrayOutputStream2.toByteArray(), "utf-8") : new String(h.a().c(byteArrayOutputStream2.toByteArray()));
                    } else {
                        inflaterInputStream = new GZIPInputStream(inputStream2);
                    }
                    bArr = new byte[128];
                    while (true) {
                        read = inputStream2.read(bArr);
                        if (read != -1) {
                        }
                        byteArrayOutputStream2.write(bArr, 0, read);
                    }
                    inputStream = inputStream2;
                    str = !z ? new String(byteArrayOutputStream2.toByteArray(), "utf-8") : new String(h.a().c(byteArrayOutputStream2.toByteArray()));
                } catch (Throwable th) {
                    inputStream = inputStream2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    th = th;
                    try {
                        th.printStackTrace();
                        TbsLog.e("HttpUtil", "httpPost exception: " + th);
                        return "[HttpError] " + th.getMessage();
                    } finally {
                        a(inputStream);
                        a(byteArrayOutputStream);
                    }
                }
                inputStream2 = inflaterInputStream;
                byteArrayOutputStream2 = new ByteArrayOutputStream();
            } else {
                byteArrayOutputStream2 = null;
                str = null;
            }
            a(inputStream);
            a(byteArrayOutputStream2);
            return str;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    private static HttpURLConnection a(String str, Map<String, String> map) {
        String str2;
        String str3;
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setConnectTimeout(20000);
                if (Build.VERSION.SDK_INT > 13) {
                    str2 = "Connection";
                    str3 = "close";
                } else {
                    str2 = "http.keepAlive";
                    str3 = DYConstants.DY_FALSE;
                }
                httpURLConnection2.setRequestProperty(str2, str3);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    httpURLConnection2.setRequestProperty(entry.getKey(), entry.getValue());
                }
                return httpURLConnection2;
            } catch (Exception e2) {
                e = e2;
                httpURLConnection = httpURLConnection2;
                TbsLog.e("HttpUtil", "initHttpPostURLConnection exception: " + e);
                return httpURLConnection;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    private static void a(HttpURLConnection httpURLConnection, byte[] bArr) {
        OutputStream outputStream = null;
        try {
            try {
                outputStream = httpURLConnection.getOutputStream();
                outputStream.write(bArr);
                outputStream.flush();
            } catch (Exception e2) {
                TbsLog.e("HttpUtil", "writePostData exception: " + e2);
            }
        } finally {
            a(outputStream);
        }
    }
}
