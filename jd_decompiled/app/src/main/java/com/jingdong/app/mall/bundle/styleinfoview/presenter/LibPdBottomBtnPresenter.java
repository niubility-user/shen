package com.jingdong.app.mall.bundle.styleinfoview.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnClickListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCarListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDOperAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocShopInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdAcsEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBasicInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessABTestInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDLVProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDIsAppointProtocol;
import com.jingdong.app.mall.bundle.styleinfoview.yuyue.PDYuYueVerifyDialog;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.methodEntity.CartAddForPDEntity;
import com.jingdong.common.entity.cart.methodEntity.CartForRefreshPdEntity;
import com.jingdong.common.entity.cart.yanbao.CartResponseNewYBDetail;
import com.jingdong.common.entity.settlement.FillOrder;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class LibPdBottomBtnPresenter {
    public static final int REQUEST_CODE_PLUS_SWAYING = 38;
    private Activity mActivity;
    private LibPdStyleInfoViewBottomBtnClickListener mBottomBtnClickListener;
    private LibPdStyleInfoViewCarListener mCarListener;
    private IMyActivity mIMyActivity;
    private LibPdStyleInfoViewUtils mLibPdStyleInfoViewUtils;
    private ProductDetailEntity mProduct;

    public LibPdBottomBtnPresenter(ProductDetailEntity productDetailEntity, Activity activity, IMyActivity iMyActivity, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener, LibPdStyleInfoViewUtils libPdStyleInfoViewUtils) {
        this.mProduct = productDetailEntity;
        this.mActivity = activity;
        this.mIMyActivity = iMyActivity;
        this.mCarListener = libPdStyleInfoViewCarListener;
        this.mLibPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
        if (libPdStyleInfoViewUtils != null) {
            this.mBottomBtnClickListener = libPdStyleInfoViewUtils.mBottomBtnClickListener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLiveParam(JDJSONObject jDJSONObject) {
        ProductDetailEntity productDetailEntity;
        if (jDJSONObject == null || (productDetailEntity = this.mProduct) == null) {
            return;
        }
        jDJSONObject.put("businessOrigin", (Object) productDetailEntity.businessOrigin);
        jDJSONObject.put(VideoPerfEntity.FIELD_ROOM_ID, (Object) this.mProduct.roomId);
        jDJSONObject.put(LibPdStyleInfoViewUtils.EXTRA_SUPPER_ROOM_PROMO, (Object) this.mProduct.supperRoomPromo);
    }

    @Nullable
    private ArrayList<CartSkuSummary> cartSkuService() {
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity == null || !productDetailEntity.isSelectCarShop()) {
            return null;
        }
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        CartSkuSummary cartSkuSummary = new CartSkuSummary();
        cartSkuSummary.setLocId(this.mProduct.getStoreId());
        cartSkuSummary.setSkuId(this.mProduct.getServiceSku());
        cartSkuSummary.setNum(this.mProduct.getCarShopInfoCount());
        arrayList.add(cartSkuSummary);
        return arrayList;
    }

    private ArrayList<String> getFurnitureSelectIds() {
        List<WareBusinessFurnitureGroupEntity> fiInfo;
        List<WareBusinessFurnitureItemEntity> list;
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity == null || productDetailEntity.getWareFurnitureInfo() == null || (fiInfo = this.mProduct.getFiInfo()) == null || fiInfo.isEmpty()) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        for (WareBusinessFurnitureGroupEntity wareBusinessFurnitureGroupEntity : fiInfo) {
            if (wareBusinessFurnitureGroupEntity != null && (list = wareBusinessFurnitureGroupEntity.items) != null && !list.isEmpty()) {
                Iterator<WareBusinessFurnitureItemEntity> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        WareBusinessFurnitureItemEntity next = it.next();
                        if (next.isSelected) {
                            arrayList.add(String.valueOf(next.skuId));
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<CartResponseNewYBDetail> getSelectYanbao() {
        List<WareBusinessYanBaoGroupEntity> yanBaoList;
        List<WareBusinessYanBaoItemEntity> list;
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity == null || productDetailEntity.isYanbaoShield() || this.mProduct.getWareYanBaoEntity() == null || (yanBaoList = this.mProduct.getYanBaoList()) == null || yanBaoList.isEmpty()) {
            return null;
        }
        ArrayList<CartResponseNewYBDetail> arrayList = new ArrayList<>(0);
        for (WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity : yanBaoList) {
            if (wareBusinessYanBaoGroupEntity != null && (list = wareBusinessYanBaoGroupEntity.products) != null && !list.isEmpty()) {
                Iterator<WareBusinessYanBaoItemEntity> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        WareBusinessYanBaoItemEntity next = it.next();
                        if (next.isSelected) {
                            CartResponseNewYBDetail cartResponseNewYBDetail = new CartResponseNewYBDetail(null);
                            cartResponseNewYBDetail.setPlatformId(String.valueOf(next.platformPid));
                            arrayList.add(cartResponseNewYBDetail);
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private ArrayList<String> getServiceSelect() {
        List<WareBusinessJdServerProduct> list;
        ProductDetailEntity productDetailEntity = this.mProduct;
        ArrayList<String> arrayList = null;
        if (productDetailEntity != null && productDetailEntity.hasJdServerPlus()) {
            for (WareBusinessPlusListEntity wareBusinessPlusListEntity : this.mProduct.getJdSerPlusList()) {
                if (wareBusinessPlusListEntity != null && (list = wareBusinessPlusListEntity.products) != null && !list.isEmpty()) {
                    for (WareBusinessJdServerProduct wareBusinessJdServerProduct : wareBusinessPlusListEntity.products) {
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                        }
                        if (wareBusinessJdServerProduct != null && wareBusinessJdServerProduct.isSelected) {
                            arrayList.add(wareBusinessJdServerProduct.serviceSku);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isActivityFinish() {
        Activity activity = this.mActivity;
        return activity != null && activity.isFinishing();
    }

    private int isSelectAcsService(ProductDetailEntity productDetailEntity, ArrayList<CartResponseNewYBDetail> arrayList) {
        WareBusinessData wareBusinessData;
        PdAcsEntity pdAcsEntity;
        List<PdAcsEntity.AcsItem> list;
        if (arrayList != null && (wareBusinessData = productDetailEntity.mWareBusinessData) != null && (pdAcsEntity = wareBusinessData.acsModel) != null && (list = pdAcsEntity.acsList) != null) {
            if (list.size() > 0 && arrayList.size() > 0) {
                Iterator<CartResponseNewYBDetail> it = arrayList.iterator();
                while (it.hasNext()) {
                    CartResponseNewYBDetail next = it.next();
                    if (next != null) {
                        String platformId = next.getPlatformId();
                        for (PdAcsEntity.AcsItem acsItem : list) {
                            if (acsItem != null && TextUtils.equals(platformId, acsItem.platformPid)) {
                                productDetailEntity.appleCare = 0;
                                return 0;
                            }
                        }
                    }
                }
            }
            productDetailEntity.appleCare = 1;
        }
        return productDetailEntity.appleCare;
    }

    @NonNull
    private CartSkuSummary mainSkuSummary() {
        LocShopInfo locShopInfo;
        CartSkuSummary cartSkuSummary = new CartSkuSummary();
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity == null) {
            return cartSkuSummary;
        }
        cartSkuSummary.setSkuId(productDetailEntity.skuId);
        cartSkuSummary.setNum(this.mProduct.number);
        cartSkuSummary.setStoreId(this.mProduct.getDaojiaStoreId());
        cartSkuSummary.setDeliveryId(this.mProduct.deliveryId);
        cartSkuSummary.getExtFlag().dist = this.mProduct.dist;
        cartSkuSummary.getExtFlag().extMap.put("pt", this.mProduct.pt);
        cartSkuSummary.getExtFlag().extMap.put(CartConstant.KEY_CM, this.mProduct.mCurModelId);
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        String str = "";
        if (productDetailEntity2.selectItemInfo != null && productDetailEntity2.isSelectWhiteBar()) {
            cartSkuSummary.getExtFlag().extMap.put("cart_bt_planId", this.mProduct.selectItemInfo.btDefaultPlanId + "");
            cartSkuSummary.getExtFlag().extMap.put("cart_bt_couponId", this.mProduct.selectItemInfo.btCouponId);
            cartSkuSummary.getExtFlag().extMap.put("cart_bt_activityId", this.mProduct.selectItemInfo.btActivityId);
            cartSkuSummary.getExtFlag().extMap.put("cart_bt_couponType", this.mProduct.selectItemInfo.btCouponType);
        }
        if (this.mProduct.isShowHealthOnLine() && !TextUtils.isEmpty(this.mProduct.serviceInfoId)) {
            cartSkuSummary.getExtFlag().extMap.put("jkfw", this.mProduct.serviceInfoId);
        }
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData != null && (locShopInfo = wareBusinessData.locShopInfo) != null && !TextUtils.isEmpty(locShopInfo.locShopId)) {
            str = this.mProduct.mWareBusinessData.locShopInfo.locShopId;
        }
        cartSkuSummary.setLocId(str);
        cartSkuSummary.setFsSkuIds(getFurnitureSelectIds());
        Boolean bool = this.mProduct.jpsCheckStatus;
        if (bool != null && bool.booleanValue()) {
            cartSkuSummary.getExtFlag().jps = "1";
        }
        if (!TextUtils.isEmpty(this.mProduct.roomId)) {
            cartSkuSummary.getExtFlag().extMap.put(VideoPerfEntity.FIELD_ROOM_ID, this.mProduct.roomId);
        }
        if (!TextUtils.isEmpty(this.mProduct.businessOrigin)) {
            cartSkuSummary.getExtFlag().extMap.put("addChannel", this.mProduct.businessOrigin);
        }
        if (!TextUtils.isEmpty(this.mProduct.supperRoomPromo)) {
            cartSkuSummary.getExtFlag().extMap.put(LibPdStyleInfoViewUtils.EXTRA_SUPPER_ROOM_PROMO, this.mProduct.supperRoomPromo);
        }
        return cartSkuSummary;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doYuyue(final com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener r19) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.doYuyue(com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener):void");
    }

    public void onCarClick() {
        onCarClick(false);
    }

    public void onNowBuyClick() {
        onNowBuyClick(-1);
    }

    public void onRecommendStartProductDetailActivity(Activity activity, ProductDetailEntity productDetailEntity) {
        List<WareBusinessWareImageEntity> list;
        WareBusinessWareImageEntity wareBusinessWareImageEntity;
        WarePropertyInfo warePropertyInfo;
        WareBasicInfo wareBasicInfo;
        if (productDetailEntity == null || activity == null) {
            return;
        }
        Bundle bundle = new Bundle();
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        if (wareBusinessData != null && (wareBasicInfo = wareBusinessData.wareInfo) != null) {
            bundle.putString("id", wareBasicInfo.skuId);
            bundle.putString(PDConstant.EXTRA_CSKU, productDetailEntity.mWareBusinessData.wareInfo.skuId);
        }
        bundle.putString("title", productDetailEntity.name);
        WareBusinessData wareBusinessData2 = productDetailEntity.mWareBusinessData;
        if (wareBusinessData2 != null && (warePropertyInfo = wareBusinessData2.property) != null && !TextUtils.isEmpty(warePropertyInfo.bbtf)) {
            bundle.putString(LibPdStyleInfoViewUtils.BBTF, productDetailEntity.mWareBusinessData.property.bbtf);
        }
        bundle.putString("price", productDetailEntity.getJdPrice());
        bundle.putString("sourceType", productDetailEntity.sourceType);
        WareBusinessData wareBusinessData3 = productDetailEntity.mWareBusinessData;
        if (wareBusinessData3 != null && (list = wareBusinessData3.wareImage) != null && !list.isEmpty() && (wareBusinessWareImageEntity = productDetailEntity.mWareBusinessData.wareImage.get(0)) != null) {
            bundle.putString("image", wareBusinessWareImageEntity.big);
            String str = wareBusinessWareImageEntity.big;
        }
        DeeplinkProductDetailHelper.startProductDetail(activity, bundle);
    }

    public void queryLv(final String str, final String str2, final String str3, final LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        LoginUserHelper.getInstance().executeLoginRunnable(this.mIMyActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.7
            @Override // java.lang.Runnable
            public void run() {
                if (LibPdBottomBtnPresenter.this.mProduct == null) {
                    return;
                }
                PDLVProtocol pDLVProtocol = new PDLVProtocol(LibPdBottomBtnPresenter.this.mProduct.mManageKey);
                PdLVBody.Builder builder = new PdLVBody.Builder();
                builder.add("skuId", LibPdBottomBtnPresenter.this.mProduct.skuId).add("ft", str).add("comefrom", str2).add("number", String.valueOf(LibPdBottomBtnPresenter.this.mProduct.number)).add("murl", str3);
                pDLVProtocol.setViewYuYueListener(libPdStyleInfoViewYuYueListener);
                pDLVProtocol.setInputParams(builder.getJsonValue());
                if (LibPdBottomBtnPresenter.this.mActivity instanceof BaseActivity) {
                    ((BaseActivity) LibPdBottomBtnPresenter.this.mActivity).addHttpGroupWithNPSSetting(pDLVProtocol.request());
                }
            }
        });
    }

    public void queryLvForYuyueCar(final String str, final String str2, final String str3, final LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        LoginUserHelper.getInstance().executeLoginRunnable(this.mIMyActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.6
            @Override // java.lang.Runnable
            public void run() {
                if (LibPdBottomBtnPresenter.this.mProduct == null) {
                    return;
                }
                PDLVProtocol pDLVProtocol = new PDLVProtocol(LibPdBottomBtnPresenter.this.mProduct.mManageKey);
                PdLVBody.Builder builder = new PdLVBody.Builder();
                builder.add("skuId", LibPdBottomBtnPresenter.this.mProduct.skuId).add("ft", str).add("comefrom", str2).add("number", String.valueOf(LibPdBottomBtnPresenter.this.mProduct.number)).add("locShopId", str3);
                if (TextUtils.equals(str2, "customize")) {
                    StringBuilder sb = new StringBuilder();
                    if (LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect != null && !LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect.isEmpty()) {
                        for (int i2 = 0; i2 < LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect.size(); i2++) {
                            sb.append(LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect.get(i2));
                            if (i2 != LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect.size() - 1) {
                                sb.append(DYConstants.DY_REGEX_COMMA);
                            }
                        }
                    }
                    if (LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect != null && !LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect.isEmpty()) {
                        if (sb.length() > 0) {
                            sb.append(DYConstants.DY_REGEX_COMMA);
                        }
                        for (int i3 = 0; i3 < LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect.size(); i3++) {
                            sb.append(LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect.get(i3));
                            if (i3 != LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect.size() - 1) {
                                sb.append(DYConstants.DY_REGEX_COMMA);
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        builder.add("ybServiceId", sb.toString());
                    }
                }
                pDLVProtocol.setViewYuYueListener(libPdStyleInfoViewYuYueListener);
                pDLVProtocol.setInputParams(builder.getJsonValue());
                if (LibPdBottomBtnPresenter.this.mActivity instanceof BaseActivity) {
                    ((BaseActivity) LibPdBottomBtnPresenter.this.mActivity).addHttpGroupWithNPSSetting(pDLVProtocol.request());
                }
            }
        });
    }

    public void qureyIsAppoint(final boolean z, final LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        LoginUserHelper.getInstance().executeLoginRunnable(this.mIMyActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.5
            @Override // java.lang.Runnable
            public void run() {
                PDIsAppointProtocol pDIsAppointProtocol = new PDIsAppointProtocol(LibPdBottomBtnPresenter.this.mProduct.mManageKey);
                pDIsAppointProtocol.setViewYuYueListener(libPdStyleInfoViewYuYueListener);
                pDIsAppointProtocol.setInputParams(LibPdBottomBtnPresenter.this.mProduct.skuId, null);
                pDIsAppointProtocol.isNowBuy = z;
                if (LibPdBottomBtnPresenter.this.mActivity instanceof BaseActivity) {
                    ((BaseActivity) LibPdBottomBtnPresenter.this.mActivity).addHttpGroupWithNPSSetting(pDIsAppointProtocol.request());
                }
            }
        });
    }

    public void setBottomBtnClickListener(LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        if (libPdStyleInfoViewBottomBtnClickListener != null) {
            this.mBottomBtnClickListener = libPdStyleInfoViewBottomBtnClickListener;
        }
    }

    public void setProducHolderData(ProductDetailEntity productDetailEntity) {
        this.mProduct = productDetailEntity;
    }

    public void onCarClick(boolean z) {
        onCarClick(z, null, null);
    }

    public void onNowBuyClick(final int i2) {
        if (this.mActivity == null || this.mProduct == null || this.mIMyActivity == null) {
            return;
        }
        LoginUserHelper.getInstance().executeLoginRunnable(this.mIMyActivity, new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.2
            @Override // java.lang.Runnable
            public void run() {
                if (LibPdBottomBtnPresenter.this.mProduct == null) {
                    return;
                }
                int i3 = i2;
                if (i3 == -1) {
                    i3 = LibPdBottomBtnPresenter.this.mProduct.getSkuSource();
                }
                int i4 = i3;
                ArrayList arrayList = new ArrayList();
                if (LibPdBottomBtnPresenter.this.mProduct != null) {
                    LibPdBottomBtnPresenter.this.mProduct.getType4Entity();
                    if (LibPdBottomBtnPresenter.this.mProduct.mType4Entity != null && !TextUtils.isEmpty(LibPdBottomBtnPresenter.this.mProduct.mType4Entity.showMsg)) {
                        arrayList.add(LibPdBottomBtnPresenter.this.mProduct.getType4Entity());
                    }
                    if (LibPdBottomBtnPresenter.this.mProduct.mType5Entity != null && !TextUtils.isEmpty(LibPdBottomBtnPresenter.this.mProduct.mType5Entity.necessaryKey)) {
                        arrayList.add(LibPdBottomBtnPresenter.this.mProduct.mType5Entity);
                    }
                }
                String str = (LibPdBottomBtnPresenter.this.mProduct == null || LibPdBottomBtnPresenter.this.mProduct.mWareBusinessData == null || LibPdBottomBtnPresenter.this.mProduct.mWareBusinessData.property == null || TextUtils.isEmpty(LibPdBottomBtnPresenter.this.mProduct.mWareBusinessData.property.isOTC)) ? "" : LibPdBottomBtnPresenter.this.mProduct.mWareBusinessData.property.isOTC;
                JDJSONObject toOrderParam = LibPdBottomBtnPresenter.this.mProduct.getToOrderParam();
                if (LibPdBottomBtnPresenter.this.mProduct == null || !LibPdBottomBtnPresenter.this.mProduct.isOp()) {
                    if (LibPdBottomBtnPresenter.this.mProduct == null || LibPdBottomBtnPresenter.this.mProduct.jpsCheckStatus == null || !LibPdBottomBtnPresenter.this.mProduct.jpsCheckStatus.booleanValue()) {
                        if (LibPdBottomBtnPresenter.this.mProduct == null || !LibPdBottomBtnPresenter.this.mProduct.isSelectWhiteBar()) {
                            if (LibPdBottomBtnPresenter.this.mProduct != null) {
                                LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                                PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.NORMAL, i4, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                                return;
                            }
                            return;
                        }
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        try {
                            if (LibPdBottomBtnPresenter.this.mProduct.selectItemInfo != null) {
                                jDJSONObject.put("btDefaultPlanId", (Object) Integer.valueOf(LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btDefaultPlanId));
                                jDJSONObject.put("btCouponId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponId);
                                jDJSONObject.put("btActivityId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btActivityId);
                                jDJSONObject.put("btCouponType", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponType);
                            }
                            toOrderParam.put("balanceBizType", (Object) 16);
                            toOrderParam.put("balanceBizData", (Object) jDJSONObject);
                            LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            ExceptionReporter.reportExceptionToBugly(e2);
                        }
                        PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.NORMAL, i4, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                    } else if (!LibPdBottomBtnPresenter.this.mProduct.isSelectWhiteBar()) {
                        LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                        PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.NORMAL, i4 == 34 ? i4 : 12, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                    } else {
                        JDJSONObject jDJSONObject2 = new JDJSONObject();
                        try {
                            if (LibPdBottomBtnPresenter.this.mProduct.selectItemInfo != null) {
                                jDJSONObject2.put("btDefaultPlanId", (Object) Integer.valueOf(LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btDefaultPlanId));
                                jDJSONObject2.put("btCouponId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponId);
                                jDJSONObject2.put("btActivityId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btActivityId);
                                jDJSONObject2.put("btCouponType", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponType);
                            }
                            toOrderParam.put("balanceBizType", (Object) 16);
                            toOrderParam.put("balanceBizData", (Object) jDJSONObject2);
                            LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            ExceptionReporter.reportExceptionToBugly(e3);
                        }
                        PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.NORMAL, i4 == 34 ? i4 : 12, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                    }
                } else if (!LibPdBottomBtnPresenter.this.mProduct.isSelectWhiteBar()) {
                    LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                    PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.JDWORLDWIDE, i4 == 34 ? i4 : 6, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                } else {
                    JDJSONObject jDJSONObject3 = new JDJSONObject();
                    try {
                        if (LibPdBottomBtnPresenter.this.mProduct.selectItemInfo != null) {
                            jDJSONObject3.put("btDefaultPlanId", (Object) Integer.valueOf(LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btDefaultPlanId));
                            jDJSONObject3.put("btCouponId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponId);
                            jDJSONObject3.put("btActivityId", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btActivityId);
                            jDJSONObject3.put("btCouponType", (Object) LibPdBottomBtnPresenter.this.mProduct.selectItemInfo.btCouponType);
                        }
                        toOrderParam.put("balanceBizType", (Object) 16);
                        toOrderParam.put("balanceBizData", (Object) jDJSONObject3);
                        LibPdBottomBtnPresenter.this.addLiveParam(toOrderParam);
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        ExceptionReporter.reportExceptionToBugly(e4);
                    }
                    PDBaseDeepLinkHelper.startFillOrderWithRouter(LibPdBottomBtnPresenter.this.mActivity, toOrderParam.toJSONString(), LibPdBottomBtnPresenter.this.mProduct.skuId, LibPdBottomBtnPresenter.this.mProduct.number, LibPdBottomBtnPresenter.this.mProduct.deliveryId, LibPdBottomBtnPresenter.this.mProduct.giftPoolIdsSelect, LibPdBottomBtnPresenter.this.mProduct.getSelectedGiftItems(), LibPdBottomBtnPresenter.this.mProduct.yanbaoIdsSelect, LibPdBottomBtnPresenter.this.mProduct.jsServerPlusIdsSelect, FillOrder.JDWORLDWIDE, i4 == 34 ? i4 : 6, LibPdBottomBtnPresenter.this.mProduct.pt, arrayList, str, LibPdBottomBtnPresenter.this.mProduct.getAuthKey());
                }
            }
        });
    }

    public void onCarClick(boolean z, final PDOperAppointEntity pDOperAppointEntity, final PDYuYueVerifyDialog pDYuYueVerifyDialog) {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        if (this.mProduct == null) {
            return;
        }
        ArrayList<CartSkuSummary> cartSkuService = cartSkuService();
        CartSkuSummary mainSkuSummary = mainSkuSummary();
        this.mProduct.isOverseaWhiteBar = !TextUtils.isEmpty("");
        CartAddForPDEntity cartAddForPDEntity = new CartAddForPDEntity();
        cartAddForPDEntity.product = mainSkuSummary;
        cartAddForPDEntity.ybSelect = getSelectYanbao();
        cartAddForPDEntity.giftsSelect = this.mProduct.getSelectedGiftItems();
        cartAddForPDEntity.tiedSkus = cartSkuService;
        cartAddForPDEntity.refer = "";
        cartAddForPDEntity.source = "shangxiang";
        cartAddForPDEntity.skuTag = this.mProduct.getSkuTag();
        cartAddForPDEntity.isHiddenErrorToast = this.mProduct.isHideAdd2CardErrorToast();
        cartAddForPDEntity.serviceSelect = getServiceSelect();
        cartAddForPDEntity.contractPhoneSource = null;
        ProductDetailEntity productDetailEntity = this.mProduct;
        WareBusinessData wareBusinessData = productDetailEntity.mWareBusinessData;
        if ((wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.recommendPopup) ? false : true) {
            cartAddForPDEntity.appleCare = isSelectAcsService(productDetailEntity, cartAddForPDEntity.ybSelect);
        } else {
            cartAddForPDEntity.appleCare = 0;
        }
        cartAddForPDEntity.isHiddenOkToast = true;
        cartAddForPDEntity.isHandleSuccess = false;
        cartAddForPDEntity.isHiddenErrorToast = true;
        cartAddForPDEntity.myActivity = this.mIMyActivity;
        cartAddForPDEntity.pdShoppingCartlistener = new ShoppingBaseController.PDShoppingCartListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.presenter.LibPdBottomBtnPresenter.1
            @Override // com.jingdong.common.controller.ShoppingBaseController.PDShoppingCartListener
            public void dismissDlg() {
                if (LibPdBottomBtnPresenter.this.isActivityFinish() || LibPdBottomBtnPresenter.this.mProduct == null) {
                    return;
                }
                if (LibPdBottomBtnPresenter.this.mCarListener != null) {
                    LibPdBottomBtnPresenter.this.mCarListener.libPdDismissDlg();
                }
                if (LibPdBottomBtnPresenter.this.mBottomBtnClickListener != null) {
                    LibPdBottomBtnPresenter.this.mBottomBtnClickListener.libPdBottomDismissDlg();
                }
                if (LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils != null) {
                    LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils.dismiss();
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.PDShoppingCartListener
            public void dismissGuideToast() {
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onEnd(CartResponse cartResponse) {
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onError(String str) {
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
            public void onReady() {
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.PDShoppingCartListener
            public void refreshPDView(CartForRefreshPdEntity cartForRefreshPdEntity) {
                PDOperAppointEntity pDOperAppointEntity2;
                if (LibPdBottomBtnPresenter.this.isActivityFinish() || LibPdBottomBtnPresenter.this.mProduct == null || cartForRefreshPdEntity == null) {
                    return;
                }
                if (LibPdBottomBtnPresenter.this.mCarListener != null) {
                    LibPdBottomBtnPresenter.this.mCarListener.libPdRefreshPdView(cartForRefreshPdEntity);
                }
                if (LibPdBottomBtnPresenter.this.mBottomBtnClickListener != null) {
                    LibPdBottomBtnPresenter.this.mBottomBtnClickListener.libPdBottomRefreshPDView(cartForRefreshPdEntity, null, false, null, false);
                }
                if (LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils != null) {
                    LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils.dismiss();
                }
                PDYuYueVerifyDialog pDYuYueVerifyDialog2 = pDYuYueVerifyDialog;
                if (pDYuYueVerifyDialog2 == null || (pDOperAppointEntity2 = pDOperAppointEntity) == null) {
                    return;
                }
                pDOperAppointEntity2.isAddCartSuccess = cartForRefreshPdEntity.isSuccess;
                pDYuYueVerifyDialog2.processOrderProduct(pDOperAppointEntity2);
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.PDShoppingCartListener
            public void showDlg() {
                if (LibPdBottomBtnPresenter.this.isActivityFinish() || LibPdBottomBtnPresenter.this.mProduct == null) {
                    return;
                }
                if (LibPdBottomBtnPresenter.this.mCarListener != null) {
                    LibPdBottomBtnPresenter.this.mCarListener.showDlg();
                }
                if (LibPdBottomBtnPresenter.this.mBottomBtnClickListener != null) {
                    LibPdBottomBtnPresenter.this.mBottomBtnClickListener.libPdBottomShowDlg();
                }
                if (LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils != null) {
                    LibPdBottomBtnPresenter.this.mLibPdStyleInfoViewUtils.dismiss();
                }
            }

            @Override // com.jingdong.common.controller.ShoppingBaseController.PDShoppingCartListener
            public void showGuideToast() {
            }
        };
        ShoppingBaseController.addCartForPD(cartAddForPDEntity);
    }
}
