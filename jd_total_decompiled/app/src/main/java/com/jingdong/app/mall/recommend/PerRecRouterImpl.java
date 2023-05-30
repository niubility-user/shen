package com.jingdong.app.mall.recommend;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.global.PerRecEvent;
import com.jingdong.app.mall.global.b;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import de.greenrobot.event.EventBus;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class PerRecRouterImpl {
    private static JSONObject a;

    public static synchronized JSONObject getPerRecStatus() {
        synchronized (PerRecRouterImpl.class) {
            JSONObject jSONObject = a;
            if (jSONObject != null && jSONObject.has("prstate")) {
                return a;
            }
            String a2 = b.a(new File(JdSdk.getInstance().getApplicationContext().getFilesDir(), "jdPerRecState"));
            if (!"0".equals(a2) && !"1".equals(a2)) {
                a2 = "0";
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("prstate", a2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            a = jSONObject2;
            return jSONObject2;
        }
    }

    public static synchronized boolean getPerRecStatusValue() {
        boolean z;
        synchronized (PerRecRouterImpl.class) {
            z = true;
            try {
                JSONObject perRecStatus = getPerRecStatus();
                if (perRecStatus != null) {
                    String optString = perRecStatus.optString("prstate", "0");
                    if (!TextUtils.isEmpty(optString)) {
                        if (Integer.parseInt(optString.trim()) != 0) {
                            z = false;
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    public static JSONObject perRecState(IRouterParams iRouterParams) {
        JSONObject jSONObject = new JSONObject();
        if (iRouterParams == null) {
            return jSONObject;
        }
        try {
            jSONObject = new JSONObject(iRouterParams.getRouterParam());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (jSONObject.length() == 0) {
            return getPerRecStatus();
        }
        savePerRecStatus(jSONObject);
        return jSONObject;
    }

    public static synchronized void savePerRecStatus(JSONObject jSONObject) {
        synchronized (PerRecRouterImpl.class) {
            if (jSONObject != null) {
                if (jSONObject.has("prstate")) {
                    String optString = jSONObject.optString("prstate", "0");
                    if (!"0".equals(optString) && !"1".equals(optString)) {
                        optString = "0";
                    }
                    if (a == null) {
                        a = new JSONObject();
                    }
                    try {
                        a.put("prstate", optString);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    EventBus.getDefault().post(new PerRecEvent("0".equals(optString)));
                    b.b(optString, new File(JdSdk.getInstance().getApplicationContext().getFilesDir(), "jdPerRecState"));
                }
            }
        }
    }

    public void getPerRecStatusValue(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry == null || routerEntry.callBackListener == null) {
            return;
        }
        try {
            boolean perRecStatusValue = getPerRecStatusValue();
            CallBackListener callBackListener = routerEntry.callBackListener;
            if (callBackListener instanceof CallBackWithReturnListener) {
                ((CallBackWithReturnListener) callBackListener).onComplete(perRecStatusValue ? "0" : "1");
            } else {
                callBackListener.onComplete();
            }
        } catch (Exception unused) {
            routerEntry.callBackListener.onError(703);
        }
    }
}
