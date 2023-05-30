package com.jd.manto.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.manto.sdk.api.IRouter;
import com.jingdong.manto.sdk.thread.MantoHandler;

/* loaded from: classes17.dex */
public class RouterProxyActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    Bundle f6789g;

    /* renamed from: h  reason: collision with root package name */
    ResultReceiver f6790h;

    /* renamed from: i  reason: collision with root package name */
    boolean f6791i = false;

    /* loaded from: classes17.dex */
    public static class RouterResultReceiver extends ResultReceiver {

        /* renamed from: g  reason: collision with root package name */
        IRouter.CallBack f6792g;

        RouterResultReceiver(Handler handler, IRouter.CallBack callBack) {
            super(handler);
            this.f6792g = callBack;
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i2, Bundle bundle) {
            if (i2 == 0) {
                this.f6792g.onSuccess(bundle);
            } else if (i2 == 1) {
                this.f6792g.onFail(bundle != null ? bundle.getInt("fail_code", -1) : -1);
            }
        }
    }

    private void u(Context context, String str, Bundle bundle) {
        JDRouter build = JDRouter.build(context, str);
        build.callBackListener((CallBackWithReturnListener) new a());
        build.open();
    }

    public static void v(Context context, Bundle bundle, IRouter.CallBack callBack) {
        if (bundle == null) {
            return;
        }
        Intent intent = new Intent(context, RouterProxyActivity.class);
        intent.putExtra("manto_extra_bundle", bundle);
        intent.putExtra("manto_extra_router", new RouterResultReceiver(MantoHandler.fetchFreeHandler(Looper.getMainLooper()), callBack));
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        super.finish();
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            this.f6789g = getIntent().getBundleExtra("manto_extra_bundle");
            this.f6790h = (ResultReceiver) getIntent().getParcelableExtra("manto_extra_router");
            String string = this.f6789g.getString("url");
            if (string == null) {
                this.f6790h.send(1, null);
                finish();
                return;
            }
            u(this, string, this.f6789g);
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f6791i = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.f6791i) {
            if (this.f6790h != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("fail_code", -2);
                this.f6790h.send(1, bundle);
            }
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements CallBackWithReturnListener {
        a() {
        }

        @Override // com.jingdong.common.unification.router.CallBackWithReturnListener
        public void onComplete(Object obj) {
            Bundle bundle = new Bundle();
            if (obj instanceof String) {
                bundle.putString("result", (String) obj);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean("result", ((Boolean) obj).booleanValue());
            } else if (obj instanceof Number) {
                if (obj instanceof Integer) {
                    bundle.putInt("result", ((Integer) obj).intValue());
                } else {
                    bundle.putDouble("result", ((Double) obj).doubleValue());
                }
            }
            ResultReceiver resultReceiver = RouterProxyActivity.this.f6790h;
            if (resultReceiver != null) {
                resultReceiver.send(0, bundle);
            }
            RouterProxyActivity.this.finish();
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onError(int i2) {
            Bundle bundle = new Bundle();
            bundle.putInt("fail_code", i2);
            ResultReceiver resultReceiver = RouterProxyActivity.this.f6790h;
            if (resultReceiver != null) {
                resultReceiver.send(1, bundle);
            }
            RouterProxyActivity.this.finish();
        }

        @Override // com.jingdong.common.unification.router.CallBackListener
        public void onComplete() {
            ResultReceiver resultReceiver = RouterProxyActivity.this.f6790h;
            if (resultReceiver != null) {
                resultReceiver.send(0, null);
            }
            RouterProxyActivity.this.finish();
        }
    }
}
