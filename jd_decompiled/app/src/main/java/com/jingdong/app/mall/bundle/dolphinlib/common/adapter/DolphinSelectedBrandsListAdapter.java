package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.PriceUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSelectedBrandsListAdapter extends BaseAdapter {
    public DolphinCountDownGridFloorConfig boardParams;
    private BabelScope mBabelScope;
    private Context mContext;
    private List<GoodsEntity> mDatas = new ArrayList();
    private DolphinMtaEntity mMtaEntity;

    /* loaded from: classes19.dex */
    public class SelectedBrandsListViewHolder {
        private ImageWraper mGoodsImageView;
        private TextView mName;
        private TextView mPrice;

        public SelectedBrandsListViewHolder() {
            DolphinSelectedBrandsListAdapter.this = r1;
        }
    }

    public DolphinSelectedBrandsListAdapter(Context context) {
        this.mContext = context;
    }

    public void click(GoodsEntity goodsEntity) {
        BabelJumpEntity babelJumpEntity = goodsEntity.jump;
        if (babelJumpEntity != null) {
            this.mMtaEntity.sku = goodsEntity.skuId;
            CommonServiceUtil.execJump(this.mContext, babelJumpEntity);
            CommonServiceUtil.onClickMta(this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + this.mMtaEntity.eventIdSuffix, this.mMtaEntity.getEventParam()).page(this.mBabelScope.getPageName(), this.mBabelScope.getPageParam()).build());
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

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        SelectedBrandsListViewHolder selectedBrandsListViewHolder;
        if (view != null && (view.getTag() instanceof SelectedBrandsListViewHolder)) {
            selectedBrandsListViewHolder = (SelectedBrandsListViewHolder) view.getTag();
        } else {
            SelectedBrandsListViewHolder selectedBrandsListViewHolder2 = new SelectedBrandsListViewHolder();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_selected_brands_goods_item, viewGroup, false);
            selectedBrandsListViewHolder2.mGoodsImageView = (ImageWraper) inflate.findViewById(R.id.goods_image);
            selectedBrandsListViewHolder2.mName = (TextView) inflate.findViewById(R.id.goods_name);
            selectedBrandsListViewHolder2.mPrice = (TextView) inflate.findViewById(R.id.goods_price);
            inflate.setTag(selectedBrandsListViewHolder2);
            selectedBrandsListViewHolder = selectedBrandsListViewHolder2;
            view = inflate;
        }
        final GoodsEntity goodsEntity = this.mDatas.get(i2);
        if (goodsEntity == null) {
            return view;
        }
        if (this.boardParams != null) {
            selectedBrandsListViewHolder.mName.setTextColor(ColorUtil.parseColor(this.boardParams.titleColor, Color.parseColor("#505050")));
            selectedBrandsListViewHolder.mPrice.setTextColor(ColorUtil.parseColor(this.boardParams.priceColor, Color.parseColor(JDDarkUtil.COLOR_262626)));
        }
        CommonServiceUtil.displayImage(goodsEntity.image, selectedBrandsListViewHolder.mGoodsImageView);
        selectedBrandsListViewHolder.mName.setText(goodsEntity.name);
        PriceUtil.setSpanViewPriceWithPrefix(selectedBrandsListViewHolder.mPrice, goodsEntity.pPrice, "");
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSelectedBrandsListAdapter.1
            {
                DolphinSelectedBrandsListAdapter.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                DolphinSelectedBrandsListAdapter.this.click(goodsEntity);
            }
        });
        this.mMtaEntity.sku = goodsEntity.skuId;
        CommonServiceUtil.sendExposureData(this.mBabelScope, DolphinAdvMtaEntity.PREFIX_EXPO_SKU + this.mMtaEntity.eventIdSuffix, this.mMtaEntity.getEventParam());
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
