package tv.danmaku.ijk.media.ext.auth;

import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.example.BuildConfig;
import tv.danmaku.ijk.media.ext.auth.bean.AuthInfoBean;
import tv.danmaku.ijk.media.ext.auth.bean.AuthPlayInfo;
import tv.danmaku.ijk.media.ext.auth.bean.BaseBean;
import tv.danmaku.ijk.media.ext.report.ReportConstant;
import tv.danmaku.ijk.media.utils.PlayerStringUtils;

/* loaded from: classes11.dex */
public class AuthCheck {
    private static String AUTH_HOST = "api.m.jd.com";
    private static String AUTH_HOST_TEST = "api.m.jd.care";
    private static final int ERROR_NOE_PLAY_AUTH = 3;
    private static final int ERROR_NON_AUTH_INFO = 1;
    private static final int ERROR_NON_PLAY_LIST = 2;
    private static final String FUNCTION_AUTH = "checkAccessRights";
    private static final String FUNCTION_REPORT = "reportVideoErrorLog";
    private static final int MAX_RETRY = 3;
    private static final int PLAY_AUTH_SUCCESS = 0;
    private static final String TAG = "AuthCheck";
    private static AuthInfoBean mAuthInfoBean;
    private static AuthCheck mInstance;
    private boolean debug;
    private String mAppId;
    private Timer mCheckTimer;
    private int retryCount = 0;

    private AuthCheck() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> buildReportInfo(int i2, int i3, int i4, boolean z, AuthPlayInfo authPlayInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put("reportType", String.valueOf(i2));
        hashMap.put("appId", this.mAppId);
        hashMap.put("occurTime", PlayerStringUtils.generateReportTime());
        hashMap.put(ReportConstant.CommonInfo.PLAYER_VERSION, BuildConfig.SDK_VERSION);
        hashMap.put("os_plant", "0");
        if (!z) {
            hashMap.put("errCode", String.valueOf(i4));
            hashMap.put("ifSuccess", "1");
        } else {
            hashMap.put("ifSuccess", "0");
        }
        if (i2 == 1) {
            hashMap.put("reTry", String.valueOf(i3));
        }
        if (authPlayInfo != null) {
            hashMap.put(ReportConstant.CommonInfo.PLAYER_TYPE, authPlayInfo.getPlayerType());
            hashMap.put(ReportConstant.CommonInfo.PLAY_MODE, authPlayInfo.getPlayMode());
            hashMap.put("playtypeId", authPlayInfo.getPlayType());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCheck(final String str, final String str2) {
        this.mAppId = str;
        HttpSetting httpSetting = new HttpSetting();
        if (this.debug) {
            httpSetting.setHost(AUTH_HOST_TEST);
        } else {
            httpSetting.setHost(AUTH_HOST);
        }
        httpSetting.setFunctionId(FUNCTION_AUTH);
        httpSetting.putJsonParam("appId", str);
        httpSetting.putJsonParam("signParam", str2);
        httpSetting.setPost(true);
        httpSetting.setListener(new HttpGroup.OnCommonNewListener<BaseBean<AuthInfoBean>>() { // from class: tv.danmaku.ijk.media.ext.auth.AuthCheck.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (AuthCheck.this.retryCount < 3) {
                    if (AuthCheck.this.mCheckTimer == null) {
                        return;
                    }
                    AuthCheck.this.mCheckTimer.schedule(new TimerTask() { // from class: tv.danmaku.ijk.media.ext.auth.AuthCheck.2.1
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            AuthCheck.this.doCheck(str, str2);
                        }
                    }, Final.FIVE_SECOND);
                    return;
                }
                int errorCode = httpError.getErrorCode();
                int errorCode2 = httpError.getErrorCode();
                int i2 = errorCode2 != 1 ? errorCode2 != 4 ? errorCode2 != 11 ? errorCode : -102 : -101 : -100;
                AuthCheck authCheck = AuthCheck.this;
                authCheck.reportAuthResult(authCheck.buildReportInfo(1, authCheck.retryCount, i2, false, null));
                AuthCheck.this.releaseTimer();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnJsonResponseEndLisener
            public void onEnd(HttpResponse httpResponse, BaseBean<AuthInfoBean> baseBean) {
                AuthInfoBean authInfoBean;
                if (baseBean != null) {
                    if ("-2".equals(baseBean.bizCode)) {
                        String unused = AuthCheck.TAG;
                        AuthInfoBean unused2 = AuthCheck.mAuthInfoBean = new AuthInfoBean();
                        AuthCheck.mAuthInfoBean.setGlobalSwitch(1);
                    } else if (baseBean.isSuccess() && (authInfoBean = baseBean.data) != null) {
                        AuthInfoBean unused3 = AuthCheck.mAuthInfoBean = authInfoBean;
                        if (AuthCheck.mAuthInfoBean.getTimestamp() != 0) {
                            JDPlayerSdk.DIFF_TIME = System.currentTimeMillis() - AuthCheck.mAuthInfoBean.getTimestamp();
                        }
                    }
                }
                AuthCheck.this.releaseTimer();
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        this.retryCount++;
    }

    public static AuthCheck getInstance() {
        if (mInstance == null) {
            mInstance = new AuthCheck();
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseTimer() {
        Timer timer = this.mCheckTimer;
        if (timer != null) {
            try {
                timer.cancel();
                this.mCheckTimer = null;
                this.retryCount = 0;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportAuthResult(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(AUTH_HOST);
            httpSetting.setFunctionId(FUNCTION_REPORT);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpSetting.putJsonParam(entry.getKey(), entry.getValue());
            }
            httpSetting.setPost(true);
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    public void init(final String str, final String str2, boolean z) {
        this.debug = z;
        this.mCheckTimer = new Timer();
        try {
            doCheck(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.mCheckTimer.schedule(new TimerTask() { // from class: tv.danmaku.ijk.media.ext.auth.AuthCheck.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    AuthCheck.this.doCheck(str, str2);
                }
            }, Final.FIVE_SECOND);
        }
    }

    public boolean isValidPlayType(AuthPlayInfo authPlayInfo) {
        int i2;
        AuthInfoBean authInfoBean = mAuthInfoBean;
        if (authInfoBean == null || !authInfoBean.enableAuth()) {
            return true;
        }
        List<AuthInfoBean.PermissionData> bizList = mAuthInfoBean.getBizList();
        if (authPlayInfo == null || TextUtils.isEmpty(authPlayInfo.getPlayType())) {
            i2 = 0;
        } else if (!bizList.isEmpty() && bizList.contains(new AuthInfoBean.PermissionData(authPlayInfo.getPlayType()))) {
            reportAuthResult(buildReportInfo(0, 0, 2, true, authPlayInfo));
            return true;
        } else {
            i2 = 1;
        }
        reportAuthResult(buildReportInfo(3, 0, i2, false, authPlayInfo));
        return false;
    }

    public void release() {
        releaseTimer();
        mAuthInfoBean = null;
        this.mAppId = null;
    }
}
