package com.jingdong.aura.core.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.jingdong.aura.a.c.l;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class i {
    static {
        com.jingdong.aura.core.util.l.c.a("Utils");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0090: MOVE (r7 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:54:0x0090 */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(int i2) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        IOException e2;
        BufferedReader bufferedReader2;
        FileNotFoundException e3;
        InputStreamReader inputStreamReader2;
        InputStreamReader inputStreamReader3;
        InputStreamReader inputStreamReader4 = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream("/proc/" + i2 + "/cmdline"));
            } catch (FileNotFoundException e4) {
                inputStreamReader = null;
                e3 = e4;
                bufferedReader2 = null;
            } catch (IOException e5) {
                inputStreamReader = null;
                e2 = e5;
                bufferedReader2 = null;
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
                if (inputStreamReader4 != null) {
                }
                if (bufferedReader != null) {
                }
                throw th;
            }
            try {
                bufferedReader2 = new BufferedReader(inputStreamReader);
            } catch (FileNotFoundException e6) {
                e3 = e6;
                bufferedReader2 = null;
            } catch (IOException e7) {
                e2 = e7;
                bufferedReader2 = null;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader3 = inputStreamReader;
                bufferedReader = null;
                inputStreamReader4 = inputStreamReader3;
                if (inputStreamReader4 != null) {
                    try {
                        inputStreamReader4.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                }
                throw th;
            }
            try {
                char[] cArr = new char[256];
                bufferedReader2.read(cArr);
                int i3 = 0;
                for (int i4 = 0; i4 < 256 && cArr[i4] != 0; i4++) {
                    i3++;
                }
                String str = new String(cArr, 0, i3);
                try {
                    inputStreamReader.close();
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
                try {
                    bufferedReader2.close();
                } catch (IOException unused2) {
                }
                return str;
            } catch (FileNotFoundException e10) {
                e3 = e10;
                e3.printStackTrace();
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e11) {
                        e11.printStackTrace();
                    }
                }
                if (bufferedReader2 == null) {
                    return "";
                }
                try {
                    bufferedReader2.close();
                    return "";
                } catch (IOException unused3) {
                    return "";
                }
            } catch (IOException e12) {
                e2 = e12;
                e2.printStackTrace();
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e13) {
                        e13.printStackTrace();
                    }
                }
                if (bufferedReader2 == null) {
                    return "";
                }
                bufferedReader2.close();
                return "";
            }
        } catch (Throwable th3) {
            inputStreamReader3 = inputStreamReader2;
            bufferedReader = i2;
            th = th3;
        }
    }

    public static int b(Context context, String str) {
        PackageInfo a = a(context, str);
        if (a == null) {
            return 0;
        }
        return a.versionCode;
    }

    public static boolean c(String str) {
        try {
            return l.a.getPackageManager().getReceiverInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean d(String str) {
        try {
            return l.a.getPackageManager().getServiceInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean b(String str) {
        try {
            return l.a.getPackageManager().getProviderInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static PackageInfo a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static long a(boolean z) {
        long blockSizeLong;
        long availableBlocksLong;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (z) {
            try {
                if (Build.VERSION.SDK_INT >= 18) {
                    blockSizeLong = statFs.getBlockSizeLong();
                    availableBlocksLong = statFs.getAvailableBlocksLong();
                    return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                com.jingdong.aura.a.b.e.a("com.jingdong.aura", "", "getDataDiskFreeSize", th);
                return 400L;
            }
        }
        blockSizeLong = statFs.getBlockSize();
        availableBlocksLong = statFs.getAvailableBlocks();
        return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
    }

    public static void a(Map map, Object obj, Object obj2) {
        List list = (List) map.get(obj);
        if (list == null) {
            list = new ArrayList();
        }
        list.add(obj2);
        map.put(obj, list);
    }

    public static boolean a(String str) {
        try {
            return l.a.getPackageManager().getActivityInfo(new ComponentName(l.a.getPackageName(), str), 131072) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
