package com.jingdong.common.recommend.ui.product;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendDetails;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.recommend.ui.RecommendEmptyView;
import com.jingdong.common.unification.translation.JDTransitionManager;
import java.util.List;

/* loaded from: classes6.dex */
public class PDRecommendView extends RecommendChildRecyclerView {
    RecommendConfig config;
    boolean isDarkModel;

    /* loaded from: classes6.dex */
    public class PDRecommendAdapter extends RecommendChildRecyclerView.RecommendAdapter {
        public PDRecommendAdapter(BaseActivity baseActivity, RecommendUtil recommendUtil) {
            super(baseActivity, recommendUtil);
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ int getItemCount() {
            return super.getItemCount();
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ int getItemViewType(int i2) {
            return super.getItemViewType(i2);
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
            super.onBindViewHolder(viewHolder, i2);
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            if (i2 == 1) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView == null) {
                    ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView = new PDRecommendStateView(PDRecommendView.this.getContext());
                    ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView.addNoData();
                    ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView.setFooterState(1002);
                    ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView.setRetryListener(new RecommendEmptyView.RetryListener() { // from class: com.jingdong.common.recommend.ui.product.PDRecommendView.PDRecommendAdapter.1
                        @Override // com.jingdong.common.recommend.ui.RecommendEmptyView.RetryListener
                        public void emptyRetry() {
                            PDRecommendView.this.setEmptyViewState(1002);
                            PDRecommendView.this.loadRecommendData();
                        }
                    });
                    ((PDRecommendStateView) ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView).setDarkModel(PDRecommendView.this.isDarkModel);
                }
                return new RecommendChildRecyclerView.SimpleViewHolder(((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView);
            }
            return super.onCreateViewHolder(viewGroup, i2);
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            super.onViewAttachedToWindow(viewHolder);
            if (viewHolder != null) {
                if (viewHolder.getItemViewType() - this.TYPE_NUM == 10001 || viewHolder.getItemViewType() - this.TYPE_NUM == 10002) {
                    ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
                    if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                        ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
                    }
                }
            }
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List list) {
            super.onBindViewHolder(viewHolder, i2, list);
        }
    }

    public PDRecommendView(Context context) {
        super(context);
        this.config = new RecommendConfig();
        this.isDarkModel = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void changViewPadding() {
        int i2 = RecommendUtils.RECYCLER_VIEW_PADDING_NEW;
        setPadding(i2, 0, i2, 0);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected RecommendChildRecyclerView.RecommendAdapter createAdapter() {
        return new PDRecommendAdapter(this.mActivity, this.mRecommendUtil);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected void initRecommendManager(int i2) {
        PDRecommendManager pDRecommendManager = new PDRecommendManager(this.mActivity, i2, null) { // from class: com.jingdong.common.recommend.ui.product.PDRecommendView.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void notifyDataChanged(int i3, int i4, int i5) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.beforeRefreshView(i3);
                super.notifyDataChanged(i3, i4, i5);
                PDRecommendView.this.afterNotifyDataChange(i3, i5);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendDataError() {
                super.onRecommendDataError();
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.safeNotifyDataSetChanged();
                if (((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView != null) {
                    ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView.setFooterState(1003);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRecommendOnePageFinish() {
                super.onRecommendOnePageFinish();
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get() || ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView == null) {
                    return;
                }
                ((RecommendChildRecyclerView) PDRecommendView.this).mRecommendEmptyView.setFooterState(1001);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefershListDataRangeChange(int i3, int i4) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.notifyItemRangeChanged(i3, i4);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefershListDataRangeRemove(int i3, int i4) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.notifyItemRangeRemoved(i3, i4);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.ui.product.PDRecommendManager, com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRefreshListData() {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.safeNotifyDataSetChanged();
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRefreshListDataChanged(int i3, int i4, Object obj) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.notifyItemRangeChanged(i3, i4, obj);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRefreshListDataRangeInsert(int i3, int i4) {
                super.onRefreshListDataRangeInsert(i3, i4);
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                PDRecommendView.this.notifyItemRangeInserted(i3, i4);
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            protected void onRequestFail(int i3) {
                if (((RecommendChildRecyclerView) PDRecommendView.this).isDestroy.get()) {
                    return;
                }
                ((RecommendChildRecyclerView) PDRecommendView.this).recommendLoading.set(false);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public void onRequestSuccess(int i3) {
                ((RecommendChildRecyclerView) PDRecommendView.this).recommendLoading.set(false);
                if (((RecommendChildRecyclerView) PDRecommendView.this).onRequestResultListener != null) {
                    ((RecommendChildRecyclerView) PDRecommendView.this).onRequestResultListener.onSuccess(i3, null, null);
                }
            }

            @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
            public boolean specialProductClick(RecommendProduct recommendProduct, int i3, int i4) {
                if (recommendProduct != null && recommendProduct.wareId != null) {
                    Bundle generateProductBundle = RecommendJumpUtils.generateProductBundle(recommendProduct, i3);
                    if (!JDTransitionManager.appendViewDataToBundle(generateProductBundle, recommendProduct.productItemImage, recommendProduct.imgUrl)) {
                        DeeplinkProductDetailHelper.startProductDetailFeeds(((RecommendChildRecyclerView) PDRecommendView.this).mActivity, generateProductBundle);
                    } else {
                        generateProductBundle.putBoolean(BaseActivity.DISPOSEABLE_UNABLE_ANIM, true);
                        DeeplinkProductDetailHelper.startProductDetailFeeds(((RecommendChildRecyclerView) PDRecommendView.this).mActivity, generateProductBundle, ActivityOptionsCompat.makeSceneTransitionAnimation(((RecommendChildRecyclerView) PDRecommendView.this).mActivity, new Pair[0]).toBundle());
                    }
                    if (TextUtils.isEmpty(recommendProduct.isSimilarGoods) || !"1".equals(recommendProduct.isSimilarGoods)) {
                        RecommendMtaUtils.productClickMta(((RecommendChildRecyclerView) PDRecommendView.this).mActivity, recommendProduct, i3, i4);
                    } else {
                        RecommendDetails recommendDetails = new RecommendDetails();
                        recommendDetails.sourceValue = recommendProduct.sourceValue;
                        RecommendMtaUtils.recommendDetalisClickMta(((RecommendChildRecyclerView) PDRecommendView.this).mActivity, recommendDetails, i3);
                    }
                }
                return true;
            }
        };
        this.mRecommendProductManager = pDRecommendManager;
        this.mRecommendUtil = pDRecommendManager.getRecommendUtil();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected void notifyEmptyViewState() {
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected void setBackGround(int i2) {
        ColorDrawable colorDrawable = this.mDefaultBg;
        if (colorDrawable != null) {
            colorDrawable.setColor(0);
            setBackgroundDrawable(this.mDefaultBg);
        }
    }

    public void setIsDarkModel(boolean z) {
        this.config.setPDFeedDarkStyle(z);
        this.isDarkModel = z;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void viewReset() {
        super.viewReset();
        setEmptyViewState(1002);
    }

    public PDRecommendView(RecyclerView recyclerView, @NonNull BaseActivity baseActivity, int i2) {
        super(recyclerView, baseActivity, i2);
        this.config = new RecommendConfig();
        this.isDarkModel = false;
        setCurrentPlan("B");
        setBackGround(0);
        this.isShowEmptyView.set(true);
        setIsEnableAutoLoad(true);
        setAutoPlayEnable(true);
        setRecommendConfig(this.config);
    }
}
