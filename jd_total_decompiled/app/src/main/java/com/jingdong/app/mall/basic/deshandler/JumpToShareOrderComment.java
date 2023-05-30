package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkShareOrderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_SHARE_ORDER_COMMENT)
/* loaded from: classes19.dex */
public class JumpToShareOrderComment extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(83))) {
            String string = bundle.getString("subDes");
            if (!TextUtils.isEmpty(string)) {
                if (TextUtils.equals("showCommentSelection", string)) {
                    DeepLinkShareOrderHelper.startCommentSelectionActivity(context, bundle);
                } else {
                    URLParamMap uRLParamMap = new URLParamMap();
                    uRLParamMap.put(RemoteMessageConst.TO, "https://share.m.jd.com/eofficer/notification.html");
                    SerializableContainer serializableContainer = new SerializableContainer();
                    serializableContainer.setMap(uRLParamMap);
                    bundle.putSerializable("urlParamMap", serializableContainer);
                    bundle.putString("urlAction", RemoteMessageConst.TO);
                    DeepLinkCommonHelper.startWebActivity(context, bundle, true);
                }
            }
        }
        c(context);
    }
}
