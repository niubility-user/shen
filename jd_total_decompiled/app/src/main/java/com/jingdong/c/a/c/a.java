package com.jingdong.c.a.c;

import com.jingdong.sdk.oklog.OKLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/* loaded from: classes7.dex */
public class a implements IUiListener {
    public String a;
    private com.jingdong.c.a.a b;

    public a(com.jingdong.c.a.a aVar) {
        this.b = aVar;
    }

    @Override // com.tencent.tauth.IUiListener
    public void onCancel() {
        this.b.a(13, this.a, "");
    }

    @Override // com.tencent.tauth.IUiListener
    public void onComplete(Object obj) {
        this.b.a(11, this.a, obj.toString());
    }

    @Override // com.tencent.tauth.IUiListener
    public void onError(UiError uiError) {
        this.b.a(12, this.a, uiError.errorMessage);
    }

    @Override // com.tencent.tauth.IUiListener
    public void onWarning(int i2) {
        OKLog.d("BaseUiListener", "warning code: " + i2);
        this.b.a(12, this.a, "warning code: " + i2);
    }
}
