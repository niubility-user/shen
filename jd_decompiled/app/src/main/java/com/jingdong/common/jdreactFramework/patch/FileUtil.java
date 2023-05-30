package com.jingdong.common.jdreactFramework.patch;

import android.content.Context;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class FileUtil {
    public static boolean copyFile(InputStream inputStream, String str) throws IOException {
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
            } catch (Throwable th) {
                fileOutputStream = fileOutputStream2;
                th = th;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException | Exception unused2) {
                        throw th;
                    }
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void copyFileFromAssets(Context context, String str, String str2) throws IOException {
        try {
            copyFile(context.getAssets().open(str), str2);
        } catch (IOException unused) {
        }
    }

    public static void copyFolder(String str, String str2) throws IOException {
        File file = new File(str);
        File file2 = new File(str2);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        for (File file3 : file.listFiles()) {
            if (file3.isDirectory()) {
                File file4 = new File(str2, file3.getName());
                if (!file4.exists()) {
                    file4.mkdirs();
                }
                copyFolder(file3.getAbsolutePath(), file4.getAbsolutePath());
            } else {
                FileInputStream fileInputStream = new FileInputStream(file3);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file2, file3.getName()));
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } finally {
                    fileInputStream.close();
                    fileOutputStream.close();
                }
            }
        }
    }

    public static void copyFolderFromAssets(Context context, String str, String str2) throws IOException {
        for (String str3 : context.getAssets().list(str)) {
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

    public static void deleteDir(File file) throws Exception {
        String[] list;
        if (file == null) {
            return;
        }
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                deleteDir(new File(file, str));
            }
        }
        if (file.delete()) {
            return;
        }
        throw new Exception("delete file error : " + file.getAbsolutePath());
    }

    public static void emptyDir(File file) throws Exception {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                for (String str : list) {
                    deleteDir(new File(file, str));
                }
            }
        } else if (!file.delete()) {
            throw new Exception("delete file error : " + file.getAbsolutePath());
        }
    }

    public static JSONObject fileToJsonObject(String str) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return new JSONObject(sb.toString());
            }
        }
    }

    public static byte[] getBytesFromFile(File file) throws IOException {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        if (file == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream(1000);
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
            byteArrayOutputStream = null;
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
            fileInputStream.close();
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                fileInputStream.close();
                byteArrayOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return byteArray;
        } catch (Throwable th4) {
            th = th4;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                    throw th;
                }
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static String getFileMD5ForThrow(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
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
        } finally {
            fileInputStream.close();
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

    public static void unzip(String str, String str2) throws IOException {
        FileOutputStream fileOutputStream;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        ZipInputStream zipInputStream = null;
        r0 = null;
        FileOutputStream fileOutputStream2 = null;
        try {
            ZipInputStream zipInputStream2 = new ZipInputStream(new FileInputStream(str));
            try {
                for (ZipEntry nextEntry = zipInputStream2.getNextEntry(); nextEntry != null; nextEntry = zipInputStream2.getNextEntry()) {
                    File file2 = new File(str2 + File.separator + nextEntry.getName());
                    if (nextEntry.isDirectory()) {
                        file2.mkdirs();
                    } else {
                        File parentFile = file2.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        FileOutputStream fileOutputStream3 = new FileOutputStream(file2);
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = zipInputStream2.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream3.write(bArr, 0, read);
                            }
                            fileOutputStream2 = fileOutputStream3;
                        } catch (Throwable th) {
                            zipInputStream = zipInputStream2;
                            fileOutputStream = fileOutputStream3;
                            th = th;
                            if (zipInputStream != null) {
                                zipInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                }
                zipInputStream2.close();
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                zipInputStream = zipInputStream2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void copyFile(File file, File file2) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getBytesFromFile(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = byteArrayInputStream.read(bArr);
                if (read != -1) {
                    bufferedOutputStream.write(bArr, 0, read);
                } else {
                    bufferedOutputStream.flush();
                    try {
                        byteArrayInputStream.close();
                        bufferedOutputStream.close();
                        return;
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
                bufferedOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            throw th;
        }
    }

    public static void copyFile(String str, String str2) throws IOException {
        mkParentDirs(str2);
        copyFile(new File(str), new File(str2));
    }
}
