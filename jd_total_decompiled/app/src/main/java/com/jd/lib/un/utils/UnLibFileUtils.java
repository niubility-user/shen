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
import java.io.FileOutputStream;
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
    */
    public static boolean saveToFile(String str, String str2) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream2 = null;
        File file = null;
        try {
            try {
                try {
                    File file2 = new File(str);
                    try {
                        File parentFile = file2.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        FileInputStream fileInputStream3 = new FileInputStream(new File(str2));
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                            while (true) {
                                try {
                                    int read = fileInputStream3.read(bArr);
                                    if (read < 0) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, read);
                                } catch (Exception e2) {
                                    file = file2;
                                    fileInputStream = fileInputStream3;
                                    e = e2;
                                    fileOutputStream = fileOutputStream2;
                                    try {
                                        UnLog.e(TAG, e.toString());
                                        if (file != null) {
                                            file.delete();
                                        }
                                        if (fileInputStream != null) {
                                            try {
                                                fileInputStream.close();
                                            } catch (Exception unused) {
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        return false;
                                    } catch (Throwable th) {
                                        th = th;
                                        fileInputStream2 = fileInputStream;
                                        if (fileInputStream2 != null) {
                                            try {
                                                fileInputStream2.close();
                                            } catch (Exception unused2) {
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (Exception unused3) {
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    fileInputStream2 = fileInputStream3;
                                    th = th2;
                                    fileOutputStream = fileOutputStream2;
                                    if (fileInputStream2 != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    throw th;
                                }
                            }
                            fileOutputStream2.flush();
                            try {
                                fileInputStream3.close();
                            } catch (Exception unused4) {
                            }
                            fileOutputStream2.close();
                        } catch (Exception e3) {
                            fileOutputStream = null;
                            file = file2;
                            fileInputStream = fileInputStream3;
                            e = e3;
                        } catch (Throwable th3) {
                            fileOutputStream = null;
                            fileInputStream2 = fileInputStream3;
                            th = th3;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        fileInputStream = null;
                        fileOutputStream = null;
                        file = file2;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = null;
                fileOutputStream = null;
            }
        } catch (Exception unused5) {
        }
    }
}
