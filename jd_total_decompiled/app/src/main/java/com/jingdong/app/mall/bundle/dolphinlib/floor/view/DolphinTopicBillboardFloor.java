package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.adapter.DolphinTopicBillboardStaggeredAdapter;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTopicBillboardFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinTopicBillboardFloorModel;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class DolphinTopicBillboardFloor extends FrameLayout implements IFloorView<DolphinTopicBillboardFloorModel>, LifecycleObserver {
    private static final int RV_ROW_SPAN_COUNT = 2;
    private ImageView mBgImage;
    private GradientDrawable mContainerDrawable;
    private View mContentContainer;
    private RecyclerView mRvTopicBillboard;
    private DolphinTopicBillboardStaggeredAdapter mTopicBillboardAdapter;
    private Set<Integer> setExpoMta;

    /* loaded from: classes19.dex */
    private static final class FixedStaggeredLayoutManager extends StaggeredGridLayoutManager {
        FixedStaggeredLayoutManager() {
            super(2, 0);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onScrollStateChanged(int i2) {
            try {
                super.onScrollStateChanged(i2);
            } catch (Exception unused) {
            }
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                return super.scrollHorizontallyBy(i2, recycler, state);
            } catch (Exception unused) {
                return 0;
            }
        }
    }

    /* loaded from: classes19.dex */
    private static final class ItemDecorator extends RecyclerView.ItemDecoration {
        private static final int MARGIN_RIGHT_SIZE = DPIUtil.dip2px(7.0f);
        private static final int MARGIN_BOTTOM_SIZE = DPIUtil.dip2px(8.0f);
        private static final int MARGIN_LEFT_ON_FIRST_ITEM = DPIUtil.dip2px(10.0f);

        private ItemDecorator() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
            int i2 = MARGIN_BOTTOM_SIZE;
            rect.top = (spanIndex * i2) / 2;
            rect.bottom = i2 - (((spanIndex + 1) * i2) / 2);
            if (recyclerView.getChildAdapterPosition(view) < 2) {
                rect.left = MARGIN_LEFT_ON_FIRST_ITEM;
            } else {
                rect.left = MARGIN_RIGHT_SIZE;
            }
        }
    }

    public DolphinTopicBillboardFloor(Context context) {
        super(context);
        this.setExpoMta = new HashSet();
        if (getLayoutParams() == null) {
            setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        }
        setBackgroundColor(0);
    }

    private boolean onUpdateContainerBackground(DolphinTopicBillboardFloorConfig dolphinTopicBillboardFloorConfig) {
        if (dolphinTopicBillboardFloorConfig == null || this.mContainerDrawable == null || this.mRvTopicBillboard == null) {
            return false;
        }
        this.mContainerDrawable.setColor(!TextUtils.isEmpty(dolphinTopicBillboardFloorConfig.bgCellColor) ? ColorUtil.parseColor(dolphinTopicBillboardFloorConfig.bgCellColor, 0) : 0);
        this.mContainerDrawable.setCornerRadii(new float[]{DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.topLeftRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.topLeftRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.topRightRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.topRightRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.bottomLeftRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.bottomLeftRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.bottomRightRadius), DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.bottomRightRadius)});
        this.mContentContainer.setBackgroundDrawable(this.mContainerDrawable);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mContentContainer.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        }
        marginLayoutParams.leftMargin = DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.marginLeft);
        marginLayoutParams.topMargin = DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.marginTop);
        marginLayoutParams.rightMargin = DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.marginRight);
        marginLayoutParams.bottomMargin = DPIUtil.dip2px(dolphinTopicBillboardFloorConfig.marginBottom);
        this.mContentContainer.setLayoutParams(marginLayoutParams);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onUpdateFloorBackground(DolphinTopicBillboardFloorConfig dolphinTopicBillboardFloorConfig) {
        if (dolphinTopicBillboardFloorConfig == null) {
            return false;
        }
        if (!TextUtils.isEmpty(dolphinTopicBillboardFloorConfig.backgroundImage)) {
            return onUpdateFloorBgImage(dolphinTopicBillboardFloorConfig.backgroundImage);
        }
        return onUpdateFloorBgColor(dolphinTopicBillboardFloorConfig.backgroundColor);
    }

    private boolean onUpdateFloorBgColor(String str) {
        int i2 = 0;
        if (!TextUtils.isEmpty(str)) {
            if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                i2 = DeepDarkUtils.getDarkColor_F2F2F2_bg1();
            } else {
                i2 = ColorUtil.parseColor(str, 0);
            }
        }
        setBackgroundColor(i2);
        return true;
    }

    private boolean onUpdateFloorBgImage(String str) {
        if (this.mBgImage == null) {
            ImageView imageView = CommonServiceUtil.getImageView(getContext());
            this.mBgImage = imageView;
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.mBgImage, 0, new FrameLayout.LayoutParams(-1, -1));
        }
        CommonServiceUtil.displayImage(str, this.mBgImage);
        return true;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LogUtil.d("asdf", "*** init topic billboard ***");
        if (getContext() instanceof LifecycleOwner) {
            ((LifecycleOwner) getContext()).getLifecycle().addObserver(this);
        }
        LayoutInflater.from(getContext()).inflate(R.layout.dolphin_topic_billboard_floor, this);
        this.mContentContainer = findViewById(R.id.containerTopicBillboard);
        this.mContainerDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.dolphin_topic_billboard_bg);
        RecyclerView recyclerView = (RecyclerView) this.mContentContainer.findViewById(R.id.rvTopicBillboard);
        this.mRvTopicBillboard = recyclerView;
        recyclerView.setFocusable(false);
        this.mRvTopicBillboard.setFocusableInTouchMode(false);
        DolphinTopicBillboardStaggeredAdapter dolphinTopicBillboardStaggeredAdapter = new DolphinTopicBillboardStaggeredAdapter();
        this.mTopicBillboardAdapter = dolphinTopicBillboardStaggeredAdapter;
        this.mRvTopicBillboard.setAdapter(dolphinTopicBillboardStaggeredAdapter);
        this.mRvTopicBillboard.setLayoutManager(new FixedStaggeredLayoutManager());
        this.mRvTopicBillboard.addItemDecoration(new ItemDecorator());
        this.mRvTopicBillboard.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinTopicBillboardFloor.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView2, int i2, int i3) {
                super.onScrolled(recyclerView2, i2, i3);
                int childCount = recyclerView2.getChildCount();
                if (childCount <= 0 || DolphinTopicBillboardFloor.this.mTopicBillboardAdapter == null) {
                    return;
                }
                for (int i4 = 0; i4 < childCount; i4++) {
                    DolphinTopicBillboardFloor.this.setExpoMta.add(Integer.valueOf(recyclerView2.getChildAdapterPosition(recyclerView2.getChildAt(i4))));
                }
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onLifecycleDestroy() {
        if (getContext() instanceof LifecycleOwner) {
            ((LifecycleOwner) getContext()).getLifecycle().removeObserver(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onLifecyclePaused() {
        if (this.mTopicBillboardAdapter == null || this.setExpoMta.isEmpty()) {
            return;
        }
        this.mTopicBillboardAdapter.onSendAdvExpo(getContext(), new ArrayList(this.setExpoMta));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onLifecycleResumed() {
        if (this.setExpoMta.isEmpty()) {
            return;
        }
        this.setExpoMta.clear();
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void update(BabelScope babelScope, final DolphinTopicBillboardFloorModel dolphinTopicBillboardFloorModel, int i2) {
        LogUtil.d("asdf", "*** update topic billboard *** ");
        DolphinTopicBillboardStaggeredAdapter dolphinTopicBillboardStaggeredAdapter = this.mTopicBillboardAdapter;
        if (dolphinTopicBillboardStaggeredAdapter != null && dolphinTopicBillboardFloorModel != null) {
            dolphinTopicBillboardStaggeredAdapter.onUpdateTopicData(dolphinTopicBillboardFloorModel.boardParams, dolphinTopicBillboardFloorModel.advertGroupData);
            this.mTopicBillboardAdapter.onUpdateMtaData(babelScope, dolphinTopicBillboardFloorModel);
        }
        onUpdateContainerBackground(dolphinTopicBillboardFloorModel.boardParams);
        onUpdateFloorBackground(dolphinTopicBillboardFloorModel.boardParams);
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(new DeepDarkChangeManager.OnUIModeChangeListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinTopicBillboardFloor.2
            @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
            public void onUIModeChanged(int i3) {
                DolphinTopicBillboardFloor.this.onUpdateFloorBackground(dolphinTopicBillboardFloorModel.boardParams);
            }
        });
    }
}
