package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessServiceIconTrustEntity {
    public String bgImage;
    public String guideJumpUrl;
    public String guideLeft;
    public String guideText;
    public String iconInDialog;
    public List<WareBusinessServiceIconEntity> iconList;
    public List<WareBusinessServiceIconEntity> iconListOne;
    public String iconUrl;
    public String logoIcon;
    public String separator;
    public String serviceIconKey;
    public String subtitle;
    public String title;
    public String type;

    public String getType() {
        return "s".equals(this.type) ? "1" : "2";
    }
}
