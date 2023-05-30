package com.jd.lib.flexcube.layout.floor.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.help.MsgActionInterface;
import com.jd.lib.flexcube.help.MsgInterface;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.ScrollMoreClickEvent;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ScrollConfig;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.scroll.FlexMoreRecyclerView;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexScrollLayout extends FrameLayout implements FlexMoreRecyclerView.d, FlexAutoPlayInterface, MsgInterface {
    public static int N = 100;
    private com.jd.lib.flexcube.widgets.b.e A;
    private FlexMoreRecyclerView B;
    private LinearLayoutManager C;
    private MyRecyclerAdapter D;
    private ItemDecoration E;
    int F;
    private float G;
    public boolean H;
    private int I;
    private boolean J;
    private boolean K;
    private e L;
    private int[] M;

    /* renamed from: g  reason: collision with root package name */
    private Context f4403g;

    /* renamed from: h  reason: collision with root package name */
    private BabelScope f4404h;

    /* renamed from: i  reason: collision with root package name */
    private FlexCubeModel f4405i;

    /* renamed from: j  reason: collision with root package name */
    private float f4406j;

    /* renamed from: k  reason: collision with root package name */
    private List<BaseWidgetEntity> f4407k;

    /* renamed from: l  reason: collision with root package name */
    private String f4408l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f4409m;

    /* renamed from: n  reason: collision with root package name */
    private ScrollMoreClickEvent f4410n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    public List<MaterialModel> w;
    private ImageView x;
    private FlexScrollBar y;
    public int z;

    /* loaded from: classes14.dex */
    public class ItemDecoration extends RecyclerView.ItemDecoration {
        public ItemDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (childAdapterPosition > 0) {
                rect.left = FlexScrollLayout.this.q;
            } else {
                rect.left = FlexScrollLayout.this.s;
            }
            List<MaterialModel> list = FlexScrollLayout.this.w;
            if (list != null) {
                int size = list.size();
                FlexScrollLayout flexScrollLayout = FlexScrollLayout.this;
                int i2 = (size / flexScrollLayout.z) - 1;
                if (flexScrollLayout.f4409m) {
                    i2++;
                }
                if (childAdapterPosition == i2) {
                    rect.right = FlexScrollLayout.this.t;
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    public class MyRecyclerAdapter extends RecyclerView.Adapter {
        private View a;
        private int b = 8;

        /* loaded from: classes14.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FlexScrollLayout.this.a(view);
            }
        }

        /* loaded from: classes14.dex */
        class b extends RecyclerView.ViewHolder {
            public FlexScrollColumn a;

            public b(@NonNull MyRecyclerAdapter myRecyclerAdapter, FlexScrollColumn flexScrollColumn) {
                super(flexScrollColumn);
                this.a = flexScrollColumn;
            }
        }

        /* loaded from: classes14.dex */
        class c extends RecyclerView.ViewHolder {
            public FlexScrollColumn a;

            public c(@NonNull MyRecyclerAdapter myRecyclerAdapter, FlexScrollColumn flexScrollColumn) {
                super(flexScrollColumn);
                this.a = flexScrollColumn;
            }
        }

        /* loaded from: classes14.dex */
        class d extends RecyclerView.ViewHolder {
            public FlexScrollColumn a;

            public d(@NonNull MyRecyclerAdapter myRecyclerAdapter, FlexScrollColumn flexScrollColumn) {
                super(flexScrollColumn);
                this.a = flexScrollColumn;
            }
        }

        /* loaded from: classes14.dex */
        class e extends RecyclerView.ViewHolder {
            public FlexScrollColumn a;

            public e(@NonNull MyRecyclerAdapter myRecyclerAdapter, FlexScrollColumn flexScrollColumn) {
                super(flexScrollColumn);
                this.a = flexScrollColumn;
            }
        }

        /* loaded from: classes14.dex */
        class f extends RecyclerView.ViewHolder {
            public FlexScrollMore a;

            public f(@NonNull MyRecyclerAdapter myRecyclerAdapter, FlexScrollMore flexScrollMore) {
                super(flexScrollMore);
                this.a = flexScrollMore;
            }
        }

        public MyRecyclerAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            FlexScrollLayout flexScrollLayout = FlexScrollLayout.this;
            if (flexScrollLayout.w == null) {
                return 0;
            }
            return (FlexScrollLayout.this.w.size() / FlexScrollLayout.this.z) + (flexScrollLayout.f4409m ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            List<MaterialModel> list = FlexScrollLayout.this.w;
            if (list == null) {
                return 1;
            }
            int size = list.size();
            int i3 = FlexScrollLayout.this.z;
            return i2 < size / i3 ? i3 : this.b;
        }

        public View getView() {
            return this.a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
            List<MaterialModel> list = FlexScrollLayout.this.w;
            if (list == null) {
                return;
            }
            if (viewHolder instanceof b) {
                int i3 = i2 * 1;
                int i4 = i3 + 1;
                if (i4 > list.size()) {
                    return;
                }
                ((b) viewHolder).a.updata(FlexScrollLayout.this.w.subList(i3, i4));
            } else if (viewHolder instanceof c) {
                int i5 = i2 * 2;
                int i6 = i5 + 2;
                if (i6 > list.size()) {
                    return;
                }
                ((c) viewHolder).a.updata(FlexScrollLayout.this.w.subList(i5, i6));
            } else if (viewHolder instanceof d) {
                int i7 = i2 * 3;
                int i8 = i7 + 3;
                if (i8 > list.size()) {
                    return;
                }
                ((d) viewHolder).a.updata(FlexScrollLayout.this.w.subList(i7, i8));
            } else if (viewHolder instanceof e) {
                int i9 = i2 * 4;
                int i10 = i9 + 4;
                if (i10 > list.size()) {
                    return;
                }
                ((e) viewHolder).a.updata(FlexScrollLayout.this.w.subList(i9, i10));
            } else if (viewHolder instanceof f) {
                ((f) viewHolder).a.setLayoutParams(new RecyclerView.LayoutParams(FlexScrollLayout.N, FlexScrollLayout.this.v));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            if (i2 == this.b) {
                FlexScrollMore flexScrollMore = new FlexScrollMore(FlexScrollLayout.this.f4403g);
                flexScrollMore.setLayoutParams(new RecyclerView.LayoutParams(FlexScrollLayout.N, FlexScrollLayout.this.v));
                flexScrollMore.setOnClickListener(new a());
                FlexScrollLayout.this.D();
                return new f(this, flexScrollMore);
            }
            FlexScrollColumn flexScrollColumn = new FlexScrollColumn(FlexScrollLayout.this.f4403g, i2, FlexScrollLayout.this.f4408l, FlexScrollLayout.this.f4404h, FlexScrollLayout.this.f4405i);
            flexScrollColumn.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
            int i3 = FlexScrollLayout.this.z;
            if (i3 == 1) {
                return new b(this, flexScrollColumn);
            }
            if (i3 == 2) {
                return new c(this, flexScrollColumn);
            }
            if (i3 == 3) {
                return new d(this, flexScrollColumn);
            }
            return new e(this, flexScrollColumn);
        }

        public void setView(View view) {
            this.a = view;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements FlexMoreRecyclerView.c {
        a() {
        }

        @Override // com.jd.lib.flexcube.layout.floor.scroll.FlexMoreRecyclerView.c
        public void a() {
            if (FlexScrollLayout.this.K && FlexScrollLayout.this.I == 2) {
                FlexScrollLayout.this.I = 0;
                FlexScrollLayout.this.J = true;
                FlexScrollLayout flexScrollLayout = FlexScrollLayout.this;
                flexScrollLayout.postDelayed(flexScrollLayout.z(), 1000L);
            }
        }

        @Override // com.jd.lib.flexcube.layout.floor.scroll.FlexMoreRecyclerView.c
        public void stop() {
            if (FlexScrollLayout.this.K && FlexScrollLayout.this.I == 0) {
                FlexScrollLayout.this.I = 2;
                FlexScrollLayout.this.J = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FlexScrollLayout.this.B.scrollBy(FlexScrollLayout.this.f4405i.scrollXNoSerialize, 0);
            FlexScrollLayout.this.F();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends RecyclerView.OnScrollListener {
        c() {
        }

        public int a(int i2, int i3, RecyclerView recyclerView, int i4) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition != null) {
                int left = ((findFirstVisibleItemPosition * (FlexScrollLayout.this.o + FlexScrollLayout.this.q)) + FlexScrollLayout.this.s) - findViewByPosition.getLeft();
                if (left > i4) {
                    return i4;
                }
                if (left < 0) {
                    return 0;
                }
                return left;
            }
            int i5 = i2 + i3;
            if (i5 > i4 || !recyclerView.canScrollHorizontally(1)) {
                return i4;
            }
            if (i5 < 0 || !recyclerView.canScrollHorizontally(-1)) {
                return 0;
            }
            return i5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            if (FlexScrollLayout.this.f4405i == null || FlexScrollLayout.this.y == null || FlexScrollLayout.this.y.b() == null) {
                return;
            }
            int i4 = FlexScrollLayout.this.s + FlexScrollLayout.this.o;
            int size = FlexScrollLayout.this.w.size();
            int i5 = i4 + (((size / r1.z) - 1) * (FlexScrollLayout.this.o + FlexScrollLayout.this.q)) + (FlexScrollLayout.this.f4409m ? FlexScrollLayout.N + FlexScrollLayout.this.q + FlexScrollLayout.this.t : FlexScrollLayout.this.t);
            int width = FlexScrollLayout.this.B.getWidth();
            int i6 = i5 - width;
            if (i5 <= 0 || width <= 0 || i6 <= 0 || FlexScrollLayout.this.y.getWidth() <= 0 || FlexScrollLayout.this.y.b().getWidth() <= 0) {
                return;
            }
            FlexScrollLayout flexScrollLayout = FlexScrollLayout.this;
            flexScrollLayout.F = a(flexScrollLayout.F, i2, recyclerView, i6);
            FlexScrollLayout flexScrollLayout2 = FlexScrollLayout.this;
            int i7 = flexScrollLayout2.F;
            float f2 = i6;
            flexScrollLayout2.G = (((float) i7) * 1.0f) / f2 <= 1.0f ? (i7 * 1.0f) / f2 : 1.0f;
            FlexScrollLayout.this.y.b().setTranslationX((((FlexScrollLayout.this.y.getWidth() - FlexScrollLayout.this.y.getPaddingLeft()) - FlexScrollLayout.this.y.getPaddingRight()) - FlexScrollLayout.this.y.b().getWidth()) * FlexScrollLayout.this.G);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            int A;
            if (FlexScrollLayout.this.y.b() != null) {
                if (FlexScrollLayout.this.K && FlexScrollLayout.this.f4405i != null && FlexScrollLayout.this.f4405i.scrollXNoSerialize >= 0 && (A = FlexScrollLayout.this.A()) > 0) {
                    FlexScrollLayout.this.G = Math.min((r1.f4405i.scrollXNoSerialize * 1.0f) / A, 1.0f);
                }
                FlexScrollLayout.this.y.b().setTranslationX((((FlexScrollLayout.this.y.getWidth() - FlexScrollLayout.this.y.getPaddingLeft()) - FlexScrollLayout.this.y.getPaddingRight()) - FlexScrollLayout.this.y.b().getWidth()) * FlexScrollLayout.this.G);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final WeakReference<FlexMoreRecyclerView> f4415g;

        public e(FlexMoreRecyclerView flexMoreRecyclerView) {
            this.f4415g = new WeakReference<>(flexMoreRecyclerView);
        }

        @Override // java.lang.Runnable
        public void run() {
            FlexMoreRecyclerView flexMoreRecyclerView = this.f4415g.get();
            if (flexMoreRecyclerView == null) {
                return;
            }
            if (!flexMoreRecyclerView.canScrollHorizontally(1)) {
                FlexScrollLayout.this.I = 1;
                FlexScrollLayout.this.J = false;
            } else if (FlexScrollLayout.this.I == 0 && FlexScrollLayout.this.J && FlexScrollLayout.this.K) {
                flexMoreRecyclerView.scrollBy(2, 0);
                flexMoreRecyclerView.postDelayed(this, 32L);
            }
        }
    }

    public FlexScrollLayout(@NonNull Context context) {
        super(context);
        this.z = 2;
        this.I = 0;
        this.M = new int[2];
        this.f4403g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.x = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.x);
        this.A = new com.jd.lib.flexcube.widgets.b.e(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int A() {
        if (this.B == null) {
            return 0;
        }
        int i2 = this.o;
        int i3 = this.q;
        return (((this.s + this.o) + (((this.w.size() / this.z) - 1) * (i2 + i3))) + (this.f4409m ? (N + i3) + this.t : this.t)) - this.B.getWidth();
    }

    private void B() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        if (this.I == 2) {
            this.I = 0;
        }
        if (this.J || this.I != 0) {
            return;
        }
        this.J = true;
        postDelayed(z(), 1000L);
    }

    private void G() {
        if (this.I == 0 || this.J) {
            this.I = 2;
            this.J = false;
            String.valueOf(this.B.getScrollX());
            removeCallbacks(this.L);
        }
        FlexCubeModel flexCubeModel = this.f4405i;
        if (flexCubeModel != null) {
            flexCubeModel.scrollXNoSerialize = this.F;
            flexCubeModel.stopStatusNoSerialize = this.I;
        }
    }

    private void I(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, int i2) {
        FloorConfig floorConfig;
        ScrollConfig scrollConfig;
        if (this.B == null || flexCubeModel == null || (floorConfig = flexCubeModel.floorConfig) == null || (scrollConfig = floorConfig.scrollConfig) == null || !"1".equals(scrollConfig.showBar)) {
            return;
        }
        FlexScrollBar flexScrollBar = this.y;
        if (flexScrollBar == null) {
            FlexScrollBar flexScrollBar2 = new FlexScrollBar(this.f4403g, flexCubeModel);
            this.y = flexScrollBar2;
            addView(flexScrollBar2);
            this.y.update(babelScope, flexCubeModel, i2);
            this.B.addOnScrollListener(new c());
            return;
        }
        flexScrollBar.update(babelScope, flexCubeModel, i2);
        this.y.post(new d());
    }

    private void J(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && com.jd.lib.flexcube.iwidget.b.c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.x.setVisibility(0);
                this.x.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.x).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.x.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.x.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.x.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.x.setVisibility(8);
        setBackgroundColor(0);
    }

    private void K(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2) {
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                this.s = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2);
                this.t = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2);
                this.u = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.topPadding, f2);
            } else {
                this.s = 0;
                this.u = 0;
            }
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                this.q = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardHPadding, f2);
                this.r = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardVPadding, f2);
            } else {
                this.q = 0;
                this.r = 0;
            }
            if (canvasConfig != null) {
                this.o = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.w, f2);
                this.p = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.f4223h, f2);
            } else {
                this.o = 0;
                this.p = 0;
            }
            int i2 = this.p;
            int i3 = this.z;
            this.v = (i2 * i3) + (this.r * (i3 - 1));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.v);
            layoutParams.topMargin = this.u;
            this.B.setLayoutParams(layoutParams);
            return;
        }
        B();
    }

    private void L() {
        if (this.K) {
            F();
        }
    }

    private int x(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(this.M);
            int[] iArr = this.M;
            return Math.min(iArr[0], iArr[1]);
        }
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    private int y(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(this.M);
            int[] iArr = this.M;
            return Math.max(iArr[0], iArr[1]);
        }
        return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public e z() {
        if (this.L == null) {
            this.L = new e(this.B);
        }
        return this.L;
    }

    public void C(String str) {
        if (this.B == null) {
            FlexMoreRecyclerView flexMoreRecyclerView = new FlexMoreRecyclerView(this.f4403g);
            this.B = flexMoreRecyclerView;
            flexMoreRecyclerView.c(new a());
            this.B.setFocusable(false);
            this.B.d(this);
            MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter();
            this.D = myRecyclerAdapter;
            this.B.setAdapter(myRecyclerAdapter);
            this.D.setView(this.B);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.f4403g, 0, false);
            this.C = linearLayoutManager;
            this.B.setLayoutManager(linearLayoutManager);
            ItemDecoration itemDecoration = new ItemDecoration();
            this.E = itemDecoration;
            this.B.addItemDecoration(itemDecoration);
            addView(this.B, new ViewGroup.LayoutParams(-1, -2));
        }
    }

    public void D() {
        ScrollMoreClickEvent scrollMoreClickEvent;
        ExposureInfo exposureInfo;
        BabelScope babelScope;
        if (!this.f4409m || (scrollMoreClickEvent = this.f4410n) == null || (exposureInfo = scrollMoreClickEvent.exposureInfo) == null || (babelScope = this.f4404h) == null || babelScope.iFloorUI == null || TextUtils.isEmpty(exposureInfo.eventId) || TextUtils.isEmpty(exposureInfo.srv)) {
            return;
        }
        try {
            this.f4404h.iFloorUI.sendExposureData(MtaData.Builder.from(exposureInfo.eventId, exposureInfo.srv).jsonParam(exposureInfo.srvData).split(true).build());
        } catch (Exception unused) {
        }
    }

    public void E(CfInfo cfInfo, float f2) {
        this.A.i(cfInfo, f2);
    }

    public void H(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (this.J) {
            G();
        }
        if (this.B != null && flexCubeModel != null && flexCubeModel.widgetList != null && flexCubeModel.canvasConfig != null && (floorConfig = flexCubeModel.floorConfig) != null) {
            if (floorConfig.w >= 1) {
                this.f4404h = babelScope;
                this.f4405i = flexCubeModel;
                this.f4406j = flexCubeModel.getMultiple();
                ScrollConfig scrollConfig = this.f4405i.floorConfig.scrollConfig;
                this.K = scrollConfig != null && scrollConfig.canAutoPlay();
                this.f4407k = flexCubeModel.widgetList;
                this.f4408l = flexCubeModel.canvasConfig.getUuid();
                this.f4409m = "1".equals(flexCubeModel.floorConfig.showMore);
                FloorConfig floorConfig2 = flexCubeModel.floorConfig;
                this.f4410n = floorConfig2.showMoreClickEvent;
                int i3 = floorConfig2.rows;
                int i4 = i3 >= 1 ? i3 : 1;
                this.z = i4;
                int i5 = FlexScrollFloor.o;
                if (i4 > i5) {
                    i4 = i5;
                }
                this.z = i4;
                FlexScrollFloor.c(list, i4);
                this.z = i4;
                this.B.setGoRedirect(this.f4409m);
                J(flexCubeModel.floorConfig.viewStyle, babelScope);
                K(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.f4406j);
                this.w = list;
                this.D.notifyDataSetChanged();
                if (this.H) {
                    I(babelScope, flexCubeModel, i2);
                }
                if (this.K) {
                    FlexCubeModel flexCubeModel2 = this.f4405i;
                    this.I = flexCubeModel2.stopStatusNoSerialize;
                    if (flexCubeModel2.scrollXNoSerialize > 0) {
                        postDelayed(new b(), 100L);
                        return;
                    } else {
                        F();
                        return;
                    }
                }
                return;
            }
        }
        B();
    }

    @Override // com.jd.lib.flexcube.layout.floor.scroll.FlexMoreRecyclerView.d
    public void a(View view) {
        ScrollMoreClickEvent scrollMoreClickEvent;
        if (this.f4409m && (scrollMoreClickEvent = this.f4410n) != null && "jump".equals(scrollMoreClickEvent.type)) {
            com.jd.lib.flexcube.widgets.b.a.a(this.f4403g, view, this.f4410n, this.f4404h);
        }
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        int x = x(this.C);
        int y = y(this.C);
        if (x >= 0 && y >= 0) {
            while (x <= y) {
                if (this.C.findViewByPosition(x) instanceof FlexAutoPlayInterface) {
                    View findViewByPosition = this.C.findViewByPosition(x);
                    if (-10 <= findViewByPosition.getRight() && findViewByPosition.getRight() <= this.f4405i.getFlexCubeWidth() - findViewByPosition.getLeft() && ((FlexAutoPlayInterface) this.C.findViewByPosition(x)).autoPlay(z, z2)) {
                        return true;
                    }
                }
                x++;
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchVisibilityChanged(View view, int i2) {
        super.dispatchVisibilityChanged(view, i2);
        if (this.K) {
            if (i2 == 0) {
                L();
            } else {
                G();
            }
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.A.b(canvas);
        super.draw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        L();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.K) {
            G();
        }
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.A.h(canvas);
    }

    @Override // com.jd.lib.flexcube.help.MsgInterface
    public void pushAction(Class<? extends MsgActionInterface> cls, Object obj) {
    }
}
