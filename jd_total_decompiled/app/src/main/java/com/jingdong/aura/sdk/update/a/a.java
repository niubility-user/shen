package com.jingdong.aura.sdk.update.a;

import android.os.Build;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.base.DynamicPrepareFetcher;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.b.c;
import com.jingdong.aura.sdk.update.b.d;
import com.jingdong.aura.sdk.update.b.e;
import com.jingdong.aura.sdk.update.b.g;
import com.jingdong.aura.sdk.update.b.h;
import com.jingdong.aura.sdk.update.b.n;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.jdsdk.config.Configuration;
import com.tencent.mapsdk.internal.l4;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class a {
    static volatile long a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12245c;
    private ExecutorService d;

    public a() {
        boolean z = com.jingdong.aura.sdk.update.a.a().a.useBetaHost;
        this.f12245c = "https://api.m.jd.com/api";
        this.b = e.a(d.a);
        if (z) {
            this.f12245c = "https://beta-api.m.jd.com/api";
        }
        String str = com.jingdong.aura.sdk.update.a.a().a.country;
        if (TextUtils.isEmpty(str) || !str.equals("Thailand")) {
            return;
        }
        this.b = e.a(d.b);
        this.f12245c = z ? "https://beta-api.jd.co.th/api" : "https://api.jd.co.th/api";
    }

    private String a(String str, String str2) {
        StringBuilder sb = new StringBuilder(this.f12245c);
        sb.append("?");
        sb.append("functionId=avatarHotfixPackages");
        Map<String, String> c2 = c(str2);
        for (Map.Entry<String, String> entry : c2.entrySet()) {
            try {
                String encode = URLEncoder.encode(entry.getKey(), "utf-8");
                String encode2 = URLEncoder.encode(entry.getValue(), "utf-8");
                sb.append(ContainerUtils.FIELD_DELIMITER);
                sb.append(encode);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(encode2);
            } catch (Exception unused) {
            }
        }
        sb.append("&sign=");
        sb.append(n.a(c2, str, this.b));
        return sb.toString();
    }

    private synchronized ExecutorService a() {
        if (this.d == null) {
            this.d = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.jingdong.aura.sdk.update.a.a.1
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    return new Thread(runnable, "AuraUpdateRequest");
                }
            });
        }
        return this.d;
    }

    private static String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PairKey.APP_KEY, com.jingdong.aura.sdk.update.a.a().a.appKey);
            jSONObject.put("userId", com.jingdong.aura.sdk.update.a.a().a.userIdProvider != null ? com.jingdong.aura.sdk.update.a.a().a.userIdProvider.getUserId() : com.jingdong.aura.sdk.update.a.a().a.userId);
            jSONObject.put("sign", n.a());
            jSONObject.put("apiLevel", Build.VERSION.SDK_INT);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", "auraPlugin");
            c.b("dataVersion=" + c());
            jSONObject2.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, c());
            jSONArray.put(jSONObject2);
            jSONObject.put("types", jSONArray);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        boolean z = com.jingdong.aura.sdk.update.a.a().a.useEncrption;
        String jSONObject3 = jSONObject.toString();
        c.a("ori_body:".concat(String.valueOf(jSONObject3)));
        if (z) {
            try {
                return h.a(h.b(jSONObject3.getBytes()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject3;
    }

    static List<AuraBundleResult> b(String str) {
        JSONObject jSONObject;
        int optInt;
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(str);
            optInt = jSONObject.optInt("code", -1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (optInt != 0) {
            c.b("AuraUpdateRequest", "response json code error : ".concat(String.valueOf(optInt)));
            return arrayList;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        JSONArray optJSONArray = optJSONObject.optJSONArray("packages");
        int length = optJSONArray.length();
        c.a("AuraUpdateRequest", "packages size=".concat(String.valueOf(length)));
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
            if (TextUtils.equals("auraPlugin", jSONObject2.optString("type"))) {
                a = jSONObject2.optLong(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
                if (c() == a) {
                    c.b("AuraUpdateRequest", "dataVersion not change, savedDataverson:" + c() + ", response dataVersion:" + optJSONObject);
                    return arrayList;
                }
                JSONArray optJSONArray2 = jSONObject2.optJSONArray(DynamicPrepareFetcher.KEY_PREPARE_MODULES);
                if (optJSONArray2 != null && optJSONArray2.length() != 0) {
                    for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                        AuraBundleResult auraBundleResult = new AuraBundleResult();
                        JSONObject jSONObject3 = optJSONArray2.getJSONObject(i3);
                        JSONObject optJSONObject2 = jSONObject3.optJSONObject("extJSON");
                        if (jSONObject3 != null && optJSONObject2 != null) {
                            String optString = jSONObject3.optString("md5");
                            auraBundleResult.md5 = optString;
                            auraBundleResult.bundleVersionCode = jSONObject3.optInt("version");
                            auraBundleResult.size = jSONObject3.optLong(ApkDownloadTable.FIELD_SIZE);
                            auraBundleResult.downloadUrl = jSONObject3.optString("downloadUrl").trim();
                            auraBundleResult.updateId = optJSONObject2.optString("apkName");
                            auraBundleResult.downloadedFileName = optString;
                            auraBundleResult.downloadType = optJSONObject2.optInt("downloadType", 1);
                            arrayList.add(auraBundleResult);
                        }
                        c.b("resolveApkList:modules`s content is null");
                    }
                }
                c.b("AuraUpdateRequest", "resolveApkList modules is null");
                return arrayList;
            }
        }
        return arrayList;
    }

    private static long c() {
        return com.jingdong.aura.sdk.update.a.a().f12238h.getLong("dataVersion_auraupdate", 0L);
    }

    private static Map<String, String> c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("appid", "aura");
        hashMap.put("t", str);
        hashMap.put("client", "android");
        boolean z = com.jingdong.aura.sdk.update.a.a().a.useEncrption;
        String str2 = com.jingdong.aura.sdk.update.a.a().a.appVersionName;
        String valueOf = String.valueOf(com.jingdong.aura.sdk.update.a.a().a.appVersionCode);
        String str3 = com.jingdong.aura.sdk.update.a.a().a.uuid;
        String str4 = com.jingdong.aura.sdk.update.a.a().a.channel;
        String osVersion = com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider.getOsVersion();
        String deviceBrand = com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider.getDeviceBrand();
        String deviceModel = com.jingdong.aura.sdk.update.a.a().a.privacyFieldProvider.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "default";
        }
        if (z) {
            hashMap.put(l4.f16791e, "E1.1");
            if (valueOf == null) {
                valueOf = "";
            }
            try {
                c.a("ori_build:".concat(String.valueOf(valueOf)));
                valueOf = h.a(h.b(valueOf.getBytes()));
                if (str3 == null) {
                    str3 = "";
                }
                c.a("ori_uuid:".concat(String.valueOf(str3)));
                str3 = h.a(h.b(str3.getBytes()));
                if (str4 == null) {
                    str4 = "";
                }
                c.a("ori_partner:".concat(String.valueOf(str4)));
                str4 = h.a(h.b(str4.getBytes()));
                if (osVersion == null) {
                    osVersion = "";
                }
                c.a("ori_osVersion:".concat(String.valueOf(osVersion)));
                osVersion = h.a(h.b(osVersion.getBytes()));
                if (deviceBrand == null) {
                    deviceBrand = "";
                }
                c.a("ori_d_brand:".concat(String.valueOf(deviceBrand)));
                deviceBrand = h.a(h.b(deviceBrand.getBytes()));
                if (deviceModel == null) {
                    deviceModel = "";
                }
                c.a("ori_d_modle:".concat(String.valueOf(deviceModel)));
                deviceModel = h.a(h.b(deviceModel.getBytes()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        hashMap.put(HybridSDK.APP_VERSION, str2);
        hashMap.put(HybridSDK.APP_VERSION_CODE, valueOf);
        hashMap.put("uuid", str3);
        hashMap.put(Configuration.PARTNER, str4);
        hashMap.put(HybridSDK.OS_VERSION, osVersion);
        hashMap.put(HybridSDK.D_BRAND, deviceBrand);
        hashMap.put(HybridSDK.D_MODEL, deviceModel);
        return hashMap;
    }

    final HttpURLConnection a(String str) {
        String b = b();
        String a2 = a(b, str);
        c.a("requeset apklist:".concat(String.valueOf(a2)));
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a2).openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
        httpURLConnection.connect();
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        StringBuilder sb = new StringBuilder();
        sb.append(a2.replace(this.f12245c + "?", ""));
        sb.append("&body=");
        sb.append(URLEncoder.encode(b, "utf-8"));
        String sb2 = sb.toString();
        c.a("content:".concat(String.valueOf(sb2)));
        dataOutputStream.writeBytes(sb2);
        dataOutputStream.flush();
        dataOutputStream.close();
        return httpURLConnection;
    }

    public final void a(final b bVar) {
        try {
            a().execute(new Runnable() { // from class: com.jingdong.aura.sdk.update.a.a.2
                @Override // java.lang.Runnable
                public final void run() {
                    List<String> list;
                    a aVar = a.this;
                    b bVar2 = bVar;
                    try {
                        HttpURLConnection a2 = aVar.a(String.valueOf(System.currentTimeMillis()));
                        Map<String, List<String>> headerFields = a2.getHeaderFields();
                        if (headerFields != null && headerFields.containsKey("X-API-Sign-Message") && (list = headerFields.get("X-API-Sign-Message")) != null && list.contains("stale")) {
                            c.b("X-API-Sign-Message\uff1astale, retry...");
                            String str = headerFields.get("X-API-Sign-Millis").get(0);
                            if (!TextUtils.isEmpty(str)) {
                                a2.disconnect();
                                c.b("X-API-Sign-Message\uff1astale, reconnection...");
                                a2 = aVar.a(str);
                            }
                        }
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(a2.getInputStream()));
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuffer.append(readLine);
                        }
                        bufferedReader.close();
                        a2.disconnect();
                        c.a("requeset apklist success");
                        String stringBuffer2 = stringBuffer.toString();
                        if (TextUtils.isEmpty(stringBuffer2)) {
                            return;
                        }
                        c.a("apklist response:".concat(String.valueOf(stringBuffer2)));
                        List<AuraBundleResult> b = a.b(stringBuffer2);
                        if (b.size() > 0 && g.a(b)) {
                            com.jingdong.aura.sdk.update.a.a().f12238h.edit().putLong("dataVersion_auraupdate", a.a).apply();
                            if (bVar2 != null) {
                                bVar2.a(b);
                            }
                        }
                    } catch (Exception e2) {
                        c.b("requeset apklist error!");
                        e2.printStackTrace();
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
            new RuntimeException(th);
        }
    }
}
