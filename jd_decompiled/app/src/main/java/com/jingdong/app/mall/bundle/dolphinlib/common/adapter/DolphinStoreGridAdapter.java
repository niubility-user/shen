package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
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
import com.jd.lib.babel.servicekit.util.FontUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.AdvertEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinStoreFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinStoreGridAdapter extends BaseAdapter {
    private DolphinAdvMtaEntity mAdvMtaEntity;
    private BabelScope mBabelScope;
    private DolphinStoreFloorConfig mBoardParams;
    private Context mContext;
    private List<AdvertEntity> mDatas = new ArrayList();
    private DolphinMtaEntity mMtaEntity;

    /* loaded from: classes19.dex */
    public class StoreViewHolder {
        private ImageWraper mGoodsFour;
        private ImageWraper mGoodsOne;
        private ImageWraper mGoodsThree;
        private ImageWraper mGoodsTwo;
        private View.OnClickListener mListener;
        private TextView mSeeMore;
        private ImageWraper mStoreBg;
        private TextView mStoreName;
        private TextView mStoreTag;

        public StoreViewHolder() {
            DolphinStoreGridAdapter.this = r1;
        }
    }

    public DolphinStoreGridAdapter(Context context) {
        this.mContext = context;
    }

    private void showGoods(StoreViewHolder storeViewHolder, List<GoodsEntity> list) {
        CommonServiceUtil.displayImage(list.get(0).image, storeViewHolder.mGoodsOne);
        CommonServiceUtil.displayImage(list.get(1).image, storeViewHolder.mGoodsTwo);
        CommonServiceUtil.displayImage(list.get(2).image, storeViewHolder.mGoodsThree);
        CommonServiceUtil.displayImage(list.get(3).image, storeViewHolder.mGoodsFour);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<AdvertEntity> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<AdvertEntity> list = this.mDatas;
        return (list == null || i2 < 0 || i2 > list.size() + (-1)) ? "" : this.mDatas.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public View getView(final int i2, View view, ViewGroup viewGroup) {
        StoreViewHolder storeViewHolder;
        if (view != null && (view.getTag() instanceof StoreViewHolder)) {
            storeViewHolder = (StoreViewHolder) view.getTag();
        } else {
            StoreViewHolder storeViewHolder2 = new StoreViewHolder();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_store_grid_item, viewGroup, false);
            storeViewHolder2.mStoreBg = (ImageWraper) inflate.findViewById(R.id.store_bg);
            storeViewHolder2.mStoreName = (TextView) inflate.findViewById(R.id.store_name);
            storeViewHolder2.mStoreTag = (TextView) inflate.findViewById(R.id.store_tag);
            storeViewHolder2.mGoodsOne = (ImageWraper) inflate.findViewById(R.id.goods_one);
            storeViewHolder2.mGoodsTwo = (ImageWraper) inflate.findViewById(R.id.goods_two);
            storeViewHolder2.mGoodsThree = (ImageWraper) inflate.findViewById(R.id.goods_three);
            storeViewHolder2.mGoodsFour = (ImageWraper) inflate.findViewById(R.id.goods_four);
            storeViewHolder2.mSeeMore = (TextView) inflate.findViewById(R.id.see_more);
            storeViewHolder2.mListener = new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinStoreGridAdapter.1
                {
                    DolphinStoreGridAdapter.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    DolphinStoreGridAdapter.this.onHolderClick(view2, i2);
                }
            };
            storeViewHolder2.mStoreBg.setOnClickListener(storeViewHolder2.mListener);
            storeViewHolder2.mGoodsOne.setOnClickListener(storeViewHolder2.mListener);
            storeViewHolder2.mGoodsTwo.setOnClickListener(storeViewHolder2.mListener);
            storeViewHolder2.mGoodsThree.setOnClickListener(storeViewHolder2.mListener);
            storeViewHolder2.mGoodsFour.setOnClickListener(storeViewHolder2.mListener);
            storeViewHolder2.mSeeMore.setOnClickListener(storeViewHolder2.mListener);
            FontUtil.changeTextFont(storeViewHolder2.mStoreName, 4097);
            FontUtil.changeTextFont(storeViewHolder2.mStoreTag, 4099);
            FontUtil.changeTextFont(storeViewHolder2.mSeeMore, 4099);
            storeViewHolder2.mStoreName.setTextColor(ColorUtil.parseColor(this.mBoardParams.titleTextColor, Color.parseColor("#343434")));
            storeViewHolder2.mStoreTag.setTextColor(ColorUtil.parseColor(this.mBoardParams.tagTextColor, Color.parseColor("#343434")));
            inflate.setTag(storeViewHolder2);
            storeViewHolder = storeViewHolder2;
            view = inflate;
        }
        AdvertEntity advertEntity = this.mDatas.get(i2);
        if (advertEntity == null) {
            return view;
        }
        CommonServiceUtil.displayImage(advertEntity.pictureUrl, storeViewHolder.mStoreBg);
        storeViewHolder.mStoreName.setText(advertEntity.name);
        if (!TextUtils.isEmpty(advertEntity.desc)) {
            storeViewHolder.mStoreTag.setText(advertEntity.desc);
            storeViewHolder.mStoreTag.setVisibility(0);
        } else {
            storeViewHolder.mStoreTag.setVisibility(8);
        }
        storeViewHolder.mSeeMore.setText("\u67e5\u770b\u66f4\u591a");
        List<GoodsEntity> list = advertEntity.productList;
        if (list != null && list.size() >= 4) {
            showGoods(storeViewHolder, advertEntity.productList);
        }
        return view;
    }

    public void onHolderClick(View view, int i2) {
        List<GoodsEntity> list;
        LogUtil.d("asdf", "position:" + i2);
        AdvertEntity advertEntity = this.mDatas.get(i2);
        if (advertEntity == null || (list = advertEntity.productList) == null || list.size() < 4) {
            return;
        }
        int i3 = 0;
        if (view.getId() != R.id.goods_one) {
            if (view.getId() == R.id.goods_two) {
                i3 = 1;
            } else if (view.getId() == R.id.goods_three) {
                i3 = 2;
            } else if (view.getId() == R.id.goods_four) {
                i3 = 3;
            } else if (view.getId() == R.id.store_bg || view.getId() == R.id.see_more) {
                BabelJumpEntity babelJumpEntity = advertEntity.jump;
                if (babelJumpEntity != null) {
                    DolphinAdvMtaEntity dolphinAdvMtaEntity = this.mAdvMtaEntity;
                    dolphinAdvMtaEntity.agid = advertEntity.groupId;
                    dolphinAdvMtaEntity.adid = advertEntity.advertId;
                    CommonServiceUtil.execJump(this.mContext, babelJumpEntity);
                    CommonServiceUtil.onClickMta(this.mContext, MtaData.Builder.from("Babel_dev_adv_" + this.mAdvMtaEntity.eventIdSuffix, this.mAdvMtaEntity.getEventParam()).page(this.mBabelScope.getPageName(), this.mBabelScope.getPageParam()).build());
                    return;
                }
                return;
            }
        }
        GoodsEntity goodsEntity = advertEntity.productList.get(i3);
        BabelJumpEntity babelJumpEntity2 = goodsEntity.jump;
        if (babelJumpEntity2 != null) {
            DolphinMtaEntity dolphinMtaEntity = this.mMtaEntity;
            dolphinMtaEntity.sgid = advertEntity.skuGroupId;
            dolphinMtaEntity.sku = goodsEntity.skuId;
            CommonServiceUtil.execJump(this.mContext, babelJumpEntity2);
            CommonServiceUtil.onClickMta(this.mContext, MtaData.Builder.from(DolphinAdvMtaEntity.PREFIX_DEV_SKU + this.mMtaEntity.eventIdSuffix, this.mMtaEntity.getEventParam()).page(this.mBabelScope.getPageName(), this.mBabelScope.getPageParam()).build());
        }
    }

    public void setDatas(List<AdvertEntity> list, DolphinStoreFloorConfig dolphinStoreFloorConfig) {
        if (list != null) {
            this.mDatas.clear();
            this.mDatas.addAll(list);
        }
        this.mBoardParams = dolphinStoreFloorConfig;
        notifyDataSetChanged();
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity, DolphinAdvMtaEntity dolphinAdvMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
        this.mAdvMtaEntity = dolphinAdvMtaEntity;
    }
}
