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
import android.widget.CheckBox;
import android.widget.FrameLayout;
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
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jdreactFramework.JDReactExtendPackage;
import com.jingdong.common.jdreactFramework.JDReactMessageEvent;
import com.jingdong.common.jdreactFramework.download.PluginListenerNew;
import com.jingdong.common.jdreactFramework.download.PluginLocalDownloadInfo;
import com.jingdong.common.jdreactFramework.download.PluginUpdateInfo;
import com.jingdong.common.jdreactFramework.download.PluginVersion;
import com.jingdong.common.jdreactFramework.download.ReactNativeFileManager;
import com.jingdong.common.jdreactFramework.download.ReactNativeUpdateManager;
import com.jingdong.common.jdreactFramework.utils.AbstractJDReactInitialHelper;
import com.jingdong.common.jdreactFramework.utils.ReactMessageUtils;
import com.jingdong.common.jdreactFramework.utils.ReactSharedPreferenceUtils;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.sample.jshop.utils.JshopShowErrorViewUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.NetUtils;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusException;
import java.io.File;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDReactNativeSignRankingListActivity extends MyActivity implements DefaultHardwareBackBtnHandler, View.OnClickListener {
    private static final int DOWNLOAD_FAILED = 1001;
    private static final int DOWNLOAD_FINISHED = 1002;
    private static final int PAGE_MY_SIGN = 1;
    private static final int PAGE_TODAY_RANK = 0;
    private static final String TAG = "JDReactNativeSignRankingListActivity";
    CheckBox btnMySign;
    CheckBox btnTodayRank;
    private String bundlePath;
    SimpleDraweeView llBtnBack;
    JshopShowErrorViewUtils mJshopShowErrorViewUtils;
    LinearLayout mNoDataView;
    PluginListenerNew mPluginListener;
    private ReactInstanceManager mReactInstanceManager;
    RelativeLayout modal;
    private Bundle openAppParam;
    ProgressBar progressBar;
    TextView progressTextView;
    LinearLayout rdoGroupTitle;
    private ReactRootView reactRootViewMySign;
    private ReactRootView reactRootViewTodayRank;
    FrameLayout rlBlow;
    RelativeLayout rlTop;
    ViewGroup rootFrameLayout;
    private Handler mHandler = new Handler() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.1
        {
            JDReactNativeSignRankingListActivity.this = this;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1001) {
                JDReactNativeSignRankingListActivity.this.dismissProgressBar();
                JDReactNativeSignRankingListActivity.this.showRetryDialog();
            } else if (i2 != 1002) {
            } else {
                JDReactNativeSignRankingListActivity.this.unregistLoadingListener();
                JDReactNativeSignRankingListActivity jDReactNativeSignRankingListActivity = JDReactNativeSignRankingListActivity.this;
                jDReactNativeSignRankingListActivity.mLoadingCompletely = true;
                jDReactNativeSignRankingListActivity.dismissProgressBar();
                PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JDReactNativeSignRankingListActivity.this, JDReactConstant.AVAILABILITY_SIGNRANK);
                if (pluginDir != null && pluginDir.pluginDir != null) {
                    JDReactNativeSignRankingListActivity.this.bundlePath = pluginDir.pluginDir + File.separator + JDReactConstant.AVAILABILITY_SIGNRANK + ".jsbundle";
                    StringBuilder sb = new StringBuilder();
                    sb.append(" bundlePath");
                    sb.append(JDReactNativeSignRankingListActivity.this.bundlePath);
                    Log.e(JDReactNativeSignRankingListActivity.TAG, sb.toString());
                }
                JDReactNativeSignRankingListActivity.this.setupLayout();
            }
        }
    };
    private boolean todayRankStarted = false;
    private boolean mySignStarted = false;
    private String pageType = "signrank";
    private boolean isLogining = false;
    private boolean isOnCreate = true;
    private boolean isFromCreate = false;
    boolean mLoadingCompletely = false;

    private ViewGroup getModal() {
        RelativeLayout relativeLayout = this.modal;
        if (relativeLayout != null) {
            return relativeLayout;
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        this.modal = relativeLayout2;
        relativeLayout2.setOnTouchListener(new View.OnTouchListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.4
            {
                JDReactNativeSignRankingListActivity.this = this;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        return this.modal;
    }

    public static Bundle jsonToBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            bundle.putString(next, jSONObject.getString(next));
        }
        return bundle;
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

    public void selectPage(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            if (!LoginUserBase.hasLogin()) {
                this.isLogining = true;
                this.btnMySign.setChecked(false);
            }
            try {
                LoginUser.getInstance().executeLoginRunnable(e.b().a(), new Runnable() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.8
                    {
                        JDReactNativeSignRankingListActivity.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JDReactNativeSignRankingListActivity.this.isLogining = false;
                        JDReactNativeSignRankingListActivity.this.btnMySign.setChecked(true);
                        JDReactNativeSignRankingListActivity.this.btnTodayRank.setChecked(false);
                        JDReactNativeSignRankingListActivity.this.reactRootViewTodayRank.setVisibility(8);
                        JDReactNativeSignRankingListActivity.this.reactRootViewMySign.setVisibility(0);
                        if (JDReactNativeSignRankingListActivity.this.mySignStarted) {
                            return;
                        }
                        JDReactNativeSignRankingListActivity.this.mySignStarted = true;
                        Bundle bundle = new Bundle();
                        bundle.putBundle("openAppParam", JDReactNativeSignRankingListActivity.this.openAppParam);
                        JDReactNativeSignRankingListActivity.this.reactRootViewMySign.startReactApplication(JDReactNativeSignRankingListActivity.this.mReactInstanceManager, "MySignList", bundle);
                        JDReactNativeSignRankingListActivity jDReactNativeSignRankingListActivity = JDReactNativeSignRankingListActivity.this;
                        JDMtaUtils.sendCommonData(jDReactNativeSignRankingListActivity, "MShopCheckIn_MyCheckInTab", "0", "", jDReactNativeSignRankingListActivity, "", "", "", "Shop_CheckinRanking", "");
                    }
                });
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        this.btnTodayRank.setChecked(true);
        this.btnMySign.setChecked(false);
        this.reactRootViewTodayRank.setVisibility(0);
        this.reactRootViewMySign.setVisibility(8);
        if (this.todayRankStarted) {
            return;
        }
        this.todayRankStarted = true;
        Bundle bundle = new Bundle();
        bundle.putBundle("openAppParam", this.openAppParam);
        this.reactRootViewTodayRank.startReactApplication(this.mReactInstanceManager, "SignRankList", bundle);
        Log.d(TAG, "reactRootViewTodayRank startReactApplication  complete");
        if (this.isOnCreate) {
            return;
        }
        JDMtaUtils.sendCommonData(this, "MShopCheckIn_TodayListTab", "0", "", this, "", "", "", "Shop_CheckinRanking", "");
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
        PluginListenerNew pluginListenerNew2 = new PluginListenerNew() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.2
            {
                JDReactNativeSignRankingListActivity.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onDownloadProgressChanged(int i2) {
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFailure(String str, String str2, boolean z, String str3) {
                JDReactNativeSignRankingListActivity.this.mHandler.removeMessages(1001);
                JDReactNativeSignRankingListActivity.this.mHandler.sendEmptyMessage(1001);
            }

            @Override // com.jingdong.common.jdreactFramework.download.PluginListenerNew
            public void onFinish(PluginUpdateInfo pluginUpdateInfo) {
                JDReactNativeSignRankingListActivity jDReactNativeSignRankingListActivity = JDReactNativeSignRankingListActivity.this;
                if (jDReactNativeSignRankingListActivity.mLoadingCompletely) {
                    return;
                }
                jDReactNativeSignRankingListActivity.mHandler.removeMessages(1002);
                JDReactNativeSignRankingListActivity.this.mHandler.sendEmptyMessage(1002);
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

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.ck, R.anim.ag);
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
        PluginLocalDownloadInfo downLoadingStatus = ReactSharedPreferenceUtils.getDownLoadingStatus(JDReactConstant.AVAILABILITY_SIGNRANK);
        boolean z = true;
        if (ReactNativeUpdateManager.getInstance().isItForceUpdate(JDReactConstant.AVAILABILITY_SIGNRANK)) {
            if (TextUtils.equals(JDReactConstant.IN_PROGRESS, downLoadingStatus.status)) {
                ReactNativeUpdateManager.getInstance().addForceDownloadTaskListener(JDReactConstant.AVAILABILITY_SIGNRANK, createLoadingListener());
            } else {
                if (TextUtils.equals("success", downLoadingStatus.status)) {
                    this.mLoadingCompletely = true;
                } else if (!NetUtils.isNetworkAvailable()) {
                    showRetryDialog();
                    return;
                } else if (!ReactNativeUpdateManager.getInstance().addForceDownloadTask(JDReactConstant.AVAILABILITY_SIGNRANK, true, createLoadingListener())) {
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
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostDestroy();
            this.mReactInstanceManager.destroy();
            this.mReactInstanceManager = null;
        }
        FrameLayout frameLayout = this.rlBlow;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        ReactRootView reactRootView = this.reactRootViewTodayRank;
        if (reactRootView != null) {
            reactRootView.unmountReactApplication();
            this.reactRootViewTodayRank = null;
        }
        ReactRootView reactRootView2 = this.reactRootViewMySign;
        if (reactRootView2 != null) {
            reactRootView2.unmountReactApplication();
            this.reactRootViewMySign = null;
        }
    }

    public void onEventMainThread(JDReactMessageEvent jDReactMessageEvent) {
        Log.e(TAG, "event is " + jDReactMessageEvent.getType());
        if ("Event.SwtichToRankTab".equalsIgnoreCase(jDReactMessageEvent.getType())) {
            selectPage(0);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        ReactInstanceManager reactInstanceManager;
        if (i2 == 82 && (reactInstanceManager = this.mReactInstanceManager) != null) {
            reactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostPause();
        }
        EventBus.getDefault().unregister(this);
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        ReactInstanceManager reactInstanceManager = this.mReactInstanceManager;
        if (reactInstanceManager != null) {
            reactInstanceManager.onHostResume(this, this);
            if (!this.isFromCreate) {
                ReactMessageUtils.send(this.mReactInstanceManager.getCurrentReactContext(), "event_onresume", null);
            } else {
                this.isFromCreate = false;
            }
        }
        if (this.isLogining) {
            this.isLogining = false;
            selectPage(0);
        }
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
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
        String stringExtra;
        Log.d(TAG, "activity begin create");
        setContentView(R.layout.reactnative_layout_ranklist);
        this.btnTodayRank = (CheckBox) findViewById(R.id.btnTodayRank);
        this.btnMySign = (CheckBox) findViewById(R.id.btnMySign);
        this.llBtnBack = (SimpleDraweeView) findViewById(R.id.ab9);
        this.rdoGroupTitle = (LinearLayout) findViewById(R.id.rdoGroupTitle);
        this.rlTop = (RelativeLayout) findViewById(R.id.ab8);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.rlBlow);
        try {
            EventBus.getDefault().register(this);
        } catch (EventBusException unused) {
            Log.e(TAG, "duplicated event");
        }
        Intent intent = getIntent();
        if (intent != null) {
            if (this.bundlePath == null) {
                this.bundlePath = intent.getStringExtra("pluginPath");
            }
            Bundle bundleExtra = intent.getBundleExtra("openAppParam");
            this.openAppParam = bundleExtra;
            if (bundleExtra == null && (stringExtra = intent.getStringExtra("openAppParam")) != null) {
                try {
                    this.openAppParam = jsonToBundle(new JSONObject(stringExtra));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Bundle bundle = this.openAppParam;
            if (bundle != null) {
                this.pageType = bundle.getString(WebPerfManager.PAGE_TYPE);
            }
        }
        if (!TextUtils.isEmpty(this.bundlePath)) {
            PluginVersion pluginDir = ReactNativeFileManager.getPluginDir(JdSdk.getInstance().getApplication(), JDReactConstant.AVAILABILITY_SIGNRANK);
            if (!pluginDir.full) {
                if (pluginDir.isPreset) {
                    this.bundlePath = "assets://" + this.bundlePath;
                }
                this.mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setSeperateBundleFileName(this.bundlePath, "assets://jdreact/JDReactCommon/JDReactCommon.jsbundle").setJSMainModulePath("jsbundles/JDReactShopSignRankList").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else if (pluginDir.isPreset) {
                this.mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setBundleAssetName(this.bundlePath).setJSMainModulePath("jsbundles/JDReactShopSignRankList").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            } else {
                this.mReactInstanceManager = ReactInstanceManager.builder().setApplication(JdSdk.getInstance().getApplication()).setJSBundleFile(this.bundlePath).setJSMainModulePath("jsbundles/JDReactShopSignRankList").addPackage(new MainReactPackage()).addPackage(new JDReactExtendPackage()).setCurrentActivity(AbstractJDReactInitialHelper.getCurrentMyActivity()).setUseDeveloperSupport(CommonBase.getJdSharedPreferences().getBoolean(JDReactConstant.JDREACT_DEVELOP_FLAG, false)).setInitialLifecycleState(LifecycleState.BEFORE_RESUME).build();
            }
            Log.d(TAG, "mReactInstanceManager build complete");
            this.reactRootViewTodayRank = new ReactRootView(JdSdk.getInstance().getApplication());
            this.reactRootViewMySign = new ReactRootView(JdSdk.getInstance().getApplication());
            frameLayout.addView(this.reactRootViewTodayRank, -1, -1);
            frameLayout.addView(this.reactRootViewMySign, -1, -1);
            this.llBtnBack.setOnClickListener(this);
            setPageId("Shop_CheckinRanking");
            this.isOnCreate = true;
            this.isFromCreate = true;
            this.btnTodayRank.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.6
                {
                    JDReactNativeSignRankingListActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDReactNativeSignRankingListActivity.this.isOnCreate = false;
                    JDReactNativeSignRankingListActivity.this.selectPage(0);
                }
            });
            this.btnMySign.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.7
                {
                    JDReactNativeSignRankingListActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JDReactNativeSignRankingListActivity.this.isOnCreate = false;
                    JDReactNativeSignRankingListActivity.this.selectPage(1);
                }
            });
            if ("mysign".equals(this.pageType)) {
                selectPage(1);
            } else {
                selectPage(0);
            }
            JDMtaUtils.sendCommonData(this, "MShopCheckIn_TodayListTab", "1", "", this, "", "", "", "Shop_CheckinRanking", "");
            return;
        }
        finish();
    }

    public void showNoNetworkDialog() {
        final JDDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(this, "waring", "\u7f51\u7edc\u4e0d\u53ef\u7528", "\u9000\u51fa");
        createJdDialogWithStyle5.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.3
            {
                JDReactNativeSignRankingListActivity.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                createJdDialogWithStyle5.dismiss();
                JDReactNativeSignRankingListActivity.this.finish();
            }
        });
        createJdDialogWithStyle5.show();
    }

    public void showRetryDialog() {
        if (this.mNoDataView == null) {
            LinearLayout linearLayout = new LinearLayout(this);
            this.mNoDataView = linearLayout;
            if (this.mJshopShowErrorViewUtils == null) {
                this.mJshopShowErrorViewUtils = new JshopShowErrorViewUtils(this, linearLayout);
            }
            this.mJshopShowErrorViewUtils.getErrorViewHasRetry(new View.OnClickListener() { // from class: com.jingdong.common.jdreactFramework.activities.JDReactNativeSignRankingListActivity.5
                {
                    JDReactNativeSignRankingListActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (NetUtils.isNetworkAvailable()) {
                        JDReactNativeSignRankingListActivity.this.removeDialog();
                        ReactNativeUpdateManager.getInstance().addForceDownloadTask(JDReactConstant.AVAILABILITY_SIGNRANK, true, JDReactNativeSignRankingListActivity.this.createLoadingListener());
                        JDReactNativeSignRankingListActivity.this.showProgressBar();
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
            ReactNativeUpdateManager.getInstance().unregistForceDownloadTaskListener(JDReactConstant.AVAILABILITY_SIGNRANK, this.mPluginListener);
            this.mPluginListener = null;
        }
    }
}
