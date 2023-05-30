package com.jingdong.common.web.ui;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.adapter.ColorHttpAdapter;
import com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper;
import com.jingdong.common.utils.ClipboardUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.ColorHttpAdapterImpl;
import com.jingdong.common.web.R;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.util.HybridBackdoorLogger;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes12.dex */
public class SecretDoor extends ScrollView implements View.OnClickListener {
    public static boolean shouldSslDialog;
    private JDWebView jdWebView;
    private EditText mCookieEt;
    private TextView mCookieTv;
    private TextView mCoreTv;
    private EditText mHybridIdEt;
    private TextView mHybridResultTv;
    private TextView mPathTv;
    private EditText mSwitchEt;
    private TextView mUATv;
    private EditText mUrlEt;

    public SecretDoor(Context context, JDWebView jDWebView) {
        super(context);
        this.jdWebView = jDWebView;
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(HashMap hashMap) {
        if (hashMap != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : hashMap.keySet()) {
                sb.append(str);
                sb.append(" = ");
                sb.append((String) hashMap.get(str));
                sb.append("\n============\n");
            }
            this.mHybridResultTv.setText(sb.toString());
            return;
        }
        this.mHybridResultTv.setText("no result");
    }

    private void bindDatas() {
        boolean switchBooleanValue = SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.DEBUG_ENABLE, false);
        int tbsSDKVersion = WebView.getTbsSDKVersion(getContext());
        int tbsVersion = QbSdk.getTbsVersion(getContext());
        boolean isSystemCoreNotX5 = this.jdWebView.isSystemCoreNotX5();
        String simpleName = (switchBooleanValue && (getContext() instanceof Activity)) ? ((Activity) getContext()).getClass().getSimpleName() : "";
        Locale locale = Locale.getDefault();
        Object[] objArr = new Object[4];
        objArr[0] = isSystemCoreNotX5 ? "system" : "x5";
        objArr[1] = Integer.valueOf(tbsVersion);
        objArr[2] = Integer.valueOf(tbsSDKVersion);
        objArr[3] = "Page: " + simpleName;
        this.mCoreTv.setText(String.format(locale, "Using %s core. X5 core version:%d, sdkVersion:%d. %s", objArr));
        if (switchBooleanValue) {
            findViewById(R.id.line1).setVisibility(0);
            findViewById(R.id.line2).setVisibility(0);
            findViewById(R.id.line5).setVisibility(0);
            findViewById(R.id.line6).setVisibility(0);
            findViewById(R.id.logBtn).setVisibility(0);
            findViewById(R.id.uaTv).setVisibility(0);
            findViewById(R.id.pathTv).setVisibility(0);
            findViewById(R.id.debugBtn).setVisibility(0);
            findViewById(R.id.hybridNetLL).setVisibility(0);
            findViewById(R.id.hybridPinLL).setVisibility(0);
            String uaInfo = this.jdWebView.getUaInfo();
            this.mUATv.setText("UserAgent:\n" + uaInfo);
            this.mPathTv.setText("Url History:\n" + listToString(this.jdWebView.getUrlHistory()));
            Button button = (Button) findViewById(R.id.bridgeApp);
            if (WebHybridUtils.userAppNetAdatper) {
                button.setText("\u5173\u95edhybrid\u6865\u63a5\u5230\u5bbf\u4e3b");
            } else {
                button.setText("\u6253\u5f00hybrid\u6865\u63a5\u5230\u5bbf\u4e3b");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(final HashMap hashMap) {
        this.mHybridResultTv.post(new Runnable() { // from class: com.jingdong.common.web.ui.s
            @Override // java.lang.Runnable
            public final void run() {
                SecretDoor.this.b(hashMap);
            }
        });
    }

    private void deleteFile(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                for (File file2 : listFiles) {
                    deleteFile(file2);
                }
            }
            file.delete();
        }
    }

    private void deleteHybridDir(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input path");
            return;
        }
        File file = new File(obj);
        try {
            if (!file.exists()) {
                this.mHybridResultTv.setText("path (" + obj + ") is not existed");
            } else {
                deleteFile(file);
                this.mHybridResultTv.setText("path (" + obj + ") deleted");
            }
        } catch (Throwable th) {
            th.printStackTrace();
            this.mHybridResultTv.setText("deleted fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(HashMap hashMap) {
        if (hashMap != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : hashMap.keySet()) {
                sb.append(str);
                sb.append(" = ");
                sb.append((String) hashMap.get(str));
                sb.append("\n============\n");
            }
            this.mHybridResultTv.setText(sb.toString());
            return;
        }
        this.mHybridResultTv.setText("no result");
    }

    private void exit() {
        ((ViewGroup) getParent()).removeView(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(final HashMap hashMap) {
        this.mHybridResultTv.post(new Runnable() { // from class: com.jingdong.common.web.ui.n
            @Override // java.lang.Runnable
            public final void run() {
                SecretDoor.this.f(hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(HashMap hashMap) {
        if (hashMap != null) {
            StringBuilder sb = new StringBuilder();
            for (String str : hashMap.keySet()) {
                sb.append(str);
                sb.append(" = ");
                sb.append((String) hashMap.get(str));
                sb.append("\n============\n");
            }
            this.mHybridResultTv.setText(sb.toString());
            return;
        }
        this.mHybridResultTv.setText("no result");
    }

    private void init() {
        View.inflate(getContext(), R.layout.jd_webview_secretdoor, this);
        setBackgroundColor(-528383090);
        findViewById(R.id.loadBtn).setOnClickListener(this);
        findViewById(R.id.xviewBtn).setOnClickListener(this);
        findViewById(R.id.execJsBtn).setOnClickListener(this);
        findViewById(R.id.fswBtn).setOnClickListener(this);
        findViewById(R.id.clearCacheBtn).setOnClickListener(this);
        int i2 = R.id.cookieBtn;
        findViewById(i2).setOnClickListener(this);
        findViewById(R.id.clearJdComCookieBtn).setOnClickListener(this);
        findViewById(R.id.reloadBtn).setOnClickListener(this);
        findViewById(R.id.logBtn).setOnClickListener(this);
        findViewById(R.id.certificateBtn).setOnClickListener(this);
        findViewById(R.id.exitBtn).setOnClickListener(this);
        findViewById(R.id.copyUrlBtn).setOnClickListener(this);
        findViewById(R.id.clearBtn).setOnClickListener(this);
        findViewById(R.id.switchBtn).setOnClickListener(this);
        int i3 = R.id.uaTv;
        findViewById(i3).setOnClickListener(this);
        int i4 = R.id.pathTv;
        findViewById(i4).setOnClickListener(this);
        findViewById(R.id.debugBtn).setOnClickListener(this);
        findViewById(R.id.hybridLogBtn).setOnClickListener(this);
        findViewById(R.id.hybridHitBtn).setOnClickListener(this);
        findViewById(R.id.hybridDelBuildInVerBtn).setOnClickListener(this);
        findViewById(R.id.hybridDefaultOpenBtn).setOnClickListener(this);
        findViewById(R.id.hybridSwitchBtn).setOnClickListener(this);
        findViewById(R.id.hybridOfflineConfig).setOnClickListener(this);
        findViewById(R.id.hybridCommonConfig).setOnClickListener(this);
        findViewById(R.id.hybridBuildInConfig).setOnClickListener(this);
        findViewById(R.id.hybridRootFile).setOnClickListener(this);
        findViewById(R.id.hybridDelFile).setOnClickListener(this);
        findViewById(R.id.hybridIdOfflineFile).setOnClickListener(this);
        findViewById(R.id.hybridIdBuildInFile).setOnClickListener(this);
        findViewById(R.id.hybridIdCommonFile).setOnClickListener(this);
        findViewById(R.id.hybridIdOfflineZip).setOnClickListener(this);
        findViewById(R.id.hybridIdBuildInZip).setOnClickListener(this);
        findViewById(i2).setOnClickListener(this);
        findViewById(R.id.bridgeApp).setOnClickListener(this);
        findViewById(R.id.decoupleNet).setOnClickListener(this);
        findViewById(R.id.pinConfirmBtn).setOnClickListener(this);
        this.mSwitchEt = (EditText) findViewById(R.id.switchEt);
        this.mUrlEt = (EditText) findViewById(R.id.urlEt);
        this.mCookieEt = (EditText) findViewById(R.id.cookieEt);
        this.mCookieTv = (TextView) findViewById(R.id.cookieTv);
        this.mCoreTv = (TextView) findViewById(R.id.coreTv);
        this.mUATv = (TextView) findViewById(i3);
        this.mPathTv = (TextView) findViewById(i4);
        this.mHybridIdEt = (EditText) findViewById(R.id.hybridIdEt);
        this.mHybridResultTv = (TextView) findViewById(R.id.hybridResultTv);
        bindDatas();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(final HashMap hashMap) {
        this.mHybridResultTv.post(new Runnable() { // from class: com.jingdong.common.web.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                SecretDoor.this.j(hashMap);
            }
        });
    }

    private boolean onHybridClick(View view) {
        if (view.getId() == R.id.hybridLogBtn) {
            WebHybridLogView.showLog = true;
            HybridBackdoorLogger.setHybridBackdoorLogger(new HybridBackdoorLogger());
            this.jdWebView.showHybridLogLayout();
            ToastUtils.showToast(getContext(), "Success !");
            exit();
            return true;
        } else if (view.getId() == R.id.hybridHitBtn) {
            WebHybridLogView.toastHit = true;
            ToastUtils.showToast(getContext(), "Success !");
            return true;
        } else if (view.getId() == R.id.hybridDelBuildInVerBtn) {
            getContext().getApplicationContext().getSharedPreferences("jdhybrid_buildInData", 0).edit().putInt("buildInConfigVer", 0).apply();
            ToastUtils.showToast(getContext(), "buildInConfigVer set to 0");
            return true;
        } else if (view.getId() == R.id.hybridDefaultOpenBtn) {
            WebHybridUtils.autoHybridInViewGlobal = true;
            ToastUtils.showToast(getContext(), "Now all JDWebViews' hybrid functions are Enable.");
            exit();
            return true;
        } else if (view.getId() == R.id.hybridSwitchBtn) {
            WebHybridUtils.hybridEnable = !WebHybridUtils.hybridEnable;
            Context context = getContext();
            StringBuilder sb = new StringBuilder();
            sb.append("Now hybrid function is ");
            sb.append(WebHybridUtils.hybridEnable ? "ON" : "OFF");
            ToastUtils.showToast(context, sb.toString());
            return true;
        } else if (view.getId() == R.id.hybridOfflineConfig) {
            showHybridOfflineConfig(view);
            return true;
        } else if (view.getId() == R.id.hybridCommonConfig) {
            showHybridCommonConfig(view);
            return true;
        } else if (view.getId() == R.id.hybridBuildInConfig) {
            showHybridBuildInConfig(view);
            return true;
        } else if (view.getId() == R.id.hybridRootFile) {
            showHybridRootDir(view);
            return true;
        } else if (view.getId() == R.id.hybridDelFile) {
            deleteHybridDir(view);
            return true;
        } else if (view.getId() == R.id.hybridIdOfflineFile) {
            showHybridIdOfflineFolder(view);
            return true;
        } else if (view.getId() == R.id.hybridIdBuildInFile) {
            showHybridIdBuildInFolder(view);
            return true;
        } else if (view.getId() == R.id.hybridIdCommonFile) {
            showHybridIdCommonFolder(view);
            return true;
        } else if (view.getId() == R.id.hybridIdOfflineZip) {
            showHybridIdOfflineZip(view);
            return true;
        } else if (view.getId() == R.id.hybridIdBuildInZip) {
            showHybridIdBuildInZip(view);
            return true;
        } else {
            return false;
        }
    }

    private void setHybridNetAdapter() {
        Button button = (Button) findViewById(R.id.bridgeApp);
        if (WebHybridUtils.userAppNetAdatper) {
            button.setText("\u5173\u95edhybrid\u6865\u63a5\u5230\u5bbf\u4e3b");
            HybridSDK.registerAdapter(ColorHttpAdapter.NAME, ColorHttpAdapterImpl.class);
            return;
        }
        button.setText("\u6253\u5f00hybrid\u6865\u63a5\u5230\u5bbf\u4e3b");
        HybridSDK.unregisterAdapter(ColorHttpAdapter.NAME);
    }

    private void showHybridBuildInConfig(View view) {
        this.mHybridResultTv.setText("fetching config...");
        JDHybridDebugHelper.getAllBuildInConfig(new JDHybridDebugHelper.Callback() { // from class: com.jingdong.common.web.ui.p
            @Override // com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback
            public final void onResult(Object obj) {
                SecretDoor.this.d((HashMap) obj);
            }
        });
    }

    private void showHybridCommonConfig(View view) {
        this.mHybridResultTv.setText("fetching config...");
        JDHybridDebugHelper.getAllCommonConfig(new JDHybridDebugHelper.Callback() { // from class: com.jingdong.common.web.ui.o
            @Override // com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback
            public final void onResult(Object obj) {
                SecretDoor.this.h((HashMap) obj);
            }
        });
    }

    private void showHybridIdBuildInFolder(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input id");
        } else {
            this.mHybridResultTv.setText(JDHybridDebugHelper.getEntityDirFiles(2, obj));
        }
    }

    private void showHybridIdBuildInZip(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input id");
        } else {
            this.mHybridResultTv.setText(JDHybridDebugHelper.getEntityZipFile(2, obj));
        }
    }

    private void showHybridIdCommonFolder(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input id");
        } else {
            this.mHybridResultTv.setText(JDHybridDebugHelper.getEntityDirFiles(3, obj));
        }
    }

    private void showHybridIdOfflineFolder(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input id");
        } else {
            this.mHybridResultTv.setText(JDHybridDebugHelper.getEntityDirFiles(1, obj));
        }
    }

    private void showHybridIdOfflineZip(View view) {
        String obj = this.mHybridIdEt.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            this.mHybridResultTv.setText("please input id");
        } else {
            this.mHybridResultTv.setText(JDHybridDebugHelper.getEntityZipFile(1, obj));
        }
    }

    private void showHybridOfflineConfig(View view) {
        this.mHybridResultTv.setText("fetching config...");
        JDHybridDebugHelper.getAllOfflineConfig(new JDHybridDebugHelper.Callback() { // from class: com.jingdong.common.web.ui.q
            @Override // com.jd.libs.hybrid.offlineload.debug.JDHybridDebugHelper.Callback
            public final void onResult(Object obj) {
                SecretDoor.this.l((HashMap) obj);
            }
        });
    }

    private void showHybridRootDir(View view) {
        this.mHybridResultTv.setText(JDHybridDebugHelper.getFileRootDirInfo());
    }

    public String listToString(List<String> list) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 != 0) {
                sb.append("|||");
            }
            sb.append(list.get(i2));
        }
        return sb.toString();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object newInstance;
        if (view.getId() == R.id.loadBtn) {
            this.jdWebView.loadUrl(this.mUrlEt.getText().toString().trim());
            ((ViewGroup) getParent()).removeView(this);
        } else if (view.getId() == R.id.xviewBtn) {
            try {
                Class<?> cls = Class.forName("com.jingdong.common.XView.XViewEntity");
                Class.forName("com.jingdong.common.XView.XView");
                Class<?> cls2 = Class.forName("com.jingdong.common.XView.XViewCallBack");
                Class<?> cls3 = Class.forName("com.jingdong.common.XView.XViewHelper");
                if (cls != null && cls2 != null && cls3 != null && (newInstance = cls.newInstance()) != null) {
                    Field field = cls.getField("url");
                    if (field != null) {
                        field.set(newInstance, this.mUrlEt.getText().toString().trim());
                    }
                    Method method = cls3.getMethod("createXView", Activity.class, ViewGroup.class, String.class, cls, cls2);
                    if (method != null) {
                        Object invoke = method.invoke(null, (Activity) getContext(), this.jdWebView, "shadowTriggerImpl", newInstance, null);
                        if (invoke == null) {
                            return;
                        }
                        Method method2 = invoke.getClass().getMethod("startXView", new Class[0]);
                        if (method2 != null) {
                            method2.invoke(invoke, new Object[0]);
                        }
                    }
                }
            } catch (Exception unused) {
            }
            exit();
        } else if (view.getId() == R.id.execJsBtn) {
            this.jdWebView.injectJs(this.mUrlEt.getText().toString().trim());
        } else if (view.getId() == R.id.fswBtn) {
            QbSdk.forceSysWebView();
            ToastUtils.showToast(getContext(), "please close the page and open it again");
            exit();
        } else if (view.getId() == R.id.clearCacheBtn) {
            this.jdWebView.setUseCache(false);
            this.jdWebView.reload();
            ToastUtils.showToast(getContext(), "forbid webview cache! \n reload this page ");
            exit();
        } else if (view.getId() == R.id.reloadBtn) {
            this.jdWebView.reload();
            exit();
        } else if (view.getId() == R.id.logBtn) {
            WebLogView.showLog = true;
            this.jdWebView.showLogLayout();
            ToastUtils.showToast(getContext(), "Success !");
            exit();
        } else if (view.getId() == R.id.certificateBtn) {
            shouldSslDialog = true;
            ToastUtils.showToast(getContext(), "Success !");
            exit();
        } else if (view.getId() == R.id.exitBtn) {
            exit();
        } else if (view.getId() == R.id.copyUrlBtn) {
            ClipboardUtil.clipContentWithToast(getContext(), "Url", this.jdWebView.getFinalUrl(), "Url copied to clipBoard");
        } else if (view.getId() == R.id.cookieBtn) {
            String trim = this.mCookieEt.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                trim = this.jdWebView.getFinalUrl();
            }
            String cookie = CookieManager.getInstance().getCookie(trim);
            this.mCookieTv.setText("Cookie:\n" + cookie);
            this.mCookieTv.setVisibility(0);
        } else if (view.getId() == R.id.clearJdComCookieBtn) {
            String str = "Before Clear-->\nCookie: " + CookieManager.getInstance().getCookie("https://jd.com");
            WebUtils.clearJdComCookies();
            this.mCookieTv.setText(str + "\n-------\nAfter Clear->\nCookie: " + CookieManager.getInstance().getCookie("https://jd.com"));
            this.mCookieTv.setVisibility(0);
        } else if (view.getId() == R.id.clearBtn) {
            this.mUrlEt.setText("");
        } else if (view.getId() == R.id.switchBtn) {
            String trim2 = this.mSwitchEt.getText().toString().trim();
            if (TextUtils.isEmpty(trim2)) {
                ToastUtils.showToast(getContext(), "Please input switch name first!");
            } else {
                ToastUtils.showToast(getContext(), SwitchQueryFetcher.getSwitchStringValue(trim2, "Switch is not exist!"));
            }
        } else if (view.getId() == R.id.uaTv) {
            this.mUATv.setCompoundDrawables(null, null, null, null);
            this.mUATv.setMaxLines(100);
        } else if (view.getId() == R.id.pathTv) {
            this.mPathTv.setCompoundDrawables(null, null, null, null);
            this.mPathTv.setMaxLines(100);
        } else if (view.getId() == R.id.debugBtn) {
            WebView.setWebContentsDebuggingEnabled(true);
            ToastUtils.showToast(getContext(), "Success !");
            exit();
        } else if (view.getId() == R.id.bridgeApp) {
            WebHybridUtils.userAppNetAdatper = !WebHybridUtils.userAppNetAdatper;
            setHybridNetAdapter();
        } else if (view.getId() == R.id.decoupleNet) {
            String setting = HybridSDK.getSetting(HybridSDK.SWITCH_NET_HYBRID);
            HybridSDK.updateSettings(HybridSDK.SWITCH_NET_HYBRID, "1".equals(setting) ? "0" : "1");
            Context context = getContext();
            Object[] objArr = new Object[1];
            objArr[0] = "1".equals(setting) ? "App" : "Hybrid\u81ea\u5b9a\u4e49";
            ToastUtils.showToast(context, String.format("\u5f53\u524d\u4f7f\u7528%s\u7f51\u7edc\u6846\u67b6", objArr));
        } else if (view.getId() == R.id.pinConfirmBtn) {
            EditText editText = (EditText) findViewById(R.id.pinEt);
            if (editText.getText() == null) {
                return;
            }
            HybridSDK.updateSettings("pin", editText.getText().toString());
            ToastUtils.showToast(getContext(), "\u8bbe\u7f6e\u6210\u529f");
        } else {
            onHybridClick(view);
        }
    }
}
