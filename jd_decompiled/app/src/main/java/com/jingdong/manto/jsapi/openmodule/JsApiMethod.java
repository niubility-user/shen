package com.jingdong.manto.jsapi.openmodule;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes15.dex */
public final class JsApiMethod {
    public int ctrlIndex;
    public final String methodName;
    public final int type;

    public JsApiMethod(String str, int i2) {
        this.methodName = str;
        this.type = i2;
    }

    public JsApiMethod(String str, int i2, int i3) {
        this.methodName = str;
        this.type = i3;
    }
}
