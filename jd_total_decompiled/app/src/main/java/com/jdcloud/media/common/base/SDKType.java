package com.jdcloud.media.common.base;

import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;

/* loaded from: classes18.dex */
public enum SDKType {
    LIVE(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_LIVE),
    PLAYER(PlayerReportInfoEntity.PAGE_ID),
    PIP("pip"),
    EDITOR("editor"),
    OTHER("unknown"),
    VSR("sre");
    
    private String typeName;

    SDKType(String str) {
        this.typeName = str;
    }

    public String getType() {
        return this.typeName;
    }
}
