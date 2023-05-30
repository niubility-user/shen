package com.jd.viewkit.templates.model.jdviewkitvirtualvidelview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualVideoView extends JDViewKitVirtualView {
    private static final String playerConfigKey = "playerConfig";
    private JDViewKitVirtualPlayerConfig playerConfig;

    public JDViewKitVirtualVideoView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, playerConfigKey);
        if (jSONObject2 != null) {
            setPlayerConfig(new JDViewKitVirtualPlayerConfig(jSONObject2));
        }
    }

    public JDViewKitVirtualPlayerConfig getPlayerConfig() {
        return this.playerConfig;
    }

    public void setPlayerConfig(JDViewKitVirtualPlayerConfig jDViewKitVirtualPlayerConfig) {
        this.playerConfig = jDViewKitVirtualPlayerConfig;
    }
}
