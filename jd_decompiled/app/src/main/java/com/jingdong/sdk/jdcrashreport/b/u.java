package com.jingdong.sdk.jdcrashreport.b;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
public class u {

    /* loaded from: classes7.dex */
    class a implements Comparator<String> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str.compareTo(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Map<String, String> map, String str, String str2) {
        try {
            TreeMap treeMap = new TreeMap(new a());
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getValue())) {
                        treeMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (str != null) {
                treeMap.put("body", str);
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = treeMap.entrySet().iterator();
            while (it.hasNext()) {
                sb.append((String) ((Map.Entry) it.next()).getValue());
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            if (sb.toString().endsWith(ContainerUtils.FIELD_DELIMITER)) {
                sb.setLength(sb.length() - 1);
            }
            String c2 = c(sb.toString().getBytes("UTF-8"), str2.getBytes("UTF-8"));
            return !TextUtils.isEmpty(c2) ? c2.toLowerCase() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String b(byte[] bArr) {
        try {
            return new BigInteger(1, MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(bArr)).toString(16);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String c(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return d(mac.doFinal(bArr));
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private static String d(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; bArr != null && i2 < bArr.length; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }
}
