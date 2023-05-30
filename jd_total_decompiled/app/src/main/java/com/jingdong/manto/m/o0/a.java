package com.jingdong.manto.m.o0;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.sdk.api.IFaceDetect;
import com.jingdong.manto.utils.t;
import java.nio.ByteBuffer;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule {
    private ByteBuffer a;

    /* renamed from: com.jingdong.manto.m.o0.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0586a implements IFaceDetect.FaceInitCallback {
        final /* synthetic */ MantoResultCallBack a;
        final /* synthetic */ Bundle b;

        C0586a(a aVar, MantoResultCallBack mantoResultCallBack, Bundle bundle) {
            this.a = mantoResultCallBack;
            this.b = bundle;
        }

        @Override // com.jingdong.manto.sdk.api.IFaceDetect.FaceInitCallback
        public void onFailed(String str) {
            this.b.putString("error", str);
            this.a.onFailed(this.b);
        }

        @Override // com.jingdong.manto.sdk.api.IFaceDetect.FaceInitCallback
        public void onSuccess() {
            this.a.onSuccess(this.b);
        }
    }

    /* loaded from: classes15.dex */
    class b implements IFaceDetect.FaceDetectCallback {
        final /* synthetic */ MantoResultCallBack a;

        b(a aVar, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoResultCallBack;
        }

        @Override // com.jingdong.manto.sdk.api.IFaceDetect.FaceDetectCallback
        public void onFailed(Bundle bundle) {
            this.a.onFailed(bundle);
        }

        @Override // com.jingdong.manto.sdk.api.IFaceDetect.FaceDetectCallback
        public void onResult(Bundle bundle) {
            this.a.onSuccess(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "faceDetect";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public final void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        byte[] bytes;
        Bundle bundle2 = new Bundle(1);
        IFaceDetect iFaceDetect = (IFaceDetect) com.jingdong.a.n(IFaceDetect.class);
        if (iFaceDetect == null) {
            mantoResultCallBack.onFailed(bundle2);
        } else if (TextUtils.equals(str, "initFaceDetect")) {
            iFaceDetect.init(com.jingdong.a.g(), new C0586a(this, mantoResultCallBack, bundle2));
        } else if (TextUtils.equals(str, "stopFaceDetect")) {
            iFaceDetect.release();
            mantoResultCallBack.onSuccess(bundle2);
        } else if (TextUtils.equals(str, "faceDetect")) {
            try {
                jSONObject = new JSONObject(bundle.getString("params"));
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                mantoResultCallBack.onFailed(null);
                return;
            }
            Bundle bundle3 = new Bundle();
            if (bundle.getBoolean(IMantoBaseModule.HAS_NATIVE_BUFFER)) {
                ByteBuffer byteBuffer = this.a;
                if (byteBuffer == null) {
                    bundle3.putString("message", "ByteBuffer is null, no message");
                    mantoResultCallBack.onFailed(bundle3);
                    return;
                }
                bytes = t.a(byteBuffer);
            } else {
                bytes = jSONObject.optString("frameBuffer").getBytes();
            }
            int optInt = jSONObject.optInt("width");
            int optInt2 = jSONObject.optInt("height");
            if (bytes != null && bytes.length != 0) {
                iFaceDetect.getJfaceTracker(bytes, optInt, optInt2, new b(this, mantoResultCallBack));
                return;
            }
            bundle3.putString("message", "track data is null");
            mantoResultCallBack.onFailed(bundle2);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Object opt = jSONObject.opt("frameBuffer");
        if (opt != null && (opt instanceof ByteBuffer)) {
            this.a = (ByteBuffer) opt;
            jSONObject.remove("frameBuffer");
        }
        bundle.putString("params", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("initFaceDetect", 1));
        list.add(new JsApiMethod("stopFaceDetect", 1));
        list.add(new JsApiMethod("faceDetect", 1));
    }
}
