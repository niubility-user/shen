package com.jingdong.common.jdreactFramework.utils;

import android.content.Context;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.common.utils.LangUtils;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes5.dex */
public class FileUtil {
    private static final String TAG = "HybridFileUtil";

    public static void chModFile(String str, File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Runtime.getRuntime().exec("chmod " + str + LangUtils.SINGLE_SPACE + file).destroy();
        } catch (Exception e2) {
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, " -->> chModFile mode:" + str + " file:" + file + " error:" + e2.getMessage());
            }
        }
    }

    public static boolean copyFile(InputStream inputStream, String str) {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                }
                try {
                    fileOutputStream2.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException | Exception unused) {
                }
                return true;
            } catch (Exception unused2) {
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException | Exception unused3) {
                        return false;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return false;
            } catch (Throwable th) {
                fileOutputStream = fileOutputStream2;
                th = th;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException | Exception unused4) {
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Exception unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void copyFileFromAssets(Context context, String str, String str2) {
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "copyFileFromAssets-->> " + str);
        }
        try {
            copyFile(context.getAssets().open(str), str2);
        } catch (IOException unused) {
        }
    }

    public static void copyFolder(File file, File file2, String[] strArr) throws Exception {
        for (File file3 : file.listFiles()) {
            if (file3.isFile()) {
                String str = file2 + File.separator + file3.getName();
                for (String str2 : strArr) {
                    if (str.endsWith(str2)) {
                        File file4 = new File(str);
                        file4.getParentFile().mkdirs();
                        if (!file4.exists()) {
                            file4.createNewFile();
                        }
                        copyFile(file3, file4);
                    }
                }
            } else {
                copyFolder(file3, file2, strArr);
            }
        }
    }

    public static void copyFolderFromAssets(Context context, String str, String str2) throws IOException {
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "copyFolderFromAssets rootDirFullPath-" + str + " targetDirFullPath-" + str2);
        }
        for (String str3 : context.getAssets().list(str)) {
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "name-->> " + str + "/" + str3);
            }
            if (isFileByName(str3)) {
                copyFileFromAssets(context, str + "/" + str3, str2 + "/" + str3);
            } else {
                String str4 = str + "/" + str3;
                String str5 = str2 + "/" + str3;
                new File(str5).mkdirs();
                copyFolderFromAssets(context, str4, str5);
            }
        }
    }

    public static void delFile(String str) {
        new File(str.toString()).delete();
    }

    public static void deleteDir(File file) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                deleteDir(new File(file, str));
            }
        }
        file.delete();
    }

    public static void emptyDir(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String str : list) {
                    deleteDir(new File(file, str));
                }
                return;
            }
            return;
        }
        file.delete();
    }

    public static byte[] file2BetyArray(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        File file = new File(str);
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream(1000);
                } catch (Exception unused) {
                    byteArrayOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = null;
                }
            } catch (Exception unused2) {
                fileInputStream = null;
                byteArrayOutputStream = null;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                fileInputStream = null;
            }
            try {
                byte[] bArr = new byte[1000];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused3) {
                }
                return byteArray;
            } catch (Exception unused4) {
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused5) {
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                try {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException unused6) {
                }
                throw th;
            }
        }
        return null;
    }

    public static byte[] file2ByteArrayWithLength(String str, int i2) {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        if (!file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream(i2);
                try {
                    byte[] bArr = new byte[i2];
                    int read = fileInputStream.read(bArr, 0, i2);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        fileInputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException unused) {
                    }
                    return byteArray;
                } catch (Exception unused2) {
                    try {
                        fileInputStream.close();
                        byteArrayOutputStream.close();
                    } catch (IOException unused3) {
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    try {
                        fileInputStream2.close();
                        byteArrayOutputStream.close();
                    } catch (IOException unused4) {
                    }
                    throw th;
                }
            } catch (Exception unused5) {
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
        } catch (Exception unused6) {
            fileInputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
        }
    }

    public static boolean fileIsExists(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static byte[] getBytesFromFile(File file) {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        if (file == null) {
            return null;
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream(1000);
                    try {
                        byte[] bArr = new byte[1000];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read != -1) {
                                byteArrayOutputStream.write(bArr, 0, read);
                            } else {
                                fileInputStream.close();
                                byteArrayOutputStream.close();
                                return byteArrayOutputStream.toByteArray();
                            }
                        }
                    } catch (IOException unused) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return null;
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return null;
                    }
                } catch (IOException unused2) {
                    byteArrayOutputStream = null;
                }
            } catch (IOException unused3) {
                byteArrayOutputStream = null;
                fileInputStream = null;
            }
        } catch (Exception unused4) {
            return null;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x006b -> B:51:0x006e). Please submit an issue!!! */
    public static void getFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                File file = new File(str);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                    try {
                        bufferedOutputStream2.write(bArr);
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        fileOutputStream.close();
                    } catch (Exception unused) {
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception unused2) {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception unused3) {
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
    }

    public static String getFileMD5(File file) {
        try {
            return getFileMD5ForThrow(file);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getFileMD5ForThrow(File file) throws Exception {
        return getFileMD5ForThrow(new FileInputStream(file));
    }

    public static long getFileSize(String str) {
        try {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                return file.length();
            }
            return 0L;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String getHttpFileContent(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                    byteArrayOutputStream.flush();
                } else {
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return new String(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (MalformedURLException | IOException unused) {
            return "";
        }
    }

    private static boolean isFileByName(String str) {
        return str.contains(OrderISVUtil.MONEY_DECIMAL);
    }

    public static void mkParentDirs(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf > 0) {
            new File(str.substring(0, lastIndexOf)).mkdirs();
        }
    }

    public static void newFolder(String str) {
        try {
            File file = new File(str.toString());
            if (file.exists()) {
                return;
            }
            file.mkdir();
        } catch (Exception unused) {
        }
    }

    public static void scanPrint(Context context, String str) {
        if (JDReactHelper.newInstance().isDebug()) {
            JLog.d(TAG, "=====scan print start " + str + " =====");
        }
        try {
            for (String str2 : new File(str).list()) {
                if (isFileByName(str2)) {
                    String str3 = str + "/" + str2;
                    if (JDReactHelper.newInstance().isDebug()) {
                        JLog.d(TAG, "File: " + str3);
                    }
                } else {
                    String str4 = str + "/" + str2;
                    if (JDReactHelper.newInstance().isDebug()) {
                        JLog.d(TAG, "Directory: " + str4);
                    }
                    scanPrint(context, str4);
                }
            }
            if (JDReactHelper.newInstance().isDebug()) {
                JLog.d(TAG, "====scan print end " + str + " ====");
            }
        } catch (Exception unused) {
        }
    }

    public static boolean writeBytesToFile(File file, byte[] bArr) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (IOException unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException unused2) {
                return false;
            }
        } catch (IOException unused3) {
            fileOutputStream2 = fileOutputStream;
            try {
                fileOutputStream2.close();
            } catch (IOException unused4) {
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            try {
                fileOutputStream2.close();
                throw th;
            } catch (IOException unused5) {
                return false;
            }
        }
    }

    public static String getFileMD5ForThrow(FileInputStream fileInputStream) throws Exception {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        FileChannel channel = fileInputStream.getChannel();
        long size = channel.size();
        double d = size;
        Double.isNaN(d);
        int ceil = (int) Math.ceil(d / 2.147483647E9d);
        MappedByteBuffer[] mappedByteBufferArr = new MappedByteBuffer[ceil];
        long j2 = 0;
        long j3 = 2147483647L;
        int i2 = 0;
        while (i2 < ceil) {
            long j4 = size - j2;
            long j5 = j4 < 2147483647L ? j4 : j3;
            int i3 = i2;
            mappedByteBufferArr[i3] = channel.map(FileChannel.MapMode.READ_ONLY, j2, j5);
            j2 += j5;
            i2 = i3 + 1;
            j3 = j5;
        }
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        for (int i4 = 0; i4 < ceil; i4++) {
            messageDigest.update(mappedByteBufferArr[i4]);
        }
        byte[] digest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
        for (byte b : digest) {
            char c2 = cArr[(b & 240) >> 4];
            char c3 = cArr[b & 15];
            stringBuffer.append(c2);
            stringBuffer.append(c3);
        }
        return stringBuffer.toString();
    }

    public static String getFileMD5(FileInputStream fileInputStream) {
        try {
            return getFileMD5ForThrow(fileInputStream);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getBytesFromFile(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            bufferedOutputStream.flush();
        } catch (Exception unused) {
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (IOException unused2) {
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException unused3) {
            }
            throw th;
        }
        try {
            byteArrayInputStream.close();
        } catch (IOException unused4) {
        }
        try {
            bufferedOutputStream.close();
        } catch (IOException unused5) {
        }
    }

    public static void copyFolder(File file, File file2) throws Exception {
        for (File file3 : file.listFiles()) {
            if (file3.isFile()) {
                File file4 = new File(file2 + File.separator + file3.getName());
                file4.getParentFile().mkdirs();
                if (!file4.exists()) {
                    file4.createNewFile();
                }
                copyFile(file3, file4);
            } else {
                copyFolder(file3, file2);
            }
        }
    }

    public static void copyFile(String str, String str2) throws IOException {
        mkParentDirs(str2);
        copyFile(new File(str), new File(str2));
    }
}
