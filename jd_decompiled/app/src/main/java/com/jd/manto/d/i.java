package com.jd.manto.d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.JsApiScanCode;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;
import com.jingdong.sdk.deeplink.DeepLinkUri;
import com.jingdong.sdk.oklog.OKLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class i extends JsApiScanCode {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a extends PermissionHelper.PermissionResultCallBack {
        final /* synthetic */ Activity a;
        final /* synthetic */ Bundle b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f6658c;
        final /* synthetic */ boolean d;

        a(Activity activity, Bundle bundle, int i2, boolean z) {
            this.a = activity;
            this.b = bundle;
            this.f6658c = i2;
            this.d = z;
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onCanceled() {
            if (!this.d || this.a.isFinishing()) {
                return;
            }
            this.a.finish();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onDenied() {
            if (!this.d || this.a.isFinishing()) {
                return;
            }
            this.a.finish();
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onGranted() {
            com.jingdong.sdk.deeplink.b.a().b(this.a.getApplicationContext());
            DeepLinkDispatch.startActivityForResult(this.a, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), this.b, this.f6658c);
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onIgnored() {
        }

        @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
        public void onOpenSetting() {
        }
    }

    public static void a(Activity activity, Bundle bundle, int i2, boolean z) {
        OKLog.d("MMM", "=> startCaptureActivity");
        if (PermissionHelper.hasGrantedCamera(activity, PermissionHelper.generateBundle("deeplink", DeepLinkScanHelper.class.getSimpleName(), "startCaptureActivityForResult"), new a(activity, bundle, i2, z))) {
            com.jingdong.sdk.deeplink.b.a().b(activity.getApplicationContext());
            DeepLinkDispatch.startActivityForResult(activity, new DeepLinkUri.Builder().scheme("jingdong").host(DeepLinkScanHelper.HOST_SCAN_CAPTURE).toString(), bundle, i2);
        }
    }

    @Override // com.jingdong.manto.jsapi.refact.JsApiScanCode
    public Bundle OnResult(Intent intent, int i2) {
        Bundle bundle = new Bundle();
        if (i2 != -1) {
            if (i2 != 0) {
                bundle.putString(IMantoBaseModule.ERROR_CODE, "0");
                return bundle;
            }
            bundle.putString(IMantoBaseModule.ERROR_CODE, "-1");
            return bundle;
        }
        String stringExtra = intent.getStringExtra("content");
        bundle.putString("charSet", "utf-8");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "";
        } else {
            Matcher matcher = Pattern.compile(JsApiScanCode.REGEX).matcher(stringExtra);
            if (matcher.matches()) {
                try {
                    new JSONObject(matcher.group(1)).optString("appId").trim();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        bundle.putString("result", stringExtra);
        bundle.putString("rawData", Base64.encodeToString(stringExtra.getBytes(), 0));
        bundle.putString(IMantoBaseModule.ERROR_CODE, "1");
        return bundle;
    }

    public void b(Activity activity, int i2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("needCallback", 1);
        a(activity, bundle2, getRequestCode(), false);
    }

    @Override // com.jingdong.manto.jsapi.refact.JsApiScanCode
    public void scan(Activity activity, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        b(activity, 65535, bundle);
    }
}
