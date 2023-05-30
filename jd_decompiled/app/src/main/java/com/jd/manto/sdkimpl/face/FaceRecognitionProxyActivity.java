package com.jd.manto.sdkimpl.face;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jdcn.common_bridge.JdcnCommonBridge;
import com.jingdong.common.R;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.thread.MantoHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class FaceRecognitionProxyActivity extends Activity {

    /* renamed from: g  reason: collision with root package name */
    private ResultReceiver f6793g;

    /* loaded from: classes17.dex */
    private static class FaceResultReceiver extends ResultReceiver {

        /* renamed from: g  reason: collision with root package name */
        private MantoResultCallBack f6794g;

        FaceResultReceiver(Handler handler, MantoResultCallBack mantoResultCallBack) {
            super(handler);
            this.f6794g = mantoResultCallBack;
        }

        @Override // android.os.ResultReceiver
        protected final void onReceiveResult(int i2, Bundle bundle) {
            MantoResultCallBack mantoResultCallBack = this.f6794g;
            if (mantoResultCallBack == null) {
                return;
            }
            if (i2 == 0) {
                mantoResultCallBack.onSuccess(bundle);
            } else {
                mantoResultCallBack.onFailed(bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes17.dex */
    public class a implements JdcnCommonBridge.JdcnCommonBridgeCallback {
        a() {
        }

        @Override // com.jdcn.common_bridge.JdcnCommonBridge.JdcnCommonBridgeCallback
        public void callback(String str) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject("riskResult");
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("result");
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt("routerCode", -1);
                    if (optInt != 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("result", optJSONObject2.toString());
                        bundle.putInt("routerCode", optInt);
                        if (FaceRecognitionProxyActivity.this.f6793g != null) {
                            FaceRecognitionProxyActivity.this.f6793g.send(1, bundle);
                        }
                        FaceRecognitionProxyActivity.this.finish();
                        return;
                    } else if (optJSONObject2 != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("result", optJSONObject2.toString());
                        if (FaceRecognitionProxyActivity.this.f6793g != null) {
                            FaceRecognitionProxyActivity.this.f6793g.send(0, bundle2);
                        }
                        FaceRecognitionProxyActivity.this.finish();
                        return;
                    }
                }
                Bundle bundle3 = new Bundle();
                bundle3.putString("msg", "riskResult error");
                if (FaceRecognitionProxyActivity.this.f6793g != null) {
                    FaceRecognitionProxyActivity.this.f6793g.send(1, bundle3);
                }
                FaceRecognitionProxyActivity.this.finish();
            } catch (JSONException unused) {
                Bundle bundle4 = new Bundle();
                bundle4.putString("msg", "JSONException exist");
                if (FaceRecognitionProxyActivity.this.f6793g != null) {
                    FaceRecognitionProxyActivity.this.f6793g.send(1, bundle4);
                }
                FaceRecognitionProxyActivity.this.finish();
            }
        }
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("msg", "params null error");
            ResultReceiver resultReceiver = this.f6793g;
            if (resultReceiver != null) {
                resultReceiver.send(1, bundle2);
            }
            finish();
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "67");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("routerType", "person_verify");
            jSONObject2.put("function", VerifyTracker.P_CODE_VERIFY);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("businessId", bundle.getString("businessId", ""));
            jSONObject3.put("appName", bundle.getString("appName", ""));
            jSONObject3.put("appAuthorityKey", bundle.getString("appAuthorityKey", ""));
            jSONObject3.put("verifyToken", bundle.getString("verifyToken", ""));
            jSONObject2.put("sdkParams", jSONObject3);
            jSONObject.put("routerParams", jSONObject2);
            JdcnCommonBridge.serviceCall(this, jSONObject.toString(), new a());
        } catch (JSONException unused) {
            Bundle bundle3 = new Bundle();
            bundle3.putString("msg", "params error");
            ResultReceiver resultReceiver2 = this.f6793g;
            if (resultReceiver2 != null) {
                resultReceiver2.send(1, bundle3);
            }
            finish();
        }
    }

    public static void c(Activity activity, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        Intent intent = new Intent(activity, FaceRecognitionProxyActivity.class);
        intent.putExtra("bundle", bundle);
        intent.putExtra("face_result", new FaceResultReceiver(MantoHandler.fetchFreeHandler(Looper.getMainLooper()), mantoResultCallBack));
        activity.startActivity(intent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnStatusBarTintUtil.setStatusBar4Base(this, 1);
        UnStatusBarTintUtil.setStatusBarLightMode(this);
        int i2 = R.anim.nothing;
        overridePendingTransition(i2, i2);
        this.f6793g = (ResultReceiver) getIntent().getParcelableExtra("face_result");
        b(getIntent().getBundleExtra("bundle"));
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}
