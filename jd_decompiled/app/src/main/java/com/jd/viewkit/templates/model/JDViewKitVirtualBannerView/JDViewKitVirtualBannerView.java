package com.jd.viewkit.templates.model.JDViewKitVirtualBannerView;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualBannerView extends JDViewKitVirtualView {
    private static final String autoPlayKey = "autoPlay";
    private static final String bannerConfigKey = "bannerConfig";
    private static final String dotTypeKey = "dotType";
    private static final String dotsConfigKey = "dotsConfig";
    private static final String dslIdKey = "dslId";
    private static final String intervalKey = "interval";
    private static final String isCricleKey = "isCircle";
    private static final String subTypeKey = "subType";
    private boolean autoPlay;
    private JDViewKitVirtualBannerConfig bannerConfig;
    private JDViewKitVirtualBannerDotConfig bannerDotConfig;
    private int dotType;
    private int interval;
    private boolean isCircle;
    private String subType;

    public JDViewKitVirtualBannerView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        if (map.containsKey(jSONObject.optString(dslIdKey))) {
            JDViewKitVirtualBannerView jDViewKitVirtualBannerView = (JDViewKitVirtualBannerView) map.get(jSONObject.optString(dslIdKey));
            setCircle(jDViewKitVirtualBannerView.isCircle());
            setAutoPlay(jDViewKitVirtualBannerView.isAutoPlay());
            setInterval(jDViewKitVirtualBannerView.getInterval());
            setSubType(jDViewKitVirtualBannerView.getSubType());
            setDotType(jDViewKitVirtualBannerView.getDotType());
            setBannerConfig(jDViewKitVirtualBannerView.getBannerConfig());
            setBannerDotConfig(jDViewKitVirtualBannerView.getBannerDotConfig());
            return;
        }
        setCircle(JSONTool.getBoolean(jSONObject, isCricleKey));
        setAutoPlay(JSONTool.getBoolean(jSONObject, "autoPlay"));
        setInterval(JSONTool.getInt(jSONObject, "interval"));
        setDotType(JSONTool.getInt(jSONObject, dotTypeKey));
        setSubType(JSONTool.getString(jSONObject, subTypeKey));
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, bannerConfigKey);
        if (jSONObject2 != null) {
            setBannerConfig(new JDViewKitVirtualBannerConfig(jSONObject2));
        }
        JSONObject jSONObject3 = JSONTool.getJSONObject(jSONObject, dotsConfigKey);
        if (jSONObject3 != null) {
            setBannerDotConfig(new JDViewKitVirtualBannerDotConfig(jSONObject3));
        }
    }

    public JDViewKitVirtualBannerConfig getBannerConfig() {
        return this.bannerConfig;
    }

    public JDViewKitVirtualBannerDotConfig getBannerDotConfig() {
        return this.bannerDotConfig;
    }

    public int getDotType() {
        return this.dotType;
    }

    public int getInterval() {
        return this.interval;
    }

    @Override // com.jd.viewkit.templates.model.JDViewKitVirtualView
    public String getSubType() {
        return this.subType;
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

    public void setBannerConfig(JDViewKitVirtualBannerConfig jDViewKitVirtualBannerConfig) {
        this.bannerConfig = jDViewKitVirtualBannerConfig;
    }

    public void setBannerDotConfig(JDViewKitVirtualBannerDotConfig jDViewKitVirtualBannerDotConfig) {
        this.bannerDotConfig = jDViewKitVirtualBannerDotConfig;
    }

    public void setCircle(boolean z) {
        this.isCircle = z;
    }

    public void setDotType(int i2) {
        this.dotType = i2;
    }

    public void setInterval(int i2) {
        this.interval = i2;
    }

    @Override // com.jd.viewkit.templates.model.JDViewKitVirtualView
    public void setSubType(String str) {
        this.subType = str;
    }
}
