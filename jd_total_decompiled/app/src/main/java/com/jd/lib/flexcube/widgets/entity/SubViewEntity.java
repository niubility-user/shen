package com.jd.lib.flexcube.widgets.entity;

import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.widgets.entity.subview.SubViewFlexCubeModel;

/* loaded from: classes15.dex */
public class SubViewEntity extends BaseWidgetEntity {
    public SubViewFlexCubeModel flexCubeModel;

    public SubViewEntity() {
        this.type = "800000";
    }

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        SubViewFlexCubeModel subViewFlexCubeModel = this.flexCubeModel;
        if (subViewFlexCubeModel != null) {
            return subViewFlexCubeModel.config;
        }
        return null;
    }

    public boolean hasVideo() {
        SubViewFlexCubeModel subViewFlexCubeModel = this.flexCubeModel;
        if (subViewFlexCubeModel != null) {
            return subViewFlexCubeModel.hasVideo;
        }
        return false;
    }

    public void parseFlexCubeModel(String str) {
        BaseConfig baseConfig;
        FloorConfig floorConfig;
        SubViewFlexCubeModel subViewFlexCubeModel = (SubViewFlexCubeModel) CommonServiceUtil.parseObject(str, SubViewFlexCubeModel.class);
        if (subViewFlexCubeModel != null) {
            this.flexCubeModel = subViewFlexCubeModel;
            if (subViewFlexCubeModel == null || (baseConfig = subViewFlexCubeModel.config) == null || (floorConfig = subViewFlexCubeModel.floorConfig) == null) {
                return;
            }
            baseConfig.w = floorConfig.w;
            baseConfig.f4225h = floorConfig.f4266h;
        }
    }
}
