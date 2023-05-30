package com.jingdong.common.babelrn.view;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.R;
import com.jingdong.common.babelrn.module.JDReactBabelPackage;
import com.jingdong.common.jdreactFramework.JDReactAuraHelper;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.preload.JDReactModuleEntity;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.audio.ReactAudioPlayerUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdreact.plugin.audio.ReactAudioRecordUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.io.File;

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
    */
    public void setupLayout() {
        String str;
        int i2;
        boolean z;
        Bundle arguments = getArguments();
        if (arguments == null || this.bundlePath != null) {
            str = null;
        } else {
            this.bundlePath = arguments.getString("pluginPath");
            str = arguments.getString("version");
        }
        if (!SharedPreferencesUtil.getSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)) {
            if (this.bundlePath == null) {
                this.bundlePath = "";
            }
            File file = new File(this.bundlePath);
            if (!TextUtils.isEmpty(this.reactModule) && !TextUtils.isEmpty(this.bundlePath) && (this.bundlePath.startsWith(JDReactConstant.ASSERT_DIR) || file.exists())) {
                if (!this.bundlePath.startsWith("/data") && this.bundlePath.startsWith(JDReactConstant.ASSERT_DIR)) {
                    i2 = 1;
                    z = true;
                    if (TextUtils.isEmpty(str)) {
                        str = "0.0";
                    }
                    AbstractJDReactInitialHelper abstractJDReactInitialHelper = new AbstractJDReactInitialHelper(this.thisActivity, this.reactBundle, null, this.reactModule, this.reactParams, this.bundlePath, new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.babelrn.view.RNFragment.2
                        long loadBeginTime;

                        @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                        public void onReactLoadCancel() {
                            RNFragment.this.dismissProgressBar();
                            RNFragment.this.onReactLoadFail(this.loadBeginTime);
                            Log.v(RNFragment.TAG, "onReactLoadCancel time = " + System.currentTimeMillis());
                        }

                        @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                        public void onReactLoadFinish() {
                            RNFragment.this.dismissProgressBar();
                            if (RNFragment.this.mAbstractJDReactInitialHelper != null) {
                                RNFragment.this.mAbstractJDReactInitialHelper.onResume();
                            }
                            RNFragment.this.onReactLoadSuc(this.loadBeginTime);
                            RNFragment.this.loadSuccess = true;
                            Log.v(RNFragment.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
                        }

                        @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
                        public void onReactLoadStart() {
                            if (RNFragment.this.isOpenLoadingView()) {
                                RNFragment.this.showProgressBar();
                            }
                            this.loadBeginTime = System.currentTimeMillis();
                            Log.v(RNFragment.TAG, "onReactLoadStart time = " + System.currentTimeMillis());
                        }
                    }, z, i2, str, false, false) { // from class: com.jingdong.common.babelrn.view.RNFragment.3
                        @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
                        protected void defaultOnBackPressed() {
                            RNFragment.this.invokeDefaultOnBackKey();
                        }

                        @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
                        protected ReactPackage getDefaultReactPackage() {
                            return AbstractJDReactInitialHelper.getPackageManger();
                        }

                        @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
                        protected ReactPackage getExtendReactPackage() {
                            return new JDReactBabelPackage();
                        }

                        @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
                        protected void initRootView(ReactRootView reactRootView) {
                            RNFragment.this.initView(reactRootView);
                        }
                    };
                    this.mAbstractJDReactInitialHelper = abstractJDReactInitialHelper;
                    abstractJDReactInitialHelper.initRootView(z);
                }
            } else {
                Log.e(TAG, "module name can't be null");
                onReactLoadFail(true);
                return;
            }
        }
        i2 = 0;
        z = false;
        if (TextUtils.isEmpty(str)) {
        }
        AbstractJDReactInitialHelper abstractJDReactInitialHelper2 = new AbstractJDReactInitialHelper(this.thisActivity, this.reactBundle, null, this.reactModule, this.reactParams, this.bundlePath, new ReactInstanceManager.ReactInstanceProgressListener() { // from class: com.jingdong.common.babelrn.view.RNFragment.2
            long loadBeginTime;

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadCancel() {
                RNFragment.this.dismissProgressBar();
                RNFragment.this.onReactLoadFail(this.loadBeginTime);
                Log.v(RNFragment.TAG, "onReactLoadCancel time = " + System.currentTimeMillis());
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadFinish() {
                RNFragment.this.dismissProgressBar();
                if (RNFragment.this.mAbstractJDReactInitialHelper != null) {
                    RNFragment.this.mAbstractJDReactInitialHelper.onResume();
                }
                RNFragment.this.onReactLoadSuc(this.loadBeginTime);
                RNFragment.this.loadSuccess = true;
                Log.v(RNFragment.TAG, "onReactLoadFinish time = " + System.currentTimeMillis());
            }

            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceProgressListener
            public void onReactLoadStart() {
                if (RNFragment.this.isOpenLoadingView()) {
                    RNFragment.this.showProgressBar();
                }
                this.loadBeginTime = System.currentTimeMillis();
                Log.v(RNFragment.TAG, "onReactLoadStart time = " + System.currentTimeMillis());
            }
        }, z, i2, str, false, false) { // from class: com.jingdong.common.babelrn.view.RNFragment.3
            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void defaultOnBackPressed() {
                RNFragment.this.invokeDefaultOnBackKey();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getDefaultReactPackage() {
                return AbstractJDReactInitialHelper.getPackageManger();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected ReactPackage getExtendReactPackage() {
                return new JDReactBabelPackage();
            }

            @Override // com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper
            protected void initRootView(ReactRootView reactRootView) {
                RNFragment.this.initView(reactRootView);
            }
        };
        this.mAbstractJDReactInitialHelper = abstractJDReactInitialHelper2;
        abstractJDReactInitialHelper2.initRootView(z);
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
