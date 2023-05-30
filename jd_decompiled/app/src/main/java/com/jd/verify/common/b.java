package com.jd.verify.common;

import android.content.Context;
import android.webkit.WebView;
import com.jd.verify.View.e;

/* loaded from: classes18.dex */
public class b extends WebView {
    private com.jd.verify.View.b a;

    public b(Context context, e eVar, com.jd.verify.View.a aVar) {
        super(context);
        com.jd.verify.View.b bVar = new com.jd.verify.View.b(context, this);
        this.a = bVar;
        bVar.a(eVar);
        this.a.a(aVar);
        this.a.e();
    }

    public boolean a() {
        return this.a.d();
    }

    public void setIsLoadFinish(boolean z) {
        this.a.b(z);
    }

    public void setNotifyListener(a aVar) {
        this.a.a(aVar);
    }

    public void setProgressDialog(com.jd.verify.View.a aVar) {
        this.a.a(aVar);
    }
}
