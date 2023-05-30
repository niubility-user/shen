package com.jingdong.sdk.jdhttpdns.core;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes7.dex */
public final class SignatureHelper {
    public static final String TAG = "SignatureHelper";

    public static String HMACSHA256(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return byte2hex(mac.doFinal(bArr));
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String byte2hex(byte[] bArr) {
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

    public static String signature(HashMap<String, String> hashMap, String str) {
        if (hashMap == null || hashMap.size() <= 0) {
            return null;
        }
        TreeSet treeSet = new TreeSet();
        Iterator<Map.Entry<String, String>> it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            treeSet.add(it.next().getKey());
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it2 = treeSet.iterator();
        while (it2.hasNext()) {
            String obj = it2.next().toString();
            String str2 = hashMap.get(obj);
            if (DNSLog.D) {
                DNSLog.d(TAG, "sorted key : " + obj + ", value : " + str2);
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
        if (DNSLog.D) {
            DNSLog.d(TAG, "before signature param str : " + stringBuffer2);
        }
        return HMACSHA256(strToByteArray(stringBuffer2), strToByteArray(str));
    }

    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }
}
