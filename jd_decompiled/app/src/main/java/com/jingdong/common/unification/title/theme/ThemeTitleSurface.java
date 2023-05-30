package com.jingdong.common.unification.title.theme;

import com.jd.dynamic.DYConstants;

/* loaded from: classes6.dex */
public class ThemeTitleSurface {
    public String colorType;
    public String imageUrl;
    public boolean isEffected;
    public String localImagePath;
    public String pageCode;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("pageCode:" + this.pageCode);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("isEffected:" + this.isEffected);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("imageUrl:" + this.imageUrl);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("localImagePath:" + this.localImagePath);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("colorType:" + this.colorType);
        return sb.toString();
    }
}
