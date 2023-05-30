package com.jingdong.aura.a.b;

import android.text.TextUtils;
import com.coremedia.iso.boxes.MetaBox;
import com.jingdong.aura.a.c.l;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class h implements l.b.a.d {

    /* renamed from: i  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12103i = com.jingdong.aura.core.util.l.c.a("BundleImpl");
    private com.jingdong.aura.a.b.l.a a;
    private final File b;

    /* renamed from: c  reason: collision with root package name */
    private ClassLoader f12104c;
    private final g d;

    /* renamed from: e  reason: collision with root package name */
    private int f12105e;

    /* renamed from: f  reason: collision with root package name */
    private String f12106f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f12107g;

    /* renamed from: h  reason: collision with root package name */
    private int f12108h;

    /* JADX WARN: Removed duplicated region for block: B:14:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public h(File file, String str, long j2, g gVar, InputStream inputStream, File file2, boolean z, String str2) {
        new Hashtable();
        this.f12108h = 0;
        com.jingdong.aura.core.util.l.b bVar = f12103i;
        bVar.a("BundleImpl: bundleDir:" + file + " location:" + str + " versionCode:" + j2 + " archiveInputStream:" + inputStream + " archiveFile:" + file2 + " isInstall:" + z + " md5:" + str2);
        this.f12107g = false;
        long currentTimeMillis = System.currentTimeMillis();
        this.f12106f = str;
        this.d = gVar;
        gVar.a(this);
        this.f12105e = com.jingdong.aura.a.b.k.b.e();
        this.b = file;
        try {
            if (inputStream != null) {
                this.a = new com.jingdong.aura.a.b.l.b(str, j2, file, inputStream, str2);
            } else {
                if (file2 != null) {
                    this.a = new com.jingdong.aura.a.b.l.b(str, j2, file, file2, str2);
                }
                this.f12108h = 2;
                if (TextUtils.isEmpty(str)) {
                    bVar.c("location is empty!");
                    e.a(str, "bundleLocationNullCheck", str, "bundle location is null!", "new bundleImpl1", null);
                }
                p();
                if (z) {
                    a(false);
                    com.jingdong.aura.a.b.k.b.a(str, this);
                    com.jingdong.aura.a.b.k.b.a(1, this);
                }
                if (bVar.b()) {
                    return;
                }
                bVar.a("Framework: Bundle " + toString() + " created. " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                return;
            }
            this.f12108h = 2;
            if (TextUtils.isEmpty(str)) {
            }
            p();
            if (z) {
            }
            if (bVar.b()) {
            }
        } catch (IOException e2) {
            f12103i.b("new BundleArchive1 exception:" + e2.getMessage(), e2);
            throw new l.b.a.b("new BundleArchive failed", e2);
        }
    }

    public static void a(File file, String str, int i2, InputStream inputStream, String str2) {
        try {
            long c2 = com.jingdong.aura.a.b.l.h.c(file);
            f12103i.b("old max version=" + c2 + ", want new versioncode = " + i2);
            long j2 = (long) i2;
            if (c2 >= j2) {
                return;
            }
            new com.jingdong.aura.a.b.l.b(str, j2, file, inputStream, str2);
        } catch (Exception e2) {
            f12103i.b("location:" + str + ", " + e2.getMessage());
            throw new l.b.a.b("Could not update bundle " + str, e2);
        }
    }

    @Override // l.b.a.d
    public String b() {
        return this.f12106f;
    }

    @Override // l.b.a.d
    public void c() {
        int i2 = this.f12108h;
        if (i2 != 1) {
            if (i2 == 32) {
                try {
                    o();
                } catch (Throwable th) {
                    com.jingdong.aura.a.b.k.b.a(2, this, th);
                }
            }
            this.f12108h = 1;
            new File(this.b, MetaBox.TYPE).delete();
            this.f12104c = null;
            com.jingdong.aura.a.b.k.b.d(toString());
            com.jingdong.aura.a.b.k.b.a(16, this);
            this.d.a(false);
            this.d.a((h) null);
            return;
        }
        throw new IllegalStateException("Bundle " + toString() + " is already uninstalled.");
    }

    public com.jingdong.aura.a.b.l.a d() {
        return this.a;
    }

    public com.jingdong.aura.a.b.l.a e() {
        return this.a;
    }

    public String f() {
        return d().e().e() + "/lib";
    }

    public ClassLoader g() {
        return this.f12104c;
    }

    public int h() {
        return this.f12105e;
    }

    public String i() {
        return d().e().e() + "/lib:" + l.a.getApplicationInfo().nativeLibraryDir + ":" + System.getProperty("java.library.path");
    }

    public boolean j() {
        return this.f12107g;
    }

    public int k() {
        return this.f12108h;
    }

    public int l() {
        com.jingdong.aura.a.b.l.a aVar = this.a;
        if (aVar == null) {
            return 0;
        }
        return aVar.c();
    }

    public synchronized void m() {
        this.a.d();
    }

    public synchronized void n() {
        int i2 = this.f12108h;
        if (i2 == 1) {
            throw new IllegalStateException("Cannot start uninstalled bundle " + toString());
        } else if (i2 != 32) {
            if (i2 == 2) {
                a(true);
            }
            this.f12108h = 8;
            this.d.a(true);
            this.f12108h = 32;
            com.jingdong.aura.a.b.k.b.a(2, this);
            com.jingdong.aura.core.util.l.b bVar = f12103i;
            if (bVar.b()) {
                bVar.a("Framework: Bundle " + toString() + " started.");
            }
        }
    }

    public synchronized void o() {
        int i2 = this.f12108h;
        if (i2 == 1) {
            throw new IllegalStateException("Cannot stop uninstalled bundle " + toString());
        } else if (i2 == 32) {
            this.f12108h = 16;
            com.jingdong.aura.core.util.l.b bVar = f12103i;
            if (bVar.b()) {
                bVar.a("Framework: Bundle " + toString() + " stopped.");
            }
            this.f12108h = 4;
            com.jingdong.aura.a.b.k.b.a(4, this);
            this.d.a(false);
        }
    }

    public void p() {
        if (c.T()) {
            f12103i.a("sync update meta data");
            synchronized (this) {
                q();
            }
            return;
        }
        f12103i.a("update meta data");
        q();
    }

    public void q() {
        Throwable th;
        DataOutputStream dataOutputStream;
        IOException iOException;
        File file = new File(this.b, MetaBox.TYPE);
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                try {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    dataOutputStream = new DataOutputStream(fileOutputStream);
                    try {
                        dataOutputStream.writeUTF(this.f12106f);
                        dataOutputStream.writeInt(this.f12105e);
                        dataOutputStream.writeBoolean(this.f12107g);
                        dataOutputStream.flush();
                        fileOutputStream.getFD().sync();
                        dataOutputStream.close();
                    } catch (IOException e2) {
                        iOException = e2;
                        dataOutputStream2 = dataOutputStream;
                        String str = this.f12106f;
                        if (str == null) {
                            str = "";
                        }
                        String str2 = str;
                        f12103i.b(str2 + ":updateMetadata exception!" + iOException.getMessage(), iOException);
                        e.a(str2, "updateMetadata", str2, "updateMetadata file exception", "BundleImpl_updateMetadata", iOException);
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e3) {
                                f12103i.b(e3.getMessage(), e3);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    iOException = e4;
                }
            } catch (IOException e5) {
                f12103i.b(e5.getMessage(), e5);
            }
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
        }
    }

    public String toString() {
        return this.f12106f;
    }

    private synchronized void a(boolean z) {
        if (this.f12108h != 4) {
            if (this.f12104c == null) {
                this.f12104c = com.jingdong.aura.a.b.j.b.a(this);
            }
            if (z) {
                this.f12108h = 4;
            }
            com.jingdong.aura.a.b.k.b.a(0, this);
        }
    }

    @Override // l.b.a.d
    public void a() {
        this.f12107g = true;
        p();
        if (this.f12105e <= com.jingdong.aura.a.b.k.b.g()) {
            n();
        }
    }

    public void a(InputStream inputStream, int i2, String str) {
        if (this.f12108h != 1) {
            try {
                this.a.a(this.f12106f, i2, this.b, inputStream, str);
                return;
            } catch (Throwable th) {
                throw new l.b.a.b("Could not update bundle " + toString(), th);
            }
        }
        throw new IllegalStateException("Cannot update uninstalled bundle " + toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0125 A[Catch: Exception -> 0x0150, TRY_LEAVE, TryCatch #0 {Exception -> 0x0150, blocks: (B:32:0x0106, B:34:0x0125), top: B:47:0x0106 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0189 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public h(String str, File file, g gVar, long j2) {
        DataInputStream dataInputStream;
        DataInputStream dataInputStream2;
        com.jingdong.aura.core.util.l.b bVar;
        new Hashtable();
        this.f12108h = 0;
        long currentTimeMillis = System.currentTimeMillis();
        DataInputStream dataInputStream3 = null;
        try {
            try {
                dataInputStream2 = new DataInputStream(new FileInputStream(new File(file, MetaBox.TYPE)));
                try {
                    this.f12106f = dataInputStream2.readUTF();
                    this.f12105e = dataInputStream2.readInt();
                    this.f12107g = dataInputStream2.readBoolean();
                    dataInputStream2.close();
                } catch (Exception e2) {
                    e = e2;
                    dataInputStream = dataInputStream2;
                    try {
                        e.a(str, "readBundleMeta", str, "read Bundle Meta file exception", "BundleImpl", e);
                        f12103i.b("read Bundle Meta file exception:" + e.getMessage(), e);
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (!TextUtils.isEmpty(this.f12106f)) {
                            e.a(str, "bundleLocationNotEqual", str, "bundle location not equal", "new bundleImpl2", null);
                            f12103i.c("location:" + str + " mLocation:" + this.f12106f);
                        }
                        if (TextUtils.isEmpty(this.f12106f)) {
                            this.f12105e = com.jingdong.aura.a.b.k.b.e();
                            this.f12107g = false;
                            this.f12106f = str;
                            e.a(str, "bundleLocationNullCheck", str, "bundle location is null!!", "new bundleImpl2", null);
                            f12103i.c(str + ":mLocation is empty!");
                        }
                        this.d = gVar;
                        gVar.a(this);
                        this.b = file;
                        this.f12108h = 2;
                        this.a = new com.jingdong.aura.a.b.l.b(this.f12106f, file, j2);
                        a(false);
                        com.jingdong.aura.a.b.k.b.a(this.f12106f, this);
                        com.jingdong.aura.a.b.k.b.a(1, this);
                        bVar = f12103i;
                        if (bVar.b()) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        dataInputStream3 = dataInputStream;
                        dataInputStream2 = dataInputStream3;
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (dataInputStream2 != null) {
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                dataInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                dataInputStream2 = dataInputStream3;
                if (dataInputStream2 != null) {
                }
                throw th;
            }
        } catch (IOException unused2) {
        }
        try {
            if (!TextUtils.isEmpty(this.f12106f) && !this.f12106f.equals(str)) {
                e.a(str, "bundleLocationNotEqual", str, "bundle location not equal", "new bundleImpl2", null);
                f12103i.c("location:" + str + " mLocation:" + this.f12106f);
            }
        } catch (Throwable th4) {
            f12103i.a("location check fail:", th4);
        }
        if (TextUtils.isEmpty(this.f12106f) && c.b()) {
            this.f12105e = com.jingdong.aura.a.b.k.b.e();
            this.f12107g = false;
            this.f12106f = str;
            e.a(str, "bundleLocationNullCheck", str, "bundle location is null!!", "new bundleImpl2", null);
            f12103i.c(str + ":mLocation is empty!");
        }
        this.d = gVar;
        gVar.a(this);
        this.b = file;
        this.f12108h = 2;
        try {
            this.a = new com.jingdong.aura.a.b.l.b(this.f12106f, file, j2);
            a(false);
            com.jingdong.aura.a.b.k.b.a(this.f12106f, this);
            com.jingdong.aura.a.b.k.b.a(1, this);
            bVar = f12103i;
            if (bVar.b()) {
                return;
            }
            bVar.a("Framework: Bundle " + toString() + " loaded. " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        } catch (Exception e4) {
            f12103i.b("new BundleArchive2 exception:" + e4.getMessage(), e4);
            throw new l.b.a.b("Could not load bundle " + this.f12106f, e4);
        }
    }
}
