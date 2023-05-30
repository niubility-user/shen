package com.meizu.cloud.pushsdk.c.c;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes13.dex */
public class b {
    private static final String a = "b";
    private static final Object b = new Object();

    /* renamed from: c  reason: collision with root package name */
    private static b f15696c;

    private b(Context context) {
        try {
            System.setProperty("sun.net.http.allowRestrictedHeaders", DYConstants.DY_TRUE);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context);
    }

    public static b a(Context context) {
        if (f15696c == null) {
            synchronized (b) {
                if (f15696c == null) {
                    f15696c = new b(context);
                }
            }
        }
        return f15696c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00fc, code lost:
        if (r1 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fe, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0123, code lost:
        if (r1 != null) goto L59;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.meizu.cloud.pushsdk.c.c.b] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.meizu.cloud.pushsdk.c.c.c b(java.lang.String r7, java.util.Map<java.lang.String, java.lang.String> r8, java.lang.String r9) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.c.c.b.b(java.lang.String, java.util.Map, java.lang.String):com.meizu.cloud.pushsdk.c.c.c");
    }

    private Map<String, String> c(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>(2);
        }
        byte[] n2 = a.l().n();
        if (n2 == null || n2.length <= 0) {
            byte[] m2 = a.l().m();
            if (m2 != null && m2.length > 0) {
                String str = new String(a.l().m());
                DebugLogger.d(a, "attach x_a_key: " + str);
                map.put("X-A-Key", str);
            }
        } else {
            String str2 = new String(n2);
            DebugLogger.d(a, "attach x_s_key: " + str2);
            map.put("X-S-Key", str2);
        }
        return map;
    }

    private void d(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
        } catch (Throwable th) {
            th = th;
            gZIPOutputStream = null;
        }
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.flush();
            try {
                gZIPOutputStream.close();
            } catch (Exception unused) {
            }
        } catch (Throwable th2) {
            th = th2;
            if (gZIPOutputStream != null) {
                try {
                    gZIPOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }

    private void e(URLConnection uRLConnection) {
        try {
            String headerField = uRLConnection.getHeaderField("Key-Timeout");
            DebugLogger.d(a, "get keyTimeout = " + headerField);
        } catch (NullPointerException unused) {
        }
    }

    private byte[] f(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            try {
                int read = inputStream.read();
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(read);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused2) {
        }
        return byteArray;
    }

    private void h(URLConnection uRLConnection) {
        try {
            String headerField = uRLConnection.getHeaderField("X-S-Key");
            DebugLogger.d(a, "get x_s_key = " + headerField);
            if (TextUtils.isEmpty(headerField)) {
                return;
            }
            a.l().j(headerField);
        } catch (NullPointerException unused) {
        }
    }

    public c g(String str, Map<String, String> map, String str2) {
        try {
            return b(str, c(map), str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
