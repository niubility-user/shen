package com.jingdong.aura.a.b.l;

import com.coremedia.iso.boxes.MetaBox;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class h {
    private static final String[] a = {"com.jd.lib.productdetail", "com.jd.lib.coupon", "com.jd.lib.mycoupon", "com.jd.lib.ordercenter", "com.jd.lib.live", "com.jd.mobiledd.sdk", "com.jd.lib.personal", "com.jd.lib.cart", "com.jd.lib.login", "com.jd.lib.settlement", "com.jd.lib.push", "com.jd.lib.category"};

    public static String a(File file, long j2) {
        return b(new File(file, "package_" + String.valueOf(j2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String b(File file) {
        Throwable th;
        IOException e2;
        DataInputStream dataInputStream;
        File file2 = new File(file, MetaBox.TYPE);
        DataInputStream dataInputStream2 = null;
        if (file2.exists()) {
            try {
                dataInputStream = new DataInputStream(new FileInputStream(file2));
            } catch (IOException e3) {
                e2 = e3;
                dataInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                if (dataInputStream2 != null) {
                }
                throw th;
            }
            try {
                try {
                    dataInputStream.readUTF();
                    String readUTF = dataInputStream.readUTF();
                    try {
                        dataInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return readUTF;
                } catch (Throwable th3) {
                    th = th3;
                    dataInputStream2 = dataInputStream;
                    if (dataInputStream2 != null) {
                        try {
                            dataInputStream2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace();
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException e7) {
                        e7.printStackTrace();
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static long c(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        long j2 = -1;
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0L;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > j2) {
                    j2 = parseLong;
                }
            }
        }
        return j2;
    }

    private static boolean d(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        long j2 = -1;
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > j2) {
                    j2 = parseLong;
                }
            }
        }
        if (-1 == j2) {
            return false;
        }
        boolean z = false;
        for (File file3 : listFiles) {
            if (file3.getName().startsWith("package") && Long.parseLong(com.jingdong.aura.core.util.h.e(file3.getName(), CartConstant.KEY_YB_INFO_LINK)) != j2) {
                com.jingdong.aura.core.util.f.a(file3);
                z = true;
            }
        }
        return z;
    }

    public static SortedMap<Long, c> e(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        TreeMap treeMap = new TreeMap();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return treeMap;
        }
        for (File file2 : listFiles) {
            if (file2.getName().startsWith("package")) {
                long parseLong = Long.parseLong(com.jingdong.aura.core.util.h.e(file2.getName(), CartConstant.KEY_YB_INFO_LINK));
                if (parseLong > 0) {
                    treeMap.put(Long.valueOf(parseLong), null);
                }
            }
        }
        return treeMap;
    }

    public static boolean a(long j2, String str, long j3, String str2) {
        int i2 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 > 0) {
            return true;
        }
        if (i2 != 0 || com.jingdong.aura.core.util.h.a(str)) {
            return false;
        }
        return com.jingdong.aura.core.util.h.a(str2) || !str.equals(str2);
    }

    public static boolean a(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || (listFiles = file.listFiles()) == null) {
            return false;
        }
        for (File file2 : listFiles) {
            if (file2.isDirectory() && file2.getName().startsWith("com.") && d(file2)) {
                return true;
            }
        }
        int i2 = 0;
        for (File file3 : listFiles) {
            String name = file3.getName();
            if (file3.isDirectory() && name.startsWith("com.") && com.jingdong.aura.a.b.k.b.b(name) == null && a(name)) {
                com.jingdong.aura.core.util.f.a(file3);
                i2++;
                if (i2 >= 2) {
                    return true;
                }
            }
        }
        return i2 > 0;
    }

    private static boolean a(String str) {
        if (com.jingdong.aura.core.util.h.a(str)) {
            return false;
        }
        for (String str2 : AuraConfig.AUTO) {
            if (str.equals(str2)) {
                return false;
            }
        }
        if (com.jingdong.aura.a.a.a.c().i(str)) {
            return false;
        }
        for (String str3 : a) {
            if (str.equals(str3)) {
                return false;
            }
        }
        return true;
    }
}
