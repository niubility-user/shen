package com.jingdong.aura.a.b;

import android.app.Application;
import androidx.databinding.DataBinderMapperProxy;
import com.jingdong.aura.wrapper.AuraInitializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: e */
    private static final com.jingdong.aura.core.util.l.b f12085e = com.jingdong.aura.core.util.l.c.a("Aura");

    /* renamed from: f */
    private static a f12086f;
    private com.jingdong.aura.a.c.c a;
    private com.jingdong.aura.a.c.h b;

    /* renamed from: c */
    private AuraInitializer.b f12087c;
    private DataBinderMapperProxy d;

    public static a b() {
        if (f12086f == null) {
            f12086f = new a();
        }
        return f12086f;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00d2 A[Catch: all -> 0x00fe, TRY_LEAVE, TryCatch #0 {all -> 0x00fe, blocks: (B:41:0x005d, B:44:0x006a, B:47:0x006f, B:49:0x0088, B:51:0x00d2, B:53:0x00e9, B:48:0x007c), top: B:61:0x005d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.app.Application r7) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.b.a.a(android.app.Application):void");
    }

    public void c(String str) {
        com.jingdong.aura.a.c.d.b(str);
    }

    public AuraInitializer.b c() {
        return this.f12087c;
    }

    public File b(String str) {
        l.b.a.d b = com.jingdong.aura.a.b.k.b.b(str);
        if (b != null) {
            return ((h) b).e().b();
        }
        return null;
    }

    public void a(Application application, Properties properties) {
        f12085e.e("startup called");
        if (c.N()) {
            com.jingdong.aura.a.b.k.b.a(application, properties);
            DataBinderMapperProxy dataBinderMapperProxy = this.d;
            if (dataBinderMapperProxy != null) {
                com.jingdong.aura.a.b.k.b.a(dataBinderMapperProxy);
            }
        }
    }

    public void a(l.b.a.e eVar) {
        com.jingdong.aura.a.b.k.b.a(eVar);
    }

    public List<l.b.a.d> a() {
        f12085e.e("getBundles called");
        return com.jingdong.aura.a.b.k.b.c();
    }

    public l.b.a.d a(String str) {
        f12085e.e("getBundle called");
        return com.jingdong.aura.a.b.k.b.b(str);
    }

    public void a(String str, File file, long j2, String str2) {
        f12085e.e("installBundle called file.  location=" + str + ", versionCode=" + j2 + ", md5=" + str2);
        e.a("AuraMaiDianInstallBundle", str, (int) j2, "", "installBundleFile");
        com.jingdong.aura.a.b.k.b.a(str, file, null, j2, str2);
    }

    public void a(String str, InputStream inputStream, long j2, String str2) {
        f12085e.e("installBundle called stream. location=" + str + ", versionCode=" + j2 + ", md5=" + str2);
        e.a("AuraMaiDianInstallBundle", str, (int) j2, "", "installBundleInputStream");
        com.jingdong.aura.a.b.k.b.a(str, null, inputStream, j2, str2);
    }

    public boolean a(String str, String str2, int i2, String str3) {
        Throwable th;
        FileNotFoundException e2;
        FileInputStream fileInputStream;
        com.jingdong.aura.core.util.l.b bVar = f12085e;
        bVar.e("Update called");
        FileInputStream fileInputStream2 = null;
        if (str2 == null) {
            bVar.a("path is null");
            e.a(str, i2, "bundle update failed, because apkFile path is null form Aura.update for bundle" + str, "Aura.update", (Throwable) null);
            return false;
        } else if (!c.N()) {
            e.a(str, i2, "bundle update failed, because AuraConfigInternal.isUseAura is false form Aura.update for bundle" + str, "Aura.update", (Throwable) null);
            return false;
        } else if (!c.a(1)) {
            e.a(str, i2, "bundle update failed, because disk size not enough  form Aura.update for bundle" + str, "Aura.update", (Throwable) null);
            return false;
        } else {
            File file = new File(str2);
            try {
                try {
                    if (!file.exists()) {
                        bVar.a("path not exists. " + str2);
                        e.a(str, i2, "bundle update failed, because apkFile not exist form Aura.update for bundle" + str, "Aura.update", (Throwable) null);
                        return false;
                    }
                    try {
                        fileInputStream = new FileInputStream(file);
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        boolean a = com.jingdong.aura.a.b.k.b.a(str, fileInputStream, i2, str3);
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        return a;
                    } catch (FileNotFoundException e5) {
                        e2 = e5;
                        fileInputStream2 = fileInputStream;
                        e.a(str, i2, "bundle update failed 1 " + str, "Aura.update", e2);
                        e2.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream2 = fileInputStream;
                        e.a(str, i2, "update bundle failed 2" + str, "Aura.update", th);
                        th.printStackTrace();
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        return false;
                    }
                } catch (Throwable th4) {
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    throw th4;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        }
    }

    public void a(AuraInitializer.b bVar) {
        this.f12087c = bVar;
    }
}
