package com.jd.viewkit.templates.model.jdveiwkitvirtualmultistateview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualMultistateView extends JDViewKitVirtualView {
    private static final String stateKey = "state";
    private Map<String, JDViewKitVirtualView> stateMap;

    public JDViewKitVirtualMultistateView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.stateMap = new HashMap();
        if (JSONTool.getJSONObject(jSONObject, "state") != null) {
            this.stateMap.clear();
            JSONObject jSONObject2 = JSONTool.getJSONObject(jSONObject, "state");
            for (String str : JSONTool.getMap(jSONObject2).keySet()) {
                if (StringTool.isNotEmpty(str)) {
                    this.stateMap.put(str, new JDViewKitVirtualView(JSONTool.getJSONObject(jSONObject2, str), jDViewKitVirtualServiceModel, map));
                }
            }
        }
    }

    public Map<String, JDViewKitVirtualView> getStateMap() {
        return this.stateMap;
    }

    public void setStateMap(Map<String, JDViewKitVirtualView> map) {
        this.stateMap = map;
    }
}
