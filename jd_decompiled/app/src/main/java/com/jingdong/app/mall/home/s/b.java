package com.jingdong.app.mall.home.s;

import com.google.gson.JsonObject;
import com.jd.framework.json.JDJSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class b {
    private String a;
    private HashMap<String, a> b = new HashMap<>();

    /* loaded from: classes4.dex */
    class a {
        String a;
        long b = System.currentTimeMillis();

        /* renamed from: c  reason: collision with root package name */
        long f10806c;

        a(b bVar, String str) {
            this.a = str;
        }

        String a() {
            return this.a;
        }

        long b() {
            return this.f10806c - this.b;
        }

        void c() {
            if (this.f10806c <= 0) {
                this.f10806c = System.currentTimeMillis();
            }
        }

        void d(JDJSONObject jDJSONObject) {
            jDJSONObject.put(this.a.concat("_S"), (Object) Long.valueOf(this.b));
            jDJSONObject.put(this.a.concat("_E"), (Object) Long.valueOf(this.f10806c));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(@NotNull String str) {
        this.a = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        a aVar = this.b.get(str);
        if (aVar != null) {
            aVar.c();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        if (this.b.get(str) == null) {
            this.b.put(str, new a(this, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NotNull HashMap<String, String> hashMap) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        Iterator<Map.Entry<String, a>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().d(jDJSONObject);
        }
        hashMap.put(this.a, jDJSONObject.toJSONString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(JsonObject jsonObject) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, a>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            a value = it.next().getValue();
            sb.append(value.a());
            sb.append(" used: ");
            sb.append(value.b());
            sb.append("  \\t");
        }
        jsonObject.addProperty(this.a, sb.toString());
    }
}
