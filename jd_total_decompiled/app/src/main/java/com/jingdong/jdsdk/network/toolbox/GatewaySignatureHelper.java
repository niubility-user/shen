package com.jingdong.jdsdk.network.toolbox;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl;

/* loaded from: classes14.dex */
public class GatewaySignatureHelper {
    public static final String TAG = "GatewaySignatureHelper";

    private static String HMACSHA256(byte[] bArr, byte[] bArr2) {
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

    public static String signature(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            Set<String> queryParameterNames = parse.queryParameterNames();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(queryParameterNames);
            if (!TextUtils.isEmpty(str2) && !queryParameterNames.contains("body")) {
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
                    String queryParameter = parse.queryParameter(obj);
                    if (TextUtils.equals(obj, "body") && TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2)) {
                        queryParameter = str2;
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "sorted key : " + obj + ", value : " + queryParameter);
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
                if (OKLog.D) {
                    OKLog.d(TAG, "raw signature param str : " + stringBuffer2);
                }
                str = str + String.format("&sign=%s", HMACSHA256(strToByteArray(stringBuffer2), strToByteArray(str3)));
                if (OKLog.D) {
                    OKLog.d(TAG, "url after sign : " + str);
                }
            }
        }
        return str;
    }

    public static String signature2(String str, String str2, String str3, Map<String, String> map) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            Set<String> queryParameterNames = parse.queryParameterNames();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(queryParameterNames);
            if (!TextUtils.isEmpty(str2) && !queryParameterNames.contains("body")) {
                linkedHashSet.add("body");
            }
            if (map != null && !map.isEmpty()) {
                for (String str4 : map.keySet()) {
                    String str5 = map.get(str4);
                    if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str5)) {
                        linkedHashSet.add(str4);
                    }
                }
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
                    String queryParameter = parse.queryParameter(obj);
                    if (TextUtils.equals(obj, "body") && TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2)) {
                        queryParameter = str2;
                    }
                    if (map != null && map.containsKey(obj)) {
                        queryParameter = map.get(obj);
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "sorted key : " + obj + ", value : " + queryParameter);
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
                if (OKLog.D) {
                    OKLog.d(TAG, "raw signature param str : " + stringBuffer2);
                }
                String format = String.format("&sign=%s", HMACSHA256(strToByteArray(stringBuffer2), strToByteArray(str3)));
                if (OKLog.D) {
                    OKLog.d(TAG, "sign result : " + format);
                }
                return format;
            }
            return "";
        }
        return "";
    }

    public static String signature3(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        HttpUrl parse = HttpUrl.parse(str);
        if (parse != null) {
            Set<String> queryParameterNames = parse.queryParameterNames();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(queryParameterNames);
            if (!TextUtils.isEmpty(str2) && !queryParameterNames.contains("body")) {
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
                    String queryParameter = parse.queryParameter(obj);
                    if (TextUtils.equals(obj, "body") && TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(str2)) {
                        queryParameter = str2;
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "sorted key : " + obj + ", value : " + queryParameter);
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
                if (OKLog.D) {
                    OKLog.d(TAG, "raw signature param str : " + stringBuffer2);
                }
                String HMACSHA256 = HMACSHA256(strToByteArray(stringBuffer2), strToByteArray(str3));
                if (OKLog.D) {
                    OKLog.d(TAG, "sign result : " + HMACSHA256);
                }
                return HMACSHA256;
            }
            return "";
        }
        return "";
    }

    public static byte[] strToByteArray(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }
}
