package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.text.LabelGroupConfig;
import com.jd.lib.flexcube.widgets.entity.text.LabelGroupDataPath;

/* loaded from: classes15.dex */
public class LabelGroupEntity extends BaseWidgetEntity {
    public LabelGroupConfig config;
    public LabelGroupDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
