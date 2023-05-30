package com.jingdong.common.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.cleanmvp.engine.HttpGroupUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.R;
import com.jingdong.common.cart.CartBaseTool;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.cart.CartCommonUtil;
import com.jingdong.common.cart.CartConfigState;
import com.jingdong.common.cart.ICartLoginOut;
import com.jingdong.common.cart.clean.OnDiskCacheListener;
import com.jingdong.common.deeplinkhelper.DeepLinkLoginHelper;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.Pack;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.entity.cart.CartConfigInfo;
import com.jingdong.common.entity.cart.CartPackGiftSummary;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartRequest;
import com.jingdong.common.entity.cart.CartRequestCommon;
import com.jingdong.common.entity.cart.CartRequestOperate;
import com.jingdong.common.entity.cart.CartRequestOperateBestPromotion;
import com.jingdong.common.entity.cart.CartRequestOperateGift;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuGiftSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.CartSummary;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.methodEntity.CartAddForPDEntity;
import com.jingdong.common.entity.cart.methodEntity.CartAddFullEntity;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.entity.cart.methodEntity.CartForRefreshPdEntity;
import com.jingdong.common.entity.cart.methodEntity.CartModifyServiceEntity;
import com.jingdong.common.entity.cart.yanbao.CartResponseNewYBDetail;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.DialogController;
import com.jingdong.common.utils.ABTestUtils;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.AdvertUtils;
import com.jingdong.common.utils.CartHttpCacheUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.ICommon;
import com.jingdong.common.utils.JDGetWayQueueTools;
import com.jingdong.common.utils.JsonParser;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.threadpool.ThreadManager;
import com.jingdong.union.common.config.UnionConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ShoppingBaseController {
    public static final String CART_REQUEST_REFER_11 = "11";
    public static final String CART_REQUEST_REFER_12 = "12";
    public static final String CART_REQUEST_REFER_7 = "7";
    private static final String SHARED_PREFERENCES_SHOPPING_CART_COUNT = "shoppingCartCount";
    private static final String TAG = "ShoppingController";
    private static ICommon iCommon;
    private static ICartLoginOut loginOutListener;
    public static boolean noRefreshCart;

    @Deprecated
    /* loaded from: classes5.dex */
    public static class AddShoppingWithYBListener implements ShoppingListener {
        private IMyActivity myActivity;

        public AddShoppingWithYBListener(IMyActivity iMyActivity) {
            if (iMyActivity != null) {
                this.myActivity = iMyActivity;
                return;
            }
            throw new RuntimeException("myActivity is null");
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            final DialogController dialogController = new DialogController() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingWithYBListener.1
                {
                    AddShoppingWithYBListener.this = this;
                }

                @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -1) {
                        return;
                    }
                    dialogInterface.dismiss();
                }
            };
            dialogController.setMessage(str);
            dialogController.setPositiveButton("\u786e\u5b9a");
            dialogController.init(this.myActivity.getThisActivity());
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingWithYBListener.2
                {
                    AddShoppingWithYBListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    dialogController.show();
                }
            });
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }
    }

    /* loaded from: classes5.dex */
    public interface PDShoppingCartListener extends ShoppingListener {
        void dismissDlg();

        void dismissGuideToast();

        void refreshPDView(CartForRefreshPdEntity cartForRefreshPdEntity);

        void showDlg();

        void showGuideToast();
    }

    /* loaded from: classes5.dex */
    public interface ShoppingListener {
        void onEnd(CartResponse cartResponse);

        void onError(String str);

        void onReady();
    }

    /* loaded from: classes5.dex */
    public static class ShoppingMultiListener implements ShoppingListener {
        private ArrayList<CartPackSummary> packItems;
        private ArrayList<CartSummary> skuItems;

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            if (OKLog.D) {
                OKLog.d(ShoppingBaseController.TAG, " onEnd ---> getResultCode : " + cartResponse.getResultCode());
            }
            if (cartResponse == null || cartResponse.getResultCode() != 0) {
                return;
            }
            ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.ShoppingMultiListener.1
                {
                    ShoppingMultiListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (OKLog.D) {
                        OKLog.d(ShoppingBaseController.TAG, " ShoppingMultiListener  onEnd---> skuItems : " + ShoppingMultiListener.this.skuItems);
                        OKLog.d(ShoppingBaseController.TAG, " ShoppingMultiListener  onEnd---> packItems : " + ShoppingMultiListener.this.packItems);
                    }
                    if (ShoppingMultiListener.this.skuItems != null) {
                        Iterator it = ShoppingMultiListener.this.skuItems.iterator();
                        while (it.hasNext()) {
                            CartSummary cartSummary = (CartSummary) it.next();
                            if (cartSummary.itemType == 1) {
                                JDMtaUtils.onSaveProductOrderPage(((CartSkuSummary) cartSummary).getSkuId());
                            } else {
                                JDMtaUtils.onSavePackOrderPage(((CartPackSummary) cartSummary).getPackId());
                            }
                        }
                    }
                    if (ShoppingMultiListener.this.packItems != null) {
                        Iterator it2 = ShoppingMultiListener.this.packItems.iterator();
                        while (it2.hasNext()) {
                            JDMtaUtils.onSavePackOrderPage(((CartPackSummary) it2.next()).getPackId());
                        }
                    }
                }
            });
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }

        public void setPackItems(ArrayList<CartPackSummary> arrayList) {
            this.packItems = arrayList;
        }

        public void setSkuItems(ArrayList<CartSummary> arrayList) {
            this.skuItems = arrayList;
        }

        public void setSummaryItems(ArrayList<CartSkuSummary> arrayList) {
            ArrayList<CartSummary> arrayList2 = this.skuItems;
            if (arrayList2 == null) {
                this.skuItems = new ArrayList<>();
            } else {
                arrayList2.clear();
            }
            if (arrayList != null) {
                Iterator<CartSkuSummary> it = arrayList.iterator();
                while (it.hasNext()) {
                    this.skuItems.add(it.next());
                }
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class ShoppingSingleListener implements ShoppingListener {
        private CartSkuSummary skuItem;

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            if (OKLog.D) {
                OKLog.d(ShoppingBaseController.TAG, " onEnd ---> getResultCode : " + cartResponse.getResultCode());
            }
            if (cartResponse == null || cartResponse.getResultCode() != 0) {
                return;
            }
            ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.ShoppingSingleListener.1
                {
                    ShoppingSingleListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (ShoppingSingleListener.this.skuItem != null) {
                        Product product = new Product();
                        try {
                            product.setId(Long.valueOf(Long.parseLong(ShoppingSingleListener.this.skuItem.getSkuId())));
                            product.setNum(1);
                        } catch (Exception e2) {
                            if (OKLog.E) {
                                OKLog.e(ShoppingBaseController.TAG, e2);
                            }
                            product = null;
                        }
                        if (product != null) {
                            JDMtaUtils.onSaveProductOrderPage(product.getId() + "");
                        }
                    }
                }
            });
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }

        public void setSkuItem(CartSkuSummary cartSkuSummary) {
            this.skuItem = cartSkuSummary;
        }
    }

    public static void addCartForPD(CartAddForPDEntity cartAddForPDEntity) {
        CartRequestOperate cartRequestOperate;
        if (cartAddForPDEntity == null) {
            return;
        }
        final IMyActivity iMyActivity = cartAddForPDEntity.myActivity;
        final CartSkuSummary cartSkuSummary = cartAddForPDEntity.product;
        ArrayList<CartResponseNewYBDetail> arrayList = cartAddForPDEntity.ybSelect;
        ArrayList<String> arrayList2 = cartAddForPDEntity.serviceSelect;
        ArrayList<String> arrayList3 = cartAddForPDEntity.threeCcSelect;
        ArrayList<String> arrayList4 = cartAddForPDEntity.retailServiceSelect;
        ArrayList<String> arrayList5 = cartAddForPDEntity.locServiceSelect;
        ArrayList<CartSkuSummary> arrayList6 = cartAddForPDEntity.tiedSkus;
        final PDShoppingCartListener pDShoppingCartListener = cartAddForPDEntity.pdShoppingCartlistener;
        final boolean z = cartAddForPDEntity.isHiddenOkToast;
        final boolean z2 = cartAddForPDEntity.isHandleSuccess;
        final boolean z3 = cartAddForPDEntity.isHiddenErrorToast;
        final String str = cartAddForPDEntity.skuTag;
        String str2 = cartAddForPDEntity.refer;
        String str3 = cartAddForPDEntity.contractPhoneSource;
        final String str4 = cartAddForPDEntity.source;
        ArrayList<NewGiftItem> arrayList7 = cartAddForPDEntity.giftsSelect;
        final View view = cartAddForPDEntity.anchorView;
        String str5 = cartAddForPDEntity.businessName;
        if (iMyActivity == null || !(iMyActivity.getThisActivity() instanceof BaseActivity) || cartSkuSummary == null) {
            return;
        }
        AddShoppingWithYBListener addShoppingWithYBListener = new AddShoppingWithYBListener(iMyActivity) { // from class: com.jingdong.common.controller.ShoppingBaseController.2
            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingWithYBListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                super.onEnd(cartResponse);
                PDShoppingCartListener pDShoppingCartListener2 = pDShoppingCartListener;
                if (pDShoppingCartListener2 != null) {
                    pDShoppingCartListener2.onEnd(cartResponse);
                }
                int resultCode = cartResponse != null ? cartResponse.getResultCode() : 2;
                String resultMsg = ShoppingBaseController.getResultMsg(cartResponse);
                final CartForRefreshPdEntity cartForRefreshPdEntity = new CartForRefreshPdEntity();
                cartForRefreshPdEntity.resultCode = resultCode;
                cartForRefreshPdEntity.djBadInfo = cartResponse.djBadInfo;
                if (cartResponse.getInfo() != null) {
                    cartForRefreshPdEntity.ybPackId = cartResponse.getInfo().ybPackId;
                }
                CartAddFullEntity cartAddFullEntity = new CartAddFullEntity();
                cartAddFullEntity.activity = (BaseActivity) iMyActivity.getThisActivity();
                cartAddFullEntity.cartResponse = cartResponse;
                cartAddFullEntity.source = str4;
                cartAddFullEntity.isHandleSuccess = true;
                cartAddFullEntity.isHandleFull = true;
                boolean z4 = z;
                cartAddFullEntity.isShowSuccessToast = !z4;
                PDShoppingCartListener pDShoppingCartListener3 = pDShoppingCartListener;
                cartAddFullEntity.pdShoppingCartListener = pDShoppingCartListener3;
                cartAddFullEntity.anchorView = view;
                if (resultCode != 0) {
                    if (z3) {
                        if (pDShoppingCartListener3 != null) {
                            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.2.2
                                {
                                    AnonymousClass2.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    CartForRefreshPdEntity cartForRefreshPdEntity2 = cartForRefreshPdEntity;
                                    cartForRefreshPdEntity2.isSuccess = false;
                                    pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity2);
                                }
                            });
                        }
                    } else if (resultCode == 1) {
                        CartCommonUtil.cartAddHandle(cartAddFullEntity);
                    } else {
                        if (!TextUtils.isEmpty(resultMsg)) {
                            ShoppingBaseController.showToast(iMyActivity, resultCode, resultMsg);
                        }
                        if (pDShoppingCartListener != null) {
                            iMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.2.1
                                {
                                    AnonymousClass2.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    CartForRefreshPdEntity cartForRefreshPdEntity2 = cartForRefreshPdEntity;
                                    cartForRefreshPdEntity2.isSuccess = false;
                                    pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity2);
                                }
                            });
                        }
                    }
                } else if (z2) {
                    CartCommonUtil.cartAddHandle(cartAddFullEntity);
                } else {
                    if (!z4 && !TextUtils.isEmpty(resultMsg)) {
                        ShoppingBaseController.showToast(iMyActivity, resultCode, resultMsg);
                    }
                    if (pDShoppingCartListener != null) {
                        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.2.3
                            {
                                AnonymousClass2.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                CartForRefreshPdEntity cartForRefreshPdEntity2 = cartForRefreshPdEntity;
                                cartForRefreshPdEntity2.isSuccess = true;
                                pDShoppingCartListener.refreshPDView(cartForRefreshPdEntity2);
                            }
                        });
                    }
                }
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.2.4
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        JDMtaUtils.onSaveProductOrderPageWithSkuTag(cartSkuSummary.getSkuId(), str);
                    }
                });
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingWithYBListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str6) {
                super.onError(str6);
                PDShoppingCartListener pDShoppingCartListener2 = pDShoppingCartListener;
                if (pDShoppingCartListener2 != null) {
                    pDShoppingCartListener2.onError(str6);
                }
            }
        };
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        if (arrayList7 != null && arrayList7.size() > 0) {
            cartSkuSummary.setGiftPoolsItems(arrayList7);
        }
        arrayList9.add(cartSkuSummary);
        if (arrayList6 != null && arrayList6.size() > 0) {
            arrayList9.addAll(arrayList6);
            cartRequestOperate = new CartRequestOperate(arrayList9, (ArrayList<CartPackSummary>) null, "2");
            cartRequestOperate.setTideUp(1);
        } else {
            cartRequestOperate = new CartRequestOperate(arrayList9, (ArrayList<CartPackSummary>) null, "2");
        }
        arrayList8.add(cartRequestOperate);
        ArrayList arrayList10 = new ArrayList();
        if (arrayList != null && arrayList.size() > 0) {
            CartPackSummary cartPackSummary = new CartPackSummary(cartSkuSummary.getSkuId(), 1, "8");
            Iterator<CartResponseNewYBDetail> it = arrayList.iterator();
            while (it.hasNext()) {
                cartPackSummary.addSku(new CartSkuSummary(it.next().getPlatformId(), 1));
            }
            arrayList10.add(cartPackSummary);
        }
        if (arrayList2 != null && arrayList2.size() > 0) {
            CartPackSummary cartPackSummary2 = new CartPackSummary(cartSkuSummary.getSkuId(), 1, "8");
            Iterator<String> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                CartSkuSummary cartSkuSummary2 = new CartSkuSummary(it2.next(), 1);
                cartSkuSummary2.getExtFlag().service = 1;
                cartPackSummary2.addSku(cartSkuSummary2);
            }
            arrayList10.add(cartPackSummary2);
        }
        if (arrayList3 != null && arrayList3.size() > 0) {
            CartPackSummary cartPackSummary3 = new CartPackSummary(cartSkuSummary.getSkuId(), 1, "8");
            Iterator<String> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                CartSkuSummary cartSkuSummary3 = new CartSkuSummary(it3.next(), 1);
                cartSkuSummary3.getExtFlag().service = 2;
                cartPackSummary3.addSku(cartSkuSummary3);
            }
            arrayList10.add(cartPackSummary3);
        }
        if (arrayList4 != null && arrayList4.size() > 0) {
            CartPackSummary cartPackSummary4 = new CartPackSummary(cartSkuSummary.getSkuId(), 1, "8");
            for (int i2 = 0; i2 < arrayList4.size(); i2++) {
                if (!TextUtils.isEmpty(arrayList4.get(i2))) {
                    CartSkuSummary cartSkuSummary4 = new CartSkuSummary(arrayList4.get(i2), 1);
                    cartSkuSummary4.getExtFlag().service = 3;
                    if (arrayList5 != null && arrayList5.size() > i2 && !TextUtils.isEmpty(arrayList5.get(i2))) {
                        cartSkuSummary4.setLocId(arrayList5.get(i2));
                    }
                    cartPackSummary4.addSku(cartSkuSummary4);
                }
            }
            arrayList10.add(cartPackSummary4);
        }
        if (arrayList10.size() > 0) {
            arrayList8.add(new CartRequestOperate((ArrayList<CartSkuSummary>) null, arrayList10, "2"));
        }
        CartRequest cartRequest = new CartRequest(arrayList8);
        if (!TextUtils.isEmpty(str2)) {
            cartRequest.refer = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            cartRequest.contractPhoneSource = str3;
        }
        cartRequest.setNoResponse(true);
        cartRequest.storeId = cartSkuSummary.storeId;
        cartRequest.appleCare = cartAddForPDEntity.appleCare;
        cartRequest.businessName = str5;
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(addShoppingWithYBListener, false), true, true);
    }

    public static void addCartUniform(CartAddUniformEntity cartAddUniformEntity) {
        ArrayList<CartPackSummary> arrayList;
        if (cartAddUniformEntity != null) {
            ArrayList<CartSkuSummary> arrayList2 = cartAddUniformEntity.skuList;
            if ((arrayList2 == null || arrayList2.size() == 0) && ((arrayList = cartAddUniformEntity.packList) == null || arrayList.size() == 0)) {
                return;
            }
            CartRequest cartRequest = new CartRequest(new CartRequestOperate(cartAddUniformEntity.skuList, cartAddUniformEntity.packList, "2"));
            cartRequest.setNoResponse(cartAddUniformEntity.isNoResponse);
            cartRequest.businessName = cartAddUniformEntity.businessName;
            ShoppingListener shoppingListener = cartAddUniformEntity.listener;
            if (shoppingListener instanceof ShoppingMultiListener) {
                ((ShoppingMultiListener) shoppingListener).setSummaryItems(cartAddUniformEntity.skuList);
                ((ShoppingMultiListener) cartAddUniformEntity.listener).setPackItems(cartAddUniformEntity.packList);
            }
            syncCartAdd(cartAddUniformEntity.myActivity, cartRequest, new ShoppingHttpListener(cartAddUniformEntity.listener), cartAddUniformEntity.isNotify, cartAddUniformEntity.isEffect);
        }
    }

    public static void addCartUninform(CartAddUniformEntity cartAddUniformEntity, CartRequest cartRequest) {
        ArrayList<CartSkuSummary> arrayList;
        ArrayList<CartPackSummary> arrayList2;
        if (cartAddUniformEntity == null || !(((arrayList = cartAddUniformEntity.skuList) == null || arrayList.size() == 0) && ((arrayList2 = cartAddUniformEntity.packList) == null || arrayList2.size() == 0))) {
            CartRequestOperate cartRequestOperate = new CartRequestOperate(cartAddUniformEntity.skuList, cartAddUniformEntity.packList, "2");
            if (cartRequest != null) {
                cartRequest.getCartOperates().add(cartRequestOperate);
            } else {
                cartRequest = new CartRequest(cartRequestOperate);
            }
            cartRequest.setNoResponse(cartAddUniformEntity.isNoResponse);
            ShoppingListener shoppingListener = cartAddUniformEntity.listener;
            if (shoppingListener instanceof ShoppingMultiListener) {
                ((ShoppingMultiListener) shoppingListener).setSummaryItems(cartAddUniformEntity.skuList);
                ((ShoppingMultiListener) cartAddUniformEntity.listener).setPackItems(cartAddUniformEntity.packList);
            }
            syncCartAdd(cartAddUniformEntity.myActivity, cartRequest, new ShoppingHttpListener(cartAddUniformEntity.listener), cartAddUniformEntity.isNotify, cartAddUniformEntity.isEffect);
        }
    }

    public static void addMultiProductToCart(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, boolean z3) {
        if ((arrayList == null || arrayList.size() == 0) && (arrayList2 == null || arrayList2.size() == 0)) {
            return;
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate(arrayList, arrayList2, "2"));
        cartRequest.setNoResponse(z);
        if (shoppingListener instanceof ShoppingMultiListener) {
            ShoppingMultiListener shoppingMultiListener = (ShoppingMultiListener) shoppingListener;
            shoppingMultiListener.setSummaryItems(arrayList);
            shoppingMultiListener.setPackItems(arrayList2);
        }
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), z2, z3);
    }

    public static void addMultiProductToCartNewMode(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, boolean z3, String str, String str2) {
        if ((arrayList == null || arrayList.size() == 0) && (arrayList2 == null || arrayList2.size() == 0)) {
            return;
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate(arrayList, arrayList2, "2"));
        cartRequest.setNoResponse(z);
        cartRequest.businessName = str2;
        cartRequest.isNewMode = str;
        if (shoppingListener instanceof ShoppingMultiListener) {
            ShoppingMultiListener shoppingMultiListener = (ShoppingMultiListener) shoppingListener;
            shoppingMultiListener.setSummaryItems(arrayList);
            shoppingMultiListener.setPackItems(arrayList2);
        }
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), z2, z3);
    }

    @Deprecated
    public static void addOnePack(IMyActivity iMyActivity, final Pack pack, boolean z, boolean z2, boolean z3) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOnePack -->> myActivity:" + iMyActivity + ", addPack:" + pack);
        }
        if (iMyActivity == null || pack == null || pack.getId() == null) {
            return;
        }
        AddShoppingListener addShoppingListener = new AddShoppingListener(iMyActivity, z2, z3) { // from class: com.jingdong.common.controller.ShoppingBaseController.4
            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                super.onEnd(cartResponse);
                if (cartResponse.getResultCode() == 0) {
                    ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.4.1
                        {
                            AnonymousClass4.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (OKLog.D) {
                                OKLog.d(ShoppingBaseController.TAG, "DB_PacksTable.insertPacksCart new add 1  -->> " + pack.getName());
                            }
                            JDMtaUtils.onSavePackOrderPage(pack.getId() + "");
                        }
                    });
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str) {
                super.onError(str);
            }
        };
        String valueOf = String.valueOf(pack.getId());
        if (z) {
            StringBuilder sb = new StringBuilder();
            for (Product product : pack.getProductList()) {
                if (product != null) {
                    sb.append(CartConstant.KEY_YB_INFO_LINK + product.getId());
                }
            }
            if (!TextUtils.isEmpty(sb)) {
                valueOf = valueOf + ((Object) sb);
            }
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, new CartPackSummary(valueOf, 1), "2"));
        cartRequest.setNoResponse(true);
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(addShoppingListener), true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:92:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x00ab  */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void addProductForProductList(final IMyActivity iMyActivity, String str, int i2, final Runnable runnable, final Runnable runnable2, SourceEntity sourceEntity, boolean z, boolean z2) {
        Product product;
        ArrayList arrayList;
        if (OKLog.D) {
            OKLog.d(TAG, " addOneProductOrPack -->> myActivity:" + iMyActivity + "productId:" + str + "productNum:" + i2);
        }
        if (iMyActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            String trim = str.trim();
            Product product2 = new Product();
            try {
                product2.setId(Long.valueOf(Long.parseLong(trim)));
                product2.setNum(Integer.valueOf(i2));
                if (sourceEntity != null) {
                    product2.setSourceEntity(sourceEntity);
                }
                product = product2;
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
            final Product product3 = product;
            AddShoppingListener addShoppingListener = new AddShoppingListener(iMyActivity) { // from class: com.jingdong.common.controller.ShoppingBaseController.5
                @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onEnd(CartResponse cartResponse) {
                    if (cartResponse.getResultCode() != 0) {
                        iMyActivity.post(runnable);
                        return;
                    }
                    if (cartResponse.getInfo() != null) {
                        ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.5.1
                            {
                                AnonymousClass5.this = this;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                if (product3 != null) {
                                    JDMtaUtils.onSaveProductOrderPage(product3.getId() + "");
                                }
                            }
                        });
                    }
                    iMyActivity.post(runnable2);
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onError(String str2) {
                    iMyActivity.post(runnable);
                }
            };
            if (product == null) {
                CartSkuSummary cartSkuSummary = new CartSkuSummary(product.getId() + "", product.getNum().intValue());
                arrayList = new ArrayList();
                arrayList.add(cartSkuSummary);
            } else {
                arrayList = null;
            }
            if (arrayList != null) {
                return;
            }
            CartRequest cartRequest = new CartRequest(new CartRequestOperate(arrayList, (ArrayList<CartPackSummary>) null, "2"));
            cartRequest.setNoResponse(true);
            syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(addShoppingListener), z, z2);
            return;
        }
        product = null;
        final Product product32 = product;
        AddShoppingListener addShoppingListener2 = new AddShoppingListener(iMyActivity) { // from class: com.jingdong.common.controller.ShoppingBaseController.5
            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                if (cartResponse.getResultCode() != 0) {
                    iMyActivity.post(runnable);
                    return;
                }
                if (cartResponse.getInfo() != null) {
                    ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.5.1
                        {
                            AnonymousClass5.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (product32 != null) {
                                JDMtaUtils.onSaveProductOrderPage(product32.getId() + "");
                            }
                        }
                    });
                }
                iMyActivity.post(runnable2);
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str2) {
                iMyActivity.post(runnable);
            }
        };
        if (product == null) {
        }
        if (arrayList != null) {
        }
    }

    public static void addSingleProductToCart(IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, boolean z, boolean z2, boolean z3, ShoppingSingleListener shoppingSingleListener) {
        if (cartSkuSummary == null || TextUtils.isEmpty(cartSkuSummary.getSkuId())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(cartSkuSummary);
        if (shoppingSingleListener != null) {
            shoppingSingleListener.setSkuItem(cartSkuSummary);
        }
        addMultiProductToCart(iMyActivity, arrayList, null, shoppingSingleListener, z, z2, z3);
    }

    @Deprecated
    public static void addToCart(final IMyActivity iMyActivity, final String str) {
        addToCartById(iMyActivity, str, new ShoppingListener() { // from class: com.jingdong.common.controller.ShoppingBaseController.6
            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
                iMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (ShoppingBaseController.iCommon != null) {
                            ShoppingBaseController.iCommon.goToShoppingCartPage(iMyActivity, false);
                        }
                        JDMtaUtils.onSaveProductOrderPage(str + "");
                    }
                });
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str2) {
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onReady() {
            }
        });
    }

    @Deprecated
    private static void addToCartById(IMyActivity iMyActivity, String str, ShoppingListener shoppingListener) {
        syncCartAdd(iMyActivity, new CartRequest(new CartRequestOperate(new CartSkuSummary(str, 1), (CartPackSummary) null, "2")), new ShoppingHttpListener(shoppingListener), true, true);
    }

    public static void addYbAndService(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartPackSummary> arrayList, ShoppingListener shoppingListener) {
        if (arrayList != null) {
            try {
                if (arrayList.size() > 0) {
                    if (shoppingListener instanceof ShoppingMultiListener) {
                        ArrayList<CartSummary> arrayList2 = new ArrayList<>();
                        Iterator<CartPackSummary> it = arrayList.iterator();
                        while (it.hasNext()) {
                            CartPackSummary next = it.next();
                            if (next != null && next.getChildItems() != null && next.getChildItems().size() > 0) {
                                arrayList2.addAll(next.getChildItems());
                            }
                        }
                        ((ShoppingMultiListener) shoppingListener).setSkuItems(arrayList2);
                    }
                    CartRequest cartRequest = new CartRequest(new CartRequestOperate((ArrayList<CartSkuSummary>) null, arrayList, "2"));
                    cartRequest.setModelGroupUtil(cartHttpCacheUtil);
                    syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), true, true);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, " -->> " + e2.getMessage(), e2);
                }
            }
        }
    }

    public static void batchAddGiftToPromotionPack(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOneGiftToPack -->> myActivity:" + iMyActivity + ", addGiftCartSkuSummary:" + arrayList.size() + ", beAddedToCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (cartPackSummary != null) {
            Iterator<CartSkuSummary> it = arrayList.iterator();
            while (it.hasNext()) {
                cartPackSummary.addGift(it.next());
            }
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, cartPackSummary, "2"));
        cartRequest.setLoadingViewRoot(viewGroup);
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void batchChangeGift(IMyActivity iMyActivity, ArrayList<CartSkuGiftSummary> arrayList, ArrayList<CartPackGiftSummary> arrayList2, ShoppingListener shoppingListener) {
        if ((arrayList == null || arrayList.size() == 0) && (arrayList2 == null || arrayList2.size() == 0)) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperateGift(arrayList, arrayList2, "3"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setNoResponse(true);
        syncCartGiftChange(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void batchChangePromotion(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuGiftSummary> arrayList, ArrayList<CartPackGiftSummary> arrayList2, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap) {
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperateGift(arrayList, arrayList2, "4"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartChangePromotion(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void batchDeleteGiftBox(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<? super CartSummary> arrayList, CartPackSummary cartPackSummary, ShoppingListener shoppingListener) {
        if (arrayList == null || cartPackSummary == null) {
            return;
        }
        cartPackSummary.setChildItems(arrayList);
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, cartPackSummary, "4"));
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), null);
    }

    public static void batchDeleteGiftToPack(IMyActivity iMyActivity, ArrayList<? super CartSkuSummary> arrayList, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOneGiftToPack -->> myActivity:" + iMyActivity + ", deleteGiftCartSkuSummary:" + arrayList.size() + ", beDeletedToCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (arrayList == null || cartPackSummary == null) {
            return;
        }
        Iterator<? super CartSkuSummary> it = arrayList.iterator();
        while (it.hasNext()) {
            cartPackSummary.addGift(it.next());
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, cartPackSummary, "4"));
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void batchDeleteMustGift(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuGiftSummary> arrayList, ArrayList<CartPackGiftSummary> arrayList2, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap) {
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperateGift(arrayList, arrayList2, "4"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartGiftRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void changeBestPromotion(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, JDJSONObject jDJSONObject, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap) {
        changeBestPromotion(-1, cartHttpCacheUtil, iMyActivity, jDJSONObject, shoppingListener, viewGroup, hashMap);
    }

    public static void clearLocalCart() {
        try {
            setProductCount(0);
            SubShoppingBaseController.clearSubCartNum();
            validateCartIcon();
            ICartLoginOut iCartLoginOut = loginOutListener;
            if (iCartLoginOut != null) {
                iCartLoginOut.appLoginOut();
                OnDiskCacheListener.cachedResponse = null;
                OnDiskCacheListener.deleteDiskCache(new File(OnDiskCacheListener.getDiskCacheDir(JdSdk.getInstance().getApplicationContext()), CartBaseUtil.CART_CACHE_FILE));
                SharedPreferencesUtil.putInt("cacheVersion", 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void deleteOneGiftFormPack(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOneGiftToPack -->> myActivity:" + iMyActivity + ", deleteGiftCartSkuSummary:" + cartSkuSummary + ", beDeletedToCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        deleteOneGiftFormPackExt(cartHttpCacheUtil, iMyActivity, cartSkuSummary, cartPackSummary, shoppingListener, viewGroup, null, hashMap, onQueueCancelListener);
    }

    public static void deleteOneGiftFormPackExt(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOneGiftToPack -->> myActivity:" + iMyActivity + ", deleteGiftCartSkuSummary:" + cartSkuSummary + ", beDeletedToCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (cartPackSummary != null) {
            cartPackSummary.addGift(cartSkuSummary);
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, cartPackSummary, "4"));
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extFlag = hashMap;
        }
        if (hashMap2 != null && !hashMap2.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap2);
        }
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void deleteOnePack(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " deleteOneProduct -->> myActivity:" + iMyActivity + ", deleteCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(cartPackSummary);
        deleteProductOrPackList(cartHttpCacheUtil, iMyActivity, null, arrayList, shoppingListener, viewGroup, hashMap, onQueueCancelListener);
    }

    public static void deleteOneProduct(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " deleteOneProduct -->> myActivity:" + iMyActivity + ", deleteCartSkuSummary:" + cartSkuSummary + ", listener:" + shoppingListener);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(cartSkuSummary);
        deleteProductOrPackList(cartHttpCacheUtil, iMyActivity, arrayList, null, shoppingListener, viewGroup, hashMap, onQueueCancelListener);
    }

    public static void deleteProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " deleteProductOrPackList -->> myActivity:" + iMyActivity + ", deleteSkus:" + arrayList + ", deletePacks:" + arrayList2 + ", listener:" + shoppingListener);
        }
        if (arrayList == null && arrayList2 == null) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperate(arrayList, arrayList2, "4"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void deleteProducts(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, HashMap<String, String> hashMap, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap2, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (arrayList == null && arrayList2 == null) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperate(arrayList, arrayList2, "4"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        cartRequest.extFlag = hashMap;
        if (hashMap2 != null && !hashMap2.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap2);
        }
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void deleteTradein(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ShoppingListener shoppingListener, ArrayList<CartPackSummary> arrayList, HashMap<String, Object> hashMap) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((ArrayList<CartSkuSummary>) null, arrayList, (String) null));
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), null);
    }

    public static void deleteYbAndService(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ShoppingListener shoppingListener, ArrayList<CartPackSummary> arrayList, HashMap<String, Object> hashMap) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((ArrayList<CartSkuSummary>) null, arrayList, "9"));
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = false;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), null);
    }

    public static void editProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, ViewGroup viewGroup, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        editProductOrPackList(cartHttpCacheUtil, iMyActivity, arrayList, arrayList2, shoppingListener, z, false, null, viewGroup, onQueueCancelListener);
    }

    public static int getCartNum(CartResponse cartResponse) {
        if (cartResponse == null || cartResponse.getInfo() == null) {
            return 0;
        }
        return cartResponse.getInfo().cartNum;
    }

    public static int getCartProductNum(CartResponse cartResponse) {
        if (cartResponse == null || cartResponse.getInfo() == null) {
            return 0;
        }
        return cartResponse.getInfo().num;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(65:1|(1:3)(1:203)|4|(3:197|198|199)(1:6)|(2:192|193)(1:8)|9|(3:183|184|(60:186|(2:189|187)|190|191|12|13|(1:15)|16|(1:18)|19|(1:21)|22|(1:24)|25|(1:27)|28|(1:30)|31|(1:33)|34|(1:36)|37|(1:39)|40|(1:42)|43|(1:45)|46|(1:48)|49|(1:51)|52|53|(1:55)(3:176|(1:178)(1:180)|179)|56|(1:58)(1:175)|59|(1:61)|62|(2:64|65)|66|(1:68)|69|(1:71)|72|(1:74)|75|(1:77)|78|(1:80)|(3:84|(2:85|(3:87|(3:92|93|94)|95)(1:98))|99)|(3:103|(2:104|(3:106|(3:111|112|113)|114)(1:117))|118)|143|(3:148|149|(2:151|(2:152|(3:154|(3:159|160|161)|162)(1:165)))(0))(0)|145|146|127|128|(2:130|(2:132|(1:134)))|136))|11|12|13|(0)|16|(0)|19|(0)|22|(0)|25|(0)|28|(0)|31|(0)|34|(0)|37|(0)|40|(0)|43|(0)|46|(0)|49|(0)|52|53|(0)(0)|56|(0)(0)|59|(0)|62|(0)|66|(0)|69|(0)|72|(0)|75|(0)|78|(0)|(4:82|84|(3:85|(0)(0)|95)|99)|(4:101|103|(3:104|(0)(0)|114)|118)|143|(0)(0)|145|146|127|128|(0)|136|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:577:0x034d, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:579:0x034f, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:580:0x0350, code lost:
        r1 = "syncCart -->> ";
        r2 = com.jingdong.common.controller.ShoppingBaseController.TAG;
     */
    /* JADX WARN: Code restructure failed: missing block: B:581:0x0354, code lost:
        r3 = r7;
        r1 = r1;
        r2 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:594:0x03fb, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:596:0x03fe, code lost:
        if (com.jingdong.sdk.oklog.OKLog.E != false) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:597:0x0400, code lost:
        com.jingdong.sdk.oklog.OKLog.e(r2, r1, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:598:0x0403, code lost:
        com.jingdong.jdsdk.network.toolbox.ExceptionReporter.reportExceptionToBugly(r0);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:459:0x00e2 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:462:0x00f6 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:465:0x00ff A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:468:0x010b A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:471:0x0116 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0122 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:477:0x012e A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0139 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:483:0x0147 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:486:0x0152 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0173 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:492:0x018a A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:495:0x01a1 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:499:0x01b0 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:501:0x01b4 A[Catch: JSONException -> 0x034f, TRY_ENTER, TRY_LEAVE, TryCatch #4 {JSONException -> 0x034f, blocks: (B:457:0x00d1, B:460:0x00e7, B:463:0x00f9, B:466:0x0105, B:469:0x0110, B:472:0x011c, B:475:0x0128, B:478:0x0133, B:481:0x0141, B:484:0x014c, B:487:0x016d, B:490:0x017c, B:493:0x018f, B:496:0x01a8, B:507:0x01c2, B:511:0x01cb, B:514:0x01d6, B:518:0x01e2, B:521:0x01ed, B:524:0x021d, B:527:0x0229, B:530:0x0239, B:570:0x0322, B:501:0x01b4, B:506:0x01bf), top: B:608:0x00d1 }] */
    /* JADX WARN: Removed duplicated region for block: B:509:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x01e8 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:523:0x01fa A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0223 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0232 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TRY_LEAVE, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0243 A[Catch: JSONException -> 0x00cc, TRY_ENTER, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:539:0x0268 A[Catch: JSONException -> 0x00cc, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:551:0x02b4 A[Catch: JSONException -> 0x00cc, TryCatch #1 {JSONException -> 0x00cc, blocks: (B:446:0x009f, B:448:0x00a5, B:449:0x00b0, B:451:0x00b6, B:452:0x00c8, B:459:0x00e2, B:462:0x00f6, B:465:0x00ff, B:468:0x010b, B:471:0x0116, B:474:0x0122, B:477:0x012e, B:480:0x0139, B:483:0x0147, B:486:0x0152, B:489:0x0173, B:492:0x018a, B:495:0x01a1, B:499:0x01b0, B:517:0x01e0, B:520:0x01e8, B:523:0x01fa, B:526:0x0223, B:529:0x0232, B:532:0x0243, B:534:0x024f, B:536:0x0255, B:537:0x0262, B:539:0x0268, B:541:0x027a, B:543:0x0286, B:544:0x0294, B:546:0x029b, B:548:0x02a1, B:549:0x02ae, B:551:0x02b4, B:553:0x02c6, B:555:0x02d2, B:556:0x02e0), top: B:602:0x009f }] */
    /* JADX WARN: Removed duplicated region for block: B:584:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x0367 A[Catch: Exception -> 0x03fb, TryCatch #0 {Exception -> 0x03fb, blocks: (B:586:0x035d, B:588:0x0367, B:590:0x0371, B:592:0x03e6), top: B:600:0x035d }] */
    /* JADX WARN: Removed duplicated region for block: B:610:0x02e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:614:0x0294 A[EDGE_INSN: B:614:0x0294->B:544:0x0294 BREAK  A[LOOP:0: B:537:0x0262->B:617:0x0262], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:621:0x02e0 A[EDGE_INSN: B:621:0x02e0->B:556:0x02e0 BREAK  A[LOOP:1: B:549:0x02ae->B:624:0x02ae], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v24, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v23, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v30, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r7v6, types: [org.json.JSONObject] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JSONObject getCartRequestParam(CartRequest cartRequest, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        int i2;
        HashMap<String, String> hashMap;
        CartRequestCommon cartRequestCommon;
        ArrayList<CartRequestOperate> arrayList;
        boolean z;
        HashMap<String, String> hashMap2;
        HashMap<String, Object> hashMap3;
        String str14;
        String str15;
        JSONObject jSONObject;
        JSONObject params;
        JSONObject jSONObject2;
        String str16;
        String str17;
        String versionName;
        JSONObject jSONObject3;
        HashMap<String, String> hashMap4;
        String str18;
        String str19;
        AddressGlobal addressGlobal;
        String valueOf;
        Iterator<Map.Entry<String, String>> it;
        Iterator<Map.Entry<String, String>> it2;
        if (cartRequest != null) {
            CartRequestCommon cartCommon = cartRequest.getCartCommon();
            ArrayList<CartRequestOperate> cartOperates = cartRequest.getCartOperates();
            boolean noResponse = cartRequest.getNoResponse();
            String str20 = cartRequest.replaceSkus;
            String str21 = cartRequest.refer;
            String str22 = cartRequest.contractPhoneSource;
            String str23 = cartRequest.response;
            String str24 = cartRequest.ybOperateType;
            String str25 = cartRequest.hsOperateType;
            String str26 = cartRequest.threeCcOperateType;
            String str27 = cartRequest.gsOperateType;
            HashMap<String, String> hashMap5 = cartRequest.operationType;
            hashMap2 = cartRequest.extFlag;
            hashMap3 = cartRequest.extendParams;
            String str28 = cartRequest.isNewMode;
            String str29 = cartRequest.businessName;
            String str30 = cartRequest.storeId;
            int i3 = cartRequest.appleCare;
            str3 = str26;
            str12 = str25;
            str11 = str24;
            str10 = str23;
            str9 = str22;
            str8 = str21;
            str7 = str20;
            z = noResponse;
            arrayList = cartOperates;
            cartRequestCommon = cartCommon;
            hashMap = hashMap5;
            str6 = str30;
            str5 = str27;
            str2 = "8";
            i2 = i3;
            str4 = str29;
            str13 = "";
        } else {
            str2 = "8";
            str3 = "";
            str4 = str3;
            str5 = str4;
            str6 = str5;
            str7 = str6;
            str8 = str7;
            str9 = str8;
            str10 = str9;
            str11 = str10;
            str12 = str11;
            str13 = str12;
            i2 = 0;
            hashMap = null;
            cartRequestCommon = null;
            arrayList = null;
            z = false;
            hashMap2 = null;
            hashMap3 = null;
        }
        if (cartRequestCommon != null) {
            try {
                params = cartRequestCommon.toParams();
            } catch (JSONException e2) {
                e = e2;
                str14 = "syncCart -->> ";
                str15 = TAG;
                jSONObject = null;
                if (OKLog.E) {
                }
                jSONObject2 = jSONObject;
                str17 = str14;
                str16 = str15;
                versionName = PackageInfoUtil.getVersionName();
                if (!TextUtils.isEmpty(versionName)) {
                }
                return jSONObject2;
            }
        } else {
            params = null;
        }
        if (params == null) {
            try {
                jSONObject3 = new JSONObject();
            } catch (JSONException e3) {
                e = e3;
                jSONObject = params;
                str14 = "syncCart -->> ";
                str15 = TAG;
                if (OKLog.E) {
                }
                jSONObject2 = jSONObject;
                str17 = str14;
                str16 = str15;
                versionName = PackageInfoUtil.getVersionName();
                if (!TextUtils.isEmpty(versionName)) {
                }
                return jSONObject2;
            }
        } else {
            jSONObject3 = params;
        }
        if (arrayList != null) {
            try {
            } catch (JSONException e4) {
                e = e4;
                jSONObject = jSONObject3;
                str14 = "syncCart -->> ";
                str15 = TAG;
                if (OKLog.E) {
                    OKLog.e(str15, str14, e);
                }
                jSONObject2 = jSONObject;
                str17 = str14;
                str16 = str15;
                versionName = PackageInfoUtil.getVersionName();
                if (!TextUtils.isEmpty(versionName)) {
                }
                return jSONObject2;
            }
            if (arrayList.size() > 0) {
                hashMap4 = hashMap;
                JSONArray jSONArray = new JSONArray();
                for (Iterator<CartRequestOperate> it3 = arrayList.iterator(); it3.hasNext(); it3 = it3) {
                    jSONArray.put(it3.next().toParams());
                }
                jSONObject3.put(CartConstant.KEY_OPERATIONS, jSONArray);
                jSONObject3.put(CartConstant.KEY_SYNC_TYPE, "1");
                jSONObject3.put(CartConstant.KEY_CART_UUID, StatisticsReportUtil.readCartUUID());
                if (z) {
                    jSONObject3.put(CartConstant.KEY_NO_RESPONSE, z);
                }
                jSONObject3.put(CartConstant.KEY_ADDRESS_ID, CartBaseTool.getAddressId());
                if (!TextUtils.isEmpty(str7)) {
                    jSONObject3.put(CartConstant.KEY_OPERATIONS, str7);
                }
                if (!TextUtils.isEmpty(str8)) {
                    jSONObject3.put(UnionConstants.BUNDLE_REFER, str8);
                }
                if (!TextUtils.isEmpty(str9)) {
                    jSONObject3.put("contractPhoneSource", str9);
                }
                if (!TextUtils.isEmpty(str10)) {
                    jSONObject3.put("response", str10);
                }
                if (!TextUtils.isEmpty(str11)) {
                    jSONObject3.put("ybOperateType", str11);
                }
                if (!TextUtils.isEmpty(str12)) {
                    jSONObject3.put("hsOperateType", str12);
                }
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject3.put("threeCcOperateType", str3);
                }
                if (!TextUtils.isEmpty(str5)) {
                    jSONObject3.put("gsOperateType", str5);
                }
                addressGlobal = AddressUtil.getAddressGlobal();
                if (addressGlobal != null) {
                    jSONObject3.put(PdLVBody.LONGITUDE, addressGlobal.getLongitude());
                    jSONObject3.put(PdLVBody.LATITUDE, addressGlobal.getLatitude());
                    jSONObject3.put("coord_type", addressGlobal.getCoordType());
                }
                if (isKeplerFunctionId(str)) {
                    jSONObject3.put("businessId", AdvertUtils.getBusinessId());
                }
                valueOf = String.valueOf(CartCommonUtil.getCartBundleVersionCode());
                if (!TextUtils.isEmpty(valueOf)) {
                    jSONObject3.put("cartBundleVersion", valueOf);
                }
                jSONObject3.put(CartConstant.KEY_USER_TYPE, CartCommonUtil.getUserType());
                if (!TextUtils.isEmpty(CartCommonUtil.noticeStatus)) {
                    jSONObject3.put(CartConstant.KEY_NOTICE_STATUS, CartCommonUtil.noticeStatus);
                }
                if (!CartCommonUtil.isNeedUploadUserFlag()) {
                    jSONObject3.put("homeWishListUserFlag", "1");
                } else {
                    jSONObject3.put("homeWishListUserFlag", CartCommonUtil.isHomeWishUser() ? "2" : "3");
                }
                jSONObject3.put("hitNewUIStatus", !ABTestUtils.is800UIStyle() ? 1 : 0);
                jSONObject3.put("mqTriggerStatus", CartCommonUtil.noFirstEntryCartPage ? "0" : "1");
                if (!CartCommonUtil.noFirstEntryCartPage) {
                    CartCommonUtil.noFirstEntryCartPage = true;
                }
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject3.put("businessName", str4);
                }
                str19 = 0;
                CartCommonUtil.getDcUrlData().updateTag = false;
                if (isResponseCartFid(str)) {
                    CartCommonUtil.getDcUrlData().updateTag = CartCommonUtil.genUpdateTag();
                    jSONObject3.put("updateTag", CartCommonUtil.getDcUrlData().updateTag);
                    str19 = 0;
                    CartCommonUtil.getDcUrlData().isFirstCart = false;
                    CartCommonUtil.getDcUrlData().isMD5Changed = false;
                }
                if (!TextUtils.isEmpty(str6)) {
                    jSONObject3.put("storeId", str6);
                }
                jSONObject3.put("appleCare", i2);
                if (CartCommonUtil.isSearchCart) {
                    jSONObject3.put("searchCart", true);
                }
                str18 = str;
                if ("cartAdd".equals(str18)) {
                    str18 = JDMtaUtils.getUnpl();
                    jSONObject3.put("unpl", str18);
                }
                if (hashMap4 != null && !hashMap4.isEmpty()) {
                    JSONObject jSONObject4 = new JSONObject();
                    it2 = hashMap4.entrySet().iterator();
                    while (true) {
                        str19 = it2.hasNext();
                        if (str19 == true) {
                            break;
                        }
                        Map.Entry<String, String> next = it2.next();
                        if (!TextUtils.isEmpty(next.getKey()) && !TextUtils.isEmpty(next.getValue())) {
                            jSONObject4.put(next.getKey(), next.getValue());
                        }
                    }
                    str18 = CartConstant.KEY_OPERATIONTYPE;
                    jSONObject3.put(CartConstant.KEY_OPERATIONTYPE, jSONObject4);
                }
                if (hashMap2 != null && !hashMap2.isEmpty()) {
                    JSONObject jSONObject5 = new JSONObject();
                    it = hashMap2.entrySet().iterator();
                    while (true) {
                        str19 = it.hasNext();
                        if (str19 == true) {
                            break;
                        }
                        Map.Entry<String, String> next2 = it.next();
                        if (!TextUtils.isEmpty(next2.getKey()) && !TextUtils.isEmpty(next2.getValue())) {
                            jSONObject5.put(next2.getKey(), next2.getValue());
                        }
                    }
                    str18 = CartConstant.KEY_EXTFLAG;
                    jSONObject3.put(CartConstant.KEY_EXTFLAG, jSONObject5);
                }
                if (hashMap3 != null) {
                    try {
                        if (!hashMap3.isEmpty()) {
                            Iterator<Map.Entry<String, Object>> it4 = hashMap3.entrySet().iterator();
                            while (true) {
                                str18 = it4.hasNext();
                                if (str18 == 0) {
                                    break;
                                }
                                Map.Entry<String, Object> next3 = it4.next();
                                str19 = TextUtils.isEmpty(next3.getKey());
                                if (str19 == 0 && (str19 = next3.getValue()) != 0) {
                                    str19 = next3.getKey();
                                    jSONObject3.put(str19, next3.getValue());
                                }
                            }
                        }
                    } catch (Exception e5) {
                        if (OKLog.E) {
                            String str31 = "syncCart -->> ";
                            String str32 = TAG;
                            OKLog.e(str32, str31, e5);
                            str18 = str31;
                            str19 = str32;
                        }
                    }
                }
                str18 = "syncCart -->> ";
                str19 = TAG;
                jSONObject3.put("configVersion", CartConfigState.getInstance().getConfigVersion());
                jSONObject3.put(CartConstant.KEY_CONFIG_DEGRADATION, CartConfigState.getInstance().getDegradation());
                str17 = str18;
                str16 = str19;
                jSONObject2 = jSONObject3;
                versionName = PackageInfoUtil.getVersionName();
                if (!TextUtils.isEmpty(versionName)) {
                    String[] split = versionName.split("\\.");
                    if (split.length == 3) {
                        String str33 = str13;
                        StringBuilder sb = new StringBuilder(str33);
                        int parseInt = Integer.parseInt(split[0]);
                        int parseInt2 = Integer.parseInt(split[1]);
                        int parseInt3 = Integer.parseInt(split[2]);
                        sb.append(parseInt + 6);
                        String str34 = str2;
                        sb.append(str34);
                        sb.append(parseInt2 + 1);
                        sb.append(str34);
                        sb.append(parseInt3 + 8);
                        String str35 = sb.toString().hashCode() + str33;
                        int intFromPreference = CommonBase.getIntFromPreference(CartBaseUtil.KEY_CART_CONFIG, 0);
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append((intFromPreference + str33).hashCode());
                        sb2.append(str33);
                        String sb3 = sb2.toString();
                        if (jSONObject2 != null) {
                            jSONObject2.put(CartConstant.KEY_CART_CVHV, str35 + sb3);
                        }
                    }
                }
                return jSONObject2;
            }
        }
        hashMap4 = hashMap;
        jSONObject3.put(CartConstant.KEY_SYNC_TYPE, "1");
        jSONObject3.put(CartConstant.KEY_CART_UUID, StatisticsReportUtil.readCartUUID());
        if (z) {
        }
        jSONObject3.put(CartConstant.KEY_ADDRESS_ID, CartBaseTool.getAddressId());
        if (!TextUtils.isEmpty(str7)) {
        }
        if (!TextUtils.isEmpty(str8)) {
        }
        if (!TextUtils.isEmpty(str9)) {
        }
        if (!TextUtils.isEmpty(str10)) {
        }
        if (!TextUtils.isEmpty(str11)) {
        }
        if (!TextUtils.isEmpty(str12)) {
        }
        if (!TextUtils.isEmpty(str3)) {
        }
        if (!TextUtils.isEmpty(str5)) {
        }
        addressGlobal = AddressUtil.getAddressGlobal();
        if (addressGlobal != null) {
        }
        if (isKeplerFunctionId(str)) {
        }
        valueOf = String.valueOf(CartCommonUtil.getCartBundleVersionCode());
        if (!TextUtils.isEmpty(valueOf)) {
        }
        jSONObject3.put(CartConstant.KEY_USER_TYPE, CartCommonUtil.getUserType());
        if (!TextUtils.isEmpty(CartCommonUtil.noticeStatus)) {
        }
        if (!CartCommonUtil.isNeedUploadUserFlag()) {
        }
        jSONObject3.put("hitNewUIStatus", !ABTestUtils.is800UIStyle() ? 1 : 0);
        jSONObject3.put("mqTriggerStatus", CartCommonUtil.noFirstEntryCartPage ? "0" : "1");
        if (!CartCommonUtil.noFirstEntryCartPage) {
        }
        if (!TextUtils.isEmpty(str4)) {
        }
        str19 = 0;
        CartCommonUtil.getDcUrlData().updateTag = false;
        if (isResponseCartFid(str)) {
        }
        if (!TextUtils.isEmpty(str6)) {
        }
        jSONObject3.put("appleCare", i2);
        if (CartCommonUtil.isSearchCart) {
        }
        str18 = str;
        if ("cartAdd".equals(str18)) {
        }
        if (hashMap4 != null) {
            JSONObject jSONObject42 = new JSONObject();
            it2 = hashMap4.entrySet().iterator();
            while (true) {
                str19 = it2.hasNext();
                if (str19 == true) {
                }
            }
            str18 = CartConstant.KEY_OPERATIONTYPE;
            jSONObject3.put(CartConstant.KEY_OPERATIONTYPE, jSONObject42);
        }
        if (hashMap2 != null) {
            JSONObject jSONObject52 = new JSONObject();
            it = hashMap2.entrySet().iterator();
            while (true) {
                str19 = it.hasNext();
                if (str19 == true) {
                }
            }
            str18 = CartConstant.KEY_EXTFLAG;
            jSONObject3.put(CartConstant.KEY_EXTFLAG, jSONObject52);
        }
        if (hashMap3 != null) {
        }
        str18 = "syncCart -->> ";
        str19 = TAG;
        jSONObject3.put("configVersion", CartConfigState.getInstance().getConfigVersion());
        jSONObject3.put(CartConstant.KEY_CONFIG_DEGRADATION, CartConfigState.getInstance().getDegradation());
        str17 = str18;
        str16 = str19;
        jSONObject2 = jSONObject3;
        versionName = PackageInfoUtil.getVersionName();
        if (!TextUtils.isEmpty(versionName)) {
        }
        return jSONObject2;
    }

    public static int getProductCount() {
        return CommonBase.getJdSharedPreferences().getInt(SHARED_PREFERENCES_SHOPPING_CART_COUNT, 0);
    }

    public static String getResultMsg(CartResponse cartResponse) {
        if (cartResponse == null) {
            return StringUtil.getString(R.string.cart_operate_fail);
        }
        if (cartResponse.getResultCode() == 0) {
            if (TextUtils.isEmpty(cartResponse.getResultMsg())) {
                return StringUtil.getString(R.string.cart_operate_success);
            }
            return cartResponse.getResultMsg();
        } else if (TextUtils.isEmpty(cartResponse.getResultMsg())) {
            return StringUtil.getString(R.string.cart_operate_fail);
        } else {
            return cartResponse.getResultMsg();
        }
    }

    private static boolean isKeplerFunctionId(String str) {
        return TextUtils.equals("cart", str) || TextUtils.equals("cartYB", str) || TextUtils.equals("cartChange", str) || TextUtils.equals("cartCheckAll", str) || TextUtils.equals("cartUnCheckAll", str) || TextUtils.equals("cartAdd", str) || TextUtils.equals("cartRemove", str) || TextUtils.equals("cartRemoveGift", str) || TextUtils.equals("cartCheckSingle", str) || TextUtils.equals("cartUnCheckSingle", str) || TextUtils.equals("changePromotion", str) || TextUtils.equals("cartChangeGift", str) || TextUtils.equals(CartConstant.FUNCTION_ID_LOCATION_FLOOR_DATA, str) || TextUtils.equals("cartReplace", str);
    }

    public static boolean isResponseCartFid(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(str, "cart") || TextUtils.equals(str, "cartChange") || TextUtils.equals(str, CartConstant.FUNCTION_ID_CART_SWITCH) || TextUtils.equals(str, "cartRemove") || TextUtils.equals(str, "cartRemoveGift") || TextUtils.equals(str, "changePromotion") || TextUtils.equals(str, "cartCheckSingle") || TextUtils.equals(str, "cartUnCheckSingle") || TextUtils.equals(str, "cartCheckAll") || TextUtils.equals(str, CartConstant.FUNCTION_ID_LOCATION_FLOOR_DATA) || TextUtils.equals(str, "cartUnCheckAll");
    }

    public static void modifyService(CartModifyServiceEntity cartModifyServiceEntity) {
        if (cartModifyServiceEntity == null || cartModifyServiceEntity.myActivity == null) {
            return;
        }
        CartPackSummary cartPackSummary = cartModifyServiceEntity.addYbPack;
        CartPackSummary cartPackSummary2 = cartModifyServiceEntity.addHsPack;
        CartPackSummary cartPackSummary3 = cartModifyServiceEntity.addThreeCcPack;
        CartPackSummary cartPackSummary4 = cartModifyServiceEntity.delYbPack;
        CartPackSummary cartPackSummary5 = cartModifyServiceEntity.delHsPack;
        CartPackSummary cartPackSummary6 = cartModifyServiceEntity.delThreeCcPack;
        CartPackSummary cartPackSummary7 = cartModifyServiceEntity.addGiftCardPack;
        CartPackSummary cartPackSummary8 = cartModifyServiceEntity.delGiftCardPack;
        ViewGroup viewGroup = cartModifyServiceEntity.loadingLayout;
        String str = cartModifyServiceEntity.refer;
        final ShoppingMultiListener shoppingMultiListener = cartModifyServiceEntity.listener;
        HashMap<String, Object> hashMap = cartModifyServiceEntity.requestParam;
        boolean z = (cartPackSummary == null || cartPackSummary.getChildItems() == null || cartPackSummary.getChildItems().size() <= 0) ? false : true;
        boolean z2 = (cartPackSummary2 == null || cartPackSummary2.getChildItems() == null || cartPackSummary2.getChildItems().size() <= 0) ? false : true;
        boolean z3 = (cartPackSummary3 == null || cartPackSummary3.getChildItems() == null || cartPackSummary3.getChildItems().size() <= 0) ? false : true;
        boolean z4 = cartPackSummary4 != null;
        boolean z5 = cartPackSummary5 != null;
        boolean z6 = cartPackSummary6 != null;
        boolean z7 = cartPackSummary7 != null;
        boolean z8 = cartPackSummary8 != null;
        if (shoppingMultiListener == null) {
            shoppingMultiListener = new ShoppingMultiListener();
        }
        ArrayList<CartSummary> arrayList = new ArrayList<>();
        if (z) {
            arrayList.addAll(cartPackSummary.getChildItems());
        }
        if (z2) {
            arrayList.addAll(cartPackSummary2.getChildItems());
        }
        if (z3) {
            arrayList.addAll(cartPackSummary3.getChildItems());
        }
        shoppingMultiListener.setSkuItems(arrayList);
        ArrayList arrayList2 = new ArrayList();
        if (cartPackSummary != null) {
            arrayList2.add(cartPackSummary);
        }
        if (cartPackSummary2 != null) {
            arrayList2.add(cartPackSummary2);
        }
        if (cartPackSummary3 != null) {
            arrayList2.add(cartPackSummary3);
        }
        if (cartPackSummary4 != null) {
            arrayList2.add(cartPackSummary4);
        }
        if (cartPackSummary5 != null) {
            arrayList2.add(cartPackSummary5);
        }
        if (cartPackSummary6 != null) {
            arrayList2.add(cartPackSummary6);
        }
        if (cartPackSummary7 != null) {
            arrayList2.add(cartPackSummary7);
        }
        if (cartPackSummary8 != null) {
            arrayList2.add(cartPackSummary8);
        }
        CartSkuSummary cartSkuSummary = cartModifyServiceEntity.switchDeliverySku;
        ArrayList arrayList3 = null;
        if (cartSkuSummary != null) {
            arrayList3 = new ArrayList();
            arrayList3.add(cartSkuSummary);
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate(arrayList3, arrayList2, "2"));
        cartRequest.setModelGroupUtil(new CartHttpCacheUtil());
        cartRequest.refer = str;
        cartRequest.isNotify = true;
        cartRequest.isEffect = true;
        if (z) {
            cartRequest.ybOperateType = "1";
        }
        if (z4) {
            cartRequest.ybOperateType = "2";
        }
        if (z2) {
            cartRequest.hsOperateType = "1";
        }
        if (z5) {
            cartRequest.hsOperateType = "2";
        }
        if (z3) {
            cartRequest.threeCcOperateType = "1";
        }
        if (z6) {
            cartRequest.threeCcOperateType = "2";
        }
        if (z7) {
            cartRequest.gsOperateType = "1";
        }
        if (z8) {
            cartRequest.gsOperateType = "2";
        }
        if (viewGroup != null) {
            cartRequest.setLoadingViewRoot(viewGroup);
        }
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        if (cartModifyServiceEntity.source == 1) {
            syncCart(cartModifyServiceEntity.myActivity, CartConstant.FUNCTION_ID_CART_SWITCH, cartRequest, new ShoppingHttpListener(shoppingMultiListener));
            return;
        }
        HttpGroup httpGroupaAsynPool = cartModifyServiceEntity.myActivity.getHttpGroupaAsynPool();
        if (httpGroupaAsynPool == null) {
            return;
        }
        JSONObject cartRequestParam = getCartRequestParam(cartRequest, CartConstant.FUNCTION_ID_CART_SWITCH);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setEncryptBody(CartCommonUtil.isEncryptBody());
        httpSetting.setProgressBarRootLayout(viewGroup);
        httpSetting.setFunctionId(CartConstant.FUNCTION_ID_CART_SWITCH);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getCartHost());
        if (cartRequestParam != null && !TextUtils.isEmpty(cartRequestParam.toString())) {
            httpSetting.setJsonParams(JsonParser.parseParamsJsonFromString(cartRequestParam.toString()));
        }
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.controller.ShoppingBaseController.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (shoppingMultiListener == null || httpResponse == null) {
                    return;
                }
                shoppingMultiListener.onEnd(new CartResponse(httpResponse.getString(), httpResponse.getFastJsonObject()));
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                ShoppingMultiListener shoppingMultiListener2 = shoppingMultiListener;
                if (shoppingMultiListener2 != null) {
                    shoppingMultiListener2.onReady();
                }
            }
        });
        httpGroupaAsynPool.add(httpSetting);
    }

    public static void requestCartHeartBeatExt() {
        try {
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setEncryptBody(CartCommonUtil.isEncryptBody());
            httpSetting.setNeedRetryOnBusinessLayer(false);
            httpSetting.setFunctionId(CartConstant.FUNCTION_ID_CART_HBEXT);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setHost(Configuration.getCartHost());
            httpSetting.setNotifyUser(false);
            httpSetting.setEffect(0);
            httpSetting.setListener(null);
            AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
            if (addressGlobal != null) {
                httpSetting.putJsonParam(PdLVBody.LONGITUDE, addressGlobal.getLongitude());
                httpSetting.putJsonParam(PdLVBody.LATITUDE, addressGlobal.getLatitude());
                httpSetting.putJsonParam("coord_type", addressGlobal.getCoordType());
            }
            new HttpGroupUtil().getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.i(TAG, e2);
            }
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static void saveCartConfig(CartResponse cartResponse) {
        int i2;
        if (cartResponse.config != null && (i2 = cartResponse.configVersion) != -1 && (i2 != CartConfigState.getInstance().getConfigVersion() || cartResponse.degradation != CartConfigState.getInstance().getDegradation())) {
            if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "\u3010\u5b58\u50a8\u3011saveCartConfig configVersion:" + cartResponse.configVersion);
                OKLog.d(CartConfigState.TAG, "\u3010\u5b58\u50a8\u3011saveCartConfig \u63a5\u53e3\u8fd4\u56de config:" + cartResponse.config);
            }
            JDJSONObject jointData = CartConfigInfo.jointData(cartResponse.config, cartResponse.configVersion);
            if (cartResponse.degradation == 1) {
                if (!CartConfigState.getInstance().isLoad()) {
                    if (OKLog.D) {
                        OKLog.d(CartConfigState.TAG, "\u6682\u672a\u52a0\u8f7d\u672c\u5730\uff0c\u89e6\u53d1\u52a0\u8f7d");
                    }
                    CartCommonUtil.loadCartConfigInfo(JdSdk.getInstance().getApplicationContext());
                }
                if (CartConfigState.getInstance().isValid()) {
                    if (OKLog.D) {
                        OKLog.d(CartConfigState.TAG, "\u964d\u7ea7\u6570\u636e\u5408\u5e76");
                    }
                    jointData = CartConfigState.getInstance().mergeCartConfigInfo(jointData);
                } else if (OKLog.D) {
                    OKLog.d(CartConfigState.TAG, "\u964d\u7ea7\u6570\u636e\u76f4\u63a5\u5b58\u50a8");
                }
            } else if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "\u975e\u964d\u7ea7\u6570\u636e\u76f4\u63a5\u5b58\u50a8");
            }
            CartCommonUtil.initCartConfigData(jointData, cartResponse.configVersion, cartResponse.degradation);
            CartCommonUtil.saveCartConfigToFile(jointData, cartResponse.configVersion, cartResponse.degradation);
            if (OKLog.D) {
                OKLog.d(CartConfigState.TAG, "\u3010\u5b58\u50a8\u3011 \u6700\u7ec8\u4f7f\u7528&\u5b58\u50a8 config :" + jointData);
            }
        } else if (OKLog.D) {
            OKLog.d(CartConfigState.TAG, " \u4e3b\u6570\u636e\u672a\u4e0b\u53d1 config \u76f8\u5173\u6570\u636e\uff0c\u6216\u4e0b\u53d1\u7684 config \u6570\u636e\u4e0e\u672c\u5730\u7248\u672c\u4e00\u81f4\u3002");
        }
    }

    public static void selectAll(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " selectAll -->> myActivity:" + iMyActivity + ", listener:" + shoppingListener);
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate("7"));
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartCheckAll(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), true, true, onQueueCancelListener);
    }

    public static void selectOneProductOrPack(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " selectOneProductOrPack -->> myActivity:" + iMyActivity + ", selectSku:" + cartSkuSummary + ", selectePack:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (cartSkuSummary == null && cartPackSummary == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CartRequestOperate(cartSkuSummary, cartPackSummary, "5"));
        CartRequest cartRequest = new CartRequest(arrayList);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.isTouch = true;
        cartRequest.isEffect = true;
        cartRequest.isNotify = true;
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartCheckSingle(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void setCommon(ICommon iCommon2) {
        iCommon = iCommon2;
    }

    public static void setLoginOutListener(ICartLoginOut iCartLoginOut) {
        loginOutListener = iCartLoginOut;
    }

    public static void setProductCount(int i2) {
        CommonBase.getJdSharedPreferences().edit().putInt(SHARED_PREFERENCES_SHOPPING_CART_COUNT, i2).commit();
    }

    public static void showToast(final IMyActivity iMyActivity, final int i2, final String str) {
        iMyActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.3
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.showToastInCenter((Context) iMyActivity.getThisActivity(), i2 == 0 ? (byte) 2 : (byte) 1, str, 0);
            }
        });
    }

    public static void syncCart(IMyActivity iMyActivity, CartRequest cartRequest, HttpGroup.OnCommonListener onCommonListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cart", cartRequest, onCommonListener, onQueueCancelListener);
    }

    public static void syncCartAdd(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, boolean z, boolean z2) {
        cartRequest.isNotify = z;
        cartRequest.isEffect = z2;
        syncCart(iMyActivity, "cartAdd", cartRequest, shoppingHttpListener);
    }

    private static void syncCartChange(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cartChange", cartRequest, shoppingHttpListener, onQueueCancelListener);
    }

    public static void syncCartChangePromotion(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener) {
        cartRequest.isNotify = true;
        cartRequest.isEffect = true;
        syncCart(iMyActivity, "changePromotion", cartRequest, shoppingHttpListener);
    }

    public static void syncCartCheckAll(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, boolean z, boolean z2, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        cartRequest.isNotify = z;
        cartRequest.isEffect = z2;
        syncCart(iMyActivity, "cartCheckAll", cartRequest, shoppingHttpListener, onQueueCancelListener);
    }

    public static void syncCartCheckSingle(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cartCheckSingle", cartRequest, shoppingHttpListener, onQueueCancelListener);
    }

    public static void syncCartGiftChange(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener) {
        cartRequest.isNotify = true;
        cartRequest.isEffect = true;
        syncCart(iMyActivity, "cartChangeGift", cartRequest, shoppingHttpListener);
    }

    public static void syncCartGiftRemove(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener) {
        cartRequest.isNotify = true;
        cartRequest.isEffect = true;
        syncCart(iMyActivity, "cartRemoveGift", cartRequest, shoppingHttpListener);
    }

    public static void syncCartNoResponse(IMyActivity iMyActivity) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setNoResponse(true);
        cartRequest.isNotify = true;
        cartRequest.isEffect = false;
        syncCart(iMyActivity, cartRequest, new ShoppingHttpListener(null));
    }

    private static void syncCartRemove(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cartRemove", cartRequest, shoppingHttpListener, onQueueCancelListener);
    }

    public static void syncCartUnCheckAll(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cartUnCheckAll", cartRequest, shoppingHttpListener, onQueueCancelListener);
    }

    public static void syncCartUnCheckSingle(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingListener shoppingListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        syncCart(iMyActivity, "cartUnCheckSingle", cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void syncCartWithNoResponse(IMyActivity iMyActivity, ShoppingListener shoppingListener) {
        CartRequest cartRequest = new CartRequest();
        cartRequest.setNoResponse(true);
        cartRequest.isNotify = true;
        cartRequest.isEffect = false;
        syncCart(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void unSelectAll(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " unSelectAll -->> myActivity:" + iMyActivity + ", listener:" + shoppingListener);
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate("8"));
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = true;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartUnCheckAll(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void unSelectOneProductOrPack(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, CartSkuSummary cartSkuSummary, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " unSelectOneProductOrPack -->> myActivity:" + iMyActivity + ", unSelectSku:" + cartSkuSummary + ", unSelectePack:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (cartSkuSummary == null && cartPackSummary == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CartRequestOperate(cartSkuSummary, cartPackSummary, "6"));
        CartRequest cartRequest = new CartRequest(arrayList);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = true;
        cartRequest.isNotify = true;
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartUnCheckSingle(iMyActivity, cartRequest, shoppingListener, onQueueCancelListener);
    }

    public static void uniformSyncCart(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingListener shoppingListener) {
        syncCart(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void validateCartIcon() {
        try {
            IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
            if (mainFrameActivity != null) {
                mainFrameActivity.validateCartIcon();
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static void changeBestPromotion(int i2, CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, JDJSONObject jDJSONObject, ShoppingListener shoppingListener, ViewGroup viewGroup, HashMap<String, Object> hashMap) {
        HashMap<String, Object> hashMap2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CartRequestOperateBestPromotion(jDJSONObject));
        CartRequest cartRequest = new CartRequest(arrayList);
        if (i2 != -1 && (hashMap2 = cartRequest.extendParams) != null) {
            hashMap2.put("autoChangeProm", 1);
        }
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        syncCartChangePromotion(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void editProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        editProductOrPackList(cartHttpCacheUtil, iMyActivity, arrayList, arrayList2, shoppingListener, z, false, null, viewGroup, hashMap, onQueueCancelListener);
    }

    public static void syncCart(IMyActivity iMyActivity, CartRequest cartRequest, HttpGroup.OnCommonListener onCommonListener) {
        syncCart(iMyActivity, "cart", cartRequest, onCommonListener, null);
    }

    public static void editProductOrPackList(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, ViewGroup viewGroup, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        editProductOrPackList(new CartHttpCacheUtil(), iMyActivity, arrayList, arrayList2, shoppingListener, z, z2, null, viewGroup, onQueueCancelListener);
    }

    public static void syncCart(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingListener shoppingListener) {
        syncCart(iMyActivity, "cart", cartRequest, new ShoppingHttpListener(shoppingListener), null);
    }

    /* loaded from: classes5.dex */
    public static class ShoppingHttpListener implements HttpGroup.OnCommonListener {
        private boolean isUpdateSourceEntity;
        private ShoppingListener mShoppingListener;

        public ShoppingHttpListener(ShoppingListener shoppingListener, boolean z) {
            this.isUpdateSourceEntity = true;
            this.mShoppingListener = shoppingListener;
            this.isUpdateSourceEntity = z;
        }

        private void shoppingOnEnd(CartResponse cartResponse) {
            ShoppingListener shoppingListener = this.mShoppingListener;
            if (shoppingListener != null) {
                shoppingListener.onEnd(cartResponse);
            }
        }

        private void shoppingOnError(String str) {
            ShoppingListener shoppingListener = this.mShoppingListener;
            if (shoppingListener != null) {
                shoppingListener.onError(str);
            }
        }

        private void shoppingOnReady() {
            ShoppingListener shoppingListener = this.mShoppingListener;
            if (shoppingListener != null) {
                shoppingListener.onReady();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            boolean z;
            if (OKLog.D) {
                OKLog.i(ShoppingBaseController.TAG, " ShoppingHttpListener-->> onEnd isCache : " + httpResponse.isCache() + " , httpResponse : " + httpResponse);
            }
            if (httpResponse != null) {
                CartResponse cartResponse = new CartResponse(httpResponse.getString(), httpResponse.getFastJsonObject());
                cartResponse.setCache(httpResponse.isCache());
                if (OKLog.D) {
                    OKLog.d(ShoppingBaseController.TAG, " -->> isCache : " + httpResponse.isCache());
                }
                int i2 = 0;
                if (cartResponse.getResultCode() == 21) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("message", cartResponse.getResultMsg());
                        jSONObject.put("resultCode", cartResponse.getResultCode());
                        CartCommonUtil.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "Shopcart_AddFailExpo", "", RecommendMtaUtils.Shopcart_PageId, "", "", jSONObject.toString(), null);
                        if (!LoginUserBase.hasLogin()) {
                            Bundle bundle = new Bundle();
                            if (!JDElderModeUtils.isElderMode()) {
                                bundle.putInt(LoginConstans.NEED_REFRESH_MODE, LoginConstans.REFRESH_MODE_VALUE);
                            }
                            DeepLinkLoginHelper.startLoginActivity(JdSdk.getInstance().getApplicationContext(), bundle);
                            shoppingOnEnd(cartResponse);
                            return;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }
                if (!TextUtils.isEmpty(cartResponse.getErrorMessage())) {
                    shoppingOnError(cartResponse.getErrorMessage());
                    return;
                }
                if (OKLog.D) {
                    OKLog.i(ShoppingBaseController.TAG, " -->> getInfo : " + cartResponse.getInfo());
                }
                if (cartResponse.getInfo() == null) {
                    z = true;
                } else {
                    i2 = cartResponse.getInfo().cartNum;
                    z = !cartResponse.getInfo().subFlow;
                    if (TextUtils.equals(cartResponse.getInfo().getAbResult("30"), "B")) {
                        ShoppingBaseController.requestCartHeartBeatExt();
                    }
                }
                ShoppingBaseController.saveCartConfig(cartResponse);
                ShoppingBaseController.noRefreshCart = cartResponse.noRefreshCart.booleanValue();
                if (OKLog.D) {
                    OKLog.d(ShoppingBaseController.TAG, " \u4fdd\u5b58 noRefreshCart:" + ShoppingBaseController.noRefreshCart);
                }
                if (cartResponse.getInfo() != null && !TextUtils.isEmpty(cartResponse.getInfo().cartExpansion220)) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("Type", (Object) (TextUtils.equals(cartResponse.getInfo().cartExpansion220, "B") ? "1" : "0"));
                    CartCommonUtil.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), "Shopcart_PlusCartABExpo", "", RecommendMtaUtils.Shopcart_PageId, "", "", jDJSONObject.toString(), null);
                }
                if (cartResponse.getResultCode() == 0 && z) {
                    ShoppingBaseController.setProductCount(i2);
                }
                ShoppingBaseController.validateCartIcon();
                shoppingOnEnd(cartResponse);
                if (LoginUserBase.hasLogin()) {
                    LoginUserBase.setAlreadySyncCart(true);
                    return;
                }
                return;
            }
            shoppingOnError(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            if (OKLog.D) {
                OKLog.i("ShoppingCartNewActivity", " ShoppingHttpListener-->> onError : ");
            }
            shoppingOnError(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            shoppingOnReady();
        }

        public ShoppingHttpListener(ShoppingListener shoppingListener) {
            this(shoppingListener, true);
        }
    }

    public static void editProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, ViewGroup viewGroup, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        editProductOrPackList(cartHttpCacheUtil, iMyActivity, arrayList, arrayList2, shoppingListener, z, z2, null, viewGroup, onQueueCancelListener);
    }

    public static void syncCart(IMyActivity iMyActivity, String str, CartRequest cartRequest, HttpGroup.OnCommonListener onCommonListener) {
        syncCart(iMyActivity, str, cartRequest, onCommonListener, null);
    }

    public static void syncCartAdd(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingHttpListener shoppingHttpListener) {
        syncCartAdd(iMyActivity, cartRequest, shoppingHttpListener, true, true);
    }

    @Deprecated
    /* loaded from: classes5.dex */
    public static class AddShoppingListener implements ShoppingListener {
        private boolean isFromCar;
        private boolean isFromPD;
        private IMyActivity myActivity;

        public AddShoppingListener(IMyActivity iMyActivity) {
            this.isFromCar = false;
            this.isFromPD = false;
            if (iMyActivity != null) {
                this.myActivity = iMyActivity;
                return;
            }
            throw new RuntimeException("myActivity is null");
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            final DialogController dialogController = new DialogController() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener.3
                {
                    AddShoppingListener.this = this;
                }

                @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 == -2) {
                        dialogInterface.dismiss();
                    } else if (i2 != -1) {
                    } else {
                        if (ShoppingBaseController.iCommon != null) {
                            if (AddShoppingListener.this.isFromPD) {
                                if (AddShoppingListener.this.isFromCar) {
                                    AddShoppingListener.this.myActivity.finish();
                                } else {
                                    ShoppingBaseController.iCommon.goToShoppingCartPageSingle(AddShoppingListener.this.myActivity);
                                }
                            } else {
                                ShoppingBaseController.iCommon.goToShoppingCartPage(AddShoppingListener.this.myActivity, true);
                            }
                        }
                        this.alertDialog.dismiss();
                    }
                }
            };
            dialogController.setTitle(com.jingdong.jdsdk.res.StringUtil.product_add_title);
            dialogController.setMessage(com.jingdong.jdsdk.res.StringUtil.product_add_message);
            dialogController.setPositiveButton(com.jingdong.jdsdk.res.StringUtil.product_add_positive);
            dialogController.setNegativeButton(com.jingdong.jdsdk.res.StringUtil.product_add_negative);
            dialogController.init(this.myActivity.getThisActivity());
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener.4
                {
                    AddShoppingListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    dialogController.show();
                }
            });
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            final DialogController dialogController = new DialogController() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener.1
                {
                    AddShoppingListener.this = this;
                }

                @Override // com.jingdong.common.ui.DialogController, android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -1) {
                        return;
                    }
                    dialogInterface.dismiss();
                }
            };
            dialogController.setMessage(str);
            dialogController.setPositiveButton("\u786e\u5b9a");
            dialogController.init(this.myActivity.getThisActivity());
            this.myActivity.post(new Runnable() { // from class: com.jingdong.common.controller.ShoppingBaseController.AddShoppingListener.2
                {
                    AddShoppingListener.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    dialogController.show();
                }
            });
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }

        public AddShoppingListener(IMyActivity iMyActivity, boolean z, boolean z2) {
            this(iMyActivity);
            this.isFromPD = z;
            this.isFromCar = z2;
        }
    }

    public static void editProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, String str, ViewGroup viewGroup, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " editProductOrPackList -->> myActivity:" + iMyActivity + ", editSkus:" + arrayList + ", editPacks:" + arrayList2 + ", listener:" + shoppingListener);
        }
        if (cartHttpCacheUtil == null) {
            cartHttpCacheUtil = new CartHttpCacheUtil();
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperate(arrayList, arrayList2, "3"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = z;
        cartRequest.isNotify = true;
        cartRequest.setNoResponse(z2);
        if (!TextUtils.isEmpty(str)) {
            cartRequest.refer = str;
        }
        syncCartChange(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    public static void syncCart(IMyActivity iMyActivity, String str, CartRequest cartRequest, HttpGroup.OnCommonListener onCommonListener, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        HttpGroup httpGroupaAsynPool;
        if (cartRequest == null) {
            cartRequest = new CartRequest();
        }
        if (cartRequest.getGroup() != null) {
            httpGroupaAsynPool = cartRequest.getGroup();
        } else if (iMyActivity == null) {
            httpGroupaAsynPool = HttpGroupUtils.getHttpGroupaAsynPool();
        } else {
            httpGroupaAsynPool = iMyActivity.getHttpGroupaAsynPool();
        }
        HttpGroup httpGroup = httpGroupaAsynPool;
        CartHttpCacheUtil modelGroupUtil = cartRequest.getModelGroupUtil();
        modelGroupUtil.needError = true;
        modelGroupUtil.isUseLocalCookie = true;
        modelGroupUtil.isNotifyUser = cartRequest.isNotify;
        modelGroupUtil.host = Configuration.getCartHost();
        modelGroupUtil.isProduct = cartRequest.isEffect;
        modelGroupUtil.cancelListener = onQueueCancelListener;
        modelGroupUtil.setLoadingContainer(cartRequest.getLoadingViewRoot());
        modelGroupUtil.addUseCache(iMyActivity, httpGroup, str, getCartRequestParam(cartRequest, str).toString(), onCommonListener);
    }

    public static void syncCartAdd(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingListener shoppingListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " cartRequest -->> cartRequest:" + cartRequest.toString());
        }
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void syncCartNoResponse(IMyActivity iMyActivity, String str) {
        if (OKLog.D) {
            OKLog.d(TAG, " syncCartNoResponse whit extensionParams");
        }
        if (!TextUtils.isEmpty(str)) {
            if (OKLog.D) {
                OKLog.d(TAG, " syncCartNoResponse whit extensionParams : " + str);
            }
            JDJSONObject parseObject = JDJSON.parseObject(str);
            if (parseObject != null && parseObject.containsKey("source") && "checkout".equals(parseObject.optString("source")) && noRefreshCart) {
                if (OKLog.D) {
                    OKLog.d(TAG, " syncCartNoResponse whit extensionParams :  \u8d2d\u7269\u8f66\u4e0b\u53d1\u5927\u4fc3\u964d\u7ea7 return");
                    return;
                }
                return;
            }
        }
        if (OKLog.D) {
            OKLog.d(TAG, " syncCartNoResponse whit extensionParams :  cart \u8c03\u7528");
        }
        CartRequest cartRequest = new CartRequest();
        cartRequest.setNoResponse(true);
        cartRequest.isNotify = true;
        cartRequest.isEffect = false;
        syncCart(iMyActivity, cartRequest, new ShoppingHttpListener(null));
    }

    public static void syncCartWithNoResponse(IMyActivity iMyActivity, CartRequest cartRequest, ShoppingListener shoppingListener) {
        syncCart(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void addMultiProductToCart(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z) {
        addMultiProductToCart(iMyActivity, arrayList, arrayList2, shoppingListener, z, true, true);
    }

    public static void batchDeleteMustGift(IMyActivity iMyActivity, ArrayList<CartSkuGiftSummary> arrayList, ArrayList<CartPackGiftSummary> arrayList2, ShoppingListener shoppingListener, ViewGroup viewGroup) {
        batchDeleteMustGift(new CartHttpCacheUtil(), iMyActivity, arrayList, arrayList2, shoppingListener, viewGroup, null);
    }

    public static void batchAddGiftToPromotionPack(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, String str, ViewGroup viewGroup) {
        if (OKLog.D) {
            OKLog.d(TAG, " addOneGiftToPack -->> myActivity:" + iMyActivity + ", addGiftCartSkuSummary:" + arrayList.size() + ", beAddedToCartPackSummary:" + cartPackSummary + ", listener:" + shoppingListener);
        }
        if (cartPackSummary != null) {
            Iterator<CartSkuSummary> it = arrayList.iterator();
            while (it.hasNext()) {
                cartPackSummary.addGift(it.next());
            }
        }
        CartRequest cartRequest = new CartRequest(new CartRequestOperate((CartSkuSummary) null, cartPackSummary, "2"));
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.businessName = str;
        syncCartAdd(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener));
    }

    public static void deleteProductOrPackList(IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, boolean z3) {
        if (OKLog.D) {
            OKLog.d(TAG, " deleteProductOrPackList -->> myActivity:" + iMyActivity + ", deleteSkus:" + arrayList + ", deletePacks:" + arrayList2 + ", listener:" + shoppingListener);
        }
        if (arrayList == null && arrayList2 == null) {
            return;
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperate(arrayList, arrayList2, "4"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.isEffect = z;
        cartRequest.isNotify = z2;
        cartRequest.setNoResponse(z3);
        syncCartRemove(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), null);
    }

    public static void batchDeleteGiftToPack(IMyActivity iMyActivity, ArrayList<? super CartSkuSummary> arrayList, CartPackSummary cartPackSummary, ShoppingListener shoppingListener, ViewGroup viewGroup) {
        batchDeleteGiftToPack(iMyActivity, arrayList, cartPackSummary, shoppingListener, viewGroup, null);
    }

    public static void editProductOrPackList(CartHttpCacheUtil cartHttpCacheUtil, IMyActivity iMyActivity, ArrayList<CartSkuSummary> arrayList, ArrayList<CartPackSummary> arrayList2, ShoppingListener shoppingListener, boolean z, boolean z2, String str, ViewGroup viewGroup, HashMap<String, Object> hashMap, JDGetWayQueueTools.OnQueueCancelListener onQueueCancelListener) {
        if (OKLog.D) {
            OKLog.d(TAG, " editProductOrPackList -->> myActivity:" + iMyActivity + ", editSkus:" + arrayList + ", editPacks:" + arrayList2 + ", listener:" + shoppingListener);
        }
        if (cartHttpCacheUtil == null) {
            cartHttpCacheUtil = new CartHttpCacheUtil();
        }
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new CartRequestOperate(arrayList, arrayList2, "3"));
        CartRequest cartRequest = new CartRequest(arrayList3);
        cartRequest.setLoadingViewRoot(viewGroup);
        cartRequest.setModelGroupUtil(cartHttpCacheUtil);
        cartRequest.isEffect = z;
        cartRequest.isNotify = true;
        cartRequest.setNoResponse(z2);
        if (hashMap != null && !hashMap.isEmpty()) {
            cartRequest.extendParams.putAll(hashMap);
        }
        if (!TextUtils.isEmpty(str)) {
            cartRequest.refer = str;
        }
        syncCartChange(iMyActivity, cartRequest, new ShoppingHttpListener(shoppingListener), onQueueCancelListener);
    }

    @Deprecated
    public static void addProductForProductList(IMyActivity iMyActivity, String str, int i2, Runnable runnable, Runnable runnable2, SourceEntity sourceEntity) {
        addProductForProductList(iMyActivity, str, i2, runnable, runnable2, sourceEntity, true, true);
    }
}
