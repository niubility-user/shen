package c.t.m.g;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

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

    /* JADX WARN: Removed duplicated region for block: B:187:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:201:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(java.lang.String r23, byte[] r24, int r25, java.lang.Object r26) {
        /*
            Method dump skipped, instructions count: 440
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.c0.b(java.lang.String, byte[], int, java.lang.Object):void");
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
