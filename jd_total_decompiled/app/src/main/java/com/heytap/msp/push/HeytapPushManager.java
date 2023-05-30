package com.heytap.msp.push;

import android.content.Context;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.IGetAppNotificationCallBackService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.heytap.msp.push.mode.DataMessage;
import com.heytap.msp.push.mode.MessageStat;
import com.heytap.msp.push.notification.PushNotificationManager;
import com.heytap.msp.push.statis.StatisticUtils;
import g.d.a.b;
import g.d.a.j.f;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class HeytapPushManager {
    public static void cancelNotification(JSONObject jSONObject) {
        b.s().f(jSONObject);
    }

    public static void clearNotificationType(JSONObject jSONObject) {
        b.s().l(jSONObject);
    }

    public static void clearNotifications() {
        clearNotifications(null);
    }

    public static void disableAppNotificationSwitch(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        b.s().n(iSetAppNotificationCallBackService);
    }

    public static void enableAppNotificationSwitch(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        b.s().o(iSetAppNotificationCallBackService);
    }

    public static void getAppNotificationSwitch(IGetAppNotificationCallBackService iGetAppNotificationCallBackService) {
        b.s().p(iGetAppNotificationCallBackService);
    }

    public static String getMcsPackageName(Context context) {
        return b.s().u(context);
    }

    public static void getNotificationStatus(JSONObject jSONObject) {
        b.s().w(jSONObject);
    }

    public static ICallBackResultService getPushCallback() {
        return b.s().z();
    }

    public static PushNotificationManager getPushNotificationManager() {
        return PushNotificationManager.getInstance();
    }

    public static void getPushStatus() {
        b.s().C();
    }

    public static int getPushVersionCode() {
        return b.s().D();
    }

    public static String getPushVersionName() {
        return b.s().E();
    }

    public static String getReceiveSdkAction(Context context) {
        return b.s().F(context);
    }

    public static void getRegister(JSONObject jSONObject) {
        b.s().G(jSONObject);
    }

    public static String getRegisterID() {
        return b.s().H();
    }

    public static int getSDKVersionCode() {
        return b.I();
    }

    public static String getSDKVersionName() {
        return b.J();
    }

    public static void init(Context context, boolean z) {
        b.s().K(context, z);
    }

    public static boolean isSupportPush(Context context) {
        return b.s().M(context);
    }

    public static void openNotificationSettings(JSONObject jSONObject) {
        b.s().O(jSONObject);
    }

    public static void pausePush(JSONObject jSONObject) {
        b.s().P(jSONObject);
    }

    public static void register(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        b.s().Q(context, str, str2, jSONObject, iCallBackResultService);
    }

    public static void requestNotificationPermission() {
        b.s().R();
    }

    public static void resumePush(JSONObject jSONObject) {
        b.s().S(jSONObject);
    }

    public static void setAppKeySecret(String str, String str2) {
        b.s().T(str, str2);
    }

    public static void setNotificationType(int i2, JSONObject jSONObject) {
        b.s().U(i2, jSONObject);
    }

    public static void setPushCallback(ICallBackResultService iCallBackResultService) {
        b.s().V(iCallBackResultService);
    }

    public static void setPushTime(List<Integer> list, int i2, int i3, int i4, int i5, JSONObject jSONObject) throws IllegalArgumentException {
        b.s().W(list, i2, i3, i4, i5, jSONObject);
    }

    public static void setRegisterID(String str) {
        b.s().X(str);
    }

    public static void statisticEvent(Context context, String str, DataMessage dataMessage) {
        StatisticUtils.statisticEvent(context, str, dataMessage);
    }

    @Deprecated
    public static void statisticMessage(Context context, MessageStat messageStat) {
        f.b(context, messageStat);
    }

    public static void unRegister(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        b.s().a0(context, str, str2, jSONObject, iCallBackResultService);
    }

    public static void clearNotificationType() {
        clearNotificationType(null);
    }

    public static void clearNotifications(JSONObject jSONObject) {
        b.s().m(jSONObject);
    }

    public static void getNotificationStatus() {
        getNotificationStatus(null);
    }

    public static void getRegister() {
        getRegister(null);
    }

    public static void openNotificationSettings() {
        openNotificationSettings(null);
    }

    public static void pausePush() {
        pausePush(null);
    }

    public static void register(Context context, String str, String str2, ICallBackResultService iCallBackResultService) {
        register(context, str, str2, null, iCallBackResultService);
    }

    public static void resumePush() {
        resumePush(null);
    }

    public static void setNotificationType(int i2) {
        setNotificationType(i2, null);
    }

    public static void setPushTime(List<Integer> list, int i2, int i3, int i4, int i5) {
        setPushTime(list, i2, i3, i4, i5, null);
    }

    @Deprecated
    public static void statisticMessage(Context context, List<MessageStat> list) {
        f.c(context, list);
    }

    public static void unRegister(JSONObject jSONObject) {
        b.s().b0(jSONObject);
    }

    public static void unRegister() {
        unRegister(null);
    }
}
