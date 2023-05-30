package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.unification.router.CallBackListener;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public interface IJDModule {
    void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener);
}
