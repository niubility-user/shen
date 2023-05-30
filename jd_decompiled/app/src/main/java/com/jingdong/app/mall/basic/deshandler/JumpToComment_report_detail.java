package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkEvaluateCenterHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_COMMENT_REPORT_DETAIL)
/* loaded from: classes19.dex */
public class JumpToComment_report_detail extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("wareId");
            String string2 = bundle.getString("reportId");
            Bundle bundle2 = new Bundle();
            bundle2.putString("skuId", string);
            bundle2.putString("reportId", string2);
            DeepLinkEvaluateCenterHelper.startCommentReportDetail(context, bundle2);
        }
        c(context);
    }
}
