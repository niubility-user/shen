package com.jingdong.app.mall.bundle.smile.utils;

import android.content.Context;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;

/* loaded from: classes3.dex */
public class RouterUtil {
    public static final String SMILE_MODULE = "JDDongDongSmileModule";

    public static void deleteSmileys(Context context, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("deleteSmileys").putBooleanParam("visitor", z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void findSmileys(Context context, CallBackWithReturnListener callBackWithReturnListener, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("findSmileys").putBooleanParam("visitor", z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void findSmileysWithFlag(Context context, CallBackWithReturnListener callBackWithReturnListener, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("findSmileysWithFlag").putBooleanParam("visitor", z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void getEmojiUrl(Context context, CallBackWithReturnListener callBackWithReturnListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("getEmojiUrl");
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void getPin(Context context, CallBackWithReturnListener callBackWithReturnListener, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("getPin").putBooleanParam("visitor", z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void isLightMode(Context context, CallBackWithReturnListener callBackWithReturnListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("isLightMode");
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void putDownloadMark(Context context, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("putDownloadMark").putBooleanParam(IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void saveSmileys(Context context, String str, boolean z) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(SMILE_MODULE).setMethodName("saveSmileys").putStringParam("smileBean", str).putBooleanParam("visitor", z);
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }
}
