package com.cmic.sso.sdk.c;

import android.os.Build;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes.dex */
public class c extends a {
    private static final String[] a = {"TLSv1.2"};
    private final com.cmic.sso.sdk.a b;

    public c(SSLSocketFactory sSLSocketFactory, com.cmic.sso.sdk.a aVar) {
        this.delegate = sSLSocketFactory;
        this.b = aVar;
    }

    private Socket a(Socket socket) {
        if ((socket instanceof SSLSocket) && Build.VERSION.SDK_INT < 20) {
            com.cmic.sso.sdk.e.c.b("Tls12SocketFactory", "5.0\u4ee5\u4e0b\u542f\u52a8tls 1.2");
            SSLSocket sSLSocket = (SSLSocket) socket;
            for (String str : sSLSocket.getEnabledProtocols()) {
                com.cmic.sso.sdk.e.c.a("enableProtocol", str);
            }
            sSLSocket.setEnabledProtocols(a);
            sSLSocket.setEnabledCipherSuites(new String[]{"TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA"});
        }
        this.b.a("socketip", socket.getLocalAddress().getHostAddress());
        return socket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return a(this.delegate.createSocket());
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    public String toString() {
        return "Tls12SocketFactory";
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        return a(this.delegate.createSocket(socket, str, i2, z));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2) throws IOException {
        return a(this.delegate.createSocket(str, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) throws IOException {
        return a(this.delegate.createSocket(str, i2, inetAddress, i3));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2) throws IOException {
        return a(this.delegate.createSocket(inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) throws IOException {
        return a(this.delegate.createSocket(inetAddress, i2, inetAddress2, i3));
    }
}
