package com.jd.lib.productdetail.mainimage.bean;

import android.content.Context;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;

/* loaded from: classes15.dex */
public class PdMainImagePagerEntity {
    public String imageUrl;
    private Context mContext;
    public boolean mIsDefault = false;
    public WareBusinessMagicHeadPicInfoEntity magicHeadPicData;
    public int position;
    public String skuId;
    public WareBusinessUnitMainImageEntity topImageEntity;

    public PdMainImagePagerEntity imageUrl(String str) {
        this.imageUrl = str;
        return this;
    }

    public PdMainImagePagerEntity skuId(String str) {
        this.skuId = str;
        return this;
    }
}
