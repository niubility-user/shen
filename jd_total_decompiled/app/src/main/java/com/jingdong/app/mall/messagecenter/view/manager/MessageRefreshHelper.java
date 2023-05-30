package com.jingdong.app.mall.messagecenter.view.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.messagecenter.BadgeCacheDataUtils;
import com.jingdong.common.messagecenter.MessageBasicConfigUtils;
import com.jingdong.common.messagecenter.MessageCommonUtils;
import com.jingdong.common.messagecenter.MessageTabRedCtrl;
import com.jingdong.common.messagecenter.NewBadgeUtil;
import com.jingdong.common.messagecenter.RomUtil;
import com.jingdong.common.messagecenter.StationCacheDataUtils;
import com.jingdong.common.messagepop.JDMessagePopManager;
import com.jingdong.common.messagepop.MessageNoticeModel;
import com.jingdong.common.messagepop.MessagePopModel;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.rom.RomUtils;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.MSGWithDDUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.depend.DependUtil;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class MessageRefreshHelper {
    private static final String TAG = "MessageRefreshHelper";
    private static MessageRefreshHelper getInstance = null;
    private static boolean isForeground = true;
    private BackForegroundWatcher.BackForegroundListener mBackForegroundListener = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        int f11263g;

        a() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                return;
            }
            this.f11263g = fastJsonObject.optInt("msgRingEnable");
            fastJsonObject.optString("pattern");
            fastJsonObject.optString("numPattern");
            String optString = fastJsonObject.optString("isAllAccountListEnable");
            String optString2 = fastJsonObject.optString("ddServiceEnable");
            Boolean valueOf = Boolean.valueOf(fastJsonObject.optBoolean("popMainSwitch", false));
            String optString3 = fastJsonObject.optString("v900FirstLevelABTestFlag");
            String optString4 = fastJsonObject.optString("isPullStationRecord");
            String optString5 = fastJsonObject.optString("isPullStationCallback");
            String optString6 = fastJsonObject.optString("stationPullPeriod");
            JDJSONArray optJSONArray = fastJsonObject.optJSONArray("stationPullDelayTime");
            String jSONString = JDJSON.toJSONString(MessageNoticeModel.toList(fastJsonObject.optJSONArray("popSwitches")));
            MessageRefreshHelper.this.parseAndroidBadgeAbSwitch(fastJsonObject.optString("androidBadgeSwitch"));
            String optString7 = fastJsonObject.optString("stationPVSwitch");
            JDJSONArray optJSONArray2 = fastJsonObject.optJSONArray("stationWhitePage");
            MSGWithDDUtil.putDDServiceEnable(optString2.isEmpty() ? 1 : Integer.valueOf(optString2).intValue());
            MSGWithDDUtil.putMSGRINGEnable(this.f11263g);
            MSGWithDDUtil.putAccountListEnable(optString);
            MSGWithDDUtil.putPopMainSwitch(valueOf.booleanValue());
            MSGWithDDUtil.putPopSwitches(jSONString);
            MSGWithDDUtil.putFirstLevelABTestFlag(optString3);
            MSGWithDDUtil.putIsPullStationRecord(optString4);
            MSGWithDDUtil.putIsPullStationCallback(optString5);
            MSGWithDDUtil.putStationPullPeriod(optString6);
            if (optJSONArray != null) {
                MSGWithDDUtil.putStationPullDelayTime(JDJSON.toJSONString(optJSONArray));
            }
            StationCacheDataUtils.putStationPVSwitch(optString7);
            StationCacheDataUtils.putStationWhitePage(JDJSON.toJSONString(optJSONArray2));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            MSGWithDDUtil.putDDServiceEnable(1);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements BackForegroundWatcher.BackForegroundListener {
        b(MessageRefreshHelper messageRefreshHelper) {
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onBackToForeground() {
            boolean unused = MessageRefreshHelper.isForeground = true;
        }

        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
        public void onForeToBackground() {
            boolean unused = MessageRefreshHelper.isForeground = false;
        }
    }

    private void clearBadgeIfDemotion(String str, String str2, String str3, String str4) {
        if ("1".equals(str)) {
            if ((!RomUtils.checkIsHuaweiRom() || "1".equals(str2)) && ((!RomUtils.checkIsHonorRom() || "1".equals(str3)) && (!RomUtil.isVIVO() || "1".equals(str4)))) {
                return;
            }
            NewBadgeUtil.clearBadge();
            return;
        }
        NewBadgeUtil.clearBadge();
    }

    public static MessageRefreshHelper getInstance() {
        if (getInstance == null) {
            synchronized (MessageRefreshHelper.class) {
                if (getInstance == null) {
                    getInstance = new MessageRefreshHelper();
                }
            }
        }
        return getInstance;
    }

    private String getMessageNoticeStatusExposureData() {
        try {
            String str = MSGWithDDUtil.getDDStationWindowKey() ? "1" : "0";
            HashMap hashMap = new HashMap();
            hashMap.put("total", String.valueOf(pushStatus()));
            hashMap.put("badge", "");
            hashMap.put(RemoteMessageConst.Notification.SOUND, "");
            hashMap.put("alert", "");
            hashMap.put(XView2Constants.XVIEW2_ACTION_DIALOG, str);
            return JDJSON.toJSONString(hashMap);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private synchronized void onMessageEvent20002(Context context, String str) {
        if (isForeground) {
            if (Log.D) {
                Log.d(TAG, "app\u5728\u524d\u53f0\u6267\u884c\u957f\u8fde\u63a5\u64cd\u4f5c");
            }
            MessageTabRedCtrl.getInstance().requestMessage();
            Intent intent = new Intent("message_refresh_first_activity");
            intent.putExtra("msg", str);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        } else if (Log.D) {
            Log.d(TAG, "app\u5728\u540e\u53f0\u653e\u5f03\u957f\u8fde\u63a5\u4e1a\u52a1\u64cd\u4f5c");
        }
    }

    private synchronized void onMessageEvent20003(Context context, String str) {
        if (Log.D) {
            Log.d(TAG, "msg: " + str);
        }
        String str2 = TAG;
        Log.d(str2, "isForeground:" + isForeground);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String optString = JDJSON.parseObject(str).optString("message");
        if (Log.D) {
            Log.d(str2, "message: " + optString);
        }
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        try {
            MessagePopModel messagePopModel = new MessagePopModel(JDJSON.parseObject(optString));
            if (isForeground) {
                JDMessagePopManager.getInstance().showMessagePop(messagePopModel, JdSdk.getInstance().getApplicationContext());
            }
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAndroidBadgeAbSwitch(String str) {
        if (TextUtils.isEmpty(str)) {
            BadgeCacheDataUtils.putBadgeABSwitch("0");
            return;
        }
        try {
            JDJSONObject jDJSONObject = (JDJSONObject) JDJSON.parse(str);
            if (jDJSONObject != null) {
                String string = jDJSONObject.getString("badgeSwitch");
                String string2 = jDJSONObject.getString("huaweiSwitch");
                String string3 = jDJSONObject.getString("vivoSwitch");
                String string4 = jDJSONObject.getString("honorSwitch");
                BadgeCacheDataUtils.putBadgeABSwitch(string);
                BadgeCacheDataUtils.putHuaWeiBadgeABSwitch(string2);
                BadgeCacheDataUtils.putVIVOBadgeABSwitch(string3);
                BadgeCacheDataUtils.putHonorBadgeABSwitch(string4);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            BadgeCacheDataUtils.putBadgeABSwitch("0");
        }
    }

    private int pushStatus() {
        try {
            return NotificationManagerCompat.from(JdSdk.getInstance().getApplication()).areNotificationsEnabled() ? 1 : 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private void requestMessageConfig() {
        requestMsgConfig(new a());
    }

    private void requestMsgConfig(HttpGroup.OnCommonListener onCommonListener) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("msgConfig");
        httpSetting.setPost(true);
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.NGW_HOST));
        httpSetting.putJsonParam("uuid", DependUtil.getInstance().getDepend().getUUID());
        httpSetting.setEffect(0);
        httpSetting.setListener(onCommonListener);
        httpSetting.setNotifyUser(false);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setAppId("MessageCenter");
        httpSetting.setSecretKey("ddcccc63f0b2426fb61acb24c9439b3f");
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private void setWatcherListener() {
        this.mBackForegroundListener = new b(this);
        BackForegroundWatcher.getInstance().registerListener(this.mBackForegroundListener);
    }

    public void netWorkChangeRefreshRedPoint(Context context) {
        if (NavigationBase.getInstance().isMsgDisplayType()) {
            onMessageEvent20002(context, "refresh");
        }
    }

    public void registerMessage() {
        MessageCommonUtils.registeredBusiness(20002, MessageRefreshHelper.class);
        MessageCommonUtils.registeredBusiness(20003, MessageRefreshHelper.class);
        setWatcherListener();
        if (!MessageBasicConfigUtils.isDegradeForX(MessageBasicConfigUtils.MSG_CONFIG_SWITCH)) {
            requestMessageConfig();
        }
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "MessageNotice_SetupSwitch_Status", pushStatus() + "___", null, null, null, getMessageNoticeStatusExposureData(), null);
    }
}
