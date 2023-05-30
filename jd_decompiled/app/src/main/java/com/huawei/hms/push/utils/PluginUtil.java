package com.huawei.hms.push.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.push.g;
import com.huawei.hms.push.j;
import com.huawei.hms.push.k;
import com.huawei.hms.push.p;
import com.huawei.hms.push.r;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.jingdong.common.database.table.PushMessageTable;
import com.jingdong.common.jdreactFramework.SpecialMtaConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.nio.charset.Charset;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class PluginUtil {
    private PluginUtil() {
    }

    private static void a(Context context, String str, String str2, String str3) {
        HMSLog.i("PluginUtil", "onNotification");
        if (!g.a(context)) {
            HMSLog.i("PluginUtil", context.getPackageName() + " disable display notification.");
            j.a(context, str, null, SpecialMtaConstants.JDReact_ModuleUpgradeFailed);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.push.msg.NOTIFY_MSG");
        Charset charset = k.a;
        intent.putExtra("selfshow_info", str3.getBytes(charset));
        intent.putExtra("selfshow_token", str2.getBytes(charset));
        intent.setPackage(context.getPackageName());
        r.a(context, intent);
        HMSLog.i("PluginUtil", "invokeSelfShow done");
    }

    public static void onAppOpened(Context context, String str, String str2, Bundle bundle) {
        j.a(context, str, str2, "appHasOpenedId");
        j.a(context, bundle, "hmsStatistics");
    }

    public static boolean onDataMessage(Context context, String str, String str2, boolean z) {
        HMSLog.i("PluginUtil", "onDataMessage");
        if (TextUtils.isEmpty(str2)) {
            HMSLog.i("PluginUtil", "Empty message received");
            return true;
        }
        if (z) {
            a(context, str);
        }
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString(PushMessageTable.TB_CLOUMN_MESSAGE_ID, str);
        bundle.putByteArray(RemoteMessageConst.MSGBODY, str2.getBytes(k.a));
        bundle.putString(RemoteMessageConst.MSGTYPE, "received_message");
        return new p().a(context, bundle, intent);
    }

    public static boolean onDeletedMessages(Context context) {
        HMSLog.i("PluginUtil", "onDeletedMessages");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("message_proxy_type", ProxyCenter.getProxy().getProxyType());
        bundle.putString(RemoteMessageConst.MSGTYPE, "server_deleted_message");
        return new p().a(context, bundle, intent);
    }

    public static void onMessage(Context context, String[] strArr) {
        HMSLog.i("PluginUtil", "onMessage");
        if (context == null || strArr == null || strArr.length < 5) {
            return;
        }
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context.getApplicationContext());
        }
        if (a(context, strArr)) {
            a(context, strArr[0], strArr[3], strArr[4]);
        } else {
            onDataMessage(context, strArr[0], strArr[4], true);
        }
    }

    public static boolean onNewToken(Context context, String str, String str2, ErrorEnum errorEnum) {
        HMSLog.i("PluginUtil", "onNewToken called.");
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putInt("error", errorEnum.getInternalCode());
        bundle.putString(RemoteMessageConst.MSGTYPE, "new_token");
        bundle.putString(RemoteMessageConst.DEVICE_TOKEN, str);
        if (TextUtils.equals(str2, context.getPackageName())) {
            bundle.putString("subjectId", null);
        } else {
            bundle.putString("subjectId", str2);
        }
        bundle.putString("message_proxy_type", ProxyCenter.getProxy().getProxyType());
        return new p().a(context, bundle, intent);
    }

    public static void onNotificationArrived(Context context, String str, String str2) {
        j.a(context, str, str2, "100");
    }

    public static void onNotificationClicked(Context context, String str, String str2) {
        j.a(context, str, str2, "1");
        onAppOpened(context, str, str2, null);
    }

    public static boolean onOldDataMessage(Context context, String str, String str2) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context.getApplicationContext());
        }
        return onDataMessage(context, str, a(str2), true);
    }

    public static void saveNotifySwitch(Context context, boolean z) {
        new PushPreferences(context, "push_notify_flag").saveBoolean("notify_msg_enable", z);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("data", str);
            jSONObject2.put(RemoteMessageConst.MessageBody.MSG_CONTENT, jSONObject);
            return jSONObject2.toString();
        } catch (JSONException unused) {
            HMSLog.e("PluginUtil", "rebuild message failed");
            return null;
        }
    }

    private static boolean a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = BaseInfo.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            HMSLog.w("PluginUtil", "get running app processes null!");
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                int i2 = runningAppProcessInfo.importance;
                return (i2 == 100 || i2 == 200) ? false : true;
            }
        }
        return false;
    }

    private static boolean a(Context context, String[] strArr) {
        if (TextUtils.equals(strArr[1], "0")) {
            return a(context) || TextUtils.equals(strArr[2], "1");
        }
        return false;
    }

    private static void a(Context context, String str) {
        j.a(context, str, null, SpecialMtaConstants.JDReact_ModuleUpgradeSuccessfully);
    }
}
