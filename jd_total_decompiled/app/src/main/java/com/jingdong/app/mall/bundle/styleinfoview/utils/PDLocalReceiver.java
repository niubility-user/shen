package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.events.PDViewEvent;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import jpbury.t;

/* loaded from: classes3.dex */
public class PDLocalReceiver extends BroadcastReceiver {
    public static final String INTENT_ACTION_ADD_CAR = "com.jingdong.productActivity.INTENT_ACTION_ADD_CAR";
    public static final String INTENT_ACTION_CAR_BUNDLING_SALE = "com.jingdong.productActivity.ACTION_CAR_BUNDLINGSALE_INFO";
    public static final String INTENT_ACTION_HAGGALE_PRICE = "com.jingdong.productActivity.INTENT_ACTION_HAGGALE_PRICE";
    public static final String INTENT_ACTION_LOC = "com.jingdong.productActivity.ACTION_LOC_INFO";
    public static final String INTENT_ACTION_OVERSEA_WHITEBAR_BUY = "productDetailAuthAction";
    public static final String INTENT_ACTION_PLUS_LINKIST_MEMBER = "com.jingdong.productActivity.ACTION_OPEN_PLUS_LINKIST_MEMBER";
    public static final String INTENT_ACTION_PLUS_MEMBER = "com.jingdong.cashierAction.payResult";
    public static final String INTENT_ACTION_REAL_NAME_CERTIFICATION = "com.jingdong.productActivity.ACTION_REAL_NAME_CERTIFICATION";
    public static final String INTENT_ACTION_SHOES_SIZE_HELPER = "com.jd.lib.feetmeasuerement.feetresult";
    public static final String INTENT_ACTION_SHOP_VIP = "com.jingdong.productActivity.INTENT_ACTION_SHOP_VIP";
    public static final String INTENT_ACTION_SIZE_HELPER = "com.jingdong.productActivity.ACTION_SIZE_HELPER_INFO";
    public static final String INTENT_ACTION_UPDATE_CAR = "com.jingdong.productActivity.INTENT_ACTION_UPDATE_CAR";
    public static final String INTENT_ACTION_ZERO_EPSON = "com.jingdong.productActivity.INTENT_ACTION_ZERO_EPSON";
    private PDManager mDetailManager;
    private ProductDetailEntity mProduct;

    public PDLocalReceiver(ProductDetailEntity productDetailEntity) {
        this.mProduct = productDetailEntity;
        this.mDetailManager = PDManager.getInstances(productDetailEntity.mManageKey);
    }

    private void fromPdToJieSuan() {
        PDManager pDManager = this.mDetailManager;
        if (pDManager != null) {
            pDManager.post("pd_ProductDetailActivity", "pd_action_form_product_to_jiesuan_epson");
        }
    }

    private void postReceiverData(Intent intent, String str) {
        String stringExtra = intent.getStringExtra("key");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        try {
            JDJSONObject parseObject = JDJSON.parseObject(stringExtra);
            if (parseObject != null) {
                PDManager.getEventBus().post(new PDViewEvent(str, parseObject, this.mProduct.mManageKey));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    private void requestNewProduct(String str) {
        new Bundle().putString("key", str);
        this.mDetailManager.post("pd_ProductDetailActivity", "pd_PDTopViewrefreshProduct");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        if (TextUtils.equals(INTENT_ACTION_CAR_BUNDLING_SALE, intent.getAction())) {
            postReceiverData(intent, PDViewEvent.ACTION_EVENT_RECEIVRE_CAR_BUNDLING_SALE);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_LOC_INFO", intent.getAction())) {
            postReceiverData(intent, "pd_action_receiver_loc");
        } else if (TextUtils.equals("com.jingdong.cashierAction.payResult", intent.getAction())) {
            String stringExtra = intent.getStringExtra("productPayResult");
            if (TextUtils.isEmpty(stringExtra) || !TextUtils.equals(JumpUtil.VAULE_DES_REACTNATIVE_PAYSUCCESS, stringExtra)) {
                return;
            }
            if (Log.D) {
                Log.d("OpenPlusSuccessReceiver", "key = " + stringExtra);
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_OPEN_PLUS_LINKIST_MEMBER", intent.getAction())) {
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_REAL_NAME_CERTIFICATION", intent.getAction())) {
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_SIZE_HELPER_INFO", intent.getAction())) {
            postReceiverData(intent, "pd_action_receiver_size_helper");
        } else if (TextUtils.equals("com.jd.lib.feetmeasuerement.feetresult", intent.getAction())) {
            postReceiverData(intent, "pd_action_receiver_shoes_size_helper");
        } else if (TextUtils.equals("productDetailAuthAction", intent.getAction())) {
            PDManager pDManager = this.mDetailManager;
            if (pDManager != null) {
                pDManager.post("pd_ProductDetailActivity", "pd_PDWhiteBar_Buy", "none");
            }
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_UPDATE_CAR", intent.getAction())) {
            postReceiverData(intent, "action_event_update_car");
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_ADD_CAR", intent.getAction())) {
            postReceiverData(intent, "action_event_add_car");
        } else if (TextUtils.equals(INTENT_ACTION_HAGGALE_PRICE, intent.getAction())) {
            postReceiverData(intent, PDViewEvent.ACTION_EVENT_HAGGALE_PRICE);
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_ZERO_EPSON", intent.getAction())) {
            fromPdToJieSuan();
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_SHOP_VIP", intent.getAction())) {
            requestNewProduct(this.mProduct.skuId);
        }
    }
}
