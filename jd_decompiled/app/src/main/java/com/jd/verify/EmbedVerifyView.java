package com.jd.verify;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.tencent.connect.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class EmbedVerifyView extends FrameLayout {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f7094c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private com.jd.verify.model.a f7095e;

    /* renamed from: f  reason: collision with root package name */
    private com.jd.verify.View.b f7096f;

    public EmbedVerifyView(Context context) {
        this(context, null);
    }

    private String getWebParams() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sessionId", this.b);
            jSONObject.put("udid", this.a);
            jSONObject.put(Constants.PARAM_PLATFORM, "android");
            jSONObject.put("version", "1.0");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.jd.verify.j.d.a(jSONObject.toString());
        return jSONObject.toString();
    }

    public void create() {
        if (this.f7096f.c() != null) {
            com.jd.verify.j.d.a("create:" + this.b);
            this.f7096f.c().loadUrl("javascript:create('" + this.b + "' , '" + this.f7094c + "')");
        }
    }

    public WebView getWebView() {
        return this.f7096f.c();
    }

    public void reSize(int i2) {
        getLayoutParams().width = getWidth();
        getLayoutParams().height = (int) (i2 * this.f7096f.a().getResources().getDisplayMetrics().density);
        requestLayout();
    }

    public void setAccount(String str) {
        this.f7094c = str;
    }

    public void setAdditionParam(com.jd.verify.model.a aVar) {
        this.f7095e = aVar;
    }

    public void setIsLoadFinish(boolean z) {
        this.f7096f.b(z);
    }

    public void setLanguage(String str) {
        this.d = str;
    }

    public void setNotifyListener(com.jd.verify.common.a aVar) {
        this.f7096f.a(aVar);
    }

    public void setSession_id(String str) {
        this.b = str;
    }

    public void setUdid(String str) {
        this.a = str;
    }

    public void startLoad() {
        com.jd.verify.j.d.a("startLoad");
        WebView c2 = this.f7096f.c();
        if (c2 == null) {
            return;
        }
        c2.addJavascriptInterface(new EmbedJSInterface(c2.getContext(), null, this, getWebParams(), this.a, this.f7095e, this.f7096f.b(), this.d), NoStockRecommendHead.DEVICE);
        c2.loadUrl(com.jd.verify.j.c.a().c());
        c2.buildLayer();
        c2.setLayerType(1, null);
    }

    public void stopLoad() {
        if (this.f7096f.c() == null) {
            return;
        }
        this.f7096f.c().stopLoading();
    }

    public EmbedVerifyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmbedVerifyView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        com.jd.verify.View.b bVar = new com.jd.verify.View.b(context, (WebView) LayoutInflater.from(context).inflate(R.layout.embed_verify_view_layout, this).findViewById(R.id.web));
        this.f7096f = bVar;
        bVar.a(true);
        this.f7096f.e();
    }
}
