package com.jd.lib.flexcube.widgets.tools;

import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;

/* loaded from: classes15.dex */
public class DataTool {
    public static int[] getNineFloorWH(FlexCubeModel flexCubeModel) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        FloorConfig floorConfig = flexCubeModel.floorConfig;
        CanvasConfig canvasConfig = flexCubeModel.canvasConfig;
        float multiple = flexCubeModel.getMultiple();
        float canvasMultiple = flexCubeModel.getCanvasMultiple();
        int totalRow = flexCubeModel.getTotalRow();
        if (floorConfig == null || floorConfig.w < 1 || !FlexCube.GRIDVIEW.equals(FlexCube.getFloorId(flexCubeModel.getId()))) {
            return new int[]{0, 0};
        }
        FloorStyle floorStyle = floorConfig.floorStyle;
        if (floorStyle != null) {
            i3 = floorStyle.leftPadding;
            i4 = floorStyle.rightPadding;
            i5 = floorStyle.topPadding;
            i6 = floorStyle.bottomPadding;
            i2 = floorStyle.cardVPadding;
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        ViewStyle viewStyle = floorConfig.viewStyle;
        if (viewStyle != null) {
            i8 = viewStyle.topPadding;
            i7 = viewStyle.bottomPadding;
        } else {
            i7 = 0;
            i8 = 0;
        }
        int i9 = canvasConfig != null ? canvasConfig.f4223h : 0;
        int d = b.d(i3, multiple);
        int d2 = b.d(i5, multiple);
        int d3 = b.d(i4, multiple);
        int d4 = b.d(i6, multiple);
        int d5 = (b.d(floorConfig.w, multiple) - b.d(i3, multiple)) - b.d(i4, multiple);
        if (d5 < 0) {
            d5 = 0;
        }
        return new int[]{d5 + d + d3, b.d(i8 + i7 + (i2 * (totalRow - 1)), multiple) + b.d(i9 * totalRow, canvasMultiple) + d2 + d4};
    }
}
