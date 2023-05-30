package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkRankHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddress;
import com.jingdong.common.lbs.businessAddress.JDBusinessAddressManager;
import com.jingdong.common.lbs.http.JDLbsHttpOption;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.constant.CacheConstant;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import java.util.HashMap;
import org.json.JSONObject;

@Des(des = "seckill,miaosha")
/* loaded from: classes19.dex */
public class JumpToMiaosha extends a {
    private void s(Context context, Bundle bundle) {
        if (context != null && "com.jd.lib.jdmiaosha.activity.MiaoShaActivity".equals(context.getClass().getName())) {
            BaseActivity baseActivity = (BaseActivity) context;
            if (!baseActivity.isFinishing()) {
                baseActivity.finish();
            }
        }
        j(context, "zhangshangmiaosha", bundle);
        if (bundle != null) {
            bundle.putSerializable("source", new SourceEntity(SourceEntity.SOURCE_TYPE_HOME_MIAOSHA, ""));
            bundle.putSerializable("com.360buy:navigationDisplayFlag", -1);
            bundle.putBoolean("com.360buy:clearHistoryFlag", true);
        }
        DeepLinkMiaoShaHelper.startMiaoShaList(context, bundle);
        c(context);
    }

    private void t(Bundle bundle) {
        String str;
        try {
            boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue("seckill_flexcube_open", false);
            boolean equals = JDMobileConfig.getInstance().getConfig("JDMiaoSha", "jdseckilloptimize", "switchType").equals("1");
            String string = bundle.getString("isFromOpenApp");
            if ((TextUtils.isEmpty(string) || !"1".equals(string)) && !bundle.containsKey("categoryId") && !bundle.containsKey(MiaoShaPublicConstants.KEY_GID) && !bundle.containsKey("groupTimes") && !bundle.containsKey("groupTime") && !bundle.containsKey(MiaoShaPublicConstants.JUMP_TO)) {
                if (bundle.containsKey("secKillKeyTabType")) {
                    String string2 = bundle.getString("secKillKeyTabType");
                    if (!TextUtils.isEmpty(string2) && !"seckill".equals(string2)) {
                        return;
                    }
                }
                if (switchBooleanValue) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("activityId", "y2jaMWvCjwVfkXZPF7uUvX6z1de");
                    hashMap.put("bizCode", "seckill");
                    if (JumpNetDataProvider.getInstance().request("qryChannelNativeFloors", hashMap, true)) {
                        bundle.putBoolean("qryChannelNativeFloors", true);
                    }
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HybridSDK.LNG, JDLocationCache.getInstance().getLocation().getLng() + "");
                jSONObject.put("lat", JDLocationCache.getInstance().getLocation().getLat() + "");
                JDBusinessAddress defaultBusinessAddress = JDBusinessAddressManager.getInstance().getDefaultBusinessAddress(new JDLbsHttpOption("cc9bb5aa70482f95467114eb32567dbf"));
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(HybridSDK.LNG, defaultBusinessAddress.getLng() + "");
                    jSONObject2.put("lat", defaultBusinessAddress.getLat() + "");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put("geo", jSONObject);
                long j2 = bundle.getLong("productId", -1L);
                if (j2 == -1) {
                    str = bundle.getString("productId", "");
                } else {
                    str = j2 + "";
                }
                hashMap2.put("skuId", str);
                hashMap2.put("newBrandId", bundle.getString("newBrandId", ""));
                if (bundle.containsKey("channel")) {
                    hashMap2.put("channel", bundle.getString("channel", ""));
                }
                if (bundle.containsKey("jumpFrom")) {
                    hashMap2.put("jumpFrom", Integer.valueOf(bundle.getInt("jumpFrom", -1)));
                }
                try {
                    if (bundle.containsKey(DeepLinkCommuneHelper.PRODUCT_IMAGE)) {
                        hashMap2.put(DeepLinkCommuneHelper.PRODUCT_IMAGE, new JSONObject(bundle.getString(DeepLinkCommuneHelper.PRODUCT_IMAGE)));
                    }
                } catch (Exception unused) {
                }
                if (bundle.containsKey("ignoreSku")) {
                    hashMap2.put("ignoreSku", bundle.getString("ignoreSku", "0"));
                }
                String string3 = bundle.containsKey(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK) ? bundle.getString(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, "") : "";
                int i2 = bundle.containsKey(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX) ? bundle.getInt(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, 0) : 0;
                String string4 = bundle.containsKey("innerSkuImgUrl") ? bundle.getString("innerSkuImgUrl", "") : "";
                hashMap2.put(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, string3);
                hashMap2.put(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, Integer.valueOf(i2));
                hashMap2.put("innerSkuImgUrl", string4);
                if (JumpNetDataProvider.getInstance().request(CacheConstant.ID_MIAO_SHA, hashMap2, true)) {
                    bundle.remove(DeepLinkCommuneHelper.PRODUCT_IMAGE);
                    bundle.remove("ignoreSku");
                    bundle.putBoolean("PreloadSeckillAreaList", true);
                }
                HashMap hashMap3 = new HashMap();
                if (bundle.containsKey("topSkus")) {
                    hashMap3.put("topSkus", bundle.getString("topSkus", ""));
                }
                if (bundle.containsKey(DeepLinkRankHelper.CATE_ID)) {
                    hashMap3.put(DeepLinkRankHelper.CATE_ID, bundle.getString(DeepLinkRankHelper.CATE_ID, ""));
                }
                if (bundle.containsKey("categorys")) {
                    hashMap3.put("categorys", bundle.getString("categorys", ""));
                }
                if (bundle.containsKey("wordChain")) {
                    hashMap3.put("wordChain", bundle.getString("wordChain", ""));
                }
                hashMap3.put(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, string3);
                hashMap3.put(MiaoShaPublicConstants.MIAO_SHA_INNER_INDEX, Integer.valueOf(i2));
                hashMap3.put("innerSkuImgUrl", string4);
                hashMap3.put("skuId", str);
                hashMap3.put("geo", jSONObject);
                hashMap3.put("addressGeo", jSONObject2);
                if (JumpNetDataProvider.getInstance().request("seckillGenericFloors", hashMap3, true)) {
                    bundle.putBoolean("PreloadSeckillGenericFloor", true);
                }
                if (equals) {
                    HashMap hashMap4 = new HashMap();
                    hashMap4.put("geo", jSONObject);
                    hashMap4.put("bubId", SharedPreferencesUtil.getString("miao_home_tab_id", ""));
                    hashMap4.put("topBubId", SharedPreferencesUtil.getString("miao_home_tab_id_top_bubble", ""));
                    if (JumpNetDataProvider.getInstance().request("seckillTabImg", hashMap4, true)) {
                        bundle.putBoolean("PreloadSeckillTabImg", true);
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        t(bundle);
        s(context, bundle);
    }
}
