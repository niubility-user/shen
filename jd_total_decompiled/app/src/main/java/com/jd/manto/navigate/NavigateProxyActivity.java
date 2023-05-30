package com.jd.manto.navigate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.manto.d.x;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class NavigateProxyActivity extends BaseActivity {
    public static void A(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("manto_extra_navigate_url", str);
        B(context, 0, bundle);
    }

    private static void B(Context context, int i2, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String a = x.a(context);
        Intent intent = new Intent(context, NavigateProxyActivity.class);
        intent.putExtra("manto_extra_type", i2);
        intent.putExtra("manto_extra_params", bundle);
        intent.putExtra("manto_extra_action", a);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        context.startActivity(intent);
    }

    private void C(Intent intent) {
        switch (intent.getIntExtra("manto_extra_type", 0)) {
            case 0:
                J(intent);
                return;
            case 1:
                E(intent);
                return;
            case 2:
                H(intent);
                return;
            case 3:
                Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
                if (bundleExtra == null) {
                    return;
                }
                String string = bundleExtra.getString("url");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                G(string, intent.getStringExtra("manto_extra_action"));
                return;
            case 4:
                I(intent);
                return;
            case 5:
                F(intent);
                return;
            case 6:
                D(intent);
                return;
            default:
                finish();
                return;
        }
    }

    private void E(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
        if (bundleExtra == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("manto_extra_action");
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des(JumpUtil.VALUE_DES_FILLORDER);
        builder.category("jump");
        builder.map("sourceType", 6);
        builder.map("skuSource", 25);
        builder.map("authKey", "4d7d50d8-7d78-4ad4-8024-84da9642fda2");
        builder.map("wareId", bundleExtra.getString("wareId", ""));
        builder.map("wareNum", Integer.valueOf(bundleExtra.getInt("wareNum", 1)));
        if (bundleExtra.containsKey(CartConstant.KEY_EXTFLAG)) {
            builder.map(CartConstant.KEY_EXTFLAG, bundleExtra.getString(CartConstant.KEY_EXTFLAG, "{}"));
        }
        if (bundleExtra.containsKey(JumpUtil.VALUE_DES_BUSINESS_MAP)) {
            builder.map(JumpUtil.VALUE_DES_BUSINESS_MAP, bundleExtra.getString(JumpUtil.VALUE_DES_BUSINESS_MAP, "{}"));
        }
        if (bundleExtra.containsKey("openAppSkuInfo")) {
            builder.map("openAppSkuInfo", bundleExtra.getString("openAppSkuInfo", "{}"));
        }
        builder.map("fromManto", Boolean.TRUE);
        builder.map("mantoShareAction", stringExtra);
        builder.build().jump(this);
    }

    private void F(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
        if (bundleExtra == null) {
            return;
        }
        String string = bundleExtra.getString("venderId", "");
        String string2 = bundleExtra.getString("entry", "");
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des(JumpUtil.VALUE_DES_DD_NEW_CHAT);
        builder.category("jump");
        builder.map("venderId", string);
        builder.map("entry", string2);
        if (bundleExtra.containsKey("sku")) {
            builder.map("sku", bundleExtra.getString("sku"));
        }
        if (bundleExtra.containsKey("orderId")) {
            builder.map("orderId", bundleExtra.getString("orderId"));
        }
        builder.map("fromManto", Boolean.TRUE);
        builder.build().jump(this);
    }

    private void G(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des("m");
        builder.category("jump");
        builder.map("url", str);
        builder.map("fromManto", Boolean.TRUE);
        builder.map("mantoShareAction", str2);
        builder.build().jump(this);
    }

    private void H(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
        if (bundleExtra == null) {
            return;
        }
        String string = bundleExtra.getString("url");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String config = JDMobileConfig.getInstance().getConfig("manto", "feedback", "url", "http://mp-static.jd.com/feedback.html");
        G(config + "?" + string, intent.getStringExtra("manto_extra_action"));
    }

    private void I(Intent intent) {
        String stringExtra = intent.getStringExtra("manto_extra_action");
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des(JumpUtil.VALUE_DES_FILLORDER);
        builder.category("jump");
        builder.map("sourceType", 6);
        builder.map("skuSource", 50);
        builder.map("authKey", "c0c7c76d-30bd-3dca-efc9-6f40275bdc0a");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isMultiSku", "1");
        } catch (Exception unused) {
        }
        builder.map(CartConstant.KEY_EXTFLAG, jSONObject.toString());
        builder.map("fromManto", Boolean.TRUE);
        builder.map("mantoShareAction", stringExtra);
        builder.build().jump(this);
    }

    private void J(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
        if (bundleExtra == null) {
            return;
        }
        String string = bundleExtra.getString("manto_extra_navigate_url");
        if (string == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("manto_extra_action");
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder(Uri.parse(string));
        builder.map("fromManto", Boolean.TRUE);
        builder.map("mantoShareAction", stringExtra);
        builder.build().jump(this);
    }

    public static void u(Context context, Bundle bundle) {
        B(context, 6, bundle);
    }

    public static void v(Context context, Bundle bundle) {
        B(context, 1, bundle);
    }

    public static void w(Context context, Bundle bundle) {
        B(context, 5, bundle);
    }

    public static void x(Context context, Bundle bundle) {
        B(context, 2, bundle);
    }

    public static void y(Context context, Bundle bundle) {
        B(context, 3, bundle);
    }

    public static void z(Context context, Bundle bundle) {
        B(context, 4, bundle);
    }

    public void D(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("manto_extra_params");
        if (bundleExtra == null) {
            return;
        }
        String string = bundleExtra.getString("venderId", "");
        OpenAppJumpBuilder.Builder builder = new OpenAppJumpBuilder.Builder();
        builder.host(OpenAppConstant.HOST_1);
        builder.scheme(OpenAppConstant.SCHEME_OPENAPP_1);
        builder.des(JumpUtil.VALUE_DES_FILLORDER);
        builder.category("jump");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(string);
            jSONObject.put("validVenderIds", jSONArray);
        } catch (Exception unused) {
        }
        builder.map(CartConstant.KEY_EXTFLAG, jSONObject.toString());
        builder.map("fromManto", Boolean.TRUE);
        builder.build().jump(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            C(intent);
            finish();
            return;
        }
        finish();
    }
}
