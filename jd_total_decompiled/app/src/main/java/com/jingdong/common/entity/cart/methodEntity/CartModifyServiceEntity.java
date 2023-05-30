package com.jingdong.common.entity.cart.methodEntity;

import android.view.ViewGroup;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.frame.IMyActivity;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CartModifyServiceEntity {
    public static int SOURCE_CART = 1;
    public static int SOURCE_JIESUAN = 0;
    public static int SOURCE_PD = 2;
    public CartPackSummary addGiftCardPack;
    public CartPackSummary addHsPack;
    public CartPackSummary addThreeCcPack;
    public CartPackSummary addYbPack;
    public CartPackSummary delGiftCardPack;
    public CartPackSummary delHsPack;
    public CartPackSummary delThreeCcPack;
    public CartPackSummary delYbPack;
    public ShoppingBaseController.ShoppingMultiListener listener;
    public ViewGroup loadingLayout;
    public IMyActivity myActivity;
    public String refer;
    public HashMap<String, Object> requestParam;
    public int source = 0;
    public CartSkuSummary switchDeliverySku;
}
