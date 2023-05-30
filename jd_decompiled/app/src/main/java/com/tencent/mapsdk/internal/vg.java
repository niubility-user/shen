package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class vg {

    /* renamed from: c  reason: collision with root package name */
    private static final String f17402c = "https://confinfo.map.qq.com/confinfo?";
    private Context a;
    private WeakReference<b> b;

    /* loaded from: classes9.dex */
    public interface b {
        void a();
    }

    /* loaded from: classes9.dex */
    public class c extends AsyncTask<Context, Void, Void> {
        private c() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0074, code lost:
            if (r7.a.a != null) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
            com.tencent.mapsdk.internal.ga.a((java.io.Closeable) r4);
         */
        @Override // android.os.AsyncTask
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void doInBackground(android.content.Context... r8) {
            /*
                Method dump skipped, instructions count: 287
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.vg.c.doInBackground(android.content.Context[]):java.lang.Void");
        }
    }

    public vg(Context context, b bVar) {
        this.a = context;
        this.b = new WeakReference<>(bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(String str, int i2) {
        JSONObject optJSONObject;
        JSONObject jSONObject = new JSONObject(str);
        return (jSONObject.optInt("error", -1) != 0 || (optJSONObject = jSONObject.optJSONObject("info")) == null) ? i2 : optJSONObject.optInt("scenic", i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(Context context) {
        Bundle bundle;
        if (context == null) {
            return "";
        }
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? "" : bundle.getString("TencentMapSDK");
    }

    public void a() {
        new c().execute(this.a);
    }
}
