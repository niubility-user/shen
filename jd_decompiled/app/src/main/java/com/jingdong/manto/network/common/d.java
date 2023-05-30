package com.jingdong.manto.network.common;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.manto.utils.MantoCryptoUtils;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* loaded from: classes16.dex */
class d {

    /* loaded from: classes16.dex */
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
    public static String a(boolean z, String str, JSONObject jSONObject, String str2) {
        try {
            TreeMap treeMap = new TreeMap(new a());
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(jSONObject.optString(next))) {
                        treeMap.put(next, jSONObject.optString(next));
                    }
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                treeMap.put("body", str2);
            }
            if (!TextUtils.isEmpty(str) && z) {
                treeMap.put("functionId", str);
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
            String b = com.jingdong.manto.b.g().b("sign_secret");
            if (TextUtils.isEmpty(b)) {
                b = MantoCryptoUtils.a(com.jingdong.manto.b.l() + "7D6D16CC3D2BE89108F9DCFC9A855253", "616E746F");
            }
            String a2 = MantoCryptoUtils.a(sb.toString().getBytes("UTF-8"), b.getBytes("UTF-8"));
            return !TextUtils.isEmpty(a2) ? a2.toLowerCase() : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
