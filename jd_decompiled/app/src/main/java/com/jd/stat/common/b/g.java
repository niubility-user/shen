package com.jd.stat.common.b;

import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class g {
    private static Handler a;
    private static ThreadLocal<DateFormat> b = new ThreadLocal<DateFormat>() { // from class: com.jd.stat.common.b.g.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            return simpleDateFormat;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private static ThreadLocal<DecimalFormat> f6983c = new ThreadLocal<DecimalFormat>() { // from class: com.jd.stat.common.b.g.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public DecimalFormat initialValue() {
            try {
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
                decimalFormat.applyPattern("0.0");
                return decimalFormat;
            } catch (Exception unused) {
                return new DecimalFormat("0.0");
            }
        }
    };

    /* loaded from: classes18.dex */
    public interface a {
        boolean a(String str);
    }

    public static String a(long j2) {
        return b.get().format(Long.valueOf(j2));
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && str.split("-").length == 3;
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split("-");
        return (split.length <= 1 || TextUtils.isEmpty(split[0]) || TextUtils.equals("", split[0]) || TextUtils.equals("", split[0]) || TextUtils.isEmpty(split[1])) ? false : true;
    }

    public static String d(String str) {
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

    public static String e(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("sha-1");
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

    public static String f(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("sha-256");
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

    public static String g(String str) {
        return str == null ? "" : str;
    }

    public static String h(String str) {
        return (TextUtils.isEmpty(str) || str.length() <= 1) ? "" : str.substring(0, str.length() - 1);
    }

    public static String i(String str) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Runtime.getRuntime().exec(str).getInputStream());
        try {
            try {
                return a(bufferedInputStream);
            } catch (IOException e2) {
                throw new IOException(e2);
            }
        } finally {
            b(bufferedInputStream);
        }
    }

    public static String a() {
        return b.get().format(new Date());
    }

    public static String a(double d) {
        return f6983c.get().format(d / 1000000.0d);
    }

    public static String a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
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
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static void b(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            a(closeable);
        }
    }

    public static String a(String str, Iterable<? extends CharSequence> iterable) {
        if (iterable == null) {
            return "";
        }
        Iterator<? extends CharSequence> it = iterable.iterator();
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
            if (it.hasNext()) {
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public static String a(boolean z, String str, String str2) {
        if (z) {
            return str + str2;
        }
        return str;
    }

    public static String a(Throwable th) {
        int i2;
        if (th == null) {
            th = new Throwable();
            i2 = 1;
        } else {
            i2 = 0;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        if (stackTrace.length > i2) {
            while (i2 < stackTrace.length) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                sb.append(stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName());
                if (i2 == Math.min(stackTrace.length - 1, 3)) {
                    break;
                }
                sb.append("|");
                i2++;
            }
        }
        return sb.toString();
    }

    public static String a(Throwable th, int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        if (th == null) {
            th = new Throwable();
            i2++;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        StringBuilder sb = new StringBuilder();
        if (stackTrace.length > i2) {
            while (i2 < stackTrace.length) {
                StackTraceElement stackTraceElement = stackTrace[i2];
                sb.append(stackTraceElement.getClassName() + "|" + stackTraceElement.getMethodName());
                if (i2 == Math.min(stackTrace.length - 1, 3)) {
                    break;
                }
                sb.append("|");
                i2++;
            }
        }
        return sb.toString();
    }

    public static String a(String str, a aVar) throws IOException {
        Scanner scanner = null;
        try {
            Scanner scanner2 = new Scanner(Runtime.getRuntime().exec(str).getInputStream());
            while (scanner2.hasNextLine()) {
                try {
                    String nextLine = scanner2.nextLine();
                    if (aVar != null && aVar.a(nextLine)) {
                        scanner2.close();
                        return nextLine;
                    }
                } catch (Throwable th) {
                    th = th;
                    scanner = scanner2;
                    try {
                        throw new IOException(th);
                    } catch (Throwable th2) {
                        if (scanner != null) {
                            scanner.close();
                        }
                        throw th2;
                    }
                }
            }
            scanner2.close();
            return "";
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private static String a(BufferedInputStream bufferedInputStream) throws IOException {
        int read;
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        do {
            read = bufferedInputStream.read(bArr);
            if (read > 0) {
                sb.append(new String(bArr, 0, read));
            }
        } while (read > 0);
        return sb.toString();
    }
}
