package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;

@Des(des = JumpUtil.VAULE_DES_FLOW_ORDERDETAIL)
/* loaded from: classes19.dex */
public class JumpToFlowOrderDetail extends com.jingdong.app.mall.basic.deshandler.a {

    /* loaded from: classes19.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8032g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bundle f8033h;

        a(Context context, Bundle bundle) {
            this.f8032g = context;
            this.f8033h = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            DeepLinkCommonHelper.startActivityDirect(this.f8032g, DeepLinkCommonHelper.HOST_PHONECHARGEFLOWORDERDETAIL_ACTIVITY, this.f8033h);
            JumpToFlowOrderDetail.this.c(this.f8032g);
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        String string = bundle.getString("orderId");
        Bundle bundle2 = new Bundle();
        Long l2 = -1L;
        try {
            l2 = Long.valueOf(Long.parseLong(string));
        } catch (Exception unused) {
        }
        if (l2.longValue() != -1) {
            bundle2.putLong("orderId", l2.longValue());
            if (LoginUserBase.hasLogin()) {
                DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_PHONECHARGEFLOWORDERDETAIL_ACTIVITY, bundle2);
                c(context);
                return;
            }
            LoginUserHelper.getInstance().executeLoginRunnable((IMyActivity) context, new a(context, bundle2));
            return;
        }
        c(context);
    }
}
