package com.jingdong.common.widget.custom.livewidget.loopgallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes12.dex */
public abstract class LoopGalleryPagerAdapter<E> extends PagerAdapter {
    protected View first;
    protected boolean isCarousel;
    protected View last;
    protected Context mContext;

    public LoopGalleryPagerAdapter(Context context) {
        this.mContext = context;
        initItem();
    }

    private View getItemView() {
        View createItemView = createItemView(this.mContext);
        createItemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.livewidget.loopgallery.LoopGalleryPagerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoopGalleryPagerAdapter loopGalleryPagerAdapter = LoopGalleryPagerAdapter.this;
                loopGalleryPagerAdapter.onClick(loopGalleryPagerAdapter.getRealPosition(view.getId()));
            }
        });
        return createItemView;
    }

    private void initItem() {
        if (isTrueCarousel()) {
            if (this.first == null) {
                this.first = getItemView();
            }
            if (this.last == null) {
                this.last = getItemView();
            }
        }
    }

    private boolean isTrueCarousel() {
        return this.isCarousel && getItemCount() >= 2;
    }

    protected abstract void bindData(View view, E e2);

    protected abstract View createItemView(Context context);

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return getItemCount() + (isTrueCarousel() ? 4 : 0);
    }

    public abstract int getItemCount();

    public abstract E getItemEntity(int i2);

    protected int getRealPosition(int i2) {
        if (!this.isCarousel || getItemCount() <= 1) {
            return i2;
        }
        int itemCount = (i2 - 2) % getItemCount();
        return itemCount < 0 ? itemCount + getItemCount() : itemCount;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        View itemView;
        if (Log.D) {
            Log.d("LoopGalleryPagerAdapter", "instantiateItem: position = " + i2);
        }
        try {
            boolean z = this.isCarousel;
            if (!z || (itemView = this.first) == null || i2 != 2) {
                if (z && this.last != null && i2 == getCount() - 4) {
                    itemView = this.last;
                } else {
                    itemView = getItemView();
                }
            }
            itemView.setId(i2);
            viewGroup.addView(itemView);
            bindData(itemView, getItemEntity(getRealPosition(i2)));
            return itemView;
        } catch (Exception e2) {
            View itemView2 = getItemView();
            e2.printStackTrace();
            return itemView2;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        initItem();
        super.notifyDataSetChanged();
    }

    public abstract void onClick(int i2);

    public void setCarousel(boolean z) {
        this.isCarousel = z;
    }
}
