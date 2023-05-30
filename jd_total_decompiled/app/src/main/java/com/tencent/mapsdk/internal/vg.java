package com.tencent.mapsdk.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import com.tencent.map.tools.net.NetManager;
import com.tencent.map.tools.net.NetResponse;
import java.io.Closeable;
import java.io.InputStream;
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
        */
        public Void doInBackground(Context... contextArr) {
            InputStream inputStream;
            byte[] b;
            int a = sg.a();
            StringBuilder sb = new StringBuilder();
            sb.append(vg.f17402c);
            sb.append("apiKey=");
            if (contextArr != null && contextArr.length > 0) {
                sb.append(vg.this.a(contextArr[0].getApplicationContext()));
            }
            NetResponse doStream = NetManager.getInstance().builder().url(sb.toString()).timeOut(3000).doStream();
            try {
                inputStream = doStream.dataStream;
            } catch (Throwable th) {
                th = th;
                inputStream = null;
            }
            try {
                b = ga.b(inputStream);
            } catch (Throwable th2) {
                th = th2;
                try {
                    th.printStackTrace();
                    return null;
                } finally {
                    if (inputStream != null) {
                        ga.a((Closeable) inputStream);
                    }
                    if (doStream != null) {
                        ga.a((Closeable) doStream.dataStream);
                    }
                    if ((a != sg.a()) && vg.this.a != null) {
                        sg.a(vg.this.a, a);
                        ((b) vg.this.b.get()).a();
                    }
                }
            }
            if (b != null && b.length != 0) {
                a = vg.this.a(new String(b), a);
                if (inputStream != null) {
                    ga.a((Closeable) inputStream);
                }
                ga.a((Closeable) doStream.dataStream);
                if (a != sg.a()) {
                }
                return null;
            }
            ga.a((Closeable) doStream.dataStream);
            if ((a != sg.a()) && vg.this.a != null) {
                sg.a(vg.this.a, a);
                ((b) vg.this.b.get()).a();
            }
            return null;
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
