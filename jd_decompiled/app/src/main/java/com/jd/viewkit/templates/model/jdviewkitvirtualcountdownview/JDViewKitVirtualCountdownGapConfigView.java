package com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview;

import com.facebook.react.uimanager.ViewProps;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.JSONTool;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualCountdownGapConfigView {
    private int colorInt;
    private int fontSize;
    private int gap;
    private int type;
    private int unit;

    public JDViewKitVirtualCountdownGapConfigView(JSONObject jSONObject) {
        setType(JSONTool.getInt(jSONObject, "type"));
        setFontSize(JSONTool.getInt(jSONObject, ViewProps.FONT_SIZE));
        setGap(JSONTool.getInt(jSONObject, "gap"));
        setColorInt(ColorTool.parseColor(JSONTool.getString(jSONObject, "color"), 0));
        setUnit(JSONTool.getInt(jSONObject, "unit"));
    }

    public int getColorInt() {
        return this.colorInt;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getGap() {
        return this.gap;
    }

    public int getType() {
        return this.type;
    }

    public int getUnit() {
        return this.unit;
    }

    public void setColorInt(int i2) {
        this.colorInt = i2;
    }

    public void setFontSize(int i2) {
        this.fontSize = i2;
    }

    public void setGap(int i2) {
        this.gap = i2;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public void setUnit(int i2) {
        this.unit = i2;
    }
}
