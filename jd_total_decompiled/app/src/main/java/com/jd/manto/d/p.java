package com.jd.manto.d;

import android.app.Activity;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.manto.sdk.api.IMantoLightMode;

/* loaded from: classes17.dex */
public class p implements IMantoLightMode {
    @Override // com.jingdong.manto.sdk.api.IMantoLightMode
    public void onFinish(Activity activity) {
        try {
            if (ActivityManagerUtil.getScreenManager().getNumActivitiesInStack() <= 1) {
                DeepLinkCommonHelper.startActivity(activity, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
            }
        } catch (Exception unused) {
        }
    }
}
