package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.smtt.sdk.TbsDownloader;
import com.tencent.smtt.sdk.TbsExtensionFunctionManager;
import com.tencent.smtt.sdk.TbsPVConfig;
import com.tencent.smtt.sdk.TbsShareManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes9.dex */
public class a {
    public static int a(Context context, File file) {
        return a(context, file, 0);
    }

    public static int a(Context context, File file, int i2) {
        try {
            return a(context, file, Build.VERSION.SDK_INT >= 20 ? !TbsExtensionFunctionManager.getInstance().canUseFunction(context, TbsExtensionFunctionManager.DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME) : false, i2);
        } catch (Exception unused) {
            TbsLog.i("ApkUtil", "getApkVersion Failed");
            return 0;
        }
    }

    public static int a(Context context, File file, boolean z, int i2) {
        if (file != null) {
            try {
                if (file.exists()) {
                    boolean contains = file.getName().contains("tbs.org");
                    boolean contains2 = file.getName().contains("x5.tbs.decouple");
                    if (contains || contains2) {
                        int a = a(contains2, file, i2);
                        if (a > 0) {
                            return a;
                        }
                        if (!TbsShareManager.isThirdPartyApp(context) && !file.getAbsolutePath().contains(context.getApplicationInfo().packageName)) {
                            return 0;
                        }
                    }
                    int i3 = Build.VERSION.SDK_INT;
                    boolean z2 = (i3 == 23 || i3 == 25) && BaseInfo.getDeviceManufacture().toLowerCase().contains("mi");
                    TbsPVConfig.releaseInstance();
                    int readApk = TbsPVConfig.getInstance(context).getReadApk();
                    if (readApk == 1) {
                        z = false;
                        z2 = false;
                    } else if (readApk == 2) {
                        return 0;
                    }
                    if (z || z2) {
                        int b = b(file);
                        if (b > 0) {
                            return b;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (file == null || !file.exists()) {
            return 0;
        }
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 1);
            if (packageArchiveInfo != null) {
                return packageArchiveInfo.versionCode;
            }
            return 0;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return -1;
        }
    }

    private static int a(boolean z, File file, int i2) {
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                File[] listFiles = parentFile.listFiles();
                Pattern compile = Pattern.compile(a(z, i2) + "(.*)");
                for (File file2 : listFiles) {
                    if (compile.matcher(file2.getName()).find() && file2.isFile() && file2.exists()) {
                        return Integer.parseInt(file2.getName().substring(file2.getName().lastIndexOf(OrderISVUtil.MONEY_DECIMAL) + 1));
                    }
                }
                return -1;
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX WARN: Not initialized variable reg: 5, insn: 0x0068: MOVE (r3 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:138:0x0068 */
    /* JADX WARN: Removed duplicated region for block: B:150:0x006b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        MessageDigest messageDigest;
        int i2;
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[32];
        FileInputStream fileInputStream3 = null;
        try {
            try {
                messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                fileInputStream = new FileInputStream(file);
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (fileInputStream3 != null) {
                }
                throw th;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, read);
                }
                byte[] digest = messageDigest.digest();
                int i3 = 0;
                for (i2 = 0; i2 < 16; i2++) {
                    byte b = digest[i2];
                    int i4 = i3 + 1;
                    cArr2[i3] = cArr[(b >>> 4) & 15];
                    i3 = i4 + 1;
                    cArr2[i4] = cArr[b & 15];
                }
                String str = new String(cArr2);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return str;
            } catch (Exception e4) {
                e = e4;
                e.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream3 = fileInputStream2;
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static final String a(boolean z, int i2) {
        return i2 == 64 ? true : i2 == 32 ? false : b.c() ? z ? "x5.64.decouple.backup" : "x5.64.backup" : z ? "x5.decouple.backup" : "x5.backup";
    }

    /* JADX WARN: Code restructure failed: missing block: B:167:0x0065, code lost:
        r1 = r1[1].trim();
        com.tencent.smtt.utils.TbsLog.i(com.tencent.smtt.sdk.TbsDownloader.LOGTAG, "getApkVersionByReadFile version is " + r1);
        r1 = java.lang.Integer.parseInt(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0086, code lost:
        r7.close();
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x00b5, code lost:
        if (r2 != 0) goto L172;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.util.jar.JarFile] */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.util.jar.JarFile] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.util.jar.JarFile] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object, java.io.File] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int b(File file) {
        Throwable th;
        Exception e2;
        BufferedReader bufferedReader;
        String[] split;
        int parseInt;
        ?? sb = new StringBuilder();
        sb.append("getApkVersionByReadFile");
        sb.append(file);
        sb.append("exists:");
        sb.append(file.exists());
        sb.append(";canread:");
        ?? canRead = file.canRead();
        sb.append(canRead);
        TbsLog.i(TbsDownloader.LOGTAG, sb.toString());
        synchronized (a.class) {
            try {
                try {
                    try {
                        canRead = new JarFile((File) file);
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(canRead.getInputStream(canRead.getJarEntry("assets/webkit/tbs.conf"))));
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        bufferedReader.close();
                                        break;
                                    } else if (readLine.contains("tbs_core_version") && (split = readLine.split(ContainerUtils.KEY_VALUE_DELIMITER)) != null && split.length == 2) {
                                        break;
                                    }
                                } catch (Exception e3) {
                                    e2 = e3;
                                    TbsLog.i(e2);
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                }
                            }
                        } catch (Exception e4) {
                            e2 = e4;
                            bufferedReader = null;
                        } catch (Throwable th2) {
                            th = th2;
                            file = 0;
                            if (file != 0) {
                                try {
                                    file.close();
                                } catch (Exception unused) {
                                    throw th;
                                }
                            }
                            if (canRead != 0) {
                                canRead.close();
                            }
                            throw th;
                        }
                    } catch (Exception e5) {
                        canRead = 0;
                        e2 = e5;
                        bufferedReader = null;
                    } catch (Throwable th3) {
                        canRead = 0;
                        th = th3;
                        file = 0;
                    }
                    canRead.close();
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Exception unused2) {
            }
            return -1;
        }
        return parseInt;
    }
}
