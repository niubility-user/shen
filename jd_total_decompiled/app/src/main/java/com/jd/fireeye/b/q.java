package com.jd.fireeye.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes13.dex */
public class q {
    private static ThreadLocal<DateFormat> a = new a();
    private static ThreadLocal<DecimalFormat> b = new b();

    /* loaded from: classes13.dex */
    class a extends ThreadLocal<DateFormat> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    /* loaded from: classes13.dex */
    class b extends ThreadLocal<DecimalFormat> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DecimalFormat initialValue() {
            return new DecimalFormat("0.0");
        }
    }

    public static String a() {
        return a.get().format(new Date());
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length != 3 || TextUtils.isEmpty(split[0]) || split[0].startsWith("0000000000") || TextUtils.equals("", split[0]) || TextUtils.equals("", split[0]) || TextUtils.isEmpty(split[1]) || "020000000000".equals(split[1])) ? false : true;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length <= 1 || TextUtils.isEmpty(split[0]) || TextUtils.equals("", split[0]) || TextUtils.equals("", split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
    }

    public static String a(double d) {
        return b.get().format(d / 1000000.0d);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
        if (r6 == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r6 != 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        r6.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String b(String str) {
        IOException e2;
        BufferedReader bufferedReader;
        IOException e3;
        String str2 = "";
        BufferedReader bufferedReader2 = null;
        try {
            try {
                str = Runtime.getRuntime().exec("getprop " + str);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(str.getInputStream()), 1024);
                } catch (IOException e4) {
                    e2 = e4;
                    IOException iOException = e2;
                    bufferedReader = null;
                    e3 = iOException;
                    e3.printStackTrace();
                    if (bufferedReader != null) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (str != 0) {
                        str.destroy();
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e2 = e6;
                str = 0;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
            }
            try {
                str2 = bufferedReader.readLine();
                try {
                    bufferedReader.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            } catch (IOException e8) {
                e3 = e8;
                e3.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e9) {
                        e9.printStackTrace();
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader2 = bufferedReader;
        }
    }

    public static String a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b2 : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b2 >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Boolean a(Context context) {
        Boolean bool = Boolean.FALSE;
        if (context == null) {
            return bool;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1).isConnected() ? Boolean.TRUE : bool;
        } catch (Exception e2) {
            e2.printStackTrace();
            return bool;
        }
    }
}
