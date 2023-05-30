package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkBingHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;

@Des(des = "getXB,native_littlebing,xiaobing")
/* loaded from: classes19.dex */
public class JumpToXiaobing extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        boolean isSwitchOpen = DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(14));
        JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplicationContext(), "BundleBingClick", AuraBundleInfos.getBundleNameFromBundleId(14) + CartConstant.KEY_YB_INFO_LINK + isSwitchOpen, "", "" + PackageInfoUtil.getVersionCode(), "", "", "");
        if (isSwitchOpen && (context instanceof IMyActivity)) {
            DeepLinkBingHelper.launchBingActivity((IMyActivity) context, bundle);
        }
        c(context);
    }
}
