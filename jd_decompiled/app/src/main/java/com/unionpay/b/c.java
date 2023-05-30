package com.unionpay.b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.jingdong.sdk.platform.business.personal.R2;
import com.unionpay.UPSEInfoResp;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class c implements Handler.Callback {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar) {
        this.a = bVar;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        String str;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        int i2;
        boolean z;
        int i3 = message.what;
        if (i3 != 1) {
            if (i3 != 4) {
                switch (i3) {
                    case 4000:
                        handler2 = this.a.f18152j;
                        handler2.removeMessages(4);
                        b.a(this.a, (Bundle) message.obj);
                        break;
                    case 4001:
                        handler3 = this.a.f18152j;
                        handler3.removeMessages(R2.color.key_step_logger_analyser_bg_color);
                        Object obj = message.obj;
                        if (obj instanceof Bundle) {
                            b.b(this.a, (Bundle) obj);
                            break;
                        }
                        break;
                    case R2.color.jrtxt_main_title_color /* 4002 */:
                        handler4 = this.a.f18152j;
                        handler4.removeMessages(R2.color.key_step_logger_analyser_bg_color);
                        Object obj2 = message.obj;
                        if (obj2 instanceof Bundle) {
                            b.c(this.a, (Bundle) obj2);
                            break;
                        }
                        break;
                    case R2.color.key_step_logger_analyser_bg_color /* 4003 */:
                        str = "queryHwPayStatus timeout";
                        break;
                    case R2.color.keyboard_color_action_text /* 4004 */:
                        handler5 = this.a.f18152j;
                        handler5.removeMessages(R2.color.keyboard_color_action_text_dark);
                        try {
                            i2 = ((Integer) message.obj).intValue();
                        } catch (Exception unused) {
                            i2 = 0;
                        }
                        z = this.a.f18149g;
                        if (!z) {
                            if (i2 == 1) {
                                b.e(this.a);
                                break;
                            }
                            this.a.c();
                            break;
                        }
                        break;
                    case R2.color.keyboard_color_action_text_dark /* 4005 */:
                        com.unionpay.utils.j.c("uppay", "QUERY_VENDOR_CAPACITY_TIMEOUT");
                        b.g(this.a);
                        this.a.c();
                        break;
                }
            } else {
                str = "timeout";
            }
            com.unionpay.utils.j.c("uppay", str);
            r7.a(r7.d, this.a.f18147e, UPSEInfoResp.ERROR_TIMEOUT, "timeout");
            b.c(this.a);
        } else {
            handler = this.a.f18152j;
            handler.removeMessages(4);
            com.unionpay.utils.j.c("uppay", "msg error");
            b.a(this.a, message.arg1, (String) message.obj);
        }
        return false;
    }
}
