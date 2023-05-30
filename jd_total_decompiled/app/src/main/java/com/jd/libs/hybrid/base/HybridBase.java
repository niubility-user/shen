package com.jd.libs.hybrid.base;

import android.app.Activity;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.engine.BaseInfoEngine;
import com.jd.libs.hybrid.base.engine.ConfigEngine;
import com.jd.libs.hybrid.base.engine.CookieEngine;
import com.jd.libs.hybrid.base.engine.DownloadEngine;
import com.jd.libs.hybrid.base.engine.HybridClient;
import com.jd.libs.hybrid.base.engine.RequestPreloadEngine;
import com.jd.libs.hybrid.base.engine.SettingsEngine;
import com.jd.libs.xwin.http.StreamRequest;
import com.jd.libs.xwin.http.a;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

@Keep
/* loaded from: classes16.dex */
public class HybridBase implements ConfigEngine, SettingsEngine, CookieEngine, BaseInfoEngine, DownloadEngine, RequestPreloadEngine {
    public static final String SWITCH_DOWNLOAD_ADAPTER = "switch_download_adapter";
    private static HybridBase instance;
    private HybridClient hybridClient;

    public static HybridBase getInstance() {
        if (instance == null) {
            synchronized (HybridBase.class) {
                if (instance == null) {
                    instance = new HybridBase();
                }
            }
        }
        return instance;
    }

    @Override // com.jd.libs.hybrid.base.engine.RequestPreloadEngine
    public String custom(String str, JSONObject jSONObject) {
        HybridClient hybridClient = this.hybridClient;
        return hybridClient != null ? hybridClient.getRequestPreloadEngine().custom(str, jSONObject) : "";
    }

    @Override // com.jd.libs.hybrid.base.engine.DownloadEngine
    public void downloadFile(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, String str3, String str4, a.InterfaceC0173a interfaceC0173a) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            hybridClient.getDownloadEngine().downloadFile(str, str2, hashMap, z, z2, str3, str4, interfaceC0173a);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.DownloadEngine
    public void downloadStream(String str, String str2, HashMap<String, String> hashMap, boolean z, boolean z2, StreamRequest.StreamListener streamListener) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            hybridClient.getDownloadEngine().downloadStream(str, str2, hashMap, z, z2, streamListener);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getAllConfig(ConfigEngine.Callback<String> callback) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            hybridClient.getConfigEngine().getAllConfig(callback);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getArea() {
        HybridClient hybridClient = this.hybridClient;
        return hybridClient != null ? hybridClient.getBaseInfoEngine().getArea() : "";
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getConfigById(String str, ConfigEngine.Callback<String> callback) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            hybridClient.getConfigEngine().getConfigById(str, callback);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.CookieEngine
    public String getCookieString(String str) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            return hybridClient.getCookieEngine().getCookieString(str);
        }
        return null;
    }

    @Override // com.jd.libs.hybrid.base.engine.ConfigEngine
    public void getDebugConfig(String str, ConfigEngine.Callback<String> callback) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            hybridClient.getConfigEngine().getDebugConfig(str, callback);
        }
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getLat() {
        HybridClient hybridClient = this.hybridClient;
        return hybridClient != null ? hybridClient.getBaseInfoEngine().getLat() : "0.0";
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getLng() {
        HybridClient hybridClient = this.hybridClient;
        return hybridClient != null ? hybridClient.getBaseInfoEngine().getLng() : "0.0";
    }

    @Override // com.jd.libs.hybrid.base.engine.SettingsEngine
    public String getSetting(String str) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            return hybridClient.getSettingsEngine().getSetting(str);
        }
        return null;
    }

    @Override // com.jd.libs.hybrid.base.engine.BaseInfoEngine
    public String getUrl(Activity activity) {
        HybridClient hybridClient = this.hybridClient;
        return hybridClient != null ? hybridClient.getBaseInfoEngine().getUrl(activity) : "";
    }

    @Override // com.jd.libs.hybrid.base.engine.CookieEngine
    public boolean saveCookieString(String str, List<String> list) {
        HybridClient hybridClient = this.hybridClient;
        if (hybridClient != null) {
            return hybridClient.getCookieEngine().saveCookieString(str, list);
        }
        return false;
    }

    public void setLoaderClient(HybridClient hybridClient) {
        this.hybridClient = hybridClient;
    }
}
