package com.jingdong.common.babelrn.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.R;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil;
import com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public abstract class RNFragment extends BaseFragment {
    private static final int DISMISS_PROGRESS = 1004;
    private static final int SHOW_PROGRESS = 1003;
    private static final String TAG = "RNFragment";
    private String bundlePath;
    private boolean loadSuccess;
    private AbstractJDReactInitialHelper mAbstractJDReactInitialHelper;
    public FrameLayout mRootView;
    RelativeLayout modal;
    ProgressBar progressBar;
    TextView progressTextView;
    private String reactBundle;
    private String reactModule;
    private Bundle reactParams;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.babelrn.view.RNFragment.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1003) {
                RNFragment.this.showProgressBarNow();
            } else if (i2 != 1004) {
            } else {
                RNFragment.this.dismissProgressBarNow();
            }
        }
    };
    public boolean force = true;
    private int appWidth = 0;

    private TextView getLoadingTextView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        TextView textView = new TextView(this.thisActivity);
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.common_textview_bg_color));
        return textView;
    }

    private ViewGroup getModal() {
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(this.thisActivity);
        this.modal = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.babelrn.view.RNFragment.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    private void newProgressBar() {
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            this.modal.removeView(progressBar);
        }
        try {
            ProgressBar loadingProgressBar = BaseApplication.getLoadingProgressBar();
            this.progressBar = loadingProgressBar;
            this.modal.addView(loadingProgressBar);
        } catch (Throwable unused) {
            TextView textView = this.progressTextView;
            if (textView == null) {
                TextView loadingTextView = getLoadingTextView();
                this.progressTextView = loadingTextView;
                loadingTextView.setText("\u52a0\u8f7d\u4e2d...");
            } else {
                this.modal.removeView(textView);
            }
            this.modal.addView(this.progressTextView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressBar() {
        this.mHandler.removeMessages(1004);
        this.mHandler.removeMessages(1003);
        this.mHandler.sendEmptyMessage(1003);
    }

    public void dismissProgressBar() {
        this.mHandler.removeMessages(1003);
        this.mHandler.removeMessages(1004);
        this.mHandler.sendEmptyMessage(1004);
    }

    public void dismissProgressBarNow() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout == null || rootFrameLayout == null) {
            return;
        }
        rootFrameLayout.removeView(relativeLayout);
        rootFrameLayout.invalidate();
    }

    public AbstractJDReactInitialHelper getJDReactInitialHelper() {
        return this.mAbstractJDReactInitialHelper;
    }

    protected abstract void getReactEntity();

    public ViewGroup getRootFrameLayout() {
        return this.mRootView;
    }

    protected abstract void initView(ReactRootView reactRootView);

    public void invokeDefaultOnBackKey() {
        this.thisActivity.finish();
    }

    protected abstract boolean isOpenLoadingView();

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int appWidth = DPIUtil.getAppWidth(getActivity());
        if (appWidth == 0 || appWidth == this.appWidth) {
            return;
        }
        this.appWidth = appWidth;
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper == null || abstractJDReactInitialHelper.getReactManager() == null) {
            return;
        }
        int appHeight = DPIUtil.getAppHeight(getActivity());
        try {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("screenWidth", appWidth);
            createMap.putInt("screenHeight", appHeight);
            createMap.putBoolean("force", this.force);
            this.mAbstractJDReactInitialHelper.sendEvent("screenSizeChanged", createMap);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public View onCreateViews(LayoutInflater layoutInflater, Bundle bundle) {
        if (this.mRootView == null) {
            JDReactAuraHelper.getInstance().setPackageManger();
            Fresco.clearMemoryCache();
            this.mRootView = new FrameLayout(this.thisActivity);
            this.mRootView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            setIsUseBasePV(false);
            onCreateViews(layoutInflater);
            getReactEntity();
        }
        return this.mRootView;
    }

    protected abstract void onCreateViews(LayoutInflater layoutInflater);

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onDestroy();
        }
        ReactAudioRecordUtil.getInstance().free();
        ReactAudioPlayerUtil.getInstance().free();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        AbstractJDReactInitialHelper abstractJDReactInitialHelper;
        if (i2 == 4) {
            if (this.loadSuccess && (abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper) != null) {
                abstractJDReactInitialHelper.onBackPressed();
                return true;
            }
            this.thisActivity.finish();
            return true;
        } else if (i2 == 82) {
            AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = this.mAbstractJDReactInitialHelper;
            if (abstractJDReactInitialHelper2 != null) {
                return abstractJDReactInitialHelper2.onMenuKeyUp();
            }
            return this.thisActivity.onKeyDown(i2, keyEvent);
        } else {
            return false;
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onPause();
        }
    }

    protected abstract void onReactLoadFail(long j2);

    protected abstract void onReactLoadFail(boolean z);

    protected abstract void onReactLoadSuc(long j2);

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AbstractJDReactInitialHelper abstractJDReactInitialHelper = this.mAbstractJDReactInitialHelper;
        if (abstractJDReactInitialHelper != null) {
            abstractJDReactInitialHelper.onResume();
            if (this.mAbstractJDReactInitialHelper.getCurrentReactContext() != null) {
                try {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("appName", this.reactBundle);
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mAbstractJDReactInitialHelper.getCurrentReactContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("JDReactNativeRefresh", createMap);
                } catch (Exception unused) {
                }
            }
        }
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.jingdong.cleanmvp.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    public void setJDReactModuleInfo(JDReactModuleEntity jDReactModuleEntity) {
        this.reactBundle = jDReactModuleEntity.getmBundleName();
        this.reactModule = jDReactModuleEntity.getmModuleName();
        this.reactParams = jDReactModuleEntity.getmLaunchOptions();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setupLayout() {
        /*
            r18 = this;
            r14 = r18
            android.os.Bundle r0 = r18.getArguments()
            if (r0 == 0) goto L1b
            java.lang.String r1 = r14.bundlePath
            if (r1 != 0) goto L1b
            java.lang.String r1 = "pluginPath"
            java.lang.String r1 = r0.getString(r1)
            r14.bundlePath = r1
            java.lang.String r1 = "version"
            java.lang.String r0 = r0.getString(r1)
            goto L1c
        L1b:
            r0 = 0
        L1c:
            android.content.SharedPreferences r1 = com.jingdong.jdsdk.utils.SharedPreferencesUtil.getSharedPreferences()
            java.lang.String r2 = "react_develop_flag"
            r3 = 0
            boolean r1 = r1.getBoolean(r2, r3)
            r2 = 1
            if (r1 != 0) goto L7b
            java.lang.String r1 = r14.bundlePath
            if (r1 != 0) goto L32
            java.lang.String r1 = ""
            r14.bundlePath = r1
        L32:
            java.io.File r1 = new java.io.File
            java.lang.String r4 = r14.bundlePath
            r1.<init>(r4)
            java.lang.String r4 = r14.reactModule
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L70
            java.lang.String r4 = r14.bundlePath
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L70
            java.lang.String r4 = r14.bundlePath
            java.lang.String r5 = "jdreact"
            boolean r4 = r4.startsWith(r5)
            if (r4 != 0) goto L5a
            boolean r1 = r1.exists()
            if (r1 != 0) goto L5a
            goto L70
        L5a:
            java.lang.String r1 = r14.bundlePath
            java.lang.String r4 = "/data"
            boolean r1 = r1.startsWith(r4)
            if (r1 == 0) goto L65
            goto L7b
        L65:
            java.lang.String r1 = r14.bundlePath
            boolean r1 = r1.startsWith(r5)
            if (r1 == 0) goto L7b
            r10 = 1
            r15 = 1
            goto L7d
        L70:
            java.lang.String r0 = "RNFragment"
            java.lang.String r1 = "module name can't be null"
            com.jingdong.corelib.utils.Log.e(r0, r1)
            r14.onReactLoadFail(r2)
            return
        L7b:
            r10 = 0
            r15 = 0
        L7d:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L85
            java.lang.String r0 = "0.0"
        L85:
            r11 = r0
            com.jingdong.common.babelrn.view.RNFragment$3 r13 = new com.jingdong.common.babelrn.view.RNFragment$3
            com.jingdong.common.BaseActivity r2 = r14.thisActivity
            java.lang.String r3 = r14.reactBundle
            r4 = 0
            java.lang.String r5 = r14.reactModule
            android.os.Bundle r6 = r14.reactParams
            java.lang.String r7 = r14.bundlePath
            com.jingdong.common.babelrn.view.RNFragment$2 r8 = new com.jingdong.common.babelrn.view.RNFragment$2
            r8.<init>()
            r12 = 0
            r16 = 0
            r0 = r13
            r1 = r18
            r9 = r15
            r17 = r15
            r15 = r13
            r13 = r16
            r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r14.mAbstractJDReactInitialHelper = r15
            r3 = r17
            r15.initRootView(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.babelrn.view.RNFragment.setupLayout():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showProgressBarNow() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        ViewGroup modal = getModal();
        newProgressBar();
        if (modal == null || rootFrameLayout == null) {
            return;
        }
        if (modal.getParent() == null) {
            rootFrameLayout.addView(modal, new ViewGroup.LayoutParams(-1, -1));
        }
        rootFrameLayout.invalidate();
    }
}
