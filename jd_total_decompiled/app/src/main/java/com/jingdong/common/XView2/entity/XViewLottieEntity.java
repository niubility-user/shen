package com.jingdong.common.XView2.entity;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jingdong.common.XView2.entity.lottie.XViewLottieConfig;
import com.jingdong.common.XView2.entity.lottie.XViewLottieDataPath;

/* loaded from: classes5.dex */
public class XViewLottieEntity extends BaseWidgetEntity {
    public XViewLottieConfig config;
    public XViewLottieDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return getConfig();
    }

    public XViewLottieConfig getConfig() {
        if (this.config == null) {
            this.config = new XViewLottieConfig();
        }
        return this.config;
    }
}
