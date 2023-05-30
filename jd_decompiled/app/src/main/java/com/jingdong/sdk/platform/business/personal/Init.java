package com.jingdong.sdk.platform.business.personal;

import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.constant.BpPersonalConstants;
import com.jingdong.sdk.platform.business.personal.floor.CommonBannerFloor;
import com.jingdong.sdk.platform.business.personal.floor.CommonMultiIconFloor;
import com.jingdong.sdk.platform.manager.ViewHolderManager;

/* loaded from: classes10.dex */
public class Init {
    public static void init(ViewHolderManager viewHolderManager) {
        if (OKLog.D) {
            OKLog.d("Init", "manager register!");
        }
        viewHolderManager.register(BpPersonalConstants.FLOOR_COMMON_BANNER, CommonBannerFloor.class);
        viewHolderManager.register(BpPersonalConstants.FLOOR_COMMON_MULTI_ICON, CommonMultiIconFloor.class);
    }
}
