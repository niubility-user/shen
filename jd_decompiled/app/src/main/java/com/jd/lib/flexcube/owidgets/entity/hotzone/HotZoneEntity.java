package com.jd.lib.flexcube.owidgets.entity.hotzone;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;

/* loaded from: classes15.dex */
public class HotZoneEntity extends BaseWidgetEntity {
    public BaseConfig config;
    public HotZoneDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        if (this.config == null) {
            this.config = new BaseConfig();
        }
        return this.config;
    }
}
