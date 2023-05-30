package com.jingdong.app.mall.open;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
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
import com.jingdong.jdsdk.constant.JDBroadcastConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;

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

    /* JADX WARN: Removed duplicated region for block: B:75:0x0048 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0049 A[Catch: Exception -> 0x009d, TryCatch #0 {Exception -> 0x009d, blocks: (B:59:0x0009, B:61:0x0013, B:63:0x0019, B:66:0x0020, B:68:0x002c, B:73:0x0038, B:76:0x0049, B:78:0x004f, B:79:0x0053, B:81:0x0059, B:82:0x0062, B:84:0x0068, B:87:0x006f, B:89:0x007b, B:90:0x0087, B:92:0x008d, B:94:0x0097), top: B:104:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(android.content.Intent r10) {
        /*
            r9 = this;
            java.lang.String r0 = "\""
            java.lang.String r1 = ""
            java.lang.String r2 = "Openapp_parseJdvErr"
            if (r10 != 0) goto L9
            return
        L9:
            java.lang.String r3 = r10.getScheme()     // Catch: java.lang.Exception -> L9d
            boolean r4 = com.jingdong.common.jump.JumpUtil.isJdScheme(r3)     // Catch: java.lang.Exception -> L9d
            if (r4 != 0) goto L20
            boolean r4 = com.jingdong.common.jump.JumpUtil.isJdPayScheme(r3)     // Catch: java.lang.Exception -> L9d
            if (r4 != 0) goto L20
            boolean r3 = com.jingdong.common.jump.JumpUtil.isJdPayOpenScheme(r3)     // Catch: java.lang.Exception -> L9d
            if (r3 != 0) goto L20
            return
        L20:
            java.lang.String r3 = r10.getAction()     // Catch: java.lang.Exception -> L9d
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L9d
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L37
            java.lang.String r3 = "isSourceInnerApp"
            boolean r3 = r10.getBooleanExtra(r3, r4)     // Catch: java.lang.Exception -> L9d
            if (r3 == 0) goto L35
            goto L37
        L35:
            r3 = 0
            goto L38
        L37:
            r3 = 1
        L38:
            android.net.Uri r6 = r10.getData()     // Catch: java.lang.Exception -> L9d
            java.lang.String r7 = "params"
            java.lang.String r7 = r6.getQueryParameter(r7)     // Catch: java.lang.Exception -> L9d
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Exception -> L9d
            if (r8 == 0) goto L49
            return
        L49:
            boolean r8 = r7.startsWith(r0)     // Catch: java.lang.Exception -> L9d
            if (r8 == 0) goto L53
            java.lang.String r7 = r7.substring(r5)     // Catch: java.lang.Exception -> L9d
        L53:
            boolean r0 = r7.endsWith(r0)     // Catch: java.lang.Exception -> L9d
            if (r0 == 0) goto L62
            int r0 = r7.length()     // Catch: java.lang.Exception -> L9d
            int r0 = r0 - r5
            java.lang.String r7 = r7.substring(r4, r0)     // Catch: java.lang.Exception -> L9d
        L62:
            com.jd.framework.json.JDJSONObject r0 = com.jingdong.common.jump.JumpUtil.string2JDJsonObject(r7, r6)     // Catch: java.lang.Exception -> L9d
            if (r0 == 0) goto L87
            boolean r4 = r0.isEmpty()     // Catch: java.lang.Exception -> L9d
            if (r4 == 0) goto L6f
            goto L87
        L6f:
            java.lang.String r4 = "m_param"
            java.lang.String r0 = r0.optString(r4)     // Catch: java.lang.Exception -> L9d
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L9d
            if (r4 != 0) goto Lbc
            com.jingdong.jdsdk.JdSdk r4 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Exception -> L9d
            android.app.Application r4 = r4.getApplication()     // Catch: java.lang.Exception -> L9d
            com.jingdong.jdsdk.mta.JDMtaUtils.setMtaContent4OpenApp(r4, r0, r3)     // Catch: java.lang.Exception -> L9d
            goto Lbc
        L87:
            android.net.Uri r0 = r10.getData()     // Catch: java.lang.Exception -> L9d
            if (r0 == 0) goto L96
            android.net.Uri r0 = r10.getData()     // Catch: java.lang.Exception -> L9d
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L9d
            goto L97
        L96:
            r0 = r1
        L97:
            java.lang.String r3 = "convertToJson null"
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportOpenAppJumpException(r2, r0, r3)     // Catch: java.lang.Exception -> L9d
            return
        L9d:
            r0 = move-exception
            android.net.Uri r3 = r10.getData()
            if (r3 == 0) goto Lac
            android.net.Uri r10 = r10.getData()
            java.lang.String r1 = r10.toString()
        Lac:
            java.lang.String r10 = com.jingdong.jdsdk.network.toolbox.ExceptionReporter.getStackStringFromException(r0)
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportOpenAppJumpException(r2, r1, r10)
            boolean r10 = com.jingdong.sdk.oklog.OKLog.E
            if (r10 == 0) goto Lbc
            java.lang.String r10 = com.jingdong.app.mall.open.BaseEntryActivity.f11429i
            com.jingdong.sdk.oklog.OKLog.e(r10, r0)
        Lbc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.open.BaseEntryActivity.C(android.content.Intent):void");
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
