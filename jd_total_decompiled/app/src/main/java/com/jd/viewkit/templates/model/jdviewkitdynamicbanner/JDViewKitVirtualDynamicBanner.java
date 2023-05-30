package com.jd.viewkit.templates.model.jdviewkitdynamicbanner;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualDynamicBanner extends JDViewKitVirtualView {
    private static final String autoPlayKey = "autoPlay";
    private static final String bannerConfigKey = "bannerConfig";
    private static final String intervalKey = "interval";
    private static final String isCircleKey = "isCircle";
    private boolean autoPlay;
    private JDViewKitVirtualDynamicBannerConfig bannerConfig;
    private int interval;
    private boolean isCircle;

    public JDViewKitVirtualDynamicBanner(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        setCircle(JSONTool.getBoolean(jSONObject, isCircleKey));
        setAutoPlay(JSONTool.getBoolean(jSONObject, "autoPlay"));
        setInterval(JSONTool.getInt(jSONObject, "interval"));
        setBannerConfig(new JDViewKitVirtualDynamicBannerConfig(JSONTool.getJSONObject(jSONObject, bannerConfigKey)));
    }

    public JDViewKitVirtualDynamicBannerConfig getBannerConfig() {
        return this.bannerConfig;
    }

    public int getInterval() {
        return this.interval;
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public boolean isCircle() {
        return this.isCircle;
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = z;
    }

    public void setBannerConfig(JDViewKitVirtualDynamicBannerConfig jDViewKitVirtualDynamicBannerConfig) {
        this.bannerConfig = jDViewKitVirtualDynamicBannerConfig;
    }

    public void setCircle(boolean z) {
        this.isCircle = z;
    }

    public void setInterval(int i2) {
        this.interval = i2;
    }
}
