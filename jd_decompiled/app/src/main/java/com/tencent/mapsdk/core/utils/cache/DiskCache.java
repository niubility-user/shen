package com.tencent.mapsdk.core.utils.cache;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import com.tencent.mapsdk.internal.fa;
import com.tencent.mapsdk.internal.l9;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.m9;
import com.tencent.mapsdk.internal.n9;
import com.tencent.mapsdk.internal.qa;
import com.tencent.mapsdk.internal.r9;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public final class DiskCache<D extends m9> extends r9<D> {

    /* renamed from: k  reason: collision with root package name */
    private static final String f16199k = "DiskCache";

    /* renamed from: l  reason: collision with root package name */
    private static final String f16200l = ".disk_idx";

    /* renamed from: m  reason: collision with root package name */
    private static final String f16201m = ".disk_idx_root";

    /* renamed from: n  reason: collision with root package name */
    private static final b f16202n = new a();
    private n9.a<c> d;

    /* renamed from: e  reason: collision with root package name */
    private d f16203e;

    /* renamed from: f  reason: collision with root package name */
    private File f16204f;

    /* renamed from: g  reason: collision with root package name */
    private File f16205g;

    /* renamed from: h  reason: collision with root package name */
    private Map<String, String> f16206h;

    /* renamed from: i  reason: collision with root package name */
    private List<String> f16207i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f16208j;

    /* loaded from: classes9.dex */
    public static class a implements b {
        @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
        public File a(String str, String str2, byte[] bArr) {
            File file = new File(str2, str);
            fa.b(file, bArr);
            return file;
        }

        @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
        public byte[] a(String str, File file) {
            return fa.h(file);
        }

        @Override // com.tencent.mapsdk.core.utils.cache.DiskCache.b
        public boolean b(String str, File file) {
            return fa.d(file);
        }
    }

    /* loaded from: classes9.dex */
    public interface b {
        File a(String str, String str2, byte[] bArr);

        byte[] a(String str, File file);

        boolean b(String str, File file);
    }

    /* loaded from: classes9.dex */
    public static final class c extends m9 {
        private File a;
        private int b;

        public c(File file, int i2) {
            this.a = file;
            this.b = i2;
        }

        @Override // com.tencent.mapsdk.internal.m9
        public int a() {
            return this.b;
        }

        @Override // com.tencent.mapsdk.internal.m9
        public void a(byte[] bArr) {
        }

        @Override // com.tencent.mapsdk.internal.m9
        public byte[] b() {
            return new byte[this.b];
        }

        public String toString() {
            return this.a.getName() + DYConstants.DY_REGEX_COMMA + this.b;
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends r9.d {

        /* renamed from: k  reason: collision with root package name */
        public static final long f16209k = -1;

        /* renamed from: e  reason: collision with root package name */
        private File f16210e;

        /* renamed from: f  reason: collision with root package name */
        private String f16211f;

        /* renamed from: g  reason: collision with root package name */
        private b f16212g;

        /* renamed from: h  reason: collision with root package name */
        private long f16213h;

        /* renamed from: i  reason: collision with root package name */
        private final l9.b<c> f16214i;

        /* renamed from: j  reason: collision with root package name */
        private l9.b<File> f16215j;

        /* loaded from: classes9.dex */
        public class a implements l9.b<c> {
            public a() {
            }

            @Override // com.tencent.mapsdk.internal.l9.b
            public boolean a(c cVar) {
                if (cVar != null) {
                    boolean a = d.this.f16215j != null ? d.this.f16215j.a(cVar.a) : false;
                    if (a) {
                        return a;
                    }
                    fa.d(cVar.a);
                    return true;
                }
                return false;
            }
        }

        public d() {
            super(r9.b.DISK);
            this.f16210e = fa.f16514e;
            this.f16211f = "tmp";
            this.f16212g = DiskCache.f16202n;
            this.f16213h = -1L;
            this.f16214i = new a();
        }

        public d(String str) {
            super(r9.b.DISK);
            this.f16210e = fa.f16514e;
            this.f16211f = "tmp";
            this.f16212g = DiskCache.f16202n;
            this.f16213h = -1L;
            this.f16214i = new a();
            this.f16211f = str;
        }

        public d a(long j2) {
            this.f16213h = j2;
            return this;
        }

        public d a(b bVar) {
            this.f16212g = bVar;
            return this;
        }

        public d a(l9.b<File> bVar) {
            this.f16215j = bVar;
            return this;
        }

        public d a(File file) {
            this.f16210e = file;
            return this;
        }

        public d a(String str) {
            this.f16211f = str;
            return this;
        }

        public File d() {
            return new File(this.f16210e, this.f16211f);
        }

        @Override // com.tencent.mapsdk.internal.r9.d
        public String toString() {
            return "Options{mCacheDirectory=" + this.f16210e + ", mCacheName='" + this.f16211f + "', fileAccessStrategy=" + this.f16212g + "} " + super.toString();
        }
    }

    @Keep
    public DiskCache(d dVar) {
        super(dVar);
        this.f16203e = dVar;
        if (dVar != null) {
            this.f16204f = fa.a(dVar.f16210e, this.f16203e.f16211f);
            boolean z = this.f16203e.a() == -1;
            this.f16208j = z;
            if (!z) {
                this.d = new n9.a<>(this.f16203e.a(), this.f16203e.f16214i);
            }
            l();
        }
    }

    private void a(String str, c cVar) {
        if (cVar == null || cVar.a == null) {
            return;
        }
        File parentFile = cVar.a.getParentFile();
        File b2 = fa.b(parentFile, f16200l);
        String str2 = str + "#" + cVar.toString();
        if (fa.d(b2, str2) == -1) {
            qa.g(f16199k).a("index writeLine data:" + str2);
            fa.e(b2, str2);
        }
        int d2 = fa.d(this.f16205g, parentFile.getAbsolutePath());
        if (d2 != -1) {
            String str3 = DYConstants.DY_REGEX_COMMA + str;
            String b3 = fa.b(this.f16205g, d2);
            if (b3 != null && !b3.contains(str)) {
                qa.g(f16199k).a("root writeAppend data:" + str3);
                fa.b(this.f16205g, d2, DYConstants.DY_REGEX_COMMA + str);
            }
        } else {
            String str4 = parentFile.getAbsolutePath() + "#" + str;
            qa.g(f16199k).a("root writeLine data:" + str4);
            fa.e(this.f16205g, str4);
        }
        this.f16206h.put(str, parentFile.getAbsolutePath());
    }

    private void c(String str) {
        String b2;
        String str2 = this.f16206h.get(str);
        if (str2 != null) {
            qa.g(f16199k).a("key\uff1a" + str, "dir : " + str2);
            File file = new File(new File(str2), f16200l);
            int d2 = fa.d(file, str);
            if (d2 != -1) {
                fa.a(file, d2);
            }
            int d3 = fa.d(this.f16205g, str2);
            if (d3 == -1 || (b2 = fa.b(this.f16205g, d3)) == null || !b2.contains(str)) {
                return;
            }
            fa.a(this.f16205g, d3, b2.replaceAll(str + DYConstants.DY_REGEX_COMMA, ""));
        }
    }

    private void d(String str) {
        String str2 = this.f16206h.get(str);
        if (str2 == null || this.f16207i.contains(str2)) {
            return;
        }
        qa.g(f16199k).a("key\uff1a" + str, "dir : " + str2);
        List<String> g2 = fa.g(fa.b(new File(str2), f16200l));
        if (g2 == null || g2.isEmpty()) {
            return;
        }
        qa.g(f16199k).a(g2.toArray());
        if (this.d != null) {
            Iterator<String> it = g2.iterator();
            while (it.hasNext()) {
                String[] split = it.next().split("#");
                String[] split2 = split[1].split(DYConstants.DY_REGEX_COMMA);
                this.d.a((n9.a<c>) split[0], (String) new c(new File(str2, split2[0]), Integer.parseInt(split2[1])));
            }
        }
        if (g2.size() > 0) {
            this.f16207i.add(str2);
        }
    }

    private void l() {
        this.f16205g = fa.b(this.f16204f, f16201m);
        this.f16207i = new ArrayList();
        this.f16206h = new HashMap();
        List<String> g2 = fa.g(this.f16205g);
        if (g2 != null) {
            for (String str : g2) {
                if (str.length() > 0) {
                    String[] split = str.split("#");
                    if (split.length > 1) {
                        for (String str2 : split[1].split(DYConstants.DY_REGEX_COMMA)) {
                            this.f16206h.put(str2, split[0]);
                        }
                    }
                }
            }
        }
        qa.a("loadRootIndex count:" + this.f16206h.size(), "disk_cache_dir:" + this.f16204f);
    }

    @Override // com.tencent.mapsdk.internal.l9, com.tencent.mapsdk.internal.s9
    public long a() {
        if (this.f16208j) {
            return -1L;
        }
        return this.d.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.tencent.mapsdk.internal.l9
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public D a(java.lang.String r10, java.lang.Class<D> r11) {
        /*
            r9 = this;
            java.lang.String r0 = " must have a empty construct. #"
            java.lang.String r1 = "The "
            com.tencent.mapsdk.core.utils.cache.DiskCache$d r2 = r9.f16203e
            com.tencent.mapsdk.internal.r9$c r2 = r2.c()
            java.lang.String r2 = r2.a(r10)
            boolean r3 = r9.f16208j
            r4 = 0
            if (r3 == 0) goto L1f
            com.tencent.mapsdk.core.utils.cache.DiskCache$d r3 = r9.f16203e
            long r5 = com.tencent.mapsdk.core.utils.cache.DiskCache.d.e(r3)
            r7 = -1
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L35
        L1f:
            r9.d(r2)
            boolean r3 = r9.f16208j
            if (r3 != 0) goto L35
            com.tencent.mapsdk.internal.n9$a<com.tencent.mapsdk.core.utils.cache.DiskCache$c> r3 = r9.d
            java.lang.Object r3 = r3.b(r2)
            com.tencent.mapsdk.core.utils.cache.DiskCache$c r3 = (com.tencent.mapsdk.core.utils.cache.DiskCache.c) r3
            if (r3 == 0) goto L35
            java.io.File r3 = com.tencent.mapsdk.core.utils.cache.DiskCache.c.a(r3)
            goto L36
        L35:
            r3 = r4
        L36:
            com.tencent.mapsdk.core.utils.cache.DiskCache$d r5 = r9.f16203e
            com.tencent.mapsdk.core.utils.cache.DiskCache$b r5 = com.tencent.mapsdk.core.utils.cache.DiskCache.d.d(r5)
            byte[] r2 = r5.a(r2, r3)
            if (r2 == 0) goto L95
            java.lang.Object r3 = r11.newInstance()     // Catch: java.lang.InstantiationException -> L4d java.lang.IllegalAccessException -> L71
            r4 = r3
            com.tencent.mapsdk.internal.m9 r4 = (com.tencent.mapsdk.internal.m9) r4     // Catch: java.lang.InstantiationException -> L4d java.lang.IllegalAccessException -> L71
            r4.a(r2)     // Catch: java.lang.InstantiationException -> L4d java.lang.IllegalAccessException -> L71
            goto L95
        L4d:
            r10 = move-exception
            java.lang.Error r2 = new java.lang.Error
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r11 = r11.getSimpleName()
            r3.append(r11)
            r3.append(r0)
            java.lang.String r11 = r10.getMessage()
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r2.<init>(r11, r10)
            throw r2
        L71:
            r10 = move-exception
            java.lang.Error r2 = new java.lang.Error
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r11 = r11.getSimpleName()
            r3.append(r11)
            r3.append(r0)
            java.lang.String r11 = r10.getMessage()
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r2.<init>(r11, r10)
            throw r2
        L95:
            if (r2 != 0) goto L99
            r11 = 0
            goto L9a
        L99:
            int r11 = r2.length
        L9a:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.String r0 = "DC"
            java.lang.String r1 = "get data length"
            com.tencent.mapsdk.internal.qa.a(r0, r10, r1, r11)
            com.tencent.mapsdk.internal.qa.f(r0, r10)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.core.utils.cache.DiskCache.a(java.lang.String, java.lang.Class):com.tencent.mapsdk.internal.m9");
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void a(String str, D d2) {
        if (TextUtils.isEmpty(str) || d2 == null) {
            return;
        }
        qa.a(la.p, str, "put");
        String a2 = this.f16203e.c().a(str);
        byte[] b2 = d2.b();
        if (b2 != null) {
            File a3 = this.f16203e.f16212g.a(a2, this.f16204f.getAbsolutePath(), b2);
            if (!this.f16208j || this.f16203e.f16213h != -1) {
                c cVar = new c(a3, b2.length);
                if (!this.f16208j) {
                    this.d.a((n9.a<c>) a2, (String) cVar);
                }
                a(a2, cVar);
            }
        }
        qa.a(la.p, str, "put data length", Integer.valueOf(b2 == null ? 0 : b2.length));
    }

    @Override // com.tencent.mapsdk.internal.l9
    public boolean b(String str) {
        String a2 = this.f16203e.c().a(str);
        if (!this.f16208j || this.f16203e.f16213h != -1) {
            d(a2);
            if (!this.f16208j) {
                c cVar = (c) this.d.b((n9.a<c>) a2);
                r1 = cVar != null ? cVar.a : null;
                if (r1 != null && r1.exists()) {
                    this.d.c(a2);
                }
            }
            if (r1 != null && r1.exists()) {
                c(a2);
            }
        }
        return this.f16203e.f16212g.b(a2, r1);
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void clear() {
        if (this.f16204f != null) {
            if (!this.f16208j) {
                this.d.b();
            }
            this.f16203e.f16212g.b(null, this.f16204f);
        }
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long f() {
        if (this.f16208j) {
            return -1L;
        }
        return this.d.h();
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long getCount() {
        Map i2;
        if (this.f16208j) {
            d dVar = this.f16203e;
            if (dVar == null || dVar.f16213h == -1) {
                return -1L;
            }
            i2 = this.f16206h;
        } else {
            i2 = this.d.i();
        }
        return i2.size();
    }

    @Override // com.tencent.mapsdk.internal.r9
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public d i() {
        return this.f16203e;
    }

    public void m() {
        d dVar = this.f16203e;
        if (dVar == null || dVar.f16213h == -1 || this.f16206h.size() <= this.f16203e.f16213h) {
            return;
        }
        qa.a("cached tile count:" + this.f16206h.size());
        String str = "cached tile count:" + this.f16206h.size();
        clear();
    }
}
