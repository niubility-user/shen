package com.jdjr.risk.util.b;

import android.content.Context;
import android.text.TextUtils;
import com.wangyin.platform.CryptoUtils;
import java.security.MessageDigest;
import java.util.Arrays;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes18.dex */
public class e {
    public static String a = "MIIESTCCAzGgAwIBAgIUb9ahAbcp4PbmKCuGRs+GaBrPMBgwDQYJKoZIhvcNAQELBQAwXjEYMBYGA1UEAwwPV2FuZ1lpbiBVc2VyIENBMR8wHQYDVQQLDBZXYW5nWWluIFNlY3VyaXR5Q2VudGVyMRQwEgYDVQQKDAtXYW5nWWluLmNvbTELMAkGA1UEBhMCQ04wHhcNMTgwODI5MTAyOTE2WhcNMTkwODI5MTAyOTE2WjCBlDF0MHIGA1UEAwxr5Lqs5Lic6YeR6J6NLeaKgOacr+eglOWPkemDqC3kuKrkurrkuJrliqHnu7zlkIjnoJTlj5Hpg6gt6aOO5o6n56CU5Y+R6YOoLeaZuuiDveivhuWIq+WunumqjOWupChBS1MwMDAwMEFLUykxDzANBgNVBAsMBmpyIHRvcDELMAkGA1UECgwCamQwggEgMA0GCSqGSIb3DQEBAQUAA4IBDQAwggEIAoIBAQC40b+9fdJRXY+AOdC5I3mfwZVFWMzpc+8CSBseuMdKEX57stGoKAVilElvUVCM4amrBqb90/18Ji9fQ+Ra/hiOxjsaDkhrMkSwi1b+VT4Zg3orn/Gpt9/A7UpfRCZlhKVTI370k6vfTZgKtXOtowDtksPLhYffu/vJbCuSN2gMq0WmZ55WWXWE6QRB/0r9nOtBjjs6Ebsj3M99TUbZtgt6MKsOmsK9bfyYiNhZdq2L7F77JcbM7ZRil//xI4ET5ks1hYzrt4rXrg26ATLZhkjSmsDTuuMfk1QkqIRLlQdIDuaWpU6rTg8u8lUDsTSd2gsk71EAaeP2dfWaL60++ZDHAgEDo4HJMIHGMAkGA1UdEwQCMAAwCwYDVR0PBAQDAgbAMGwGA1UdHwRlMGMwYaBfoF2GW2h0dHA6Ly90b3BjYS5kLmNoaW5hYmFuay5jb20uY24vcHVibGljL2l0cnVzY3JsP0NBPTFFRTQ1QjcxNkQwOUE0OTI4MkIxMzQ2QTJDQzNDNjI3MzExMzgwRUIwHwYDVR0jBBgwFoAUCKxvAe67vsOUVzpp1dx/r34ctOAwHQYDVR0OBBYEFOxwX51lfkiPGzdSHJp/aoWEy7yGMA0GCSqGSIb3DQEBCwUAA4IBAQAQFz4OkKRmF1eahWwFes7ZMLmYuc+wc1Jfa166Ylefjb79zu3p+P+Acb07hhbKioHIdsw6IszzYqMntmP9OfCAkXhxEmAeZNAgsHdw5aIoD4Uzg0pD7oVKjCaStFsadaPUa3vVJR/grKFAQRPunsGC8pLb8X2WjBOeYLZNgAwUhrtJZzjeog+zYvQRo55Ed/kXVHrdgSVA9vCmhKwnmRhe6kzJj7GUikqm4GdQhjJIfkV/0eULsrLEhM+dHn4qKDdZzNBIa/AEQDpC9pmD8ZnIzxAAdeuPOhOuv/DyCvQwIv4KymYASHIl4ouMOYV8hPgau2W5H4bUyPKbz4HiM/Gf";

    public static String a(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            byte[] encodeDataToServer = CryptoUtils.newInstance(context).encodeDataToServer(str, System.currentTimeMillis());
            return "00000".equals(new String(Arrays.copyOfRange(encodeDataToServer, 0, 5))) ? new String(Arrays.copyOfRange(encodeDataToServer, 5, encodeDataToServer.length)) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String a(Context context, byte[] bArr) {
        if (bArr != null) {
            try {
                byte[] p7Envelope = CryptoUtils.newInstance(context).p7Envelope(a, bArr);
                byte[] bArr2 = new byte[p7Envelope.length - 5];
                System.arraycopy(p7Envelope, 0, new byte[5], 0, 5);
                System.arraycopy(p7Envelope, 5, bArr2, 0, p7Envelope.length - 5);
                return "AKS*_*" + g.a(bArr2);
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static String a(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            messageDigest.update(str.getBytes());
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            byte[] decodeDataFromServer = CryptoUtils.newInstance(context).decodeDataFromServer(str);
            return "00000".equals(new String(Arrays.copyOfRange(decodeDataFromServer, 0, 5))) ? new String(Arrays.copyOfRange(decodeDataFromServer, 5, decodeDataFromServer.length)) : "";
        } catch (Throwable unused) {
            return "";
        }
    }
}
