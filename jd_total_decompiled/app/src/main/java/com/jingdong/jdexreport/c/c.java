package com.jingdong.jdexreport.c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* loaded from: classes12.dex */
public abstract class c {

    /* renamed from: j  reason: collision with root package name */
    protected static final String f12575j = "com.jingdong.jdexreport.c.c";
    protected URL a;
    protected int b;

    /* renamed from: c  reason: collision with root package name */
    protected int f12576c;
    protected String d;

    /* renamed from: e  reason: collision with root package name */
    protected String f12577e;

    /* renamed from: h  reason: collision with root package name */
    protected byte[] f12580h;

    /* renamed from: i  reason: collision with root package name */
    protected boolean f12581i = true;

    /* renamed from: f  reason: collision with root package name */
    protected HashMap<String, String> f12578f = new HashMap<>();

    /* renamed from: g  reason: collision with root package name */
    protected HashMap<String, String> f12579g = new HashMap<>();

    public c(int i2, int i3, int i4, String str, String str2, boolean z) {
        a(i2, i3, i4, str, str2, z);
    }

    public void a(String str) {
        this.f12577e = str;
    }

    public HashMap<String, String> b() {
        return this.f12578f;
    }

    public byte[] c() {
        return this.f12580h;
    }

    public HashMap<String, String> d() {
        return this.f12579g;
    }

    public String e() {
        return this.f12577e;
    }

    public void a(int i2, int i3, int i4, String str, String str2, boolean z) {
        this.f12576c = i3;
        this.f12581i = z;
    }

    public void b(String str) {
        this.d = str;
    }

    public HttpURLConnection a() throws Exception {
        if (!"".equals(this.d)) {
            URL url = new URL(this.d);
            this.a = url;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection != null) {
                return httpURLConnection;
            }
            throw new IOException(f12575j + "|getConnect|mConn is empty");
        }
        throw new IOException(f12575j + "|getConnect|mStrUrl is empty");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(HttpURLConnection httpURLConnection, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        String str;
        a(httpURLConnection);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray != null && byteArray.length != 0) {
            HashMap<String, String> d = d();
            if (d != null && (str = d.get("content-encoding")) != null && str.contains("gzip")) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(1024);
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = gZIPInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream2.write(bArr, 0, read);
                }
                gZIPInputStream.close();
                byteArray = byteArrayOutputStream2.toByteArray();
            }
            this.f12580h = byteArray;
            return;
        }
        this.f12580h = null;
    }

    protected void a(HttpURLConnection httpURLConnection) {
        httpURLConnection.getLastModified();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null) {
            return;
        }
        for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
            int i2 = 0;
            String str = "";
            for (String str2 : entry.getValue()) {
                if (i2 > 0) {
                    str = str + "<--->";
                }
                str = str + str2;
                i2++;
            }
            this.f12579g.put(String.valueOf(entry.getKey()).toLowerCase(Locale.getDefault()), str);
        }
    }
}
