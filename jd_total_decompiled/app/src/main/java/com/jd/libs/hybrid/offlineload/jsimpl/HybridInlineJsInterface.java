package com.jd.libs.hybrid.offlineload.jsimpl;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.processor.TestConfigService;
import com.jingdong.common.web.managers.WebPerfManager;
import org.json.JSONObject;

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

    /* JADX WARN: Removed duplicated region for block: B:95:0x0050 A[Catch: Exception -> 0x00c3, TryCatch #0 {Exception -> 0x00c3, blocks: (B:83:0x001d, B:85:0x002e, B:87:0x0034, B:89:0x0041, B:92:0x0048, B:95:0x0050, B:97:0x005c, B:99:0x0068, B:102:0x0075, B:106:0x0095, B:110:0x00b5), top: B:115:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x005c A[Catch: Exception -> 0x00c3, TryCatch #0 {Exception -> 0x00c3, blocks: (B:83:0x001d, B:85:0x002e, B:87:0x0034, B:89:0x0041, B:92:0x0048, B:95:0x0050, B:97:0x005c, B:99:0x0068, B:102:0x0075, B:106:0x0095, B:110:0x00b5), top: B:115:0x001d }] */
    @JavascriptInterface
    @Keep
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void handleInlineTestData(String str) {
        int i2;
        Log.d(TAG, "[Test-offline] received test js data: " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("callBackName");
            if (TextUtils.isEmpty(optString)) {
                Log.e(TAG, "[Test-offline] empty h5 callback name");
                return;
            }
            String optString2 = jSONObject.optString("type");
            if (!TextUtils.isEmpty(optString2) && TextUtils.isDigitsOnly(optString2)) {
                i2 = Integer.parseInt(optString2);
                if (i2 != -1) {
                    Log.e(TAG, "[Test-offline] illegal test type");
                    sendJSONStr2M(this.mWebView, optString, -3);
                    return;
                }
                String optString3 = jSONObject.optString(WebPerfManager.HYBRID_ID);
                if (TextUtils.isEmpty(optString3)) {
                    Log.e(TAG, "[Test-offline] empty hybrid id");
                    sendJSONStr2M(this.mWebView, optString, -1);
                    return;
                } else if (i2 == 0) {
                    Log.d(TAG, "[Test-offline] update test package, id: " + optString3);
                    TestConfigService.requestTestOffline(optString3, new TestCallback() { // from class: com.jd.libs.hybrid.offlineload.jsimpl.c
                        {
                            HybridInlineJsInterface.this = this;
                        }

                        @Override // com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface.TestCallback
                        public final void callback(int i3) {
                            HybridInlineJsInterface.this.b(optString, i3);
                        }
                    });
                    return;
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        Log.d(TAG, "[Test-offline] delete all test packages");
                        TestConfigService.deleteAllTestConfig(new TestCallback() { // from class: com.jd.libs.hybrid.offlineload.jsimpl.b
                            {
                                HybridInlineJsInterface.this = this;
                            }

                            @Override // com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface.TestCallback
                            public final void callback(int i3) {
                                HybridInlineJsInterface.this.f(optString, i3);
                            }
                        });
                        return;
                    }
                    return;
                } else {
                    Log.d(TAG, "[Test-offline] delete test package, id: " + optString3);
                    TestConfigService.deleteTestConfig(optString3, new TestCallback() { // from class: com.jd.libs.hybrid.offlineload.jsimpl.d
                        {
                            HybridInlineJsInterface.this = this;
                        }

                        @Override // com.jd.libs.hybrid.offlineload.jsimpl.HybridInlineJsInterface.TestCallback
                        public final void callback(int i3) {
                            HybridInlineJsInterface.this.d(optString, i3);
                        }
                    });
                    return;
                }
            }
            i2 = -1;
            if (i2 != -1) {
            }
        } catch (Exception e2) {
            Log.e(TAG, e2);
        }
    }
}
