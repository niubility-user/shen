package com.tencent.mapsdk.internal;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.json.JsonUtils;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.ba;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class uh {

    /* renamed from: h */
    private ci f17347h;

    /* renamed from: i */
    private boolean f17348i;

    /* renamed from: l */
    private OverSeaTileProvider f17351l;
    private int a = bi.f16347e;
    private int b = 1000;

    /* renamed from: c */
    private int f17343c = bi.f16349g;
    private int d = 0;

    /* renamed from: e */
    private volatile boolean f17344e = false;

    /* renamed from: f */
    private int f17345f = 0;

    /* renamed from: g */
    private String f17346g = null;

    /* renamed from: j */
    private OverSeaSource f17349j = OverSeaSource.DEFAULT;

    /* renamed from: k */
    private Language f17350k = Language.zh;

    /* loaded from: classes9.dex */
    public class a extends ba.c<Boolean> {
        public final /* synthetic */ Callback a;

        public a(Callback callback) {
            uh.this = r1;
            this.a = callback;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(Boolean bool) {
            this.a.callback(bool);
        }
    }

    /* loaded from: classes9.dex */
    public class b extends ba.i<Boolean> {
        public final /* synthetic */ Context b;

        /* renamed from: c */
        public final /* synthetic */ OverSeaSource f17352c;

        public b(Context context, OverSeaSource overSeaSource) {
            uh.this = r1;
            this.b = context;
            this.f17352c = overSeaSource;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() {
            ma.c(la.f16821h, "\u5f00\u59cb\u521d\u59cb\u5316\u914d\u7f6e");
            String a = uh.this.a(this.b, this.f17352c);
            ma.c(la.f16821h, "\u672c\u5730\u914d\u7f6e\u6570\u636e\uff1a" + a);
            if (!e7.b(a)) {
                try {
                    uh.this.f17347h = (ci) JsonUtils.parseToModel(new JSONObject(a), ci.class, new Object[0]);
                } catch (JSONException e2) {
                    ma.b(la.f16821h, e2);
                }
                uh uhVar = uh.this;
                uhVar.a(uhVar.f17347h);
            } else if (x9.c("4.5.10", "4.3.1", 3)) {
                uh.this.b(this.b);
            }
            ma.c(la.f16821h, "\u5b8c\u6210\u521d\u59cb\u5316\u914d\u7f6e");
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            OverSeaSource.values();
            int[] iArr = new int[2];
            a = iArr;
            try {
                OverSeaSource overSeaSource = OverSeaSource.DEFAULT;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                OverSeaSource overSeaSource2 = OverSeaSource.SPARE;
                iArr2[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private xh a(bi biVar) {
        List<xh> a2;
        if (biVar == null || (a2 = biVar.a()) == null) {
            return null;
        }
        for (xh xhVar : a2) {
            if (xhVar.a() == 2 && this.f17348i) {
                return xhVar;
            }
            if (xhVar.a() == 1 && !this.f17348i) {
                return xhVar;
            }
        }
        return null;
    }

    public String a(Context context, OverSeaSource overSeaSource) {
        String str;
        kc a2 = kc.a(context);
        int ordinal = overSeaSource.ordinal();
        if (ordinal == 0) {
            str = l4.f16793g;
        } else if (ordinal != 1) {
            return null;
        } else {
            str = "worldMapConfig_BING";
        }
        return a2.d(str);
    }

    private void a(Context context, OverSeaSource overSeaSource, String str) {
        String str2;
        kc a2 = kc.a(context);
        int ordinal = overSeaSource.ordinal();
        if (ordinal == 0) {
            str2 = l4.f16793g;
        } else if (ordinal != 1) {
            return;
        } else {
            str2 = "worldMapConfig_BING";
        }
        a2.b(str2, str);
    }

    private void a(Context context, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        ma.c(la.f16821h, "\u4e0b\u8f7d\u65b0\u8fb9\u754c\u6570\u636e\uff1a" + str);
        try {
            NetResponse doStream = NetManager.getInstance().builder().gzip().url(str).doStream();
            InputStream inputStream = doStream.getInputStream();
            if (doStream.statusCode == 200) {
                String str2 = TextUtils.isEmpty(doStream.contentEncoding) ? "gzip" : doStream.contentEncoding;
                byte[] a2 = str2 != null && str2.length() > 0 && str2.toLowerCase().contains("gzip") ? ia.a(inputStream) : ga.b(inputStream);
                if (a2 == null || a2.length <= 0) {
                    return;
                }
                String str3 = new String(a2);
                this.f17345f = sh.b().d(str3);
                ma.c(la.f16821h, "\u65b0\u8fb9\u754c\u6570\u636e\u7248\u672c\u53f7\uff1a" + this.f17345f);
                sh.b().e(str3);
            }
        } catch (Throwable th) {
            ma.b(la.f16821h, th);
        }
    }

    public void a(ci ciVar) {
        if (ciVar == null) {
            return;
        }
        bi b2 = ciVar.b();
        if (b2 != null) {
            this.d = b2.c();
            ma.c(la.f16821h, "\u66f4\u65b0\u7248\u672c\uff1a" + this.d);
            ai b3 = b2.b();
            if (b3 != null) {
                this.f17345f = b3.b();
                ma.c(la.f16821h, "\u66f4\u65b0\u8fb9\u754c\u7248\u672c\uff1a" + this.f17343c);
            }
        }
        di c2 = c(b2);
        if (c2 != null) {
            this.b = c2.d();
            this.a = c2.c();
            this.f17343c = c2.f();
            this.f17346g = c2.e();
            ma.c(la.f16821h, "\u66f4\u65b0\u56fe\u6e90\u7248\u672c\uff1a" + this.f17343c);
        }
        this.f17344e = ciVar.a() == 0;
    }

    private List<yh> b(bi biVar) {
        if (biVar != null) {
            return biVar.d();
        }
        return null;
    }

    private di c(bi biVar) {
        xh a2;
        if (biVar == null || (a2 = a(biVar)) == null) {
            return null;
        }
        return a2.b();
    }

    public int a() {
        return this.f17345f;
    }

    public File a(Context context) {
        return new File(lc.b(context).c(), g());
    }

    public void a(Context context, OverSeaSource overSeaSource, Callback<Boolean> callback) {
        if (context == null) {
            return;
        }
        this.f17349j = overSeaSource;
        ba.a((ba.i) new b(context, overSeaSource)).a((ba.d.b) Boolean.FALSE, (ba.c<ba.d.b>) (callback != null ? new a(callback) : null));
    }

    public void a(Language language) {
        this.f17350k = language;
    }

    public void a(OverSeaTileProvider overSeaTileProvider) {
        this.f17351l = overSeaTileProvider;
    }

    public void a(boolean z) {
        ma.c(la.f16821h, "\u4f7f\u7528\u6d77\u5916\u6697\u8272\u6a21\u5f0f\uff1f" + z);
        this.f17348i = z;
    }

    public Language b() {
        return this.f17350k;
    }

    /* JADX WARN: Removed duplicated region for block: B:91:0x00b2 A[Catch: JSONException -> 0x00c1, TRY_LEAVE, TryCatch #0 {JSONException -> 0x00c1, blocks: (B:89:0x00ac, B:91:0x00b2), top: B:101:0x00ac }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void b(Context context) {
        String str;
        String d;
        kc a2 = kc.a(context);
        ma.c(la.f16821h, "\u517c\u5bb9\u8001\u6570\u636e");
        int a3 = a2.a(l4.f16795i, 1000);
        int a4 = a2.a(l4.f16796j, bi.f16347e);
        int a5 = a2.a(l4.f16797k, bi.f16349g);
        int a6 = a2.a(l4.f16799m, 0);
        boolean a7 = a2.a(l4.f16794h);
        String d2 = a2.d(l4.f16800n);
        int[] iArr = new int[0];
        try {
            String d3 = a2.d(l4.o);
            if (e7.b(d3)) {
                str = l4.o;
            } else {
                JSONArray jSONArray = new JSONArray(d3);
                int length = jSONArray.length();
                iArr = new int[length];
                str = l4.o;
                for (int i2 = 0; i2 < length; i2++) {
                    try {
                        iArr[i2] = jSONArray.getInt(i2);
                    } catch (Exception e2) {
                        e = e2;
                        ma.b(la.f16821h, e);
                        d = a2.d(l4.p);
                        int a8 = a2.a(l4.f16798l, 0);
                        xh xhVar = new xh();
                        xhVar.a(1);
                        di diVar = new di();
                        diVar.b(d2);
                        diVar.a(iArr);
                        diVar.a(a4);
                        diVar.b(a3);
                        diVar.c(a5);
                        xhVar.a(diVar);
                        bi biVar = new bi();
                        biVar.a(a6);
                        List<yh> list = null;
                        if (!e7.b(d)) {
                        }
                        biVar.b(list);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(xhVar);
                        biVar.a(arrayList);
                        ai aiVar = new ai();
                        aiVar.a(a8);
                        biVar.a(aiVar);
                        ci ciVar = new ci();
                        this.f17347h = ciVar;
                        ciVar.a(!a7 ? 0 : -1);
                        this.f17347h.a(biVar);
                        String jSONObject = this.f17347h.toJson().toString();
                        ma.c(la.f16821h, "\u8001\u6570\u636e\uff1a" + jSONObject);
                        a2.b(l4.f16793g, jSONObject);
                        a2.a(new String[]{l4.f16795i, l4.f16796j, l4.f16797k, l4.f16799m, l4.f16794h, l4.f16800n, str, l4.p, l4.f16798l});
                        a(this.f17347h);
                    }
                }
            }
        } catch (Exception e3) {
            e = e3;
            str = l4.o;
        }
        d = a2.d(l4.p);
        int a82 = a2.a(l4.f16798l, 0);
        xh xhVar2 = new xh();
        xhVar2.a(1);
        di diVar2 = new di();
        diVar2.b(d2);
        diVar2.a(iArr);
        diVar2.a(a4);
        diVar2.b(a3);
        diVar2.c(a5);
        xhVar2.a(diVar2);
        bi biVar2 = new bi();
        biVar2.a(a6);
        List<yh> list2 = null;
        try {
            if (!e7.b(d)) {
                list2 = JsonUtils.parseToList(new JSONArray(d), yh.class, new Object[0]);
            }
        } catch (JSONException e4) {
            ma.b(la.f16821h, e4);
        }
        biVar2.b(list2);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(xhVar2);
        biVar2.a(arrayList2);
        ai aiVar2 = new ai();
        aiVar2.a(a82);
        biVar2.a(aiVar2);
        ci ciVar2 = new ci();
        this.f17347h = ciVar2;
        ciVar2.a(!a7 ? 0 : -1);
        this.f17347h.a(biVar2);
        String jSONObject2 = this.f17347h.toJson().toString();
        ma.c(la.f16821h, "\u8001\u6570\u636e\uff1a" + jSONObject2);
        a2.b(l4.f16793g, jSONObject2);
        a2.a(new String[]{l4.f16795i, l4.f16796j, l4.f16797k, l4.f16799m, l4.f16794h, l4.f16800n, str, l4.p, l4.f16798l});
        a(this.f17347h);
    }

    public boolean b(Context context, String str) {
        bi b2;
        boolean z;
        ma.c(la.f16821h, "\u5f00\u59cb\u66f4\u65b0\u914d\u7f6e\uff1a" + str);
        ci ciVar = (ci) JsonUtils.parseToModel(str, ci.class, new Object[0]);
        if (ciVar == null || (b2 = ciVar.b()) == null) {
            ma.c(la.f16821h, "\u914d\u7f6e\u66f4\u65b0\u6570\u636e\u89e3\u6790\u5931\u8d25\uff0c\u4f7f\u7528\u4e0a\u6b21\u7684\u914d\u7f6e");
            return false;
        }
        if (ciVar.a() != 0) {
            z = this.f17344e;
            this.f17344e = false;
        } else {
            z = !this.f17344e;
            this.f17344e = true;
        }
        ma.c(la.f16821h, "\u6743\u9650\u662f\u5426\u66f4\u65b0\uff1a" + z);
        boolean z2 = b2.c() != this.d;
        ma.c(la.f16821h, "\u534f\u8bae\u7248\u672c\u662f\u5426\u66f4\u65b0\uff1a" + z2);
        if (!z && !z2) {
            return false;
        }
        xh a2 = a(b2);
        if (a2 != null) {
            int a3 = a2.a();
            di b3 = a2.b();
            if (b3 != null) {
                int f2 = b3.f();
                int d = b3.d();
                ma.c(la.f16821h, "\u7248\u672c\u5bf9\u6bd4: old[" + this.f17343c + "]-new[" + f2 + "]");
                ma.c(la.f16821h, "\u6837\u5f0f\u5bf9\u6bd4: old[" + this.b + "]-new[" + d + "]");
                if (f2 != this.f17343c || d != this.b || a3 != this.a) {
                    File a4 = a(context);
                    if (a4.exists()) {
                        fa.d(a4);
                        ma.c(la.f16821h, "\u5220\u9664\u6d77\u5916\u56fe\u7f13\u5b58\u76ee\u5f55: " + a4);
                    }
                }
            }
        }
        ai b4 = b2.b();
        if (b4 != null) {
            String a5 = b4.a();
            ma.c(la.f16821h, "\u914d\u7f6e\u8fb9\u754c\u7ebf: " + a5);
            a(context, a5);
            b4.a(this.f17345f);
        }
        this.f17347h = ciVar;
        a(context, this.f17349j, str);
        a(this.f17347h);
        ma.c(la.f16821h, "\u914d\u7f6e\u66f4\u65b0\u5b8c\u6210");
        return true;
    }

    public OverSeaTileProvider c() {
        return this.f17351l;
    }

    public OverSeaSource d() {
        return this.f17349j;
    }

    public int e() {
        int ordinal = this.f17349j.ordinal();
        if (ordinal != 0) {
            return ordinal != 1 ? 0 : 1;
        }
        return 2;
    }

    public String f() {
        if (this.f17351l != null) {
            return this.f17351l.getProviderVersion() + File.separator + this.f17350k.name();
        }
        di k2 = k();
        if (k2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(k2.c());
            String str = File.separator;
            sb.append(str);
            sb.append(k2.d());
            sb.append(str);
            sb.append(k2.f());
            sb.append(str);
            sb.append(this.f17350k.name());
            return sb.toString();
        }
        return "";
    }

    public String g() {
        String str;
        OverSeaTileProvider overSeaTileProvider = this.f17351l;
        boolean z = true;
        if (overSeaTileProvider != null) {
            z = overSeaTileProvider.onDayNightChange(this.f17348i);
            str = "rastermap/customoversea/" + this.f17351l.getProviderName();
        } else if (this.f17349j.ordinal() != 1) {
            str = "rastermap/world";
        } else {
            z = false;
            str = "rastermap/bingworld";
        }
        return str + ((this.f17348i && z) ? "/dark" : "");
    }

    public List<yh> h() {
        ci ciVar = this.f17347h;
        if (ciVar == null) {
            return null;
        }
        if (this.f17351l != null) {
            ArrayList arrayList = new ArrayList(b(this.f17347h.b()));
            yh yhVar = new yh();
            yhVar.a(new int[]{0, 18});
            ArrayList arrayList2 = new ArrayList();
            zh zhVar = new zh();
            zhVar.a(sh.f17255i);
            zhVar.b(true);
            zhVar.b(1);
            zhVar.b(this.f17351l.getProviderName());
            zhVar.a(this.f17351l.getLogo(true));
            zhVar.b(this.f17351l.getLogo(false));
            arrayList2.add(zhVar);
            yhVar.a(arrayList2);
            arrayList.add(0, yhVar);
            return arrayList;
        }
        return b(ciVar.b());
    }

    public ai i() {
        bi b2;
        ci ciVar = this.f17347h;
        if (ciVar == null || (b2 = ciVar.b()) == null) {
            return null;
        }
        return b2.b();
    }

    public int j() {
        return this.d;
    }

    public di k() {
        ci ciVar = this.f17347h;
        if (ciVar == null) {
            return null;
        }
        return c(ciVar.b());
    }

    public boolean l() {
        return this.f17348i;
    }

    public boolean m() {
        return this.f17344e;
    }
}
