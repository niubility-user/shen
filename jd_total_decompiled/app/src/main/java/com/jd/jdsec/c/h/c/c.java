package com.jd.jdsec.c.h.c;

import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class c implements g {
    static final h a = new d();
    static final i b = new e();

    /* renamed from: c  reason: collision with root package name */
    private static boolean f2760c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public class a extends TimerTask {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JSONArray f2761g;

        a(c cVar, JSONArray jSONArray) {
            this.f2761g = jSONArray;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            for (int i2 = 0; i2 < this.f2761g.length(); i2++) {
                try {
                    JSONObject d = com.jd.jdsec.a.j.b.d(this.f2761g, i2);
                    if (d != null) {
                        c.b.a(d);
                    }
                } catch (Exception e2) {
                    com.jd.jdsec.a.l.b.b("JDSec.Security.CloudControlSender", e2.getMessage());
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    class b extends TimerTask {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JSONArray f2762g;

        b(JSONArray jSONArray) {
            this.f2762g = jSONArray;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.this.c(this.f2762g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(Exception exc) {
        f2760c = false;
        com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u4e0b\u8f7d\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6\u65f6 download fail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(JSONArray jSONArray) {
        f2760c = false;
        com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u4e0b\u8f7d\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6\u65f6 download success");
        if (AuraBundleConfig.getInstance().isBundlePrepared("com.jd.lib.device_model_compute")) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u4e0b\u8f7d\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6\u65f6 download ready");
            b(jSONArray);
        }
    }

    public void b(JSONArray jSONArray) {
        com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "edgeComputedEnter invoked");
        new Timer().schedule(new a(this, jSONArray), Final.FIVE_SECOND);
    }

    public void c(final JSONArray jSONArray) {
        if (AuraBundleConfig.getInstance().isBundlePrepared("com.jd.lib.device_model_compute")) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u5f53\u60f3\u4e0b\u8f7d\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6\u65f6\uff0c\u63d2\u4ef6\u5df2\u7ecf\u51c6\u5907\u597d\uff0c\u653e\u5f03\u5b89\u88c5! \u76f4\u63a5\u8fdb\u5165\u7aef\u8ba1\u7b97\u6d41\u7a0b");
            b(jSONArray);
        } else if (f2760c) {
            com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u5f53\u60f3\u4e0b\u8f7d\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6\u65f6\uff0c\u5df2\u7ecf\u6709\u5b89\u88c5\u8fdb\u7a0b\u5728\u6267\u884c\u4e2d!");
        } else {
            f2760c = true;
            com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6 \u5f00\u59cb \u521d\u59cb\u5316\u4e0b\u8f7d\u6a21\u5757");
            try {
                ((IAuraInstallManager) AuraServiceLoader.get(com.jd.jdsec.c.g.a, IAuraInstallManager.class)).startInstall(com.jd.jdsec.c.g.a, new AuraInstallRequest.Builder().setBundleName("com.jd.lib.device_model_compute").setDownloadType(1).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jd.jdsec.c.h.c.b
                    @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
                    public final void onSuccess() {
                        c.this.d(jSONArray);
                    }
                }).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jd.jdsec.c.h.c.a
                    @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
                    public final void onFailure(Exception exc) {
                        c.a(exc);
                    }
                }).build());
                com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6 \u5f00\u59cb \u4e0b\u8f7d");
            } catch (Exception e2) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.CloudControlSender", "\u7aef\u8ba1\u7b97\u540e\u88c5\u63d2\u4ef6 \u4e0b\u8f7d\u7a0b\u5e8f\u7684\u521d\u59cb\u5316\u8fc7\u7a0b\u51fa\u9519" + e2.getMessage());
                f2760c = false;
            }
        }
    }

    @Override // com.jd.jdsec.c.h.c.g
    public void a(JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> configs = JDMobileConfig.getInstance().getConfigs("JDFzbSDK", "fzbConfig");
        com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "JDMobileConfig antiCheatConfig,\u5224\u65ad\u662f\u5426\u80fd\u62ff\u5230\u6574\u4f53\u914d\u7f6e" + configs);
        try {
            JSONArray jSONArray = new JSONArray(configs.get("banList"));
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String optString = jSONObject2.optString(Constants.PARAM_PLATFORM, "");
                if ("".equals(optString) || "android".equalsIgnoreCase(optString)) {
                    hashMap.put(jSONObject2.optString("key"), jSONObject2.optString("appVersion", ""));
                }
            }
            com.jd.jdsec.c.h.b.a.e(new JSONObject(configs.get("highFreqConfig")));
        } catch (Exception e2) {
            com.jd.jdsec.a.l.b.b("JDSec.Security.CloudControlSender", e2.getMessage());
        } finally {
            com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "DUCC banList Config: " + hashMap);
            a.a(jSONObject, hashMap);
        }
        if (configs != null) {
            try {
                JSONArray jSONArray2 = new JSONArray(configs.get("modelControl"));
                JSONObject jSONObject3 = new JSONObject(configs.get("postLoadPlugin"));
                if (AuraBundleConfig.getInstance().isBundlePrepared("com.jd.lib.device_model_compute")) {
                    com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u7aef\u8ba1\u7b97\u63d2\u4ef6\u521d\u59cb\u65f6\u5df2\u51c6\u5907\u597d");
                    b(jSONArray2);
                } else {
                    com.jd.jdsec.a.l.b.e("JDSec.Security.CloudControlSender", "\u7aef\u8ba1\u7b97\u63d2\u4ef6\u521d\u59cb\u65f6\u672a\u51c6\u5907\u597d");
                    if (com.jd.jdsec.a.j.b.f(BaseInfo.getDeviceModel(), jSONObject3)) {
                        new Timer().schedule(new b(jSONArray2), 10000L);
                    }
                }
            } catch (Exception e3) {
                com.jd.jdsec.a.l.b.b("JDSec.Security.CloudControlSender", e3.getMessage());
            }
        }
    }
}
