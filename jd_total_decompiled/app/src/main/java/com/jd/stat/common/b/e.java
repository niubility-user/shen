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
    */
    public e(String str) {
        this.f6982c = 0L;
        this.d = 0L;
        char c2 = '\uffff';
        try {
            int hashCode = str.hashCode();
            if (hashCode != 101397) {
                if (hashCode == 92913686 && str.equals("alter")) {
                    c2 = 1;
                }
            } else if (str.equals("fix")) {
                c2 = 0;
            }
            this.b.addAll(com.jd.stat.security.d.a().F());
        } catch (Exception unused) {
        }
        this.f6982c = System.currentTimeMillis();
        this.d = System.currentTimeMillis();
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
