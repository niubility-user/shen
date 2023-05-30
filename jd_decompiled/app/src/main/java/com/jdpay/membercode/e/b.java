package com.jdpay.membercode.e;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.membercode.JDPayMemberCode;
import com.jdpay.membercode.bean.CodeResultInfoBean;
import com.jdpay.membercode.network.ResponseBean;
import com.jdpay.net.ResultObserver;

/* loaded from: classes18.dex */
public class b extends com.jdpay.membercode.e.a {
    private com.jdpay.membercode.c d;

    /* renamed from: e  reason: collision with root package name */
    private String f7532e;

    /* loaded from: classes18.dex */
    class a implements ResultObserver<ResponseBean<CodeResultInfoBean>> {
        a() {
        }

        @Override // com.jdpay.net.ResultObserver
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(@Nullable ResponseBean<CodeResultInfoBean> responseBean) {
            if (responseBean == null || !responseBean.isSuccessful() || responseBean.data == null) {
                onFailure(null);
            } else {
                b.this.d.onPayResult(responseBean.data);
            }
        }

        @Override // com.jdpay.net.ResultObserver
        public void onFailure(@Nullable Throwable th) {
            JDPayLog.e(th);
        }
    }

    public b(com.jdpay.membercode.c cVar) {
        this.d = cVar;
    }

    @Override // com.jdpay.membercode.e.a
    public void c(boolean z) {
        if (TextUtils.isEmpty(this.f7532e) || "210000000000000000".equals(this.f7532e)) {
            return;
        }
        JDPayLog.i("Query:" + this.f7532e);
        JDPayMemberCode.getService().c(this.f7532e, new a());
    }

    public void f(String str) {
        this.f7532e = str;
    }
}
