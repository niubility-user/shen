package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.entity.RecommendAdData;
import com.jingdong.common.recommend.forlist.RecommendUtil;

/* loaded from: classes6.dex */
public class BaseRecommendAdViewHolder extends BaseRecommendViewHolder {
    protected ImageView adIcon;
    protected View leftDotView;
    protected View rightDotView;

    public BaseRecommendAdViewHolder(View view) {
        super(view);
        this.leftDotView = view.findViewById(R.id.recommend_left_dot);
        this.rightDotView = view.findViewById(R.id.recommend_dot);
        this.adIcon = (ImageView) view.findViewById(R.id.recommend_ad_icon);
    }

    public void onAdClick(String str) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (TextUtils.isEmpty(str) || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRecommendMoneyExpo(str);
    }

    public void setAdData(RecommendAdData recommendAdData) {
        View view = this.rightDotView;
        if (view != null) {
            view.setVisibility(recommendAdData.showAdDot() ? 0 : 8);
        }
        View view2 = this.leftDotView;
        if (view2 != null) {
            view2.setVisibility(recommendAdData.showMonetizedDot() ? 0 : 8);
        }
        if (TextUtils.isEmpty(recommendAdData.client_exposal_url) || recommendAdData.hasRealExpo || recommendAdData.isFromCache) {
            return;
        }
        realExpo(recommendAdData.client_exposal_url);
        recommendAdData.hasRealExpo = true;
    }
}
