package com.huawei.secure.android.common.ssl;

import com.huawei.secure.android.common.ssl.g.f;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes12.dex */
public class c extends SSLSocketFactory {

    /* renamed from: g  reason: collision with root package name */
    private static final String f1541g = c.class.getSimpleName();
    protected SSLContext a;
    protected SSLSocket b = null;

    /* renamed from: c  reason: collision with root package name */
    protected String[] f1542c;
    protected String[] d;

    /* renamed from: e  reason: collision with root package name */
    protected String[] f1543e;

    /* renamed from: f  reason: collision with root package name */
    protected String[] f1544f;

    public c(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException, IllegalArgumentException {
        this.a = null;
        this.a = a.f();
        b(x509TrustManager);
        this.a.init(null, new X509TrustManager[]{x509TrustManager}, null);
    }

    private void a(Socket socket) {
        boolean z;
        boolean z2 = true;
        if (com.huawei.secure.android.common.ssl.g.b.a(this.f1544f)) {
            z = false;
        } else {
            f.e(f1541g, "set protocols");
            a.e((SSLSocket) socket, this.f1544f);
            z = true;
        }
        if (com.huawei.secure.android.common.ssl.g.b.a(this.f1543e) && com.huawei.secure.android.common.ssl.g.b.a(this.d)) {
            z2 = false;
        } else {
            f.e(f1541g, "set white cipher or black cipher");
            SSLSocket sSLSocket = (SSLSocket) socket;
            a.d(sSLSocket);
            if (!com.huawei.secure.android.common.ssl.g.b.a(this.f1543e)) {
                a.h(sSLSocket, this.f1543e);
            } else {
                a.b(sSLSocket, this.d);
            }
        }
        if (!z) {
            f.e(f1541g, "set default protocols");
            a.d((SSLSocket) socket);
        }
        if (z2) {
            return;
        }
        f.e(f1541g, "set default cipher suites");
        a.c((SSLSocket) socket);
    }

    public void b(X509TrustManager x509TrustManager) {
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException {
        f.e(f1541g, "createSocket: host , port");
        Socket createSocket = this.a.getSocketFactory().createSocket(str, i2);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.f1542c = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        String[] strArr = this.f1542c;
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
        f.e(f1541g, "createSocket s host port autoClose");
        Socket createSocket = this.a.getSocketFactory().createSocket(socket, str, i2, z);
        if (createSocket instanceof SSLSocket) {
            a(createSocket);
            SSLSocket sSLSocket = (SSLSocket) createSocket;
            this.b = sSLSocket;
            this.f1542c = (String[]) sSLSocket.getEnabledCipherSuites().clone();
        }
        return createSocket;
    }
}
