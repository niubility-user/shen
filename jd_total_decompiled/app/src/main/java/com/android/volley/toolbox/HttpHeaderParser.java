package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import java.util.Map;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

/* loaded from: classes.dex */
public class HttpHeaderParser {
    public static <T> Cache.Entry parseCacheHeaders(Request<T> request, NetworkResponse networkResponse) {
        boolean z;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        String str = map.get(HttpHeaders.DATE);
        long parseDateAsEpoch = str != null ? parseDateAsEpoch(str) : 0L;
        String str2 = map.get(HttpHeaders.CACHE_CONTROL);
        int i2 = 0;
        if (str2 != null) {
            String[] split = str2.split(DYConstants.DY_REGEX_COMMA);
            int i3 = 0;
            j2 = 0;
            j3 = 0;
            while (i2 < split.length) {
                String trim = split[i2].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j3 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i3 = 1;
                }
                i2++;
            }
            i2 = i3;
            z = true;
        } else {
            z = false;
            j2 = 0;
            j3 = 0;
        }
        String str3 = map.get(HttpHeaders.EXPIRES);
        long parseDateAsEpoch2 = str3 != null ? parseDateAsEpoch(str3) : 0L;
        String str4 = map.get(HttpHeaders.ETAG);
        if (z) {
            j5 = currentTimeMillis + (j2 * 1000);
            if (i2 != 0) {
                j6 = j5;
            } else {
                Long.signum(j3);
                j6 = (j3 * 1000) + j5;
            }
            j4 = 0;
        } else {
            j4 = 0;
            j5 = (parseDateAsEpoch <= 0 || parseDateAsEpoch2 < parseDateAsEpoch) ? 0L : currentTimeMillis + (parseDateAsEpoch2 - parseDateAsEpoch);
            j6 = j5;
        }
        if (request.getCacheTime() > j4) {
            try {
                j5 = Long.parseLong(str3);
                j6 = j5;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String str5 = map.get(HttpHeaders.LAST_MODIFIED);
        long parseDateAsEpoch3 = str5 != null ? parseDateAsEpoch(str5) : j4;
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = str4;
        entry.softTtl = j5;
        entry.ttl = j6;
        entry.serverDate = parseDateAsEpoch;
        entry.lastModified = parseDateAsEpoch3;
        entry.responseHeaders = map;
        return entry;
    }

    public static String parseCharset(Map<String, String> map, String str) {
        String str2 = map.get(HttpHeaders.CONTENT_TYPE);
        if (str2 == null) {
            str2 = map.get("content-type");
        }
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i2 = 1; i2 < split.length; i2++) {
                String[] split2 = split[i2].trim().split(ContainerUtils.KEY_VALUE_DELIMITER);
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0L;
        }
    }

    public static String parseCharset(Map<String, String> map) {
        return parseCharset(map, CharEncoding.ISO_8859_1);
    }
}
