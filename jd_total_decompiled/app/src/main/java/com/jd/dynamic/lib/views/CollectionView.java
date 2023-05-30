package com.jd.dynamic.lib.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.JDLoadingMoreLayout;
import com.handmark.pulltorefresh.library.LoadMoreListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IDarkChangeListener;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.views.CollectionView;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import rx.Observable;
import rx.functions.Action1;

/* loaded from: classes13.dex */
public class CollectionView extends FrameLayout implements IDarkChangeListener {
    public static final int SCROLLDIRECTION_HORIZONTAL = 1;
    public static final int SCROLLDIRECTION_VERTICAL = 0;
    private int A;
    private String B;
    private String C;
    private boolean D;
    private int E;
    private boolean F;
    private boolean G;

    /* renamed from: g */
    private JSONArray f2467g;

    /* renamed from: h */
    private JSONArray f2468h;

    /* renamed from: i */
    private ItemView f2469i;

    /* renamed from: j */
    private ItemView f2470j;

    /* renamed from: k */
    private RecyclerViewAdapter f2471k;

    /* renamed from: l */
    private RecyclerView f2472l;

    /* renamed from: m */
    private PullToRefreshLoadMoreRecyclerView f2473m;

    /* renamed from: n */
    private String f2474n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private final List<ItemView> u;
    private int v;
    private ViewNode w;
    private DynamicTemplateEngine x;
    private MyScrollListener y;
    private int z;

    /* renamed from: com.jd.dynamic.lib.views.CollectionView$1 */
    /* loaded from: classes13.dex */
    public class AnonymousClass1 implements PullToRefreshBase.OnRefreshListener2<RecyclerView> {
        AnonymousClass1() {
            CollectionView.this = r1;
        }

        public /* synthetic */ void a(String str) {
            CollectionView collectionView = CollectionView.this;
            com.jd.dynamic.lib.utils.s.b(str, collectionView, collectionView.x, CollectionView.this);
        }

        public static /* synthetic */ void b(Throwable th) {
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
        public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
            com.jd.dynamic.lib.utils.t.e("CollectionView - onRefresh");
            CollectionView.this.updateLoadRefreshState(2);
            Observable.from(com.jd.dynamic.lib.utils.s.i(CollectionView.this.C)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.a
                {
                    CollectionView.AnonymousClass1.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CollectionView.AnonymousClass1.this.a((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.views.b
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CollectionView.AnonymousClass1.b((Throwable) obj);
                }
            });
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
        public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        }
    }

    /* renamed from: com.jd.dynamic.lib.views.CollectionView$4 */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[JDLoadingMoreLayout.FooterState.values().length];
            b = iArr;
            try {
                iArr[JDLoadingMoreLayout.FooterState.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[JDLoadingMoreLayout.FooterState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[JDLoadingMoreLayout.FooterState.LOADING_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[JDLoadingMoreLayout.FooterState.LOADING_SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[JDLoadingMoreLayout.FooterState.REACH_END_INVISIBLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[JDLoadingMoreLayout.FooterState.REACH_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[PullToRefreshBase.State.values().length];
            a = iArr2;
            try {
                iArr2[PullToRefreshBase.State.REFRESHING.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[PullToRefreshBase.State.RESET.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[PullToRefreshBase.State.REFRESH_COMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public class GridItemDecoration extends RecyclerView.ItemDecoration {
        private int a;
        private int b;

        /* renamed from: c */
        private int f2476c;

        public GridItemDecoration(int i2, int i3, int i4) {
            CollectionView.this = r1;
            this.a = i2;
            this.f2476c = com.jd.dynamic.lib.viewparse.d.b(r1.getContext(), i4);
            this.b = com.jd.dynamic.lib.viewparse.d.b(r1.getContext(), i3);
        }

        private int a(RecyclerView recyclerView) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                return ((GridLayoutManager) layoutManager).getSpanCount();
            }
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            }
            return -1;
        }

        private void b(Rect rect, View view, int i2) {
            if (rect == null || view == null) {
                return;
            }
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            if (layoutParams.isFullSpan()) {
                rect.set(0, 0, 0, 0);
            } else if (layoutParams.getSpanIndex() % 2 == 0) {
                int i3 = this.f2476c;
                rect.set(i3, this.b, i3 / 2, 0);
            } else {
                int i4 = this.f2476c;
                rect.set(i4 / 2, this.b, i4, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            int i2;
            int i3;
            int i4;
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (this.a == -1) {
                this.a = a(recyclerView);
            }
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                b(rect, view, childAdapterPosition);
            } else if (layoutParams instanceof GridLayoutManager.LayoutParams) {
                if (((GridLayoutManager.LayoutParams) layoutParams).getSpanSize() == 1) {
                    int i5 = childAdapterPosition % CollectionView.this.o;
                    if (CollectionView.this.t == 0) {
                        int i6 = this.f2476c;
                        int i7 = this.a;
                        int i8 = (i5 * i6) / i7;
                        rect.left = i8;
                        int i9 = i6 - (((i5 + 1) * i6) / i7);
                        rect.right = i9;
                        if (childAdapterPosition >= i7 && (i4 = this.b) > 0) {
                            rect.top = i4;
                        }
                        ((ViewGroup.MarginLayoutParams) layoutParams).width = CollectionView.this.a(i9, i8);
                        return;
                    }
                    int i10 = this.b;
                    int i11 = this.a;
                    rect.top = (i5 * i10) / i11;
                    rect.bottom = i10 - (((i5 + 1) * i10) / i11);
                    if (childAdapterPosition < i11 || (i2 = this.f2476c) <= 0) {
                        return;
                    }
                } else if (CollectionView.this.t == 0) {
                    if (childAdapterPosition < this.a || (i3 = this.b) <= 0) {
                        return;
                    }
                    rect.top = i3;
                    return;
                } else if (childAdapterPosition < this.a || (i2 = this.f2476c) <= 0) {
                    return;
                }
                rect.left = i2;
            }
        }
    }

    /* loaded from: classes13.dex */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int a;
        private int b;

        /* renamed from: c */
        private int f2477c;

        public GridSpacingItemDecoration(int i2, int i3, int i4) {
            CollectionView.this = r1;
            this.a = i2;
            this.b = com.jd.dynamic.lib.viewparse.d.b(r1.getContext(), i3);
            this.f2477c = com.jd.dynamic.lib.viewparse.d.b(r1.getContext(), i4);
        }

        private void a(Rect rect, View view, int i2) {
            if (rect == null || view == null) {
                return;
            }
            StaggeredGridLayoutManager.LayoutParams layoutParams = view.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams ? (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams() : null;
            if (layoutParams.isFullSpan()) {
                rect.set(0, 0, 0, 0);
            } else if (layoutParams.getSpanIndex() % 2 == 0) {
                int i3 = this.b;
                rect.set(i3, this.f2477c, i3 / 2, 0);
            } else {
                int i4 = this.b;
                rect.set(i4 / 2, this.f2477c, i4, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            ViewGroup.MarginLayoutParams marginLayoutParams = view.getLayoutParams() instanceof RecyclerView.LayoutParams ? (RecyclerView.LayoutParams) view.getLayoutParams() : null;
            if (marginLayoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                a(rect, view, childAdapterPosition);
                return;
            }
            GridLayoutManager.LayoutParams layoutParams = marginLayoutParams instanceof GridLayoutManager.LayoutParams ? (GridLayoutManager.LayoutParams) marginLayoutParams : null;
            int spanSize = layoutParams.getSpanSize();
            int spanIndex = layoutParams.getSpanIndex();
            if (spanSize != 1) {
                if (childAdapterPosition == 0) {
                    marginLayoutParams.topMargin = this.f2477c;
                }
                marginLayoutParams.bottomMargin = this.f2477c;
                return;
            }
            int i2 = this.b;
            int i3 = this.a;
            rect.left = i2 - ((spanIndex * i2) / i3);
            rect.right = ((spanIndex + 1) * i2) / i3;
            marginLayoutParams.bottomMargin = this.f2477c;
            if (CollectionView.this.t == 0) {
                marginLayoutParams.width = CollectionView.this.a(rect.right, rect.left);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class LinearSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int a;

        public LinearSpacingItemDecoration(int i2) {
            this.a = DPIUtil.dip2px(i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                int orientation = ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation();
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (orientation == 1) {
                    rect.set(0, childAdapterPosition != 0 ? this.a : 0, 0, 0);
                } else {
                    rect.set(childAdapterPosition != 0 ? this.a : 0, 0, 0, 0);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class MyScrollListener extends RecyclerView.OnScrollListener {
        private String a;
        private String b;

        private MyScrollListener() {
            CollectionView.this = r1;
        }

        /* synthetic */ MyScrollListener(CollectionView collectionView, AnonymousClass1 anonymousClass1) {
            this();
        }

        public /* synthetic */ void a(String str) {
            CollectionView collectionView = CollectionView.this;
            com.jd.dynamic.lib.utils.s.b(str, collectionView, collectionView.x, CollectionView.this);
        }

        public static /* synthetic */ void b(Throwable th) {
        }

        public /* synthetic */ void c(String str) {
            CollectionView collectionView = CollectionView.this;
            com.jd.dynamic.lib.utils.s.b(str, collectionView, collectionView.x, CollectionView.this);
        }

        public static /* synthetic */ void d(Throwable th) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull final RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                    CollectionView collectionView = CollectionView.this;
                    recyclerView.getClass();
                    collectionView.post(new Runnable() { // from class: com.jd.dynamic.lib.views.c0
                        @Override // java.lang.Runnable
                        public final void run() {
                            recyclerView.invalidateItemDecorations();
                        }
                    });
                }
                if (TextUtils.isEmpty(this.b)) {
                    return;
                }
                Observable.from(com.jd.dynamic.lib.utils.s.i(this.b)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.c
                    {
                        CollectionView.MyScrollListener.this = this;
                    }

                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        CollectionView.MyScrollListener.this.a((String) obj);
                    }
                }, new Action1() { // from class: com.jd.dynamic.lib.views.f
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        CollectionView.MyScrollListener.b((Throwable) obj);
                    }
                });
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            CollectionView.this.z = recyclerView.computeVerticalScrollOffset();
            CollectionView.this.A += i2;
            if (TextUtils.isEmpty(this.a)) {
                return;
            }
            Observable.from(com.jd.dynamic.lib.utils.s.i(this.a)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.d
                {
                    CollectionView.MyScrollListener.this = this;
                }

                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CollectionView.MyScrollListener.this.c((String) obj);
                }
            }, new Action1() { // from class: com.jd.dynamic.lib.views.e
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CollectionView.MyScrollListener.d((Throwable) obj);
                }
            });
        }

        public void setScrollEndEvent(String str) {
            this.b = str;
        }

        public void setScrollEvent(String str) {
            this.a = str;
        }
    }

    public CollectionView(@NonNull Context context, ViewNode viewNode) {
        super(context, null, 0);
        this.t = 0;
        this.u = new ArrayList();
        this.v = 0;
        this.z = 0;
        this.A = 0;
        this.E = 0;
        this.F = false;
        this.G = false;
        this.w = viewNode;
        this.D = h();
        e(context);
        if (DynamicSdk.getEngine().getDynamicDark() != null) {
            this.G = DynamicSdk.getEngine().getDynamicDark().isDarkMode();
        }
    }

    public int a(int i2, int i3) {
        return (int) (((((getContext() instanceof Activity ? DPIUtil.getAppWidth((Activity) getContext()) : DPIUtil.getWidth(getContext())) - getParentOutWidth(getRecyclerView())) / this.o) - i3) - i2);
    }

    private void e(Context context) {
        View view;
        AnonymousClass1 anonymousClass1 = null;
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView = (PullToRefreshLoadMoreRecyclerView) View.inflate(context, R.layout.recycle_view_layout, null);
        this.f2473m = pullToRefreshLoadMoreRecyclerView;
        if (this.D) {
            this.f2472l = pullToRefreshLoadMoreRecyclerView.getRefreshableView();
            view = this.f2473m;
        } else {
            RecyclerView recyclerView = new RecyclerView(context);
            this.f2472l = recyclerView;
            view = recyclerView;
        }
        this.f2472l.setId(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        this.f2471k = recyclerViewAdapter;
        recyclerViewAdapter.setHasStableIds(true);
        this.f2472l.setAdapter(this.f2471k);
        this.f2472l.setItemAnimator(null);
        this.y = new MyScrollListener(this, anonymousClass1);
        this.f2472l.setNestedScrollingEnabled(false);
        this.f2472l.addOnScrollListener(this.y);
        super.addView(view, new FrameLayout.LayoutParams(-1, -1));
        this.f2473m.setOnRefreshListener(new AnonymousClass1());
        this.f2473m.setOnLoadMoreListener(new LoadMoreListener() { // from class: com.jd.dynamic.lib.views.CollectionView.2
            {
                CollectionView.this = this;
            }

            @Override // com.handmark.pulltorefresh.library.LoadMoreListener
            public void loadMore() {
                CollectionView.this.loadMoreData();
            }
        });
    }

    public /* synthetic */ void f(String str) {
        com.jd.dynamic.lib.utils.s.b(str, this, this.x, this);
    }

    public static /* synthetic */ void g(Throwable th) {
    }

    private boolean h() {
        ViewNode viewNode = this.w;
        return viewNode != null && (viewNode.getAttributes().containsKey("onLoadRefresh") || this.w.getAttributes().containsKey("onLoadMore") || this.w.getELAttributes().containsKey("onLoadRefresh") || this.w.getELAttributes().containsKey("onLoadMore"));
    }

    private boolean k() {
        return !TextUtils.isEmpty(this.C);
    }

    private JDLoadingMoreLayout q() {
        RecyclerViewAdapter recyclerViewAdapter = this.f2471k;
        if (recyclerViewAdapter == null) {
            return null;
        }
        int itemCount = recyclerViewAdapter.getItemCount() - 1;
        if (itemCount >= 0 && (this.f2472l.getLayoutManager() instanceof LinearLayoutManager)) {
            View findViewByPosition = ((LinearLayoutManager) this.f2472l.getLayoutManager()).findViewByPosition(itemCount);
            if (findViewByPosition instanceof JDLoadingMoreLayout) {
                JDLoadingMoreLayout jDLoadingMoreLayout = (JDLoadingMoreLayout) findViewByPosition;
                jDLoadingMoreLayout.setAutoDarkMode(this.f2473m.getAutoDark() != null && this.f2473m.getAutoDark().booleanValue());
                jDLoadingMoreLayout.refreshTheme();
                return jDLoadingMoreLayout;
            }
        }
        return null;
    }

    public void addItemView(ItemView itemView) {
        this.u.add(itemView);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
    }

    public void attachEngine(DynamicTemplateEngine dynamicTemplateEngine) {
        this.x = dynamicTemplateEngine;
    }

    /* JADX WARN: Removed duplicated region for block: B:213:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x01b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void finishSetting() {
        JSONArray jSONArray;
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView;
        PullToRefreshBase.Mode mode;
        int i2;
        RecyclerView recyclerView;
        LinearSpacingItemDecoration linearSpacingItemDecoration;
        RecyclerView recyclerView2;
        RecyclerView.ItemDecoration gridSpacingItemDecoration;
        RecyclerView recyclerView3;
        RecyclerView.ItemDecoration gridSpacingItemDecoration2;
        boolean isDarkMode = DynamicSdk.getEngine().getDynamicDark() != null ? DynamicSdk.getEngine().getDynamicDark().isDarkMode() : false;
        if (com.jd.dynamic.b.a.b.o().c()) {
            this.f2472l.setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            setPadding(0, 0, 0, 0);
            this.f2472l.setClipToPadding(this.F);
        }
        boolean z = this.f2472l.getLayoutManager() != null;
        JSONArray jSONArray2 = this.f2467g;
        if (jSONArray2 != null && jSONArray2.length() > 0) {
            if (isDarkMode != this.G || this.f2468h == null || !TextUtils.equals(com.jd.dynamic.lib.utils.p.a(this.f2467g.toString()), com.jd.dynamic.lib.utils.p.a(this.f2468h.toString())) || getTag(R.id.dynamic_recyclerview_is_item) != null || com.jd.dynamic.lib.utils.m.c(this) != null) {
                this.G = isDarkMode;
                jSONArray = this.f2467g;
            }
            if (com.jd.dynamic.lib.utils.m.K(this.f2467g) && !z) {
                if (!"Grid".equals(this.f2474n)) {
                    if (this.o <= 0) {
                        this.o = getSpanCount();
                    }
                    com.jd.dynamic.lib.utils.j jVar = new com.jd.dynamic.lib.utils.j(getContext(), this.o);
                    this.f2471k.setSpanCount(this.o);
                    boolean z2 = this.r > 0 || this.s > 0;
                    if (com.jd.dynamic.b.a.b.o().c() && z2) {
                        recyclerView3 = this.f2472l;
                        gridSpacingItemDecoration2 = new GridItemDecoration(this.o, this.s, this.r);
                    } else {
                        recyclerView3 = this.f2472l;
                        gridSpacingItemDecoration2 = new GridSpacingItemDecoration(this.o, this.p, this.q);
                    }
                    recyclerView3.addItemDecoration(gridSpacingItemDecoration2);
                    jVar.setOrientation(this.t != 1 ? 1 : 0);
                    this.f2472l.setLayoutManager(jVar);
                    jVar.setSpanSizeLookup(this.f2471k.getSpanSizeLookUp());
                } else if ("Staggered".equals(this.f2474n)) {
                    com.jd.dynamic.lib.utils.l lVar = new com.jd.dynamic.lib.utils.l(this.o, 1);
                    this.f2471k.setSpanCount(this.o);
                    i2 = (this.r > 0 || this.s > 0) ? 1 : 0;
                    if (!com.jd.dynamic.b.a.b.o().c() || i2 == 0) {
                        recyclerView2 = this.f2472l;
                        gridSpacingItemDecoration = new GridSpacingItemDecoration(this.o, this.p, this.q);
                    } else {
                        recyclerView2 = this.f2472l;
                        gridSpacingItemDecoration = new GridItemDecoration(this.o, this.s, this.r);
                    }
                    recyclerView2.addItemDecoration(gridSpacingItemDecoration);
                    this.f2472l.setLayoutManager(lVar);
                } else {
                    com.jd.dynamic.lib.utils.k kVar = new com.jd.dynamic.lib.utils.k(getContext());
                    kVar.setOrientation(this.t == 1 ? 0 : 1);
                    this.f2472l.setLayoutManager(kVar);
                    i2 = (this.r > 0 || this.s > 0) ? 1 : 0;
                    if (!com.jd.dynamic.b.a.b.o().c() || i2 == 0) {
                        recyclerView = this.f2472l;
                        linearSpacingItemDecoration = new LinearSpacingItemDecoration(this.q);
                    } else {
                        recyclerView = this.f2472l;
                        linearSpacingItemDecoration = new LinearSpacingItemDecoration(this.s);
                    }
                    recyclerView.addItemDecoration(linearSpacingItemDecoration);
                }
            }
            if (TextUtils.isEmpty(this.C)) {
                pullToRefreshLoadMoreRecyclerView = this.f2473m;
                mode = PullToRefreshBase.Mode.PULL_FROM_START;
            } else {
                pullToRefreshLoadMoreRecyclerView = this.f2473m;
                mode = PullToRefreshBase.Mode.DISABLED;
            }
            pullToRefreshLoadMoreRecyclerView.setMode(mode);
        }
        jSONArray = null;
        this.f2468h = jSONArray;
        this.f2471k.updateData(z);
        if (com.jd.dynamic.lib.utils.m.K(this.f2467g)) {
            if (!"Grid".equals(this.f2474n)) {
            }
        }
        if (TextUtils.isEmpty(this.C)) {
        }
        pullToRefreshLoadMoreRecyclerView.setMode(mode);
    }

    public JSONArray getData() {
        return this.f2467g;
    }

    public DynamicTemplateEngine getEngine() {
        return this.x;
    }

    public ItemView getHeaderView() {
        if (this.f2469i == null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.u.size()) {
                    break;
                }
                ItemView itemView = this.u.get(i2);
                if (itemView.getTag() != null && ((Integer) itemView.getTag()).intValue() == 2000) {
                    this.f2469i = itemView;
                    break;
                }
                i2++;
            }
        }
        return this.f2469i;
    }

    public ItemView getItemViewFromId(int i2, Map<String, Integer> map) {
        ViewNode viewNode;
        Integer num;
        List<ItemView> list = this.u;
        if (list == null || list.size() == 0) {
            return null;
        }
        for (ItemView itemView : this.u) {
            if (((Integer) itemView.getTag()).intValue() == 1000 && ((viewNode = itemView.mine) == null || viewNode.getAttributes() == null || itemView.mine.getAttributes().size() <= 0 || TextUtils.isEmpty(itemView.mine.getAttributes().get(DYConstants.DY_IDENTIFIER)) || ((num = map.get(itemView.mine.getAttributes().get(DYConstants.DY_IDENTIFIER))) != null && i2 == num.intValue()))) {
                return itemView;
            }
        }
        return this.u.get(0);
    }

    public int getLoadMoreState() {
        int i2 = this.E;
        if (i2 != 0) {
            return i2;
        }
        if (!hasLoadMore() || this.f2473m == null || q() == null) {
            return 0;
        }
        int i3 = AnonymousClass4.b[q().getFooterState().ordinal()];
        if (i3 != 2) {
            if (i3 != 3) {
                if (i3 != 4) {
                    return (i3 == 5 || i3 == 6) ? 4 : 0;
                }
                return 1;
            }
            return 3;
        }
        return 2;
    }

    public int getLoadRefreshState() {
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView;
        if (!k() || (pullToRefreshLoadMoreRecyclerView = this.f2473m) == null) {
            return 0;
        }
        int i2 = AnonymousClass4.a[pullToRefreshLoadMoreRecyclerView.getState().ordinal()];
        if (i2 != 1) {
            return (i2 == 2 || i2 == 3) ? 1 : 0;
        }
        return 2;
    }

    public int getMaxCount() {
        return this.v;
    }

    public ViewNode getMineNode() {
        return this.w;
    }

    public int getParentOutWidth(View view) {
        if (view != null) {
            int paddingLeft = view.getPaddingLeft() + 0 + view.getPaddingRight() + 0;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                paddingLeft += marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
            return (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) ? paddingLeft : paddingLeft + getParentOutWidth((ViewGroup) view.getParent());
        }
        return 0;
    }

    public RecyclerView getRecyclerView() {
        return this.f2472l;
    }

    public PullToRefreshLoadMoreRecyclerView getRefreshView() {
        return this.f2473m;
    }

    public int getScrollDirection() {
        return this.t;
    }

    public int getSpanCount() {
        ItemView itemView = this.f2470j;
        if (itemView != null) {
            View parse = itemView.parse();
            if (parse instanceof YogaLayout) {
                return ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getWidth() / ((ViewGroup.MarginLayoutParams) ((YogaLayout) parse).getYogaLayoutLayoutParams()).width;
            }
            return 4;
        }
        return 4;
    }

    public boolean hasHeader() {
        return getHeaderView() != null;
    }

    public boolean hasLoadMore() {
        return !TextUtils.isEmpty(this.B);
    }

    public int horizontalScrolledDistance() {
        return DPIUtil.px2dip(this.A);
    }

    public void loadMoreData() {
        if (!hasLoadMore() || getLoadMoreState() == 2 || getLoadMoreState() == 4) {
            return;
        }
        updateLoadMoreState(2);
        Observable.from(com.jd.dynamic.lib.utils.s.i(this.B)).forEach(new Action1() { // from class: com.jd.dynamic.lib.views.h
            {
                CollectionView.this = this;
            }

            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionView.this.f((String) obj);
            }
        }, new Action1() { // from class: com.jd.dynamic.lib.views.g
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CollectionView.g((Throwable) obj);
            }
        });
        com.jd.dynamic.lib.utils.t.e("CollectionView - loadMore");
    }

    @Override // com.jd.dynamic.base.interfaces.IDarkChangeListener
    public void onDarkChange(boolean z) {
        this.G = z;
        RecyclerView recyclerView = this.f2472l;
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return;
        }
        this.f2472l.getAdapter().notifyDataSetChanged();
    }

    public void scrollToPosition(int i2) {
        RecyclerView recyclerView = this.f2472l;
        if (recyclerView != null) {
            recyclerView.scrollToPosition(i2);
            this.f2472l.post(new Runnable() { // from class: com.jd.dynamic.lib.views.CollectionView.3
                {
                    CollectionView.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (CollectionView.this.y != null) {
                        CollectionView.this.y.onScrollStateChanged(CollectionView.this.f2472l, 0);
                    }
                }
            });
        }
    }

    public void serClipToPadding(boolean z) {
        this.F = z;
    }

    public void setColumnSpacing(int i2) {
        this.r = i2;
    }

    public void setData(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f2467g = null;
            return;
        }
        try {
            if (DynamicUtils.isElOrKnownSymbol(str)) {
                this.f2467g = null;
            } else {
                this.f2467g = new JSONArray(str);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void setLayoutType(String str) {
        this.f2474n = str;
    }

    public void setMaxCount(int i2) {
        this.v = i2;
    }

    public void setMinimumInteritemSpacing(int i2) {
        this.p = i2;
    }

    public void setMinimumLineSpacing(int i2) {
        this.q = i2;
    }

    public void setOnLoadMoreEvent(String str) {
        this.B = str;
    }

    public void setOnLoadRefreshEvent(String str) {
        this.C = str;
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.f2472l.setOnTouchListener(onTouchListener);
    }

    public void setRecyclerViewScrollEndListener(String str) {
        MyScrollListener myScrollListener;
        if (this.f2472l == null || (myScrollListener = this.y) == null) {
            return;
        }
        myScrollListener.setScrollEndEvent(str);
    }

    public void setRecyclerViewScrollListener(String str) {
        MyScrollListener myScrollListener;
        if (this.f2472l == null || (myScrollListener = this.y) == null) {
            return;
        }
        myScrollListener.setScrollEvent(str);
    }

    public void setRowSpacing(int i2) {
        this.s = i2;
    }

    public void setScrollDirection(int i2) {
        this.t = i2;
    }

    public void setSpanCount(int i2) {
        this.o = i2;
    }

    public void updateLoadMoreState(int i2) {
        int itemCount;
        RecyclerViewAdapter recyclerViewAdapter = this.f2471k;
        if (recyclerViewAdapter != null && (itemCount = recyclerViewAdapter.getItemCount() - 1) >= 0) {
            this.f2471k.notifyItemRangeChanged(itemCount, 1, Integer.valueOf(i2));
            this.E = i2;
        }
    }

    public void updateLoadRefreshState(int i2) {
        PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView;
        if (!k() || (pullToRefreshLoadMoreRecyclerView = this.f2473m) == null) {
            return;
        }
        if (i2 != 2) {
            pullToRefreshLoadMoreRecyclerView.onRefreshComplete();
        } else {
            pullToRefreshLoadMoreRecyclerView.setRefreshing();
        }
    }

    public int verticalScrolledDistance() {
        int i2 = this.z;
        if (i2 < 0) {
            this.z = 0;
            return 0;
        }
        return DPIUtil.px2dip(i2);
    }
}
