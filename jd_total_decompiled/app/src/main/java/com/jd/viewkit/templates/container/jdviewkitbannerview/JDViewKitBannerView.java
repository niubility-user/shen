package com.jd.viewkit.templates.container.jdviewkitbannerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewBannerViewPager;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDBannerIndicatorView;
import com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.JDIndicatorBiluder;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerDotConfig;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitBannerView extends JDViewKitBaseLayout<JDViewKitVirtualBannerView> {
    private JDBannerPagerAdapter adapter;
    private JDBannerIndicatorView indicatorView;
    private JDViewBannerViewPager viewPager;

    public JDViewKitBannerView(@NonNull Context context) {
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
        JDBannerPagerAdapter jDBannerPagerAdapter = this.adapter;
        if (jDBannerPagerAdapter != null) {
            sendExpo(jDBannerPagerAdapter.getJDViewKitDataSourceModel(i2 - 1));
            sendExpo(this.adapter.getJDViewKitDataSourceModel(i2));
            sendExpo(this.adapter.getJDViewKitDataSourceModel(i2 + 1));
        }
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        if (this.dataSourceModels.size() == 0 || ((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig() == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getWidth(), getChannelModel()), GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getCenterSizeHeigh(), getChannelModel()));
        layoutParams.topMargin = GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getMarginTop(), getChannelModel());
        this.viewPager.setLayoutParams(layoutParams);
        new JDViewBannerViewPager.Builder().setChannelModel(getChannelModel()).setAutoPlay(((JDViewKitVirtualBannerView) this.virtualView).isAutoPlay()).setCircle(((JDViewKitVirtualBannerView) this.virtualView).isCircle()).setInterval(((JDViewKitVirtualBannerView) this.virtualView).getInterval()).setPageMargin(GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getSpace(), getChannelModel())).setParentWidth(GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getWidth(), getChannelModel())).setItemWidth(GlobalManage.getInstance().getRealPx(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getCenterSizeWidth(), getChannelModel())).setScale(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig().getScale()).build(this.viewPager);
        this.adapter.setCircle(((JDViewKitVirtualBannerView) this.virtualView).isCircle());
        this.adapter.setConfig(((JDViewKitVirtualBannerView) this.virtualView).getBannerConfig());
        this.adapter.setDslsMap(getDslsMap());
        this.adapter.setDatas(getDataSourceModels());
        this.adapter.setChannelModel(getChannelModel());
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.start();
        int startItem = this.adapter.getStartItem();
        this.viewPager.setCurrentItem(startItem);
        sendExpoFromPos(startItem);
        JDViewKitVirtualBannerDotConfig bannerDotConfig = ((JDViewKitVirtualBannerView) this.virtualView).getBannerDotConfig();
        if (bannerDotConfig != null && bannerDotConfig.isShowDot()) {
            JDIndicatorBiluder jDIndicatorBiluder = new JDIndicatorBiluder();
            jDIndicatorBiluder.setActiveColor(bannerDotConfig.getActiveColor()).setNormalColor(bannerDotConfig.getNormalColor()).setDotType(((JDViewKitVirtualBannerView) this.virtualView).getDotType()).setTotalNum(list.size());
            JDBannerIndicatorView jDBannerIndicatorView = this.indicatorView;
            if (jDBannerIndicatorView == null) {
                this.indicatorView = jDIndicatorBiluder.build(getContext());
                FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                if (StringTool.isNotEmpty(bannerDotConfig.getAlign()) && bannerDotConfig.getAlign().equals(DYConstants.DY_CENTER)) {
                    layoutParams2.gravity = 1;
                } else {
                    layoutParams2.leftMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginX(), getChannelModel());
                }
                layoutParams2.topMargin = GlobalManage.getInstance().getRealPx(bannerDotConfig.getDotOriginY(), getChannelModel());
                addView(this.indicatorView, layoutParams2);
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
        }
        if (StringTool.isEmpty(((JDViewKitVirtualBannerView) this.virtualView).getHidden())) {
            return;
        }
        setVisibility(8);
    }

    public JDViewKitBannerView(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        this(context, null, 0);
        this.channelModel = jDViewKitChannelModel;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualBannerView jDViewKitVirtualBannerView) {
        super.setVirtualView((JDViewKitBannerView) jDViewKitVirtualBannerView);
    }

    public JDViewKitBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDViewKitBannerView(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i2) {
        super(context, attributeSet, i2);
        setClipToPadding(false);
        setLayerType(1, null);
        JDViewBannerViewPager jDViewBannerViewPager = new JDViewBannerViewPager(getContext(), getChannelModel());
        this.viewPager = jDViewBannerViewPager;
        jDViewBannerViewPager.setPagerChangeListener(new JDViewBannerViewPager.PagerChangeListener() { // from class: com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewKitBannerView.1
            @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewBannerViewPager.PagerChangeListener
            public void onPageSelected(int i3) {
                JDViewKitBannerView.this.sendExpoFromPos(i3);
            }
        });
        this.adapter = new JDBannerPagerAdapter(getChannelModel());
        this.viewPager.setOffscreenPageLimit(4);
        addView(this.viewPager);
    }
}
