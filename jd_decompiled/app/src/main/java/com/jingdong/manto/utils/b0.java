package com.jingdong.manto.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes16.dex */
public final class b0 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Comparator<String> {
        a() {
        }

        public int a(String str, String str2) {
            if (str.equals(str2)) {
                return 0;
            }
            int i2 = -1;
            int i3 = -1;
            for (c cVar : c.values()) {
                String str3 = cVar.a;
                Locale locale = Locale.US;
                if (str3.equals(str.toLowerCase(locale))) {
                    i2 = cVar.b;
                }
                if (cVar.a.equals(str2.toLowerCase(locale))) {
                    i3 = cVar.b;
                }
                if (i2 != -1 && i3 != -1) {
                    break;
                }
            }
            return i3 - i2;
        }

        @Override // java.util.Comparator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return a(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public static class b {
        String a;
        long b;

        public b(String str, long j2) {
            this.a = str;
            this.b = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public enum c {
        arm64_v8a("arm64-v8a", 8),
        armeabi_v7("armeabi-v7a", 7),
        armeabi("armeabi", 6),
        x86_64("x86_64", 5),
        x86("x86", 4),
        mips64("mips64", 5),
        mips("mips", 4),
        unknown("unknown", 0);
        
        String a;
        int b;

        c(String str, int i2) {
            this.a = str;
            this.b = i2;
        }
    }

    public static String a(Context context) {
        try {
            String[] a2 = a();
            String a3 = a(context, a2);
            return (a2 == null || a3 != null) ? a3 : a2[0];
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String a(Context context, String... strArr) {
        ApplicationInfo applicationInfo;
        if (context == null || (applicationInfo = context.getApplicationInfo()) == null) {
            return null;
        }
        HashMap<String, ArrayList<b>> a2 = a(applicationInfo.sourceDir);
        Set<String> keySet = a2.keySet();
        if (keySet.isEmpty()) {
            return null;
        }
        String[] strArr2 = (String[]) keySet.toArray(new String[keySet.size()]);
        if (strArr2.length != 1 || strArr2.length <= 0) {
            String[] a3 = a(strArr2);
            File file = new File(applicationInfo.nativeLibraryDir);
            if (file.exists() && file.isDirectory()) {
                for (String str : a3) {
                    ArrayList<b> arrayList = a2.get(str);
                    if (arrayList != null) {
                        Iterator<b> it = arrayList.iterator();
                        if (it.hasNext()) {
                            b next = it.next();
                            File file2 = new File(file, next.a);
                            if (file2.exists() && file2.isFile()) {
                                file2.length();
                                long j2 = next.b;
                            }
                        }
                        return str;
                    }
                }
                if (strArr == null) {
                    return null;
                }
                for (String str2 : strArr) {
                    if (keySet.contains(str2)) {
                        return str2;
                    }
                }
            }
            return null;
        }
        return strArr2[0];
    }

    private static HashMap<String, ArrayList<b>> a(String str) {
        HashMap<String, ArrayList<b>> hashMap = new HashMap<>();
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(str);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        if (nextElement != null) {
                            String name = nextElement.getName();
                            if (!name.contains("../") && name.startsWith("lib/") && name.endsWith(".so")) {
                                String[] split = name.split("/", 3);
                                String str2 = split[1];
                                String str3 = split[2];
                                ArrayList<b> arrayList = hashMap.get(str2);
                                if (arrayList == null) {
                                    arrayList = new ArrayList<>();
                                    hashMap.put(str2, arrayList);
                                }
                                arrayList.add(new b(str3, nextElement.getSize()));
                            }
                        }
                    }
                    try {
                        zipFile2.close();
                    } catch (Throwable unused) {
                    }
                    return hashMap;
                } catch (Exception e2) {
                    e = e2;
                    zipFile = zipFile2;
                    throw new RuntimeException(String.format("getLibraryABIsFromApk file %s fail", str), e);
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Throwable unused2) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003b A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] a() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 2
            r2 = 1
            r3 = 0
            r4 = 21
            if (r0 < r4) goto L3c
            java.lang.String[] r0 = android.os.Build.SUPPORTED_ABIS
            if (r0 == 0) goto L3c
            int r4 = r0.length
            if (r4 <= 0) goto L3c
            int r4 = r0.length
            if (r4 != r2) goto L34
            r4 = r0[r3]
            if (r4 == 0) goto L34
            com.jingdong.manto.utils.b0$c r4 = com.jingdong.manto.utils.b0.c.armeabi_v7
            java.lang.String r5 = r4.a
            r6 = r0[r3]
            java.lang.String r6 = r6.toLowerCase()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L34
            java.lang.String[] r0 = new java.lang.String[r1]
            java.lang.String r4 = r4.a
            r0[r3] = r4
            com.jingdong.manto.utils.b0$c r4 = com.jingdong.manto.utils.b0.c.armeabi
            java.lang.String r4 = r4.a
            r0[r2] = r4
            goto L38
        L34:
            java.lang.String[] r0 = a(r0)
        L38:
            int r4 = r0.length
            if (r4 <= 0) goto L3c
            return r0
        L3c:
            java.lang.String[] r0 = new java.lang.String[r1]
            java.lang.String r1 = android.os.Build.CPU_ABI
            r0[r3] = r1
            java.lang.String r1 = android.os.Build.CPU_ABI2
            r0[r2] = r1
            java.lang.String[] r0 = a(r0)
            int r1 = r0.length
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.utils.b0.a():java.lang.String[]");
    }

    private static String[] a(String... strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        }
        String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        Arrays.sort(strArr2, new a());
        return strArr2;
    }
}
