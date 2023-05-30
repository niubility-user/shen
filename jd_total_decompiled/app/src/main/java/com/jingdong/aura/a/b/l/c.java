package com.jingdong.aura.a.b.l;

import android.text.TextUtils;
import com.coremedia.iso.boxes.MetaBox;
import com.jd.dynamic.DYConstants;
import com.jingdong.aura.a.a.a;
import com.jingdong.aura.a.c.j;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: i */
    private static final com.jingdong.aura.core.util.l.b f12123i = com.jingdong.aura.core.util.l.c.a("BundleArchiveRevision");
    private final String a;
    private final File b;

    /* renamed from: c */
    private final long f12124c;
    private final String d;

    /* renamed from: e */
    private final File f12125e;

    /* renamed from: f */
    private final String f12126f;

    /* renamed from: g */
    private ZipFile f12127g;

    /* renamed from: h */
    private d f12128h;

    public c(String str, long j2, File file, InputStream inputStream, String str2) {
        this.a = str;
        this.f12124c = j2;
        this.b = file;
        if (file.exists()) {
            com.jingdong.aura.core.util.f.a(file);
        }
        file.mkdirs();
        this.d = "file:";
        File file2 = new File(file, "bundle.zip");
        this.f12125e = file2;
        this.f12126f = str2;
        com.jingdong.aura.core.util.d.a(inputStream, file2);
        if (j.a(file2)) {
            f12123i.a("find so location:" + str);
            c(file2);
            b(file2);
        }
        b();
        a(file2, e());
        k();
        this.f12128h = new e().a(str, (int) j2, file2, file);
    }

    private void b() {
        com.jingdong.aura.core.util.l.b bVar = f12123i;
        bVar.a("checkDependencySo: mLocation :" + this.a);
        List<String> e2 = com.jingdong.aura.a.a.a.c().e(this.a);
        if (e2 == null || e2.size() <= 0) {
            return;
        }
        bVar.a("checkDependencySo: pass :mLocation:" + this.a + " dependentSos:" + e2);
        h();
    }

    private void c() {
        f12123i.d("deleteRevisionDir " + this.b.getAbsolutePath());
        com.jingdong.aura.core.util.f.a(this.b);
    }

    private void d() {
        if (this.f12127g == null) {
            this.f12127g = new ZipFile(this.f12125e, 1);
        }
    }

    private void h() {
        List<String> d;
        List<String> e2 = com.jingdong.aura.a.a.a.c().e(this.a);
        if (e2 == null || e2.size() <= 0 || (d = com.jingdong.aura.a.a.a.c().d(this.a)) == null || d.size() <= 0) {
            return;
        }
        f12123i.b(this.a + ":installDependencySos:" + e2);
        ArrayList<String> arrayList = new ArrayList();
        for (String str : e2) {
            if (!TextUtils.isEmpty(str)) {
                File e3 = e();
                StringBuilder sb = new StringBuilder();
                sb.append("lib");
                String str2 = File.separator;
                sb.append(str2);
                sb.append(str);
                File file = new File(e3, sb.toString());
                File file2 = new File(e(), "lib" + str2 + str + ".md5");
                if (!file.exists() || !file2.exists()) {
                    arrayList.add(str);
                    file.delete();
                    file2.delete();
                }
            }
        }
        if (arrayList.size() <= 0) {
            f12123i.b(this.a + ":" + e2 + "already installed");
            return;
        }
        for (String str3 : arrayList) {
            com.jingdong.aura.core.util.l.b bVar = f12123i;
            bVar.b(this.a + ":install so: " + str3);
            if (!TextUtils.isEmpty(str3)) {
                File e4 = e();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("lib");
                String str4 = File.separator;
                sb2.append(str4);
                sb2.append(str3);
                File file3 = new File(e4, sb2.toString());
                File file4 = new File(e(), "lib" + str4 + str3 + ".md5");
                File parentFile = file3.getParentFile();
                if (!parentFile.exists()) {
                    bVar.a("installDependencySos: mkdir result:" + parentFile.mkdir());
                }
                Iterator<String> it = d.iterator();
                while (true) {
                    if (it.hasNext()) {
                        String next = it.next();
                        com.jingdong.aura.core.util.l.b bVar2 = f12123i;
                        bVar2.b(this.a + ":install so form " + next);
                        com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) com.jingdong.aura.a.b.k.b.b(next);
                        if (hVar == null) {
                            bVar2.c(next + ":bundleImpl is null");
                        } else {
                            File e5 = hVar.d().e().e();
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("lib");
                            List<String> list = d;
                            String str5 = File.separator;
                            sb3.append(str5);
                            sb3.append(str3);
                            File file5 = new File(e5, sb3.toString());
                            File file6 = new File(hVar.d().e().e(), "lib" + str5 + str3 + ".md5");
                            if (file5.exists() && file6.exists()) {
                                try {
                                    Runtime.getRuntime().exec(String.format("ln -s %s %s", file5.getAbsolutePath(), file3.getAbsolutePath())).waitFor();
                                    Runtime.getRuntime().exec(String.format("ln -s %s %s", file6.getAbsolutePath(), file4.getAbsolutePath())).waitFor();
                                } catch (Exception e6) {
                                    f12123i.b("wait ln -s for coopad failed.", e6);
                                    e6.printStackTrace();
                                }
                                if (!file3.exists() || !file4.exists()) {
                                    try {
                                        file3.delete();
                                        file4.delete();
                                        com.jingdong.aura.core.util.d.a(new FileInputStream(file5), file3);
                                        com.jingdong.aura.core.util.d.a(new FileInputStream(file6), file4);
                                        f12123i.b(this.a + ":install so form " + next + " success");
                                    } catch (Exception e7) {
                                        e7.printStackTrace();
                                        com.jingdong.aura.a.b.e.a(this.a, "installDependencySo failed1: " + str3, "BundleArchiveRevision.installDependencySo", e7);
                                    }
                                }
                                if (file3.exists() && file4.exists()) {
                                    d = list;
                                    break;
                                }
                            }
                            d = list;
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:173:0x00b0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void k() {
        DataOutputStream dataOutputStream;
        IOException e2;
        File file = new File(this.b, MetaBox.TYPE);
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e3) {
                        f12123i.b(e3.getMessage(), e3);
                    }
                }
                dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                try {
                    try {
                        dataOutputStream.writeUTF(this.d);
                        dataOutputStream.writeUTF(this.f12126f);
                        dataOutputStream.flush();
                        try {
                            dataOutputStream.close();
                            try {
                                dataOutputStream.close();
                            } catch (IOException e4) {
                                f12123i.b(e4.getMessage(), e4);
                            }
                        } catch (IOException e5) {
                            f12123i.b(e5.getMessage(), e5);
                            try {
                                dataOutputStream.close();
                            } catch (IOException e6) {
                                f12123i.b(e6.getMessage(), e6);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        dataOutputStream2 = dataOutputStream;
                        if (dataOutputStream2 != null) {
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    e2 = e7;
                    f12123i.b("Could not save meta data " + file.getAbsolutePath(), e2);
                    throw new IOException("Could not save meta data " + file.getAbsolutePath(), e2);
                }
            } catch (Throwable th2) {
                th = th2;
                if (dataOutputStream2 != null) {
                    try {
                        dataOutputStream2.close();
                    } catch (IOException e8) {
                        f12123i.b(e8.getMessage(), e8);
                    }
                }
                throw th;
            }
        } catch (IOException e9) {
            dataOutputStream = null;
            e2 = e9;
        }
    }

    public boolean a() {
        int i2 = 0;
        if (!a(this.f12126f)) {
            c();
            return false;
        }
        File file = new File(String.format("%s%s%s", this.b, File.separator, "lib"));
        while (!a(file)) {
            com.jingdong.aura.core.util.f.a(file);
            c(this.f12125e);
            b(this.f12125e);
            f12123i.a("check so when check md5:");
            b();
            i2++;
            if (i2 >= com.jingdong.aura.a.b.c.J()) {
                break;
            }
        }
        boolean a = a(file);
        if (!a) {
            c();
        }
        return a;
    }

    public File e() {
        return this.b;
    }

    public File f() {
        return this.f12125e;
    }

    public long g() {
        return this.f12124c;
    }

    public boolean i() {
        return this.f12128h.e();
    }

    public synchronized void j() {
        this.f12128h.f();
    }

    public List<URL> c(String str) {
        ArrayList arrayList = new ArrayList();
        d();
        ZipFile zipFile = this.f12127g;
        if (zipFile != null && zipFile.getEntry(str) != null) {
            try {
                arrayList.add(new URL("jar:" + this.f12125e.toURL() + "!/" + str));
            } catch (Throwable th) {
                throw new RuntimeException(th);
            }
        }
        return arrayList;
    }

    public File b(String str) {
        String str2 = File.separator;
        File file = new File(String.format("%s%s%s%s", this.b, str2, "lib", str2), str);
        if (file.exists() && file.isFile()) {
            return file;
        }
        return null;
    }

    private void b(File file) {
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    String a = com.jingdong.aura.core.util.e.a(this.a, file);
                    f12123i.a("installSoLib:" + this.a + " archiveFile:" + file.getAbsolutePath() + " abi:" + a);
                    Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        String name = nextElement.getName();
                        if (name.indexOf(String.format("%s%s", "lib/", a)) != -1) {
                            String str = File.separator;
                            String format = String.format("%s%s%s%s%s", this.b, str, "lib", str, name.substring(name.lastIndexOf(str) + 1, name.length()));
                            if (nextElement.isDirectory()) {
                                File file2 = new File(format);
                                if (!file2.exists()) {
                                    file2.mkdirs();
                                }
                            } else {
                                File file3 = new File(format.substring(0, format.lastIndexOf(str)));
                                if (!file3.exists()) {
                                    file3.mkdirs();
                                }
                                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(format));
                                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile2.getInputStream(nextElement));
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = bufferedInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    bufferedOutputStream.write(bArr, 0, read);
                                }
                                bufferedOutputStream.close();
                            }
                        }
                    }
                    com.jingdong.aura.core.util.d.a(zipFile2);
                } catch (Exception e2) {
                    e = e2;
                    zipFile = zipFile2;
                    f12123i.b(e.getMessage(), e);
                    com.jingdong.aura.core.util.d.a(zipFile);
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    com.jingdong.aura.core.util.d.a(zipFile);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:223:0x002f, code lost:
        r5 = new java.io.ByteArrayOutputStream();
        r7 = new java.io.BufferedOutputStream(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x0039, code lost:
        r9 = new java.io.BufferedInputStream(r4.getInputStream(r6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0044, code lost:
        r3 = new byte[4096];
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x0046, code lost:
        r6 = r9.read(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x004b, code lost:
        if (r6 == (-1)) goto L317;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x004d, code lost:
        r7.write(r3, 0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0051, code lost:
        r7.flush();
        r3 = new org.json.JSONObject(r5.toString()).getJSONArray("libs");
        r6 = r3.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0067, code lost:
        if (r6 <= 0) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x006e, code lost:
        r7 = new java.io.File(java.lang.String.format("%s%s%s", r18.b, java.io.File.separator, "lib"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x0087, code lost:
        if (r7.exists() != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0089, code lost:
        r7.mkdirs();
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x008c, code lost:
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x008d, code lost:
        if (r7 >= r6) goto L301;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x008f, code lost:
        r8 = (org.json.JSONObject) r3.get(r7);
        r12 = r8.optString("name");
        r15 = r8.optString("path");
        r8 = r8.optString("md5");
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x00ab, code lost:
        if (r0.compareToIgnoreCase(r15) != 0) goto L244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x00b0, code lost:
        r13 = java.io.File.separator;
        r12 = new java.io.DataOutputStream(new java.io.FileOutputStream(java.lang.String.format("%s%s%s%s%s.md5", r18.b, r13, "lib", r13, r12)));
        r12.writeUTF(r8);
        r12.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x00de, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x00e3, code lost:
        r5.close();
        r9.close();
        r9.close();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x00ee, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x00ef, code lost:
        r2 = com.jingdong.aura.a.b.l.c.f12123i;
        r3 = r0.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x00f6, code lost:
        r3 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x00f8, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:253:0x00f9, code lost:
        r3 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x00fb, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x00fc, code lost:
        r3 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x00fe, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:257:0x00ff, code lost:
        com.jingdong.aura.a.b.l.c.f12123i.b(r0.getMessage(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:258:0x0108, code lost:
        r5.close();
        r3.close();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:259:0x010e, code lost:
        if (r3 != null) goto L260;
     */
    /* JADX WARN: Code restructure failed: missing block: B:260:0x0110, code lost:
        r3.close();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:262:0x0114, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:263:0x0115, code lost:
        r2 = com.jingdong.aura.a.b.l.c.f12123i;
        r3 = r0.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:265:0x011c, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x011d, code lost:
        com.jingdong.aura.a.b.l.c.f12123i.b(r0.getMessage(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x0126, code lost:
        r5.close();
        r3.close();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:268:0x012c, code lost:
        if (r3 != null) goto L269;
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x012e, code lost:
        r3.close();
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x0132, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:272:0x0133, code lost:
        r2 = com.jingdong.aura.a.b.l.c.f12123i;
        r3 = r0.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:274:0x013a, code lost:
        r5.close();
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0140, code lost:
        if (r3 != null) goto L276;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x0142, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:278:0x0146, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:279:0x0147, code lost:
        r2 = com.jingdong.aura.a.b.l.c.f12123i;
        r3 = r0.getMessage();
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x014d, code lost:
        r2.b(r3, r0);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void c(File file) {
        ZipFile zipFile = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedInputStream bufferedInputStream2 = null;
        BufferedInputStream bufferedInputStream3 = null;
        zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    String a = com.jingdong.aura.core.util.e.a(this.a, file);
                    Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                    while (true) {
                        if (!entries.hasMoreElements()) {
                            break;
                        }
                        ZipEntry nextElement = entries.nextElement();
                        if (nextElement.getName().indexOf("bundleinfo.json") != -1) {
                            break;
                        }
                    }
                    com.jingdong.aura.core.util.d.a(zipFile2);
                } catch (Exception e2) {
                    e = e2;
                    zipFile = zipFile2;
                    f12123i.b(e.getMessage(), e);
                    com.jingdong.aura.core.util.d.a(zipFile);
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    com.jingdong.aura.core.util.d.a(zipFile);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean a(String str) {
        if (str == null || !"file:".equalsIgnoreCase(this.d)) {
            return true;
        }
        File file = this.f12125e;
        return file != null && file.exists() && str.equals(com.jingdong.aura.core.util.d.b(this.f12125e.getAbsolutePath()));
    }

    /* JADX WARN: Removed duplicated region for block: B:236:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:272:0x00b8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean a(File file) {
        DataInputStream dataInputStream;
        boolean c2;
        if (file.exists()) {
            String a = com.jingdong.aura.core.util.e.a(this.a, this.f12125e);
            File[] listFiles = file.listFiles();
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                if (listFiles[i2].isFile()) {
                    File file2 = listFiles[i2];
                    String absolutePath = file2.getAbsolutePath();
                    if (absolutePath.endsWith(".so")) {
                        File file3 = new File(absolutePath + ".md5");
                        if (!file3.exists()) {
                            return false;
                        }
                        DataInputStream dataInputStream2 = null;
                        r9 = null;
                        r9 = null;
                        r9 = null;
                        String str = null;
                        try {
                            dataInputStream = new DataInputStream(new FileInputStream(file3));
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            dataInputStream = null;
                        } catch (IOException e3) {
                            e = e3;
                            dataInputStream = null;
                        } catch (Throwable th) {
                            th = th;
                        }
                        try {
                            try {
                                str = dataInputStream.readUTF();
                                try {
                                    dataInputStream.close();
                                } catch (IOException e4) {
                                    f12123i.b(e4.getMessage(), e4);
                                }
                            } catch (FileNotFoundException e5) {
                                e = e5;
                                f12123i.b(e.getMessage(), e);
                                if (dataInputStream != null) {
                                    dataInputStream.close();
                                }
                                c2 = com.jingdong.aura.a.b.c.c();
                                List<a.C0391a.C0392a> arrayList = new ArrayList<>();
                                if (c2) {
                                }
                                if (TextUtils.isEmpty(str)) {
                                }
                            } catch (IOException e6) {
                                e = e6;
                                f12123i.b(e.getMessage(), e);
                                if (dataInputStream != null) {
                                    dataInputStream.close();
                                }
                                c2 = com.jingdong.aura.a.b.c.c();
                                List<a.C0391a.C0392a> arrayList2 = new ArrayList<>();
                                if (c2) {
                                }
                                if (TextUtils.isEmpty(str)) {
                                }
                            }
                            c2 = com.jingdong.aura.a.b.c.c();
                            List<a.C0391a.C0392a> arrayList22 = new ArrayList<>();
                            if (c2) {
                                try {
                                    arrayList22 = com.jingdong.aura.a.a.a.a(this.a, a, file2.getName());
                                } catch (Throwable unused) {
                                    f12123i.d("getSoInfo error!");
                                }
                            }
                            if (TextUtils.isEmpty(str)) {
                                return false;
                            }
                            String b = com.jingdong.aura.core.util.d.b(absolutePath);
                            if (!str.equals(b)) {
                                return false;
                            }
                            com.jingdong.aura.core.util.l.b bVar = f12123i;
                            bVar.a(file2.getName() + " soMd5:" + b + " soInfoList:" + arrayList22 + " bundleSoCheckSwitch:" + c2);
                            if (c2 && (arrayList22 == null || arrayList22.isEmpty())) {
                                com.jingdong.aura.a.b.e.a(this.a, "soMd5ConfigEmptyCheck", file2.getName(), file2.getName() + " soMd5ConfigEmptyCheck fail! soInfoList is empty!", "soMd5ConfigEmptyCheck", null);
                                bVar.a(file2.getName() + " soInfoList is empty");
                                return true;
                            } else if (c2 && !a(b, arrayList22)) {
                                com.jingdong.aura.a.b.e.a(this.a, "soMd5ConfigCheck", file2.getName(), file2.getName() + "soMd5ConfigCheck fail! soMd5:" + b + " soInfoList:" + arrayList22, "soMd5ConfigCheck", null);
                                StringBuilder sb = new StringBuilder();
                                sb.append("so md5 check fail! soMd5:");
                                sb.append(b);
                                sb.append(" soInfoList:");
                                sb.append(arrayList22);
                                bVar.d(sb.toString());
                                return false;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            dataInputStream2 = dataInputStream;
                            Throwable th3 = th;
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (IOException e7) {
                                    f12123i.b(e7.getMessage(), e7);
                                }
                            }
                            throw th3;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return true;
        }
        return true;
    }

    public c(String str, long j2, File file, File file2, String str2) {
        this.a = str;
        this.f12124c = j2;
        this.b = file;
        if (file.exists()) {
            com.jingdong.aura.core.util.f.a(file);
        }
        file.mkdirs();
        this.f12126f = str2;
        String osBuild_HARDWARE = com.jingdong.aura.a.b.c.F().getOsBuild_HARDWARE();
        if (osBuild_HARDWARE != null && osBuild_HARDWARE.toLowerCase().contains("mt6592") && file2.getName().endsWith(".so")) {
            this.d = "file:";
            File file3 = new File(file, "bundle.zip");
            this.f12125e = file3;
            try {
                Runtime.getRuntime().exec(String.format("ln -s %s %s", file2.getAbsolutePath(), file3.getAbsolutePath())).waitFor();
            } catch (InterruptedException e2) {
                f12123i.b("wait ln -s for coopad failed.", e2);
            }
        } else {
            this.d = "reference:" + file2.getAbsolutePath();
            this.f12125e = file2;
        }
        if (j.a(this.f12125e)) {
            f12123i.a("find so:" + str);
            c(this.f12125e);
            b(file2);
        }
        b();
        k();
        this.f12128h = new e().a(this.a, (int) this.f12124c, this.f12125e, this.b);
    }

    public c(String str, long j2, File file) {
        this.a = str;
        File file2 = new File(file, MetaBox.TYPE);
        if (file2.exists()) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
            String readUTF = dataInputStream.readUTF();
            this.d = readUTF;
            this.f12126f = dataInputStream.readUTF();
            dataInputStream.close();
            this.f12124c = j2;
            this.b = file;
            if (!file.exists()) {
                file.mkdirs();
            }
            if (com.jingdong.aura.core.util.h.d(readUTF, "reference:")) {
                this.f12125e = new File(com.jingdong.aura.core.util.h.e(readUTF, "reference:"));
            } else {
                this.f12125e = new File(file, "bundle.zip");
            }
            if (this.f12125e.exists()) {
                this.f12128h = new e().a(str, (int) j2, this.f12125e, file);
                if (com.jingdong.aura.a.b.c.D()) {
                    this.f12128h.c();
                } else {
                    this.f12128h.a();
                }
                f12123i.a("check so from bundle Archive:" + str);
                b();
                return;
            }
            throw new IOException("Restore from existed bundle failed! Could not find bundleFile " + this.f12125e.getAbsolutePath());
        }
        throw new IOException("Could not find meta file in " + file.getAbsolutePath());
    }

    private boolean a(String str, List<a.C0391a.C0392a> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return true;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str2 = list.get(i2).d;
            if (str2 != null && str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public Class<?> a(String str, ClassLoader classLoader) {
        try {
            if (!i()) {
                j();
            }
            return this.f12128h.d().loadClass(str, classLoader);
        } catch (IllegalArgumentException e2) {
            f12123i.b(e2.getMessage(), e2);
            return null;
        } catch (Throwable th) {
            if (!(th instanceof ClassNotFoundException)) {
                f12123i.a("Exception while find class in archive revision: " + this.f12125e.getAbsolutePath(), th);
            }
            return null;
        }
    }

    private void a(File file, File file2) {
        j b = j.b(file);
        if (b == null || b.f12137f.size() <= 0) {
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    File file3 = new File(file2, "manual.ini");
                    if (!file3.exists()) {
                        file3.createNewFile();
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                    try {
                        Properties properties = new Properties();
                        Iterator<String> it = b.f12137f.iterator();
                        String str = "";
                        while (it.hasNext()) {
                            str = it.next() + DYConstants.DY_REGEX_COMMA + str;
                        }
                        properties.setProperty("manualComponents", str);
                        properties.store(fileOutputStream2, (String) null);
                        fileOutputStream2.close();
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }
}
