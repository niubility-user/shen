package com.jingdong.manto.jsapi.openmodule;

import android.content.Intent;
import android.os.Bundle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.m.d1.i;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public interface IMantoBaseModule extends IMantoSdkBase {
    public static final int ACROSS_PROCESS_SYNC_TYPE = 4;
    public static final int ACROSS_PROCESS_TYPE = 0;
    public static final String ACTION_ID = "actionId";
    public static final String ADD_EXTRAS_DATA = "mantoAddExtraDatas";
    public static final String APP_ID_KEY = "appid";
    public static final String APP_TYPE_KEY = "type";
    public static final String APP_UNIQUEID_ID_KEY = "appuniqueid";
    public static final String BUNDLE_REAL_RESULT = "bundleRealResult";
    public static final String CANCEL = "cancel";
    public static final String CARD_MODE = "isCard";
    public static final String COMPONENT_HASHCODE = "component_hashcode";
    public static final String ERROR_CODE = "errorcode";
    public static final String ERROR_CODE_CANCEL = "-1";
    public static final String ERROR_CODE_FAILED = "0";
    public static final String ERROR_CODE_SUCCESS = "1";
    public static final String EXTRAS_DATA = "mantoExtraDatas";
    public static final String FAILED = "fail";
    public static final String HANDLERESULT_WITHCALLBACK = "handleResultWithCallBack";
    public static final String HAS_NATIVE_BUFFER = "hasNativeBuffer";
    public static final int IN_PROCESS_SYNC_TYPE = 3;
    public static final int IN_PROCESS_TYPE = 1;
    public static final int LAUNCH_FOR_RESULT_TYPE = 2;
    public static final String MESSAGE = "message";
    public static final String MESSAGE_ERROR = "error";
    public static final String REQUEST_CODE_KEY = "requestCode";
    public static final String REQUEST_JSAPI_KEY = "requestJSApi";
    public static final String RESULT = "result";
    public static final String SHARE_SUPPORT_KEY = "hasShare";
    public static final String STATUS_ERROR_CODE = "errCode";
    public static final String SUCCESS = "ok";
    public static final String UPDATE_LOGIN_STATUS_KEY = "updateLoginStatus";
    public static final int VIEW_TYPE = 5;

    MantoLifecycleLisener addLifecycleLisener(String str, Bundle bundle);

    String getModuleName();

    HashMap<String, i> getNativeMethod();

    void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack);

    Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle);

    Bundle handleResult(String str, MantoCore mantoCore, Intent intent, int i2, int i3);

    void handleResultWithCallback(String str, MantoCore mantoCore, Intent intent, int i2, int i3, MantoResultCallBack mantoResultCallBack);

    Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject);
}
