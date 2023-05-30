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
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinStoreGridAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.AdvertEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinStoreFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinStoreFloorModel;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinStoreFloor extends FrameLayout implements IFloorView<DolphinStoreFloorModel> {
    private static final String TAG = "mzdplc";
    private boolean isInit;
    private DolphinStoreGridAdapter mAdapter;
    private DolphinAdvMtaEntity mAdvMtaEntity;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private LinearLayout mContainerLayout;
    private Context mContext;
    private DolphinStoreFloorModel mFloorEntity;
    private LinearLayout mFloorLayout;
    private GridView mGridView;
    private DolphinMtaEntity mMtaEntity;

    public DolphinStoreFloor(@NonNull Context context) {
        super(context);
        this.isInit = false;
        this.mContext = context;
    }

    private void exposure() {
        List<AdvertEntity> storeList = this.mFloorEntity.getStoreList();
        if (storeList != null) {
            for (int i2 = 0; i2 < storeList.size(); i2++) {
                this.mAdvMtaEntity.adid = storeList.get(i2).advertId;
                if (!TextUtils.isEmpty(this.mAdvMtaEntity.adid)) {
                    CommonServiceUtil.sendExposureData(this.mBabelScope, "Babel_dev_expo_adv_mzdplc", this.mAdvMtaEntity.getEventParam());
                }
            }
        }
    }

    private DolphinAdvMtaEntity getAdvMtaEntity() {
        DolphinAdvMtaEntity dolphinAdvMtaEntity = new DolphinAdvMtaEntity();
        dolphinAdvMtaEntity.eventIdSuffix = TAG;
        dolphinAdvMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinStoreFloorModel dolphinStoreFloorModel = this.mFloorEntity;
        dolphinAdvMtaEntity.fno = dolphinStoreFloorModel.fno;
        dolphinAdvMtaEntity.mid = dolphinStoreFloorModel.mid;
        dolphinAdvMtaEntity.agid = dolphinStoreFloorModel.advertGroupData.get(0).groupId;
        dolphinAdvMtaEntity.adid = "";
        return dolphinAdvMtaEntity;
    }

    private DolphinMtaEntity getMtaEntity() {
        DolphinMtaEntity dolphinMtaEntity = new DolphinMtaEntity();
        dolphinMtaEntity.eventIdSuffix = TAG;
        dolphinMtaEntity.aid = this.mBabelScope.getPageName();
        DolphinStoreFloorModel dolphinStoreFloorModel = this.mFloorEntity;
        dolphinMtaEntity.fno = dolphinStoreFloorModel.fno;
        dolphinMtaEntity.mid = dolphinStoreFloorModel.mid;
        dolphinMtaEntity.sgid = "";
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

    private void initList() {
        GridView gridView = new GridView(this.mContext);
        this.mGridView = gridView;
        gridView.setSelector(R.drawable.none);
        this.mGridView.setVerticalScrollBarEnabled(false);
        this.mGridView.setFocusable(false);
        this.mGridView.setHorizontalSpacing(DPIUtil.dip2px(this.mContext, 7.0f));
        this.mGridView.setGravity(17);
        DolphinStoreGridAdapter dolphinStoreGridAdapter = new DolphinStoreGridAdapter(this.mContext);
        this.mAdapter = dolphinStoreGridAdapter;
        this.mGridView.setAdapter((ListAdapter) dolphinStoreGridAdapter);
        this.mContainerLayout.addView(this.mGridView, 0);
    }

    private void updateAdapterData() {
        GridView gridView;
        if (this.mAdapter == null || (gridView = this.mGridView) == null) {
            return;
        }
        gridView.setNumColumns(this.mFloorEntity.getStoreList().size());
        this.mAdapter.setDatas(this.mFloorEntity.getStoreList(), this.mFloorEntity.boardParams);
        this.mAdapter.setMtaData(this.mBabelScope, this.mMtaEntity, this.mAdvMtaEntity);
        this.mGridView.setLayoutParams(new LinearLayout.LayoutParams((DPIUtil.dip2px(this.mContext, 165.0f) * this.mFloorEntity.getStoreList().size()) + (DPIUtil.dip2px(this.mContext, 7.0f) * (this.mFloorEntity.getStoreList().size() - 1)), -1));
    }

    private void updateFloorBg() {
        DolphinStoreFloorConfig dolphinStoreFloorConfig = this.mFloorEntity.boardParams;
        if (dolphinStoreFloorConfig != null && dolphinStoreFloorConfig.bgStyle == 1 && !TextUtils.isEmpty(dolphinStoreFloorConfig.backgroundImage)) {
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
            DolphinStoreFloorConfig dolphinStoreFloorConfig2 = this.mFloorEntity.boardParams;
            setBackgroundColor(ColorUtil.parseColor(dolphinStoreFloorConfig2 != null ? dolphinStoreFloorConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
        }
        this.mFloorLayout.setPadding(0, this.mFloorEntity.boardParams == null ? DPIUtil.dip2px(this.mContext, 0.0f) : DPIUtil.dip2px(this.mContext, r2.marginTop), 0, this.mFloorEntity.boardParams == null ? DPIUtil.dip2px(this.mContext, 0.0f) : DPIUtil.dip2px(this.mContext, r4.marginBottom));
        this.mContainerLayout.setPadding(DPIUtil.dip2px(this.mContext, this.mFloorEntity.boardParams.marginLeft), 0, DPIUtil.dip2px(this.mContext, this.mFloorEntity.boardParams.marginRight), 0);
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_store_floor, this);
        this.mFloorLayout = (LinearLayout) findViewById(R.id.floor_layout);
        this.mContainerLayout = (LinearLayout) findViewById(R.id.goods_container);
        LogUtil.d("asdf", "\u521d\u59cb\u5316\u5e97\u94fa\u697c\u5c42");
        initList();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinStoreFloorModel dolphinStoreFloorModel, int i2) {
        if (this.isInit) {
            exposure();
            return;
        }
        this.isInit = true;
        this.mBabelScope = babelScope;
        this.mFloorEntity = dolphinStoreFloorModel;
        updateFloorBg();
        LogUtil.d("asdf", "\u68c0\u67e5\u6570\u636e\u5408\u6cd5\u6027");
        if (this.mFloorEntity.handleData()) {
            this.mAdvMtaEntity = getAdvMtaEntity();
            this.mMtaEntity = getMtaEntity();
            updateAdapterData();
            return;
        }
        LogUtil.d("asdf", "\u6570\u636e\u4e0d\u5408\u6cd5");
        hideFloor();
    }
}
