package com.jingdong.common.jdreactFramework.modules.uphone_sdk;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.JDMobiSec;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.Toast;
import cn.com.union.fido.bean.uafclient.ErrorCode;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.activities.JDReactNativeUPhoneModuleActivity;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.service.FloatMonkService;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view.TestCallBack;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AutoAudioManager;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AutoAudioManagerObserver;
import com.jingdong.common.jdreactFramework.utils.ReactActivityUtils;
import com.jingdong.common.permission.FloatPermissionManager;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.ProxyConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import jd.wjlogin_sdk.util.ReplyCode;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.json.JSONException;
import org.json.JSONObject;
import org.pjsip.pjsua2.AccountConfig;
import org.pjsip.pjsua2.AuthCredInfo;
import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import org.pjsip.pjsua2.CallOpParam;
import org.pjsip.pjsua2.CallSetting;
import org.pjsip.pjsua2.EpConfig;
import org.pjsip.pjsua2.IpChangeParam;
import org.pjsip.pjsua2.LogConfig;
import org.pjsip.pjsua2.LogEntry;
import org.pjsip.pjsua2.LogWriter;
import org.pjsip.pjsua2.SendInstantMessageParam;
import org.pjsip.pjsua2.SipHeader;
import org.pjsip.pjsua2.SipHeaderVector;
import org.pjsip.pjsua2.TlsConfig;
import org.pjsip.pjsua2.TransportConfig;
import org.pjsip.pjsua2.pj_log_decoration;
import org.pjsip.pjsua2.pjsip_status_code;
import org.pjsip.pjsua2.pjsip_transport_type_e;

/* loaded from: classes5.dex */
public class JDReactNativeUphoneModule extends ReactContextBaseJavaModule implements com.jingdong.common.permission.UphoneCallback {
    private static final String FILTER_MSG_URL = "https://api.m.jd.com/api?appid=cti_nps&functionId=nps_app_save_msg&";
    private static final String PLUGIN_BUNDLEID = "com.jd.lib.uphone";
    private static final String TAG = "UphoneModule";
    private static final String UPHONE_STATEURL = "https://api.m.jd.com/api?appid=cti_nps&functionId=nps_state_informProcessState&";
    private static UphoneAccount acc = null;
    private static UphoneEndpoint ep = null;
    private static EpConfig epConfig = null;
    public static IntentFilter intentFilter = null;
    private static MyLogWriter logWriter = null;
    public static MyBroadcastReceiver receiver = null;
    private static final String s_key = "9bcd1c175f9a5a390812a53f1dad00cb8f8f13c0118477551dc4491ce5122a54";
    private static int s_loadT;
    private static HashMap<String, String> s_mapData = new HashMap<>();
    private static UphoneCall uphoneCall;
    private final int LOG_LEVEL;
    private final String UPHONE_SERVERPROTOCOL;
    private AutoAudioManager autoAudioManager;
    ServiceConnection conn;
    public Activity currActivity;
    ReactApplicationContext currContext;
    public FloatMonkService floatService;
    private Callback hangupCallback;
    private int mShowTime;
    private String m_agentId;
    private int m_hangupT;
    private String m_presenceDn;
    private String m_requestId;
    private float m_rxLvl;
    private float m_txLvl;
    private boolean mbReturnApp;
    private TransportConfig sipTpConfig;
    private TlsConfig tlsConfig;

    /* loaded from: classes5.dex */
    public class MyBroadcastReceiver extends BroadcastReceiver {
        private String conn_name;

        private MyBroadcastReceiver() {
            JDReactNativeUphoneModule.this = r1;
            this.conn_name = "";
        }

        private boolean isNetworkChange(Context context) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = false;
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnectedOrConnecting() && !this.conn_name.equalsIgnoreCase("")) {
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (extraInfo != null && !extraInfo.equalsIgnoreCase(this.conn_name)) {
                        z = true;
                    }
                    if (extraInfo == null) {
                        extraInfo = "";
                    }
                    this.conn_name = extraInfo;
                } else if (this.conn_name.equalsIgnoreCase("")) {
                    this.conn_name = activeNetworkInfo.getExtraInfo();
                }
                if (this.conn_name == null) {
                    this.conn_name = "";
                }
            }
            return z;
        }

        private boolean isNetworkChange1(Intent intent) {
            NetworkInfo networkInfo;
            return "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && (networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo")) != null && NetworkInfo.State.CONNECTED == networkInfo.getState() && networkInfo.isAvailable() && (networkInfo.getType() == 1 || networkInfo.getType() == 0);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isNetworkChange1(intent)) {
                JDReactNativeUphoneModule.this.notifyChangeNetwork();
            }
        }
    }

    /* loaded from: classes5.dex */
    public class MyLogWriter extends LogWriter {
        MyLogWriter() {
            JDReactNativeUphoneModule.this = r1;
        }

        @Override // org.pjsip.pjsua2.LogWriter
        public void write(LogEntry logEntry) {
            System.out.println(logEntry.getMsg());
        }
    }

    /* loaded from: classes5.dex */
    public enum UPhoneCallState {
        StateNone,
        StateCallReg,
        StateCallunReg,
        StateCallInit,
        StateCallConfirm,
        StateCallTalk,
        StateCallHangup
    }

    /* loaded from: classes5.dex */
    public enum UPhoneConnectState {
        StateNull,
        StateUIInit,
        StateUISDKInit,
        StateUIEnd,
        StateTlsConnected,
        StateTlsDisconnected,
        StateNetwork
    }

    /* loaded from: classes5.dex */
    public enum UPhoneStateType {
        StateConnect,
        StateCall
    }

    public JDReactNativeUphoneModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.sipTpConfig = null;
        this.tlsConfig = null;
        this.m_requestId = "";
        this.m_agentId = "";
        this.m_presenceDn = "";
        this.m_rxLvl = 0.0f;
        this.m_txLvl = 0.0f;
        this.LOG_LEVEL = 6;
        this.UPHONE_SERVERPROTOCOL = "transport=tls";
        this.mbReturnApp = false;
        this.m_hangupT = 0;
        this.conn = new ServiceConnection() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.1
            {
                JDReactNativeUphoneModule.this = this;
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                System.out.println("jdsip onServiceConnected");
                JDReactNativeUphoneModule.this.floatService = ((FloatMonkService.MsgBinder) iBinder).getService();
                JDReactNativeUphoneModule jDReactNativeUphoneModule = JDReactNativeUphoneModule.this;
                jDReactNativeUphoneModule.floatService.show(jDReactNativeUphoneModule.mShowTime);
                Activity activity = JDReactNativeUphoneModule.this.currActivity;
                if (activity != null) {
                    activity.moveTaskToBack(true);
                    if (JDReactNativeUphoneModule.this.mbReturnApp) {
                        JDReactNativeUphoneModule.this.mbReturnApp = false;
                        ReactActivityUtils.startOrShowActivityForPackage();
                    }
                }
                JDReactNativeUphoneModule.this.floatService.setOnProgressListener(new TestCallBack() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.1.1
                    {
                        AnonymousClass1.this = this;
                    }

                    @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view.TestCallBack
                    public void click() {
                        JDReactNativeUphoneModule jDReactNativeUphoneModule2 = JDReactNativeUphoneModule.this;
                        if (jDReactNativeUphoneModule2.floatService != null) {
                            jDReactNativeUphoneModule2.currContext.unbindService(jDReactNativeUphoneModule2.conn);
                            JDReactNativeUphoneModule.this.floatService = null;
                            ReactActivityUtils.showDReactUPhoneModuleActivityForRN();
                        }
                    }
                });
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                JDReactNativeUphoneModule.this.floatService = null;
            }
        };
        this.autoAudioManager = null;
        this.currContext = reactApplicationContext;
        this.floatService = null;
        this.currActivity = null;
    }

    private void changeToHeadset() {
        ((AudioManager) getReactApplicationContext().getSystemService("audio")).setSpeakerphoneOn(false);
    }

    private void changeToReceiver() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= 11) {
            audioManager.setMode(3);
        } else {
            audioManager.setMode(2);
        }
    }

    private void changeToSpeaker() {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        audioManager.setMode(0);
        audioManager.setSpeakerphoneOn(true);
    }

    public static String encrypt32(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i2 = b & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private String hmacSha(String str, String str2, String str3) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), str3);
            Mac mac = Mac.getInstance(str3);
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(str2.getBytes("UTF-8"));
            byte[] bArr = {48, ReplyCode.reply0x31, ReplyCode.reply0x32, 51, ReplyCode.reply0x34, ReplyCode.reply0x35, ReplyCode.reply0x36, ReplyCode.reply0x37, ReplyCode.reply0x38, ReplyCode.reply0x39, 97, 98, 99, ReplyCode.reply0x64, 101, 102};
            byte[] bArr2 = new byte[doFinal.length * 2];
            for (int i2 = 0; i2 < doFinal.length; i2++) {
                int i3 = doFinal[i2] & 255;
                int i4 = i2 * 2;
                bArr2[i4] = bArr[i3 >>> 4];
                bArr2[i4 + 1] = bArr[i3 & 15];
            }
            return new String(bArr2);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    private boolean isAudioNewOpen() {
        boolean z = false;
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "uphoneAudioNew", JshopConst.JSKEY_CATE_OPEN);
            if (!TextUtils.isEmpty(config)) {
                if (TextUtils.equals("1", config)) {
                    z = true;
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        OKLog.d(TAG, "isAudioNewOpen : " + z);
        return z;
    }

    public boolean loadDownloadSo() {
        if (s_loadT > 0) {
            return true;
        }
        String str = this.currContext.getFilesDir().getPath() + "/aura/" + PLUGIN_BUNDLEID + "/package_1/lib/libuphonesdk.so";
        System.out.println("hy_" + str);
        System.out.printf("hy_6", new Object[0]);
        File file = new File(str);
        if (file.exists()) {
            System.out.printf("hy_7", new Object[0]);
            System.out.printf("hy_file_size:%d", Integer.valueOf((int) (file.length() / 1024)));
            System.out.println("hy_startload:" + str);
            try {
                System.load(str);
            } catch (UnsatisfiedLinkError e2) {
                String str2 = "Pjsua2:initNatives " + e2.getMessage();
            }
            s_loadT++;
            return true;
        }
        return false;
    }

    private void readDirSo(String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            System.out.println("hy_Directory of " + str);
            String[] list = file.list();
            for (int i2 = 0; i2 < list.length; i2++) {
                if (new File(str + "/" + list[i2]).isDirectory()) {
                    System.out.println(list[i2] + " is a directory_hy_");
                } else {
                    System.out.println(list[i2] + " is a file_hy_");
                }
            }
            return;
        }
        System.out.println(str + " is not a directory_hy_");
    }

    public boolean realInitModule() {
        try {
            System.out.printf("hy_8", new Object[0]);
            if (ep != null) {
                return false;
            }
            ep = new UphoneEndpoint();
            epConfig = new EpConfig();
            UphoneEndpoint uphoneEndpoint = ep;
            uphoneEndpoint.backer = this;
            uphoneEndpoint.libCreate();
            epConfig.getLogConfig().setLevel(6L);
            epConfig.getLogConfig().setConsoleLevel(6L);
            LogConfig logConfig = epConfig.getLogConfig();
            MyLogWriter myLogWriter = new MyLogWriter();
            logWriter = myLogWriter;
            logConfig.setWriter(myLogWriter);
            logConfig.setDecor(logConfig.getDecor() & ((pj_log_decoration.PJ_LOG_HAS_CR.swigValue() | pj_log_decoration.PJ_LOG_HAS_NEWLINE.swigValue()) ^ (-1)));
            ep.libInit(epConfig);
            this.sipTpConfig = new TransportConfig();
            TlsConfig tlsConfig = new TlsConfig();
            this.tlsConfig = tlsConfig;
            this.sipTpConfig.setTlsConfig(tlsConfig);
            ep.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TLS, this.sipTpConfig);
            if (receiver == null) {
                receiver = new MyBroadcastReceiver();
                intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                getReactApplicationContext().registerReceiver(receiver, intentFilter);
            }
            if (isAudioNewOpen() && this.autoAudioManager == null) {
                this.autoAudioManager = new AutoAudioManager(getReactApplicationContext(), new AutoAudioManagerObserver() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.2
                    {
                        JDReactNativeUphoneModule.this = this;
                    }

                    @Override // com.jingdong.common.jdreactFramework.modules.uphone_sdk.audio.AutoAudioManagerObserver
                    public void onDeviceChange(boolean z) {
                    }
                });
                return true;
            }
            return true;
        } catch (Exception e2) {
            System.out.println(e2);
            return false;
        }
    }

    private void setSpeakerPhoneOn(boolean z) {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        if (z) {
            audioManager.setSpeakerphoneOn(true);
            audioManager.setMode(0);
            audioManager.setStreamVolume(3, audioManager.getStreamVolume(3), 0);
            return;
        }
        audioManager.setSpeakerphoneOn(false);
        if (Build.VERSION.SDK_INT >= 21) {
            audioManager.setMode(3);
            audioManager.setStreamVolume(0, audioManager.getStreamMaxVolume(0), 0);
            return;
        }
        audioManager.setMode(2);
        audioManager.setStreamVolume(0, audioManager.getStreamMaxVolume(0), 0);
    }

    @ReactMethod
    public void applyFloatPermission(Callback callback) {
        this.currActivity = ReactActivityUtils.getUMActivity();
        Bundle bundle = new Bundle();
        bundle.putString("className", getClass().getName());
        bundle.putString("methodName", "applyFloatPermission");
        bundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
        if ((this.currActivity != null ? FloatPermissionManager.getInstance().applyFloatWindow(this.currActivity, "\u4eac\u4e1c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u4e3a\u60a8\u63d0\u4f9b\u66f4\u4f18\u7684\u8bed\u97f3\u901a\u8bdd\u4f53\u9a8c\u3002", bundle, this) : false) || Build.VERSION.SDK_INT < 23) {
            callback.invoke(Boolean.TRUE, "successed");
        } else {
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void bluetoothStart() {
        if (OKLog.D) {
            OKLog.d(TAG, "bluetoothStart begin isAudioNewOpen " + isAudioNewOpen() + " , getUMActivity : " + ReactActivityUtils.getUMActivity());
        }
        if (isAudioNewOpen() && ReactActivityUtils.getUMActivity() != null) {
            getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.7
                {
                    JDReactNativeUphoneModule.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (OKLog.D) {
                        OKLog.d(JDReactNativeUphoneModule.TAG, "bluetoothStart autoAudioManager " + JDReactNativeUphoneModule.this.autoAudioManager);
                    }
                    if (JDReactNativeUphoneModule.this.autoAudioManager != null) {
                        JDReactNativeUphoneModule.this.autoAudioManager.bluetoothStart();
                    }
                }
            });
        }
    }

    @ReactMethod
    public void checkFloatPermission(Callback callback) {
        JDReactNativeUPhoneModuleActivity uMActivity = ReactActivityUtils.getUMActivity();
        this.currActivity = uMActivity;
        if ((uMActivity != null ? FloatPermissionManager.getInstance().checkPermission(this.currActivity) : false) || Build.VERSION.SDK_INT < 24) {
            callback.invoke(Boolean.TRUE, "successed");
        } else {
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    public void clientStateReport(String str, int i2, int i3, String str2) {
        clientStateReportStr(String.format("{\"requstID\":\"%s\",\"stateType\":\"%d\", \"state\":\"%d\",\"event\":\"%s\",\"informTime\":\"%d\"}", str, Integer.valueOf(i2), Integer.valueOf(i3), str2, Long.valueOf(System.currentTimeMillis())));
    }

    public void clientStateReportStr(final String str) {
        new Thread() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.8
            {
                JDReactNativeUphoneModule.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v10 */
            /* JADX WARN: Type inference failed for: r0v12 */
            /* JADX WARN: Type inference failed for: r0v13, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r0v14 */
            /* JADX WARN: Type inference failed for: r0v15 */
            /* JADX WARN: Type inference failed for: r0v16 */
            /* JADX WARN: Type inference failed for: r0v17, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r0v19, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r0v6 */
            /* JADX WARN: Type inference failed for: r0v8 */
            /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.StringBuilder] */
            /* JADX WARN: Type inference failed for: r3v12 */
            /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r3v4 */
            /* JADX WARN: Type inference failed for: r3v8, types: [java.io.BufferedReader] */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Throwable th;
                BufferedReader bufferedReader;
                IOException e2;
                ProtocolException e3;
                MalformedURLException e4;
                long currentTimeMillis = System.currentTimeMillis();
                ?? format = String.format("%sbody=%s&t=%d&sign=%s", JDReactNativeUphoneModule.UPHONE_STATEURL, str, Long.valueOf(currentTimeMillis), JDReactNativeUphoneModule.this.hmacSha256(JDMobiSec.n1(JDReactNativeUphoneModule.s_key), String.format("cti_nps&%s&nps_state_informProcessState&%d", str, Long.valueOf(currentTimeMillis))));
                PrintStream printStream = System.out;
                ?? sb = new StringBuilder();
                ?? r3 = "jdsip_url:";
                sb.append("jdsip_url:");
                sb.append(format);
                printStream.println(sb.toString());
                try {
                    try {
                        format = (HttpURLConnection) new URL(format).openConnection();
                        try {
                            format.setRequestMethod("GET");
                            format.setConnectTimeout(5000);
                            format.setReadTimeout(5000);
                            bufferedReader = new BufferedReader(new InputStreamReader(format.getInputStream()));
                        } catch (MalformedURLException e5) {
                            bufferedReader = null;
                            e4 = e5;
                        } catch (ProtocolException e6) {
                            bufferedReader = null;
                            e3 = e6;
                        } catch (IOException e7) {
                            bufferedReader = null;
                            e2 = e7;
                        } catch (Throwable th2) {
                            r3 = 0;
                            th = th2;
                            if (r3 != 0) {
                                try {
                                    r3.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (format != 0) {
                                format.disconnect();
                            }
                            throw th;
                        }
                    } catch (MalformedURLException e9) {
                        bufferedReader = null;
                        e4 = e9;
                        format = 0;
                    } catch (ProtocolException e10) {
                        bufferedReader = null;
                        e3 = e10;
                        format = 0;
                    } catch (IOException e11) {
                        bufferedReader = null;
                        e2 = e11;
                        format = 0;
                    } catch (Throwable th3) {
                        r3 = 0;
                        th = th3;
                        format = 0;
                    }
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb2.append(readLine);
                        }
                        JDReactNativeUphoneModule.this.show(sb2.toString());
                        try {
                            bufferedReader.close();
                        } catch (IOException e12) {
                            e12.printStackTrace();
                        }
                        if (format == 0) {
                            return;
                        }
                    } catch (MalformedURLException e13) {
                        e4 = e13;
                        e4.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e14) {
                                e14.printStackTrace();
                            }
                        }
                        if (format == 0) {
                            return;
                        }
                        format.disconnect();
                    } catch (ProtocolException e15) {
                        e3 = e15;
                        e3.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e16) {
                                e16.printStackTrace();
                            }
                        }
                        if (format == 0) {
                            return;
                        }
                        format.disconnect();
                    } catch (IOException e17) {
                        e2 = e17;
                        e2.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e18) {
                                e18.printStackTrace();
                            }
                        }
                        if (format == 0) {
                            return;
                        }
                        format.disconnect();
                    }
                    format.disconnect();
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        }.start();
    }

    @ReactMethod
    public void closeUMGuardActivity() {
        ReactActivityUtils.closeUMGuardActivity();
    }

    @ReactMethod
    public void closeUPhoneView() {
        if (ReactActivityUtils.getUMActivity() != null) {
            if (this.floatService != null) {
                this.currContext.unbindService(this.conn);
                this.floatService = null;
            }
            ReactActivityUtils.getUMActivity().finish();
            System.out.println("jdsip uphone activity finish");
        }
    }

    @ReactMethod
    public void destoryRNmodule(Callback callback) {
        try {
            UphoneAccount uphoneAccount = acc;
            if (uphoneAccount != null) {
                uphoneAccount.backer = null;
                uphoneAccount.delete();
            }
            acc = null;
            UphoneEndpoint uphoneEndpoint = ep;
            uphoneEndpoint.backer = null;
            uphoneEndpoint.libDestroy();
            ep.delete();
            ep = null;
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    public void downUphone() {
        System.out.println("hy_aura download start!");
        System.out.printf("hy_9", new Object[0]);
        ((IAuraInstallManager) AuraServiceLoader.get(this.currContext, IAuraInstallManager.class)).startInstall(this.currContext, new AuraInstallRequest.Builder().setBundleName(PLUGIN_BUNDLEID).setDownloadType(1).addOnSuccessListener(new AuraInstallRequest.IOnSuccessListener() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.11
            {
                JDReactNativeUphoneModule.this = this;
            }

            @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnSuccessListener
            public void onSuccess() {
                System.out.println("hy_aura download success!");
                System.out.printf("hy_10", new Object[0]);
                if (AuraConfig.isBundlePrepered(JDReactNativeUphoneModule.PLUGIN_BUNDLEID)) {
                    System.out.println("AuraInstallRequest success!");
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("name", "done_init_module");
                    if (JDReactNativeUphoneModule.this.loadDownloadSo() && JDReactNativeUphoneModule.this.realInitModule()) {
                        createMap.putString("result", "successed");
                    } else {
                        createMap.putString("result", JDReactConstant.FAILED);
                    }
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) JDReactNativeUphoneModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneCommNotify", createMap);
                }
            }
        }).addOnFailerListener(new AuraInstallRequest.IOnFailerListener() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.10
            {
                JDReactNativeUphoneModule.this = this;
            }

            @Override // com.jingdong.aura.provided.api.AuraInstallRequest.IOnFailerListener
            public void onFailure(Exception exc) {
                System.out.println("hy_aura down failed");
                System.out.println(exc);
                exc.printStackTrace();
                WritableMap createMap = Arguments.createMap();
                createMap.putString("name", "done_init_module");
                createMap.putString("result", JDReactConstant.FAILED);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) JDReactNativeUphoneModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneCommNotify", createMap);
            }
        }).build());
    }

    @ReactMethod
    public void filter_message(final String str, final String str2, Callback callback) {
        new Thread() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.6
            {
                JDReactNativeUphoneModule.this = this;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v24, types: [java.io.PrintStream] */
            /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.StringBuilder] */
            /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r6v12 */
            /* JADX WARN: Type inference failed for: r6v13 */
            /* JADX WARN: Type inference failed for: r6v14 */
            /* JADX WARN: Type inference failed for: r6v15 */
            /* JADX WARN: Type inference failed for: r6v17 */
            /* JADX WARN: Type inference failed for: r6v18 */
            /* JADX WARN: Type inference failed for: r6v19 */
            /* JADX WARN: Type inference failed for: r6v20, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r6v22, types: [java.net.HttpURLConnection] */
            /* JADX WARN: Type inference failed for: r7v19, types: [java.lang.StringBuilder, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r7v20 */
            /* JADX WARN: Type inference failed for: r7v21 */
            /* JADX WARN: Type inference failed for: r7v23, types: [org.json.JSONObject] */
            /* JADX WARN: Type inference failed for: r7v27, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.StringBuilder] */
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Throwable th;
                HttpURLConnection httpURLConnection;
                String str3 = new String(Base64.decode(str2, 0));
                long currentTimeMillis = System.currentTimeMillis();
                String format = String.format("{\"sessionId\":\"%s\",\"customerId\":\"%s\", \"agentId\":\"%s\", \"direction\":\"0\",\"msgType\":\"1\",\"msgContent\":\"%s\"}", JDReactNativeUphoneModule.this.m_requestId, str3, JDReactNativeUphoneModule.this.m_agentId, str, Long.valueOf(currentTimeMillis));
                ?? format2 = String.format("%sbody=%s&t=%d&sign=%s", JDReactNativeUphoneModule.FILTER_MSG_URL, format, Long.valueOf(currentTimeMillis), JDReactNativeUphoneModule.this.hmacSha256(JDMobiSec.n1(JDReactNativeUphoneModule.s_key), String.format("cti_nps&%s&nps_app_save_msg&%d", format, Long.valueOf(currentTimeMillis))));
                System.out.println("jdsip_url:" + format2);
                BufferedReader bufferedReader = 0;
                try {
                    try {
                        format2 = (HttpURLConnection) new URL(format2).openConnection();
                        try {
                            format2.setRequestMethod("GET");
                            format2.setConnectTimeout(5000);
                            format2.setReadTimeout(5000);
                            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(format2.getInputStream()));
                            try {
                                bufferedReader = new StringBuilder();
                                while (true) {
                                    String readLine = bufferedReader2.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    bufferedReader.append(readLine);
                                }
                                System.out.println("jdsip_" + bufferedReader);
                                WritableMap createMap = Arguments.createMap();
                                try {
                                    bufferedReader = new JSONObject(bufferedReader.toString()).getJSONObject("result");
                                    String string = bufferedReader.getString(RemoteMessageConst.MessageBody.MSG_CONTENT);
                                    String string2 = bufferedReader.getString("messageId");
                                    if (string != null && string2 != null) {
                                        String string3 = bufferedReader.getString("encodeMsgContent");
                                        if (string3 == null) {
                                            string3 = string;
                                        }
                                        createMap.putString("messageText", string);
                                        createMap.putString("messageId", string2);
                                        createMap.putString("encodemessageText", string3);
                                        createMap.putString("result", "sucess");
                                        bufferedReader = "jdsip_UPhoneFilter_:" + string;
                                        System.out.println(bufferedReader);
                                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) JDReactNativeUphoneModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneFilter", createMap);
                                    }
                                } catch (JSONException unused) {
                                }
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                if (format2 == 0) {
                                    return;
                                }
                            } catch (MalformedURLException e3) {
                                e = e3;
                                bufferedReader = bufferedReader2;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (format2 == 0) {
                                    return;
                                }
                                format2.disconnect();
                            } catch (ProtocolException e5) {
                                e = e5;
                                bufferedReader = bufferedReader2;
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (format2 == 0) {
                                    return;
                                }
                                format2.disconnect();
                            } catch (IOException e7) {
                                e = e7;
                                bufferedReader = bufferedReader2;
                                e.printStackTrace();
                                WritableMap createMap2 = Arguments.createMap();
                                createMap2.putString("messageText", str);
                                createMap2.putString("result", "fail");
                                ((DeviceEventManagerModule.RCTDeviceEventEmitter) JDReactNativeUphoneModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneFilter", createMap2);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e8) {
                                        e8.printStackTrace();
                                    }
                                }
                                if (format2 == 0) {
                                    return;
                                }
                                format2.disconnect();
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedReader = bufferedReader2;
                                httpURLConnection = format2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e9) {
                                        e9.printStackTrace();
                                    }
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } catch (MalformedURLException e10) {
                            e = e10;
                        } catch (ProtocolException e11) {
                            e = e11;
                        } catch (IOException e12) {
                            e = e12;
                        }
                    } catch (MalformedURLException e13) {
                        e = e13;
                        format2 = 0;
                    } catch (ProtocolException e14) {
                        e = e14;
                        format2 = 0;
                    } catch (IOException e15) {
                        e = e15;
                        format2 = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection = null;
                    }
                    format2.disconnect();
                } catch (Throwable th4) {
                    th = th4;
                    httpURLConnection = format2;
                }
            }
        }.start();
        callback.invoke(Boolean.TRUE, "successed");
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDRNUphoneModule";
    }

    public void hangup_callback(String str, WritableMap writableMap) {
        if (uphoneCall != null) {
            uphoneCall = null;
        }
        if (isAudioNewOpen() && this.autoAudioManager != null) {
            getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.5
                {
                    JDReactNativeUphoneModule.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (JDReactNativeUphoneModule.this.autoAudioManager != null) {
                        JDReactNativeUphoneModule.this.autoAudioManager.stop();
                    }
                }
            });
        } else {
            changeToReceiver();
        }
        if (this.m_hangupT <= 4) {
            this.m_hangupT = 6;
            clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallHangup.ordinal(), "CallHangup");
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", writableMap);
    }

    @ReactMethod
    public void hideFloatWindow() {
        if (ReactActivityUtils.getUMActivity() == null) {
            System.out.println("jdsip hideFloatWindow 1");
        } else if (this.floatService == null) {
        } else {
            System.out.println("jdsip hideFloatWindow 3");
            this.currContext.unbindService(this.conn);
            System.out.println("jdsip hideFloatWindow 4");
            this.floatService = null;
        }
    }

    @ReactMethod
    public void hideHomeView() {
        Activity activity = this.currActivity;
        if (activity == null) {
            return;
        }
        activity.moveTaskToBack(true);
    }

    public String hmacSha256(String str, String str2) {
        return hmacSha(str, str2, "HmacSHA256");
    }

    public void holdCall() {
        try {
            uphoneCall.setHold(new CallOpParam(true));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactMethod
    public void initRNmodule(String str, Callback callback) {
        this.currActivity = ReactActivityUtils.getUMActivity();
        JDReactNativeUPhoneModuleActivity.setJdReactNativeUphoneModule(this);
        this.m_requestId = str;
        System.out.printf("hy_1", new Object[0]);
        if (AuraConfig.isBundlePrepered(PLUGIN_BUNDLEID)) {
            System.out.printf("hy_2", new Object[0]);
            if (loadDownloadSo() && realInitModule()) {
                System.out.printf("hy_3", new Object[0]);
                callback.invoke(Boolean.TRUE, "successed");
                return;
            }
            System.out.printf("hy_4", new Object[0]);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
            return;
        }
        downUphone();
        callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
    }

    public void invite_callback(String str, WritableMap writableMap) {
        if (str.equals("connecting")) {
            if (this.m_hangupT == 0) {
                this.m_hangupT = 1;
                clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallInit.ordinal(), "Calling");
            }
        } else if (str.equals("connected")) {
            if (this.m_hangupT < 2) {
                this.m_hangupT = 2;
                clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallConfirm.ordinal(), "CallConfirm");
            }
            if (isAudioNewOpen() && this.autoAudioManager != null) {
                getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.3
                    {
                        JDReactNativeUphoneModule.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (JDReactNativeUphoneModule.this.autoAudioManager != null) {
                            JDReactNativeUphoneModule.this.autoAudioManager.start(false);
                        }
                    }
                });
            } else {
                changeToReceiver();
            }
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", writableMap);
    }

    @Override // com.jingdong.common.permission.UphoneCallback
    public void invoke(boolean z, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", "ApplyPermission");
        if (z) {
            createMap.putString("result", "successed");
        } else {
            createMap.putString("result", JDReactConstant.FAILED);
        }
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneCommNotify", createMap);
    }

    @ReactMethod
    public void isNavigationBarExist(Callback callback) {
        JDReactNativeUPhoneModuleActivity uMActivity = ReactActivityUtils.getUMActivity();
        ViewGroup viewGroup = (ViewGroup) uMActivity.getWindow().getDecorView();
        if (viewGroup != null) {
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                viewGroup.getChildAt(i2).getContext().getPackageName();
                if (viewGroup.getChildAt(i2).getId() != -1 && "navigationBarBackground".equals(uMActivity.getResources().getResourceEntryName(viewGroup.getChildAt(i2).getId()))) {
                    callback.invoke(Boolean.TRUE, "successed");
                    return;
                }
            }
        }
        callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
    }

    public boolean isSpeakerphoneOn() {
        AutoAudioManager autoAudioManager = this.autoAudioManager;
        if (autoAudioManager != null) {
            return autoAudioManager.isSpeakerOn();
        }
        return false;
    }

    @ReactMethod
    public void jdsip_dtmf(String str, Callback callback) {
        try {
            UphoneCall uphoneCall2 = uphoneCall;
            if (uphoneCall2 != null) {
                uphoneCall2.dialDtmf(str);
            }
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactMethod
    public void jdsip_hangup(Callback callback) {
        this.hangupCallback = callback;
        if (isAudioNewOpen() && this.autoAudioManager != null) {
            getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.4
                {
                    JDReactNativeUphoneModule.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (JDReactNativeUphoneModule.this.autoAudioManager != null) {
                        JDReactNativeUphoneModule.this.autoAudioManager.stop();
                    }
                }
            });
        } else {
            changeToReceiver();
        }
        if (this.m_hangupT <= 4) {
            this.m_hangupT = 6;
            clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallHangup.ordinal(), "CallHangup");
        }
        if (uphoneCall != null) {
            CallOpParam callOpParam = new CallOpParam();
            callOpParam.setStatusCode(pjsip_status_code.PJSIP_SC_DECLINE);
            try {
                uphoneCall.hangup(callOpParam);
                uphoneCall.delete();
                UphoneCall uphoneCall2 = uphoneCall;
                uphoneCall2.backer = null;
                uphoneCall2.m_ep = null;
                uphoneCall = null;
            } catch (Exception unused) {
                UphoneCall uphoneCall3 = uphoneCall;
                if (uphoneCall3 != null) {
                    uphoneCall3.backer = null;
                    uphoneCall3.m_ep = null;
                    uphoneCall3.delete();
                    uphoneCall = null;
                }
            }
        }
    }

    @ReactMethod
    public void jdsip_initandregister(ReadableMap readableMap, Callback callback) {
        try {
            String str = "sip:" + readableMap.getString("caller") + DYConstants.DY_REGEX_AT + readableMap.getString("domain") + ";transport=tls";
            String str2 = "sip:" + readableMap.getString("caller") + DYConstants.DY_REGEX_AT + readableMap.getString("domain") + ";transport=tls";
            AccountConfig accountConfig = new AccountConfig();
            accountConfig.setIdUri(str);
            accountConfig.getRegConfig().setRegistrarUri(str);
            String encrypt32 = encrypt32(String.format("SQ2019@%s", readableMap.getString("requestID")));
            SipHeaderVector sipHeaderVector = new SipHeaderVector();
            SipHeader sipHeader = new SipHeader();
            sipHeader.setHName("X-JDCC-Token");
            sipHeader.setHValue(readableMap.getString("requestID"));
            sipHeaderVector.add(sipHeader);
            accountConfig.getRegConfig().setHeaders(sipHeaderVector);
            accountConfig.getSipConfig().getAuthCreds().add(new AuthCredInfo("digest", ProxyConfig.MATCH_ALL_SCHEMES, readableMap.getString("caller"), 0, encrypt32));
            UphoneAccount uphoneAccount = new UphoneAccount();
            acc = uphoneAccount;
            try {
                uphoneAccount.backer = this;
                uphoneAccount.create(accountConfig);
                ep.libStart();
                System.out.println("zhy:not use gsm");
                ep.codecSetPriority("PCMA/8000", ErrorCode.UNKNOWN);
                System.out.println("zhy:not use pcma");
                ep.codecSetPriority("PCMU/8000", (short) 0);
                System.out.println("zhy:not use pcmu");
                callback.invoke(Boolean.TRUE, "successed");
            } catch (Exception e2) {
                e = e2;
                System.out.println(e);
                callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    @ReactMethod
    public void jdsip_makecall(ReadableMap readableMap, Callback callback) {
        String string = readableMap.getString("routeId");
        if (string == null) {
            string = "";
        }
        readableMap.getString("dn");
        String string2 = readableMap.getString("domain");
        if (string2 == null) {
            string2 = "";
        }
        String string3 = readableMap.getString("token");
        if (string3 == null) {
            string3 = "";
        }
        String string4 = readableMap.getString("skillId");
        if (string4 == null) {
            string4 = "";
        }
        String string5 = readableMap.getString("extendData");
        String str = string5 != null ? string5 : "";
        readableMap.getString("calltype");
        readableMap.getString("dn");
        String str2 = "sip:" + string + DYConstants.DY_REGEX_AT + string2 + ";transport=tls";
        System.out.println(str2);
        UphoneCall uphoneCall2 = new UphoneCall(acc, -1, ep);
        uphoneCall = uphoneCall2;
        if (uphoneCall2 == null) {
            return;
        }
        uphoneCall2.backer = this;
        CallOpParam callOpParam = new CallOpParam();
        CallSetting opt = callOpParam.getOpt();
        opt.setAudioCount(1L);
        opt.setVideoCount(0L);
        SipHeaderVector sipHeaderVector = new SipHeaderVector();
        SipHeader sipHeader = new SipHeader();
        sipHeader.setHName("X-JDCC-Skill");
        sipHeader.setHValue(string4);
        sipHeaderVector.add(sipHeader);
        SipHeader sipHeader2 = new SipHeader();
        sipHeader2.setHName("X-Genesys-JDCCSkill");
        sipHeader2.setHValue(string4);
        sipHeaderVector.add(sipHeader2);
        SipHeader sipHeader3 = new SipHeader();
        sipHeader3.setHName("sip_h_X-JDCC-Token");
        sipHeader3.setHValue(string3);
        sipHeaderVector.add(sipHeader3);
        SipHeader sipHeader4 = new SipHeader();
        sipHeader4.setHName("X-Genesys-ExtendInfo");
        sipHeader4.setHValue(str);
        sipHeaderVector.add(sipHeader4);
        SipHeader sipHeader5 = new SipHeader();
        sipHeader5.setHName("X-Genesys-RequestID");
        sipHeader5.setHValue(this.m_requestId);
        sipHeaderVector.add(sipHeader5);
        callOpParam.getTxOption().setHeaders(sipHeaderVector);
        this.m_hangupT = 0;
        try {
            uphoneCall.makeCall(str2, callOpParam);
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception unused) {
            uphoneCall.delete();
            uphoneCall = null;
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void jdsip_sendMessage(String str, String str2, Callback callback) {
        SendInstantMessageParam sendInstantMessageParam = new SendInstantMessageParam();
        sendInstantMessageParam.setContent(str);
        String str3 = "sip:" + this.m_presenceDn + DYConstants.DY_REGEX_AT + str2 + ";transport=tls";
        BuddyConfig buddyConfig = new BuddyConfig();
        buddyConfig.setUri(str3);
        Buddy buddy = new Buddy();
        try {
            buddy.create(acc, buddyConfig);
            buddy.sendInstantMessage(sendInstantMessageParam);
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactMethod
    public void jdsip_setaudio_hold(Callback callback) {
        holdCall();
        callback.invoke(Boolean.TRUE, "successed");
    }

    @ReactMethod
    public void jdsip_setaudio_inmute(Callback callback) {
        try {
            this.m_txLvl = (float) ep.audDevManager().getPlaybackDevMedia().getTxLevel();
            UphoneEndpoint uphoneEndpoint = ep;
            if (uphoneEndpoint != null) {
                uphoneEndpoint.audDevManager().getPlaybackDevMedia().adjustTxLevel(0.0f);
            }
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void jdsip_setaudio_outmute(Callback callback) {
        try {
            System.out.printf("outmute:%f", Float.valueOf(this.m_txLvl));
            if (this.m_txLvl > 0.0f) {
                UphoneEndpoint uphoneEndpoint = ep;
                if (uphoneEndpoint != null) {
                    uphoneEndpoint.audDevManager().getPlaybackDevMedia().adjustTxLevel(this.m_txLvl);
                }
            } else {
                UphoneEndpoint uphoneEndpoint2 = ep;
                if (uphoneEndpoint2 != null) {
                    uphoneEndpoint2.audDevManager().getPlaybackDevMedia().adjustTxLevel(0.9f);
                }
            }
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void jdsip_setaudio_unhold(Callback callback) {
        unHoldCall();
        callback.invoke(Boolean.TRUE, "successed");
    }

    @ReactMethod
    public void jdsip_unregister(Callback callback) {
        try {
            UphoneAccount uphoneAccount = acc;
            uphoneAccount.backer = null;
            uphoneAccount.delete();
            acc = null;
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    public void loadSpeaker(final boolean z) {
        getReactApplicationContext().runOnUiQueueThread(new Runnable() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.12
            {
                JDReactNativeUphoneModule.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (JDReactNativeUphoneModule.this.autoAudioManager != null) {
                    JDReactNativeUphoneModule.this.autoAudioManager.speaker(z);
                }
            }
        });
    }

    public void nativeCallRN(String str, WritableMap writableMap) {
        System.out.println("jdsip nativeCallRN event" + str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    public void notifyChangeNetwork() {
        new Thread() { // from class: com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule.9
            {
                JDReactNativeUphoneModule.this = this;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    System.out.println("Network change detected");
                    IpChangeParam ipChangeParam = new IpChangeParam();
                    if (JDReactNativeUphoneModule.ep != null) {
                        JDReactNativeUphoneModule.ep.handleIpChange(ipChangeParam);
                    }
                } catch (Exception e2) {
                    System.out.println(e2);
                }
            }
        }.start();
    }

    @Override // com.jingdong.common.permission.UphoneCallback
    public void onIgnored() {
    }

    public void onTransportState(String str) {
        if (str.equals("disconnected")) {
            clientStateReport(this.m_requestId, UPhoneStateType.StateConnect.ordinal(), UPhoneConnectState.StateTlsDisconnected.ordinal(), "TlsDisconnected");
        } else if (str.equals("connected")) {
            clientStateReport(this.m_requestId, UPhoneStateType.StateConnect.ordinal(), UPhoneConnectState.StateTlsConnected.ordinal(), "TlsConnected");
        }
    }

    @ReactMethod
    public void readMapData(String str, Callback callback) {
        String str2 = s_mapData.get(str);
        if (str2 == null || str2.length() <= 0) {
            callback.invoke(Boolean.FALSE, "");
        } else {
            callback.invoke(Boolean.TRUE, str2);
        }
    }

    public void recv_msg(String str) {
        WritableMap createMap = Arguments.createMap();
        System.out.println("jsdip recv_msg=" + str);
        if (str.startsWith("[")) {
            str = str.substring(str.indexOf("]") + 1);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getString("messageType").equals("notify") && jSONObject.getString("messageText").equals("talking")) {
                createMap.putString("messageType", "CALL");
                createMap.putString("messageText", "talk");
                JSONObject jSONObject2 = jSONObject.getJSONObject("userData");
                createMap.putString("agentID", jSONObject2.getString("agentId"));
                createMap.putString("DN", jSONObject2.getString("thisDN"));
                createMap.putString("tServerHost", jSONObject2.getString("tServerHost"));
                this.m_agentId = jSONObject2.getString("agentId");
                this.m_presenceDn = jSONObject2.getString("thisDN");
                String string = jSONObject2.getString("connId");
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", createMap);
                clientStateReportStr(String.format("{\"requstID\":\"%s\",\"connID\":\"%s\",\"agentId\":\"%s\",\"stateType\":\"%d\", \"state\":\"%d\",\"event\":\"%s\",\"informTime\":\"%d\"}", this.m_requestId, string, this.m_agentId, Integer.valueOf(UPhoneStateType.StateCall.ordinal()), Integer.valueOf(UPhoneCallState.StateCallTalk.ordinal()), "CallTalk", Long.valueOf(System.currentTimeMillis())));
                return;
            }
            createMap.putString("message", str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneIMMessage", createMap);
        } catch (JSONException unused) {
        }
    }

    public void reg_callback(int i2, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("messageType", "REG");
        if (i2 / 100 == 2) {
            createMap.putString("messageText", "Registered");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", createMap);
            clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallReg.ordinal(), "Registered");
            return;
        }
        createMap.putString("messageText", "unRegistered");
        createMap.putString("Reason", str);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", createMap);
        clientStateReport(this.m_requestId, UPhoneStateType.StateCall.ordinal(), UPhoneCallState.StateCallunReg.ordinal(), "unRegistered");
    }

    @ReactMethod
    public void resetDefaultAudiodev(Callback callback) {
        try {
            ep.audDevManager().setCaptureDev(-1);
            ep.audDevManager().setPlaybackDev(-2);
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void rn_report(String str, int i2, int i3, String str2) {
        clientStateReport(str, i2, i3, str2);
        if (OKLog.D) {
            OKLog.d(TAG, "rn_report");
        }
    }

    @ReactMethod
    public void saveMapData(String str, String str2, Callback callback) {
        if (str.length() == 0 || str2.length() == 0) {
            callback.invoke(Boolean.TRUE, JDReactConstant.FAILED);
            return;
        }
        s_mapData.put(str, str2);
        callback.invoke(Boolean.TRUE, "successed");
    }

    @ReactMethod
    public void sendMessage(String str, String str2, Callback callback) {
        SendInstantMessageParam sendInstantMessageParam = new SendInstantMessageParam();
        sendInstantMessageParam.setContent(str);
        try {
            UphoneCall uphoneCall2 = uphoneCall;
            if (uphoneCall2 != null) {
                uphoneCall2.sendInstantMessage(sendInstantMessageParam);
            }
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactMethod
    public void setCover() {
        JDReactNativeUPhoneModuleActivity.setCoverFlag(true);
    }

    @ReactMethod
    public void setnoAudiodev(Callback callback) {
        try {
            ep.audDevManager().setNoDev();
            callback.invoke(Boolean.TRUE, "successed");
        } catch (Exception e2) {
            System.out.println(e2);
            callback.invoke(Boolean.FALSE, JDReactConstant.FAILED);
        }
    }

    @ReactMethod
    public void show(String str) {
    }

    @ReactMethod
    public void showFloatWindow(String str, boolean z) {
        System.out.println("jdsip 11111");
        if (ReactActivityUtils.getUMActivity() == null) {
            return;
        }
        System.out.println("jdsip 222222");
        if (this.floatService != null) {
            return;
        }
        System.out.println("jdsip showFloatWindow");
        Bundle bundle = new Bundle();
        bundle.putString("className", getClass().getName());
        bundle.putString("methodName", "showFloatWindow");
        bundle.putBoolean(PermissionHelper.PARAM_USER_INITIATIVE, true);
        this.currActivity = ReactActivityUtils.getUMActivity();
        this.mShowTime = Integer.parseInt(str);
        if (FloatPermissionManager.getInstance().applyFloatWindow(this.currActivity, "\u4eac\u4e1c\u9700\u8981\u83b7\u53d6\u60a8\u7684\u60ac\u6d6e\u7a97\u6743\u9650\uff0c\u4e3a\u60a8\u63d0\u4f9b\u66f4\u4f18\u7684\u8bed\u97f3\u901a\u8bdd\u4f53\u9a8c\u3002", bundle, this) || Build.VERSION.SDK_INT < 24) {
            FloatMonkService floatMonkService = this.floatService;
            if (floatMonkService != null) {
                floatMonkService.show(this.mShowTime);
                this.currActivity.moveTaskToBack(true);
                return;
            }
            this.mbReturnApp = z;
            if (this.currContext.bindService(new Intent(this.currContext, FloatMonkService.class), this.conn, 1)) {
                System.out.println("jdsip bindservice success");
            } else {
                System.out.println("jdsip bindservice failed");
            }
        }
    }

    @ReactMethod
    public void startUMGuardActivity() {
        ReactActivityUtils.startUMGuardActivity();
    }

    @ReactMethod
    public void swithSpeaker(Boolean bool) {
        AudioManager audioManager = (AudioManager) getReactApplicationContext().getSystemService("audio");
        if (bool.booleanValue()) {
            if (isAudioNewOpen() && this.autoAudioManager != null) {
                loadSpeaker(true);
            } else {
                changeToSpeaker();
            }
        } else if (isAudioNewOpen() && this.autoAudioManager != null) {
            loadSpeaker(false);
        } else {
            changeToReceiver();
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("messageType", "Audio");
        if (audioManager.isSpeakerphoneOn()) {
            createMap.putString("messageType", "Speaker");
        } else {
            createMap.putString("messageType", "Earpiece");
        }
        System.out.printf("hy_switch_speaker:%s,%b", createMap.getString("messageType"), bool);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("UPhoneToRNMessage", createMap);
    }

    public void unHoldCall() {
        CallOpParam callOpParam = new CallOpParam(true);
        callOpParam.getOpt().setFlag(1L);
        try {
            uphoneCall.reinvite(callOpParam);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @ReactMethod
    public void uninit_call() {
        Toast.makeText(getReactApplicationContext(), "uninit_call", 1).show();
        try {
            UphoneAccount uphoneAccount = acc;
            uphoneAccount.backer = null;
            uphoneAccount.delete();
            acc = null;
            UphoneEndpoint uphoneEndpoint = ep;
            uphoneEndpoint.backer = null;
            uphoneEndpoint.libDestroy();
            ep.delete();
            ep = null;
        } catch (Exception e2) {
            System.out.println(e2);
        }
    }
}
