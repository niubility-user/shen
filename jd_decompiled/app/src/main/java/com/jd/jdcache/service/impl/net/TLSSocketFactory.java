package com.jd.jdcache.service.impl.net;

import android.os.Build;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.jetbrains.annotations.NotNull;
import tv.danmaku.ijk.media.player.IMediaPlayer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000  2\u00020\u0001:\u0001 B\t\b\u0016\u00a2\u0006\u0004\b\u001c\u0010\u001dB\u0011\b\u0016\u0012\u0006\u0010\u001e\u001a\u00020\u0001\u00a2\u0006\u0004\b\u001c\u0010\u001fJ\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0005J/\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0010J/\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0014J\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0015J/\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0018J\u000f\u0010\u000e\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006!"}, d2 = {"Lcom/jd/jdcache/service/impl/net/TLSSocketFactory;", "Ljavax/net/ssl/SSLSocketFactory;", "", "", "getDefaultCipherSuites", "()[Ljava/lang/String;", "getSupportedCipherSuites", "Ljava/net/Socket;", "s", "host", "", IMediaPlayer.OnNativeInvokeListener.ARG_PORT, "", "autoClose", "createSocket", "(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;", "(Ljava/lang/String;I)Ljava/net/Socket;", "Ljava/net/InetAddress;", "localHost", "localPort", "(Ljava/lang/String;ILjava/net/InetAddress;I)Ljava/net/Socket;", "(Ljava/net/InetAddress;I)Ljava/net/Socket;", ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, "localAddress", "(Ljava/net/InetAddress;ILjava/net/InetAddress;I)Ljava/net/Socket;", "()Ljava/net/Socket;", "delegate", "Ljavax/net/ssl/SSLSocketFactory;", "<init>", "()V", "factory", "(Ljavax/net/ssl/SSLSocketFactory;)V", "Companion", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class TLSSocketFactory extends SSLSocketFactory {

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String[] PROTOCOL_ARRAY;
    private SSLSocketFactory delegate;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\n\u00a8\u0006\r"}, d2 = {"Lcom/jd/jdcache/service/impl/net/TLSSocketFactory$Companion;", "", "Ljava/net/Socket;", "socket", "", "setSupportProtocolAndCipherSuites", "(Ljava/net/Socket;)V", "", "", "PROTOCOL_ARRAY", "[Ljava/lang/String;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setSupportProtocolAndCipherSuites(Socket socket) {
            if (socket instanceof SSLSocket) {
                ((SSLSocket) socket).setEnabledProtocols(TLSSocketFactory.PROTOCOL_ARRAY);
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        PROTOCOL_ARRAY = Build.VERSION.SDK_INT >= 26 ? new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"} : new String[]{"SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"};
    }

    public TLSSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sslContext.init(null, null, new SecureRandom());
            Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            Intrinsics.checkExpressionValueIsNotNull(socketFactory, "sslContext.socketFactory");
            this.delegate = socketFactory;
        } catch (GeneralSecurityException unused) {
            throw new AssertionError();
        }
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public Socket createSocket(@NotNull Socket s, @NotNull String host, int port, boolean autoClose) throws IOException {
        Socket ssl = this.delegate.createSocket(s, host, port, autoClose);
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public String[] getDefaultCipherSuites() {
        String[] defaultCipherSuites = this.delegate.getDefaultCipherSuites();
        Intrinsics.checkExpressionValueIsNotNull(defaultCipherSuites, "delegate.defaultCipherSuites");
        return defaultCipherSuites;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    @NotNull
    public String[] getSupportedCipherSuites() {
        String[] supportedCipherSuites = this.delegate.getSupportedCipherSuites();
        Intrinsics.checkExpressionValueIsNotNull(supportedCipherSuites, "delegate.supportedCipherSuites");
        return supportedCipherSuites;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull String host, int port) throws IOException {
        Socket ssl = this.delegate.createSocket(host, port);
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull String host, int port, @NotNull InetAddress localHost, int localPort) throws IOException {
        Socket ssl = this.delegate.createSocket(host, port, localHost, localPort);
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }

    public TLSSocketFactory(@NotNull SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull InetAddress host, int port) throws IOException {
        Socket ssl = this.delegate.createSocket(host, port);
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket(@NotNull InetAddress address, int port, @NotNull InetAddress localAddress, int localPort) throws IOException {
        Socket ssl = this.delegate.createSocket(address, port, localAddress, localPort);
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }

    @Override // javax.net.SocketFactory
    @NotNull
    public Socket createSocket() throws IOException {
        Socket ssl = this.delegate.createSocket();
        Companion companion = INSTANCE;
        Intrinsics.checkExpressionValueIsNotNull(ssl, "ssl");
        companion.setSupportProtocolAndCipherSuites(ssl);
        return ssl;
    }
}
