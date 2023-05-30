package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinGoodsGridViewPagerAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinCountDownView;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinPageIndicatorView;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinViewPagerContainer;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsGridPagerEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinCountDownGridFloor extends FrameLayout implements IFloorView<DolphinCountDownGridFloorModel> {
    private static final int GRID_COLUMN = 3;
    private static final int GRID_ROW = 2;
    private static final String TAG = "f2d6";
    private boolean isInit;
    private DolphinGoodsGridViewPagerAdapter mAdapter;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private RelativeLayout mContainerLayout;
    private Context mContext;
    private TextView mCountDownText;
    private DolphinCountDownView mCountDownView;
    private DolphinCountDownGridFloorModel mFloorEntity;
    private LinearLayout mFloorLayout;
    private ImageWraper mHeadBgView;
    private LinearLayout mHeadLayout;
    private DolphinPageIndicatorView mIndicator;
    private DolphinMtaEntity mMtaEntity;
    private ArrayList<GoodsGridPagerEntity> mPagerEntities;
    private DolphinViewPagerContainer mViewPagerContainer;

    public DolphinCountDownGridFloor(Context context) {
        super(context);
        this.isInit = false;
        this.mContext = context;
    }

    private ArrayList<GoodsGridPagerEntity> disposeDatas(List<GoodsEntity> list) {
        ArrayList<GoodsGridPagerEntity> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 % 6 == 0) {
                arrayList.add(new GoodsGridPagerEntity());
            }
            if (arrayList.size() > 0) {
                arrayList.get(arrayList.size() - 1).goods.add(list.get(i2));
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
            CommonServiceUtil.sendExposureData(this.mBabelScope, "Babel_dev_expo_sku_f2d6", this.mMtaEntity.getEventParam());
        }
    }

    private DolphinMtaEntity getMtaEntity() {
        DolphinMtaEntity dolphinMtaEntity = new DolphinMtaEntity();
        dolphinMtaEntity.eventIdSuffix = TAG;
        dolphinMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinCountDownGridFloorModel dolphinCountDownGridFloorModel = this.mFloorEntity;
        dolphinMtaEntity.fno = dolphinCountDownGridFloorModel.fno;
        dolphinMtaEntity.mid = dolphinCountDownGridFloorModel.mid;
        dolphinMtaEntity.sgid = dolphinCountDownGridFloorModel.productGroupData.get(0).groupId;
        dolphinMtaEntity.sku = "";
        return dolphinMtaEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideFloor() {
        setVisibility(8);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = 0;
        } else {
            layoutParams = new RecyclerView.LayoutParams(-1, 0);
        }
        setLayoutParams(layoutParams);
    }

    private void showIndicator(int i2) {
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

    @SuppressLint({"SimpleDateFormat"})
    private void updateCountDown(DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig) {
        long j2;
        try {
            j2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dolphinCountDownGridFloorConfig.countDownEndTime).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            j2 = 0;
        }
        if (j2 == 0) {
            hideFloor();
            return;
        }
        this.mCountDownView.update(j2, 0L);
        this.mCountDownView.setStyle(0, 0, dolphinCountDownGridFloorConfig.countDownTextColor, DPIUtil.dip2px(this.mContext, 12.0f), dolphinCountDownGridFloorConfig.countDownBgColor, DPIUtil.dip2px(this.mContext, 2.0f));
        this.mCountDownView.setCountDownListener(new DolphinCountDownView.CountDownSimpleListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinCountDownGridFloor.2
            @Override // com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinCountDownView.CountDownSimpleListener
            public void finish() {
                DolphinCountDownGridFloor.this.hideFloor();
            }

            @Override // com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinCountDownView.CountDownSimpleListener
            public void oneHourFinish() {
            }
        });
    }

    private void updateFloorBg() {
        DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig = this.mFloorEntity.boardParams;
        if (dolphinCountDownGridFloorConfig != null && dolphinCountDownGridFloorConfig.bgStyle == 1 && !TextUtils.isEmpty(dolphinCountDownGridFloorConfig.backgroundImage)) {
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
        DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig2 = this.mFloorEntity.boardParams;
        setBackgroundColor(ColorUtil.parseColor(dolphinCountDownGridFloorConfig2 != null ? dolphinCountDownGridFloorConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_count_down_grid_floor, this);
        this.mFloorLayout = (LinearLayout) findViewById(R.id.floor_layout);
        this.mHeadLayout = (LinearLayout) findViewById(R.id.floor_head_layout);
        this.mHeadBgView = (ImageWraper) findViewById(R.id.count_down_bg);
        this.mCountDownView = (DolphinCountDownView) findViewById(R.id.count_down_view);
        this.mCountDownText = (TextView) findViewById(R.id.count_down_text);
        this.mContainerLayout = (RelativeLayout) findViewById(R.id.content_layout);
        this.mIndicator = (DolphinPageIndicatorView) findViewById(R.id.page_indicator_view);
        DolphinViewPagerContainer dolphinViewPagerContainer = new DolphinViewPagerContainer(this.mContext);
        this.mViewPagerContainer = dolphinViewPagerContainer;
        this.mContainerLayout.addView(dolphinViewPagerContainer);
        this.mIndicator.setCursor(DPIUtil.dip2px(this.mContext, 12.0f), DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 3.0f), DPIUtil.dip2px(this.mContext, 5.0f));
        this.mContainerLayout.setBackgroundResource(R.drawable.dolphin_left_right_bottom_grid_bg);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinCountDownGridFloorModel dolphinCountDownGridFloorModel, int i2) {
        if (this.isInit) {
            exposure(0);
            return;
        }
        this.isInit = true;
        this.mBabelScope = babelScope;
        this.mFloorEntity = dolphinCountDownGridFloorModel;
        updateFloorBg();
        DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig = this.mFloorEntity.boardParams;
        if (dolphinCountDownGridFloorConfig != null) {
            if (!TextUtils.isEmpty(dolphinCountDownGridFloorConfig.headBgImage)) {
                CommonServiceUtil.displayImage(this.mFloorEntity.boardParams.headBgImage, this.mHeadBgView);
            }
            updateCountDown(this.mFloorEntity.boardParams);
            this.mCountDownText.setText(this.mFloorEntity.boardParams.countDownAfterText);
            this.mCountDownText.setTextColor(ColorUtil.parseColor(this.mFloorEntity.boardParams.countDownAfterTextColor, Color.parseColor("#FFFFFF")));
        }
        if (this.mFloorEntity.handleData()) {
            this.mViewPagerContainer.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            this.mPagerEntities = disposeDatas(this.mFloorEntity.getFirstList());
            this.mMtaEntity = getMtaEntity();
            DolphinGoodsGridViewPagerAdapter dolphinGoodsGridViewPagerAdapter = new DolphinGoodsGridViewPagerAdapter(getContext(), this.mPagerEntities);
            this.mAdapter = dolphinGoodsGridViewPagerAdapter;
            dolphinGoodsGridViewPagerAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            this.mAdapter.setGridProperty(3, 2, DPIUtil.dip2px(this.mContext, 1.0f), DPIUtil.dip2px(this.mContext, 1.0f), -1);
            this.mAdapter.setLeftAndRightBottomRadius(true);
            this.mViewPagerContainer.setAdapter(this.mAdapter);
            this.mViewPagerContainer.getViewPager().setGridProperty(3, 2, DPIUtil.dip2px(this.mContext, 1.0f), DPIUtil.dip2px(this.mContext, 1.0f));
            this.mViewPagerContainer.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinCountDownGridFloor.1
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i3, float f2, int i4) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i3) {
                    DolphinCountDownGridFloor.this.mIndicator.openLight(i3);
                    DolphinCountDownGridFloor.this.exposure(i3);
                }
            });
            showIndicator(this.mPagerEntities.size());
            exposure(0);
            return;
        }
        hideFloor();
    }
}
