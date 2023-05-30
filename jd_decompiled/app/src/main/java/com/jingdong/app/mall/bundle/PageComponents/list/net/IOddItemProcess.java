package com.jingdong.app.mall.bundle.PageComponents.list.net;

import com.jingdong.app.mall.bundle.PageComponents.list.net.BaseGeneralData;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import java.util.List;

/* loaded from: classes19.dex */
public abstract class IOddItemProcess<GeneralData extends BaseGeneralData> {
    public int getSpanCountForOdd() {
        return 1;
    }

    public boolean isSpan(int i2) {
        return false;
    }

    public int removeOddEntity(GeneralData generaldata) {
        int spanCountForOdd = getSpanCountForOdd();
        if (spanCountForOdd > 1) {
            List<IFloorEntity> list = generaldata.generalList;
            if (list.size() <= spanCountForOdd) {
                return -1;
            }
            int i2 = 0;
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                if (isSpan(list.get(i4).getFloorType())) {
                    i2++;
                    i3 = i4;
                }
            }
            if (i2 % spanCountForOdd == 1 && i3 > 0) {
                list.remove(i3);
                return i3;
            }
        }
        return -1;
    }
}
