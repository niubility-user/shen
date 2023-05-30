package com.jingdong.common.recommend;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.cart.clean.CartCleanTransParam;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.methodEntity.CartAddFullEntity;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.frame.IMyActivity;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendAddCartUtils {
    private static final int FROM = 6;

    public static void addCart(final IMyActivity iMyActivity, String str, final int i2) {
        CartAddUniformEntity cartAddUniformEntity = new CartAddUniformEntity();
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        arrayList.add(new CartSkuSummary(str, 1));
        cartAddUniformEntity.businessName = "UR_list_" + i2;
        cartAddUniformEntity.myActivity = iMyActivity;
        cartAddUniformEntity.skuList = arrayList;
        cartAddUniformEntity.isNoResponse = true;
        cartAddUniformEntity.isNotify = false;
        cartAddUniformEntity.isEffect = true;
        cartAddUniformEntity.listener = new ShoppingBaseController.ShoppingSingleListener() { // from class: com.jingdong.common.recommend.RecommendAddCartUtils.1
            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                super.onEnd(cartResponse);
                if (i2 != 6) {
                    RecommendAddCartUtils.handleShoppingCartResult(cartResponse, iMyActivity.getThisActivity());
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable("cartResponse", cartResponse);
                BaseEvent baseEvent = new BaseEvent("cartAddResultServer");
                baseEvent.setBundle(bundle);
                EventBus.getDefault().post(baseEvent);
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str2) {
                super.onError(str2);
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onReady() {
                super.onReady();
            }
        };
        ShoppingBaseController.addCartUniform(cartAddUniformEntity);
    }

    public static void handleShoppingCartResult(CartResponse cartResponse, Activity activity) {
        CartAddFullEntity cartAddFullEntity = new CartAddFullEntity();
        cartAddFullEntity.activity = (BaseActivity) activity;
        cartAddFullEntity.cartResponse = cartResponse;
        cartAddFullEntity.isHandleSuccess = true;
        cartAddFullEntity.isHandleFull = true;
        cartAddFullEntity.isHandleElse = true;
        cartAddFullEntity.source = CartCleanTransParam.CART_CLEAN_SOURCE_FROM_TUIJIANWEI;
        CartCommonUtil.cartFull(cartAddFullEntity);
    }
}
