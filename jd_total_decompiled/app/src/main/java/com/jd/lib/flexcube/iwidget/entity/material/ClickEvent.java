package com.jd.lib.flexcube.iwidget.entity.material;

import android.content.Context;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import java.io.Serializable;

/* loaded from: classes14.dex */
public class ClickEvent implements Serializable {
    public static final String TYPE_ADDCART = "addCart";
    public static final String TYPE_JUMP = "jump";
    public static final String TYPE_NONE = "none";
    public CartEntity addCart;
    public String customParams;
    public BabelJumpEntity jump;
    public String type;

    public boolean handleAddCart(Context context, BabelScope babelScope) {
        CartEntity cartEntity;
        if (!"addCart".equals(this.type) || (cartEntity = this.addCart) == null) {
            return false;
        }
        CommonServiceUtil.addSingleProductToCartWithToast(context, cartEntity.skuId, null);
        CartEntity cartEntity2 = this.addCart;
        if (cartEntity2 == null || babelScope == null) {
            return true;
        }
        CommonServiceUtil.onClickMta(context, MtaData.Builder.from(cartEntity2.eventId, cartEntity2.srv).page(babelScope.getPageName(), babelScope.getPageParam()).build());
        return true;
    }

    public boolean handleJump(Context context, BabelScope babelScope) {
        BabelJumpEntity babelJumpEntity;
        if (!"jump".equals(this.type) || (babelJumpEntity = this.jump) == null) {
            return false;
        }
        CommonServiceUtil.execJump(context, babelJumpEntity);
        BabelJumpEntity babelJumpEntity2 = this.jump;
        if (babelJumpEntity2 == null || babelScope == null) {
            return true;
        }
        CommonServiceUtil.onClickMta(context, MtaData.Builder.from(babelJumpEntity2.eventId, babelJumpEntity2.srv).page(babelScope.getPageName(), babelScope.getPageParam()).jsonParam(this.jump.srvData).build());
        return true;
    }
}
