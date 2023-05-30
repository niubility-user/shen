package com.jd.lib.flexcube.layout.floor.banner.vertical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.entity.BaseWidgetEntity;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.banner.BannerConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.banner.vertical.view.FlexVerticalBannerRow;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexVerticalBannerLayout extends FrameLayout implements FlexAutoPlayInterface {
    public static float E = 300.0f;
    private int A;
    private int B;
    Handler C;
    protected final Runnable D;

    /* renamed from: g  reason: collision with root package name */
    private Context f4368g;

    /* renamed from: h  reason: collision with root package name */
    private ImageView f4369h;

    /* renamed from: i  reason: collision with root package name */
    private RecyclerView f4370i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayoutManager f4371j;

    /* renamed from: k  reason: collision with root package name */
    private VerticalRecyclerAdapter f4372k;

    /* renamed from: l  reason: collision with root package name */
    private e f4373l;

    /* renamed from: m  reason: collision with root package name */
    private BabelScope f4374m;

    /* renamed from: n  reason: collision with root package name */
    private FlexCubeModel f4375n;
    private List<BaseWidgetEntity> o;
    public List<MaterialModel> p;
    public int q;
    public int r;
    private float s;
    private String t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* loaded from: classes14.dex */
    public class ItemDecoration extends RecyclerView.ItemDecoration {
        public ItemDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            if (recyclerView.getChildAdapterPosition(view) > 0) {
                rect.top = FlexVerticalBannerLayout.this.y;
            } else {
                rect.top = 0;
            }
        }
    }

    /* loaded from: classes14.dex */
    public class VerticalRecyclerAdapter extends RecyclerView.Adapter<c> {
        public VerticalRecyclerAdapter() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(@NonNull c cVar, int i2) {
            List<MaterialModel> list = FlexVerticalBannerLayout.this.p;
            if (list == null) {
                return;
            }
            try {
                int size = list.size();
                FlexVerticalBannerLayout flexVerticalBannerLayout = FlexVerticalBannerLayout.this;
                int i3 = flexVerticalBannerLayout.q;
                int i4 = i2 % (size / i3);
                cVar.a.updateView(i3, flexVerticalBannerLayout.t, FlexVerticalBannerLayout.this.f4375n);
                cVar.a.updata(FlexVerticalBannerLayout.this.p.subList(i4 * i3, (i4 + 1) * i3));
            } catch (Throwable unused) {
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FlexVerticalBannerLayout.this.p == null ? 0 : Integer.MAX_VALUE;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i2) {
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public c onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            Context context = FlexVerticalBannerLayout.this.f4368g;
            FlexVerticalBannerLayout flexVerticalBannerLayout = FlexVerticalBannerLayout.this;
            FlexVerticalBannerRow flexVerticalBannerRow = new FlexVerticalBannerRow(context, flexVerticalBannerLayout.q, flexVerticalBannerLayout.t, FlexVerticalBannerLayout.this.f4374m, FlexVerticalBannerLayout.this.f4375n);
            flexVerticalBannerRow.setLayoutParams(new RecyclerView.LayoutParams(-2, -2));
            return new c(FlexVerticalBannerLayout.this, flexVerticalBannerRow);
        }
    }

    /* loaded from: classes14.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FlexVerticalBannerLayout.this.u && FlexVerticalBannerLayout.this.v) {
                if (FlexVerticalBannerLayout.this.f4370i != null && FlexVerticalBannerLayout.this.f4371j != null) {
                    FlexVerticalBannerLayout.this.f4370i.smoothScrollToPosition(FlexVerticalBannerLayout.this.f4371j.findLastVisibleItemPosition() + 1);
                }
                FlexVerticalBannerLayout.this.C.postDelayed(this, r0.w);
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends LinearLayoutManager {

        /* loaded from: classes14.dex */
        class a extends LinearSmoothScroller {
            a(Context context) {
                super(context);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return (FlexVerticalBannerLayout.E / 1125.0f) * (400.0f / (FlexVerticalBannerLayout.this.x > 100 ? FlexVerticalBannerLayout.this.x : 100));
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
    public class c extends RecyclerView.ViewHolder {
        public FlexVerticalBannerRow a;

        public c(@NonNull FlexVerticalBannerLayout flexVerticalBannerLayout, FlexVerticalBannerRow flexVerticalBannerRow) {
            super(flexVerticalBannerRow);
            this.a = flexVerticalBannerRow;
        }
    }

    /* loaded from: classes14.dex */
    private static class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private final int f4377g;

        /* renamed from: h  reason: collision with root package name */
        private final RecyclerView f4378h;

        d(int i2, RecyclerView recyclerView) {
            this.f4377g = i2;
            this.f4378h = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f4378h.scrollToPosition(this.f4377g);
        }
    }

    public FlexVerticalBannerLayout(@NonNull Context context) {
        super(context);
        this.s = 1.0f;
        this.u = true;
        this.v = true;
        this.w = 3000;
        this.C = new Handler();
        this.D = new a();
        this.f4368g = context;
        this.q = 1;
        this.r = 1;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4369h = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4369h);
        this.f4373l = new e(this);
    }

    private void l() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
    }

    private void r(BannerConfig bannerConfig) {
        if (bannerConfig == null) {
            this.u = true;
            this.w = 3000;
            return;
        }
        if ("1".equals(bannerConfig.autoPlay)) {
            this.u = true;
        } else {
            this.u = false;
        }
        if (com.jd.lib.flexcube.iwidget.b.c.d(bannerConfig.interval)) {
            this.w = com.jd.lib.flexcube.iwidget.b.b.f(bannerConfig.interval, 3) * 1000;
        } else {
            this.w = 3000;
        }
    }

    private void s(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && com.jd.lib.flexcube.iwidget.b.c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4369h.setVisibility(0);
                this.f4369h.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4369h).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4369h.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4369h.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4369h.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4369h.setVisibility(8);
        setBackgroundColor(0);
    }

    private void t(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2) {
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                this.z = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2);
                com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2);
                this.A = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.topPadding, f2);
            } else {
                this.z = 0;
                this.A = 0;
            }
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardHPadding, f2);
                this.y = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.cardVPadding, f2);
            } else {
                this.y = 0;
            }
            if (canvasConfig != null) {
                com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.w, f2);
                this.x = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.f4223h, f2);
            } else {
                this.x = 0;
            }
            int i2 = this.x;
            int i3 = this.r;
            this.B = (i2 * i3) + (this.y * (i3 - 1));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.B);
            layoutParams.topMargin = this.A;
            layoutParams.leftMargin = this.z;
            this.f4370i.setLayoutParams(layoutParams);
            return;
        }
        l();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4373l.b(canvas);
        super.draw(canvas);
    }

    public void m(String str) {
        this.q = com.jd.lib.flexcube.iwidget.b.b.f(FlexCube.getSubInfo(str), 1);
        if (this.f4370i == null) {
            RecyclerView recyclerView = new RecyclerView(this.f4368g);
            this.f4370i = recyclerView;
            addView(recyclerView);
            b bVar = new b(this.f4368g, 1, false);
            this.f4371j = bVar;
            this.f4370i.setLayoutManager(bVar);
            VerticalRecyclerAdapter verticalRecyclerAdapter = new VerticalRecyclerAdapter();
            this.f4372k = verticalRecyclerAdapter;
            this.f4370i.setAdapter(verticalRecyclerAdapter);
            this.f4370i.addItemDecoration(new ItemDecoration());
        }
    }

    public void n(CfInfo cfInfo, float f2) {
        this.f4373l.i(cfInfo, f2);
    }

    public void o() {
        this.C.removeCallbacks(this.D);
        this.C.postDelayed(this.D, this.w);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        p();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4373l.h(canvas);
    }

    public void p() {
        this.C.removeCallbacks(this.D);
    }

    public void q(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        FloorConfig floorConfig;
        if (this.f4370i != null && flexCubeModel != null && flexCubeModel.widgetList != null && flexCubeModel.canvasConfig != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4374m = babelScope;
            this.f4375n = flexCubeModel;
            this.s = flexCubeModel.getMultiple();
            this.o = flexCubeModel.widgetList;
            this.t = flexCubeModel.canvasConfig.getUuid();
            this.p = list;
            r(flexCubeModel.floorConfig.bannerConfig);
            s(flexCubeModel.floorConfig.viewStyle, babelScope);
            t(flexCubeModel.floorConfig, flexCubeModel.canvasConfig, this.s);
            RecyclerView recyclerView = this.f4370i;
            int i3 = this.r;
            recyclerView.post(new d(i3 + (-1) < 0 ? 0 : i3 - 1, recyclerView));
            this.f4372k.notifyDataSetChanged();
            if (list != null && (this.r != 1 || this.q <= 0 || list.size() / this.q != 1)) {
                this.v = true;
                o();
                return;
            }
            this.v = false;
            return;
        }
        l();
    }
}
