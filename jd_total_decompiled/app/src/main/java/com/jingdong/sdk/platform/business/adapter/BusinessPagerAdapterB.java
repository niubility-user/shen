package com.jingdong.sdk.platform.business.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.business.entity.BusinessItemEntity;
import com.jingdong.sdk.platform.business.views.CommonBusinessItemViewB;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class BusinessPagerAdapterB extends PagerAdapter {
    private static final int COUNT_OF_ONE_PAGE = 3;
    private boolean isDarkTheme;
    private Context mContext;
    private View.OnClickListener mItemClickListener;
    private List<BusinessItemEntity> mItems;
    private int mTotalSize;
    private int pageSize;
    private boolean tenthRevision;
    private List<WeakReference<LinearLayout>> viewList = new ArrayList();

    public BusinessPagerAdapterB(Context context) {
        this.mContext = context;
    }

    private LinearLayout initView(LinearLayout linearLayout, int i2) {
        int i3;
        if (linearLayout == null) {
            linearLayout = new LinearLayout(this.mContext);
            linearLayout.setOrientation(0);
        }
        linearLayout.removeAllViews();
        int i4 = i2 % this.pageSize;
        int i5 = i4 * 3;
        int i6 = (i4 + 1) * 3;
        int i7 = this.mTotalSize;
        if (i6 >= i7) {
            i6 = i7;
        }
        if (i5 >= 0 && i6 <= i7 && i6 >= i5) {
            List<BusinessItemEntity> subList = this.mItems.subList(i5, i6);
            int size = subList.size();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.weight = 1.0f;
            layoutParams.gravity = 16;
            for (int i8 = 0; i8 < 3; i8++) {
                CommonBusinessItemViewB commonBusinessItemViewB = (CommonBusinessItemViewB) LayoutInflater.from(this.mContext).inflate(R.layout.lib_platform_floor_business_item_b, (ViewGroup) null);
                if (size != 1 && (size - 1 == 0 || i8 == 0 || i8 % i3 != 0)) {
                    commonBusinessItemViewB.setItemRightViewGone(true);
                } else {
                    commonBusinessItemViewB.setItemRightViewGone(false);
                }
                if (i8 <= size - 1) {
                    BusinessItemEntity businessItemEntity = subList.get(i8);
                    commonBusinessItemViewB.setTag(businessItemEntity);
                    commonBusinessItemViewB.bindData2View(businessItemEntity, this.isDarkTheme, this.tenthRevision);
                    commonBusinessItemViewB.setOnClickListener(this.mItemClickListener);
                    commonBusinessItemViewB.setVisibility(0);
                } else {
                    commonBusinessItemViewB.setVisibility(this.mTotalSize < 3 && this.pageSize == 1 ? 8 : 4);
                }
                linearLayout.addView(commonBusinessItemViewB, layoutParams);
            }
        }
        return linearLayout;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof LinearLayout) {
            LinearLayout linearLayout = (LinearLayout) obj;
            viewGroup.removeView(linearLayout);
            this.viewList.add(new WeakReference<>(linearLayout));
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        int i2 = this.pageSize;
        if (i2 <= 0) {
            return 0;
        }
        return i2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public float getPageWidth(int i2) {
        return 1.0f;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = null;
        if (this.viewList.size() > 0) {
            if (this.viewList.get(0) != null) {
                linearLayout = initView(this.viewList.get(0).get(), i2);
                this.viewList.remove(0);
            }
        } else {
            linearLayout = initView(null, i2);
        }
        viewGroup.addView(linearLayout);
        return linearLayout;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void setData(List<BusinessItemEntity> list, boolean z, boolean z2) {
        this.mItems = list;
        this.isDarkTheme = z;
        this.mTotalSize = list.size();
        int size = list.size();
        this.tenthRevision = z2;
        int i2 = size % 3;
        int i3 = size / 3;
        if (i2 != 0) {
            i3++;
        }
        this.pageSize = i3;
    }

    public void setItemOnClickListener(View.OnClickListener onClickListener) {
        this.mItemClickListener = onClickListener;
    }
}
