package com.jingdong.common.recommend.ui.shopping;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendConfig;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendExpoHelper;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendHeaderData;
import com.jingdong.common.recommend.entity.RecommendHomeTabs;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.ui.RecommendChildRecyclerView;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.eldermode.util.OnElderModeChangeListener;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ShoppingCartRecommendView extends RecommendChildRecyclerView {
    boolean isUploadScrollY;
    private int parentScrollY;

    /* loaded from: classes6.dex */
    class CartAdapter extends RecommendChildRecyclerView.RecommendAdapter {
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
            if (ShoppingCartRecommendView.this.hasData()) {
                return this.recommendUtil.getNewRecommendItemCount() + 1 + 1;
            }
            return 1;
        }

        @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView.RecommendAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            if (ShoppingCartRecommendView.this.hasData()) {
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

        private CartAdapter(BaseActivity baseActivity, RecommendUtil recommendUtil) {
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

    public ShoppingCartRecommendView(RecyclerView recyclerView, @NonNull BaseActivity baseActivity, int i2) {
        super(recyclerView, baseActivity, i2);
        this.isUploadScrollY = true;
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(baseActivity, new Observer<Integer>() { // from class: com.jingdong.common.recommend.ui.shopping.ShoppingCartRecommendView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable Integer num) {
                ShoppingCartRecommendView.this.onDeepDarkChanged();
                if (ShoppingCartRecommendView.this.getAdapter() != null) {
                    ShoppingCartRecommendView.this.getAdapter().notifyDataSetChanged();
                }
            }
        });
        JDElderModeUtils.addElderModeChangeListener(new OnElderModeChangeListener() { // from class: com.jingdong.common.recommend.ui.shopping.ShoppingCartRecommendView.2
            @Override // com.jingdong.sdk.eldermode.util.OnElderModeChangeListener
            public void onElderModeChanged(int i3) {
                if (ShoppingCartRecommendView.this.getAdapter() != null) {
                    ShoppingCartRecommendView.this.getAdapter().notifyDataSetChanged();
                }
            }
        });
        setAutoPlayEnable(true);
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setEnableVideoOffset(1);
        }
        if (RecommendMtaUtils.enableRightExpo) {
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
        RecommendConfig recommendConfig;
        super.beforeRefreshView(i2);
        if (i2 == 1) {
            RecommendUtil recommendUtil = this.mRecommendUtil;
            if (recommendUtil != null && (recommendConfig = recommendUtil.getRecommendConfig()) != null) {
                recommendConfig.serviceDarkSwitch(this.mRecommendUtil.serviceDarkModeEnable);
            }
            try {
                post(new Runnable() { // from class: com.jingdong.common.recommend.ui.shopping.ShoppingCartRecommendView.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ShoppingCartRecommendView.this.onScrollChanged(0);
                        } catch (Exception e2) {
                            if (OKLog.D) {
                                e2.printStackTrace();
                            }
                        }
                    }
                });
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected RecommendChildRecyclerView.RecommendAdapter createAdapter() {
        return new CartAdapter(this.mActivity, this.mRecommendUtil);
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    protected int getSpanSize(int i2) {
        if (getAdapter() != null) {
            int itemViewType = getAdapter().getItemViewType(i2);
            return (itemViewType == 0 || itemViewType == 3) ? 2 : 1;
        }
        return 1;
    }

    public boolean hasData() {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        return recommendUtil != null && recommendUtil.getNewRecommendItemCount() > 0;
    }

    public boolean isVisible() {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager == null) {
            return false;
        }
        return recommendProductManager.getRecommendUtil().isVisible();
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void loadRecommendData() {
        RecommendUtil recommendUtil = this.mRecommendUtil;
        if (recommendUtil != null && recommendUtil.getNewRecommendItemCount() <= 0) {
            if (RecommendUtils.isBAppType()) {
                this.mRecommendProductManager.setSourceExt(RecommendConstant.SHOPPING_B_SOURCE);
            } else {
                this.mRecommendProductManager.setSourceExt("");
            }
        }
        super.loadRecommendData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (!this.isUploadScrollY || getChildTop() <= 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("height", (this.parentScrollY + getChildTop()) + "");
        } catch (JSONException e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        JDMtaUtils.sendExposureDataWithExt(getContext(), "shopcart_RecomHeight", "", RecommendMtaUtils.Shopcart_PageId, "JDHomeFragment", "", jSONObject.toString(), "", "", "", null);
        this.isUploadScrollY = false;
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView, com.jingdong.common.recommend.ScrollDispatchHelper.ScrollDispatchChild
    public void onParentScroll(int i2) {
        this.parentScrollY += i2;
        super.onParentScroll(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void onRangeInserted(int i2, int i3) {
        super.onRangeInserted(i2 + 1, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void onSuccess(int i2, RecommendHomeTabs recommendHomeTabs, RecommendHeaderData recommendHeaderData) {
        super.onSuccess(i2, recommendHomeTabs, recommendHeaderData);
        if (recommendHeaderData != null) {
            setCurrentPlan("B");
        }
    }

    public void setRecommendVisible(boolean z) {
        RecommendProductManager recommendProductManager = this.mRecommendProductManager;
        if (recommendProductManager != null) {
            recommendProductManager.setRecommendVisible(z);
        }
    }

    @Override // com.jingdong.common.recommend.ui.RecommendChildRecyclerView
    public void viewReset() {
        if (hasData()) {
            this.isUploadScrollY = true;
            this.parentScrollY = 0;
        }
        super.viewReset();
    }
}
