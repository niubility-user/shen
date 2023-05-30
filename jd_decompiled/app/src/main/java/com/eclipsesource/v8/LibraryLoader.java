package com.eclipsesource.v8;

import com.eclipsesource.v8.PlatformDetector;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class LibraryLoader {
    static final String DELIMITER = System.getProperty("line.separator");
    static final String SEPARATOR = System.getProperty("file.separator");
    static final String SWT_LIB_DIR = ".j2v8";

    LibraryLoader() {
    }

    static void chmod(String str, String str2) {
        if (PlatformDetector.OS.isWindows()) {
            return;
        }
        try {
            Runtime.getRuntime().exec(new String[]{"chmod", str, str2}).waitFor();
        } catch (Throwable unused) {
        }
    }

    public static String computeLibraryFullName(boolean z) {
        return "lib" + computeLibraryShortName(z) + OrderISVUtil.MONEY_DECIMAL + PlatformDetector.OS.getLibFileExtension();
    }

    public static String computeLibraryShortName(boolean z) {
        String str;
        String name = (z && PlatformDetector.OS.isLinux()) ? PlatformDetector.Vendor.getName() : null;
        String name2 = PlatformDetector.OS.getName();
        String name3 = PlatformDetector.Arch.getName();
        StringBuilder sb = new StringBuilder();
        sb.append("j2v8");
        if (name != null) {
            str = "-" + name;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append("-");
        sb.append(name2);
        sb.append("-");
        sb.append(name3);
        return sb.toString();
    }

    static boolean extract(String str, boolean z, StringBuffer stringBuffer) {
        String computeLibraryFullName = computeLibraryFullName(z);
        return extract(str + SEPARATOR + computeLibraryFullName, computeLibraryFullName, stringBuffer);
    }

    static boolean load(String str, StringBuffer stringBuffer) {
        try {
            if (str.indexOf(SEPARATOR) != -1) {
                System.load(str);
                return true;
            }
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e2) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append(DELIMITER);
            }
            stringBuffer.append('\t');
            stringBuffer.append(e2.getMessage());
            stringBuffer.append(DELIMITER);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibrary(String str) {
        if (PlatformDetector.OS.isAndroid()) {
            System.loadLibrary("j2v8");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (tryLoad(true, stringBuffer) || tryLoad(false, stringBuffer)) {
            return;
        }
        if (str == null) {
            str = System.getProperty("java.io.tmpdir");
        }
        if (extract(str, true, stringBuffer) || extract(str, false, stringBuffer)) {
            return;
        }
        throw new UnsatisfiedLinkError("Could not load J2V8 library. Reasons: " + stringBuffer.toString());
    }

    static boolean tryLoad(boolean z, StringBuffer stringBuffer) {
        String computeLibraryShortName = computeLibraryShortName(z);
        String computeLibraryFullName = computeLibraryFullName(z);
        StringBuilder sb = new StringBuilder();
        sb.append(System.getProperty("user.dir"));
        String str = SEPARATOR;
        sb.append(str);
        sb.append("jni");
        sb.append(str);
        sb.append(computeLibraryFullName);
        String sb2 = sb.toString();
        if (load(computeLibraryFullName, stringBuffer) || load(computeLibraryShortName, stringBuffer)) {
            return true;
        }
        return new File(sb2).exists() && load(sb2, stringBuffer);
    }

    static boolean extract(String str, String str2, StringBuffer stringBuffer) {
        InputStream inputStream;
        File file = new File(str);
        boolean z = true;
        FileOutputStream fileOutputStream = null;
        try {
            if (file.exists()) {
                file.delete();
            }
            inputStream = LibraryLoader.class.getResourceAsStream("/" + str2);
            if (inputStream != null) {
                try {
                    byte[] bArr = new byte[4096];
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                    while (true) {
                        try {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream2.write(bArr, 0, read);
                        } catch (Throwable unused) {
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused3) {
                                }
                            }
                            if (z && file.exists()) {
                                file.delete();
                            }
                            return false;
                        }
                    }
                    fileOutputStream2.close();
                    inputStream.close();
                    chmod("755", str);
                    if (load(str, stringBuffer)) {
                        return true;
                    }
                } catch (Throwable unused4) {
                }
            }
        } catch (Throwable unused5) {
            inputStream = null;
            z = false;
        }
        return false;
    }
}
