package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.utils.m;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDUnionModule implements IJDModule {
    public void jumpUnion(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry != null) {
            m.g(context, (Bundle) routerEntry.mExtraData);
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }
}
