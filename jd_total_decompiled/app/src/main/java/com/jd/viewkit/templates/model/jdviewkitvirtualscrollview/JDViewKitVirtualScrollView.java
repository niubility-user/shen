package com.jd.viewkit.templates.model.jdviewkitvirtualscrollview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualScrollView extends JDViewKitVirtualView {
    public static String moreConfigKey = "moreConfig";
    public static String scrollConfigKey = "scrollConfig";
    private JDViewKitVirtualScrollConfig scrollConfig;
    private JDViewKitVirtualScrollMoreConfig scrollMoreConfig;

    public JDViewKitVirtualScrollView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        setScrollConfig(new JDViewKitVirtualScrollConfig(JSONTool.getJSONObject(jSONObject, scrollConfigKey)));
        setScrollMoreConfig(new JDViewKitVirtualScrollMoreConfig(JSONTool.getJSONObject(jSONObject, moreConfigKey)));
    }

    public JDViewKitVirtualScrollConfig getScrollConfig() {
        return this.scrollConfig;
    }

    public JDViewKitVirtualScrollMoreConfig getScrollMoreConfig() {
        return this.scrollMoreConfig;
    }

    public void setScrollConfig(JDViewKitVirtualScrollConfig jDViewKitVirtualScrollConfig) {
        this.scrollConfig = jDViewKitVirtualScrollConfig;
    }

    public void setScrollMoreConfig(JDViewKitVirtualScrollMoreConfig jDViewKitVirtualScrollMoreConfig) {
        this.scrollMoreConfig = jDViewKitVirtualScrollMoreConfig;
    }
}
