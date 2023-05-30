package com.cmic.sso.sdk.c;

import android.net.Network;
import android.net.SSLCertificateSocketFactory;
import android.os.Build;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* loaded from: classes.dex */
public class d extends a {
    private static final String[] b = {"TLSv1.2"};
    private final HttpsURLConnection d;

    /* renamed from: e  reason: collision with root package name */
    private final Network f1022e;

    /* renamed from: f  reason: collision with root package name */
    private final com.cmic.sso.sdk.a f1023f;

    /* renamed from: c  reason: collision with root package name */
    private final String f1021c = d.class.getSimpleName();
    HostnameVerifier a = HttpsURLConnection.getDefaultHostnameVerifier();

    public d(HttpsURLConnection httpsURLConnection, Network network, com.cmic.sso.sdk.a aVar) {
        this.d = httpsURLConnection;
        this.f1022e = network;
        this.f1023f = aVar;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException, UnknownHostException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) throws IOException, UnknownHostException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) throws IOException {
        return null;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        String requestProperty = this.d.getRequestProperty(HttpHeaders.HOST);
        if (requestProperty != null) {
            str = requestProperty;
        }
        com.cmic.sso.sdk.e.c.b(this.f1021c, "customized createSocket. host: " + str);
        com.cmic.sso.sdk.e.c.b(this.f1021c, "plainSocket localAddress: " + socket.getLocalAddress().getHostAddress());
        if (z) {
            com.cmic.sso.sdk.e.c.b(this.f1021c, "plainSocket close");
            socket.close();
        }
        SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
        SSLSocket sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket();
        Network network = this.f1022e;
        if (network != null && Build.VERSION.SDK_INT >= 21) {
            network.bindSocket(sSLSocket);
        }
        sSLSocket.connect(socket.getRemoteSocketAddress());
        this.f1023f.a("socketip", sSLSocket.getLocalAddress().getHostAddress());
        sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 20) {
            com.cmic.sso.sdk.e.c.b(this.f1021c, "5.0\u4ee5\u4e0b\u542f\u52a8tls 1.2");
            sSLSocket.setEnabledProtocols(b);
            sSLSocket.setEnabledCipherSuites(new String[]{"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"});
        }
        if (i3 >= 17) {
            com.cmic.sso.sdk.e.c.b(this.f1021c, "Setting SNI hostname");
            sSLCertificateSocketFactory.setHostname(sSLSocket, str);
        } else {
            com.cmic.sso.sdk.e.c.b(this.f1021c, "No documented SNI support on Android <4.2, trying with reflection");
            try {
                sSLSocket.getClass().getMethod("setHostname", String.class).invoke(sSLSocket, str);
            } catch (Exception e2) {
                e2.printStackTrace();
                com.cmic.sso.sdk.e.c.a(this.f1021c, "SNI not useable");
            }
        }
        SSLSession session = sSLSocket.getSession();
        if (this.a.verify(str, session)) {
            com.cmic.sso.sdk.e.c.b(this.f1021c, "Established " + session.getProtocol() + " connection with " + session.getPeerHost() + " using " + session.getCipherSuite());
            return sSLSocket;
        }
        throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return new String[0];
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return new String[0];
    }
}
