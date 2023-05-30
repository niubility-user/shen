package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.search.entity.SearchInfo;

@Des(des = JumpUtil.VALUE_DES_ONEHOUR_SEARCH)
/* loaded from: classes19.dex */
public class JumpToHourSearch extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("from", bundle.getString("from"));
        bundle2.putString("category", bundle.getString("category"));
        String string = bundle.getString("channelName");
        String string2 = bundle.getString("channelTitle");
        if (!TextUtils.isEmpty(string)) {
            SearchInfo searchInfo = new SearchInfo();
            searchInfo.channelName = string;
            searchInfo.channelTitle = string2;
            bundle2.putSerializable("searchinfo", searchInfo);
        }
        bundle2.putString("keyword", bundle.getString("keyword"));
        bundle2.putString("caller", bundle.getString("caller"));
        String string3 = bundle.getString("showWord");
        String string4 = bundle.getString("realWord");
        if (!TextUtils.isEmpty(string3)) {
            bundle2.putString("hintword", string3);
            bundle2.putString("realword", string4);
        }
        bundle2.putString(PdLVBody.LONGITUDE, bundle.getString(PdLVBody.LONGITUDE));
        bundle2.putString(PdLVBody.LATITUDE, bundle.getString(PdLVBody.LATITUDE));
        DeepLinkProductListHelper.startHourSearchActivity(context, bundle2);
        c(context);
    }
}
