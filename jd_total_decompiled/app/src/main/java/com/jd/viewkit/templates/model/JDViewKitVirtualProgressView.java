package com.jd.viewkit.templates.model;

import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.ColorTool;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualProgressView extends JDViewKitVirtualView {
    public static String countDownTypeKey = "countDownType";
    public static String interactTypeKey = "interactType";
    public static String progressColorKey = "progressColor";
    private String countDownType;
    private String interactType;
    private int progressColorInt;
    private String progressColorStr;

    public JDViewKitVirtualProgressView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        setProgressColorStr(JSONTool.getString(JSONTool.getJSONObject(jSONObject, JDViewKitVirtualView.styleKey), progressColorKey));
        this.interactType = JSONTool.getString(jSONObject, interactTypeKey);
        this.countDownType = JSONTool.getString(jSONObject, countDownTypeKey);
        if (StringTool.isNotEmpty(getProgressColorStr())) {
            setProgressColorInt(ColorTool.parseColor(getProgressColorStr(), ColorTool.parseColor("#00000000", -1)));
        }
    }

    public int getProgressColorInt() {
        return this.progressColorInt;
    }

    public String getProgressColorStr() {
        return this.progressColorStr;
    }

    public boolean isCountDownProgress() {
        return "1".equals(this.interactType);
    }

    public boolean isLeftStart() {
        return "1".equals(this.countDownType);
    }

    public void setProgressColorInt(int i2) {
        this.progressColorInt = i2;
    }

    public void setProgressColorStr(String str) {
        this.progressColorStr = str;
    }
}
