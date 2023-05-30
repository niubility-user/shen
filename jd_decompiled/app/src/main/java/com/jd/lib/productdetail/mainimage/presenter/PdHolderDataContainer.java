package com.jd.lib.productdetail.mainimage.presenter;

import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class PdHolderDataContainer {
    public boolean isSHowCfTag = true;
    public SkinRecommendInfoEntity skinRecommendInfoEntity;

    public void onCleared(BaseActivity baseActivity) {
        this.skinRecommendInfoEntity = null;
        this.isSHowCfTag = true;
    }
}
