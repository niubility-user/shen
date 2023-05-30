package com.jingdong.manto.m.a1;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.t.c;
import com.jingdong.manto.t.d;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.r;
import com.jingdong.manto.utils.z;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class b extends AbstractMantoModule {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13281c;
        final /* synthetic */ MantoResultCallBack d;

        a(String str, JSONObject jSONObject, String str2, MantoResultCallBack mantoResultCallBack) {
            this.a = str;
            this.b = jSONObject;
            this.f13281c = str2;
            this.d = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            if ("saveImageToPhotosAlbum".equals(this.a) || "saveVideoToPhotosAlbum".equals(this.a)) {
                b.this.a("saveImageToPhotosAlbum".equals(this.a), this.b, this.f13281c, this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.m.a1.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0543b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ Bundle f13283c;
        final /* synthetic */ MantoResultCallBack d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ boolean f13284e;

        RunnableC0543b(b bVar, String str, String str2, Bundle bundle, MantoResultCallBack mantoResultCallBack, boolean z) {
            this.a = str;
            this.b = str2;
            this.f13283c = bundle;
            this.d = mantoResultCallBack;
            this.f13284e = z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x005a, code lost:
            if (com.jingdong.manto.utils.z.a(r1, true) != false) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x007e, code lost:
            if (com.jingdong.manto.utils.z.a(r1, false) != false) goto L28;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
            r5.d.onSuccess(r5.f13283c);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            Bundle bundle;
            String str;
            d g2 = c.g(this.a, this.b);
            if (g2 == null || TextUtils.isEmpty(g2.b)) {
                bundle = this.f13283c;
                str = "fail file not exists";
            } else if (z.a(this.f13284e, g2.f14209c)) {
                if (!this.f13284e) {
                    String e2 = z.e("mp4");
                    if (!r.a(g2.b, e2)) {
                        e2 = null;
                    }
                    if (!TextUtils.isEmpty(e2)) {
                    }
                    this.d.onFailed(this.f13283c);
                }
                String b = z.b(g2.f14209c);
                if (TextUtils.isEmpty(b)) {
                    b = "jpg";
                }
                String e3 = z.e(b);
                MantoLog.d("better", CartConstant.KEY_YB_INFO_LINK + e3);
                if (r.a(g2.b, e3, false)) {
                }
                this.d.onFailed(this.f13283c);
                return;
            } else {
                bundle = this.f13283c;
                str = "fail invalid file type";
            }
            bundle.putString("message", str);
            this.d.onFailed(this.f13283c);
        }
    }

    public void a(boolean z, JSONObject jSONObject, String str, MantoResultCallBack mantoResultCallBack) {
        Bundle bundle = new Bundle();
        String optString = jSONObject.optString(TbsReaderView.KEY_FILE_PATH, "");
        if (Build.VERSION.SDK_INT < 29) {
            bundle.putString("message", "under Android Q  not support");
            mantoResultCallBack.onFailed(bundle);
        } else if (!TextUtils.isEmpty(optString)) {
            com.jingdong.manto.sdk.thread.a.b(new RunnableC0543b(this, str, optString, bundle, mantoResultCallBack, z));
        } else {
            bundle.putString("message", "fail filePath invalid");
            mantoResultCallBack.onFailed(bundle);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "SavePhotos";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("json"));
            String string = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
            new Bundle();
            MantoThreadUtils.runOnUIThread(new a(str, jSONObject, string, mantoResultCallBack));
        } catch (JSONException e2) {
            e2.printStackTrace();
            mantoResultCallBack.onFailed(null);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("saveImageToPhotosAlbum", 1));
        list.add(new JsApiMethod("saveVideoToPhotosAlbum", 1));
    }
}
