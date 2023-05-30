package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = JumpUtil.VAULE_DES_CARTB)
/* loaded from: classes19.dex */
public class JumpToCartb extends com.jingdong.app.mall.basic.deshandler.a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements c {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        a(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.app.mall.basic.deshandler.JumpToCartb.c
        public void a() {
            JumpToCartb.this.u(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class b implements c {
        final /* synthetic */ Context a;
        final /* synthetic */ Bundle b;

        b(Context context, Bundle bundle) {
            this.a = context;
            this.b = bundle;
        }

        @Override // com.jingdong.app.mall.basic.deshandler.JumpToCartb.c
        public void a() {
            JumpToCartb.this.u(this.a, this.b);
        }
    }

    /* loaded from: classes19.dex */
    public interface c {
        void a();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean t(Context context, Bundle bundle) {
        Exception exc;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        BaseActivity a2;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        String str6 = "";
        if (Log.D) {
            Log.d(this.a, "forwardShoppingCartB bundle : " + bundle + " , toString : " + bundle.toString());
        }
        ArrayList arrayList4 = null;
        try {
            str4 = bundle.getString("skuId");
            try {
                str6 = bundle.getString("packsId");
                str2 = bundle.getString("skuList");
                try {
                    str3 = bundle.getString("wareList");
                    try {
                        str5 = bundle.getString("packList");
                    } catch (Exception e2) {
                        e = e2;
                        exc = e;
                        str = str6;
                        str6 = str4;
                        if (Log.D) {
                            Log.d(this.a, "forwardShoppingCartB skuId : " + str6);
                            Log.d(this.a, "forwardShoppingCartB packsId : " + str);
                            exc.printStackTrace();
                        }
                        str4 = str6;
                        str6 = str;
                        str5 = null;
                        if (Log.D) {
                        }
                        a2 = e.b().a();
                        if (!TextUtils.isEmpty(str4)) {
                        }
                        int m2 = m(bundle, "skuNum");
                        int m3 = m(bundle, "packsNum");
                        String string = bundle.getString(OpenAppJumpController.KEY_PACKS_SKU);
                        if (a2 != null) {
                        }
                        return true;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str3 = null;
                }
            } catch (Exception e4) {
                e = e4;
                str2 = null;
                str3 = null;
            }
        } catch (Exception e5) {
            exc = e5;
            str = "";
            str2 = null;
            str3 = null;
        }
        if (Log.D) {
            Log.d(this.a, "forwardShoppingCartB skuId : " + str4);
            Log.d(this.a, "forwardShoppingCartB packsId : " + str6);
            Log.d(this.a, "forwardShoppingCartB skuIdList : " + str2);
            Log.d(this.a, "forwardShoppingCartB skuNumList : " + str3);
            Log.d(this.a, "forwardShoppingCartB packList : " + str5);
        }
        a2 = e.b().a();
        if (!TextUtils.isEmpty(str4) && TextUtils.isEmpty(str6)) {
            if (!TextUtils.isEmpty(str2)) {
                a(a2, str2, new a(context, bundle));
                return false;
            } else if (!TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str5)) {
                b(a2, str3, str5, new b(context, bundle));
                return false;
            }
        } else {
            int m22 = m(bundle, "skuNum");
            int m32 = m(bundle, "packsNum");
            String string2 = bundle.getString(OpenAppJumpController.KEY_PACKS_SKU);
            if (a2 != null) {
                if (TextUtils.isEmpty(str4)) {
                    arrayList = null;
                } else {
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.add(new CartSkuSummary(str4, m22));
                    arrayList = arrayList5;
                }
                if (!TextUtils.isEmpty(str6) && !TextUtils.isEmpty(string2)) {
                    CartPackSummary cartPackSummary = new CartPackSummary(str6, Integer.valueOf(m32));
                    try {
                        JSONObject jSONObject = new JSONObject(string2);
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            cartPackSummary.addSku(new CartSkuSummary(next, jSONObject.optInt(next, 1)));
                        }
                        arrayList3 = new ArrayList();
                    } catch (JSONException e6) {
                        e = e6;
                    }
                    try {
                        arrayList3.add(cartPackSummary);
                        arrayList2 = arrayList3;
                    } catch (JSONException e7) {
                        e = e7;
                        arrayList4 = arrayList3;
                        e.printStackTrace();
                        arrayList2 = arrayList4;
                        ShoppingBaseController.addMultiProductToCart(a2, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
                        return true;
                    }
                    ShoppingBaseController.addMultiProductToCart(a2, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
                    return true;
                }
                arrayList2 = arrayList4;
                ShoppingBaseController.addMultiProductToCart(a2, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
                return true;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(Context context, Bundle bundle) {
        if (context instanceof BaseActivity) {
            s.n((BaseActivity) context, bundle);
        }
        c(context);
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (t(context, bundle)) {
            u(context, bundle);
        }
    }
}
