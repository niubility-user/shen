package com.jd.libs.hybrid.offlineload.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jd.framework.network.filedown.JDFileService;
import com.jd.libs.hybrid.base.util.ExceptionUtils;
import com.jd.libs.hybrid.base.util.Log;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    */
    public static String getStringFromAsset(Context context, String str) {
        StringBuilder sb;
        StringBuilder sb2 = null;
        r2 = null;
        BufferedReader bufferedReader = null;
        sb2 = null;
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                sb = new StringBuilder();
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                            } else {
                                try {
                                    break;
                                } catch (Throwable th) {
                                    th = th;
                                    Log.e("FileUtils", th);
                                    sb2 = sb;
                                    if (sb2 == null) {
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            try {
                                Log.e("FileUtils", th);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th3) {
                                        th = th3;
                                        Log.e("FileUtils", th);
                                        sb2 = sb;
                                        if (sb2 == null) {
                                        }
                                    }
                                }
                                sb2 = sb;
                                if (sb2 == null) {
                                }
                            } catch (Throwable th4) {
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th5) {
                                        Log.e("FileUtils", th5);
                                    }
                                }
                                throw th4;
                            }
                        }
                    }
                    bufferedReader2.close();
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                sb = null;
            }
            sb2 = sb;
        }
        return sb2 == null ? sb2.toString() : "";
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getStringFromFile(Context context, File file) {
        StringBuilder sb;
        StringBuilder sb2 = null;
        r1 = null;
        BufferedReader bufferedReader = null;
        sb2 = null;
        if (file != null && context != null) {
            try {
                sb = new StringBuilder();
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                            } else {
                                try {
                                    break;
                                } catch (Throwable th) {
                                    th = th;
                                    Log.e("FileUtils", th);
                                    sb2 = sb;
                                    if (sb2 == null) {
                                    }
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedReader = bufferedReader2;
                            try {
                                Log.e("FileUtils", th);
                                OfflineExceptionUtils.reportError("hybrid_file", OfflineExceptionUtils.ERR_MSG_CODE, "getStringFromFile", ExceptionUtils.getStackStringFromException(th));
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th3) {
                                        th = th3;
                                        Log.e("FileUtils", th);
                                        sb2 = sb;
                                        if (sb2 == null) {
                                        }
                                    }
                                }
                                sb2 = sb;
                                if (sb2 == null) {
                                }
                            } catch (Throwable th4) {
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable th5) {
                                        Log.e("FileUtils", th5);
                                    }
                                }
                                throw th4;
                            }
                        }
                    }
                    bufferedReader2.close();
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                sb = null;
            }
            sb2 = sb;
        }
        return sb2 == null ? sb2.toString() : "";
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
