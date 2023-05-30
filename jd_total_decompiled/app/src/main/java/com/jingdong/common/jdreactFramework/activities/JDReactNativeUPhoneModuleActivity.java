package com.jingdong.common.jdreactFramework.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.JDReactNativeUphoneModule;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.Iterator;
import java.util.List;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes5.dex */
public class JDReactNativeUPhoneModuleActivity extends JDReactNativeCommonActivity {
    public static final int JD_KEYCODE_HOME = 3;
    public static JDReactNativeUPhoneModuleActivity instance;
    public static boolean isCover;
    public static boolean isExist;
    public static boolean isHide;
    public static JDReactNativeUphoneModule jdReactNativeUphoneModule;
    private static Activity lastActivity;

    private static boolean bring2Front(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        try {
            intent.setComponent(new ComponentName(f.f19954c, str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        intent.addFlags(270663680);
        instance.getApplicationContext().startActivity(intent);
        return true;
    }

    public static boolean isRunningForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : BaseInfo.getRunningAppProcesses(context)) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                return true;
            }
        }
        return false;
    }

    public static void setCoverFlag(boolean z) {
        isCover = z;
    }

    public static void setJdReactNativeUphoneModule(JDReactNativeUphoneModule jDReactNativeUphoneModule) {
        jdReactNativeUphoneModule = jDReactNativeUphoneModule;
    }

    public static void setLastActivity(Activity activity) {
        lastActivity = activity;
    }

    public void emitHomeEvent(String str, WritableMap writableMap) {
        JDReactNativeUphoneModule jDReactNativeUphoneModule = jdReactNativeUphoneModule;
        if (jDReactNativeUphoneModule != null) {
            jDReactNativeUphoneModule.nativeCallRN("KeyEvent", writableMap);
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    @RequiresApi(api = 11)
    public void finish() {
        showLastActivity();
        super.finish();
    }

    @RequiresApi(api = 3)
    public boolean mainProcessIsBackground() {
        Context applicationContext = getApplication().getApplicationContext();
        ActivityManager activityManager = (ActivityManager) applicationContext.getSystemService("activity");
        Iterator<ActivityManager.RunningAppProcessInfo> it = BaseInfo.getRunningAppProcesses(this).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.processName.equals(applicationContext.getPackageName())) {
                if (next.importance != 100) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        instance = this;
        isExist = true;
        getWindow().addFlags(128);
    }

    @Override // com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseCommonActivity, com.jingdong.common.jdreactFramework.activities.JDReactNativeBaseActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        isExist = false;
        isHide = false;
        instance = null;
        jdReactNativeUphoneModule = null;
        super.onDestroy();
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    @RequiresApi(api = 3)
    public void onStop() {
        super.onStop();
        if (isCover) {
            isCover = false;
        } else if (mainProcessIsBackground()) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("messageType", "KeyEvent");
            createMap.putString("keyCode", "homekey");
            emitHomeEvent("KeyEvent", createMap);
        }
    }

    public void setHide(boolean z) {
        isHide = z;
    }

    @RequiresApi(api = 11)
    public void showLastActivity() {
        if (lastActivity == null) {
            return;
        }
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        List<ActivityManager.RunningTaskInfo> runningTasks = BaseInfo.getRunningTasks(this);
        for (int i2 = 0; i2 < runningTasks.size(); i2++) {
            if (runningTasks.get(i2).topActivity.toShortString().indexOf(JDReactNativeUPhoneModuleActivity.class.getName()) <= -1 && runningTasks.get(i2).topActivity.getPackageName().equals(getPackageName())) {
                activityManager.moveTaskToFront(runningTasks.get(i2).id, 1);
                return;
            }
        }
    }
}
