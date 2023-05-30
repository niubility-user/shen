package com.jd.stat.security.jma.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.jd.stat.network.NetworkException;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class g extends a implements f {
    private Handler b;
    private ArrayList<JSONObject> a = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private String f7083c = "";

    public g() {
        e();
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject) {
        try {
            i();
            JSONObject a = com.jd.stat.common.b.a.a(com.jd.stat.security.c.a, a(jSONObject));
            a.put("sdkversion", "2.5.8");
            a.put("functionId", jSONObject.optString("eventid"));
            com.jd.stat.security.jma.a.f.a(a.toString(), this.f7083c, true);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        if (this.b == null) {
            HandlerThread handlerThread = new HandlerThread("JmaTrack");
            handlerThread.start();
            this.b = new Handler(handlerThread.getLooper());
        }
    }

    private void f() {
        Context context = com.jd.stat.security.c.a;
        if (context != null) {
            try {
                File externalFilesDir = context.getExternalFilesDir("jmawsdt");
                if (externalFilesDir != null && !externalFilesDir.isDirectory()) {
                    externalFilesDir.mkdirs();
                }
                this.f7083c = externalFilesDir + "/logwsdt.dt";
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b("JDMob.Security.ScreenEventSender", "create file path = " + this.f7083c);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        final List<String> a = com.jd.stat.security.jma.a.f.a(this.f7083c);
        if (a == null || a.isEmpty()) {
            return;
        }
        com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.b()) { // from class: com.jd.stat.security.jma.b.g.2
            @Override // com.jd.stat.network.d
            protected String e() {
                try {
                    return "content=" + URLEncoder.encode(g.this.a(a).getString("content"), "UTF-8");
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        };
        dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.jma.b.g.3
            @Override // com.jd.stat.network.f
            public void a(NetworkException networkException) {
            }

            @Override // com.jd.stat.network.f
            public void a(com.jd.stat.network.e eVar) {
                JSONObject d = eVar.d();
                if (d == null || !TextUtils.equals("0", d.optString("code"))) {
                    return;
                }
                g.this.h();
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b("JDMob.Security.ScreenEventSender", "push data to server success!");
                }
            }
        });
        dVar.a((Object) ("ScreenEventSender." + System.currentTimeMillis()));
        dVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        File file = new File(this.f7083c);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private void i() {
        File file = new File(this.f7083c);
        if (file.exists() && file.isFile() && file.length() > 51200) {
            file.delete();
        }
    }

    @Override // com.jd.stat.security.jma.b.f
    public void c(final JSONObject jSONObject) {
        if (c(jSONObject.optString("pagename"))) {
            final JSONObject a = com.jd.stat.security.jma.a.e.a(jSONObject);
            MotionEvent motionEvent = (MotionEvent) jSONObject.remove("motionparam");
            if (a == null || motionEvent == null || a == null || motionEvent.getAction() != 1) {
                return;
            }
            this.a.add(jSONObject);
            Handler handler = this.b;
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.jd.stat.security.jma.b.g.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.jd.stat.common.b.c.a(a, jSONObject);
                        g.this.d(a);
                        int size = g.this.a.size();
                        if (size >= com.jd.stat.security.d.a().k()) {
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.b("JDMob.Security.ScreenEventSender", "start push data to server,size = " + size);
                            }
                            g.this.a.clear();
                            g.this.g();
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(List<String> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("appname", com.jd.stat.common.c.a(com.jd.stat.security.c.a));
            jSONObject2.put("client", "android");
            jSONObject2.put("appid", com.jd.stat.security.c.g());
            JSONArray jSONArray = new JSONArray();
            if (list != null && !list.isEmpty()) {
                int i2 = 5;
                if (list.size() <= 5) {
                    i2 = list.size();
                }
                for (int i3 = 0; i3 < i2; i3++) {
                    JSONObject jSONObject3 = null;
                    try {
                        jSONObject3 = new JSONObject(list.get(i3));
                    } catch (JSONException unused) {
                    }
                    jSONArray.put(jSONObject3);
                }
            }
            jSONObject2.put("body", jSONArray);
            jSONObject2.put("whwswswws", a());
            jSONObject.put("content", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public void d() {
        if (this.a.isEmpty()) {
            return;
        }
        this.a.clear();
        g();
    }

    private static boolean c(String str) {
        return com.jd.stat.security.jma.a.e.e().c(str);
    }
}
