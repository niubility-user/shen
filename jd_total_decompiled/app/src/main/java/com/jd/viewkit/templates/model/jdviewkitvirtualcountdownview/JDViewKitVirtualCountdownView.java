package com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualCountdownView extends JDViewKitVirtualView {
    public static String cardConfigViewKey = "cardConfig";
    private static String countTypeKey = "countType";
    public static String gapConfigViewKey = "gapConfig";
    public static String isEndHideKey = "isEndHide";
    private static String triggerTypeKey = "triggerType";
    private JDViewKitVirtualCountdownCardConfigView cardConfigView;
    private int countType;
    private JDViewKitVirtualCountdownGapConfigView gapConfigView;
    private int isEndHide;
    private int triggerType;

    public JDViewKitVirtualCountdownView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.triggerType = 0;
        this.countType = 0;
        this.isEndHide = 0;
        if (JSONTool.getJSONObject(jSONObject, cardConfigViewKey) != null) {
            this.cardConfigView = new JDViewKitVirtualCountdownCardConfigView(jSONObject, jDViewKitVirtualServiceModel, map);
        }
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, gapConfigViewKey);
        if (jSONObject2 != null) {
            this.gapConfigView = new JDViewKitVirtualCountdownGapConfigView(jSONObject2);
        }
        this.triggerType = JSONTool.getInt(jSONObject, triggerTypeKey);
        this.countType = JSONTool.getInt(jSONObject, countTypeKey);
        this.isEndHide = JSONTool.getInt(jSONObject, isEndHideKey);
    }

    public JDViewKitVirtualCountdownCardConfigView getCardConfigView() {
        return this.cardConfigView;
    }

    public int getCountType() {
        return this.countType;
    }

    public JDViewKitVirtualCountdownGapConfigView getGapConfigView() {
        return this.gapConfigView;
    }

    public int getIsEndHide() {
        return this.isEndHide;
    }

    public int getTriggerType() {
        return this.triggerType;
    }
}
