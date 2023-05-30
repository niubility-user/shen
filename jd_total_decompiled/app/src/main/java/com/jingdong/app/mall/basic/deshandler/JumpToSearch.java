package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.search.FilterConstant;
import com.jingdong.common.search.entity.FilterItem;
import com.jingdong.common.search.entity.SearchInfo;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.List;

@Des(des = "search")
/* loaded from: classes19.dex */
public class JumpToSearch extends a {
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
        bundle2.putString(JshopConst.JSHOP_SEARCH_KEYWORD, bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD));
        String string3 = bundle.getString("channelLatitude");
        String string4 = bundle.getString("channelLongitude");
        if (!TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string4)) {
            bundle2.putString("channelLatitude", string3);
            bundle2.putString("channelLongitude", string4);
            if (Log.D) {
                Log.d("JumpToProduct_list", "channelLatitude:" + string3 + ", channelLongitude" + string4);
            }
        }
        bundle2.putString("caller", bundle.getString("caller"));
        bundle2.putString("featureBits", bundle.getString("featureBits"));
        bundle2.putString("relatedWares", bundle.getString("relatedWares"));
        bundle2.putString("relatedWaresAbVersion", bundle.getString("relatedWaresAbVersion"));
        String string5 = bundle.getString("showWord");
        String string6 = bundle.getString("realWord");
        if (!TextUtils.isEmpty(string5)) {
            bundle2.putString("hintword", string5);
            bundle2.putString("realword", string6);
            bundle2.putBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, false);
        } else {
            bundle2.putBoolean(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, true);
        }
        String string7 = bundle.getString("bodyMaps", "");
        if (!TextUtils.isEmpty(string7)) {
            if (Log.D) {
                Log.d("JumpToProduct_list", "bodyMaps:" + string7);
            }
            List parseArray = JDJSON.parseArray(string7, FilterItem.class);
            if (parseArray != null && !parseArray.isEmpty()) {
                bundle2.putSerializable("bodyMaps", (Serializable) parseArray);
            }
        }
        String string8 = bundle.getString("clearBodyMaps", "");
        if (!TextUtils.isEmpty(string8)) {
            if (Log.D) {
                Log.d("JumpToProduct_list", "clearBodyMaps:" + string8);
            }
            List parseArray2 = JDJSON.parseArray(string8.toString(), FilterItem.class);
            if (parseArray2 != null && !parseArray2.isEmpty()) {
                bundle2.putSerializable("clearBodyMaps", (Serializable) parseArray2);
            }
        }
        String string9 = bundle.getString(FilterConstant.IS_ALLWORLD_SHOPPING);
        if (!TextUtils.isEmpty(string9) && TextUtils.equals("1", string9)) {
            bundle2.putBoolean(FilterConstant.IS_ALLWORLD_SHOPPING, true);
            bundle2.putBoolean("global_from_channel", true);
        }
        String string10 = bundle.getString(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON);
        if (!TextUtils.isEmpty(string10) && TextUtils.equals("1", string10)) {
            bundle2.putBoolean(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, true);
        }
        DeepLinkProductListHelper.startSearchActivity(context, bundle2);
        c(context);
    }
}
