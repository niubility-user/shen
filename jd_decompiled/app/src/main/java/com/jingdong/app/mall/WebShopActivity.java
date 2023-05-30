package com.jingdong.app.mall;

import android.content.Intent;
import android.content.res.JDMobiSec;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;

/* loaded from: classes19.dex */
public class WebShopActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    private X5WebView f7878g;

    /* renamed from: h  reason: collision with root package name */
    private String f7879h = JDMobiSec.n1("c68a0e564a984d70550efd3907a85e91");

    private void initView() {
        X5WebView x5WebView = (X5WebView) findViewById(R.id.shop_web);
        this.f7878g = x5WebView;
        String userAgentString = x5WebView.getSettings().getUserAgentString();
        if (!TextUtils.isEmpty(userAgentString)) {
            this.f7878g.getSettings().setUserAgent(userAgentString.replace(JDMobiSec.n1("c49a1b5649"), JDMobiSec.n1("c1921e505cd01136574e")));
        }
        String valueOf = String.valueOf(PackageInfoUtil.getVersionCode());
        StringBuilder sb = new StringBuilder();
        sb.append(valueOf);
        String n1 = JDMobiSec.n1("f19d164358cc3d3c574ffc344c");
        sb.append(n1);
        if (SharedPreferencesUtil.getBoolean(sb.toString(), true)) {
            CookieManager.getInstance().removeAllCookie();
            SharedPreferencesUtil.putBoolean(valueOf + n1, false);
        }
        this.f7878g.loadUrl(this.f7879h);
    }

    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_webshop);
        this.f7879h = getIntent().getStringExtra(JDMobiSec.n1("d99b18734bce"));
        initView();
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (this.f7878g.canGoBack()) {
                this.f7878g.goBack();
                return true;
            }
            Intent intent = new Intent(JDMobiSec.n1("cf901e5456cb0671514ee33847bf1f9dd5ca4f9e499f5f2530ec"));
            intent.setFlags(268435456);
            intent.addCategory(JDMobiSec.n1("cf901e5456cb0671514ee33847bf1f9fd7ca439648c36b4a31ed313d"));
            startActivity(intent);
            return true;
        }
        return true;
    }
}
