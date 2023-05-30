package com.jingdong.sdk.talos.inner.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPInputStream;

/* loaded from: classes10.dex */
public abstract class a {

    /* renamed from: c  reason: collision with root package name */
    protected URL f15481c;
    protected String d;

    /* renamed from: i  reason: collision with root package name */
    String f15486i;

    /* renamed from: j  reason: collision with root package name */
    protected byte[] f15487j;

    /* renamed from: k  reason: collision with root package name */
    public e f15488k;
    protected String a = "POST";
    protected String b = "utf-8";

    /* renamed from: e  reason: collision with root package name */
    protected int f15482e = 15000;

    /* renamed from: f  reason: collision with root package name */
    protected int f15483f = 15000;

    /* renamed from: g  reason: collision with root package name */
    protected HashMap<String, String> f15484g = new HashMap<>();

    /* renamed from: h  reason: collision with root package name */
    protected HashMap<String, String> f15485h = new HashMap<>();

    private void e(HttpURLConnection httpURLConnection, ByteArrayOutputStream byteArrayOutputStream) {
        String str;
        httpURLConnection.getLastModified();
        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
        if (headerFields != null) {
            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                String str2 = "";
                int i2 = 0;
                for (String str3 : entry.getValue()) {
                    if (i2 > 0) {
                        str2 = str2 + "<--->";
                    }
                    str2 = str2 + str3;
                    i2++;
                }
                this.f15485h.put(String.valueOf(entry.getKey()).toLowerCase(Locale.getDefault()), str2);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray == null || byteArray.length == 0) {
            this.f15487j = null;
            return;
        }
        HashMap<String, String> hashMap = this.f15485h;
        if (hashMap != null && (str = hashMap.get("content-encoding")) != null && str.contains("gzip")) {
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
        this.f15487j = byteArray;
    }

    public final String a() {
        return this.d;
    }

    public abstract void b(OutputStream outputStream);

    public final void c(String str) {
        this.d = str;
    }

    public final void d(String str, String str2) {
        this.f15484g.put(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:251:0x024c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0253 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:265:0x025a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            Method dump skipped, instructions count: 613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.talos.inner.a.a.f():void");
    }
}
