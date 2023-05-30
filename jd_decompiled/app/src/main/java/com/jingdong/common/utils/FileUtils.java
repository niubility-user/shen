package com.jingdong.common.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* loaded from: classes6.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static String encodeBase64File(String str) {
        byte[] bArr = new byte[0];
        FileInputStream fileInputStream = null;
        try {
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(new File(str));
                    try {
                        bArr = new byte[fileInputStream2.available()];
                        fileInputStream2.read(bArr);
                        fileInputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        fileInputStream = fileInputStream2;
                        OKLog.e(TAG, e);
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return Base64.encodeToString(bArr, 0);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e3) {
                                OKLog.e(TAG, e3);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (IOException e5) {
            OKLog.e(TAG, e5);
        }
        return Base64.encodeToString(bArr, 0);
    }

    public static boolean fileIsExists(String str) {
        if (OKLog.D) {
            OKLog.d(TAG, "apkpath -->> " + str);
        }
        return new File(str).exists();
    }

    public static long getDataDiskFreeSize(boolean z) {
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
                OKLog.e(TAG, th);
                return -1L;
            }
        }
        blockSizeLong = statFs.getBlockSize();
        availableBlocksLong = statFs.getAvailableBlocks();
        return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
    }

    public static double getDirSize(File file) {
        double d = 0.0d;
        if (file == null || !file.exists()) {
            return 0.0d;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return 0.0d;
            }
            for (File file2 : listFiles) {
                d += getDirSize(file2);
            }
            return d;
        }
        double length = file.length();
        Double.isNaN(length);
        return (length / 1024.0d) / 1024.0d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getMD5(java.lang.String r11) {
        /*
            java.lang.String r0 = "0123456789abcdef"
            java.lang.String r1 = "FileUtils"
            r2 = 0
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            r4.<init>(r11)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            boolean r11 = r4.exists()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            if (r11 == 0) goto L6f
            boolean r11 = r4.isFile()     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            if (r11 != 0) goto L1d
            goto L6f
        L1d:
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            r11.<init>(r4)     // Catch: java.lang.Throwable -> L70 java.io.IOException -> L72 java.io.FileNotFoundException -> L7e java.security.NoSuchAlgorithmException -> L89
            java.nio.channels.FileChannel r5 = r11.getChannel()     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            java.nio.channels.FileChannel$MapMode r6 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r7 = 0
            long r9 = r4.length()     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            java.nio.MappedByteBuffer r4 = r5.map(r6, r7, r9)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r3.update(r4)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            byte[] r3 = r3.digest()     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            int r5 = r3.length     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            int r5 = r5 * 2
            r4.<init>(r5)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            int r5 = r3.length     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r6 = 0
        L43:
            if (r6 >= r5) goto L5e
            r7 = r3[r6]     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            int r8 = r7 >> 4
            r8 = r8 & 15
            char r8 = r0.charAt(r8)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r4.append(r8)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r7 = r7 & 15
            char r7 = r0.charAt(r7)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r4.append(r7)     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            int r6 = r6 + 1
            goto L43
        L5e:
            java.lang.String r0 = r4.toString()     // Catch: java.io.FileNotFoundException -> L6b java.security.NoSuchAlgorithmException -> L6d java.io.IOException -> L73 java.lang.Throwable -> L94
            r11.close()     // Catch: java.lang.Exception -> L66
            goto L6a
        L66:
            r11 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r1, r11)
        L6a:
            return r0
        L6b:
            r0 = move-exception
            goto L80
        L6d:
            r0 = move-exception
            goto L8b
        L6f:
            return r2
        L70:
            r0 = move-exception
            goto L96
        L72:
            r11 = r2
        L73:
            if (r11 == 0) goto L93
            r11.close()     // Catch: java.lang.Exception -> L79
            goto L93
        L79:
            r11 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r1, r11)
            goto L93
        L7e:
            r0 = move-exception
            r11 = r2
        L80:
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L94
            if (r11 == 0) goto L93
            r11.close()     // Catch: java.lang.Exception -> L79
            goto L93
        L89:
            r0 = move-exception
            r11 = r2
        L8b:
            com.jingdong.sdk.oklog.OKLog.e(r1, r0)     // Catch: java.lang.Throwable -> L94
            if (r11 == 0) goto L93
            r11.close()     // Catch: java.lang.Exception -> L79
        L93:
            return r2
        L94:
            r0 = move-exception
            r2 = r11
        L96:
            if (r2 == 0) goto La0
            r2.close()     // Catch: java.lang.Exception -> L9c
            goto La0
        L9c:
            r11 = move-exception
            com.jingdong.sdk.oklog.OKLog.e(r1, r11)
        La0:
            goto La2
        La1:
            throw r0
        La2:
            goto La1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.FileUtils.getMD5(java.lang.String):java.lang.String");
    }

    public static String getRealFilePathOfUri(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }

    public static String renameFile(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                File file2 = new File(file.getParent() + File.separator + str2);
                if (file2.exists() && file2.isFile()) {
                    file2.delete();
                }
                if (file.renameTo(file2)) {
                    return file2.getAbsolutePath();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x007b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean saveToFile(java.lang.String r6, java.lang.String r7) {
        /*
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r0 = new byte[r0]
            r1 = 0
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L5c
            java.io.File r6 = r3.getParentFile()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            boolean r4 = r6.exists()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            if (r4 != 0) goto L18
            r6.mkdirs()     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
        L18:
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            java.io.File r4 = new java.io.File     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            r4.<init>(r7)     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            r6.<init>(r4)     // Catch: java.lang.Exception -> L54 java.lang.Throwable -> L59
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4d
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4d
        L27:
            int r2 = r6.read(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            if (r2 < 0) goto L31
            r7.write(r0, r1, r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            goto L27
        L31:
            r7.flush()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L40
            r1 = 1
            r6.close()     // Catch: java.lang.Exception -> L38
        L38:
            r7.close()     // Catch: java.lang.Exception -> L75
            goto L75
        L3c:
            r0 = move-exception
            r2 = r6
            r6 = r0
            goto L79
        L40:
            r0 = move-exception
            r2 = r3
            r5 = r7
            r7 = r6
            r6 = r0
            r0 = r5
            goto L5f
        L47:
            r7 = move-exception
            r5 = r2
            r2 = r6
            r6 = r7
            r7 = r5
            goto L79
        L4d:
            r7 = move-exception
            r0 = r2
            r2 = r3
            r5 = r7
            r7 = r6
            r6 = r5
            goto L5f
        L54:
            r6 = move-exception
            r7 = r2
            r0 = r7
            r2 = r3
            goto L5f
        L59:
            r6 = move-exception
            r7 = r2
            goto L79
        L5c:
            r6 = move-exception
            r7 = r2
            r0 = r7
        L5f:
            java.lang.String r3 = "FileUtils"
            com.jingdong.sdk.oklog.OKLog.e(r3, r6)     // Catch: java.lang.Throwable -> L76
            if (r2 == 0) goto L69
            r2.delete()     // Catch: java.lang.Throwable -> L76
        L69:
            if (r7 == 0) goto L70
            r7.close()     // Catch: java.lang.Exception -> L6f
            goto L70
        L6f:
        L70:
            if (r0 == 0) goto L75
            r0.close()     // Catch: java.lang.Exception -> L75
        L75:
            return r1
        L76:
            r6 = move-exception
            r2 = r7
            r7 = r0
        L79:
            if (r2 == 0) goto L80
            r2.close()     // Catch: java.lang.Exception -> L7f
            goto L80
        L7f:
        L80:
            if (r7 == 0) goto L85
            r7.close()     // Catch: java.lang.Exception -> L85
        L85:
            goto L87
        L86:
            throw r6
        L87:
            goto L86
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.FileUtils.saveToFile(java.lang.String, java.lang.String):boolean");
    }
}
