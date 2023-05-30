package com.jingdong.aura.a.b;

import com.coremedia.iso.boxes.MetaBox;
import com.jingdong.aura.a.c.l;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public h(java.io.File r17, java.lang.String r18, long r19, com.jingdong.aura.a.b.g r21, java.io.InputStream r22, java.io.File r23, boolean r24, java.lang.String r25) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.b.h.<init>(java.io.File, java.lang.String, long, com.jingdong.aura.a.b.g, java.io.InputStream, java.io.File, boolean, java.lang.String):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public h(java.lang.String r16, java.io.File r17, com.jingdong.aura.a.b.g r18, long r19) {
        /*
            Method dump skipped, instructions count: 397
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.b.h.<init>(java.lang.String, java.io.File, com.jingdong.aura.a.b.g, long):void");
    }
}
