package com.jd.libs.xconsole.d;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.google.common.net.HttpHeaders;
import com.jd.libs.xconsole.c.b;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/* loaded from: classes16.dex */
public class a extends WebSocketListener {

    /* renamed from: f  reason: collision with root package name */
    private static volatile a f6189f;
    private OkHttpClient a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f6190c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private WebSocket f6191e;

    private a() {
        if (f6189f != null) {
            throw new IllegalStateException("can not create instance \uff01");
        }
    }

    public static a b() {
        if (f6189f == null) {
            synchronized (a.class) {
                if (f6189f == null) {
                    f6189f = new a();
                }
            }
        }
        return f6189f;
    }

    public void a(String str) {
        this.d = str;
        OkHttpClient build = new OkHttpClient.Builder().retryOnConnectionFailure(true).pingInterval(15L, TimeUnit.SECONDS).build();
        this.a = build;
        build.dispatcher().cancelAll();
        this.a.newWebSocket(new Request.Builder().url(String.format("ws://bt-cms.hybrid.jd.com/webSocket/phone/%s", str)).header("Connection", HttpHeaders.UPGRADE).build(), this);
    }

    @SuppressLint({"DefaultLocale"})
    public String c() {
        long j2 = this.b + 1;
        this.b = j2;
        return String.format("m_%d", Long.valueOf(j2));
    }

    public boolean d(String str) {
        if (this.f6191e != null) {
            if (str.length() >= 100000) {
                return this.f6191e.send(str.substring(0, 100000));
            }
            return this.f6191e.send(str);
        }
        return false;
    }

    @Override // okhttp3.WebSocketListener
    public void onClosed(WebSocket webSocket, int i2, String str) {
        String str2 = "\u8fde\u63a5\u65ad\u5f00" + i2 + " - " + str;
        super.onClosed(webSocket, i2, str);
        this.f6191e = null;
        if (SystemClock.elapsedRealtime() - this.f6190c >= 60000) {
            a(this.d);
        }
    }

    @Override // okhttp3.WebSocketListener
    public void onFailure(WebSocket webSocket, Throwable th, Response response) {
        th.printStackTrace();
        super.onFailure(webSocket, th, response);
        webSocket.cancel();
        this.f6191e = null;
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, String str) {
        String str2 = "\u63a5\u53d7\u6d88\u606f" + str;
        b.a(webSocket, str);
    }

    @Override // okhttp3.WebSocketListener
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.f6191e = webSocket;
        this.f6190c = SystemClock.elapsedRealtime();
    }

    @Override // okhttp3.WebSocketListener
    public void onMessage(WebSocket webSocket, ByteString byteString) {
        b.b(webSocket, byteString);
    }
}
