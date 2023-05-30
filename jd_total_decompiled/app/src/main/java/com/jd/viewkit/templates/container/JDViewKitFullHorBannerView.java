package com.jd.viewkit.templates.container;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorView;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDIndicatorBiluder;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerDotConfig;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitFullHorBannerView extends JDViewKitBaseLayout<JDViewKitVirtualBannerView> {
    private JDFullBannerPagerAdapter adapter;
    private JDBannerIndicatorView indicatorView;
    private JDFullViewBannerViewPager viewPager;

    /* loaded from: classes18.dex */
    public class JDFullViewBannerViewPager extends ViewPager {
        private static final int MIN_PAGE_LIMIT = 3;
        private boolean autoPlay;
        private Runnable autoPlayTask;
        private boolean circle;
        private int currentItem;
        private Handler handler;
        private IndicatorListener indicatorListener;
        private int interval;
        private boolean playing;

        public JDFullViewBannerViewPager(@NonNull JDViewKitFullHorBannerView jDViewKitFullHorBannerView, Context context) {
            this(context, null);
        }

        static /* synthetic */ int access$208(JDFullViewBannerViewPager jDFullViewBannerViewPager) {
            int i2 = jDFullViewBannerViewPager.currentItem;
            jDFullViewBannerViewPager.currentItem = i2 + 1;
            return i2;
        }

        public void init() {
            clearOnPageChangeListeners();
            addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.jd.viewkit.templates.container.JDViewKitFullHorBannerView.JDFullViewBannerViewPager.2
                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i2) {
                    if (i2 == 1) {
                        JDFullViewBannerViewPager.this.pause();
                    } else if (i2 != 2) {
                    } else {
                        JDFullViewBannerViewPager.this.start();
                    }
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i2, float f2, int i3) {
                }

                @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
                public void onPageSelected(int i2) {
                    if (JDFullViewBannerViewPager.this.indicatorListener != null) {
                        JDFullViewBannerViewPager.this.indicatorListener.update(i2 % JDFullViewBannerViewPager.this.indicatorListener.getRealCount());
                    }
                    JDViewKitFullHorBannerView.this.sendExpoFromPos(i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            start();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            pause();
        }

        @Override // android.view.View
        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z) {
                start();
            } else {
                pause();
            }
        }

        public void pause() {
            this.playing = false;
            this.handler.removeCallbacks(this.autoPlayTask);
        }

        public void setAutoPlay(boolean z) {
            this.autoPlay = z;
        }

        public void setCircle(boolean z) {
            this.circle = z;
        }

        public void setIndicatorListener(IndicatorListener indicatorListener) {
            this.indicatorListener = indicatorListener;
        }

        public void setInterval(int i2) {
            this.interval = i2;
        }

        public void start() {
            if (getAdapter() != null && this.autoPlay && getAdapter().getCount() > 1) {
                pause();
                this.playing = true;
                this.handler.postDelayed(this.autoPlayTask, this.interval);
            }
        }

        public JDFullViewBannerViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            this.handler = new Handler();
            this.autoPlay = false;
            this.playing = false;
            this.circle = false;
            this.currentItem = 0;
            this.autoPlayTask = new Runnable() { // from class: com.jd.viewkit.templates.container.JDViewKitFullHorBannerView.JDFullViewBannerViewPager.1
                @Override // java.lang.Runnable
                public void run() {
                    if (JDFullViewBannerViewPager.this.autoPlay && JDFullViewBannerViewPager.this.playing) {
                        JDFullViewBannerViewPager jDFullViewBannerViewPager = JDFullViewBannerViewPager.this;
                        jDFullViewBannerViewPager.currentItem = jDFullViewBannerViewPager.getCurrentItem();
                        JDFullViewBannerViewPager.access$208(JDFullViewBannerViewPager.this);
                        if (JDFullViewBannerViewPager.this.getAdapter() != null && JDFullViewBannerViewPager.this.getCurrentItem() == JDFullViewBannerViewPager.this.getAdapter().getCount() - 1) {
                            if (JDFullViewBannerViewPager.this.circle) {
                                JDFullViewBannerViewPager.this.setCurrentItem(0, true);
                            }
                        } else {
                            JDFullViewBannerViewPager jDFullViewBannerViewPager2 = JDFullViewBannerViewPager.this;
                            jDFullViewBannerViewPager2.setCurrentItem(jDFullViewBannerViewPager2.currentItem, true);
                        }
                    }
                    JDFullViewBannerViewPager.this.handler.postDelayed(this, JDFullViewBannerViewPager.this.interval);
                }
            };
            setOverScrollMode(2);
        }
    }

    public JDViewKitFullHorBannerView(@NonNull Context context) {
        this(context, null, 0);
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
        JDFullBannerPagerAdapter jDFullBannerPagerAdapter = this.adapter;
        if (jDFullBannerPagerAdapter != null) {
            sendExpo(jDFullBannerPagerAdapter.getJDViewKitDataSourceModel(i2));
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        if (this.dataSourceModels.size() == 0) {
            return;
        }
        this.viewPager.setAutoPlay(((JDViewKitVirtualBannerView) this.virtualView).isAutoPlay());
        this.viewPager.setCircle(((JDViewKitVirtualBannerView) this.virtualView).isCircle());
        this.viewPager.setInterval(((JDViewKitVirtualBannerView) this.virtualView).getInterval());
        this.viewPager.init();
        this.adapter.setCircle(((JDViewKitVirtualBannerView) this.virtualView).isCircle());
        this.adapter.setDslsMap(getDslsMap());
        this.adapter.setDatas(getDataSourceModels());
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.start();
        int startItem = this.adapter.getStartItem();
        this.viewPager.setCurrentItem(startItem);
        sendExpoFromPos(startItem);
        JDViewKitVirtualBannerDotConfig bannerDotConfig = ((JDViewKitVirtualBannerView) this.virtualView).getBannerDotConfig();
        if (bannerDotConfig != null && bannerDotConfig.isShowDot() && this.dataSourceModels.size() > 1) {
            JDIndicatorBiluder jDIndicatorBiluder = new JDIndicatorBiluder();
            jDIndicatorBiluder.setChannelModel(getChannelModel()).setActiveColor(bannerDotConfig.getActiveColor()).setNormalColor(bannerDotConfig.getNormalColor()).setDotType(((JDViewKitVirtualBannerView) this.virtualView).getDotType()).setDotSubType(1).setTotalNum(list.size());
            JDBannerIndicatorView jDBannerIndicatorView = this.indicatorView;
            if (jDBannerIndicatorView == null) {
                this.indicatorView = jDIndicatorBiluder.build(getContext());
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                if (StringTool.isNotEmpty(bannerDotConfig.getAlign()) && bannerDotConfig.getAlign().equals(DYConstants.DY_CENTER)) {
                    layoutParams.gravity = 1;
                } else {
                    layoutParams.leftMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginX(), getChannelModel());
                }
                layoutParams.topMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginY(), getChannelModel());
                addView(this.indicatorView, layoutParams);
            } else if (z) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) jDBannerIndicatorView.getLayoutParams();
                if (StringTool.isNotEmpty(bannerDotConfig.getAlign()) && bannerDotConfig.getAlign().equals(DYConstants.DY_CENTER) && marginLayoutParams != null && (marginLayoutParams instanceof FrameLayout.LayoutParams)) {
                    ((FrameLayout.LayoutParams) marginLayoutParams).gravity = 1;
                } else {
                    marginLayoutParams.leftMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginX(), getChannelModel());
                }
                marginLayoutParams.topMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginY(), getChannelModel());
                this.indicatorView.requestLayout();
            }
            this.viewPager.setIndicatorListener(this.indicatorView);
        } else if (this.indicatorView != null) {
            this.viewPager.setIndicatorListener(null);
            removeView(this.indicatorView);
        }
        if (StringTool.isEmpty(((JDViewKitVirtualBannerView) this.virtualView).getHidden())) {
            return;
        }
        setVisibility(8);
    }

    public JDViewKitFullHorBannerView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, null, 0);
        this.channelModel = jDViewKitChannelModel;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualBannerView jDViewKitVirtualBannerView) {
        super.setVirtualView((JDViewKitFullHorBannerView) jDViewKitVirtualBannerView);
    }

    /* loaded from: classes18.dex */
    public class JDFullBannerPagerAdapter extends PagerAdapter {
        private static final int INITIAL_POSITION = 30;
        private static final int MAX_ITEMS_COUNT = 1000;
        private boolean circle;
        private int currentItem;
        private View currentView;
        private Map<String, JDViewKitVirtualView> dslsMap;
        private List<JDViewKitDataSourceModel> mDatas;

        public JDFullBannerPagerAdapter() {
            this.circle = true;
            this.currentItem = 0;
        }

        private int getRealCount() {
            List<JDViewKitDataSourceModel> list = this.mDatas;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        private int getStartSelectItem() {
            if (getRealCount() == 0) {
                return 0;
            }
            int realCount = (getRealCount() * 500) / 2;
            if (realCount % getRealCount() == 0) {
                return realCount;
            }
            while (realCount % getRealCount() != 0) {
                realCount++;
            }
            return realCount;
        }

        private JDViewKitAbsoluteLayout getView(Context context, JDViewKitDataSourceModel jDViewKitDataSourceModel, int i2) {
            JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
            JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(context, jDViewKitDataSourceModel, jDViewKitVirtualView, JDViewKitFullHorBannerView.this.getChannelModel());
            if (jDViewKitVirtualView != null && jDViewKitVirtualView.getBorderRadius() != 0) {
                view.setBackgroundColor(0);
                View view2 = new View(context);
                view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                view.setBgColorAndBorder(view2);
                view.addView(view2, 0);
            }
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            if (this.circle && getRealCount() > 1) {
                return getRealCount() * 1000;
            }
            return getRealCount();
        }

        public JDViewKitDataSourceModel getJDViewKitDataSourceModel(int i2) {
            int realCount = i2 % getRealCount();
            if (realCount < 0 || realCount >= this.mDatas.size()) {
                return null;
            }
            return this.mDatas.get(realCount);
        }

        public int getStartItem() {
            if (!this.circle || getCount() <= 1) {
                return 0;
            }
            return (1000 / (getRealCount() * 2)) * getRealCount();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        @NonNull
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
            int realCount = i2 % getRealCount();
            JDViewKitAbsoluteLayout view = getView(viewGroup.getContext(), this.mDatas.get(realCount), realCount);
            viewGroup.addView(view);
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        public void setCircle(boolean z) {
            this.circle = z;
        }

        public void setDatas(List<JDViewKitDataSourceModel> list) {
            if (this.mDatas == null) {
                this.mDatas = new ArrayList();
            }
            this.mDatas.clear();
            this.mDatas.addAll(list);
        }

        public void setDslsMap(Map<String, JDViewKitVirtualView> map) {
            this.dslsMap = map;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
            this.currentView = (View) obj;
        }

        public JDFullBannerPagerAdapter(List<JDViewKitDataSourceModel> list, Map<String, JDViewKitVirtualView> map, boolean z) {
            this.circle = true;
            this.currentItem = 0;
            this.mDatas = list;
            this.dslsMap = map;
            this.circle = z;
        }
    }

    public JDViewKitFullHorBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDViewKitFullHorBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        this.viewPager = new JDFullViewBannerViewPager(this, getContext());
        this.adapter = new JDFullBannerPagerAdapter();
        addView(this.viewPager);
    }
}
