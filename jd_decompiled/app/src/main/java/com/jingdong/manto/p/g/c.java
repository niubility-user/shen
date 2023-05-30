package com.jingdong.manto.p.g;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.jd.jdcache.util.UrlHelper;
import com.jdpay.net.http.HTTP;
import com.jingdong.manto.h;
import com.jingdong.manto.i.e;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.c0;
import com.jingdong.manto.p.g.b;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.m;
import com.jingdong.manto.utils.t;
import com.jingdong.manto.utils.u;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class c {
    private int a;
    private final ArrayList<com.jingdong.manto.p.g.b> b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    protected final CopyOnWriteArrayList<String> f13950c = new CopyOnWriteArrayList<>();

    /* loaded from: classes16.dex */
    class a implements Callback {
        final /* synthetic */ com.jingdong.manto.p.g.b a;
        final /* synthetic */ String b;

        a(com.jingdong.manto.p.g.b bVar, String str) {
            this.a = bVar;
            this.b = str;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, IOException iOException) {
            b bVar;
            String str;
            c.this.a(this.a);
            if ((iOException instanceof ConnectTimeoutException) || (iOException instanceof SocketTimeoutException)) {
                bVar = this.a.f13935c;
                str = "request timeout";
            } else {
                bVar = this.a.f13935c;
                str = "request error";
            }
            bVar.a(str);
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
            JSONObject jSONObject = new JSONObject();
            if (response.networkResponse() != null && response.networkResponse().request() != null) {
                Headers headers = TextUtils.equals(m.a("response", "1"), "1") ? response.networkResponse().headers() : response.networkResponse().request().headers();
                if (headers != null) {
                    for (int i2 = 0; i2 < headers.size(); i2++) {
                        String name = headers.name(i2);
                        try {
                            jSONObject.put(name, headers.get(name));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            Object obj = null;
            try {
                obj = TextUtils.equals(this.b, "arraybuffer") ? ByteBuffer.wrap(response.body().bytes()) : response.body().string();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            this.a.f13935c.a(IMantoBaseModule.SUCCESS, obj, response.code(), jSONObject);
            c.this.d(this.a.f13939h);
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a(String str);

        void a(String str, Object obj, int i2, JSONObject jSONObject);
    }

    public c(String str, String str2, e eVar) {
        this.a = 20;
        int i2 = eVar.f13095e;
        this.a = i2 < 1 ? this.a : i2;
    }

    private void a(String str, Call call) {
        d(str);
        try {
            call.cancel();
        } catch (Exception unused) {
        }
    }

    private static boolean c(String str) {
        return str.equalsIgnoreCase("POST") || str.equalsIgnoreCase(UrlHelper.METHOD_PUT) || str.equalsIgnoreCase(UrlHelper.METHOD_DELETE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void d(String str) {
        if (str != null) {
            synchronized (this.b) {
                Iterator<com.jingdong.manto.p.g.b> it = this.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    com.jingdong.manto.p.g.b next = it.next();
                    if (next != null && TextUtils.equals(str, next.f13939h)) {
                        this.b.remove(next);
                        break;
                    }
                }
            }
        }
    }

    public final com.jingdong.manto.p.g.b a(String str) {
        if (str == null) {
            return null;
        }
        synchronized (this.b) {
            Iterator<com.jingdong.manto.p.g.b> it = this.b.iterator();
            while (it.hasNext()) {
                com.jingdong.manto.p.g.b next = it.next();
                if (next != null && TextUtils.equals(str, next.f13939h)) {
                    return next;
                }
            }
            return null;
        }
    }

    public void a(h hVar, c0 c0Var, int i2, JSONObject jSONObject, Map<String, String> map, ArrayList<String> arrayList, b bVar, String str, String str2) {
        if (!u.a(hVar, jSONObject, c0Var)) {
            bVar.a(c0Var.msg);
            return;
        }
        String optString = jSONObject.optString("url");
        Object opt = jSONObject.opt("data");
        String optString2 = jSONObject.optString("method");
        String optString3 = jSONObject.optString("responseType", "text");
        if (TextUtils.isEmpty(optString2)) {
            optString2 = "GET";
        }
        if (TextUtils.isEmpty(optString)) {
            bVar.a("url is null");
        } else if (!URLUtil.isHttpsUrl(optString) && !URLUtil.isHttpUrl(optString)) {
            bVar.a("request protocol must be http or https");
        } else {
            byte[] bArr = new byte[0];
            if (opt != null && c(optString2)) {
                if (opt instanceof String) {
                    bArr = ((String) opt).getBytes(Charset.forName("UTF-8"));
                } else if (opt instanceof ByteBuffer) {
                    bArr = t.a((ByteBuffer) opt);
                }
            }
            if ("POST".equalsIgnoreCase(optString2)) {
                String str3 = map.get("content-type");
                if ("application/x-www-form-urlencoded".equalsIgnoreCase(str3) && (opt instanceof String)) {
                    bArr = ((String) opt).getBytes(Charset.forName("UTF-8"));
                } else if (TextUtils.equals(HTTP.CONTENT_TYPE_JSON, str3)) {
                    MantoLog.d("RequestTaskManager", String.format("POST and content-type = %s", str3));
                    if (opt instanceof String) {
                        try {
                            bArr = new JSONObject(String.valueOf(opt)).toString().getBytes(Charset.forName("UTF-8"));
                        } catch (Throwable unused) {
                        }
                    }
                }
            }
            synchronized (this.b) {
                if (this.b.size() >= this.a) {
                    bVar.a("max connected");
                    return;
                }
                com.jingdong.manto.p.g.b a2 = new b.C0651b().f(optString).a(optString2).a(arrayList).a(bArr).a(bVar).a(map).a(i2).b(str2).e(str).c(map.get("content-type")).d(jSONObject.optString("responseType", "text")).a();
                synchronized (this.b) {
                    this.b.add(a2);
                }
                if (a2.f13936e == null) {
                    a2.f13936e = new ArrayList<>();
                }
                if (!com.jingdong.manto.p.c.a(a2.f13936e, a2.f13940i)) {
                    a(a2);
                    a2.f13935c.a("url not in domain list");
                    return;
                }
                Call newCall = com.jingdong.manto.p.a.b().a(i2).newCall(a2.f13941j);
                a2.f13938g = newCall;
                newCall.enqueue(new a(a2, optString3));
            }
        }
    }

    public final void a(com.jingdong.manto.p.g.b bVar) {
        this.f13950c.add(bVar.f13939h);
        a(bVar.f13939h, bVar.f13938g);
    }

    public final boolean b(String str) {
        return this.f13950c.contains(str);
    }
}
