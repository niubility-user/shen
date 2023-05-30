package com.jd.lib.productdetail.core.entitys.caro2o;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCarTextEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PDCarEntity {
    public String addCarText;
    public String addCarUrl;
    public String carContext;
    public String carInfoIntroduceUrl;
    public String carManagerUrl;
    public List<PDCarItemEntity> carModelInfo;
    public boolean firstBind;
    public String firstBindText;
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
