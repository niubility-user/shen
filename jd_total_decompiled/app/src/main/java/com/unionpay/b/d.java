package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.jingdong.sdk.platform.business.personal.R2;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class d implements HwOpenPayTask.IHwResultCallBack {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar) {
        this.a = bVar;
    }

    @Override // com.huawei.nfc.sdk.service.HwOpenPayTask.IHwResultCallBack
    public final void onResult(int i2, Bundle bundle) {
        Handler handler;
        Handler handler2;
        Handler handler3;
        com.unionpay.utils.j.c("uppay", "supportCapacity result:" + i2);
        handler = this.a.f18152j;
        if (handler != null) {
            handler2 = this.a.f18152j;
            Message obtainMessage = handler2.obtainMessage(R2.color.keyboard_color_action_text);
            obtainMessage.obj = Integer.valueOf(i2);
            handler3 = this.a.f18152j;
            handler3.sendMessage(obtainMessage);
        }
    }
}
