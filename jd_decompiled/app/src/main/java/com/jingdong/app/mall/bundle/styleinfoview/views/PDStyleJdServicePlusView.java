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
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareJdServerPlusEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
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
public class PDStyleJdServicePlusView extends PDBaseLinearView {
    private Context mContext;
    private String mMoreUrl;
    private View[] mServerItems;
    private LinearLayout mServiceContainer;
    private String[] mServiceSkuids;
    private PdAutoChangeTextSize mServiceTitle;

    public PDStyleJdServicePlusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    private View getStyleOneGroupServiceView(final int i2, WareBusinessPlusListEntity wareBusinessPlusListEntity, Drawable drawable) {
        List<WareBusinessJdServerProduct> list = wareBusinessPlusListEntity.products;
        ProductDetailEntity productDetailEntity = this.mProduct;
        boolean z = productDetailEntity != null && productDetailEntity.isDarkTheme();
        if (list == null || list.isEmpty()) {
            return null;
        }
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.libpdstyleinfoview_style_service_group, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_img);
        imageView.setVisibility(8);
        if (!TextUtils.isEmpty(wareBusinessPlusListEntity.scIconUrl)) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            createSimple.resetViewBeforeLoading(false);
            int i3 = R.drawable.libpdstyleinfoview_yanbao_icon;
            createSimple.showImageOnLoading(i3);
            createSimple.showImageOnFail(i3);
            imageView.setVisibility(0);
            JDImageUtils.displayImage(wareBusinessPlusListEntity.scIconUrl, imageView, createSimple, false);
        }
        int color = ContextCompat.getColor(this.mContext, z ? R.color.pd_color_ececec : R.color.libpdstyleinfoview_color_262626);
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) inflate.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_name);
        pdAutoChangeTextSize.setText(wareBusinessPlusListEntity.scName);
        pdAutoChangeTextSize.setTextColor(color);
        int color2 = ContextCompat.getColor(this.mContext, z ? R.color.pd_color_848383 : R.color.libpdstyleinfoview_color_868489);
        PdAutoChangeTextSize pdAutoChangeTextSize2 = (PdAutoChangeTextSize) inflate.findViewById(R.id.libpdstyleinfoview_detail_style_service_title_desc);
        pdAutoChangeTextSize2.setTextColor(color2);
        PdAutoChangeTextSize pdAutoChangeTextSize3 = (PdAutoChangeTextSize) inflate.findViewById(R.id.libpdstyleinfoview_detail_style_service_explain);
        pdAutoChangeTextSize3.setVisibility(8);
        if (!TextUtils.isEmpty(this.mMoreUrl)) {
            pdAutoChangeTextSize3.setVisibility(0);
            int i4 = z ? R.color.pd_color_ff3826 : R.color.libpdstyleinfoview_color_f0250f;
            Context context = this.mContext;
            if (this.mProduct.isNewJx()) {
                i4 = R.color.pd_color_c09947;
            }
            pdAutoChangeTextSize3.setTextColor(ContextCompat.getColor(context, i4));
            pdAutoChangeTextSize3.setCompoundDrawables(null, null, drawable, null);
            pdAutoChangeTextSize3.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleJdServicePlusView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (PDStyleJdServicePlusView.this.mProduct.getWareJdServerPlusEntity() != null) {
                        PDBaseDeepLinkHelper.gotoMWithUrl(PDStyleJdServicePlusView.this.mContext, PDStyleJdServicePlusView.this.mMoreUrl + PDStyleJdServicePlusView.this.mServiceSkuids[i2]);
                        PDHelper.onClick("Component_ServiceIntro", PDStyleJdServicePlusView.this.mProduct.skuId + "_2", "com.jd.lib.productdetail.ProductDetailActivity", PDStyleJdServicePlusView.this.mProduct.source);
                    }
                }
            });
        }
        PDFlowLayoutFix pDFlowLayoutFix = (PDFlowLayoutFix) inflate.findViewById(R.id.libpdstyleinfoview_detail_style_service_layout);
        pDFlowLayoutFix.setSpace(PDUtils.dip2px(10.0f), PDUtils.dip2px(10.0f));
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            WareBusinessJdServerProduct wareBusinessJdServerProduct = list.get(i5);
            if (wareBusinessJdServerProduct != null) {
                wareBusinessJdServerProduct.scNameNative = wareBusinessPlusListEntity.scName;
                PDStyleServiceItemView yBServiceItem = getYBServiceItem(wareBusinessJdServerProduct, pdAutoChangeTextSize2, i2);
                if (yBServiceItem != null) {
                    if (wareBusinessJdServerProduct.isSelected) {
                        this.mServerItems[i2] = yBServiceItem;
                        pdAutoChangeTextSize2.setText(wareBusinessJdServerProduct.serviceSkuAdWord);
                        this.mServiceSkuids[i2] = wareBusinessJdServerProduct.serviceSku;
                    }
                    yBServiceItem.setItemSelected(wareBusinessJdServerProduct.isSelected);
                    yBServiceItem.setTag(Integer.valueOf(i2));
                    pDFlowLayoutFix.addView(yBServiceItem);
                }
            }
        }
        return inflate;
    }

    private void getStyleServiceView(List<WareBusinessPlusListEntity> list) {
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
            WareBusinessPlusListEntity wareBusinessPlusListEntity = list.get(i2);
            if (wareBusinessPlusListEntity != null && (styleOneGroupServiceView = getStyleOneGroupServiceView(i2, wareBusinessPlusListEntity, tipDrawable)) != null) {
                this.mServiceContainer.addView(styleOneGroupServiceView, layoutParams);
            }
        }
    }

    private Drawable getTipDrawable() {
        int i2 = this.mProduct.isDarkTheme() ? R.drawable.lib_pd_style_explain_dark : R.drawable.lib_pd_style_explain;
        Resources resources = getResources();
        if (this.mProduct.isNewJx()) {
            i2 = R.drawable.libpdstyleinfoview_style_jx_explain;
        }
        Drawable drawable = resources.getDrawable(i2);
        drawable.setBounds(0, 0, PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f));
        return drawable;
    }

    private Spannable getYBItemText(WareBusinessJdServerProduct wareBusinessJdServerProduct) {
        String str = wareBusinessJdServerProduct.serviceSkuShortName + "  ";
        String str2 = "   \u00a5" + wareBusinessJdServerProduct.serviceSkuPrice;
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

    private PDStyleServiceItemView getYBServiceItem(final WareBusinessJdServerProduct wareBusinessJdServerProduct, final PdAutoChangeTextSize pdAutoChangeTextSize, int i2) {
        WarePropertyInfo warePropertyInfo;
        if (TextUtils.isEmpty(wareBusinessJdServerProduct.serviceSkuShortName) || TextUtils.isEmpty(wareBusinessJdServerProduct.serviceSkuPrice) || TextUtils.isEmpty(wareBusinessJdServerProduct.serviceSkuShortName)) {
            return null;
        }
        PDStyleServiceItemView pDStyleServiceItemView = (PDStyleServiceItemView) LayoutInflater.from(this.mContext).inflate(R.layout.libpdstyleinfoview_style_info_service_item, (ViewGroup) null);
        pDStyleServiceItemView.setServiceContent(getYBItemText(wareBusinessJdServerProduct));
        pDStyleServiceItemView.setServiceDiscount(wareBusinessJdServerProduct.discount);
        pDStyleServiceItemView.changeStyle(this.mProduct.isNewJx(), this.mProduct.isDarkTheme());
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null && TextUtils.equals(warePropertyInfo.selectedServiceSku, wareBusinessJdServerProduct.serviceSku)) {
            wareBusinessJdServerProduct.isSelected = true;
            this.mServerItems[i2] = pDStyleServiceItemView;
            this.mServiceSkuids[i2] = wareBusinessJdServerProduct.serviceSku;
            getJdServerPlusInfo();
            return pDStyleServiceItemView;
        }
        pDStyleServiceItemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleJdServicePlusView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view instanceof PDStyleServiceItemView) {
                    PDStyleServiceItemView pDStyleServiceItemView2 = (PDStyleServiceItemView) view;
                    Integer num = (Integer) view.getTag();
                    PDStyleServiceItemView pDStyleServiceItemView3 = (PDStyleServiceItemView) PDStyleJdServicePlusView.this.mServerItems[num.intValue()];
                    if (pDStyleServiceItemView3 != null) {
                        if (pDStyleServiceItemView3 != pDStyleServiceItemView2) {
                            pDStyleServiceItemView3.setItemSelected(false);
                            pdAutoChangeTextSize.setText("");
                        } else {
                            pDStyleServiceItemView3.setItemSelected(!pDStyleServiceItemView3.isSelected());
                            pdAutoChangeTextSize.setText(pDStyleServiceItemView3.isSelected() ? wareBusinessJdServerProduct.serviceSkuAdWord : "");
                            wareBusinessJdServerProduct.isSelected = pDStyleServiceItemView3.isSelected();
                        }
                    }
                    if (pDStyleServiceItemView3 != pDStyleServiceItemView2) {
                        PDStyleJdServicePlusView.this.refreshStatus(num.intValue(), wareBusinessJdServerProduct);
                        pDStyleServiceItemView2.setItemSelected(pDStyleServiceItemView3 == null || !pDStyleServiceItemView3.isSelected());
                        pdAutoChangeTextSize.setText(wareBusinessJdServerProduct.serviceSkuAdWord);
                    }
                    PDStyleJdServicePlusView.this.mServerItems[num.intValue()] = view;
                    PDStyleJdServicePlusView.this.mServiceSkuids[num.intValue()] = String.valueOf(pDStyleServiceItemView2.isSelected() ? wareBusinessJdServerProduct.serviceSku : "");
                    PDStyleJdServicePlusView.this.getJdServerPlusInfo();
                    PDHelper.onClick("Component_JDServicePlus", PDStyleJdServicePlusView.this.mProduct.skuId + CartConstant.KEY_YB_INFO_LINK + wareBusinessJdServerProduct.scNameNative + CartConstant.KEY_YB_INFO_LINK + wareBusinessJdServerProduct.serviceSkuShortName + "#" + wareBusinessJdServerProduct.serviceSku, "com.jd.lib.productdetail.ProductDetailActivity", PDStyleJdServicePlusView.this.mProduct.source);
                }
            }
        });
        return pDStyleServiceItemView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshStatus(int i2, WareBusinessJdServerProduct wareBusinessJdServerProduct) {
        List<WareBusinessPlusListEntity> jdSerPlusList;
        List<WareBusinessJdServerProduct> list;
        if (this.mProduct.getWareJdServerPlusEntity() == null || (jdSerPlusList = this.mProduct.getJdSerPlusList()) == null || jdSerPlusList.isEmpty() || i2 >= jdSerPlusList.size() || (list = jdSerPlusList.get(i2).products) == null || list.isEmpty()) {
            return;
        }
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            WareBusinessJdServerProduct wareBusinessJdServerProduct2 = list.get(i3);
            if (wareBusinessJdServerProduct2 != null) {
                if (wareBusinessJdServerProduct2.isSelected && TextUtils.equals(wareBusinessJdServerProduct2.serviceSku, wareBusinessJdServerProduct.serviceSku)) {
                    wareBusinessJdServerProduct2.isSelected = false;
                } else {
                    wareBusinessJdServerProduct2.isSelected = TextUtils.equals(wareBusinessJdServerProduct2.serviceSku, wareBusinessJdServerProduct.serviceSku);
                }
            }
        }
    }

    private void setDarkTheme() {
        ProductDetailEntity productDetailEntity = this.mProduct;
        this.mServiceTitle.setTextColor(ContextCompat.getColor(this.mContext, productDetailEntity != null && productDetailEntity.isDarkTheme() ? R.color.pd_color_ececec : R.color.pd_style_gray));
    }

    public void bindData2View(WareJdServerPlusEntity wareJdServerPlusEntity) {
        List<WareBusinessPlusListEntity> list;
        if (wareJdServerPlusEntity != null && (list = wareJdServerPlusEntity.jdSerPlusList) != null && !list.isEmpty()) {
            setDarkTheme();
            this.mServiceTitle.setVisibility(0);
            this.mMoreUrl = wareJdServerPlusEntity.jdSerPlusUrl;
            this.mServiceTitle.setText(!TextUtils.isEmpty(wareJdServerPlusEntity.jdSerPlusTitle) ? wareJdServerPlusEntity.jdSerPlusTitle : this.mContext.getString(R.string.libpdstyleinfoview_style_yanbao));
            this.mServerItems = new PDStyleServiceItemView[wareJdServerPlusEntity.jdSerPlusList.size()];
            getStyleServiceView(wareJdServerPlusEntity.jdSerPlusList);
            return;
        }
        this.mServiceTitle.setVisibility(8);
        setVisibility(8);
    }

    public void getJdServerPlusInfo() {
        List<WareBusinessJdServerProduct> list;
        WarePropertyInfo warePropertyInfo;
        this.mProduct.jsServerPlusIdsSelect = new ArrayList<>();
        if (this.mProduct.hasJdServerPlus()) {
            ArrayList arrayList = new ArrayList();
            for (WareBusinessPlusListEntity wareBusinessPlusListEntity : this.mProduct.getJdSerPlusList()) {
                if (wareBusinessPlusListEntity != null && (list = wareBusinessPlusListEntity.products) != null) {
                    Iterator<WareBusinessJdServerProduct> it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            WareBusinessJdServerProduct next = it.next();
                            WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
                            if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null && !TextUtils.isEmpty(warePropertyInfo.selectedServiceSku) && TextUtils.equals(this.mProduct.mWareBusinessData.property.selectedServiceSku, next.serviceSku)) {
                                next.isSelected = true;
                            }
                            if (next != null && next.isSelected) {
                                this.mProduct.jsServerPlusIdsSelect.add(next.serviceSku);
                                arrayList.add(next);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseLinearView
    protected void initView() {
        this.mServiceContainer = (LinearLayout) findViewById(R.id.libpdstyleinfoview_detail_style_service_item_container);
        this.mServiceTitle = (PdAutoChangeTextSize) findViewById(R.id.libpdstyleinfoview_detail_style_service_title);
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
}
