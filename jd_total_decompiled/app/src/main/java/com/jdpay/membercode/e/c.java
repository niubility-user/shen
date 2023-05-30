package com.jdpay.membercode.e;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.bean.CodeInfoBean;
import com.jdpay.membercode.network.ResponseBean;
import com.jdpay.membercode.widget.d;
import com.jdpay.net.ResultObserver;

/* loaded from: classes18.dex */
public class c extends com.jdpay.membercode.e.a {
    private final d d;

    /* loaded from: classes18.dex */
    public class a implements ResultObserver<ResponseBean<CodeInfoBean>> {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        a(boolean z, boolean z2) {
            c.this = r1;
            this.a = z;
            this.b = z2;
        }

        private void a() {
            if (this.a || !this.b) {
                return;
            }
            c cVar = c.this;
            int i2 = cVar.f7530c;
            cVar.b(i2, i2);
        }

        @Override // com.jdpay.net.ResultObserver
        /* renamed from: b */
        public void onSuccess(@Nullable ResponseBean<CodeInfoBean> responseBean) {
            CodeInfoBean codeInfoBean;
            if (responseBean == null || !responseBean.isSuccessful() || (codeInfoBean = responseBean.data) == null || TextUtils.isEmpty(codeInfoBean.code)) {
                onFailure(null);
                return;
            }
            c.this.d.n(responseBean.data.code);
            a();
        }

        @Override // com.jdpay.net.ResultObserver
        public void onFailure(@Nullable Throwable th) {
            JDPayLog.e(th);
            c.this.d.a(th);
            a();
        }
    }

    public c(@NonNull d dVar) {
        this.d = dVar;
    }

    @Override // com.jdpay.membercode.e.a
    public void c(boolean z) {
        boolean d = d();
        if (!z && d) {
            a();
        }
        JDPayMemberCode.getService().f(new a(z, d));
    }
}
