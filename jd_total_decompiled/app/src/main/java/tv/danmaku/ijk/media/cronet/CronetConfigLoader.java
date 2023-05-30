package tv.danmaku.ijk.media.cronet;

import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import tv.danmaku.ijk.media.JDPlayerConstant;
import tv.danmaku.ijk.media.JDPlayerSdk;
import tv.danmaku.ijk.media.cronet.bean.QuicPolicyInfoBean;
import tv.danmaku.ijk.media.ext.mta.bean.PlayerReportInfoEntity;
import tv.danmaku.ijk.media.player.threadpool.VideoPlayerThreadManager;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class CronetConfigLoader {
    private static final HashMap<String, Class> CLASS_CACHE = new HashMap<>(1);
    private static String blackIPs;
    public static boolean quicEnable;
    private Timer requestTimer;
    private int time = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public static Class getClassForName(String str) throws ClassNotFoundException {
        HashMap<String, Class> hashMap = CLASS_CACHE;
        Class<?> cls = hashMap.get(str);
        if (cls == null && (cls = JDPlayerSdk.getInstance().getApplicationContext().getClassLoader().loadClass(str)) != null) {
            hashMap.put(str, cls);
        }
        return cls;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getQuicConfig() {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost("api.m.jd.com");
        httpSetting.setFunctionId("getPolicy");
        httpSetting.putJsonParam(PlayerReportInfoEntity.PAGE_ID, "native");
        httpSetting.putJsonParam("role", PlayerReportInfoEntity.PAGE_ID);
        httpSetting.putJsonParam("os", "android");
        httpSetting.putJsonParam("okCode", 0);
        httpSetting.putJsonParam("os_version", Build.VERSION.RELEASE);
        httpSetting.putJsonParam("app_id", "jd.main");
        httpSetting.setPost(true);
        httpSetting.setListener(new HttpGroup.OnCommonNewListener<QuicPolicyInfoBean>() { // from class: tv.danmaku.ijk.media.cronet.CronetConfigLoader.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                CronetConfigLoader.this.retry();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnJsonResponseEndLisener
            public void onEnd(HttpResponse httpResponse, QuicPolicyInfoBean quicPolicyInfoBean) {
                long j2 = 0;
                try {
                    j2 = Long.parseLong(httpResponse.getHeader("X_REQUEST_RTT"));
                    DebugLog.d("quic", "quicpro === Request rtt:" + j2);
                } catch (Exception unused) {
                }
                CronetConfigLoader.this.parseResult(quicPolicyInfoBean, j2);
                if (CronetConfigLoader.this.requestTimer != null) {
                    CronetConfigLoader.this.requestTimer.cancel();
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        this.time++;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseResult(QuicPolicyInfoBean quicPolicyInfoBean, long j2) {
        if (quicPolicyInfoBean == null || TextUtils.isEmpty(quicPolicyInfoBean.getQuic_ip_blacklist())) {
            return;
        }
        if (quicPolicyInfoBean.getUse_quic() == 0) {
            quicEnable = false;
            return;
        }
        if (!TextUtils.isEmpty(quicPolicyInfoBean.getQuic_domain())) {
            JDPlayerConstant.LSQUIC_HOST = quicPolicyInfoBean.getQuic_domain();
        }
        if (!TextUtils.isEmpty(quicPolicyInfoBean.getQuic_service_port())) {
            JDPlayerConstant.LSQUIC_PORT = quicPolicyInfoBean.getQuic_service_port();
        }
        if (quicPolicyInfoBean.getSysTime() > 0) {
            JDPlayerSdk.DIFF_TIME = (quicPolicyInfoBean.getSysTime() + j2) - System.currentTimeMillis();
            DebugLog.e("quic", "quicpro === Server Time: " + quicPolicyInfoBean.getSysTime() + ", diffTime: " + JDPlayerSdk.DIFF_TIME);
        }
        blackIPs = quicPolicyInfoBean.getQuic_ip_blacklist();
        updateQuicEnableState();
    }

    public static boolean quicEnable() {
        DebugLog.d("quic", " === quicEnable:" + quicEnable);
        return quicEnable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retry() {
        if (this.time > 2) {
            return;
        }
        if (this.requestTimer == null) {
            this.requestTimer = new Timer();
        }
        this.requestTimer.schedule(new TimerTask() { // from class: tv.danmaku.ijk.media.cronet.CronetConfigLoader.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CronetConfigLoader.this.getQuicConfig();
            }
        }, Final.FIVE_SECOND);
    }

    private void updateQuicEnableState() {
        if (JDPlayerSdk.getCronetBridge().isCronetPrepared()) {
            if (!JDPlayerSdk.getCronetBridge().loadQuicSo()) {
                quicEnable = false;
            } else if (TextUtils.isEmpty(blackIPs)) {
                quicEnable = true;
            } else {
                VideoPlayerThreadManager.addTask(new Runnable() { // from class: tv.danmaku.ijk.media.cronet.CronetConfigLoader.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            Class classForName = CronetConfigLoader.getClassForName("com.jd.QuicPro");
                            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(16);
                            boolean z = true;
                            Object invoke = classForName.getMethod("ResolveDomainIfNotInBlacklist", String.class, String.class, ByteBuffer.class).invoke(null, JDPlayerConstant.LSQUIC_HOST, CronetConfigLoader.blackIPs, allocateDirect);
                            String trim = new String(allocateDirect.array(), allocateDirect.arrayOffset(), allocateDirect.remaining()).trim();
                            if (invoke != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("ResolveDomainIfNotInBlacklist, blackIps: ");
                                sb.append(CronetConfigLoader.blackIPs);
                                sb.append(" ,ip: ");
                                sb.append(trim);
                                sb.append(" , result=");
                                sb.append(((Integer) invoke).intValue() == 0);
                                sb.toString();
                                if (((Integer) invoke).intValue() != 0) {
                                    z = false;
                                }
                                CronetConfigLoader.quicEnable = z;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            CronetConfigLoader.quicEnable = false;
                        }
                    }
                });
            }
        }
    }

    public void load() {
        try {
            getQuicConfig();
        } catch (Exception unused) {
            retry();
        }
    }
}
