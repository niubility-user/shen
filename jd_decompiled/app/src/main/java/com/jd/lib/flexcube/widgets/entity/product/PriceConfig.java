package com.jd.lib.flexcube.widgets.entity.product;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.widgets.entity.common.FontInfo;
import com.jd.lib.flexcube.widgets.entity.common.ImgLabel;

/* loaded from: classes15.dex */
public class PriceConfig extends BaseConfig {
    public String autoFitType;
    public String color;
    public FontInfo fontInfo;
    public String linearLayout_autoFitType;
    public ImgLabel priceLabel;

    public int getTextSize(float f2) {
        FontInfo fontInfo = this.fontInfo;
        if (fontInfo != null) {
            return (int) (fontInfo.size * f2);
        }
        return 0;
    }
}
