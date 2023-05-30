package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.o.a;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.ILogin;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.widget.ToastUtils;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = JumpUtil.VALUE_DES_FILLORDER)
/* loaded from: classes19.dex */
public class JumpToFillorder extends com.jingdong.app.mall.basic.deshandler.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements ILogin {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(JumpToFillorder jumpToFillorder, Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardFillOrder".equals(str)) {
                DeepLinkFillOrderHelper.startFillOrder(this.a, this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements ILogin {
        final /* synthetic */ int a;
        final /* synthetic */ Context b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f8030c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f8031e;

        /* loaded from: classes19.dex */
        class a implements a.b {
            a() {
            }

            @Override // com.jingdong.app.mall.o.a.b
            public void onFail() {
                ToastUtils.shortToast(b.this.b, "\u7f51\u7edc\u9519\u8bef\u6216\u5546\u54c1\u4e0d\u652f\u6301\u793c\u54c1\u8d2d");
            }

            @Override // com.jingdong.app.mall.o.a.b
            public void onSuccess() {
                DeepLinkCommonHelper.startActivityDirect(b.this.b, DeepLinkCommonHelper.HOST_GIFT_SHOPPING_ACTIVITY, null);
            }
        }

        b(JumpToFillorder jumpToFillorder, int i2, Context context, String str, int i3, String str2) {
            this.a = i2;
            this.b = context;
            this.f8030c = str;
            this.d = i3;
            this.f8031e = str2;
        }

        @Override // com.jingdong.common.login.ILogin
        public void onSuccess(String str) {
            if ("forwardFillOrder".equals(str)) {
                int i2 = this.a;
                if (i2 == 0) {
                    DeepLinkFillOrderHelper.startFillOrder(this.b, this.f8030c, this.d, this.f8031e, FillOrder.NORMAL, 100);
                } else if (i2 == 1) {
                    DeepLinkFillOrderHelper.startFillOrder(this.b, this.f8030c, this.d, this.f8031e, FillOrder.JDWORLDWIDE, 100);
                } else if (i2 == 2) {
                    DeepLinkFillOrderHelper.startFillOrder(this.b, this.f8030c, this.d, this.f8031e, FillOrder.GIFTCARD, 100);
                } else if (i2 != 3) {
                    if (i2 != 4) {
                        ToastUtils.shortToast(this.b, "\u53c2\u6570\u9519\u8bef");
                    } else {
                        DeepLinkFillOrderHelper.startFillOrder(this.b, this.f8030c, this.d, this.f8031e, FillOrder.PRESALE, 100);
                    }
                } else if (TextUtils.isEmpty(this.f8031e)) {
                    com.jingdong.app.mall.o.a.a((BaseActivity) this.b, this.f8030c, this.d, new a());
                } else {
                    DeepLinkFillOrderHelper.startFillOrder(this.b, this.f8030c, this.d, this.f8031e, FillOrder.GIFTBUY, 100);
                }
            }
        }
    }

    private void s(Context context, Bundle bundle) {
        String str;
        JSONObject jSONObject;
        String str2 = "";
        int i2 = 1;
        int i3 = 999;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
            str = jSONObject.optString("skuId");
        } catch (JSONException e2) {
            e = e2;
            str = "";
        }
        try {
            i2 = jSONObject.optInt("skuNum");
            i3 = jSONObject.optInt("orderType");
            str2 = jSONObject.optString(NewFillOrderConstant.INTENT_EXTRA_CARTSTR);
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            int i4 = i3;
            DeepLinkLoginHelper.startLoginActivity(context, null, new b(this, i4, context, str, i2, str2), "forwardFillOrder");
        }
        int i42 = i3;
        DeepLinkLoginHelper.startLoginActivity(context, null, new b(this, i42, context, str, i2, str2), "forwardFillOrder");
    }

    private void t(Context context, Bundle bundle) {
        DeepLinkLoginHelper.startLoginActivity(context, null, new a(this, context, bundle), "forwardFillOrder");
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (bundle == null) {
            if (Log.D) {
                Log.d(this.a, "bundle -->> is null");
                return;
            }
            return;
        }
        if (Log.D) {
            Log.d(this.a, "bundle -->> " + bundle.toString());
        }
        if (bundle.containsKey("json")) {
            s(context, bundle);
        } else {
            t(context, bundle);
        }
        c(context);
    }
}
