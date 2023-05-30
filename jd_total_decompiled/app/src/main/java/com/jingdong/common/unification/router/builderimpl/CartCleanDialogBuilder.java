package com.jingdong.common.unification.router.builderimpl;

import com.jingdong.common.BaseActivity;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.cart.clean.CartCleanTransParam;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.unification.router.builder.RouterBuilder;
import com.jingdong.common.unification.router.builder.RouterEntry;

/* loaded from: classes6.dex */
public class CartCleanDialogBuilder extends RouterBuilder<CartCleanDialogBuilder, CartCleanDialogEntry> {

    /* loaded from: classes6.dex */
    public class CartCleanDialogEntry extends RouterEntry<CartCleanDialogEntry> {
        public CartCleanTransParam cartCleanTransParam;

        public CartCleanDialogEntry() {
        }
    }

    public CartCleanDialogBuilder() {
        super("JDCartCleanDialogModule", "show");
    }

    public CartCleanDialogBuilder ab(boolean z) {
        putBoolean(CartCleanConstants.CART_CLEAN_DIALOG_AB, z);
        return this;
    }

    public CartCleanDialogBuilder activity(BaseActivity baseActivity) {
        extraObject("activity", baseActivity);
        return this;
    }

    public CartCleanDialogBuilder listener(ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener) {
        extraObject(CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, pDShoppingCartListener);
        return this;
    }

    public CartCleanDialogBuilder popupType(int i2) {
        putInt(CartCleanConstants.CART_CLEAN_DIALOG_TYPE, i2);
        return this;
    }

    public CartCleanDialogBuilder setCartCleanTransParam(CartCleanTransParam cartCleanTransParam) {
        ((CartCleanDialogEntry) this.mRouterEntry).cartCleanTransParam = cartCleanTransParam;
        return this;
    }

    public CartCleanDialogBuilder source(String str) {
        putString("source", str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.unification.router.builder.RouterBuilder
    public CartCleanDialogEntry initRouterEntry() {
        return new CartCleanDialogEntry();
    }
}
