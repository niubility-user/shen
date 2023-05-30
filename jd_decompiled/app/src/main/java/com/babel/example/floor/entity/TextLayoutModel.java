package com.babel.example.floor.entity;

import com.jd.lib.babel.ifloor.entity.BaseFloorModel;
import java.util.List;

/* loaded from: classes.dex */
public class TextLayoutModel extends BaseFloorModel {
    public List<PicGroup> advertGroupData;
    public TextLayoutConfig boardParams;

    @Override // com.jd.lib.babel.ifloor.entity.BaseFloorModel
    public boolean handleData() {
        List<PicGroup> list = this.advertGroupData;
        return list != null && list.size() > 0 && this.advertGroupData.get(0).hasData();
    }
}
