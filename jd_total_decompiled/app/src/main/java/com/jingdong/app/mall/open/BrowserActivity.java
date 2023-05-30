package com.jingdong.app.mall.open;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.R;
import com.jingdong.common.ActivityManagerUtil;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.utils.DexAsyncUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpGroupSetting;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class BrowserActivity extends BaseEntryActivity {

    /* renamed from: k  reason: collision with root package name */
    private static List<String> f11431k;

    /* renamed from: j  reason: collision with root package name */
    private JDDialog f11432j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Uri f11433g;

        a(Uri uri) {
            this.f11433g = uri;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject.containsKey("code") && "0".equals(fastJsonObject.getString("code"))) {
                if (!fastJsonObject.containsKey("isLegal") || !"0".equals(fastJsonObject.getString("isLegal"))) {
                    BrowserActivity.this.S("Startup_HttpDeepLink_Status", this.f11433g.toString(), false);
                    BrowserActivity.this.T(this.f11433g);
                    return;
                }
                BrowserActivity.this.S("Startup_HttpDeepLink_Status", this.f11433g.toString(), true);
                BrowserActivity.this.O(this.f11433g.toString());
                return;
            }
            onError(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            BrowserActivity.this.L(this.f11433g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Uri f11435g;

        b(Uri uri) {
            this.f11435g = uri;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BrowserActivity.this.f11432j.dismiss();
            BrowserActivity.this.S("Startup_HttpDeepLink_PopupBack", this.f11435g.toString(), false);
            BrowserActivity.this.Q();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Uri f11437g;

        c(Uri uri) {
            this.f11437g = uri;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BrowserActivity.this.f11432j.dismiss();
            BrowserActivity.this.S("Startup_HttpDeepLink_PopupOpen", this.f11437g.toString(), false);
            BrowserActivity.this.R(this.f11437g);
            BrowserActivity.this.finish();
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        f11431k = arrayList;
        arrayList.add(".paipai.com");
        f11431k.add(".7fresh.com");
        f11431k.add(".isvjcloud.com");
        f11431k.add(".jdpay.com");
        f11431k.add(".jcloud.com");
        f11431k.add(".jd.hk");
        f11431k.add(".jdcloud.com");
        f11431k.add(".jingxi.com");
        f11431k.add(".healthjd.com");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(Uri uri) {
        String host = uri.getHost();
        if (TextUtils.isEmpty(host)) {
            S("Startup_HttpDeepLink_Status", uri != null ? uri.toString() : "", false);
            openHomePage();
            return;
        }
        Iterator<String> it = f11431k.iterator();
        while (it.hasNext()) {
            if (host.endsWith(it.next())) {
                S("Startup_HttpDeepLink_Status", uri.toString(), true);
                O(uri.toString().trim());
                return;
            }
        }
        S("Startup_HttpDeepLink_Status", uri.toString(), false);
        T(uri);
    }

    private void M(Uri uri) {
        HttpGroupSetting createNewSettings = HttpGroupUtils.createNewSettings();
        createNewSettings.setType(1000);
        HttpGroup httpGroup = HttpGroup.getHttpGroup(createNewSettings);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEffect(0);
        httpSetting.setNotifyUser(false);
        httpSetting.setCallTimeout(1000);
        httpSetting.setConnectTimeout(1000);
        httpSetting.setReadTimeout(1000);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("getUrlLegality");
        httpSetting.putJsonParam("url", uri.toString());
        httpSetting.setListener(new a(uri));
        httpGroup.add(httpSetting);
    }

    public static boolean N(Intent intent) {
        List<ResolveInfo> queryIntentActivities = JdSdk.getInstance().getApplication().getPackageManager().queryIntentActivities(intent, 65536);
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        JumpUtil.execJumpByDes("m", this, bundle);
    }

    public static Intent P(Uri uri, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (z) {
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
        intent.setFlags(268435456);
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        if (ActivityManagerUtil.getScreenManager().getNumActivitiesInStack() == 1) {
            finish();
        } else {
            U();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(Uri uri) {
        Intent P = P(uri, true);
        if (!N(P)) {
            P = P(uri, false);
        }
        startActivityNoException(P);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(String str, String str2, boolean z) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        jDJSONObject.put("url", (Object) str2);
        jDJSONObject.put("jdurl", (Object) (z ? "1" : "0"));
        JDMtaUtils.sendClickDataWithExt(this, str, "", "", "", getClass().getName(), "", "", jDJSONObject.toString(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T(Uri uri) {
        S("Startup_HttpDeepLink_Popup", uri.toString(), false);
        JDDialog jDDialog = this.f11432j;
        if (jDDialog != null) {
            jDDialog.dismiss();
        }
        TextView textView = new TextView(this);
        textView.setText(uri.toString());
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        textView.setTextColor(getResources().getColor(R.color.ci));
        textView.setTextSize(0, getResources().getDimension(R.dimen.d_));
        textView.setPadding(0, 0, 0, (int) getResources().getDimension(R.dimen.da));
        JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(this, "\u6e29\u99a8\u63d0\u793a", "\u60a8\u6253\u5f00\u7684\u4e0d\u662f\u4eac\u4e1c\u9875\u9762\uff0c\u8bf7\u4f7f\u7528\u6d4f\u89c8\u5668\u6253\u5f00\uff0c\u5e76\u6ce8\u610f\u4f7f\u7528\u5b89\u5168\uff1a", textView, "\u8fd4\u56de", "\u6d4f\u89c8\u5668\u6253\u5f00");
        this.f11432j = createJdDialogWithStyle9;
        createJdDialogWithStyle9.setOnLeftButtonClickListener(new b(uri));
        this.f11432j.setOnRightButtonClickListener(new c(uri));
        this.f11432j.show();
    }

    private void U() {
        try {
            moveTaskToBack(true);
        } catch (Throwable unused) {
            BaseFrameUtil.exit(null);
        }
        finish();
    }

    private void openHomePage() {
        try {
            startActivity(new Intent(this, MainFrameActivity.class));
        } catch (Throwable unused) {
            startActivity(DexAsyncUtil.getMainFrameActivityIntent(this));
        }
    }

    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    protected void v(Intent intent) {
        Uri data = intent.getData();
        if (data != null && data.getHost() != null) {
            if (data.getHost().endsWith(".jd.com")) {
                if ("http".equals(data.getScheme()) || "https".equals(data.getScheme())) {
                    S("Startup_HttpDeepLink_Status", data.toString(), true);
                    O(data.toString());
                    return;
                }
                return;
            }
            M(data);
            return;
        }
        S("Startup_HttpDeepLink_Status", data != null ? data.toString() : "", false);
        openHomePage();
        finish();
    }
}
