package com.jd.lib.un.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Base64;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes16.dex */
public class UnLibFileUtils {
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
                        UnLog.e(TAG, e.toString());
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
                                UnLog.e(TAG, e3.toString());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                }
            } catch (IOException e5) {
                UnLog.e(TAG, e5.toString());
            }
            return Base64.encodeToString(bArr, 0);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean fileIsExists(String str) {
        if (UnLog.D) {
            UnLog.d(TAG, "apkpath -->> " + str);
        }
        return new File(str).exists();
    }

    public static long getDataDiskFreeSize(boolean z) {
        long blockSizeLong;
        long availableBlocksLong;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (z) {
            try {
                if (UnDeviceInfo.getSdkVersion() >= 18) {
                    blockSizeLong = statFs.getBlockSizeLong();
                    availableBlocksLong = statFs.getAvailableBlocksLong();
                    return ((((blockSizeLong * 2) / 1024) * availableBlocksLong) / 2) / 1024;
                }
            } catch (Throwable th) {
                UnLog.e(TAG, th.toString());
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
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.FileInputStream] */
    public static String getMD5(String str) {
        FileInputStream fileInputStream;
        ?? r2 = 0;
        try {
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
                    File file = new File(str);
                    if (file.exists() && file.isFile()) {
                        fileInputStream = new FileInputStream(file);
                        try {
                            messageDigest.update(fileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, file.length()));
                            byte[] digest = messageDigest.digest();
                            StringBuilder sb = new StringBuilder(digest.length * 2);
                            for (byte b : digest) {
                                sb.append("0123456789abcdef".charAt((b >> 4) & 15));
                                sb.append("0123456789abcdef".charAt(b & 15));
                            }
                            String sb2 = sb.toString();
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                                UnLog.e(TAG, e2.toString());
                            }
                            return sb2;
                        } catch (FileNotFoundException e3) {
                            e = e3;
                            UnLog.e(TAG, e.toString());
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (IOException unused) {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        } catch (NoSuchAlgorithmException e4) {
                            e = e4;
                            UnLog.e(TAG, e.toString());
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            return null;
                        }
                    }
                    return null;
                } catch (FileNotFoundException e5) {
                    e = e5;
                    fileInputStream = null;
                } catch (IOException unused2) {
                    fileInputStream = null;
                } catch (NoSuchAlgorithmException e6) {
                    e = e6;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    if (r2 != 0) {
                        try {
                            r2.close();
                        } catch (Exception e7) {
                            UnLog.e(TAG, e7.toString());
                        }
                    }
                    throw th;
                }
            } catch (Exception e8) {
                UnLog.e(TAG, e8.toString());
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            r2 = str;
        }
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

    /* JADX WARN: Removed duplicated region for block: B:66:0x0085 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
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
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4d
        L27:
            int r2 = r6.read(r0)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            if (r2 < 0) goto L31
            r7.write(r0, r1, r2)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            goto L27
        L31:
            r7.flush()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L41
            r1 = 1
            r6.close()     // Catch: java.lang.Exception -> L38
        L38:
            r7.close()     // Catch: java.lang.Exception -> L79
            goto L79
        L3c:
            r0 = move-exception
            r2 = r6
            r6 = r0
            r0 = r7
            goto L7c
        L41:
            r0 = move-exception
            r2 = r3
            r5 = r7
            r7 = r6
            r6 = r0
            r0 = r5
            goto L5f
        L48:
            r7 = move-exception
            r0 = r2
            r2 = r6
            r6 = r7
            goto L7c
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
            r0 = r2
            goto L7c
        L5c:
            r6 = move-exception
            r7 = r2
            r0 = r7
        L5f:
            java.lang.String r3 = "FileUtils"
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L7a
            com.jd.lib.un.utils.UnLog.e(r3, r6)     // Catch: java.lang.Throwable -> L7a
            if (r2 == 0) goto L6d
            r2.delete()     // Catch: java.lang.Throwable -> L7a
        L6d:
            if (r7 == 0) goto L74
            r7.close()     // Catch: java.lang.Exception -> L73
            goto L74
        L73:
        L74:
            if (r0 == 0) goto L79
            r0.close()     // Catch: java.lang.Exception -> L79
        L79:
            return r1
        L7a:
            r6 = move-exception
            r2 = r7
        L7c:
            if (r2 == 0) goto L83
            r2.close()     // Catch: java.lang.Exception -> L82
            goto L83
        L82:
        L83:
            if (r0 == 0) goto L88
            r0.close()     // Catch: java.lang.Exception -> L88
        L88:
            goto L8a
        L89:
            throw r6
        L8a:
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.un.utils.UnLibFileUtils.saveToFile(java.lang.String, java.lang.String):boolean");
    }
}
