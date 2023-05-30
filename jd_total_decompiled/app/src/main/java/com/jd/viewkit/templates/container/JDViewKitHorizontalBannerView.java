package com.jd.viewkit.templates.container;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitHorizontalBannerView extends JDViewKitBaseLayout<JDViewKitVirtualBannerView> {
    private static final int DELAY = 3000;
    int currentItem;
    private int interval;
    private boolean isAutoPlay;
    private Handler mHandler;
    private MyPageAdapter myPageAdapter;
    private final Runnable task;
    List<View> viewList;
    ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class MyPageAdapter extends PagerAdapter {
        private MyPageAdapter() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return 1000;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            List<View> list = JDViewKitHorizontalBannerView.this.viewList;
            View view = list.get(i2 % list.size());
            if (view != null && view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            viewGroup.addView(view);
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public JDViewKitHorizontalBannerView(@NonNull Context context) {
        super(context);
        this.viewList = new ArrayList();
        this.mHandler = new Handler();
        this.interval = 3000;
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitHorizontalBannerView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitHorizontalBannerView.this.isAutoPlay) {
                    JDViewKitHorizontalBannerView.this.mHandler.postDelayed(JDViewKitHorizontalBannerView.this.task, JDViewKitHorizontalBannerView.this.interval);
                    return;
                }
                JDViewKitHorizontalBannerView jDViewKitHorizontalBannerView = JDViewKitHorizontalBannerView.this;
                int i2 = jDViewKitHorizontalBannerView.currentItem + 1;
                jDViewKitHorizontalBannerView.currentItem = i2;
                jDViewKitHorizontalBannerView.viewPager.setCurrentItem(i2);
                JDViewKitHorizontalBannerView.this.mHandler.postDelayed(JDViewKitHorizontalBannerView.this.task, JDViewKitHorizontalBannerView.this.interval);
            }
        };
        this.mContext = context;
        initView();
    }

    private JDViewKitAbsoluteLayout getView(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        return JDViewKitViewFactory.getView(this.mContext, jDViewKitDataSourceModel, getDslsMap().get(jDViewKitDataSourceModel.getDslId()), getChannelModel());
    }

    private void initView() {
        VerticalViewPager verticalViewPager = new VerticalViewPager(this.mContext);
        this.viewPager = verticalViewPager;
        addView(verticalViewPager);
        MyPageAdapter myPageAdapter = new MyPageAdapter();
        this.myPageAdapter = myPageAdapter;
        this.viewPager.setAdapter(myPageAdapter);
        this.viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() { // from class: com.jd.viewkit.templates.container.JDViewKitHorizontalBannerView.1
            @Override // androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i2) {
                JDViewKitHorizontalBannerView.this.sendExpoFromPos(i2);
            }
        });
    }

    private void startCarousel() {
        stopCarousel();
        this.isAutoPlay = true;
        this.mHandler.postDelayed(this.task, this.interval);
    }

    private void stopCarousel() {
        this.isAutoPlay = false;
        this.mHandler.removeCallbacks(this.task);
    }

    private void updatePaly() {
        List<JDViewKitDataSourceModel> list = this.dataSourceModels;
        if (list != null && list.size() > 1) {
            startCarousel();
        } else {
            stopCarousel();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updatePaly();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopCarousel();
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
        if (list == null || list.size() <= 0) {
            return;
        }
        sendExpo(this.dataSourceModels.get(i2 % this.dataSourceModels.size()));
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        if (this.dataSourceModels.size() == 0) {
            return;
        }
        this.viewList.clear();
        this.viewList.add(getView(this.dataSourceModels.get(r2.size() - 1)));
        Iterator<JDViewKitDataSourceModel> it = this.dataSourceModels.iterator();
        while (it.hasNext()) {
            this.viewList.add(getView(it.next()));
        }
        this.viewList.add(getView(this.dataSourceModels.get(0)));
        this.viewPager.getAdapter().notifyDataSetChanged();
        this.currentItem = 0;
        this.viewPager.setCurrentItem(0);
        sendExpoFromPos(this.currentItem);
        updatePaly();
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualBannerView jDViewKitVirtualBannerView) {
        super.setVirtualView((JDViewKitHorizontalBannerView) jDViewKitVirtualBannerView);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.viewPager.getLayoutParams();
        marginLayoutParams.width = getLayoutParams().width;
        marginLayoutParams.height = getLayoutParams().height;
        this.viewPager.setLayoutParams(marginLayoutParams);
        int interval = jDViewKitVirtualBannerView.getInterval();
        this.interval = interval;
        if (interval < 1000) {
            this.interval = 3000;
        }
    }

    public JDViewKitHorizontalBannerView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context);
        this.channelModel = jDViewKitChannelModel;
    }

    public JDViewKitHorizontalBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.viewList = new ArrayList();
        this.mHandler = new Handler();
        this.interval = 3000;
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitHorizontalBannerView.2
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitHorizontalBannerView.this.isAutoPlay) {
                    JDViewKitHorizontalBannerView.this.mHandler.postDelayed(JDViewKitHorizontalBannerView.this.task, JDViewKitHorizontalBannerView.this.interval);
                    return;
                }
                JDViewKitHorizontalBannerView jDViewKitHorizontalBannerView = JDViewKitHorizontalBannerView.this;
                int i2 = jDViewKitHorizontalBannerView.currentItem + 1;
                jDViewKitHorizontalBannerView.currentItem = i2;
                jDViewKitHorizontalBannerView.viewPager.setCurrentItem(i2);
                JDViewKitHorizontalBannerView.this.mHandler.postDelayed(JDViewKitHorizontalBannerView.this.task, JDViewKitHorizontalBannerView.this.interval);
            }
        };
        this.mContext = context;
        initView();
    }
}
