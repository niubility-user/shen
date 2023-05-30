package com.tencent.mobileqq.openpay.api;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.mobileqq.openpay.data.base.BaseApi;
import com.tencent.mobileqq.openpay.data.pay.PayResponse;

/* loaded from: classes9.dex */
final class a implements IOpenApi {
    private final String[] a = {"pay"};
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        this.mContext = context;
    }

    private static int a(String str, String str2) {
        if (str == null) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i2 = 0;
        while (i2 < split.length && i2 < split2.length) {
            try {
                int parseInt = Integer.parseInt(split[i2]);
                int parseInt2 = Integer.parseInt(split2[i2]);
                if (parseInt < parseInt2) {
                    return -1;
                }
                if (parseInt > parseInt2) {
                    return 1;
                }
                i2++;
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
                return str.compareTo(str2);
            }
        }
        if (split.length > i2) {
            return 1;
        }
        return split2.length > i2 ? -1 : 0;
    }

    private String a() {
        try {
            PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo("com.tencent.mobileqq", 0);
            if (packageInfo != null && !TextUtils.isEmpty(packageInfo.versionName)) {
                return packageInfo.versionName;
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private boolean a(BaseApi baseApi) {
        Bundle bundle = new Bundle();
        baseApi.toBundle(bundle);
        try {
            String packageName = this.mContext.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            bundle.putString("_mqqpay_payapi_packageName", packageName);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("mqqwallet://open_pay/"));
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtras(bundle);
            intent.addFlags(268435456).addFlags(134217728);
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean b(BaseApi baseApi) {
        try {
            String packageName = this.mContext.getPackageName();
            if (TextUtils.isEmpty(packageName)) {
                return false;
            }
            Bundle bundle = new Bundle();
            baseApi.toBundle(bundle);
            bundle.putString("_mqqpay_baseapi_pkgname", packageName);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("mqqwallet://open_pay/"));
            intent.setPackage("com.tencent.mobileqq");
            intent.putExtras(bundle);
            intent.addFlags(268435456).addFlags(134217728);
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.tencent.mobileqq.openpay.api.IOpenApi
    public final boolean execApi(BaseApi baseApi) {
        String a;
        if (baseApi != null && baseApi.checkParams() && "pay".compareTo(baseApi.getApiName()) == 0 && (a = a()) != null) {
            if (a(a, "5.3.0") >= 0) {
                return a(baseApi);
            }
            if (a(a, "4.7.2") >= 0) {
                return b(baseApi);
            }
        }
        return false;
    }

    @Override // com.tencent.mobileqq.openpay.api.IOpenApi
    public final boolean handleIntent(Intent intent, IOpenApiListener iOpenApiListener) {
        Bundle extras;
        if (intent != null && iOpenApiListener != null) {
            String stringExtra = intent.getStringExtra("com_tencent_mobileqq_open_pay");
            if (TextUtils.isEmpty(stringExtra) || stringExtra.compareTo("com.tencent.mobileqq.open.pay") != 0 || (extras = intent.getExtras()) == null) {
                return false;
            }
            PayResponse payResponse = null;
            if (extras.getInt("_mqqpay_baseapi_apimark", -1) == 1) {
                payResponse = new PayResponse();
                payResponse.fromBundle(extras);
            }
            if (payResponse != null && payResponse.checkParams()) {
                iOpenApiListener.onOpenResponse(payResponse);
                return true;
            }
        }
        return false;
    }

    @Override // com.tencent.mobileqq.openpay.api.IOpenApi
    public final boolean isMobileQQInstalled() {
        return a() != null;
    }

    @Override // com.tencent.mobileqq.openpay.api.IOpenApi
    public final boolean isMobileQQSupportApi(String str) {
        int i2;
        String a;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        while (true) {
            i2 = (i2 <= 0 && str.compareTo(this.a[0]) != 0) ? i2 + 1 : 0;
        }
        return i2 <= 0 && (a = a()) != null && a(a, "4.7.2") >= 0;
    }
}
