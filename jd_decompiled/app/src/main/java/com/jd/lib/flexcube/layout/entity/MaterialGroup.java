package com.jd.lib.flexcube.layout.entity;

import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class MaterialGroup {
    public String groupId;
    public List<MaterialModel> groupInfoList;

    public MaterialGroup() {
    }

    public boolean hasData() {
        List<MaterialModel> list = this.groupInfoList;
        return list != null && list.size() > 0;
    }

    public MaterialGroup(String str, boolean z) {
        this.groupId = str;
        if (z) {
            this.groupInfoList = new ArrayList();
        }
    }
}
