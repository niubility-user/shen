package com.jingdong.manto;

import android.webkit.JavascriptInterface;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public class d {
    final n a;

    public d(n nVar) {
        this.a = nVar;
    }

    @JavascriptInterface
    public float getPixelRatio() {
        return MantoDensityUtils.getDensity(this.a.f14071i);
    }

    @JavascriptInterface
    public float getWidth() {
        return MantoDensityUtils.getDMWidthPixels() / getPixelRatio();
    }
}
