package com.jd.viewkit.templates.model.jdviewkitvirtualvidelview;

import com.jd.viewkit.tool.JSONTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualPlayerConfig {
    public static String autoPlayKey = "autoPlay";
    public static String defaultVoiceTypeKey = "defaultVoiceType";
    public static String displayTypeKey = "displayType";
    public static String isCircularPlayKey = "isCircularPlay";
    public static String showControlKey = "showControl";
    public static String showDurationKey = "showDuration";
    public String autoPlay;
    public String defaultVoiceType;
    public String displayType;
    public String isCircularPlay;
    public String showControl = "1";
    public String showDuration;

    public JDViewKitVirtualPlayerConfig(JSONObject jSONObject) {
        setDefaultVoiceType(JSONTool.getString(jSONObject, defaultVoiceTypeKey));
        setDisplayType(JSONTool.getString(jSONObject, displayTypeKey));
        setIsCircularPlay(JSONTool.getString(jSONObject, isCircularPlayKey));
        setShowDuration(JSONTool.getString(jSONObject, showDurationKey));
        setAutoPlay(JSONTool.getString(jSONObject, autoPlayKey));
        setShowControl(JSONTool.getString(jSONObject, showControlKey));
    }

    public String getAutoPlay() {
        return this.autoPlay;
    }

    public String getDefaultVoiceType() {
        return this.defaultVoiceType;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public String getIsCircularPlay() {
        return this.isCircularPlay;
    }

    public String getShowControl() {
        return this.showControl;
    }

    public String getShowDuration() {
        return this.showDuration;
    }

    public boolean isShowControl() {
        return "1".equals(this.showControl);
    }

    public void setAutoPlay(String str) {
        this.autoPlay = str;
    }

    public void setDefaultVoiceType(String str) {
        this.defaultVoiceType = str;
    }

    public void setDisplayType(String str) {
        this.displayType = str;
    }

    public void setIsCircularPlay(String str) {
        this.isCircularPlay = str;
    }

    public void setShowControl(String str) {
        this.showControl = str;
    }

    public void setShowDuration(String str) {
        this.showDuration = str;
    }
}
