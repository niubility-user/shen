package com.jingdong.jdsdk.network.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes14.dex */
public class ParamEncodeUtil {
    public static final String DEFAULT_CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    public static final String DEFAULT_PARAMS_ENCODING = "UTF-8";

    public static byte[] encodeParameters(Map<String, String> map, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (z) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append('=');
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append(Typography.amp);
                }
                return sb.toString().getBytes(str);
            } catch (UnsupportedEncodingException e2) {
                throw new RuntimeException("Encoding not supported: " + str, e2);
            }
        }
        for (Map.Entry<String, String> entry2 : map.entrySet()) {
            sb.append(entry2.getKey());
            sb.append('=');
            sb.append(entry2.getValue());
            sb.append(Typography.amp);
        }
        return sb.toString().getBytes();
    }
}
