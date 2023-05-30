package com.jd.dynamic.b.k;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.INetWorkRequest;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class a {
    private final com.jd.dynamic.b.k.b.d a;
    private final com.jd.dynamic.b.k.b.b b;

    /* loaded from: classes13.dex */
    public static class b {
        private static final a a = new a();
    }

    /* loaded from: classes13.dex */
    public final class c implements com.jd.dynamic.b.k.b.a {
        private final com.jd.dynamic.b.k.b.e a;
        private final com.jd.dynamic.b.k.b.b b;

        public c(@NotNull com.jd.dynamic.b.k.b.e eVar, @NotNull com.jd.dynamic.b.k.b.b bVar) {
            this.a = eVar;
            this.b = bVar;
        }

        @Override // com.jd.dynamic.b.k.b.a
        public void a() {
            com.jd.dynamic.b.a.b o = com.jd.dynamic.b.a.b.o();
            Intrinsics.checkExpressionValueIsNotNull(o, "DYABConfigUtil.getInstance()");
            int T = o.T();
            com.jd.dynamic.b.a.b o2 = com.jd.dynamic.b.a.b.o();
            Intrinsics.checkExpressionValueIsNotNull(o2, "DYABConfigUtil.getInstance()");
            long a = o2.a();
            com.jd.dynamic.b.k.b.e eVar = this.a;
            if (eVar instanceof i) {
                f d = ((i) eVar).d();
                if (T != d.a() || a != d.b()) {
                    com.jd.dynamic.b.k.b.e eVar2 = this.a;
                    f a2 = a.b().a(T, a);
                    Intrinsics.checkExpressionValueIsNotNull(a2, "ReportManager.getInstanc\u2026.createConfig(count, gap)");
                    eVar2.a(a2);
                }
                this.b.a(this, ((i) this.a).d().b());
            }
            com.jd.dynamic.b.k.b.e eVar3 = this.a;
            DynamicSdk.Engine engine = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
            String appType = engine.getAppType();
            Intrinsics.checkExpressionValueIsNotNull(appType, "DynamicSdk.getEngine().appType");
            eVar3.a(appType);
        }
    }

    /* loaded from: classes13.dex */
    public final class d implements com.jd.dynamic.b.k.b.b {
        private Handler a;
        private HandlerThread b;

        /* renamed from: com.jd.dynamic.b.k.a$d$a */
        /* loaded from: classes13.dex */
        static final class RunnableC0072a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ com.jd.dynamic.b.k.b.a f1792g;

            RunnableC0072a(com.jd.dynamic.b.k.b.a aVar) {
                this.f1792g = aVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.f1792g.a();
            }
        }

        public d() {
            HandlerThread handlerThread = new HandlerThread("REPORT_WORKER");
            this.b = handlerThread;
            handlerThread.start();
            this.a = new Handler(this.b.getLooper());
        }

        @Override // com.jd.dynamic.b.k.b.b
        public void a(@NotNull com.jd.dynamic.b.k.b.a aVar, long j2) {
            this.a.postDelayed(new RunnableC0072a(aVar), j2);
        }
    }

    /* loaded from: classes13.dex */
    public final class e {
        private final String a;

        public e(@NotNull String str) {
            this.a = str;
        }

        private final void b(Long l2, JSONObject jSONObject) {
            if (!(l2 instanceof Long) || l2.longValue() <= 0) {
                return;
            }
            jSONObject.put("createTime", System.currentTimeMillis());
        }

        @Nullable
        public JSONObject a(@NotNull h hVar) {
            JSONObject jSONObject;
            String a;
            JSONObject jSONObject2 = new JSONObject();
            Long e2 = hVar.e();
            try {
                jSONObject2.put("eventTime", (!(e2 instanceof Long) || e2.longValue() <= 0) ? System.currentTimeMillis() : e2.longValue());
                jSONObject2.put("type", "event");
                jSONObject2.put("data", hVar.c());
                jSONObject2.put("eventId", "dynamic");
                jSONObject2.put("pageId", "dynamic");
                if (hVar.d() != null) {
                    jSONObject = new JSONObject(hVar.d());
                    b(e2, jSONObject);
                    jSONObject.put("uuid", hVar.f());
                    jSONObject.put("dynamicPageId", hVar.b());
                    a = hVar.a();
                } else {
                    jSONObject = new JSONObject();
                    b(e2, jSONObject);
                    jSONObject.put("uuid", hVar.f());
                    jSONObject.put("dynamicPageId", hVar.b());
                    a = hVar.a();
                }
                jSONObject.put("dynamicEventId", a);
                jSONObject2.put("ext", jSONObject);
                return jSONObject2.put("module", this.a);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* loaded from: classes13.dex */
    public final class f {
        private final int a;
        private final long b;

        public f(int i2, long j2) {
            this.a = i2;
            this.b = j2;
        }

        public final int a() {
            return this.a;
        }

        public final long b() {
            return this.b;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof f) {
                    f fVar = (f) obj;
                    return this.a == fVar.a && this.b == fVar.b;
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            long j2 = this.b;
            return (this.a * 31) + ((int) (j2 ^ (j2 >>> 32)));
        }

        @NotNull
        public String toString() {
            return "ReportConfig(triggerCount=" + this.a + ", triggerGap=" + this.b + ")";
        }
    }

    /* loaded from: classes13.dex */
    public final class g implements com.jd.dynamic.b.k.b.d {
        private final Map<String, com.jd.dynamic.b.k.b.e> a = new HashMap();
        private final Object b = new Object();

        @Override // com.jd.dynamic.b.k.b.d
        @NotNull
        public com.jd.dynamic.b.k.b.e a(@NotNull String str) {
            com.jd.dynamic.b.k.b.e eVar;
            synchronized (this.b) {
                if (this.a.containsKey(str)) {
                    com.jd.dynamic.b.k.b.e eVar2 = this.a.get(str);
                    if (eVar2 == null) {
                        Intrinsics.throwNpe();
                    }
                    eVar = eVar2;
                } else {
                    i iVar = new i(str);
                    this.a.put(str, iVar);
                    com.jd.dynamic.b.a.b o = com.jd.dynamic.b.a.b.o();
                    Intrinsics.checkExpressionValueIsNotNull(o, "DYABConfigUtil.getInstance()");
                    int T = o.T();
                    com.jd.dynamic.b.a.b o2 = com.jd.dynamic.b.a.b.o();
                    Intrinsics.checkExpressionValueIsNotNull(o2, "DYABConfigUtil.getInstance()");
                    f a = a.b().a(T, o2.a());
                    Intrinsics.checkExpressionValueIsNotNull(a, "ReportManager.getInstanc\u2026.createConfig(count, gap)");
                    iVar.a(a);
                    eVar = iVar;
                }
            }
            return eVar;
        }
    }

    /* loaded from: classes13.dex */
    public final class h {
        @NotNull
        private final String a;
        @NotNull
        private final String b;
        @NotNull

        /* renamed from: c */
        private final String f1793c;
        @Nullable
        private final String d;
        @Nullable

        /* renamed from: e */
        private final Long f1794e;
        @Nullable

        /* renamed from: f */
        private final String f1795f;

        public h(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable String str4, @Nullable Long l2, @Nullable String str5) {
            this.a = str;
            this.b = str2;
            this.f1793c = str3;
            this.d = str4;
            this.f1794e = l2;
            this.f1795f = str5;
        }

        public /* synthetic */ h(String str, String str2, String str3, String str4, Long l2, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i2 & 8) != 0 ? null : str4, (i2 & 16) != 0 ? null : l2, (i2 & 32) != 0 ? null : str5);
        }

        @NotNull
        public final String a() {
            return this.a;
        }

        @NotNull
        public final String b() {
            return this.b;
        }

        @NotNull
        public final String c() {
            return this.f1793c;
        }

        @Nullable
        public final String d() {
            return this.d;
        }

        @Nullable
        public final Long e() {
            return this.f1794e;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof h) {
                    h hVar = (h) obj;
                    return Intrinsics.areEqual(this.a, hVar.a) && Intrinsics.areEqual(this.b, hVar.b) && Intrinsics.areEqual(this.f1793c, hVar.f1793c) && Intrinsics.areEqual(this.d, hVar.d) && Intrinsics.areEqual(this.f1794e, hVar.f1794e) && Intrinsics.areEqual(this.f1795f, hVar.f1795f);
                }
                return false;
            }
            return true;
        }

        @Nullable
        public final String f() {
            return this.f1795f;
        }

        public int hashCode() {
            String str = this.a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.b;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.f1793c;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.d;
            int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
            Long l2 = this.f1794e;
            int hashCode5 = (hashCode4 + (l2 != null ? l2.hashCode() : 0)) * 31;
            String str5 = this.f1795f;
            return hashCode5 + (str5 != null ? str5.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "ReportParam(eventId=" + this.a + ", pageId=" + this.b + ", data=" + this.f1793c + ", extJson=" + this.d + ", eventTime=" + this.f1794e + ", uuid=" + this.f1795f + ")";
        }
    }

    /* loaded from: classes13.dex */
    public final class i implements com.jd.dynamic.b.k.b.c, com.jd.dynamic.b.k.b.e {
        private final e a;
        private final Object b = new Object();

        /* renamed from: c */
        private final LinkedList<JSONObject> f1796c = new LinkedList<>();
        private f d = new f(10, Final.HALF_MINUTE);

        /* renamed from: com.jd.dynamic.b.k.a$i$a */
        /* loaded from: classes13.dex */
        public static final class C0073a implements INetWorkRequest.ResponseCallBack {
            private final List<JSONObject> a;
            private final com.jd.dynamic.b.k.b.c b;

            /* JADX WARN: Multi-variable type inference failed */
            public C0073a(@NotNull List<? extends JSONObject> list, @NotNull com.jd.dynamic.b.k.b.c cVar) {
                this.a = list;
                this.b = cVar;
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
            public void onError(@Nullable INetWorkRequest.ErrorResponse errorResponse) {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
            public void onStart() {
            }

            @Override // com.jd.dynamic.base.interfaces.INetWorkRequest.ResponseCallBack
            public void onSuccess(@Nullable JSONObject jSONObject) {
            }
        }

        public i(@NotNull String str) {
            this.a = new e(str);
        }

        private final JSONObject e(List<? extends JSONObject> list, String str) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                Iterator<? extends JSONObject> it = list.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next());
                }
                jSONObject.put("events", jSONArray);
                jSONObject.put("appId", str);
                jSONObject.put("source", "android");
                return jSONObject;
            } catch (Exception unused) {
                return null;
            }
        }

        private final List<JSONObject> f() {
            synchronized (this.b) {
                if (this.f1796c.isEmpty()) {
                    return null;
                }
                LinkedList linkedList = new LinkedList(this.f1796c);
                this.f1796c.clear();
                return linkedList;
            }
        }

        @Override // com.jd.dynamic.b.k.b.e
        public void a(@NotNull f fVar) {
            this.d = fVar;
        }

        @Override // com.jd.dynamic.b.k.b.e
        public void a(@NotNull String str) {
            synchronized (this.b) {
                List<JSONObject> f2 = f();
                if (f2 != null) {
                    JSONObject e2 = e(f2, str);
                    if (e2 != null) {
                        DynamicSdk.Engine engine = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
                        INetWorkRequest request = engine.getRequest();
                        String jSONObject = e2.toString();
                        DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
                        request.requestWithHost("platEventCollect", jSONObject, engine2.getHost(), new C0073a(f2, this));
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        }

        @Override // com.jd.dynamic.b.k.b.e
        public void b(@NotNull Context context, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5) {
            synchronized (this.b) {
                JSONObject a = this.a.a(new h(str2, str3, str4, str5, null, null, 48, null));
                if (a != null) {
                    this.f1796c.add(a);
                    if (this.f1796c.size() > this.d.a()) {
                        a(str);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        @Override // com.jd.dynamic.b.k.b.e
        public void c(@NotNull Context context, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, long j2, @NotNull String str5, @Nullable String str6) {
            synchronized (this.b) {
                JSONObject a = this.a.a(new h(str2, str3, str4, str6, Long.valueOf(j2), str5));
                if (a != null) {
                    this.f1796c.add(a);
                    if (this.f1796c.size() > this.d.a()) {
                        a(str);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        @NotNull
        public final f d() {
            return this.d;
        }
    }

    private a() {
        this.a = new g();
        this.b = new d();
    }

    public static a b() {
        return b.a;
    }

    public f a(int i2, long j2) {
        if (i2 <= 0) {
            i2 = 10;
        }
        if (j2 > 300000 || j2 < Final.HALF_MINUTE) {
            j2 = 30000;
        }
        return new f(i2, j2);
    }

    public com.jd.dynamic.b.k.b.d c() {
        return this.a;
    }

    public void d() {
        com.jd.dynamic.b.k.b.e a = this.a.a("dynamic");
        com.jd.dynamic.b.k.b.b bVar = this.b;
        c cVar = new c(a, bVar);
        if (a instanceof i) {
            bVar.a(cVar, ((i) a).d().b());
        }
    }
}
