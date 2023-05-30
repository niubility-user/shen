package com.jd.lib.flexcube.owidgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.owidgets.entity.exchange.ExchangeButtonDataPath;

/* loaded from: classes15.dex */
public class ExchangeButtonEntity extends BaseWidgetEntity {
    public BaseConfig config;
    public ExchangeButtonDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
