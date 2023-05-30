package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.miaosha.MiaoShaPublicConstants;
import com.jingdong.common.deeplinkhelper.DeepLinkMiaoShaHelper;
import com.jingdong.common.jump.JumpNetDataProvider;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.corelib.utils.Log;
import java.util.HashMap;
import org.json.JSONObject;

@Des(des = JumpUtil.VALUE_DES_MIAOSHA_PARITY)
/* loaded from: classes19.dex */
public class JumpToMiaoShaParity extends a {
    private void s(Context context, Bundle bundle) {
        DeepLinkMiaoShaHelper.startMiaoShaParityActivity(context, bundle);
        c(context);
    }

    private void t(Bundle bundle) {
        try {
            String string = bundle.getString("isFromOpenApp");
            if ((TextUtils.isEmpty(string) || !"1".equals(string)) && !bundle.containsKey("categoryId") && !bundle.containsKey("secKillKeyTabType")) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HybridSDK.LNG, JDLocationCache.getInstance().getLocation().getLng() + "");
                jSONObject.put("lat", JDLocationCache.getInstance().getLocation().getLat() + "");
                HashMap hashMap = new HashMap();
                hashMap.put("geo", jSONObject);
                hashMap.put("categoryId", "2");
                String string2 = bundle.getString("topSkuId");
                if (!TextUtils.isEmpty(string2)) {
                    hashMap.put("topSkuId", string2);
                }
                String string3 = bundle.getString(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK);
                if (!TextUtils.isEmpty(string3)) {
                    hashMap.put(MiaoShaPublicConstants.MIAO_SHA_INNER_LINK, string3);
                }
                hashMap.put("pageId", "0");
                if (JumpNetDataProvider.getInstance().request("paritySeckillGoods", hashMap, true)) {
                    bundle.putBoolean("PreloadParityGoods", true);
                    Log.d(this.a, "start preload paritySeckillGoods");
                }
                HashMap hashMap2 = new HashMap();
                hashMap2.put("geo", jSONObject);
                if (JumpNetDataProvider.getInstance().request("paritySeckillCategory", hashMap2, true)) {
                    bundle.putBoolean("PreloadParityCategory", true);
                    Log.d(this.a, "start preload paritySeckillCategory");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        t(bundle);
        s(context, bundle);
    }
}
