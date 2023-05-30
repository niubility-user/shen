package c.t.m.g;

import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/* loaded from: classes.dex */
public class c0 {
    public static k a;

    /* loaded from: classes.dex */
    public interface a {
        void a(String str);

        void a(byte[] bArr);
    }

    /* loaded from: classes.dex */
    public interface b {
        void a(int i2, Map<String, Object> map);
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(String str);

        void b(String str);
    }

    public static String a(String str) {
        if (str != null) {
            for (String str2 : str.split(";")) {
                String trim = str2.trim();
                int indexOf = trim.indexOf("charset=");
                if (-1 != indexOf) {
                    return trim.substring(indexOf + 8, trim.length());
                }
            }
        }
        return "GBK";
    }

    /* JADX WARN: Removed duplicated region for block: B:288:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:302:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void b(String str, byte[] bArr, int i2, Object obj) {
        Object obj2;
        long j2;
        long j3;
        long j4;
        boolean z;
        k kVar;
        long j5;
        boolean z2;
        c cVar = (obj == null || !(obj instanceof c)) ? null : (c) obj;
        a aVar = (obj == null || !(obj instanceof a)) ? null : (a) obj;
        b bVar = (obj == null || !(obj instanceof b)) ? null : (b) obj;
        HashMap hashMap = bVar != null ? new HashMap() : null;
        long currentTimeMillis = System.currentTimeMillis();
        String s = z3.s();
        long j6 = 0;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.4; Nexus 5 Build/KRT16M)");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            httpURLConnection.setRequestProperty("tmap-traceid", s);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoOutput(true);
            long length = bArr.length;
            try {
                httpURLConnection.setFixedLengthStreamingMode(length);
                j5 = length;
                try {
                    httpURLConnection.setRequestProperty("Connection", "close");
                    d(bArr, httpURLConnection.getOutputStream());
                    int responseCode = httpURLConnection.getResponseCode();
                    j2 = currentTimeMillis;
                    try {
                        if (responseCode != 200) {
                            String concat = "net sdk error: ".concat(String.valueOf(responseCode));
                            if (cVar != null) {
                                cVar.a(concat);
                            }
                            if (aVar != null) {
                                aVar.a(concat);
                            }
                            if (bVar != null && hashMap != null) {
                                hashMap.put("resp_code", Integer.valueOf(responseCode));
                                hashMap.put("msg_fail", concat);
                                hashMap.put("tmap_traceid", s);
                                bVar.a(0, hashMap);
                            }
                            try {
                                httpURLConnection.getInputStream().close();
                            } catch (Throwable unused) {
                            }
                            obj2 = "msg_fail";
                            z2 = false;
                            j6 = 4;
                        } else {
                            String a2 = a(httpURLConnection.getHeaderField("content-type"));
                            byte[] e2 = e(httpURLConnection.getInputStream());
                            if (e2 != null) {
                                obj2 = "msg_fail";
                                try {
                                    j6 = e2.length;
                                } catch (Throwable th) {
                                    th = th;
                                    j6 = j5;
                                    if (th instanceof UnknownHostException) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("postSync error.");
                                        sb.append(th.getMessage());
                                    }
                                    if (i2 > 0 || !((th instanceof GeneralSecurityException) || (th instanceof SSLException))) {
                                        String str2 = "tryTime=" + i2 + DYConstants.DY_REGEX_COMMA + str + DYConstants.DY_REGEX_COMMA + z0.a(th);
                                        if (cVar != null) {
                                            cVar.a(str2);
                                        }
                                        if (aVar != null) {
                                            aVar.a(str2);
                                        }
                                        if (bVar != null && hashMap != null) {
                                            hashMap.put(obj2, str2);
                                            hashMap.put("tmap_traceid", s);
                                            bVar.a(0, hashMap);
                                            j3 = j6;
                                            j4 = 4;
                                            z = false;
                                            kVar = a;
                                            if (kVar != null) {
                                            }
                                        }
                                    } else {
                                        b(str.replaceAll("https:", "http:"), bArr, i2 + 1, obj);
                                    }
                                    j3 = j6;
                                    j4 = 4;
                                    z = false;
                                    kVar = a;
                                    if (kVar != null) {
                                    }
                                }
                            } else {
                                obj2 = "msg_fail";
                            }
                            if (aVar != null) {
                                aVar.a(e2);
                            }
                            if (cVar != null) {
                                cVar.b(new String(e2, a2));
                            }
                            if (bVar != null && hashMap != null) {
                                hashMap.put("resp_code", Integer.valueOf(responseCode));
                                hashMap.put("charset", a2);
                                hashMap.put("byte_data", e2);
                                hashMap.put("tmap_traceid", s);
                                bVar.a(1, hashMap);
                            }
                            z2 = true;
                        }
                        httpURLConnection.disconnect();
                        z = z2;
                        j4 = j6;
                        j3 = j5;
                    } catch (Throwable th2) {
                        th = th2;
                        obj2 = "msg_fail";
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obj2 = "msg_fail";
                    j2 = currentTimeMillis;
                }
            } catch (Throwable th4) {
                th = th4;
                obj2 = "msg_fail";
                j2 = currentTimeMillis;
                j5 = length;
            }
        } catch (Throwable th5) {
            th = th5;
            obj2 = "msg_fail";
            j2 = currentTimeMillis;
        }
        kVar = a;
        if (kVar != null) {
            kVar.a(str, j2, j3, j4, System.currentTimeMillis() - j2, z);
        }
    }

    public static void c(String str, byte[] bArr, Object obj) {
        if (bArr == null) {
            bArr = e2.a;
        }
        b(str, bArr, 0, obj);
    }

    public static void d(byte[] bArr, OutputStream outputStream) {
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
    }

    public static byte[] e(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
        byte[] c2 = y0.a().c(512);
        while (true) {
            int read = inputStream.read(c2);
            if (read == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                inputStream.close();
                y0.a().b(c2);
                return byteArray;
            }
            byteArrayOutputStream.write(c2, 0, read);
        }
    }

    public static byte[] f(String str, Object obj) {
        c cVar = (obj == null || !(obj instanceof c)) ? null : (c) obj;
        a aVar = (obj == null || !(obj instanceof a)) ? null : (a) obj;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.connect();
            byte[] e2 = e(httpURLConnection.getInputStream());
            if (e2 != null) {
                str.getBytes();
            }
            if (aVar != null) {
                aVar.a(e2);
            }
            if (cVar != null) {
                cVar.b(new String(e2, "UTF-8"));
            }
            httpURLConnection.disconnect();
            return e2;
        } catch (Throwable th) {
            long length = str.getBytes().length;
            if (aVar != null) {
                aVar.a(th.toString());
            }
            if (cVar != null) {
                cVar.a(th.toString());
            }
            k kVar = a;
            if (kVar != null) {
                kVar.a(str, currentTimeMillis, length, 0L, System.currentTimeMillis() - currentTimeMillis, false);
            }
            return null;
        }
    }
}
