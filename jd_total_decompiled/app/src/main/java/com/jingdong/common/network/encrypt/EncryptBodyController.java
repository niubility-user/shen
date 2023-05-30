package com.jingdong.common.network.encrypt;

import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class EncryptBodyController {
    public static String encryptBody(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("body", str);
        }
        return EncryptTool.encrypt(hashMap);
    }
}
