package com.jd.security.jdguard.f;

import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jd.security.jdguard.core.Bridge;
import com.jd.security.jdguard.f.e;
import com.jdpay.net.http.HTTP;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes17.dex */
public class b {
    private b() {
    }

    public static b b() {
        return new b();
    }

    @NonNull
    private HttpURLConnection c(String str, boolean z, e.a aVar) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(aVar.a);
        httpURLConnection.setReadTimeout(aVar.b);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON);
        httpURLConnection.setRequestProperty("jdgver", Bridge.getJDGVN());
        httpURLConnection.setRequestProperty("appkey", Bridge.getAppKey());
        httpURLConnection.setRequestProperty("Content-type", "text/plain");
        httpURLConnection.setRequestProperty("Charset", aVar.f6937c);
        if (z) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    private String d(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        httpURLConnection.getContentEncoding();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IllegalStateException unused) {
            inputStream = null;
        }
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr, 0, 1024);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        return new String(byteArrayOutputStream.toByteArray());
                    }
                }
            } finally {
                a.b(inputStream);
            }
        } else {
            throw new IOException("HttpURLConnection.getInputStream() returned null");
        }
    }

    public String a(String str, byte[] bArr, e.a aVar) throws Throwable {
        DataOutputStream dataOutputStream;
        HttpURLConnection c2 = c(str, true, aVar);
        DataOutputStream dataOutputStream2 = null;
        try {
            dataOutputStream = new DataOutputStream(c2.getOutputStream());
        } catch (Throwable th) {
            th = th;
        }
        try {
            dataOutputStream.write(bArr);
            dataOutputStream.flush();
            int responseCode = c2.getResponseCode();
            aVar.f6938e = responseCode;
            if (responseCode == 200) {
                String d = d(c2);
                a.b(dataOutputStream);
                c2.disconnect();
                return d;
            }
            throw new Exception(aVar.f6938e + "-" + str);
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream2 = dataOutputStream;
            try {
                throw th;
            } catch (Throwable th3) {
                a.b(dataOutputStream2);
                c2.disconnect();
                throw th3;
            }
        }
    }
}
