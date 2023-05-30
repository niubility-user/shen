package com.jingdong.app.mall.open;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.main.MainActivity;
import com.jingdong.app.mall.privacy.JDPrivacyManager;
import com.jingdong.app.mall.privacy.PrivacyBridge;
import com.jingdong.app.mall.safemode.i;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.e;
import com.jingdong.app.mall.utils.j;
import com.jingdong.app.mall.utils.r;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.XView2.XView2Manager;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.login.SPUtil;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.utils.FireEyeUtils;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.JDSecUtils;
import com.jingdong.common.utils.JMAUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.X5InitUtil;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JDBroadcastConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public abstract class BaseEntryActivity extends MyActivity {

    /* renamed from: h */
    private static long f11428h;

    /* renamed from: i */
    private static final String f11429i = BaseEntryActivity.class.getSimpleName();

    /* renamed from: g */
    private b f11430g;

    /* loaded from: classes4.dex */
    public class a implements JDPrivacyManager.PrivacyCallback {
        a() {
            BaseEntryActivity.this = r1;
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onClose(boolean z) {
            if (!z) {
                BaseEntryActivity baseEntryActivity = BaseEntryActivity.this;
                baseEntryActivity.z(baseEntryActivity.getIntent());
                return;
            }
            BaseEntryActivity.this.init();
        }

        @Override // com.jingdong.app.mall.privacy.JDPrivacyManager.PrivacyCallback
        public void onDismiss() {
        }
    }

    /* loaded from: classes4.dex */
    public class b extends BroadcastReceiver {
        private b() {
            BaseEntryActivity.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (JumpUtil.isPcStream) {
                BaseEntryActivity.this.finish();
            }
        }

        /* synthetic */ b(BaseEntryActivity baseEntryActivity, a aVar) {
            this();
        }
    }

    public static /* synthetic */ boolean B() {
        e.a();
        return false;
    }

    private String D(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str) || (indexOf = str.indexOf(ContainerUtils.KEY_VALUE_DELIMITER)) == -1) {
            return "";
        }
        int i2 = indexOf + 1;
        return str.length() - i2 <= 0 ? "" : str.substring(i2);
    }

    public static long w() {
        return f11428h;
    }

    protected static boolean x() {
        return BaseFrameUtil.getInstance().getMainFrameActivity() instanceof Activity;
    }

    private void y() {
        JMAUtils.JMAReportReferrerForInterfaceActivity("SInterfaceActivity", this, D((getIntent() == null || getIntent().getData() == null) ? "" : getIntent().getData().toString()), getIntent() != null ? getIntent().getAction() : "");
        JDSecUtils.report("SInterfaceActivity", this);
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0049 A[Catch: Exception -> 0x009d, TryCatch #0 {Exception -> 0x009d, blocks: (B:113:0x0009, B:115:0x0013, B:117:0x0019, B:120:0x0020, B:122:0x002c, B:127:0x0038, B:130:0x0049, B:132:0x004f, B:133:0x0053, B:135:0x0059, B:136:0x0062, B:138:0x0068, B:141:0x006f, B:143:0x007b, B:144:0x0087, B:146:0x008d, B:148:0x0097), top: B:158:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void C(Intent intent) {
        boolean z;
        String queryParameter;
        if (intent == null) {
            return;
        }
        try {
            String scheme = intent.getScheme();
            if (!JumpUtil.isJdScheme(scheme) && !JumpUtil.isJdPayScheme(scheme) && !JumpUtil.isJdPayOpenScheme(scheme)) {
                return;
            }
            if (!TextUtils.isEmpty(intent.getAction()) && !intent.getBooleanExtra(OpenAppConstant.FLAG_INNERAPP, false)) {
                z = false;
                Uri data = intent.getData();
                queryParameter = data.getQueryParameter("params");
                if (TextUtils.isEmpty(queryParameter)) {
                    if (queryParameter.startsWith("\"")) {
                        queryParameter = queryParameter.substring(1);
                    }
                    if (queryParameter.endsWith("\"")) {
                        queryParameter = queryParameter.substring(0, queryParameter.length() - 1);
                    }
                    JDJSONObject string2JDJsonObject = JumpUtil.string2JDJsonObject(queryParameter, data);
                    if (string2JDJsonObject != null && !string2JDJsonObject.isEmpty()) {
                        String optString = string2JDJsonObject.optString(JshopConst.JSHOP_M_PARAM);
                        if (TextUtils.isEmpty(optString)) {
                            return;
                        }
                        JDMtaUtils.setMtaContent4OpenApp(JdSdk.getInstance().getApplication(), optString, z);
                        return;
                    }
                    ExceptionReporter.reportOpenAppJumpException("Openapp_parseJdvErr", intent.getData() != null ? intent.getData().toString() : "", "convertToJson null");
                    return;
                }
                return;
            }
            z = true;
            Uri data2 = intent.getData();
            queryParameter = data2.getQueryParameter("params");
            if (TextUtils.isEmpty(queryParameter)) {
            }
        } catch (Exception e2) {
            ExceptionReporter.reportOpenAppJumpException("Openapp_parseJdvErr", intent.getData() != null ? intent.getData().toString() : "", ExceptionReporter.getStackStringFromException(e2));
            if (OKLog.E) {
                OKLog.e(f11429i, e2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.jingdong.app.mall.open.BaseEntryActivity$a] */
    public void init() {
        j.c().d();
        j.c().f(new r() { // from class: com.jingdong.app.mall.open.b
            @Override // com.jingdong.app.mall.utils.r
            public final void a(boolean z) {
                e.a();
            }
        });
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.jingdong.app.mall.open.a
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return BaseEntryActivity.B();
            }
        });
        c.a().e(this, getIntent());
        i.g().j();
        Boolean bool = 0;
        this.f11430g = new b(this, bool);
        registerReceiver(this.f11430g, new IntentFilter(JDBroadcastConstant.ACTION_USER_LOGIN_ACTIVITY_FINISH));
        boolean z = true;
        JDMtaUtils.acceptPrivacyProtocol(true);
        FireEyeUtils.reportFireEyeEvent("powerOn", true);
        SPUtil.setPackageName(getCallingPackage());
        y();
        WebUtils.fix64();
        X5InitUtil.preloadX5(this);
        SwitchQueryFetcher.getFetcher().fetch("1");
        WebViewHelper.getUrlFilterRule();
        if (!x()) {
            WebHybridUtils.loadConfig();
            WebHybridUtils.loadBuildInConfig();
        }
        if (XView2Manager.mIsXViewEnable && !SwitchQueryFetcher.isXTime()) {
            XView2Manager.getInstance().requestXViewData();
        }
        com.jingdong.app.mall.performance.b.m().s();
        if (getIntent() != null) {
            if (!TextUtils.isEmpty(getIntent().getAction()) && !getIntent().getBooleanExtra(OpenAppConstant.FLAG_INNERAPP, false)) {
                z = false;
            }
            bool = Boolean.valueOf(z);
        }
        if (bool != 0 && !bool.booleanValue()) {
            f11428h = SystemClock.elapsedRealtime();
        }
        v(getIntent());
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        setResult(-1, intent);
        finish();
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        OpenLinkTimeManager.getInstance().interfaceActivityCreate();
        super.onCreate(bundle);
        if (!"native".equals(JdCrashReport.getAppCrashState()) && !"java".equals(JdCrashReport.getAppCrashState())) {
            C(getIntent());
            if (JDPrivacyHelper.isAcceptPrivacy(this)) {
                init();
                return;
            }
            PrivacyBridge.launchPrivacyDialog(true, this, new a());
            OpenLinkTimeManager.getInstance().isShowPrivacyDialog();
            return;
        }
        finish();
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b bVar = this.f11430g;
        if (bVar != null) {
            unregisterReceiver(bVar);
        }
    }

    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!JDPrivacyHelper.isAcceptPrivacy(this)) {
            z(intent);
        } else {
            v(intent);
        }
    }

    public void u(Intent intent) {
    }

    protected abstract void v(Intent intent);

    protected void z(Intent intent) {
        u(intent);
        try {
            JDMtaUtils.sendCommonData(this, "Startup_OpenAppParam_Unauthorized", getIntent() != null ? getIntent().getDataString() : "", "", getClass().getName(), "", "", "");
            startActivity(new Intent(this, BaseFrameUtil.getInstance().getMainFrameActivity() == null ? MainActivity.class : MainFrameActivity.class));
        } catch (Exception unused) {
        } catch (Throwable th) {
            finish();
            throw th;
        }
        finish();
    }
}
