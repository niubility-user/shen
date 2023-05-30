package com.jd.viewkit.helper;

import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.container.JDViewKitTopNavView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitTopNavFloorModel extends JDViewKitFloorModel {
    public JDViewKitTopNavFloorModel(String str, Map<String, Object> map, List<JDViewKitDataSourceModel> list, JDViewKitVirtualView jDViewKitVirtualView) {
        super(str, map, list, jDViewKitVirtualView);
    }

    public int getScelectIndex() {
        return JDViewKitTopNavView.getScelectIndex(getDataSourceModelList(), this.dslMap);
    }

    public List<JDViewKitEventModel> getTopEventModelList() {
        return JDViewKitTopNavView.getTopEventModelList(getDataSourceModelList(), this.dslMap);
    }
}
