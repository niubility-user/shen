package com.jingdong.manto.m.u1;

import android.webkit.JavascriptInterface;
import androidx.annotation.RequiresApi;

/* loaded from: classes15.dex */
public class d {
    public String a;
    private com.jingdong.manto.m.u1.g.a b = new com.jingdong.manto.m.u1.g.a();

    public void a(String str) {
        this.a = str;
    }

    @JavascriptInterface
    public void bindImageTexture(int i2, String str, int i3) {
        f.a(this.a + i2).a(str, i3);
    }

    @JavascriptInterface
    public String invokeMethod(int i2, int i3, String str) {
        String.valueOf(i2);
        return this.b.a(this.a + i2, i3, str);
    }

    @JavascriptInterface
    public String preLoadImage(int i2, String str, int i3) {
        return f.a(this.a + i2).b(str, i3);
    }

    @JavascriptInterface
    public int setContextType(int i2, int i3) {
        String.valueOf(i2);
        f.a(this.a + i2).b(i3);
        return 0;
    }

    @JavascriptInterface
    @RequiresApi(api = 15)
    public void updateCanvasScale(int i2, int i3, int i4) {
        f.a(this.a + i2).a(i3, i4);
    }
}
