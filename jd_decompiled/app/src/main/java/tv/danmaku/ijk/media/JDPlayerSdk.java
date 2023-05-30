package tv.danmaku.ijk.media;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jdcloud.mcdnsdk.McdnManager;
import com.jdcloud.media.common.JDTAuthUtil;
import com.jdcloud.media.common.bean.LogData;
import com.jdcloud.media.common.log.ILogCallback;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.unification.video.mta.NewVideoPlayMtaUtil;
import com.jingdong.jdma.minterface.MaInitCommonInfo;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import tv.danmaku.ijk.media.alpha.download.AlphaDownloadConfig;
import tv.danmaku.ijk.media.alpha.download.AlphaDownloadManager;
import tv.danmaku.ijk.media.cronet.CronetBridgeDefaultImpl;
import tv.danmaku.ijk.media.cronet.CronetConfigLoader;
import tv.danmaku.ijk.media.cronet.ICronetBridge;
import tv.danmaku.ijk.media.ext.auth.AuthCheck;
import tv.danmaku.ijk.media.ext.cache.VideoCacheCleanReceiver;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;
import tv.danmaku.ijk.media.ext.config.DynamicLibInfoBean;
import tv.danmaku.ijk.media.ext.config.PlayerConfigLoader;
import tv.danmaku.ijk.media.ext.dynamic.RemoteSoLoader;
import tv.danmaku.ijk.media.ext.identify.HostAppInfo;
import tv.danmaku.ijk.media.ext.identify.PlayerNetHeaderUtil;
import tv.danmaku.ijk.media.ext.mta.DefaultPlayerReport;
import tv.danmaku.ijk.media.ext.mta.PlayerReportInvoke;
import tv.danmaku.ijk.media.utils.DebugLog;
import tv.danmaku.ijk.media.utils.PlayerNetworkUtil;

/* loaded from: classes11.dex */
public class JDPlayerSdk {
    public static long DIFF_TIME = 0;
    public static final String TAG_JDPLAYER = "JDPlayerTag";
    public static String mAppId;
    private static ICronetBridge mCronetBridge;
    private static JDPlayerSdk mInstance;
    private static PlayerReportInvoke mReportInvoke;
    private Context applicationContext;
    private static final AtomicBoolean hasInit = new AtomicBoolean(false);
    public static boolean debugEnable = false;
    public static boolean dataFlowTipShow = false;
    private static boolean isForeground = false;

    /* loaded from: classes11.dex */
    public static final class JDPlayerConfig {
        private Context appContext;
        private String appId;
        private ICronetBridge cronetBridge;
        private boolean debug;
        private boolean googleChannel;
        private HostAppInfo hostAppInfo;
        private MaInitCommonInfo mtaInitInfo;
        private PlayerReportInvoke reportInvoke;
        private String vsrKey;

        /* loaded from: classes11.dex */
        public static final class Builder {
            private ICronetBridge cronetBridge;
            private boolean googleChannel;
            private HostAppInfo hostAppInfo;
            private MaInitCommonInfo mtaInitInfo;
            private PlayerReportInvoke reportInvoke;
            private String vsrKey;
            private Context appContext = null;
            private String appId = null;
            private boolean debug = false;

            public Builder appContext(Context context) {
                this.appContext = context;
                return this;
            }

            public Builder appId(String str) {
                this.appId = str;
                return this;
            }

            public JDPlayerConfig build() {
                JDPlayerConfig jDPlayerConfig = new JDPlayerConfig();
                jDPlayerConfig.appContext = this.appContext;
                jDPlayerConfig.appId = this.appId;
                jDPlayerConfig.googleChannel = this.googleChannel;
                jDPlayerConfig.vsrKey = this.vsrKey;
                jDPlayerConfig.debug = this.debug;
                jDPlayerConfig.mtaInitInfo = this.mtaInitInfo;
                jDPlayerConfig.cronetBridge = this.cronetBridge;
                jDPlayerConfig.hostAppInfo = this.hostAppInfo;
                jDPlayerConfig.reportInvoke = this.reportInvoke;
                return jDPlayerConfig;
            }

            public Builder cronetBridge(ICronetBridge iCronetBridge) {
                this.cronetBridge = iCronetBridge;
                return this;
            }

            public Builder enableDebug(boolean z) {
                this.debug = z;
                return this;
            }

            public Builder googleChannel(boolean z) {
                this.googleChannel = z;
                return this;
            }

            public Builder hostAppInfo(HostAppInfo hostAppInfo) {
                this.hostAppInfo = hostAppInfo;
                return this;
            }

            public Builder mtaInitInfo(MaInitCommonInfo maInitCommonInfo) {
                this.mtaInitInfo = maInitCommonInfo;
                return this;
            }

            public Builder reportInvoke(PlayerReportInvoke playerReportInvoke) {
                this.reportInvoke = playerReportInvoke;
                return this;
            }

            public Builder vsrKey(String str) {
                this.vsrKey = str;
                return this;
            }
        }
    }

    private JDPlayerSdk() {
    }

    public static ICronetBridge getCronetBridge() {
        if (mCronetBridge == null) {
            mCronetBridge = new CronetBridgeDefaultImpl();
        }
        return mCronetBridge;
    }

    public static synchronized JDPlayerSdk getInstance() {
        JDPlayerSdk jDPlayerSdk;
        synchronized (JDPlayerSdk.class) {
            if (mInstance == null) {
                mInstance = new JDPlayerSdk();
            }
            jDPlayerSdk = mInstance;
        }
        return jDPlayerSdk;
    }

    public static PlayerReportInvoke getPlayerReport() {
        if (mReportInvoke == null) {
            mReportInvoke = new DefaultPlayerReport();
        }
        return mReportInvoke;
    }

    public void initAlphaDownloadManager() {
        AlphaDownloadManager.getInstance().init(new AlphaDownloadConfig.Builder().appContext(this.applicationContext).builder());
    }

    public void initVsrSdk(String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            JDTAuthUtil.getInstance().init(this.applicationContext, str, new ILogCallback() { // from class: tv.danmaku.ijk.media.JDPlayerSdk.3
                {
                    JDPlayerSdk.this = this;
                }

                @Override // com.jdcloud.media.common.log.ILogCallback
                public void onLog(LogData logData) {
                    if (logData == null) {
                        return;
                    }
                    String str2 = "trace is" + logData.getTrack();
                    if (logData.getXdata() != null) {
                        for (Map.Entry entry : logData.getXdata().entrySet()) {
                            if (entry != null) {
                                String str3 = "xdata key is " + entry.getKey() + " ,value is" + entry.getValue();
                            }
                        }
                    }
                }
            });
        }
    }

    private void registerGlobalReceiver() {
        if (this.applicationContext == null) {
            return;
        }
        this.applicationContext.registerReceiver(new VideoCacheCleanReceiver(), new IntentFilter(PersonalConstants.BROADCAST_GENERAL_SETUP));
    }

    public static void release() {
        AuthCheck.getInstance().release();
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

    public void init(final JDPlayerConfig jDPlayerConfig) {
        AtomicBoolean atomicBoolean = hasInit;
        if (atomicBoolean.get()) {
            return;
        }
        if (jDPlayerConfig != null) {
            this.applicationContext = jDPlayerConfig.appContext;
            mAppId = jDPlayerConfig.appId;
            boolean z = jDPlayerConfig.debug;
            debugEnable = z;
            DebugLog.setDebug(z);
            mCronetBridge = jDPlayerConfig.cronetBridge;
            mReportInvoke = jDPlayerConfig.reportInvoke;
            if (jDPlayerConfig.hostAppInfo != null) {
                PlayerNetHeaderUtil.injectAppInfo(jDPlayerConfig.hostAppInfo);
            }
            if (jDPlayerConfig.mtaInitInfo != null) {
                NewVideoPlayMtaUtil.setCommonInfo(jDPlayerConfig.mtaInitInfo);
            }
            RemoteSoLoader.getInstance().setLoadEnable(!jDPlayerConfig.googleChannel);
            new PlayerNetworkUtil();
            registerGlobalReceiver();
            PlayerConfigLoader.getInstance().registerListener(new PlayerConfigLoader.ConfigLoaderCallback() { // from class: tv.danmaku.ijk.media.JDPlayerSdk.1
                {
                    JDPlayerSdk.this = this;
                }

                @Override // tv.danmaku.ijk.media.ext.config.PlayerConfigLoader.ConfigLoaderCallback
                public void onConfigLoad(boolean z2, boolean z3, boolean z4, boolean z5, DynamicLibInfoBean dynamicLibInfoBean) {
                    if (z2) {
                        AuthCheck.getInstance().init(JDPlayerSdk.mAppId, JDPlayerSdk.this.applicationContext.getPackageName(), jDPlayerConfig.debug);
                    }
                    if (z3 && !TextUtils.isEmpty(jDPlayerConfig.vsrKey)) {
                        JDPlayerSdk.this.initVsrSdk(jDPlayerConfig.vsrKey);
                    }
                    if (z4) {
                        new CronetConfigLoader().load();
                    }
                    if (z5) {
                        JDPlayerSdk.this.initAlphaDownloadManager();
                    }
                    if (jDPlayerConfig.hostAppInfo != null && !jDPlayerConfig.hostAppInfo.isMainProcess()) {
                        DebugLog.d("mcdn", "onConfigLoad ==== initMCDN false, not main process");
                    } else if (dynamicLibInfoBean != null && dynamicLibInfoBean.isEnable() && Build.VERSION.SDK_INT >= 23) {
                        DebugLog.d("mcdn", "onConfigLoad ==== initMCDN ");
                        McdnManager.g().a("7546a76aac9076eeb64afe50607a8cbb", dynamicLibInfoBean);
                    } else {
                        DebugLog.d("mcdn", "onConfigLoad ==== disable ");
                    }
                }
            });
            dataFlowTipShow = false;
            Context context = this.applicationContext;
            if (context != null && (context instanceof Application)) {
                ((Application) context).registerActivityLifecycleCallbacks(new JDPlayerActivityCallbacks() { // from class: tv.danmaku.ijk.media.JDPlayerSdk.2
                    int activityCount = 0;

                    {
                        JDPlayerSdk.this = this;
                    }

                    @Override // tv.danmaku.ijk.media.JDPlayerActivityCallbacks, android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStarted(@NonNull Activity activity) {
                        super.onActivityStarted(activity);
                        int i2 = this.activityCount + 1;
                        this.activityCount = i2;
                        if (i2 > 0) {
                            boolean unused = JDPlayerSdk.isForeground = true;
                        }
                    }

                    @Override // tv.danmaku.ijk.media.JDPlayerActivityCallbacks, android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityStopped(@NonNull Activity activity) {
                        super.onActivityStopped(activity);
                        PreloadManager.getInstance().removeAllPreloadTask();
                        int i2 = this.activityCount - 1;
                        this.activityCount = i2;
                        if (i2 == 0) {
                            boolean unused = JDPlayerSdk.isForeground = false;
                        }
                    }
                });
            }
            atomicBoolean.set(true);
            return;
        }
        throw new IllegalArgumentException("config can not be null");
    }

    public boolean isIsForeground() {
        return isForeground;
    }
}
