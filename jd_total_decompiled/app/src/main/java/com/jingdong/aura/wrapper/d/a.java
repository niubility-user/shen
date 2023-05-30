package com.jingdong.aura.wrapper.d;

import android.app.Application;
import com.jingdong.aura.a.a.a;
import com.jingdong.aura.wrapper.AuraConfig;
import java.io.File;
import java.io.IOException;
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
    */
    public synchronized void a(boolean z, boolean z2) {
        ZipFile zipFile;
        if (!this.b) {
            d.d("Bundle Installer not initialized yet, process abort!");
            return;
        }
        if (!this.f12264c || z2) {
            ZipFile zipFile2 = null;
            try {
                try {
                    zipFile = new ZipFile(this.a.getApplicationInfo().sourceDir);
                } catch (IOException unused) {
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    List<String> a = a(zipFile, "lib/" + com.jingdong.aura.a.b.c.l() + "/libcom.", ".so");
                    if (a != null && a.size() > 0) {
                        com.jingdong.aura.a.b.c.a(a.size(), true);
                    }
                    if (z) {
                        ArrayList arrayList = new ArrayList();
                        for (String str : a) {
                            for (String str2 : AuraConfig.AUTO) {
                                if (str.contains(str2)) {
                                    arrayList.add(str);
                                }
                            }
                        }
                        b(zipFile, arrayList, this.a);
                    } else {
                        a(zipFile, a, this.a);
                    }
                    if (!z2) {
                        com.jingdong.aura.wrapper.c.a(this.a);
                    }
                    zipFile.close();
                } catch (IOException unused2) {
                    zipFile2 = zipFile;
                    if (zipFile2 != null) {
                        zipFile2.close();
                    }
                    if (z2) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                    zipFile2 = zipFile;
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            if (z2) {
                this.f12264c = true;
            }
        }
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
