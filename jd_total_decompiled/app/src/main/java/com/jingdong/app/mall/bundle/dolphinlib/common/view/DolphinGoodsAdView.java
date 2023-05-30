package com.jingdong.app.mall.bundle.dolphinlib.common.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsAdEntity;

/* loaded from: classes19.dex */
public class DolphinGoodsAdView extends RelativeLayout {
    private RelativeLayout mAdLayout;
    private BabelScope mBabelScope;
    private ImageWraper mCarImage;
    private ImageWraper mCardBg;
    private LinearLayout mCardLayout;
    private Context mContext;
    private ImageWraper mGoodsImage;
    private TextView mGoodsTagText;
    private TextView mGoodsTitle;
    private TextView mHandPrice;
    private TextView mLinePrice;
    private DolphinMtaEntity mMtaEntity;
    private int resBg;

    public DolphinGoodsAdView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_goods_ad_card, (ViewGroup) this, true);
        this.mAdLayout = (RelativeLayout) findViewById(R.id.ad_layout);
        this.mCardBg = (ImageWraper) findViewById(R.id.card_bg);
        this.mCardLayout = (LinearLayout) findViewById(R.id.card_layout);
        this.mGoodsImage = (ImageWraper) findViewById(R.id.goods_image);
        this.mGoodsTagText = (TextView) findViewById(R.id.goods_tag_text);
        this.mGoodsTitle = (TextView) findViewById(R.id.goods_title);
        this.mHandPrice = (TextView) findViewById(R.id.goods_hand_price);
        this.mLinePrice = (TextView) findViewById(R.id.goods_line_price);
        this.mCarImage = (ImageWraper) findViewById(R.id.add_car_image);
        setTag("AD_CARD");
    }

    public void setLayoutSize(int i2, int i3) {
        if (this.mCardBg != null) {
            this.mCardBg.setLayoutParams(new RelativeLayout.LayoutParams(i2, i3));
        }
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }

    public void update(@NonNull final GoodsAdEntity goodsAdEntity, int i2) {
        if (!TextUtils.isEmpty(goodsAdEntity.cardBg)) {
            CommonServiceUtil.displayImage(goodsAdEntity.cardBg, this.mCardBg);
        } else if (i2 > 0) {
            this.mAdLayout.setBackgroundResource(i2);
        }
        if (goodsAdEntity.goods != null) {
            CommonServiceUtil.displayImage(goodsAdEntity.adImage, this.mGoodsImage);
            if (!TextUtils.isEmpty(goodsAdEntity.goods.tag)) {
                this.mGoodsTagText.setText(goodsAdEntity.goods.tag);
                this.mGoodsTagText.setVisibility(0);
            } else {
                this.mGoodsTagText.setVisibility(8);
            }
            this.mGoodsTitle.setText(goodsAdEntity.goods.name);
            String str = " \uff1f";
            String str2 = !TextUtils.isEmpty(goodsAdEntity.goods.copyWriting) ? goodsAdEntity.goods.copyWriting : " \uff1f";
            this.mHandPrice.setTextSize(1, str2.length() >= 9 ? 21 : 23);
            PriceUtil.setSpanViewPrice(this.mHandPrice, str2);
            if (!TextUtils.isEmpty(goodsAdEntity.goods.linePrice)) {
                str = goodsAdEntity.goods.linePrice;
            } else if (!TextUtils.isEmpty(goodsAdEntity.goods.pcpPrice)) {
                str = goodsAdEntity.goods.pcpPrice;
            }
            PriceUtil.setStrikePrice(this.mLinePrice, str);
            FontUtil.changeTextFont(this.mGoodsTagText, 4099);
            FontUtil.changeTextFont(this.mGoodsTitle, 4099);
            FontUtil.changeTextFont(this.mHandPrice, 4099);
            FontUtil.changeTextFont(this.mLinePrice, 4099);
            DolphinMtaEntity dolphinMtaEntity = this.mMtaEntity;
            if (dolphinMtaEntity != null) {
                dolphinMtaEntity.sgid = goodsAdEntity.groupId;
                dolphinMtaEntity.sku = goodsAdEntity.goods.skuId;
            }
            if (!TextUtils.isEmpty(goodsAdEntity.goods.addCarImage)) {
                CommonServiceUtil.displayImage(goodsAdEntity.goods.addCarImage, this.mCarImage);
                this.mCarImage.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinGoodsAdView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CommonServiceUtil.addSingleProductToCartWithToast(DolphinGoodsAdView.this.mContext, goodsAdEntity.goods.skuId, null);
                        CommonServiceUtil.onClickMta(DolphinGoodsAdView.this.mContext, MtaData.Builder.from("Babel_dev_shoppingcart", DolphinGoodsAdView.this.mMtaEntity.getEventParam()).page(DolphinGoodsAdView.this.mBabelScope.getPageName(), DolphinGoodsAdView.this.mBabelScope.getPageParam()).build());
                    }
                });
            }
            final String eventParam = this.mMtaEntity.getEventParam();
            this.mAdLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinGoodsAdView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (goodsAdEntity.goods.jump != null) {
                        CommonServiceUtil.execJump(DolphinGoodsAdView.this.mContext, goodsAdEntity.goods.jump);
                        CommonServiceUtil.onClickMta(DolphinGoodsAdView.this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + DolphinGoodsAdView.this.mMtaEntity.eventIdSuffix, eventParam).page(DolphinGoodsAdView.this.mBabelScope.getPageName(), DolphinGoodsAdView.this.mBabelScope.getPageParam()).build());
                    }
                }
            });
            CommonServiceUtil.sendExposureData(this.mBabelScope, DolphinAdvMtaEntity.PREFIX_EXPO_SKU + this.mMtaEntity.eventIdSuffix, eventParam);
            this.mCardLayout.setVisibility(0);
            return;
        }
        this.mCardLayout.setVisibility(8);
    }

    public DolphinGoodsAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }
}
