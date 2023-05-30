package com.jingdong.aura.wrapper.d;

import android.app.Application;
import com.jingdong.aura.a.a.a;
import com.jingdong.aura.wrapper.AuraConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class a {
    private static final com.jingdong.aura.core.util.l.b d = com.jingdong.aura.core.util.l.c.a("BundlesInstaller");

    /* renamed from: e  reason: collision with root package name */
    private static a f12263e;
    private Application a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12264c;

    a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            a aVar2 = f12263e;
            if (aVar2 != null) {
                return aVar2;
            }
            synchronized (a.class) {
                if (f12263e == null) {
                    f12263e = new a();
                }
                aVar = f12263e;
            }
            return aVar;
        }
    }

    public void b(ZipFile zipFile, List<String> list, Application application) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(zipFile, it.next(), application);
        }
        for (String str : AuraConfig.AUTO) {
            l.b.a.d a = com.jingdong.aura.a.b.a.b().a(str);
            if (a != null) {
                try {
                    a.a();
                } catch (Throwable unused) {
                    String str2 = "Could not auto start bundle: " + a.b();
                }
            }
        }
    }

    public void a(Application application) {
        this.a = application;
        this.b = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00b0 A[Catch: all -> 0x00b4, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:5:0x0005, B:8:0x000e, B:32:0x008f, B:48:0x00ab, B:50:0x00b0, B:39:0x009b, B:43:0x00a3, B:42:0x00a0, B:45:0x00a6), top: B:62:0x0001, inners: #0, #6 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x00ab -> B:59:0x00ae). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(boolean r9, boolean r10) {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.b     // Catch: java.lang.Throwable -> Lb4
            if (r0 != 0) goto Le
            com.jingdong.aura.core.util.l.b r9 = com.jingdong.aura.wrapper.d.a.d     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r10 = "Bundle Installer not initialized yet, process abort!"
            r9.d(r10)     // Catch: java.lang.Throwable -> Lb4
            monitor-exit(r8)
            return
        Le:
            boolean r0 = r8.f12264c     // Catch: java.lang.Throwable -> Lb4
            if (r0 == 0) goto L14
            if (r10 == 0) goto Lb2
        L14:
            r0 = 0
            r1 = 1
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch: java.lang.Throwable -> L98 java.io.IOException -> La4
            android.app.Application r3 = r8.a     // Catch: java.lang.Throwable -> L98 java.io.IOException -> La4
            android.content.pm.ApplicationInfo r3 = r3.getApplicationInfo()     // Catch: java.lang.Throwable -> L98 java.io.IOException -> La4
            java.lang.String r3 = r3.sourceDir     // Catch: java.lang.Throwable -> L98 java.io.IOException -> La4
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L98 java.io.IOException -> La4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r0.<init>()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r3 = "lib/"
            r0.append(r3)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r3 = com.jingdong.aura.a.b.c.l()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r0.append(r3)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r3 = "/libcom."
            r0.append(r3)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r3 = ".so"
            java.util.List r0 = r8.a(r2, r0, r3)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            if (r0 == 0) goto L52
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            if (r3 <= 0) goto L52
            int r3 = r0.size()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            com.jingdong.aura.a.b.c.a(r3, r1)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
        L52:
            if (r9 == 0) goto L83
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r9.<init>()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
        L5d:
            boolean r3 = r0.hasNext()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            if (r3 == 0) goto L7d
            java.lang.Object r3 = r0.next()     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            java.lang.String[] r4 = com.jingdong.aura.wrapper.AuraConfig.AUTO     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            int r5 = r4.length     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r6 = 0
        L6d:
            if (r6 >= r5) goto L5d
            r7 = r4[r6]     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            boolean r7 = r3.contains(r7)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            if (r7 == 0) goto L7a
            r9.add(r3)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
        L7a:
            int r6 = r6 + 1
            goto L6d
        L7d:
            android.app.Application r0 = r8.a     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r8.b(r2, r9, r0)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            goto L88
        L83:
            android.app.Application r9 = r8.a     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            r8.a(r2, r0, r9)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
        L88:
            if (r10 != 0) goto L8f
            android.app.Application r9 = r8.a     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
            com.jingdong.aura.wrapper.c.a(r9)     // Catch: java.lang.Throwable -> L93 java.io.IOException -> L96
        L8f:
            r2.close()     // Catch: java.io.IOException -> Laa java.lang.Throwable -> Lb4
            goto Lae
        L93:
            r9 = move-exception
            r0 = r2
            goto L99
        L96:
            r0 = r2
            goto La4
        L98:
            r9 = move-exception
        L99:
            if (r0 == 0) goto La3
            r0.close()     // Catch: java.io.IOException -> L9f java.lang.Throwable -> Lb4
            goto La3
        L9f:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.lang.Throwable -> Lb4
        La3:
            throw r9     // Catch: java.lang.Throwable -> Lb4
        La4:
            if (r0 == 0) goto Lae
            r0.close()     // Catch: java.io.IOException -> Laa java.lang.Throwable -> Lb4
            goto Lae
        Laa:
            r9 = move-exception
            r9.printStackTrace()     // Catch: java.lang.Throwable -> Lb4
        Lae:
            if (r10 == 0) goto Lb2
            r8.f12264c = r1     // Catch: java.lang.Throwable -> Lb4
        Lb2:
            monitor-exit(r8)
            return
        Lb4:
            r9 = move-exception
            monitor-exit(r8)
            goto Lb8
        Lb7:
            throw r9
        Lb8:
            goto Lb7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.wrapper.d.a.a(boolean, boolean):void");
    }

    private List<String> a(ZipFile zipFile, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(str) && name.endsWith(str2)) {
                    arrayList.add(name);
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private void a(ZipFile zipFile, List<String> list, Application application) {
        for (String str : AuraConfig.DELAY) {
            String a = a(list, str);
            if (a != null && a.length() > 0) {
                a(zipFile, a, application);
                list.remove(a);
            }
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            a(zipFile, it.next(), application);
        }
        for (String str2 : AuraConfig.AUTO) {
            l.b.a.d a2 = com.jingdong.aura.a.b.a.b().a(str2);
            if (a2 != null) {
                try {
                    a2.a();
                } catch (Throwable unused) {
                    String str3 = "Could not auto start bundle: " + a2.b();
                }
            }
        }
    }

    private String a(List<String> list, String str) {
        if (list != null && str != null) {
            for (String str2 : list) {
                if (str2.contains(str)) {
                    return str2;
                }
            }
        }
        return null;
    }

    private boolean a(ZipFile zipFile, String str, Application application) {
        String str2;
        com.jingdong.aura.core.util.l.b bVar = d;
        bVar.a("processLibsBundle entryName " + str);
        String a = com.jingdong.aura.wrapper.c.a(str);
        String b = com.jingdong.aura.wrapper.c.b(str);
        if (b != null && b.length() > 0) {
            File file = new File(new File(com.jingdong.aura.core.util.d.a().getParentFile(), "lib"), a);
            if (com.jingdong.aura.a.b.a.b().a(b) != null) {
                return false;
            }
            try {
                a.C0391a a2 = com.jingdong.aura.a.a.a.c().a(b);
                long j2 = 0;
                if (a2 != null) {
                    j2 = a2.f12077j;
                    str2 = a2.f12079l;
                } else {
                    str2 = "";
                }
                String str3 = str2;
                long j3 = j2;
                if (file.exists()) {
                    com.jingdong.aura.a.b.a.b().a(b, file, j3, str3);
                } else {
                    com.jingdong.aura.a.b.a.b().a(b, zipFile.getInputStream(zipFile.getEntry(str)), j3, str3);
                }
                bVar.a("Succeed to install bundle " + b);
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
