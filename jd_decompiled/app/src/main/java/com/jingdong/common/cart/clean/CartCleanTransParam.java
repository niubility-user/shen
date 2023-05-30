package com.jingdong.common.cart.clean;

import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;

/* loaded from: classes5.dex */
public class CartCleanTransParam {
    public static final int CART_CLEAN_POPUP_TYPE_AUTO = 2;
    public static final int CART_CLEAN_POPUP_TYPE_CLICK = 1;
    public static final String CART_CLEAN_SOURCE_FROM_CART = "gouwuche";
    public static final String CART_CLEAN_SOURCE_FROM_ORDER = "dingdan";
    public static final String CART_CLEAN_SOURCE_FROM_PRODUCTDETAIL = "shangxiang";
    public static final String CART_CLEAN_SOURCE_FROM_SOUSUO = "sousuo";
    public static final String CART_CLEAN_SOURCE_FROM_TUIJIANWEI = "tuijianwei";
    public BaseActivity cleanDialogActivity;
    public ShoppingBaseController.PDShoppingCartListener pdShoppingCartListener;
    public String source = CART_CLEAN_SOURCE_FROM_CART;
    public int popupType = 1;
    public boolean isCleanGuide = false;
}
