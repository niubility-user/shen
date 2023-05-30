package com.jd.lib.push.d;

import android.text.TextUtils;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.mta.b;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes16.dex */
public class a {
    public static ICallBackResultService a = new C0165a();

    /* renamed from: com.jd.lib.push.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class C0165a implements ICallBackResultService {
        C0165a() {
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onError(int i2, String str) {
            g.b("OPPOPushCallback", "onError \u9519\u8bef\u7801\uff1a " + i2 + " \u9519\u8bef\u4fe1\u606f\uff1a " + str);
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onGetNotificationStatus(int i2, int i3) {
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onGetPushStatus(int i2, int i3) {
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onRegister(int i2, String str) {
            g.b("OPPOPushCallback", "\u6ce8\u518c code=" + i2 + " regId=" + str);
            if (i2 == 0) {
                if (!TextUtils.isEmpty(str)) {
                    b.b().l(R2.dimen.dp_632);
                    com.jd.lib.push.a.b(6, str);
                    return;
                }
                b.b().l(R2.dimen.dp_65);
                return;
            }
            b.b().h(6, i2);
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onSetPushTime(int i2, String str) {
        }

        @Override // com.heytap.msp.push.callback.ICallBackResultService
        public void onUnRegister(int i2) {
            g.b("OPPOPushCallback", "\u6ce8\u9500\u5e7f\u64ad\u7684\u7ed3\u679c :>>>>" + i2);
        }
    }
}
