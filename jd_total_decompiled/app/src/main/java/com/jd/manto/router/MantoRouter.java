package com.jd.manto.router;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.manto.mainproc.MainProcMessage;

@Keep
/* loaded from: classes17.dex */
public class MantoRouter {
    public static final int ERROR_EXCEPTION = 202;
    public static final int ERROR_NO_APPID = 200;
    public static final int ERROR_NO_BUNDLE = 201;
    public static final String KEY_APPID = "appId";
    public static final String KEY_EVENT = "eventName";
    public static final String KEY_PARAM = "params";
    public static final String SEND_TO_MANTO = "sendMsgToManto";
    public static final String SEND_TO_MANTO_ALL = "sendMsgToMantoAll";

    public void sendMsgToManto(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry == null || routerEntry.callBackListener == null) {
            return;
        }
        try {
            MainProcMessage mainProcMessage = new MainProcMessage();
            mainProcMessage.messageName = SEND_TO_MANTO;
            if (jDJSONObject == null) {
                routerEntry.callBackListener.onError(201);
                return;
            }
            String optString = jDJSONObject.optString("appId");
            if (TextUtils.isEmpty(optString)) {
                routerEntry.callBackListener.onError(200);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("eventName", jDJSONObject.optString("eventName"));
            bundle.putString("params", jDJSONObject.optString("params"));
            mainProcMessage.data = bundle;
            com.jingdong.a.j().sendMessageToManto(optString, mainProcMessage);
            routerEntry.callBackListener.onComplete();
        } catch (Exception unused) {
            routerEntry.callBackListener.onError(202);
        }
    }

    public void sendMsgToMantoAll(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry == null || routerEntry.callBackListener == null) {
            return;
        }
        try {
            MainProcMessage mainProcMessage = new MainProcMessage();
            mainProcMessage.messageName = SEND_TO_MANTO_ALL;
            if (jDJSONObject == null) {
                routerEntry.callBackListener.onError(201);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("eventName", jDJSONObject.optString("eventName"));
            bundle.putString("params", jDJSONObject.optString("params"));
            mainProcMessage.data = bundle;
            com.jingdong.a.j().sendMessageToManto(mainProcMessage);
            routerEntry.callBackListener.onComplete();
        } catch (Exception unused) {
            routerEntry.callBackListener.onError(202);
        }
    }
}
