package okhttp3.internal.connection;

import android.text.TextUtils;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Connection;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http1.Http1Codec;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Codec;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes11.dex */
public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    public long idleAtNanos = Long.MAX_VALUE;

    public RealConnection(ConnectionPool connectionPool, Route route) {
        this.connectionPool = connectionPool;
        this.route = route;
    }

    private void connectSocket(int i2, int i3, Call call, EventListener eventListener) throws IOException {
        Socket createSocket;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.HTTP) {
            createSocket = new Socket(proxy);
        } else {
            createSocket = address.socketFactory().createSocket();
        }
        this.rawSocket = createSocket;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i3);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i2);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e2) {
                if (NPE_THROW_WITH_NULL.equals(e2.getMessage())) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e3);
            throw connectException;
        }
    }

    private void connectTls(ConnectionSpecSelector connectionSpecSelector) throws IOException {
        Protocol protocol;
        Address address = this.route.address();
        SSLSocket sSLSocket = null;
        try {
            try {
                SSLSocket sSLSocket2 = (SSLSocket) address.sslSocketFactory().createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
                try {
                    ConnectionSpec configureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                    if (configureSecureSocket.supportsTlsExtensions()) {
                        Platform.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                    }
                    sSLSocket2.startHandshake();
                    SSLSession session = sSLSocket2.getSession();
                    Handshake handshake = Handshake.get(session);
                    if (!address.hostnameVerifier().verify(address.url().host(), session)) {
                        List<Certificate> peerCertificates = handshake.peerCertificates();
                        if (!peerCertificates.isEmpty()) {
                            X509Certificate x509Certificate = (X509Certificate) peerCertificates.get(0);
                            throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
                        }
                        throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                    }
                    address.certificatePinner().check(address.url().host(), handshake.peerCertificates());
                    String selectedProtocol = configureSecureSocket.supportsTlsExtensions() ? Platform.get().getSelectedProtocol(sSLSocket2) : null;
                    this.socket = sSLSocket2;
                    this.source = Okio.buffer(Okio.source(sSLSocket2));
                    this.sink = Okio.buffer(Okio.sink(this.socket));
                    this.handshake = handshake;
                    if (selectedProtocol != null) {
                        protocol = Protocol.get(selectedProtocol);
                    } else {
                        protocol = Protocol.HTTP_1_1;
                    }
                    this.protocol = protocol;
                    if (sSLSocket2 != null) {
                        Platform.get().afterHandshake(sSLSocket2);
                    }
                } catch (AssertionError e2) {
                    e = e2;
                    if (!Util.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    sSLSocket = sSLSocket2;
                    if (sSLSocket != null) {
                        Platform.get().afterHandshake(sSLSocket);
                    }
                    Util.closeQuietly((Socket) sSLSocket);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (AssertionError e3) {
            e = e3;
        }
    }

    private void connectTunnel(int i2, int i3, int i4, Call call, EventListener eventListener) throws IOException {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        for (int i5 = 0; i5 < 21; i5++) {
            connectSocket(i2, i3, call, eventListener);
            createTunnelRequest = createTunnel(i3, i4, createTunnelRequest, url);
            if (createTunnelRequest == null) {
                return;
            }
            Util.closeQuietly(this.rawSocket);
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
        }
    }

    private Request createTunnel(int i2, int i3, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            Http1Codec http1Codec = new Http1Codec(null, null, this.source, this.sink);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            this.source.timeout().timeout(i2, timeUnit);
            this.sink.timeout().timeout(i3, timeUnit);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            Response build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, timeUnit);
            newFixedLengthSource.close();
            int code = build.code();
            if (code == 200) {
                if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            } else if (code == 407) {
                Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                if (authenticate != null) {
                    if ("close".equalsIgnoreCase(build.header("Connection"))) {
                        return authenticate;
                    }
                    request = authenticate;
                } else {
                    throw new IOException("Failed to authenticate with proxy");
                }
            } else {
                throw new IOException("Unexpected response code for CONNECT: " + build.code());
            }
        }
    }

    private Request createTunnelRequest() throws IOException {
        Request build = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header(com.google.common.net.HttpHeaders.HOST, Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
        Request authenticate = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(build).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header(com.google.common.net.HttpHeaders.PROXY_AUTHENTICATE, "OkHttp-Preemptive").build());
        return authenticate != null ? authenticate : build;
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i2, Call call, EventListener eventListener) throws IOException {
        if (this.route.address().sslSocketFactory() == null) {
            List<Protocol> protocols = this.route.address().protocols();
            Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (protocols.contains(protocol)) {
                this.socket = this.rawSocket;
                this.protocol = protocol;
                startHttp2(i2);
                return;
            }
            this.socket = this.rawSocket;
            this.protocol = Protocol.HTTP_1_1;
            return;
        }
        eventListener.secureConnectStart(call);
        connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, this.handshake);
        if (this.protocol == Protocol.HTTP_2) {
            startHttp2(i2);
        }
    }

    private void startHttp2(int i2) throws IOException {
        this.socket.setSoTimeout(0);
        Http2Connection build = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).pingIntervalMillis(i2).build();
        this.http2Connection = build;
        build.start();
    }

    public static RealConnection testConnection(ConnectionPool connectionPool, Route route, Socket socket, long j2) {
        RealConnection realConnection = new RealConnection(connectionPool, route);
        realConnection.socket = socket;
        realConnection.idleAtNanos = j2;
        return realConnection;
    }

    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    /* JADX WARN: Removed duplicated region for block: B:199:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x00f4 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0142 A[EDGE_INSN: B:239:0x0142->B:223:0x0142 BREAK  A[LOOP:0: B:229:0x0088->B:241:?], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void connect(int i2, int i3, int i4, int i5, boolean z, Call call, EventListener eventListener) {
        if (this.protocol == null) {
            List<ConnectionSpec> connectionSpecs = this.route.address().connectionSpecs();
            ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(connectionSpecs);
            if (this.route.address().sslSocketFactory() == null) {
                if (connectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
                    String host = this.route.address().url().host();
                    if (!Platform.get().isCleartextTrafficPermitted(host)) {
                        throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + host + " not permitted by network security policy"));
                    }
                } else {
                    throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
                }
            } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
                throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
            }
            RouteException routeException = null;
            do {
                try {
                    try {
                        if (this.route.requiresTunnel()) {
                            connectTunnel(i2, i3, i4, call, eventListener);
                            if (this.rawSocket == null) {
                                if (!this.route.requiresTunnel() && this.rawSocket == null) {
                                    throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                                }
                                if (this.http2Connection == null) {
                                    synchronized (this.connectionPool) {
                                        this.allocationLimit = this.http2Connection.maxConcurrentStreams();
                                    }
                                    return;
                                }
                                return;
                            }
                        } else {
                            try {
                                connectSocket(i2, i3, call, eventListener);
                            } catch (IOException e2) {
                                e = e2;
                                Util.closeQuietly(this.socket);
                                Util.closeQuietly(this.rawSocket);
                                this.socket = null;
                                this.rawSocket = null;
                                this.source = null;
                                this.sink = null;
                                this.handshake = null;
                                this.protocol = null;
                                this.http2Connection = null;
                                eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
                                if (routeException != null) {
                                    routeException = new RouteException(e);
                                } else {
                                    routeException.addConnectException(e);
                                }
                                if (z) {
                                    break;
                                }
                                throw routeException;
                            }
                        }
                        establishProtocol(connectionSpecSelector, i5, call, eventListener);
                        eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
                        if (!this.route.requiresTunnel()) {
                        }
                        if (this.http2Connection == null) {
                        }
                    } catch (IOException e3) {
                        e = e3;
                        Util.closeQuietly(this.socket);
                        Util.closeQuietly(this.rawSocket);
                        this.socket = null;
                        this.rawSocket = null;
                        this.source = null;
                        this.sink = null;
                        this.handshake = null;
                        this.protocol = null;
                        this.http2Connection = null;
                        eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
                        if (routeException != null) {
                        }
                        if (z) {
                        }
                        throw routeException;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } while (connectionSpecSelector.connectionFailed(e));
            throw routeException;
        }
        throw new IllegalStateException("already connected");
    }

    @Override // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public boolean isEligible(Address address, @Nullable Route route) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            if (address.url().getResolverInetAddress() != null) {
                if (route().address().url().getResolverInetAddress() != null) {
                    return TextUtils.equals(address.url().getResolverInetAddress().getHostAddress(), route().address().url().getResolverInetAddress().getHostAddress());
                }
                return false;
            }
            return true;
        } else if (this.http2Connection != null && route != null && route.proxy().type() == Proxy.Type.DIRECT && this.route.proxy().type() == Proxy.Type.DIRECT && this.route.socketAddress().equals(route.socketAddress()) && route.address().hostnameVerifier() == OkHostnameVerifier.INSTANCE && supportsUrl(address.url())) {
            try {
                address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
                return true;
            } catch (SSLPeerUnverifiedException unused) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isHealthy(boolean z) {
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.http2Connection;
        if (http2Connection != null) {
            return http2Connection.isHealthy(System.nanoTime());
        }
        if (z) {
            try {
                int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted();
                } finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                return false;
            }
        }
        return true;
    }

    public boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation) throws SocketException {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this.source.timeout().timeout(chain.readTimeoutMillis(), timeUnit);
        this.sink.timeout().timeout(chain.writeTimeoutMillis(), timeUnit);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public RealWebSocket.Streams newWebSocketStreams(final StreamAllocation streamAllocation) {
        return new RealWebSocket.Streams(true, this.source, this.sink) { // from class: okhttp3.internal.connection.RealConnection.1
            {
                RealConnection.this = this;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                StreamAllocation streamAllocation2 = streamAllocation;
                streamAllocation2.streamFinished(true, streamAllocation2.codec(), -1L, null);
            }
        };
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onSettings(Http2Connection http2Connection) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection.maxConcurrentStreams();
        }
    }

    @Override // okhttp3.internal.http2.Http2Connection.Listener
    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    @Override // okhttp3.Connection
    public Protocol protocol() {
        return this.protocol;
    }

    @Override // okhttp3.Connection
    public Route route() {
        return this.route;
    }

    @Override // okhttp3.Connection
    public Socket socket() {
        return this.socket;
    }

    public boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (httpUrl.host().equals(this.route.address().url().host())) {
            return true;
        }
        return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(":");
        sb.append(this.route.address().url().port());
        sb.append(", proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake = this.handshake;
        sb.append(handshake != null ? handshake.cipherSuite() : "none");
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
}
