package com.jd.stat.common.b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e extends JSONObject {
    private LinkedHashMap<String, Long> a = new LinkedHashMap<>();
    private HashSet<String> b = new HashSet<>();

    /* renamed from: c  reason: collision with root package name */
    private long f6982c;
    private long d;

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r0 == 1) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
        r4.b.addAll(com.jd.stat.security.d.a().G());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public e(java.lang.String r5) {
        /*
            r4 = this;
            r4.<init>()
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r4.a = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r4.b = r0
            r0 = 0
            r4.f6982c = r0
            r4.d = r0
            r0 = -1
            int r1 = r5.hashCode()     // Catch: java.lang.Exception -> L5b
            r2 = 101397(0x18c15, float:1.42087E-40)
            r3 = 1
            if (r1 == r2) goto L32
            r2 = 92913686(0x589c016, float:1.295398E-35)
            if (r1 == r2) goto L28
            goto L3b
        L28:
            java.lang.String r1 = "alter"
            boolean r5 = r5.equals(r1)     // Catch: java.lang.Exception -> L5b
            if (r5 == 0) goto L3b
            r0 = 1
            goto L3b
        L32:
            java.lang.String r1 = "fix"
            boolean r5 = r5.equals(r1)     // Catch: java.lang.Exception -> L5b
            if (r5 == 0) goto L3b
            r0 = 0
        L3b:
            if (r0 == 0) goto L4e
            if (r0 == r3) goto L40
            goto L5b
        L40:
            java.util.HashSet<java.lang.String> r5 = r4.b     // Catch: java.lang.Exception -> L5b
            com.jd.stat.security.d r0 = com.jd.stat.security.d.a()     // Catch: java.lang.Exception -> L5b
            java.util.Set r0 = r0.G()     // Catch: java.lang.Exception -> L5b
            r5.addAll(r0)     // Catch: java.lang.Exception -> L5b
            goto L5b
        L4e:
            java.util.HashSet<java.lang.String> r5 = r4.b     // Catch: java.lang.Exception -> L5b
            com.jd.stat.security.d r0 = com.jd.stat.security.d.a()     // Catch: java.lang.Exception -> L5b
            java.util.Set r0 = r0.F()     // Catch: java.lang.Exception -> L5b
            r5.addAll(r0)     // Catch: java.lang.Exception -> L5b
        L5b:
            long r0 = java.lang.System.currentTimeMillis()
            r4.f6982c = r0
            long r0 = java.lang.System.currentTimeMillis()
            r4.d = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.stat.common.b.e.<init>(java.lang.String):void");
    }

    public String a() {
        try {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, Long> entry : this.a.entrySet()) {
                if (this.b.contains(entry.getKey())) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return "c";
        }
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject put(@NonNull String str, boolean z) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.put(str, z);
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject putOpt(@Nullable String str, @Nullable Object obj) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.putOpt(str, obj);
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject put(@NonNull String str, double d) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.put(str, d);
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject put(@NonNull String str, int i2) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.put(str, i2);
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject put(@NonNull String str, long j2) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.put(str, j2);
    }

    @Override // org.json.JSONObject
    @NonNull
    public JSONObject put(@NonNull String str, @Nullable Object obj) throws JSONException {
        this.a.put(str, Long.valueOf(System.currentTimeMillis() - this.d));
        this.d = System.currentTimeMillis();
        return super.put(str, obj);
    }
}
