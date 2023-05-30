package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.search.FilterConstant;
import com.jingdong.common.search.ProductListConstant;
import com.jingdong.common.search.entity.FilterItem;
import com.jingdong.common.search.entity.SearchInfo;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.List;

@Des(des = JumpUtil.VAULE_DES_PRODUCT_LIST)
/* loaded from: classes19.dex */
public class JumpToProduct_list extends a {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void s(Context context, Bundle bundle) {
        String str;
        int i2;
        String str2;
        Intent intent;
        int i3;
        int i4;
        boolean z;
        String string;
        boolean z2;
        Bundle bundle2;
        String str3;
        char c2;
        String str4;
        if (h() != null && TextUtils.equals(h().getClass().getName(), "com.jd.lib.search.view.Activity.ProductListActivity") && TextUtils.isEmpty(bundle.getString("activityId")) && TextUtils.isEmpty(bundle.getString("couponId")) && TextUtils.isEmpty(bundle.getString("eCardID")) && !TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDSearch", "isSupportProductList", "isSupportProductList", "1"), "1")) {
            h().finish();
        }
        Intent intent2 = new Intent();
        String string2 = bundle.getString("from");
        String string3 = bundle.getString("sourceType");
        String string4 = bundle.getString("sourceValue");
        if (!TextUtils.isEmpty(string3) && !TextUtils.isEmpty(string4)) {
            intent2.putExtra("source", new SourceEntity(string3, string4));
        }
        if (Log.D) {
            Log.d(this.a, "toProductListActivity() -->> from = " + string2);
            Log.d(this.a, "toProductListActivity() -->> sourceType = " + string3);
            Log.d(this.a, "toProductListActivity() -->> sourceValue = " + string4);
        }
        if (TextUtils.equals(string2, "search")) {
            String string5 = bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD);
            Bundle bundle3 = new Bundle();
            if (!TextUtils.isEmpty(string5)) {
                try {
                    string = URLDecoder.decode(string5, "utf-8");
                } catch (Exception e2) {
                    if (Log.D) {
                        e2.printStackTrace();
                    }
                    string = bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD);
                }
                if (string == null) {
                    string = "";
                }
                bundle3.putString(JshopConst.JSHOP_SEARCH_KEYWORD, string);
            }
            bundle3.putBoolean("firstToList", true);
            if (bundle.getInt(FilterConstant.IS_ALLWORLD_SHOPPING, 0) == 0) {
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, false);
            } else {
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, true);
            }
            if ("1".equals(bundle.getString(FilterConstant.IS_ALLWORLD_SHOPPING))) {
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, true);
                intent2.putExtra("global_from_channel", true);
            }
            String string6 = bundle.getString("multiShopIds");
            String string7 = bundle.getString("brandIds");
            String string8 = bundle.getString("channelName", "");
            String string9 = bundle.getString("channelTitle", "");
            String string10 = bundle.getString("showWord", "");
            String string11 = bundle.getString("realWord", "");
            String string12 = bundle.getString("jdSupermarket", "");
            String string13 = bundle.getString("filterConfigKeys");
            String string14 = bundle.getString("cid", "");
            String string15 = bundle.getString(FilterConstant.FIELD, "");
            String string16 = bundle.getString(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON);
            String string17 = bundle.getString("save");
            if (!TextUtils.isEmpty(string10)) {
                intent2.putExtra("hintword", string10);
                intent2.putExtra("realword", string11);
                intent2.putExtra(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, false);
                z2 = true;
            } else {
                z2 = true;
                intent2.putExtra(DeepLinkProductListHelper.HINTWORDSAMEWITHMAIN, true);
            }
            if (!TextUtils.isEmpty(string16) && "1".equals(string16.trim())) {
                intent2.putExtra(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, z2);
            } else {
                intent2.putExtra(DeepLinkProductListHelper.HASHIDDENAUDIOBUTTON, false);
            }
            if (!TextUtils.isEmpty(string7)) {
                intent2.putExtra("brandIds", string7);
            }
            if (!TextUtils.isEmpty(string6)) {
                intent2.putExtra("multiShopIds", string6);
            }
            if (!TextUtils.isEmpty(string17)) {
                intent2.putExtra("save", string17);
            }
            if (!TextUtils.isEmpty(string8)) {
                SearchInfo searchInfo = new SearchInfo();
                searchInfo.channelName = string8;
                searchInfo.channelTitle = string9;
                if (!TextUtils.isEmpty(string14) && !TextUtils.isEmpty(string15)) {
                    searchInfo.cids = string14;
                    string15.hashCode();
                    switch (string15.hashCode()) {
                        case 49:
                            if (string15.equals("1")) {
                                c2 = 0;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 50:
                            if (string15.equals("2")) {
                                c2 = 1;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        case 51:
                            if (string15.equals("3")) {
                                c2 = 2;
                                break;
                            }
                            c2 = '\uffff';
                            break;
                        default:
                            c2 = '\uffff';
                            break;
                    }
                    switch (c2) {
                        case 0:
                            str4 = "cid1";
                            break;
                        case 1:
                            str4 = "cid2";
                            break;
                        case 2:
                            str4 = "catid";
                            break;
                        default:
                            str4 = "";
                            break;
                    }
                    searchInfo.fields = str4;
                }
                if (!TextUtils.isEmpty(string12)) {
                    searchInfo.supermarket = string12;
                }
                if (!TextUtils.isEmpty(string13)) {
                    searchInfo.filterConfigKeys = string13;
                }
                intent2.putExtra("searchinfo", searchInfo);
            }
            String string18 = bundle.getString("bodyMaps", "");
            if (TextUtils.isEmpty(string18)) {
                bundle2 = bundle3;
                str3 = "JumpToProduct_list";
            } else {
                if (Log.D) {
                    str3 = "JumpToProduct_list";
                    Log.d(str3, "bodyMaps:" + string18);
                } else {
                    str3 = "JumpToProduct_list";
                }
                List parseArray = JDJSON.parseArray(string18, FilterItem.class);
                if (parseArray == null || parseArray.isEmpty()) {
                    bundle2 = bundle3;
                } else {
                    bundle2 = bundle3;
                    bundle2.putSerializable("bodyMaps", (Serializable) parseArray);
                }
            }
            String string19 = bundle.getString("clearBodyMaps", "");
            if (!TextUtils.isEmpty(string19)) {
                if (Log.D) {
                    Log.d(str3, "clearBodyMaps:" + string19);
                }
                List parseArray2 = JDJSON.parseArray(string19.toString(), FilterItem.class);
                if (parseArray2 != null && !parseArray2.isEmpty()) {
                    bundle2.putSerializable("clearBodyMaps", (Serializable) parseArray2);
                }
            }
            intent2.putExtra(ProductListConstant.PRODUCT_LIST_SEARCH_SOURCE, bundle.getString(ProductListConstant.PRODUCT_LIST_SEARCH_SOURCE, ""));
            intent2.putExtras(bundle2);
            intent2.putExtra("sortKey", 0);
        } else if (TextUtils.equals(string2, "category")) {
            intent2.putExtra("sortKey", 1);
            String string20 = bundle.getString("cid");
            String string21 = bundle.getString("levelFirst");
            String string22 = bundle.getString("levelSecond");
            String string23 = bundle.getString("title");
            int i5 = bundle.getInt(FilterConstant.IS_ALLWORLD_SHOPPING, 0);
            Bundle bundle4 = new Bundle();
            if (!TextUtils.isEmpty(string23)) {
                bundle4.putString("name", string23);
            }
            if (!TextUtils.isEmpty(string20)) {
                bundle4.putString("cid", string20);
            }
            if (!TextUtils.isEmpty(string21)) {
                bundle4.putString("levelFirst", string21);
            }
            if (!TextUtils.isEmpty(string22)) {
                bundle4.putString("levelSecond", string22);
            }
            if (i5 == 0) {
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, false);
                z = true;
            } else {
                z = true;
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, true);
            }
            if ("1".equals(bundle.getString(FilterConstant.IS_ALLWORLD_SHOPPING))) {
                intent2.putExtra(FilterConstant.IS_ALLWORLD_SHOPPING, z);
            }
            bundle4.putBoolean("firstToList", z);
            intent2.putExtras(bundle4);
        } else if (TextUtils.equals(string2, "promotion")) {
            String string24 = bundle.getString("activityId");
            String string25 = bundle.getString("skuId");
            String string26 = bundle.getString("tip");
            String string27 = bundle.getString(ProductListConstant.INLET);
            String string28 = bundle.getString(ProductListConstant.INTEL);
            String string29 = bundle.getString("skuCid2");
            String string30 = bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD);
            Bundle bundle5 = new Bundle();
            if (!TextUtils.isEmpty(string24)) {
                bundle5.putString("activityId", string24);
            }
            if (!TextUtils.isEmpty(string25)) {
                bundle5.putString("skuId", string25);
            }
            if (!TextUtils.isEmpty(string26)) {
                bundle5.putString("tip", string26);
            }
            if (!TextUtils.isEmpty(string29)) {
                bundle5.putString("skuCid2", string29);
            }
            if (!TextUtils.isEmpty(string30)) {
                bundle5.putString(JshopConst.JSHOP_SEARCH_KEYWORD, string30);
            }
            try {
                i3 = Integer.parseInt(string28);
            } catch (Exception e3) {
                if (Log.D) {
                    e3.printStackTrace();
                }
                i3 = -1;
            }
            if (i3 == -1) {
                try {
                    i4 = Integer.parseInt(string27);
                } catch (Exception e4) {
                    if (Log.D) {
                        e4.printStackTrace();
                    }
                    i4 = -1;
                }
            } else {
                i4 = i3;
            }
            String string31 = bundle.getString("passThroughMap", "");
            if (!TextUtils.isEmpty(string31)) {
                if (Log.D) {
                    Log.d("JumpToProduct_list", "passThroughMap:" + string31);
                }
                bundle5.putSerializable("passThroughMap", string31);
            }
            String string32 = bundle.getString("clearPassThroughMap", "");
            if (!TextUtils.isEmpty(string32)) {
                if (Log.D) {
                    Log.d("JumpToProduct_list", "clearPassThroughMap:" + string32);
                }
                bundle5.putSerializable("clearPassThroughMap", string32);
            }
            bundle5.putInt(ProductListConstant.INLET, i4);
            bundle5.putBoolean("firstToList", true);
            intent2 = intent2;
            intent2.putExtras(bundle5);
            intent2.putExtra("sortKey", 0);
        } else if (TextUtils.equals(string2, "couponBatch")) {
            String string33 = bundle.getString("couponId");
            String string34 = bundle.getString("tip");
            String string35 = bundle.getString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE);
            String string36 = bundle.getString("couponfrom");
            String string37 = bundle.getString("skuCid2");
            String string38 = bundle.getString(ProductListConstant.INLET);
            String string39 = bundle.getString(ProductListConstant.INTEL);
            String string40 = bundle.getString("skuId");
            String string41 = bundle.getString(JshopConst.JSHOP_SEARCH_KEYWORD);
            Bundle bundle6 = new Bundle();
            if (TextUtils.isEmpty(string33)) {
                str = "passThroughMap";
            } else {
                str = "passThroughMap";
                bundle6.putString("CouponbatchID", string33);
            }
            if (!TextUtils.isEmpty(string34)) {
                bundle6.putString("tip", string34);
            }
            if (!TextUtils.isEmpty(string35)) {
                bundle6.putString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, string35);
            }
            if (!TextUtils.isEmpty(string36)) {
                bundle6.putString("couponfrom", string36);
            }
            if (!TextUtils.isEmpty(string37)) {
                bundle6.putString("skuCid2", string37);
            }
            if (!TextUtils.isEmpty(string41)) {
                bundle6.putString(JshopConst.JSHOP_SEARCH_KEYWORD, string41);
            }
            try {
                i2 = Integer.parseInt(string39);
            } catch (Exception e5) {
                if (Log.D) {
                    e5.printStackTrace();
                }
                i2 = -1;
            }
            if (i2 == -1) {
                try {
                    i2 = Integer.parseInt(string38);
                } catch (Exception e6) {
                    if (Log.D) {
                        e6.printStackTrace();
                    }
                    i2 = -1;
                }
            }
            bundle6.putInt(ProductListConstant.INLET, i2);
            if (!TextUtils.isEmpty(string40)) {
                bundle6.putString("skuId", string40);
            }
            String str5 = str;
            String string42 = bundle.getString(str5, "");
            if (TextUtils.isEmpty(string42)) {
                str2 = "JumpToProduct_list";
            } else {
                if (Log.D) {
                    str2 = "JumpToProduct_list";
                    Log.d(str2, "passThroughMap:" + string42);
                } else {
                    str2 = "JumpToProduct_list";
                }
                bundle6.putSerializable(str5, string42);
            }
            String string43 = bundle.getString("clearPassThroughMap", "");
            if (!TextUtils.isEmpty(string43)) {
                if (Log.D) {
                    Log.d(str2, "clearPassThroughMap:" + string43);
                }
                bundle6.putSerializable("clearPassThroughMap", string43);
            }
            bundle6.putBoolean("firstToList", true);
            intent = intent2;
            intent.putExtras(bundle6);
            intent.putExtra("sortKey", 0);
            DeepLinkProductListHelper.startProductListActivity(context, intent.getExtras());
        } else if (TextUtils.equals(string2, "crossCategory")) {
            String string44 = bundle.getString("activityId");
            String string45 = bundle.getString(ProductListConstant.INLET);
            String string46 = bundle.getString("tipFirst");
            String string47 = bundle.getString("tipSecond");
            Bundle bundle7 = new Bundle();
            if (!TextUtils.isEmpty(string44)) {
                bundle7.putString("activityId", string44);
            }
            if (!TextUtils.isEmpty(string45)) {
                bundle7.putString(ProductListConstant.INLET, string45);
            }
            if (!TextUtils.isEmpty(string46)) {
                bundle7.putString("tipFirst", string46);
            }
            if (!TextUtils.isEmpty(string47)) {
                bundle7.putString("tipSecond", string47);
            }
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_CROSSCATEGORY_LIST, bundle);
            return;
        } else {
            return;
        }
        intent = intent2;
        DeepLinkProductListHelper.startProductListActivity(context, intent.getExtras());
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        s(context, bundle);
        c(context);
    }
}
