package com.jd.viewkit.dataSources.manager;

import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.thirdinterface.model.JDViewKitParamsModel;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public interface JDViewKitDataSourcesManager {
    Map<String, Object> dslData2Map(String str);

    List<JDViewKitDataSourceModel> dslData2Model(Map<String, Object> map, JDViewKitVirtualView jDViewKitVirtualView, Long l2);

    void setParamsModel(JDViewKitParamsModel jDViewKitParamsModel);
}
