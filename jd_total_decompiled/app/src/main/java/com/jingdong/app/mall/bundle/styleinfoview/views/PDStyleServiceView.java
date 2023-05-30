package com.jingdong.app.mall.bundle.styleinfoview.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.jingdong.app.mall.bundle.styleinfoview.R;
import com.jingdong.app.mall.bundle.styleinfoview.common.PDFlowLayoutFix;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYanBaoCoupon;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYanBaoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class PDStyleServiceView extends PDBaseLinearView {
    private Context mContext;
    private boolean mIsFurniture;
    private View[] mServerItems;
    private PdAutoChangeTextSize mServiceActivityTip;
    private LinearLayout mServiceContainer;
    private PdAutoChangeTextSize mServiceCoupon;
    private String[] mServiceSkuids;
    private PdAutoChangeTextSize mServiceTitle;
    private WareYanBaoEntity mYanBaoEntity;

    public PDStyleServiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(WareYanBaoEntity wareYanBaoEntity, View view) {
        WareYanBaoCoupon wareYanBaoCoupon = wareYanBaoEntity.yanBaoCoupon;
        if (wareYanBaoCoupon == null || TextUtils.isEmpty(wareYanBaoCoupon.yanBaoCouponLinkUrl)) {
            return;
        }
        PDBaseDeepLinkHelper.gotoMWithUrl(getContext(), wareYanBaoEntity.yanBaoCoupon.yanBaoCouponLinkUrl);
    }

    private View getStyleOneGroupServiceView(int i2, WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity, Drawable drawable) {
        PDStyleServiceItemView yBServiceItem;
        List<WareBusinessYanBaoItemEntity> list = wareBusinessYanBaoGroupEntity.products;
        ProductDetailEntity productDetailEntity = this.mProduct;
        boolean z = productDetailEntity != null && productDetailEntity.isDarkTheme();
        View view = null;
        if (list != null && !list.isEmpty()) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.libpdstyleinfoview_style_service_group, (ViewGroup) null);
            ImageView imageView = (ImageView) view.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_img);
            imageView.setVisibility(8);
            if (!TextUtils.isEmpty(wareBusinessYanBaoGroupEntity.imgurl)) {
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.resetViewBeforeLoading(false);
                int i3 = R.drawable.libpdstyleinfoview_yanbao_icon;
                createSimple.showImageOnLoading(i3);
                createSimple.showImageOnFail(i3);
                imageView.setVisibility(0);
                JDImageUtils.displayImage(wareBusinessYanBaoGroupEntity.imgurl, imageView, createSimple, false);
            }
            int color = ContextCompat.getColor(this.mContext, z ? R.color.libpdstyleinfoview_color_ececec : R.color.libpdstyleinfoview_color_262626);
            PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) view.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_name);
            pdAutoChangeTextSize.setText(wareBusinessYanBaoGroupEntity.sortName);
            pdAutoChangeTextSize.setTextColor(color);
            int color2 = ContextCompat.getColor(this.mContext, z ? R.color.libpdstyleinfoview_color_848383 : R.color.libpdstyleinfoview_color_868489);
            PdAutoChangeTextSize pdAutoChangeTextSize2 = (PdAutoChangeTextSize) view.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_desc);
            pdAutoChangeTextSize2.setTextColor(color2);
            showExplain(i2, wareBusinessYanBaoGroupEntity, view, drawable);
            PDFlowLayoutFix pDFlowLayoutFix = (PDFlowLayoutFix) view.findViewById(R.id.libpdstyleinfoview_detail_style_service_layout);
            pDFlowLayoutFix.setSpace(PDUtils.dip2px(12.0f), PDUtils.dip2px(4.0f));
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity = list.get(i4);
                if (wareBusinessYanBaoItemEntity != null && (yBServiceItem = getYBServiceItem(wareBusinessYanBaoItemEntity, wareBusinessYanBaoGroupEntity.sortName, pdAutoChangeTextSize2)) != null) {
                    if (wareBusinessYanBaoItemEntity.isSelected) {
                        this.mServerItems[i2] = yBServiceItem;
                        pdAutoChangeTextSize2.setText(wareBusinessYanBaoItemEntity.tip);
                        this.mServiceSkuids[i2] = String.valueOf(wareBusinessYanBaoItemEntity.platformPid);
                    }
                    yBServiceItem.setItemSelected(wareBusinessYanBaoItemEntity.isSelected);
                    yBServiceItem.setTag(Integer.valueOf(i2));
                    pDFlowLayoutFix.addView(yBServiceItem);
                }
            }
        }
        return view;
    }

    private void getStyleServiceView(List<WareBusinessYanBaoGroupEntity> list) {
        View styleOneGroupServiceView;
        LinearLayout linearLayout = this.mServiceContainer;
        if (linearLayout != null && linearLayout.getChildCount() != 0) {
            this.mServiceContainer.removeAllViews();
        }
        Drawable tipDrawable = getTipDrawable();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int size = list.size();
        this.mServiceSkuids = new String[size];
        for (int i2 = 0; i2 < size; i2++) {
            WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity = list.get(i2);
            if (wareBusinessYanBaoGroupEntity != null && (styleOneGroupServiceView = getStyleOneGroupServiceView(i2, wareBusinessYanBaoGroupEntity, tipDrawable)) != null) {
                this.mServiceContainer.addView(styleOneGroupServiceView, layoutParams);
            }
        }
    }

    private Drawable getTipDrawable() {
        int i2 = this.mProduct.isDarkTheme() ? R.drawable.libpdstyleinfoview_style_explain_dark : R.drawable.libpdstyleinfoview_style_explain;
        Resources resources = getResources();
        if (this.mProduct.isNewJx()) {
            i2 = R.drawable.libpdstyleinfoview_style_jx_explain;
        }
        Drawable drawable = resources.getDrawable(i2);
        drawable.setBounds(0, 0, PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f));
        return drawable;
    }

    private Spannable getYBItemText(WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity) {
        String str = wareBusinessYanBaoItemEntity.sortName + "  ";
        String str2 = "   \u00a5" + wareBusinessYanBaoItemEntity.price;
        int length = str.length();
        int length2 = str.length() + 1;
        SpannableString spannableString = new SpannableString(str + str2);
        try {
            spannableString.setSpan(new VerticalImageSpan(this.mContext, BitmapFactory.decodeResource(this.mContext.getResources(), this.mProduct.isDarkTheme() ? R.drawable.libpdstyleinfoview_style_service_section_dark : R.drawable.libpdstyleinfoview_style_service_section)), length, length2, 33);
            return spannableString;
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("Exception", e2.getMessage());
            }
            return new SpannableString(str + str2);
        }
    }

    private PDStyleServiceItemView getYBServiceItem(final WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity, final String str, final PdAutoChangeTextSize pdAutoChangeTextSize) {
        if (TextUtils.isEmpty(wareBusinessYanBaoItemEntity.sortName) || TextUtils.isEmpty(wareBusinessYanBaoItemEntity.price) || TextUtils.isEmpty(wareBusinessYanBaoItemEntity.tip)) {
            return null;
        }
        PDStyleServiceItemView pDStyleServiceItemView = (PDStyleServiceItemView) LayoutInflater.from(this.mContext).inflate(R.layout.libpdstyleinfoview_style_info_service_item, (ViewGroup) null);
        pDStyleServiceItemView.setServiceContent(getYBItemText(wareBusinessYanBaoItemEntity));
        pDStyleServiceItemView.setServiceDiscount(wareBusinessYanBaoItemEntity.discount);
        pDStyleServiceItemView.changeStyle(this.mProduct.isNewJx(), this.mProduct.isDarkTheme());
        pDStyleServiceItemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleServiceView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view instanceof PDStyleServiceItemView) {
                    PDStyleServiceItemView pDStyleServiceItemView2 = (PDStyleServiceItemView) view;
                    Integer num = (Integer) view.getTag();
                    PDStyleServiceItemView pDStyleServiceItemView3 = (PDStyleServiceItemView) PDStyleServiceView.this.mServerItems[num.intValue()];
                    if (pDStyleServiceItemView3 != null) {
                        if (pDStyleServiceItemView3 != pDStyleServiceItemView2) {
                            pDStyleServiceItemView3.setItemSelected(false);
                            pdAutoChangeTextSize.setText("");
                        } else {
                            pDStyleServiceItemView3.setItemSelected(!pDStyleServiceItemView3.isSelected());
                            pdAutoChangeTextSize.setText(pDStyleServiceItemView3.isSelected() ? wareBusinessYanBaoItemEntity.tip : "");
                            wareBusinessYanBaoItemEntity.isSelected = pDStyleServiceItemView3.isSelected();
                            if (PDStyleServiceView.this.mIsFurniture) {
                                PDStyleServiceView.this.refreshFurnitureStatus(num.intValue(), wareBusinessYanBaoItemEntity);
                            }
                        }
                    }
                    if (pDStyleServiceItemView3 != pDStyleServiceItemView2) {
                        if (PDStyleServiceView.this.mIsFurniture) {
                            PDStyleServiceView.this.refreshFurnitureStatus(num.intValue(), wareBusinessYanBaoItemEntity);
                        } else {
                            PDStyleServiceView.this.refreshServerStatus(num.intValue(), wareBusinessYanBaoItemEntity);
                        }
                        pDStyleServiceItemView2.setItemSelected(pDStyleServiceItemView3 == null || !pDStyleServiceItemView3.isSelected());
                        pdAutoChangeTextSize.setText(wareBusinessYanBaoItemEntity.tip);
                    }
                    if (PDStyleServiceView.this.mYanBaoEntity != null && PDStyleServiceView.this.mIsFurniture) {
                        PDStyleServiceView pDStyleServiceView = PDStyleServiceView.this;
                        pDStyleServiceView.getFurnitureServiceInfo(pDStyleServiceView.mYanBaoEntity);
                    } else {
                        PDStyleServiceView.this.getServiceInfo();
                    }
                    PDStyleServiceView.this.mServerItems[num.intValue()] = view;
                    PDStyleServiceView.this.mServiceSkuids[num.intValue()] = String.valueOf(pDStyleServiceItemView2.isSelected() ? Long.valueOf(wareBusinessYanBaoItemEntity.platformPid) : "");
                    if (PDStyleServiceView.this.mIsFurniture) {
                        String str2 = PDStyleServiceView.this.mProduct.skuId + CartConstant.KEY_YB_INFO_LINK + str + CartConstant.KEY_YB_INFO_LINK + wareBusinessYanBaoItemEntity.sortName + "#" + wareBusinessYanBaoItemEntity.platformPid;
                        ProductDetailEntity productDetailEntity = PDStyleServiceView.this.mProduct;
                        PDHelper.onClick("Component_Guarantee", str2, "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity.source, productDetailEntity.getSkuTag(), PDStyleServiceView.this.mProduct.getShopId());
                        return;
                    }
                    String str3 = PDStyleServiceView.this.mProduct.skuId + CartConstant.KEY_YB_INFO_LINK + str + CartConstant.KEY_YB_INFO_LINK + wareBusinessYanBaoItemEntity.sortName + "#" + wareBusinessYanBaoItemEntity.platformPid;
                    ProductDetailEntity productDetailEntity2 = PDStyleServiceView.this.mProduct;
                    PDHelper.onClick("Component_Guarantee", str3, "com.jd.lib.productdetail.ProductDetailActivity", productDetailEntity2.source, productDetailEntity2.getSkuTag(), PDStyleServiceView.this.mProduct.getShopId());
                }
            }
        });
        return pDStyleServiceItemView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshFurnitureStatus(int i2, WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity) {
        List<WareBusinessFurnitureItemEntity> list;
        if (this.mProduct.getWareFurnitureInfo() == null) {
            return;
        }
        List<WareBusinessFurnitureGroupEntity> fiInfo = this.mProduct.getFiInfo();
        if (fiInfo != null && !fiInfo.isEmpty() && i2 < fiInfo.size() && (list = fiInfo.get(i2).items) != null && !list.isEmpty()) {
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                WareBusinessFurnitureItemEntity wareBusinessFurnitureItemEntity = list.get(i3);
                if (wareBusinessFurnitureItemEntity != null) {
                    if (wareBusinessFurnitureItemEntity.isSelected && wareBusinessFurnitureItemEntity.skuId == wareBusinessYanBaoItemEntity.platformPid) {
                        wareBusinessFurnitureItemEntity.isSelected = false;
                    } else {
                        wareBusinessFurnitureItemEntity.isSelected = wareBusinessFurnitureItemEntity.skuId == wareBusinessYanBaoItemEntity.platformPid;
                    }
                }
            }
        }
        this.mYanBaoEntity = PDUtils.setFurniture2YanBao(this.mProduct.getWareFurnitureInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshServerStatus(int i2, WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity) {
        List<WareBusinessYanBaoGroupEntity> yanBaoList;
        List<WareBusinessYanBaoItemEntity> list;
        if (this.mProduct.getWareYanBaoEntity() == null || (yanBaoList = this.mProduct.getYanBaoList()) == null || yanBaoList.isEmpty() || i2 >= yanBaoList.size() || (list = yanBaoList.get(i2).products) == null || list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            WareBusinessYanBaoItemEntity wareBusinessYanBaoItemEntity2 = list.get(i3);
            if (wareBusinessYanBaoItemEntity2 != null) {
                wareBusinessYanBaoItemEntity2.isSelected = wareBusinessYanBaoItemEntity == wareBusinessYanBaoItemEntity2;
            }
        }
    }

    private void setDarkTheme() {
        ProductDetailEntity productDetailEntity = this.mProduct;
        this.mServiceTitle.setTextColor(ContextCompat.getColor(this.mContext, productDetailEntity != null && productDetailEntity.isDarkTheme() ? R.color.libpdstyleinfoview_color_ececec : R.color.libpdstyleinfoview_color_2e2d2d));
    }

    public void bindData2View(final WareYanBaoEntity wareYanBaoEntity, boolean z) {
        setDarkTheme();
        this.mYanBaoEntity = wareYanBaoEntity;
        this.mIsFurniture = z;
        List<WareBusinessYanBaoGroupEntity> list = wareYanBaoEntity.yanBaoList;
        this.mServiceTitle.setText(!TextUtils.isEmpty(wareYanBaoEntity.yanBaoTitle) ? wareYanBaoEntity.yanBaoTitle : this.mContext.getString(R.string.libpdstyleinfoview_style_yanbao));
        if (!TextUtils.isEmpty(wareYanBaoEntity.whiteBarTitle)) {
            this.mServiceActivityTip.setVisibility(0);
            this.mServiceActivityTip.setText(wareYanBaoEntity.whiteBarTitle);
            this.mServiceActivityTip.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleServiceView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (TextUtils.isEmpty(wareYanBaoEntity.whiteBarUrl)) {
                        return;
                    }
                    PDBaseDeepLinkHelper.gotoMWithUrl(PDStyleServiceView.this.mContext, wareYanBaoEntity.whiteBarUrl);
                }
            });
        } else {
            this.mServiceActivityTip.setVisibility(8);
        }
        if (list != null && !list.isEmpty()) {
            this.mServiceTitle.setVisibility(0);
            this.mServerItems = new PDStyleServiceItemView[list.size()];
            getStyleServiceView(list);
        } else {
            this.mServiceTitle.setVisibility(8);
            setVisibility(8);
        }
        WareYanBaoCoupon wareYanBaoCoupon = wareYanBaoEntity.yanBaoCoupon;
        if (wareYanBaoCoupon != null && !TextUtils.isEmpty(wareYanBaoCoupon.yanBaoCouponLinkUrl) && !TextUtils.isEmpty(wareYanBaoEntity.yanBaoCoupon.yanBaoCouponText)) {
            this.mServiceCoupon.setText(wareYanBaoEntity.yanBaoCoupon.yanBaoCouponText);
            this.mServiceCoupon.setVisibility(0);
            this.mServiceCoupon.setBackgroundResource(R.drawable.libpdstyleinfoview_detail_style_service_coupon_bg);
            this.mServiceCoupon.setTextColor(PDUtils.getColorWithTheme(this.mProduct.isDarkTheme(), ContextCompat.getColor(getContext(), R.color.libpdstyleinfoview_color_f2270c), ContextCompat.getColor(getContext(), R.color.pd_color_e63322)));
            this.mServiceCoupon.setTextSize(2, 10.0f);
            this.mServiceCoupon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PDStyleServiceView.this.b(wareYanBaoEntity, view);
                }
            });
            return;
        }
        this.mServiceCoupon.setVisibility(8);
    }

    public void getFurnitureServiceInfo(WareYanBaoEntity wareYanBaoEntity) {
        List<WareBusinessYanBaoGroupEntity> list;
        List<WareBusinessYanBaoItemEntity> list2;
        this.mProduct.furnitureIdsSelect = new ArrayList<>();
        if (wareYanBaoEntity == null || (list = wareYanBaoEntity.yanBaoList) == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity : wareYanBaoEntity.yanBaoList) {
            if (wareBusinessYanBaoGroupEntity != null && (list2 = wareBusinessYanBaoGroupEntity.products) != null) {
                Iterator<WareBusinessYanBaoItemEntity> it = list2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WareBusinessYanBaoItemEntity next = it.next();
                    if (next != null && next.isSelected) {
                        this.mProduct.furnitureIdsSelect.add(String.valueOf(next.platformPid));
                        arrayList.add(next);
                        break;
                    }
                }
            }
        }
    }

    public void getServiceInfo() {
        List<WareBusinessYanBaoItemEntity> list;
        this.mProduct.yanbaoIdsSelect = new ArrayList<>();
        if (this.mProduct.isYanbaoShield() || !this.mProduct.hasYanBao()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity : this.mProduct.getYanBaoList()) {
            if (wareBusinessYanBaoGroupEntity != null && (list = wareBusinessYanBaoGroupEntity.products) != null) {
                Iterator<WareBusinessYanBaoItemEntity> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    WareBusinessYanBaoItemEntity next = it.next();
                    if (next != null && next.isSelected) {
                        this.mProduct.yanbaoIdsSelect.add(String.valueOf(next.platformPid));
                        arrayList.add(next);
                        break;
                    }
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseLinearView
    protected void initView() {
        this.mServiceContainer = (LinearLayout) findViewById(R.id.libpdstyleinfoview_detail_style_service_floor_container);
        this.mServiceTitle = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_title);
        this.mServiceActivityTip = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_ad);
        this.mServiceCoupon = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_coupon);
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseLinearView
    public void onDestoryView() {
        super.onDestoryView();
        LinearLayout linearLayout = this.mServiceContainer;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            this.mServiceContainer = null;
        }
    }

    public void removeAllService() {
        LinearLayout linearLayout = this.mServiceContainer;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
        }
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mServiceTitle;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setVisibility(8);
        }
    }

    public void showExplain(final int i2, final WareBusinessYanBaoGroupEntity wareBusinessYanBaoGroupEntity, View view, Drawable drawable) {
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) view.findViewById(R.id.libpdstyleinfoview_detail_style_service_explain);
        pdAutoChangeTextSize.setVisibility(8);
        if (wareBusinessYanBaoGroupEntity != null) {
            if (this.mIsFurniture && TextUtils.isEmpty(wareBusinessYanBaoGroupEntity.detailUrl)) {
                pdAutoChangeTextSize.setVisibility(8);
            } else if (!this.mIsFurniture && this.mProduct.getWareYanBaoEntity() != null && TextUtils.isEmpty(this.mProduct.getYanBaoDetailUrl())) {
                pdAutoChangeTextSize.setVisibility(8);
            } else {
                boolean z = false;
                pdAutoChangeTextSize.setVisibility(0);
                ProductDetailEntity productDetailEntity = this.mProduct;
                if (productDetailEntity != null && productDetailEntity.isDarkTheme()) {
                    z = true;
                }
                int i3 = z ? R.color.libpdstyleinfoview_color_ff3826 : R.color.libpdstyleinfoview_color_f2270c;
                Resources resources = getResources();
                if (this.mProduct.isNewJx()) {
                    i3 = R.color.libpdstyleinfoview_color_c09947;
                }
                pdAutoChangeTextSize.setTextColor(resources.getColor(i3));
                pdAutoChangeTextSize.setCompoundDrawables(null, null, drawable, null);
                pdAutoChangeTextSize.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleServiceView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        String str;
                        String str2 = PDStyleServiceView.this.mServiceSkuids[i2];
                        if (!PDStyleServiceView.this.mIsFurniture || TextUtils.isEmpty(wareBusinessYanBaoGroupEntity.detailUrl)) {
                            if (PDStyleServiceView.this.mIsFurniture || PDStyleServiceView.this.mProduct.getWareYanBaoEntity() == null) {
                                str = null;
                            } else {
                                String yanBaoDetailUrl = PDStyleServiceView.this.mProduct.getYanBaoDetailUrl();
                                if (TextUtils.isEmpty(str2)) {
                                    str = yanBaoDetailUrl;
                                } else {
                                    str = yanBaoDetailUrl + str2;
                                }
                            }
                        } else {
                            str = wareBusinessYanBaoGroupEntity.detailUrl;
                        }
                        PDBaseDeepLinkHelper.gotoMWithUrl(PDStyleServiceView.this.mContext, str);
                        if (PDStyleServiceView.this.mIsFurniture) {
                            PDHelper.onClick("Component_ServiceIntro", PDStyleServiceView.this.mProduct.skuId + "_3", "com.jd.lib.productdetail.ProductDetailActivity", PDStyleServiceView.this.mProduct.source);
                            return;
                        }
                        PDHelper.onClick("Component_ServiceIntro", PDStyleServiceView.this.mProduct.skuId + "_1", "com.jd.lib.productdetail.ProductDetailActivity", PDStyleServiceView.this.mProduct.source);
                    }
                });
            }
        }
    }
}
