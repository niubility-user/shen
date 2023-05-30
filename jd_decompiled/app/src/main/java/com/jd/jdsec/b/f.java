package com.jd.jdsec.b;

import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* loaded from: classes13.dex */
public class f {
    private int a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f2735c;

    private String b(Map<String, String> map) {
        return c(map, "UTF-8");
    }

    private String c(Map<String, String> map, String str) {
        String str2;
        if (map != null && !map.isEmpty() && (str2 = map.get(HttpHeaders.CONTENT_TYPE)) != null) {
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

    public String a() {
        if (this.b == null) {
            return "";
        }
        try {
            return new String(this.b, b(this.f2735c));
        } catch (UnsupportedEncodingException unused) {
            return new String(this.b);
        }
    }

    public void d(int i2) {
        this.a = i2;
    }

    public void e(byte[] bArr) {
        this.b = bArr;
    }

    public int f() {
        return this.a;
    }

    public void g(Map<String, String> map) {
        this.f2735c = map;
    }

    public boolean h() {
        int i2 = this.a;
        return i2 >= 200 && i2 < 300;
    }
}
