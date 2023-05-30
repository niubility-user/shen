package com.jingdong.aura.a.b.k;

import android.app.Application;
import android.text.TextUtils;
import com.coremedia.iso.boxes.MetaBox;
import com.jd.dynamic.DYConstants;
import com.jingdong.aura.a.b.f;
import com.jingdong.aura.core.util.g;
import com.jingdong.aura.core.util.h;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public final class b {
    private static d b;
    private static e d;

    /* renamed from: e */
    private static ClassLoader f12113e;

    /* renamed from: i */
    private static String f12117i;
    private static final com.jingdong.aura.core.util.l.b a = com.jingdong.aura.core.util.l.c.a("Framework");

    /* renamed from: c */
    private static boolean f12112c = false;

    /* renamed from: f */
    private static List<Object> f12114f = new ArrayList();

    /* renamed from: g */
    private static Map<String, List<Object>> f12115g = new HashMap();

    /* renamed from: h */
    private static String f12116h = null;

    /* renamed from: j */
    private static List<String> f12118j = new ArrayList();

    /* renamed from: k */
    private static int f12119k = 1;

    /* renamed from: l */
    private static int f12120l = 0;

    static {
        new ConcurrentHashMap();
        new HashMap();
    }

    public static void a(Application application, Properties properties) {
        b = new d(properties);
        a(application);
    }

    public static void b(l.b.a.e eVar) {
        c.b(eVar);
    }

    public static List<l.b.a.d> c() {
        return a.a();
    }

    public static Map<String, List<Object>> d() {
        return f12115g;
    }

    public static int e() {
        return f12119k;
    }

    public static List f() {
        return f12114f;
    }

    public static int g() {
        return f12120l;
    }

    public static l.b.a.d h() {
        return d;
    }

    public static ClassLoader i() {
        return f12113e;
    }

    private static void j() {
        File a2 = com.jingdong.aura.core.util.d.a();
        if (a2 == null || !a2.exists()) {
            a2 = com.jingdong.aura.core.util.d.a();
        }
        f12116h = b.a("com.aura.basedir", a2.getAbsolutePath());
        b.a("com.aura.jars", "file:" + f12116h);
        b.a("com.aura.classloader.buffersize", 10240);
        b.a("com.aura.debug.packages", false);
        b.a("com.aura.debug.services", false);
        b.a("com.aura.debug.classloading", false);
        if (b.a("com.aura.debug", false)) {
            a.b("SETTING ALL DEBUG FLAGS");
        }
        b.a("com.aura.strictStartup", false);
        String a3 = b.a("org.osgi.framework.system.packages");
        if (a3 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(a3, DYConstants.DY_REGEX_COMMA);
            int countTokens = stringTokenizer.countTokens();
            for (int i2 = 0; i2 < countTokens; i2++) {
                f.a(stringTokenizer.nextToken().trim());
            }
        }
        b.b("org.osgi.framework.executionenvironment", System.getProperty("java.specification.name") + "/" + System.getProperty("java.specification.version"));
        String property = System.getProperty("os.name");
        if (property == null) {
            property = "undefined";
        }
        b.b("org.osgi.framework.os.name", property);
        String property2 = System.getProperty("os.version");
        if (property2 == null) {
            property2 = "undefined";
        }
        b.b("org.osgi.framework.os.version", property2);
        String property3 = System.getProperty("os.arch");
        b.b("org.osgi.framework.processor", property3 != null ? property3 : "undefined");
        b.b("org.osgi.framework.version", "1.4.7.7");
        b.b("org.osgi.framework.vendor", "Aura");
        String language = Locale.getDefault().getLanguage();
        if (language == null) {
            language = "en";
        }
        b.b("org.osgi.framework.language", language);
    }

    public static boolean k() {
        return f12112c;
    }

    private static void l() {
        StringBuilder sb = new StringBuilder();
        d dVar = b;
        sb.append(dVar.a("com.aura.storage", dVar.a("org.osgi.framework.dir", f12116h + File.separatorChar + "aura")));
        sb.append(File.separatorChar);
        f12117i = sb.toString();
        e eVar = new e();
        d = eVar;
        eVar.a(8);
    }

    private static int m() {
        try {
            com.jingdong.aura.core.util.l.b bVar = a;
            bVar.b("Restoring profile");
            File file = new File(f12117i, MetaBox.TYPE);
            if (!file.exists()) {
                bVar.b("Profile not found, performing clean start ...");
                return -1;
            }
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int readInt = dataInputStream.readInt();
            String[] c2 = h.c(dataInputStream.readUTF(), DYConstants.DY_REGEX_COMMA);
            if (c2 != null) {
                f12118j.addAll(Arrays.asList(c2));
            }
            dataInputStream.close();
            return readInt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    static void n() {
        try {
            File file = new File(f12117i, MetaBox.TYPE);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e2) {
                    a.b(e2.getMessage(), e2);
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeInt(f12120l);
            String a2 = h.a(f12118j.toArray(), DYConstants.DY_REGEX_COMMA);
            if (a2 == null) {
                a2 = "";
            }
            dataOutputStream.writeUTF(a2);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e3) {
            com.jingdong.aura.a.b.e.a("com.jingdong.aura", "storeMetadata failed", "Framework.storeMetadata", e3);
            a.a("Could not save meta data.", e3);
        }
    }

    private static void o() {
        for (com.jingdong.aura.a.b.h hVar : (com.jingdong.aura.a.b.h[]) c().toArray(new com.jingdong.aura.a.b.h[c().size()])) {
            hVar.p();
        }
        n();
    }

    public static void p() {
        List<String> a2 = com.jingdong.aura.a.a.a.c().a();
        if (a2 == null) {
            return;
        }
        for (String str : a2) {
            if (str != null) {
                l.b.a.d b2 = b(str);
                if (b2 != null) {
                    try {
                        b2.c();
                    } catch (l.b.a.b e2) {
                        e2.printStackTrace();
                    }
                }
                a(str);
            }
        }
        com.jingdong.aura.core.util.f.a();
    }

    public static l.b.a.d b(String str) {
        return a.a(str);
    }

    public static String c(String str) {
        return b.a(str);
    }

    public static void d(String str) {
        a.b(str);
    }

    private static void a(Application application) {
        int m2;
        boolean z;
        f12112c = true;
        com.jingdong.aura.core.util.l.b bVar = a;
        bVar.e("---------------------------------------------------------");
        bVar.e("  Aura 1.4.7.7 starting ...");
        bVar.e("---------------------------------------------------------");
        long currentTimeMillis = System.currentTimeMillis();
        j();
        l();
        boolean a2 = b.a("osgi.init", false);
        if (a2) {
            m2 = -1;
            z = false;
        } else {
            m2 = m();
            z = true;
        }
        if (m2 == -1) {
            File file = new File(f12117i);
            boolean d2 = g.d(application);
            if (a2 && file.exists() && d2) {
                bVar.b("Purging storage ...");
                try {
                    com.jingdong.aura.core.util.f.a(file);
                } catch (Throwable th) {
                    a.b("first start delete aura cache fail!", th);
                    throw new RuntimeException("deleteDirectory failed", th);
                }
            }
            if (a2 && d2) {
                bVar.b("deleting storage Directory ...");
                com.jingdong.aura.core.util.f.a();
            }
            try {
                file.mkdirs();
                Integer.getInteger("osgi.maxLevel", (Integer) 1).intValue();
                f12119k = b.a("osgi.startlevel.bundle", 1);
                m2 = b.a("osgi.startlevel.framework", 1);
                z = false;
            } catch (Throwable th2) {
                throw new RuntimeException("mkdirs failed", th2);
            }
        }
        c.a(0, d, null);
        d.a((l.b.a.d[]) a.a().toArray(new l.b.a.d[a.b()]), m2, false);
        f12112c = false;
        if (!z) {
            try {
                o();
            } catch (Throwable th3) {
                throw new RuntimeException("storeProfile failed", th3);
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        bVar.e("---------------------------------------------------------");
        StringBuilder sb = new StringBuilder();
        sb.append("  Framework ");
        sb.append(z ? "restarted" : "started");
        sb.append(" in ");
        sb.append(currentTimeMillis2);
        sb.append(" milliseconds.");
        bVar.e(sb.toString());
        bVar.e("---------------------------------------------------------");
        d.a(32);
        try {
            c.a(1, d, null);
        } catch (Throwable th4) {
            throw new RuntimeException("notifyFrameworkListeners failed", th4);
        }
    }

    public static File b() {
        return new File(f12117i);
    }

    public static com.jingdong.aura.a.b.h a(String str, File file, InputStream inputStream, long j2, String str2) {
        com.jingdong.aura.a.b.h hVar;
        String str3;
        com.jingdong.aura.a.b.h a2;
        if (str == null) {
            a.a("location is null");
            return null;
        }
        boolean i2 = com.jingdong.aura.a.a.a.c().i(str);
        if (!i2 && file == null && inputStream == null) {
            a.a("apkFile or archiveInputStream is null");
            throw new RuntimeException("apkFile or archiveInputStream is null");
        }
        com.jingdong.aura.a.b.h hVar2 = (com.jingdong.aura.a.b.h) b(str);
        if (hVar2 != null) {
            return hVar2;
        }
        File file2 = new File(f12117i, str);
        try {
            com.jingdong.aura.core.util.c.a(str);
            com.jingdong.aura.core.util.a.a().a(file2);
            com.jingdong.aura.a.b.h hVar3 = (com.jingdong.aura.a.b.h) b(str);
            if (hVar3 != null) {
                return hVar3;
            }
            if (!file2.exists() || (a2 = a(file2, str, j2, str2)) == null) {
                if (i2) {
                    RuntimeException runtimeException = new RuntimeException("can not install provided bundle:" + str);
                    if (com.jingdong.aura.a.b.c.O()) {
                        a.d("report provided bundle install fail:" + str);
                        boolean exists = file2.exists();
                        long j3 = 0;
                        boolean z = false;
                        String str4 = "";
                        if (exists) {
                            long c2 = com.jingdong.aura.a.b.l.h.c(file2);
                            str3 = com.jingdong.aura.a.b.l.h.a(file2, c2);
                            z = com.jingdong.aura.a.b.l.h.a(j2, str2, c2, str3);
                            str4 = file2.getAbsolutePath();
                            j3 = c2;
                        } else {
                            str3 = "";
                        }
                        com.jingdong.aura.a.b.e.a(str, "ProvidedBundleInstall", str, "install provided bundle fail: providedBundleArchiveFileExist:" + exists + " bundleArchiveFilePath:" + str4 + " versionCode:" + j2 + " md5:" + str2 + " currentMaxVersionCode:" + j3 + " currentMaxVersionCodeMd5:" + str3 + " needCreateNewBundle:" + z, "installNewBundle", runtimeException);
                    }
                    throw runtimeException;
                }
                String g2 = com.jingdong.aura.a.a.a.c().g(str);
                String str5 = (TextUtils.isEmpty(g2) || g2.equals(str2)) ? str2 : g2;
                if (file != null) {
                    hVar = new com.jingdong.aura.a.b.h(file2, str, j2, new com.jingdong.aura.a.b.g(), null, file, true, str5);
                } else {
                    hVar = new com.jingdong.aura.a.b.h(file2, str, j2, new com.jingdong.aura.a.b.g(), inputStream, null, true, str5);
                }
                n();
                return hVar;
            }
            return a2;
        } finally {
        }
    }

    public static boolean a(String str, InputStream inputStream, int i2, String str2) {
        boolean z = false;
        if (str != null && inputStream != null) {
            String valueOf = String.valueOf(System.currentTimeMillis());
            new File(new File(f12117i, "wal"), valueOf).mkdirs();
            com.jingdong.aura.core.util.c.a(str);
            try {
                com.jingdong.aura.a.b.h hVar = (com.jingdong.aura.a.b.h) b(str);
                if (hVar != null) {
                    hVar.a(inputStream, i2, str2);
                } else {
                    File file = new File(f12117i, str);
                    if (file.exists()) {
                        a.b("checkUpdateBundle. " + str);
                        com.jingdong.aura.a.b.h.a(file, str, i2, inputStream, str2);
                    } else {
                        a.b("new bundleImpl. " + str);
                        new com.jingdong.aura.a.b.h(file, str, (long) i2, new com.jingdong.aura.a.b.g(), inputStream, null, false, str2);
                    }
                }
                z = true;
                com.jingdong.aura.core.util.c.b(str);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    com.jingdong.aura.a.b.e.a(str, i2, "installOrUpdate failed.", "Framework.installOrUpdate", th);
                } finally {
                    com.jingdong.aura.core.util.c.b(str);
                }
            }
            f12118j.add(valueOf);
            n();
            return z;
        }
        a.e("locations and files must not be null");
        return false;
    }

    private static com.jingdong.aura.a.b.h a(File file, String str, long j2, String str2) {
        if (file == null || str == null) {
            return null;
        }
        com.jingdong.aura.core.util.l.b bVar = a;
        bVar.a("bundle:" + str + " bundleArchiveFile:" + file.getAbsolutePath());
        long c2 = com.jingdong.aura.a.b.l.h.c(file);
        String a2 = com.jingdong.aura.a.b.l.h.a(file, c2);
        bVar.a("bundleArchiveFile version:" + c2 + " md5:" + a2);
        bVar.a("host bundle version:" + j2 + " md5:" + str2);
        if (com.jingdong.aura.a.b.l.h.a(j2, str2, c2, a2)) {
            return null;
        }
        try {
            File file2 = new File(new File(file, "package_" + c2), "bundle.zip");
            if (com.jingdong.aura.a.b.c.U() && com.jingdong.aura.wrapper.c.a() && file2.exists()) {
                ZipFile zipFile = new ZipFile(file2, 1);
                if (zipFile.size() > 0) {
                    bVar.a("start verity bundle sign\uff01");
                    com.jingdong.aura.wrapper.b.a(file2, str);
                } else {
                    bVar.d("bundleZipFile size:" + zipFile.size());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return a(str, file, c2);
    }

    private static com.jingdong.aura.a.b.h a(String str, File file, long j2) {
        try {
            return new com.jingdong.aura.a.b.h(str, file, new com.jingdong.aura.a.b.g(), j2);
        } catch (Throwable th) {
            a.a("restore bundle failed: " + str, th);
            th.printStackTrace();
            com.jingdong.aura.a.b.e.a(str, "RestoreBundle", str, "restore bundle failed", "Framework.restoreFromExistedBundle", th);
            return null;
        }
    }

    public static void a(ClassLoader classLoader) {
        f12113e = classLoader;
    }

    public static void a(l.b.a.e eVar) {
        c.a(eVar);
    }

    public static void a(l.b.a.f fVar) {
        c.a(fVar);
    }

    public static void a(int i2, l.b.a.d dVar) {
        c.a(i2, dVar);
    }

    public static void a(int i2, l.b.a.d dVar, Throwable th) {
        c.a(i2, dVar, th);
    }

    public static String a() {
        List<l.b.a.d> c2 = c();
        if (c2 == null || c2.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("InstalledBundles[");
        for (l.b.a.d dVar : c2) {
            sb.append(dVar.b());
            sb.append(":");
            sb.append(((com.jingdong.aura.a.b.h) dVar).l());
            sb.append(";");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void a(String str, l.b.a.d dVar) {
        a.a(str, dVar);
    }

    public static void a(String str) {
        if (str == null) {
            return;
        }
        File file = new File(f12117i, str);
        com.jingdong.aura.core.util.a.a().a(file);
        if (file.exists()) {
            com.jingdong.aura.core.util.f.a(file);
        }
        com.jingdong.aura.core.util.a.a().b(file);
    }

    public static void a(int i2) {
        f12120l = i2;
    }
}
