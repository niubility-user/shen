package com.jingdong.jdma.f;

import android.text.TextUtils;
import com.jingdong.jdma.c.d;
import com.jingdong.jdma.common.utils.LogUtil;
import com.jingdong.jdma.common.utils.m;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class g {

    /* loaded from: classes12.dex */
    static class a implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ com.jingdong.jdma.bean.d.a b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12737c;
        final /* synthetic */ List d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12738e;

        a(com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, String str, List list, boolean z) {
            this.a = bVar;
            this.b = aVar;
            this.f12737c = str;
            this.d = list;
            this.f12738e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(this.b, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.c(this.f12737c, this.d, this.a, this.b, this.f12738e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(this.b, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    static class b implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ com.jingdong.jdma.bean.d.a b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12739c;
        final /* synthetic */ com.jingdong.jdma.bean.c.a d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12740e;

        b(com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, String str, com.jingdong.jdma.bean.c.a aVar2, boolean z) {
            this.a = bVar;
            this.b = aVar;
            this.f12739c = str;
            this.d = aVar2;
            this.f12740e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(this.b, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.c(this.f12739c, this.d, this.a, this.b, this.f12740e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(this.b, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    static class c implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ com.jingdong.jdma.bean.d.a b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f12741c;
        final /* synthetic */ JSONArray d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12742e;

        c(com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, String str, JSONArray jSONArray, boolean z) {
            this.a = bVar;
            this.b = aVar;
            this.f12741c = str;
            this.d = jSONArray;
            this.f12742e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(this.b, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.c(this.f12741c, this.d, this.a, this.b, this.f12742e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(this.b, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    static class d implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ com.jingdong.jdma.bean.c.a f12743c;
        final /* synthetic */ com.jingdong.jdma.bean.d.a d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12744e;

        d(com.jingdong.jdma.f.b bVar, String str, com.jingdong.jdma.bean.c.a aVar, com.jingdong.jdma.bean.d.a aVar2, boolean z) {
            this.a = bVar;
            this.b = str;
            this.f12743c = aVar;
            this.d = aVar2;
            this.f12744e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(null, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.d(this.b, this.f12743c, this.a, this.d, this.f12744e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(null, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    static class e implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ List f12745c;
        final /* synthetic */ com.jingdong.jdma.bean.d.a d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12746e;

        e(com.jingdong.jdma.f.b bVar, String str, List list, com.jingdong.jdma.bean.d.a aVar, boolean z) {
            this.a = bVar;
            this.b = str;
            this.f12745c = list;
            this.d = aVar;
            this.f12746e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(null, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.d(this.b, this.f12745c, this.a, this.d, this.f12746e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(null, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    static class f implements d.e {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ JSONArray f12747c;
        final /* synthetic */ com.jingdong.jdma.bean.d.a d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f12748e;

        f(com.jingdong.jdma.f.b bVar, String str, JSONArray jSONArray, com.jingdong.jdma.bean.d.a aVar, boolean z) {
            this.a = bVar;
            this.b = str;
            this.f12747c = jSONArray;
            this.d = aVar;
            this.f12748e = z;
        }

        @Override // com.jingdong.jdma.c.d.e
        public void a() {
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(null, new com.jingdong.jdma.bean.b.c.d("http report thread reject execute the runnable"));
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                g.d(this.b, this.f12747c, this.a, this.d, this.f12748e);
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.jdma.f.b bVar = this.a;
                if (bVar != null) {
                    bVar.a(null, new com.jingdong.jdma.bean.b.c.a(th.getMessage()));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, List<com.jingdong.jdma.bean.c.a> list, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) throws Throwable {
        JSONObject a2 = com.jingdong.jdma.bean.a.b().a();
        JSONArray jSONArray = new JSONArray();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            jSONArray.put(new JSONObject(list.get(i2).c()));
        }
        a2.put("data", jSONArray);
        String jSONObject = a2.toString();
        if (!TextUtils.isEmpty(jSONObject)) {
            a(str, jSONObject, bVar, aVar, z);
        } else if (bVar != null) {
            bVar.a(aVar, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, com.jingdong.jdma.bean.c.a aVar, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar2, boolean z) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", com.jingdong.jdma.bean.a.b().d());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(aVar.c());
        jSONObject.put("data", jSONArray);
        String jSONObject2 = jSONObject.toString();
        if (!TextUtils.isEmpty(jSONObject2)) {
            a(str, jSONObject2, bVar, aVar2, z);
        } else if (bVar != null) {
            bVar.a(aVar2, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i2, long j2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("responseCode", i2 + "");
        hashMap.put("bodyLength", j2 + "");
        hashMap.put("commonNum", com.jingdong.jdma.common.utils.c.f12674c + "");
        hashMap.put("quickNum", com.jingdong.jdma.common.utils.c.d + "");
        hashMap.put("dauNum", com.jingdong.jdma.common.utils.c.f12675e + "");
        com.jingdong.jdma.f.c.a().b("reportError", hashMap);
    }

    public static void a(String str, List<com.jingdong.jdma.bean.c.a> list, int i2, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new a(bVar, aVar, str, list, z));
    }

    public static void a(String str, com.jingdong.jdma.bean.c.a aVar, int i2, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar2, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new b(bVar, aVar2, str, aVar, z));
    }

    public static void a(String str, JSONArray jSONArray, int i2, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new c(bVar, aVar, str, jSONArray, z));
    }

    public static void a(String str, HashMap<String, String> hashMap, com.jingdong.jdma.f.b bVar, boolean z) throws Throwable {
        JSONObject a2 = com.jingdong.jdma.bean.a.b().a();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(new JSONObject(m.a(hashMap)));
        a2.put("data", jSONArray);
        a(str, a2.toString(), bVar, (com.jingdong.jdma.bean.d.a) null, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, List<com.jingdong.jdma.bean.c.a> list, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", com.jingdong.jdma.bean.a.b().d());
        JSONArray jSONArray = new JSONArray();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            jSONArray.put(list.get(i2).c());
        }
        jSONObject.put("data", jSONArray);
        String jSONObject2 = jSONObject.toString();
        if (!TextUtils.isEmpty(jSONObject2)) {
            a(str, jSONObject2, bVar, aVar, z);
        } else if (bVar != null) {
            bVar.a(aVar, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, com.jingdong.jdma.bean.c.a aVar, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar2, boolean z) throws Throwable {
        JSONObject a2 = com.jingdong.jdma.bean.a.b().a();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(new JSONObject(aVar.c()));
        a2.put("data", jSONArray);
        String jSONObject = a2.toString();
        if (!TextUtils.isEmpty(jSONObject)) {
            a(str, jSONObject, bVar, aVar2, z);
        } else if (bVar != null) {
            bVar.a(aVar2, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    public static void a(String str, com.jingdong.jdma.bean.c.a aVar, int i2, com.jingdong.jdma.bean.d.a aVar2, com.jingdong.jdma.f.b bVar, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new d(bVar, str, aVar, aVar2, z));
    }

    public static void a(String str, List<com.jingdong.jdma.bean.c.a> list, int i2, com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.f.b bVar, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new e(bVar, str, list, aVar, z));
    }

    public static void a(String str, JSONArray jSONArray, int i2, com.jingdong.jdma.bean.d.a aVar, com.jingdong.jdma.f.b bVar, boolean z) {
        com.jingdong.jdma.c.d.a().a(i2, new f(bVar, str, jSONArray, aVar, z));
    }

    private static void a(String str, String str2, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) throws Throwable {
        if (bVar != null) {
            bVar.a();
        }
        String str3 = null;
        URL url = new URL(str);
        boolean z2 = z && com.jingdong.jdma.d.a.d().b() && !com.jingdong.jdma.d.a.d().e();
        if (z2) {
            try {
                str3 = com.jingdong.jdma.d.a.d().a(url.getHost());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        com.jingdong.jdma.c.c cVar = new com.jingdong.jdma.c.c();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("JD-STD", com.jingdong.jdma.bean.a.b().c());
        com.jingdong.jdma.bean.b.a aVar2 = new com.jingdong.jdma.bean.b.a();
        aVar2.d(str);
        aVar2.b(str3);
        aVar2.c("POST");
        aVar2.a(hashMap);
        if (LogUtil.isDebug()) {
            LogUtil.w("JDMA_SendUtil", "send data -->" + str2);
            if (str2 != null) {
                LogUtil.w("JDMA_SendUtil", "send data size -->" + (str2.getBytes("utf-8").length / 1024) + " kb");
            }
        }
        if (com.jingdong.jdma.common.utils.c.f12680j) {
            byte[] b2 = com.jingdong.jdma.a.a.c.b(str2.getBytes());
            if (b2 == null) {
                if (bVar != null) {
                    bVar.a(aVar, new com.jingdong.jdma.bean.b.c.a());
                    return;
                }
                return;
            }
            aVar2.a(com.jingdong.jdma.a.a.c.a(b2));
        } else {
            aVar2.a(str2);
        }
        if (LogUtil.isDebug()) {
            LogUtil.w("JDMA_SendUtil", "send encrypted data -->" + aVar2.c());
            LogUtil.w("JDMA_SendUtil", "send encrypted data size -->" + (aVar2.c().getBytes("utf-8").length / 1024) + " kb.");
        }
        long currentTimeMillis = System.currentTimeMillis();
        cVar.a(aVar2, new C0494g(bVar, aVar, z2));
        LogUtil.w("JDMA_SendUtil", "\u7f51\u7edc\u53d1\u9001\u8017\u65f6\u65f6\u95f4:" + (System.currentTimeMillis() - currentTimeMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, JSONArray jSONArray, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) throws Throwable {
        JSONObject a2 = com.jingdong.jdma.bean.a.b().a();
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            jSONArray2.put(new JSONObject(jSONArray.optString(i2)));
        }
        a2.put("data", jSONArray2);
        String jSONObject = a2.toString();
        if (!TextUtils.isEmpty(jSONObject)) {
            a(str, jSONObject, bVar, aVar, z);
        } else if (bVar != null) {
            bVar.a(aVar, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String str, JSONArray jSONArray, com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", com.jingdong.jdma.bean.a.b().d());
        jSONObject.put("data", jSONArray);
        String jSONObject2 = jSONObject.toString();
        if (!TextUtils.isEmpty(jSONObject2)) {
            a(str, jSONObject2, bVar, aVar, z);
        } else if (bVar != null) {
            bVar.a(aVar, new com.jingdong.jdma.bean.b.c.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.jdma.f.g$g  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0494g implements com.jingdong.jdma.c.a {
        final /* synthetic */ com.jingdong.jdma.f.b a;
        final /* synthetic */ com.jingdong.jdma.bean.d.a b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ boolean f12749c;

        C0494g(com.jingdong.jdma.f.b bVar, com.jingdong.jdma.bean.d.a aVar, boolean z) {
            this.a = bVar;
            this.b = aVar;
            this.f12749c = z;
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.b bVar) {
            String str = "";
            boolean z = false;
            try {
                try {
                    JSONObject jSONObject = new JSONObject(bVar.a());
                    str = jSONObject.optString("c", "");
                    z = !str.isEmpty();
                    LogUtil.w("JDMA_SendUtil", "http report success");
                    if (!z && com.jingdong.jdma.f.c.d) {
                        com.jingdong.jdma.f.c.a().a("reportResult", jSONObject.toString());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    com.jingdong.jdma.f.b bVar2 = this.a;
                    if (bVar2 == null) {
                        return;
                    }
                    if (z) {
                        bVar2.a(this.b);
                        if (!this.f12749c) {
                            return;
                        }
                    } else {
                        com.jingdong.jdma.bean.b.c.b bVar3 = new com.jingdong.jdma.bean.b.c.b(200);
                        bVar3.a(str);
                        this.a.a(this.b, bVar3);
                        return;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    com.jingdong.jdma.f.b bVar4 = this.a;
                    if (bVar4 == null) {
                        return;
                    }
                    if (z) {
                        bVar4.a(this.b);
                        if (!this.f12749c) {
                            return;
                        }
                    } else {
                        com.jingdong.jdma.bean.b.c.b bVar5 = new com.jingdong.jdma.bean.b.c.b(200);
                        bVar5.a(str);
                        this.a.a(this.b, bVar5);
                        return;
                    }
                }
            } finally {
                com.jingdong.jdma.f.b bVar6 = this.a;
                if (bVar6 != null) {
                    if (0 != 0) {
                        bVar6.a(this.b);
                        if (this.f12749c) {
                            com.jingdong.jdma.d.a.d().c();
                        }
                    } else {
                        com.jingdong.jdma.bean.b.c.b bVar7 = new com.jingdong.jdma.bean.b.c.b(200);
                        bVar7.a("");
                        this.a.a(this.b, bVar7);
                    }
                }
            }
        }

        @Override // com.jingdong.jdma.c.a
        public void a(com.jingdong.jdma.bean.b.c.a aVar) {
            LogUtil.w("JDMA_SendUtil", "http report fail");
            if ((aVar instanceof com.jingdong.jdma.bean.b.c.b) && com.jingdong.jdma.f.c.d) {
                com.jingdong.jdma.bean.b.c.b bVar = (com.jingdong.jdma.bean.b.c.b) aVar;
                g.b(bVar.b(), bVar.a());
            }
            if (this.f12749c) {
                com.jingdong.jdma.d.a.d().a();
            }
            com.jingdong.jdma.f.b bVar2 = this.a;
            if (bVar2 != null) {
                bVar2.a(this.b, aVar);
            }
        }

        @Override // com.jingdong.jdma.c.a
        public void a() {
            LogUtil.w("JDMA_SendUtil", "http report finish");
            com.jingdong.jdma.f.b bVar = this.a;
            if (bVar != null) {
                com.jingdong.jdma.bean.d.a aVar = this.b;
                bVar.a(aVar != null ? aVar.a() : -1);
            }
        }
    }
}
