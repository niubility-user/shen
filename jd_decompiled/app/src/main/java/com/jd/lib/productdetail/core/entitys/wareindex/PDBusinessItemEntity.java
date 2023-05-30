package com.jd.lib.productdetail.core.entitys.wareindex;

/* loaded from: classes15.dex */
public class PDBusinessItemEntity {
    public String arrow;
    public String backgroundColor;
    public String businessType;
    public int dataType;
    public boolean isLogin;
    public boolean isLv;
    public boolean isSpecialBusiness;
    public int jumpToType;
    public String nameColor;
    public PDBusinessNodeEntity node;
    public PDBusinessPathEntity path;
    public int status;
    public String subNameColor;
    public String uid;

    public PDBusinessInfoEntity getBusinessInfo() {
        if (this.node != null) {
            PDBusinessInfoEntity pDBusinessInfoEntity = new PDBusinessInfoEntity();
            PDBusinessNodeEntity pDBusinessNodeEntity = this.node;
            pDBusinessInfoEntity.name = pDBusinessNodeEntity.name;
            pDBusinessInfoEntity.subName = pDBusinessNodeEntity.subName;
            pDBusinessInfoEntity.imageUrl = pDBusinessNodeEntity.imageUrl;
            pDBusinessInfoEntity.url = pDBusinessNodeEntity.url;
            return pDBusinessInfoEntity;
        }
        return null;
    }
}
