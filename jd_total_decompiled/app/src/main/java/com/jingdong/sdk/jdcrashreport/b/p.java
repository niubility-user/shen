package com.jingdong.sdk.jdcrashreport.b;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes7.dex */
public final class p {
    public static String a(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream2);
                gZIPOutputStream.write(str.getBytes());
                gZIPOutputStream.close();
                String encodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                try {
                    byteArrayOutputStream2.close();
                    return encodeToString;
                } catch (IOException unused) {
                    return encodeToString;
                }
            } catch (IOException unused2) {
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return "";
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2;
        Throwable th;
        GZIPInputStream gZIPInputStream;
        if (str == null || str.length() == 0) {
            return str;
        }
        GZIPInputStream gZIPInputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException unused) {
            byteArrayOutputStream = null;
            byteArrayInputStream2 = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            byteArrayInputStream = null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(Base64.decode(str, 2));
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            } catch (IOException unused2) {
                byteArrayInputStream2 = byteArrayInputStream;
            } catch (Throwable th3) {
                th = th3;
                gZIPInputStream = null;
            }
        } catch (IOException unused3) {
            byteArrayInputStream2 = null;
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = 0;
            th = th;
            gZIPInputStream = byteArrayInputStream;
            if (gZIPInputStream != null) {
            }
            if (byteArrayInputStream != 0) {
            }
            if (byteArrayOutputStream != null) {
            }
            throw th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = gZIPInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String byteArrayOutputStream2 = byteArrayOutputStream.toString();
            try {
                gZIPInputStream.close();
            } catch (IOException unused4) {
            }
            try {
                byteArrayInputStream.close();
            } catch (IOException unused5) {
            }
            try {
                byteArrayOutputStream.close();
                return byteArrayOutputStream2;
            } catch (IOException e2) {
                e2.printStackTrace();
                return byteArrayOutputStream2;
            }
        } catch (IOException unused6) {
            gZIPInputStream2 = gZIPInputStream;
            byteArrayInputStream2 = byteArrayInputStream;
            if (gZIPInputStream2 != null) {
                try {
                    gZIPInputStream2.close();
                } catch (IOException unused7) {
                }
            }
            if (byteArrayInputStream2 != null) {
                try {
                    byteArrayInputStream2.close();
                } catch (IOException unused8) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return "";
        } catch (Throwable th5) {
            th = th5;
            if (gZIPInputStream != null) {
                try {
                    gZIPInputStream.close();
                } catch (IOException unused9) {
                }
            }
            if (byteArrayInputStream != 0) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException unused10) {
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }
}
