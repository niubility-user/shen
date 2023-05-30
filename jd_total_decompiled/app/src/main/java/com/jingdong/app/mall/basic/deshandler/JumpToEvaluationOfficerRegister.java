package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

@Des(des = JumpUtil.VALUE_DES_EVALUATION_OFFICER_REGISTER)
/* loaded from: classes19.dex */
public class JumpToEvaluationOfficerRegister extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("toEvaluateCenter", "0");
        String string2 = bundle.getString("isTitleBarGone", "1");
        if ("0".equals(string)) {
            String string3 = bundle.getString("url");
            URLParamMap uRLParamMap = new URLParamMap();
            if (!TextUtils.isEmpty(string3)) {
                uRLParamMap.put(RemoteMessageConst.TO, string3);
            }
            Bundle bundle2 = new Bundle();
            SerializableContainer serializableContainer = new SerializableContainer();
            serializableContainer.setMap(uRLParamMap);
            bundle2.putSerializable("urlParamMap", serializableContainer);
            bundle2.putString("urlAction", RemoteMessageConst.TO);
            bundle2.putBoolean("isTopBarGone", "1".equals(string2));
            DeepLinkCommonHelper.startWebActivity(context, bundle2, true);
        } else {
            DeepLinkEvaluateCenterHelper.startEvaluateCenter(context, bundle);
        }
        c(context);
    }
}
