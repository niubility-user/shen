package com.jingdong.common.entity.cart.methodEntity;

import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.frame.IMyActivity;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartAddUniformEntity {
    public String businessName;
    public boolean isNoResponse;
    public ShoppingBaseController.ShoppingListener listener;
    public IMyActivity myActivity;
    public ArrayList<CartPackSummary> packList;
    public ArrayList<CartSkuSummary> skuList;
    public boolean isNotify = true;
    public boolean isEffect = true;
}
