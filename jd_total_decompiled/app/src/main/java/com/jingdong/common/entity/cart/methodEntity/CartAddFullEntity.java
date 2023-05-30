package com.jingdong.common.entity.cart.methodEntity;

import android.view.View;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartResponse;

/* loaded from: classes5.dex */
public class CartAddFullEntity {
    public BaseActivity activity;
    public View anchorView;
    public CartResponse cartResponse;
    public boolean isHandleElse;
    public boolean isHandleSuccess;
    public ShoppingBaseController.PDShoppingCartListener pdShoppingCartListener;
    public String source;
    public boolean isHandleFull = true;
    public boolean isShowSuccessToast = true;
}
