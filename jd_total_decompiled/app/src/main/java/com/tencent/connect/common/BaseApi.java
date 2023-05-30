package com.tencent.connect.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.jdsdk.a.a;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.auth.c;
import com.tencent.connect.common.Constants;
import com.tencent.open.TDialog;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.f;
import com.tencent.open.utils.g;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public abstract class BaseApi {
    public static String businessId;
    public static String installChannel;
    public static boolean isOEM;
    public static String registerChannel;
    protected c b;

    /* renamed from: c  reason: collision with root package name */
    protected QQToken f16153c;

    /* loaded from: classes9.dex */
    public class TempRequestListener implements IRequestListener {
        private final IUiListener b;

        /* renamed from: c  reason: collision with root package name */
        private final Handler f16154c;

        public TempRequestListener(IUiListener iUiListener) {
            this.b = iUiListener;
            this.f16154c = new Handler(g.a().getMainLooper()) { // from class: com.tencent.connect.common.BaseApi.TempRequestListener.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 0) {
                        TempRequestListener.this.b.onComplete(message.obj);
                    } else {
                        TempRequestListener.this.b.onError(new UiError(message.what, (String) message.obj, null));
                    }
                }
            };
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = jSONObject;
            obtainMessage.what = 0;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = httpStatusException.getMessage();
            obtainMessage.what = -9;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onIOException(IOException iOException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = iOException.getMessage();
            obtainMessage.what = -2;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onJSONException(JSONException jSONException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = jSONException.getMessage();
            obtainMessage.what = -4;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onMalformedURLException(MalformedURLException malformedURLException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = malformedURLException.getMessage();
            obtainMessage.what = -3;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = networkUnavailableException.getMessage();
            obtainMessage.what = -10;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = socketTimeoutException.getMessage();
            obtainMessage.what = -8;
            this.f16154c.sendMessage(obtainMessage);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onUnknowException(Exception exc) {
            Message obtainMessage = this.f16154c.obtainMessage();
            obtainMessage.obj = exc.getMessage();
            obtainMessage.what = -6;
            this.f16154c.sendMessage(obtainMessage);
        }
    }

    public BaseApi(c cVar, QQToken qQToken) {
        this.b = cVar;
        this.f16153c = qQToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("format", "json");
        bundle.putString("status_os", Build.VERSION.RELEASE);
        bundle.putString("status_machine", f.a().c(g.a()));
        bundle.putString("status_version", Build.VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", a.a);
        QQToken qQToken = this.f16153c;
        if (qQToken != null && qQToken.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.f16153c.getAccessToken());
            bundle.putString("oauth_consumer_key", this.f16153c.getAppId());
            bundle.putString("openid", this.f16153c.getOpenId());
        }
        SharedPreferences sharedPreferences = g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + "-" + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.f16153c.getAppId());
        if (this.f16153c.isSessionValid()) {
            bundle.putString(Constants.PARAM_KEY_STR, this.f16153c.getAccessToken());
            bundle.putString(Constants.PARAM_KEY_TYPE, "0x80");
        }
        String openId = this.f16153c.getOpenId();
        if (openId != null) {
            bundle.putString("hopenid", openId);
        }
        bundle.putString(Constants.PARAM_PLATFORM, "androidqz");
        SharedPreferences sharedPreferences = g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0);
        if (isOEM) {
            bundle.putString(Constants.PARAM_PLATFORM_ID, "desktop_m_qq-" + installChannel + "-android-" + registerChannel + "-" + businessId);
        } else {
            bundle.putString(Constants.PARAM_PLATFORM_ID, sharedPreferences.getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", a.a);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent c() {
        Intent intent = new Intent();
        if (m.c(g.a())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, "com.tencent.open.agent.AgentActivity");
            if (k.b(g.a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", "com.tencent.open.agent.AgentActivity");
        if (k.b(g.a(), intent)) {
            return intent;
        }
        intent.setClassName(Constants.PACKAGE_TIM, "com.tencent.open.agent.AgentActivity");
        if (k.b(g.a(), intent)) {
            return intent;
        }
        return null;
    }

    public void releaseResource() {
    }

    public BaseApi(QQToken qQToken) {
        this(null, qQToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent c(String str) {
        Intent intent = new Intent();
        Intent b = b(str);
        if (b == null || b.getComponent() == null) {
            return null;
        }
        intent.setClassName(b.getComponent().getPackageName(), "com.tencent.open.agent.AgentActivity");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(String str) {
        Bundle a = a();
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            a.putString("need_version", str);
        }
        sb.append("https://openmobile.qq.com/oauth2.0/m_jump_by_version?");
        sb.append(HttpUtils.encodeUrl(a));
        return sb.toString();
    }

    protected Intent b(String str) {
        Intent intent = new Intent();
        if (m.c(g.a())) {
            intent.setClassName(Constants.PACKAGE_QQ_PAD, str);
            if (k.a(g.a(), intent)) {
                return intent;
            }
        }
        intent.setClassName("com.tencent.mobileqq", str);
        if (k.a(g.a(), intent)) {
            return intent;
        }
        intent.setClassName(Constants.PACKAGE_TIM, str);
        if (k.a(g.a(), intent)) {
            return intent;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(StringBuilder sb, Activity activity) {
        if (sb.indexOf("?") < 0) {
            sb.append("?");
        } else {
            sb.append(ContainerUtils.FIELD_DELIMITER);
        }
        sb.append(Constants.JumpUrlConstants.URL_KEY_SRC);
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append("app");
        String appId = this.f16153c.getAppId();
        String openId = this.f16153c.getOpenId();
        if (!TextUtils.isEmpty(appId)) {
            a(sb, "app_id", appId);
        }
        if (!TextUtils.isEmpty(openId)) {
            a(sb, Constants.JumpUrlConstants.URL_KEY_OPENID, m.k(openId));
        }
        String a = m.a(activity);
        if (!TextUtils.isEmpty(a)) {
            if (a.length() > 20) {
                a = a.substring(0, 20) + "...";
            }
            a(sb, "app_name", m.k(a));
        }
        a(sb, "sdk_version", m.k(Constants.SDK_VERSION));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(StringBuilder sb, String str, String str2) {
        sb.append(ContainerUtils.FIELD_DELIMITER);
        sb.append(str);
        sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
        sb.append(m.f(str2));
    }

    private Intent a(Activity activity, Intent intent, Map<String, Object> map, int i2) {
        Intent intent2 = new Intent(activity.getApplicationContext(), AssistActivity.class);
        intent2.putExtra("is_login", true);
        a(activity, intent2, intent, i2);
        if (map == null) {
            return intent2;
        }
        try {
            if (map.containsKey(Constants.KEY_RESTORE_LANDSCAPE)) {
                intent2.putExtra(Constants.KEY_RESTORE_LANDSCAPE, ((Boolean) map.get(Constants.KEY_RESTORE_LANDSCAPE)).booleanValue());
            }
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.BaseApi", "Exception", e2);
        }
        return intent2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Activity activity, int i2, Intent intent, boolean z) {
        Intent intent2 = new Intent(activity.getApplicationContext(), AssistActivity.class);
        if (z) {
            intent2.putExtra("is_qq_mobile_share", true);
        }
        a(activity, intent2, intent, i2);
        try {
            activity.startActivityForResult(intent2, i2);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.BaseApi", "startAssistActivity exception", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Activity activity, Intent intent, int i2) {
        a(activity, intent, i2, (Map<String, Object>) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Activity activity, Intent intent, int i2, Map<String, Object> map) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i2);
        try {
            activity.startActivityForResult(a(activity, intent, map, i2), i2);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.BaseApi", "startAssitActivity exception", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Fragment fragment, Intent intent, int i2, Map<String, Object> map) {
        intent.putExtra(Constants.KEY_REQUEST_CODE, i2);
        try {
            fragment.startActivityForResult(a(fragment.getActivity(), intent, map, i2), i2);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.BaseApi", "startAssitActivity exception", e2);
        }
    }

    private void a(Activity activity, Intent intent, Intent intent2, int i2) {
        PendingIntent activity2;
        if (intent == null || intent2 == null) {
            return;
        }
        try {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 16 && intent2.getClipData() == null) {
                intent2.setClipData(ClipData.newPlainText(null, null));
            }
            int flags = intent2.getFlags();
            if (i3 >= 21) {
                intent2.setFlags(flags & (-196));
            } else if (i3 >= 19) {
                intent2.setFlags(flags & (-68));
            } else {
                intent2.setFlags(flags & (-4));
            }
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.BaseApi", "setActivityIntent security catch exception", th);
        }
        intent.putExtra(AssistActivity.KEY_REQUEST_ORIENTATION, activity.getRequestedOrientation());
        intent.putExtra(AssistActivity.EXTRA_INTENT, intent2);
        try {
            SLog.i("openSDK_LOG.BaseApi", "setActivityIntent requestCode: " + i2);
            if (Build.VERSION.SDK_INT >= 23) {
                activity2 = PendingIntent.getActivity(activity, i2, intent2, 1140850688);
            } else {
                activity2 = PendingIntent.getActivity(activity, i2, intent2, 1073741824);
            }
            intent.putExtra(AssistActivity.KEY_EXTRA_PENDING_INTENT, activity2);
        } catch (Throwable th2) {
            SLog.e("openSDK_LOG.BaseApi", "setActivityIntent create pendingIntent exception", th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Activity activity, Bundle bundle, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.BaseApi", "--handleDownloadLastestQQ");
        new TDialog(activity, "", "https://imgcache.qq.com/ptlogin/static/qzsjump.html?" + HttpUtils.encodeUrl(bundle), null, this.f16153c).show();
    }
}
