package com.jd.viewkit.templates.model.JDViewKitVirtualBannerView;

import com.jd.dynamic.DYConstants;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.NumberTool;
import com.jd.viewkit.tool.StringTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualBannerConfig {
    public static String centerSizeKey = "centerSize";
    public static String marginLeftKey = "marginLeft";
    public static String marginTopKey = "marginTop";
    public static String scaleKey = "scale";
    public static String spaceKey = "space";
    private int centerSizeHeigh;
    private int centerSizeWidth;
    private int marginLeft;
    private int marginTop;
    private float scale;
    private int space;

    public JDViewKitVirtualBannerConfig(JSONObject jSONObject) {
        String string = JSONTool.getString(jSONObject, centerSizeKey);
        if (!StringTool.isEmpty(string)) {
            String[] split = string.split(DYConstants.DY_REGEX_COMMA);
            if (split.length == 2) {
                setCenterSizeWidth(NumberTool.valueOfInt(split[0]));
                setCenterSizeHeigh(NumberTool.valueOfInt(split[1]));
            }
        }
        setScale((float) JSONTool.getDouble(jSONObject, scaleKey));
        setMarginTop(JSONTool.getInt(jSONObject, marginTopKey));
        setMarginLeft(JSONTool.getInt(jSONObject, marginLeftKey));
        setSpace(JSONTool.getInt(jSONObject, spaceKey));
    }

    public int getCenterSizeHeigh() {
        return this.centerSizeHeigh;
    }

    public int getCenterSizeWidth() {
        return this.centerSizeWidth;
    }

    public int getMarginLeft() {
        return this.marginLeft;
    }

    public int getMarginTop() {
        return this.marginTop;
    }

    public float getScale() {
        return this.scale;
    }

    public int getSpace() {
        return this.space;
    }

    public void setCenterSizeHeigh(int i2) {
        this.centerSizeHeigh = i2;
    }

    public void setCenterSizeWidth(int i2) {
        this.centerSizeWidth = i2;
    }

    public void setMarginLeft(int i2) {
        this.marginLeft = i2;
    }

    public void setMarginTop(int i2) {
        this.marginTop = i2;
    }

    public void setScale(float f2) {
        this.scale = f2;
    }

    public void setSpace(int i2) {
        this.space = i2;
    }
}
