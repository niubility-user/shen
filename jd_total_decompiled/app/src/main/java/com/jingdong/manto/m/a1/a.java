package com.jingdong.manto.m.a1;

import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.r;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a extends AbstractMantoModule implements com.jingdong.manto.jsapi.coverview.b {
    private static final Collection<e> a;

    /* renamed from: com.jingdong.manto.m.a1.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0542a implements Runnable {
        final /* synthetic */ MantoCore a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13275c;
        final /* synthetic */ Bundle d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ MantoResultCallBack f13276e;

        RunnableC0542a(MantoCore mantoCore, String str, String str2, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
            this.a = mantoCore;
            this.b = str;
            this.f13275c = str2;
            this.d = bundle;
            this.f13276e = mantoResultCallBack;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Bundle bundle;
            String str;
            Iterator it = a.a.iterator();
            com.jingdong.manto.v.a.a aVar = null;
            while (it.hasNext() && (aVar = ((e) it.next()).a(a.this, this.a, this.b, this.f13275c)) == null) {
            }
            if (aVar != null) {
                int i2 = b.a[((g) aVar.a(0)).ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        this.d.putInt("width", Integer.valueOf(((f) aVar.a(1)).f13279e).intValue());
                        this.d.putInt("height", Integer.valueOf(((f) aVar.a(1)).b).intValue());
                        this.d.putString(MBaseKeyNames.KEY_ORIENTATION, ((f) aVar.a(1)).a);
                        this.d.putString("type", ((f) aVar.a(1)).f13278c);
                        this.d.putString("path", ((f) aVar.a(1)).d);
                        this.f13276e.onSuccess(this.d);
                        return;
                    }
                    this.f13276e.onFailed(this.d);
                }
                bundle = this.d;
                str = "file not found";
            } else {
                bundle = this.d;
                str = "src not support";
            }
            bundle.putString("message", str);
            this.f13276e.onFailed(this.d);
        }
    }

    /* loaded from: classes15.dex */
    static class b {
        static final int[] a;

        static {
            int[] iArr = new int[g.values().length];
            a = iArr;
            try {
                iArr[g.FILE_NOT_FOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.RESOLVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.UNKNOWN_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes15.dex */
    private static final class c implements e {
        private c() {
        }

        /* synthetic */ c(RunnableC0542a runnableC0542a) {
            this();
        }

        @Override // com.jingdong.manto.m.a1.a.e
        public com.jingdong.manto.v.a.a a(com.jingdong.manto.jsapi.coverview.b bVar, MantoCore mantoCore, String str, @NonNull String str2) {
            RunnableC0542a runnableC0542a = null;
            if (str2.startsWith("jdfile://")) {
                com.jingdong.manto.t.d g2 = com.jingdong.manto.t.c.g(str, str2);
                if (g2 == null || TextUtils.isEmpty(g2.b) || !r.a(g2.b)) {
                    return com.jingdong.manto.v.a.a.a(g.FILE_NOT_FOUND);
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(g2.b, options);
                f fVar = new f(runnableC0542a);
                fVar.f13279e = options.outWidth;
                fVar.b = options.outHeight;
                fVar.f13278c = com.jingdong.manto.m.f1.b.a(options);
                fVar.d = g2.a;
                fVar.a = com.jingdong.manto.m.f1.b.b(options) ? com.jingdong.manto.m.f1.b.a(com.jingdong.manto.m.f1.b.b(g2.b)) : "up";
                return com.jingdong.manto.v.a.a.a(g.RESOLVED, fVar);
            }
            return null;
        }
    }

    /* loaded from: classes15.dex */
    private static final class d implements e {
        private d() {
        }

        /* synthetic */ d(RunnableC0542a runnableC0542a) {
            this();
        }

        @Override // com.jingdong.manto.m.a1.a.e
        public com.jingdong.manto.v.a.a a(com.jingdong.manto.jsapi.coverview.b bVar, MantoCore mantoCore, String str, String str2) {
            InputStream a = bVar.a(mantoCore, str2);
            if (a == null) {
                return com.jingdong.manto.v.a.a.a(g.FILE_NOT_FOUND);
            }
            a.mark(0);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(a, new Rect(), options);
            f fVar = new f(null);
            fVar.f13279e = options.outWidth;
            fVar.b = options.outHeight;
            fVar.f13278c = com.jingdong.manto.m.f1.b.a(options);
            com.jingdong.manto.m.f1.b.b(options);
            try {
                a.reset();
            } catch (IOException unused) {
            }
            fVar.a = com.jingdong.manto.m.f1.b.b(options) ? com.jingdong.manto.m.f1.b.a(1) : "up";
            if (a != null) {
                try {
                    a.close();
                } catch (IOException unused2) {
                }
            }
            return com.jingdong.manto.v.a.a.a(g.RESOLVED, fVar);
        }
    }

    /* loaded from: classes15.dex */
    private interface e {
        com.jingdong.manto.v.a.a a(com.jingdong.manto.jsapi.coverview.b bVar, MantoCore mantoCore, String str, String str2);
    }

    /* loaded from: classes15.dex */
    private static class f {
        public String a;
        public int b;

        /* renamed from: c  reason: collision with root package name */
        public String f13278c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public int f13279e;

        private f() {
        }

        /* synthetic */ f(RunnableC0542a runnableC0542a) {
            this();
        }
    }

    /* loaded from: classes15.dex */
    private enum g {
        FILE_NOT_FOUND,
        UNKNOWN_FAIL,
        RESOLVED
    }

    static {
        LinkedList linkedList = new LinkedList();
        RunnableC0542a runnableC0542a = null;
        linkedList.add(new c(runnableC0542a));
        linkedList.add(new d(runnableC0542a));
        a = Collections.unmodifiableCollection(linkedList);
    }

    @Override // com.jingdong.manto.jsapi.coverview.b
    public InputStream a(MantoCore mantoCore, String str) {
        return AbstractMantoModule.readFile(mantoCore, str);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "GetImageInfo";
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        Bundle bundle2 = new Bundle();
        String string = bundle.getString(IMantoBaseModule.APP_UNIQUEID_ID_KEY, "");
        String optString = jSONObject.optString("src");
        if (!TextUtils.isEmpty(optString)) {
            com.jingdong.manto.sdk.thread.a.b(new RunnableC0542a(mantoCore, string, optString, bundle2, mantoResultCallBack));
            return;
        }
        bundle2.putString("message", "invalid data");
        mantoResultCallBack.onFailed(bundle2);
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod("getImageInfo", 1));
    }
}
