package com.jd.lib.flexcube.layout.floor.banner.full;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.FlexViewGroup;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.banner.BannerConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexFullBannerLayout extends FrameLayout implements FlexAutoPlayInterface {
    public static float H = 300.0f;
    private RecyclerView A;
    private FullBannerRecyclerAdapter B;
    private com.jd.lib.flexcube.layout.floor.banner.common.a C;
    private LinearLayoutManager D;
    Handler E;
    protected final Runnable F;
    private int[] G;

    /* renamed from: g  reason: collision with root package name */
    private int f4316g;

    /* renamed from: h  reason: collision with root package name */
    private int f4317h;

    /* renamed from: i  reason: collision with root package name */
    private int f4318i;

    /* renamed from: j  reason: collision with root package name */
    private int f4319j;

    /* renamed from: k  reason: collision with root package name */
    private int f4320k;

    /* renamed from: l  reason: collision with root package name */
    private float f4321l;

    /* renamed from: m  reason: collision with root package name */
    private float f4322m;

    /* renamed from: n  reason: collision with root package name */
    private String f4323n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private List<BaseWidgetEntity> t;
    public List<MaterialModel> u;
    private Context v;
    private BabelScope w;
    private FlexCubeModel x;
    private ImageView y;
    private com.jd.lib.flexcube.widgets.b.e z;

    /* loaded from: classes14.dex */
    public class FullBannerRecyclerAdapter extends RecyclerView.Adapter<f> {
        public FullBannerRecyclerAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull f fVar, int i2) {
            int u = FlexFullBannerLayout.this.u();
            if (u > 0) {
                int i3 = i2 % u;
                if (fVar.a != null) {
                    if (FlexFullBannerLayout.this.x != null) {
                        float canvasMultiple = FlexFullBannerLayout.this.x.getCanvasMultiple();
                        FlexViewGroup flexViewGroup = fVar.a;
                        if (canvasMultiple != flexViewGroup.f4221m) {
                            flexViewGroup.a(FlexFullBannerLayout.this.t, FlexFullBannerLayout.this.f4322m, FlexFullBannerLayout.this.f4323n);
                            fVar.a.setLayoutParams(new RecyclerView.LayoutParams(FlexFullBannerLayout.this.f4316g, FlexFullBannerLayout.this.f4317h));
                        }
                    }
                    fVar.a.i(FlexFullBannerLayout.this.w, FlexFullBannerLayout.this.x == null ? null : FlexFullBannerLayout.this.x.canvasConfig, FlexFullBannerLayout.this.f4322m);
                    fVar.a.h(FlexFullBannerLayout.this.u.get(i3), FlexFullBannerLayout.this.w);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (FlexFullBannerLayout.this.u() <= 1) {
                return FlexFullBannerLayout.this.u();
            }
            return Integer.MAX_VALUE;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public f onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            FlexViewGroup flexViewGroup = new FlexViewGroup(FlexFullBannerLayout.this.v);
            flexViewGroup.a(FlexFullBannerLayout.this.t, FlexFullBannerLayout.this.f4322m, FlexFullBannerLayout.this.f4323n);
            flexViewGroup.setLayoutParams(new RecyclerView.LayoutParams(FlexFullBannerLayout.this.f4316g, FlexFullBannerLayout.this.f4317h));
            return new f(FlexFullBannerLayout.this, flexViewGroup);
        }
    }

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FlexFullBannerLayout.this.o) {
                if (FlexFullBannerLayout.this.u() <= 1) {
                    return;
                }
                FlexFullBannerLayout.k(FlexFullBannerLayout.this);
                if (FlexFullBannerLayout.this.A != null) {
                    FlexFullBannerLayout.this.A.smoothScrollToPosition(FlexFullBannerLayout.this.p);
                    FlexFullBannerLayout.this.y();
                }
            }
            FlexFullBannerLayout.this.E.postDelayed(this, r0.q);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b extends LinearLayoutManager {

        /* loaded from: classes14.dex */
        class a extends LinearSmoothScroller {
            a(Context context) {
                super(context);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (FlexFullBannerLayout.H / 1125.0f) * (((double) FlexFullBannerLayout.this.f4321l) < 0.1d ? 1.0f : FlexFullBannerLayout.this.f4321l);
            }
        }

        b(Context context, int i2, boolean z) {
            super(context, i2, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
            a aVar = new a(recyclerView.getContext());
            aVar.setTargetPosition(i2);
            startSmoothScroll(aVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends PagerSnapHelper {
        c() {
        }

        @Override // androidx.recyclerview.widget.PagerSnapHelper, androidx.recyclerview.widget.SnapHelper
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i2, int i3) {
            int findTargetSnapPosition = super.findTargetSnapPosition(layoutManager, i2, i3);
            FlexFullBannerLayout.this.p = findTargetSnapPosition;
            FlexFullBannerLayout.this.y();
            return findTargetSnapPosition;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class d extends RecyclerView.OnScrollListener {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            FlexCubeVideoService videoService;
            if (i2 != 0 || (videoService = FlexCube.getInstance().getVideoService()) == null) {
                return;
            }
            FlexFullBannerLayout.this.autoPlay(videoService.isWifi(), videoService.isSupportAutoPlay());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e extends RecyclerView.ItemDecoration {
        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            rect.left = FlexFullBannerLayout.this.f4318i;
            rect.right = FlexFullBannerLayout.this.f4319j;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class f extends RecyclerView.ViewHolder {
        public FlexViewGroup a;

        public f(@NonNull FlexFullBannerLayout flexFullBannerLayout, FlexViewGroup flexViewGroup) {
            super(flexViewGroup);
            this.a = flexViewGroup;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes14.dex */
    public static class g implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final int f4325g;

        /* renamed from: h  reason: collision with root package name */
        private final RecyclerView f4326h;

        g(int i2, RecyclerView recyclerView) {
            this.f4325g = i2;
            this.f4326h = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4326h.scrollToPosition(this.f4325g);
        }
    }

    public FlexFullBannerLayout(@NonNull Context context) {
        super(context);
        this.f4321l = 1.0f;
        this.o = false;
        this.q = 3000;
        this.E = new Handler();
        this.F = new a();
        this.G = new int[2];
        this.v = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.y = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.y);
        this.z = new com.jd.lib.flexcube.widgets.b.e(this);
    }

    private void E(BannerConfig bannerConfig) {
        if (bannerConfig == null) {
            this.o = false;
            this.q = 3000;
            this.r = com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK);
            this.s = com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368);
            return;
        }
        if ("1".equals(bannerConfig.autoPlay)) {
            this.o = true;
        } else {
            this.o = false;
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.interval)) {
            this.q = com.jd.lib.flexcube.iwidget.b.b.f(bannerConfig.interval, 3) * 1000;
        } else {
            this.q = 3000;
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.selectedColor)) {
            this.r = com.jd.lib.flexcube.iwidget.b.a.a(bannerConfig.selectedColor, com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK));
        } else {
            this.r = com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK);
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.unselectedColor)) {
            this.s = com.jd.lib.flexcube.iwidget.b.a.a(bannerConfig.unselectedColor, com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368));
        } else {
            this.s = com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368);
        }
    }

    private void F(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && com.jd.lib.flexcube.iwidget.b.c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.y.setVisibility(0);
                this.y.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.y).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.y.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.y.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.y.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.y.setVisibility(8);
        setBackgroundColor(0);
    }

    private void G(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3) {
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                this.f4318i = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2);
                this.f4319j = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2);
                this.f4320k = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.topPadding, f2);
            } else {
                this.f4318i = 0;
                this.f4320k = 0;
            }
            if (canvasConfig != null) {
                this.f4316g = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.w, f3);
                this.f4317h = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.f4223h, f3);
            } else {
                this.f4316g = 0;
                this.f4317h = 0;
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.f4317h);
            layoutParams.topMargin = this.f4320k;
            this.A.setLayoutParams(layoutParams);
            return;
        }
        w();
    }

    static /* synthetic */ int k(FlexFullBannerLayout flexFullBannerLayout) {
        int i2 = flexFullBannerLayout.p;
        flexFullBannerLayout.p = i2 + 1;
        return i2;
    }

    private int s(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(this.G);
            int[] iArr = this.G;
            return Math.min(iArr[0], iArr[1]);
        }
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    private int t(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(this.G);
            int[] iArr = this.G;
            return Math.max(iArr[0], iArr[1]);
        }
        return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int u() {
        List<MaterialModel> list = this.u;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private int v() {
        return u() > 0 ? this.p % u() : this.p;
    }

    private void w() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    public void A(com.jd.lib.flexcube.layout.floor.banner.common.a aVar) {
        this.C = aVar;
    }

    public void B() {
        this.E.removeCallbacks(this.F);
        this.E.postDelayed(this.F, this.q);
    }

    public void C() {
        this.E.removeCallbacks(this.F);
    }

    public void D(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (this.A != null && flexCubeModel != null && flexCubeModel.widgetList != null && flexCubeModel.canvasConfig != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.w = babelScope;
            this.x = flexCubeModel;
            this.f4321l = flexCubeModel.getMultiple();
            this.f4322m = flexCubeModel.getCanvasMultiple();
            this.t = flexCubeModel.widgetList;
            this.f4323n = flexCubeModel.canvasConfig.getUuid();
            E(flexCubeModel.floorConfig.bannerConfig);
            F(flexCubeModel.floorConfig.viewStyle, babelScope);
            G(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.f4321l, this.f4322m);
            this.u = list;
            if (list != null && list.size() > 1) {
                this.p = list.size() * 100;
            } else {
                this.p = 0;
            }
            RecyclerView recyclerView = this.A;
            recyclerView.post(new g(this.p, recyclerView));
            com.jd.lib.flexcube.layout.floor.banner.common.a aVar = this.C;
            if (aVar != null) {
                aVar.a(u(), this.f4321l, this.r, this.s);
            }
            this.B.notifyDataSetChanged();
            return;
        }
        w();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        if (!this.o && z) {
            try {
                FlexCubeModel flexCubeModel = this.x;
                if (flexCubeModel != null && flexCubeModel.hasVideo) {
                    int s = s(this.D);
                    int t = t(this.D);
                    if (s >= 0 && t >= 0) {
                        while (s <= t) {
                            if (this.D.findViewByPosition(s) instanceof FlexAutoPlayInterface) {
                                View findViewByPosition = this.D.findViewByPosition(s);
                                if (-10 <= findViewByPosition.getRight() && findViewByPosition.getRight() <= this.x.getFlexCubeWidth() - findViewByPosition.getLeft() && ((FlexAutoPlayInterface) this.D.findViewByPosition(s)).autoPlay(z, z2)) {
                                    return true;
                                }
                            }
                            s++;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            B();
        } else if (action == 0) {
            C();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.z.b(canvas);
        super.draw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        B();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.z.h(canvas);
    }

    public void x(String str) {
        if (this.A == null) {
            RecyclerView recyclerView = new RecyclerView(this.v);
            this.A = recyclerView;
            recyclerView.setFocusable(false);
            b bVar = new b(this.v, 0, false);
            this.D = bVar;
            this.A.setLayoutManager(bVar);
            FullBannerRecyclerAdapter fullBannerRecyclerAdapter = new FullBannerRecyclerAdapter();
            this.B = fullBannerRecyclerAdapter;
            this.A.setAdapter(fullBannerRecyclerAdapter);
            new c().attachToRecyclerView(this.A);
            this.A.addItemDecoration(new e());
            this.A.addOnScrollListener(new d());
            addView(this.A);
        }
        B();
    }

    public void y() {
        com.jd.lib.flexcube.layout.floor.banner.common.a aVar = this.C;
        if (aVar != null) {
            aVar.b(v());
        }
    }

    public void z(CfInfo cfInfo, float f2) {
        this.z.i(cfInfo, f2);
    }
}
