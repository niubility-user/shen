package c.t.m.g;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import c.t.m.g.c0;
import com.google.common.net.HttpHeaders;
import com.jd.dynamic.DYConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class p4 implements q1 {

    /* loaded from: classes.dex */
    public class a implements c0.c {
        public final /* synthetic */ String[] a;

        public a(p4 p4Var, String[] strArr) {
            this.a = strArr;
        }

        @Override // c.t.m.g.c0.c
        public void a(String str) {
        }

        @Override // c.t.m.g.c0.c
        public void b(String str) {
            this.a[0] = str;
            new StringBuilder("NationCode, onSuccessed: ").append(this.a[0]);
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public byte[] b;
        public String a = "gbk";

        /* renamed from: c */
        public String f605c = "";
    }

    public p4(Context context, String str) {
    }

    public static void b(byte[] bArr, OutputStream outputStream) {
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
    }

    public static byte[] c(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
        byte[] c2 = y0.a().c(512);
        while (true) {
            int read = inputStream.read(c2);
            if (read == -1) {
                inputStream.close();
                y0.a().b(c2);
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(c2, 0, read);
        }
    }

    public static b d(String str, byte[] bArr) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("User-Agent", "Dalvik/1.6.0 (Linux; U; Android 4.4; Nexus 5 Build/KRT16M)");
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.setRequestProperty("Connection", "close");
            b(bArr, httpURLConnection.getOutputStream());
            int responseCode = httpURLConnection.getResponseCode();
            StringBuilder sb = new StringBuilder("urlStr: ");
            sb.append(url);
            sb.append(", retCode : ");
            sb.append(responseCode);
            if (responseCode != 200) {
                httpURLConnection.disconnect();
                return null;
            }
            b bVar = new b();
            String headerField = httpURLConnection.getHeaderField("content-type");
            String headerField2 = httpURLConnection.getHeaderField("x-android-sent-millis");
            String e2 = e(headerField);
            byte[] c2 = c(httpURLConnection.getInputStream());
            bVar.a = e2;
            bVar.b = c2;
            bVar.f605c = headerField2;
            return bVar;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String e(String str) {
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

    @Override // c.t.m.g.q1
    public Bundle a(String str, byte[] bArr) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            b d = d(str, bArr);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            if (d != null) {
                o4.o("NET", "0,0," + DYConstants.DY_NULL_STR + DYConstants.DY_REGEX_COMMA + currentTimeMillis2 + DYConstants.DY_REGEX_COMMA + w1.a());
                Bundle bundle = new Bundle();
                bundle.putString("req_key", "");
                String str2 = d.f605c;
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putLong("data_header_time", Long.parseLong(str2));
                }
                byte[] bArr2 = d.b;
                if (bArr2 == null) {
                    bundle.putByteArray("data_bytes", "{}".getBytes("UTF-8"));
                    bundle.putString("data_charset", "utf-8");
                    return bundle;
                }
                String str3 = d.a;
                bundle.putByteArray("data_bytes", bArr2);
                bundle.putString("data_charset", str3);
                return bundle;
            }
            throw new IOException("net sdk error: ".concat("response is null"));
        } catch (Exception e2) {
            throw new IOException(e2.getMessage());
        }
    }

    @Override // c.t.m.g.q1
    public String a(String str) {
        try {
            String[] strArr = {null};
            c0.f(str, new a(this, strArr));
            if (strArr[0] != null) {
                return strArr[0];
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}
