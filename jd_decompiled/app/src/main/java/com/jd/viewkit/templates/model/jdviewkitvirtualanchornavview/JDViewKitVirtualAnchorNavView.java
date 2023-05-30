package com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview;

import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.model.JDViewKitVirtualServiceModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitVirtualAnchorNavView extends JDViewKitVirtualView {
    public JDViewKitVirtualAnchorNavView(JSONObject jSONObject, JDViewKitVirtualServiceModel jDViewKitVirtualServiceModel, Map<String, JDViewKitVirtualView> map) {
        super(jSONObject, jDViewKitVirtualServiceModel, map);
    }

    public static List<JDViewKitVirtualAnchorIndex> getAchorIndex(List<JDViewKitDataSourceModel> list) {
        String str;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (JDViewKitDataSourceModel jDViewKitDataSourceModel : list) {
                if (jDViewKitDataSourceModel.getDataMap() != null && (str = (String) jDViewKitDataSourceModel.getDataMap().get("moduleId")) != null) {
                    arrayList.add(new JDViewKitVirtualAnchorIndex(str, -1, -1));
                }
            }
            if (list.size() == arrayList.size()) {
                return arrayList;
            }
            return null;
        }
        return null;
    }
}
