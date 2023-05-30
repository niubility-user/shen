package com.tencent.mapsdk.internal;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class og {

    /* renamed from: g  reason: collision with root package name */
    public static final String f16936g = "tileOverlay";

    /* renamed from: h  reason: collision with root package name */
    public static final String f16937h = "getTile";

    /* renamed from: i  reason: collision with root package name */
    private static final String f16938i = "x";

    /* renamed from: j  reason: collision with root package name */
    private static final String f16939j = "y";

    /* renamed from: k  reason: collision with root package name */
    private static final String f16940k = "z";

    /* renamed from: l  reason: collision with root package name */
    public static final int f16941l = 1;

    /* renamed from: m  reason: collision with root package name */
    public static final int f16942m = 2;

    /* renamed from: n  reason: collision with root package name */
    public static final int f16943n = 100;
    private qi a;
    private Map<Integer, jg> b = new Hashtable(4);

    /* renamed from: c  reason: collision with root package name */
    private qg f16944c;
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private qc f16945e;

    /* renamed from: f  reason: collision with root package name */
    public String f16946f;

    public og(Context context, qc qcVar) {
        this.d = context;
        this.f16945e = qcVar;
        this.a = qcVar.S();
        this.f16946f = lc.b(context).c().getPath();
    }

    private int a(Uri uri) {
        String lastPathSegment;
        if (uri == null || (lastPathSegment = uri.getLastPathSegment()) == null) {
            return -1;
        }
        try {
            return Integer.parseInt(lastPathSegment);
        } catch (NumberFormatException e2) {
            ma.b(Log.getStackTraceString(e2));
            return -1;
        }
    }

    private int a(Uri uri, String str) {
        if (uri == null) {
            return -1;
        }
        try {
            return Integer.parseInt(uri.getQueryParameter(str));
        } catch (NumberFormatException e2) {
            ma.b(Log.getStackTraceString(e2));
            return -1;
        }
    }

    public static void a(Context context) {
        File[] listFiles;
        if (context == null) {
            return;
        }
        File file = new File(lc.b(context).c().getPath() + jg.K);
        if (!file.exists() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        for (File file2 : listFiles) {
            if (currentTimeMillis - file2.lastModified() > TimeUnit.DAYS.toMillis(7L)) {
                fa.d(file2);
            }
        }
    }

    public int a(TileOverlayCallback tileOverlayCallback, boolean z) {
        if (this.a == null) {
            return -1;
        }
        qa.d(la.b, "\u5185\u90e8addTileOverlay");
        return this.a.a(tileOverlayCallback, z);
    }

    public Context a() {
        return this.d;
    }

    public jg a(int i2) {
        if (i2 >= 0) {
            return this.b.get(Integer.valueOf(i2));
        }
        return null;
    }

    public TileOverlay a(TileOverlayOptions tileOverlayOptions) {
        jg b = b(tileOverlayOptions);
        qa.h(la.b);
        return new z0(b);
    }

    public void a(int i2, int i3) {
        qi qiVar = this.a;
        if (qiVar == null) {
            return;
        }
        qiVar.f(i2, i3);
    }

    public void a(int i2, int i3, int i4) {
        qi qiVar = this.a;
        if (qiVar == null) {
            return;
        }
        qiVar.b(i2, i3, i4);
    }

    public void a(jg jgVar) {
        int i2;
        if (jgVar == null || (i2 = jgVar.B) <= 0) {
            return;
        }
        this.b.put(Integer.valueOf(i2), jgVar);
    }

    public void a(boolean z) {
        this.a.p(z);
    }

    public byte[] a(String str) {
        int a;
        jg jgVar;
        try {
            Uri parse = Uri.parse(str);
            if (!e7.c(parse.getAuthority(), f16937h) || (a = a(parse)) == -1 || (jgVar = this.b.get(Integer.valueOf(a))) == null) {
                return null;
            }
            return jgVar.a(a(parse, "x"), a(parse, "y"), a(parse, f16940k));
        } catch (Exception e2) {
            ma.b(Log.getStackTraceString(e2));
            return null;
        }
    }

    public jg b(TileOverlayOptions tileOverlayOptions) {
        if (this.f16944c == null) {
            this.f16944c = new qg(this);
        }
        return this.f16944c.a(tileOverlayOptions);
    }

    public qc b() {
        return this.f16945e;
    }

    public void b(int i2) {
        qi qiVar = this.a;
        if (qiVar == null) {
            return;
        }
        qiVar.d(i2);
    }

    public void c(int i2) {
        if (this.a == null) {
            return;
        }
        this.b.remove(Integer.valueOf(i2));
        this.a.g(i2);
        qa.i(la.b);
    }

    public boolean c() {
        return this.a.Q();
    }

    public void d() {
        Map<Integer, jg> map = this.b;
        if (map == null || map.isEmpty()) {
            return;
        }
        for (jg jgVar : (jg[]) this.b.values().toArray(new jg[this.b.keySet().size()])) {
            jgVar.remove();
        }
    }
}
