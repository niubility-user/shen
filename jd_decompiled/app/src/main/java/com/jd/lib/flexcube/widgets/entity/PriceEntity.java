package com.jd.lib.flexcube.widgets.entity;

import android.text.TextUtils;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.widgets.entity.product.PriceConfig;
import com.jd.lib.flexcube.widgets.entity.product.PriceDataPath;

/* loaded from: classes15.dex */
public class PriceEntity extends BaseWidgetEntity {
    public PriceConfig config;
    public PriceDataPath dataPath;

    @Override // com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity
    public BaseConfig getBaseConfig() {
        return this.config;
    }

    public PriceConfig getConfig() {
        return this.config;
    }

    public boolean hasLabel() {
        return getConfig().priceLabel != null && !TextUtils.isEmpty(getConfig().priceLabel.src) && getConfig().priceLabel.w > 0.0f && getConfig().priceLabel.f4511h > 0.0f;
    }
}
