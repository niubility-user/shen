package com.jd.lib.flexcube.widgets.tools;

import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.MaterialGroup;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class SplitDataUtils {
    public static List<FlexCubeModel> getLinearModeList(String str) {
        ArrayList arrayList = new ArrayList();
        FlexCubeModel flexCubeModel = (FlexCubeModel) CommonServiceUtil.parseObject(str, FlexCubeModel.class);
        if (flexCubeModel == null) {
            return arrayList;
        }
        FloorConfig floorConfig = flexCubeModel.floorConfig;
        CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
        String str2 = flexCubeModel.elementList;
        List<MaterialGroup> list = flexCubeModel.materialGroupList;
        if (list != null && list.size() >= 1) {
            String str3 = flexCubeModel.materialGroupList.get(0).groupId;
            if (flexCubeModel.materialGroupList.get(0).groupInfoList != null && flexCubeModel.materialGroupList.get(0).groupInfoList.size() >= 1 && flexCubeModel.getColumns() >= 1) {
                int size = flexCubeModel.materialGroupList.get(0).groupInfoList.size() / flexCubeModel.getColumns();
                for (int i2 = 0; i2 < size; i2++) {
                    FlexCubeModel flexCubeModel2 = new FlexCubeModel();
                    flexCubeModel2.canvasConfig = canvasConfig;
                    flexCubeModel2.elementList = str2;
                    flexCubeModel2.floorConfig = floorConfig;
                    ArrayList arrayList2 = new ArrayList();
                    flexCubeModel2.materialGroupList = arrayList2;
                    arrayList2.add(new MaterialGroup(str3, true));
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < flexCubeModel.getColumns(); i3++) {
                        int columns = (flexCubeModel.getColumns() * i2) + i3;
                        flexCubeModel2.materialGroupList.get(0).groupInfoList.add(flexCubeModel.materialGroupList.get(0).groupInfoList.get(columns));
                        arrayList3.add(Integer.valueOf(columns));
                    }
                    arrayList.add(flexCubeModel2);
                }
            }
        }
        return arrayList;
    }
}
