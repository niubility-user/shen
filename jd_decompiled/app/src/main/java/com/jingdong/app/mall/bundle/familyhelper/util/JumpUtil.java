package com.jingdong.app.mall.bundle.familyhelper.util;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.imhelper.DDParameterBuilder;
import com.jingdong.common.deeplinkhelper.imhelper.DeeplinkDongDongHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpCallbackListener;
import com.jingdong.common.jump.OpenAppJumpBuilder;

/* loaded from: classes18.dex */
public class JumpUtil {
    public static void jumpToCommon(final Activity activity, String str) {
        if (StringUtil.isEmpty(str)) {
            return;
        }
        if (str.startsWith("http")) {
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            com.jingdong.common.jump.JumpUtil.execJumpByDes("m", activity, bundle, new JumpCallbackListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.util.JumpUtil.2
                @Override // com.jingdong.common.jump.JumpCallbackListener
                public void onError() {
                }

                @Override // com.jingdong.common.jump.JumpCallbackListener
                public void onSuccess() {
                    activity.finish();
                }
            });
        } else if (str.startsWith("openapp")) {
            new OpenAppJumpBuilder.Builder(Uri.parse(str)).build().jump(activity);
        }
    }

    public static void jumpToJDReactMyFamily(final Activity activity, String str, JDJSONObject jDJSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString(JDReactConstant.IntentConstant.MODULE_NAME, "JDReactMyFamily");
        bundle.putString("appname", "JDReactMyFamily");
        bundle.putBoolean("ishidden", true);
        bundle.putBoolean("transparentenable", true);
        bundle.putBoolean("isFamilyHelper", true);
        bundle.putString("source", str);
        if (jDJSONObject == null) {
            jDJSONObject = new JDJSONObject();
        }
        bundle.putString("extParams", jDJSONObject.toString());
        com.jingdong.common.jump.JumpUtil.execJumpByDes(com.jingdong.common.jump.JumpUtil.VALUE_DES_JDREACT_COMMON, activity, bundle, new JumpCallbackListener() { // from class: com.jingdong.app.mall.bundle.familyhelper.util.JumpUtil.1
            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onError() {
            }

            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onSuccess() {
                activity.finish();
            }
        });
    }

    public static void jumpToSingleChat(Activity activity, String str, JDJSONObject jDJSONObject) {
        DeeplinkDongDongHelper deeplinkDongDongHelper = DeeplinkDongDongHelper.getInstance();
        DDParameterBuilder addToApp = DDParameterBuilder.generateWithPin().addFrom("com.jd.start.dd.familyask").addToPin(str).addToApp("im.customer");
        if (jDJSONObject != null) {
            String optString = jDJSONObject.optString("pid");
            if (!StringUtil.isEmpty(optString)) {
                addToApp.addSkuID(optString);
            }
        }
        deeplinkDongDongHelper.startDongDong(activity, addToApp.getBundle());
        activity.finish();
    }
}
