package com.jd.lib.babel.ifloor.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.lib.babel.servicekit.iservice.BabelCartCallback;
import com.jd.lib.babel.servicekit.iservice.ICart;
import com.jd.lib.babel.servicekit.iservice.IFrame;
import com.jd.lib.babel.servicekit.model.CartSummary;
import com.jd.lib.babel.servicekit.model.MtaData;

/* loaded from: classes13.dex */
public class OnCartClickListener implements View.OnClickListener {
    private BabelCartCallback babelCartCallback;
    private CartSummary cartSummary;
    private Context context;
    private MtaData mtaData;
    private String skuId;

    public OnCartClickListener(Context context, String str, MtaData mtaData) {
        this.context = context;
        this.skuId = str;
        this.mtaData = mtaData;
    }

    private void addToCart(CartSummary cartSummary) {
        Activity activity;
        ICart iCart;
        Context context = this.context;
        if (context instanceof Activity) {
            activity = (Activity) context;
        } else {
            IFrame iFrame = (IFrame) CommonServiceUtil.getService(IFrame.class);
            activity = iFrame != null ? iFrame.getActivity(this.context) : null;
        }
        if (activity == null || (iCart = (ICart) CommonServiceUtil.getService(ICart.class)) == null) {
            return;
        }
        iCart.addSingleProductToCartWithToast(activity, cartSummary, this.babelCartCallback);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (TextUtils.isEmpty(this.skuId) && this.cartSummary == null) {
            return;
        }
        if ((!TextUtils.isEmpty(this.skuId)) != false) {
            this.cartSummary = new CartSummary(this.skuId);
        }
        addToCart(this.cartSummary);
        try {
            CommonServiceUtil.onClickMta(this.context, this.mtaData);
        } catch (Throwable unused) {
        }
    }

    public void setBabelCartCallback(BabelCartCallback babelCartCallback) {
        this.babelCartCallback = babelCartCallback;
    }

    public OnCartClickListener(Context context, CartSummary cartSummary, MtaData mtaData) {
        this.context = context;
        this.cartSummary = cartSummary;
        this.mtaData = mtaData;
    }
}
