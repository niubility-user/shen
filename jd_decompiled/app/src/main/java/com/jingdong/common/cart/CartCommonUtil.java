package com.jingdong.common.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.cart.clean.CartCleanTransParam;
import com.jingdong.common.cart.clean.CartGuideCleanPopupWindow;
import com.jingdong.common.cart.clean.CartPlusExpandGuideDialog;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartConfigDetail;
import com.jingdong.common.entity.cart.CartConfigInfo;
import com.jingdong.common.entity.cart.CartDcUrlData;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartResponseShop;
import com.jingdong.common.entity.cart.CartResponseSku;
import com.jingdong.common.entity.cart.SubmitOrderProductInfo;
import com.jingdong.common.entity.cart.methodEntity.CartAddFullEntity;
import com.jingdong.common.entity.cart.methodEntity.CartForRefreshPdEntity;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.unification.router.JDRouterUtil;
import com.jingdong.common.unification.router.builderimpl.CartCleanDialogBuilder;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.PersonalInfoManager;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class CartCommonUtil {
    private static final String TAG = "CartCommonUtil";
    private static CartDcUrlData dcUrlData = null;
    public static HashMap<String, String> digitalAbCards = null;
    public static HashMap<String, String> digitalBuriedExpLabel = null;
    public static boolean isSearchCart = false;
    public static boolean noFirstEntryCartPage = false;
    public static String noticeStatus = "";
    public static HashMap<String, String> requestIdMap = new HashMap<>();
    public static boolean useUuid;

    public static void cartAddHandle(final CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null) {
            return;
        }
        int resultCode = cartResponse.getResultCode();
        if (resultCode == 0) {
            if (cartAddFullEntity.isHandleSuccess) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.3
                    @Override // java.lang.Runnable
                    public void run() {
                        CartCommonUtil.handleCartSuccessNew(cartAddFullEntity);
                    }
                });
            }
        } else if (resultCode == 1) {
            if (cartAddFullEntity.isHandleFull) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.4
                    @Override // java.lang.Runnable
                    public void run() {
                        CartCommonUtil.handleCartFullNew(cartAddFullEntity);
                    }
                });
            }
        } else if (cartAddFullEntity.isHandleElse) {
            handleCartElse(cartAddFullEntity);
        }
    }

    public static void cartFull(final CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null) {
            return;
        }
        int resultCode = cartResponse.getResultCode();
        if (resultCode == 0) {
            if (cartAddFullEntity.isHandleSuccess) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.5
                    @Override // java.lang.Runnable
                    public void run() {
                        CartCommonUtil.handleCartSuccess(cartAddFullEntity);
                    }
                });
            }
        } else if (resultCode == 1) {
            if (cartAddFullEntity.isHandleFull) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.6
                    @Override // java.lang.Runnable
                    public void run() {
                        CartCommonUtil.handleCartFull(cartAddFullEntity);
                    }
                });
            }
        } else if (cartAddFullEntity.isHandleElse) {
            handleCartElse(cartAddFullEntity);
        }
    }

    public static void closeDialogByH5(IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            EventBus.getDefault().post(new BaseEvent(CartBaseUtil.EVENT_TYPE_DLG_CLOSE, iRouterParams.getRouterParam()));
        }
    }

    public static boolean genUpdateTag() {
        return getDcUrlData().isFirstCart || (getDcUrlData().dcTagServerTime > 0 && getDcUrlData().isMD5Changed) || (getDcUrlData().dcTagServerTime > 0 && System.currentTimeMillis() >= getDcUrlData().dcTagClientTime);
    }

    public static String getBuriedExpLabel(String str) {
        HashMap<String, String> hashMap = digitalBuriedExpLabel;
        if (hashMap != null) {
            return hashMap.get(str);
        }
        return null;
    }

    public static long getCartBundleVersionCode() {
        return AuraBundleConfig.getInstance().getBundleVersionCode("com.jd.lib.cart");
    }

    public static String getCartParamMapInfo() {
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.cart.router.JDCartModuleNew");
            String str = (String) loadClass.getMethod("getCartParamMapInfo", new Class[0]).invoke(loadClass.getConstructor(new Class[0]).newInstance(new Object[0]), new Object[0]);
            if (OKLog.D) {
                OKLog.d(TAG, "[getCartParamMapInfo] json === " + str);
            }
            return str;
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    public static String getConfigFileName() {
        return CartBaseUtil.KEY_CART_CONFIG_SAVE_PATh;
    }

    public static CartDcUrlData getDcUrlData() {
        if (dcUrlData == null) {
            dcUrlData = new CartDcUrlData();
        }
        return dcUrlData;
    }

    public static SubmitOrderProductInfo getSubmitOrderProductInfo(CartResponse cartResponse) {
        try {
            Class<?> loadClass = JdSdk.getInstance().getApplication().getClassLoader().loadClass("com.jd.lib.cart.engine.entity.response.CartResponseEntity");
            Object invoke = loadClass.getMethod("getInfo", new Class[0]).invoke(loadClass.getConstructor(CartResponse.class).newInstance(cartResponse), new Object[0]);
            if (invoke instanceof SubmitOrderProductInfo) {
                return (SubmitOrderProductInfo) invoke;
            }
            return null;
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
            return null;
        }
    }

    public static String getTextInfo(String str) {
        CartConfigDetail cartConfigDetail;
        HashMap<String, String> hashMap;
        CartConfigInfo cartConfigInfo = CartConfigState.getInstance().getCartConfigInfo();
        return (cartConfigInfo == null || TextUtils.isEmpty(str) || (cartConfigDetail = cartConfigInfo.cartConfigDetail) == null || (hashMap = cartConfigDetail.textInfoMap) == null || hashMap.get(str) == null) ? "" : hashMap.get(str);
    }

    public static String getTextInfoWithDefaultValue(String str, String str2) {
        if (TextUtils.isEmpty(getTextInfo(str))) {
            return TextUtils.isEmpty(str2) ? "" : str2;
        }
        return getTextInfo(str);
    }

    public static String getUserType() {
        return (LoginUserBase.hasLogin() && PersonalInfoManager.getInstance().isAvailable()) ? PersonalInfoManager.getInstance().isUserPlusStatus() ? "1" : "2" : "0";
    }

    private static void handleCartElse(CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null || cartAddFullEntity.activity == null) {
            return;
        }
        String resultMsg = cartResponse.getResultMsg();
        if (TextUtils.isEmpty(resultMsg)) {
            return;
        }
        ToastUtils.showToastInCenter((Context) cartAddFullEntity.activity, (byte) 1, resultMsg, 0);
    }

    public static void handleCartFull(CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null || cartAddFullEntity.activity == null) {
            return;
        }
        String resultMsg = cartResponse.getResultMsg();
        BaseActivity baseActivity = cartAddFullEntity.activity;
        String str = cartAddFullEntity.source;
        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener = cartAddFullEntity.pdShoppingCartListener;
        if (!LoginUserBase.hasLogin()) {
            if (!TextUtils.isEmpty(resultMsg)) {
                ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, resultMsg, 0);
            }
            if (pDShoppingCartListener != null) {
                CartForRefreshPdEntity cartForRefreshPdEntity = new CartForRefreshPdEntity();
                cartForRefreshPdEntity.resultCode = 1;
                pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                return;
            }
            return;
        }
        CartCleanTransParam cartCleanTransParam = new CartCleanTransParam();
        cartCleanTransParam.popupType = 1;
        cartCleanTransParam.source = str;
        cartCleanTransParam.pdShoppingCartListener = pDShoppingCartListener;
        cartCleanTransParam.cleanDialogActivity = cartAddFullEntity.activity;
        if (!JDRouterUtil.isRouterJump() || CartUniformState.isShowingAddCartDlg) {
            return;
        }
        CartUniformState.isShowingAddCartDlg = true;
        ((CartCleanDialogBuilder) JDRouter.to(CartCleanDialogBuilder.class)).setCartCleanTransParam(cartCleanTransParam).jump(cartAddFullEntity.activity);
    }

    public static void handleCartFullNew(CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null || cartAddFullEntity.activity == null) {
            return;
        }
        String resultMsg = cartResponse.getResultMsg();
        BaseActivity baseActivity = cartAddFullEntity.activity;
        String str = cartAddFullEntity.source;
        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener = cartAddFullEntity.pdShoppingCartListener;
        if (!LoginUserBase.hasLogin()) {
            if (!TextUtils.isEmpty(resultMsg)) {
                ToastUtils.showToastInCenter((Context) baseActivity, (byte) 1, resultMsg, 0);
            }
            if (pDShoppingCartListener != null) {
                CartForRefreshPdEntity cartForRefreshPdEntity = new CartForRefreshPdEntity();
                cartForRefreshPdEntity.resultCode = 1;
                pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                return;
            }
            return;
        }
        if (CartGuideCleanPopupWindow.getInstance(baseActivity).isShowing()) {
            CartGuideCleanPopupWindow.getInstance(baseActivity).dismissGuideToast();
        }
        CartCleanTransParam cartCleanTransParam = new CartCleanTransParam();
        cartCleanTransParam.popupType = 1;
        cartCleanTransParam.source = str;
        cartCleanTransParam.pdShoppingCartListener = pDShoppingCartListener;
        cartCleanTransParam.cleanDialogActivity = cartAddFullEntity.activity;
        if (!JDRouterUtil.isRouterJump() || CartUniformState.isShowingAddCartDlg) {
            return;
        }
        CartUniformState.isShowingAddCartDlg = true;
        ((CartCleanDialogBuilder) JDRouter.to(CartCleanDialogBuilder.class)).setCartCleanTransParam(cartCleanTransParam).jump(cartAddFullEntity.activity);
    }

    public static void handleCartSuccess(CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        String str;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null || cartAddFullEntity.activity == null) {
            return;
        }
        String resultMsg = cartResponse.getResultMsg();
        final BaseActivity baseActivity = cartAddFullEntity.activity;
        final String str2 = cartAddFullEntity.source;
        boolean z = cartAddFullEntity.isShowSuccessToast;
        final ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener = cartAddFullEntity.pdShoppingCartListener;
        final CartForRefreshPdEntity cartForRefreshPdEntity = new CartForRefreshPdEntity();
        cartForRefreshPdEntity.resultCode = 0;
        cartForRefreshPdEntity.djBadInfo = cartResponse.djBadInfo;
        if (cartResponse.getInfo() != null) {
            cartForRefreshPdEntity.ybPackId = cartResponse.getInfo().ybPackId;
        }
        cartForRefreshPdEntity.isSuccess = true;
        if (!LoginUserBase.hasLogin()) {
            if (!TextUtils.isEmpty(resultMsg) && z) {
                ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("source", str2);
                    jSONObject.put("content", resultMsg);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
                sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject.toString(), null);
            }
            if (pDShoppingCartListener != null) {
                pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
            }
        } else if (cartResponse.getInfo() == null) {
        } else {
            int i2 = cartResponse.getInfo().cartAddClearGuide;
            String str3 = cartResponse.getInfo().cartClearShowImg;
            String textInfoWithDefaultValue = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTFULLCANCEL, baseActivity.getString(R.string.lib_cart_cartfull_cancel));
            String textInfoWithDefaultValue2 = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTFULLCLEAR, baseActivity.getString(R.string.lib_cart_cartfull_clear));
            String textInfoWithDefaultValue3 = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTADDCLEARALTITLE, baseActivity.getString(R.string.lib_cart_cartaddclear_title));
            String str4 = "";
            if (i2 == 1) {
                str = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTADDCLEARALMOST, baseActivity.getString(R.string.lib_cart_cartaddclear_almost));
                str4 = "1";
            } else if (i2 == 2) {
                str = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTADDCLEARFULL, baseActivity.getString(R.string.lib_cart_cartaddclear_full));
                str4 = "2";
            } else {
                str = "";
            }
            if (i2 == 0) {
                if (!TextUtils.isEmpty(resultMsg) && z) {
                    ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("source", str2);
                        jSONObject2.put("content", resultMsg);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        ExceptionReporter.reportExceptionToBugly(e3);
                    }
                    sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject2.toString(), null);
                }
                if (pDShoppingCartListener != null) {
                    pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                    return;
                }
                return;
            }
            final String str5 = str4;
            if (i2 != 1 && i2 != 2) {
                if (i2 != 3 || TextUtils.isEmpty(str3)) {
                    return;
                }
                if (SharedPreferencesUtil.getBoolean("isCartExpandShowed", false)) {
                    if (!TextUtils.isEmpty(resultMsg) && z) {
                        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            jSONObject3.put("source", str2);
                            jSONObject3.put("content", resultMsg);
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                            ExceptionReporter.reportExceptionToBugly(e4);
                        }
                        sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject3.toString(), null);
                    }
                    if (pDShoppingCartListener != null) {
                        pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                        return;
                    }
                    return;
                }
                CartPlusExpandGuideDialog cartPlusExpandGuideDialog = new CartPlusExpandGuideDialog(baseActivity, str3);
                if (baseActivity != null && !baseActivity.isFinishing() && !CartUniformState.isShowingAddCartDlg) {
                    CartUniformState.isShowingAddCartDlg = true;
                    cartPlusExpandGuideDialog.show();
                    SharedPreferencesUtil.putBoolean("isCartExpandShowed", true);
                    sendJdExposureMta(baseActivity, "Shopcart_PlusExpandExpo", str2, baseActivity, "", "");
                    if (pDShoppingCartListener != null) {
                        pDShoppingCartListener.showDlg();
                    }
                }
                cartPlusExpandGuideDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.cart.CartCommonUtil.10
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener2 = pDShoppingCartListener;
                        if (pDShoppingCartListener2 != null) {
                            pDShoppingCartListener2.dismissDlg();
                            pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                        }
                        CartUniformState.isShowingAddCartDlg = false;
                    }
                });
                return;
            }
            final JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(baseActivity, textInfoWithDefaultValue3, str, textInfoWithDefaultValue, textInfoWithDefaultValue2);
            createJdDialogWithStyle6.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.cart.CartCommonUtil.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseActivity baseActivity2 = baseActivity;
                    JDMtaUtils.sendCommonData(baseActivity2, "Shopcart_FullPopupIsee", str2, "", baseActivity2, str5, "", "");
                    createJdDialogWithStyle6.dismiss();
                }
            });
            createJdDialogWithStyle6.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.cart.CartCommonUtil.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseActivity baseActivity2 = baseActivity;
                    JDMtaUtils.sendCommonData(baseActivity2, "Shopcart_FullPopupClean", str2, "", baseActivity2, str5, "", "");
                    CartUniformState.showCartCleanDialog = true;
                    createJdDialogWithStyle6.dismiss();
                    CartCleanTransParam cartCleanTransParam = new CartCleanTransParam();
                    cartCleanTransParam.popupType = 1;
                    cartCleanTransParam.source = str2;
                    cartCleanTransParam.pdShoppingCartListener = pDShoppingCartListener;
                    cartCleanTransParam.cleanDialogActivity = baseActivity;
                    if (JDRouterUtil.isRouterJump()) {
                        ((CartCleanDialogBuilder) JDRouter.to(CartCleanDialogBuilder.class)).setCartCleanTransParam(cartCleanTransParam).jump(baseActivity);
                    }
                }
            });
            createJdDialogWithStyle6.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.cart.CartCommonUtil.9
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (!CartUniformState.showCartCleanDialog) {
                        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener2 = pDShoppingCartListener;
                        if (pDShoppingCartListener2 != null) {
                            pDShoppingCartListener2.dismissDlg();
                            pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                        }
                        CartUniformState.isShowingAddCartDlg = false;
                        return;
                    }
                    CartUniformState.showCartCleanDialog = false;
                }
            });
            if (baseActivity == null || baseActivity.isFinishing() || CartUniformState.isShowingAddCartDlg) {
                return;
            }
            CartUniformState.isShowingAddCartDlg = true;
            sendJdExposureMta(baseActivity, "Shopcart_FullPopupExpo", str2, baseActivity, str5, "");
            createJdDialogWithStyle6.show();
            if (pDShoppingCartListener != null) {
                pDShoppingCartListener.showDlg();
            }
        }
    }

    public static void handleCartSuccessNew(CartAddFullEntity cartAddFullEntity) {
        CartResponse cartResponse;
        final String str;
        if (cartAddFullEntity == null || (cartResponse = cartAddFullEntity.cartResponse) == null || cartAddFullEntity.activity == null) {
            return;
        }
        String resultMsg = cartResponse.getResultMsg();
        final BaseActivity baseActivity = cartAddFullEntity.activity;
        final String str2 = cartAddFullEntity.source;
        boolean z = cartAddFullEntity.isShowSuccessToast;
        final ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener = cartAddFullEntity.pdShoppingCartListener;
        final CartForRefreshPdEntity cartForRefreshPdEntity = new CartForRefreshPdEntity();
        cartForRefreshPdEntity.resultCode = 0;
        cartForRefreshPdEntity.djBadInfo = cartResponse.djBadInfo;
        if (cartResponse.getInfo() != null) {
            cartForRefreshPdEntity.ybPackId = cartResponse.getInfo().ybPackId;
        }
        cartForRefreshPdEntity.isSuccess = true;
        if (!LoginUserBase.hasLogin()) {
            if (!TextUtils.isEmpty(resultMsg) && z) {
                ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("source", str2);
                    jSONObject.put("content", resultMsg);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
                sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject.toString(), null);
            }
            if (pDShoppingCartListener != null) {
                pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
            }
        } else if (cartResponse.getInfo() == null) {
        } else {
            int i2 = cartResponse.getInfo().cartAddClearGuide;
            String str3 = cartResponse.getInfo().cartClearShowImg;
            String str4 = "";
            if (i2 == 1) {
                str4 = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTCLEAR180ALMOSTTIP, baseActivity.getString(R.string.lib_cart_cartclear_180_almost_tip));
                str = "1";
            } else if (i2 == 2) {
                str4 = getTextInfoWithDefaultValue(CartConstant.KEY_CART_TEXTINFO_CARTCLEAR180FULLTIP, baseActivity.getString(R.string.lib_cart_cartclear_180_full_tip));
                str = "2";
            } else {
                str = "";
            }
            if (i2 == 0) {
                if (!TextUtils.isEmpty(resultMsg) && z) {
                    ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("source", str2);
                        jSONObject2.put("content", resultMsg);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        ExceptionReporter.reportExceptionToBugly(e3);
                    }
                    sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject2.toString(), null);
                }
                if (pDShoppingCartListener != null) {
                    pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                }
            } else if (i2 != 1 && i2 != 2) {
                if (i2 != 3 || TextUtils.isEmpty(str3)) {
                    return;
                }
                if (SharedPreferencesUtil.getBoolean("isCartExpandShowed", false)) {
                    if (!TextUtils.isEmpty(resultMsg) && z) {
                        ToastUtils.showToastInCenter((Context) baseActivity, (byte) 2, resultMsg, 0);
                        JSONObject jSONObject3 = new JSONObject();
                        try {
                            jSONObject3.put("source", str2);
                            jSONObject3.put("content", resultMsg);
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                            ExceptionReporter.reportExceptionToBugly(e4);
                        }
                        sendExposureDataWithExt(baseActivity, "Shopcart_AddSuccessExpo", str2, "", baseActivity, "", jSONObject3.toString(), null);
                    }
                    if (pDShoppingCartListener != null) {
                        pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                        return;
                    }
                    return;
                }
                CartPlusExpandGuideDialog cartPlusExpandGuideDialog = new CartPlusExpandGuideDialog(baseActivity, str3);
                if (baseActivity != null && !baseActivity.isFinishing() && !CartUniformState.isShowingAddCartDlg) {
                    if (pDShoppingCartListener != null) {
                        pDShoppingCartListener.showDlg();
                    }
                    CartUniformState.isShowingAddCartDlg = true;
                    cartPlusExpandGuideDialog.show();
                    SharedPreferencesUtil.putBoolean("isCartExpandShowed", true);
                    sendJdExposureMta(baseActivity, "Shopcart_PlusExpandExpo", str2, baseActivity, "", "");
                }
                cartPlusExpandGuideDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.common.cart.CartCommonUtil.12
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        ShoppingBaseController.PDShoppingCartListener pDShoppingCartListener2 = pDShoppingCartListener;
                        if (pDShoppingCartListener2 != null) {
                            pDShoppingCartListener2.dismissDlg();
                            pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                        }
                        CartUniformState.isShowingAddCartDlg = false;
                    }
                });
            } else if (CartUniformState.isShowingAddCartDlg) {
            } else {
                CartGuideCleanPopupWindow cartGuideCleanPopupWindow = CartGuideCleanPopupWindow.getInstance(baseActivity);
                cartGuideCleanPopupWindow.setGuideCleanClick(new CartGuideCleanPopupWindow.CartGuideCleanClick() { // from class: com.jingdong.common.cart.CartCommonUtil.11
                    @Override // com.jingdong.common.cart.clean.CartGuideCleanPopupWindow.CartGuideCleanClick
                    public void onCloseClick() {
                        BaseActivity baseActivity2 = baseActivity;
                        JDMtaUtils.sendCommonData(baseActivity2, "Shopcart_FullPopupIsee", str2, "", baseActivity2, str, "", "");
                    }

                    @Override // com.jingdong.common.cart.clean.CartGuideCleanPopupWindow.CartGuideCleanClick
                    public void onContentClick() {
                        BaseActivity baseActivity2 = baseActivity;
                        JDMtaUtils.sendCommonData(baseActivity2, "Shopcart_FullPopupClean", str2, "", baseActivity2, str, "", "");
                        CartUniformState.isShowingAddCartDlg = true;
                        CartCleanTransParam cartCleanTransParam = new CartCleanTransParam();
                        cartCleanTransParam.popupType = 1;
                        cartCleanTransParam.source = str2;
                        cartCleanTransParam.pdShoppingCartListener = pDShoppingCartListener;
                        cartCleanTransParam.cleanDialogActivity = baseActivity;
                        if (JDRouterUtil.isRouterJump()) {
                            ((CartCleanDialogBuilder) JDRouter.to(CartCleanDialogBuilder.class)).setCartCleanTransParam(cartCleanTransParam).jump(baseActivity);
                        }
                    }
                });
                cartGuideCleanPopupWindow.showGuideToast(cartAddFullEntity, str4);
                sendJdExposureMta(baseActivity, "Shopcart_FullPopupExpo", str2, baseActivity, str, "");
                if (pDShoppingCartListener != null) {
                    pDShoppingCartListener.showGuideToast();
                    pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity);
                }
            }
        }
    }

    public static void initCartConfigData(JDJSONObject jDJSONObject, int i2, int i3) {
        if (jDJSONObject != null) {
            if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "initCartConfigData:");
            }
            CartConfigState.getInstance().setCartConfigInfo(CartConfigInfo.parseConfigInfo(jDJSONObject), i2, i3, jDJSONObject);
        }
    }

    public static boolean isCustomizedShop(CartResponseShop cartResponseShop) {
        return isLuxuryShop(cartResponseShop);
    }

    public static boolean isDigitalB(String str) {
        HashMap<String, String> hashMap = digitalAbCards;
        if (hashMap != null) {
            return TextUtils.equals(hashMap.get(str), "B");
        }
        return false;
    }

    public static boolean isEncryptBody() {
        return TextUtils.equals(JDMobileConfig.getInstance().getConfig("jdCart", "encryptBodySwitch", "encryptBodySwitchKey", "0"), "1");
    }

    public static boolean isHomeWishUser() {
        return PersonalInfoManager.getInstance().isFamilyNum();
    }

    public static boolean isLuxuryShop(CartResponseShop cartResponseShop) {
        return cartResponseShop != null && cartResponseShop.venderType == 117;
    }

    public static boolean isNeedUploadUserFlag() {
        return (LoginUserBase.hasLogin() && PersonalInfoManager.getInstance().isAvailable()) ? false : true;
    }

    public static boolean isScfSku(CartResponseSku cartResponseSku) {
        if (cartResponseSku == null) {
            return false;
        }
        return isSpecial(cartResponseSku.getSpecialId(), 60);
    }

    public static boolean isSpecial(long j2, int i2) {
        return ((j2 >> i2) & 1) == 1;
    }

    public static void loadCartConfigInfo(Context context) {
        if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, " loadCartConfigInfo");
        }
        if (!CartConfigState.getInstance().isLoad() && !CartConfigState.getInstance().isValid()) {
            CartConfigState.getInstance().setLoad(true);
            JDJSONObject jDJSONObject = null;
            int intFromPreference = CommonBase.getIntFromPreference(CartBaseUtil.KEY_CART_CONFIG, 0);
            if (intFromPreference > 0) {
                if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, "loadCartConfigInfo: version\uff1a" + intFromPreference);
                }
                jDJSONObject = CartBaseTool.readCartConfigInfo(context, getConfigFileName());
            } else if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "loadCartConfigInfo:\u672c\u5730\u65e0\u7f13\u5b58\u6570\u636e version \u4e3a\u7a7a");
            }
            if (jDJSONObject != null && !CartConfigState.getInstance().isValid()) {
                if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, "loadCartConfigInfo:\u52a0\u8f7d\u672c\u5730\u6570\u636e jsonObject\uff1a" + jDJSONObject);
                }
                initCartConfigData(jDJSONObject, intFromPreference, CommonBase.getIntFromPreference(CartConstant.KEY_CONFIG_DEGRADATION, 0));
            } else if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "loadCartConfigInfo:\u672c\u5730\u65e0\u7f13\u5b58\u6570\u636e jsonObject \u4e3a\u7a7a\uff0c\u6216\u5185\u5b58\u4e2d\u5df2\u6709\u63a5\u53e3\u8fd4\u56de\u7684\u6570\u636e");
            }
        } else if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, "loadCartConfigInfo\uff1a\u7ed3\u675f\uff0c\u5df2\u8bfb\u672c\u5730\u6216\u5df2\u6709\u63a5\u53e3\u8fd4\u56de\u6570\u636e");
        }
    }

    public static void loadConfig(final Context context) {
        if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, " loadConfig  ---> context : " + context);
        }
        if (CartConfigState.getInstance().isLoad()) {
            return;
        }
        ThreadManager.single().post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.1
            @Override // java.lang.Runnable
            public void run() {
                if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, " loadConfig  ---> run : ");
                }
                CartCommonUtil.loadCartConfigInfo(context);
            }
        });
    }

    public static void preLoadCartConfig(BaseActivity baseActivity) {
        if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, "----- APP \u542f\u52a8\u61d2\u52a0\u8f7d preLoadCartConfig : ");
        }
        loadConfig(baseActivity);
        CartGlobalEventHandler.getInstance().onCreate();
    }

    public static void saveCartConfigToFile(final JDJSONObject jDJSONObject, final int i2, final int i3) {
        if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, " saveCartConfigToFile start");
        }
        if (jDJSONObject == null) {
            return;
        }
        ThreadManager.single().post(new Runnable() { // from class: com.jingdong.common.cart.CartCommonUtil.2
            @Override // java.lang.Runnable
            public void run() {
                if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, " saveCartConfigToFile run");
                }
                boolean saveCartConfigInfo = CartBaseTool.saveCartConfigInfo(JdSdk.getInstance().getApplicationContext(), CartCommonUtil.getConfigFileName(), jDJSONObject.toJSONString());
                if (saveCartConfigInfo) {
                    CommonBase.putIntToPreference(CartBaseUtil.KEY_CART_CONFIG, i2);
                    CommonBase.putIntToPreference(CartConstant.KEY_CONFIG_DEGRADATION, i3);
                }
                if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, " isSaved:" + saveCartConfigInfo);
                }
            }
        });
    }

    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, Object obj, String str4, String str5, HashMap<String, String> hashMap) {
        String str6;
        if (obj == null) {
            str6 = "";
        } else if (obj instanceof String) {
            str6 = (String) obj;
        } else {
            str6 = obj.getClass().getName();
        }
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str6, str4, str5, "", "", "", hashMap);
    }

    public static void sendJdExposureMta(Context context, String str, String str2, Object obj, String str3, String str4) {
        JDMtaUtils.sendExposureData(context, obj, str4, str3, str, str2, "", "", "");
    }

    public static void setDialogTitle(IRouterParams iRouterParams) {
        if (iRouterParams != null) {
            EventBus.getDefault().post(new BaseEvent(CartBaseUtil.EVENT_TYPE_DLG_TITLE, iRouterParams.getRouterParam()));
        }
    }
}
