package com.jingdong.aura.a.b.l;

import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h() {
        /*
            r8 = this;
            java.lang.String r0 = "Could not save metadex data "
            boolean r1 = r8.a
            if (r1 != 0) goto L7
            return
        L7:
            java.io.File r1 = new java.io.File
            java.io.File r2 = r8.d
            java.lang.String r3 = "metadex"
            r1.<init>(r2, r3)
            r2 = 0
            java.io.File r3 = r1.getParentFile()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            boolean r3 = r3.exists()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            if (r3 != 0) goto L22
            java.io.File r3 = r1.getParentFile()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            r3.mkdirs()     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
        L22:
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L76 java.lang.Exception -> L78
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            java.io.File r4 = r8.d     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            java.lang.String r5 = "bundle.dex"
            r2.<init>(r4, r5)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            boolean r4 = r2.exists()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            if (r4 == 0) goto L46
            java.lang.String r2 = r2.getAbsolutePath()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            java.lang.String r2 = com.jingdong.aura.core.util.d.a(r2)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            r3.writeUTF(r2)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
        L46:
            r3.flush()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            r3.close()     // Catch: java.io.IOException -> L5b java.lang.Exception -> L74 java.lang.Throwable -> Lad
            r3.close()     // Catch: java.io.IOException -> L50
            goto L5a
        L50:
            r0 = move-exception
            com.jingdong.aura.core.util.l.b r1 = com.jingdong.aura.a.b.l.d.f12129g
            java.lang.String r2 = r0.getMessage()
            r1.b(r2, r0)
        L5a:
            return
        L5b:
            r2 = move-exception
            com.jingdong.aura.core.util.l.b r4 = com.jingdong.aura.a.b.l.d.f12129g     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            java.lang.String r5 = r2.getMessage()     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            r4.b(r5, r2)     // Catch: java.lang.Exception -> L74 java.lang.Throwable -> Lad
            r3.close()     // Catch: java.io.IOException -> L69
            goto L73
        L69:
            r0 = move-exception
            com.jingdong.aura.core.util.l.b r1 = com.jingdong.aura.a.b.l.d.f12129g
            java.lang.String r2 = r0.getMessage()
            r1.b(r2, r0)
        L73:
            return
        L74:
            r2 = move-exception
            goto L7c
        L76:
            r0 = move-exception
            goto Laf
        L78:
            r3 = move-exception
            r7 = r3
            r3 = r2
            r2 = r7
        L7c:
            com.jingdong.aura.core.util.l.b r4 = com.jingdong.aura.a.b.l.d.f12129g     // Catch: java.lang.Throwable -> Lad
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
            r5.<init>()     // Catch: java.lang.Throwable -> Lad
            r5.append(r0)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r6 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> Lad
            r5.append(r6)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> Lad
            r4.b(r5, r2)     // Catch: java.lang.Throwable -> Lad
            java.io.IOException r4 = new java.io.IOException     // Catch: java.lang.Throwable -> Lad
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lad
            r5.<init>()     // Catch: java.lang.Throwable -> Lad
            r5.append(r0)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r0 = r1.getAbsolutePath()     // Catch: java.lang.Throwable -> Lad
            r5.append(r0)     // Catch: java.lang.Throwable -> Lad
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> Lad
            r4.<init>(r0, r2)     // Catch: java.lang.Throwable -> Lad
            throw r4     // Catch: java.lang.Throwable -> Lad
        Lad:
            r0 = move-exception
            r2 = r3
        Laf:
            if (r2 == 0) goto Lbf
            r2.close()     // Catch: java.io.IOException -> Lb5
            goto Lbf
        Lb5:
            r1 = move-exception
            com.jingdong.aura.core.util.l.b r2 = com.jingdong.aura.a.b.l.d.f12129g
            java.lang.String r3 = r1.getMessage()
            r2.b(r3, r1)
        Lbf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.b.l.d.h():void");
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
