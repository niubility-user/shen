package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.jump.JumpUtil;

@Des(des = JumpUtil.VALUE_DES_JSHOP_PRODUCT_LIST)
/* loaded from: classes19.dex */
public class JumpToJshop_product_list extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle.containsKey("pageId")) {
            bundle.putString("page_id", bundle.getString("pageId"));
        }
        if (bundle.containsKey("categoryId")) {
            bundle.putString("id", bundle.getString("categoryId"));
        }
        if (bundle.containsKey("sort")) {
            bundle.putInt("sortKey", bundle.getInt("sort"));
        }
        if (bundle.containsKey("categoryName")) {
            bundle.putString("title", bundle.getString("categoryName"));
        }
        if (bundle.containsKey("searchType")) {
            bundle.putString("searchType", bundle.getString("searchType"));
        }
        DeepLinkJShopHomeHelper.gotoJShopProductList(context, bundle);
        c(context);
    }
}
