package com.jd.viewkit.templates.model.jdviewkitvirtualstatefulview;

import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualStatefulView extends JDViewKitVirtualView {
    private static final String stateKey = "state";
    private static final String subTypeKey = "subType";
    private Map<String, JDViewKitVirtualView> stateMap;
    private String subType;

    public JDViewKitVirtualStatefulView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
        this.stateMap = new HashMap();
        setSubType(JSONTool.getString(jSONObject, subTypeKey));
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

    @Override // com.jd.viewkit.templates.model.JDViewKitVirtualView
    public String getSubType() {
        return this.subType;
    }

    public void setStateMap(Map<String, JDViewKitVirtualView> map) {
        this.stateMap = map;
    }

    @Override // com.jd.viewkit.templates.model.JDViewKitVirtualView
    public void setSubType(String str) {
        this.subType = str;
    }
}
