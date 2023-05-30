package com.meizu.cloud.pushsdk.c.c;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

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
    */
    private c b(String str, Map<String, String> map, String str2) throws Exception {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        byte[] f2;
        c cVar = null;
        cVar = null;
        cVar = null;
        ?? r0 = 0;
        cVar = null;
        if (str2 != null) {
            byte[] e2 = a.l().e(str2.getBytes());
            String str3 = e2 != null ? new String(Base64.encode(e2, 2)) : null;
            try {
                httpURLConnection = (HttpURLConnection) new URL(PushConstants.URL_UPLOAD_DATA).openConnection();
                httpURLConnection.setRequestMethod(str);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setRequestProperty("Connection", "keep-alive");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
                if (map != null && map.size() > 0) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                try {
                    if (str3 != null) {
                        try {
                            d(httpURLConnection, str3.getBytes());
                        } catch (Exception e3) {
                            e = e3;
                            inputStream = null;
                            DebugLogger.e(a, "realStringPartRequest error " + e.getMessage());
                        } catch (Throwable th) {
                            th = th;
                            if (r0 != 0) {
                                try {
                                    r0.close();
                                } catch (IOException unused) {
                                }
                            }
                            httpURLConnection.disconnect();
                            throw th;
                        }
                    }
                    int responseCode = httpURLConnection.getResponseCode();
                    String str4 = a;
                    DebugLogger.d(str4, "code = " + responseCode);
                    h(httpURLConnection);
                    e(httpURLConnection);
                    inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null) {
                        try {
                            f2 = f(inputStream);
                            if (f2 != null) {
                                String str5 = new String(f2);
                                DebugLogger.d(str4, "body = " + str5);
                                try {
                                    new JSONObject(str5).getInt("code");
                                } catch (JSONException e4) {
                                    e4.printStackTrace();
                                }
                            }
                        } catch (Exception e5) {
                            e = e5;
                            DebugLogger.e(a, "realStringPartRequest error " + e.getMessage());
                        }
                    } else {
                        f2 = null;
                    }
                    cVar = f2 != null ? new c(responseCode, new String(f2)) : new c(responseCode, null);
                } catch (Throwable th2) {
                    th = th2;
                    r0 = str3;
                }
            } catch (MalformedURLException e6) {
                e6.printStackTrace();
            }
        }
        return cVar;
        httpURLConnection.disconnect();
        return cVar;
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
