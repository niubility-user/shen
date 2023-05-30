package com.jingdong.common.content.builder;

import androidx.annotation.NonNull;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.frame.IMyActivity;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class AddCartEntityBuilder {
    private CartAddUniformEntity entity = new CartAddUniformEntity();

    /* loaded from: classes5.dex */
    public interface AddCartParam {
        @NonNull
        String getSkuId();

        String getStoreId();
    }

    private AddCartEntityBuilder() {
    }

    public static AddCartEntityBuilder create(@NonNull IMyActivity iMyActivity, @NonNull String str) {
        AddCartEntityBuilder addCartEntityBuilder = new AddCartEntityBuilder();
        CartAddUniformEntity cartAddUniformEntity = addCartEntityBuilder.entity;
        cartAddUniformEntity.myActivity = iMyActivity;
        cartAddUniformEntity.businessName = str;
        return addCartEntityBuilder;
    }

    public AddCartEntityBuilder add(@NonNull CartPackSummary cartPackSummary) {
        CartAddUniformEntity cartAddUniformEntity = this.entity;
        if (cartAddUniformEntity.packList == null) {
            cartAddUniformEntity.packList = new ArrayList<>();
        }
        this.entity.packList.add(cartPackSummary);
        return this;
    }

    public AddCartEntityBuilder addSku(@NonNull String str) {
        return addSku(str, 1);
    }

    public CartAddUniformEntity build() {
        return this.entity;
    }

    public AddCartEntityBuilder setEffect(boolean z) {
        this.entity.isEffect = z;
        return this;
    }

    public AddCartEntityBuilder setListener(ShoppingBaseController.ShoppingListener shoppingListener) {
        this.entity.listener = shoppingListener;
        return this;
    }

    public AddCartEntityBuilder setNoResponse(boolean z) {
        this.entity.isNoResponse = z;
        return this;
    }

    public AddCartEntityBuilder setNotify(boolean z) {
        this.entity.isNotify = z;
        return this;
    }

    public AddCartEntityBuilder addSku(@NonNull String str, int i2) {
        return addSku(str, i2, null);
    }

    public AddCartEntityBuilder addSku(@NonNull String str, int i2, String str2) {
        return addSku(new CartSkuSummary(str, i2, str2));
    }

    public AddCartEntityBuilder addSku(@NonNull AddCartParam addCartParam) {
        return addSku(addCartParam.getSkuId(), 1, addCartParam.getStoreId());
    }

    public AddCartEntityBuilder addSku(@NonNull CartSkuSummary cartSkuSummary) {
        CartAddUniformEntity cartAddUniformEntity = this.entity;
        if (cartAddUniformEntity.skuList == null) {
            cartAddUniformEntity.skuList = new ArrayList<>();
        }
        this.entity.skuList.add(cartSkuSummary);
        return this;
    }
}
