package com.jingdong.common.cart;

import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.controller.SubShoppingBaseController;
import com.jingdong.common.entity.cart.CartRequest;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.sdk.oklog.OKLog;
import de.greenrobot.event.EventBus;

/* loaded from: classes5.dex */
public class CartGlobalEventHandler {
    private static CartGlobalEventHandler instance;

    private CartGlobalEventHandler() {
    }

    public static synchronized CartGlobalEventHandler getInstance() {
        CartGlobalEventHandler cartGlobalEventHandler;
        synchronized (CartGlobalEventHandler.class) {
            if (instance == null) {
                instance = new CartGlobalEventHandler();
            }
            cartGlobalEventHandler = instance;
        }
        return cartGlobalEventHandler;
    }

    public void onCreate() {
        if (EventBus.getDefault().isRegistered(getInstance())) {
            return;
        }
        EventBus.getDefault().register(getInstance());
    }

    public void onDestroy() {
        EventBus.getDefault().unregister(getInstance());
    }

    public void onEvent(BaseEvent baseEvent) {
        if (baseEvent != null) {
            String type = baseEvent.getType();
            type.hashCode();
            if (type.equals(LoginEvent.TYPE_LOGIN) && LoginUserBase.hasLogin()) {
                SubShoppingBaseController.updateDrugListCartNum();
                if (OKLog.D) {
                    OKLog.d("CartGlobalEventHandler", "LoginEvent ----->  \u767b\u9646\u6210\u529f");
                }
                CartRequest cartRequest = new CartRequest();
                cartRequest.setNoResponse(true);
                ShoppingBaseController.syncCart((IMyActivity) null, cartRequest, new ShoppingBaseController.ShoppingHttpListener(null));
            }
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
    }
}
