package com.jd.lib.babel.servicekit.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.jd.lib.babel.servicekit.iservice.BabelCartCallback;
import com.jd.lib.babel.servicekit.iservice.ICart;
import com.jd.lib.babel.servicekit.model.CartSummary;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.sdk.jdtoast.ToastUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes13.dex */
public class CartUtil implements ICart {
    private void addPackToCart(final Activity activity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, final BabelCartCallback babelCartCallback, final boolean z) {
        if (activity instanceof IMyActivity) {
            ShoppingBaseController.addMultiProductToCart((IMyActivity) activity, arrayList, arrayList2, new ShoppingBaseController.ShoppingSingleListener() { // from class: com.jd.lib.babel.servicekit.impl.CartUtil.2
                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onEnd(CartResponse cartResponse) {
                    super.onEnd(cartResponse);
                    if (cartResponse.getResultCode() == 0) {
                        BabelCartCallback babelCartCallback2 = babelCartCallback;
                        if (babelCartCallback2 != null) {
                            babelCartCallback2.onSuccess(null);
                        }
                        if (z) {
                            CartUtil.this.runSuccessToast(activity);
                        }
                    } else if (cartResponse.getResultCode() == 1) {
                        BabelCartCallback babelCartCallback3 = babelCartCallback;
                        if (babelCartCallback3 != null) {
                            babelCartCallback3.onFull();
                        }
                        if (z) {
                            CartUtil.this.runFullToast(activity);
                        }
                    } else {
                        BabelCartCallback babelCartCallback4 = babelCartCallback;
                        if (babelCartCallback4 != null) {
                            babelCartCallback4.onError();
                        }
                        if (z) {
                            CartUtil.this.runErrorToast(activity);
                        }
                    }
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onError(String str) {
                    BabelCartCallback babelCartCallback2 = babelCartCallback;
                    if (babelCartCallback2 != null) {
                        babelCartCallback2.onError();
                    }
                    if (z) {
                        CartUtil.this.runErrorToast(activity);
                    }
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onReady() {
                }
            }, true, false, false);
        } else if (babelCartCallback != null) {
            babelCartCallback.onError();
        }
    }

    private void addToCartList(final Activity activity, CartSkuSummary cartSkuSummary, final BabelCartCallback babelCartCallback, final boolean z) {
        if (activity instanceof IMyActivity) {
            ShoppingBaseController.addSingleProductToCart((IMyActivity) activity, cartSkuSummary, true, false, false, new ShoppingBaseController.ShoppingSingleListener() { // from class: com.jd.lib.babel.servicekit.impl.CartUtil.1
                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onEnd(CartResponse cartResponse) {
                    super.onEnd(cartResponse);
                    if (cartResponse.getResultCode() == 0) {
                        BabelCartCallback babelCartCallback2 = babelCartCallback;
                        if (babelCartCallback2 != null) {
                            babelCartCallback2.onSuccess(null);
                        }
                        if (z) {
                            CartUtil.this.runSuccessToast(activity);
                        }
                    } else if (cartResponse.getResultCode() == 1) {
                        BabelCartCallback babelCartCallback3 = babelCartCallback;
                        if (babelCartCallback3 != null) {
                            babelCartCallback3.onFull();
                        }
                        if (z) {
                            CartUtil.this.runFullToast(activity);
                        }
                    } else {
                        BabelCartCallback babelCartCallback4 = babelCartCallback;
                        if (babelCartCallback4 != null) {
                            babelCartCallback4.onError();
                        }
                        if (z) {
                            CartUtil.this.runErrorToast(activity);
                        }
                    }
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onError(String str) {
                    BabelCartCallback babelCartCallback2 = babelCartCallback;
                    if (babelCartCallback2 != null) {
                        babelCartCallback2.onError();
                    }
                    if (z) {
                        CartUtil.this.runErrorToast(activity);
                    }
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onReady() {
                }
            });
        } else if (babelCartCallback != null) {
            babelCartCallback.onError();
        }
    }

    private CartPackSummary cartSummaryToCartPackSummary(CartSummary cartSummary) {
        CartPackSummary cartPackSummary = new CartPackSummary(cartSummary.packId, 1);
        ArrayList<CartSummary> arrayList = cartSummary.childItems;
        if (arrayList != null) {
            Iterator<CartSummary> it = arrayList.iterator();
            while (it.hasNext()) {
                cartPackSummary.addSku(new CartSkuSummary(it.next().skuId, 1));
            }
        }
        return cartPackSummary;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runErrorToast(final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jd.lib.babel.servicekit.impl.CartUtil.4
            @Override // java.lang.Runnable
            public void run() {
                Context context2 = context;
                ToastUtils.showToastInCenter(context2, (byte) 3, context2.getString(R.string.add_shopping_cart_error), 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runFullToast(final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jd.lib.babel.servicekit.impl.CartUtil.5
            @Override // java.lang.Runnable
            public void run() {
                Context context2 = context;
                ToastUtils.showToast(context2, context2.getString(R.string.add_to_cart_full));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runSuccessToast(final Context context) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jd.lib.babel.servicekit.impl.CartUtil.3
            @Override // java.lang.Runnable
            public void run() {
                Context context2 = context;
                ToastUtils.showToastInCenter(context2, (byte) 2, context2.getString(R.string.product_has_add2car_message), 0);
            }
        });
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ICart
    public void addMultiProductToCart(Activity activity, ArrayList<CartSummary> arrayList, ArrayList<CartSummary> arrayList2, BabelCartCallback babelCartCallback) {
        addMultiProductToCart(activity, arrayList, arrayList2, babelCartCallback, false);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ICart
    public void addMultiProductToCartWithToast(Activity activity, ArrayList<CartSummary> arrayList, ArrayList<CartSummary> arrayList2, BabelCartCallback babelCartCallback) {
        addMultiProductToCart(activity, arrayList, arrayList2, babelCartCallback, true);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ICart
    public void addSingleProductToCart(Activity activity, CartSummary cartSummary, BabelCartCallback babelCartCallback) {
        addSingleProductToCart(activity, cartSummary, babelCartCallback, false);
    }

    @Override // com.jd.lib.babel.servicekit.iservice.ICart
    public void addSingleProductToCartWithToast(Activity activity, CartSummary cartSummary, BabelCartCallback babelCartCallback) {
        addSingleProductToCart(activity, cartSummary, babelCartCallback, true);
    }

    private void addSingleProductToCart(Activity activity, CartSummary cartSummary, BabelCartCallback babelCartCallback, boolean z) {
        if (!TextUtils.isEmpty(cartSummary.packId)) {
            CartPackSummary cartSummaryToCartPackSummary = cartSummaryToCartPackSummary(cartSummary);
            ArrayList<CartPackSummary> arrayList = new ArrayList<>();
            arrayList.add(cartSummaryToCartPackSummary);
            addPackToCart(activity, null, arrayList, babelCartCallback, z);
        } else if (TextUtils.isEmpty(cartSummary.skuId)) {
        } else {
            addToCartList(activity, new CartSkuSummary(cartSummary.skuId, 1), babelCartCallback, z);
        }
    }

    public void addMultiProductToCart(Activity activity, ArrayList<CartSummary> arrayList, ArrayList<CartSummary> arrayList2, BabelCartCallback babelCartCallback, boolean z) {
        ArrayList<CartSkuSummary> arrayList3;
        ArrayList<CartPackSummary> arrayList4 = null;
        if (arrayList != null) {
            ArrayList<CartSkuSummary> arrayList5 = new ArrayList<>();
            Iterator<CartSummary> it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList5.add(new CartSkuSummary(it.next().skuId, 1));
            }
            arrayList3 = arrayList5;
        } else {
            arrayList3 = null;
        }
        if (arrayList2 != null) {
            arrayList4 = new ArrayList<>();
            Iterator<CartSummary> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList4.add(cartSummaryToCartPackSummary(it2.next()));
            }
        }
        addPackToCart(activity, arrayList3, arrayList4, babelCartCallback, z);
    }
}
