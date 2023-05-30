package com.jingdong.common.messagepop;

import android.os.Handler;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.MessageBasicConfigUtils;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.MSGWithDDUtil;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class MessagePullUtils {
    public static final String IS_DOWNGRADE = "1";
    private static String TAG = "MessagePullUtils";
    private static Handler mHandler;
    private static final ConcurrentHashMap<String, Boolean> mMap = new ConcurrentHashMap<>();

    public static /* synthetic */ void a(String str) {
        mMap.put(str, Boolean.FALSE);
        stationPullServiceV1008(str);
    }

    private static void confirmPullRecordV1008(String str, String str2) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("confirmPullRecordV1008");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NGW_HOST));
        httpSetting.setEffect(1);
        httpSetting.setAppId("MessageCenter");
        httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagepop.MessagePullUtils.2
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                OKLog.d(MessagePullUtils.TAG, httpResponse);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                OKLog.e(MessagePullUtils.TAG, "onError\uff1a" + httpError.getMessage());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                Log.d(MessagePullUtils.TAG, "onReady");
            }
        });
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("msgId", str);
        httpSetting.putJsonParam("triggerPage", str2);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private static MessageDelayTimeModel getDefaultMessageModel() {
        MessageDelayTimeModel messageDelayTimeModel = new MessageDelayTimeModel();
        messageDelayTimeModel.setDelayTime(MSGWithDDUtil.getStationPullPeriod());
        messageDelayTimeModel.setOnFlag(false);
        return messageDelayTimeModel;
    }

    private static MessageDelayTimeModel getStationPullDelayTimeModel(String str) {
        String stationPullDelayTime = MSGWithDDUtil.getStationPullDelayTime();
        if (TextUtils.isEmpty(stationPullDelayTime)) {
            return getDefaultMessageModel();
        }
        for (MessageDelayTimeModel messageDelayTimeModel : MessageDelayTimeModel.toList(JDJSON.parseArray(stationPullDelayTime))) {
            if (str.equals(messageDelayTimeModel.getPageId())) {
                return messageDelayTimeModel;
            }
        }
        return getDefaultMessageModel();
    }

    public static synchronized void handlePopMessageFromPull(String str) {
        synchronized (MessagePullUtils.class) {
            if (Log.D) {
                Log.d(TAG, "msg: " + str);
            }
            Log.d(TAG, "isForeground:" + ProcessUtil.isForeground());
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String optString = JDJSON.parseObject(str).optString("message");
            if (Log.D) {
                Log.d(TAG, "message: " + optString);
            }
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            try {
                MessagePopModel messagePopModel = new MessagePopModel(JDJSON.parseObject(optString));
                if (ProcessUtil.isForeground()) {
                    JDMessagePopManager.getInstance().showMessagePop(messagePopModel, JdSdk.getInstance().getApplicationContext());
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private static boolean isInQueue(String str) {
        Boolean bool;
        try {
            bool = mMap.get(str);
            if (bool == null) {
                bool = Boolean.FALSE;
            }
        } catch (Exception e2) {
            Boolean bool2 = Boolean.FALSE;
            e2.printStackTrace();
            bool = bool2;
        }
        return bool.booleanValue();
    }

    public static void pullMessageCallBack(String str, String str2) {
        if (!"1".equals(MSGWithDDUtil.getIsPullStationCallback()) || TextUtils.isEmpty(str)) {
            return;
        }
        confirmPullRecordV1008(str, str2);
    }

    public static void pullMessageNotification(final String str) {
        if (!MSGWithDDUtil.getDDStationWindowKey() || !LoginUserBase.hasLogin() || TextUtils.isEmpty(str) || MessageBasicConfigUtils.isDegradeForX(MessageBasicConfigUtils.STATION_SWITCH)) {
            return;
        }
        MessageDelayTimeModel stationPullDelayTimeModel = getStationPullDelayTimeModel(str);
        if ("1".equals(MSGWithDDUtil.getIsPullStationRecord()) && !isInQueue(str) && stationPullDelayTimeModel.isOnFlag()) {
            mMap.put(str, Boolean.TRUE);
            int delayTime = stationPullDelayTimeModel.getDelayTime() * 1000;
            if (mHandler == null) {
                mHandler = new Handler();
            }
            mHandler.postDelayed(new Runnable() { // from class: com.jingdong.common.messagepop.n
                @Override // java.lang.Runnable
                public final void run() {
                    MessagePullUtils.a(str);
                }
            }, delayTime);
        }
    }

    private static void stationPullServiceV1008(String str) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("stationPullServiceV1008");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NGW_HOST));
        httpSetting.setEffect(1);
        httpSetting.setAppId("MessageCenter");
        httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagepop.MessagePullUtils.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getCode() != 0 || httpResponse.getJSONObject() == null) {
                    return;
                }
                try {
                    String optString = httpResponse.getJSONObject().optString("content");
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    MessagePullUtils.handlePopMessageFromPull(optString);
                } catch (Exception e2) {
                    OKLog.e(MessagePullUtils.TAG, e2);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                Log.e(MessagePullUtils.TAG, "onError\uff1a" + httpError.getMessage());
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                Log.d(MessagePullUtils.TAG, "onReady");
            }
        });
        httpSetting.setNotifyUser(false);
        httpSetting.putJsonParam("pageId", str);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
