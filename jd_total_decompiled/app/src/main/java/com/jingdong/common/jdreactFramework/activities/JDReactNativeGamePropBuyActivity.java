package com.jingdong.common.jdreactFramework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginLocalDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.NetUtils;
import java.io.File;

/* loaded from: classes5.dex */
public class JDReactNativeGamePropBuyActivity extends MyActivity implements DefaultHardwareBackBtnHandler, View.OnClickListener {
    private static final int DOWNLOAD_FAILED = 1001;
    private static final int DOWNLOAD_FINISHED = 1002;
    private static final String TAG = "JDReactNativeCommonActivity";
    private static String bundlePath;
    private static ReactInstanceManager mReactInstanceManager;
    SimpleDraweeView llBtnBack;
    JshopShowErrorViewUtils mJshopShowErrorViewUtils;
    LinearLayout mNoDataView;
    PluginListenerNew mPluginListener;
    RelativeLayout modal;
    ProgressBar progressBar;
    TextView progressTextView;
    ReactRootView reactRootView;
    ViewGroup rootFrameLayout;
    TextView titleText;
    boolean mLoadingCompletely = false;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeGamePropBuyActivity.1
        {
            JDReactNativeGamePropBuyActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1001) {
                JDReactNativeGamePropBuyActivity.this.dismissProgressBar();
                JDReactNativeGamePropBuyActivity.this.showRetryDialog();
            } else if (i2 != 1002) {
            } else {
                JDReactNativeGamePropBuyActivity.this.unregistLoadingListener();
                JDReactNativeGamePropBuyActivity jDReactNativeGamePropBuyActivity = JDReactNativeGamePropBuyActivity.this;
                jDReactNativeGamePropBuyActivity.mLoadingCompletely = true;
                jDReactNativeGamePropBuyActivity.dismissProgressBar();
                PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactNativeGamePropBuyActivity.this, JDReactConstant.AVAILABILITY_GAME_PROP);
                if (pluginDir != null && pluginDir.pluginDir != null) {
                    String unused = JDReactNativeGamePropBuyActivity.bundlePath = pluginDir.pluginDir + File.separator + JDReactConstant.AVAILABILITY_GAME_PROP + ".jsbundle";
                    StringBuilder sb = new StringBuilder();
                    sb.append(" bundlePath");
                    sb.append(JDReactNativeGamePropBuyActivity.bundlePath);
                    Log.e(JDReactNativeGamePropBuyActivity.TAG, sb.toString());
                }
                JDReactNativeGamePropBuyActivity.this.setupLayout();
            }
        }
    };

    private ViewGroup getModal() {
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        this.modal = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeGamePropBuyActivity.3
            {
                JDReactNativeGamePropBuyActivity.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    public static ReactInstanceManager getmReactInstanceManager() {
        if (mReactInstanceManager == null && !TextUtils.isEmpty(bundlePath)) {
            PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JdSdk.getInstance().getApplication(), JDReactConstant.AVAILABILITY_GAME_PROP);
            if (!pluginDir.full) {
                if (pluginDir.isPreset) {
                    bundlePath = "assets://" + bundlePath;
                }
                mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setSeperateBundleFileName(bundlePath, "assets://jdreact/JDReactCommon/JDReactCommon.jsbundle").setJSMainModulePath("jsbundles/JDReactGamePropBuy").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else if (pluginDir.isPreset) {
                mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setBundleAssetName(bundlePath).setJSMainModulePath("jsbundles/JDReactGamePropBuy").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setJSBundleFile(bundlePath).setJSMainModulePath("jsbundles/JDReactGamePropBuy").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
        }
        return mReactInstanceManager;
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

    public void showProgressBar() {
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

    public PluginListenerNew createLoadingListener() {
        PluginListenerNew pluginListenerNew = this.mPluginListener;
        if (pluginListenerNew != null) {
            return pluginListenerNew;
        }
        PluginListenerNew pluginListenerNew2 = new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeGamePropBuyActivity.2
            {
                JDReactNativeGamePropBuyActivity.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str, String str2, boolean z, String str3) {
                JDReactNativeGamePropBuyActivity.this.mHandler.removeMessages(1001);
                JDReactNativeGamePropBuyActivity.this.mHandler.sendEmptyMessage(1001);
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                JDReactNativeGamePropBuyActivity jDReactNativeGamePropBuyActivity = JDReactNativeGamePropBuyActivity.this;
                if (jDReactNativeGamePropBuyActivity.mLoadingCompletely) {
                    return;
                }
                jDReactNativeGamePropBuyActivity.mHandler.removeMessages(1002);
                JDReactNativeGamePropBuyActivity.this.mHandler.sendEmptyMessage(1002);
            }
        };
        this.mPluginListener = pluginListenerNew2;
        return pluginListenerNew2;
    }

    public void dismissProgressBar() {
        ViewGroup rootFrameLayout = getRootFrameLayout();
        ViewGroup modal = getModal();
        if (modal == null || rootFrameLayout == null) {
            return;
        }
        rootFrameLayout.removeView(modal);
        rootFrameLayout.invalidate();
    }

    public void dismissRetryDialog() {
        removeDialog();
    }

    public TextView getLoadingTextView() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        TextView textView = new TextView(this);
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(17);
        textView.setBackgroundColor(getResources().getColor(R.color.jm));
        return textView;
    }

    @Override // com.jingdong.common.BaseActivity
    public ViewGroup getRootFrameLayout() {
        ViewGroup viewGroup = this.rootFrameLayout;
        if (viewGroup != null) {
            return viewGroup;
        }
        ViewGroup viewGroup2 = (ViewGroup) getWindow().peekDecorView();
        this.rootFrameLayout = viewGroup2;
        if (viewGroup2 == null) {
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
            }
            this.rootFrameLayout = getRootFrameLayout();
        }
        return this.rootFrameLayout;
    }

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.ab9) {
            return;
        }
        finish();
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PluginLocalDownloadInfo downLoadingStatus = ReactSharedPreferenceUtils.getDownLoadingStatus(JDReactConstant.AVAILABILITY_GAME_PROP);
        boolean z = true;
        if (ReactNativeUpdateManager.getInstance().isItForceUpdate(JDReactConstant.AVAILABILITY_GAME_PROP)) {
            if (TextUtils.equals(JDReactConstant.IN_PROGRESS, downLoadingStatus.status)) {
                ReactNativeUpdateManager.getInstance().addForceDownloadTaskListener(JDReactConstant.AVAILABILITY_GAME_PROP, createLoadingListener());
            } else {
                if (TextUtils.equals("success", downLoadingStatus.status)) {
                    this.mLoadingCompletely = true;
                } else if (!NetUtils.isNetworkAvailable()) {
                    showRetryDialog();
                    return;
                } else if (!ReactNativeUpdateManager.getInstance().addForceDownloadTask(JDReactConstant.AVAILABILITY_GAME_PROP, true, createLoadingListener())) {
                    this.mLoadingCompletely = true;
                }
                z = false;
            }
            if (z) {
                showProgressBar();
                return;
            } else {
                setupLayout();
                return;
            }
        }
        this.mLoadingCompletely = true;
        setupLayout();
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregistLoadingListener();
        dismissRetryDialog();
        dismissProgressBar();
        if (this.mLoadingCompletely) {
            ReactRootView reactRootView = this.reactRootView;
            if (reactRootView != null) {
                reactRootView.unmountReactApplication();
                this.reactRootView.removeAllViews();
            }
            ReactInstanceManager reactInstanceManager = mReactInstanceManager;
            if (reactInstanceManager != null) {
                reactInstanceManager.onHostDestroy();
                mReactInstanceManager.destroy();
                mReactInstanceManager = null;
            }
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            finish();
            return true;
        }
        return false;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager;
        if (i2 == 82 && (reactInstanceManager = mReactInstanceManager) != null) {
            reactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        ReactInstanceManager reactInstanceManager;
        super.onPause();
        if (this.mLoadingCompletely && (reactInstanceManager = mReactInstanceManager) != null) {
            reactInstanceManager.onHostPause();
        }
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        ReactInstanceManager reactInstanceManager;
        super.onResume();
        if (this.mLoadingCompletely && (reactInstanceManager = mReactInstanceManager) != null) {
            reactInstanceManager.onHostResume(this, this);
        }
    }

    public void removeDialog() {
        LinearLayout linearLayout;
        ViewGroup rootFrameLayout = getRootFrameLayout();
        if (rootFrameLayout == null || (linearLayout = this.mNoDataView) == null || linearLayout.getParent() == null) {
            return;
        }
        rootFrameLayout.removeView(this.mNoDataView);
    }

    public void setupLayout() {
        setContentView(R.layout.reactnative_layout_game_buy);
        this.llBtnBack = (SimpleDraweeView) findViewById(R.id.ab9);
        this.reactRootView = (ReactRootView) findViewById(R.id.reactRootView);
        this.titleText = (TextView) findViewById(R.id.ab_);
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        if (intent != null) {
            if (bundlePath == null) {
                bundlePath = intent.getStringExtra("pluginPath");
            }
            bundle.putString("name", intent.getStringExtra("name"));
            bundle.putString("buyCount", "" + intent.getIntExtra("buyCount", 1));
            bundle.putString("price", intent.getStringExtra("price"));
            bundle.putString("skuId", intent.getStringExtra("skuId"));
        }
        if (!TextUtils.isEmpty(bundlePath)) {
            getmReactInstanceManager();
            Log.d(TAG, "mReactInstanceManager build complete");
            this.llBtnBack.setVisibility(0);
            this.llBtnBack.setOnClickListener(this);
            this.reactRootView.setVisibility(0);
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("gamePropParam", bundle);
            this.reactRootView.startReactApplication(mReactInstanceManager, JDReactConstant.AVAILABILITY_GAME_PROP, bundle2);
            return;
        }
        finish();
    }

    public void showRetryDialog() {
        if (this.mNoDataView == null) {
            LinearLayout linearLayout = new LinearLayout(this);
            this.mNoDataView = linearLayout;
            if (this.mJshopShowErrorViewUtils == null) {
                this.mJshopShowErrorViewUtils = new JshopShowErrorViewUtils(this, linearLayout);
            }
            this.mJshopShowErrorViewUtils.getErrorViewHasRetry(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeGamePropBuyActivity.4
                {
                    JDReactNativeGamePropBuyActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NetUtils.isNetworkAvailable()) {
                        JDReactNativeGamePropBuyActivity.this.removeDialog();
                        ReactNativeUpdateManager.getInstance().addForceDownloadTask(JDReactConstant.AVAILABILITY_GAME_PROP, true, JDReactNativeGamePropBuyActivity.this.createLoadingListener());
                        JDReactNativeGamePropBuyActivity.this.showProgressBar();
                    }
                }
            });
            this.mJshopShowErrorViewUtils.setMessageInfo(getString(R.string.jshop_net_fail), getString(R.string.jshop_net_check), "");
            this.mJshopShowErrorViewUtils.setErrorImage(R.drawable.y_04);
            if (getRootFrameLayout() == null) {
                return;
            }
        }
        if (this.mNoDataView.getParent() == null) {
            this.mNoDataView.setVisibility(0);
            this.rootFrameLayout.addView(this.mNoDataView, new ViewGroup.LayoutParams(-1, -1));
        }
        this.rootFrameLayout.invalidate();
    }

    public void unregistLoadingListener() {
        if (this.mPluginListener != null) {
            ReactNativeUpdateManager.getInstance().unregistForceDownloadTaskListener(JDReactConstant.AVAILABILITY_GAME_PROP, this.mPluginListener);
            this.mPluginListener = null;
        }
    }
}
