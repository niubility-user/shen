package com.jingdong.app.mall.bundle.mobileConfig.c;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
public class b {
    public static String a(String str, String str2, String str3) {
        Uri parse;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
            return str;
        }
        Set<String> queryParameterNames = parse.getQueryParameterNames();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(queryParameterNames);
        if (!TextUtils.equals("{}", str2) && !queryParameterNames.contains("body")) {
            linkedHashSet.add("body");
        }
        if (linkedHashSet.size() > 0) {
            TreeSet treeSet = new TreeSet();
            Iterator it = linkedHashSet.iterator();
            while (it.hasNext()) {
                treeSet.add((String) it.next());
            }
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it2 = treeSet.iterator();
            while (it2.hasNext()) {
                String obj = it2.next().toString();
                String queryParameter = parse.getQueryParameter(obj);
                if (TextUtils.equals(obj, "body") && TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2) && !TextUtils.equals("{}", str2)) {
                    queryParameter = str2;
                }
                if (!TextUtils.isEmpty(queryParameter)) {
                    stringBuffer.append(queryParameter);
                    stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                }
            }
            String stringBuffer2 = stringBuffer.toString();
            if (stringBuffer2.endsWith(ContainerUtils.FIELD_DELIMITER) && stringBuffer2.length() > 1) {
                stringBuffer2 = stringBuffer2.substring(0, stringBuffer2.length() - 1);
            }
            return str + String.format("&sign=%s", a(a(stringBuffer2), a(str3)));
        }
        return str;
    }

    public static String a(byte[] bArr) {
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

    private static String a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return a(mac.doFinal(bArr));
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }
}
