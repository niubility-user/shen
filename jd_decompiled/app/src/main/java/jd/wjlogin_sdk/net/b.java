package jd.wjlogin_sdk.net;

import android.util.Pair;
import com.google.common.net.HttpHeaders;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import jd.wjlogin_sdk.c.e;
import jd.wjlogin_sdk.common.f;
import jd.wjlogin_sdk.net.AbsHttpService;
import jd.wjlogin_sdk.net.a;
import jd.wjlogin_sdk.util.p;
import jd.wjlogin_sdk.util.r;

/* loaded from: classes11.dex */
public class b extends AbsHttpService {

    /* renamed from: m */
    private static final String f19846m = "WJLogin.HttpConnect";

    /* loaded from: classes11.dex */
    public class a implements HostnameVerifier {
        final /* synthetic */ a.c a;

        a(a.c cVar) {
            b.this = r1;
            this.a = cVar;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            if (str != null && str.equals(this.a.d)) {
                str = this.a.f19844c;
            }
            return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
        }
    }

    /* renamed from: jd.wjlogin_sdk.net.b$b */
    /* loaded from: classes.dex */
    public static class C0849b extends AbsHttpService.a {
        @Override // jd.wjlogin_sdk.net.AbsHttpService.a
        /* renamed from: b */
        public b a() {
            return new b(this.a, this.f19837c, this.d, this.f19838e, this.b, this.f19839f, this.f19840g, this.f19841h, this.f19842i);
        }
    }

    b(String str, int i2, Map<String, String> map, byte[] bArr, boolean z, int i3, int i4, int i5, boolean z2) {
        super(str, i2, map, bArr, z, i3, i4, i5, z2);
    }

    @Override // jd.wjlogin_sdk.net.c
    public Pair<Integer, byte[]> a(e eVar) throws Throwable {
        byte[] bArr;
        if (r.d(jd.wjlogin_sdk.common.b.a())) {
            if (eVar != null) {
                bArr = eVar.a(this.f19832g).getBytes();
            } else {
                bArr = this.f19832g;
            }
            this.f19831f = this.f19830e;
            return a(bArr, jd.wjlogin_sdk.net.a.c() ? jd.wjlogin_sdk.net.a.c(this.f19830e) : null);
        }
        throw new NetworkException("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e");
    }

    @Override // jd.wjlogin_sdk.net.c
    public String a() {
        return "HttpURLConnection";
    }

    @Override // jd.wjlogin_sdk.net.c
    public Pair<Integer, byte[]> b() throws Throwable {
        return a((e) null);
    }

    private void b(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        int i2 = this.a;
        if (i2 == 0) {
            httpURLConnection.setRequestMethod("POST");
            a(httpURLConnection, bArr);
        } else if (i2 != 1) {
            httpURLConnection.setRequestMethod("POST");
            a(httpURLConnection, bArr);
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
    }

    private Map<String, String> a(URI uri, byte[] bArr) {
        try {
            if (this.f19836k && jd.wjlogin_sdk.config.a.c().i()) {
                p.a(f19846m, "addGuardSign begin");
                String str = AbsHttpService.f19828l.get(HttpHeaders.CONTENT_TYPE);
                boolean z = this.a == 0;
                String str2 = z ? IMantoServerRequester.POST : IMantoServerRequester.GET;
                if (f.a().getWJdGuardProxy() != null) {
                    p.a(f19846m, "addGuardSign  \u5f00\u59cb\u52a0\u7b7e time=" + System.currentTimeMillis());
                    Map<String, String> jDGuardSign = f.a().getWJdGuardProxy().getJDGuardSign(uri, bArr, str, str2, z);
                    p.a(f19846m, "addGuardSign  \u7ed3\u675f\u52a0\u7b7e time=" + System.currentTimeMillis());
                    return jDGuardSign;
                }
                return null;
            }
            return null;
        } catch (Exception e2) {
            p.a(f19846m, "addGuardSign Exception");
            e2.printStackTrace();
            return null;
        }
    }

    private HttpURLConnection b(URL url) throws IOException {
        HttpURLConnection a2 = a(url);
        int i2 = this.f19829c;
        a2.setConnectTimeout(i2);
        a2.setReadTimeout(i2);
        a2.setDoInput(true);
        a2.setDoOutput(true);
        return a2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(2:103|104)(2:97|98)|(1:100)|(2:102|61)|57|58|60|61) */
    /* JADX WARN: Removed duplicated region for block: B:212:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0225 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<java.lang.Integer, byte[]> a(byte[] r13, jd.wjlogin_sdk.net.a.c r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: jd.wjlogin_sdk.net.b.a(byte[], jd.wjlogin_sdk.net.a$c):android.util.Pair");
    }

    private void a(HttpURLConnection httpURLConnection, byte[] bArr) throws IOException {
        if (httpURLConnection != null) {
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            if (bArr != null) {
                dataOutputStream.write(bArr);
            }
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    private HttpURLConnection a(URL url) throws IOException {
        if ("https".equals(url.getProtocol().toLowerCase())) {
            return (HttpsURLConnection) url.openConnection();
        }
        return (HttpURLConnection) url.openConnection();
    }

    byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
