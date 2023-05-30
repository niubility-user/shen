package com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCarTextEntity;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class PDCarEntity {
    public String addCarUrl;
    public String carContext;
    public String carInfoIntroduceUrl;
    public String carManagerUrl;
    public List<PDCarItemEntity> carModelInfo;
    public boolean needCarGift;
    public boolean showFlag;
    public WareBusinessCarTextEntity textMap;

    public void dealData(boolean z, boolean z2) {
        List<PDCarItemEntity> list = this.carModelInfo;
        if (list == null || !z) {
            return;
        }
        for (PDCarItemEntity pDCarItemEntity : list) {
            if (pDCarItemEntity != null && !TextUtils.isEmpty(pDCarItemEntity.carModelName)) {
                pDCarItemEntity.carModelName = PDUtils.decryptThreeDESECB(pDCarItemEntity.carModelName, z2);
            }
        }
    }
}
