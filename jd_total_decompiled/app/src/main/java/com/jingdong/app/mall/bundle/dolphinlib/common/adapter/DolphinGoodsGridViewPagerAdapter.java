package com.jingdong.app.mall.bundle.dolphinlib.common.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.view.DolphinGoodsAdView;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsAdEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsGridPagerEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinGoodsGridViewPagerAdapter extends AbstractDolphinViewPagerAdapter {
    public static final int GRID_3_OR_4_COLUMN = 3;
    public static final int GRID_DOUBLE_COLUMN = 2;
    public static final int GRID_SINGLE_COLUMN = 1;
    private GoodsAdEntity adEntity;
    private int adNumColumn;
    private int adNumRow;
    private DolphinGoodsAdView adView;
    private int gridColumnType;
    private int gridColumnWidth;
    private int gridNumColumn;
    private int gridNumRow;
    private int gridRowHeight;
    private int horizontalSpacing;
    private BabelScope mBabelScope;
    private Context mContext;
    private DolphinMtaEntity mMtaEntity;
    private ArrayList<GoodsGridPagerEntity> pagerEntityList;
    private int resBg;
    private int verticalSpacing;
    private boolean isLeftAndRightBottomRadius = false;
    private SparseArray<RelativeLayout> viewList = new SparseArray<>();

    public DolphinGoodsGridViewPagerAdapter(Context context, ArrayList<GoodsGridPagerEntity> arrayList) {
        this.mContext = context;
        this.pagerEntityList = arrayList;
    }

    private void createAdapter(GridView gridView, List<GoodsEntity> list) {
        int i2 = this.gridColumnType;
        if (i2 == 1) {
            DolphinSingleColumnGoodsGridAdapter dolphinSingleColumnGoodsGridAdapter = new DolphinSingleColumnGoodsGridAdapter(this.mContext);
            dolphinSingleColumnGoodsGridAdapter.setDatas(list, getGridItemWidth(), this.gridNumColumn, this.gridNumRow, this.resBg);
            dolphinSingleColumnGoodsGridAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            gridView.setAdapter((ListAdapter) dolphinSingleColumnGoodsGridAdapter);
        } else if (i2 != 3) {
        } else {
            DolphinGoodsGridAdapter dolphinGoodsGridAdapter = new DolphinGoodsGridAdapter(this.mContext);
            dolphinGoodsGridAdapter.setDatas(list, getGridItemWidth(), this.gridNumColumn, this.gridNumRow, this.resBg);
            dolphinGoodsGridAdapter.setLeftAndRightBottomRadius(this.isLeftAndRightBottomRadius);
            dolphinGoodsGridAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            gridView.setAdapter((ListAdapter) dolphinGoodsGridAdapter);
        }
    }

    private int getGridItemWidth() {
        int width = DPIUtil.getWidth(this.mContext) - (DPIUtil.dip2px(this.mContext, 9.0f) * 2);
        int i2 = this.horizontalSpacing;
        int i3 = this.gridNumColumn;
        int i4 = (width - (i2 * (i3 - 1))) / i3;
        this.gridColumnWidth = i4;
        return i4;
    }

    private void setAdCardLayoutParams(int i2, int i3) {
        if (this.adView != null) {
            this.adView.setLayoutParams(new RelativeLayout.LayoutParams(i2, i3));
            this.adView.setLayoutSize(i2, i3);
        }
    }

    private void showAdCard(ViewGroup viewGroup) {
        DolphinGoodsAdView dolphinGoodsAdView = new DolphinGoodsAdView(this.mContext);
        this.adView = dolphinGoodsAdView;
        dolphinGoodsAdView.setMtaData(this.mBabelScope, this.mMtaEntity);
        this.adView.update(this.adEntity, this.resBg);
        viewGroup.addView(this.adView);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.pagerEntityList.size();
    }

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.adapter.AbstractDolphinViewPagerAdapter
    public RelativeLayout getCurrent(int i2) {
        return this.viewList.get(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        RelativeLayout relativeLayout;
        if (this.viewList.indexOfKey(i2) >= 0) {
            relativeLayout = getCurrent(i2);
        } else {
            RelativeLayout relativeLayout2 = new RelativeLayout(this.mContext);
            GridView gridView = new GridView(this.mContext);
            gridView.setSelector(R.drawable.none);
            gridView.setNumColumns(this.gridNumColumn);
            gridView.setVerticalScrollBarEnabled(false);
            gridView.setFocusable(false);
            gridView.setHorizontalSpacing(this.horizontalSpacing);
            gridView.setVerticalSpacing(this.verticalSpacing);
            createAdapter(gridView, this.pagerEntityList.get(i2).goods);
            relativeLayout2.addView(gridView);
            if (i2 == 0 && this.adEntity != null) {
                showAdCard(relativeLayout2);
            }
            this.viewList.put(i2, relativeLayout2);
            relativeLayout = relativeLayout2;
        }
        viewGroup.addView(relativeLayout);
        return relativeLayout;
    }

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.adapter.AbstractDolphinViewPagerAdapter
    public boolean isChildOnTop(int i2) {
        if (this.viewList.get(i2) != null) {
            return !ViewCompat.canScrollVertically(this.viewList.get(i2), -1);
        }
        return true;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public void setAdProperty(int i2, int i3, GoodsAdEntity goodsAdEntity) {
        this.adNumColumn = i2;
        this.adNumRow = i3;
        this.adEntity = goodsAdEntity;
    }

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.adapter.AbstractDolphinViewPagerAdapter
    public void setChildOnTop(int i2) {
    }

    public void setGridProperty(int i2, int i3, int i4, int i5, int i6) {
        this.gridNumColumn = i2;
        this.gridNumRow = i3;
        this.horizontalSpacing = i4;
        this.verticalSpacing = i5;
        this.resBg = i6;
        if (i2 == 1) {
            this.gridColumnType = 1;
        } else if (i2 == 2) {
            this.gridColumnType = 2;
        } else {
            this.gridColumnType = 3;
        }
    }

    public void setLeftAndRightBottomRadius(boolean z) {
        this.isLeftAndRightBottomRadius = z;
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }

    public void syncHeight(int i2) {
        this.gridRowHeight = i2;
        int gridItemWidth = getGridItemWidth();
        int i3 = this.adNumColumn;
        int i4 = (gridItemWidth * i3) + (this.horizontalSpacing * (i3 - 1));
        int i5 = this.gridRowHeight;
        int i6 = this.adNumRow;
        setAdCardLayoutParams(i4, (i5 * i6) + (this.verticalSpacing * (i6 - 1)));
    }
}
