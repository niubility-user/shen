package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeNetworkHelper implements JDFlutterCall {
    private static final String FETCH_DATA_FASTJSON = "fastjson";
    private static final String FETCH_DATA_KEY_FUNCTION_ID = "function_id";
    private static final String FETCH_DATA_KEY_HOST = "host";
    private static final String FETCH_DATA_KEY_HOST_BETA = "host_beta";
    private static final String FETCH_DATA_KEY_MOVIES_PLAYTYPE = "play_type";
    private static final String FETCH_DATA_KEY_PARAMS_JSON = "params_json";
    private static final String FETCH_DATA_KEY_URL = "url";
    private static final String FETCH_DATA_METHOD = "method";
    private static final String FETCH_DATA_TIME_OUT = "timeout";
    private static final String FUNCTION_UPDATE_REMAIN_TICKETS = "updateRemainTickets";
    public static final int HTTP_TIMEOUT = 30000;
    public static final String NETWORKCHANNEL = "com.jd.fetchp/fetchp'";
    private static final int NORMAL_ERROR_CODE = 0;
    private static final int NORMAL_SUCCESS_CODE = 1;
    private static final String PREFS_BETA_MODE_KEY = "jdreact_beta_mode_debug";
    private static final String TAG = "JDReactNativeNetworkModule";

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:42:0x013e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public void fetchMap(java.util.HashMap r20, com.facebook.react.bridge.Callback r21, com.facebook.react.bridge.Callback r22) {
        /*
            Method dump skipped, instructions count: 523
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeNetworkHelper.fetchMap(java.util.HashMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback):void");
    }

    public boolean isBetaHost() {
        try {
            return Configuration.isBeta();
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, JDFlutterCallResult jDFlutterCallResult, Activity activity) {
    }
}
