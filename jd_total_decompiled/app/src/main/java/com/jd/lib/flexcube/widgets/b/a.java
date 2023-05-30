package com.jd.lib.flexcube.widgets.b;

import android.content.Context;
import android.view.View;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.flexcube.iwidget.entity.material.CartEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IClickEvent;
import com.jd.lib.flexcube.layout.entity.ShowMoreClickEvent;

/* loaded from: classes15.dex */
public class a {
    public static void a(Context context, View view, ClickEvent clickEvent, BabelScope babelScope) {
        CartEntity cartEntity;
        BabelJumpEntity babelJumpEntity;
        if (context == null || clickEvent == null) {
            return;
        }
        IClickEvent iClickEvent = babelScope != null ? (IClickEvent) babelScope.getService(IClickEvent.class) : null;
        if (iClickEvent != null) {
            iClickEvent.onClick(view, clickEvent);
        } else if ("jump".equals(clickEvent.type) && (babelJumpEntity = clickEvent.jump) != null) {
            CommonServiceUtil.execJump(context, babelJumpEntity);
            BabelJumpEntity babelJumpEntity2 = clickEvent.jump;
            if (babelJumpEntity2 == null || babelScope == null) {
                return;
            }
            CommonServiceUtil.onClickMta(context, MtaData.Builder.from(babelJumpEntity2.eventId, babelJumpEntity2.srv).page(babelScope.getPageName(), babelScope.getPageParam()).jsonParam(clickEvent.jump.srvData).build());
        } else if ("addCart".equals(clickEvent.type) && (cartEntity = clickEvent.addCart) != null) {
            CommonServiceUtil.addSingleProductToCartWithToast(context, cartEntity.skuId, null);
            CartEntity cartEntity2 = clickEvent.addCart;
            if (cartEntity2 == null || babelScope == null) {
                return;
            }
            CommonServiceUtil.onClickMta(context, MtaData.Builder.from(cartEntity2.eventId, cartEntity2.srv).page(babelScope.getPageName(), babelScope.getPageParam()).build());
        } else if ("flatviewShowMore".equals(clickEvent.type) && (clickEvent instanceof ShowMoreClickEvent) && babelScope != null) {
            ShowMoreClickEvent showMoreClickEvent = (ShowMoreClickEvent) clickEvent;
            CommonServiceUtil.onClickMta(context, MtaData.Builder.from(showMoreClickEvent.eventId, showMoreClickEvent.srv).page(babelScope.getPageName(), babelScope.getPageParam()).build());
        }
    }
}
