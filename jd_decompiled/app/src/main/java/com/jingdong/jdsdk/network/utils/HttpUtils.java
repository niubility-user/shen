package com.jingdong.jdsdk.network.utils;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import g.c.a.b;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes14.dex */
public class HttpUtils {
    public static String getHeaderFieldIgnoreCase(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty() && !TextUtils.isEmpty(str)) {
            if (map.containsKey(str)) {
                return map.get(str);
            }
            if (map.containsKey(str.toLowerCase())) {
                return map.get(str.toLowerCase());
            }
        }
        return null;
    }

    public static InetAddress ip2InetAddress(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            if (InetAddressUtils.isIPv4Address(str2)) {
                String[] split = str2.split("\\.");
                byte[] bArr = new byte[4];
                for (int i2 = 0; i2 < 4; i2++) {
                    bArr[i2] = (byte) (Integer.parseInt(split[i2]) & 255);
                }
                try {
                    return InetAddress.getByAddress(str, bArr);
                } catch (UnknownHostException e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            if (str2.startsWith("[") && str2.endsWith("]")) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            byte[] bArr2 = new byte[16];
            System.arraycopy(b.fromString(str2).toByteArray(), 0, bArr2, 0, 16);
            try {
                return Inet6Address.getByAddress(str, bArr2);
            } catch (UnknownHostException e3) {
                e3.printStackTrace();
                return null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
        th.printStackTrace();
        return null;
    }

    public static List<String> splitQuery(URL url) {
        ArrayList arrayList = new ArrayList();
        try {
            String query = url.getQuery();
            if (!TextUtils.isEmpty(query)) {
                arrayList.addAll(Arrays.asList(query.split(ContainerUtils.FIELD_DELIMITER)));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return arrayList;
    }

    public static String toQueryStr(List<String> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        String sb2 = sb.toString();
        return sb2.endsWith(ContainerUtils.FIELD_DELIMITER) ? sb2.substring(0, sb2.length() - 1) : sb2;
    }
}
