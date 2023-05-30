package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.text.ImageConfig;
import com.jd.lib.flexcube.widgets.entity.text.ImageDataPath;

/* loaded from: classes15.dex */
public class ImageEntity extends BaseWidgetEntity {
    public ImageConfig config;
    public ImageDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }

    public ImageConfig getConfig() {
        if (this.config == null) {
            this.config = new ImageConfig();
        }
        return this.config;
    }
}
