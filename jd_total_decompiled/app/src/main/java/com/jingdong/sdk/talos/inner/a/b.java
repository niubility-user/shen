package com.jingdong.sdk.talos.inner.a;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.talos.inner.e;
import com.tencent.mapsdk.internal.l4;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes10.dex */
public final class b extends a {

    /* renamed from: m  reason: collision with root package name */
    private static final List<String> f15489m = Arrays.asList("uuid", HybridSDK.APP_VERSION, HybridSDK.APP_VERSION_CODE, HybridSDK.D_BRAND, HybridSDK.D_MODEL, HybridSDK.OS_VERSION, "screen", "networkType", "body");

    /* renamed from: l  reason: collision with root package name */
    private String f15490l;

    public b(String str, String str2) {
        c(str);
        this.f15490l = str2;
    }

    private String i(String str) {
        Object key;
        Object value;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            HashMap hashMap = new HashMap();
            hashMap.put("appid", "talos");
            hashMap.put("functionId", this.f15490l);
            hashMap.put("client", "android");
            hashMap.put(HybridSDK.APP_VERSION, e.f.c());
            hashMap.put(HybridSDK.APP_VERSION_CODE, String.valueOf(e.f.d()));
            hashMap.put("uuid", com.jingdong.sdk.talos.a.h().e());
            hashMap.put(HybridSDK.OS_VERSION, BaseInfo.getAndroidVersion());
            hashMap.put("screen", e.f.e());
            String networkType = BaseInfo.getNetworkType();
            if (TextUtils.isEmpty(networkType)) {
                networkType = "UNKNOWN";
            }
            hashMap.put("networkType", networkType);
            hashMap.put(Configuration.PARTNER, com.jingdong.sdk.talos.a.h().q());
            hashMap.put(HybridSDK.D_BRAND, BaseInfo.getDeviceBrand());
            hashMap.put(HybridSDK.D_MODEL, BaseInfo.getDeviceModel());
            hashMap.put(l4.f16791e, "E1.0");
            hashMap.put("body", str);
            hashMap.put("t", String.valueOf(currentTimeMillis));
            StringBuilder sb = new StringBuilder();
            sb.append(a());
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(this.f15490l);
            sb.append(" --> ");
            HashMap hashMap2 = new HashMap();
            for (Map.Entry entry : hashMap.entrySet()) {
                if (f15489m.contains(entry.getKey())) {
                    key = entry.getKey();
                    value = e.b.a(e.b.b(((String) entry.getValue()).getBytes()));
                } else {
                    key = entry.getKey();
                    value = entry.getValue();
                }
                hashMap2.put(key, value);
                sb.append((String) entry.getKey());
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append((String) entry.getValue());
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            String a = e.c.a(hashMap2, com.jingdong.sdk.talos.inner.b.c());
            sb.append(IStatInfoConfig.REPORT_PARAM_SIGN);
            sb.append(a);
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry entry2 : hashMap2.entrySet()) {
                sb2.append(URLEncoder.encode((String) entry2.getKey(), "UTF-8"));
                sb2.append(ContainerUtils.KEY_VALUE_DELIMITER);
                if (!TextUtils.isEmpty((CharSequence) entry2.getValue())) {
                    sb2.append(URLEncoder.encode((String) entry2.getValue(), "UTF-8"));
                }
                sb2.append(ContainerUtils.FIELD_DELIMITER);
            }
            sb2.append(IStatInfoConfig.REPORT_PARAM_SIGN.concat(String.valueOf(a)));
            if (com.jingdong.sdk.talos.a.h().v()) {
                e.d.b("LogX.HttpRequest", sb.toString());
                e.d.b("LogX.HttpRequest", this.f15490l + " --> " + sb2.toString());
            }
            return sb2.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // com.jingdong.sdk.talos.inner.a.a
    public final void b(OutputStream outputStream) {
        byte[] bytes = this.f15486i.getBytes();
        if (bytes.length != 0) {
            outputStream.write(bytes);
        }
        outputStream.flush();
        outputStream.close();
    }

    public final void g(HashMap<String, String> hashMap) {
        try {
            this.f15486i = i(new JSONObject(hashMap).toString());
        } catch (Throwable unused) {
        }
    }

    public final void h(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f15490l = str;
        }
    }
}
