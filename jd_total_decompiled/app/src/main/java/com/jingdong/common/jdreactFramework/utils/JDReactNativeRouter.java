package com.jingdong.common.jdreactFramework.utils;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.builder.RouterEntry;
import de.greenrobot.event.EventBus;

@Keep
/* loaded from: classes5.dex */
public class JDReactNativeRouter {
    public static final String EXTRA_EVENT_NAME = "eventName";
    public static final String EXTRA_MODULENAME = "moduleName";
    public static final String EXTRA_PARAMS = "params";
    private static final String TAG = "JDReactNativeRouter";

    public void sendEvent(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        if (routerEntry != null) {
            String optString = jDJSONObject.optString("moduleName");
            String optString2 = jDJSONObject.optString("eventName");
            String optString3 = jDJSONObject.optString("params");
            try {
                JDReactEvent jDReactEvent = new JDReactEvent(JDReactEvent.TYPE_JDREACT_EVENT_NEW);
                Bundle bundle = new Bundle();
                jDReactEvent.setBundle(bundle);
                bundle.putString("moduleName", optString);
                bundle.putString("eventName", optString2);
                bundle.putString("params", optString3);
                EventBus.getDefault().post(jDReactEvent);
                CallBackListener callBackListener = routerEntry.callBackListener;
                if (callBackListener != null) {
                    callBackListener.onComplete();
                }
            } catch (Exception e2) {
                JLog.e(TAG, "sendEvent error :" + e2.getMessage());
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 != null) {
                    callBackListener2.onError(-1);
                }
            }
        }
    }
}
