package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.jingdong.sdk.platform.business.personal.R2;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class f implements HwOpenPayTask.IHwPayResultCallBack {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(b bVar) {
        this.a = bVar;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onError(String str, String str2) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        com.unionpay.utils.j.c("uppay", "queryHwPayStatus onError, errorCode:" + str + " errorMsg:" + str2);
        handler = this.a.f18152j;
        if (handler != null) {
            handler2 = this.a.f18152j;
            Message obtainMessage = handler2.obtainMessage(R2.color.jrtxt_main_title_color);
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            obtainMessage.obj = bundle;
            handler3 = this.a.f18152j;
            handler3.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwPayResultCallBack
    public final void onResult(Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        com.unionpay.utils.j.c("uppay", "queryHwPayStatus onResult");
        handler = this.a.f18152j;
        if (handler != null) {
            handler2 = this.a.f18152j;
            Message obtainMessage = handler2.obtainMessage(4001);
            obtainMessage.obj = bundle;
            handler3 = this.a.f18152j;
            handler3.sendMessage(obtainMessage);
        }
    }
}
