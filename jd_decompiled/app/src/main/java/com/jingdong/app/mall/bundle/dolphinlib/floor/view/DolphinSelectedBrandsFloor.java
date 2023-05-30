package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
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
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinSelectedBrandsListAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinCountDownGridFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinSelectedBrandsFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSelectedBrandsFloor extends FrameLayout implements IFloorView<DolphinSelectedBrandsFloorModel> {
    private static final String TAG = "jxpp";
    private DolphinSelectedBrandsFloorModel baseFloorModel;
    private ImageWraper floor_img;
    private LinearLayout goods_container;
    private DolphinSelectedBrandsListAdapter mAdapter;
    private BabelScope mBabelScope;
    private ImageView mBgView;
    private Context mContext;
    private GridView mGridView;

    public DolphinSelectedBrandsFloor(Context context) {
        super(context);
        this.mContext = context;
    }

    private void addList() {
        GridView gridView = new GridView(this.mContext);
        this.mGridView = gridView;
        gridView.setSelector(R.drawable.none);
        this.mGridView.setVerticalScrollBarEnabled(false);
        this.mGridView.setFocusable(false);
        this.mGridView.setHorizontalSpacing(DPIUtil.dip2px(this.mContext, 7.0f));
        this.mGridView.setGravity(17);
        DolphinSelectedBrandsListAdapter dolphinSelectedBrandsListAdapter = new DolphinSelectedBrandsListAdapter(this.mContext);
        this.mAdapter = dolphinSelectedBrandsListAdapter;
        this.mGridView.setAdapter((ListAdapter) dolphinSelectedBrandsListAdapter);
        this.goods_container.addView(this.mGridView, 0);
    }

    private DolphinMtaEntity getMtaEntity(String str, DolphinSelectedBrandsFloorModel dolphinSelectedBrandsFloorModel) {
        DolphinMtaEntity dolphinMtaEntity = new DolphinMtaEntity();
        dolphinMtaEntity.eventIdSuffix = TAG;
        dolphinMtaEntity.aid = this.mBabelScope.getPageName();
        dolphinMtaEntity.fno = dolphinSelectedBrandsFloorModel.fno;
        dolphinMtaEntity.mid = dolphinSelectedBrandsFloorModel.mid;
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

    private void updateAdapterData() {
        if (this.mAdapter == null || this.baseFloorModel.productGroupData.size() == 0) {
            return;
        }
        if (this.baseFloorModel.advertGroupData.get(0).advertList.size() > 0) {
            CommonServiceUtil.displayImage(this.baseFloorModel.advertGroupData.get(0).advertList.get(0).pictureUrl, this.floor_img);
            this.floor_img.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinSelectedBrandsFloor.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommonServiceUtil.execJump(DolphinSelectedBrandsFloor.this.mContext, DolphinSelectedBrandsFloor.this.baseFloorModel.advertGroupData.get(0).advertList.get(0).jump);
                }
            });
        }
        List<GoodsEntity> list = this.baseFloorModel.productGroupData.get(0).productList;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mAdapter.setMtaData(this.mBabelScope, getMtaEntity(this.baseFloorModel.productGroupData.get(0).groupId, this.baseFloorModel));
        this.mGridView.setNumColumns(list.size());
        this.mAdapter.setDatas(list, this.baseFloorModel.boardParams);
        this.mGridView.setLayoutParams(new LinearLayout.LayoutParams((DPIUtil.dip2px(this.mContext, 90.0f) * list.size()) + (DPIUtil.dip2px(this.mContext, 7.0f) * (list.size() - 1)), -1));
    }

    private void updateFloorBg(DolphinSelectedBrandsFloorModel dolphinSelectedBrandsFloorModel) {
        DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig = dolphinSelectedBrandsFloorModel.boardParams;
        if (dolphinCountDownGridFloorConfig != null && dolphinCountDownGridFloorConfig.bgStyle == 1 && !TextUtils.isEmpty(dolphinCountDownGridFloorConfig.backgroundImage)) {
            if (this.mBgView == null) {
                ImageView imageView = CommonServiceUtil.getImageView(getContext());
                this.mBgView = imageView;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.mBgView, 0, new FrameLayout.LayoutParams(-1, -1));
            }
            CommonServiceUtil.displayImage(dolphinSelectedBrandsFloorModel.boardParams.backgroundImage, this.mBgView);
            setBackgroundColor(0);
            return;
        }
        ImageView imageView2 = this.mBgView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(null);
        }
        DolphinCountDownGridFloorConfig dolphinCountDownGridFloorConfig2 = dolphinSelectedBrandsFloorModel.boardParams;
        setBackgroundColor(ColorUtil.parseColor(dolphinCountDownGridFloorConfig2 != null ? dolphinCountDownGridFloorConfig2.backgroundColor : "", ViewCompat.MEASURED_SIZE_MASK));
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LayoutInflater.from(this.mContext).inflate(R.layout.dolphin_selected_brands_floor, this);
        this.floor_img = (ImageWraper) findViewById(R.id.floor_img);
        this.goods_container = (LinearLayout) findViewById(R.id.goods_container);
        addList();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(@NonNull BabelScope babelScope, @NonNull DolphinSelectedBrandsFloorModel dolphinSelectedBrandsFloorModel, int i2) {
        this.mBabelScope = babelScope;
        this.baseFloorModel = dolphinSelectedBrandsFloorModel;
        updateFloorBg(dolphinSelectedBrandsFloorModel);
        if (dolphinSelectedBrandsFloorModel.handleData()) {
            updateAdapterData();
        } else {
            hideFloor();
        }
    }
}
