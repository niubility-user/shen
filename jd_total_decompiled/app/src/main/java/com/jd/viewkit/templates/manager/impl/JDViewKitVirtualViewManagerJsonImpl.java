package com.jd.viewkit.templates.manager.impl;

import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import com.jd.viewkit.tool.JSONTool;
import com.jd.viewkit.tool.StringTool;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualViewManagerJsonImpl implements JDViewKitVirtualViewManager {
    private Map<String, JDViewKitVirtualView> dslsMap = new HashMap();

    @Override // com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager
    public void dslMap2Virtual(String str, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel) {
        JSONObject jSONObject;
        if (StringTool.isEmpty(str)) {
            return;
        }
        JSONObject JSONObject = JSONTool.JSONObject(str);
        Iterator<String> keys = JSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String string = JSONTool.getString(JSONObject, next);
            if (string != null) {
                jSONObject = JSONTool.JSONObject(string);
            } else {
                jSONObject = JSONTool.getJSONObject(JSONObject, next);
            }
            JDViewKitVirtualView parseVirtualView = JDViewKitVirtualView.parseVirtualView(jSONObject, jDViewKitVirtualServiceModel, this.dslsMap);
            if (parseVirtualView != null) {
                this.dslsMap.put(next, parseVirtualView);
            }
        }
    }

    @Override // com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager
    public JDViewKitVirtualView dslRoot2Virtaul(String str, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel) {
        JDViewKitVirtualView jDViewKitVirtualView;
        JSONObject JSONObject = JSONTool.JSONObject(str);
        JSONTool.getJSONObject(JSONObject, "root");
        JDViewKitVirtualView parseVirtualView = JDViewKitVirtualView.parseVirtualView(JSONObject, jDViewKitVirtualServiceModel, this.dslsMap);
        if (parseVirtualView == null || StringTool.isEmpty(parseVirtualView.getType()) || !JDViewKitBaseLayout.typeSet.contains(parseVirtualView.getType())) {
            return null;
        }
        return (!StringTool.isNotEmpty(parseVirtualView.getDslId()) || (jDViewKitVirtualView = this.dslsMap.get(parseVirtualView.getDslId())) == null) ? parseVirtualView : jDViewKitVirtualView;
    }

    @Override // com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager
    public Map<String, JDViewKitVirtualView> getDslsMap() {
        return this.dslsMap;
    }

    @Override // com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager
    public JDViewKitVirtualView getRootVirtaul(String str) {
        return this.dslsMap.get(str);
    }

    @Override // com.jd.viewkit.templates.manager.JDViewKitVirtualViewManager
    public void saveRootVirtaul(String str, JDViewKitVirtualView jDViewKitVirtualView) {
        this.dslsMap.put(str, jDViewKitVirtualView);
    }
}
