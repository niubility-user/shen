package com.hihonor.push.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.hihonor.push.framework.aidl.entity.RequestHeader;
import com.hihonor.push.sdk.common.data.ApiException;
import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/* loaded from: classes12.dex */
public class i {
    public static RequestHeader a() throws ApiException {
        String string;
        Context a = f0.f1091e.a();
        String str = null;
        try {
            Object obj = a.getPackageManager().getApplicationInfo(a.getPackageName(), 128).metaData.get("com.hihonor.push.app_id");
            if (obj != null) {
                str = String.valueOf(obj);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            l.b("ConfigUtils", "getPushAppId", e2);
        }
        if (!TextUtils.isEmpty(str)) {
            String str2 = "checkPushAppId Parameter is " + str;
            String f2 = f(a, a.getPackageName());
            if (!TextUtils.isEmpty(f2)) {
                String str3 = "checkPushCertFingerprint Parameter is " + f2;
                RequestHeader requestHeader = new RequestHeader();
                requestHeader.setPackageName(a.getPackageName());
                requestHeader.setAppId(str);
                requestHeader.setCertificateFingerprint(f2);
                o oVar = o.b;
                requestHeader.setPushToken(oVar.c(a));
                synchronized (oVar) {
                    oVar.a(a);
                    SharedPreferences sharedPreferences = o.a.a;
                    string = sharedPreferences != null ? sharedPreferences.getString("key_aaid", "") : "";
                    if (TextUtils.isEmpty(string)) {
                        string = UUID.randomUUID().toString().replace("-", "");
                        String str4 = "getRandomUUID UUID =" + string;
                        o.a.b("key_aaid", string);
                    }
                }
                requestHeader.setAAID(string);
                requestHeader.setSdkVersion(70041301);
                return requestHeader;
            }
            l.a("checkPushConfig Parameter is missing.");
            throw com.hihonor.push.sdk.b0.a.ERROR_CERT_FINGERPRINT_EMPTY.toApiException();
        }
        l.a("checkPushConfig Parameter is missing");
        throw com.hihonor.push.sdk.b0.a.ERROR_NO_APPID.toApiException();
    }

    public static <TResult> e<TResult> b(Callable<TResult> callable) {
        ExecutorService executorService = l0.f1101c.b;
        j0 j0Var = new j0();
        try {
            executorService.execute(new d1(j0Var, callable));
        } catch (Exception e2) {
            j0Var.a(e2);
        }
        return j0Var.a;
    }

    public static com.hihonor.push.sdk.j.a c(Context context) {
        com.hihonor.push.sdk.j.a aVar = new com.hihonor.push.sdk.j.a();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("android", "com.hihonor.android.pushagentproxy.HiPushService"));
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices.size() > 0) {
            Iterator<ResolveInfo> it = queryIntentServices.iterator();
            if (it.hasNext()) {
                ResolveInfo next = it.next();
                String str = next.serviceInfo.applicationInfo.packageName;
                String f2 = f(context, str);
                aVar.f(str);
                aVar.g(next.serviceInfo.name);
                aVar.h(f2);
            }
        }
        return aVar;
    }

    public static ApiException d(Exception exc) {
        if (exc.getCause() instanceof ApiException) {
            return (ApiException) exc.getCause();
        }
        if (exc instanceof ApiException) {
            return (ApiException) exc;
        }
        return new ApiException(-1, exc.getMessage());
    }

    public static <TResult> TResult e(e<TResult> eVar) throws ExecutionException, InterruptedException {
        boolean z;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            synchronized (eVar.a) {
                z = eVar.b;
            }
            if (!z) {
                a1 a1Var = new a1();
                l0 l0Var = l0.f1101c;
                eVar.a(new y0(l0Var.a, a1Var));
                eVar.a(new u0(l0Var.a, a1Var));
                eVar.a(new n0(l0Var.a, a1Var));
                a1Var.a.await();
            }
            if (eVar.f()) {
                return eVar.d();
            }
            throw new ExecutionException(eVar.c());
        }
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:123:0x0050 -> B:124:0x0051). Please submit an issue!!! */
    public static String f(Context context, String str) {
        Signature[] signatureArr;
        String str2;
        SigningInfo signingInfo;
        String str3 = "getCertFingerprint pkgName=" + str + "isOnlyOne=true";
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        if (Build.VERSION.SDK_INT >= 30) {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 134217728);
            if (packageInfo != null && (signingInfo = packageInfo.signingInfo) != null) {
                if (signingInfo.hasMultipleSigners()) {
                    signatureArr = signingInfo.getApkContentsSigners();
                } else {
                    signatureArr = signingInfo.getSigningCertificateHistory();
                }
            }
            signatureArr = null;
        } else {
            PackageInfo packageInfo2 = packageManager.getPackageInfo(str, 64);
            if (packageInfo2 != null) {
                signatureArr = packageInfo2.signatures;
            }
            signatureArr = null;
        }
        if (signatureArr != null && signatureArr.length > 0) {
            int length = signatureArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                try {
                    byte[] digest = MessageDigest.getInstance("SHA256").digest(signatureArr[i2].toByteArray());
                    StringBuilder sb = new StringBuilder();
                    for (byte b : digest) {
                        String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.ENGLISH);
                        if (upperCase.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(upperCase);
                    }
                    str2 = sb.toString();
                } catch (NoSuchAlgorithmException unused) {
                    str2 = null;
                }
                if (str2 != null) {
                    arrayList.add(str2);
                    break;
                }
                i2++;
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (String) arrayList.get(0);
    }

    public static String g(byte[] bArr) {
        if (bArr.length != 0) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bArr) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        }
        return "";
    }

    public static void h(Handler handler) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException("Must be called on the handler thread");
        }
    }

    public static void i(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e2) {
            l.b("DeflateUtil", "close", e2);
        }
    }

    public static byte[] j(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        try {
            byte[] bytes = upperCase.getBytes(StandardCharsets.UTF_8);
            for (int i2 = 0; i2 < length; i2++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i3 = i2 * 2;
                sb.append(new String(new byte[]{bytes[i3]}, StandardCharsets.UTF_8));
                bArr[i2] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i3 + 1]}, StandardCharsets.UTF_8)).byteValue());
            }
        } catch (NumberFormatException e2) {
            String str2 = "hex string 2 byte array exception : " + e2.getMessage();
        }
        return bArr;
    }

    public static byte[] k(byte[] bArr, int i2) {
        if (bArr == null) {
            return bArr;
        }
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (i2 < 0) {
                bArr[i3] = (byte) (bArr[i3] << (-i2));
            } else {
                bArr[i3] = (byte) (bArr[i3] >> i2);
            }
        }
        return bArr;
    }

    public static byte[] l(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        if (bArr != null) {
            int length = bArr.length;
            if (bArr2.length != length) {
                return null;
            }
            bArr3 = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                bArr3[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
            }
        }
        return bArr3;
    }

    public static int m(Context context) {
        com.hihonor.push.sdk.c0.a aVar;
        if (context != null) {
            com.hihonor.push.sdk.j.a c2 = c(context);
            String c3 = c2.c();
            String str = "service package name is " + c3;
            if (TextUtils.isEmpty(c3)) {
                aVar = com.hihonor.push.sdk.c0.a.NOT_INSTALLED;
            } else {
                try {
                    if (context.getPackageManager().getApplicationInfo(c3, 0).enabled) {
                        aVar = com.hihonor.push.sdk.c0.a.ENABLED;
                    } else {
                        aVar = com.hihonor.push.sdk.c0.a.DISABLED;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    aVar = com.hihonor.push.sdk.c0.a.NOT_INSTALLED;
                }
            }
            if (com.hihonor.push.sdk.c0.a.NOT_INSTALLED.equals(aVar)) {
                return 8002008;
            }
            if (com.hihonor.push.sdk.c0.a.DISABLED.equals(aVar)) {
                return 8002007;
            }
            if (!TextUtils.equals(c3, "android") || TextUtils.isEmpty(c2.e())) {
                return 8002006;
            }
            return com.hihonor.push.sdk.b0.a.SUCCESS.statusCode;
        }
        throw new NullPointerException("context must not be null.");
    }
}
