package com.jingdong.union;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.union.a.e;
import com.jingdong.union.common.config.JdUnionBase;
import com.jingdong.union.common.helper.a;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import com.jingdong.union.dependency.IJumpSubCallBack;
import com.jingdong.union.dependency.c;

/* loaded from: classes12.dex */
public class UnionLoadingActivity extends Activity {

    /* renamed from: g  reason: collision with root package name */
    private c f15582g;

    /* renamed from: h  reason: collision with root package name */
    private View f15583h;

    /* renamed from: i  reason: collision with root package name */
    private FrameLayout f15584i;

    /* renamed from: j  reason: collision with root package name */
    private View f15585j;

    /* renamed from: k  reason: collision with root package name */
    private Bundle f15586k;

    private void a() {
        setContentView(R.layout.union_sdk_activity);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_bg);
        this.f15584i = frameLayout;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
            View view = this.f15585j;
            if (view != null && view.getParent() == null) {
                this.f15584i.addView(this.f15585j);
            }
        }
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_conetent);
        View loadingView = JdUnionBase.getLoadingView().getLoadingView(this);
        this.f15583h = loadingView;
        if (loadingView == null || relativeLayout == null || loadingView.getParent() != null) {
            return;
        }
        relativeLayout.addView(this.f15583h);
    }

    private void b(int i2, String str) {
        if (i2 == 1) {
            Bundle bundle = new Bundle();
            bundle.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            bundle.putString("msg", str);
            JdUnionBase.getMtaUtils().sendCommonData(this, "jingdongunionsdk_1626424295026|3", com.jingdong.union.a.c.a(bundle), LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, this.f15586k);
        } else if (i2 == 2) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
            bundle2.putString("msg", str);
            e.c(this, "jingdongunionsdk_1626424295026|5", bundle2, bundle2);
        }
    }

    public static void c(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UnionLoadingActivity.class);
        intent.putExtra("loading_type", 2);
        intent.putExtra("bundle_tag", bundle);
        if (context instanceof Activity) {
            intent.addFlags(67108864);
            context.startActivity(intent);
            return;
        }
        intent.addFlags(335544320);
        context.startActivity(intent);
    }

    public static void d(Context context, boolean z, Bundle bundle) {
        JdUnionBase.getMtaUtils().sendCommonData(context, "Union_startLoadingPage", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, bundle);
        Intent intent = new Intent(context, UnionLoadingActivity.class);
        intent.putExtra("loading_type", 1);
        intent.putExtra("common_cb_tag", z);
        intent.putExtra("bundle_tag", bundle);
        if (context instanceof Activity) {
            intent.addFlags(67108864);
            context.startActivity(intent);
            return;
        }
        intent.addFlags(335544320);
        context.startActivity(intent);
    }

    private void e(Intent intent, boolean z, boolean z2) {
        if (intent != null && intent.getIntExtra("loading_type", -1) != -1) {
            int intExtra = intent.getIntExtra("loading_type", -1);
            boolean booleanExtra = intent.getBooleanExtra("common_cb_tag", true);
            this.f15586k = intent.getBundleExtra("bundle_tag");
            IJumpDispatchCallBack jumpDispatchCallBack = JdUnionBase.getJumpDispatchCallBack();
            if (!booleanExtra) {
                jumpDispatchCallBack = JdUnionBase.tempIJumpDispatchCallBack;
            }
            IJumpDispatchCallBack iJumpDispatchCallBack = jumpDispatchCallBack;
            IJumpSubCallBack iJumpSubCallBack = JdUnionBase.tempIJumpSubCallBack;
            if (this.f15586k != null && ((iJumpDispatchCallBack != null || intExtra != 1) && (iJumpSubCallBack != null || intExtra != 2))) {
                this.f15585j = JdUnionBase.tempBgView;
                a();
                JdUnionBase.getMtaUtils().sendCommonData(this, "Union_finishLoadingPage", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, "JdUnionBase", LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, LangUtils.SINGLE_SPACE, this.f15586k);
                if (intExtra == 1) {
                    a aVar = new a();
                    this.f15582g = aVar;
                    aVar.f(this, this.f15586k, iJumpDispatchCallBack);
                    return;
                } else if (intExtra == 2) {
                    com.jingdong.union.common.helper.c cVar = new com.jingdong.union.common.helper.c();
                    this.f15582g = cVar;
                    cVar.e(this, this.f15586k, iJumpSubCallBack);
                    return;
                } else {
                    finish();
                    return;
                }
            }
            b(intExtra, "sdk \u542f\u52a8 UnionLoadingActivity \u53c2\u6570\u4f20\u9012\u9519\u8bef\uff0ctype=" + intExtra + "\uff0cisCommonCB=" + booleanExtra + "\uff0csavedInstanceState=" + z2);
            finish();
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        View view;
        super.finish();
        FrameLayout frameLayout = this.f15584i;
        if ((frameLayout == null || !frameLayout.isShown()) && (view = this.f15583h) != null) {
            view.setVisibility(8);
        }
        int i2 = R.anim.union_nothing;
        overridePendingTransition(i2, i2);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        c cVar = this.f15582g;
        if (cVar != null) {
            cVar.a(this);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = R.anim.union_nothing;
        overridePendingTransition(i2, i2);
        if (Build.VERSION.SDK_INT == 26) {
            setRequestedOrientation(3);
        } else {
            setRequestedOrientation(1);
        }
        try {
            e(getIntent(), true, bundle == null);
        } catch (Throwable th) {
            th.printStackTrace();
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        JdUnionBase.releaseTemp();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            e(intent, false, true);
        } catch (Throwable th) {
            th.printStackTrace();
            finish();
        }
    }
}
