package com.jingdong.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.jingdong.common.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.JDSharedPreferences;
import com.jingdong.sdk.aac.data.BaseLiveData;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class DeepDarkChangeManager {
    public static final String KEY_DARK_MODE_HAS_SWITCHED = "dark_mode_has_switched";
    public static final int MODE_DARK = 1;
    public static final int MODE_LIGHT = 0;
    private static final String TAG = "DeepDarkChangeManager";
    private AtomicInteger currentUIMode;
    private boolean darkModeGuideSwitch;
    private boolean isUIModeFollowSystem;
    private BaseLiveData<Integer> liveData;
    private CopyOnWriteArrayList<OnUIModeChangeListener> onDeepDarkChangeListeners;
    private SharedPreferences sharedPreferences;

    /* loaded from: classes6.dex */
    public interface OnUIModeChangeListener {
        void onUIModeChanged(int i2);
    }

    /* loaded from: classes6.dex */
    public static class SingletonHolder {
        private static final DeepDarkChangeManager INSTANCE = new DeepDarkChangeManager();

        private SingletonHolder() {
        }
    }

    public static DeepDarkChangeManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private int getSysUIMode() {
        try {
            Configuration configuration = JdSdk.getInstance().getApplication().getResources().getConfiguration();
            if (configuration != null) {
                int i2 = configuration.uiMode & 48;
                if (OKLog.D) {
                    OKLog.d(TAG, "getSysUIMode mode=" + i2);
                }
                return i2 != 32 ? 0 : 1;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    private boolean isShowDarkGuide(Context context) {
        if (context == null) {
            return false;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "\u662f\u5426\u5c55\u793a\u8fc7\u6697\u9ed1\u5f15\u5bfc\u5f39\u7a97=" + this.sharedPreferences.getBoolean(Constants.DEEP_DARK_GUIDE_DIALOG, false));
            OKLog.d(TAG, "\u6697\u9ed1\u7edf\u4e00\u5f00\u5173=" + getDeepDarkGuideSwitch());
            OKLog.d(TAG, "\u5f53\u524d\u7cfb\u7edf\u662f\u5426\u4e3a\u6697\u9ed1=" + getDarkModeStatus(context));
            OKLog.d(TAG, "\u7cfb\u7edf\u662f\u5426\u4e3aAndroid10\u53ca\u4ee5\u4e0a=" + isAtLeastQ());
            OKLog.d(TAG, "\u662f\u5426\u64cd\u4f5c\u8fc7\u6697\u9ed1\u6a21\u5f0f\u8bbe\u7f6e\u9875\u9762\u76842\u4e2a\u5f00\u5173=" + getDarkModeSwitchHasTurned());
            OKLog.d(TAG, "\u5f53\u524d\u7684\u6a21\u5f0f(\u6807\u51c6/\u957f\u8f88)=" + JDElderModeUtils.getElderMode());
        }
        return !this.sharedPreferences.getBoolean(Constants.DEEP_DARK_GUIDE_DIALOG, false) && getDeepDarkGuideSwitch() && getDarkModeStatus(context) && isAtLeastQ() && !getDarkModeSwitchHasTurned() && JDElderModeUtils.getElderMode() == 0;
    }

    public boolean addDeepDarkChangeListener(OnUIModeChangeListener onUIModeChangeListener) {
        if (this.onDeepDarkChangeListeners == null) {
            this.onDeepDarkChangeListeners = new CopyOnWriteArrayList<>();
        }
        return this.onDeepDarkChangeListeners.add(onUIModeChangeListener);
    }

    public void clearDeepDarkChangeListeners() {
        CopyOnWriteArrayList<OnUIModeChangeListener> copyOnWriteArrayList = this.onDeepDarkChangeListeners;
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.clear();
    }

    public boolean getDarkModeStatus(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public boolean getDarkModeSwitchHasTurned() {
        return this.sharedPreferences.getBoolean(KEY_DARK_MODE_HAS_SWITCHED, false);
    }

    public boolean getDeepDarkGuideSwitch() {
        return this.darkModeGuideSwitch;
    }

    public boolean getIsUIModeFollowSystem() {
        return this.isUIModeFollowSystem;
    }

    public int getUIMode() {
        if (OKLog.D) {
            OKLog.d(TAG, "getUIMode=" + this.currentUIMode.get());
        }
        return this.currentUIMode.get();
    }

    public void handleUIModeConfiguration(Configuration configuration) {
        if (configuration == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "handleUIModeConfiguration=" + configuration);
            OKLog.d(TAG, "isUIModeFollowSystem=" + this.isUIModeFollowSystem);
        }
        if (this.isUIModeFollowSystem) {
            int i2 = configuration.uiMode & 48;
            if (OKLog.D) {
                OKLog.d(TAG, "mode=" + i2);
            }
            if (i2 != 16) {
                if (i2 == 32 && getUIMode() != 1) {
                    saveDeepDarkSwitch(1);
                    notifyDeepDarkChanged(this.currentUIMode.get());
                }
            } else if (getUIMode() != 0) {
                saveDeepDarkSwitch(0);
                notifyDeepDarkChanged(this.currentUIMode.get());
            }
        }
    }

    public boolean isAtLeastQ() {
        String str = Build.VERSION.CODENAME;
        return (str.length() == 1 && str.charAt(0) >= 'Q' && str.charAt(0) <= 'Z') || Build.VERSION.SDK_INT > 28;
    }

    public void notifyDeepDarkChanged(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "notifyDeepDarkChanged_uiMode=" + i2);
        }
        this.liveData.postValue(Integer.valueOf(i2));
        CopyOnWriteArrayList<OnUIModeChangeListener> copyOnWriteArrayList = this.onDeepDarkChangeListeners;
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return;
        }
        for (int size = this.onDeepDarkChangeListeners.size() - 1; size >= 0; size--) {
            OnUIModeChangeListener onUIModeChangeListener = this.onDeepDarkChangeListeners.get(size);
            if (onUIModeChangeListener != null) {
                try {
                    onUIModeChangeListener.onUIModeChanged(i2);
                } catch (Exception unused) {
                }
            }
        }
    }

    public void postValueActive() {
        try {
            if (this.liveData != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "postValueActive-getUIMode()=" + getUIMode());
                }
                this.liveData.setValue(Integer.valueOf(getUIMode()));
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(new Exception("postValueActive_" + e2));
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public boolean removeDeepDarkChangeListener(OnUIModeChangeListener onUIModeChangeListener) {
        CopyOnWriteArrayList<OnUIModeChangeListener> copyOnWriteArrayList = this.onDeepDarkChangeListeners;
        return copyOnWriteArrayList != null && copyOnWriteArrayList.remove(onUIModeChangeListener);
    }

    public void saveDeepDarkGuideSwitch(boolean z) {
        this.sharedPreferences.edit().putBoolean(Constants.DEEP_DARK_GUIDE_SWITCH, z).apply();
    }

    public void saveDeepDarkSwitch(int i2) {
        this.currentUIMode.set(i2);
        this.sharedPreferences.edit().putInt(Constants.DEEP_DARK_MODE, i2).apply();
    }

    public void setDarkModeSwitchTurned() {
        this.sharedPreferences.edit().putBoolean(KEY_DARK_MODE_HAS_SWITCHED, true).apply();
    }

    public void setIsUIModeFollowSystem(boolean z) {
        this.isUIModeFollowSystem = z;
        this.sharedPreferences.edit().putBoolean(Constants.DEEP_DARK_FOLLOW_SYS_SWITCH, z).apply();
    }

    public boolean showDarkGuide(final Context context) {
        if (!isShowDarkGuide(context)) {
            if (OKLog.D) {
                OKLog.d(TAG, "showDarkGuide_context=" + context);
                return false;
            }
            return false;
        }
        final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, context.getResources().getString(R.string.deep_dark_title), context.getResources().getString(R.string.deep_dark_message), context.getResources().getString(R.string.deep_dark_leftButtonText), context.getResources().getString(R.string.deep_dark_rightButtonText));
        createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.DeepDarkChangeManager.2
            {
                DeepDarkChangeManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDMtaUtils.onClickWithPageId(context, "Home_DeepBlackPopupClose", getClass().getSimpleName(), RecommendMtaUtils.Home_PageId);
                createJdDialogWithStyle6.dismiss();
            }
        });
        createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.utils.DeepDarkChangeManager.3
            {
                DeepDarkChangeManager.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDMtaUtils.onClickWithPageId(context, "Home_DeepBlackPopup", getClass().getSimpleName(), RecommendMtaUtils.Home_PageId);
                DeepDarkChangeManager.this.setIsUIModeFollowSystem(true);
                DeepDarkChangeManager.this.handleUIModeConfiguration(context.getResources().getConfiguration());
                Context context2 = context;
                ToastUtils.showToast(context2, context2.getResources().getString(R.string.deep_dark_toast));
                createJdDialogWithStyle6.dismiss();
            }
        });
        this.sharedPreferences.edit().putBoolean(Constants.DEEP_DARK_GUIDE_DIALOG, true).apply();
        createJdDialogWithStyle6.show();
        JDMtaUtils.sendExposureData(context, getClass().getSimpleName(), RecommendMtaUtils.Home_PageId, "", "Home_DeepBlackPopup_Expo", "", "", "", "");
        return true;
    }

    private DeepDarkChangeManager() {
        if (this.sharedPreferences == null) {
            this.sharedPreferences = new JDSharedPreferences(JdSdk.getInstance().getApplicationContext(), "jd_deepdark_ui_mode_sp", 0);
        }
        this.liveData = new BaseLiveData<Integer>() { // from class: com.jingdong.common.utils.DeepDarkChangeManager.1
            {
                DeepDarkChangeManager.this = this;
            }
        };
        this.currentUIMode = new AtomicInteger();
        this.darkModeGuideSwitch = this.sharedPreferences.getBoolean(Constants.DEEP_DARK_GUIDE_SWITCH, false);
        boolean z = this.sharedPreferences.getBoolean(Constants.DEEP_DARK_FOLLOW_SYS_SWITCH, false);
        this.isUIModeFollowSystem = z;
        if (!this.darkModeGuideSwitch) {
            saveDeepDarkSwitch(0);
        } else if (z) {
            saveDeepDarkSwitch(getSysUIMode());
        } else {
            this.currentUIMode.set(this.sharedPreferences.getInt(Constants.DEEP_DARK_MODE, 0));
        }
    }

    public void removeDeepDarkChangeListener(Observer<Integer> observer) {
        if (observer != null) {
            this.liveData.removeObserver(observer);
        }
    }

    public void addDeepDarkChangeListener(LifecycleOwner lifecycleOwner, Observer<Integer> observer) {
        if (lifecycleOwner == null || observer == null) {
            return;
        }
        this.liveData.observe(lifecycleOwner, observer);
    }

    public void addDeepDarkChangeListener(LifecycleOwner lifecycleOwner, Object obj, DeepDarkObserver deepDarkObserver) {
        DeepDarkWrapper deepDarkWrapper = new DeepDarkWrapper(obj, deepDarkObserver);
        if (lifecycleOwner == null || deepDarkObserver == null) {
            return;
        }
        this.liveData.observe(lifecycleOwner, deepDarkWrapper);
    }

    public void addDeepDarkChangeListener(LifecycleOwner lifecycleOwner, Observer<Integer> observer, boolean z) {
        if (z) {
            addDeepDarkChangeListener(lifecycleOwner, observer);
        } else if (lifecycleOwner == null || observer == null) {
        } else {
            addDeepDarkChangeListener(lifecycleOwner, new DeepDarkObserverWrapper(observer, this.liveData));
        }
    }
}
