package com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview;

import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualRichTextTagView extends JDViewKitVirtualTextView {
    public static String adaptiveWidthKey = "adaptiveWidth";
    public static String maxWidthKey = "maxWidth";
    public static String viewTypeTag = "tag";
    private int adaptiveWidth;
    private int maxWidth;

    public JDViewKitVirtualRichTextTagView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, JDViewKitVirtualView.styleKey);
        this.adaptiveWidth = JSONTool.getInt(jSONObject2, adaptiveWidthKey);
        this.maxWidth = JSONTool.getInt(jSONObject2, maxWidthKey);
    }

    public int getAdaptiveWidth() {
        return this.adaptiveWidth;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setAdaptiveWidth(int i2) {
        this.adaptiveWidth = i2;
    }

    public void setMaxWidth(int i2) {
        this.maxWidth = i2;
    }
}
