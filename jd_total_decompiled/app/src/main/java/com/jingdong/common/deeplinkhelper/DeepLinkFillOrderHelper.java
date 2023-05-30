package com.jingdong.common.deeplinkhelper;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.entity.cart.SubmitOrderProductInfo;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.entity.settlement.NewFillOrderConstant;
import com.jingdong.common.unification.router.CallBackListener;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class DeepLinkFillOrderHelper {
    public static final String FROM_KEY = "fromKey";
    public static final String FROM_SHOPPINGCART = "fromShoppingCart";
    public static final String LIVE_SOURCE_PT_KEY = "liveSourcePt";
    public static final String ORDERTYPE_KEY = "orderType";
    public static final int SOURCETYPE_JD170 = 101;
    public static final String SOURCETYPE_KEY = "sourceType";
    public static final int SOURCETYPE_OPENAPP = 100;
    public static final int SOURCETYPE_ORDERLIST = 3;
    public static final int SOURCETYPE_PRODUCTDETAIL = 2;
    public static final int SOURCETYPE_SHOPPINGCART = 1;
    private static final String TAG = "DeepLinkFillOrderHelper";
    public static final String WAREID_KEY = "wareId";
    public static final String WARENUM_KEY = "wareNum";

    /* renamed from: com.jingdong.common.deeplinkhelper.DeepLinkFillOrderHelper$1 */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$entity$settlement$FillOrder;

        static {
            int[] iArr = new int[FillOrder.values().length];
            $SwitchMap$com$jingdong$common$entity$settlement$FillOrder = iArr;
            try {
                iArr[FillOrder.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.JDWORLDWIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.GIFTCARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.GIFTBUY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.PRESALE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.LOUSBUY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.ISREGULARBUY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.JDWORLDWIDE_LOUSBUY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$jingdong$common$entity$settlement$FillOrder[FillOrder.JDWORLDWIDE_PRESALE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        final String SCHEME = "router://JDCheckoutModule/show?";
        final StringBuilder builder = new StringBuilder("router://JDCheckoutModule/show?");
        private boolean isFirst = true;

        public static Builder create() {
            return new Builder();
        }

        public Builder put(String str, Object obj) {
            if (!this.isFirst) {
                this.builder.append(ContainerUtils.FIELD_DELIMITER);
            }
            this.isFirst = false;
            this.builder.append(str + ContainerUtils.KEY_VALUE_DELIMITER + obj);
            return this;
        }

        public String toURL() {
            if (OKLog.D) {
                OKLog.d(DeepLinkFillOrderHelper.TAG, "\u7ed3\u7b97JDRouterURL\uff1a" + this.builder.toString());
            }
            return this.builder.toString();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Bundle creatFillOrderBundle(String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i4, int i5, int i6, String str5, int i7, long j2, String str6, FillOrder fillOrder, int i8, int i9, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("sourceType", i8);
        bundle2.putBoolean("fromDeepLink", true);
        bundle2.putString("wareId", str);
        bundle2.putInt("wareNum", i2);
        bundle2.putString(NewFillOrderConstant.INTENT_EXTRA_CARTSTR, str2);
        if (!TextUtils.isEmpty(str4)) {
            bundle2.putString(CartConstant.KEY_DELIVERYID, str4);
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            bundle2.putStringArrayList("3cGiftPoolsId", arrayList);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            bundle2.putStringArrayList("ybList", arrayList2);
        }
        bundle2.putInt("skuSource", i9);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        switch (AnonymousClass1.$SwitchMap$com$jingdong$common$entity$settlement$FillOrder[fillOrder.ordinal()]) {
            case 2:
                bundle2.putBoolean("isSpecial", true);
                break;
            case 3:
                bundle2.putBoolean("solidCard", true);
                break;
            case 4:
                bundle2.putBoolean("giftBuy", true);
                bundle2.putInt(NewFillOrderConstant.GIFT_COUNT, i2);
                break;
            case 5:
                bundle2.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
                bundle2.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, TextUtils.isEmpty(str2) ? getCartStr(str, i2).toString() : str2);
                break;
            case 6:
                bundle2.putBoolean("isIousBuy", true);
                bundle2.putInt("baiTiaoNum", i3);
                bundle2.putString(NewFillOrderConstant.RATE, str3);
                break;
            case 7:
                bundle2.putBoolean("isRegularBuy", true);
                bundle2.putInt("sendCycle", i4);
                bundle2.putInt("sectionNum", i5);
                bundle2.putInt("buyNum", i6);
                bundle2.putString("isTimeOrder", str5);
                bundle2.putInt("buyRate", i7);
                bundle2.putLong("startDate", j2);
                bundle2.putString("promiseMsg", str6);
                break;
            case 8:
                bundle2.putBoolean("isSpecial", true);
                bundle2.putBoolean("isIousBuy", true);
                bundle2.putInt("baiTiaoNum", i3);
                bundle2.putString(NewFillOrderConstant.RATE, str3);
                bundle2.putBoolean("isSpecial", true);
                break;
            case 9:
                bundle2.putBoolean("isSpecialPresale", true);
                bundle2.putBoolean("isSpecial", true);
                bundle2.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
                bundle2.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, TextUtils.isEmpty(str2) ? getCartStr(str, i2).toString() : str2);
                break;
        }
        return bundle2;
    }

    private static void dealBundle(Bundle bundle) {
        if (FROM_SHOPPINGCART.equals(bundle.getString(FROM_KEY))) {
            bundle.putBoolean("fromDeepLink", false);
        } else {
            bundle.putBoolean("fromDeepLink", true);
        }
        String string = bundle.getString("wareId");
        int i2 = bundle.getInt("wareNum");
        int i3 = bundle.getInt("orderType");
        if (i3 == 1) {
            bundle.putBoolean("isSpecial", true);
        } else if (i3 == 2) {
            bundle.putBoolean("solidCard", true);
        } else if (i3 == 3) {
            bundle.putBoolean("giftBuy", true);
            bundle.putInt(NewFillOrderConstant.GIFT_COUNT, i2);
        } else if (i3 == 4) {
            bundle.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
            String string2 = bundle.getString(NewFillOrderConstant.INTENT_EXTRA_CARTSTR);
            if (TextUtils.isEmpty(string2)) {
                string2 = getCartStr(string, i2).toString();
            }
            bundle.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, string2);
        } else if (i3 != 8) {
        } else {
            bundle.putBoolean("isSpecialPresale", true);
            bundle.putBoolean("isSpecial", true);
            bundle.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
            String string3 = bundle.getString(NewFillOrderConstant.INTENT_EXTRA_CARTSTR);
            if (TextUtils.isEmpty(string3)) {
                string3 = getCartStr(string, i2).toString();
            }
            bundle.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, string3);
        }
    }

    private static JSONObject getCartStr(String str, int i2) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("Id", str);
            jSONObject2.put("num", i2 + "");
            jSONArray.put(jSONObject2);
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
        return jSONObject;
    }

    public static String getFillOrderURL(String str, int i2, FillOrder fillOrder, int i3) {
        return Builder.create().put("wareId", str).put("wareNum", Integer.valueOf(i2)).put("orderType", Integer.valueOf(getOrderType(fillOrder))).put("sourceType", Integer.valueOf(i3)).toURL();
    }

    public static int getOrderType(FillOrder fillOrder) {
        switch (AnonymousClass1.$SwitchMap$com$jingdong$common$entity$settlement$FillOrder[fillOrder.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            case 7:
                return 6;
            case 8:
                return 7;
            case 9:
                return 8;
            default:
                return 0;
        }
    }

    public static void openFillOrderFromCart(Context context, SubmitOrderProductInfo submitOrderProductInfo, String str, String str2, int i2, boolean z, int i3, CallBackListener callBackListener) throws Exception {
        JDRouter.build(context, Builder.create().put("cartAllSelectedSkuids", str).put("OTCSkuList", str2).put("otcSkuNum", Integer.valueOf(i2)).put("orderType", Integer.valueOf(z ? 1 : 0)).put("sourceType", Integer.valueOf(i3)).put(FROM_KEY, FROM_SHOPPINGCART).toURL()).extraObject(NewFillOrderConstant.INTENT_EXTRA_SELECTED_CART, submitOrderProductInfo).callBackListener(callBackListener).open();
    }

    public static void startCompleteOrderListActivity(Context context, Bundle bundle) {
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_COMPLETE_ORDER_LIST_ACTIVITY, bundle);
    }

    @Deprecated
    public static void startCompleteorderactivity(Context context, Bundle bundle) {
        DeepLinkOrderCenterHelper.startOrderList(context);
    }

    public static void startFillOrder(Context context, Bundle bundle) {
        if (bundle != null) {
            dealBundle(bundle);
        }
        if (bundle != null ? bundle.getBoolean("isPopupOrder", false) : false) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_POPUPNEWFILLORDER_ACTIVITY, bundle);
        } else {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_NEWFILLORDER_ACTIVITY, bundle);
        }
    }

    public static void startPopupFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3, int i4, Bundle bundle) {
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        bundle2.putBoolean("isPopupOrder", true);
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, i3, i4, bundle2);
    }

    public static void startFillOrder(Context context, Bundle bundle, int i2) {
        if (bundle != null) {
            dealBundle(bundle);
        }
        DeepLinkCommonHelper.startActivity(context, DeepLinkCommonHelper.HOST_NEWFILLORDER_ACTIVITY, bundle, true, i2, false, "");
    }

    public static void startFillOrder(Context context, String str, int i2, int i3) {
        startFillOrder(context, str, i2, FillOrder.NORMAL, i3);
    }

    public static void startFillOrder(Context context, String str, int i2, FillOrder fillOrder, int i3) {
        startFillOrder(context, str, i2, "", fillOrder, i3);
    }

    public static void startFillOrder(Context context, String str, int i2, FillOrder fillOrder, int i3, Bundle bundle) {
        startFillOrder(context, str, i2, "", fillOrder, i3, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, FillOrder fillOrder, int i3) {
        startFillOrder(context, str, i2, str2, 0, "", "", (ArrayList<String>) null, (ArrayList<String>) null, fillOrder, i3);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, FillOrder fillOrder, int i3, Bundle bundle) {
        startFillOrder(context, str, i2, str2, 0, "", "", (ArrayList<String>) null, (ArrayList<String>) null, fillOrder, i3, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, int i3, int i4, String str2, int i5, long j2, String str3, FillOrder fillOrder, int i6) {
        startFillOrder(context, str, 0, "", 0, "", "", null, null, i2, i3, i4, str2, i5, j2, str3, fillOrder, i6, 0);
    }

    public static void startFillOrder(Context context, String str, int i2, int i3, int i4, String str2, int i5, long j2, String str3, FillOrder fillOrder, int i6, Bundle bundle) {
        startFillOrder(context, str, 0, "", 0, "", "", null, null, i2, i3, i4, str2, i5, j2, str3, fillOrder, i6, 0, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3) {
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, arrayList2, fillOrder, i3);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3, Bundle bundle) {
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, arrayList2, fillOrder, i3, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3, int i4) {
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, i3, i4);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i3, int i4, Bundle bundle) {
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, i3, i4, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i4) {
        startFillOrder(context, str, i2, str2, i3, str3, str4, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, i4, 0);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, FillOrder fillOrder, int i4, Bundle bundle) {
        startFillOrder(context, str, i2, str2, i3, str3, str4, arrayList, arrayList2, 0, 0, 0, "", 0, 0L, "", fillOrder, i4, 0, bundle);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i4, int i5, int i6, String str5, int i7, long j2, String str6, FillOrder fillOrder, int i8, int i9) {
        Bundle bundle = new Bundle();
        bundle.putInt("sourceType", i8);
        bundle.putBoolean("fromDeepLink", true);
        bundle.putString("wareId", str);
        bundle.putInt("wareNum", i2);
        bundle.putString(NewFillOrderConstant.INTENT_EXTRA_CARTSTR, str2);
        if (!TextUtils.isEmpty(str4)) {
            bundle.putString(CartConstant.KEY_DELIVERYID, str4);
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            bundle.putStringArrayList("3cGiftPoolsId", arrayList);
        }
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            bundle.putStringArrayList("ybList", arrayList2);
        }
        bundle.putInt("skuSource", i9);
        switch (AnonymousClass1.$SwitchMap$com$jingdong$common$entity$settlement$FillOrder[fillOrder.ordinal()]) {
            case 2:
                bundle.putBoolean("isSpecial", true);
                break;
            case 3:
                bundle.putBoolean("solidCard", true);
                break;
            case 4:
                bundle.putBoolean("giftBuy", true);
                bundle.putInt(NewFillOrderConstant.GIFT_COUNT, i2);
                break;
            case 5:
                bundle.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
                bundle.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, TextUtils.isEmpty(str2) ? getCartStr(str, i2).toString() : str2);
                break;
            case 6:
                bundle.putBoolean("isIousBuy", true);
                bundle.putInt("baiTiaoNum", i3);
                bundle.putString(NewFillOrderConstant.RATE, str3);
                break;
            case 7:
                bundle.putBoolean("isRegularBuy", true);
                bundle.putInt("sendCycle", i4);
                bundle.putInt("sectionNum", i5);
                bundle.putInt("buyNum", i6);
                bundle.putString("isTimeOrder", str5);
                bundle.putInt("buyRate", i7);
                bundle.putLong("startDate", j2);
                bundle.putString("promiseMsg", str6);
                break;
            case 8:
                bundle.putBoolean("isSpecial", true);
                bundle.putBoolean("isIousBuy", true);
                bundle.putInt("baiTiaoNum", i3);
                bundle.putString(NewFillOrderConstant.RATE, str3);
                bundle.putBoolean("isSpecial", true);
                break;
            case 9:
                bundle.putBoolean("isSpecialPresale", true);
                bundle.putBoolean("isSpecial", true);
                bundle.putBoolean(NewFillOrderConstant.INTENT_EXTRA_IS_PRESALE, true);
                bundle.putString(NewFillOrderConstant.INTENT_EXTRA_PRESALE_PRODUCT_ID, TextUtils.isEmpty(str2) ? getCartStr(str, i2).toString() : str2);
                break;
        }
        DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_NEWFILLORDER_ACTIVITY, bundle);
    }

    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i4, int i5, int i6, String str5, int i7, long j2, String str6, FillOrder fillOrder, int i8, int i9, Bundle bundle) {
        Bundle creatFillOrderBundle = creatFillOrderBundle(str, i2, str2, i3, str3, str4, arrayList, arrayList2, i4, i5, i6, str5, i7, j2, str6, fillOrder, i8, i9, bundle);
        if (bundle != null ? bundle.getBoolean("isPopupOrder", false) : false) {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_POPUPNEWFILLORDER_ACTIVITY, creatFillOrderBundle);
        } else {
            DeepLinkCommonHelper.startActivityDirect(context, DeepLinkCommonHelper.HOST_NEWFILLORDER_ACTIVITY, creatFillOrderBundle);
        }
    }

    @Deprecated
    public static void startFillOrder(Context context, String str, int i2, String str2, int i3, String str3, String str4, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str5, FillOrder fillOrder, int i4) {
        startFillOrder(context, str, i2, str2, i3, str3, str4, arrayList, arrayList2, fillOrder, i4);
    }

    @Deprecated
    public static void startFillOrder(Context context, String str, int i2, String str2, ArrayList<String> arrayList, FillOrder fillOrder, int i3) {
        startFillOrder(context, str, i2, "", 0, "", str2, arrayList, (ArrayList<String>) null, fillOrder, i3);
    }
}
