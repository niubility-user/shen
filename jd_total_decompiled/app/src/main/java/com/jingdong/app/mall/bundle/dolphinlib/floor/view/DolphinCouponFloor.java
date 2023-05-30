package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinCouponAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.CouponEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCouponFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinCouponFloor extends FrameLayout implements IFloorView<DolphinCouponFloorModel> {
    private static final String TAG = "mzyhqlc";
    private boolean isInit;
    private DolphinCouponAdapter mAdapter;
    private DolphinAdvMtaEntity mAdvMtaEntity;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private LinearLayout mContainerLayout;
    private Context mContext;
    private DolphinCouponFloorModel mFloorEntity;
    private LinearLayout mFloorLayout;
    private GridView mGridView;
    private DolphinMtaEntity mMtaEntity;

    public DolphinCouponFloor(@NonNull Context context) {
        super(context);
        this.isInit = false;
        this.mContext = context;
    }

    private void exposure() {
        List<CouponEntity> list = this.mFloorEntity.items;
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                this.mAdvMtaEntity.adid = list.get(i2).key;
                if (!TextUtils.isEmpty(this.mAdvMtaEntity.adid)) {
                    CommonServiceUtil.sendExposureData(this.mBabelScope, "Babel_dev_expo_adv_mzyhqlc", this.mAdvMtaEntity.makeAdvExpoJson());
                }
            }
        }
    }

    private DolphinAdvMtaEntity getAdvMtaEntity() {
        DolphinAdvMtaEntity dolphinAdvMtaEntity = new DolphinAdvMtaEntity();
        dolphinAdvMtaEntity.eventIdSuffix = TAG;
        dolphinAdvMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinCouponFloorModel dolphinCouponFloorModel = this.mFloorEntity;
        dolphinAdvMtaEntity.fno = dolphinCouponFloorModel.fno;
        dolphinAdvMtaEntity.mid = dolphinCouponFloorModel.mid;
        dolphinAdvMtaEntity.agid = "";
        dolphinAdvMtaEntity.adid = "";
        return dolphinAdvMtaEntity;
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

    private void initList() {
        GridView gridView = new GridView(this.mContext);
        this.mGridView = gridView;
        gridView.setSelector(R.drawable.none);
        this.mGridView.setVerticalScrollBarEnabled(false);
        this.mGridView.setFocusable(false);
        this.mGridView.setHorizontalSpacing(DPIUtil.dip2px(this.mContext, 3.0f));
        this.mGridView.setGravity(17);
        DolphinCouponAdapter dolphinCouponAdapter = new DolphinCouponAdapter(this.mContext);
        this.mAdapter = dolphinCouponAdapter;
        this.mGridView.setAdapter((ListAdapter) dolphinCouponAdapter);
        this.mContainerLayout.addView(this.mGridView, 0);
    }

    private void updateAdapterData() {
        if (this.mAdapter == null || this.mGridView == null) {
            return;
        }
        int size = this.mFloorEntity.items.size();
        LogUtil.d("asdf", "size:" + size);
        this.mGridView.setNumColumns(size);
        DolphinCouponAdapter dolphinCouponAdapter = this.mAdapter;
        DolphinCouponFloorModel dolphinCouponFloorModel = this.mFloorEntity;
        dolphinCouponAdapter.setDatas(dolphinCouponFloorModel.items, dolphinCouponFloorModel.boardParams);
        this.mAdapter.setMtaData(this.mBabelScope, this.mAdvMtaEntity);
        this.mGridView.setLayoutParams(new LinearLayout.LayoutParams((DPIUtil.dip2px(this.mContext, 111.0f) * size) + (DPIUtil.dip2px(this.mContext, 3.0f) * (size - 1)), -1));
    }

    private void updateFloorBg() {
        DolphinCouponFloorConfig dolphinCouponFloorConfig = this.mFloorEntity.boardParams;
        if (dolphinCouponFloorConfig != null && dolphinCouponFloorConfig.bgStyle == 1 && !TextUtils.isEmpty(dolphinCouponFloorConfig.backgroundImage)) {
            if (this.mBgView == null) {
                ImageView imageView = CommonServiceUtil.getImageView(getContext());
                this.mBgView = imageView;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.mBgView, 0, new FrameLayout.LayoutParams(-1, -1));
            }
            CommonServiceUtil.displayImage(this.mFloorEntity.boardParams.backgroundImage, this.mBgView);
            setBackgroundColor(0);
        } else {
            ImageView imageView2 = this.mBgView;
            if (imageView2 != null) {
                imageView2.setImageDrawable(null);
            }
            DolphinCouponFloorConfig dolphinCouponFloorConfig2 = this.mFloorEntity.boardParams;
            setBackgroundColor(ColorUtil.parseColor(dolphinCouponFloorConfig2 != null ? dolphinCouponFloorConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
        }
        this.mFloorLayout.setPadding(0, this.mFloorEntity.boardParams == null ? DPIUtil.dip2px(this.mContext, 0.0f) : DPIUtil.dip2px(this.mContext, r2.marginTop), 0, this.mFloorEntity.boardParams == null ? DPIUtil.dip2px(this.mContext, 0.0f) : DPIUtil.dip2px(this.mContext, r4.marginBottom));
        this.mContainerLayout.setPadding(DPIUtil.dip2px(this.mContext, this.mFloorEntity.boardParams.marginLeft), 0, DPIUtil.dip2px(this.mContext, this.mFloorEntity.boardParams.marginRight), 0);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_coupon_floor, this);
        this.mFloorLayout = (LinearLayout) findViewById(R.id.floor_layout);
        this.mContainerLayout = (LinearLayout) findViewById(R.id.coupon_container);
        LogUtil.d("asdf", "\u521d\u59cb\u5316\u4f18\u60e0\u5238\u697c\u5c42");
        initList();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinCouponFloorModel dolphinCouponFloorModel, int i2) {
        if (this.isInit) {
            exposure();
            return;
        }
        this.isInit = true;
        this.mBabelScope = babelScope;
        this.mFloorEntity = dolphinCouponFloorModel;
        updateFloorBg();
        LogUtil.d("asdf", "\u68c0\u67e5\u6570\u636e\u5408\u6cd5\u6027");
        if (this.mFloorEntity.handleData()) {
            this.mAdvMtaEntity = getAdvMtaEntity();
            updateAdapterData();
            return;
        }
        LogUtil.d("asdf", "\u6570\u636e\u4e0d\u5408\u6cd5");
        hideFloor();
    }
}
