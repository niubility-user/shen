package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.text.GraphicConfig;
import com.jd.lib.flexcube.widgets.entity.text.GraphicDataPath;
import com.jd.lib.flexcube.widgets.entity.text.TextDataPath;

/* loaded from: classes15.dex */
public class GraphicEntity extends BaseWidgetEntity {
    public GraphicConfig config;
    public GraphicDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return getConfig();
    }

    public GraphicConfig getConfig() {
        if (this.config == null) {
            this.config = new GraphicConfig();
        }
        return this.config;
    }

    public TextEntity getTextEntity() {
        if (this.dataPath == null) {
            return null;
        }
        TextEntity textEntity = new TextEntity();
        textEntity.config = getConfig().getTextConfig();
        String str = getConfig().imgTextOrder;
        str.hashCode();
        if (!str.equals("1") && !str.equals("2")) {
            textEntity.config.w = getConfig().w - (getConfig().paddingInfo != null ? getConfig().paddingInfo.getLeftAndRightValue() : 0);
        } else {
            textEntity.config.w = ((getConfig().w - getConfig().imgTextDistance) - getConfig().getImgConfig().w) - (getConfig().paddingInfo != null ? getConfig().paddingInfo.getLeftAndRightValue() : 0);
        }
        textEntity.config.autoFitType = "1";
        textEntity.dataPath = new TextDataPath(this.dataPath.text);
        return textEntity;
    }
}
