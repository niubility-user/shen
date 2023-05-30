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
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.TagUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTagEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSingleColumnGoodsGridAdapter extends BaseAdapter {
    private int gridNumColumn;
    private int gridNumRow;
    private int itemWidth;
    private BabelScope mBabelScope;
    private Context mContext;
    private List<GoodsEntity> mDatas = new ArrayList();
    private DolphinMtaEntity mMtaEntity;
    private int resBg;

    /* loaded from: classes19.dex */
    public class GoodsViewHolder {
        private TextView mGoodsHandPrice;
        private ImageWraper mGoodsImageView;
        private LinearLayout mGoodsLayout;
        private ImageWraper mGoodsLineChart;
        private LinearLayout mGoodsPromotionLayout;
        private TextView mGoodsReferencePrice;
        private TextView mGoodsTitle;

        public GoodsViewHolder() {
        }
    }

    public DolphinSingleColumnGoodsGridAdapter(Context context) {
        this.mContext = context;
    }

    private void createPromotion(GoodsViewHolder goodsViewHolder, GoodsEntity goodsEntity) {
        goodsViewHolder.mGoodsPromotionLayout.removeAllViews();
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(goodsEntity.tag)) {
            arrayList.add(goodsEntity.tag);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            TextView textView = new TextView(this.mContext);
            textView.setIncludeFontPadding(false);
            textView.setLines(1);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(ColorUtil.parseColor("#E8220E", -1));
            textView.setTextSize(1, 10.0f);
            textView.setBackgroundResource(R.drawable.dolphin_promotion_bg);
            textView.setPadding(DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 1.0f), DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 1.0f));
            textView.setText((CharSequence) arrayList.get(i2));
            FontUtil.changeTextFont(textView, 4099);
            goodsViewHolder.mGoodsPromotionLayout.addView(textView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.rightMargin = DPIUtil.dip2px(this.mContext, 2.0f);
            textView.setLayoutParams(layoutParams);
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
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_single_column_goods_grid_item, viewGroup, false);
            goodsViewHolder2.mGoodsLayout = (LinearLayout) inflate.findViewById(R.id.goods_layout);
            goodsViewHolder2.mGoodsImageView = (ImageWraper) inflate.findViewById(R.id.goods_image);
            goodsViewHolder2.mGoodsTitle = (TextView) inflate.findViewById(R.id.goods_title);
            goodsViewHolder2.mGoodsPromotionLayout = (LinearLayout) inflate.findViewById(R.id.goods_promotion_layout);
            goodsViewHolder2.mGoodsReferencePrice = (TextView) inflate.findViewById(R.id.goods_reference_price);
            goodsViewHolder2.mGoodsLineChart = (ImageWraper) inflate.findViewById(R.id.goods_line_chart);
            goodsViewHolder2.mGoodsHandPrice = (TextView) inflate.findViewById(R.id.goods_hand_price);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsTitle, 4099);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsReferencePrice, 4099);
            FontUtil.changeTextFont(goodsViewHolder2.mGoodsHandPrice, 4099);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSingleColumnGoodsGridAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                }
            });
            if (this.resBg > 0) {
                goodsViewHolder2.mGoodsLayout.setBackgroundResource(this.resBg);
            }
            inflate.setTag(goodsViewHolder2);
            goodsViewHolder = goodsViewHolder2;
            view = inflate;
        }
        final GoodsEntity goodsEntity = this.mDatas.get(i2);
        if (goodsEntity == null) {
            return view;
        }
        CommonServiceUtil.displayImage(goodsEntity.image, goodsViewHolder.mGoodsImageView);
        List<DolphinTagEntity> list = goodsEntity.skuTagList;
        if (list == null || list.size() <= 0) {
            goodsViewHolder.mGoodsTitle.setText(goodsEntity.name);
        } else {
            TagUtil.addTagsForSkuTitle(this.mContext, goodsViewHolder.mGoodsTitle, goodsEntity.name, goodsEntity.skuTagList);
        }
        createPromotion(goodsViewHolder, goodsEntity);
        if (!TextUtils.isEmpty(goodsEntity.linePrice)) {
            str = goodsEntity.linePrice;
        } else {
            str = !TextUtils.isEmpty(goodsEntity.pcpPrice) ? goodsEntity.pcpPrice : " \uff1f";
        }
        goodsViewHolder.mGoodsReferencePrice.setText("\u65e5\u5e38\u4ef7 " + PriceUtil.str2Price(str));
        if (!TextUtils.isEmpty(goodsEntity.goodsLineChart)) {
            CommonServiceUtil.displayImage(goodsEntity.goodsLineChart, goodsViewHolder.mGoodsLineChart);
        }
        PriceUtil.setSpanViewPriceWithPrefix(goodsViewHolder.mGoodsHandPrice, TextUtils.isEmpty(goodsEntity.copyWriting) ? " \uff1f" : goodsEntity.copyWriting, "\u9884\u4f30\u5230\u624b\u4ef7 ");
        goodsViewHolder.mGoodsLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSingleColumnGoodsGridAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (goodsEntity.jump != null) {
                    DolphinSingleColumnGoodsGridAdapter.this.mMtaEntity.sku = goodsEntity.skuId;
                    CommonServiceUtil.execJump(DolphinSingleColumnGoodsGridAdapter.this.mContext, goodsEntity.jump);
                    CommonServiceUtil.onClickMta(DolphinSingleColumnGoodsGridAdapter.this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + DolphinSingleColumnGoodsGridAdapter.this.mMtaEntity.eventIdSuffix, DolphinSingleColumnGoodsGridAdapter.this.mMtaEntity.getEventParam()).page(DolphinSingleColumnGoodsGridAdapter.this.mBabelScope.getPageName(), DolphinSingleColumnGoodsGridAdapter.this.mBabelScope.getPageParam()).build());
                }
            }
        });
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

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }
}
