package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import java.util.List;

/* loaded from: classes3.dex */
public class WareBusinessServiceIconTrustEntity {
    public String guideJumpUrl;
    public String guideText;
    public String iconInDialog;
    public List<WareBusinessServiceIconEntity> iconList;
    public List<WareBusinessServiceIconEntity> iconListOne;
    public String iconUrl;
    public String separator;
    public String type;

    public String getType() {
        return "s".equals(this.type) ? "1" : "2";
    }
}
