package com.jd.manto.share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.entity.ShareInfo;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.manto.sdk.api.IShareManager;
import com.jingdong.manto.sdk.thread.MantoHandler;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoProcessUtil;

/* loaded from: classes17.dex */
public class ShareProxyActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    private ResultReceiver f6825g;

    /* renamed from: h  reason: collision with root package name */
    ShareUtil.CallbackListener f6826h = new a();

    /* renamed from: i  reason: collision with root package name */
    ShareUtil.ClickCallbackListener f6827i = new b();

    /* loaded from: classes17.dex */
    private static class ShareResultReceiver extends ResultReceiver {

        /* renamed from: g  reason: collision with root package name */
        final IShareManager.ShareCallback f6828g;

        ShareResultReceiver(Handler handler, IShareManager.ShareCallback shareCallback) {
            super(handler);
            this.f6828g = shareCallback;
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i2, Bundle bundle) {
            IShareManager.ShareCallback shareCallback = this.f6828g;
            if (shareCallback != null) {
                if (i2 == 1) {
                    shareCallback.onShareSuccess(bundle);
                } else if (i2 == 4) {
                    shareCallback.onShareClickChannel(bundle);
                } else if (i2 == 2) {
                    shareCallback.onShareFailed(bundle);
                } else if (i2 == 3) {
                    shareCallback.onShareCancel();
                } else {
                    shareCallback.onShareCancel();
                }
            }
        }
    }

    /* loaded from: classes17.dex */
    class a implements ShareUtil.CallbackListener {
        a() {
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onCancel() {
            ShareProxyActivity.this.w(3, new Bundle());
            ShareProxyActivity.this.finishActivity();
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onComplete(Object obj) {
            Bundle bundle = new Bundle();
            bundle.putString("shareChannel", (String) obj);
            ShareProxyActivity.this.w(1, bundle);
            ShareProxyActivity.this.finishActivity();
        }

        @Override // com.jingdong.common.utils.ShareUtil.CallbackListener
        public void onError(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("shareErrMsg", str);
            ShareProxyActivity.this.w(2, bundle);
            ShareProxyActivity.this.finishActivity();
        }
    }

    /* loaded from: classes17.dex */
    class b implements ShareUtil.ClickCallbackListener {
        b() {
        }

        @Override // com.jingdong.common.utils.ShareUtil.ClickCallbackListener
        public void onClick(String str) {
            Bundle bundle = new Bundle();
            bundle.putString("shareChannel", str);
            ShareProxyActivity.this.w(4, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        this.isDisposeableUnableExitAnim = true;
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(int i2, Bundle bundle) {
        ResultReceiver resultReceiver = this.f6825g;
        if (resultReceiver != null) {
            resultReceiver.send(i2, bundle);
        }
    }

    public static void x(Context context, ShareInfo shareInfo, IShareManager.ShareCallback shareCallback) {
        int lastIndexOf;
        if (shareInfo == null) {
            return;
        }
        String processName = MantoProcessUtil.getProcessName();
        if (!TextUtils.isEmpty(processName) && (lastIndexOf = processName.lastIndexOf(":")) >= 0 && lastIndexOf < processName.length() - 1) {
            String str = context.getPackageName() + ".ACTION_ASSIST_" + processName.substring(processName.lastIndexOf(":") + 1).toUpperCase();
            MantoLog.d("ShareProxyActivity", str);
            shareInfo.setJdMpTaskAction(str);
        }
        Intent intent = new Intent(context, ShareProxyActivity.class);
        intent.putExtra("manto_extra_share_result_receiver", new ShareResultReceiver(MantoHandler.fetchFreeHandler(Looper.getMainLooper()), shareCallback));
        intent.putExtra("manto_extra_share_info", shareInfo);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 != 1215) {
            super.onActivityResult(i2, i3, intent);
            finishActivity();
            return;
        }
        finishActivity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        if (getIntent() != null && getIntent().getExtras() != null) {
            UnStatusBarTintUtil.setStatusBarLightMode(this);
            ShareInfo shareInfo = (ShareInfo) getIntent().getParcelableExtra("manto_extra_share_info");
            if (shareInfo == null) {
                finishActivity();
                return;
            }
            this.f6825g = (ResultReceiver) getIntent().getParcelableExtra("manto_extra_share_result_receiver");
            ShareUtil.mLastUsedTime = 0L;
            ShareUtil.showShareDialog(this, shareInfo, this.f6826h, this.f6827i);
            return;
        }
        finishActivity();
    }
}
