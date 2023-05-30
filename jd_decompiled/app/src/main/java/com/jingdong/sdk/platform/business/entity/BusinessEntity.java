package com.jingdong.sdk.platform.business.entity;

import android.text.TextUtils;
import com.jingdong.sdk.platform.base.UnProguard;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class BusinessEntity extends UnProguard {
    public List<BusinessItemEntity> bizList;
    public boolean darkModel;
    public String eventId;
    public BusinessSlideStyle slide;
    public boolean tenthRevision;

    public List<BusinessItemEntity> dealData() {
        List<BusinessItemEntity> list = this.bizList;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (BusinessItemEntity businessItemEntity : this.bizList) {
            if (businessItemEntity != null && !TextUtils.isEmpty(businessItemEntity.icon) && !TextUtils.isEmpty(businessItemEntity.title) && !TextUtils.isEmpty(businessItemEntity.desc)) {
                arrayList.add(businessItemEntity);
            }
        }
        return arrayList;
    }

    public boolean isEffective() {
        return dealData() != null && dealData().size() > 0;
    }
}
