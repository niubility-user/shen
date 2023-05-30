package com.jingdong.sdk.talos.inner;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.google.common.primitives.SignedBytes;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.network.toolbox.GatewaySignatureHelper;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.zip.GZIPOutputStream;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class e {
    public int a;
    public l b;

    /* renamed from: c  reason: collision with root package name */
    j f15511c;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unexpected branching in enum static init block */
    /* loaded from: classes.dex */
    public static final class a {
        public static final int a = 1;
        public static final int b = 2;

        /* renamed from: c  reason: collision with root package name */
        public static final int f15512c = 3;
    }

    /* loaded from: classes10.dex */
    public final class b {
        private static final char[] a = {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static byte[] b = new byte[128];

        static {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[] bArr = b;
                if (i3 > bArr.length - 1) {
                    break;
                }
                bArr[i3] = -1;
                i3++;
            }
            while (true) {
                char[] cArr = a;
                if (i2 > cArr.length - 1) {
                    return;
                }
                b[cArr[i2]] = (byte) i2;
                i2++;
            }
        }

        public static String a(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 <= bArr.length - 1; i2 += 3) {
                byte[] bArr2 = new byte[4];
                byte b2 = 0;
                for (int i3 = 0; i3 <= 2; i3++) {
                    int i4 = i2 + i3;
                    if (i4 <= bArr.length - 1) {
                        bArr2[i3] = (byte) (b2 | ((bArr[i4] & 255) >>> ((i3 * 2) + 2)));
                        b2 = (byte) ((((bArr[i4] & 255) << (((2 - i3) * 2) + 2)) & 255) >>> 2);
                    } else {
                        bArr2[i3] = b2;
                        b2 = SignedBytes.MAX_POWER_OF_TWO;
                    }
                }
                bArr2[3] = b2;
                for (int i5 = 0; i5 <= 3; i5++) {
                    sb.append(bArr2[i5] <= 63 ? a[bArr2[i5]] : '=');
                }
            }
            return sb.toString();
        }

        public static byte[] b(byte[] bArr) {
            byte[] bArr2 = null;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.finish();
                gZIPOutputStream.close();
                bArr2 = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr2;
            } catch (Exception e2) {
                e2.printStackTrace();
                return bArr2;
            }
        }
    }

    /* loaded from: classes10.dex */
    public final class c {
        public static boolean a;

        public static String a(HashMap<String, String> hashMap, String str) {
            if (hashMap.isEmpty() || TextUtils.isEmpty(str)) {
                return null;
            }
            TreeSet treeSet = new TreeSet();
            Iterator<String> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                treeSet.add(it.next());
            }
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it2 = treeSet.iterator();
            while (it2.hasNext()) {
                String obj = it2.next().toString();
                String str2 = hashMap.get(obj);
                if (a) {
                    d.b(GatewaySignatureHelper.TAG, "sorted key : " + obj + ", value : " + str2);
                }
                if (!TextUtils.isEmpty(str2)) {
                    stringBuffer.append(str2);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.endsWith(ContainerUtils.FIELD_DELIMITER) && stringBuffer2.length() > 1) {
                stringBuffer2 = stringBuffer2.substring(0, stringBuffer2.length() - 1);
            }
            if (a) {
                d.b(GatewaySignatureHelper.TAG, "raw signature param str : ".concat(String.valueOf(stringBuffer2)));
            }
            return c(d(stringBuffer2), d(str));
        }

        private static String b(byte[] bArr) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; bArr != null && i2 < bArr.length; i2++) {
                String hexString = Integer.toHexString(bArr[i2] & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString().toLowerCase();
        }

        private static String c(byte[] bArr, byte[] bArr2) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(secretKeySpec);
                return b(mac.doFinal(bArr));
            } catch (InvalidKeyException e2) {
                e2.printStackTrace();
                return null;
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
                return null;
            }
        }

        private static byte[] d(String str) {
            if (str == null) {
                return null;
            }
            byte[] bArr = new byte[0];
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return bArr;
            }
        }
    }

    /* loaded from: classes.dex */
    public final class d {
        public static boolean a;

        private static int a(int i2, String str, String str2) {
            if (a) {
                String concat = TextUtils.isEmpty(str) ? "LogX" : "LogX-".concat(String.valueOf(str));
                if (str2 == null) {
                    str2 = "";
                }
                return Log.println(i2, concat, str2);
            }
            return 0;
        }

        public static void b(String str, String str2) {
            a(3, str, str2);
        }

        public static void c(String str, String str2) {
            a(4, str, str2);
        }

        public static void d(String str, String str2) {
            a(6, str, str2);
        }
    }

    /* renamed from: com.jingdong.sdk.talos.inner.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public final class C0748e {
        private static String a;

        private static String a(int i2) {
            FileInputStream fileInputStream;
            int read;
            byte[] bArr = new byte[128];
            FileInputStream fileInputStream2 = null;
            try {
                try {
                    fileInputStream = new FileInputStream("/proc/" + i2 + "/cmdline");
                    try {
                        read = fileInputStream.read(bArr);
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        try {
                            d.d("ProcessUtil", th.getMessage());
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                                return "";
                            }
                            return "";
                        } catch (Throwable th2) {
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (Throwable unused) {
                                }
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                if (read <= 0) {
                    fileInputStream.close();
                    return "";
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= read) {
                        break;
                    } else if (bArr[i3] <= 0) {
                        read = i3;
                        break;
                    } else {
                        i3++;
                    }
                }
                String str = new String(bArr, 0, read);
                try {
                    fileInputStream.close();
                } catch (Throwable unused2) {
                }
                return str;
            } catch (Throwable unused3) {
                return "";
            }
        }

        public static boolean b() {
            return !d().contains(":");
        }

        public static String c() {
            String d = d();
            return !d.contains(":") ? "main" : d.substring(d.lastIndexOf(":"));
        }

        private static String d() {
            if (a == null) {
                a = e();
            }
            return a;
        }

        private static String e() {
            int myPid = Process.myPid();
            return myPid <= 0 ? "" : a(myPid);
        }
    }

    /* loaded from: classes.dex */
    public final class f {
        private static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd");
        private static String b;

        /* renamed from: c  reason: collision with root package name */
        private static int f15513c;
        private static String d;

        public static long a() {
            try {
                return a.parse(a.format(new Date(System.currentTimeMillis()))).getTime();
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0L;
            }
        }

        public static boolean b(String str, Class cls) {
            try {
                ClassLoader classLoader = cls.getClassLoader();
                Class<?> cls2 = Runtime.getRuntime().getClass();
                Class<?>[] clsArr = new Class[2];
                if (Build.VERSION.SDK_INT > 24) {
                    clsArr[0] = ClassLoader.class;
                    clsArr[1] = String.class;
                    Method declaredMethod = cls2.getDeclaredMethod("loadLibrary0", clsArr);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(Runtime.getRuntime(), classLoader, str);
                } else {
                    clsArr[0] = String.class;
                    clsArr[1] = ClassLoader.class;
                    Method declaredMethod2 = cls2.getDeclaredMethod("loadLibrary", clsArr);
                    declaredMethod2.setAccessible(true);
                    declaredMethod2.invoke(Runtime.getRuntime(), str, classLoader);
                }
                return true;
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return false;
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                return false;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                return false;
            }
        }

        public static String c() {
            if (TextUtils.isEmpty(b)) {
                b = BaseInfo.getAppVersionName();
            }
            return b;
        }

        public static int d() {
            if (f15513c == 0) {
                f15513c = BaseInfo.getAppVersionCode();
            }
            return f15513c;
        }

        public static String e() {
            if (TextUtils.isEmpty(d)) {
                d = com.jingdong.sdk.talos.a.g() != null ? BaseInfo.getDisplayMetrics() : "";
            }
            return d;
        }
    }
}
