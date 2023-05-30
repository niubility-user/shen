package com.jd.libs.hybrid.offlineload.jsimpl;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.Log;

@Keep
/* loaded from: classes16.dex */
public class HybridInlineJsInterface {
    public static final String JS_OBJ_NAME = "JDHybridTest";
    private static final String TAG = "HybridInlineJsInterface";
    private HybridWebView mWebView;

    /* loaded from: classes16.dex */
    public interface TestCallback {
        public static final int TYPE_DELETE_ERROR = 0;
        public static final int TYPE_DELETE_SUCCESS = 1;
        public static final int TYPE_DOWNLOAD_ERROR = 3;
        public static final int TYPE_HYBRID_ID_ERROR = -1;
        public static final int TYPE_TEST_DATA_ERROR = -3;
        public static final int TYPE_UPDATE_ERROR = 0;
        public static final int TYPE_UPDATE_SUCCESS = 1;

        void callback(int i2);
    }

    public HybridInlineJsInterface(HybridWebView hybridWebView) {
        this.mWebView = hybridWebView;
    }

    /* renamed from: a */
    public /* synthetic */ void b(String str, int i2) {
        sendJSONStr2M(this.mWebView, str, i2);
    }

    /* renamed from: c */
    public /* synthetic */ void d(String str, int i2) {
        sendJSONStr2M(this.mWebView, str, i2);
    }

    /* renamed from: e */
    public /* synthetic */ void f(String str, int i2) {
        sendJSONStr2M(this.mWebView, str, i2);
    }

    public static /* synthetic */ void g(String str, int i2, HybridWebView hybridWebView) {
        String str2 = "javascript:" + str + "('" + i2 + "');";
        Log.d(TAG, "[Test-offline] send data back to H5, run js --> " + str2);
        hybridWebView.evaluateJavascript(str2, null);
    }

    private void sendJSONStr2M(final HybridWebView hybridWebView, final String str, final int i2) {
        View view = hybridWebView.getView();
        Handler handler = view != null ? view.getHandler() : null;
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.post(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.jsimpl.a
            @Override // java.lang.Runnable
            public final void run() {
                HybridInlineJsInterface.g(str, i2, hybridWebView);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0050 A[Catch: Exception -> 0x00c3, TryCatch #0 {Exception -> 0x00c3, blocks: (B:44:0x001d, B:46:0x002e, B:48:0x0034, B:50:0x0041, B:53:0x0048, B:56:0x0050, B:58:0x005c, B:60:0x0068, B:63:0x0075, B:67:0x0095, B:71:0x00b5), top: B:76:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x005c A[Catch: Exception -> 0x00c3, TryCatch #0 {Exception -> 0x00c3, blocks: (B:44:0x001d, B:46:0x002e, B:48:0x0034, B:50:0x0041, B:53:0x0048, B:56:0x0050, B:58:0x005c, B:60:0x0068, B:63:0x0075, B:67:0x0095, B:71:0x00b5), top: B:76:0x001d }] */
    @android.webkit.JavascriptInterface
    @androidx.annotation.Keep
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void handleInlineTestData(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "[Test-offline] received test js data: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "HybridInlineJsInterface"
            com.jd.libs.hybrid.base.util.Log.d(r1, r0)
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L1d
            return
        L1d:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc3
            r0.<init>(r6)     // Catch: java.lang.Exception -> Lc3
            java.lang.String r6 = "callBackName"
            java.lang.String r6 = r0.optString(r6)     // Catch: java.lang.Exception -> Lc3
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> Lc3
            if (r2 == 0) goto L34
            java.lang.String r6 = "[Test-offline] empty h5 callback name"
            com.jd.libs.hybrid.base.util.Log.e(r1, r6)     // Catch: java.lang.Exception -> Lc3
            return
        L34:
            java.lang.String r2 = "type"
            java.lang.String r2 = r0.optString(r2)     // Catch: java.lang.Exception -> Lc3
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> Lc3
            r4 = -1
            if (r3 != 0) goto L4d
            boolean r3 = android.text.TextUtils.isDigitsOnly(r2)     // Catch: java.lang.Exception -> Lc3
            if (r3 != 0) goto L48
            goto L4d
        L48:
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.Exception -> Lc3
            goto L4e
        L4d:
            r2 = -1
        L4e:
            if (r2 != r4) goto L5c
            java.lang.String r0 = "[Test-offline] illegal test type"
            com.jd.libs.hybrid.base.util.Log.e(r1, r0)     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.base.HybridWebView r0 = r5.mWebView     // Catch: java.lang.Exception -> Lc3
            r2 = -3
            r5.sendJSONStr2M(r0, r6, r2)     // Catch: java.lang.Exception -> Lc3
            return
        L5c:
            java.lang.String r3 = "hybridId"
            java.lang.String r0 = r0.optString(r3)     // Catch: java.lang.Exception -> Lc3
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> Lc3
            if (r3 == 0) goto L73
            java.lang.String r0 = "[Test-offline] empty hybrid id"
            com.jd.libs.hybrid.base.util.Log.e(r1, r0)     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.base.HybridWebView r0 = r5.mWebView     // Catch: java.lang.Exception -> Lc3
            r5.sendJSONStr2M(r0, r6, r4)     // Catch: java.lang.Exception -> Lc3
            return
        L73:
            if (r2 != 0) goto L92
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc3
            r2.<init>()     // Catch: java.lang.Exception -> Lc3
            java.lang.String r3 = "[Test-offline] update test package, id: "
            r2.append(r3)     // Catch: java.lang.Exception -> Lc3
            r2.append(r0)     // Catch: java.lang.Exception -> Lc3
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.base.util.Log.d(r1, r2)     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.jsimpl.c r2 = new com.jd.libs.hybrid.offlineload.jsimpl.c     // Catch: java.lang.Exception -> Lc3
            r2.<init>()     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.processor.TestConfigService.requestTestOffline(r0, r2)     // Catch: java.lang.Exception -> Lc3
            return
        L92:
            r3 = 1
            if (r2 != r3) goto Lb2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc3
            r2.<init>()     // Catch: java.lang.Exception -> Lc3
            java.lang.String r3 = "[Test-offline] delete test package, id: "
            r2.append(r3)     // Catch: java.lang.Exception -> Lc3
            r2.append(r0)     // Catch: java.lang.Exception -> Lc3
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.base.util.Log.d(r1, r2)     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.jsimpl.d r2 = new com.jd.libs.hybrid.offlineload.jsimpl.d     // Catch: java.lang.Exception -> Lc3
            r2.<init>()     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.processor.TestConfigService.deleteTestConfig(r0, r2)     // Catch: java.lang.Exception -> Lc3
            return
        Lb2:
            r0 = 2
            if (r2 != r0) goto Lc7
            java.lang.String r0 = "[Test-offline] delete all test packages"
            com.jd.libs.hybrid.base.util.Log.d(r1, r0)     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.jsimpl.b r0 = new com.jd.libs.hybrid.offlineload.jsimpl.b     // Catch: java.lang.Exception -> Lc3
            r0.<init>()     // Catch: java.lang.Exception -> Lc3
            com.jd.libs.hybrid.offlineload.processor.TestConfigService.deleteAllTestConfig(r0)     // Catch: java.lang.Exception -> Lc3
            return
        Lc3:
            r6 = move-exception
            com.jd.libs.hybrid.base.util.Log.e(r1, r6)
        Lc7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface.handleInlineTestData(java.lang.String):void");
    }
}
