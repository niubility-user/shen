package com.jd.lib.productdetail.core.floor;

import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator;

/* loaded from: classes15.dex */
public class PDTemplateCreator extends FloorTemplateCreator<FloorTemplate> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator
    public FloorTemplate createFloorTemplate(String str, String str2) {
        FloorTemplate floorTemplate = new FloorTemplate(null);
        floorTemplate.mId = str;
        floorTemplate.bId = str2;
        return floorTemplate;
    }
}
