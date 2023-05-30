package com.meizu.cloud.pushsdk.platform;

import com.huawei.hms.framework.common.ContainerUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes14.dex */
public class b {
    public static String a(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Map<String, String> map, String str) {
        Set<Map.Entry> entrySet = new TreeMap(map).entrySet();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : entrySet) {
            sb.append((String) entry.getKey());
            sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
            sb.append((String) entry.getValue());
        }
        sb.append(str);
        return a(sb.toString());
    }
}
