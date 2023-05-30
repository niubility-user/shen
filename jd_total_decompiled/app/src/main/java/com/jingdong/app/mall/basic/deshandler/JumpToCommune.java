package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.util.Arrays;

@Des(des = JumpUtil.VALUE_DES_COMMUNE)
/* loaded from: classes19.dex */
public class JumpToCommune extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null && DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(20))) {
            String string = bundle.getString("subDes");
            if (!TextUtils.isEmpty(string)) {
                if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_LIST, string)) {
                    DeepLinkCommuneHelper.startCommuneList(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_DETAIL, string)) {
                    DeepLinkCommuneHelper.startCommuneDetail(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_PUBLISH, string)) {
                    DeepLinkCommuneHelper.startCommunePublish(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_USER_CENTER, string)) {
                    DeepLinkCommuneHelper.startCommuneUserCenter(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_COMMENT_LIST, string)) {
                    DeepLinkCommuneHelper.startCommuneCommentList(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_REPLY_LIST, string)) {
                    DeepLinkCommuneHelper.startCommuneReplyList(context, bundle);
                } else if (TextUtils.equals(DeepLinkCommuneHelper.HOST_COMMUNE_LABEL_DETAIL, string)) {
                    DeepLinkCommuneHelper.startCommuneLabelDetail(context, bundle);
                } else if (Arrays.asList(DeepLinkCommuneHelper.HOSTs).contains(string)) {
                    DeepLinkCommuneHelper.startCommunePage(context, bundle, string);
                } else {
                    URLParamMap uRLParamMap = new URLParamMap();
                    uRLParamMap.put(RemoteMessageConst.TO, "https://vs.m.jd.com/commentAppdau/index.html");
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
