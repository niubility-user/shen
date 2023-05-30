package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import cn.com.union.fido.common.MIMEType;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.g;
import com.tencent.open.utils.i;
import com.tencent.open.utils.j;
import com.tencent.open.utils.k;
import com.tencent.open.utils.m;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SocialApiIml extends BaseApi {
    private Activity a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class a extends DefaultUiListener {
        private IUiListener b;

        /* renamed from: c  reason: collision with root package name */
        private String f17615c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private Bundle f17616e;

        /* renamed from: f  reason: collision with root package name */
        private Activity f17617f;

        a(Activity activity, IUiListener iUiListener, String str, String str2, Bundle bundle) {
            this.b = iUiListener;
            this.f17615c = str;
            this.d = str2;
            this.f17616e = bundle;
            this.f17617f = activity;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            this.b.onCancel();
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            String str;
            try {
                str = ((JSONObject) obj).getString(SocialConstants.PARAM_ENCRY_EOKEN);
            } catch (JSONException e2) {
                e2.printStackTrace();
                SLog.e("openSDK_LOG.SocialApiIml", "OpenApi, EncrytokenListener() onComplete error", e2);
                str = null;
            }
            this.f17616e.putString("encrytoken", str);
            SocialApiIml socialApiIml = SocialApiIml.this;
            socialApiIml.a((Context) socialApiIml.a, this.f17615c, this.f17616e, this.d, this.b);
            if (TextUtils.isEmpty(str)) {
                SLog.d("openSDK_LOG.SocialApiIml", "The token get from qq or qzone is empty. Write temp token to localstorage.");
                SocialApiIml.this.writeEncryToken(this.f17617f);
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            SLog.d("openSDK_LOG.SocialApiIml", "OpenApi, EncryptTokenListener() onError" + uiError.errorMessage);
            this.b.onError(uiError);
        }
    }

    public SocialApiIml(QQToken qQToken) {
        super(qQToken);
    }

    public void ask(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_ASK, bundle, iUiListener);
    }

    @Override // com.tencent.connect.common.BaseApi
    protected Intent b(String str) {
        Intent intent = new Intent();
        intent.setClassName(Constants.PACKAGE_QQ_PAD, str);
        if (m.c(g.a()) && k.a(g.a(), intent)) {
            return intent;
        }
        Intent intent2 = new Intent();
        intent2.setClassName("com.tencent.mobileqq", str);
        if (!k.a(g.a(), intent2) || k.c(g.a(), "4.7") < 0) {
            Intent intent3 = new Intent();
            intent3.setClassName("com.qzone", str);
            if (k.a(g.a(), intent3) && k.a(k.a(g.a(), "com.qzone"), "4.2") >= 0 && k.a(g.a(), intent3.getComponent().getPackageName(), Constants.SIGNATRUE_QZONE)) {
                return intent3;
            }
            return null;
        }
        return intent2;
    }

    public void gift(Activity activity, Bundle bundle, IUiListener iUiListener) {
        a(activity, SocialConstants.ACTION_GIFT, bundle, iUiListener);
    }

    public void invite(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent c2 = c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (c2 == null) {
            SLog.i("openSDK_LOG.SocialApiIml", "--invite--friend chooser not found");
            c2 = c(SocialConstants.ACTIVITY_INVITE);
        }
        bundle.putAll(b());
        a(activity, c2, SocialConstants.ACTION_INVITE, bundle, j.a().a(g.a(), "https://imgcache.qq.com/open/mobile/invite/sdk_invite.html?"), iUiListener, false);
    }

    public void story(Activity activity, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent c2 = c(SocialConstants.ACTIVITY_STORY);
        bundle.putAll(b());
        a(activity, c2, SocialConstants.ACTION_STORY, bundle, j.a().a(g.a(), "https://imgcache.qq.com/open/mobile/sendstory/sdk_sendstory_v1.3.html?"), iUiListener, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void writeEncryToken(Context context) {
        String str;
        String accessToken = this.f16153c.getAccessToken();
        String appId = this.f16153c.getAppId();
        String openId = this.f16153c.getOpenId();
        if (accessToken == null || accessToken.length() <= 0 || appId == null || appId.length() <= 0 || openId == null || openId.length() <= 0) {
            str = null;
        } else {
            str = m.g("tencent&sdk&qazxc***14969%%" + accessToken + appId + openId + "qzone3.4");
        }
        com.tencent.open.c.b bVar = new com.tencent.open.c.b(context);
        WebSettings settings = bVar.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        com.tencent.open.web.a.a(bVar);
        String str2 = "<!DOCTYPE HTML><html lang=\"en-US\"><head><meta charset=\"UTF-8\"><title>localStorage Test</title><script type=\"text/javascript\">document.domain = 'qq.com';localStorage[\"" + this.f16153c.getOpenId() + CartConstant.KEY_YB_INFO_LINK + this.f16153c.getAppId() + "\"]=\"" + str + "\";</script></head><body></body></html>";
        String a2 = j.a().a(context, "https://imgcache.qq.com");
        bVar.loadDataWithBaseURL(a2, str2, MIMEType.MIME_TYPE_HTML, "utf-8", a2);
    }

    public SocialApiIml(com.tencent.connect.auth.c cVar, QQToken qQToken) {
        super(cVar, qQToken);
    }

    private void a(Activity activity, String str, Bundle bundle, IUiListener iUiListener) {
        this.a = activity;
        Intent c2 = c(SocialConstants.ACTIVITY_FRIEND_CHOOSER);
        if (c2 == null) {
            SLog.i("openSDK_LOG.SocialApiIml", "--askgift--friend chooser not found");
            c2 = c(SocialConstants.ACTIVITY_ASK_GIFT);
        }
        Intent intent = c2;
        bundle.putAll(b());
        if (SocialConstants.ACTION_ASK.equals(str)) {
            bundle.putString("type", "request");
        } else if (SocialConstants.ACTION_GIFT.equals(str)) {
            bundle.putString("type", SocialConstants.TYPE_FREEGIFT);
        }
        a(activity, intent, str, bundle, j.a().a(g.a(), "https://imgcache.qq.com/open/mobile/request/sdk_request.html?"), iUiListener, false);
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, String str2, IUiListener iUiListener, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("-->handleIntent action = ");
        sb.append(str);
        sb.append(", activityIntent = null ? ");
        boolean z2 = true;
        sb.append(intent == null);
        SLog.i("openSDK_LOG.SocialApiIml", sb.toString());
        if (intent != null) {
            a(activity, intent, str, bundle, iUiListener);
            return;
        }
        i a2 = i.a(g.a(), this.f16153c.getAppId());
        if (!z && !a2.b("C_LoginH5")) {
            z2 = false;
        }
        if (z2) {
            a(activity, str, bundle, str2, iUiListener);
        } else {
            a(activity, bundle, iUiListener);
        }
    }

    private void a(Activity activity, Intent intent, String str, Bundle bundle, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithAgent action = " + str);
        intent.putExtra(Constants.KEY_ACTION, str);
        intent.putExtra(Constants.KEY_PARAMS, bundle);
        UIListenerManager.getInstance().setListenerWithRequestcode(11105, iUiListener);
        a(activity, intent, 11105);
    }

    private void a(Activity activity, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5 action = " + str);
        Intent c2 = c();
        IUiListener aVar = new a(activity, iUiListener, str, str2, bundle);
        Intent b = b("com.tencent.open.agent.EncryTokenActivity");
        if (b != null && c2 != null && c2.getComponent() != null && b.getComponent() != null && c2.getComponent().getPackageName().equals(b.getComponent().getPackageName())) {
            b.putExtra("oauth_consumer_key", this.f16153c.getAppId());
            b.putExtra("openid", this.f16153c.getOpenId());
            b.putExtra(Constants.PARAM_ACCESS_TOKEN, this.f16153c.getAccessToken());
            b.putExtra(Constants.KEY_ACTION, SocialConstants.ACTION_CHECK_TOKEN);
            SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--found token activity");
            UIListenerManager.getInstance().setListenerWithRequestcode(11106, aVar);
            a(activity, b, 11106);
            return;
        }
        SLog.i("openSDK_LOG.SocialApiIml", "-->handleIntentWithH5--token activity not found");
        String g2 = m.g("tencent&sdk&qazxc***14969%%" + this.f16153c.getAccessToken() + this.f16153c.getAppId() + this.f16153c.getOpenId() + "qzone3.4");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SocialConstants.PARAM_ENCRY_EOKEN, g2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        aVar.onComplete(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, Bundle bundle, String str2, IUiListener iUiListener) {
        SLog.v("openSDK_LOG.SocialApiIml", "OpenUi, showDialog --start");
        CookieSyncManager.createInstance(context);
        bundle.putString("oauth_consumer_key", this.f16153c.getAppId());
        if (this.f16153c.isSessionValid()) {
            bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.f16153c.getAccessToken());
        }
        String openId = this.f16153c.getOpenId();
        if (openId != null) {
            bundle.putString("openid", openId);
        }
        try {
            bundle.putString(Constants.PARAM_PLATFORM_ID, g.a().getSharedPreferences(Constants.PREFERENCE_PF, 0).getString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF));
        } catch (Exception e2) {
            e2.printStackTrace();
            bundle.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str3 = str2 + HttpUtils.encodeUrl(bundle);
        SLog.d("openSDK_LOG.SocialApiIml", "OpenUi, showDialog TDialog");
        if (!SocialConstants.ACTION_CHALLENGE.equals(str) && !SocialConstants.ACTION_BRAG.equals(str)) {
            new TDialog(this.a, str, str3, iUiListener, this.f16153c).show();
            return;
        }
        SLog.d("openSDK_LOG.SocialApiIml", "OpenUi, showDialog PKDialog");
        new d(this.a, str, str3, iUiListener, this.f16153c).show();
    }
}