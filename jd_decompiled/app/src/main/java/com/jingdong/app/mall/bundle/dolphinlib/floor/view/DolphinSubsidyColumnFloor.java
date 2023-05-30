package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSubsidyViewPagerAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinPageIndicatorView;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinViewPagerContainer;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinSubsidyColumnFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsGridPagerEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSubsidyColumnFloor extends RelativeLayout implements IFloorView<DolphinSubsidyColumnFloorModel> {
    private static final int GRID_COLUMN = 1;
    private static final int GRID_ROW = 4;
    private static final String TAG = "bybt";
    private boolean isInit;
    private DolphinSubsidyViewPagerAdapter mAdapter;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private RelativeLayout mContainerLayout;
    private ImageWraper mContentBgView;
    private Context mContext;
    private DolphinSubsidyColumnFloorModel mFloorEntity;
    private DolphinPageIndicatorView mIndicator;
    private DolphinMtaEntity mMtaEntity;
    private DolphinViewPagerContainer mViewPagerContainer;
    private ArrayList<GoodsGridPagerEntity> pagerEntities;

    public DolphinSubsidyColumnFloor(Context context) {
        super(context);
        this.isInit = false;
        this.mContext = context;
    }

    private ArrayList<GoodsGridPagerEntity> disposeDatas(List<GoodsEntity> list) {
        ArrayList<GoodsGridPagerEntity> arrayList = new ArrayList<>();
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (i2 % 4 == 0) {
                    arrayList.add(new GoodsGridPagerEntity());
                }
                if (arrayList.size() > 0) {
                    arrayList.get(arrayList.size() - 1).goods.add(list.get(i2));
                    arrayList.get(arrayList.size() - 1).boardParams = this.mFloorEntity.boardParams;
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exposure(int i2) {
        List<GoodsEntity> list;
        ArrayList<GoodsGridPagerEntity> arrayList = this.pagerEntities;
        if (arrayList == null || arrayList.size() <= 0 || this.pagerEntities.size() <= i2 || (list = this.pagerEntities.get(i2).goods) == null) {
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.mMtaEntity.sku = list.get(i3).skuId;
            CommonServiceUtil.sendExposureData(this.mBabelScope, "Babel_dev_expo_sku_bybt", this.mMtaEntity.getEventParam());
        }
    }

    private DolphinMtaEntity getMtaEntity(String str) {
        DolphinMtaEntity dolphinMtaEntity = new DolphinMtaEntity();
        dolphinMtaEntity.eventIdSuffix = TAG;
        dolphinMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinSubsidyColumnFloorModel dolphinSubsidyColumnFloorModel = this.mFloorEntity;
        dolphinMtaEntity.fno = dolphinSubsidyColumnFloorModel.fno;
        dolphinMtaEntity.mid = dolphinSubsidyColumnFloorModel.mid;
        dolphinMtaEntity.sgid = str;
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
        DolphinSubsidyColumnFloorModel dolphinSubsidyColumnFloorModel = this.mFloorEntity;
        if (dolphinSubsidyColumnFloorModel != null && !TextUtils.isEmpty(dolphinSubsidyColumnFloorModel.boardParams.backgroundImage)) {
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
        DolphinSubsidyColumnFloorModel dolphinSubsidyColumnFloorModel2 = this.mFloorEntity;
        setBackgroundColor(ColorUtil.parseColor(dolphinSubsidyColumnFloorModel2 != null ? dolphinSubsidyColumnFloorModel2.boardParams.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
    }

    private void updateFloorContentBg() {
        DolphinSubsidyColumnFloorModel dolphinSubsidyColumnFloorModel = this.mFloorEntity;
        if (dolphinSubsidyColumnFloorModel != null && !TextUtils.isEmpty(dolphinSubsidyColumnFloorModel.boardParams.contentBgUrl)) {
            this.mContentBgView.setLayoutParams(new RelativeLayout.LayoutParams(-1, (DPIUtil.dip2px(this.mContext, 140.0f) * 4) + (DPIUtil.dip2px(this.mContext, 5.0f) * 3) + DPIUtil.dip2px(this.mContext, 29.0f)));
            CommonServiceUtil.displayImage(this.mFloorEntity.boardParams.contentBgUrl, this.mContentBgView);
            this.mContentBgView.setVisibility(0);
            return;
        }
        this.mContentBgView.setVisibility(8);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_subsidy_column_floor, this);
        this.mContainerLayout = (RelativeLayout) findViewById(R.id.content_layout);
        this.mIndicator = (DolphinPageIndicatorView) findViewById(R.id.page_indicator_view);
        DolphinViewPagerContainer dolphinViewPagerContainer = new DolphinViewPagerContainer(this.mContext);
        this.mViewPagerContainer = dolphinViewPagerContainer;
        this.mContainerLayout.addView(dolphinViewPagerContainer);
        this.mContentBgView = (ImageWraper) findViewById(R.id.content_bg);
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
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinSubsidyColumnFloorModel dolphinSubsidyColumnFloorModel, int i2) {
        this.mBabelScope = babelScope;
        this.mFloorEntity = dolphinSubsidyColumnFloorModel;
        if (this.isInit) {
            exposure(0);
            return;
        }
        this.isInit = true;
        updateFloorContentBg();
        updateFloorBg();
        if (this.mFloorEntity.handleData()) {
            this.mViewPagerContainer.setLayoutParams(new RelativeLayout.LayoutParams(-1, (DPIUtil.dip2px(this.mContext, 140.0f) * 4) + (DPIUtil.dip2px(this.mContext, 5.0f) * 3)));
            this.pagerEntities = disposeDatas(this.mFloorEntity.productGroupData.get(0).productList);
            this.mMtaEntity = getMtaEntity(this.mFloorEntity.productGroupData.get(0).groupId);
            DolphinSubsidyViewPagerAdapter dolphinSubsidyViewPagerAdapter = new DolphinSubsidyViewPagerAdapter(getContext(), this.pagerEntities);
            this.mAdapter = dolphinSubsidyViewPagerAdapter;
            dolphinSubsidyViewPagerAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            this.mAdapter.setGridProperty(0, DPIUtil.dip2px(this.mContext, 5.0f), R.drawable.dolphin_single_column_grid_bg);
            this.mViewPagerContainer.setAdapter(this.mAdapter);
            this.mViewPagerContainer.getViewPager().setGridProperty(1, 4, 0, DPIUtil.dip2px(this.mContext, 5.0f));
            this.mViewPagerContainer.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinSubsidyColumnFloor.1
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i3, float f2, int i4) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i3) {
                    DolphinSubsidyColumnFloor.this.mIndicator.openLight(i3);
                    DolphinSubsidyColumnFloor.this.exposure(i3);
                }
            });
            showIndicator(this.pagerEntities.size());
            exposure(0);
            return;
        }
        hideFloor();
    }
}
