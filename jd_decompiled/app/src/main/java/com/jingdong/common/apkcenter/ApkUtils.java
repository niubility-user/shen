package com.jingdong.common.apkcenter;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: classes5.dex */
public class ApkUtils {
    private static final String TAG = "ApkUtils";

    private static final String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x005e: IF  (r2 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:47:0x0068, block:B:42:0x005e */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0059 -> B:48:0x005c). Please submit an issue!!! */
    public static final byte[] getApkPublicKey(String str) {
        JarFile jarFile;
        JarFile jarFile2;
        JarEntry jarEntry;
        Certificate[] loadCertificates;
        try {
            try {
                try {
                    jarFile2 = new JarFile(str);
                    try {
                        jarEntry = jarFile2.getJarEntry("classes.dex");
                    } catch (IOException e2) {
                        e = e2;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (jarFile2 != null) {
                            jarFile2.close();
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        if (OKLog.E) {
                            OKLog.e(TAG, th);
                        }
                        if (jarFile2 != null) {
                            jarFile2.close();
                        }
                        return null;
                    }
                } catch (IOException e3) {
                    e = e3;
                    jarFile2 = null;
                } catch (Throwable th2) {
                    th = th2;
                    jarFile2 = null;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (jarEntry != null && (loadCertificates = loadCertificates(jarFile2, jarEntry, new byte[4096])) != null && loadCertificates.length == 1) {
                byte[] encoded = loadCertificates[0].getPublicKey().getEncoded();
                try {
                    jarFile2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return encoded;
            }
            jarFile2.close();
            return null;
        } catch (Throwable th3) {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th3;
        }
    }

    public static PublicKey getPublicKey(byte[] bArr) throws CertificateException {
        return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr)).getPublicKey();
    }

    public static String getSignature(Application application) {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 64).signatures[0].toCharsString();
        } catch (PackageManager.NameNotFoundException e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public static byte[] getSignatures(Application application) {
        try {
            return getPublicKey(application.getPackageManager().getPackageInfo(application.getPackageName(), 64).signatures[0].toByteArray()).getEncoded();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    public static boolean isDownLoadApk(String str, int i2) {
        if (!TextUtils.isEmpty(str) && i2 > 0) {
            try {
                return i2 > JdSdk.getInstance().getApplication().getPackageManager().getPackageInfo(str, 1).versionCode;
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, th);
                }
            }
        }
        return true;
    }

    public static boolean isPackageValid(Application application, String str) {
        byte[] apkPublicKey;
        return (application == null || TextUtils.isEmpty(str) || !FileUtils.fileIsExists(str) || (apkPublicKey = getApkPublicKey(str)) == null || !Arrays.equals(apkPublicKey, getSignatures(application))) ? false : true;
    }

    public static boolean isSignaturesSame(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == null || signatureArr2 == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (Signature signature : signatureArr) {
            hashSet.add(signature);
        }
        HashSet hashSet2 = new HashSet();
        for (Signature signature2 : signatureArr2) {
            hashSet2.add(signature2);
        }
        return hashSet.equals(hashSet2);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0023 -> B:39:0x0033). Please submit an issue!!! */
    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        BufferedInputStream bufferedInputStream;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(jarFile.getInputStream(jarEntry));
                do {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        try {
                            OKLog.e(TAG, th);
                            if (bufferedInputStream != null) {
                                bufferedInputStream.close();
                            }
                            return r1;
                        } catch (Throwable th2) {
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (Exception e2) {
                                    OKLog.e(TAG, e2);
                                }
                            }
                            throw th2;
                        }
                    }
                } while (bufferedInputStream.read(bArr, 0, bArr.length) != -1);
                bufferedInputStream.close();
                r1 = jarEntry != null ? jarEntry.getCertificates() : null;
                bufferedInputStream.close();
            } catch (Throwable th3) {
                th = th3;
                bufferedInputStream = null;
            }
        } catch (Exception e3) {
            OKLog.e(TAG, e3);
        }
        return r1;
    }

    public static Signature[] getSignature(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Object newInstance;
        Object invoke;
        Class<?> cls = Class.forName("android.content.pm.PackageParser");
        Class<?>[] clsArr = {String.class};
        Object[] objArr = {str};
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 19) {
            newInstance = cls.newInstance();
        } else {
            newInstance = cls.getConstructor(clsArr).newInstance(objArr);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics.setToDefaults();
        if (i2 > 19) {
            Object[] objArr2 = {new File(str), 64};
            Method declaredMethod = cls.getDeclaredMethod("parsePackage", File.class, Integer.TYPE);
            declaredMethod.setAccessible(true);
            invoke = declaredMethod.invoke(newInstance, objArr2);
        } else {
            Method declaredMethod2 = cls.getDeclaredMethod("parsePackage", File.class, String.class, DisplayMetrics.class, Integer.TYPE);
            declaredMethod2.setAccessible(true);
            invoke = declaredMethod2.invoke(newInstance, new File(str), str, displayMetrics, 64);
        }
        cls.getDeclaredMethod("collectCertificates", invoke.getClass(), Integer.TYPE).invoke(newInstance, invoke, 64);
        return (Signature[]) invoke.getClass().getDeclaredField("mSignatures").get(invoke);
    }
}
