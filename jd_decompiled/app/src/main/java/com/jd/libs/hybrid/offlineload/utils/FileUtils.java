package com.jd.libs.hybrid.offlineload.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.framework.network.filedown.JDFileService;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.common.utils.LangUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/* loaded from: classes16.dex */
public class FileUtils {
    private FileUtils() {
    }

    private static void a(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Runtime.getRuntime().exec("chmod " + str + LangUtils.SINGLE_SPACE + file);
            if (Log.isDebug()) {
                Log.d("FileUtils", "change mode file : " + file.getAbsolutePath() + " with mode : " + str);
            }
        } catch (Exception e2) {
            if (Log.isDebug()) {
                Log.d("FileUtils", " -->> chModFile mode:" + str + " file:" + file + " error:" + e2.getMessage());
            }
        }
    }

    private static File b(Context context, boolean z, String str) {
        File filesDir = context.getFilesDir();
        if (str == null) {
            str = "";
        }
        File file = new File(filesDir, str);
        if (z && !file.exists()) {
            if (file.mkdirs()) {
                a(JDFileService.FILE_DIR_MODE_FOR_INTERNAL, file);
            } else {
                throw new IllegalStateException("Unable to create directory: " + file.getAbsolutePath());
            }
        }
        return file;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [java.lang.Throwable, java.io.IOException] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public static boolean copyAssetFile(Context e2, String str, File file) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || e2 == 0) {
            return false;
        }
        FileOutputStream fileOutputStream = 0;
        try {
            try {
                try {
                    e2 = e2.getAssets().open(str);
                    try {
                        byte[] bArr = new byte[2048];
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        while (true) {
                            try {
                                int read = e2.read(bArr);
                                fileOutputStream = -1;
                                fileOutputStream = -1;
                                if (read <= -1) {
                                    break;
                                }
                                fileOutputStream2.write(bArr, 0, read);
                            } catch (Exception e3) {
                                e = e3;
                                fileOutputStream = fileOutputStream2;
                                Log.e("FileUtils", e);
                                OfflineExceptionUtils.reportError("hybrid_file", OfflineExceptionUtils.ERR_MSG_CODE, "copyAssetFile", ExceptionUtils.getStackStringFromException(e));
                                if (fileOutputStream != 0) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e4) {
                                        Log.e("FileUtils", e4);
                                    }
                                }
                                if (e2 != 0) {
                                    e2.close();
                                    fileOutputStream = fileOutputStream;
                                    e2 = e2;
                                }
                                return z;
                            } catch (Throwable th) {
                                th = th;
                                fileOutputStream = fileOutputStream2;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e5) {
                                        Log.e("FileUtils", e5);
                                    }
                                }
                                if (e2 != 0) {
                                    try {
                                        e2.close();
                                    } catch (IOException e6) {
                                        Log.e("FileUtils", e6);
                                    }
                                }
                                throw th;
                            }
                        }
                        z = true;
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e7) {
                            Log.e("FileUtils", e7);
                        }
                    } catch (Exception e8) {
                        e = e8;
                    }
                } catch (Exception e9) {
                    e = e9;
                    e2 = 0;
                } catch (Throwable th2) {
                    th = th2;
                    e2 = 0;
                }
                if (e2 != 0) {
                    e2.close();
                    e2 = e2;
                }
            } catch (IOException e10) {
                e2 = e10;
                Log.e("FileUtils", (Throwable) e2);
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static void deleteFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        deleteFile(new File(str));
    }

    @Nullable
    public static String getDir(Context context, String str) {
        return getDir(context, true, str);
    }

    public static String getRandomFileName() {
        return System.currentTimeMillis() + "" + (new Random().nextInt(9000) + 1000);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getStringFromAsset(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "FileUtils"
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            r2 = 0
            if (r1 != 0) goto L54
            if (r5 == 0) goto L54
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L37
            r1.<init>()     // Catch: java.lang.Throwable -> L37
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: java.lang.Throwable -> L35
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L35
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L35
            java.io.InputStream r5 = r5.open(r6)     // Catch: java.lang.Throwable -> L35
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L35
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L35
        L22:
            java.lang.String r5 = r3.readLine()     // Catch: java.lang.Throwable -> L32
            if (r5 == 0) goto L2c
            r1.append(r5)     // Catch: java.lang.Throwable -> L32
            goto L22
        L2c:
            r3.close()     // Catch: java.lang.Throwable -> L30
            goto L46
        L30:
            r5 = move-exception
            goto L43
        L32:
            r5 = move-exception
            r2 = r3
            goto L39
        L35:
            r5 = move-exception
            goto L39
        L37:
            r5 = move-exception
            r1 = r2
        L39:
            com.jd.libs.hybrid.base.util.Log.e(r0, r5)     // Catch: java.lang.Throwable -> L48
            if (r2 == 0) goto L46
            r2.close()     // Catch: java.lang.Throwable -> L42
            goto L46
        L42:
            r5 = move-exception
        L43:
            com.jd.libs.hybrid.base.util.Log.e(r0, r5)
        L46:
            r2 = r1
            goto L54
        L48:
            r5 = move-exception
            if (r2 == 0) goto L53
            r2.close()     // Catch: java.lang.Throwable -> L4f
            goto L53
        L4f:
            r6 = move-exception
            com.jd.libs.hybrid.base.util.Log.e(r0, r6)
        L53:
            throw r5
        L54:
            if (r2 == 0) goto L5b
            java.lang.String r5 = r2.toString()
            goto L5d
        L5b:
            java.lang.String r5 = ""
        L5d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.utils.FileUtils.getStringFromAsset(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getStringFromFile(android.content.Context r5, java.io.File r6) {
        /*
            java.lang.String r0 = "FileUtils"
            r1 = 0
            if (r6 == 0) goto L5a
            if (r5 == 0) goto L5a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L30
            r5.<init>()     // Catch: java.lang.Throwable -> L30
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L2e
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L2e
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L2e
            r4.<init>(r6)     // Catch: java.lang.Throwable -> L2e
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L2e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L2e
        L1b:
            java.lang.String r6 = r2.readLine()     // Catch: java.lang.Throwable -> L2b
            if (r6 == 0) goto L25
            r5.append(r6)     // Catch: java.lang.Throwable -> L2b
            goto L1b
        L25:
            r2.close()     // Catch: java.lang.Throwable -> L29
            goto L4c
        L29:
            r6 = move-exception
            goto L49
        L2b:
            r6 = move-exception
            r1 = r2
            goto L32
        L2e:
            r6 = move-exception
            goto L32
        L30:
            r6 = move-exception
            r5 = r1
        L32:
            com.jd.libs.hybrid.base.util.Log.e(r0, r6)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = "hybrid_file"
            java.lang.String r3 = "\u4ee3\u7801\u5f02\u5e38"
            java.lang.String r4 = "getStringFromFile"
            java.lang.String r6 = com.jd.libs.hybrid.base.util.ExceptionUtils.getStackStringFromException(r6)     // Catch: java.lang.Throwable -> L4e
            com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils.reportError(r2, r3, r4, r6)     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L4c
            r1.close()     // Catch: java.lang.Throwable -> L48
            goto L4c
        L48:
            r6 = move-exception
        L49:
            com.jd.libs.hybrid.base.util.Log.e(r0, r6)
        L4c:
            r1 = r5
            goto L5a
        L4e:
            r5 = move-exception
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.lang.Throwable -> L55
            goto L59
        L55:
            r6 = move-exception
            com.jd.libs.hybrid.base.util.Log.e(r0, r6)
        L59:
            throw r5
        L5a:
            if (r1 == 0) goto L61
            java.lang.String r5 = r1.toString()
            goto L63
        L61:
            java.lang.String r5 = ""
        L63:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.utils.FileUtils.getStringFromFile(android.content.Context, java.io.File):java.lang.String");
    }

    public static String getTimestampForName() {
        return System.currentTimeMillis() + "" + (new Random().nextInt(900) + 100) + "";
    }

    @Nullable
    public static String getDir(Context context, boolean z, String str) {
        try {
            return b(context, z, str).getAbsolutePath();
        } catch (Exception e2) {
            Log.e("FileUtils", "getOldDir error: " + e2.getMessage(), e2);
            OfflineExceptionUtils.reportDownloadCodeError("FileUtils#getOldDir", null, null, e2);
            return null;
        }
    }

    public static void deleteFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                for (File file2 : listFiles) {
                    deleteFile(file2);
                }
            }
            file.delete();
        }
    }
}
