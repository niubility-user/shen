package performance.jd.jdreportperformance.c;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes.dex */
public class b {
    private String a;
    private int b = 10000;

    /* renamed from: c  reason: collision with root package name */
    private int f20434c = 20000;
    private String d = "POST";

    /* renamed from: e  reason: collision with root package name */
    private boolean f20435e = false;

    /* renamed from: f  reason: collision with root package name */
    private boolean f20436f = true;

    /* renamed from: g  reason: collision with root package name */
    private boolean f20437g = true;

    /* renamed from: h  reason: collision with root package name */
    private boolean f20438h = true;

    /* renamed from: i  reason: collision with root package name */
    private byte[] f20439i = null;

    /* loaded from: classes.dex */
    public static class a {
        public int a = 0;
        public int b = 0;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, List<String>> f20440c;
        private byte[] d;

        public String a() {
            byte[] bArr = this.d;
            if (bArr == null || bArr.length == 0) {
                return "";
            }
            Map<String, List<String>> map = this.f20440c;
            if (map != null) {
                try {
                    List<String> list = map.get("content-encoding");
                    if (list != null && list.contains("gzip")) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.d);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int read = gZIPInputStream.read(bArr2, 0, 1024);
                            if (read == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        gZIPInputStream.close();
                        this.d = byteArrayOutputStream.toByteArray();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }
            try {
                return new String(this.d, "utf-8");
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(byte[] bArr) {
        this.f20439i = bArr;
    }

    public a a() {
        HttpsURLConnection httpsURLConnection;
        byte[] bArr;
        a aVar = new a();
        if (TextUtils.isEmpty(this.a)) {
            aVar.a = 1;
            return aVar;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                httpsURLConnection = (HttpsURLConnection) new URL(this.a).openConnection();
                try {
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                httpsURLConnection = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (httpsURLConnection == null) {
            aVar.a = 2;
            if (httpsURLConnection != null) {
                try {
                    httpsURLConnection.disconnect();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            return aVar;
        }
        httpsURLConnection.setUseCaches(this.f20435e);
        httpsURLConnection.setDoOutput(this.f20437g);
        httpsURLConnection.setDoInput(this.f20436f);
        httpsURLConnection.setRequestMethod(this.d);
        httpsURLConnection.setConnectTimeout(this.b);
        httpsURLConnection.setReadTimeout(this.f20434c);
        httpsURLConnection.setInstanceFollowRedirects(this.f20438h);
        httpsURLConnection.setHostnameVerifier(c.a);
        httpsURLConnection.setRequestProperty(HttpHeaders.HOST, "perf.m.jd.com");
        httpsURLConnection.connect();
        if ("POST".equals(this.d) && (bArr = this.f20439i) != null && bArr.length != 0) {
            DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
            dataOutputStream.write(this.f20439i);
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        aVar.f20440c = httpsURLConnection.getHeaderFields();
        int responseCode = httpsURLConnection.getResponseCode();
        if (responseCode != 200) {
            performance.jd.jdreportperformance.b.b.b.b("HttpRequest", "response code exception: " + responseCode);
        }
        aVar.b = responseCode;
        if (this.f20436f) {
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpsURLConnection.getInputStream());
            try {
                byte[] bArr2 = new byte[1024];
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                while (true) {
                    int read = bufferedInputStream2.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                aVar.d = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                bufferedInputStream = bufferedInputStream2;
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = bufferedInputStream2;
                try {
                    aVar.a = 3;
                    th.printStackTrace();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    return aVar;
                } catch (Throwable th4) {
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    if (httpsURLConnection != null) {
                        try {
                            httpsURLConnection.disconnect();
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                    throw th4;
                }
            }
        }
        if (bufferedInputStream != null) {
            try {
                bufferedInputStream.close();
            } catch (Exception e8) {
                e8.printStackTrace();
            }
        }
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
        return aVar;
    }
}
