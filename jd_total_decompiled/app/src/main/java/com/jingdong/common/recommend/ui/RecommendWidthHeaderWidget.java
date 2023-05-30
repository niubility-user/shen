package com.jingdong.common.recommend.ui;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendExpoHelper;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendWidthHeaderWidget extends RecommendChildRecyclerView {
    private int fromType;

    /* loaded from: classes6.dex */
    class WidgetAdapter extends RecommendChildRecyclerView.RecommendAdapter {
        public static final int TYPE_HEADER = 3;

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter
        protected int getDataCorrectPosition(int i2) {
            return i2 - 1;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.recommendUtil == null) {
                return 0;
            }
            if (RecommendWidthHeaderWidget.this.hasRecommendData()) {
                return this.recommendUtil.getNewRecommendItemCount() + 1 + 1;
            }
            return 1;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            if (RecommendWidthHeaderWidget.this.hasRecommendData()) {
                if (i2 == 0) {
                    return 3;
                }
                if (i2 == getItemCount() - 1) {
                    return 0;
                }
                return this.recommendUtil.getNewRecommendItemType(i2 - 1, this.TYPE_NUM);
            }
            return 0;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter
        protected int getTypeNum() {
            return 4;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder == null) {
                return;
            }
            if (getItemViewType(i2) == 3) {
                this.recommendUtil.onBindRecommendHeaderViewHolder(viewHolder);
            } else {
                super.onBindViewHolder(viewHolder, i2);
            }
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == 3) {
                return this.recommendUtil.onCreateNewRecommedHeaderViewHolder(viewGroup);
            }
            return super.onCreateViewHolder(viewGroup, i2);
        }

        private WidgetAdapter(BaseActivity baseActivity, RecommendUtil recommendUtil) {
            super(baseActivity, recommendUtil);
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
            if (viewHolder == null) {
                return;
            }
            if (getItemViewType(i2) == 3) {
                this.recommendUtil.onBindRecommendHeaderViewHolder(viewHolder);
            } else {
                super.onBindViewHolder(viewHolder, i2, list);
            }
        }
    }

    public RecommendWidthHeaderWidget(RecyclerView recyclerView, @NonNull BaseActivity baseActivity, int i2) {
        super(recyclerView, baseActivity, i2);
        this.fromType = i2;
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(baseActivity, new Observer<Integer>() { // from class: com.jingdong.common.recommend.ui.RecommendWidthHeaderWidget.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Integer num) {
                RecommendWidthHeaderWidget.this.onDeepDarkChanged();
                if (RecommendWidthHeaderWidget.this.getAdapter() != null) {
                    RecommendWidthHeaderWidget.this.getAdapter().notifyDataSetChanged();
                }
            }
        });
        JDElderModeUtils.addElderModeChangeListener(new OnElderModeChangeListener() { // from class: com.jingdong.common.recommend.ui.RecommendWidthHeaderWidget.2
            @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
            public void onElderModeChanged(int i3) {
                if (RecommendWidthHeaderWidget.this.getAdapter() != null) {
                    RecommendWidthHeaderWidget.this.getAdapter().notifyDataSetChanged();
                }
            }
        });
        setAutoPlayEnable(true);
        if (i2 == 4 || i2 == 3 || i2 == 51 || i2 == 49 || i2 == 10) {
            needRealExpoHelper();
            RecommendExpoHelper recommendExpoHelper = this.expoHelper;
            if (recommendExpoHelper != null) {
                recommendExpoHelper.parentViewBindExpo();
                this.expoHelper.setRightExpo(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void beforeRefreshView(int i2) {
        RecommendUtil recommendUtil;
        RecommendConfig recommendConfig;
        super.beforeRefreshView(i2);
        if (i2 != 1 || (recommendUtil = this.mRecommendUtil) == null || (recommendConfig = recommendUtil.getRecommendConfig()) == null) {
            return;
        }
        recommendConfig.serviceDarkSwitch(this.mRecommendUtil.serviceDarkModeEnable);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected RecommendChildRecyclerView.RecommendAdapter createAdapter() {
        return new WidgetAdapter(this.mActivity, this.mRecommendUtil);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected int getSpanSize(int i2) {
        if (getAdapter() != null) {
            int itemViewType = getAdapter().getItemViewType(i2);
            return (itemViewType == 0 || itemViewType == 3) ? 2 : 1;
        }
        return 1;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void loadRecommendData() {
        RecommendProductManager recommendProductManager;
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if (recommendUtil != null && recommendUtil.getNewRecommendItemCount() <= 0 && RecommendUtils.isBAppType() && (recommendProductManager = this.mRecommendProductManager) != null) {
            if (this.fromType == 51) {
                recommendProductManager.setSourceExt(RecommendConstant.ORDERCENTER_CANCELFINISH_B_SOURCE);
            } else {
                recommendProductManager.setSourceExt("");
            }
        }
        super.loadRecommendData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void onRangeInserted(int i2, int i3) {
        super.onRangeInserted(i2 + 1, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData) {
        if (recommendHeaderData != null) {
            setCurrentPlan("B");
        }
    }
}
