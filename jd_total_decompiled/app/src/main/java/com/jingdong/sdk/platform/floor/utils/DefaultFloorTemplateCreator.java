package com.jingdong.sdk.platform.floor.utils;

import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;

/* loaded from: classes10.dex */
public class DefaultFloorTemplateCreator extends FloorTemplateCreator<BaseTemplateEntity> {
    private DefaultFloorTemplateCreator() {
    }

    public static DefaultFloorTemplateCreator newInstance() {
        return new DefaultFloorTemplateCreator();
    }

    @Override // com.jingdong.sdk.platform.floor.utils.FloorTemplateCreator
    public BaseTemplateEntity createFloorTemplate(String str, String str2) {
        return new BaseTemplateEntity(str, str2);
    }
}
