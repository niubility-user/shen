package com.jd.phc.i.e;

import androidx.annotation.NonNull;
import com.google.common.net.HttpHeaders;
import com.jd.phc.i.d.c;
import com.jd.phc.i.d.d;
import com.jd.phc.i.d.e;
import com.jd.phc.i.e.b;
import com.jdpay.net.http.HTTP;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes17.dex */
public class a {
    private static final boolean a = com.jd.phc.a.a;

    private a() {
    }

    private void a(HttpURLConnection httpURLConnection, HashMap<String, String> hashMap) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public static a c() {
        return new a();
    }

    @NonNull
    private HttpURLConnection d(String str, boolean z, b.a aVar) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(aVar.a);
        httpURLConnection.setReadTimeout(aVar.b);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip,deflate");
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, HTTP.CONTENT_TYPE_JSON);
        httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT, HTTP.CONTENT_TYPE_JSON);
        httpURLConnection.setRequestProperty("Charset", aVar.f6859c);
        HashMap<String, String> hashMap = aVar.f6860e;
        if (hashMap != null) {
            a(httpURLConnection, hashMap);
        }
        if (z) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    private String e(HttpURLConnection httpURLConnection) throws IOException {
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (a) {
            com.jd.phc.i.b.a("HttpClient", "response code: " + httpURLConnection.getResponseCode() + ", encoding: " + contentEncoding + ", method: " + httpURLConnection.getRequestMethod());
        }
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IllegalStateException e2) {
            if (a) {
                e2.printStackTrace();
            }
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
                com.jd.phc.i.a.a(inputStream);
            }
        } else {
            throw new IOException("HttpURLConnection.getInputStream() returned null");
        }
    }

    public String b(String str, byte[] bArr, b.a aVar) throws IOException {
        if (aVar.d) {
            if (aVar.f6860e == null) {
                aVar.f6860e = new HashMap<>();
            }
            aVar.f6860e.put("Content-Encoding", "gzip");
        }
        HttpURLConnection d = d(str, true, aVar);
        DataOutputStream dataOutputStream = null;
        try {
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(d.getOutputStream());
                try {
                    if (aVar.d) {
                        dataOutputStream2.write(com.jd.phc.i.a.b(bArr));
                    } else {
                        dataOutputStream2.write(bArr);
                    }
                    dataOutputStream2.flush();
                    int responseCode = d.getResponseCode();
                    aVar.f6862g = responseCode;
                    if (responseCode == 200) {
                        String e2 = e(d);
                        com.jd.phc.i.a.a(dataOutputStream2);
                        d.disconnect();
                        return e2;
                    }
                    throw new e(aVar.f6862g, "response error");
                } catch (c e3) {
                    e = e3;
                    if (a) {
                        e.printStackTrace();
                    }
                    throw e;
                } catch (SocketTimeoutException e4) {
                    e = e4;
                    if (a) {
                        e.printStackTrace();
                    }
                    throw new c(d.TIMEOUT_ERROR);
                } catch (Exception e5) {
                    e = e5;
                    if (a) {
                        e.printStackTrace();
                    }
                    throw new c(d.UNKNOWN_ERROR.setDesc(e.getMessage()));
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    com.jd.phc.i.a.a(dataOutputStream);
                    d.disconnect();
                    throw th;
                }
            } catch (c e6) {
                e = e6;
            } catch (SocketTimeoutException e7) {
                e = e7;
            } catch (Exception e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
