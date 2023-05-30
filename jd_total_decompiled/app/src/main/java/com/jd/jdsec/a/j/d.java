package com.jd.jdsec.a.j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes13.dex */
public class d {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e0  */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Boolean a(String str, String str2) throws Exception {
        Throwable th;
        InputStream inputStream;
        com.jd.jdsec.a.l.b.e("JDSec.Security.FileHandler", "\u5728\u4e0b\u8f7d");
        FileOutputStream fileOutputStream = null;
        try {
            try {
                str = (HttpURLConnection) new URL(str).openConnection();
                try {
                    str.setConnectTimeout(30000);
                    str.connect();
                    if (str.getResponseCode() != 200) {
                        com.jd.jdsec.a.l.b.e("JDSec.Security.FileHandler", "downloadZipFile Server ResponseCode=" + str.getResponseCode() + " ResponseMessage=" + str.getResponseMessage());
                    }
                    inputStream = str.getInputStream();
                    try {
                        com.jd.jdsec.a.l.b.e("JDSec.Security.FileHandler", "Downloading Zip File destinationFilePath=" + str2);
                        new File(str2).createNewFile();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(str2);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read != -1) {
                                    fileOutputStream2.write(bArr, 0, read);
                                } else {
                                    try {
                                        break;
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            fileOutputStream2.close();
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (str != 0) {
                                str.disconnect();
                            }
                            new File(str2);
                            return Boolean.TRUE;
                        } catch (Exception e3) {
                            e = e3;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            Boolean bool = Boolean.FALSE;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    if (str != 0) {
                                        str.disconnect();
                                    }
                                    return bool;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (str != 0) {
                            }
                            return bool;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                    if (str != 0) {
                                        str.disconnect();
                                    }
                                    throw th;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (str != 0) {
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        e = e6;
                    }
                } catch (Exception e7) {
                    e = e7;
                    inputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = null;
                }
            } catch (Exception e8) {
                e = e8;
                str = 0;
                inputStream = null;
            } catch (Throwable th4) {
                th = th4;
                str = 0;
                inputStream = null;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public static boolean b(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                return d(str);
            }
            return c(str);
        }
        return false;
    }

    private static boolean c(String str) {
        String str2 = File.separator;
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            boolean z = true;
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    z = d(file2.getAbsolutePath());
                    if (!z) {
                        break;
                    }
                } else {
                    if (file2.isDirectory() && !(z = c(file2.getAbsolutePath()))) {
                        break;
                    }
                }
            }
            if (z && file.delete()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean d(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }
}
