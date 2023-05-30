package com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.common.view.JDViewKitRecycleView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.jdviewkitdynamicbanner.JDViewKitVirtualDynamicBanner;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.tool.StringTool;
import com.jd.viewkit.tool.animator.LinerObjectAnimator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitReversalDynamicBanner extends JDViewKitBaseLayout<JDViewKitVirtualDynamicBanner> {
    public static final int scorllCount = 1000;
    private ReversalDynamicAdapter adapter;
    private int animatorX;
    private boolean autoPlay;
    private int columnNumber;
    private int edgeSpace;
    private GridLayoutManager gridLayoutManager;
    private int interval;
    private boolean isCircle;
    private Boolean isScrollEnd;
    private Boolean isScrolling;
    private boolean isStart;
    private Boolean isUserControl;
    private boolean isUserTouch;
    private int itemHeigh;
    private int itemSpace;
    private int itemWidth;
    private int lineNumber;
    private int lineSpace;
    private LinerObjectAnimator linerObjectAnimator;
    List<View> list;
    private Handler mHandler;
    private boolean mIsLayout;
    private JDViewKitRecycleView mainRecycleView;
    private int onTouchX;
    private List<Integer> paddings;
    private Runnable runnable;
    private SpaceItemDecoration spaceItemDecoration;
    private final Runnable task;
    private int viewHeigh;
    private int viewWidth;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes18.dex */
    public class ReversalDynamicAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context mContext;
        public List<JDViewKitDataSourceModel> modelList;
        private int typeInt = -1;
        private Map<String, String> typeMap = new ArrayMap();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes18.dex */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public JDViewKitAbsoluteLayout absoluteLayout;

            public ViewHolder(JDViewKitAbsoluteLayout jDViewKitAbsoluteLayout) {
                super(jDViewKitAbsoluteLayout);
                this.absoluteLayout = jDViewKitAbsoluteLayout;
            }
        }

        public ReversalDynamicAdapter(Context context) {
            this.mContext = context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.modelList == null) {
                return 0;
            }
            if (JDViewKitReversalDynamicBanner.this.isCircle) {
                return this.modelList.size() * 1000;
            }
            return this.modelList.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            if (JDViewKitReversalDynamicBanner.this.isCircle) {
                i2 %= this.modelList.size();
            }
            String dslId = this.modelList.get(i2).getDslId();
            String str = this.typeMap.get(dslId);
            if (StringTool.isEmpty(str)) {
                this.typeInt++;
                str = "" + this.typeInt;
                this.typeMap.put(dslId, str);
            }
            return Integer.parseInt(str);
        }

        public List<JDViewKitDataSourceModel> getModelList() {
            return this.modelList;
        }

        public String getTemplateByViewType(int i2) {
            String str = "" + i2;
            for (String str2 : this.typeMap.keySet()) {
                if (this.typeMap.get(str2).equals(str)) {
                    return str2;
                }
            }
            return null;
        }

        public void setModelList(List<JDViewKitDataSourceModel> list) {
            this.modelList = list;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
            if (JDViewKitReversalDynamicBanner.this.isCircle) {
                i2 %= this.modelList.size();
            }
            JDViewKitDataSourceModel jDViewKitDataSourceModel = this.modelList.get(i2);
            if (jDViewKitDataSourceModel != null) {
                viewHolder.absoluteLayout.setDataSourceModel(jDViewKitDataSourceModel, JDViewKitReversalDynamicBanner.this.mIsLayout);
                JDViewKitReversalDynamicBanner.this.sendExpo(jDViewKitDataSourceModel);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            JDViewKitVirtualView jDViewKitVirtualView = (JDViewKitVirtualView) ((JDViewKitBaseLayout) JDViewKitReversalDynamicBanner.this).dslsMap.get(getTemplateByViewType(i2));
            if (jDViewKitVirtualView != null) {
                JDViewKitReversalDynamicBanner.this.viewWidth = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getWidth(), JDViewKitReversalDynamicBanner.this.getChannelModel());
            }
            return new ViewHolder(JDViewKitViewFactory.getView(viewGroup.getContext(), jDViewKitVirtualView, JDViewKitReversalDynamicBanner.this.getChannelModel()));
        }

        public ReversalDynamicAdapter(Context context, List<JDViewKitDataSourceModel> list) {
            this.mContext = context;
            this.modelList = list;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes18.dex */
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int itemSpace;
        private int lineSpace;

        public SpaceItemDecoration(int i2, int i3) {
            this.itemSpace = i2;
            this.lineSpace = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
            if (childLayoutPosition / JDViewKitReversalDynamicBanner.this.lineNumber != 0) {
                rect.left = this.itemSpace;
            }
            if (childLayoutPosition % JDViewKitReversalDynamicBanner.this.lineNumber != 0) {
                rect.top = this.lineSpace;
            }
        }

        public int getItemSpace() {
            return this.itemSpace;
        }

        public int getLineSpace() {
            return this.lineSpace;
        }

        public void setItemSpace(int i2) {
            this.itemSpace = i2;
        }

        public void setLineSpace(int i2) {
            this.lineSpace = i2;
        }
    }

    public JDViewKitReversalDynamicBanner(@NonNull Context context) {
        super(context);
        this.paddings = new ArrayList();
        Boolean bool = Boolean.FALSE;
        this.isUserControl = bool;
        this.isScrollEnd = bool;
        this.isScrolling = bool;
        this.isUserTouch = false;
        this.runnable = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.1
            @Override // java.lang.Runnable
            public void run() {
                JDViewKitReversalDynamicBanner.this.selectNum();
            }
        };
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.2
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitReversalDynamicBanner.this.autoPlay || JDViewKitReversalDynamicBanner.this.isUserTouch) {
                    return;
                }
                JDViewKitReversalDynamicBanner.this.startAin();
                JDViewKitReversalDynamicBanner.this.mHandler.postDelayed(this, JDViewKitReversalDynamicBanner.this.interval);
            }
        };
        this.list = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNowScrollX() {
        try {
            View childAt = this.gridLayoutManager.getChildAt(0);
            return (this.gridLayoutManager.getPosition(childAt) * (this.itemWidth + this.itemSpace)) - childAt.getLeft();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectNum() {
        this.isUserControl = Boolean.TRUE;
        View childAt = this.gridLayoutManager.getChildAt(0);
        if (childAt != null) {
            LinearInterpolator linearInterpolator = new LinearInterpolator();
            if (childAt.getRight() > this.itemWidth / 2) {
                this.mainRecycleView.smoothScrollBy(childAt.getLeft(), 0, linearInterpolator);
            } else {
                this.mainRecycleView.smoothScrollBy(childAt.getRight() + this.itemSpace, 0, linearInterpolator);
            }
        }
    }

    private void setItemTop(View view, int i2, float f2, int i3) {
        if (this.paddings.size() == this.columnNumber) {
            if (i2 == 0) {
                view.setTranslationY(this.paddings.get(0).intValue());
            } else if (i2 >= this.paddings.size()) {
                List<Integer> list = this.paddings;
                view.setTranslationY(list.get(list.size() - 1).intValue());
            } else {
                int intValue = this.paddings.get(i2 - 1).intValue();
                int intValue2 = this.paddings.get(i2).intValue();
                int abs = Math.abs(intValue2 - intValue);
                int min = Math.min(intValue, intValue2);
                if (intValue2 < intValue) {
                    f2 = 1.0f - f2;
                }
                view.setTranslationY((abs * f2) + min);
            }
        }
    }

    private void setViewAnimation(View view, float f2, float f3) {
        if (view != null) {
            view.setRotationY(f2);
            view.setAlpha(f3);
            float f4 = (f3 * 0.2f) + 0.8f;
            view.setScaleY(f4);
            view.setScaleX(f4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void start() {
        if (this.isStart) {
            return;
        }
        this.isStart = true;
        this.mHandler.postDelayed(this.task, this.interval);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        LinerObjectAnimator linerObjectAnimator = this.linerObjectAnimator;
        if (linerObjectAnimator != null) {
            this.animatorX = linerObjectAnimator.lineDistanceState;
            linerObjectAnimator.cancel();
        }
        this.mHandler.removeCallbacks(this.task);
        this.isStart = false;
    }

    private void updataView() {
        if (this.mainRecycleView == null) {
            JDViewKitRecycleView jDViewKitRecycleView = new JDViewKitRecycleView(this.mContext);
            this.mainRecycleView = jDViewKitRecycleView;
            jDViewKitRecycleView.setBackgroundColor(0);
            this.mainRecycleView.setOnFlingListener(new RecyclerView.OnFlingListener() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.4
                @Override // androidx.recyclerview.widget.RecyclerView.OnFlingListener
                public boolean onFling(int i2, int i3) {
                    return true;
                }
            });
            SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(this.itemSpace, this.lineSpace);
            this.spaceItemDecoration = spaceItemDecoration;
            this.mainRecycleView.addItemDecoration(spaceItemDecoration);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.mContext, this.lineNumber, 0, false);
            this.gridLayoutManager = gridLayoutManager;
            this.mainRecycleView.setLayoutManager(gridLayoutManager);
            ReversalDynamicAdapter reversalDynamicAdapter = new ReversalDynamicAdapter(this.mContext);
            this.adapter = reversalDynamicAdapter;
            this.mainRecycleView.setAdapter(reversalDynamicAdapter);
            this.mainRecycleView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            addView(this.mainRecycleView);
            this.mainRecycleView.setTouchEventListener(new JDViewKitRecycleView.TouchEventListener() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.5
                @Override // com.jd.viewkit.common.view.JDViewKitRecycleView.TouchEventListener
                public void touchEventCancel() {
                }

                @Override // com.jd.viewkit.common.view.JDViewKitRecycleView.TouchEventListener
                public void touchEventDown() {
                    JDViewKitReversalDynamicBanner.this.isUserTouch = true;
                    JDViewKitReversalDynamicBanner.this.stop();
                    JDViewKitReversalDynamicBanner jDViewKitReversalDynamicBanner = JDViewKitReversalDynamicBanner.this;
                    jDViewKitReversalDynamicBanner.onTouchX = jDViewKitReversalDynamicBanner.getNowScrollX() + JDViewKitReversalDynamicBanner.this.animatorX;
                    if (JDViewKitReversalDynamicBanner.this.linerObjectAnimator != null) {
                        JDViewKitReversalDynamicBanner.this.linerObjectAnimator.cancel();
                    }
                }

                @Override // com.jd.viewkit.common.view.JDViewKitRecycleView.TouchEventListener
                public void touchEventUp() {
                    JDViewKitReversalDynamicBanner.this.start();
                    JDViewKitReversalDynamicBanner.this.mainRecycleView.postDelayed(JDViewKitReversalDynamicBanner.this.runnable, 0L);
                    JDViewKitReversalDynamicBanner.this.isUserTouch = false;
                }
            });
            this.mainRecycleView.setMyOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.6
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
                    super.onScrollStateChanged(recyclerView, i2);
                    if (i2 == 2) {
                        JDViewKitReversalDynamicBanner.this.isScrolling = Boolean.TRUE;
                    }
                    if (i2 == 0) {
                        JDViewKitReversalDynamicBanner.this.isScrolling = Boolean.FALSE;
                    }
                    if (i2 == 0 && !JDViewKitReversalDynamicBanner.this.isUserControl.booleanValue()) {
                        recyclerView.postDelayed(JDViewKitReversalDynamicBanner.this.runnable, 200L);
                    }
                    if (recyclerView.getScrollState() != 2) {
                        JDViewKitReversalDynamicBanner.this.isUserControl = Boolean.FALSE;
                    }
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
                    super.onScrolled(recyclerView, i2, i3);
                    JDViewKitReversalDynamicBanner.this.updataViewByScrolled(i2);
                    if (recyclerView.getScrollState() != 2 || JDViewKitReversalDynamicBanner.this.isUserControl.booleanValue()) {
                        return;
                    }
                    if (i2 > -3 && i2 < 3) {
                        recyclerView.stopScroll();
                        JDViewKitReversalDynamicBanner.this.isScrolling = Boolean.FALSE;
                        JDViewKitReversalDynamicBanner.this.isScrollEnd = Boolean.TRUE;
                        return;
                    }
                    JDViewKitReversalDynamicBanner.this.isScrollEnd = Boolean.FALSE;
                }
            });
        }
        if (this.virtualView != 0) {
            if (this.columnNumber > 1 && this.lineSpace > 0) {
                this.gridLayoutManager.setSpanCount(this.lineNumber);
                this.spaceItemDecoration.setItemSpace(this.itemSpace);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mainRecycleView.getLayoutParams();
            int i2 = this.viewWidth;
            int i3 = this.edgeSpace;
            layoutParams.width = i2 - (i3 * 2);
            layoutParams.leftMargin = i3;
            this.mainRecycleView.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updataViewByScrolled(int i2) {
        double d;
        double d2;
        double d3;
        float f2;
        float f3;
        View childAt;
        View childAt2;
        this.list.clear();
        View childAt3 = this.gridLayoutManager.getChildAt(0);
        if (childAt3 != null) {
            this.list.add(childAt3);
            double left = childAt3.getLeft();
            Double.isNaN(left);
            double width = childAt3.getWidth() + this.itemSpace;
            Double.isNaN(width);
            d = Math.abs((left * 1.0d) / width);
            childAt3.setPivotX(this.itemWidth);
            if (this.lineNumber == 2 && (childAt2 = this.gridLayoutManager.getChildAt(1)) != null && childAt3.getLeft() == childAt2.getLeft()) {
                this.list.add(childAt2);
                childAt3.setPivotY(this.itemHeigh);
                childAt2.setPivotX(this.itemWidth);
                childAt2.setPivotY(0.0f);
                setViewAnimation(childAt2, -((float) (d * 90.0d)), 1.0f - ((float) d));
            }
            setViewAnimation(childAt3, -((float) (d * 90.0d)), 1.0f - ((float) d));
        } else {
            d = 0.0d;
        }
        int i3 = this.lineNumber;
        int i4 = this.columnNumber;
        int i5 = (i3 * i4) + 1;
        if (i3 == 1) {
            i5 = i3 * i4;
        }
        View childAt4 = this.gridLayoutManager.getChildAt(i5);
        if (childAt4 != null) {
            this.list.add(childAt4);
            d2 = d;
            double right = childAt4.getRight() - this.mainRecycleView.getWidth();
            Double.isNaN(right);
            double width2 = childAt4.getWidth();
            Double.isNaN(width2);
            double abs = Math.abs((right * 1.0d) / width2);
            childAt4.setPivotX(0.0f);
            if (this.lineNumber == 2 && (childAt = this.gridLayoutManager.getChildAt(i5 - 1)) != null && childAt4.getLeft() == childAt.getLeft()) {
                this.list.add(childAt);
                childAt4.setPivotY(0.0f);
                childAt.setPivotX(0.0f);
                childAt.setPivotY(this.itemHeigh);
                f3 = 1.0f;
                setViewAnimation(childAt, (float) (abs * 90.0d), 1.0f - ((float) abs));
            } else {
                f3 = 1.0f;
            }
            setViewAnimation(childAt4, (float) (90.0d * abs), f3 - ((float) abs));
            d3 = 1.0d;
        } else {
            d2 = d;
            d3 = 1.0d;
        }
        float f4 = (float) (d3 - d2);
        boolean z = getNowScrollX() < this.onTouchX;
        for (int i6 = 0; i6 < this.gridLayoutManager.getChildCount(); i6++) {
            View childAt5 = this.gridLayoutManager.getChildAt(i6);
            if (childAt5 != null) {
                double right2 = childAt3.getRight();
                Double.isNaN(right2);
                double d4 = this.itemWidth;
                Double.isNaN(d4);
                setItemTop(childAt5, i6 / this.lineNumber, (float) ((right2 * 1.0d) / d4), i2);
                if (!this.list.contains(childAt5)) {
                    float f5 = 16.0f;
                    if (z) {
                        f5 = -16.0f;
                        childAt5.setPivotX(this.itemWidth);
                    } else {
                        childAt5.setPivotX(0.0f);
                    }
                    int i7 = this.lineNumber;
                    if (i7 == 2) {
                        if (i6 % i7 == 0) {
                            childAt5.setPivotY(this.itemHeigh);
                        } else {
                            childAt5.setPivotY(0.0f);
                        }
                    }
                    int i8 = i6 / this.lineNumber;
                    if (i8 == 2) {
                        childAt5.setPivotX(this.itemWidth / 2);
                    }
                    childAt5.setPivotX(this.itemWidth / 2);
                    float f6 = f4 * 2.0f;
                    if (f6 > 1.0f) {
                        f6 = 2.0f - f6;
                    }
                    childAt5.setRotationY(f5 * f6);
                    childAt5.setAlpha(0.99f);
                    double d5 = f4;
                    Double.isNaN(d5);
                    float abs2 = (float) (Math.abs(d5 - 0.5d) + 0.5d);
                    if (!z) {
                        if (i8 == 1) {
                            f2 = (abs2 * 0.05f) + 0.95f;
                            childAt5.setTranslationX(0.0f);
                        } else if (i8 == 2) {
                            f2 = 0.9f + (0.1f * abs2);
                            childAt5.setTranslationX((1.0f - abs2) * (-30.0f));
                            childAt5.setScaleX(f2);
                            childAt5.setScaleY(f2);
                        } else {
                            if (i8 == 3) {
                                f2 = 0.875f + (0.125f * abs2);
                                childAt5.setTranslationX((1.0f - abs2) * (-60.0f));
                            }
                            f2 = 1.0f;
                            childAt5.setScaleX(f2);
                            childAt5.setScaleY(f2);
                        }
                        childAt5.setScaleX(f2);
                        childAt5.setScaleY(f2);
                    } else if (i8 == 3) {
                        f2 = (abs2 * 0.05f) + 0.95f;
                        childAt5.setTranslationX(0.0f);
                        childAt5.setScaleX(f2);
                        childAt5.setScaleY(f2);
                    } else {
                        if (i8 == 2) {
                            f2 = 0.9f + (0.1f * abs2);
                            childAt5.setTranslationX((1.0f - abs2) * 30.0f);
                        } else {
                            if (i8 == 1) {
                                f2 = 0.875f + (0.125f * abs2);
                                childAt5.setTranslationX((1.0f - abs2) * 60.0f);
                            }
                            f2 = 1.0f;
                        }
                        childAt5.setScaleX(f2);
                        childAt5.setScaleY(f2);
                    }
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    public void sendExpo(JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        Map<String, JDViewKitVirtualView> map;
        JDViewKitVirtualView jDViewKitVirtualView;
        if (jDViewKitDataSourceModel == null || (map = this.dslsMap) == null || (jDViewKitVirtualView = map.get(jDViewKitDataSourceModel.getDslId())) == null || jDViewKitVirtualView.getExpoEvent() == null) {
            return;
        }
        JDViewKitEventHelper.sendExpo(jDViewKitVirtualView, jDViewKitVirtualView.getExpoEvent(), jDViewKitDataSourceModel, this);
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setDataSourceModels(List<JDViewKitDataSourceModel> list, boolean z) {
        super.setDataSourceModels(list, z);
        this.mIsLayout = z;
        if (this.mainRecycleView == null || getDataSourceModels() == null || getDataSourceModels().size() == 0) {
            return;
        }
        JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(getDataSourceModels().get(0).getDslId());
        if (jDViewKitVirtualView != null) {
            this.itemWidth = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getWidth(), getChannelModel());
            int realPx = GlobalManage.getInstance().getRealPx(jDViewKitVirtualView.getHeigh(), getChannelModel());
            this.itemHeigh = realPx;
            int i2 = this.viewHeigh;
            int i3 = this.lineNumber;
            if (i2 / i3 > realPx) {
                this.spaceItemDecoration.setLineSpace(this.lineSpace - ((i2 / i3) - realPx));
            } else {
                this.spaceItemDecoration.setLineSpace(this.lineSpace);
            }
        }
        if (this.lineNumber == 2 && getDataSourceModels().size() % 2 == 1) {
            getDataSourceModels().remove(getDataSourceModels().size() - 1);
        }
        this.adapter.setModelList(getDataSourceModels());
        this.adapter.notifyDataSetChanged();
        if (this.isCircle) {
            this.gridLayoutManager.scrollToPositionWithOffset((getDataSourceModels().size() * 5) + this.lineNumber, this.itemWidth);
        }
        if (this.autoPlay) {
            stop();
            start();
        }
    }

    public void startAin() {
        if (this.linerObjectAnimator == null) {
            LinerObjectAnimator linerObjectAnimator = new LinerObjectAnimator(600L, this.itemSpace + this.itemWidth);
            this.linerObjectAnimator = linerObjectAnimator;
            linerObjectAnimator.setAnimatorListener(new LinerObjectAnimator.LinerObjectAnimatorListener() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.3
                @Override // com.jd.viewkit.tool.animator.LinerObjectAnimator.LinerObjectAnimatorListener
                public void end(int i2) {
                    JDViewKitReversalDynamicBanner.this.mainRecycleView.scrollBy(i2, 0);
                    View childAt = JDViewKitReversalDynamicBanner.this.gridLayoutManager.getChildAt(0);
                    if (childAt != null) {
                        JDViewKitReversalDynamicBanner.this.mainRecycleView.scrollBy(childAt.getLeft(), 0);
                    }
                }

                @Override // com.jd.viewkit.tool.animator.LinerObjectAnimator.LinerObjectAnimatorListener
                public void updata(int i2) {
                    if (JDViewKitReversalDynamicBanner.this.isUserTouch) {
                        JDViewKitReversalDynamicBanner.this.linerObjectAnimator.cancel();
                    }
                    JDViewKitReversalDynamicBanner.this.mainRecycleView.scrollBy(i2, 0);
                }
            });
        }
        if (this.isUserTouch) {
            return;
        }
        this.linerObjectAnimator.start();
        this.animatorX = 0;
    }

    @Override // com.jd.viewkit.templates.JDViewKitBaseLayout
    public void setVirtualView(JDViewKitVirtualDynamicBanner jDViewKitVirtualDynamicBanner) {
        super.setVirtualView((JDViewKitReversalDynamicBanner) jDViewKitVirtualDynamicBanner);
        if (jDViewKitVirtualDynamicBanner == null || jDViewKitVirtualDynamicBanner.getBannerConfig() == null) {
            return;
        }
        int lineNumber = jDViewKitVirtualDynamicBanner.getBannerConfig().getLineNumber();
        this.lineNumber = lineNumber;
        if (lineNumber == 0) {
            lineNumber = 1;
        }
        this.lineNumber = lineNumber;
        int columnNumber = jDViewKitVirtualDynamicBanner.getBannerConfig().getColumnNumber();
        this.columnNumber = columnNumber;
        if (columnNumber == 0) {
            columnNumber = 4;
        }
        this.columnNumber = columnNumber;
        this.lineSpace = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getBannerConfig().getLineSpace(), getChannelModel());
        this.edgeSpace = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getBannerConfig().getEdgeSpace(), getChannelModel());
        this.itemSpace = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getBannerConfig().getItemSpace(), getChannelModel());
        this.viewWidth = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getWidth(), getChannelModel());
        this.viewHeigh = GlobalManage.getInstance().getRealPx(jDViewKitVirtualDynamicBanner.getHeigh(), getChannelModel());
        this.isCircle = jDViewKitVirtualDynamicBanner.isCircle();
        this.autoPlay = jDViewKitVirtualDynamicBanner.isAutoPlay();
        int interval = jDViewKitVirtualDynamicBanner.getInterval();
        this.interval = interval;
        if (interval == 0) {
            interval = 3000;
        }
        this.interval = interval;
        if (jDViewKitVirtualDynamicBanner.getBannerConfig().getTopPaddings() != null) {
            this.paddings.clear();
            Iterator<Integer> it = jDViewKitVirtualDynamicBanner.getBannerConfig().getTopPaddings().iterator();
            while (it.hasNext()) {
                this.paddings.add(Integer.valueOf(GlobalManage.getInstance().getRealPx(it.next().intValue(), getChannelModel())));
            }
        }
        int i2 = this.columnNumber;
        if (i2 > 0) {
            this.itemWidth = ((this.viewWidth - (this.edgeSpace * 2)) - (this.itemSpace * (i2 - 1))) / i2;
        }
        updataView();
    }

    public JDViewKitReversalDynamicBanner(@NonNull Context context, @NonNull JDViewKitChannelModel jDViewKitChannelModel) {
        super(context, jDViewKitChannelModel);
        this.paddings = new ArrayList();
        Boolean bool = Boolean.FALSE;
        this.isUserControl = bool;
        this.isScrollEnd = bool;
        this.isScrolling = bool;
        this.isUserTouch = false;
        this.runnable = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.1
            @Override // java.lang.Runnable
            public void run() {
                JDViewKitReversalDynamicBanner.this.selectNum();
            }
        };
        this.mHandler = new Handler();
        this.task = new Runnable() { // from class: com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal.JDViewKitReversalDynamicBanner.2
            @Override // java.lang.Runnable
            public void run() {
                if (!JDViewKitReversalDynamicBanner.this.autoPlay || JDViewKitReversalDynamicBanner.this.isUserTouch) {
                    return;
                }
                JDViewKitReversalDynamicBanner.this.startAin();
                JDViewKitReversalDynamicBanner.this.mHandler.postDelayed(this, JDViewKitReversalDynamicBanner.this.interval);
            }
        };
        this.list = new ArrayList();
    }
}
