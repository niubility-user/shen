package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.text.TextConfig;
import com.jd.lib.flexcube.widgets.entity.text.TextDataPath;

/* loaded from: classes15.dex */
public class TextEntity extends BaseWidgetEntity {
    public TextConfig config;
    public TextDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
