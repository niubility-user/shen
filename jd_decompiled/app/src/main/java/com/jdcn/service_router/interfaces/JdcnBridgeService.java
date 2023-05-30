package com.jdcn.service_router.interfaces;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public interface JdcnBridgeService {
    void serviceCall(Context context, JSONObject jSONObject, JdcnBridgeServiceCallback jdcnBridgeServiceCallback);
}
