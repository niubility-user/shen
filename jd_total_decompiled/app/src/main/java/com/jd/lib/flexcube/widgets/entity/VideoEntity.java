package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.video.VideoConfig;
import com.jd.lib.flexcube.widgets.entity.video.VideoDataPath;

/* loaded from: classes15.dex */
public class VideoEntity extends BaseWidgetEntity {
    public VideoConfig config;
    public VideoDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }
}
