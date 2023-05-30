package com.jingdong.manto.s;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.manto.f;
import com.jingdong.manto.network.common.IMantoHttpListener;
import com.jingdong.manto.network.common.MantoJDHttpHandler;
import com.jingdong.manto.network.mantorequests.r;
import com.jingdong.manto.q.n;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.a;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class a {

    /* renamed from: j  reason: collision with root package name */
    private static volatile a f14156j;

    /* renamed from: k  reason: collision with root package name */
    private static WeakReference<f> f14157k = new WeakReference<>(null);
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f14158c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f14159e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f14160f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f14161g;
    private final HashMap<WebSocket, Boolean> a = new HashMap<>(1);
    boolean d = false;

    /* renamed from: h  reason: collision with root package name */
    MantoHandler f14162h = new c(Looper.getMainLooper());

    /* renamed from: i  reason: collision with root package name */
    a.InterfaceC0688a f14163i = new d();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.s.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0669a extends WebSocketListener {
        C0669a() {
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i2, String str) {
            synchronized (a.this.a) {
                a.this.a.remove(webSocket);
            }
            a.this.f14159e = false;
            com.jingdong.manto.utils.a.a().b(a.this.f14163i);
            Message message = new Message();
            message.what = 6;
            message.obj = "\u771f\u673a\u8c03\u8bd5\u8fde\u63a5\u5df2\u65ad\u5f00";
            a.this.f14162h.b(message);
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i2, String str) {
            super.onClosing(webSocket, i2, str);
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            synchronized (a.this.a) {
                a.this.a.remove(webSocket);
            }
            a.this.f14159e = false;
            com.jingdong.manto.utils.a.a().b(a.this.f14163i);
            Message message = new Message();
            message.what = 6;
            message.obj = "\u771f\u673a\u8c03\u8bd5\u8fde\u63a5\u5931\u8d25";
            a.this.f14162h.b(message);
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("code");
                if (!TextUtils.equals("-21", optString) && !TextUtils.equals("-25", optString)) {
                    if (TextUtils.equals("0", optString)) {
                        String optString2 = jSONObject.optString("result");
                        if (TextUtils.equals(optString2, "app_pong")) {
                            a.this.f14162h.a(5);
                            a.this.f14162h.a(3, 3000L);
                            return;
                        } else if (TextUtils.equals(optString2, "close")) {
                            a.this.f14162h.b(5);
                            return;
                        } else if (TextUtils.equals(optString2, "pong")) {
                            return;
                        } else {
                            a.this.a((f) a.f14157k.get(), optString2);
                            return;
                        }
                    }
                    return;
                }
                Message message = new Message();
                message.what = 6;
                message.obj = jSONObject.optString("errorMessage", "\u771f\u673a\u8c03\u8bd5\u8fde\u63a5\u5df2\u65ad\u5f00");
                a.this.f14162h.b(message);
                synchronized (a.this.a) {
                    a.this.a.remove(webSocket);
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
            synchronized (a.this.a) {
                a.this.a.put(webSocket, Boolean.TRUE);
            }
            a.this.f14162h.b(4);
            a.this.f14159e = true;
            a.this.d = false;
            com.jingdong.manto.utils.a.a().a(a.this.f14163i);
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
            a.this.f14162h.b(message);
        }
    }

    /* loaded from: classes16.dex */
    class c extends MantoHandler {
        c(Looper looper) {
            super(looper);
        }

        @Override // com.jingdong.manto.sdk.thread.MantoHandler, com.jingdong.manto.sdk.thread.d.a
        public final void a(Message message) {
            switch (message.what) {
                case 1:
                    a.this.g();
                    return;
                case 2:
                    a.this.f14158c = (String) message.obj;
                    a.this.c();
                    return;
                case 3:
                    a.this.i();
                    return;
                case 4:
                    a.this.h();
                    return;
                case 5:
                case 7:
                    a.this.b();
                    return;
                case 6:
                    a.this.a((String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes16.dex */
    class d implements a.InterfaceC0688a {
        d() {
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void a(Context context) {
            a.this.d = true;
        }

        @Override // com.jingdong.manto.utils.a.InterfaceC0688a
        public void b(Context context) {
            a aVar = a.this;
            aVar.d = false;
            aVar.f14162h.a(7);
            a.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class e implements DialogInterface.OnClickListener {
        e() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            try {
                ((f) a.f14157k.get()).i().finish();
                a.f14157k.clear();
                dialogInterface.dismiss();
                a.this.f14161g = false;
            } catch (Exception unused) {
            }
        }
    }

    private a() {
    }

    private String a(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("user", str);
            jSONObject2.put("data", jSONObject);
        } catch (Exception unused) {
        }
        return jSONObject2.toString();
    }

    private void a(f fVar) {
        f14157k = new WeakReference<>(fVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(f fVar, String str) {
        if (fVar == null) {
            return;
        }
        try {
            fVar.f13015g.a("remoteDebugCommand", str, 0);
            n i2 = fVar.f13014f.getFirstPage().i();
            i2.a("remoteDebugCommand", str, i2.hashCode());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        try {
            if (this.f14161g) {
                return;
            }
            com.jingdong.manto.widget.dialog.a.a(f14157k.get().i(), null, str, "\u786e\u5b9a", null, new e(), null, null, null, null).show();
            this.f14161g = true;
        } catch (Exception unused) {
            this.f14161g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Object obj;
        Pair<WebSocket, Boolean> b2 = b(this.b);
        if (b2 == null || (obj = b2.first) == null) {
            return;
        }
        try {
            ((WebSocket) obj).close(1000, "close in background");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        String str = "wss://vapp-ide-ws.jd.com/debug-web-socket/";
        if (com.jingdong.a.a) {
            MantoLog.d("MantoRealtimeDebugWebSocketMgr", "doWebSocketConn------<");
            str = "wss://vapp-ide-ws-pre.jd.com/debug-web-socket/";
        }
        WebSocket newWebSocket = com.jingdong.manto.p.a.b().a(60000).newWebSocket(new Request.Builder().url(str + this.f14158c + "_APP").tag(this.b).build(), new C0669a());
        synchronized (this.a) {
            this.a.put(newWebSocket, Boolean.FALSE);
        }
    }

    public static a f() {
        if (f14156j == null) {
            synchronized (a.class) {
                if (f14156j == null) {
                    f14156j = new a();
                }
            }
        }
        return f14156j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        MantoJDHttpHandler.commit(new r(), new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("phoneModel", BaseInfo.getDeviceModel());
            jSONObject2.put("phoneBrand", BaseInfo.getDeviceBrand());
            jSONObject2.put("system", Build.VERSION.SDK_INT);
            jSONObject2.put("appVersion", com.jingdong.manto.b.g().b("versionName"));
            jSONObject.put("debug", "2");
            jSONObject.put("msg", jSONObject2.toString());
        } catch (Exception unused) {
        }
        a(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Object obj;
        Pair<WebSocket, Boolean> b2 = b(this.b);
        if (b2 == null || (obj = b2.first) == null) {
            return;
        }
        WebSocket webSocket = (WebSocket) obj;
        boolean booleanValue = ((Boolean) b2.second).booleanValue();
        if (this.d) {
            this.f14162h.a(7, Final.HALF_MINUTE);
        } else if (booleanValue) {
            webSocket.send(a(this.f14158c + "_APP", new JSONObject()));
            this.f14162h.a(5, 2000L);
        }
    }

    public synchronized void a(JSONObject jSONObject) {
        Object obj;
        Pair<WebSocket, Boolean> b2 = b(this.b);
        if (b2 != null && (obj = b2.first) != null) {
            WebSocket webSocket = (WebSocket) obj;
            if (((Boolean) b2.second).booleanValue()) {
                webSocket.send(a(this.f14158c + "_APP", jSONObject));
            }
        }
    }

    public void a(boolean z) {
        this.f14160f = z;
    }

    Pair<WebSocket, Boolean> b(String str) {
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

    public void b(f fVar, String str) {
        a(fVar);
        if (b(str) != null) {
            return;
        }
        this.b = str;
        this.f14162h.b(1);
    }

    public boolean d() {
        return this.f14159e;
    }

    public boolean e() {
        return this.f14160f;
    }
}
