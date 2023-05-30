package com.jingdong.jdreact.plugin.network;

import com.jingdong.jdreact.plugin.utils.CommonUtil;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes13.dex */
public class SignTool {
    public static String HMACSHA256(String str, String str2) {
        return (str == null || str2 == null) ? "" : HMACSHA256(str.getBytes(), str2.getBytes());
    }

    public static String HMACSHA256(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "HmacSHA256");
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(secretKeySpec);
                return CommonUtil.bytes2hex(mac.doFinal(bArr));
            } catch (Exception e2) {
                LogUtil.e("HMACSHA256", e2);
            }
        }
        return "";
    }
}
