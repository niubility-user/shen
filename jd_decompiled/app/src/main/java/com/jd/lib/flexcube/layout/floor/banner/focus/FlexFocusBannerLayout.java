package com.jd.lib.flexcube.layout.floor.banner.focus;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.core.internal.view.SupportMenu;
import androidx.recyclerview.widget.RecyclerView;
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
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.banner.focus.layoutmanager.FlexFocusBannerCenterSnapHelper;
import com.jd.lib.flexcube.layout.floor.banner.focus.layoutmanager.FlexFocusBannerLayoutManager;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexFocusBannerLayout extends FrameLayout implements FlexAutoPlayInterface {
    private int A;
    private int B;
    private int C;
    private List<BaseWidgetEntity> D;
    public List<MaterialModel> E;
    private int F;
    Handler G;
    protected final Runnable H;

    /* renamed from: g  reason: collision with root package name */
    private Context f4284g;

    /* renamed from: h  reason: collision with root package name */
    private BabelScope f4285h;

    /* renamed from: i  reason: collision with root package name */
    private FlexCubeModel f4286i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f4287j;

    /* renamed from: k  reason: collision with root package name */
    private e f4288k;

    /* renamed from: l  reason: collision with root package name */
    private RecyclerView f4289l;

    /* renamed from: m  reason: collision with root package name */
    private FlexFocusBannerLayoutManager f4290m;

    /* renamed from: n  reason: collision with root package name */
    private FocusBannerRecyclerAdapter f4291n;
    private com.jd.lib.flexcube.layout.floor.banner.common.a o;
    private int p;
    private float q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private float w;
    private String x;
    private boolean y;
    private int z;

    /* loaded from: classes14.dex */
    public class FocusBannerRecyclerAdapter extends RecyclerView.Adapter<c> {
        public FocusBannerRecyclerAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull c cVar, int i2) {
            int q = FlexFocusBannerLayout.this.q();
            if (q > 0) {
                int i3 = i2 % q;
                if (cVar.a != null) {
                    if (FlexFocusBannerLayout.this.f4286i != null && Math.abs(FlexFocusBannerLayout.this.f4286i.getMultiple() - cVar.a.f4221m) > 1.0E-4d) {
                        cVar.a.a(FlexFocusBannerLayout.this.D, FlexFocusBannerLayout.this.w, FlexFocusBannerLayout.this.x);
                        cVar.a.setLayoutParams(new RecyclerView.LayoutParams(FlexFocusBannerLayout.this.r, FlexFocusBannerLayout.this.s));
                    }
                    cVar.a.i(FlexFocusBannerLayout.this.f4285h, FlexFocusBannerLayout.this.f4286i == null ? null : FlexFocusBannerLayout.this.f4286i.canvasConfig, FlexFocusBannerLayout.this.w);
                    cVar.a.h(FlexFocusBannerLayout.this.E.get(i3), FlexFocusBannerLayout.this.f4285h);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FlexFocusBannerLayout.this.q();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public c onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            FlexViewGroup flexViewGroup = new FlexViewGroup(FlexFocusBannerLayout.this.f4284g);
            flexViewGroup.setLayoutParams(new RecyclerView.LayoutParams(FlexFocusBannerLayout.this.r, FlexFocusBannerLayout.this.s));
            flexViewGroup.a(FlexFocusBannerLayout.this.D, FlexFocusBannerLayout.this.w, FlexFocusBannerLayout.this.x);
            return new c(FlexFocusBannerLayout.this, flexViewGroup);
        }
    }

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FlexFocusBannerLayout.this.y) {
                if (FlexFocusBannerLayout.this.q() <= 1) {
                    return;
                }
                if (FlexFocusBannerLayout.this.f4289l != null && FlexFocusBannerLayout.this.z == FlexFocusBannerLayout.this.f4290m.g()) {
                    FlexFocusBannerLayout.j(FlexFocusBannerLayout.this);
                    FlexFocusBannerLayout.this.u();
                    FlexFocusBannerLayout.this.f4289l.smoothScrollToPosition(FlexFocusBannerLayout.this.z);
                }
            }
            FlexFocusBannerLayout.this.G.postDelayed(this, r0.A);
        }
    }

    /* loaded from: classes14.dex */
    class b extends RecyclerView.OnScrollListener {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            FlexCubeVideoService videoService;
            int g2 = FlexFocusBannerLayout.this.f4290m.g();
            if (FlexFocusBannerLayout.this.z != g2) {
                FlexFocusBannerLayout.this.z = g2;
            }
            if (i2 == 0) {
                FlexFocusBannerLayout.this.x();
            }
            if (i2 == 0 && (videoService = FlexCube.getInstance().getVideoService()) != null) {
                FlexFocusBannerLayout.this.autoPlay(videoService.isWifi(), videoService.isSupportAutoPlay());
            }
            FlexFocusBannerLayout.this.u();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (i2 != 0) {
                FlexFocusBannerLayout.this.y();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends RecyclerView.ViewHolder {
        public FlexViewGroup a;

        public c(@NonNull FlexFocusBannerLayout flexFocusBannerLayout, FlexViewGroup flexViewGroup) {
            super(flexViewGroup);
            this.a = flexViewGroup;
        }
    }

    public FlexFocusBannerLayout(@NonNull Context context) {
        super(context);
        this.p = 10;
        this.q = 0.8f;
        this.w = 1.0f;
        this.y = false;
        this.A = 3000;
        this.G = new Handler();
        this.H = new a();
        this.f4284g = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4287j = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4287j);
        this.f4288k = new e(this);
    }

    private void A(BannerConfig bannerConfig) {
        if (bannerConfig == null) {
            this.y = false;
            this.A = 3000;
            this.B = com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK);
            this.C = com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368);
            return;
        }
        if ("1".equals(bannerConfig.autoPlay)) {
            this.y = true;
        } else {
            this.y = false;
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.interval)) {
            this.A = com.jd.lib.flexcube.iwidget.b.b.f(bannerConfig.interval, 3) * 1000;
        } else {
            this.A = 3000;
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.selectedColor)) {
            this.B = com.jd.lib.flexcube.iwidget.b.a.a(bannerConfig.selectedColor, com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK));
        } else {
            this.B = com.jd.lib.flexcube.iwidget.b.a.a("#fa2c19", SupportMenu.CATEGORY_MASK);
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.unselectedColor)) {
            this.C = com.jd.lib.flexcube.iwidget.b.a.a(bannerConfig.unselectedColor, com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368));
        } else {
            this.C = com.jd.lib.flexcube.iwidget.b.a.a("rgba(0, 0, 0, 0.5)", -7829368);
        }
    }

    private void B(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && com.jd.lib.flexcube.iwidget.b.c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4287j.setVisibility(0);
                this.f4287j.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4287j).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4287j.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4287j.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4287j.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4287j.setVisibility(8);
        setBackgroundColor(0);
    }

    private void C(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2) {
        int i2;
        int i3;
        int i4;
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                this.t = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2);
                this.u = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2);
                this.v = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.topPadding, f2);
            } else {
                this.t = 0;
                this.v = 0;
            }
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i2 = com.jd.lib.flexcube.iwidget.b.b.d(floorConfig.w, f2);
                i3 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.leftPadding, f2);
                i4 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.rightPadding, f2);
                this.p = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardHPadding, f2);
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
            }
            if (canvasConfig != null) {
                this.r = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.w, f2);
                this.s = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.f4223h, f2);
            } else {
                this.r = 0;
                this.s = 0;
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((((i2 - i3) - i4) - this.t) - this.u, this.s);
            layoutParams.topMargin = this.v;
            layoutParams.leftMargin = this.t;
            this.f4289l.setLayoutParams(layoutParams);
            return;
        }
        s();
    }

    static /* synthetic */ int j(FlexFocusBannerLayout flexFocusBannerLayout) {
        int i2 = flexFocusBannerLayout.z;
        flexFocusBannerLayout.z = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int q() {
        List<MaterialModel> list = this.E;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    private int r() {
        return q() > 0 ? this.z % q() : this.z;
    }

    private void s() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        if (!this.y && z) {
            try {
                FlexCubeModel flexCubeModel = this.f4286i;
                if (flexCubeModel != null && flexCubeModel.hasVideo) {
                    View findViewByPosition = this.f4290m.findViewByPosition(this.f4290m.g());
                    if (findViewByPosition instanceof FlexAutoPlayInterface) {
                        if (((FlexAutoPlayInterface) findViewByPosition).autoPlay(z, z2)) {
                            return true;
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
            x();
        } else if (action == 0) {
            y();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4288k.b(canvas);
        super.draw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        x();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        y();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4288k.h(canvas);
    }

    public void t(String str) {
        int f2 = com.jd.lib.flexcube.iwidget.b.b.f(FlexCube.getSubInfo(str), 3);
        this.F = f2;
        int i2 = f2 >= 3 ? f2 : 3;
        this.F = i2;
        if (i2 > 11) {
            i2 = 11;
        }
        this.F = i2;
        if (this.f4289l == null) {
            this.f4289l = new RecyclerView(this.f4284g);
            addView(this.f4289l, new FrameLayout.LayoutParams(-1, -1));
            FlexFocusBannerLayoutManager flexFocusBannerLayoutManager = new FlexFocusBannerLayoutManager(getContext());
            this.f4290m = flexFocusBannerLayoutManager;
            flexFocusBannerLayoutManager.D(this.F);
            this.f4290m.B(this.p);
            this.f4290m.F(this.q);
            this.f4290m.E(1.0f - (this.F * 0.06f));
            this.f4289l.setLayoutManager(this.f4290m);
            this.f4289l.setFocusable(false);
            FocusBannerRecyclerAdapter focusBannerRecyclerAdapter = new FocusBannerRecyclerAdapter();
            this.f4291n = focusBannerRecyclerAdapter;
            this.f4289l.setAdapter(focusBannerRecyclerAdapter);
            new FlexFocusBannerCenterSnapHelper().attachToRecyclerView(this.f4289l);
            this.f4289l.addOnScrollListener(new b());
        }
    }

    public void u() {
        com.jd.lib.flexcube.layout.floor.banner.common.a aVar = this.o;
        if (aVar != null) {
            aVar.b(r());
        }
    }

    public void v(CfInfo cfInfo, float f2) {
        this.f4288k.i(cfInfo, f2);
    }

    public void w(com.jd.lib.flexcube.layout.floor.banner.common.a aVar) {
        this.o = aVar;
    }

    public void x() {
        this.G.removeCallbacks(this.H);
        this.G.postDelayed(this.H, this.A);
    }

    public void y() {
        this.G.removeCallbacks(this.H);
    }

    public void z(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (this.f4289l != null && flexCubeModel != null && flexCubeModel.widgetList != null && flexCubeModel.canvasConfig != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4285h = babelScope;
            this.f4286i = flexCubeModel;
            int i3 = this.F;
            if (i3 < 3) {
                i3 = 3;
            }
            this.F = i3;
            if (i3 > 11) {
                i3 = 11;
            }
            this.F = i3;
            float f2 = this.w;
            if (f2 > 0.0f && f2 != flexCubeModel.getMultiple()) {
                FlexFocusBannerLayoutManager flexFocusBannerLayoutManager = new FlexFocusBannerLayoutManager(getContext());
                this.f4290m = flexFocusBannerLayoutManager;
                flexFocusBannerLayoutManager.D(this.F);
                this.f4290m.B(this.p);
                this.f4290m.F(this.q);
                this.f4290m.E(1.0f - (this.F * 0.06f));
                this.f4289l.setLayoutManager(this.f4290m);
            }
            this.w = flexCubeModel.getMultiple();
            this.D = flexCubeModel.widgetList;
            this.x = flexCubeModel.canvasConfig.getUuid();
            float e2 = com.jd.lib.flexcube.iwidget.b.b.e(flexCubeModel.canvasConfig.scalingRatio, 1);
            if (0.5d <= e2 && e2 <= 1.0f) {
                this.q = e2;
            } else {
                this.q = 1.0f;
            }
            A(flexCubeModel.floorConfig.bannerConfig);
            B(flexCubeModel.floorConfig.viewStyle, babelScope);
            C(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.w);
            this.E = list;
            com.jd.lib.flexcube.layout.floor.banner.common.a aVar = this.o;
            if (aVar != null) {
                aVar.a(q(), this.w, this.B, this.C);
            }
            this.f4290m.F(this.q);
            this.f4290m.B(this.p);
            this.f4291n.notifyDataSetChanged();
            x();
            this.f4290m.z(true);
            return;
        }
        s();
    }
}
