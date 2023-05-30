package com.jingdong.manto.s;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.r;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.a;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class d {

    /* renamed from: g  reason: collision with root package name */
    private static volatile d f14178g;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f14179c;
    private final HashMap<WebSocket, Boolean> a = new HashMap<>(1);
    boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    MantoHandler f14180e = new c(Looper.getMainLooper());

    /* renamed from: f  reason: collision with root package name */
    a.InterfaceC0688a f14181f = new C0672d();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends WebSocketListener {
        a() {
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i2, String str) {
            synchronized (d.this.a) {
                d.this.a.remove(webSocket);
            }
            com.jingdong.manto.utils.a.a().b(d.this.f14181f);
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i2, String str) {
            super.onClosing(webSocket, i2, str);
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            synchronized (d.this.a) {
                d.this.a.remove(webSocket);
            }
            com.jingdong.manto.utils.a.a().b(d.this.f14181f);
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (TextUtils.equals("0", jSONObject.optString("code"))) {
                    String optString = jSONObject.optString("result");
                    if (TextUtils.equals(optString, "app_pong")) {
                        d.this.f14180e.a(5);
                        d.this.f14180e.a(3, 3000L);
                    } else if (TextUtils.equals(optString, "close")) {
                        d.this.f14180e.b(5);
                    } else {
                        JSONObject jSONObject2 = new JSONObject(optString);
                        String optString2 = jSONObject2.optString("clientKey");
                        String optString3 = jSONObject2.optString("sessionToken");
                        String optString4 = jSONObject2.optString(PairKey.APP_KEY);
                        if (TextUtils.equals(optString3, d.this.f14179c) && TextUtils.equals(optString4, d.this.b)) {
                            Message message = new Message();
                            message.what = 4;
                            message.obj = optString2;
                            d.this.f14180e.b(message);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            synchronized (d.this.a) {
                d.this.a.put(webSocket, Boolean.TRUE);
            }
            d.this.d = false;
            com.jingdong.manto.utils.a.a().a(d.this.f14181f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b extends IMantoHttpListener {
        b() {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onError(JSONObject jSONObject, Throwable th) {
        }

        @Override // com.jingdong.manto.network.common.IMantoHttpListener
        public void onSuccess(JSONObject jSONObject) {
            JSONObject optJSONObject;
            if (jSONObject == null || !TextUtils.equals("0", jSONObject.optString("code")) || (optJSONObject = jSONObject.optJSONObject("result")) == null) {
                return;
            }
            String optString = optJSONObject.optString("sessionToken");
            Message message = new Message();
            message.what = 2;
            message.obj = optString;
            d.this.f14180e.b(message);
        }
    }

    /* loaded from: classes16.dex */
    class c extends MantoHandler {
        c(Looper looper) {
            super(looper);
        }

        @Override // com.jingdong.manto.sdk.thread.MantoHandler, com.jingdong.manto.sdk.thread.d.a
        public final void a(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                d.this.d();
            } else if (i2 == 2) {
                d.this.f14179c = (String) message.obj;
                d.this.b();
            } else if (i2 == 3) {
                d.this.e();
            } else if (i2 == 4) {
                d.this.b((String) message.obj);
            } else if (i2 != 5) {
            } else {
                d.this.a();
            }
        }
    }

    /* renamed from: com.jingdong.manto.s.d$d  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0672d implements a.InterfaceC0688a {
        C0672d() {
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void a(Context context) {
            d.this.d = true;
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void b(Context context) {
            d.this.d = false;
        }
    }

    private d() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        Object obj;
        Pair<WebSocket, Boolean> a2 = a(this.b);
        if (a2 == null || (obj = a2.first) == null) {
            return;
        }
        try {
            ((WebSocket) obj).close(1000, "close in background");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String str = "wss://vapp-ide-ws.jd.com/web-socket/";
        if (com.jingdong.a.a) {
            MantoLog.d("MantoRealtimeWebSocketMgr", "doWebSocketConn------<");
            str = "wss://vapp-ide-ws-pre.jd.com/web-socket/";
        }
        WebSocket newWebSocket = com.jingdong.manto.p.a.b().a(60000).newWebSocket(new Request.Builder().url(str + this.f14179c + "_APP").tag(this.b).build(), new a());
        synchronized (this.a) {
            this.a.put(newWebSocket, Boolean.FALSE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        LaunchParam launchParam = new LaunchParam();
        launchParam.appId = str;
        launchParam.debugType = "5";
        com.jingdong.a.o(launchParam);
    }

    public static d c() {
        if (f14178g == null) {
            synchronized (d.class) {
                if (f14178g == null) {
                    f14178g = new d();
                }
            }
        }
        return f14178g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MantoJDHttpHandler.commit(new r(), new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Object obj;
        Pair<WebSocket, Boolean> a2 = a(this.b);
        if (a2 == null || (obj = a2.first) == null) {
            return;
        }
        WebSocket webSocket = (WebSocket) obj;
        boolean booleanValue = ((Boolean) a2.second).booleanValue();
        if (this.d) {
            this.f14180e.b(5);
        } else if (booleanValue) {
            webSocket.send(this.f14179c + "_APP");
            this.f14180e.a(5, 1000L);
        }
    }

    Pair<WebSocket, Boolean> a(String str) {
        synchronized (this.a) {
            try {
                try {
                    for (Map.Entry<WebSocket, Boolean> entry : this.a.entrySet()) {
                        WebSocket key = entry.getKey();
                        boolean booleanValue = entry.getValue().booleanValue();
                        if (key != null && TextUtils.equals(str, (String) key.request().tag())) {
                            return new Pair<>(key, Boolean.valueOf(booleanValue));
                        }
                    }
                } catch (Exception unused) {
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void c(String str) {
        if (a(str) != null) {
            return;
        }
        this.b = str;
        this.f14180e.b(1);
    }
}
