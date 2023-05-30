package com.jd.viewkit.helper;

import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.templates.container.JDViewKitBottomNavView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventModel;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitBottomNavFloorModel extends JDViewKitFloorModel {
    private List<JDViewKitEventModel> eventModelList;

    public JDViewKitBottomNavFloorModel(String str, Map<String, Object> map, List<JDViewKitDataSourceModel> list, JDViewKitVirtualView jDViewKitVirtualView) {
        super(str, map, list, jDViewKitVirtualView);
    }

    public List<JDViewKitEventModel> getBottomEventModelList() {
        if (this.eventModelList == null) {
            this.eventModelList = JDViewKitBottomNavView.getBottomEventModelList(getDataSourceModelList(), this.dslMap);
        }
        return this.eventModelList;
    }

    public JDViewKitEventModel getBottomNavSelectEventModel() {
        return JDViewKitBottomNavView.getBottomNavSelectEventModel(getDataSourceModelList(), this.dslMap);
    }
}
