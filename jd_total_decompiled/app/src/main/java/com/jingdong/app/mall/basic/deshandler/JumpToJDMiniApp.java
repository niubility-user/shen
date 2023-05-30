package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.manto.launch.LaunchParam;
import com.jingdong.sdk.oklog.OKLog;

@Des(des = "vapp")
/* loaded from: classes19.dex */
public class JumpToJDMiniApp extends a {
    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        LaunchParam launchParam = new LaunchParam();
        String string = bundle.getString(BaseEvent.SCENE, "");
        if (!TextUtils.isEmpty(string)) {
            launchParam.scene = string;
        }
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
        try {
            OKLog.d("mpBetter", bundle.toString());
            launchParam.debugType = bundle.getString("vapptype", "1");
            launchParam.extrasJson = bundle.getString("param", "");
        } catch (Throwable unused) {
            launchParam.debugType = String.valueOf(bundle.getInt("vapptype", 1));
        }
        launchParam.appId = bundle.getString("appId");
        launchParam.launchPath = bundle.getString("path");
        launchParam.pageAlias = bundle.getString("pageAlias");
        com.jingdong.a.o(launchParam);
        c(context);
    }
}
