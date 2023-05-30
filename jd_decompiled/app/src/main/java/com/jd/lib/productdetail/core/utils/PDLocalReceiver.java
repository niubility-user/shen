package com.jd.lib.productdetail.core.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.events.ActionConstants;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.corelib.utils.Log;
import jpbury.t;

/* loaded from: classes15.dex */
public class PDLocalReceiver extends BroadcastReceiver {
    public static final String INTENT_ACTION_ADD_CAR = "com.jingdong.productActivity.INTENT_ACTION_ADD_CAR";
    public static final String INTENT_ACTION_BUILD_USER_INFO_FINISH = "com.jingdong.productActivity.INTENT_ACTION_BUILD_USER_INFO_FINISH";
    public static final String INTENT_ACTION_BUILD_VISION_ARCHIVES_SAVE = "com.jingdong.productActivity.INTENT_ACTION_BUILD_VISION_ARCHIVES_SAVE";
    public static final String INTENT_ACTION_LOC = "com.jingdong.productActivity.ACTION_LOC_INFO";
    public static final String INTENT_ACTION_OVERSEA_WHITEBAR_BUY = "productDetailAuthAction";
    public static final String INTENT_ACTION_PLUS_LINKIST_MEMBER = "com.jingdong.productActivity.ACTION_OPEN_PLUS_LINKIST_MEMBER";
    public static final String INTENT_ACTION_PLUS_MEMBER = "com.jingdong.cashierAction.payResult";
    public static final String INTENT_ACTION_REAL_NAME_CERTIFICATION = "com.jingdong.productActivity.ACTION_REAL_NAME_CERTIFICATION";
    public static final String INTENT_ACTION_REFRESH_PRODUCTDETAIL = "com.jingdong.productActivity.ACTION_REFRESH_PRODUCTDETAIL";
    public static final String INTENT_ACTION_SHOES_SIZE_HELPER = "com.jd.lib.feetmeasuerement.feetresult";
    public static final String INTENT_ACTION_SHOP_VIP = "com.jingdong.productActivity.INTENT_ACTION_SHOP_VIP";
    public static final String INTENT_ACTION_SHOW_SERVICE_POP = "com.jingdong.productActivity.ACTION_PRODUCTDETAIL_SHOW_SERVICE_POP";
    public static final String INTENT_ACTION_SIZE_HELPER = "com.jingdong.productActivity.ACTION_SIZE_HELPER_INFO";
    public static final String INTENT_ACTION_STUDENT_CERT = "com.jingdong.productActivity.ACTION_STUDENT_CERTIFICATION";
    public static final String INTENT_ACTION_UPDATE_CAR = "com.jingdong.productActivity.INTENT_ACTION_UPDATE_CAR";
    public static final String INTENT_ACTION_WHITEBAR_NEWUSER_CERT = "com.jingdong.productActivity.ACTION_WHITEBAR_CERTIFICATION";
    public static final String INTENT_ACTION_ZERO_EPSON = "com.jingdong.productActivity.INTENT_ACTION_ZERO_EPSON";
    private static final String TAG = "PDLocalReceiver";
    private PDManager mDetailManager;
    private String mFilter;
    private ProductDetailEntity mProduct;

    public PDLocalReceiver(ProductDetailEntity productDetailEntity) {
        this(productDetailEntity, "");
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
                PDManager.getEventBus().post(new PDViewEvent(str + this.mFilter, parseObject, this.mProduct.mManageKey));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    private void requestNewProduct(String str) {
        requestNewProduct(str, null);
    }

    private void visionArchivesSave(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("key", str);
        PDManager pDManager = this.mDetailManager;
        if (pDManager != null) {
            pDManager.post("pd_ProductDetailActivity", ActionConstants.ACTION_EVENT_VISION_ARCHIVES_SAVE_SUCCESS, bundle);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ProductDetailEntity productDetailEntity;
        if (intent == null) {
            return;
        }
        if (TextUtils.equals("com.jingdong.productActivity.ACTION_LOC_INFO", intent.getAction())) {
            postReceiverData(intent, "pd_action_receiver_loc");
        } else if (TextUtils.equals("com.jingdong.cashierAction.payResult", intent.getAction())) {
            String stringExtra = intent.getStringExtra("productPayResult");
            if (TextUtils.isEmpty(stringExtra) || !TextUtils.equals(JumpUtil.VAULE_DES_REACTNATIVE_PAYSUCCESS, stringExtra)) {
                return;
            }
            if (Log.D) {
                Log.d("OpenPlusSuccessReceiver", "key = " + stringExtra);
            }
            ProductDetailEntity productDetailEntity2 = this.mProduct;
            if (productDetailEntity2 == null || TextUtils.isEmpty(productDetailEntity2.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_OPEN_PLUS_LINKIST_MEMBER", intent.getAction())) {
            ProductDetailEntity productDetailEntity3 = this.mProduct;
            if (productDetailEntity3 == null || TextUtils.isEmpty(productDetailEntity3.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals("com.jingdong.productActivity.ACTION_REAL_NAME_CERTIFICATION", intent.getAction())) {
            ProductDetailEntity productDetailEntity4 = this.mProduct;
            if (productDetailEntity4 == null || TextUtils.isEmpty(productDetailEntity4.skuId)) {
                return;
            }
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
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_ZERO_EPSON", intent.getAction())) {
            fromPdToJieSuan();
        } else if (TextUtils.equals("com.jingdong.productActivity.INTENT_ACTION_SHOP_VIP", intent.getAction())) {
            ProductDetailEntity productDetailEntity5 = this.mProduct;
            if (productDetailEntity5 == null || TextUtils.isEmpty(productDetailEntity5.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals(INTENT_ACTION_STUDENT_CERT, intent.getAction())) {
            ProductDetailEntity productDetailEntity6 = this.mProduct;
            if (productDetailEntity6 == null || TextUtils.isEmpty(productDetailEntity6.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals(INTENT_ACTION_WHITEBAR_NEWUSER_CERT, intent.getAction())) {
            ProductDetailEntity productDetailEntity7 = this.mProduct;
            if (productDetailEntity7 == null || TextUtils.isEmpty(productDetailEntity7.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals(INTENT_ACTION_BUILD_USER_INFO_FINISH, intent.getAction())) {
            ProductDetailEntity productDetailEntity8 = this.mProduct;
            if (productDetailEntity8 == null || TextUtils.isEmpty(productDetailEntity8.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals(INTENT_ACTION_REFRESH_PRODUCTDETAIL, intent.getAction())) {
            ProductDetailEntity productDetailEntity9 = this.mProduct;
            if (productDetailEntity9 == null || TextUtils.isEmpty(productDetailEntity9.skuId)) {
                return;
            }
            requestNewProduct(this.mProduct.skuId);
        } else if (TextUtils.equals(INTENT_ACTION_BUILD_VISION_ARCHIVES_SAVE, intent.getAction())) {
            ProductDetailEntity productDetailEntity10 = this.mProduct;
            if (productDetailEntity10 == null || TextUtils.isEmpty(productDetailEntity10.skuId)) {
                return;
            }
            visionArchivesSave(this.mProduct.skuId);
        } else if (TextUtils.equals(PairKey.CASHIER_BROADCAST_ACTION_FINISH_BTN_CLICK, intent.getAction())) {
            ProductDetailEntity productDetailEntity11 = this.mProduct;
            if (productDetailEntity11 == null || this.mDetailManager == null || !productDetailEntity11.isBoomer() || this.mProduct.isSdkOpen()) {
                return;
            }
            this.mDetailManager.post("pd_ProductDetailActivity", "pd_ProductDetailActivity_toFinish");
        } else if (!TextUtils.equals(INTENT_ACTION_SHOW_SERVICE_POP, intent.getAction()) || (productDetailEntity = this.mProduct) == null || TextUtils.isEmpty(productDetailEntity.skuId) || this.mDetailManager == null) {
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("key", this.mProduct.skuId);
            this.mDetailManager.post("pd_ProductDetailActivity", ActionConstants.ACTION_EVENT_SHOW_SERVICE_POP, bundle);
        }
    }

    public PDLocalReceiver(ProductDetailEntity productDetailEntity, String str) {
        this.mFilter = "";
        this.mProduct = productDetailEntity;
        this.mDetailManager = PDManager.getInstances(productDetailEntity.mManageKey);
        this.mFilter = str;
    }

    private void requestNewProduct(String str, String str2) {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("key1", str2);
        }
        PDManager pDManager = this.mDetailManager;
        if (pDManager != null) {
            pDManager.post("pd_ProductDetailActivity", "pd_PDTopViewrefreshProduct");
        }
    }
}
