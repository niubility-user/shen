package com.jd.manto.center.e;

import com.jingdong.cleanmvp.engine.HttpGroupUtil;

/* loaded from: classes17.dex */
public abstract class a {
    private HttpGroupUtil a;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(HttpGroupUtil httpGroupUtil) {
        this.a = httpGroupUtil;
    }

    public void a() {
        HttpGroupUtil httpGroupUtil = this.a;
        if (httpGroupUtil != null) {
            httpGroupUtil.destroyHttpGroup();
        }
    }

    public HttpGroupUtil b() {
        return this.a;
    }
}
