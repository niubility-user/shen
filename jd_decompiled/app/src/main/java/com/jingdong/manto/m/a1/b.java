package com.jingdong.manto.m.a1;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoThreadUtils;
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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r5 = this;
                java.lang.String r0 = r5.a
                java.lang.String r1 = r5.b
                com.jingdong.manto.t.d r0 = com.jingdong.manto.t.c.g(r0, r1)
                java.lang.String r1 = "message"
                if (r0 == 0) goto L98
                java.lang.String r2 = r0.b
                boolean r2 = android.text.TextUtils.isEmpty(r2)
                if (r2 == 0) goto L16
                goto L98
            L16:
                boolean r2 = r5.f13284e
                java.lang.String r3 = r0.f14209c
                boolean r2 = com.jingdong.manto.utils.z.a(r2, r3)
                if (r2 == 0) goto L90
                boolean r1 = r5.f13284e
                r2 = 0
                if (r1 == 0) goto L5d
                java.lang.String r1 = r0.f14209c
                java.lang.String r1 = com.jingdong.manto.utils.z.b(r1)
                boolean r3 = android.text.TextUtils.isEmpty(r1)
                if (r3 == 0) goto L33
                java.lang.String r1 = "jpg"
            L33:
                java.lang.String r1 = com.jingdong.manto.utils.z.e(r1)
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "_"
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                java.lang.String r4 = "better"
                com.jingdong.manto.utils.MantoLog.d(r4, r3)
                java.lang.String r0 = r0.b
                boolean r0 = com.jingdong.manto.utils.r.a(r0, r1, r2)
                if (r0 == 0) goto L88
                r0 = 1
                boolean r0 = com.jingdong.manto.utils.z.a(r1, r0)
                if (r0 == 0) goto L88
                goto L80
            L5d:
                java.lang.String r1 = "mp4"
                java.lang.String r1 = com.jingdong.manto.utils.z.e(r1)
                java.lang.String r0 = r0.b
                boolean r0 = com.jingdong.manto.utils.r.a(r0, r1)
                if (r0 != 0) goto L6c
                r1 = 0
            L6c:
                boolean r0 = android.text.TextUtils.isEmpty(r1)
                if (r0 == 0) goto L7a
            L72:
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r5.d
                android.os.Bundle r1 = r5.f13283c
                r0.onFailed(r1)
                return
            L7a:
                boolean r0 = com.jingdong.manto.utils.z.a(r1, r2)
                if (r0 == 0) goto L88
            L80:
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r5.d
                android.os.Bundle r1 = r5.f13283c
                r0.onSuccess(r1)
                goto L8f
            L88:
                com.jingdong.manto.jsapi.openmodule.MantoResultCallBack r0 = r5.d
                android.os.Bundle r1 = r5.f13283c
                r0.onFailed(r1)
            L8f:
                return
            L90:
                android.os.Bundle r0 = r5.f13283c
                java.lang.String r2 = "fail invalid file type"
            L94:
                r0.putString(r1, r2)
                goto L72
            L98:
                android.os.Bundle r0 = r5.f13283c
                java.lang.String r2 = "fail file not exists"
                goto L94
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.m.a1.b.RunnableC0543b.run():void");
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
