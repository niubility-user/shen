package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkFollowHelper;

@Des(des = "tag_list")
/* loaded from: classes19.dex */
public class JumpToFollowTopic extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        DeepLinkFollowHelper.startFollowTopicActivity(context, bundle);
        c(context);
    }
}
