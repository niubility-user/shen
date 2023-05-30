package com.jingdong.sdk.platform.business;

import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.viewholder.CommonBusinessViewHolderB;
import com.jingdong.sdk.platform.business.viewholder.CommonDViewHolderB;
import com.jingdong.sdk.platform.business.viewholder.CommonPartsViewHolderB;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.manager.ViewHolderManager;

/* loaded from: classes9.dex */
public class Init {
    public static void init(ViewHolderManager viewHolderManager) {
        if (OKLog.D) {
            OKLog.d("Init", "manager register!");
        }
        viewHolderManager.register(BaseFloorConstant.PLATFORM_FLOOR_BPCOMMOND, CommonDViewHolderB.class);
        viewHolderManager.register(BaseFloorConstant.PLATFORM_FLOOR_BPPARTS, CommonPartsViewHolderB.class);
        viewHolderManager.register(BaseFloorConstant.PLATFORM_FLOOR_TOP_BUSINESS, CommonBusinessViewHolderB.class);
        viewHolderManager.register(BaseFloorConstant.PLATFORM_FLOOR_BUSINESS, CommonBusinessViewHolderB.class);
    }
}
