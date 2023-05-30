package com.jd.cashier.app.jdlibcutter.impl.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.R;
import com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.db.AppStateType;
import com.jd.stat.security.jma.JMA;
import com.jingdong.common.kepler.DragView;
import com.jingdong.common.kepler.KeplerJumpUtils;
import com.jingdong.common.unification.statusbar.AndroidWorkaround;
import com.jingdong.common.unification.statusbar.IStatusController;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;
import com.jingdong.common.unification.uniutil.UnAndroidUtils;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CacheTimeUtil;
import com.jingdong.common.utils.CompatOThemeUtils;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.NightModeUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JDSoftReference;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.b.a;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.cache.GlobalImageCache;
import com.jingdong.lib.monitor.MonitorInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes13.dex */
public class ActivityConnectorImpl implements IActivityConnector, IStatusController {
    private static final String DISPOSEABLE_UNABLE_ANIM = "disposableUdnableAnim";
    private static final String TAG = "ActivityLifeCycleImpl";
    private static final String[] mIgnoreModels = {"MI-ONE Plus"};
    private DragView dragView;
    private boolean isDisposeableUnableExitAnim;
    private RelativeLayout layout;
    private JDSoftReference<Activity> mSoftReference;
    private View model;
    private FrameLayout noNetTip;
    private ViewGroup rootFrameLayout;
    private KeplerJumpUtils.ShowOrHideCallback showOrHideCallback;
    private long stopTime;
    private boolean touchFlag = true;
    private boolean mStatusBarTintEnable = true;
    private boolean mStatusBarTransparentEnable = false;
    private boolean isNeedKeplerFlowDragView = true;

    private void checkNetwork(FragmentActivity fragmentActivity) {
        if (NetUtils.isNetworkAvailable()) {
            hideModel(fragmentActivity);
        } else {
            showModel(fragmentActivity);
        }
    }

    @SuppressLint({"NewApi"})
    private View getModel(final FragmentActivity fragmentActivity) {
        int dip2px = DPIUtil.dip2px(59.0f) + getStatusHeight(fragmentActivity);
        if (this.model == null) {
            try {
                this.model = ImageUtil.inflate(R.layout.jdlibcutter_app_network_model, null);
            } catch (Throwable th) {
                if (OKLog.E) {
                    OKLog.e(TAG, th);
                }
            }
        }
        View view = this.model;
        if (view == null) {
            return null;
        }
        if (this.layout == null) {
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.app_network_model_layout);
            this.layout = relativeLayout;
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jd.cashier.app.jdlibcutter.impl.activity.ActivityConnectorImpl.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Intent intent = new Intent("android.settings.SETTINGS");
                    if (fragmentActivity.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                        fragmentActivity.startActivity(intent);
                        return;
                    }
                    FragmentActivity fragmentActivity2 = fragmentActivity;
                    ToastUtils.shortToast(fragmentActivity2, fragmentActivity2.getString(R.string.jdlibcutter_can_not_enter_network_setting_toast));
                }
            });
        }
        if (this.noNetTip == null) {
            FrameLayout frameLayout = (FrameLayout) this.model.findViewById(R.id.un_base_no_net);
            this.noNetTip = frameLayout;
            frameLayout.setPadding(frameLayout.getPaddingLeft(), dip2px, this.noNetTip.getPaddingRight(), this.noNetTip.getPaddingBottom());
        }
        return this.model;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ViewGroup getRootFrameLayout(FragmentActivity fragmentActivity) {
        ViewGroup viewGroup = this.rootFrameLayout;
        if (viewGroup != null) {
            return viewGroup;
        }
        ViewGroup viewGroup2 = (ViewGroup) fragmentActivity.getWindow().peekDecorView();
        this.rootFrameLayout = viewGroup2;
        if (viewGroup2 == null) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            this.rootFrameLayout = getRootFrameLayout(fragmentActivity);
        }
        return this.rootFrameLayout;
    }

    private int getStatusHeight(FragmentActivity fragmentActivity) {
        int i2;
        int identifier;
        if (fragmentActivity != null) {
            Rect rect = new Rect();
            fragmentActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            i2 = rect.top;
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            try {
                Class<?> cls = Class.forName("com.android.internal.R$dimen");
                i2 = fragmentActivity.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
            } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchFieldException e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
        return (i2 != 0 || (identifier = fragmentActivity.getResources().getIdentifier("status_bar_height", "dimen", "android")) <= 0) ? i2 : fragmentActivity.getResources().getDimensionPixelSize(identifier);
    }

    private void handleOnResume(FragmentActivity fragmentActivity) {
        try {
            NightModeUtil.setNightModeAlpha(fragmentActivity);
            checkNetwork(fragmentActivity);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private void hideModel(FragmentActivity fragmentActivity) {
        ViewGroup rootFrameLayout = getRootFrameLayout(fragmentActivity);
        View model = getModel(fragmentActivity);
        if (model == null || rootFrameLayout.indexOfChild(model) == -1) {
            return;
        }
        rootFrameLayout.removeView(model);
        rootFrameLayout.invalidate();
    }

    private void initHardAcclCheck(FragmentActivity fragmentActivity) {
        Object obj;
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
            String deviceModel = BaseInfo.getDeviceModel();
            boolean z = false;
            for (String str : mIgnoreModels) {
                if (str.equalsIgnoreCase(deviceModel)) {
                    return;
                }
            }
            try {
                Bundle bundle = fragmentActivity.getPackageManager().getActivityInfo(fragmentActivity.getComponentName(), 128).metaData;
                if (bundle != null && (obj = bundle.get("hardwareAccelerated")) != null) {
                    if (!((Boolean) obj).booleanValue()) {
                        z = true;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (z || !shouldOpenHardwareAccelerate()) {
                return;
            }
            openHardAccl(fragmentActivity);
        }
    }

    private boolean isStatusBarTintEnable(FragmentActivity fragmentActivity) {
        return UnStatusBarTintUtil.isEnable((Activity) fragmentActivity) && this.mStatusBarTintEnable;
    }

    private void openHardAccl(FragmentActivity fragmentActivity) {
        try {
            fragmentActivity.getWindow().setFlags(16777216, 16777216);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private void setStatusBarTint(FragmentActivity fragmentActivity) {
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) && isStatusBarTintEnable(fragmentActivity)) {
            if (statusBarTransparentEnable()) {
                UnStatusBarTintUtil.setStatusBar4Base(fragmentActivity, 1, false);
            } else {
                UnStatusBarTintUtil.setStatusBar4Base(fragmentActivity, statusBarHint(), false);
            }
            AndroidWorkaround.assistActivity(fragmentActivity.findViewById(16908290), fragmentActivity);
        }
    }

    private boolean shouldOpenHardwareAccelerate() {
        return ABTestUtils.useHardwareAccelerate();
    }

    private void showKeplerDragView(final FragmentActivity fragmentActivity) {
        if (this.isNeedKeplerFlowDragView) {
            if (this.showOrHideCallback == null) {
                this.showOrHideCallback = new KeplerJumpUtils.ShowOrHideCallback() { // from class: com.jd.cashier.app.jdlibcutter.impl.activity.ActivityConnectorImpl.1
                    @Override // com.jingdong.common.kepler.KeplerJumpUtils.ShowOrHideCallback
                    public void showOrHide(boolean z) {
                        KeplerJumpUtils.tryHideFlowBackView(ActivityConnectorImpl.this.getRootFrameLayout(fragmentActivity), ActivityConnectorImpl.this.dragView);
                        if (KeplerJumpUtils.canShownRetainView()) {
                            try {
                                Class<?> cls = Class.forName("com.jingdong.common.XView.RetainXViewHelper");
                                cls.getMethod("closeRetainXView", new Class[0]).invoke(cls, new Object[0]);
                            } catch (Exception e2) {
                                if (Log.D) {
                                    Log.d("KeplerJumpUtils", "Exception:" + e2.toString());
                                }
                            }
                        }
                    }
                };
            }
            KeplerJumpUtils.tryHideFlowBackView(getRootFrameLayout(fragmentActivity), this.dragView);
            this.dragView = KeplerJumpUtils.tryShowFlowBackView(fragmentActivity, getRootFrameLayout(fragmentActivity), this.showOrHideCallback);
        }
    }

    private void showModel(FragmentActivity fragmentActivity) {
        ViewGroup rootFrameLayout = getRootFrameLayout(fragmentActivity);
        View model = getModel(fragmentActivity);
        if (model != null && rootFrameLayout.indexOfChild(model) == -1) {
            rootFrameLayout.addView(model);
            rootFrameLayout.invalidate();
        }
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public String getServerConfigValue() {
        return ABTestUtils.useStatusBarTint() ? "1" : "0";
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void initStatus() {
        UnStatusBarTintUtil.init(this);
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean isDisplayCutout() {
        return UnAndroidUtils.isDisplayCutout();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onClearBitmapCache() {
        try {
            GlobalImageCache.getLruBitmapCache().clean();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onCreate(FragmentActivity fragmentActivity) {
        try {
            MonitorInfo.setRunStage(1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            if (fragmentActivity.getIntent() != null && fragmentActivity.getIntent().hasExtra("disposableUdnableAnim") && fragmentActivity.getIntent().getBooleanExtra("disposableUdnableAnim", false)) {
                int i2 = R.anim.jdlibcutter_activity_nothing;
                fragmentActivity.overridePendingTransition(i2, i2);
            } else if (ABTestUtils.useUniActivityAnim()) {
                fragmentActivity.overridePendingTransition(R.anim.jdlibcutter_activity_right_in, R.anim.jdlibcutter_activity_left_out);
            }
            JDSoftReference<Activity> jDSoftReference = new JDSoftReference<>(fragmentActivity);
            this.mSoftReference = jDSoftReference;
            jDSoftReference.setTag(getClass().getName());
            a.d("", fragmentActivity.toString() + String.format("(%d)", 0));
            if (Build.VERSION.SDK_INT != 26 || fragmentActivity.getApplicationInfo().targetSdkVersion <= 26 || !CompatOThemeUtils.isTranslucentOrFloating(fragmentActivity)) {
                fragmentActivity.setRequestedOrientation(1);
            }
        } catch (Exception unused) {
            int i3 = R.anim.jdlibcutter_activity_nothing;
            fragmentActivity.overridePendingTransition(i3, i3);
        }
        try {
            ((InputMethodManager) fragmentActivity.getSystemService("input_method")).hideSoftInputFromWindow(fragmentActivity.getWindow().getDecorView().getWindowToken(), 0);
            initHardAcclCheck(fragmentActivity);
            AdvertUtils.dealFltOnPageStart(getClass().getSimpleName());
            if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                ThemeTitleDataController.getInstance().getThemeTitleData(1);
                JDSkinSDK.getInstance().getResData(AppStateType.APP_CHANGE_ACTIVITY);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onDestroy(FragmentActivity fragmentActivity) {
        try {
            JMA.stopTrackView(getClass().getCanonicalName());
            Window window = fragmentActivity.getWindow();
            if (window != null) {
                window.setBackgroundDrawable(null);
            }
            this.showOrHideCallback = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onFinish(FragmentActivity fragmentActivity) {
        if (this.isDisposeableUnableExitAnim) {
            this.isDisposeableUnableExitAnim = false;
            int i2 = R.anim.jdlibcutter_activity_nothing;
            fragmentActivity.overridePendingTransition(i2, i2);
        } else if (ABTestUtils.useUniActivityAnim()) {
            fragmentActivity.overridePendingTransition(R.anim.jdlibcutter_activity_left_in, R.anim.jdlibcutter_activity_right_out);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onPause(FragmentActivity fragmentActivity) {
        JDMtaUtils.onPause();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onRestart(FragmentActivity fragmentActivity) {
        try {
            a.d("", toString() + String.format("(%d)", 1));
            if (System.currentTimeMillis() - this.stopTime > 86400000) {
                CacheTimeUtil.queryCacheTime();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onResume(FragmentActivity fragmentActivity) {
        try {
            JDMtaUtils.onResume(fragmentActivity);
            handleOnResume(fragmentActivity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onStart(FragmentActivity fragmentActivity) {
        showKeplerDragView(fragmentActivity);
        if (this.touchFlag) {
            JMA.addTrackView(fragmentActivity);
            this.touchFlag = false;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void onStop(FragmentActivity fragmentActivity) {
        this.stopTime = System.currentTimeMillis();
        if (this.isNeedKeplerFlowDragView) {
            KeplerJumpUtils.tryHideFlowBackView(getRootFrameLayout(fragmentActivity), this.dragView);
            try {
                Class<?> cls = Class.forName("com.jingdong.common.XView.RetainXViewHelper");
                cls.getMethod("closeRetainXView", new Class[0]).invoke(cls, new Object[0]);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d("KeplerJumpUtils", "Exception:" + e2.toString());
                }
            }
        }
        JMA.flush();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void setContentView(FragmentActivity fragmentActivity) {
        setStatusBarTint(fragmentActivity);
        if (this.mStatusBarTintEnable) {
            return;
        }
        UnStatusBarTintUtil.cutout(fragmentActivity);
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void setStatusBarTintEnable(boolean z) {
        this.mStatusBarTintEnable = z;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnector
    public void setStatusBarTransparentEnable(boolean z) {
        this.mStatusBarTransparentEnable = z;
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public int statusBarHint() {
        return -1;
    }

    @Override // com.jingdong.common.unification.statusbar.IStatusController
    public boolean statusBarTransparentEnable() {
        return this.mStatusBarTransparentEnable;
    }
}
