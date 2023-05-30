package com.jingdong.app.mall.bundle.styleinfoview.views;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoViewUtils;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o.PDCarEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o.PDCarItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCarAllInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.events.PDViewEvent;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDLocalReceiver;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDManager;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.entity.PdStyleImageDetail;
import com.jingdong.common.entity.productdetail.PDStyleColorSizeEntity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.entity.productdetail.PDStylePropertyEntity;
import com.jingdong.common.entity.productdetail.PdSelectEntity;
import com.jingdong.common.ui.PDStyleColorSizeView;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.PDConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PDStyleSizeView extends PDBaseLinearView {
    private SimpleDraweeView giftArrow;
    private TextView giftContext;
    private LinearLayout giftCountLayout;
    private TextView giftCountText;
    private SimpleDraweeView giftIcon;
    private int mCarBgDrawable;
    private Context mContext;
    private PopupWindow mCopyWindow;
    private PDCarItemEntity mCurCarEntity;
    private String mCurCarModelId;
    private TextView mCurCarView;
    private View mDivider;
    private LibPdStyleInfoViewUtils mLibPdStyleInfoViewUtils;
    private LocalBroadcastManager mLocalBroadcastManager;
    private PDStyleColorSizeView mPDStyleColorSizeView;
    private PDStyleColorSizeEntity mPdStyleColorSizeEntity;
    private PdStyleSizeViewListner mSizeViewListner;
    private StyleSizeItemClickListenerImpl mStyleSizeItemClickListener;
    private ColorStateList mTextColor;
    private TextView mTipTextView;
    private View mTipVIew;
    private PDLocalReceiver pdLocalReceiver;

    /* loaded from: classes3.dex */
    public interface PdStyleSizeViewListner {
        void selectSizeView(PdSelectEntity pdSelectEntity);

        void setProgressLoading(boolean z, boolean z2);
    }

    /* loaded from: classes3.dex */
    class StyleSizeItemClickListenerImpl extends PDStyleColorSizeView.StyleSizeItemClickListenerImpl {
        StyleSizeItemClickListenerImpl() {
            PDStyleSizeView.this = r1;
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onBuyStyleExplainClickListener(PDStylePropertyEntity pDStylePropertyEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onDashViewClick(View view, PDStylePropertyEntity pDStylePropertyEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl
        protected void onMtaParam(PDStylePropertyEntity pDStylePropertyEntity) {
            StringBuilder sb = new StringBuilder(pDStylePropertyEntity.text);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(pDStylePropertyEntity.isDash ? 2 : pDStylePropertyEntity.status == 0 ? 1 : 0);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(PDStyleSizeView.this.mProduct.skuId);
            PDUtils.onClickForTc("Component_Specification", sb.toString(), PDStyleSizeView.this.mProduct.source);
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onOpenImgDetailClickListener(PdStyleImageDetail pdStyleImageDetail) {
            if (pdStyleImageDetail != null) {
                TextUtils.isEmpty(pdStyleImageDetail.url);
            }
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSelectText(PdSelectEntity pdSelectEntity) {
            super.onSelectText(pdSelectEntity);
            if (PDStyleSizeView.this.mSizeViewListner != null) {
                PDStyleSizeView.this.mSizeViewListner.selectSizeView(pdSelectEntity);
            }
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSelectViewClick(View view, String str, PDStylePropertyEntity pDStylePropertyEntity, String str2) {
            PDStyleSizeView.this.requestNewProduct(str);
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSizeHelperClickListener(PDStyleFilterEntity pDStyleFilterEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl
        public void onSizeHelperExpo(PDStyleFilterEntity pDStyleFilterEntity) {
        }

        @Override // com.jingdong.common.ui.PDStyleColorSizeView.StyleSizeItemClickListenerImpl, com.jingdong.common.ui.PDStyleColorSizeView.OnStyleSizeItemClickListener
        public void onSkuIdLongClick(View view) {
            super.onSkuIdLongClick(view);
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int left = (view.getLeft() + (view.getWidth() / 2)) - (PDUtils.dip2px(70.0f) / 2);
            int i2 = iArr[1];
            PDStyleSizeView pDStyleSizeView = PDStyleSizeView.this;
            ProductDetailEntity productDetailEntity = pDStyleSizeView.mProduct;
            if (productDetailEntity == null || productDetailEntity.skuId == null) {
                return;
            }
            Context context = pDStyleSizeView.mContext;
            PDStyleSizeView pDStyleSizeView2 = PDStyleSizeView.this;
            String str = pDStyleSizeView2.mProduct.skuId;
            pDStyleSizeView.mCopyWindow = PDUtils.showPopUp(context, str, view, str, pDStyleSizeView2.mContext.getResources().getString(R.string.libpdstyleinfoview_sku_copy), left, i2);
        }
    }

    public PDStyleSizeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private void addCarModeInfo(JDJSONObject jDJSONObject) {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        if (jDJSONObject == null) {
            return;
        }
        PDCarItemEntity pDCarItemEntity = new PDCarItemEntity();
        pDCarItemEntity.carModelId = jDJSONObject.getString(PDConstant.EXTRA_MODEL_ID);
        pDCarItemEntity.carModelName = jDJSONObject.getString("brandName");
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null) {
            return;
        }
        if (pDCarEntity.carModelInfo == null) {
            pDCarEntity.carModelInfo = new ArrayList();
        }
        boolean z = false;
        for (PDCarItemEntity pDCarItemEntity2 : this.mProduct.getCarModelInfo()) {
            if (pDCarItemEntity2 != null && TextUtils.equals(pDCarItemEntity2.carModelId, pDCarItemEntity.carModelId)) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        this.mProduct.mWareBusinessData.carAllInfo.carInfo.carModelInfo.add(pDCarItemEntity);
    }

    private void hasNoCheckData() {
        if (this.mPDStyleColorSizeView != null) {
            initFilterData();
        }
    }

    private void initCarGiftView() {
        View view = this.mDivider;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mDivider.setVisibility(8);
    }

    private void initFilterData() {
        if (this.mPDStyleColorSizeView == null) {
            return;
        }
        if (this.mPdStyleColorSizeEntity == null) {
            this.mPdStyleColorSizeEntity = new PDStyleColorSizeEntity();
        }
        this.mPdStyleColorSizeEntity.isJX = this.mProduct.isNewJx();
        PDStyleColorSizeEntity pDStyleColorSizeEntity = this.mPdStyleColorSizeEntity;
        ProductDetailEntity productDetailEntity = this.mProduct;
        pDStyleColorSizeEntity.skuId = productDetailEntity.skuId;
        pDStyleColorSizeEntity.sopSkuList = productDetailEntity.getSopSkuList();
        PDStyleColorSizeEntity pDStyleColorSizeEntity2 = this.mPdStyleColorSizeEntity;
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        WareBusinessData wareBusinessData = productDetailEntity2.mWareBusinessData;
        pDStyleColorSizeEntity2.styleEntity = wareBusinessData != null ? wareBusinessData.colorSizeInfo : null;
        this.mPDStyleColorSizeView.setDarkTheme(productDetailEntity2.isDarkTheme());
        Context context = this.mContext;
        if (context instanceof Activity) {
            this.mPDStyleColorSizeView.setActivity((Activity) context);
        }
        this.mPDStyleColorSizeView.bindData2View(this.mPdStyleColorSizeEntity, this.mProduct.isSop, false);
    }

    private void refreshLoading(boolean z) {
        getEventBus().post(new PDViewEvent("pd_ProductDetailActivity_show_style_loading", Boolean.valueOf(z), this.mManagerKey));
    }

    private void registerBroadcastReceiver(String str) {
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        if (this.pdLocalReceiver == null) {
            this.pdLocalReceiver = new PDLocalReceiver(this.mProduct);
        }
        this.mLocalBroadcastManager.registerReceiver(this.pdLocalReceiver, new IntentFilter(str));
    }

    public void requestNewProduct(String str) {
        ProductDetailEntity productDetailEntity;
        PdStyleSizeViewListner pdStyleSizeViewListner = this.mSizeViewListner;
        if (pdStyleSizeViewListner != null) {
            pdStyleSizeViewListner.setProgressLoading(true, false);
        }
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils;
        if (libPdStyleInfoViewUtils == null || (productDetailEntity = this.mProduct) == null) {
            return;
        }
        libPdStyleInfoViewUtils.requestSku(str, productDetailEntity.source);
    }

    private void resetCarModeInfo(JDJSONObject jDJSONObject) {
        JDJSONArray jSONArray;
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        if (jDJSONObject == null || jDJSONObject.isNull("cars") || (jSONArray = jDJSONObject.getJSONArray("cars")) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.size(); i2++) {
            PDCarItemEntity pDCarItemEntity = new PDCarItemEntity();
            Object obj = jSONArray.get(i2);
            if (obj instanceof JDJSONObject) {
                JDJSONObject jDJSONObject2 = (JDJSONObject) obj;
                pDCarItemEntity.carModelId = jDJSONObject2.getString("id");
                pDCarItemEntity.carModelName = jDJSONObject2.getString("brandName");
                arrayList.add(pDCarItemEntity);
            }
        }
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null) {
            return;
        }
        pDCarEntity.carModelInfo = arrayList;
    }

    private void showFilterView(List<PDStyleFilterEntity> list) {
        PDStyleColorSizeView pDStyleColorSizeView = this.mPDStyleColorSizeView;
        ProductDetailEntity productDetailEntity = this.mProduct;
        pDStyleColorSizeView.setSkuId(productDetailEntity.skuId, productDetailEntity.mWareBusinessData.newStyle);
        this.mPDStyleColorSizeView.setSopSkuList(this.mProduct.getSopSkuList(), this.mProduct.isSop);
        this.mPDStyleColorSizeView.setFilterList(list);
        this.mPDStyleColorSizeView.showFilterView();
    }

    private void unRegisterBroadcastReceiver() {
        LocalBroadcastManager localBroadcastManager;
        PDLocalReceiver pDLocalReceiver = this.pdLocalReceiver;
        if (pDLocalReceiver == null || (localBroadcastManager = this.mLocalBroadcastManager) == null) {
            return;
        }
        localBroadcastManager.unregisterReceiver(pDLocalReceiver);
        this.pdLocalReceiver = null;
    }

    public void bindData2View() {
        changeStyle(this.mProduct.isNewJx());
        initFilterData();
    }

    public void changeStyle(boolean z) {
        int i2 = this.mProduct.isDarkTheme() ? R.color.libpdstyleinfoview_text_dark_selector : R.color.libpdstyleinfoview_text_selector;
        Resources resources = getResources();
        if (z) {
            i2 = com.jingdong.common.R.color.lib_style_jx_text_selector;
        }
        this.mTextColor = resources.getColorStateList(i2);
        PDStyleColorSizeView pDStyleColorSizeView = this.mPDStyleColorSizeView;
        if (pDStyleColorSizeView != null) {
            pDStyleColorSizeView.changeStyle(z);
        }
    }

    public void dismissPopupWindow() {
        PopupWindow popupWindow = this.mCopyWindow;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.mCopyWindow.dismiss();
    }

    public String getCarModelId() {
        return this.mCurCarModelId;
    }

    public int getFilterLayoutTop() {
        PDStyleColorSizeView pDStyleColorSizeView = this.mPDStyleColorSizeView;
        return getTop() + (pDStyleColorSizeView != null ? pDStyleColorSizeView.getTop() : 0);
    }

    public List<PDStylePropertyEntity> getStyleProperty() {
        PDStyleColorSizeView pDStyleColorSizeView = this.mPDStyleColorSizeView;
        if (pDStyleColorSizeView != null) {
            return pDStyleColorSizeView.getStyleProperty();
        }
        return null;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseLinearView
    protected void initView() {
        if (this.mPDStyleColorSizeView == null) {
            this.mPDStyleColorSizeView = (PDStyleColorSizeView) ((ViewStub) findViewById(R.id.libpdstyleinfoview_detail_style_filter_layout_container)).inflate();
        }
        if (this.mStyleSizeItemClickListener == null) {
            this.mStyleSizeItemClickListener = new StyleSizeItemClickListenerImpl();
        }
        this.mPDStyleColorSizeView.setOnStyleSizeItemClickListener(this.mStyleSizeItemClickListener);
        View inflate = ((ViewStub) findViewById(com.jingdong.common.R.id.lib_pd_style_item_tip)).inflate();
        this.mTipVIew = inflate;
        this.mTipTextView = (TextView) inflate.findViewById(com.jingdong.common.R.id.pd_style_item_tip_content);
        this.mTipVIew.setVisibility(8);
        this.mDivider = findViewById(com.jingdong.common.R.id.detail_style_size_divider);
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseLinearView
    public void onDestoryView() {
        super.onDestoryView();
        this.mCurCarModelId = null;
        this.mTipVIew = null;
        this.mTipTextView = null;
        this.mStyleSizeItemClickListener = null;
        unRegisterBroadcastReceiver();
    }

    public void registerEventBus() {
        if (PDManager.getEventBus().isRegistered(this)) {
            return;
        }
        PDManager.getEventBus().register(this);
    }

    public void setCurCarModelId(String str) {
        this.mCurCarModelId = str;
    }

    public void setPdStyleSizeViewListner(PdStyleSizeViewListner pdStyleSizeViewListner) {
        this.mSizeViewListner = pdStyleSizeViewListner;
    }

    public void setStyleUtil(LibPdStyleInfoViewUtils libPdStyleInfoViewUtils) {
        this.mLibPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
    }

    public void showorHideTipView(String str, int i2) {
        View view = this.mTipVIew;
        if (view != null) {
            view.setVisibility(i2);
            TextView textView = this.mTipTextView;
            if (textView != null) {
                textView.setText(str);
            }
        }
    }

    public void unregisterEventBus() {
        PDManager.getEventBus().unregister(this);
    }
}
