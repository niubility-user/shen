package com.jd.stat.security;

import android.content.Context;
import android.text.TextUtils;
import com.jd.stat.common.b.f;
import com.jd.stat.common.t;
import com.jd.stat.network.NetworkException;
import com.jd.stat.security.jma.b.j;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e {
    private static e a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7071c;

    private e(Context context) {
        this.b = f.a(context).getString("jma_softfingerprint", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appname", com.jd.stat.common.c.a(c.a));
        jSONObject.put("whwswswws", this.b);
        jSONObject.put("jdkey", t.b());
        jSONObject.put("installtionid", c.h());
        jSONObject.put("appid", c.g());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("alterationinfo");
        jSONArray.put("fixedinfo");
        jSONObject.put("dataset", jSONArray);
        jSONObject.put("sdkversion", "2.5.8");
        jSONObject.put("clientversion", com.jd.stat.common.c.c(c.a));
        jSONObject.put("client", "android");
        jSONObject.put("body", new JSONObject());
        return jSONObject;
    }

    public void b() {
        if (c.k()) {
            this.f7071c = false;
            f.a("lastsofttime", "");
            c();
        }
    }

    public void c() {
        if (d.a().p() && !this.f7071c) {
            String b = f.b("lastsofttime", "");
            if (!TextUtils.isEmpty(b) && b.length() > 13) {
                b = b.substring(0, 8);
            }
            if (com.jd.stat.common.b.b.a) {
                com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "genSoftKey.lasttime = " + b);
            }
            if (!TextUtils.isEmpty(b)) {
                String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
                if (com.jd.stat.common.b.b.a) {
                    com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "genSoftKey.now = " + format);
                }
                if (TextUtils.equals(format, b)) {
                    if (com.jd.stat.common.b.b.a) {
                        com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "everyday request only one time!");
                        return;
                    }
                    return;
                }
            }
            if (com.jd.stat.common.b.b.a) {
                com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "start request softkey!");
            }
            this.f7071c = true;
            com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.a()) { // from class: com.jd.stat.security.e.3
                @Override // com.jd.stat.network.d
                protected String e() {
                    try {
                        return "content=" + URLEncoder.encode(e.this.d().toString(), "UTF-8");
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
            };
            dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.e.4
                @Override // com.jd.stat.network.f
                public void a(NetworkException networkException) {
                }

                @Override // com.jd.stat.network.f
                public void a(com.jd.stat.network.e eVar) {
                    JSONObject d = eVar.d();
                    if (d != null) {
                        try {
                            if (com.jd.stat.common.b.b.a) {
                                com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "get soft key");
                                com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", String.format("softkey json: \n%s", com.jd.stat.common.b.c.a(d.toString())));
                            }
                            String optString = d.optString("whwswswws");
                            if (TextUtils.isEmpty(optString)) {
                                return;
                            }
                            e.this.b = optString;
                            f.a(c.a).edit().putString("jma_softfingerprint", optString).putString("lastsofttime", new SimpleDateFormat("yyyyMMdd").format(new Date())).apply();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
            dVar.a((Object) ("SecuritySoftKeyGetter." + System.currentTimeMillis()));
            dVar.h();
        }
    }

    public static e a(Context context) {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e(context);
                }
            }
        }
        return a;
    }

    public String a() {
        return this.b;
    }

    public void a(final com.jd.stat.common.callback.a<Integer, Integer> aVar) {
        if (!d.a().p()) {
            if (aVar != null) {
                aVar.b(-1);
                return;
            }
            return;
        }
        if (com.jd.stat.common.b.b.a) {
            com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "start request softkey and check appid!");
        }
        com.jd.stat.network.d dVar = new com.jd.stat.network.d(j.a()) { // from class: com.jd.stat.security.e.1
            @Override // com.jd.stat.network.d
            protected String e() {
                try {
                    return "content=" + URLEncoder.encode(e.this.d().toString(), "UTF-8");
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return null;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    return null;
                }
            }
        };
        dVar.b(true);
        dVar.a(new com.jd.stat.network.f() { // from class: com.jd.stat.security.e.2
            @Override // com.jd.stat.network.f
            public void a(com.jd.stat.network.e eVar) {
                JSONObject d = eVar.d();
                if (d != null) {
                    try {
                        if (com.jd.stat.common.b.b.a) {
                            com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", "get soft key");
                            com.jd.stat.common.b.b.b("JDMob.Security.SecuritySoftKeyGetter", String.format("softkey json: \n%s", com.jd.stat.common.b.c.a(d.toString())));
                        }
                        String optString = d.optString("whwswswws");
                        if (!TextUtils.isEmpty(optString)) {
                            e.this.b = optString;
                            f.a(c.a).edit().putString("jma_softfingerprint", optString).putString("lastsofttime", new SimpleDateFormat("yyyyMMdd").format(new Date())).apply();
                        }
                        int optInt = d.optInt("code", -1);
                        int optInt2 = d.optInt("appidStatuscode", -1);
                        com.jd.stat.common.callback.a aVar2 = aVar;
                        if (aVar2 != null) {
                            if (optInt != 0) {
                                if (optInt != 3) {
                                    aVar2.b(104);
                                } else {
                                    aVar2.b(105);
                                }
                            } else if (optInt2 == 0) {
                                aVar2.b(101);
                            } else if (optInt2 == 1) {
                                aVar2.a(100);
                            } else if (optInt2 == -1) {
                                aVar2.b(104);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

            @Override // com.jd.stat.network.f
            public void a(NetworkException networkException) {
                if (aVar != null) {
                    int errorCode = networkException.getErrorCode();
                    if (errorCode != -1001) {
                        if (errorCode == 400) {
                            aVar.b(105);
                            return;
                        } else if (errorCode != 408 && errorCode != 504) {
                            aVar.b(104);
                            return;
                        }
                    }
                    aVar.b(102);
                }
            }
        });
        dVar.a((Object) ("SecuritySoftKeyGetter." + System.currentTimeMillis()));
        dVar.h();
    }
}
