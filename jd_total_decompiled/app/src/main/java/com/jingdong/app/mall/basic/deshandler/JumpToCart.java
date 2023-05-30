package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.e;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@Des(des = "cart")
/* loaded from: classes19.dex */
public class JumpToCart extends a {
    /* JADX WARN: Removed duplicated region for block: B:20:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void s(Bundle bundle) {
        String str;
        BaseActivity a;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        String str2 = "";
        try {
            str = bundle.getString("skuId");
            try {
                str2 = bundle.getString("packsId");
            } catch (Exception e2) {
                e = e2;
                if (Log.D) {
                    Log.d(this.a, "forwardShoppingCart skuId : " + str);
                    Log.d(this.a, "forwardShoppingCart packsId : ");
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(str)) {
                }
                int m2 = m(bundle, "skuNum");
                int m3 = m(bundle, "packsNum");
                String string = bundle.getString(OpenAppJumpController.KEY_PACKS_SKU);
                a = e.b().a();
                if (Log.D) {
                }
                if (a == null) {
                }
            }
        } catch (Exception e3) {
            e = e3;
            str = "";
        }
        if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            int m22 = m(bundle, "skuNum");
            int m32 = m(bundle, "packsNum");
            String string2 = bundle.getString(OpenAppJumpController.KEY_PACKS_SKU);
            a = e.b().a();
            if (Log.D) {
                Log.d(this.a, "forwardShoppingCart skuId : " + str);
                Log.d(this.a, "forwardShoppingCart packsId : " + str2);
                Log.d(this.a, "forwardShoppingCart skuNum : " + m22);
                Log.d(this.a, "forwardShoppingCart packsNum : " + m32);
                Log.d(this.a, "forwardShoppingCart activity : " + a);
                Log.d(this.a, "forwardShoppingCart landPageId : " + bundle.getString("landPageId"));
            }
            if (a == null) {
                ArrayList arrayList4 = null;
                if (TextUtils.isEmpty(str)) {
                    arrayList = null;
                } else {
                    ArrayList arrayList5 = new ArrayList();
                    arrayList5.add(new CartSkuSummary(str, m22));
                    arrayList = arrayList5;
                }
                if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(string2)) {
                    CartPackSummary cartPackSummary = new CartPackSummary(str2, Integer.valueOf(m32));
                    try {
                        JSONObject jSONObject = new JSONObject(string2);
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            cartPackSummary.addSku(new CartSkuSummary(next, jSONObject.optInt(next, 1)));
                        }
                        arrayList3 = new ArrayList();
                    } catch (JSONException e4) {
                        e = e4;
                    }
                    try {
                        arrayList3.add(cartPackSummary);
                        arrayList2 = arrayList3;
                    } catch (JSONException e5) {
                        e = e5;
                        arrayList4 = arrayList3;
                        e.printStackTrace();
                        arrayList2 = arrayList4;
                        ShoppingBaseController.addMultiProductToCart(a, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
                    }
                    ShoppingBaseController.addMultiProductToCart(a, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
                }
                arrayList2 = arrayList4;
                ShoppingBaseController.addMultiProductToCart(a, arrayList, arrayList2, new ShoppingBaseController.ShoppingMultiListener(), true, true, true);
            }
        }
    }

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        s(bundle);
        k(context, bundle);
    }
}
