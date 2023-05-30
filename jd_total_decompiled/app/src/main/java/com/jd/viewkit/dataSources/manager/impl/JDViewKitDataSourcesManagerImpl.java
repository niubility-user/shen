package com.jd.viewkit.dataSources.manager.impl;

import com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.thirdinterface.model.JDViewKitParamsModel;
import com.jd.viewkit.tool.ExpressionParserTool;
import com.jd.viewkit.tool.JSONTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class JDViewKitDataSourcesManagerImpl implements JDViewKitDataSourcesManager {
    private JDViewKitParamsModel paramsModel;

    @Override // com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager
    public Map<String, Object> dslData2Map(String str) {
        JSONObject JSONObject = JSONTool.JSONObject(str);
        if (JSONObject != null) {
            return JSONTool.getMap(JSONObject);
        }
        return null;
    }

    @Override // com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager
    public List<JDViewKitDataSourceModel> dslData2Model(Map<String, Object> map, JDViewKitVirtualView jDViewKitVirtualView, Long l2) {
        ArrayList arrayList = new ArrayList();
        List listByValueRe = ExpressionParserTool.getListByValueRe(jDViewKitVirtualView.getValueRefer(), map);
        if (listByValueRe != null && listByValueRe.size() > 0) {
            for (Object obj : listByValueRe) {
                if (obj instanceof Map) {
                    JDViewKitDataSourceModel jDViewKitDataSourceModel = new JDViewKitDataSourceModel((Map) obj);
                    jDViewKitDataSourceModel.setParamsModel(this.paramsModel);
                    jDViewKitDataSourceModel.setTimeStame(l2);
                    arrayList.add(jDViewKitDataSourceModel);
                }
            }
        }
        return arrayList;
    }

    @Override // com.jd.viewkit.dataSources.manager.JDViewKitDataSourcesManager
    public void setParamsModel(JDViewKitParamsModel jDViewKitParamsModel) {
        this.paramsModel = jDViewKitParamsModel;
    }
}
