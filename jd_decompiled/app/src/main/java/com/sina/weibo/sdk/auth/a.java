package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.sina.weibo.sdk.b.a;
import com.sina.weibo.sdk.b.c;
import com.sina.weibo.sdk.common.UiError;
import com.sina.weibo.sdk.net.h;
import com.sina.weibo.sdk.web.WebActivity;
import com.tencent.connect.common.Constants;

/* loaded from: classes9.dex */
public final class a {
    public WbAuthListener d;

    public final void a(Activity activity) {
        c.a("WBSsoTag", "startClientAuth()");
        try {
            a.C0775a e2 = com.sina.weibo.sdk.b.a.e(activity);
            Intent intent = new Intent();
            if (e2 == null) {
                intent.setClassName("com.sina.weibo", "com.sina.weibo.SSOActivity");
            } else {
                intent.setClassName(e2.packageName, e2.ag);
            }
            AuthInfo a = com.sina.weibo.sdk.a.a();
            intent.putExtra(PairKey.APP_KEY, a.getAppKey());
            intent.putExtra("redirectUri", a.getRedirectUrl());
            intent.putExtra(Constants.PARAM_SCOPE, a.getScope());
            intent.putExtra("packagename", a.getPackageName());
            intent.putExtra("key_hash", a.getHash());
            intent.putExtra("_weibo_command_type", 3);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            intent.putExtra("_weibo_transaction", sb.toString());
            if (activity == null) {
                this.d.onError(new UiError(-1, "activity is null", ""));
            } else if (com.sina.weibo.sdk.b.a.a(activity, intent)) {
                activity.startActivityForResult(intent, 32973);
                c.a("WBSsoTag", "start SsoActivity ");
            } else {
                this.d.onError(new UiError(-2, "your app is illegal", ""));
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            c.b("WBSsoTag", e3.getMessage());
            this.d.onError(new UiError(-3, "occur exception", e3.getMessage()));
        }
    }

    public final void b(Activity activity) {
        h hVar = new h();
        AuthInfo a = com.sina.weibo.sdk.a.a();
        if (a == null) {
            return;
        }
        hVar.put(Constants.PARAM_CLIENT_ID, a.getAppKey());
        hVar.put("redirect_uri", a.getRedirectUrl());
        hVar.put(Constants.PARAM_SCOPE, a.getScope());
        hVar.put("packagename", a.getPackageName());
        hVar.put("key_hash", a.getHash());
        hVar.put("response_type", "code");
        hVar.put("version", "0041005000");
        hVar.put("luicode", "10000360");
        hVar.put("lfid", "OP_" + a.getAppKey());
        Oauth2AccessToken readAccessToken = AccessTokenHelper.readAccessToken(activity);
        if (readAccessToken != null) {
            String accessToken = readAccessToken.getAccessToken();
            if (!TextUtils.isEmpty(readAccessToken.getAccessToken())) {
                hVar.put("trans_token", accessToken);
                hVar.put("trans_access_token", accessToken);
            }
        }
        String str = "https://open.weibo.cn/oauth2/authorize?" + hVar.g();
        if (this.d != null) {
            b b = b.b();
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            String sb2 = sb.toString();
            b.a(sb2, this.d);
            Intent intent = new Intent(activity, WebActivity.class);
            com.sina.weibo.sdk.web.b.a aVar = new com.sina.weibo.sdk.web.b.a(a, str, sb2);
            Bundle bundle = new Bundle();
            aVar.writeToBundle(bundle);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }
}
