package com.jingdong.common.recommend.forlist;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.entity.RecommendHomeCardBean;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendHomeLiveVideoWithOneChannelViewHolder extends BaseRecommendViewHolder {
    private BaseActivity baseActivity;
    private ConstraintLayout cardview_container;
    private View recommendHomeCardOneChannelItemView;
    private RecommendHomeCardOneChannelViewHolder recommendHomeCardOneChannelViewHolder;
    private View recommendHomeLiveVideoItemView;
    private RecommendHomeLiveVideoViewHolder recommendHomeLiveVideoViewHolder;

    public RecommendHomeLiveVideoWithOneChannelViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.baseActivity = baseActivity;
        findViews(view);
    }

    private void findViews(View view) {
        this.cardview_container = (ConstraintLayout) view.findViewById(R.id.cardview_container);
        this.recommendHomeCardOneChannelItemView = view.findViewById(R.id.included_home_card_one_channel_floor);
        this.recommendHomeLiveVideoItemView = view.findViewById(R.id.video_cardview);
        View view2 = this.recommendHomeCardOneChannelItemView;
        if (view2 != null) {
            this.recommendHomeCardOneChannelViewHolder = new RecommendHomeCardOneChannelViewHolder(this.baseActivity, view2);
        }
        View view3 = this.recommendHomeLiveVideoItemView;
        if (view3 != null) {
            this.recommendHomeLiveVideoViewHolder = new RecommendHomeLiveVideoViewHolder(this.baseActivity, view3);
        }
    }

    private void setViewLayout() {
        ViewGroup.LayoutParams layoutParams = this.cardview_container.getLayoutParams();
        layoutParams.height = DPIUtil.getWidthByDesignValue750((Activity) this.baseActivity, (int) R2.attr.backgroundInsetBottom);
        this.cardview_container.setLayoutParams(layoutParams);
        this.itemView.setBackgroundColor(0);
        this.cardview_container.setBackgroundColor(0);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void exposurePercent(double d) {
        super.exposurePercent(d);
        RecommendHomeCardOneChannelViewHolder recommendHomeCardOneChannelViewHolder = this.recommendHomeCardOneChannelViewHolder;
        if (recommendHomeCardOneChannelViewHolder != null) {
            recommendHomeCardOneChannelViewHolder.exposurePercent(d);
        }
        RecommendHomeLiveVideoViewHolder recommendHomeLiveVideoViewHolder = this.recommendHomeLiveVideoViewHolder;
        if (recommendHomeLiveVideoViewHolder != null) {
            recommendHomeLiveVideoViewHolder.exposurePercent(d);
        }
    }

    public void setData(RecommendHomeCardBean recommendHomeCardBean, JDDisplayImageOptions jDDisplayImageOptions) {
        List<RecommendHomeCardBean.SubWareList> list;
        if (recommendHomeCardBean == null || (list = recommendHomeCardBean.subWareList) == null || list.size() == 0) {
            return;
        }
        setViewLayout();
        if (recommendHomeCardBean.subWareList.size() > 1) {
            RecommendHomeCardBean recommendHomeCardBean2 = new RecommendHomeCardBean();
            List<RecommendHomeCardBean.SubWareList> list2 = recommendHomeCardBean.subWareList;
            recommendHomeCardBean2.subWareList = list2.subList(1, list2.size());
            RecommendHomeCardOneChannelViewHolder recommendHomeCardOneChannelViewHolder = this.recommendHomeCardOneChannelViewHolder;
            if (recommendHomeCardOneChannelViewHolder != null) {
                recommendHomeCardOneChannelViewHolder.setData(recommendHomeCardBean2, jDDisplayImageOptions);
            }
        }
        RecommendHomeLiveVideoViewHolder recommendHomeLiveVideoViewHolder = this.recommendHomeLiveVideoViewHolder;
        if (recommendHomeLiveVideoViewHolder != null) {
            recommendHomeLiveVideoViewHolder.setData(recommendHomeCardBean, jDDisplayImageOptions);
        }
    }

    public void setPlayUiVisible(boolean z) {
        RecommendHomeLiveVideoViewHolder recommendHomeLiveVideoViewHolder = this.recommendHomeLiveVideoViewHolder;
        if (recommendHomeLiveVideoViewHolder != null) {
            recommendHomeLiveVideoViewHolder.setPlayUiVisible(z);
        }
    }
}
