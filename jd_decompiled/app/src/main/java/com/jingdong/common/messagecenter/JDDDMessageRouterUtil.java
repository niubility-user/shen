package com.jingdong.common.messagecenter;

import android.content.Context;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.CallBackWithReturnListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUrlBuilder;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.utils.UserUtil;
import java.net.URLEncoder;

/* loaded from: classes5.dex */
public class JDDDMessageRouterUtil {
    public static final String MODULNAME = "JDDongDongModule";

    public static void backgroundSwitch(Context context) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("backgroundSwitch").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void cancelIcsPopWindow(Context context) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("cancelIcsPopWindow");
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void clearAllUnread(Context context, CallBackListener callBackListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("clearAllUnread").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackListener).open();
        }
    }

    public static void deleteAllRecent(Context context, CallBackListener callBackListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("deleteAllRecent").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackListener).open();
        }
    }

    public static void deleteRecentItem(Context context, String str, CallBackListener callBackListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("deleteRecentItem").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase()).putStringParam("recentContactEntityJsonStr", str);
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackListener).open();
        }
    }

    public static void foregroundSwitch(Context context) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("foregroundSwitch").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void getInitData(Context context) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("initData").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void getRecentList(Context context) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("getRecentList").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void getUnreadCount(Context context, CallBackWithReturnListener callBackWithReturnListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("getUnreadCount").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase());
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }

    public static void openChat(Context context, String str) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("openChat").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase()).putStringParam("recentContactEntityJsonStr", str);
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void openMixChat(Context context, String str) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            try {
                jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("openMixChat").putStringParam("pin", UserUtil.getWJLoginHelper().getPin().toLowerCase()).putStringParam(NotificationMessageSummary.TRANSMISSION, URLEncoder.encode(str, "utf-8"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            JDRouter.build(context, jDRouterUrlBuilder.build()).open();
        }
    }

    public static void topSession(Context context, String str, String str2, int i2, int i3, CallBackWithReturnListener callBackWithReturnListener) {
        if (JDRouterUtil.isRouterJump()) {
            JDRouterUrlBuilder jDRouterUrlBuilder = new JDRouterUrlBuilder();
            jDRouterUrlBuilder.setModuleName(MODULNAME).setMethodName("topSession").putStringParam("pin", str).putStringParam("sessionId", str2).putIntParam("type", i2).putIntParam("top", i3);
            JDRouter.build(context, jDRouterUrlBuilder.build()).callBackListener(callBackWithReturnListener).open();
        }
    }
}
