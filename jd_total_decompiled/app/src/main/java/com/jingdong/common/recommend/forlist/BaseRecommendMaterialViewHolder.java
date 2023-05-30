package com.jingdong.common.recommend.forlist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFeedBackManger;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.FeedBackReason;
import com.jingdong.common.recommend.entity.RecommendMaterialData;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.RecommendMaterialFeedBackAniView;
import java.util.List;

/* loaded from: classes6.dex */
public class BaseRecommendMaterialViewHolder extends BaseRecommendAdViewHolder {
    private BaseActivity activity;
    RecommendMaterialFeedBackAniView aniView;
    protected TextView debugMarkTextView;
    private View deleteView;
    private RecommendMaterialData materialData;
    private int positionInData;

    public BaseRecommendMaterialViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.activity = baseActivity;
        this.deleteView = view.findViewById(R.id.recommend_delete);
        this.debugMarkTextView = (TextView) view.findViewById(R.id.recommend_debug_mark);
    }

    private boolean canFeedBack(RecommendMaterialData recommendMaterialData) {
        List<FeedBackReason> list = recommendMaterialData.feedBackReason;
        return (list == null || list.isEmpty()) ? false : true;
    }

    private void handlePopupWindowProblem(RecommendFeedBackManger recommendFeedBackManger) {
        recommendFeedBackManger.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                View view = BaseRecommendMaterialViewHolder.this.itemView;
                if (view instanceof ViewGroup) {
                    ((ViewGroup) view).requestDisallowInterceptTouchEvent(false);
                }
            }
        });
        View view = this.itemView;
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).requestDisallowInterceptTouchEvent(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFeedBackWindow(RecommendMaterialData recommendMaterialData, int i2, int i3) {
        RecommendFeedBackManger recommendFeedBackManger = RecommendFeedBackManger.getInstance();
        handlePopupWindowProblem(recommendFeedBackManger);
        recommendFeedBackManger.showTipPopupWindow(this.activity, this.deleteView, recommendMaterialData, i2, this.clickedListener, i3);
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            onRecommendClickedListener.onDotMoreMta(i3, recommendMaterialData != null ? recommendMaterialData.sourceValueFeedback : "");
        }
    }

    public void setMaterialData(final RecommendMaterialData recommendMaterialData, final int i2) {
        this.materialData = recommendMaterialData;
        this.positionInData = i2;
        RecommendViewUtil.showUnIcon(this.adIcon, recommendMaterialData.iconAd);
        if (recommendMaterialData != null) {
            if (canFeedBack(recommendMaterialData)) {
                RecommendViewUtil.visible(this.deleteView);
                RecommendViewUtil.setOnClickListener(this.deleteView, new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseRecommendMaterialViewHolder.this.showFeedBackWindow(recommendMaterialData, i2, 1);
                    }
                });
                this.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder.2
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        BaseRecommendMaterialViewHolder.this.showFeedBackWindow(recommendMaterialData, i2, 2);
                        return false;
                    }
                });
                return;
            }
            RecommendViewUtil.gone(this.deleteView);
            this.itemView.setOnLongClickListener(null);
        }
    }

    public void showFeedBackAni() {
        if (this.aniView == null) {
            RecommendMaterialFeedBackAniView recommendMaterialFeedBackAniView = new RecommendMaterialFeedBackAniView(this.itemView.getContext());
            this.aniView = recommendMaterialFeedBackAniView;
            recommendMaterialFeedBackAniView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            View view = this.itemView;
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).addView(this.aniView);
            }
            this.aniView.showGuide();
            this.aniView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                }
            });
            this.aniView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder.5
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    if (BaseRecommendMaterialViewHolder.this.aniView.getParent() != null) {
                        RecommendViewUtil.gone(BaseRecommendMaterialViewHolder.this.aniView);
                        BaseRecommendMaterialViewHolder baseRecommendMaterialViewHolder = BaseRecommendMaterialViewHolder.this;
                        baseRecommendMaterialViewHolder.showFeedBackWindow(baseRecommendMaterialViewHolder.materialData, BaseRecommendMaterialViewHolder.this.positionInData, 2);
                        return true;
                    }
                    return true;
                }
            });
        }
    }
}
