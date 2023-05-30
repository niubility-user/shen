package com.jingdong.app.mall.bundle.jdrhsdk;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.jingdong.app.mall.bundle.jdrhsdk.a.b;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleData;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleError;
import com.jingdong.app.mall.bundle.jdrhsdk.api.JDRiskHandleManager;
import com.jingdong.app.mall.bundle.jdrhsdk.d.d;
import com.jingdong.app.mall.bundle.jdrhsdk.d.e;
import com.jingdong.app.mall.bundle.jdrhsdk.ui.JDProgressBar;
import com.jingdong.app.mall.bundle.jdrhsdk.ui.b;
import com.jingdong.app.mall.bundle.jdrhsdk.ui.c;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class JDRiskHandleActivity extends FragmentActivity implements View.OnClickListener, b.e {
    private static final String REPORT_PAGE_ID = "LoginDisposition";
    private static final String TAG = "RiskHandle.JDRiskHandleActivity";
    private ImageView backArrow;
    private com.jingdong.app.mall.bundle.jdrhsdk.b.a colorData;
    private RelativeLayout feedbackLayout;
    private TextView feedbackText;
    private RelativeLayout headLine;
    private JDProgressBar loading;
    private RelativeLayout loadingLayout;
    private FrameLayout mainFragmentLayout;
    private View statusBar;
    private TextView titleText;
    private final com.jingdong.app.mall.bundle.jdrhsdk.ui.b commonFragment = new com.jingdong.app.mall.bundle.jdrhsdk.ui.b();
    private com.jingdong.app.mall.bundle.jdrhsdk.ui.c errorFragment = null;
    private int refreshErrorCount = 0;
    private com.jingdong.app.mall.bundle.jdrhsdk.a.b riskHandle = null;
    private boolean refreshing = false;
    private boolean destroyBySelf = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class a implements b.InterfaceC0252b {
        a() {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.ui.b.InterfaceC0252b
        public void a() {
            JDRiskHandleActivity.this.onClickHandle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class b implements c.InterfaceC0253c {
        b() {
        }

        @Override // com.jingdong.app.mall.bundle.jdrhsdk.ui.c.InterfaceC0253c
        public void a() {
            JDRiskHandleActivity.this.toRefresh();
        }
    }

    /* loaded from: classes2.dex */
    class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f8129g;

        c(boolean z) {
            this.f8129g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDRiskHandleActivity.this.loadingLayout.setVisibility(this.f8129g ? 0 : 8);
        }
    }

    private void clickMta(String str, String str2, JSONObject jSONObject) {
        String jSONObject2;
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            jSONObject2 = "";
        }
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.f(this, str, "", "", str2, "", "", "", TextUtils.isEmpty(jSONObject2) ? "" : jSONObject2, null);
    }

    private void createErrorFragment() {
        if (this.errorFragment == null) {
            com.jingdong.app.mall.bundle.jdrhsdk.ui.c cVar = new com.jingdong.app.mall.bundle.jdrhsdk.ui.c();
            this.errorFragment = cVar;
            cVar.r(new b());
        }
    }

    private void expoMta(String str, String str2, JSONObject jSONObject) {
        String jSONObject2;
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            jSONObject2 = "";
        }
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.g(this, str, "", str2, "", "", TextUtils.isEmpty(jSONObject2) ? "" : jSONObject2, null);
    }

    private void feedBack(int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (i2 == 101) {
                jSONObject.put("business_type", "2");
            }
            clickMta("LoginDisposition_Feedback", REPORT_PAGE_ID, jSONObject);
            com.jingdong.app.mall.bundle.jdrhsdk.d.a.q(this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String getBusinessType() {
        com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar = this.riskHandle;
        if (bVar == null) {
            return "";
        }
        bVar.m();
        return "2";
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(this.mainFragmentLayout.getId());
    }

    private String getPageType(Fragment fragment) {
        return fragment instanceof com.jingdong.app.mall.bundle.jdrhsdk.ui.b ? "1" : "2";
    }

    private void initMainFragment() {
        this.commonFragment.o(new a());
        reportExposeMta(this.commonFragment);
        getSupportFragmentManager().beginTransaction().add(this.mainFragmentLayout.getId(), this.commonFragment).commitAllowingStateLoss();
    }

    private void initRisk() {
        this.riskHandle.d(this);
        this.commonFragment.r(this.riskHandle.a());
        this.commonFragment.t(this.riskHandle.p());
    }

    private void initStatusBar() {
        e.f(this, findViewById(R.id.jdrhsdk_activity_riskhandle_status_bar));
    }

    private void initViews() {
        ImageView imageView = (ImageView) findViewById(R.id.jdrhsdk_activity_back_arrow);
        this.backArrow = imageView;
        imageView.setOnClickListener(this);
        this.feedbackText = (TextView) findViewById(R.id.jdrhsdk_activity_feedback);
        this.titleText = (TextView) findViewById(R.id.jdrhsdk_title_text);
        this.headLine = (RelativeLayout) findViewById(R.id.jdrhsdk_main_headline);
        this.mainFragmentLayout = (FrameLayout) findViewById(R.id.jdrhsdk_main_frame);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.jdrhsdk_activity_feedback_layout);
        this.feedbackLayout = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.loadingLayout = (RelativeLayout) findViewById(R.id.jdrhsdk_progress_layout);
        this.loading = (JDProgressBar) findViewById(R.id.jdrhsdk_progress_bar);
        initMainFragment();
    }

    private void innerPreInitLayoutParams(Activity activity) {
        if (activity == null) {
            return;
        }
        e.w(activity);
        preInitLayoutParams(activity);
    }

    private boolean isErrorFragmentShowing() {
        return this.errorFragment != null && getCurrentFragment() == this.errorFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickHandle() {
        reportClickHandle();
        com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar = this.riskHandle;
        if (bVar == null || bVar.n()) {
            return;
        }
        this.riskHandle.q();
    }

    private void onError() {
        if (this.refreshing && isErrorFragmentShowing()) {
            onRefreshError();
        } else {
            switchErrorFragment();
        }
    }

    private void onRefreshError() {
        this.refreshing = false;
        this.refreshErrorCount++;
        reportClickRefresh(false);
        if (this.refreshErrorCount >= 2) {
            onRiskHandleFailure(JDRiskHandleError.CODE_USER_RETRY_FAILED, JDRiskHandleError.MSG_USER_RETRY_FAILED);
            return;
        }
        com.jingdong.app.mall.bundle.jdrhsdk.ui.c cVar = this.errorFragment;
        if (cVar != null) {
            cVar.x();
        }
    }

    private void preInitLayoutParams(Activity activity) {
        RelativeLayout.LayoutParams layoutParams;
        int b2;
        if (e.v(activity)) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.headLine.getLayoutParams();
            layoutParams2.height = e.n(activity, 44);
            this.headLine.setLayoutParams(layoutParams2);
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.backArrow.getLayoutParams();
            layoutParams3.leftMargin = e.n(activity, 10);
            layoutParams3.height = e.n(activity, 28);
            layoutParams3.width = e.n(activity, 28);
            this.backArrow.setLayoutParams(layoutParams3);
            int n2 = e.n(activity, 9);
            this.backArrow.setPadding(n2, 0, n2, 0);
            RelativeLayout.LayoutParams layoutParams4 = (RelativeLayout.LayoutParams) this.titleText.getLayoutParams();
            layoutParams4.height = e.n(activity, 18);
            this.titleText.setLayoutParams(layoutParams4);
            this.titleText.setTextSize(0, e.n(activity, 18));
            e.i(this.titleText);
            RelativeLayout.LayoutParams layoutParams5 = (RelativeLayout.LayoutParams) this.feedbackText.getLayoutParams();
            layoutParams5.height = e.n(activity, 14);
            this.feedbackText.setLayoutParams(layoutParams5);
            this.feedbackText.setTextSize(0, e.n(activity, 14));
            e.i(this.feedbackText);
            RelativeLayout.LayoutParams layoutParams6 = (RelativeLayout.LayoutParams) this.feedbackLayout.getLayoutParams();
            layoutParams6.height = e.n(activity, 28);
            layoutParams6.width = e.n(activity, 36);
            layoutParams6.rightMargin = e.n(activity, 10);
            this.feedbackLayout.setLayoutParams(layoutParams6);
            layoutParams = (RelativeLayout.LayoutParams) this.loading.getLayoutParams();
            layoutParams.height = e.n(activity, 20);
            b2 = e.n(activity, 20);
        } else {
            RelativeLayout.LayoutParams layoutParams7 = (RelativeLayout.LayoutParams) this.headLine.getLayoutParams();
            layoutParams7.height = e.b(activity, 120);
            this.headLine.setLayoutParams(layoutParams7);
            RelativeLayout.LayoutParams layoutParams8 = (RelativeLayout.LayoutParams) this.backArrow.getLayoutParams();
            layoutParams8.leftMargin = e.b(activity, 40);
            layoutParams8.height = e.b(activity, 40);
            layoutParams8.width = e.b(activity, 40);
            this.backArrow.setLayoutParams(layoutParams8);
            this.backArrow.setPadding(0, 0, 0, 0);
            RelativeLayout.LayoutParams layoutParams9 = (RelativeLayout.LayoutParams) this.titleText.getLayoutParams();
            layoutParams9.height = e.b(activity, 40);
            this.titleText.setLayoutParams(layoutParams9);
            this.titleText.setTextSize(0, e.b(activity, 40));
            e.i(this.titleText);
            RelativeLayout.LayoutParams layoutParams10 = (RelativeLayout.LayoutParams) this.feedbackText.getLayoutParams();
            layoutParams10.height = e.b(activity, 31);
            this.feedbackText.setLayoutParams(layoutParams10);
            this.feedbackText.setTextSize(0, e.b(activity, 31));
            e.i(this.feedbackText);
            RelativeLayout.LayoutParams layoutParams11 = (RelativeLayout.LayoutParams) this.feedbackLayout.getLayoutParams();
            layoutParams11.height = e.b(activity, 40);
            layoutParams11.width = e.b(activity, 80);
            layoutParams11.rightMargin = e.b(activity, 40);
            this.feedbackLayout.setLayoutParams(layoutParams11);
            layoutParams = (RelativeLayout.LayoutParams) this.loading.getLayoutParams();
            layoutParams.height = e.b(activity, 80);
            b2 = e.b(activity, 80);
        }
        layoutParams.width = b2;
        this.loading.setLayoutParams(layoutParams);
    }

    private void pvMta(String str, JSONObject jSONObject) {
        String jSONObject2;
        if (jSONObject != null) {
            try {
                jSONObject2 = jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            jSONObject2 = "";
        }
        com.jingdong.app.mall.bundle.jdrhsdk.d.a.d(this, this, jSONObject2, str, "", "", "", "", null, null);
    }

    private void reportClickHandle() {
        if (this.riskHandle == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("business_type", getBusinessType());
            clickMta("LoginDisposition_Go", REPORT_PAGE_ID, jSONObject);
        } catch (JSONException e2) {
            d.f(TAG, "", e2);
        }
    }

    private void reportClickRefresh(boolean z) {
        if (this.riskHandle == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("business_type", getBusinessType());
            jSONObject.put("click_type", z ? "1" : "0");
            clickMta("LoginDisposition_Refresh", REPORT_PAGE_ID, jSONObject);
        } catch (JSONException e2) {
            d.f(TAG, "", e2);
        }
    }

    private void reportExposeMta(Fragment fragment) {
        if (this.riskHandle == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", "");
            jSONObject.put("page_type", getPageType(fragment));
            jSONObject.put("business_type", getBusinessType());
            expoMta("LoginDisposition_Expo", REPORT_PAGE_ID, jSONObject);
        } catch (JSONException e2) {
            d.f(TAG, "", e2);
        }
    }

    private void reportPvMta() {
        if (this.riskHandle == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("url", "");
            jSONObject.put("page_type", "1");
            jSONObject.put("business_type", getBusinessType());
            pvMta(REPORT_PAGE_ID, jSONObject);
        } catch (JSONException e2) {
            d.f(TAG, "", e2);
        }
    }

    private void reportReturnMta() {
        if (this.riskHandle == null) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("business_type", getBusinessType());
            clickMta("LoginDisposition_Return", REPORT_PAGE_ID, jSONObject);
        } catch (JSONException e2) {
            d.f(TAG, "", e2);
        }
    }

    private void switchCommonFragment() {
        getSupportFragmentManager().beginTransaction().replace(this.mainFragmentLayout.getId(), this.commonFragment).commitAllowingStateLoss();
    }

    private void switchErrorFragment() {
        if (isErrorFragmentShowing()) {
            return;
        }
        createErrorFragment();
        this.refreshErrorCount = 0;
        reportExposeMta(this.errorFragment);
        getSupportFragmentManager().beginTransaction().replace(this.mainFragmentLayout.getId(), this.errorFragment).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toRefresh() {
        this.refreshing = true;
        com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar = this.riskHandle;
        if (bVar == null || bVar.n()) {
            return;
        }
        this.riskHandle.q();
    }

    private void toast(@StringRes int i2) {
        Toast.makeText(this, i2, 0).show();
    }

    private void toast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    public com.jingdong.app.mall.bundle.jdrhsdk.b.a getColorData() {
        com.jingdong.app.mall.bundle.jdrhsdk.b.a aVar = this.colorData;
        return aVar == null ? new com.jingdong.app.mall.bundle.jdrhsdk.b.a("") : aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar;
        if (view.getId() == R.id.jdrhsdk_activity_back_arrow) {
            reportReturnMta();
            onRiskHandleFailure(JDRiskHandleError.CODE_USER_CANCEL, JDRiskHandleError.MSG_USER_CANCEL);
        } else if (view.getId() != R.id.jdrhsdk_activity_feedback_layout || (bVar = this.riskHandle) == null) {
        } else {
            feedBack(bVar.m());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        innerPreInitLayoutParams(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.jdrhsdk_activity_riskhandle);
        initStatusBar();
        com.jingdong.app.mall.bundle.jdrhsdk.b.a aVar = new com.jingdong.app.mall.bundle.jdrhsdk.b.a(getIntent().getStringExtra("response"));
        this.colorData = aVar;
        com.jingdong.app.mall.bundle.jdrhsdk.a.b a2 = com.jingdong.app.mall.bundle.jdrhsdk.a.c.a(this, aVar);
        this.riskHandle = a2;
        if (a2 == null) {
            onRiskHandleFailure(-2001, JDRiskHandleError.MSG_CHECK_HANDLE_TYPE_ERROR);
            return;
        }
        JDRiskHandleManager.getInstance().setCurRiskHandle(this.riskHandle);
        this.riskHandle.o();
        initViews();
        initRisk();
        innerPreInitLayoutParams(this);
        reportPvMta();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar = this.riskHandle;
        if (bVar != null) {
            bVar.i();
        }
        if (this.destroyBySelf) {
            return;
        }
        JDRiskHandleManager.getInstance().onHandleFail(JDRiskHandleError.CODE_USER_CANCEL, JDRiskHandleError.MSG_USER_CANCEL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        try {
            com.jingdong.app.mall.bundle.jdrhsdk.a.b bVar = this.riskHandle;
            if (bVar == null || !(bVar instanceof com.jingdong.app.mall.bundle.jdrhsdk.a.a)) {
                return;
            }
            ((com.jingdong.app.mall.bundle.jdrhsdk.a.a) bVar).t();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b.e
    public void onRiskHandleError(String str) {
        onError();
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b.e
    public void onRiskHandleFailure(int i2, String str) {
        JDRiskHandleManager.getInstance().onHandleFail(i2, str);
        this.destroyBySelf = true;
        finish();
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b.e
    public void onRiskHandleLoadingChanged(boolean z) {
        runOnUiThread(new c(z));
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b.e
    public void onRiskHandleSuccess(String str) {
        JDRiskHandleData jDRiskHandleData = new JDRiskHandleData();
        jDRiskHandleData.setData(str);
        JDRiskHandleManager.getInstance().onHandleSuccess(jDRiskHandleData);
        this.destroyBySelf = true;
        finish();
    }

    @Override // com.jingdong.app.mall.bundle.jdrhsdk.a.b.e
    public void onRiskHandleViewLoaded() {
        if (this.refreshing) {
            reportClickRefresh(true);
            switchCommonFragment();
            this.refreshing = false;
        }
    }
}
