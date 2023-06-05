package com.jingdong.common.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes6.dex */
public class BitmapkitZip {
    private static final String TAG = "BitmapkitZip";

    public static byte[] objectToBytes(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (IOException e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003d, code lost:
        if (r7.length() <= 0) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
        if (r3.getName().endsWith(r7) != false) goto L78;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0086: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:50:0x0086 */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0089 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */

    /**
     * 对特定文件进行解压,并拿到byte数据.
     * <br>反编译的代码缺失了对RsaFileSuffix的使用, 猜测是以后缀进行匹配
     * @param apkPath base.apk的地址,如"/data/app/com.jingdong.app.mall-BrX6yKhr86jyzYPsue-8fQ==/bask.apk"
     * @param dir 目录地址,如"META-INF/"
     * @param fileSuffix 文件后缀, 如".RSA"
     */
    public static byte[] unZip(String apkPath, String dir, String fileSuffix) {
        ZipFile zipFile;
        ZipFile zipFile2;
        ZipFile zipFile3 = null;
        try {
        } catch (IOException e2) {
            OKLog.e(TAG, e2);
        }
        try {
            try {
                zipFile = new ZipFile(new File(apkPath));
            } catch (Exception e3) {
                e = e3;
                zipFile = null;
            } catch (Throwable th) {
                th = th;
                if (zipFile3 != null) {
                }
                throw th;
            }
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    if (!nextElement.isDirectory() && (dir == null || dir.length() <= 0 || nextElement.getName().startsWith(dir))) {
                        InputStream inputStream = zipFile.getInputStream(nextElement);
                        int size = (int) nextElement.getSize();
                        byte[] bArr = new byte[size];
                        if (inputStream.read(bArr) != size) {
                            try {
                                zipFile.close();
                            } catch (IOException e4) {
                                OKLog.e(TAG, e4);
                            }
                            return null;
                        }
                        try {
                            zipFile.close();
                        } catch (IOException e5) {
                            OKLog.e(TAG, e5);
                        }
                        return bArr;
                    }
                }
                zipFile.close();
            } catch (Exception e6) {
                e = e6;
                OKLog.e(TAG, e);
                if (zipFile != null) {
                    zipFile.close();
                }
                return null;
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            zipFile3 = zipFile2;
            if (zipFile3 != null) {
                try {
                    zipFile3.close();
                } catch (IOException e7) {
                    OKLog.e(TAG, e7);
                }
            }
            throw th;
        }
    }
}
