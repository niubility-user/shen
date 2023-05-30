package com.jingdong.common.deeplinkhelper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.cart.CartBaseUtil;
import com.jingdong.common.entity.cart.CartCombineOderExtParam;
import com.jingdong.common.entity.cart.CartShopFareInfo;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.constant.CartConstant;
import org.json.JSONArray;

/* loaded from: classes5.dex */
public class DeepLinkCartHelper {
    private static final String HOST_CART_COMBINE_ORDER = "combineorderactivity";
    private static final String HOST_CART_GIFTPOOL = "giftpoolactivity";
    private static final String HOST_CART_SHOPPINGCART_MAIN = "shoppingcart";
    private static final String HOST_CART_TAKE_COUPON = "carttakecouponactivity";

    /* JADX WARN: Removed duplicated region for block: B:18:0x0028 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int calculateReqType(com.jingdong.common.entity.cart.CartShopFareInfo r3, com.jingdong.common.entity.cart.CartShopFareInfo r4) {
        /*
            r0 = 0
            r1 = 1
            if (r4 != 0) goto L9
            if (r3 != 0) goto L9
            r3 = 1
        L7:
            r4 = 0
            goto L20
        L9:
            if (r4 != 0) goto L12
            if (r3 == 0) goto L12
            int r3 = r3.fareType
            r4 = r3
            r3 = 0
            goto L20
        L12:
            if (r4 == 0) goto L19
            if (r3 != 0) goto L19
            int r3 = r4.fareType
            goto L7
        L19:
            int r4 = r4.fareType
            int r3 = r3.fareType
            r2 = r4
            r4 = r3
            r3 = r2
        L20:
            if (r3 != r1) goto L26
            if (r4 != r1) goto L26
            r0 = 2
            goto L29
        L26:
            if (r4 != r1) goto L29
            r0 = 1
        L29:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.deeplinkhelper.DeepLinkCartHelper.calculateReqType(com.jingdong.common.entity.cart.CartShopFareInfo, com.jingdong.common.entity.cart.CartShopFareInfo):int");
    }

    public static void forwardCombineOrderActivityFromOthers(IMyActivity iMyActivity, JSONArray jSONArray, JSONArray jSONArray2, CartShopFareInfo cartShopFareInfo, CartShopFareInfo cartShopFareInfo2, CartCombineOderExtParam cartCombineOderExtParam) {
        if (iMyActivity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(CartConstant.BUNDLE_KEY_CART_COMBINEORDER_REQTYPE, calculateReqType(cartShopFareInfo, cartShopFareInfo2));
        bundle.putParcelable(CartConstant.BUNDLE_KEY_CART_COMBINEORDER_EXTPARAM, cartCombineOderExtParam);
        if (jSONArray2 != null) {
            bundle.putString("SKU_IDS", jSONArray2.toString());
        } else {
            bundle.putString("SKU_IDS", new JSONArray().toString());
        }
        if (jSONArray != null) {
            bundle.putString(CartConstant.BUNDLE_KEY_CART_COMBINEORDER_FRESH_SKU_IDS, jSONArray.toString());
        } else {
            bundle.putString(CartConstant.BUNDLE_KEY_CART_COMBINEORDER_FRESH_SKU_IDS, new JSONArray().toString());
        }
        if (cartShopFareInfo != null) {
            bundle.putParcelable("freshSku", cartShopFareInfo);
        }
        if (cartShopFareInfo2 != null) {
            bundle.putParcelable("normalSku", cartShopFareInfo2);
        }
        if (cartCombineOderExtParam != null && CartBaseUtil.COMBINEORDER_USER_SETTLEMENT.equals(cartCombineOderExtParam.user)) {
            startCartCombineOrderForResult(iMyActivity.getThisActivity(), bundle, 1001);
        } else {
            startCartCombineOrder(iMyActivity.getThisActivity(), bundle);
        }
    }

    public static void startCartCombineOrder(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityDirect(context, HOST_CART_COMBINE_ORDER, bundle);
        }
    }

    public static void startCartCombineOrderForResult(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityForResult(activity, HOST_CART_COMBINE_ORDER, bundle, i2);
        }
    }

    public static void startCartGiftPool(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityDirect(context, "giftpoolactivity", bundle);
        }
    }

    public static void startCartMain(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityDirect(context, "shoppingcart", bundle);
        }
    }

    public static void startCartMainForResult(Activity activity, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityForResult(activity, "shoppingcart", bundle, i2);
        }
    }

    public static void startCartMainUseFlag(Context context, Bundle bundle, int i2) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivity(context, "shoppingcart", bundle, true, i2, false, "");
        }
    }

    public static void startCartTakeCoupon(Context context, Bundle bundle) {
        if (DeepLinkSwitch.getInstance().isSwitchOpen(AuraBundleInfos.getSwitchMaskFromBundleId(45))) {
            DeepLinkCommonHelper.startActivityDirect(context, HOST_CART_TAKE_COUPON, bundle);
        }
    }
}
