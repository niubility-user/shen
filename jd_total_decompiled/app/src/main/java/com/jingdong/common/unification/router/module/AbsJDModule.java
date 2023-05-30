package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class AbsJDModule implements IJDModule {
    public abstract void show(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry);

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }
}
