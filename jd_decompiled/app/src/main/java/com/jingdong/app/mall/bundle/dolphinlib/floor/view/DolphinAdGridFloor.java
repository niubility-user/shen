package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinGoodsGridViewPagerAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinPageIndicatorView;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinViewPagerContainer;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdGridFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdGridFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsAdEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsGridPagerEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinAdGridFloor extends FrameLayout implements IFloorView<DolphinAdGridFloorModel> {
    private static final int GRID_COLUMN = 3;
    private static final int GRID_ROW = 3;
    private static final String TAG = "mjcx";
    private boolean isInit;
    private GoodsAdEntity mAdEntity;
    private DolphinGoodsGridViewPagerAdapter mAdapter;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private RelativeLayout mContainerLayout;
    private Context mContext;
    private DolphinAdGridFloorModel mFloorEntity;
    private LinearLayout mFloorLayout;
    private String mGroupId;
    private DolphinPageIndicatorView mIndicator;
    private DolphinMtaEntity mMtaEntity;
    private ArrayList<GoodsGridPagerEntity> mPagerEntities;
    private DolphinViewPagerContainer mViewPagerContainer;

    public DolphinAdGridFloor(Context context) {
        super(context);
        this.isInit = false;
        this.mContext = context;
    }

    private ArrayList<GoodsGridPagerEntity> disposeDatas(DolphinAdGridFloorModel dolphinAdGridFloorModel) {
        ArrayList<GoodsGridPagerEntity> arrayList = new ArrayList<>();
        List<GoodsEntity> arrayList2 = new ArrayList<>();
        if (dolphinAdGridFloorModel.productGroupData.size() > 1 && dolphinAdGridFloorModel.productGroupData.get(0).productList.size() > 0) {
            if (dolphinAdGridFloorModel.productGroupData.get(1).productList != null && dolphinAdGridFloorModel.productGroupData.get(1).productList.size() > 0) {
                GoodsEntity clone = dolphinAdGridFloorModel.productGroupData.get(1).productList.get(0).getClone();
                clone.skuId = null;
                DolphinAdGridFloorConfig dolphinAdGridFloorConfig = dolphinAdGridFloorModel.boardParams;
                int i2 = dolphinAdGridFloorConfig.placeholderColumnScale;
                int i3 = dolphinAdGridFloorConfig.placeholderRowScale;
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = i4 * 3;
                    for (int i6 = 0; i6 < i2; i6++) {
                        dolphinAdGridFloorModel.productGroupData.get(1).productList.add(i5, clone);
                    }
                }
                arrayList2 = dolphinAdGridFloorModel.productGroupData.get(1).productList;
                this.mGroupId = dolphinAdGridFloorModel.productGroupData.get(1).groupId;
            }
            GoodsAdEntity goodsAdEntity = new GoodsAdEntity();
            this.mAdEntity = goodsAdEntity;
            goodsAdEntity.groupId = dolphinAdGridFloorModel.productGroupData.get(0).groupId;
            GoodsAdEntity goodsAdEntity2 = this.mAdEntity;
            DolphinAdGridFloorConfig dolphinAdGridFloorConfig2 = dolphinAdGridFloorModel.boardParams;
            goodsAdEntity2.cardBg = dolphinAdGridFloorConfig2.adBgImage;
            goodsAdEntity2.tagBg = dolphinAdGridFloorConfig2.adBadgeImage;
            goodsAdEntity2.adImage = dolphinAdGridFloorConfig2.adImage;
            goodsAdEntity2.goods = dolphinAdGridFloorModel.productGroupData.get(0).productList.get(0);
        } else {
            arrayList2 = dolphinAdGridFloorModel.productGroupData.get(0).productList;
            this.mGroupId = dolphinAdGridFloorModel.productGroupData.get(0).groupId;
            this.mAdEntity = null;
        }
        for (int i7 = 0; i7 < arrayList2.size(); i7++) {
            if (i7 % 9 == 0) {
                arrayList.add(new GoodsGridPagerEntity());
            }
            if (arrayList.size() > 0) {
                arrayList.get(arrayList.size() - 1).goods.add(arrayList2.get(i7));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exposure(int i2) {
        List<GoodsEntity> list;
        ArrayList<GoodsGridPagerEntity> arrayList = this.mPagerEntities;
        if (arrayList == null || arrayList.size() <= 0 || this.mPagerEntities.size() <= i2 || (list = this.mPagerEntities.get(i2).goods) == null) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.mMtaEntity.sku = list.get(i3).skuId;
            if (!TextUtils.isEmpty(this.mMtaEntity.sku)) {
                CommonServiceUtil.sendExposureData(this.mBabelScope, "Babel_dev_expo_sku_mjcx", this.mMtaEntity.getEventParam());
            }
        }
    }

    private DolphinMtaEntity getMtaEntity() {
        DolphinMtaEntity dolphinMtaEntity = new DolphinMtaEntity();
        dolphinMtaEntity.eventIdSuffix = TAG;
        dolphinMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinAdGridFloorModel dolphinAdGridFloorModel = this.mFloorEntity;
        dolphinMtaEntity.fno = dolphinAdGridFloorModel.fno;
        dolphinMtaEntity.mid = dolphinAdGridFloorModel.mid;
        dolphinMtaEntity.sgid = this.mGroupId;
        dolphinMtaEntity.sku = "";
        return dolphinMtaEntity;
    }

    private void hideFloor() {
        setVisibility(8);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
        } else {
            layoutParams = new RecyclerView.LayoutParams(-1, 0);
        }
        setLayoutParams(layoutParams);
    }

    private void updateFloorBg() {
        DolphinAdGridFloorConfig dolphinAdGridFloorConfig = this.mFloorEntity.boardParams;
        if (dolphinAdGridFloorConfig != null && dolphinAdGridFloorConfig.bgStyle == 1 && !TextUtils.isEmpty(dolphinAdGridFloorConfig.backgroundImage)) {
            if (this.mBgView == null) {
                ImageView imageView = CommonServiceUtil.getImageView(getContext());
                this.mBgView = imageView;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.mBgView, 0, new FrameLayout.LayoutParams(-1, -1));
            }
            CommonServiceUtil.displayImage(this.mFloorEntity.boardParams.backgroundImage, this.mBgView);
            setBackgroundColor(0);
            return;
        }
        ImageView imageView2 = this.mBgView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(null);
        }
        DolphinAdGridFloorConfig dolphinAdGridFloorConfig2 = this.mFloorEntity.boardParams;
        setBackgroundColor(ColorUtil.parseColor(dolphinAdGridFloorConfig2 != null ? dolphinAdGridFloorConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_ad_grid_floor, this);
        this.mFloorLayout = (LinearLayout) findViewById(R.id.floor_layout);
        this.mContainerLayout = (RelativeLayout) findViewById(R.id.content_layout);
        this.mIndicator = (DolphinPageIndicatorView) findViewById(R.id.page_indicator_view);
        DolphinViewPagerContainer dolphinViewPagerContainer = new DolphinViewPagerContainer(this.mContext);
        this.mViewPagerContainer = dolphinViewPagerContainer;
        this.mContainerLayout.addView(dolphinViewPagerContainer);
        this.mIndicator.setCursor(DPIUtil.dip2px(this.mContext, 12.0f), DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 5.0f));
    }

    public void showIndicator(int i2) {
        if (i2 < 1) {
            this.mIndicator.setVisibility(8);
            return;
        }
        if (this.mIndicator.getVisibility() == 8) {
            this.mIndicator.setVisibility(0);
        }
        this.mIndicator.createCursor(i2);
        this.mIndicator.openLight(this.mViewPagerContainer.getViewPager().getCurrentItem());
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinAdGridFloorModel dolphinAdGridFloorModel, int i2) {
        if (this.isInit) {
            exposure(0);
            return;
        }
        this.isInit = true;
        this.mBabelScope = babelScope;
        this.mFloorEntity = dolphinAdGridFloorModel;
        updateFloorBg();
        if (this.mFloorEntity.handleData()) {
            this.mViewPagerContainer.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            this.mPagerEntities = disposeDatas(this.mFloorEntity);
            this.mMtaEntity = getMtaEntity();
            DolphinGoodsGridViewPagerAdapter dolphinGoodsGridViewPagerAdapter = new DolphinGoodsGridViewPagerAdapter(this.mContext, this.mPagerEntities);
            this.mAdapter = dolphinGoodsGridViewPagerAdapter;
            DolphinAdGridFloorConfig dolphinAdGridFloorConfig = this.mFloorEntity.boardParams;
            dolphinGoodsGridViewPagerAdapter.setAdProperty(dolphinAdGridFloorConfig.placeholderColumnScale, dolphinAdGridFloorConfig.placeholderRowScale, this.mAdEntity);
            this.mAdapter.setGridProperty(3, 3, DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 3.0f), R.drawable.dolphin_multi_column_grid_bg);
            this.mAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            this.mViewPagerContainer.setAdapter(this.mAdapter);
            this.mViewPagerContainer.getViewPager().setGridProperty(3, 3, DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 3.0f));
            this.mViewPagerContainer.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinAdGridFloor.1
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i3, float f2, int i4) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i3) {
                    DolphinAdGridFloor.this.mIndicator.openLight(i3);
                    DolphinAdGridFloor.this.exposure(i3);
                }
            });
            showIndicator(this.mPagerEntities.size());
            exposure(0);
            return;
        }
        hideFloor();
    }
}
