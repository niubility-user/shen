package com.jingdong.sdk.jweb.x5;

import android.content.Context;
import android.os.Build;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JWebFactory;
import com.jingdong.sdk.jweb.JWebView;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.X5JsCore;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.TbsLogClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class e {
    private static e b;
    private a a;

    /* loaded from: classes7.dex */
    public class a extends TbsLogClient {
        a(e eVar, Context context) {
            super(context);
        }

        @Override // com.tencent.smtt.utils.TbsLogClient
        public void d(String str, String str2) {
            super.d(str, str2);
        }

        @Override // com.tencent.smtt.utils.TbsLogClient
        public void e(String str, String str2) {
            super.e(str, str2);
        }

        @Override // com.tencent.smtt.utils.TbsLogClient
        public void i(String str, String str2) {
            super.i(str, str2);
        }

        @Override // com.tencent.smtt.utils.TbsLogClient
        public void v(String str, String str2) {
            super.v(str, str2);
        }

        @Override // com.tencent.smtt.utils.TbsLogClient
        public void w(String str, String str2) {
            super.w(str, str2);
        }
    }

    /* loaded from: classes7.dex */
    public static class b {
        static boolean a;
        static Boolean b;

        /* renamed from: c */
        static List<JWebFactory.InitCallback> f15165c = new ArrayList();

        /* loaded from: classes7.dex */
        public class a implements QbSdk.PreInitCallback {

            /* renamed from: com.jingdong.sdk.jweb.x5.e$b$a$a */
            /* loaded from: classes7.dex */
            class C0736a implements TbsListener {
                C0736a(a aVar) {
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadFinish(int i2) {
                    String str = "onDownloadFinish:" + i2;
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onDownloadProgress(int i2) {
                    String str = "onDownloadProgress:" + i2;
                }

                @Override // com.tencent.smtt.sdk.TbsListener
                public void onInstallFinish(int i2) {
                    String str = "onInstallFinish:" + i2;
                }
            }

            a() {
            }

            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onCoreInitFinished() {
            }

            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onViewInitFinished(boolean z) {
                b.b = Boolean.valueOf(z);
                if (!z) {
                    b.b = Boolean.FALSE;
                    QbSdk.setTbsListener(new C0736a(this));
                }
                b.b();
            }
        }

        static void a(Context context, JWebFactory.InitCallback initCallback) {
            if (initCallback != null) {
                f15165c.add(initCallback);
            }
            if (a) {
                if (b != null) {
                    b();
                    return;
                }
                return;
            }
            a = true;
            try {
                a aVar = new a();
                if (!a(WebView.getTbsCoreVersion(context))) {
                    QbSdk.forceSysWebView();
                }
                QbSdk.initX5Environment(context, aVar);
            } catch (Throwable unused) {
            }
        }

        private static boolean a(int i2) {
            return Build.VERSION.SDK_INT <= 28 || i2 <= 0 || i2 >= 45114;
        }

        public static void b() {
            ArrayList arrayList = new ArrayList(f15165c);
            f15165c.clear();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((JWebFactory.InitCallback) it.next()).onFinish(b.booleanValue());
            }
        }

        static boolean c() {
            Boolean bool = b;
            if (bool == null) {
                return false;
            }
            return bool.booleanValue();
        }
    }

    public static e a() {
        if (b == null) {
            b = new e();
        }
        return b;
    }

    public JSContext a(Context context) {
        boolean z;
        boolean z2 = false;
        try {
            z = X5JsCore.canUseX5JsCore(context);
        } catch (Throwable unused) {
            z = false;
        }
        String str = "canUseX5JsCore : " + z;
        if (z) {
            try {
                z2 = X5JsCore.canUseX5JsCoreNewAPI(context);
            } catch (Throwable unused2) {
            }
            String str2 = "canUseX5JsCoreNewAPI : " + z2;
            return z2 ? new c(context) : new com.jingdong.sdk.jweb.x5.b(context);
        }
        return null;
    }

    public JWebView a(JDWebView jDWebView) {
        return new g(jDWebView);
    }

    public void a(Context context, JWebFactory.InitCallback initCallback) {
        a aVar = this.a;
        if (aVar == null) {
            aVar = new a(this, context);
        }
        this.a = aVar;
        TbsLog.setTbsLogClient(aVar);
        b.a(context, initCallback);
    }

    public boolean b() {
        return b.c();
    }
}
