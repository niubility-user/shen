package com.jingdong.common.messagecenter.view;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.imhelper.DDCacheUtils;
import com.jingdong.common.deeplinkhelper.imhelper.RecentContactEntity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.messagecenter.JDDDMessageRouterUtil;
import com.jingdong.common.messagecenter.MessageBasicConfigUtils;
import com.jingdong.common.messagecenter.MessageTabRedCtrl;
import com.jingdong.common.messagecenter.NewBadgeUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class NewMessagRedManager {
    private static final String DD_SERVICE_ENABLE = "dd_service_enable";
    private static final String MESSAGE_REDINFO = "messageRedInfo";
    private static final String TAG = "NewMessagRedManager";
    private static int dongdongNum = 0;
    private static boolean dongdongRedStatus = false;
    private static boolean isFirst = true;
    private static final boolean isTest = false;
    private static boolean mNotifyUI = true;
    private static List<WeakReference<MessageRedObserver>> mObservers = null;
    private static String mPin = null;
    private static Map<String, NewMessageRedInfo> messageRedInfoMap = null;
    private static final String normalHost = "redpoint-msg.m.jd.com";
    private static int num = 0;
    private static volatile NewMessagRedManager personalMessageManager = null;
    private static final String preHost = "beta-redpoint-msg.m.jd.com";

    private NewMessagRedManager(String str) {
        mObservers = new ArrayList();
        messageRedInfoMap = new HashMap();
        mPin = str;
    }

    public static void clearMessageRedInfo() {
        synchronized (messageRedInfoMap) {
            messageRedInfoMap.clear();
            NewMessageRedInfo newMessageRedInfo = new NewMessageRedInfo();
            newMessageRedInfo.pattern = 1;
            newMessageRedInfo.numPattern = 0;
            newMessageRedInfo.num = 0;
            newMessageRedInfo.redPoint = false;
            messageRedInfoMap.put(MESSAGE_REDINFO, newMessageRedInfo);
            notifyMessageObservers();
        }
    }

    public static void deregisterPersonalMessageObserver(MessageRedObserver messageRedObserver) {
        if (messageRedObserver != null) {
            synchronized (mObservers) {
                Iterator<WeakReference<MessageRedObserver>> it = mObservers.iterator();
                while (it.hasNext()) {
                    MessageRedObserver messageRedObserver2 = it.next().get();
                    if (messageRedObserver2 == null || messageRedObserver2 == messageRedObserver) {
                        it.remove();
                    }
                }
            }
            return;
        }
        throw new NullPointerException("observer is null");
    }

    private static int getDDServiceEnable() {
        return SharedPreferencesUtil.getInt(DD_SERVICE_ENABLE, 1);
    }

    public static NewMessagRedManager getInstance(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (personalMessageManager == null) {
            synchronized (NewMessagRedManager.class) {
                if (personalMessageManager == null) {
                    personalMessageManager = new NewMessagRedManager(str);
                }
            }
        }
        mPin = str;
        return personalMessageManager;
    }

    public static void notifyMessageObservers() {
        NewMessageRedInfo newMessageRedInfo;
        try {
            synchronized (mObservers) {
                Iterator<WeakReference<MessageRedObserver>> it = mObservers.iterator();
                while (it.hasNext()) {
                    MessageRedObserver messageRedObserver = it.next().get();
                    if (messageRedObserver != null) {
                        messageRedObserver.onMessageRedReceived(messageRedInfoMap);
                    } else {
                        it.remove();
                    }
                }
                Map<String, NewMessageRedInfo> map = messageRedInfoMap;
                if (map != null && (newMessageRedInfo = map.get(MESSAGE_REDINFO)) != null) {
                    NewBadgeUtil.setBadge(newMessageRedInfo.num);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void otherAppUnreadCountV932(HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost("redpoint-msg.m.jd.com"));
        httpSetting.setFunctionId("otherAppUnreadCount");
        httpSetting.setListener(onCommonListener);
        httpSetting.setUseFastJsonParser(true);
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public static void parsePersonalMessage(JDJSONObject jDJSONObject) {
        synchronized (messageRedInfoMap) {
            String optString = jDJSONObject.optString("code");
            if (!TextUtils.isEmpty(optString) && "0".equals(optString)) {
                messageRedInfoMap.clear();
                redNum(jDJSONObject);
            }
        }
    }

    public static void readUnReadNum(int i2, boolean z, String str) {
        NewMessageRedInfo.putPreRedDotParams(i2);
        final NewMessageRedInfo newMessageRedInfo = new NewMessageRedInfo();
        newMessageRedInfo.pattern = 1;
        newMessageRedInfo.numPattern = 0;
        newMessageRedInfo.num = i2;
        newMessageRedInfo.redPoint = dongdongRedStatus | z;
        messageRedInfoMap.put(MESSAGE_REDINFO, newMessageRedInfo);
        if (newMessageRedInfo.isShowRedDot()) {
            MessageTabRedCtrl.getInstance().showMsgRedPoint(2, 0);
            NewMessageRedInfo.putRedPointStatus(true);
        } else if (!newMessageRedInfo.isShow9Number() && !newMessageRedInfo.isShow99Number()) {
            MessageTabRedCtrl.getInstance().showMsgRedPoint(0, 0);
            if ("1".equals(str)) {
                otherAppUnreadCountV932(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagecenter.view.NewMessagRedManager.3
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        if (httpResponse.getCode() == 0) {
                            newMessageRedInfo.redPoint = httpResponse.getFastJsonObject().optBoolean("redPoint");
                            NewMessagRedManager.messageRedInfoMap.put(NewMessagRedManager.MESSAGE_REDINFO, newMessageRedInfo);
                            if (NewMessagRedManager.mNotifyUI) {
                                NewMessagRedManager.notifyMessageObservers();
                            }
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                    public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                    }
                });
            }
        } else {
            MessageTabRedCtrl.getInstance().showMsgRedPoint(1, newMessageRedInfo.num);
        }
    }

    private static void redNum(final JDJSONObject jDJSONObject) {
        int optInt = jDJSONObject.optInt("ddAdd");
        final boolean optBoolean = jDJSONObject.optBoolean("redPoint");
        final String optString = jDJSONObject.optString("otherAppAdd");
        int dDServiceEnable = getDDServiceEnable();
        boolean z = true;
        if (1 == dDServiceEnable) {
            if (1 == optInt) {
                String unReadCount = DDCacheUtils.getUnReadCount(UserUtil.getWJLoginHelper().getPin().toLowerCase());
                if (!TextUtils.isEmpty(unReadCount)) {
                    try {
                        RecentContactEntity recentContactEntity = (RecentContactEntity) JDJSON.parseObject(unReadCount, RecentContactEntity.class);
                        if (recentContactEntity != null) {
                            int i2 = recentContactEntity.totalCount;
                            int i3 = recentContactEntity.noDisturbTotalCount;
                            if (i3 > 0) {
                                dongdongNum = i3;
                                dongdongRedStatus = true;
                            } else {
                                if (i2 <= 0) {
                                    z = false;
                                }
                                dongdongRedStatus = z;
                                dongdongNum = 0;
                            }
                        } else {
                            dongdongNum = 0;
                            dongdongRedStatus = false;
                        }
                        num = jDJSONObject.optInt("num") + dongdongNum;
                    } catch (Exception unused) {
                        num = jDJSONObject.optInt("num");
                    }
                    readUnReadNum(num, optBoolean, optString);
                    return;
                }
                JDDDMessageRouterUtil.getUnreadCount(JdSdk.getInstance().getApplicationContext(), new CallBackWithReturnListener() { // from class: com.jingdong.common.messagecenter.view.NewMessagRedManager.2
                    @Override // com.jingdong.common.unification.router.CallBackListener
                    public void onComplete() {
                    }

                    @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
                    public void onComplete(Object obj) {
                        if (obj == null || !(obj instanceof RecentContactEntity)) {
                            int unused2 = NewMessagRedManager.dongdongNum = 0;
                            boolean unused3 = NewMessagRedManager.dongdongRedStatus = false;
                        } else {
                            RecentContactEntity recentContactEntity2 = (RecentContactEntity) obj;
                            int i4 = recentContactEntity2.totalCount;
                            int i5 = recentContactEntity2.noDisturbTotalCount;
                            if (i5 > 0) {
                                int unused4 = NewMessagRedManager.dongdongNum = i5;
                                boolean unused5 = NewMessagRedManager.dongdongRedStatus = true;
                            } else {
                                boolean unused6 = NewMessagRedManager.dongdongRedStatus = i4 > 0;
                                int unused7 = NewMessagRedManager.dongdongNum = 0;
                            }
                        }
                        int unused8 = NewMessagRedManager.num = jDJSONObject.optInt("num") + NewMessagRedManager.dongdongNum;
                        NewMessagRedManager.readUnReadNum(NewMessagRedManager.num, optBoolean, optString);
                    }

                    @Override // com.jingdong.common.unification.router.CallBackListener
                    public void onError(int i4) {
                        Log.d(NewMessagRedManager.TAG, "\u5237\u65b0\u7ea2\u70b9\u6570\u5b57\u5931\u8d25:" + i4);
                        NewMessagRedManager.readUnReadNum(NewMessagRedManager.num, optBoolean, optString);
                    }
                });
                return;
            }
            num = jDJSONObject.optInt("num");
        } else if (dDServiceEnable == 0) {
            num = jDJSONObject.optInt("num");
        }
        readUnReadNum(num, optBoolean, optString);
    }

    public static void registPersonalMessageObserver(MessageRedObserver messageRedObserver) {
        if (messageRedObserver != null) {
            synchronized (mObservers) {
                Iterator<WeakReference<MessageRedObserver>> it = mObservers.iterator();
                while (it.hasNext()) {
                    if (it.next().get() == messageRedObserver) {
                        return;
                    }
                }
                mObservers.add(new WeakReference<>(messageRedObserver));
                return;
            }
        }
        throw new NullPointerException("observer is null");
    }

    public static void requestMessage(HttpGroup httpGroup) {
        try {
            if (!MessageBasicConfigUtils.isDegradeForX(MessageBasicConfigUtils.MSG_RED_DOT_SWITCH)) {
                requestMessage(httpGroup, true);
            } else {
                clearMessageRedInfo();
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void refreshRedPointInfo(NewMessageRedInfo newMessageRedInfo) {
        synchronized (messageRedInfoMap) {
            if (MessageBasicConfigUtils.isDegradeForX(MessageBasicConfigUtils.MSG_RED_DOT_SWITCH)) {
                clearMessageRedInfo();
            } else if (newMessageRedInfo != null) {
                messageRedInfoMap.clear();
                messageRedInfoMap.put(MESSAGE_REDINFO, newMessageRedInfo);
                notifyMessageObservers();
            }
        }
    }

    public static void requestMessage(HttpGroup httpGroup, final boolean z) {
        mNotifyUI = z;
        if (LoginUserBase.hasLogin()) {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NGW_HOST));
            httpSetting.setFunctionId("msgEntranceV710");
            httpSetting.setAppId("MessageCenter");
            httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
            if (isFirst) {
                httpSetting.putJsonParam("flush", "1");
                isFirst = false;
            } else {
                httpSetting.putJsonParam("flush", "0");
            }
            httpSetting.setEffect(0);
            httpSetting.setCacheMode(2);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.messagecenter.view.NewMessagRedManager.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (fastJsonObject != null) {
                        NewMessagRedManager.parsePersonalMessage(fastJsonObject);
                        if (z) {
                            NewMessagRedManager.notifyMessageObservers();
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    int preRedDotParams = NewMessageRedInfo.getPreRedDotParams();
                    if (preRedDotParams <= 0) {
                        MessageTabRedCtrl.getInstance().showMsgRedPoint(0, 0);
                    } else {
                        MessageTabRedCtrl.getInstance().showMsgRedPoint(1, preRedDotParams);
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            httpGroup.add(httpSetting);
        }
    }
}
