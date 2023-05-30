package com.jingdong.common.entity.cart.methodEntity;

import android.view.View;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.yanbao.CartResponseNewYBDetail;
import com.jingdong.common.frame.IMyActivity;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartAddForPDEntity {
    public static final String REFER_PD_DAOJIA = "25";
    public View anchorView;
    public int appleCare;
    public String businessName;
    public String contractPhoneSource;
    public ArrayList<String> giftPoolsId;
    public ArrayList<NewGiftItem> giftsSelect;
    public boolean isHandleSuccess;
    public boolean isHiddenErrorToast;
    public boolean isHiddenOkToast;
    public ArrayList<String> locServiceSelect;
    public IMyActivity myActivity;
    public ShoppingBaseController.PDShoppingCartListener pdShoppingCartlistener;
    public CartSkuSummary product;
    public String refer;
    public ArrayList<String> retailServiceSelect;
    public ArrayList<String> serviceSelect;
    public String skuTag;
    public String source;
    public ArrayList<String> threeCcSelect;
    public ArrayList<CartSkuSummary> tiedSkus;
    public ArrayList<CartResponseNewYBDetail> ybSelect;
}
