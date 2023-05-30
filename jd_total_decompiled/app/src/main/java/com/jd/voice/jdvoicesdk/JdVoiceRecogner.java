package com.jd.voice.jdvoicesdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.voice.jdvoicesdk.d.b;
import com.jd.voice.jdvoicesdk.util.Base64;
import com.jd.voice.jdvoicesdk.util.DESEncode;
import com.jd.voice.jdvoicesdk.util.EncryptNative;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.network.encrypt.EncryptStatParamController;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes18.dex */
public class JdVoiceRecogner implements b.c, b.InterfaceC0226b {
    private static final int MSG_ERROR = 2;
    private static final int MSG_RECOGNITION_RESULT = 4;
    private static final int MSG_RECOGNITION_START = 3;
    private static final int MSG_SERVICE_UNAVAILABLE = -1;
    private static final int MSG_SPEECH_BEGIN = 0;
    private static final int MSG_SPEECH_END = 1;
    private static final int MSG_VOULUM_CHANGE = 5;
    private static final int REQUEST_TYPE_VOICE_RECOGNITION = 2;
    private static final int REQUEST_TYPE_VOICE_URL = 1;
    private static final String TAG = "JdVoiceRecogner";
    private static final String TEST_TAG = "_test";
    private static final String TIME_TAG = "time";
    private static boolean isNeedQueryServiceAvailable = true;
    private static JdVoiceRecogner mClient;
    private FileInputStream inputStream;
    private com.jd.voice.jdvoicesdk.d.a mAmrRecord;
    private com.jd.voice.jdvoicesdk.a mConfig;
    private com.jd.voice.jdvoicesdk.util.b mConnectManager;
    private Context mContext;
    private Handler mHandler;
    private com.jd.voice.jdvoicesdk.b mLintener;
    private long mTheadId;
    private com.jd.voice.jdvoicesdk.d.b mWavThread;
    private String s1;
    private String TMP_FILE = "";
    private boolean isStop = false;
    private boolean isRecording = false;
    private long lastTalkTime = 0;
    private long startTalkTime = 0;

    /* loaded from: classes18.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case -1:
                    JdVoiceRecogner.this.isStop = true;
                    if (JdVoiceRecogner.this.mLintener != null) {
                        JdVoiceRecogner.this.mLintener.d();
                        return;
                    }
                    return;
                case 0:
                    if (JdVoiceRecogner.this.mLintener != null) {
                        JdVoiceRecogner.this.mLintener.a();
                    }
                    JdVoiceRecogner.this.startTalkTime = System.currentTimeMillis();
                    com.jd.voice.jdvoicesdk.util.c.a("time", "init sta:" + JdVoiceRecogner.this.startTalkTime + " last:" + JdVoiceRecogner.this.lastTalkTime);
                    return;
                case 1:
                    if (JdVoiceRecogner.this.mLintener != null) {
                        JdVoiceRecogner.this.mLintener.onEndOfSpeech();
                    }
                    JdVoiceRecogner.this.lastTalkTime = 0L;
                    JdVoiceRecogner.this.startTalkTime = 0L;
                    return;
                case 2:
                    if (JdVoiceRecogner.this.mLintener != null) {
                        Object obj = message.obj;
                        if (!(obj instanceof com.jd.voice.jdvoicesdk.c.a) || JdVoiceRecogner.this.isStop) {
                            return;
                        }
                        JdVoiceRecogner.this.mLintener.onError(((com.jd.voice.jdvoicesdk.c.a) obj).toString());
                        return;
                    }
                    return;
                case 3:
                    com.jd.voice.jdvoicesdk.util.c.a(JdVoiceRecogner.TEST_TAG, "isStop----------->" + JdVoiceRecogner.this.isStop);
                    if (JdVoiceRecogner.this.mLintener == null || JdVoiceRecogner.this.isStop) {
                        return;
                    }
                    JdVoiceRecogner.this.mLintener.b();
                    JdVoiceRecogner.this.startRecongtion();
                    return;
                case 4:
                    if (JdVoiceRecogner.this.mLintener != null && !JdVoiceRecogner.this.isStop) {
                        Object obj2 = message.obj;
                        if (obj2 instanceof com.jd.voice.jdvoicesdk.c.b) {
                            com.jd.voice.jdvoicesdk.c.b bVar = (com.jd.voice.jdvoicesdk.c.b) obj2;
                            if (bVar.b == JdVoiceRecogner.this.mTheadId) {
                                com.jd.voice.jdvoicesdk.b unused = JdVoiceRecogner.this.mLintener;
                                JdVoiceRecogner.this.mConfig.c();
                                throw null;
                            }
                            com.jd.voice.jdvoicesdk.c.a aVar = bVar.f7204c;
                        }
                    }
                    new File(JdVoiceRecogner.this.TMP_FILE).delete();
                    return;
                case 5:
                    int i2 = message.arg1;
                    int i3 = message.arg2;
                    if (JdVoiceRecogner.this.mLintener != null && !JdVoiceRecogner.this.isStop) {
                        JdVoiceRecogner.this.mLintener.c(i2);
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    com.jd.voice.jdvoicesdk.util.c.a("time", "cur:" + currentTimeMillis + " sta:" + JdVoiceRecogner.this.startTalkTime + " op:" + (currentTimeMillis - JdVoiceRecogner.this.startTalkTime));
                    long unused2 = JdVoiceRecogner.this.startTalkTime;
                    JdVoiceRecogner.this.mConfig.b();
                    throw null;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                File file = new File(JdVoiceRecogner.this.TMP_FILE);
                JdVoiceRecogner.this.mTheadId = Thread.currentThread().getId();
                com.jd.voice.jdvoicesdk.util.c.a(JdVoiceRecogner.TAG, "id------------------->" + Thread.currentThread().getId());
                if (com.jd.voice.jdvoicesdk.a.b == 2) {
                    JdVoiceRecogner.this.inputStream = new FileInputStream(file);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) file.length());
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = JdVoiceRecogner.this.inputStream.read(bArr, 0, 1024);
                        if (-1 == read) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byteArrayOutputStream.flush();
                    JdVoiceRecogner jdVoiceRecogner = JdVoiceRecogner.this;
                    jdVoiceRecogner.postVoiceData(jdVoiceRecogner.mConfig, byteArrayOutputStream.toByteArray());
                } else {
                    JdVoiceRecogner.this.inputStream = new FileInputStream(file);
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream((int) file.length());
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read2 = JdVoiceRecogner.this.inputStream.read(bArr2, 0, 1024);
                        if (-1 == read2) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr2, 0, read2);
                    }
                    byteArrayOutputStream2.flush();
                    JdVoiceRecogner.this.postVoiceData(byteArrayOutputStream2.toByteArray());
                }
                com.jd.voice.jdvoicesdk.util.c.a(JdVoiceRecogner.TEST_TAG, "\u97f3\u9891\u6587\u4ef6\u8bc6\u522b\u7ed3\u675f");
            } catch (Exception e2) {
                e2.printStackTrace();
                JdVoiceRecogner.this.mHandler.sendMessage(JdVoiceRecogner.this.mHandler.obtainMessage(2, new com.jd.voice.jdvoicesdk.c.a(1, "\u672a\u77e5\u5f02\u5e38")));
            }
        }
    }

    /* loaded from: classes18.dex */
    class c extends Thread {
        c() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean queryServiceAvailable = JdVoiceRecogner.this.queryServiceAvailable();
            if (JdVoiceRecogner.this.isStop) {
                return;
            }
            if (!queryServiceAvailable) {
                JdVoiceRecogner.this.mHandler.sendMessage(JdVoiceRecogner.this.mHandler.obtainMessage(-1));
            } else {
                JdVoiceRecogner.this.startRecord();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JdVoiceRecogner.this.mHandler.sendMessage(JdVoiceRecogner.this.mHandler.obtainMessage(3));
            com.jd.voice.jdvoicesdk.util.c.a("time", "begin recognition:" + new Date().toString());
        }
    }

    private JdVoiceRecogner(Context context) {
        this.mConnectManager = new com.jd.voice.jdvoicesdk.util.b(context);
        this.mContext = context;
        this.mHandler = new a(context.getMainLooper());
    }

    private String buildBody(com.jd.voice.jdvoicesdk.a aVar, byte[] bArr) {
        com.jd.voice.jdvoicesdk.util.c.a(TAG, "voiceData----------------------->" + Base64.encodeBytes(bArr));
        StringBuilder sb = new StringBuilder("body=");
        JSONObject jSONObject = new JSONObject();
        try {
            aVar.c();
            throw null;
        } catch (Exception e2) {
            e2.printStackTrace();
            try {
                sb.append(URLEncoder.encode(jSONObject.toString(), "UTF-8"));
                sb.append("");
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            return sb.toString();
        }
    }

    private String buildGetUrlBody() {
        StringBuilder sb = new StringBuilder("body=");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("function", Constants.DEFAULT_UIN);
            jSONObject.put("voiceSdkVersion", "1.0");
            jSONObject.put("networkType", this.mConnectManager.c());
            jSONObject.put("format", com.jd.voice.jdvoicesdk.a.a);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            sb.append(URLEncoder.encode(jSONObject.toString(), "UTF-8"));
            sb.append("");
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        sb.append(getRequestSignature(1));
        return sb.toString();
    }

    private String buildNoticeBody(com.jd.voice.jdvoicesdk.a aVar, String str) {
        StringBuilder sb = new StringBuilder("body=");
        JSONObject jSONObject = new JSONObject();
        try {
            aVar.c();
            throw null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            try {
                sb.append(URLEncoder.encode(jSONObject.toString(), "UTF-8"));
                sb.append("");
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
            }
            sb.append(getRequestSignature(2));
            return sb.toString();
        }
    }

    private String buildUrl(String str) {
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        String deviceId = BaseInfo.getDeviceId();
        StringBuilder sb = new StringBuilder(str);
        sb.append(ContainerUtils.FIELD_DELIMITER);
        sb.append("uuid=");
        sb.append(deviceId);
        try {
            sb.append(ContainerUtils.FIELD_DELIMITER);
            sb.append("osVersion=");
            sb.append(URLEncoder.encode(Build.VERSION.RELEASE, "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        return sb.toString();
    }

    private static DefaultHttpClient createHttpClient() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, false);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 15000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        return new DefaultHttpClient(basicHttpParams);
    }

    public static synchronized JdVoiceRecogner getInstance(Context context) {
        JdVoiceRecogner jdVoiceRecogner;
        synchronized (JdVoiceRecogner.class) {
            if (mClient == null) {
                mClient = new JdVoiceRecogner(context);
            }
            jdVoiceRecogner = mClient;
        }
        return jdVoiceRecogner;
    }

    private String getRequestSignature(int i2) {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString();
        String randomNumber = DESEncode.getRandomNumber();
        String sb2 = new StringBuilder(String.valueOf(new Date().getTime())).toString();
        sb.append(ContainerUtils.FIELD_DELIMITER);
        sb.append("client=android");
        sb.append(EncryptStatParamController.REPORT_PARAM_CLIENT_VERSION);
        sb.append("1.0");
        sb.append("&did=");
        sb.append(uuid);
        sb.append("&r=");
        sb.append(randomNumber);
        sb.append("&ts=");
        sb.append(sb2);
        sb.append("&privateKey=");
        sb.append(EncryptNative.getKey());
        StringBuilder sb3 = new StringBuilder("android");
        sb3.append("1.0");
        sb3.append(uuid);
        sb3.append(randomNumber);
        sb3.append(sb2);
        sb3.append(EncryptNative.getKey());
        if (i2 == 1) {
            this.s1 = com.jd.voice.jdvoicesdk.util.d.a(sb3.toString());
            sb.append("&s1=");
            sb.append(this.s1);
        } else if (i2 == 2) {
            sb3.append(this.s1);
            String a2 = com.jd.voice.jdvoicesdk.util.d.a(sb3.toString());
            sb.append("&s1=");
            sb.append(this.s1);
            sb.append("&s2=");
            sb.append(a2);
        }
        return sb.toString();
    }

    private com.jd.voice.jdvoicesdk.c.c getVoiceUrl() {
        this.mConfig.a();
        throw null;
    }

    private com.jd.voice.jdvoicesdk.c.b notice(String str) {
        this.mConfig.a();
        throw null;
    }

    private com.jd.voice.jdvoicesdk.c.b parseResult(JSONObject jSONObject) {
        com.jd.voice.jdvoicesdk.a aVar = this.mConfig;
        if (aVar == null) {
            return new com.jd.voice.jdvoicesdk.c.b();
        }
        aVar.c();
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postVoiceData(byte[] bArr) {
        System.currentTimeMillis();
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u83b7\u53d6\u4e91\u5b58\u50a8\u5730\u5740\u5f00\u59cb");
        com.jd.voice.jdvoicesdk.c.c voiceUrl = getVoiceUrl();
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u83b7\u53d6\u4e91\u5b58\u50a8\u6570\u636e\u7ed3\u675f");
        System.currentTimeMillis();
        if (!TextUtils.isEmpty(voiceUrl.d) && (TextUtils.isEmpty(voiceUrl.f7206f) || voiceUrl.f7206f.equals("0"))) {
            com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u4e0a\u4f20\u6570\u636e\u5230\u4e91\u7aef\u5f00\u59cb");
            boolean put = put(voiceUrl.d, bArr);
            com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u4e0a\u4f20\u6570\u636e\u5230\u4e91\u7aef\u7ed3\u675f");
            System.currentTimeMillis();
            if (!put) {
                com.jd.voice.jdvoicesdk.c.a aVar = new com.jd.voice.jdvoicesdk.c.a(1, "\u4e91\u4e0a\u4f20\u5f02\u5e38");
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(2, aVar));
                return;
            }
            com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u8c03\u8bd5\u670d\u52a1\u7aef\u89e3\u6790\u63a5\u53e3\u5f00\u59cb");
            com.jd.voice.jdvoicesdk.c.b notice = notice(voiceUrl.f7205e);
            com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u8c03\u8bd5\u670d\u52a1\u7aef\u89e3\u6790\u63a5\u53e3\u7ed3\u675f");
            System.currentTimeMillis();
            if (notice != null && !TextUtils.isEmpty(notice.a)) {
                notice.b = Thread.currentThread().getId();
                Handler handler2 = this.mHandler;
                handler2.sendMessage(handler2.obtainMessage(4, notice));
                com.jd.voice.jdvoicesdk.util.c.a(TAG, "url----------------upload-------->" + notice.a);
                return;
            }
            if (notice.f7204c == null) {
                notice.f7204c = new com.jd.voice.jdvoicesdk.c.a(1, "\u672a\u77e5\u5f02\u5e38");
            }
            Handler handler3 = this.mHandler;
            handler3.sendMessage(handler3.obtainMessage(2, notice.f7204c));
            return;
        }
        if (voiceUrl.f7207g == null) {
            voiceUrl.f7207g = new com.jd.voice.jdvoicesdk.c.a(1, "\u672a\u77e5\u5f02\u5e38");
        }
        Handler handler4 = this.mHandler;
        handler4.sendMessage(handler4.obtainMessage(2, voiceUrl.f7207g));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean queryServiceAvailable() {
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u67e5\u8be2\u670d\u52a1\u662f\u5426\u53ef\u7528\u5f00\u59cb");
        com.jd.voice.jdvoicesdk.c.c voiceUrl = getVoiceUrl();
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u67e5\u8be2\u670d\u52a1\u662f\u5426\u53ef\u7528\u7ed3\u675f");
        if (TextUtils.isEmpty(voiceUrl.d)) {
            return false;
        }
        return TextUtils.isEmpty(voiceUrl.f7206f) || voiceUrl.f7206f.equals("0");
    }

    public static void releaseInstance() {
        JdVoiceRecogner jdVoiceRecogner = mClient;
        if (jdVoiceRecogner != null) {
            jdVoiceRecogner.stop();
            mClient = null;
            isNeedQueryServiceAvailable = true;
        }
    }

    public static void setNeedQueryServiceAvailable(boolean z) {
        isNeedQueryServiceAvailable = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecord() {
        com.jd.voice.jdvoicesdk.util.c.a("time", "start sta:" + this.startTalkTime + " last:" + this.lastTalkTime);
        this.isRecording = true;
        if (com.jd.voice.jdvoicesdk.a.a.equals("wav") || com.jd.voice.jdvoicesdk.a.a.equals("ogg") || com.jd.voice.jdvoicesdk.a.a.equals("pcm")) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(0));
            com.jd.voice.jdvoicesdk.d.b bVar = new com.jd.voice.jdvoicesdk.d.b(this.mContext, this, this);
            this.mWavThread = bVar;
            bVar.h();
        }
        if (com.jd.voice.jdvoicesdk.a.a.equals("amr")) {
            Handler handler2 = this.mHandler;
            handler2.sendMessage(handler2.obtainMessage(0));
            com.jd.voice.jdvoicesdk.d.a aVar = new com.jd.voice.jdvoicesdk.d.a(this.mContext, this);
            this.mAmrRecord = aVar;
            aVar.b();
        }
    }

    @Override // com.jd.voice.jdvoicesdk.d.b.c
    public void changVolumLevel(int i2, int i3) {
        this.mHandler.obtainMessage(5, i2, i3).sendToTarget();
    }

    @Override // com.jd.voice.jdvoicesdk.d.b.InterfaceC0226b
    public void onError(com.jd.voice.jdvoicesdk.c.a aVar) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(2, aVar));
    }

    public boolean put(String str, byte[] bArr) {
        HttpResponse execute;
        int statusCode;
        String str2 = TAG;
        com.jd.voice.jdvoicesdk.util.c.a(str2, "put(String url,byte[] data)---------istart");
        DefaultHttpClient createHttpClient = createHttpClient();
        HttpPut httpPut = new HttpPut(str);
        httpPut.setEntity(new ByteArrayEntity(bArr));
        try {
            execute = createHttpClient.execute(httpPut);
            statusCode = execute.getStatusLine().getStatusCode();
            com.jd.voice.jdvoicesdk.util.c.a(str2, "url----------------upload-------->" + statusCode);
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClientProtocolException e3) {
            e3.printStackTrace();
        }
        if (statusCode / 100 == 2) {
            return true;
        }
        com.jd.voice.jdvoicesdk.util.c.a(str2, "url----------------upload-------->" + EntityUtils.toString(execute.getEntity()));
        com.jd.voice.jdvoicesdk.util.c.a(TAG, "put(String url,byte[] data)---------end");
        return false;
    }

    public void setRecognitionLintener(com.jd.voice.jdvoicesdk.b bVar, com.jd.voice.jdvoicesdk.a aVar) {
        this.mLintener = bVar;
    }

    public void start() {
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u5f55\u97f3\u5f00\u59cb");
        this.isStop = false;
        if (isNeedQueryServiceAvailable) {
            isNeedQueryServiceAvailable = false;
            new c().start();
            return;
        }
        startRecord();
    }

    public void startRecongtion() {
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u97f3\u9891\u6587\u4ef6\u8bc6\u522b\u5f00\u59cb");
        this.TMP_FILE = com.jd.voice.jdvoicesdk.util.a.b(this.mContext, OrderISVUtil.MONEY_DECIMAL + com.jd.voice.jdvoicesdk.a.a);
        new Thread(new b()).start();
    }

    public void stop() {
        this.isStop = true;
        stopRecord();
    }

    public void stopRecord() {
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "stopRecord");
        com.jd.voice.jdvoicesdk.util.c.a(TEST_TAG, "\u5f55\u97f3\u7ed3\u675f");
        this.isRecording = false;
        if (this.mWavThread != null) {
            com.jd.voice.jdvoicesdk.b bVar = this.mLintener;
            if (bVar != null) {
                bVar.onEndOfSpeech();
            }
            this.lastTalkTime = 0L;
            this.startTalkTime = 0L;
            this.mWavThread.i(new d());
            com.jd.voice.jdvoicesdk.util.c.a("time", "stopRecord:" + new Date().toString());
            this.mWavThread = null;
        }
        if (this.mAmrRecord != null) {
            com.jd.voice.jdvoicesdk.b bVar2 = this.mLintener;
            if (bVar2 != null) {
                bVar2.onEndOfSpeech();
            }
            this.lastTalkTime = 0L;
            this.startTalkTime = 0L;
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(3));
            this.mAmrRecord.c();
            this.mAmrRecord = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postVoiceData(com.jd.voice.jdvoicesdk.a aVar, byte[] bArr) {
        System.currentTimeMillis();
        Thread.currentThread().getId();
        aVar.a();
        throw null;
    }
}
