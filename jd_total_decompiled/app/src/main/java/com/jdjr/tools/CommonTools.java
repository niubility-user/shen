package com.jdjr.tools;

import android.content.Context;
import android.content.SharedPreferences;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import java.util.Calendar;

/* loaded from: classes18.dex */
public class CommonTools {
    public static final String KEY_FUNCLIST = "func_list";
    private static final int MINUTE = 36000;
    private static final String NAME_SP = "JDJR_Security";
    private static final String TAG = "CommonTools";
    private static SharedPreferences mInstance;
    private static Object mLock = new Object();

    public static String generateHttpFixedData(String str, String str2, String str3) {
        String str4 = getCurrentTime() + str + str2;
        int length = str3.length();
        String str5 = str4 + String.format("%08d", Integer.valueOf(length));
        try {
            str5 = str5 + String.format("%08d", Integer.valueOf(Integer.valueOf(str + str2).intValue() ^ length));
        } catch (NumberFormatException e2) {
            JDJRLog.e(TAG, e2.getMessage());
        }
        String str6 = str5 + str3;
        JDJRLog.i(TAG, "http fixed :" + str6);
        return str6;
    }

    public static String getACMAddress() {
        return "https://aks.jdpay.com/up/datacollect";
    }

    public static String getApplyCertAddress() {
        return "https://aks.jdpay.com/certa2";
    }

    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return String.format("%04d", Integer.valueOf(calendar.get(1))) + String.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(2) + 1)) + String.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(5))) + String.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(11))) + String.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(12))) + String.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(calendar.get(13))) + String.format("%03d", Integer.valueOf(calendar.get(14)));
    }

    private static SharedPreferences getDefaultSharePreference(Context context) {
        if (mInstance == null) {
            synchronized (mLock) {
                if (mInstance == null) {
                    mInstance = context.getSharedPreferences(NAME_SP, 0);
                }
            }
        }
        return mInstance;
    }

    public static String getDegradeAddress() {
        return "https://aks.jdpay.com/down/func";
    }

    public static String getIntegrityCheckAddress() {
        return "https://aks.jdpay.com/app/verify";
    }

    public static String getRootCerts() {
        return "MIIJFTCCB/2gAwIBAgIMF1fO3seLXO0TFoEgMA0GCSqGSIb3DQEBCwUAMFAxCzAJBgNVBAYTAkJFMRkwFwYDVQQKExBHbG9iYWxTaWduIG52LXNhMSYwJAYDVQQDEx1HbG9iYWxTaWduIFJTQSBPViBTU0wgQ0EgMjAxODAeFw0xOTA3MDgwNDU3MDJaFw0yMDEwMjgwOTQyNTRaMIGIMQswCQYDVQQGEwJDTjEQMA4GA1UECBMHYmVpamluZzEQMA4GA1UEBxMHYmVpamluZzFCMEAGA1UEChM5QkVJSklORyBKSU5HRE9ORyBTSEFOR0tFIElORk9STUFUSU9OIFRFQ0hOT0xPR1kgQ08uLCBMVEQuMREwDwYDVQQDDAgqLmpkLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAOXt0vMNO3GAkNgSqHpbXVnd3K6CivkJP4c6F5WhfXzDm06yrW1giHsBhw95pWPq05jsGBrI3AUgXHW/zPSZYdUzV7MZ5g6rQoomVAUrffUKsVE+oQjAQTnH2Thr9RmGykH6iS/xworljoPq725qsg6dRmlO1XOQoyd78fcBoO05pMSbL9EcSZcfT7qA4dJmKL1c1FoXe8gRUWlmYufgikTWrjN8VIF4ufi/M30fKg+bWemjyXzaXyY2fykjf3zCW4/wmdHrgD/WcHxbeecdVZs3PrPwUkAaxgy7QwEpqj+Hja102eZOWeifDiq3ozy2hELpYw7ewdkJRQLuQOqxdi0CAwEAAaOCBbQwggWwMA4GA1UdDwEB/wQEAwIFoDCBjgYIKwYBBQUHAQEEgYEwfzBEBggrBgEFBQcwAoY4aHR0cDovL3NlY3VyZS5nbG9iYWxzaWduLmNvbS9jYWNlcnQvZ3Nyc2FvdnNzbGNhMjAxOC5jcnQwNwYIKwYBBQUHMAGGK2h0dHA6Ly9vY3NwLmdsb2JhbHNpZ24uY29tL2dzcnNhb3Zzc2xjYTIwMTgwVgYDVR0gBE8wTTBBBgkrBgEEAaAyARQwNDAyBggrBgEFBQcCARYmaHR0cHM6Ly93d3cuZ2xvYmFsc2lnbi5jb20vcmVwb3NpdG9yeS8wCAYGZ4EMAQICMAkGA1UdEwQCMAAwggLKBgNVHREEggLBMIICvYIIKi5qZC5jb22CBiouMy5jboIMKi4zNjBidXkuY29tgg8qLjM2MGJ1eWltZy5jb22CDCouN2ZyZXNoLmNvbYINKi5iYWl0aWFvLmNvbYILKi5jYWl5dS5jb22CEiouY2hpbmFiYW5rLmNvbS5jboIKKi5lLmpkLmNvbYIKKi5qZC5jby50aIIHKi5qZC5oa4IHKi5qZC5pZIIHKi5qZC5ydYILKi5qZHBheS5jb22CCSouamR4LmNvbYIMKi5qb3lidXkuY29tggsqLmpveWJ1eS5lc4ILKi5qci5qZC5jb22CCiouay5qZC5jb22CDioua21hbGwuamQuY29tggoqLm0uamQuY29tgg4qLm0ucGFpcGFpLmNvbYILKi5tLnloZC5jb22CCyoub2N3bXMuY29tggwqLnBhaXBhaS5jb22CDSouc2hvcC5qZC5jb22CDSoudG9wbGlmZS5jb22CDSoud2FuZ3lpbi5jb22CCSoueWhkLmNvbYISKi55aWhhb2RpYW5pbWcuY29tgg0qLnlpeWFvamQuY29tgg4qLnlpemhpdG91LmNvbYIEMy5jboIKMzYwYnV5LmNvbYINMzYwYnV5aW1nLmNvbYIKN2ZyZXNoLmNvbYILYmFpdGlhby5jb22CCWNhaXl1LmNvbYIQY2hpbmFiYW5rLmNvbS5jboIIamQuY28udGiCBWpkLmhrggVqZC5pZIIFamQucnWCCWpkcGF5LmNvbYIHamR4LmNvbYIKam95YnV5LmNvbYIJam95YnV5LmVzgglvY3dtcy5jb22CCnBhaXBhaS5jb22CC3RvcGxpZmUuY29tggt3YW5neWluLmNvbYIHeWhkLmNvbYIQeWloYW9kaWFuaW1nLmNvbYILeWl5YW9qZC5jb22CDHlpemhpdG91LmNvbYIGamQuY29tMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcDAjAfBgNVHSMEGDAWgBT473/yzXhnqN5vjySNiPGHAwKz6zAdBgNVHQ4EFgQU69HbAq922jZayl6dpC3k0z2350owggF7BgorBgEEAdZ5AgQCBIIBawSCAWcBZQB2AG9Tdqwx8DEZ2JkApFEV/3cVHBHZAsEAKQaNsgiaN9kTAAABa8/xRTgAAAQDAEcwRQIgKG674unWu26NSs4F5x19Vt71IGZh1PexGzRoyscnwcsCIQDxdaGcswcm2q4AGJY+JtS3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";
    }

    public static String getServerCert() {
        return "MIIEhzCCA2+gAwIBAgIUb9PENTGiiBPWjL8R43mOe8tiG1swDQYJKoZIhvcNAQEFBQAwXjEYMBYGA1UEAwwPV2FuZ1lpbiBVc2VyIENBMR8wHQYDVQQLDBZXYW5nWWluIFNlY3VyaXR5Q2VudGVyMRQwEgYDVQQKDAtXYW5nWWluLmNvbTELMAkGA1UEBhMCQ04wHhcNMTcwNzI2MDIzODA0WhcNMTgwNzI2MDIzODA0WjBjMQswCQYDVQQLDAJqcjELMAkGA1UECgwCamQxJDAiBgkqhkiG9w0BCQEWFXd5d2FuZ3RpZWNoZW5nQGpkLmNvbTEhMB8GA1UEAwwYQUtTKFBVQkxJQykoQUtTMDAwMDBBS1MpMIIBIDANBgkqhkiG9w0BAQEFAAOCAQ0AMIIBCAKCAQEA4YeUWdrlIHTum9Pi/NdRcg981sfYWLeRwfpB5pIpqjc5UXvYn4i2RdeeYX965xmifxxtK8rqCrhg8fgrJfuykMxiBWlPg4fdjndAC1LuDmdQUGnQiytBpqGLfRNCbUobz61BDNB1m6mzr/QzpdmGz/zf28ml/cKhHVHOsSyzWA4Rfpxrt5lQ7uX5ixZ9gtc7bnJzEZm1EMHgP++Svfg9mXvDU/sDaoaaDBVD78BPeLT7wdX3GGFz8tO1okFz3uykd1prEYIzABG2Kw9VSFeqjcFxTne4fP7yvNO5zwmjzGD4vvHKUUOudzTFOA/xWaWNgpzzWXx+Ly1T6zdq/RCu3QIBA6OCATgwggE0MAkGA1UdEwQCMAAwCwYDVR0PBAQDAgTwMGwGA1UdLgRlMGMwYaBfoF2GW2h0dHA6Ly90b3BjYS5kLmNoaW5hYmFuay5jb20uY24vcHVibGljL2l0cnVzY3JsP0NBPTFFRTQ1QjcxNkQwOUE0OTI4MkIxMzQ2QTJDQzNDNjI3MzExMzgwRUIwbAYDVR0fBGUwYzBhoF+gXYZbaHR0cDovL3RvcGNhLmQuY2hpbmFiYW5rLmNvbS5jbi9wdWJsaWMvaXRydXNjcmw/Q0E9MUVFNDVCNzE2RDA5QTQ5MjgyQjEzNDZBMkNDM0M2MjczMTEzODBFQjAfBgNVHSMEGDAWgBQIrG8B7ru+w5RXOmnV3H+vfhy04DAdBgNVHQ4EFgQU2ANXt2VNVFykKkkP7qOzEzs+AVIwDQYJKoZIhvcNAQEFBQADggEBABT6RpA8S1dbwRreohU8q8yRCARjAKAlZYqQBPV3vdSu7YXxuIKV2adhOyasVadpeMNNsMQmtQxN93n+uo7OJH/HB/1PYGM8bGv0qHuNGX5B90UZeJSQxYjYuGrQJQd24bVRRZuaeEIC7Gkq6zI52APt/TiLtvga7UC+iSmtN8Q/OkN+fQBpexDX4iInyG/lUXrXZ69SiJw2u1WnrPMDHbjOaoocEWwZdhqjOSVDz0t40IrtHc/TQERNHTAlBsm5EqQMEiThIgEkJjFUc+Y0AMrHr9KbODgDGAcH1EcdMMM4FbF6yd0TiGIxFnqngcKFet52lxo+5L/VsSQrFhxUohM=";
    }

    public static String getServerCertsAddress() {
        return "https://aks.jdpay.com/down/cert";
    }

    public static String getStringSharePreference(Context context, String str, String str2) {
        try {
            return getDefaultSharePreference(context).getString(str, str2);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static boolean isOvertime20min(String str) {
        return str != null && Long.valueOf(getCurrentTime()).longValue() - Long.valueOf(str).longValue() > 720000;
    }

    public static void putStringSharePreference(Context context, String str, String str2) {
        try {
            SharedPreferences.Editor edit = getDefaultSharePreference(context).edit();
            edit.putString(str, str2);
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static String upCertsErrorInfoAddress() {
        return "https://aks-mini.jdpay.com/up/sdk";
    }
}
