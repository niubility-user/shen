package com.jd.lib.babel.servicekit.iservice;

import android.app.Activity;
import com.jd.lib.babel.servicekit.model.CartSummary;
import java.util.ArrayList;

/* loaded from: classes13.dex */
public interface ICart {
    void addMultiProductToCart(Activity activity, ArrayList<CartSummary> arrayList, ArrayList<CartSummary> arrayList2, BabelCartCallback babelCartCallback);

    void addMultiProductToCartWithToast(Activity activity, ArrayList<CartSummary> arrayList, ArrayList<CartSummary> arrayList2, BabelCartCallback babelCartCallback);

    void addSingleProductToCart(Activity activity, CartSummary cartSummary, BabelCartCallback babelCartCallback);

    void addSingleProductToCartWithToast(Activity activity, CartSummary cartSummary, BabelCartCallback babelCartCallback);
}
