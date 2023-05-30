package com.jingdong.aura.sdk.update.b;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hms.framework.common.ContainerUtils;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes4.dex */
public final class n {
    private static final byte[] a = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static String a() {
        return Base64.encodeToString(a.a((com.jingdong.aura.sdk.update.a.a().a.appKey + com.jingdong.aura.sdk.update.a.a().a.uuid + com.jingdong.aura.sdk.update.a.a().a.appVersionName + com.jingdong.aura.sdk.update.a.a().a.appVersionCode).getBytes(), com.jingdong.aura.sdk.update.a.a().a.appSecret.getBytes(), a), 2);
    }

    public static String a(Map<String, String> map, String str, String str2) {
        TreeMap treeMap = new TreeMap(new Comparator<String>() { // from class: com.jingdong.aura.sdk.update.b.n.1
            @Override // java.util.Comparator
            public final /* synthetic */ int compare(String str3, String str4) {
                return str3.compareTo(str4);
            }
        });
        treeMap.put("functionId", "avatarHotfixPackages");
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(value)) {
                    treeMap.put(key, value);
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
        return a(sb.toString().getBytes(Charset.forName("UTF-8")), str2.getBytes(Charset.forName("UTF-8")));
    }

    private static String a(byte[] bArr) {
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

    private static String a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return a(mac.doFinal(bArr));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
