package com.jingdong.aura.a.b.l;

import android.os.Build;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class b implements a {

    /* renamed from: c */
    private static final com.jingdong.aura.core.util.l.b f12122c = com.jingdong.aura.core.util.l.c.a("BundleArchive");
    private final c a;
    private final SortedMap<Long, c> b;

    public b(String str, File file, long j2) {
        SortedMap<Long, c> e2 = h.e(file);
        this.b = e2;
        if (e2 != null && !e2.isEmpty()) {
            long longValue = e2.lastKey().longValue();
            if (j2 > 0 && e2.containsKey(Long.valueOf(j2))) {
                longValue = j2;
            }
            c cVar = new c(str, longValue, new File(file, "package_" + String.valueOf(longValue)));
            if (cVar.a()) {
                e2.put(Long.valueOf(longValue), cVar);
                this.a = cVar;
                return;
            }
            com.jingdong.aura.a.b.e.a(str, (int) j2, "new BundleArchiveRevision failed. ", "BundleArchive_1_store", (Throwable) null);
            throw new l.b.a.b("check BundleArchiveRevision failed from BundleArchive_1");
        }
        throw new IOException("No valid revisions in bundle archive directory: " + file);
    }

    @Override // com.jingdong.aura.a.b.l.a
    public Class<?> a(String str, ClassLoader classLoader) {
        return this.a.a(str, classLoader);
    }

    @Override // com.jingdong.aura.a.b.l.a
    public File b(String str) {
        return this.a.b(str);
    }

    @Override // com.jingdong.aura.a.b.l.a
    public int c() {
        c cVar = this.a;
        if (cVar == null) {
            return 0;
        }
        return (int) cVar.g();
    }

    @Override // com.jingdong.aura.a.b.l.a
    public void d() {
        if (Build.VERSION.SDK_INT >= 29) {
            return;
        }
        this.a.j();
    }

    @Override // com.jingdong.aura.a.b.l.a
    public c e() {
        return this.a;
    }

    @Override // com.jingdong.aura.a.b.l.a
    public List<URL> a(String str) {
        return this.a.c(str);
    }

    @Override // com.jingdong.aura.a.b.l.a
    public File b() {
        return this.a.f();
    }

    @Override // com.jingdong.aura.a.b.l.a
    public boolean a() {
        if (Build.VERSION.SDK_INT >= 29) {
            return true;
        }
        return this.a.i();
    }

    @Override // com.jingdong.aura.a.b.l.a
    public c a(String str, int i2, File file, InputStream inputStream, String str2) {
        SortedMap<Long, c> sortedMap = this.b;
        if (sortedMap != null && sortedMap.lastKey().longValue() >= i2) {
            f12122c.d("packageName " + str + " versionCode " + i2 + " is small than exist " + this.b.lastKey());
            return null;
        }
        long j2 = i2;
        c cVar = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), inputStream, str2);
        int i3 = 0;
        while (!cVar.a()) {
            int i4 = i3 + 1;
            com.jingdong.aura.a.b.e.a(str, i2, "new BundleArchiveRevision failed. count = " + i4, "newRevision_inputStream", (Throwable) null);
            c cVar2 = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), inputStream, str2);
            if (i4 >= com.jingdong.aura.a.b.c.J()) {
                throw new l.b.a.b("check BundleArchiveRevision failed from newRevision_inputStream");
            }
            i3 = i4;
            cVar = cVar2;
        }
        this.b.put(Long.valueOf(j2), cVar);
        return cVar;
    }

    public b(String str, long j2, File file, InputStream inputStream, String str2) {
        this.b = new TreeMap();
        c cVar = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), inputStream, str2);
        int i2 = 0;
        while (!cVar.a()) {
            i2++;
            com.jingdong.aura.a.b.e.a(str, (int) j2, "new BundleArchiveRevision failed. count = " + i2, "BundleArchive_2_inputStream", (Throwable) null);
            cVar = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), inputStream, str2);
            if (i2 >= com.jingdong.aura.a.b.c.J()) {
                throw new l.b.a.b("check BundleArchiveRevision failed from BundleArchive_2");
            }
        }
        this.b.put(Long.valueOf(j2), cVar);
        this.a = cVar;
    }

    public b(String str, long j2, File file, File file2, String str2) {
        this.b = new TreeMap();
        c cVar = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), file2, str2);
        int i2 = 0;
        while (!cVar.a()) {
            i2++;
            com.jingdong.aura.a.b.e.a(str, (int) j2, "new BundleArchiveRevision failed. count = " + i2, "BundleArchive_3_file", (Throwable) null);
            cVar = new c(str, j2, new File(file, "package_" + String.valueOf(j2)), file2, str2);
            if (i2 >= com.jingdong.aura.a.b.c.J()) {
                throw new l.b.a.b("check BundleArchiveRevision failed from BundleArchive_3");
            }
        }
        this.b.put(Long.valueOf(j2), cVar);
        this.a = cVar;
    }
}
