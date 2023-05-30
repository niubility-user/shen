package com.jingdong.aura.a.b.l;

import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class d {

    /* renamed from: g  reason: collision with root package name */
    static final com.jingdong.aura.core.util.l.b f12129g = com.jingdong.aura.core.util.l.c.a("BundleDex");
    final String b;

    /* renamed from: c  reason: collision with root package name */
    final int f12130c;
    final File d;

    /* renamed from: e  reason: collision with root package name */
    final File f12131e;
    final boolean a = com.jingdong.aura.a.b.c.u();

    /* renamed from: f  reason: collision with root package name */
    private DexFile f12132f = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(String str, int i2, File file, File file2) {
        this.b = str;
        this.f12130c = i2;
        this.f12131e = file;
        this.d = file2;
    }

    private void b() {
        new File(this.d, "metadex").delete();
    }

    private String g() {
        File file = new File(this.d, "metadex");
        String str = null;
        if (file.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                str = dataInputStream.readUTF();
                dataInputStream.close();
                return str;
            } catch (IOException e2) {
                f12129g.b("readDexMd5FromMetaFile failed. ", e2);
                return str;
            }
        }
        return null;
    }

    public void a() {
        new File(this.d, "bundle.dex").delete();
        b();
    }

    public void c() {
        if (this.a) {
            if (!com.jingdong.aura.a.b.c.v() || com.jingdong.aura.wrapper.a.b()) {
                File file = new File(this.d, "bundle.dex");
                if (!file.exists()) {
                    b();
                    this.f12132f = null;
                    return;
                }
                String a = com.jingdong.aura.core.util.d.a(file.getAbsolutePath());
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                String g2 = g();
                if (TextUtils.isEmpty(g2) || g2.equals(a)) {
                    return;
                }
                f12129g.a("dex md5 check failed, delete the dex file. " + file.getAbsolutePath() + ". meta stored md5 = " + g2 + ", now dex md5 = " + a);
                file.delete();
                b();
                this.f12132f = null;
                com.jingdong.aura.a.b.e.a(this.b, this.f12130c, "dex md5 check failed, delete the dex file. " + file.getAbsolutePath() + ". meta stored md5 = " + g2 + ", now dex md5 = " + a, "ensureDexFileByMd5", (Throwable) null);
            }
        }
    }

    public DexFile d() {
        if (this.f12132f == null) {
            try {
                a(new File(this.d, "bundle.dex"));
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return this.f12132f;
    }

    public boolean e() {
        if (new File(this.d, "bundle.dex").exists()) {
            return true;
        }
        this.f12132f = null;
        return false;
    }

    public abstract void f();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void h() {
        Exception e2;
        DataOutputStream dataOutputStream;
        if (this.a) {
            File file = new File(this.d, "metadex");
            DataOutputStream dataOutputStream2 = null;
            try {
                try {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                } catch (Exception e3) {
                    e2 = e3;
                } catch (Throwable th) {
                    th = th;
                    if (dataOutputStream2 != null) {
                    }
                    throw th;
                }
                try {
                    File file2 = new File(this.d, "bundle.dex");
                    if (file2.exists()) {
                        dataOutputStream.writeUTF(com.jingdong.aura.core.util.d.a(file2.getAbsolutePath()));
                    }
                    dataOutputStream.flush();
                    try {
                        dataOutputStream.close();
                        try {
                            dataOutputStream.close();
                        } catch (IOException e4) {
                            f12129g.b(e4.getMessage(), e4);
                        }
                    } catch (IOException e5) {
                        f12129g.b(e5.getMessage(), e5);
                        try {
                            dataOutputStream.close();
                        } catch (IOException e6) {
                            f12129g.b(e6.getMessage(), e6);
                        }
                    }
                } catch (Exception e7) {
                    e2 = e7;
                    f12129g.b("Could not save metadex data " + file.getAbsolutePath(), e2);
                    throw new IOException("Could not save metadex data " + file.getAbsolutePath(), e2);
                }
            } catch (Throwable th2) {
                th = th2;
                dataOutputStream2 = "metadex";
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e8) {
                        f12129g.b(e8.getMessage(), e8);
                    }
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(File file) {
        if (this.f12132f == null) {
            this.f12132f = DexFile.loadDex(this.f12131e.getAbsolutePath(), file.getAbsolutePath(), 0);
        }
        if (this.f12132f == null) {
            com.jingdong.aura.a.b.e.a(this.b, this.f12130c, "can't load dex file " + file.getAbsolutePath(), "BundleArchiveRevision.loadDex", (Throwable) null);
        }
    }
}
