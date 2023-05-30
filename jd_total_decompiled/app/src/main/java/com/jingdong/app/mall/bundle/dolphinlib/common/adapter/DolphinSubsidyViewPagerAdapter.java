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
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsAdEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.GoodsGridPagerEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinSubsidyViewPagerAdapter extends AbstractDolphinViewPagerAdapter {
    public static final int GRID_SINGLE_COLUMN = 1;
    private GoodsAdEntity adEntity;
    private int horizontalSpacing;
    private boolean isLeftAndRightBottomRadius;
    private BabelScope mBabelScope;
    private Context mContext;
    private DolphinMtaEntity mMtaEntity;
    private ArrayList<GoodsGridPagerEntity> pagerEntityList;
    private int resBg;
    private int verticalSpacing;
    private SparseArray<RelativeLayout> viewList;

    public DolphinSubsidyViewPagerAdapter(Context context, ArrayList<GoodsGridPagerEntity> arrayList) {
        this(context, arrayList, null);
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
            gridView.setNumColumns(1);
            gridView.setVerticalScrollBarEnabled(false);
            gridView.setFocusable(false);
            gridView.setHorizontalSpacing(this.horizontalSpacing);
            gridView.setVerticalSpacing(this.verticalSpacing);
            List<GoodsEntity> list = this.pagerEntityList.get(i2).goods;
            DolphinSubsidyColumnAdapter dolphinSubsidyColumnAdapter = new DolphinSubsidyColumnAdapter(this.mContext);
            dolphinSubsidyColumnAdapter.setMtaData(this.mBabelScope, this.mMtaEntity);
            dolphinSubsidyColumnAdapter.setDatas(list, this.pagerEntityList.get(i2).boardParams);
            gridView.setAdapter((ListAdapter) dolphinSubsidyColumnAdapter);
            relativeLayout2.addView(gridView);
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

    @Override // com.jingdong.app.mall.bundle.dolphinlib.common.adapter.AbstractDolphinViewPagerAdapter
    public void setChildOnTop(int i2) {
    }

    public void setGridProperty(int i2, int i3, int i4) {
        this.horizontalSpacing = i2;
        this.verticalSpacing = i3;
        this.resBg = i4;
    }

    public void setMtaData(BabelScope babelScope, DolphinMtaEntity dolphinMtaEntity) {
        this.mBabelScope = babelScope;
        this.mMtaEntity = dolphinMtaEntity;
    }

    public DolphinSubsidyViewPagerAdapter(Context context, ArrayList<GoodsGridPagerEntity> arrayList, GoodsAdEntity goodsAdEntity) {
        this.isLeftAndRightBottomRadius = false;
        this.mContext = context;
        this.pagerEntityList = arrayList;
        this.adEntity = goodsAdEntity;
        this.viewList = new SparseArray<>();
    }
}
