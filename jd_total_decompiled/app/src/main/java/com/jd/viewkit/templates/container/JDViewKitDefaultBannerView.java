package com.jd.viewkit.templates.container;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.jdviewkit.R;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitDefaultBannerView extends JDViewKitBaseLayout<JDViewKitVirtualView> {
    private static final int DELAY = 3000;
    int currentItem;
    private boolean isAutoPlay;
    LinearLayout linearLayout;
    private Handler mHandler;
    private MyPageAdapter myPageAdapter;
    List<View> pointList;
    private final Runnable task;
    List<View> viewList;
    ViewPager viewPager;

    /* loaded from: classes18.dex */
    private class MyOnPageChangeLister implements ViewPager.OnPageChangeListener {
        private MyOnPageChangeLister() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (i2 != 0) {
                if (i2 == 1) {
                    JDViewKitDefaultBannerView.this.isAutoPlay = false;
                    return;
                } else if (i2 != 2) {
                    return;
                } else {
                    JDViewKitDefaultBannerView.this.isAutoPlay = true;
                    return;
                }
            }
            JDViewKitDefaultBannerView jDViewKitDefaultBannerView = JDViewKitDefaultBannerView.this;
            int i3 = jDViewKitDefaultBannerView.currentItem;
            if (i3 == 0) {
                jDViewKitDefaultBannerView.viewPager.setCurrentItem(jDViewKitDefaultBannerView.pointList.size(), false);
            } else if (i3 == jDViewKitDefaultBannerView.pointList.size() + 1) {
                JDViewKitDefaultBannerView.this.viewPager.setCurrentItem(1, false);
            }
            for (int i4 = 0; i4 < JDViewKitDefaultBannerView.this.pointList.size(); i4++) {
                View view = JDViewKitDefaultBannerView.this.pointList.get(i4);
                if (i4 == JDViewKitDefaultBannerView.this.viewPager.getCurrentItem() - 1) {
                    view.setBackgroundResource(R.drawable.point_pressed);
                } else {
                    view.setBackgroundResource(R.drawable.point_normal);
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            JDViewKitDefaultBannerView jDViewKitDefaultBannerView = JDViewKitDefaultBannerView.this;
            jDViewKitDefaultBannerView.currentItem = i2;
            jDViewKitDefaultBannerView.sendExpoFromPos(i2);
        }
    }

    /* loaded from: classes18.dex */
    private class MyPageAdapter extends PagerAdapter {
        private MyPageAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView(JDViewKitDefaultBannerView.this.viewList.get(i2));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return JDViewKitDefaultBannerView.this.viewList.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            viewGroup.addView(JDViewKitDefaultBannerView.this.viewList.get(i2));
            return JDViewKitDefaultBannerView.this.viewList.get(i2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public JDViewKitDefaultBannerView(@NonNull Context context) {
        super(context);
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitDefaultBannerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitDefaultBannerView.this.isAutoPlay) {
                    JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
                    return;
                }
                JDViewKitDefaultBannerView.this.viewPager.setCurrentItem(JDViewKitDefaultBannerView.this.viewPager.getCurrentItem() + 1);
                JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
            }
        };
        this.mContext = context;
        initView();
    }

    private void clearData() {
        this.viewPager.setAdapter(null);
        this.linearLayout.removeAllViews();
        this.viewList.clear();
        this.pointList.clear();
        this.mHandler.removeCallbacks(this.task);
    }

    private JDViewKitAbsoluteLayout getView(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        return JDViewKitViewFactory.getView(this.mContext, jDViewKitDataSourceModel, getDslsMap().get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
    }

    private void initView() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.banner, (ViewGroup) this, true);
        this.viewPager = (ViewPager) inflate.findViewById(R.id.mainViewPager);
        this.linearLayout = (LinearLayout) inflate.findViewById(R.id.mainPointView);
        this.viewList = new ArrayList();
        this.pointList = new ArrayList();
    }

    private void startCarousel() {
        this.isAutoPlay = true;
        this.mHandler.postDelayed(this.task, 3000L);
    }

    private void stopCarousel() {
        this.isAutoPlay = false;
        this.mHandler.removeCallbacks(this.task);
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    public void sendExpoFromPos(int i2) {
        List<JDViewKitDataSourceModel> list = this.dataSourceModels;
        if (list == null || i2 < 0 || list.size() <= i2) {
            return;
        }
        sendExpo(this.dataSourceModels.get(i2));
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        clearData();
        if (this.dataSourceModels.size() == 0) {
            return;
        }
        List<JDViewKitDataSourceModel> list2 = this.dataSourceModels;
        this.viewList.add(getView(list2.get(list2.size() - 1)));
        Iterator<JDViewKitDataSourceModel> it = this.dataSourceModels.iterator();
        while (it.hasNext()) {
            this.viewList.add(getView(it.next()));
        }
        this.viewList.add(getView(this.dataSourceModels.get(0)));
        this.pointList = new ArrayList();
        for (int i2 = 0; i2 < this.dataSourceModels.size(); i2++) {
            View view = new View(this.mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.leftMargin = 30;
            view.setLayoutParams(layoutParams);
            view.setBackgroundResource(R.drawable.point_normal);
            if (i2 == 0) {
                view.setBackgroundResource(R.drawable.point_pressed);
            }
            this.linearLayout.addView(view);
            this.pointList.add(view);
        }
        MyPageAdapter myPageAdapter = new MyPageAdapter();
        this.myPageAdapter = myPageAdapter;
        this.viewPager.setAdapter(myPageAdapter);
        this.viewPager.addOnPageChangeListener(new MyOnPageChangeLister());
        this.currentItem = 1;
        this.viewPager.setCurrentItem(1);
        sendExpoFromPos(this.currentItem);
        startCarousel();
    }

    public JDViewKitDefaultBannerView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
        initView();
    }

    public JDViewKitDefaultBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitDefaultBannerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitDefaultBannerView.this.isAutoPlay) {
                    JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
                    return;
                }
                JDViewKitDefaultBannerView.this.viewPager.setCurrentItem(JDViewKitDefaultBannerView.this.viewPager.getCurrentItem() + 1);
                JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
            }
        };
        initView();
    }

    public JDViewKitDefaultBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitDefaultBannerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitDefaultBannerView.this.isAutoPlay) {
                    JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
                    return;
                }
                JDViewKitDefaultBannerView.this.viewPager.setCurrentItem(JDViewKitDefaultBannerView.this.viewPager.getCurrentItem() + 1);
                JDViewKitDefaultBannerView.this.mHandler.postDelayed(JDViewKitDefaultBannerView.this.task, 3000L);
            }
        };
        initView();
    }
}
