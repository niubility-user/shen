package com.sina.weibo.sdk.net;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: classes9.dex */
public final class h {
    private HashMap<String, String> q = new HashMap<>();

    public final String g() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : this.q.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            String str2 = this.q.get(str);
            if (!TextUtils.isEmpty(str2)) {
                try {
                    sb.append(URLEncoder.encode(str, "UTF-8"));
                    sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                    sb.append(URLEncoder.encode(str2, "UTF-8"));
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public final void put(String str, String str2) {
        this.q.put(str, str2);
    }
}
