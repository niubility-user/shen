package com.jd.viewkit.templates.model.JDViewKitVirtualBannerView;

import com.jd.dynamic.DYConstants;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualBannerDotConfig {
    public static String activeColorKey = "activeColor";
    public static String alignKey = "align";
    public static String dotOriginKey = "dotOrigin";
    public static String normalColorKey = "normalColor";
    public static String showDotsKey = "showDots";
    private String activeColor;
    private String align;
    private int dotOriginX;
    private int dotOriginY;
    private String normalColor;
    private boolean showDot;

    public JDViewKitVirtualBannerDotConfig(JSONObject jSONObject) {
        String string = JSONTool.getString(jSONObject, dotOriginKey);
        if (!StringTool.isEmpty(string)) {
            String[] split = string.split(DYConstants.DY_REGEX_COMMA);
            if (split.length == 2) {
                setDotOriginX(NumberTool.valueOfInt(split[0]));
                setDotOriginY(NumberTool.valueOfInt(split[1]));
            }
        }
        setShowDot(JSONTool.getBoolean(jSONObject, showDotsKey));
        setNormalColor(JSONTool.getString(jSONObject, normalColorKey));
        setActiveColor(JSONTool.getString(jSONObject, activeColorKey));
        setAlign(JSONTool.getString(jSONObject, alignKey));
    }

    public String getActiveColor() {
        return this.activeColor;
    }

    public String getAlign() {
        return this.align;
    }

    public int getDotOriginX() {
        return this.dotOriginX;
    }

    public int getDotOriginY() {
        return this.dotOriginY;
    }

    public String getNormalColor() {
        return this.normalColor;
    }

    public boolean isShowDot() {
        return this.showDot;
    }

    public void setActiveColor(String str) {
        this.activeColor = str;
    }

    public void setAlign(String str) {
        this.align = str;
    }

    public void setDotOriginX(int i2) {
        this.dotOriginX = i2;
    }

    public void setDotOriginY(int i2) {
        this.dotOriginY = i2;
    }

    public void setNormalColor(String str) {
        this.normalColor = str;
    }

    public void setShowDot(boolean z) {
        this.showDot = z;
    }
}
