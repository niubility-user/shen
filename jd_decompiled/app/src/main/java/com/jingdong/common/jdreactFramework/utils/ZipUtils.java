package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes5.dex */
public class ZipUtils {
    private static final String BASE_DIR = "";
    private static final int BUFFER = 1024;
    public static final String EXT = ".zip";
    private static final String PATH = File.separator;
    private static final String TAG = "HybridZipUtil";

    /* JADX WARN: Removed duplicated region for block: B:171:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean copyFile(java.io.File r5, java.io.File r6) {
        /*
            Method dump skipped, instructions count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.ZipUtils.copyFile(java.io.File, java.io.File):boolean");
    }

    public static boolean copyFileDirect(File file, File file2) {
        if (file.isDirectory()) {
            if (file2.isDirectory() || file2.mkdirs()) {
                for (File file3 : file.listFiles()) {
                    File file4 = new File(file2, file3.getName());
                    if (file3.isFile()) {
                        if (!copyFile(file3, file4)) {
                            return false;
                        }
                    } else if (file3.isDirectory() && !copyFileDirect(file3, file4)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean copyJDFlutterFileDirect(File file, File file2) {
        if (file.isDirectory()) {
            if (file2.isDirectory() || file2.mkdirs()) {
                for (File file3 : file.listFiles()) {
                    File file4 = new File(file2, file3.getName());
                    if (file3.isFile()) {
                        if (!copyFile(file3, new File(file2, file3.getName() + "_new"))) {
                            return false;
                        }
                    } else if (file3.isDirectory() && !copyFileDirect(file3, file4)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decompress(String str) throws Exception {
        decompress(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0041 A[Catch: Exception -> 0x003d, TRY_LEAVE, TryCatch #0 {Exception -> 0x003d, blocks: (B:88:0x0039, B:92:0x0041), top: B:98:0x0039 }] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decompressFile(java.io.File r6, java.util.zip.ZipInputStream r7) throws java.lang.Exception {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L31
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r0]     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L26
        Lf:
            r3 = 0
            int r4 = r7.read(r2, r3, r0)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L26
            r5 = -1
            if (r4 == r5) goto L1b
            r6.write(r2, r3, r4)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L26
            goto Lf
        L1b:
            r6.close()     // Catch: java.lang.Exception -> L22
            r1.close()     // Catch: java.lang.Exception -> L22
            return
        L22:
            r6 = move-exception
            throw r6
        L24:
            r7 = move-exception
            goto L36
        L26:
            r7 = move-exception
            goto L2c
        L28:
            r7 = move-exception
            goto L37
        L2a:
            r7 = move-exception
            r6 = r0
        L2c:
            r0 = r1
            goto L33
        L2e:
            r7 = move-exception
            r1 = r0
            goto L37
        L31:
            r7 = move-exception
            r6 = r0
        L33:
            throw r7     // Catch: java.lang.Throwable -> L34
        L34:
            r7 = move-exception
            r1 = r0
        L36:
            r0 = r6
        L37:
            if (r0 == 0) goto L3f
            r0.close()     // Catch: java.lang.Exception -> L3d
            goto L3f
        L3d:
            r6 = move-exception
            goto L45
        L3f:
            if (r1 == 0) goto L46
            r1.close()     // Catch: java.lang.Exception -> L3d
            goto L46
        L45:
            throw r6
        L46:
            goto L48
        L47:
            throw r7
        L48:
            goto L47
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.ZipUtils.decompressFile(java.io.File, java.util.zip.ZipInputStream):void");
    }

    private static void fileProber(File file) {
        File parentFile = file.getParentFile();
        if (parentFile.exists()) {
            return;
        }
        fileProber(parentFile);
        parentFile.mkdir();
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.io.File getRealFileName(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "/"
            java.lang.String[] r7 = r7.split(r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            int r6 = r7.length
            r1 = 1
            if (r6 <= r1) goto La8
            r6 = 0
        L10:
            int r2 = r7.length
            int r2 = r2 - r1
            java.lang.String r3 = "GB2312"
            java.lang.String r4 = "8859_1"
            if (r6 >= r2) goto L2d
            r2 = r7[r6]
            java.lang.String r5 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L24
            byte[] r4 = r2.getBytes(r4)     // Catch: java.io.UnsupportedEncodingException -> L24
            r5.<init>(r4, r3)     // Catch: java.io.UnsupportedEncodingException -> L24
            r2 = r5
        L24:
            java.io.File r3 = new java.io.File
            r3.<init>(r0, r2)
            int r6 = r6 + 1
            r0 = r3
            goto L10
        L2d:
            com.jingdong.common.jdreactFramework.JDReactHelper r6 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            boolean r6 = r6.isDebug()
            java.lang.String r2 = "upZipFile"
            if (r6 == 0) goto L4d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r5 = "1ret = "
            r6.append(r5)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            com.jingdong.common.jdreactFramework.utils.JLog.d(r2, r6)
        L4d:
            boolean r6 = r0.exists()
            if (r6 != 0) goto L56
            r0.mkdirs()
        L56:
            int r6 = r7.length
            int r6 = r6 - r1
            r6 = r7[r6]
            java.lang.String r7 = new java.lang.String     // Catch: java.io.UnsupportedEncodingException -> L83
            byte[] r1 = r6.getBytes(r4)     // Catch: java.io.UnsupportedEncodingException -> L83
            r7.<init>(r1, r3)     // Catch: java.io.UnsupportedEncodingException -> L83
            com.jingdong.common.jdreactFramework.JDReactHelper r6 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()     // Catch: java.io.UnsupportedEncodingException -> L82
            boolean r6 = r6.isDebug()     // Catch: java.io.UnsupportedEncodingException -> L82
            if (r6 == 0) goto L84
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.io.UnsupportedEncodingException -> L82
            r6.<init>()     // Catch: java.io.UnsupportedEncodingException -> L82
            java.lang.String r1 = "substr = "
            r6.append(r1)     // Catch: java.io.UnsupportedEncodingException -> L82
            r6.append(r7)     // Catch: java.io.UnsupportedEncodingException -> L82
            java.lang.String r6 = r6.toString()     // Catch: java.io.UnsupportedEncodingException -> L82
            com.jingdong.common.jdreactFramework.utils.JLog.d(r2, r6)     // Catch: java.io.UnsupportedEncodingException -> L82
            goto L84
        L82:
            r6 = r7
        L83:
            r7 = r6
        L84:
            java.io.File r6 = new java.io.File
            r6.<init>(r0, r7)
            com.jingdong.common.jdreactFramework.JDReactHelper r7 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            boolean r7 = r7.isDebug()
            if (r7 == 0) goto La7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "2ret = "
            r7.append(r0)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            com.jingdong.common.jdreactFramework.utils.JLog.d(r2, r7)
        La7:
            return r6
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.ZipUtils.getRealFileName(java.lang.String, java.lang.String):java.io.File");
    }

    public static void renameFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        new File(str).renameTo(new File(str2));
    }

    private static void decompress(File file) throws Exception {
        decompress(file, file.getParent());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x004a A[Catch: Exception -> 0x0046, TryCatch #6 {Exception -> 0x0046, blocks: (B:90:0x0042, B:94:0x004a, B:96:0x004f), top: B:100:0x0042 }] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x004f A[Catch: Exception -> 0x0046, TRY_LEAVE, TryCatch #6 {Exception -> 0x0046, blocks: (B:90:0x0042, B:94:0x004a, B:96:0x004f), top: B:100:0x0042 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decompress(java.io.File r3, java.io.File r4) throws java.lang.Exception {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L39
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L39
            java.util.zip.CheckedInputStream r3 = new java.util.zip.CheckedInputStream     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            java.util.zip.CRC32 r2 = new java.util.zip.CRC32     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            r2.<init>()     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            r3.<init>(r1, r2)     // Catch: java.lang.Throwable -> L2d java.lang.Exception -> L30
            java.util.zip.ZipInputStream r2 = new java.util.zip.ZipInputStream     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L28 java.lang.Exception -> L2a
            decompress(r4, r2)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L26
            r2.close()     // Catch: java.lang.Exception -> L22
            r3.close()     // Catch: java.lang.Exception -> L22
            r1.close()     // Catch: java.lang.Exception -> L22
            return
        L22:
            r3 = move-exception
            throw r3
        L24:
            r4 = move-exception
            goto L3f
        L26:
            r4 = move-exception
            goto L33
        L28:
            r4 = move-exception
            goto L40
        L2a:
            r4 = move-exception
            r2 = r0
            goto L33
        L2d:
            r4 = move-exception
            r3 = r0
            goto L40
        L30:
            r4 = move-exception
            r3 = r0
            r2 = r3
        L33:
            r0 = r1
            goto L3c
        L35:
            r4 = move-exception
            r3 = r0
            r1 = r3
            goto L40
        L39:
            r4 = move-exception
            r3 = r0
            r2 = r3
        L3c:
            throw r4     // Catch: java.lang.Throwable -> L3d
        L3d:
            r4 = move-exception
            r1 = r0
        L3f:
            r0 = r2
        L40:
            if (r0 == 0) goto L48
            r0.close()     // Catch: java.lang.Exception -> L46
            goto L48
        L46:
            r3 = move-exception
            goto L53
        L48:
            if (r3 == 0) goto L4d
            r3.close()     // Catch: java.lang.Exception -> L46
        L4d:
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Exception -> L46
            goto L54
        L53:
            throw r3
        L54:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.utils.ZipUtils.decompress(java.io.File, java.io.File):void");
    }

    public static void decompress(File file, String str) throws Exception {
        if (str == null || str.contains("../")) {
            return;
        }
        decompress(file, new File(str));
    }

    private static void decompress(String str, String str2) throws Exception {
        decompress(new File(str), str2);
    }

    private static void decompress(File file, ZipInputStream zipInputStream) throws Exception {
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return;
            }
            String name = nextEntry.getName();
            if (name.contains("../")) {
                return;
            }
            File file2 = new File(file.getPath() + File.separator + name);
            fileProber(file2);
            if (nextEntry.isDirectory()) {
                file2.mkdirs();
            } else {
                decompressFile(file2, zipInputStream);
            }
            zipInputStream.closeEntry();
        }
    }
}
