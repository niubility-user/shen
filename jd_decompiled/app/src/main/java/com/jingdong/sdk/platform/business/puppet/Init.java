package com.jingdong.sdk.platform.business.puppet;

import com.jingdong.platform.annotation.PlatformInit;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.puppet.floor.CommonPuppetViewHolder;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.manager.ViewHolderManager;

@PlatformInit(id = "puppet")
/* loaded from: classes10.dex */
public class Init {
    public static void init(ViewHolderManager viewHolderManager) {
        if (OKLog.D) {
            OKLog.d("Init", "manager register!");
        }
        viewHolderManager.register(BaseFloorConstant.PLATFORM_FLOOR_PUPPET, CommonPuppetViewHolder.class);
    }
}
