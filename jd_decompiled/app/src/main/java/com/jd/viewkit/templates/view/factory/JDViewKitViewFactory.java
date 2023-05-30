package com.jd.viewkit.templates.view.factory;

import android.content.Context;
import android.view.View;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.container.JDViewKitAnchorNavView;
import com.jd.viewkit.templates.container.JDViewKitBottomNavView;
import com.jd.viewkit.templates.container.JDViewKitDefaultBannerView;
import com.jd.viewkit.templates.container.JDViewKitEmptyView;
import com.jd.viewkit.templates.container.JDViewKitFullHorBannerView;
import com.jd.viewkit.templates.container.JDViewKitHorizontalBannerView;
import com.jd.viewkit.templates.container.JDViewKitMultiTabView;
import com.jd.viewkit.templates.container.JDViewKitTopNavView;
import com.jd.viewkit.templates.container.JDViewKitViewView;
import com.jd.viewkit.templates.container.jdviewkitbannerview.JDViewKitBannerView;
import com.jd.viewkit.templates.container.jdviewkitdynamicbanner.gradient.JDViewKitGradientDynamicBanner;
import com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner;
import com.jd.viewkit.templates.container.jdviewkitflatviewviewv2.JDViewKitFlatViewViewV2;
import com.jd.viewkit.templates.container.jdviewkitscorllview.JDViewKitScorllView;
import com.jd.viewkit.templates.container.jdviewkitswipecard.JDViewKitSwipeCardView;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerView;
import com.jd.viewkit.templates.model.JDViewKitVirtualImageView;
import com.jd.viewkit.templates.model.JDViewKitVirtualProgressView;
import com.jd.viewkit.templates.model.JDViewKitVirtualTextView;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdveiwkitvirtualmultistateview.JDViewKitVirtualMultistateView;
import com.jd.viewkit.templates.model.jdviewkitdynamicbanner.JDViewKitVirtualDynamicBanner;
import com.jd.viewkit.templates.model.jdviewkitvirtualanchornavview.JDViewKitVirtualAnchorNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualbottomnavview.JDViewKitVirtualBottomNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualcountdownview.JDViewKitVirtualCountdownView;
import com.jd.viewkit.templates.model.jdviewkitvirtualcouponview.JDViewKitVirtualCouponView;
import com.jd.viewkit.templates.model.jdviewkitvirtualfreecouponview.JDViewKitVirtualFreeCouponView;
import com.jd.viewkit.templates.model.jdviewkitvirtualrichtextview.JDViewKitVirtualRichTextView;
import com.jd.viewkit.templates.model.jdviewkitvirtualscrollview.JDViewKitVirtualScrollView;
import com.jd.viewkit.templates.model.jdviewkitvirtualsearchview.JDViewKitVirtualSearchView;
import com.jd.viewkit.templates.model.jdviewkitvirtualstatefulview.JDViewKitVirtualStatefulView;
import com.jd.viewkit.templates.model.jdviewkitvirtualtopnavview.JDViewKitVirtualTopNavView;
import com.jd.viewkit.templates.model.jdviewkitvirtualvidelview.JDViewKitVirtualVideoView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.JDViewKitCountdownView;
import com.jd.viewkit.templates.view.JDViewKitCouponView;
import com.jd.viewkit.templates.view.JDViewKitFreeCouponView;
import com.jd.viewkit.templates.view.JDViewKitImageView;
import com.jd.viewkit.templates.view.JDViewKitMultistateView;
import com.jd.viewkit.templates.view.JDViewKitProgressView;
import com.jd.viewkit.templates.view.JDViewKitSearchView;
import com.jd.viewkit.templates.view.JDViewKitStatefulView;
import com.jd.viewkit.templates.view.JDViewKitTextView;
import com.jd.viewkit.templates.view.JDViewKitVideoView;
import com.jd.viewkit.templates.view.jdviewkitrichtextview.JDViewKitRichTextView;
import com.jd.viewkit.tool.StringTool;

/* loaded from: classes18.dex */
public class JDViewKitViewFactory {
    public static JDViewKitBaseLayout getRootViewByVirtualView(Context context, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualView != null && !StringTool.isEmpty(jDViewKitVirtualView.getType())) {
            if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeBanner)) {
                JDViewKitVirtualBannerView jDViewKitVirtualBannerView = (JDViewKitVirtualBannerView) jDViewKitVirtualView;
                if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeVerticalBanner)) {
                    JDViewKitBannerView jDViewKitBannerView = new JDViewKitBannerView(context, jDViewKitChannelModel);
                    jDViewKitBannerView.setVirtualView(jDViewKitVirtualBannerView);
                    return jDViewKitBannerView;
                } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeHorizontalBanner)) {
                    JDViewKitHorizontalBannerView jDViewKitHorizontalBannerView = new JDViewKitHorizontalBannerView(context, jDViewKitChannelModel);
                    jDViewKitHorizontalBannerView.setVirtualView(jDViewKitVirtualBannerView);
                    return jDViewKitHorizontalBannerView;
                } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeFullHorizontalBanner)) {
                    JDViewKitFullHorBannerView jDViewKitFullHorBannerView = new JDViewKitFullHorBannerView(context, jDViewKitChannelModel);
                    jDViewKitFullHorBannerView.setVirtualView(jDViewKitVirtualBannerView);
                    return jDViewKitFullHorBannerView;
                } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeCardHeap)) {
                    JDViewKitSwipeCardView jDViewKitSwipeCardView = new JDViewKitSwipeCardView(context, jDViewKitChannelModel);
                    jDViewKitSwipeCardView.setVirtualView(jDViewKitVirtualBannerView);
                    return jDViewKitSwipeCardView;
                } else {
                    JDViewKitDefaultBannerView jDViewKitDefaultBannerView = new JDViewKitDefaultBannerView(context, jDViewKitChannelModel);
                    jDViewKitDefaultBannerView.setVirtualView(jDViewKitVirtualView);
                    return jDViewKitDefaultBannerView;
                }
            }
            if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeDynamicBanner)) {
                if (StringTool.isNotEmpty(jDViewKitVirtualView.getSubType()) && jDViewKitVirtualView.getSubType().equals(JDViewKitVirtualView.subViewTypeReversalVerticalBanner)) {
                    JDViewKitReversalDynamicBanner jDViewKitReversalDynamicBanner = new JDViewKitReversalDynamicBanner(context, jDViewKitChannelModel);
                    jDViewKitReversalDynamicBanner.setVirtualView((JDViewKitVirtualDynamicBanner) jDViewKitVirtualView);
                    return jDViewKitReversalDynamicBanner;
                } else if (StringTool.isNotEmpty(jDViewKitVirtualView.getSubType()) && jDViewKitVirtualView.getSubType().equals(JDViewKitVirtualView.subViewTypeGradientVerticalBanner)) {
                    JDViewKitGradientDynamicBanner jDViewKitGradientDynamicBanner = new JDViewKitGradientDynamicBanner(context, jDViewKitChannelModel);
                    jDViewKitGradientDynamicBanner.setVirtualView((JDViewKitVirtualDynamicBanner) jDViewKitVirtualView);
                    return jDViewKitGradientDynamicBanner;
                }
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeView)) {
                JDViewKitViewView jDViewKitViewView = new JDViewKitViewView(context, jDViewKitChannelModel);
                jDViewKitViewView.setVirtualView(jDViewKitVirtualView);
                return jDViewKitViewView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeFlatView)) {
                JDViewKitFlatViewViewV2 jDViewKitFlatViewViewV2 = new JDViewKitFlatViewViewV2(context, jDViewKitChannelModel);
                jDViewKitFlatViewViewV2.setVirtualView(jDViewKitVirtualView);
                return jDViewKitFlatViewViewV2;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeScroll)) {
                JDViewKitScorllView jDViewKitScorllView = new JDViewKitScorllView(context, jDViewKitChannelModel);
                jDViewKitScorllView.setVirtualView((JDViewKitVirtualScrollView) jDViewKitVirtualView);
                return jDViewKitScorllView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeStateful)) {
                JDViewKitVirtualStatefulView jDViewKitVirtualStatefulView = (JDViewKitVirtualStatefulView) jDViewKitVirtualView;
                if (StringTool.isNotEmpty(jDViewKitVirtualStatefulView.getSubType()) && jDViewKitVirtualStatefulView.getSubType().equals(JDViewKitVirtualView.subViewTypeStateful)) {
                    JDViewKitStatefulView jDViewKitStatefulView = new JDViewKitStatefulView(context, jDViewKitChannelModel);
                    jDViewKitStatefulView.setVirtualView(jDViewKitVirtualStatefulView);
                    return jDViewKitStatefulView;
                }
            } else if (!jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiTab) && !jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiPlusTab)) {
                if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeAnchorNav)) {
                    JDViewKitAnchorNavView jDViewKitAnchorNavView = new JDViewKitAnchorNavView(context, jDViewKitChannelModel);
                    jDViewKitAnchorNavView.setVirtualView((JDViewKitVirtualAnchorNavView) jDViewKitVirtualView);
                    return jDViewKitAnchorNavView;
                } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeTopNav)) {
                    JDViewKitTopNavView jDViewKitTopNavView = new JDViewKitTopNavView(context, jDViewKitChannelModel);
                    jDViewKitTopNavView.setVirtualView((JDViewKitVirtualTopNavView) jDViewKitVirtualView);
                    return jDViewKitTopNavView;
                } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeBottomNav)) {
                    JDViewKitBottomNavView jDViewKitBottomNavView = new JDViewKitBottomNavView(context, jDViewKitChannelModel);
                    jDViewKitBottomNavView.setVirtualView((JDViewKitVirtualBottomNavView) jDViewKitVirtualView);
                    return jDViewKitBottomNavView;
                }
            } else {
                JDViewKitMultiTabView jDViewKitMultiTabView = new JDViewKitMultiTabView(context, jDViewKitChannelModel);
                jDViewKitMultiTabView.setVirtualView(jDViewKitVirtualView);
                return jDViewKitMultiTabView;
            }
            return new JDViewKitEmptyView(context, jDViewKitChannelModel);
        }
        return new JDViewKitEmptyView(context, jDViewKitChannelModel);
    }

    public static JDViewKitAbsoluteLayout getView(Context context, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitChannelModel jDViewKitChannelModel) {
        try {
            View viewByVirtualView = getViewByVirtualView(context, jDViewKitVirtualView, jDViewKitChannelModel);
            if (viewByVirtualView == null) {
                return new JDViewKitAbsoluteLayout(context, jDViewKitChannelModel);
            }
            return (JDViewKitAbsoluteLayout) viewByVirtualView;
        } catch (Exception unused) {
            return new JDViewKitAbsoluteLayout(context, jDViewKitChannelModel);
        }
    }

    public static View getViewByVirtualView(Context context, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitChannelModel jDViewKitChannelModel) {
        if (jDViewKitVirtualView == null || StringTool.isEmpty(jDViewKitVirtualView.getType())) {
            return null;
        }
        if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeView)) {
            JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = new JDViewKitAbsoluteLayout(context, jDViewKitChannelModel);
            jDViewKitAbsoluteLayout.setVirtualView(jDViewKitVirtualView);
            return jDViewKitAbsoluteLayout;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeImage)) {
            JDViewKitImageView jDViewKitImageView = new JDViewKitImageView(context, jDViewKitChannelModel);
            jDViewKitImageView.setJDViewKitVirtualImageView((JDViewKitVirtualImageView) jDViewKitVirtualView);
            return jDViewKitImageView;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeText)) {
            JDViewKitTextView jDViewKitTextView = new JDViewKitTextView(context, jDViewKitChannelModel);
            jDViewKitTextView.setVirtualTextView((JDViewKitVirtualTextView) jDViewKitVirtualView);
            return jDViewKitTextView;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeRichText)) {
            JDViewKitRichTextView jDViewKitRichTextView = new JDViewKitRichTextView(context, jDViewKitChannelModel);
            jDViewKitRichTextView.setVirtualRichTextView((JDViewKitVirtualRichTextView) jDViewKitVirtualView);
            return jDViewKitRichTextView;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeFlatView)) {
            JDViewKitFlatViewViewV2 jDViewKitFlatViewViewV2 = new JDViewKitFlatViewViewV2(context, jDViewKitChannelModel);
            jDViewKitFlatViewViewV2.setVirtualView(jDViewKitVirtualView);
            return jDViewKitFlatViewViewV2;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeBanner)) {
            JDViewKitVirtualBannerView jDViewKitVirtualBannerView = (JDViewKitVirtualBannerView) jDViewKitVirtualView;
            if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeVerticalBanner)) {
                JDViewKitBannerView jDViewKitBannerView = new JDViewKitBannerView(context, jDViewKitChannelModel);
                jDViewKitBannerView.setVirtualView(jDViewKitVirtualBannerView);
                return jDViewKitBannerView;
            } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeHorizontalBanner)) {
                JDViewKitHorizontalBannerView jDViewKitHorizontalBannerView = new JDViewKitHorizontalBannerView(context, jDViewKitChannelModel);
                jDViewKitHorizontalBannerView.setVirtualView(jDViewKitVirtualBannerView);
                return jDViewKitHorizontalBannerView;
            } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeFullHorizontalBanner)) {
                JDViewKitFullHorBannerView jDViewKitFullHorBannerView = new JDViewKitFullHorBannerView(context, jDViewKitChannelModel);
                jDViewKitFullHorBannerView.setVirtualView(jDViewKitVirtualBannerView);
                return jDViewKitFullHorBannerView;
            } else if (StringTool.isNotEmpty(jDViewKitVirtualBannerView.getSubType()) && jDViewKitVirtualBannerView.getSubType().equals(JDViewKitVirtualView.subViewTypeCardHeap)) {
                JDViewKitSwipeCardView jDViewKitSwipeCardView = new JDViewKitSwipeCardView(context, jDViewKitChannelModel);
                jDViewKitSwipeCardView.setVirtualView(jDViewKitVirtualBannerView);
                return jDViewKitSwipeCardView;
            } else {
                JDViewKitDefaultBannerView jDViewKitDefaultBannerView = new JDViewKitDefaultBannerView(context, jDViewKitChannelModel);
                jDViewKitDefaultBannerView.setVirtualView(jDViewKitVirtualView);
                return jDViewKitDefaultBannerView;
            }
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeDynamicBanner)) {
            if (StringTool.isNotEmpty(jDViewKitVirtualView.getSubType()) && jDViewKitVirtualView.getSubType().equals(JDViewKitVirtualView.subViewTypeReversalVerticalBanner)) {
                JDViewKitReversalDynamicBanner jDViewKitReversalDynamicBanner = new JDViewKitReversalDynamicBanner(context, jDViewKitChannelModel);
                jDViewKitReversalDynamicBanner.setVirtualView((JDViewKitVirtualDynamicBanner) jDViewKitVirtualView);
                return jDViewKitReversalDynamicBanner;
            } else if (StringTool.isNotEmpty(jDViewKitVirtualView.getSubType()) && jDViewKitVirtualView.getSubType().equals(JDViewKitVirtualView.subViewTypeGradientVerticalBanner)) {
                JDViewKitGradientDynamicBanner jDViewKitGradientDynamicBanner = new JDViewKitGradientDynamicBanner(context, jDViewKitChannelModel);
                jDViewKitGradientDynamicBanner.setVirtualView((JDViewKitVirtualDynamicBanner) jDViewKitVirtualView);
                return jDViewKitGradientDynamicBanner;
            } else {
                return null;
            }
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeScroll)) {
            JDViewKitScorllView jDViewKitScorllView = new JDViewKitScorllView(context, jDViewKitChannelModel);
            jDViewKitScorllView.setVirtualView((JDViewKitVirtualScrollView) jDViewKitVirtualView);
            return jDViewKitScorllView;
        } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeStateful)) {
            JDViewKitVirtualStatefulView jDViewKitVirtualStatefulView = (JDViewKitVirtualStatefulView) jDViewKitVirtualView;
            if (StringTool.isNotEmpty(jDViewKitVirtualStatefulView.getSubType()) && jDViewKitVirtualStatefulView.getSubType().equals(JDViewKitVirtualView.subViewTypeStateful)) {
                JDViewKitStatefulView jDViewKitStatefulView = new JDViewKitStatefulView(context, jDViewKitChannelModel);
                jDViewKitStatefulView.setVirtualView(jDViewKitVirtualStatefulView);
                return jDViewKitStatefulView;
            }
            return null;
        } else if (!jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiTab) && !jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultiPlusTab)) {
            if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeAnchorNav)) {
                JDViewKitAnchorNavView jDViewKitAnchorNavView = new JDViewKitAnchorNavView(context, jDViewKitChannelModel);
                jDViewKitAnchorNavView.setVirtualView((JDViewKitVirtualAnchorNavView) jDViewKitVirtualView);
                return jDViewKitAnchorNavView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeTopNav)) {
                JDViewKitTopNavView jDViewKitTopNavView = new JDViewKitTopNavView(context, jDViewKitChannelModel);
                jDViewKitTopNavView.setVirtualView((JDViewKitVirtualTopNavView) jDViewKitVirtualView);
                return jDViewKitTopNavView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeBottomNav)) {
                JDViewKitBottomNavView jDViewKitBottomNavView = new JDViewKitBottomNavView(context, jDViewKitChannelModel);
                jDViewKitBottomNavView.setVirtualView((JDViewKitVirtualBottomNavView) jDViewKitVirtualView);
                return jDViewKitBottomNavView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeFreeCoupon)) {
                JDViewKitFreeCouponView jDViewKitFreeCouponView = new JDViewKitFreeCouponView(context, jDViewKitChannelModel);
                jDViewKitFreeCouponView.setVirtualView((JDViewKitVirtualFreeCouponView) jDViewKitVirtualView);
                return jDViewKitFreeCouponView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeCoupon)) {
                JDViewKitCouponView jDViewKitCouponView = new JDViewKitCouponView(context, jDViewKitChannelModel);
                jDViewKitCouponView.setVirtualView((JDViewKitVirtualCouponView) jDViewKitVirtualView);
                return jDViewKitCouponView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeProgress)) {
                JDViewKitProgressView jDViewKitProgressView = new JDViewKitProgressView(context, jDViewKitChannelModel);
                jDViewKitProgressView.setVirtualView((JDViewKitVirtualProgressView) jDViewKitVirtualView);
                return jDViewKitProgressView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeSearch)) {
                JDViewKitSearchView jDViewKitSearchView = new JDViewKitSearchView(context, jDViewKitChannelModel);
                jDViewKitSearchView.setVirtualView((JDViewKitVirtualSearchView) jDViewKitVirtualView);
                return jDViewKitSearchView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeMultistateView)) {
                JDViewKitMultistateView jDViewKitMultistateView = new JDViewKitMultistateView(context, jDViewKitChannelModel);
                jDViewKitMultistateView.setVirtualView((JDViewKitVirtualMultistateView) jDViewKitVirtualView);
                return jDViewKitMultistateView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeCountdown)) {
                JDViewKitCountdownView jDViewKitCountdownView = new JDViewKitCountdownView(context, jDViewKitChannelModel);
                jDViewKitCountdownView.setVirtualView((JDViewKitVirtualCountdownView) jDViewKitVirtualView);
                return jDViewKitCountdownView;
            } else if (jDViewKitVirtualView.getType().equals(JDViewKitVirtualView.viewTypeVideo)) {
                JDViewKitVideoView jDViewKitVideoView = new JDViewKitVideoView(context, jDViewKitChannelModel);
                jDViewKitVideoView.setVirtualView((JDViewKitVirtualVideoView) jDViewKitVirtualView);
                return jDViewKitVideoView;
            } else {
                return null;
            }
        } else {
            JDViewKitMultiTabView jDViewKitMultiTabView = new JDViewKitMultiTabView(context, jDViewKitChannelModel);
            jDViewKitMultiTabView.setVirtualView(jDViewKitVirtualView);
            return jDViewKitMultiTabView;
        }
    }

    public static JDViewKitAbsoluteLayout getView(Context context, JDViewKitDataSourceModel jDViewKitDataSourceModel, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitChannelModel jDViewKitChannelModel) {
        try {
            View viewByVirtualView = getViewByVirtualView(context, jDViewKitVirtualView, jDViewKitChannelModel);
            if (viewByVirtualView == null) {
                return new JDViewKitAbsoluteLayout(context, jDViewKitChannelModel);
            }
            JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout = (JDViewKitAbsoluteLayout) viewByVirtualView;
            jDViewKitAbsoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, false);
            return jDViewKitAbsoluteLayout;
        } catch (Exception unused) {
            return new JDViewKitAbsoluteLayout(context, jDViewKitChannelModel);
        }
    }
}
