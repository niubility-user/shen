package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.process.ProcessConfig;
import com.jd.lib.flexcube.widgets.entity.process.ProcessDataPath;

/* loaded from: classes15.dex */
public class ProcessEntity extends BaseWidgetEntity {
    public ProcessConfig config;
    public ProcessDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
