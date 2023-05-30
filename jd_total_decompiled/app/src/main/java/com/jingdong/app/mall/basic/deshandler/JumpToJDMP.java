package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.launch.LaunchParam;

@Des(des = "jdmp")
/* loaded from: classes19.dex */
public class JumpToJDMP extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        LaunchParam launchParam = new LaunchParam();
        boolean z = false;
        try {
            if (ActivityManagerUtil.getScreenManager().getNumActivitiesInStack() <= 1) {
                if (BaseFrameUtil.getInstance().getMainFrameActivity() == null) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        launchParam.launchProxy = z ? "1" : "0";
        String string = bundle.getString(BaseEvent.SCENE, "");
        if (!TextUtils.isEmpty(string)) {
            launchParam.scene = string;
        }
        try {
            launchParam.extrasJson = bundle.getString("param", "");
            launchParam.debugType = bundle.getString("vapptype", "1");
        } catch (Throwable unused) {
            launchParam.debugType = String.valueOf(bundle.getInt("vapptype", 1));
        }
        launchParam.appId = bundle.getString("appId");
        launchParam.launchPath = bundle.getString("path");
        launchParam.pageAlias = bundle.getString("pageAlias");
        boolean equals = TextUtils.equals("1", bundle.getString(IMantoBaseModule.CARD_MODE));
        launchParam.isCard = equals;
        if (equals) {
            try {
                launchParam.cardPreviewParam = bundle.getString("CP");
                launchParam.cardWidth = Integer.parseInt(bundle.getString("width", "0"));
                launchParam.cardHeight = Integer.parseInt(bundle.getString("height", "0"));
            } catch (Throwable unused2) {
            }
        }
        if (bundle.containsKey(IMantoBaseModule.ACTION_ID)) {
            launchParam.actionId = bundle.getString(IMantoBaseModule.ACTION_ID, "");
        }
        if (bundle.containsKey("mpMode")) {
            launchParam.mpMode = bundle.getString("mpMode", "");
        }
        if (bundle.containsKey("logo")) {
            launchParam.logo = bundle.getString("logo", "");
        }
        com.jingdong.a.o(launchParam);
        c(context);
    }
}
