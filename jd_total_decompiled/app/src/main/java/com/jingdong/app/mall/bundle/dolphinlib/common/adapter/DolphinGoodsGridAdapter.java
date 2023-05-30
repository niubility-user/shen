package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinGoodsGridAdapter extends BaseAdapter {
    private int gridNumColumn;
    private int gridNumRow;
    private int itemWidth;
    private BabelScope mBabelScope;
    private Context mContext;
    private DolphinMtaEntity mMtaEntity;
    private int resBg;
    private List<GoodsEntity> mDatas = new ArrayList();
    private boolean isLeftAndRightBottomRadius = false;

    /* loaded from: classes19.dex */
    public class GoodsViewHolder {
        private TextView mGoodsHandPrice;
        private ImageWraper mGoodsImageView;
        private LinearLayout mGoodsLayout;
        private ImageWraper mGoodsLineChart;
        private TextView mGoodsPromotion;
        private TextView mGoodsReferencePrice;
        private TextView mGoodsTitle;

        public GoodsViewHolder() {
        }
    }

    public DolphinGoodsGridAdapter(Context context) {
        this.mContext = context;
    }

    private void setGoodsImageLayoutParams(ImageWraper imageWraper) {
        int i2 = this.itemWidth;
        imageWraper.setLayoutParams(new LinearLayout.LayoutParams(i2, i2));
    }

    private void setItemRadius(int i2, GoodsViewHolder goodsViewHolder) {
        if (this.resBg > 0) {
            goodsViewHolder.mGoodsLayout.setBackgroundResource(this.resBg);
        } else if (!this.isLeftAndRightBottomRadius) {
            goodsViewHolder.mGoodsLayout.setBackgroundResource(R.drawable.dolphin_white_bg);
        } else {
            int i3 = this.gridNumColumn;
            int i4 = (this.gridNumRow * i3) - 1;
            int i5 = i4 - (i3 - 1);
            if (i2 != i5 && i2 != i4) {
                goodsViewHolder.mGoodsLayout.setBackgroundResource(R.drawable.dolphin_white_bg);
            } else if (i2 == i5) {
                goodsViewHolder.mGoodsLayout.setBackgroundResource(R.drawable.dolphin_left_bottom_grid_bg);
            } else {
                goodsViewHolder.mGoodsLayout.setBackgroundResource(R.drawable.dolphin_right_bottom_grid_bg);
            }
        }
    }

    private void setTextSize(GoodsViewHolder goodsViewHolder) {
        int i2 = this.gridNumColumn;
        if (i2 == 3) {
            goodsViewHolder.mGoodsTitle.setTextSize(1, 12.0f);
            goodsViewHolder.mGoodsPromotion.setTextSize(1, 10.0f);
            goodsViewHolder.mGoodsReferencePrice.setTextSize(1, 10.0f);
            goodsViewHolder.mGoodsHandPrice.setTextSize(1, 16.0f);
        } else if (i2 == 4) {
            goodsViewHolder.mGoodsTitle.setTextSize(1, 10.0f);
            goodsViewHolder.mGoodsPromotion.setTextSize(1, 8.0f);
            goodsViewHolder.mGoodsReferencePrice.setTextSize(1, 8.0f);
            goodsViewHolder.mGoodsHandPrice.setTextSize(1, 14.0f);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<GoodsEntity> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<GoodsEntity> getDatas() {
        return this.mDatas;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<GoodsEntity> list = this.mDatas;
        return (list == null || i2 < 0 || i2 > list.size() + (-1)) ? "" : this.mDatas.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        GoodsViewHolder goodsViewHolder;
        String str;
        if (view != null && (view.getTag() instanceof GoodsViewHolder)) {
            goodsViewHolder = (GoodsViewHolder) view.getTag();
        } else {
            GoodsViewHolder goodsViewHolder2 = new GoodsViewHolder();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_goods_grid_item, viewGroup, false);
            goodsViewHolder2.mGoodsLayout = (LinearLayout) inflate.findViewById(R.id.goods_layout);
            goodsViewHolder2.mGoodsImageView = (ImageWraper) inflate.findViewById(R.id.goods_image);
            goodsViewHolder2.mGoodsTitle = (TextView) inflate.findViewById(R.id.goods_title);
            goodsViewHolder2.mGoodsPromotion = (TextView) inflate.findViewById(R.id.goods_promotion);
            goodsViewHolder2.mGoodsReferencePrice = (TextView) inflate.findViewById(R.id.goods_reference_price);
            goodsViewHolder2.mGoodsLineChart = (ImageWraper) inflate.findViewById(R.id.goods_line_chart);
            goodsViewHolder2.mGoodsHandPrice = (TextView) inflate.findViewById(R.id.goods_hand_price);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsTitle, 4099);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsPromotion, 4099);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsReferencePrice, 4099);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsHandPrice, 4099);
            if (this.resBg > 0) {
                goodsViewHolder2.mGoodsLayout.setBackgroundResource(this.resBg);
            }
            setItemRadius(i2, goodsViewHolder2);
            setTextSize(goodsViewHolder2);
            setGoodsImageLayoutParams(goodsViewHolder2.mGoodsImageView);
            inflate.setTag(goodsViewHolder2);
            goodsViewHolder = goodsViewHolder2;
            view = inflate;
        }
        final GoodsEntity goodsEntity = this.mDatas.get(i2);
        if (goodsEntity == null) {
            return view;
        }
        CommonServiceUtil.displayImage(goodsEntity.image, goodsViewHolder.mGoodsImageView);
        goodsViewHolder.mGoodsTitle.setText(goodsEntity.name);
        if (!TextUtils.isEmpty(goodsEntity.tag)) {
            goodsViewHolder.mGoodsPromotion.setText(goodsEntity.tag);
            goodsViewHolder.mGoodsPromotion.setVisibility(0);
        } else {
            goodsViewHolder.mGoodsPromotion.setVisibility(8);
        }
        if (!TextUtils.isEmpty(goodsEntity.linePrice)) {
            str = goodsEntity.linePrice;
        } else {
            str = !TextUtils.isEmpty(goodsEntity.pcpPrice) ? goodsEntity.pcpPrice : " \uff1f";
        }
        goodsViewHolder.mGoodsReferencePrice.setText("\u53c2\u8003\u4ef7 " + PriceUtil.str2Price(str));
        if (!TextUtils.isEmpty(goodsEntity.goodsLineChart)) {
            CommonServiceUtil.displayImage(goodsEntity.goodsLineChart, goodsViewHolder.mGoodsLineChart);
        }
        String str2 = TextUtils.isEmpty(goodsEntity.copyWriting) ? " \uff1f" : goodsEntity.copyWriting;
        goodsViewHolder.mGoodsHandPrice.setTextSize(1, str2.length() >= 9 ? 14 : 15);
        PriceUtil.setSpanViewPrice(goodsViewHolder.mGoodsHandPrice, str2);
        goodsViewHolder.mGoodsLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinGoodsGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (goodsEntity.jump != null) {
                    DolphinGoodsGridAdapter.this.mMtaEntity.sku = goodsEntity.skuId;
                    CommonServiceUtil.execJump(DolphinGoodsGridAdapter.this.mContext, goodsEntity.jump);
                    CommonServiceUtil.onClickMta(DolphinGoodsGridAdapter.this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + DolphinGoodsGridAdapter.this.mMtaEntity.eventIdSuffix, DolphinGoodsGridAdapter.this.mMtaEntity.getEventParam()).page(DolphinGoodsGridAdapter.this.mBabelScope.getPageName(), DolphinGoodsGridAdapter.this.mBabelScope.getPageParam()).build());
                }
            }
        });
        if (goodsEntity.isCloneItem) {
            goodsViewHolder.mGoodsLayout.setVisibility(4);
        } else {
            goodsViewHolder.mGoodsLayout.setVisibility(0);
        }
        return view;
    }

    public void setDatas(List<GoodsEntity> list, int i2, int i3, int i4, int i5) {
        this.gridNumColumn = i3;
        this.gridNumRow = i4;
        this.itemWidth = i2;
        this.resBg = i5;
        if (list != null) {
            this.mDatas.clear();
            this.mDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setLeftAndRightBottomRadius(boolean z) {
        this.isLeftAndRightBottomRadius = z;
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }
}
