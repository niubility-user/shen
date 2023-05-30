package com.jingdong.manto.t;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.utils.MantoCryptoUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.n;
import com.jingdong.manto.utils.s;
import com.jingdong.manto.utils.z;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes16.dex */
public class c {
    private static final String a = "c";
    private static final Collection<d> b;

    /* renamed from: c */
    private static final com.jingdong.manto.t.d f14208c = new a();

    /* loaded from: classes16.dex */
    class a extends com.jingdong.manto.t.d {
        a() {
        }

        public String toString() {
            return "MantoTempFileObject:Nil";
        }
    }

    /* loaded from: classes16.dex */
    public class b implements FileFilter {
        b() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.exists() && !file.isDirectory() && !MantoStringUtils.isEmpty(file.getName()) && file.getName().startsWith("store_");
        }
    }

    /* renamed from: com.jingdong.manto.t.c$c */
    /* loaded from: classes16.dex */
    public class RunnableC0674c implements Runnable {
        final /* synthetic */ String a;

        /* renamed from: com.jingdong.manto.t.c$c$a */
        /* loaded from: classes16.dex */
        class a implements FilenameFilter {
            a(RunnableC0674c runnableC0674c) {
            }

            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return MantoStringUtils.optional(str, "").startsWith("tmp_");
            }
        }

        RunnableC0674c(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            File[] listFiles = new File(c.e(this.a)).listFiles(new a(this));
            if (listFiles == null || listFiles.length <= 0) {
                return;
            }
            for (File file : listFiles) {
                s.b(file);
            }
        }
    }

    /* loaded from: classes16.dex */
    public interface d {
        com.jingdong.manto.t.d a(com.jingdong.manto.t.d dVar);

        com.jingdong.manto.t.d a(String str, String str2);

        com.jingdong.manto.v.a.a a(String str, String str2, String str3);

        com.jingdong.manto.t.d b(String str, String str2);
    }

    /* loaded from: classes16.dex */
    private static final class e implements d {
        private e() {
        }

        /* synthetic */ e(a aVar) {
            this();
        }

        @Override // com.jingdong.manto.t.c.d
        public com.jingdong.manto.t.d a(com.jingdong.manto.t.d dVar) {
            if (dVar == null) {
                return null;
            }
            String replaceFirst = dVar.b.replaceFirst("tmp_", "store_");
            if (com.jingdong.manto.t.a.a(dVar.b, replaceFirst)) {
                com.jingdong.manto.t.d dVar2 = new com.jingdong.manto.t.d();
                dVar2.b = replaceFirst;
                dVar2.a = dVar.a.replaceFirst("tmp_", "store_");
                dVar2.f14211f = dVar.f14211f;
                dVar2.d = dVar.d;
                dVar2.f14210e = new File(dVar2.b).lastModified();
                dVar2.f14209c = dVar.f14209c;
                dVar2.f14212g = true;
                return dVar2;
            }
            return null;
        }

        @Override // com.jingdong.manto.t.c.d
        public com.jingdong.manto.t.d a(String str, String str2) {
            String str3 = null;
            if (str2.startsWith("store_") || str2.startsWith("tmp_")) {
                String a = z.a(str2);
                String replaceFirst = str2.replaceFirst("store_", "").replaceFirst("tmp_", "");
                if (!MantoStringUtils.isEmpty(a)) {
                    replaceFirst = replaceFirst.replaceFirst(OrderISVUtil.MONEY_DECIMAL + a, "");
                }
                if (MantoStringUtils.isEmpty(replaceFirst)) {
                    return c.f14208c;
                }
                try {
                    str3 = c.c(replaceFirst, str);
                } catch (Throwable th) {
                    MantoLog.e("MediaObjectInfoHandler", String.format("retrieveFileObject, decrypt exp :%s", MantoStringUtils.throwable2String(th)));
                }
                if (MantoStringUtils.isEmpty(str3)) {
                    MantoLog.d("MediaObjectInfoHandler", "retrieveFileObject, get empty decrypted string");
                } else {
                    String[] split = str3.split(DYConstants.DY_REGEX_VERTICAL_LINE);
                    if (split.length != 2) {
                        return c.f14208c;
                    }
                    String str4 = c.e(str) + (str2.startsWith("store_") ? "store_" : "tmp_") + replaceFirst;
                    long j2 = MantoStringUtils.getLong(split[0], 0L);
                    String str5 = split[1];
                    if (!str5.equalsIgnoreCase(a)) {
                        return c.f14208c;
                    }
                    try {
                        long d = c.d(str4);
                        if (j2 != d) {
                            MantoLog.e("MediaObjectInfoHandler", String.format("retrieveFileObject, exactCRC32(%d) != fileCRC32(%d), localId(%s), appUniqueId(%s)", Long.valueOf(d), Long.valueOf(j2), str2, str));
                            return c.f14208c;
                        }
                        com.jingdong.manto.t.d dVar = new com.jingdong.manto.t.d();
                        dVar.a = "jdfile://" + str2;
                        dVar.b = str4;
                        dVar.f14209c = z.c(str5);
                        dVar.f14212g = str3.equalsIgnoreCase("store_");
                        File file = new File(dVar.b);
                        dVar.f14210e = file.lastModified();
                        dVar.f14211f = file.length();
                        return dVar;
                    } catch (Throwable th2) {
                        MantoLog.e("MediaObjectInfoHandler", String.format("retrieveFileObject, getCRC exp = %s", MantoStringUtils.throwable2String(th2)));
                    }
                }
                return c.f14208c;
            }
            return null;
        }

        @Override // com.jingdong.manto.t.c.d
        public com.jingdong.manto.v.a.a a(String str, String str2, String str3) {
            String str4;
            String optional = MantoStringUtils.optional(str3, "unknown");
            try {
                String d = c.d(String.format(Locale.US, "%d|%s", Long.valueOf(c.d(str2)), optional), str);
                if (MantoStringUtils.isEmpty(d)) {
                    return null;
                }
                String str5 = "tmp_" + d;
                String str6 = c.e(str) + str5;
                StringBuilder sb = new StringBuilder();
                sb.append("jdfile://");
                sb.append(str5);
                if (MantoStringUtils.isEmpty(optional)) {
                    str4 = "";
                } else {
                    str4 = OrderISVUtil.MONEY_DECIMAL + optional;
                }
                sb.append(str4);
                String sb2 = sb.toString();
                MantoLog.d("MediaObjectInfoHandler", String.format("attachFileObject, return localId = %s, filePath = %s", sb2, str6));
                com.jingdong.manto.v.a.c cVar = new com.jingdong.manto.v.a.c();
                cVar.a = new Object[]{sb2, str6, optional};
                return cVar;
            } catch (Throwable th) {
                MantoLog.e("MediaObjectInfoHandler", String.format("attachFileObject, get crc exp = %s", MantoStringUtils.throwable2String(th)));
                return null;
            }
        }

        @Override // com.jingdong.manto.t.c.d
        public com.jingdong.manto.t.d b(String str, String str2) {
            String str3;
            String str4 = "";
            String replaceFirst = str2.replaceFirst("store_", "").replaceFirst("tmp_", "");
            try {
                str3 = c.c(replaceFirst, str);
            } catch (Throwable th) {
                MantoLog.e("MediaObjectInfoHandler", String.format("retrieveFileObjectByRealFileName, dec exp :%s", MantoStringUtils.throwable2String(th)));
                str3 = null;
            }
            if (MantoStringUtils.isEmpty(str3)) {
                return null;
            }
            String[] split = str3.split(DYConstants.DY_REGEX_VERTICAL_LINE);
            if (split.length != 2) {
                return null;
            }
            String str5 = split[1];
            StringBuilder sb = new StringBuilder("jdfile://");
            sb.append(str2);
            if (!MantoStringUtils.isEmpty(str5)) {
                str4 = OrderISVUtil.MONEY_DECIMAL + str5;
            }
            sb.append(str4);
            String sb2 = sb.toString();
            com.jingdong.manto.t.d dVar = new com.jingdong.manto.t.d();
            dVar.a = sb2;
            dVar.b = c.e(str) + str2;
            dVar.d = replaceFirst;
            dVar.f14212g = true;
            File file = new File(dVar.b);
            dVar.f14210e = file.lastModified();
            dVar.f14211f = file.length();
            return dVar;
        }
    }

    static {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new e(null));
        b = Collections.unmodifiableCollection(linkedList);
    }

    public static com.jingdong.manto.t.d a(String str, com.jingdong.manto.t.d dVar) {
        Iterator<d> it = b.iterator();
        com.jingdong.manto.t.d dVar2 = null;
        while (it.hasNext() && (dVar2 = it.next().a(dVar)) == null) {
        }
        return dVar2;
    }

    private static <T extends com.jingdong.manto.t.d> T a(String str, String str2, Class<T> cls, String str3, boolean z) {
        if (!MantoStringUtils.isEmpty(str) && s.d(str2)) {
            String i2 = i(z.a(str2), str3);
            Iterator<d> it = b.iterator();
            com.jingdong.manto.v.a.a aVar = null;
            while (it.hasNext() && (aVar = it.next().a(str, str2, i2)) == null) {
            }
            if (aVar != null && aVar.a() >= 2) {
                try {
                    T newInstance = cls.newInstance();
                    newInstance.a = (String) aVar.a(0);
                    newInstance.f14209c = z.c(i2);
                    String str4 = (String) aVar.a(1);
                    newInstance.b = str4;
                    if (MantoStringUtils.isEmpty(str4)) {
                        MantoLog.e(a, String.format("attachCast appUniqueId %s, Null Or Nil", str));
                        return null;
                    }
                    newInstance.d = MantoStringUtils.optional((String) aVar.a(3), MantoStringUtils.optional(i2, "unknown"));
                    if (a(z, str2, newInstance.b)) {
                        File file = new File(newInstance.b);
                        newInstance.f14211f = file.length();
                        newInstance.f14210e = file.lastModified();
                        return newInstance;
                    }
                    return null;
                } catch (Throwable th) {
                    MantoLog.e(a, String.format("%s", MantoStringUtils.throwable2String(th)));
                    return null;
                }
            }
            String str5 = a;
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(aVar == null ? -1 : aVar.a());
            MantoLog.e(str5, String.format("attachCast, no handler return correct info, attach.size = %d", objArr));
        }
        return null;
    }

    public static com.jingdong.manto.t.d a(String str, String str2, String str3, boolean z) {
        return a(str, str2, com.jingdong.manto.t.d.class, str3, z);
    }

    public static com.jingdong.manto.t.d a(String str, String str2, boolean z) {
        return a(str, str2, null, z);
    }

    private static boolean a(boolean z, String str, String str2) {
        if (z && com.jingdong.manto.t.a.a(str, str2)) {
            return true;
        }
        return !z && s.a(str, str2);
    }

    public static com.jingdong.manto.t.e b(String str, String str2, boolean z) {
        return (com.jingdong.manto.t.e) a(str, str2, com.jingdong.manto.t.e.class, null, z);
    }

    public static String c(String str, String str2) {
        String b2 = MantoCryptoUtils.b(str2, str);
        MantoLog.d(a, "decrypt from: " + str + " to : " + b2);
        return b2;
    }

    public static void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.jingdong.manto.b.d().diskIO().execute(new RunnableC0674c(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long d(java.lang.String r5) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.IOException -> L23
            r1.<init>(r5)     // Catch: java.io.IOException -> L23
            java.util.zip.CheckedInputStream r5 = new java.util.zip.CheckedInputStream     // Catch: java.io.IOException -> L1e
            java.util.zip.Adler32 r2 = new java.util.zip.Adler32     // Catch: java.io.IOException -> L1e
            r2.<init>()     // Catch: java.io.IOException -> L1e
            r5.<init>(r1, r2)     // Catch: java.io.IOException -> L1e
            r0 = 2048(0x800, float:2.87E-42)
            byte[] r0 = new byte[r0]     // Catch: java.io.IOException -> L1c
        L14:
            int r2 = r5.read(r0)     // Catch: java.io.IOException -> L1c
            r3 = -1
            if (r2 != r3) goto L14
            goto L2a
        L1c:
            r0 = move-exception
            goto L27
        L1e:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
            goto L27
        L23:
            r5 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
        L27:
            r0.printStackTrace()
        L2a:
            if (r5 != 0) goto L2f
            r2 = 0
            goto L37
        L2f:
            java.util.zip.Checksum r0 = r5.getChecksum()
            long r2 = r0.getValue()
        L37:
            com.jingdong.manto.utils.t.a(r5)
            com.jingdong.manto.utils.t.a(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.t.c.d(java.lang.String):long");
    }

    public static String d(String str, String str2) {
        String c2 = MantoCryptoUtils.c(str2, str);
        MantoLog.d(a, "encrypt from: " + str + " to : " + c2);
        return c2;
    }

    public static String e(String str) {
        String f2 = f(str);
        s.b(f2);
        return f2;
    }

    public static String e(String str, String str2) {
        if (MantoStringUtils.isEmpty(str) || MantoStringUtils.isEmpty(str2)) {
            return null;
        }
        return e(str) + str2;
    }

    private static com.jingdong.manto.t.d f(String str, String str2) {
        com.jingdong.manto.t.d dVar = null;
        if (!MantoStringUtils.isEmpty(str) && !MantoStringUtils.isEmpty(str2)) {
            Iterator<d> it = b.iterator();
            while (it.hasNext() && (dVar = it.next().b(str, str2)) == null) {
            }
        }
        return dVar;
    }

    private static String f(String str) {
        return n.f14314c + str + "/files/";
    }

    public static long g(String str) {
        File[] i2 = i(str);
        long j2 = 0;
        if (i2 != null && i2.length > 0) {
            for (File file : i2) {
                j2 += file.length();
            }
        }
        return j2;
    }

    @Deprecated
    public static com.jingdong.manto.t.d g(String str, String str2) {
        File file;
        if (MantoStringUtils.isEmpty(str2) || !str2.startsWith("jdfile://") || MantoStringUtils.isEmpty(str)) {
            MantoLog.e(a, String.format("getItemByLocalId, invalid args, localId(%s), appUniqueId(%s) ", str2, str));
            return null;
        } else if (!str2.startsWith("jdfile://usr/")) {
            String substring = str2.substring(9);
            Iterator<d> it = b.iterator();
            com.jingdong.manto.t.d dVar = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                d next = it.next();
                com.jingdong.manto.t.d a2 = next.a(str, substring);
                if (a2 != null) {
                    MantoLog.i(a, String.format("getItemByLocalId, handled by %s, result = %s", next.toString(), a2));
                    dVar = a2;
                    break;
                }
                dVar = a2;
            }
            if (f14208c != dVar) {
                return dVar;
            }
            return null;
        } else {
            if (str2.length() > 13) {
                file = new File(e(str) + "usr/" + str2.substring(13));
                File parentFile = file.getParentFile();
                if (!parentFile.exists() || !parentFile.isDirectory()) {
                    parentFile.mkdirs();
                }
            } else {
                file = new File(e(str) + "usr/");
                if (!file.exists() || !file.isDirectory()) {
                    file.mkdirs();
                }
            }
            com.jingdong.manto.t.d dVar2 = new com.jingdong.manto.t.d();
            dVar2.a = str2;
            dVar2.b = file.getAbsolutePath();
            dVar2.f14209c = z.d(str2);
            dVar2.f14212g = true;
            dVar2.f14211f = file.length();
            dVar2.f14210e = file.lastModified();
            return dVar2;
        }
    }

    public static List<com.jingdong.manto.t.d> h(String str) {
        File[] i2 = i(str);
        if (i2 == null || i2.length <= 0) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        for (File file : i2) {
            com.jingdong.manto.t.d f2 = f(str, file.getName());
            if (f2 != null) {
                linkedList.add(f2);
            }
        }
        return linkedList;
    }

    public static boolean h(String str, String str2) {
        String str3;
        if (MantoStringUtils.isEmpty(str2) || !str2.startsWith("jdfile://") || MantoStringUtils.isEmpty(str)) {
            MantoLog.e(a, String.format("getItemByLocalId, invalid args, localId(%s), appUniqueId(%s) ", str2, str));
            return false;
        } else if (str2.startsWith("jdfile://usr/")) {
            if (str2.length() > 13) {
                str3 = e(str) + "usr/" + str2.substring(13);
            } else {
                str3 = e(str) + "usr/";
            }
            return new File(str3).exists();
        } else {
            String substring = str2.substring(9);
            com.jingdong.manto.t.d dVar = null;
            Iterator<d> it = b.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                d next = it.next();
                com.jingdong.manto.t.d a2 = next.a(str, substring);
                if (a2 != null) {
                    MantoLog.i(a, String.format("isLocalFileExist, handled by %s, result = %s", next.toString(), a2));
                    dVar = a2;
                    break;
                }
                dVar = a2;
            }
            return f14208c != dVar;
        }
    }

    private static String i(String str, String str2) {
        return MantoStringUtils.isEmpty(str) ? str2 : str;
    }

    private static File[] i(String str) {
        File file = new File(e(str));
        if (file.exists() && file.isDirectory()) {
            return file.listFiles(new b());
        }
        return null;
    }
}
