package com.jingdong.common.XView2.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jingdong.common.XView2.entity.timer.XViewCountDownConfig;
import com.jingdong.common.XView2.entity.timer.XViewCountDownDataPath;

/* loaded from: classes5.dex */
public class XViewCountDownViewEntity extends BaseWidgetEntity {
    public XViewCountDownConfig config;
    public XViewCountDownDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
