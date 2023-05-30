package com.jd.fireeye.a.c;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

/* loaded from: classes13.dex */
public class c {
    /* JADX WARN: Removed duplicated region for block: B:41:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0066 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x005c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0078 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String str) {
        Process process;
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            process = Runtime.getRuntime().exec("sh");
        } catch (Exception unused) {
            process = null;
        } catch (Throwable th) {
            th = th;
            process = null;
        }
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(process.getOutputStream());
            try {
                bufferedInputStream = new BufferedInputStream(process.getInputStream());
            } catch (Exception unused2) {
                bufferedInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = null;
            }
            try {
                bufferedOutputStream2.write(str.getBytes());
                bufferedOutputStream2.write(10);
                bufferedOutputStream2.flush();
                bufferedOutputStream2.close();
                process.waitFor();
                String a = a(bufferedInputStream);
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    bufferedInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                if (process != null) {
                    process.destroy();
                }
                return a;
            } catch (Exception unused3) {
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                }
                if (bufferedInputStream != null) {
                }
                if (process == null) {
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                }
                if (bufferedInputStream != null) {
                }
                if (process != null) {
                }
                throw th;
            }
        } catch (Exception unused4) {
            bufferedInputStream = null;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (process == null) {
                process.destroy();
                return "";
            }
            return "";
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    private static String a(BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, read));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();
    }
}
