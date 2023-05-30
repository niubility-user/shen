package com.jingdong.common.permission;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.unification.router.CallBackListener;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JdPermissionRouter {
    public void startSceneActivity(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
        LBSSceneSwitchHelper.startSceneActivity(context);
    }
}
