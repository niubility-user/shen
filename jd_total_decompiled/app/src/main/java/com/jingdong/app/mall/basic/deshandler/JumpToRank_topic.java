package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUES_DES_RANK_TOPIC)
/* loaded from: classes19.dex */
public class JumpToRank_topic extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkRankHelper.startRankTopicActivity(context, "", bundle.getString("topicId"), "", bundle.getString("fromName"), "", "");
        c(context);
    }
}
