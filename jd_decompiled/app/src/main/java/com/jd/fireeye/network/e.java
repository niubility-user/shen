package com.jd.fireeye.network;

import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e {
    private int a;
    private byte[] b;

    /* renamed from: c  reason: collision with root package name */
    private Map<String, String> f2625c;

    public void a(byte[] bArr) {
        this.b = bArr;
    }

    public void b(Map<String, String> map) {
        this.f2625c = map;
    }

    public int c() {
        return this.a;
    }

    public boolean d() {
        int i2 = this.a;
        return i2 >= 200 && i2 < 300;
    }

    public void a(int i2) {
        this.a = i2;
    }

    public String b() {
        if (this.b == null) {
            return "";
        }
        try {
            return new String(this.b, a(this.f2625c));
        } catch (UnsupportedEncodingException unused) {
            return new String(this.b);
        }
    }

    public JSONObject a() {
        if (this.b == null) {
            return null;
        }
        try {
            return new JSONObject(b());
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private String a(Map<String, String> map, String str) {
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

    private String a(Map<String, String> map) {
        return a(map, "UTF-8");
    }
}
