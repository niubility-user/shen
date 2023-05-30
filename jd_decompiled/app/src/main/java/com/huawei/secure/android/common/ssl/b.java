package com.huawei.secure.android.common.ssl;

import android.content.Context;
import com.huawei.secure.android.common.ssl.g.f;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.StrictHostnameVerifier;
import org.apache.http.conn.ssl.X509HostnameVerifier;

@Deprecated
/* loaded from: classes12.dex */
public class b extends SSLSocketFactory {
    @Deprecated

    /* renamed from: i  reason: collision with root package name */
    public static final X509HostnameVerifier f1533i;

    /* renamed from: j  reason: collision with root package name */
    private static final String f1534j;

    /* renamed from: k  reason: collision with root package name */
    private static volatile b f1535k;
    private SSLContext a = null;
    private SSLSocket b = null;

    /* renamed from: c  reason: collision with root package name */
    private Context f1536c;
    private String[] d;

    /* renamed from: e  reason: collision with root package name */
    private X509TrustManager f1537e;

    /* renamed from: f  reason: collision with root package name */
    private String[] f1538f;

    /* renamed from: g  reason: collision with root package name */
    private String[] f1539g;

    /* renamed from: h  reason: collision with root package name */
    private String[] f1540h;

    static {
        new BrowserCompatHostnameVerifier();
        f1533i = new StrictHostnameVerifier();
        f1534j = b.class.getSimpleName();
        f1535k = null;
    }

    private b(Context context) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, KeyManagementException {
        if (context == null) {
            f.d(f1534j, "SecureSSLSocketFactory: context is null");
            return;
        }
        c(context);
        d(a.f());
        e a = d.a(context);
        this.f1537e = a;
        this.a.init(null, new X509TrustManager[]{a}, null);
    }

    private void a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (com.huawei.secure.android.common.ssl.g.b.a(this.f1540h)) {
            z = false;
        } else {
            f.e(f1534j, "set protocols");
            a.e((SSLSocket) socket, this.f1540h);
            z = true;
        }
        if (com.huawei.secure.android.common.ssl.g.b.a(this.f1539g) && com.huawei.secure.android.common.ssl.g.b.a(this.f1538f)) {
            z2 = false;
        } else {
            f.e(f1534j, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            a.d(sSLSocket);
            if (!com.huawei.secure.android.common.ssl.g.b.a(this.f1539g)) {
                a.h(sSLSocket, this.f1539g);
            } else {
                a.b(sSLSocket, this.f1538f);
            }
        }
        if (!z) {
            f.e(f1534j, "set default protocols");
            a.d((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        f.e(f1534j, "set default cipher suites");
        a.c((SSLSocket) socket);
    }

    public static b b(Context context) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, IllegalAccessException, KeyManagementException, IllegalArgumentException {
        long currentTimeMillis = System.currentTimeMillis();
        com.huawei.secure.android.common.ssl.g.c.b(context);
        if (f1535k == null) {
            synchronized (b.class) {
                if (f1535k == null) {
                    f1535k = new b(context);
                }
            }
        }
        if (f1535k.f1536c == null && context != null) {
            f1535k.c(context);
        }
        f.b(f1534j, "getInstance: cost : " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        return f1535k;
    }

    public void c(Context context) {
        this.f1536c = context.getApplicationContext();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException {
        f.e(f1534j, "createSocket: host , port");
        Socket createSocket = this.a.getSocketFactory().createSocket(str, i2);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    public void d(SSLContext sSLContext) {
        this.a = sSLContext;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.d;
        return strArr != null ? strArr : new String[0];
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) throws IOException, UnknownHostException {
        return createSocket(str, i2);
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) throws IOException {
        return createSocket(inetAddress.getHostAddress(), i2);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        f.e(f1534j, "createSocket s host port autoClose");
        Socket createSocket = this.a.getSocketFactory().createSocket(socket, str, i2, z);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.d = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }
}
