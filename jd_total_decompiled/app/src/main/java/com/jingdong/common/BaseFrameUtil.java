package com.jingdong.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.broadcastReceiver.StorageReceiver;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.TimerUntil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.image.JDFrescoUtils;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class BaseFrameUtil {
    private static int APP_DOWNLOAD_NOTIFICATION = 1000;
    private static String APP_UPDATE_FAILED = "jd_app_update_failed";
    public static final String TAG = "BaseFrameUtil";
    private static boolean canExitInToastTime = false;
    private static BaseFrameUtil instance = null;
    private static KillStage killStage = null;
    public static boolean needStartImage = true;
    private static String sPreName = "";
    protected IMyActivity activity;
    private BaseFrameUtilImpl baseFrameUtilImpl;
    protected IMainActivity mainFrameActivity;
    public int networkInitializationState = 0;

    /* loaded from: classes5.dex */
    public interface BaseFrameUtilImpl {
        void startMainFrameActivity(Context context);
    }

    /* loaded from: classes5.dex */
    public interface KillStage {
        void run();
    }

    protected BaseFrameUtil() {
    }

    public static void exit(IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, "MyApplication exit() -->> ");
        }
        if (iMyActivity != null) {
            iMyActivity.finish();
        }
        BaseApplication.getHandler().postDelayed(new Runnable() { // from class: com.jingdong.common.BaseFrameUtil.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BaseFrameUtil.submitTime();
                    BaseFrameUtil.exitAll();
                } catch (Throwable th) {
                    if (OKLog.E) {
                        OKLog.e(BaseFrameUtil.TAG, th);
                    }
                    Process.killProcess(Process.myPid());
                }
            }
        }, 100L);
    }

    public static void exitAll() {
        if (OKLog.D) {
            OKLog.d(TAG, "MyApplication exitAll() -->> ");
        }
        JDFrescoUtils.destory();
        killStage();
    }

    public static void exitControl(Context context, String str) {
        IMainActivity mainFrameActivity = getInstance().getMainFrameActivity();
        Activity thisActivity = mainFrameActivity != null ? mainFrameActivity.getThisActivity() : null;
        String exitType = CommonBase.getExitType();
        if (TextUtils.equals(exitType, "3")) {
            exitDialog();
        } else if (TextUtils.equals(exitType, "2")) {
            exitToast(context, thisActivity, str);
        } else if (TextUtils.equals(exitType, "1") && thisActivity != null) {
            toBackGround(thisActivity);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            try {
                JDMtaUtils.onClick(context, "Home_ToBackStage", str);
                mainFrameActivity.startActivity(intent);
            } catch (Exception unused) {
                exitDialog();
            }
        }
    }

    public static void exitDialog() {
        exitDialog(null);
    }

    public static void exitToast(Context context, Activity activity, String str) {
        if (canExitInToastTime) {
            JDMtaUtils.onClick(context, "Home_ToBackTwice", str);
            toBackGround(activity);
            return;
        }
        ToastUtils.shortToast(context, "\u518d\u6b21\u8fd4\u56de\u9000\u51fa\u4eac\u4e1c");
        canExitInToastTime = true;
        JDMtaUtils.onClick(context, "Home_ToBackOnce", str);
        new Timer().schedule(new TimerTask() { // from class: com.jingdong.common.BaseFrameUtil.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                boolean unused = BaseFrameUtil.canExitInToastTime = false;
            }
        }, 2000L);
    }

    public static BaseFrameUtil getInstance() {
        if (instance == null) {
            synchronized (BaseFrameUtil.class) {
                if (instance == null) {
                    instance = new BaseFrameUtil();
                }
            }
        }
        return instance;
    }

    public static String getPreName() {
        String str;
        if (TextUtils.isEmpty(sPreName)) {
            try {
                str = JdSdk.getInstance().getApplication().getPackageManager().getPackageInfo(JdSdk.getInstance().getApplication().getPackageName(), 0).versionName;
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
                str = "";
            }
            if (!TextUtils.isEmpty(str)) {
                sPreName = "first_start_flag_" + str;
            }
        }
        return sPreName;
    }

    public static void killStage() {
        if (OKLog.D) {
            OKLog.d(TAG, "MyApplication killStage() -->> ");
        }
        CommonBase.putBooleanToPreference(APP_UPDATE_FAILED, Boolean.FALSE);
        NotificationManager notificationManager = (NotificationManager) JdSdk.getInstance().getApplication().getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager != null) {
            notificationManager.cancel(APP_DOWNLOAD_NOTIFICATION);
        }
        getInstance().setMainFrameActivity(null);
        getInstance().networkInitializationState = 0;
        JDMtaUtils.destroy();
        try {
            StorageReceiver.unregisterReceiver(JdSdk.getInstance().getApplication());
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
        KillStage killStage2 = killStage;
        if (killStage2 != null) {
            killStage2.run();
        }
        Process.killProcess(Process.myPid());
    }

    public static synchronized void killStageNoUI() {
        synchronized (BaseFrameUtil.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "MyApplication killStageNoUI() -->> ");
            }
            if (getInstance().getMainFrameActivity() == null) {
                if (OKLog.D) {
                    OKLog.d(TAG, " killSelfMethod -->> kell!");
                }
                instance.setMainFrameActivity(null);
                instance.networkInitializationState = 0;
                ((ActivityManager) JdSdk.getInstance().getApplication().getSystemService("activity")).restartPackage(JdSdk.getInstance().getApplication().getPackageName());
            }
        }
    }

    public static void setKillStage(KillStage killStage2) {
        killStage = killStage2;
    }

    public static void submitTime() {
        if (OKLog.D) {
            OKLog.d(TAG, "MyApplication setEndTime() -->> ");
        }
        TimerUntil.endTime = System.currentTimeMillis();
        if (OKLog.D) {
            System.out.println("TimerUntil.endTime=" + TimerUntil.endTime);
        }
        CommonBase.getJdSharedPreferences().edit().putLong("appUseTime", TimerUntil.getUserTime()).commit();
    }

    private static void toBackGround(Activity activity) {
        try {
            activity.moveTaskToBack(true);
        } catch (Throwable unused) {
            exit(null);
        }
    }

    public IMyActivity getCurrentMyActivity() {
        if (OKLog.D) {
            if (this.mainFrameActivity == null) {
                OKLog.d(TAG, "xxx mainFrameActivity -->>  mainFrameActivity is null");
            } else {
                OKLog.d(TAG, "xxx mainFrameActivity -->> " + this.mainFrameActivity);
            }
        }
        IMyActivity iMyActivity = this.activity;
        if (iMyActivity != null) {
            return iMyActivity;
        }
        IMainActivity iMainActivity = this.mainFrameActivity;
        if (iMainActivity != null) {
            Activity thisActivity = iMainActivity.getThisActivity();
            if (OKLog.D) {
                OKLog.d(TAG, "xxx activity -->> " + thisActivity);
            }
            if (thisActivity instanceof IMyActivity) {
                return (IMyActivity) thisActivity;
            }
            return null;
        }
        return null;
    }

    public IMainActivity getMainFrameActivity() {
        return this.mainFrameActivity;
    }

    public void restartApp(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
        exitAll();
    }

    public void setBaseFrameUtilImpl(BaseFrameUtilImpl baseFrameUtilImpl) {
        this.baseFrameUtilImpl = baseFrameUtilImpl;
    }

    public void setCurMyActivity(IMyActivity iMyActivity) {
        this.activity = iMyActivity;
    }

    @Deprecated
    public void setCurrentMyActivity(IMyActivity iMyActivity) {
    }

    public void setMainFrameActivity(IMainActivity iMainActivity) {
        this.mainFrameActivity = iMainActivity;
    }

    public void startMainFrameActivity(Context context) {
        BaseFrameUtilImpl baseFrameUtilImpl = this.baseFrameUtilImpl;
        if (baseFrameUtilImpl != null) {
            baseFrameUtilImpl.startMainFrameActivity(context);
        }
    }

    public static void exitDialog(final IMyActivity iMyActivity) {
        if (OKLog.D) {
            OKLog.d(TAG, "exitDialog() -->> ");
        }
        Activity activity = (Activity) iMyActivity;
        if (activity == null) {
            try {
                activity = getInstance().getMainFrameActivity().getThisActivity();
            } catch (Exception e2) {
                exitAll();
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        if (Configuration.getBooleanProperty(Configuration.IS_COOLMART, Boolean.FALSE).booleanValue()) {
            exit(iMyActivity);
            return;
        }
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(activity, "\u786e\u8ba4\u9000\u51fa\u4eac\u4e1c\u5ba2\u6237\u7aef\uff1f", "\u53d6\u6d88", "\u9000\u51fa");
        createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.BaseFrameUtil.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseFrameUtil.exit(iMyActivity);
            }
        });
        try {
            createJdDialogWithStyle2.show();
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e(TAG, th);
            }
            exit(iMyActivity);
        }
    }
}
