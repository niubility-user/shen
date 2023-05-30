package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.m.c1.e;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.utils.u;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public final class m {
    public final HashMap<WebSocket, Boolean> a = new HashMap<>(2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends WebSocketListener {
        final /* synthetic */ String a;
        final /* synthetic */ com.jingdong.manto.h b;

        a(String str, com.jingdong.manto.h hVar) {
            this.a = str;
            this.b = hVar;
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i2, String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("code", Integer.valueOf(i2));
            hashMap.put("socketTaskId", this.a);
            hashMap.put(XView2Constants.STATE, "close");
            hashMap.put("reason", str);
            com.jingdong.manto.m.d a = new e.a().a(this.b);
            a.a(hashMap);
            a.a();
            synchronized (m.this.a) {
                m.this.a.remove(webSocket);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onClosing(WebSocket webSocket, int i2, String str) {
            super.onClosing(webSocket, i2, str);
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            HashMap hashMap = new HashMap();
            hashMap.put("socketTaskId", this.a);
            hashMap.put(XView2Constants.STATE, "error");
            hashMap.put("errMsg", th.getMessage());
            com.jingdong.manto.m.d a = new e.a().a(this.b);
            a.a(hashMap);
            a.a();
            synchronized (m.this.a) {
                m.this.a.remove(webSocket);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            HashMap hashMap = new HashMap();
            hashMap.put("data", MantoUtils.replaceChangeLineCharacter(str));
            hashMap.put("isBuffer", Boolean.FALSE);
            hashMap.put("socketTaskId", this.a);
            hashMap.put(XView2Constants.STATE, "message");
            com.jingdong.manto.m.d a = new e.a().a(this.b);
            a.a(hashMap);
            a.a();
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, ByteString byteString) {
            super.onMessage(webSocket, byteString);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("isBuffer", Boolean.TRUE);
                jSONObject.put("socketTaskId", this.a);
                jSONObject.put(XView2Constants.STATE, "message");
            } catch (Exception unused) {
            }
            HashMap hashMap = new HashMap();
            hashMap.put("data", ByteBuffer.wrap(byteString.toByteArray()));
            u.a((e0) this.b, jSONObject, (Map) hashMap, true);
            com.jingdong.manto.m.d a = new e.a().a(this.b);
            a.a(jSONObject.toString());
            a.a();
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            Headers headers;
            JSONObject jSONObject = new JSONObject();
            if (response.networkResponse() != null && response.networkResponse().request() != null && (headers = response.networkResponse().request().headers()) != null) {
                for (int i2 = 0; i2 < headers.size(); i2++) {
                    String name = headers.name(i2);
                    try {
                        jSONObject.put(name, headers.get(name));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.put("socketTaskId", this.a);
            hashMap.put(XView2Constants.STATE, JshopConst.JSKEY_CATE_OPEN);
            hashMap.put("header", jSONObject);
            com.jingdong.manto.m.d a = new e.a().a(this.b);
            a.a(hashMap);
            a.a();
            synchronized (m.this.a) {
                m.this.a.put(webSocket, Boolean.TRUE);
            }
        }
    }

    public m(String str, int i2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(com.jingdong.manto.h hVar, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "fail";
        } else {
            str3 = "fail:" + str2;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("socketTaskId", str);
        hashMap.put(XView2Constants.STATE, "error");
        hashMap.put("errMsg", str3);
        com.jingdong.manto.m.d a2 = new e.a().a(hVar);
        a2.a(hashMap);
        a2.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pair<WebSocket, Boolean> a(String str) {
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

    public void a(com.jingdong.manto.h hVar, String str, int i2, JSONObject jSONObject, Map map) {
        WebSocket newWebSocket = com.jingdong.manto.p.a.b().a(i2).newWebSocket(new Request.Builder().url(jSONObject.optString("url")).tag(str).build(), new a(str, hVar));
        synchronized (this.a) {
            this.a.put(newWebSocket, Boolean.FALSE);
        }
    }
}
