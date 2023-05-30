package com.un.lib.popup;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.utils.JumpMessageActivityUtil;
import com.jingdong.common.widget.popupwindow.ListPopupWindowMoudle;
import com.un.lib.popup.entity.PopupCoutomBean;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class JDTopPopupWindowHelper {
    public static final String BASE_CART = "\u8d2d\u7269\u8f66";
    public static final String BASE_HOME = "\u9996\u9875";
    public static final String BASE_MESSAGE = "\u6d88\u606f";
    public static final String BASE_SEARCH = "\u641c\u7d22";
    public static final String CUSTOM_OPENAPP_TYPE = "openapp";
    public static final String CUSTOM_ROUTER_TYPE = "router";
    public static final String CUSTOM_URL_TYPE = "url";
    private static final String MainFrameActivity = "com.jingdong.app.mall.MainFrameActivity";
    public static final String TAG = "JDTopPopupWindowHelper";

    public static List<ListPopupWindowMoudle> getBaseContent(Context context, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            ListPopupWindowMoudle listPopupWindowMoudle = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_message_light), "\u6d88\u606f", false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle2 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_home_light), BASE_HOME, false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle3 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_search_light), "\u641c\u7d22", false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle4 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_cart_light), BASE_CART, false, 0);
            arrayList.add(listPopupWindowMoudle);
            arrayList.add(listPopupWindowMoudle2);
            arrayList.add(listPopupWindowMoudle3);
            if (!z2) {
                arrayList.add(listPopupWindowMoudle4);
            }
        } else {
            ListPopupWindowMoudle listPopupWindowMoudle5 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_message), "\u6d88\u606f", false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle6 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_home), BASE_HOME, false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle7 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_search), "\u641c\u7d22", false, 0);
            ListPopupWindowMoudle listPopupWindowMoudle8 = new ListPopupWindowMoudle(context.getResources().getDrawable(R.drawable.jd_top_popup_cart), BASE_CART, false, 0);
            arrayList.add(listPopupWindowMoudle5);
            arrayList.add(listPopupWindowMoudle6);
            arrayList.add(listPopupWindowMoudle7);
            if (!z2) {
                arrayList.add(listPopupWindowMoudle8);
            }
        }
        return arrayList;
    }

    public static PopupCoutomBean getCustomContent(Context context) {
        try {
            String config = JDMobileConfig.getInstance().getConfig("unification", "popup", "bottom");
            if (TextUtils.isEmpty(config)) {
                return null;
            }
            return (PopupCoutomBean) JDJSON.parseObject(config, PopupCoutomBean.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void handleCustomItemClick(Context context, ListPopupWindowMoudle listPopupWindowMoudle) {
        if (listPopupWindowMoudle == null) {
            return;
        }
        String urlDecode = urlDecode(listPopupWindowMoudle.content);
        String str = "decode_content = " + urlDecode;
        String str2 = listPopupWindowMoudle.type;
        if (str2 != null) {
            str2.hashCode();
            char c2 = '\uffff';
            switch (str2.hashCode()) {
                case -1263192169:
                    if (str2.equals("openapp")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -925132983:
                    if (str2.equals(CUSTOM_ROUTER_TYPE)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 116079:
                    if (str2.equals("url")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    jumpToCustomOpenappType(context, urlDecode);
                    return;
                case 1:
                    jumpToCustomRouterType(context, urlDecode);
                    return;
                case 2:
                    jumpToCustomUrlType(context, urlDecode);
                    return;
                default:
                    return;
            }
        }
    }

    public static void jumpToCart(Activity activity) {
        DeepLinkCommonHelper.startShoppingCartActivity(activity, null, true);
    }

    public static void jumpToCustomOpenappType(Context context, String str) {
        Uri parse;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
            return;
        }
        OpenAppJumpController.dispatchJumpRequestInApp(context, parse);
    }

    public static void jumpToCustomRouterType(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDRouter.build(context, str).callBackListener(new CallBackListener() { // from class: com.un.lib.popup.JDTopPopupWindowHelper.1
            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onComplete() {
            }

            @Override // com.jingdong.common.unification.router.CallBackListener
            public void onError(int i2) {
            }
        }).open();
    }

    public static void jumpToCustomUrlType(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        DeepLinkMHelper.startWebActivity(context, str);
    }

    public static void jumpToHome(Activity activity) {
        DeepLinkCommonHelper.startActivity(activity, DeepLinkCommonHelper.HOST_JD_TASK_CLEAR_ACTIVITY, null, true, 67108864, false, "");
        if (activity != null) {
            if (Build.VERSION.SDK_INT > 29) {
                ActivityNumController.exitActivityWithoutTop();
                if (activity.getClass() == null || "com.jingdong.app.mall.MainFrameActivity".equals(activity.getClass().getName())) {
                    return;
                }
                activity.finish();
            } else if (activity.getClass() == null || "com.jingdong.app.mall.MainFrameActivity".equals(activity.getClass().getName())) {
            } else {
                activity.finish();
            }
        }
    }

    public static void jumpToMessage(Activity activity) {
        JumpMessageActivityUtil.productDetailjumpToMessageCenter(activity);
    }

    public static void jumpToSearch(Activity activity) {
        if (activity instanceof BaseActivity) {
            PDBaseDeepLinkHelper.startSearchActivity((BaseActivity) activity);
        }
    }

    public static String urlDecode(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
