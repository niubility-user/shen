package com.jingdong.common.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkConfig;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkHelper;
import com.jingdong.app.mall.global.GlobalStatusGetter;
import com.jingdong.app.mall.global.PasteStateRouterImpl;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.unification.watermark.OnWatermarkResumeListener;
import com.jingdong.common.unification.watermark.UnWatermarkBusinessHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes.dex */
public class BackForegroundWatcher implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "BackForegroundWatcher";
    private static volatile BackForegroundWatcher sInstance;
    private boolean mFirstLaunchActivity;
    private AtomicInteger stateCounter = new AtomicInteger();
    private AtomicBoolean hasInit = new AtomicBoolean(false);
    private Map<String, WatermarkHelper> waterHelpers = new HashMap();
    private CopyOnWriteArrayList<BackForegroundListener> mListeners = new CopyOnWriteArrayList<>();
    private boolean activityInitState = false;

    /* loaded from: classes6.dex */
    public interface BackForegroundListener {
        void onBackToForeground();

        void onForeToBackground();
    }

    private void createHelper(final Activity activity) {
        if (this.waterHelpers.get(activity.toString()) == null) {
            this.waterHelpers.put(activity.toString(), new WatermarkHelper());
            UnWatermarkBusinessHelper.getInstance().addResumeListener(activity.toString(), new OnWatermarkResumeListener() { // from class: com.jingdong.common.utils.BackForegroundWatcher.1
                {
                    BackForegroundWatcher.this = this;
                }

                @Override // com.jingdong.common.unification.watermark.OnWatermarkResumeListener
                public void resume() {
                    BackForegroundWatcher.this.waterMark(activity);
                }
            });
            waterMark(activity);
        }
    }

    public static synchronized BackForegroundWatcher getInstance() {
        BackForegroundWatcher backForegroundWatcher;
        synchronized (BackForegroundWatcher.class) {
            if (sInstance == null) {
                sInstance = new BackForegroundWatcher();
            }
            backForegroundWatcher = sInstance;
        }
        return backForegroundWatcher;
    }

    public void init(Application application) {
        init(application, false);
    }

    public boolean innerGetAppForegroundStatus() {
        boolean z = this.stateCounter.get() > 0 || this.activityInitState;
        return !z ? (ProcessUtil.isMainProcess() || ProcessUtil.isMantoProcess() || ProcessUtil.isSafeModeProcess() || ProcessUtil.isCrashReportProcess()) ? !JdSdk.getInstance().appInitialized() : z : z;
    }

    public boolean isAppForeground() {
        return GlobalStatusGetter.isAppForeground();
    }

    public boolean isAppInitStateOrForeground() {
        return isAppForeground();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.activityInitState = false;
        this.waterHelpers.remove(activity.toString());
        UnWatermarkBusinessHelper.getInstance().removeResumeListener(activity.toString());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        this.activityInitState = true;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        createHelper(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.activityInitState = false;
        if (this.stateCounter.get() == 0) {
            this.stateCounter.incrementAndGet();
            if (OKLog.D) {
                OKLog.d(TAG, "\u526a\u8d34\u677f\u5f00\u5173\u72b6\u6001\uff1a" + PasteStateRouterImpl.getClipPasteStatusValue());
            }
            if (this.mFirstLaunchActivity) {
                this.mFirstLaunchActivity = false;
            } else {
                if (OKLog.D) {
                    OKLog.i(TAG, "onBackToForeground");
                }
                Iterator<BackForegroundListener> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    it.next().onBackToForeground();
                }
                if (JDPrivacyHelper.isAcceptPrivacy(activity) && (activity instanceof BaseActivity)) {
                    JDSharedCommandUtils.getInstance().resumeForJDCommand((BaseActivity) activity);
                }
                if (activity instanceof BaseActivity) {
                    ((BaseActivity) activity).setManualResume(true);
                }
            }
        } else {
            this.stateCounter.incrementAndGet();
        }
        if (OKLog.D) {
            OKLog.i(TAG, "onActivityStarted stateCounter = " + this.stateCounter);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (this.stateCounter.decrementAndGet() == 0) {
            if (OKLog.D) {
                OKLog.i(TAG, "onForeToBackground");
                OKLog.d(TAG, "\u526a\u8d34\u677f\u5f00\u5173\u72b6\u6001\uff1a" + PasteStateRouterImpl.getClipPasteStatusValue());
            }
            Iterator<BackForegroundListener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onForeToBackground();
            }
        }
        if (OKLog.D) {
            OKLog.i(TAG, "onActivityStopped stateCounter = " + this.stateCounter);
        }
    }

    public void registerListener(BackForegroundListener backForegroundListener) {
        if (this.mListeners.contains(backForegroundListener)) {
            return;
        }
        this.mListeners.add(backForegroundListener);
    }

    public void unRegisterListener(BackForegroundListener backForegroundListener) {
        this.mListeners.remove(backForegroundListener);
    }

    public void waterMark(@NotNull Activity activity) {
        WatermarkHelper watermarkHelper = this.waterHelpers.get(activity.toString());
        OKLog.d("WatermarkHelper", "onResume:" + watermarkHelper.toString() + "   " + activity.toString());
        if (!LoginUserBase.hasLogin()) {
            if (watermarkHelper == null) {
                return;
            }
            WatermarkConfig.getConfig().setCanAdd(false);
            watermarkHelper.remove();
        } else if (UnWatermarkBusinessHelper.getInstance().requestSwitch() && UnWatermarkBusinessHelper.getInstance().isJiaDianUser()) {
            WatermarkConfig.getConfig().setCanAdd(true);
            watermarkHelper.showByCanAdd(activity, LoginUserBase.getUserPin());
        } else {
            WatermarkConfig.getConfig().setCanAdd(false);
            watermarkHelper.remove();
        }
    }

    public void init(Application application, boolean z) {
        if (application != null && this.hasInit.compareAndSet(false, true)) {
            this.mFirstLaunchActivity = true;
            if (z) {
                this.stateCounter.set(1);
                this.mFirstLaunchActivity = false;
            }
            application.registerActivityLifecycleCallbacks(this);
        }
    }
}
