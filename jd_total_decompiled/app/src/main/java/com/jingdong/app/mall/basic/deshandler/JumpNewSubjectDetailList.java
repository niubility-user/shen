package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkGoodStuffHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_SUBJECT_NEW_LIST)
/* loaded from: classes19.dex */
public class JumpNewSubjectDetailList extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkGoodStuffHelper.startTopicDetailListNewActivity(context, bundle);
        c(context);
    }
}
