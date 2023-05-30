package com.jingdong.common.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.PdFillOrderRefreshEvent;
import com.jingdong.common.entity.ProductFillOrderRefreshPageEntity;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class ProductDetailController {
    public static final int MAX_NOTICE_CLICK_COUNT = 100;
    public static final int NO_STOCK_ORDER = 1;
    public static final String PRODUCT_FILL_ORDER_ENTITY = "productFillOrderEntity";
    public static final String PRODUCT_FILL_ORDER_EVENT = "productFillOrderEvent";
    public static final String PRODUCT_GO_BIG_IMAGE_ENTITY = "productGoBigImageEntity";
    public static final int SAME_SKU = 3;
    public static final int SKU_NOTICE = 2;
    private static Map<String, String> extrasInfoMap = new ConcurrentHashMap();
    public static ProductDetailListener mListener;
    public static String mSkuId;
    public View lottieLoadingView;

    /* loaded from: classes5.dex */
    public interface ProductDetailListener {
        void onRefreshComplete(String str, Bundle bundle);
    }

    public static void btnClick(BaseActivity baseActivity, int i2, String str) {
        if (i2 == 1) {
            noStockOrder(baseActivity, str);
        } else if (i2 != 2) {
        } else {
            skuNotice(baseActivity, str);
        }
    }

    public static void clear() {
        Map<String, String> map = extrasInfoMap;
        if (map != null) {
            map.clear();
        }
        mSkuId = "";
        mListener = null;
    }

    public static String extrasInfo(String str) {
        return getExtrasInfoMap().get(str);
    }

    public static Map<String, String> getExtrasInfoMap() {
        if (extrasInfoMap == null) {
            extrasInfoMap = new ConcurrentHashMap();
        }
        return extrasInfoMap;
    }

    public static void goToBigImage(Activity activity, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        Intent intent = new Intent(activity, PdBigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", 0);
        bundle.putBoolean("fromBigImage", false);
        bundle.putBoolean("pureMode", true);
        bundle.putStringArrayList("image_show_list_url", arrayList);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    private static void noStockOrder(BaseActivity baseActivity, String str) {
        PdFillOrderRefreshEvent pdFillOrderRefreshEvent = new PdFillOrderRefreshEvent(DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_NO_STOCK_ORDER, DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_NO_STOCK_ORDER);
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str);
        ProductFillOrderRefreshPageEntity productFillOrderRefreshPageEntity = new ProductFillOrderRefreshPageEntity();
        productFillOrderRefreshPageEntity.skuId = str;
        bundle.putSerializable(PRODUCT_FILL_ORDER_ENTITY, productFillOrderRefreshPageEntity);
        pdFillOrderRefreshEvent.setBundle(bundle);
        EventBus.getDefault().post(pdFillOrderRefreshEvent);
    }

    public static void refreshProductDetailCallBack(ProductFillOrderRefreshPageEntity productFillOrderRefreshPageEntity, Bundle bundle) {
        ProductDetailListener productDetailListener;
        if (productFillOrderRefreshPageEntity == null || !TextUtils.equals(productFillOrderRefreshPageEntity.skuId, mSkuId) || (productDetailListener = mListener) == null) {
            return;
        }
        productDetailListener.onRefreshComplete(productFillOrderRefreshPageEntity.jsonStr, bundle);
    }

    private static void skuNotice(BaseActivity baseActivity, String str) {
        PdFillOrderRefreshEvent pdFillOrderRefreshEvent = new PdFillOrderRefreshEvent(DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_SKU_NOTICE, DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_SKU_NOTICE);
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str);
        ProductFillOrderRefreshPageEntity productFillOrderRefreshPageEntity = new ProductFillOrderRefreshPageEntity();
        productFillOrderRefreshPageEntity.skuId = str;
        bundle.putSerializable(PRODUCT_FILL_ORDER_ENTITY, productFillOrderRefreshPageEntity);
        pdFillOrderRefreshEvent.setBundle(bundle);
        EventBus.getDefault().post(pdFillOrderRefreshEvent);
    }

    public void refreshProductDetail(String str, ProductDetailListener productDetailListener) {
        mSkuId = str;
        mListener = productDetailListener;
        PdFillOrderRefreshEvent pdFillOrderRefreshEvent = new PdFillOrderRefreshEvent(DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_REFRESH_FROM_ORDER, DeeplinkProductDetailHelper.HOST_PRODUCT_DETAIL_REFRESH_FROM_ORDER);
        Bundle bundle = new Bundle();
        bundle.putString("skuId", str);
        ProductFillOrderRefreshPageEntity productFillOrderRefreshPageEntity = new ProductFillOrderRefreshPageEntity();
        productFillOrderRefreshPageEntity.skuId = str;
        productFillOrderRefreshPageEntity.listener = productDetailListener;
        bundle.putSerializable(PRODUCT_FILL_ORDER_ENTITY, productFillOrderRefreshPageEntity);
        pdFillOrderRefreshEvent.setBundle(bundle);
        EventBus.getDefault().post(pdFillOrderRefreshEvent);
    }
}
