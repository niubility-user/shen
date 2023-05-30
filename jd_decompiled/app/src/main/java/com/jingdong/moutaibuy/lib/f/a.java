package com.jingdong.moutaibuy.lib.f;

import android.text.TextUtils;
import com.jingdong.moutaibuy.lib.f.b;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes16.dex */
public final class a {

    /* renamed from: com.jingdong.moutaibuy.lib.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public static class C0705a extends com.jingdong.moutaibuy.lib.f.b {
        public C0705a() {
            super("bottleCapCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class b extends com.jingdong.moutaibuy.lib.f.b {
        public b() {
            super("bottleFinalCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class c extends com.jingdong.moutaibuy.lib.f.b {
        public c() {
            super("bottleTakeCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class d extends com.jingdong.moutaibuy.lib.f.b {
        public d() {
            super("bottomLabelCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class e extends com.jingdong.moutaibuy.lib.f.b {
        public e() {
            super("logisticsLabelCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class f extends com.jingdong.moutaibuy.lib.f.b {
        public f() {
            super("rubberCapCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class g extends com.jingdong.moutaibuy.lib.f.b {
        public g() {
            super("traceLabelCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class h extends com.jingdong.moutaibuy.lib.f.b {
        public h() {
            super("traceLabelSplitCheck");
        }
    }

    /* loaded from: classes16.dex */
    public static class i extends com.jingdong.moutaibuy.lib.f.b {
        public i() {
            super("videoContinuityCheck");
        }
    }

    public static void a(int i2, String str, String str2, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        C0705a c0705a = new C0705a();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        c0705a.a(i2, hashMap, cVar);
    }

    public static void b(int i2, String str, String str2, String str3, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        b bVar = new b();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        hashMap.put("logisticsCode", str3);
        bVar.a(i2, hashMap, cVar);
    }

    public static void c(int i2, String str, String str2, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        c cVar2 = new c();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        cVar2.a(i2, hashMap, cVar);
    }

    public static void d(int i2, String str, String str2, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        d dVar = new d();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        dVar.a(i2, hashMap, cVar);
    }

    public static void e(int i2, String str, String str2, String str3, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        e eVar = new e();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        hashMap.put("logisticsCode", str3);
        eVar.a(i2, hashMap, cVar);
    }

    public static void f(int i2, String str, String str2, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        f fVar = new f();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        fVar.a(i2, hashMap, cVar);
    }

    public static void g(int i2, String str, String str2, String str3, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        g gVar = new g();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        hashMap.put("traceCode", str3);
        gVar.a(i2, hashMap, cVar);
    }

    public static void h(int i2, String str, String str2, b.c cVar) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        h hVar = new h();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", str2);
        hVar.a(i2, hashMap, cVar);
    }

    public static void i(int i2, String str, List<String> list, b.c cVar) {
        if (TextUtils.isEmpty(str) || list == null || list.size() <= 0) {
            return;
        }
        i iVar = new i();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("requestId", str);
        hashMap.put("image", new JSONArray((Collection) list));
        iVar.a(i2, hashMap, cVar);
    }
}
