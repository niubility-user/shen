package com.jd.verify.View;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.Window;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.verify.CallBack;
import com.jd.verify.common.JSInterface;
import com.jd.verify.j.f;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class e extends d implements DialogInterface.OnKeyListener {
    private com.jd.verify.common.b b;

    /* renamed from: c  reason: collision with root package name */
    private CallBack f7128c;
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private com.jd.verify.View.a f7129e;

    /* renamed from: f  reason: collision with root package name */
    private String f7130f;

    /* renamed from: g  reason: collision with root package name */
    private String f7131g;

    /* renamed from: h  reason: collision with root package name */
    private String f7132h;

    /* renamed from: i  reason: collision with root package name */
    private a f7133i;

    /* renamed from: j  reason: collision with root package name */
    private com.jd.verify.common.a f7134j;

    /* renamed from: k  reason: collision with root package name */
    private com.jd.verify.model.a f7135k;

    /* renamed from: l  reason: collision with root package name */
    private String f7136l;

    /* renamed from: m  reason: collision with root package name */
    private JSInterface f7137m;

    /* renamed from: n  reason: collision with root package name */
    private String f7138n;
    private com.jd.verify.j.c o;
    private String p;
    private com.jd.verify.j.a q;

    /* loaded from: classes18.dex */
    public interface a {
        void a(int i2);

        void a(int i2, String str);
    }

    public e(Context context) {
        super(context, 16973840);
        this.b = null;
        this.f7128c = null;
        this.d = null;
        this.f7129e = null;
        this.d = context;
        h();
    }

    private void h() {
        if (this.b == null) {
            this.b = new com.jd.verify.common.b(this.d, this, this.f7129e);
        }
        setOnKeyListener(this);
    }

    public void a(com.jd.verify.j.a aVar) {
        this.q = aVar;
    }

    public e b(String str) {
        this.f7136l = str;
        return this;
    }

    public e c(String str) {
        this.f7131g = str;
        return this;
    }

    @Override // android.app.Dialog
    public void create() {
        com.jd.verify.j.d.a("WebDialog create");
        com.jd.verify.j.d.a("create:session_id=" + this.f7131g);
        com.jd.verify.j.d.a("create:account=" + this.f7132h);
        if (this.b != null) {
            com.jd.verify.j.d.a("create:session_id=" + this.f7131g);
            com.jd.verify.j.d.a("create:account=" + this.f7132h);
            this.b.loadUrl("javascript:create('" + this.f7131g + "' , '" + this.f7132h + "')");
        }
    }

    public com.jd.verify.j.a d() {
        return this.q;
    }

    public e e(String str) {
        this.f7130f = str;
        return this;
    }

    public void f(String str) {
        this.f7138n = str;
    }

    public com.jd.verify.common.b g() {
        return this.b;
    }

    public void i() {
        String c2 = e().c();
        com.jd.verify.j.d.a("WebDialog start load " + c2);
        if (this.b == null) {
            return;
        }
        f b = f.b();
        b.a(this.d);
        b.c();
        this.b.setNotifyListener(this.f7134j);
        JSInterface jSInterface = new JSInterface(this.d, this.f7128c, this, f(), this.f7130f, this.f7135k, this.f7133i, this.f7134j, this.f7129e, this.f7136l);
        this.f7137m = jSInterface;
        jSInterface.setUemps(this.f7138n);
        this.f7137m.setTinyType(this.p);
        this.f7137m.setAutoCloseVerify(this.q);
        this.b.addJavascriptInterface(this.f7137m, NoStockRecommendHead.DEVICE);
        this.b.loadUrl(c2);
        this.b.buildLayer();
        this.b.setLayerType(1, null);
    }

    public void j() {
        com.jd.verify.common.b bVar = this.b;
        if (bVar != null) {
            bVar.stopLoading();
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.jd.verify.j.d.a("WebDialog real onCreate");
        requestWindowFeature(1);
        Window window = getWindow();
        window.setAttributes(window.getAttributes());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        try {
            if (this.b == null) {
                this.b = new com.jd.verify.common.b(this.d, this, this.f7129e);
            }
            this.b.setLayoutParams(layoutParams);
            this.b.setBackgroundColor(0);
            setContentView(this.b);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        return i2 == 4;
    }

    private String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionId", this.f7131g);
            jSONObject.put("udid", this.f7130f);
            jSONObject.put(Constants.PARAM_PLATFORM, "android");
            jSONObject.put("version", "1.0");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.jd.verify.j.d.a(jSONObject.toString());
        return jSONObject.toString();
    }

    public e a(com.jd.verify.model.a aVar) {
        this.f7135k = aVar;
        return this;
    }

    public void b() {
        this.b.setNotifyListener(null);
        this.b = null;
        this.f7134j = null;
        this.f7137m.setmCallBack(null);
        this.f7137m.setNotifyListener(null);
        this.f7137m = null;
    }

    public void c() {
        com.jd.verify.j.d.a("WebDialog destoryDom");
        com.jd.verify.common.b bVar = this.b;
        if (bVar != null) {
            bVar.loadUrl("javascript:destory()");
        }
    }

    public void d(String str) {
        this.p = str;
    }

    public com.jd.verify.j.c e() {
        com.jd.verify.j.c cVar = this.o;
        return cVar != null ? cVar : com.jd.verify.j.c.a();
    }

    public e a(String str) {
        this.f7132h = str;
        return this;
    }

    public e a(a aVar) {
        this.f7133i = aVar;
        return this;
    }

    public e a(com.jd.verify.common.a aVar) {
        this.f7134j = aVar;
        return this;
    }

    public e a(CallBack callBack) {
        this.f7128c = callBack;
        return this;
    }

    public e a(com.jd.verify.View.a aVar) {
        if (aVar != null) {
            this.f7129e = aVar;
            com.jd.verify.common.b bVar = this.b;
            if (bVar != null) {
                bVar.setProgressDialog(aVar);
            }
        }
        return this;
    }

    public void a(com.jd.verify.j.c cVar) {
        this.o = cVar;
    }
}
