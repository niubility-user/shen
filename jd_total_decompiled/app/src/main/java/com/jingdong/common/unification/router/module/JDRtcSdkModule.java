package com.jingdong.common.unification.router.module;

import android.content.Context;
import android.os.Bundle;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builder.RouterEntry;
import com.jingdong.common.web.IRouterParams;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDRtcSdkModule implements IJDModule {
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(2);
    private static final String INIT_VIDEO_INSTANCE = "initVideoInstance";
    private static final String KEY_OBSERVER = "observer";
    private static final String START_VIDEO_CALL = "startVideoCall";
    private static final String TAG = "JDRtcSdkModule";
    private JDRtcSdkParams _JDRtcSdkParams = new JDRtcSdkParams();

    /* loaded from: classes6.dex */
    public class JDRtcSdkParams implements IRouterParams {
        private CallBackWithReturnListener _observer = null;
        private Context context;
        private JDJSONObject jsonParam;

        public JDRtcSdkParams() {
        }

        @Override // com.jingdong.common.web.IRouterParams
        public Context getContext() {
            return this.context;
        }

        @Override // com.jingdong.common.web.IRouterParams
        public String getRouterParam() {
            return this.jsonParam.toString();
        }

        @Override // com.jingdong.common.web.IRouterParams
        public void onCallBack(Object obj) {
            CallBackWithReturnListener callBackWithReturnListener = this._observer;
            if (callBackWithReturnListener != null) {
                callBackWithReturnListener.onComplete(obj);
            }
        }

        public void setContext(Context context) {
            this.context = context;
        }

        public void setJsonParam(JDJSONObject jDJSONObject) {
            this.jsonParam = jDJSONObject;
        }

        public void setObserver(CallBackWithReturnListener callBackWithReturnListener) {
            this._observer = callBackWithReturnListener;
        }
    }

    private Class getClassForName(Context context, String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class cls = hashMap.get(str);
        if (cls == null && (cls = context.getApplicationContext().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    private Method getMethod(Context context, String str, Class<?>... clsArr) throws NoSuchMethodException, ClassNotFoundException {
        return getMethod(context, "com.jingdong.common.utils.JDAvSdkUtil", str, clsArr);
    }

    private void setCallBack(JDRtcSdkParams jDRtcSdkParams, RouterEntry routerEntry) {
        jDRtcSdkParams.setObserver((CallBackWithReturnListener) routerEntry.callBackListener);
    }

    public void initVideoInstance(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        JDRtcSdkParams jDRtcSdkParams = this._JDRtcSdkParams;
        if (jDRtcSdkParams != null) {
            jDRtcSdkParams.setContext(context);
            this._JDRtcSdkParams.setJsonParam(jDJSONObject);
            try {
                Object invoke = getMethod(context, INIT_VIDEO_INSTANCE, IRouterParams.class).invoke(null, this._JDRtcSdkParams);
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                    return;
                }
                setCallBack(this._JDRtcSdkParams, routerEntry);
                ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
            } catch (Exception e2) {
                String str = "JDRtcSdkModule initVideoInstance>>>" + e2.toString();
                CallBackListener callBackListener3 = routerEntry.callBackListener;
                if (callBackListener3 != null) {
                    JDRouterUtil.callBackError(callBackListener3, 703);
                }
            }
        }
    }

    @Override // com.jingdong.common.unification.router.module.IJDModule
    public void show(Context context, JSONObject jSONObject, Bundle bundle, CallBackListener callBackListener) {
    }

    public void startVideoCall(Context context, JDJSONObject jDJSONObject, RouterEntry routerEntry) {
        CallBackListener callBackListener;
        if (jDJSONObject == null && (callBackListener = routerEntry.callBackListener) != null) {
            JDRouterUtil.callBackError(callBackListener, 703);
        }
        JDRtcSdkParams jDRtcSdkParams = this._JDRtcSdkParams;
        if (jDRtcSdkParams != null) {
            jDRtcSdkParams.setContext(context);
            this._JDRtcSdkParams.setJsonParam(jDJSONObject);
            try {
                Object invoke = getMethod(context, START_VIDEO_CALL, IRouterParams.class).invoke(null, this._JDRtcSdkParams);
                CallBackListener callBackListener2 = routerEntry.callBackListener;
                if (callBackListener2 == null || !(callBackListener2 instanceof CallBackWithReturnListener)) {
                    return;
                }
                setCallBack(this._JDRtcSdkParams, routerEntry);
                ((CallBackWithReturnListener) routerEntry.callBackListener).onComplete(invoke);
            } catch (Exception e2) {
                String str = "JDRtcSdkModule startVideoCall>>>" + e2.toString();
                CallBackListener callBackListener3 = routerEntry.callBackListener;
                if (callBackListener3 != null) {
                    JDRouterUtil.callBackError(callBackListener3, 703);
                }
            }
        }
    }

    private Method getMethod(Context context, String str, String str2, Class<?>... clsArr) throws ClassNotFoundException, NoSuchMethodException {
        Class classForName = getClassForName(context, str);
        if (classForName != null) {
            return classForName.getMethod(str2, clsArr);
        }
        return null;
    }
}
