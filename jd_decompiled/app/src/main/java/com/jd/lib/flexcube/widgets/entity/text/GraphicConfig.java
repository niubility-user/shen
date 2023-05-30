package com.jd.lib.flexcube.widgets.entity.text;

import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.common.FrameInfo;

/* loaded from: classes15.dex */
public class GraphicConfig extends BaseConfig {
    public String autoFitType;
    public String bgColor;
    public String bgImage;
    public String bgType;
    public CfInfo cfInfo;
    public FrameInfo frameInfo;
    public ImageConfig imgConfig;
    public float imgTextDistance;
    public String imgTextOrder;
    public PaddingInfo paddingInfo;
    public TextConfig textConfig;
    public String xAlign;
    public String yAlign;

    public ImageConfig getImgConfig() {
        if (this.imgConfig == null) {
            this.imgConfig = new ImageConfig();
        }
        return this.imgConfig;
    }

    public TextConfig getTextConfig() {
        if (this.textConfig == null) {
            this.textConfig = new TextConfig();
        }
        return this.textConfig;
    }
}
