package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.mapsdk.core.utils.cache.DiskCache;
import com.tencent.mapsdk.core.utils.cache.MemoryCache;
import com.tencent.mapsdk.internal.l9;
import com.tencent.tencentmap.mapsdk.maps.model.Tile;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TileProvider;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes9.dex */
public class jg extends ye implements TileOverlayCallback {
    private static final String J = "%s" + File.separatorChar + "%d-%d-%d";
    public static final String K = "/tile/";
    private static final long L = 1024;
    public int B;
    private Map<String, Integer> C;
    private og D;
    private TileOverlayOptions E;
    private l9<lg> F;
    private jb G;
    private BlockingQueue<Runnable> H;
    private final ob I;

    /* loaded from: classes9.dex */
    public class a extends ob {
        public a() {
            jg.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.ob, com.tencent.mapsdk.internal.ib
        public void c(String str) {
            Runnable c2;
            jb jbVar = jg.this.G;
            if (jbVar == null || (c2 = jbVar.c(str)) == null) {
                return;
            }
            jg.this.H.remove(c2);
        }
    }

    /* loaded from: classes9.dex */
    public static class b implements l9.b<lg> {
        private b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }

        @Override // com.tencent.mapsdk.internal.l9.b
        public boolean a(lg lgVar) {
            if (lgVar != null) {
                lgVar.h();
                return true;
            }
            return true;
        }
    }

    public jg(og ogVar, TileOverlayOptions tileOverlayOptions) {
        super((a1) ogVar.b().j());
        this.C = new Hashtable();
        this.I = new a();
        this.D = ogVar;
        this.E = tileOverlayOptions;
        if (tileOverlayOptions == null) {
            this.B = -1;
            return;
        }
        this.F = J();
        this.B = this.D.a(this, this.E.isBetterQuality());
        c(this.E.getZIndex());
    }

    private l9<lg> J() {
        TileOverlayOptions tileOverlayOptions;
        a aVar = null;
        if (this.D == null) {
            return null;
        }
        MemoryCache.a aVar2 = new MemoryCache.a();
        aVar2.a(this.E.getMaxMemoryCacheSize(this.D.b()));
        aVar2.a(new b(aVar));
        if (TextUtils.isEmpty(this.D.f16946f) || (tileOverlayOptions = this.E) == null || TextUtils.isEmpty(tileOverlayOptions.getDiskCacheDir())) {
            return p9.b(lg.class, aVar2);
        }
        DiskCache.d dVar = new DiskCache.d();
        if (!TextUtils.isEmpty(P())) {
            dVar.a(1024L);
        }
        String str = P() + this.E.getDiskCacheDir();
        dVar.a(new File(this.D.f16946f));
        dVar.a(str);
        dVar.a(-1);
        dVar.a(new kg());
        dVar.a(new mg(this.D.f16946f + File.separator + str));
        return p9.b(lg.class, aVar2, dVar);
    }

    private void clearCache() {
        l9<lg> l9Var = this.F;
        if (l9Var == null) {
            return;
        }
        if (l9Var instanceof o9) {
            l9 a2 = ((o9) l9Var).a(0);
            if (a2 instanceof MemoryCache) {
                a2.clear();
            }
            l9 a3 = ((o9) this.F).a(1);
            if (a3 instanceof DiskCache) {
                ((DiskCache) a3).m();
            }
        } else if (l9Var instanceof MemoryCache) {
            l9Var.clear();
        }
        this.C.clear();
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        if (this.D == null || this.B < 0) {
            return;
        }
        clearCache();
        synchronized (this) {
            jb jbVar = this.G;
            if (jbVar != null) {
                jbVar.a();
                this.G = null;
            }
        }
        this.D.c(this.B);
    }

    public ng K() {
        return new ng(this.D);
    }

    public synchronized jb L() {
        if (this.G == null) {
            jb jbVar = new jb();
            this.G = jbVar;
            jbVar.a(this.I);
            ThreadPoolExecutor b2 = g7.b();
            this.H = b2.getQueue();
            this.G.a(b2);
        }
        return this.G;
    }

    public int M() {
        return this.B;
    }

    public og N() {
        return this.D;
    }

    public TileProvider O() {
        return this.E.getTileProvider();
    }

    public String P() {
        return K;
    }

    public void a(String str) {
        if (this.E == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.E.diskCacheDir(str);
        this.F = J();
    }

    public byte[] a(int i2, int i3, int i4) {
        String str;
        TileOverlayOptions tileOverlayOptions = this.E;
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null || i4 < 0) {
            str = "\u65e0\u6548\u5750\u6807\uff0c\u8fd4\u56de\u7a7a\u74e6\u5757";
        } else {
            String format = String.format(J, fa.b(this.E.getVersionInfo()), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            Tile tile = this.E.getTileProvider().getTile(i2, i3, i4);
            if (tile != null) {
                byte[] bArr = tile.mData;
                if (bArr != null && bArr.length > 0) {
                    qa.a(la.b, "cacheId", (Object) format);
                    lg lgVar = new lg(bArr);
                    l9<lg> l9Var = this.F;
                    if (l9Var != null) {
                        s9 b2 = p9.b(l9Var);
                        if (b2 != null) {
                            b2.b(format, (String) lgVar);
                        } else {
                            this.F.a(format, (String) lgVar);
                        }
                    }
                }
                return bArr;
            }
            str = "Provider\u6ca1\u6709\u74e6\u7247\u6570\u636e\uff0c\u8fd4\u56de\u7a7a\u74e6\u5757";
        }
        ma.g(la.b, str);
        return a7.a();
    }

    public int b(int i2) {
        return i2 + 100;
    }

    public void b(int i2, int i3) {
        int i4;
        og ogVar = this.D;
        if (ogVar == null || (i4 = this.B) < 0) {
            return;
        }
        ogVar.a(i4, i2, i3);
    }

    public void c(int i2) {
        if (this.D == null || this.B < 0) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        this.D.a(this.B, b(i2));
    }

    public void clearTileCache() {
        l9<lg> l9Var = this.F;
        if (l9Var == null) {
            return;
        }
        l9Var.clear();
        this.C.clear();
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof jg) && this.B == ((jg) obj).B;
    }

    public int hashCode() {
        return y().hashCode();
    }

    @Override // com.tencent.map.lib.callbacks.TileOverlayCallback
    public Bitmap onLoadTile(int i2, int i3, int i4, byte[] bArr) {
        int i5;
        TileOverlayOptions tileOverlayOptions = this.E;
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        int i6 = 0;
        String format = String.format(J, fa.b(this.E.getVersionInfo()), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        int i7 = qa.i(la.b, "load-count");
        int g2 = qa.g(la.b, "cache-count");
        int g3 = qa.g(la.b, "data-count");
        int g4 = qa.g(la.b, "req-count");
        int g5 = qa.g(la.b, "cancel-count");
        lg lgVar = (lg) p9.b(this.F).b(format, lg.class);
        if (lgVar != null) {
            g2 = qa.i(la.b, "cache-count");
            i5 = lgVar.a();
            if (i7 == g4 + g3 + g2 + g5) {
                qa.j(la.b);
            }
        } else {
            i5 = 0;
        }
        qa.a("get from cache of cacheId:" + format, "dataLength:" + i5, "loadCount:" + i7, "reqCount:" + g4, "dataCount:" + g3, "cacheCount:" + g2, "cancelCount:" + g5);
        if (lgVar != null) {
            this.C.remove(format);
            lgVar.f();
            return lgVar.d();
        }
        Integer num = this.C.get(format);
        if (num != null && num.intValue() > 10) {
            Iterator<Map.Entry<String, Integer>> it = this.C.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue().intValue() > 10) {
                    i6++;
                }
                if (i6 > 50) {
                    ma.b("\u8d85\u8fc750\u4e2a\u74e6\u7247\u8bf7\u6c42\u5927\u4e8e10\u6b21\uff0c\u91cd\u65b0\u52a0\u8f7dTileOverlay");
                    reload();
                    return null;
                }
            }
            return null;
        }
        this.C.put(format, Integer.valueOf(num == null ? 0 : num.intValue() + 1));
        StringBuilder sb = new StringBuilder(128);
        sb.append(og.f16936g);
        sb.append("://");
        sb.append(og.f16937h);
        sb.append("/");
        sb.append(this.B);
        sb.append("?");
        sb.append("x=");
        sb.append(i2);
        sb.append("&y=");
        sb.append(i3);
        sb.append("&z=");
        sb.append(i4);
        byte[] bytes = sb.toString().getBytes();
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        return null;
    }

    @Override // com.tencent.map.lib.callbacks.TileOverlayCallback
    public void onLoadTileFinish(int i2, int i3, int i4) {
        lg lgVar = (lg) p9.b(this.F).b(String.format(J, fa.b(this.E.getVersionInfo()), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)), lg.class);
        if (lgVar != null) {
            lgVar.c();
        }
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.map.lib.callbacks.TileOverlayCallback
    public void onWriteTile(int i2, int i3, int i4, String str, byte[] bArr) {
    }

    public void reload() {
        if (this.D == null || this.B < 0) {
            return;
        }
        clearCache();
        this.D.b(this.B);
        BlockingQueue<Runnable> blockingQueue = this.H;
        if (blockingQueue != null) {
            blockingQueue.clear();
        }
    }

    @Override // com.tencent.mapsdk.internal.v0
    public p0 x() {
        return null;
    }
}
