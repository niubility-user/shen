package com.jingdong.app.mall.bundle.styleinfoview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewBottomBtnClickListener;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCarListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ProductDetailEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.SecondPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCollageJoinBuyInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessWeightEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareJdServerPlusEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYanBaoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo;
import com.jingdong.app.mall.bundle.styleinfoview.holder.LibPdBottomBtnHolder;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDManager;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseRelativeView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDScrollView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleCountView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleJdServicePlusView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleServiceView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleSizeView;
import com.jingdong.app.mall.bundle.styleinfoview.views.PdAutoChangeTextSize;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.entity.Image;
import com.jingdong.common.entity.productdetail.PDStyleEntity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.entity.productdetail.PdSelectEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class LibPdStyleInfoView extends PDBaseRelativeView implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener, PDStyleSizeView.PdStyleSizeViewListner {
    private View bottomDivider;
    private RelativeLayout.LayoutParams doublePriceLayoutParams;
    private Activity mActivity;
    private LinearLayout mBetweenLayout;
    private TextView mBetweenMaxText;
    private TextView mBetweenMinText;
    private TextView mBetweenSymbol;
    public LinearLayout mBottomLinear;
    private PdAutoChangeTextSize mBtTextA;
    private View mCancelBtn;
    private LibPdStyleInfoViewCarListener mCarListener;
    private TextView mChannelTag;
    private PopupWindow mCopyWindow;
    private int mCount;
    private RelativeLayout mDetailLayout;
    private JDDisplayImageOptions mDisplayImageOptions;
    private TextView mDoubleBetweenMaxText;
    private TextView mDoubleBetweenSymbol;
    private PdAutoChangeTextSize mDoublePrice;
    private SimpleDraweeView mDoublePriceImage;
    private PdAutoChangeTextSize mDoublePriceLabel;
    private LinearLayout mDoublePriceLayout;
    private TextView mFanAmtTab;
    private PdAutoChangeTextSize mFanAmtTabBottom;
    private TextView mHandPrice;
    private SimpleDraweeView mHandPriceIcon;
    private TextView mHandPriceTitle;
    private IMyActivity mIMyActivity;
    public boolean mIsClickNetError;
    private PdAutoChangeTextSize mJdPrice;
    private PdAutoChangeTextSize mJingXiPrice;
    private TextView mJoinBuyBum;
    private TextView mJoinBuyBumBottom;
    private LinearLayout mJoinBuyNumFanLL;
    private TextView mLeftBtn;
    private LibPdStyleInfoViewUtils mLibPdStyleInfoViewUtils;
    private FrameLayout mLoadingView;
    private TextView mNetReLayout;
    private View mNetworkLayout;
    private SimpleDraweeView mPriceUp;
    private FrameLayout mPriceUpLayout;
    private SimpleDraweeView mProductIcon;
    private PdAutoChangeTextSize mProductPrice;
    private PdAutoChangeTextSize mProductSkuId;
    private PdAutoChangeTextSize mProductWeight;
    private TextView mRightBtn;
    private PDScrollView mScrollView;
    private SecondPriceEntity mSecondPriceInfo;
    private PDStyleServiceView mServiceView;
    private LinearLayout mSkuIdWrap;
    private FrameLayout mStockContainer;
    private PdAutoChangeTextSize mStockNum;
    private LinearLayout mStyleContainer;
    private PDStyleCountView mStyleCountView;
    private PDStyleJdServicePlusView mStyleJdServicePlusView;
    public PDStyleSizeView mStyleSizeView;
    private TextView mTipTextView;
    private View mTipView;
    private View mTitleContainer;
    private FrameLayout mTitleLayoutFrameB;
    private LinearLayout mToHandPriceContainer;
    private int mTrueMeasureHeight;
    private FrameLayout.LayoutParams priceUpParams;

    public LibPdStyleInfoView(Context context) {
        super(context);
    }

    private void bindTopData2View() {
        LinearLayout linearLayout;
        WareBusinessWeightEntity wareBusinessWeightEntity;
        Image image;
        SimpleDraweeView simpleDraweeView;
        setNetErrorLayout(false);
        this.mTitleContainer.setVisibility(0);
        this.mProductIcon.setVisibility(0);
        List<Image> list = this.mProduct.imageList;
        if (list != null && !list.isEmpty() && (image = list.get(0)) != null && (simpleDraweeView = this.mProductIcon) != null) {
            JDImageUtils.displayImage(image.small, (ImageView) simpleDraweeView, this.mDisplayImageOptions, true);
        }
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mProductWeight;
        if (pdAutoChangeTextSize != null) {
            WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
            if (wareBusinessData != null && (wareBusinessWeightEntity = wareBusinessData.weightInfo) != null) {
                String str = wareBusinessWeightEntity.title;
                String str2 = wareBusinessWeightEntity.content;
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    this.mProductWeight.setVisibility(0);
                    this.mProductWeight.setText(str + ":" + str2);
                } else {
                    this.mProductWeight.setVisibility(8);
                }
            } else {
                pdAutoChangeTextSize.setVisibility(8);
            }
        }
        if (this.mProductSkuId != null) {
            ProductDetailEntity productDetailEntity = this.mProduct;
            if (productDetailEntity != null && productDetailEntity.isBybtPbFlag()) {
                this.mProductSkuId.setVisibility(8);
            } else {
                this.mProductSkuId.setText(getResources().getString(R.string.libpdstyleinfoview_sku, this.mProduct.skuId));
                this.mProductSkuId.setVisibility(0);
            }
            this.mProductSkuId.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoView.2
                {
                    LibPdStyleInfoView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (LibPdStyleInfoView.this.isActivityFinish() || LibPdStyleInfoView.this.mProductSkuId == null || LibPdStyleInfoView.this.mProductSkuId.getLayout() == null || LibPdStyleInfoView.this.mProductSkuId.getLayout().getEllipsisCount(LibPdStyleInfoView.this.mProductSkuId.getLineCount() - 1) <= 0 || LibPdStyleInfoView.this.mSkuIdWrap == null) {
                        return;
                    }
                    LibPdStyleInfoView.this.mSkuIdWrap.setOrientation(1);
                }
            });
        }
        if (this.mProduct.mWareBusinessData != null && (linearLayout = this.mSkuIdWrap) != null) {
            linearLayout.setVisibility(0);
        }
        LinearLayout linearLayout2 = this.mSkuIdWrap;
        if (linearLayout2 != null) {
            linearLayout2.setOrientation(0);
        }
    }

    private void defaulePrice() {
        this.mProductPrice.setTextSize(2, 15.0f);
        this.mDoublePrice.setTextSize(2, 11.0f);
        boolean isOffSale = this.mProduct.isOffSale();
        if (PDUtils.isEmptyPrice(this.mProduct.mWareBusinessData)) {
            isOffSale = true;
        }
        if (isOffSale) {
            this.mPriceUp.setVisibility(8);
            if (PDUtils.isEmptyPrice(this.mProduct.mWareBusinessData)) {
                this.mProductPrice.setText(this.mProduct.mWareBusinessData.property.showEmptyPriceText);
                return;
            } else {
                this.mProductPrice.setText(this.mContext.getString(R.string.libpdstyleinfoview_lib_pd_no_price));
                return;
            }
        }
        if (this.mProduct.isMainPriceBetween()) {
            if (this.mBetweenLayout.getVisibility() == 8) {
                this.mBetweenLayout.setVisibility(0);
            }
            if (this.mProductPrice.getVisibility() == 0) {
                this.mProductPrice.setVisibility(8);
            }
            if (this.mProduct.getPriceInfo() != null) {
                this.mBetweenMinText.setText(PDUtils.getJPriceText(this.mProduct.getPriceInfo().jdMinPrice, 0.6f));
                this.mBetweenSymbol.setText("~");
                this.mBetweenMaxText.setText(PDUtils.getJPriceText(this.mProduct.getPriceInfo().jdMaxPrice, 0.6f));
            }
        } else {
            if (this.mBetweenLayout.getVisibility() == 0) {
                this.mBetweenLayout.setVisibility(8);
            }
            if (this.mProductPrice.getVisibility() == 8) {
                this.mProductPrice.setVisibility(0);
            }
            String str = this.mProduct.getPriceInfo() != null ? this.mProduct.getPriceInfo().channelPrice : null;
            if (TextUtils.isEmpty(str)) {
                str = this.mProduct.getJdPrice();
                TextView textView = this.mChannelTag;
                if (textView != null) {
                    textView.setVisibility(8);
                }
            } else {
                TextView textView2 = this.mChannelTag;
                if (textView2 != null) {
                    textView2.setVisibility(0);
                    this.mChannelTag.setText(this.mProduct.getPriceInfo().priceTag);
                }
            }
            String emptyPrice = PDUtils.getEmptyPrice(PDUtils.getPrice(str, false).toString(), this.mProduct.mWareBusinessData);
            SpannableString spannableString = new SpannableString(emptyPrice);
            if (!PDUtils.isEmptyPrice(this.mProduct.mWareBusinessData)) {
                handlePriceStyle(emptyPrice, spannableString);
            }
            this.mProductPrice.setText(spannableString);
            setPriceContentDes(emptyPrice);
        }
        showDoublePrice();
    }

    private SpannableString getInstallmentPriceSpan(String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return new SpannableString("");
        }
        if (TextUtils.isEmpty(this.mProduct.getDownPayDesc())) {
            i2 = 0;
        } else {
            str = str + LangUtils.SINGLE_SPACE + this.mProduct.getDownPayDesc();
            i2 = str.length() - 1;
        }
        try {
            SpannableString spannableString = new SpannableString(str);
            if (i2 > 0) {
                int i3 = i2 + 1;
                spannableString.setSpan(new RelativeSizeSpan(0.8666667f), i2, i3, 18);
                spannableString.setSpan(new ForegroundColorSpan(PDUtils.parseColor("#848689")), i2, i3, 18);
                spannableString.setSpan(new StyleSpan(1), 0, i2, 18);
            } else {
                handlePriceStyle(str, spannableString);
            }
            return spannableString;
        } catch (Exception unused) {
            return new SpannableString(str);
        }
    }

    private void handlePriceStyle(String str, Spannable spannable) {
        try {
            if (str.startsWith("\u00a5")) {
                spannable.setSpan(new AbsoluteSizeSpan(12, true), 0, 1, 18);
                int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf > 0) {
                    spannable.setSpan(new AbsoluteSizeSpan(20, true), 1, indexOf, 18);
                    spannable.setSpan(new AbsoluteSizeSpan(12, true), indexOf, str.length(), 18);
                } else if (indexOf < 0 && str.length() > 1) {
                    spannable.setSpan(new AbsoluteSizeSpan(18, true), 1, str.length(), 18);
                }
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.d("Exception", e2.getMessage());
            }
        }
    }

    private void inflatTopViewB(View view) {
        if (view == null) {
            return;
        }
        this.mTitleContainer = view.findViewById(R.id.detail_style_title_content);
        this.mDetailLayout = (RelativeLayout) view.findViewById(R.id.detail_style_price_layout);
        this.mProductPrice = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_price);
        this.mChannelTag = (TextView) view.findViewById(R.id.tx_channel_tag);
        FontsUtil.changeTextFont(this.mProductPrice, 4097);
        this.mBetweenLayout = (LinearLayout) view.findViewById(R.id.lib_pd_style_price_between_price_layout);
        TextView textView = (TextView) view.findViewById(R.id.lib_pd_style_price_between_min_text);
        this.mBetweenMinText = textView;
        FontsUtil.changeTextFont(textView, 4099);
        this.mBetweenSymbol = (TextView) view.findViewById(R.id.lib_pd_style_price_between_symbol);
        TextView textView2 = (TextView) view.findViewById(R.id.lib_pd_style_price_between_max_text);
        this.mBetweenMaxText = textView2;
        FontsUtil.changeTextFont(textView2, 4099);
        this.mDoubleBetweenSymbol = (TextView) view.findViewById(R.id.lib_pd_style_price_double_between_symbol);
        TextView textView3 = (TextView) view.findViewById(R.id.lib_pd_style_price_double_between_max_text);
        this.mDoubleBetweenMaxText = textView3;
        FontsUtil.changeTextFont(textView3, 4099);
        this.mFanAmtTab = (TextView) view.findViewById(R.id.tx_joinBuy_fanamt);
        this.mJoinBuyBum = (TextView) view.findViewById(R.id.tx_joinBuy_num);
        this.mPriceUpLayout = (FrameLayout) view.findViewById(R.id.detail_style_price_up_layout);
        this.mPriceUp = (SimpleDraweeView) view.findViewById(R.id.detail_style_price_up);
        this.mDoublePriceLayout = (LinearLayout) view.findViewById(R.id.detail_style_double_price_layout);
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_double_price);
        this.mDoublePrice = pdAutoChangeTextSize;
        FontsUtil.changeTextFont(pdAutoChangeTextSize);
        this.mDoublePriceLabel = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_double_price_img);
        this.mDoublePriceImage = (SimpleDraweeView) view.findViewById(R.id.detail_style_double_price_image);
        this.mJingXiPrice = (PdAutoChangeTextSize) view.findViewById(R.id.tv_jingxi_price);
        this.mToHandPriceContainer = (LinearLayout) view.findViewById(R.id.detail_hand_price_layout);
        this.mHandPriceTitle = (TextView) view.findViewById(R.id.detail_handprice_title);
        this.mHandPrice = (TextView) view.findViewById(R.id.detail_hand_price);
        this.mHandPriceIcon = (SimpleDraweeView) view.findViewById(R.id.detail_hand_price_icon);
        View findViewById = view.findViewById(R.id.detail_style_cancel);
        this.mCancelBtn = findViewById;
        findViewById.setOnClickListener(this);
        this.mTitleLayoutFrameB.setVisibility(0);
        this.mJoinBuyNumFanLL = (LinearLayout) view.findViewById(R.id.joinBuy_fanamt_num_ll);
        this.mFanAmtTabBottom = (PdAutoChangeTextSize) view.findViewById(R.id.tx_joinBuy_fanamt_bottom);
        this.mJoinBuyBumBottom = (TextView) view.findViewById(R.id.tx_joinBuy_num_bottom);
        PdAutoChangeTextSize pdAutoChangeTextSize2 = (PdAutoChangeTextSize) view.findViewById(R.id.tv_jd_price);
        this.mJdPrice = pdAutoChangeTextSize2;
        FontsUtil.changeTextFont(pdAutoChangeTextSize2, 4097);
        this.mStockContainer = (FrameLayout) view.findViewById(R.id.detail_stock_container);
        this.mStockNum = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_stock_num);
        this.mSkuIdWrap = (LinearLayout) view.findViewById(R.id.ll_skuid_wrap);
        this.mProductWeight = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_weight);
        this.mProductSkuId = (PdAutoChangeTextSize) view.findViewById(R.id.detail_style_skuid);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.detail_style_icon);
        this.mProductIcon = simpleDraweeView;
        simpleDraweeView.setOnClickListener(this);
        this.mProductSkuId.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoView.1
            {
                LibPdStyleInfoView.this = this;
            }

            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                if (!LibPdStyleInfoView.this.isActivityFinish() && LibPdStyleInfoView.this.mSkuIdWrap != null) {
                    int left = ((LibPdStyleInfoView.this.mSkuIdWrap.getLeft() + view2.getLeft()) + (view2.getWidth() / 2)) - (PDUtils.dip2px(100.0f) / 2);
                    int top = LibPdStyleInfoView.this.mSkuIdWrap.getTop();
                    if (LibPdStyleInfoView.this.mProductSkuId != null && ((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct != null) {
                        LibPdStyleInfoView libPdStyleInfoView = LibPdStyleInfoView.this;
                        libPdStyleInfoView.mCopyWindow = PDUtils.showPopUp(((PDBaseRelativeView) libPdStyleInfoView).mContext, ((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.skuId, LibPdStyleInfoView.this.mProductSkuId, ((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.skuId, ((PDBaseRelativeView) LibPdStyleInfoView.this).mContext.getResources().getString(R.string.libpdstyleinfoview_sku_copy), left, top);
                    }
                }
                return true;
            }
        });
    }

    public boolean isActivityFinish() {
        Activity activity = this.mActivity;
        return activity != null && activity.isFinishing();
    }

    private boolean isYuyueHidePrice() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isHidePrice()) ? false : true;
    }

    private void setDarkTheme() {
        ProductDetailEntity productDetailEntity = this.mProduct;
        if (productDetailEntity != null) {
            productDetailEntity.isDarkTheme();
        }
        int i2 = R.color.libpdstyleinfoview_white;
        int colorWithTheme = getColorWithTheme(i2, R.color.libpdstyleinfoview_color_1d1b1b);
        View view = this.mTitleContainer;
        ProductDetailEntity productDetailEntity2 = this.mProduct;
        view.setBackgroundResource(PDUtils.getColorWithTheme(productDetailEntity2 != null && productDetailEntity2.isDarkTheme(), R.drawable.lib_pd_common_dialog_bg, R.drawable.lib_pd_common_dialog_bg_dark));
        this.mScrollView.setBackgroundColor(colorWithTheme);
        this.mBottomLinear.setBackgroundColor(colorWithTheme);
        int colorWithTheme2 = getColorWithTheme(R.color.libpdstyleinfoview_color_8C8C8C, R.color.libpdstyleinfoview_color_848383);
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mProductWeight;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setTextColor(colorWithTheme2);
        }
        PdAutoChangeTextSize pdAutoChangeTextSize2 = this.mProductSkuId;
        if (pdAutoChangeTextSize2 != null) {
            pdAutoChangeTextSize2.setTextColor(colorWithTheme2);
        }
        this.mJingXiPrice.setTextColor(colorWithTheme2);
        int colorWithTheme3 = getColorWithTheme(R.color.libpdstyleinfoview_color_f2270c, R.color.libpdstyleinfoview_color_ff3826);
        PdAutoChangeTextSize pdAutoChangeTextSize3 = this.mStockNum;
        if (pdAutoChangeTextSize3 != null) {
            pdAutoChangeTextSize3.setTextColor(colorWithTheme3);
        }
        this.mJoinBuyBum.setTextColor(colorWithTheme3);
        this.mFanAmtTab.setTextColor(colorWithTheme3);
        this.mChannelTag.setTextColor(colorWithTheme3);
        PdAutoChangeTextSize pdAutoChangeTextSize4 = this.mFanAmtTabBottom;
        if (pdAutoChangeTextSize4 != null) {
            pdAutoChangeTextSize4.setTextColor(colorWithTheme3);
        }
        TextView textView = this.mJoinBuyBumBottom;
        if (textView != null) {
            textView.setTextColor(colorWithTheme3);
        }
        getColorWithTheme(i2, R.color.libpdstyleinfoview_color_ececec);
        this.bottomDivider.setBackgroundColor(getColorWithTheme(i2, R.color.libpdstyleinfoview_color_302E2E));
    }

    private void setDoublePriceImage(final SecondPriceEntity secondPriceEntity) {
        JDImageUtils.loadImage(secondPriceEntity.simplePriceIcon, new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoView.4
            {
                LibPdStyleInfoView.this = this;
            }

            private void isRangePrice() {
                if (LibPdStyleInfoView.this.mDoublePriceLayout == null || LibPdStyleInfoView.this.mSecondPriceInfo == null) {
                    return;
                }
                RelativeLayout.LayoutParams layoutParams = LibPdStyleInfoView.this.mDoublePriceLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) LibPdStyleInfoView.this.mDoublePriceLayout.getLayoutParams() : null;
                if (((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.isDoublePriceBetween()) {
                    if (LibPdStyleInfoView.this.mDoubleBetweenMaxText.getVisibility() == 8) {
                        LibPdStyleInfoView.this.mDoubleBetweenMaxText.setVisibility(0);
                    }
                    if (LibPdStyleInfoView.this.mDoubleBetweenSymbol.getVisibility() == 8) {
                        LibPdStyleInfoView.this.mDoubleBetweenSymbol.setVisibility(0);
                    }
                    LibPdStyleInfoView.this.mDoublePrice.setText(PDUtils.formatPriceForYjf(LibPdStyleInfoView.this.mSecondPriceInfo.upMinPrice));
                    LibPdStyleInfoView.this.mDoublePrice.setTextColor(PDUtils.parseColor(LibPdStyleInfoView.this.mSecondPriceInfo.simplePriceColor));
                    LibPdStyleInfoView.this.mDoubleBetweenSymbol.setText("~");
                    LibPdStyleInfoView.this.mDoubleBetweenSymbol.setTextColor(PDUtils.parseColor(LibPdStyleInfoView.this.mSecondPriceInfo.simplePriceColor));
                    LibPdStyleInfoView.this.mDoubleBetweenMaxText.setText(PDUtils.formatPriceForYjf(LibPdStyleInfoView.this.mSecondPriceInfo.upMaxPrice));
                    LibPdStyleInfoView.this.mDoubleBetweenMaxText.setTextColor(PDUtils.parseColor(LibPdStyleInfoView.this.mSecondPriceInfo.simplePriceColor));
                    if (layoutParams != null) {
                        LibPdStyleInfoView.this.setDoublePriceLocation(layoutParams);
                    }
                } else {
                    if (LibPdStyleInfoView.this.mDoubleBetweenMaxText.getVisibility() == 0) {
                        LibPdStyleInfoView.this.mDoubleBetweenMaxText.setVisibility(8);
                    }
                    if (LibPdStyleInfoView.this.mDoubleBetweenSymbol.getVisibility() == 0) {
                        LibPdStyleInfoView.this.mDoubleBetweenSymbol.setVisibility(8);
                    }
                    LibPdStyleInfoView.this.mDoublePrice.setText(PDUtils.formatPrice(LibPdStyleInfoView.this.mSecondPriceInfo.secondPrice));
                    LibPdStyleInfoView.this.mDoublePrice.setTextColor(PDUtils.parseColor(LibPdStyleInfoView.this.mSecondPriceInfo.simplePriceColor));
                    if (layoutParams != null) {
                        if (((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.isMainPriceBetween()) {
                            LibPdStyleInfoView.this.setDoublePriceLocation(layoutParams);
                        } else {
                            layoutParams.addRule(1, LibPdStyleInfoView.this.mPriceUpLayout.getId());
                            layoutParams.addRule(4, LibPdStyleInfoView.this.mProductPrice.getId());
                            layoutParams.addRule(3, 0);
                            layoutParams.addRule(8, 0);
                            layoutParams.leftMargin = PDUtils.dip2px(10.0f);
                        }
                    }
                }
                if (layoutParams != null) {
                    LibPdStyleInfoView.this.mDoublePriceLayout.setLayoutParams(layoutParams);
                }
            }

            private void qiIcon() {
                if (LibPdStyleInfoView.this.mPriceUpLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) LibPdStyleInfoView.this.mPriceUpLayout.getLayoutParams();
                    if (((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.isMainPriceBetween()) {
                        layoutParams.addRule(1, LibPdStyleInfoView.this.mBetweenLayout.getId());
                        layoutParams.addRule(8, LibPdStyleInfoView.this.mBetweenLayout.getId());
                    } else {
                        layoutParams.addRule(1, LibPdStyleInfoView.this.mProductPrice.getId());
                        layoutParams.addRule(8, LibPdStyleInfoView.this.mProductPrice.getId());
                    }
                    LibPdStyleInfoView.this.mPriceUpLayout.setLayoutParams(layoutParams);
                }
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                if (LibPdStyleInfoView.this.isActivityFinish()) {
                    return;
                }
                if (bitmap != null && !bitmap.isRecycled() && secondPriceEntity != null) {
                    qiIcon();
                    if (secondPriceEntity.busiType == 1) {
                        LibPdStyleInfoView.this.mDoublePrice.setVisibility(8);
                        LibPdStyleInfoView.this.mDoubleBetweenMaxText.setVisibility(8);
                        LibPdStyleInfoView.this.mDoubleBetweenSymbol.setVisibility(8);
                        if (LibPdStyleInfoView.this.priceUpParams != null) {
                            LibPdStyleInfoView.this.priceUpParams.setMargins(PDUtils.dip2px(6.0f), 0, 0, 0);
                            LibPdStyleInfoView.this.priceUpParams.height = DPIUtil.dip2px(12.0f);
                        }
                        LibPdStyleInfoView.this.mPriceUp.setVisibility(0);
                        LibPdStyleInfoView.this.mPriceUp.setImageBitmap(bitmap);
                    } else {
                        LibPdStyleInfoView.this.mDoublePriceImage.setVisibility(0);
                        LibPdStyleInfoView.this.mDoublePriceImage.setImageBitmap(bitmap);
                        if (!TextUtils.isEmpty(secondPriceEntity.secondPrice)) {
                            LibPdStyleInfoView.this.mDoublePriceLayout.setVisibility(0);
                            LibPdStyleInfoView.this.mDoublePrice.setVisibility(0);
                            LibPdStyleInfoView.this.mDoublePrice.setTextColor(PDUtils.parseColor(secondPriceEntity.simplePriceColor));
                        } else {
                            LibPdStyleInfoView.this.mDoublePriceLayout.setVisibility(8);
                            LibPdStyleInfoView.this.mDoublePrice.setVisibility(8);
                            LibPdStyleInfoView.this.mDoubleBetweenMaxText.setVisibility(8);
                            LibPdStyleInfoView.this.mDoubleBetweenSymbol.setVisibility(8);
                        }
                    }
                    isRangePrice();
                }
                LibPdStyleInfoView.this.setSecondPriceLayoutFlod(bitmap);
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                if (LibPdStyleInfoView.this.isActivityFinish()) {
                    return;
                }
                LibPdStyleInfoView.this.mDoublePriceLayout.setVisibility(8);
                LibPdStyleInfoView.this.mDoublePriceImage.setVisibility(8);
                LibPdStyleInfoView.this.mDoubleBetweenMaxText.setVisibility(8);
                LibPdStyleInfoView.this.mDoubleBetweenSymbol.setVisibility(8);
                LibPdStyleInfoView.this.setSecondPriceLayoutFlod(null);
            }
        });
    }

    public void setDoublePriceLocation(RelativeLayout.LayoutParams layoutParams) {
        if (this.mProductPrice.getVisibility() == 0) {
            layoutParams.addRule(3, this.mProductPrice.getId());
        }
        if (this.mBetweenLayout.getVisibility() == 0) {
            layoutParams.addRule(3, this.mBetweenLayout.getId());
        }
        layoutParams.addRule(1, 0);
        layoutParams.addRule(4, 0);
        layoutParams.leftMargin = 0;
    }

    private void setPriceContentDes(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        Context context = this.mContext;
        sb.append(context != null ? context.getString(R.string.libpdstyleinfoview_money_unit) : "");
        this.mProductPrice.setContentDescription(sb);
    }

    public void setSecondPriceLayoutFlod(final Bitmap bitmap) {
        this.mDetailLayout.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoView.5
            {
                LibPdStyleInfoView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (LibPdStyleInfoView.this.isActivityFinish()) {
                    return;
                }
                int measuredWidth = LibPdStyleInfoView.this.mProductPrice.getMeasuredWidth();
                int dip2px = PDUtils.dip2px(12.0f);
                int measuredWidth2 = LibPdStyleInfoView.this.mDoublePrice.getMeasuredWidth();
                int dip2px2 = PDUtils.dip2px(62.0f);
                Bitmap bitmap2 = bitmap;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    dip2px2 = bitmap.getWidth();
                }
                if ((((((((PDUtils.getWidth() - (LibPdStyleInfoView.this.mProductIcon != null ? LibPdStyleInfoView.this.mProductIcon.getMeasuredWidth() : 0)) - PDUtils.dip2px(15.0f)) - PDUtils.dip2px(15.0f)) - measuredWidth) - dip2px) - measuredWidth2) - dip2px2) - PDUtils.dip2px(1.0f) > 0 || LibPdStyleInfoView.this.doublePriceLayoutParams == null || LibPdStyleInfoView.this.mDoublePriceLayout == null) {
                    return;
                }
                LibPdStyleInfoView.this.doublePriceLayoutParams.setMargins(0, 0, 0, 0);
                LibPdStyleInfoView.this.doublePriceLayoutParams.addRule(3, LibPdStyleInfoView.this.mProductPrice.getId());
                LibPdStyleInfoView.this.doublePriceLayoutParams.addRule(4, 0);
                LibPdStyleInfoView.this.doublePriceLayoutParams.addRule(1, 0);
                LibPdStyleInfoView.this.mDoublePriceLayout.setLayoutParams(LibPdStyleInfoView.this.doublePriceLayoutParams);
            }
        });
    }

    private void setYuyueHidePriceText() {
        String str;
        if (this.mContext == null) {
            return;
        }
        if (TextUtils.isEmpty(this.mProduct.mWareBusinessData.yuyueInfo.hideText)) {
            str = "\u00a5 " + this.mContext.getString(R.string.libpdstyleinfoview_yuyue_hide_price_txt);
        } else {
            str = "\u00a5 " + this.mProduct.mWareBusinessData.yuyueInfo.hideText;
        }
        FontsUtil.changeTextFont(this.mProductPrice, 4099);
        this.mProductPrice.setText(this.mProduct.mWareBusinessData.yuyueInfo.formatHidePriceText(str));
    }

    private void showColorSize(PDStyleEntity pDStyleEntity) {
        List<PDStyleFilterEntity> list;
        if (pDStyleEntity != null && (list = pDStyleEntity.colorSize) != null && !list.isEmpty()) {
            if (this.mStyleSizeView == null) {
                PDStyleSizeView pDStyleSizeView = (PDStyleSizeView) ((ViewStub) findViewById(R.id.detail_style_size_container)).inflate();
                this.mStyleSizeView = pDStyleSizeView;
                pDStyleSizeView.setPdStyleSizeViewListner(this);
            }
            this.mStyleSizeView.setStyleUtil(this.mLibPdStyleInfoViewUtils);
            this.mStyleSizeView.initParamData(this.mProduct);
            this.mStyleSizeView.setVisibility(0);
            this.mStyleSizeView.bindData2View();
            return;
        }
        PDStyleSizeView pDStyleSizeView2 = this.mStyleSizeView;
        if (pDStyleSizeView2 != null) {
            pDStyleSizeView2.setVisibility(8);
        }
    }

    private void showCountView() {
        if (this.mProduct.isNumHide()) {
            PDStyleCountView pDStyleCountView = this.mStyleCountView;
            if (pDStyleCountView != null) {
                pDStyleCountView.setVisibility(8);
                return;
            }
            return;
        }
        if (this.mStyleCountView == null) {
            this.mStyleCountView = (PDStyleCountView) ((ViewStub) findViewById(R.id.libpdstyleinfoview_count_container)).inflate();
        }
        this.mStyleCountView.setVisibility(0);
        this.mStyleCountView.initParamData(this.mProduct);
        this.mStyleCountView.bindData2View(false, this.mProduct.getStockNum());
    }

    private void showDoublePrice() {
        this.priceUpParams = null;
        if (this.mPriceUp.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPriceUp.getLayoutParams();
            this.priceUpParams = layoutParams;
            layoutParams.setMargins(PDUtils.dip2px(4.0f), 0, 0, 0);
            this.priceUpParams.height = -2;
        }
        this.doublePriceLayoutParams = null;
        if (this.mDoublePriceLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mDoublePriceLayout.getLayoutParams();
            this.doublePriceLayoutParams = layoutParams2;
            layoutParams2.setMargins(PDUtils.dip2px(10.0f), 0, 0, 0);
            this.doublePriceLayoutParams.addRule(4, this.mProductPrice.getId());
            this.doublePriceLayoutParams.addRule(1, this.mPriceUpLayout.getId());
            this.mDoublePriceLayout.setLayoutParams(this.doublePriceLayoutParams);
        }
        this.mPriceUp.setVisibility(8);
        this.mDoublePriceImage.setVisibility(8);
        this.mDoublePrice.setVisibility(8);
        this.mDoublePriceLabel.setVisibility(8);
        WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
        if (wareBusinessData == null || wareBusinessData.secondPriceInfo == null) {
            return;
        }
        this.mProductPrice.setTextSize(2, 24.0f);
        this.mDoublePrice.setTextSize(2, 15.0f);
        RelativeLayout.LayoutParams layoutParams3 = this.doublePriceLayoutParams;
        if (layoutParams3 != null) {
            layoutParams3.setMargins(PDUtils.dip2px(12.0f), 0, 0, 0);
        }
        SecondPriceEntity secondPriceEntity = this.mProduct.mWareBusinessData.secondPriceInfo;
        this.mSecondPriceInfo = secondPriceEntity;
        int i2 = secondPriceEntity.busiType;
        if (i2 == 2 || i2 == 10) {
            return;
        }
        setDoublePriceImage(secondPriceEntity);
    }

    private void showJdSerPlus(WareJdServerPlusEntity wareJdServerPlusEntity) {
        List<WareBusinessPlusListEntity> list;
        if (wareJdServerPlusEntity != null && (list = wareJdServerPlusEntity.jdSerPlusList) != null && !list.isEmpty()) {
            if (this.mStyleJdServicePlusView == null) {
                View findViewById = findViewById(R.id.detail_style_jdservice_container);
                if (findViewById instanceof ViewStub) {
                    View inflate = ((ViewStub) findViewById).inflate();
                    if (inflate instanceof PDStyleJdServicePlusView) {
                        this.mStyleJdServicePlusView = (PDStyleJdServicePlusView) inflate;
                    }
                }
            }
            PDStyleJdServicePlusView pDStyleJdServicePlusView = this.mStyleJdServicePlusView;
            if (pDStyleJdServicePlusView != null) {
                pDStyleJdServicePlusView.setVisibility(0);
                this.mStyleJdServicePlusView.initParamData(this.mProduct);
                this.mStyleJdServicePlusView.bindData2View(wareJdServerPlusEntity);
                return;
            }
            return;
        }
        PDStyleJdServicePlusView pDStyleJdServicePlusView2 = this.mStyleJdServicePlusView;
        if (pDStyleJdServicePlusView2 != null) {
            pDStyleJdServicePlusView2.removeAllService();
            this.mStyleJdServicePlusView.setVisibility(8);
        }
    }

    private void showPrice() {
        this.mPriceUp.setVisibility(8);
        this.mJoinBuyBum.setVisibility(8);
        PdAutoChangeTextSize pdAutoChangeTextSize = this.mJingXiPrice;
        if (pdAutoChangeTextSize != null) {
            pdAutoChangeTextSize.setVisibility(8);
        }
        TextView textView = this.mChannelTag;
        if (textView != null) {
            textView.setVisibility(8);
        }
        PdAutoChangeTextSize pdAutoChangeTextSize2 = this.mJdPrice;
        if (pdAutoChangeTextSize2 != null) {
            pdAutoChangeTextSize2.setVisibility(8);
        }
        this.mDoublePriceLayout.setVisibility(8);
        FontsUtil.changeTextFont(this.mProductPrice, 4097);
        if (this.mProduct.getPDInstallmentInfoEntity() != null) {
            this.mProductPrice.setText(getInstallmentPriceSpan(this.mProduct.getMonthlyPay()));
        } else if (this.mProduct.isYuShou()) {
            SpannableString spannableString = new SpannableString(this.mProduct.getYuShouPrice());
            handlePriceStyle(this.mProduct.getYuShouPrice(), spannableString);
            this.mProductPrice.setText(spannableString);
        } else {
            WareBusinessData wareBusinessData = this.mProduct.mWareBusinessData;
            if (wareBusinessData != null && wareBusinessData.joinBuyInfo != null) {
                PdAutoChangeTextSize pdAutoChangeTextSize3 = this.mJdPrice;
                if (pdAutoChangeTextSize3 != null) {
                    pdAutoChangeTextSize3.setVisibility(0);
                }
                this.mJoinBuyBum.setVisibility(0);
                this.mJoinBuyBum.setText(this.mProduct.mWareBusinessData.joinBuyInfo.tuanMemberCount + this.mContext.getString(R.string.libpdstyleinfoview_collage_banner_count));
                WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity = this.mProduct.mWareBusinessData.joinBuyInfo;
                if (wareBusinessCollageJoinBuyInfoEntity.jingxiFlag) {
                    if (TextUtils.isEmpty(wareBusinessCollageJoinBuyInfoEntity.fanAmt)) {
                        this.mFanAmtTab.setVisibility(8);
                    } else {
                        this.mFanAmtTab.setVisibility(0);
                        this.mFanAmtTab.setText(this.mProduct.mWareBusinessData.joinBuyInfo.fanAmt);
                    }
                } else {
                    this.mFanAmtTab.setVisibility(8);
                    PdAutoChangeTextSize pdAutoChangeTextSize4 = this.mJingXiPrice;
                    if (pdAutoChangeTextSize4 != null) {
                        pdAutoChangeTextSize4.setVisibility(8);
                    }
                    PdAutoChangeTextSize pdAutoChangeTextSize5 = this.mFanAmtTabBottom;
                    if (pdAutoChangeTextSize5 != null) {
                        pdAutoChangeTextSize5.setVisibility(8);
                    }
                }
                String charSequence = PDUtils.formatPrice(this.mProduct.mWareBusinessData.getJoinBuyPrice()).toString();
                SpannableString spannableString2 = new SpannableString(charSequence);
                Context context = this.mContext;
                int i2 = R.color.libpdstyleinfoview_color_FD0E46;
                spannableString2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, i2)), 0, charSequence.length(), 33);
                handlePriceStyle(charSequence, spannableString2);
                this.mProductPrice.setText(spannableString2);
                this.mProductPrice.setTextColor(ContextCompat.getColor(this.mContext, i2));
                String emptyPrice = PDUtils.getEmptyPrice(this.mProduct.getJdPrice(), this.mProduct.mWareBusinessData);
                if (!TextUtils.equals(emptyPrice, this.mContext.getString(R.string.libpdstyleinfoview_pd_no_price)) && !PDUtils.isEmptyPrice(this.mProduct.mWareBusinessData)) {
                    String formatPrice = PDUtils.formatPrice(emptyPrice);
                    PdAutoChangeTextSize pdAutoChangeTextSize6 = this.mJdPrice;
                    if (pdAutoChangeTextSize6 != null) {
                        pdAutoChangeTextSize6.setText(formatPrice.toString());
                    }
                    this.mJingXiPrice.setText(this.mContext.getResources().getString(R.string.libpdstyleinfoview_jingxi_price) + formatPrice.toString());
                } else {
                    PdAutoChangeTextSize pdAutoChangeTextSize7 = this.mJdPrice;
                    if (pdAutoChangeTextSize7 != null) {
                        pdAutoChangeTextSize7.setText(emptyPrice);
                    }
                    this.mJingXiPrice.setText(emptyPrice);
                }
                if (this.mProduct.mWareBusinessData.joinBuyInfo.jingxiFlag) {
                    this.mJingXiPrice.setVisibility(0);
                    PdAutoChangeTextSize pdAutoChangeTextSize8 = this.mJdPrice;
                    if (pdAutoChangeTextSize8 != null) {
                        pdAutoChangeTextSize8.setVisibility(8);
                    }
                }
                this.mDetailLayout.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.styleinfoview.LibPdStyleInfoView.3
                    {
                        LibPdStyleInfoView.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (LibPdStyleInfoView.this.isActivityFinish() || LibPdStyleInfoView.this.mDetailLayout == null || LibPdStyleInfoView.this.mProductPrice == null || LibPdStyleInfoView.this.mJoinBuyBum == null || LibPdStyleInfoView.this.mFanAmtTab == null) {
                            return;
                        }
                        if (LibPdStyleInfoView.this.mDetailLayout.getMeasuredWidth() - (((LibPdStyleInfoView.this.mProductPrice.getMeasuredWidth() + LibPdStyleInfoView.this.mJoinBuyBum.getMeasuredWidth()) + LibPdStyleInfoView.this.mFanAmtTab.getMeasuredWidth()) + PDUtils.dip2px(12.0f)) <= 0) {
                            LibPdStyleInfoView.this.mJoinBuyBum.setVisibility(8);
                            LibPdStyleInfoView.this.mFanAmtTab.setVisibility(8);
                            if (LibPdStyleInfoView.this.mJoinBuyBumBottom == null || LibPdStyleInfoView.this.mJoinBuyNumFanLL == null) {
                                return;
                            }
                            LibPdStyleInfoView.this.mJoinBuyNumFanLL.setVisibility(0);
                            LibPdStyleInfoView.this.mJoinBuyBumBottom.setText(((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.mWareBusinessData.joinBuyInfo.tuanMemberCount + ((PDBaseRelativeView) LibPdStyleInfoView.this).mContext.getString(R.string.libpdstyleinfoview_collage_banner_count));
                            if (!((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.mWareBusinessData.joinBuyInfo.jingxiFlag || LibPdStyleInfoView.this.mFanAmtTabBottom == null) {
                                return;
                            }
                            if (TextUtils.isEmpty(((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.mWareBusinessData.joinBuyInfo.fanAmt)) {
                                LibPdStyleInfoView.this.mFanAmtTabBottom.setVisibility(8);
                                return;
                            }
                            LibPdStyleInfoView.this.mFanAmtTabBottom.setVisibility(0);
                            LibPdStyleInfoView.this.mFanAmtTabBottom.setText(((PDBaseRelativeView) LibPdStyleInfoView.this).mProduct.mWareBusinessData.joinBuyInfo.fanAmt);
                        }
                    }
                });
            } else if (isYuyueHidePrice()) {
                setYuyueHidePriceText();
            } else {
                defaulePrice();
            }
        }
        PDManager.getInstances(this.mManagerKey).getThemeHelper().setPriceTextColor(this.mContext, this.mProduct.getFloorTheme(), this.mProductPrice);
        showStockAndHandPrice();
    }

    private void showServiceView(boolean z, WareYanBaoEntity wareYanBaoEntity) {
        List<WareBusinessYanBaoGroupEntity> list;
        if (!z && wareYanBaoEntity != null && (list = wareYanBaoEntity.yanBaoList) != null && !list.isEmpty()) {
            if (this.mServiceView == null) {
                this.mServiceView = (PDStyleServiceView) ((ViewStub) findViewById(R.id.libpdstyleinfoview_detail_style_service_container)).inflate();
            }
            this.mServiceView.setVisibility(0);
            this.mServiceView.initParamData(this.mProduct);
            this.mServiceView.bindData2View(wareYanBaoEntity, false);
            return;
        }
        PDStyleServiceView pDStyleServiceView = this.mServiceView;
        if (pDStyleServiceView != null) {
            pDStyleServiceView.removeAllService();
            this.mServiceView.setVisibility(8);
        }
    }

    private void showStockAndHandPrice() {
        if (!TextUtils.isEmpty(this.mProduct.getStockNum())) {
            FrameLayout frameLayout = this.mStockContainer;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
                this.mStockContainer.setPadding(0, 0, 0, 0);
            }
            this.mToHandPriceContainer.setVisibility(8);
            PdAutoChangeTextSize pdAutoChangeTextSize = this.mStockNum;
            if (pdAutoChangeTextSize != null) {
                pdAutoChangeTextSize.setVisibility(0);
                this.mStockNum.setText(this.mProduct.getStockNum());
                PDManager.getInstances(this.mManagerKey).getThemeHelper().setTextViewColor(this.mContext, this.mProduct.getFloorTheme(), this.mStockNum);
            }
        } else if (this.mProduct.isShowStyleHandPrice()) {
            FrameLayout frameLayout2 = this.mStockContainer;
            if (frameLayout2 != null) {
                frameLayout2.setVisibility(0);
                this.mStockContainer.setPadding(0, 0, 0, PDUtils.dip2px(7.0f));
            }
            this.mToHandPriceContainer.setVisibility(0);
            PdAutoChangeTextSize pdAutoChangeTextSize2 = this.mStockNum;
            if (pdAutoChangeTextSize2 != null) {
                pdAutoChangeTextSize2.setVisibility(8);
            }
            this.mHandPriceTitle.setText(this.mProduct.mWareBusinessData.toHandssSrengthen.predictText);
            this.mHandPrice.setText(this.mProduct.mWareBusinessData.toHandssSrengthen.toHandsPrice);
            int color = ContextCompat.getColor(this.mContext, this.mProduct.isDarkTheme() ? R.color.libpdstyleinfoview_color_ff3826 : R.color.libpdstyleinfoview_color_f2270c);
            this.mHandPriceTitle.setTextColor(color);
            this.mHandPrice.setTextColor(color);
            String str = this.mProduct.mWareBusinessData.toHandssSrengthen.icon;
            if (!TextUtils.isEmpty(str)) {
                this.mHandPriceIcon.setVisibility(0);
                JDImageUtils.displayImage(str, this.mHandPriceIcon);
                return;
            }
            this.mHandPriceIcon.setVisibility(8);
        } else {
            FrameLayout frameLayout3 = this.mStockContainer;
            if (frameLayout3 != null) {
                frameLayout3.setVisibility(8);
            }
        }
    }

    public void bindData2View(IMyActivity iMyActivity, LibPdStyleInfoViewCarListener libPdStyleInfoViewCarListener, LibPdStyleInfoViewBottomBtnClickListener libPdStyleInfoViewBottomBtnClickListener) {
        Context context = this.mContext;
        if (context instanceof Activity) {
            this.mActivity = (Activity) context;
        }
        this.mIMyActivity = iMyActivity;
        this.mCarListener = libPdStyleInfoViewCarListener;
        this.mIsClickNetError = false;
        bindTopData2View();
        setDarkTheme();
        showPrice();
        showColorSize(this.mProduct.getColorSizeInfo());
        showCountView();
        showServiceView(this.mProduct.isYanbaoShield(), this.mProduct.getWareYanBaoEntity());
        showJdSerPlus(this.mProduct.getWareJdServerPlusEntity());
        new LibPdBottomBtnHolder(this.mActivity, this.mIMyActivity, this.mProduct, this.mLeftBtn, this.mRightBtn, this.mCarListener, this.mLibPdStyleInfoViewUtils, libPdStyleInfoViewBottomBtnClickListener);
    }

    protected int getColorWithTheme(@ColorRes int i2, @ColorRes int i3) {
        ProductDetailEntity productDetailEntity = this.mProduct;
        return PDUtils.getColorWithTheme(productDetailEntity != null && productDetailEntity.isDarkTheme(), ContextCompat.getColor(getContext(), i2), ContextCompat.getColor(getContext(), i3));
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseRelativeView
    protected void initView() {
        this.mTitleLayoutFrameB = (FrameLayout) findViewById(R.id.libpdstyleinfoview_detail_style_title_layout_frame_b);
        this.mScrollView = (PDScrollView) findViewById(R.id.detail_style_scroll);
        ViewStub viewStub = (ViewStub) findViewById(R.id.libpdstyleinfoview_detail_style_title_layout_b);
        this.mLoadingView = (FrameLayout) findViewById(R.id.libpdstyleinfoview_loading_layout);
        this.mBottomLinear = (LinearLayout) findViewById(R.id.libpdstyleinfoview_detail_style_bottom_layout);
        this.mLeftBtn = (TextView) findViewById(R.id.libpdstyleinfoview_detail_style_left_btn);
        this.mRightBtn = (TextView) findViewById(R.id.libpdstyleinfoview_detail_style_right_btn);
        FontsUtil.changeTextFont(this.mLeftBtn, 4097);
        FontsUtil.changeTextFont(this.mRightBtn, 4097);
        this.bottomDivider = findViewById(R.id.libpdstyleinfoview_bottom_divider);
        this.mNetworkLayout = findViewById(R.id.libpdstyleinfoview_network_layout);
        TextView textView = (TextView) findViewById(R.id.libpdstyleinfoview_network_re_text);
        this.mNetReLayout = textView;
        textView.setOnClickListener(this);
        if (this.mProductPrice == null) {
            inflatTopViewB(viewStub.inflate());
            FrameLayout frameLayout = this.mTitleLayoutFrameB;
            if (frameLayout != null) {
                frameLayout.setVisibility(0);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LibPdStyleInfoViewUtils libPdStyleInfoViewUtils;
        ProductDetailEntity productDetailEntity;
        int id = view.getId();
        if (R.id.detail_style_cancel == id) {
            LibPdStyleInfoViewUtils libPdStyleInfoViewUtils2 = this.mLibPdStyleInfoViewUtils;
            if (libPdStyleInfoViewUtils2 != null) {
                libPdStyleInfoViewUtils2.dismiss();
            }
        } else if (R.id.libpdstyleinfoview_network_re_text != id || (libPdStyleInfoViewUtils = this.mLibPdStyleInfoViewUtils) == null || (productDetailEntity = this.mProduct) == null) {
        } else {
            this.mIsClickNetError = true;
            libPdStyleInfoViewUtils.requestSku(productDetailEntity.skuId, productDetailEntity.source);
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDBaseRelativeView, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mDisplayImageOptions = new JDDisplayImageOptions().displayer(new JDRoundedBitmapDisplayer(12));
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleSizeView.PdStyleSizeViewListner
    public void selectSizeView(PdSelectEntity pdSelectEntity) {
    }

    public void setListener(LibPdStyleInfoViewUtils libPdStyleInfoViewUtils) {
        this.mLibPdStyleInfoViewUtils = libPdStyleInfoViewUtils;
    }

    public void setNetErrorLayout(boolean z) {
        if (z) {
            this.mNetworkLayout.setVisibility(0);
            this.mScrollView.setVisibility(8);
            this.mBottomLinear.setVisibility(8);
            this.bottomDivider.setVisibility(8);
            View view = this.mTitleContainer;
            if (view != null) {
                view.setVisibility(8);
            }
            SimpleDraweeView simpleDraweeView = this.mProductIcon;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
                return;
            }
            return;
        }
        this.mNetworkLayout.setVisibility(8);
        this.mScrollView.setVisibility(0);
        this.mBottomLinear.setVisibility(0);
        this.bottomDivider.setVisibility(0);
        View view2 = this.mTitleContainer;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        SimpleDraweeView simpleDraweeView2 = this.mProductIcon;
        if (simpleDraweeView2 != null) {
            simpleDraweeView2.setVisibility(0);
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.views.PDStyleSizeView.PdStyleSizeViewListner
    public void setProgressLoading(boolean z, boolean z2) {
        this.mLoadingView.setVisibility(z ? 0 : 8);
        if (z2) {
            PDStyleCountView pDStyleCountView = this.mStyleCountView;
            if (pDStyleCountView != null) {
                pDStyleCountView.setVisibility(z ? 8 : 0);
            }
            PDStyleSizeView pDStyleSizeView = this.mStyleSizeView;
            if (pDStyleSizeView != null) {
                pDStyleSizeView.setVisibility(z ? 8 : 0);
            }
            View view = this.mTitleContainer;
            if (view != null) {
                view.setVisibility(z ? 8 : 0);
            }
            SimpleDraweeView simpleDraweeView = this.mProductIcon;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(z ? 8 : 0);
            }
        }
    }

    public LibPdStyleInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }
}
