package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes19.dex */
class DolphinSubsidyColumnAdapter extends BaseAdapter {
    public DolphinCountDownGridFloorConfig boardParams;
    private BabelScope mBabelScope;
    private Context mContext;
    private List<GoodsEntity> mDatas = new ArrayList();
    private DolphinMtaEntity mMtaEntity;
    private int resBg;

    /* loaded from: classes19.dex */
    public class GoodsViewHolder {
        private TextView goDetailsTip;
        private RelativeLayout mBtnContainer;
        private ImageWraper mGoodsCornerLabel;
        private TextView mGoodsHandPrice;
        private ImageWraper mGoodsImageView;
        private LinearLayout mGoodsLayout;
        private TextView mGoodsLinePrice1;
        private TextView mGoodsLinePrice2;
        private ImageWraper mGoodsLogo;
        private TextView mGoodsSubsidyPrice;
        private TextView mGoodsTitle;
        private TextView subsidyTip;

        public GoodsViewHolder() {
        }
    }

    public DolphinSubsidyColumnAdapter(Context context) {
        this.mContext = context;
    }

    private boolean isDouble(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile("^[-\\+]?[.\\d]*$").matcher(str).matches();
    }

    private boolean isInteger(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile("^[-\\+]?[\\d]*$").matcher(str).matches();
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
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_single_column_subsidy_item, viewGroup, false);
            goodsViewHolder2.mGoodsLayout = (LinearLayout) inflate.findViewById(R.id.goods_layout);
            goodsViewHolder2.mGoodsImageView = (ImageWraper) inflate.findViewById(R.id.goods_image);
            goodsViewHolder2.mGoodsCornerLabel = (ImageWraper) inflate.findViewById(R.id.goods_corner_label);
            goodsViewHolder2.mGoodsLogo = (ImageWraper) inflate.findViewById(R.id.goods_logo);
            goodsViewHolder2.mGoodsTitle = (TextView) inflate.findViewById(R.id.goods_title);
            goodsViewHolder2.mGoodsLinePrice1 = (TextView) inflate.findViewById(R.id.goods_line_price1);
            goodsViewHolder2.mGoodsLinePrice2 = (TextView) inflate.findViewById(R.id.goods_line_price2);
            goodsViewHolder2.mGoodsSubsidyPrice = (TextView) inflate.findViewById(R.id.goods_subsidy_price);
            goodsViewHolder2.mGoodsHandPrice = (TextView) inflate.findViewById(R.id.goods_hand_price);
            goodsViewHolder2.mBtnContainer = (RelativeLayout) inflate.findViewById(R.id.btn_container);
            goodsViewHolder2.subsidyTip = (TextView) inflate.findViewById(R.id.subsidyTip);
            goodsViewHolder2.goDetailsTip = (TextView) inflate.findViewById(R.id.goDetailsTip);
            inflate.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSubsidyColumnAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                }
            });
            inflate.setTag(goodsViewHolder2);
            goodsViewHolder = goodsViewHolder2;
            view = inflate;
        }
        final GoodsEntity goodsEntity = this.mDatas.get(i2);
        if (goodsEntity == null) {
            return view;
        }
        CommonServiceUtil.displayImage(goodsEntity.image, goodsViewHolder.mGoodsImageView);
        if (this.boardParams != null) {
            goodsViewHolder.subsidyTip.setText(this.boardParams.subsidyTip);
            TextView textView = goodsViewHolder.goDetailsTip;
            if (TextUtils.isEmpty(this.boardParams.goDetailsTip)) {
                str = "";
            } else {
                str = this.boardParams.goDetailsTip + ">";
            }
            textView.setText(str);
            if (!TextUtils.isEmpty(this.boardParams.tagUrl)) {
                CommonServiceUtil.displayImage(this.boardParams.tagUrl, goodsViewHolder.mGoodsCornerLabel);
            }
        }
        if (!TextUtils.isEmpty(goodsEntity.naFlagImgM)) {
            goodsViewHolder.mGoodsLogo.setVisibility(0);
            CommonServiceUtil.displayImage(goodsEntity.naFlagImgM, goodsViewHolder.mGoodsLogo);
        } else {
            goodsViewHolder.mGoodsLogo.setVisibility(8);
        }
        goodsViewHolder.mGoodsTitle.setText(TextUtils.isEmpty(goodsEntity.name) ? "" : goodsEntity.name);
        if (!TextUtils.isEmpty(goodsEntity.linePrice)) {
            goodsViewHolder.mGoodsLinePrice1.setText(PriceUtil.str2Price(goodsEntity.linePrice));
            goodsViewHolder.mGoodsLinePrice2.setText(PriceUtil.str2Price(goodsEntity.linePrice));
        } else if (!TextUtils.isEmpty(goodsEntity.pcpPrice)) {
            goodsViewHolder.mGoodsLinePrice1.setText(PriceUtil.str2Price(goodsEntity.pcpPrice));
            goodsViewHolder.mGoodsLinePrice2.setText(PriceUtil.str2Price(goodsEntity.pcpPrice));
        } else {
            goodsViewHolder.mGoodsLinePrice1.setText(PriceUtil.str2Price(" ?"));
            goodsViewHolder.mGoodsLinePrice2.setText(PriceUtil.str2Price(" ?"));
        }
        if (!TextUtils.isEmpty(goodsEntity.copyWriting)) {
            goodsViewHolder.mGoodsHandPrice.setTextSize(1, goodsEntity.copyWriting.length() > 4 ? 18.0f : 25.0f);
        }
        PriceUtil.setSpanViewPriceWithPrefix(goodsViewHolder.mGoodsHandPrice, !TextUtils.isEmpty(goodsEntity.copyWriting) ? goodsEntity.copyWriting : " ?", "");
        if (TextUtils.isEmpty(goodsEntity.copyWriting)) {
            goodsViewHolder.mGoodsSubsidyPrice.setText(PriceUtil.str2Price(" ?"));
        } else {
            String str2 = !TextUtils.isEmpty(goodsEntity.linePrice) ? goodsEntity.linePrice : goodsEntity.pcpPrice;
            if (TextUtils.isEmpty(str2)) {
                goodsViewHolder.mGoodsSubsidyPrice.setText(PriceUtil.str2Price(" ?"));
            } else {
                int parseDouble = (int) (Double.parseDouble(str2) - Double.parseDouble(goodsEntity.copyWriting));
                goodsViewHolder.mGoodsSubsidyPrice.setTextSize(1, parseDouble > 999 ? 11.0f : 14.0f);
                goodsViewHolder.mGoodsSubsidyPrice.setText(PriceUtil.str2Price(String.valueOf(parseDouble >= 0 ? parseDouble : 0)));
            }
        }
        goodsViewHolder.mBtnContainer.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSubsidyColumnAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DolphinSubsidyColumnAdapter.this.mMtaEntity.sku = goodsEntity.skuId;
                CommonServiceUtil.execJump(DolphinSubsidyColumnAdapter.this.mContext, goodsEntity.jump);
                CommonServiceUtil.onClickMta(DolphinSubsidyColumnAdapter.this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + DolphinSubsidyColumnAdapter.this.mMtaEntity.eventIdSuffix, DolphinSubsidyColumnAdapter.this.mMtaEntity.getEventParam()).page(DolphinSubsidyColumnAdapter.this.mBabelScope.getPageName(), DolphinSubsidyColumnAdapter.this.mBabelScope.getPageParam()).build());
            }
        });
        return view;
    }

    public void setDatas(List<GoodsEntity> list, DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig) {
        if (list != null) {
            this.mDatas.clear();
            this.mDatas.addAll(list);
            this.boardParams = dolphinCountDownGridFloorConfig;
        }
        notifyDataSetChanged();
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }
}
