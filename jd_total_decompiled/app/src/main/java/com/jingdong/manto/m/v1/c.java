package com.jingdong.manto.m.v1;

import android.webkit.JavascriptInterface;
import com.jingdong.manto.h;

/* loaded from: classes15.dex */
public class c {
    private h a;
    public int b;

    public c(h hVar) {
        this.a = hVar;
    }

    @JavascriptInterface
    public void postMsgToAppService(String str) {
        h hVar = this.a;
        if (hVar == null && hVar.g() == null) {
            return;
        }
        this.a.g().evaluateJavascript(String.format("JDWorker.workerMsgHandler(%d,%s)", Integer.valueOf(this.b), str), null);
    }
}
