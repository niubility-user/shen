package com.jd.aips.tracker;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.aips.tracker.UemsPolicyManager;
import com.jd.aips.tracker.util.UemsBase64Util;
import com.jd.aips.tracker.util.UemsHttpUtil;
import com.jd.aips.tracker.util.UemsPersistentUtil;
import com.jdpay.lib.crypto.AES;
import com.jingdong.common.jdreactFramework.utils.RSAUtils;
import com.jingdong.common.utils.security.JDKeyStore;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import com.tencent.connect.common.Constants;
import com.tencent.mapsdk.internal.l4;
import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class UemsTrackerUploader {
    private static final UemsWorkerThread a;

    static {
        UemsWorkerThread uemsWorkerThread = new UemsWorkerThread("uems_tracker_thread");
        a = uemsWorkerThread;
        uemsWorkerThread.start();
    }

    private UemsTrackerUploader() {
    }

    static String a(Context context, String str, JSONObject jSONObject, String str2, String str3, String str4) {
        JSONObject a2;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("logId", str2);
            jSONObject2.put("appPackage", context.getPackageName());
            jSONObject2.put(l4.f16791e, str3);
            jSONObject2.put("buildVersion", context.getApplicationInfo().targetSdkVersion + "");
            jSONObject2.put("appVersion", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            jSONObject2.put("eventId", str);
            jSONObject2.put("trackId", System.currentTimeMillis() + "");
            jSONObject2.put(Constants.PARAM_PLATFORM, "android");
            if (TextUtils.isEmpty(jSONObject.getString("token")) && (a2 = UemsTrackManger.a(context)) != null) {
                jSONObject2.put("deviceInfo", a2.toString());
            }
            jSONObject2.put("kvs", jSONObject.toString());
            jSONObject2.put("time", System.currentTimeMillis());
            jSONObject2.put("action_elapsedRealtime", SystemClock.elapsedRealtime());
            jSONObject2.put("sourceBiz", str4);
            jSONObject2.put("terminalSource", UemsTrackManger.a());
        } catch (Throwable unused) {
        }
        return jSONObject2.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(final Context context, final String str, final JSONObject jSONObject, final String str2, final String str3, final String str4) {
        a.a(new Runnable() { // from class: com.jd.aips.tracker.UemsTrackerUploader.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Context context2 = context;
                    UemsTrackerUploader.a(context2, UemsTrackerUploader.a(context2, str, jSONObject, str2, str3, str4));
                } catch (Throwable unused) {
                }
            }
        });
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(5:58|59|(2:61|(9:63|(1:42)|43|45|46|47|48|49|50)(1:64))|66|(0)(0))|45|46|47|48|49|50) */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0137, code lost:
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0138, code lost:
        r8.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f9 A[Catch: Exception -> 0x013c, TRY_ENTER, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0105 A[Catch: Exception -> 0x013c, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010b A[Catch: Exception -> 0x013c, TryCatch #3 {Exception -> 0x013c, blocks: (B:39:0x00df, B:47:0x00f9, B:51:0x010b, B:52:0x011b, B:48:0x0105), top: B:81:0x00df }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static void a(Context context, String str) {
        SecretKey secretKey;
        byte[] b;
        String str2;
        JSONArray jSONArray;
        byte[] b2;
        UemsPolicyManager uemsPolicyManager;
        byte[] bArr;
        PublicKey publicKey;
        byte[] bArr2;
        byte[] bytes = str.getBytes();
        JSONObject jSONObject = new JSONObject();
        String str3 = null;
        JSONObject jSONObject2 = null;
        str3 = null;
        str3 = null;
        try {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(JDKeyStore.KEY_TYPE_AES);
                keyGenerator.init(128);
                secretKey = keyGenerator.generateKey();
            } catch (Exception e2) {
                e2.printStackTrace();
                secretKey = null;
            }
            if (secretKey != null) {
                try {
                    Cipher cipher = Cipher.getInstance(AES.ALGORITHM);
                    cipher.init(1, secretKey, new IvParameterSpec("0123456789abcdef".getBytes()));
                    bArr = cipher.doFinal(bytes);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    bArr = null;
                }
                if (bArr != null) {
                    jSONObject.put("data", UemsBase64Util.a(bArr));
                    try {
                        publicKey = KeyFactory.getInstance(RSAUtils.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(UemsBase64Util.a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6pjY2EyNg/a9IYjpaCmS\nYaLbcdQoE03BolQEZp4cz0TM4o17BjLxGnQYczGhWeH9Y6UihFES8J6pIyaM27yL\naAjmJfP0p3JD+eue+fUfCgjoFY2GZC5qz4OFkV5lEH9xre2XnIWrXDwQ+QLSXNxR\nywyX7DnUcgkdTKvCyhHyjA0JKf7GlScDw/XjkONaeqL+2P8ySfExV7mAbhAyW0IS\ncx/0/X37k24rlVfghqBZ2Kd52WEd3qFS6EKsxBen6iN8nLH2GXHZqlcKCUgGSYBa\n7NN25RZao7waajDKg/XA6BT/G8PH5kk2COkDOOrUZKz5SHpgwM5MFGcIYbYmpQof\nhwIDAQAB\n")));
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        publicKey = null;
                    }
                    if (publicKey != null) {
                        try {
                            Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                            cipher2.init(1, publicKey);
                            cipher2.update(secretKey.getEncoded());
                            bArr2 = cipher2.doFinal();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            bArr2 = null;
                        }
                        jSONObject.put("visa", UemsBase64Util.a(bArr2));
                    }
                }
            }
        } catch (Exception unused) {
        }
        String jSONObject3 = jSONObject.toString();
        String a2 = UemsHttpUtil.a("https://aiuems.jd.com/mlog/unite/v.do", jSONObject3);
        String optString = new JSONObject(a2).optString(NotifyType.LIGHTS);
        if (!TextUtils.isEmpty(optString) && !"0".equals(optString)) {
            uemsPolicyManager = UemsPolicyManager.Builder.a;
            uemsPolicyManager.a(optString);
        }
        String str4 = context.getFilesDir().getAbsolutePath() + File.separator + "uemstrackercache.txt";
        if (TextUtils.isEmpty(a2)) {
            try {
                File file = new File(str4);
                try {
                    if (file.exists()) {
                        try {
                            b2 = UemsPersistentUtil.b(str4);
                        } catch (Exception unused2) {
                        }
                        if (b2 != null) {
                            str2 = new String(b2, "ISO8859-1");
                            if (str2 == null) {
                                jSONObject2 = new JSONObject(str2);
                                jSONArray = (JSONArray) jSONObject2.get("data");
                                if (jSONObject2 == null) {
                                    file.createNewFile();
                                    jSONObject2 = new JSONObject();
                                    jSONArray = new JSONArray();
                                    jSONObject2.put("data", jSONArray);
                                }
                                jSONArray.put(new JSONObject(jSONObject3));
                                byte[] bytes2 = jSONObject2.toString().getBytes("ISO8859-1");
                                FileOutputStream fileOutputStream = new FileOutputStream(str4);
                                fileOutputStream.write(bytes2);
                                fileOutputStream.close();
                                return;
                            }
                            file.delete();
                        }
                        str2 = null;
                        if (str2 == null) {
                        }
                    }
                    byte[] bytes22 = jSONObject2.toString().getBytes("ISO8859-1");
                    FileOutputStream fileOutputStream2 = new FileOutputStream(str4);
                    fileOutputStream2.write(bytes22);
                    fileOutputStream2.close();
                    return;
                } catch (Exception unused3) {
                    return;
                }
                jSONArray = null;
                if (jSONObject2 == null) {
                }
                jSONArray.put(new JSONObject(jSONObject3));
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        } else {
            try {
                if (new File(str4).exists() && (b = UemsPersistentUtil.b(str4)) != null) {
                    str3 = new String(b, "ISO8859-1");
                }
            } catch (Exception unused4) {
            }
            if (str3 == null || TextUtils.isEmpty(UemsHttpUtil.a("https://aiuems.jd.com/mlog/batch/unite/v.do", str3))) {
                return;
            }
            UemsPersistentUtil.a(str4);
        }
    }
}
