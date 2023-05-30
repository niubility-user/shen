package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.constant.JshopConst;

@Des(des = JumpUtil.VALUE_DES_CHANNEL_PRODUCTLIST)
/* loaded from: classes19.dex */
public class JumpToChannelProductList extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("from", bundle.getString("from"));
        bundle2.putString("category", bundle.getString("category"));
        bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, bundle.getString("keyword"));
        bundle2.putInt("requestCode", bundle.getInt("requestCode"));
        bundle2.putString("caller", bundle.getString("caller"));
        DeepLinkProductListHelper.startChannelProductListActivity(context, bundle2);
        c(context);
    }
}
