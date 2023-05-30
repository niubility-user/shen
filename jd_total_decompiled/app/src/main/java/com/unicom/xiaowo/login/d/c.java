package com.unicom.xiaowo.login.d;

import com.huawei.hms.framework.common.ContainerUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class c {
    private static TreeMap<?, ?> a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            TreeMap<?, ?> treeMap = new TreeMap<>();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                treeMap.put(next, jSONObject.getString(next));
            }
            return treeMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String a(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            boolean z = true;
            for (Map.Entry<?, ?> entry : a(new JSONObject(str).toString()).entrySet()) {
                stringBuffer.append(z ? "" : str2);
                stringBuffer.append((String) entry.getKey());
                stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                stringBuffer.append(entry.getValue() != null ? entry.getValue() : "");
                z = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
