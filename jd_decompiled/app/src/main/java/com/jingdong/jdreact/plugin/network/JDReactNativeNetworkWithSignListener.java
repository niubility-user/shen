package com.jingdong.jdreact.plugin.network;

import com.jingdong.common.jdreactFramework.listener.NativeNetworkWithSignListener;
import java.util.HashSet;

/* loaded from: classes13.dex */
public class JDReactNativeNetworkWithSignListener implements NativeNetworkWithSignListener {
    private static final String FETCH_DATA_KEY_APPID = "customAppId";
    private static final String FETCH_DATA_KEY_BETA_HOST = "beta_host";
    private static final String FETCH_DATA_KEY_BETA_HOST_BACKUP = "host_beta";
    private static final String FETCH_DATA_KEY_CUSTOM_KOOKIE = "extraCookie";
    private static final String FETCH_DATA_KEY_EXTEND_PARAMS_JSON = "extendParams";
    private static final String FETCH_DATA_KEY_FUNCTION_ID = "function_id";
    private static final String FETCH_DATA_KEY_HOST = "host";
    private static final String FETCH_DATA_KEY_PARAMS_JSON = "params_json";
    private static final String FETCH_DATA_KEY_SECRETKEY = "customSecretKey";
    private static final String FETCH_DATA_METHOD = "method";
    private static final String FETCH_HEAD_KEY = "head";
    private static final String FETCH_PARAMS_KEY = "params";
    private static final String FETCH_USE_HTTPS_KEY = "use_https";
    private static final String TAG = "JDReactNetworkWithSign";
    private LoginHelper loginHelper;

    public JDReactNativeNetworkWithSignListener(LoginHelper loginHelper) {
        this(loginHelper, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01a4 A[Catch: Exception -> 0x0280, TRY_LEAVE, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01fd A[Catch: Exception -> 0x0280, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0237 A[Catch: Exception -> 0x0280, TryCatch #0 {Exception -> 0x0280, blocks: (B:81:0x019e, B:83:0x01a4, B:85:0x01aa, B:89:0x01b5, B:91:0x01bb, B:102:0x0248, B:104:0x024d, B:105:0x0250, B:107:0x0256, B:108:0x0259, B:110:0x025f, B:112:0x0266, B:113:0x026b, B:92:0x01db, B:88:0x01b2, B:93:0x01f7, B:95:0x01fd, B:97:0x0203, B:98:0x021f, B:99:0x0237), top: B:127:0x019e, inners: #1 }] */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeNetworkWithSignListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void fetch(java.util.HashMap r21, final com.jingdong.common.jdreactFramework.JDCallback r22, final com.jingdong.common.jdreactFramework.JDCallback r23) {
        /*
            Method dump skipped, instructions count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.network.JDReactNativeNetworkWithSignListener.fetch(java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback):void");
    }

    public JDReactNativeNetworkWithSignListener(LoginHelper loginHelper, HashSet<String> hashSet) {
        this.loginHelper = loginHelper;
        if (hashSet != null) {
            OKHttpUtil.setCustomVerifyHost(hashSet);
        }
    }
}
