package com.jingdong.manto.pkg.b;

import android.text.TextUtils;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.b.h;
import com.jingdong.manto.pkg.b.i;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.t;
import com.jingdong.manto.utils.z;
import com.jingdong.sdk.jweb.JWebResourceResponse;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class g {

    /* renamed from: e */
    private static final String f13983e = "g";

    /* renamed from: f */
    private static g f13984f = new a(null);

    /* renamed from: g */
    private static final Map<com.jingdong.manto.f, g> f13985g = new HashMap();
    private final Map<String, d> a;
    private d b;

    /* renamed from: c */
    private final Collection<String> f13986c;
    private final ConcurrentHashMap<String, String> d;

    /* loaded from: classes16.dex */
    class a extends g {
        a(com.jingdong.manto.f fVar) {
            super(fVar, null);
        }

        @Override // com.jingdong.manto.pkg.b.g
        protected <T> T a(String str, String str2, Class<T> cls) {
            return null;
        }
    }

    private g(com.jingdong.manto.f fVar) {
        PkgDetailEntity pkgDetailEntity;
        this.a = new HashMap();
        this.f13986c = Collections.unmodifiableCollection(Arrays.asList("page-frame.html"));
        this.d = new ConcurrentHashMap<>();
        if (fVar == null) {
            return;
        }
        if (TextUtils.isEmpty(fVar.r.f13088k)) {
            PkgDetailEntity pkgDetailEntity2 = fVar.f13016h;
            if (fVar.o && (pkgDetailEntity = fVar.p) != null) {
                pkgDetailEntity2 = pkgDetailEntity;
            }
            String pkgPath = PkgManager.getPkgPath(pkgDetailEntity2);
            i.d dVar = new i.d(pkgPath);
            this.b = dVar.a() ? new d(pkgPath, dVar) : null;
            return;
        }
        if (TextUtils.equals(fVar.r.f13082e, "14")) {
            i.d dVar2 = new i.d(fVar.r.f13088k);
            if (dVar2.a()) {
                r1 = new d(fVar.r.f13088k, dVar2);
            }
        } else {
            i.a aVar = new i.a(com.jingdong.manto.c.a());
            if (aVar.a()) {
                r1 = new d(fVar.r.f13088k, aVar);
            }
        }
        this.b = r1;
    }

    /* synthetic */ g(com.jingdong.manto.f fVar, a aVar) {
        this(fVar);
    }

    public static g a(com.jingdong.manto.f fVar) {
        g gVar;
        if (fVar == null) {
            return f13984f;
        }
        Map<com.jingdong.manto.f, g> map = f13985g;
        synchronized (map) {
            gVar = map.get(fVar);
            if (gVar == null) {
                gVar = new g(fVar);
                if (gVar.b != null) {
                    map.put(fVar, gVar);
                }
            }
        }
        return gVar;
    }

    public static String a(com.jingdong.manto.f fVar, String str, String str2) {
        g a2 = a(fVar);
        a2.a(fVar, str);
        return (String) a2.a(str2, str, String.class);
    }

    private void a(com.jingdong.manto.f fVar, String str) {
        PkgDetailEntity pkgDetailEntity;
        if (fVar == null || this.a.containsKey(str)) {
            return;
        }
        String str2 = null;
        if (TextUtils.equals(fVar.r.f13082e, "14")) {
            try {
                str2 = new JSONObject(fVar.r.f13089l).optString(str);
            } catch (Exception unused) {
            }
        } else {
            PkgDetailEntity pkgDetailEntity2 = fVar.f13016h;
            if (fVar.o && (pkgDetailEntity = fVar.p) != null) {
                pkgDetailEntity2 = pkgDetailEntity;
            }
            PkgManager.l subPkg = PkgManager.getSubPkg(pkgDetailEntity2, str);
            if (subPkg != null) {
                str2 = PkgManager.getPkgPath(pkgDetailEntity2, subPkg.a);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        MantoLog.i(f13983e, "pkgPath=" + str2);
        i.d dVar = new i.d(str2);
        if (dVar.a()) {
            this.a.put(str, new d(str2, dVar));
        }
    }

    public static JWebResourceResponse b(com.jingdong.manto.f fVar, String str, String str2) {
        g a2 = a(fVar);
        a2.a(fVar, str);
        return (JWebResourceResponse) a2.a(str2, str, JWebResourceResponse.class);
    }

    public static String b(com.jingdong.manto.f fVar, String str) {
        g a2 = a(fVar);
        if (a2.f13986c.contains(str)) {
            String str2 = a2.d.get(str);
            if (str2 != null) {
                return str2;
            }
            String str3 = (String) a2.a(str, (String) null, String.class);
            if (str3 != null) {
                a2.d.put(str, str3);
            }
            return str3;
        }
        return (String) a2.a(str, (String) null, String.class);
    }

    public static void b(com.jingdong.manto.f fVar) {
        Map<com.jingdong.manto.f, g> map = f13985g;
        synchronized (map) {
            if (map.get(fVar) != null) {
                map.remove(fVar);
            }
        }
    }

    public static JWebResourceResponse c(com.jingdong.manto.f fVar, String str) {
        return (JWebResourceResponse) a(fVar).a(str, (String) null, JWebResourceResponse.class);
    }

    public static InputStream d(com.jingdong.manto.f fVar, String str) {
        InputStream inputStream;
        g a2 = a(fVar);
        String str2 = fVar.f13014f.f14045g;
        if (str2 == null || str == null || !str.startsWith(str2)) {
            inputStream = null;
        } else {
            a2.a(fVar, str2);
            inputStream = (InputStream) a2.a(str, str2, InputStream.class);
        }
        return inputStream == null ? (InputStream) a2.a(str, (String) null, InputStream.class) : inputStream;
    }

    protected <T> T a(String str, String str2, Class<T> cls) {
        String a2;
        InputStream a3;
        boolean z = (MantoStringUtils.isEmpty(str) || z.a(str, "about:blank") || t.d(str)) ? false : true;
        d dVar = TextUtils.isEmpty(str2) ? this.b : this.a.get(str2);
        if (!z || dVar == null || (a3 = dVar.a((a2 = b.a(str)))) == null) {
            return null;
        }
        h hVar = h.d.a.get(cls);
        if (hVar != null) {
            return (T) hVar.a(a2, a3);
        }
        throw new RuntimeException("unsupported type: " + cls.getName());
    }
}
