package com.jd.lib.flexcube.layout.floor.banner.swipe;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.entity.PageInfo;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.flexcube.FlexCube;
import com.jd.lib.flexcube.canvas.entity.CanvasConfig;
import com.jd.lib.flexcube.help.FlexAutoPlayInterface;
import com.jd.lib.flexcube.iwidget.b.c;
import com.jd.lib.flexcube.iwidget.entity.material.ExposureInfo;
import com.jd.lib.flexcube.iwidget.entity.material.MaterialModel;
import com.jd.lib.flexcube.iwidget.ui.video.FlexCubeVideoService;
import com.jd.lib.flexcube.layout.entity.FlexCubeModel;
import com.jd.lib.flexcube.layout.entity.FloorConfig;
import com.jd.lib.flexcube.layout.entity.banner.BannerConfig;
import com.jd.lib.flexcube.layout.entity.common.BgInfo;
import com.jd.lib.flexcube.layout.entity.common.FloorStyle;
import com.jd.lib.flexcube.layout.entity.common.ViewStyle;
import com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.CustomSwipeFlingAdapterView;
import com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import java.util.List;

/* loaded from: classes14.dex */
public class FlexSwipeCardLayout extends RelativeLayout implements FlexAutoPlayInterface, SwipeFlingAdapterView.f, SwipeFlingAdapterView.e {

    /* renamed from: g  reason: collision with root package name */
    private CustomSwipeFlingAdapterView f4328g;

    /* renamed from: h  reason: collision with root package name */
    private com.jd.lib.flexcube.layout.floor.banner.swipe.a f4329h;

    /* renamed from: i  reason: collision with root package name */
    private Context f4330i;

    /* renamed from: j  reason: collision with root package name */
    private BabelScope f4331j;

    /* renamed from: k  reason: collision with root package name */
    private FlexCubeModel f4332k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f4333l;

    /* renamed from: m  reason: collision with root package name */
    private e f4334m;

    /* renamed from: n  reason: collision with root package name */
    private int f4335n;
    private int o;
    private FrameLayout.LayoutParams p;
    private int q;
    private boolean r;
    private long s;
    private boolean t;
    private Handler u;
    private Handler v;

    /* loaded from: classes14.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (!FlexSwipeCardLayout.this.t && FlexSwipeCardLayout.this.r && FlexSwipeCardLayout.this.f4328g != null && FlexSwipeCardLayout.this.f4328g.getChildCount() > 1 && FlexSwipeCardLayout.this.f4328g.getAdapter() != null) {
                try {
                    if (FlexSwipeCardLayout.this.s - ((Long) message.obj).longValue() != 0) {
                        return;
                    }
                    FlexSwipeCardLayout.this.f4328g.B();
                } catch (Exception unused) {
                }
            }
        }
    }

    /* loaded from: classes14.dex */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            FlexCubeVideoService videoService = FlexCube.getInstance().getVideoService();
            if (videoService != null) {
                FlexSwipeCardLayout.this.autoPlay(videoService.isWifi(), videoService.isSupportAutoPlay());
            }
        }
    }

    public FlexSwipeCardLayout(@NonNull Context context) {
        super(context);
        this.q = 3000;
        this.r = false;
        this.s = -1L;
        this.u = new a(Looper.getMainLooper());
        this.v = new b(Looper.getMainLooper());
        f(context);
    }

    private synchronized void e(int i2) {
        if (this.f4328g == null) {
            return;
        }
        this.s = System.currentTimeMillis();
        Message obtain = Message.obtain();
        obtain.obj = Long.valueOf(this.s);
        this.u.sendMessageDelayed(obtain, i2);
    }

    private void f(Context context) {
        this.f4330i = context;
        ImageView newImageView = ImageLoad.newImageView(context);
        this.f4333l = newImageView;
        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(this.f4333l);
        this.f4334m = new e(this);
        CustomSwipeFlingAdapterView customSwipeFlingAdapterView = new CustomSwipeFlingAdapterView(context);
        this.f4328g = customSwipeFlingAdapterView;
        addView(customSwipeFlingAdapterView);
        this.f4328g.w(true);
        this.f4328g.u(this);
        this.f4328g.y(this);
        this.f4328g.x(4);
        this.f4328g.D(new float[]{1.0f, 0.65f, 0.4f, 0.0f});
        this.f4329h = new com.jd.lib.flexcube.layout.floor.banner.swipe.a();
    }

    private void g() {
        setLayoutParams(new FrameLayout.LayoutParams(-1, 1));
        this.p = new FrameLayout.LayoutParams(0, 0);
    }

    private void k() {
        MaterialModel a2;
        ExposureInfo exposureInfo;
        BabelScope babelScope;
        com.jd.lib.flexcube.layout.floor.banner.swipe.a aVar = this.f4329h;
        if (aVar == null || (a2 = aVar.a()) == null || (exposureInfo = a2.exposureInfo) == null || (babelScope = this.f4331j) == null || babelScope.iFloorUI == null || TextUtils.isEmpty(exposureInfo.eventId) || TextUtils.isEmpty(exposureInfo.srv)) {
            return;
        }
        try {
            this.f4331j.iFloorUI.sendExposureData(MtaData.Builder.from(exposureInfo.eventId, exposureInfo.srv).jsonParam(exposureInfo.srvData).split(true).build());
        } catch (Exception unused) {
        }
    }

    private void n(BannerConfig bannerConfig) {
        if (bannerConfig == null) {
            this.r = false;
            this.q = 3000;
            return;
        }
        this.r = "1".equals(bannerConfig.autoPlay);
        if (c.d(bannerConfig.interval)) {
            this.q = com.jd.lib.flexcube.iwidget.b.b.f(bannerConfig.interval, 3) * 1000;
        } else {
            this.q = 3000;
        }
        float e2 = com.jd.lib.flexcube.iwidget.b.b.e(this.f4332k.canvasConfig.scalingRatio, 1);
        if (e2 < 0.0f && e2 >= 1.0f) {
            e2 = 0.0f;
        }
        FlexCubeModel flexCubeModel = this.f4332k;
        FloorStyle floorStyle = flexCubeModel.floorConfig.floorStyle;
        int cardHPadding = floorStyle != null ? floorStyle.getCardHPadding(flexCubeModel.getMultiple()) : 0;
        float f2 = 1.0f - e2;
        int c2 = com.jd.lib.flexcube.iwidget.b.b.c((this.f4335n * f2) / 2.0f) + cardHPadding;
        float f3 = 1.0f - (e2 * e2);
        int c3 = (cardHPadding * 2) + com.jd.lib.flexcube.iwidget.b.b.c((this.f4335n * f3) / 2.0f);
        this.f4328g.E(new int[]{0, c2, c3, c3});
        this.f4328g.F(new float[]{0.0f, f2, f3, f3});
        this.f4328g.z("1".equals(this.f4332k.canvasConfig.cardTransparent));
    }

    private void o(ViewStyle viewStyle, BabelScope babelScope) {
        BgInfo bgInfo;
        PageInfo pageInfo;
        int i2 = 0;
        if (viewStyle != null && (bgInfo = viewStyle.bgInfo) != null && babelScope != null) {
            if ("1".equals(bgInfo.type) && c.d(bgInfo.imgUrl)) {
                setBackgroundColor(0);
                this.f4333l.setVisibility(0);
                this.f4333l.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                ImageLoad.with(this.f4333l).load(bgInfo.imgUrl);
                return;
            } else if ("1".equals(bgInfo.sameColor)) {
                if (babelScope != null && (pageInfo = babelScope.pageInfo) != null) {
                    i2 = com.jd.lib.flexcube.iwidget.b.a.a(pageInfo.pageBgColor, 0);
                }
                this.f4333l.setVisibility(8);
                setBackgroundColor(i2);
                return;
            } else if ("0".equals(bgInfo.type)) {
                this.f4333l.setVisibility(8);
                setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(bgInfo.color, 0));
                return;
            } else {
                this.f4333l.setVisibility(8);
                setBackgroundColor(0);
                return;
            }
        }
        this.f4333l.setVisibility(8);
        setBackgroundColor(0);
    }

    private void p(FloorConfig floorConfig, CanvasConfig canvasConfig, float f2, float f3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        if (floorConfig != null && floorConfig.w >= 1) {
            ViewStyle viewStyle = floorConfig.viewStyle;
            if (viewStyle != null) {
                i3 = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.leftPadding, f2);
                i4 = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.rightPadding, f2);
                i2 = com.jd.lib.flexcube.iwidget.b.b.d(viewStyle.topPadding, f2);
            } else {
                i2 = 0;
                i3 = 0;
                i4 = 0;
            }
            FloorStyle floorStyle = floorConfig.floorStyle;
            if (floorStyle != null) {
                i5 = com.jd.lib.flexcube.iwidget.b.b.d(floorConfig.w, f2);
                i7 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.leftPadding, f2);
                i6 = com.jd.lib.flexcube.iwidget.b.b.d(floorStyle.rightPadding, f2);
            } else {
                i5 = 0;
                i6 = 0;
                i7 = 0;
            }
            if (canvasConfig != null) {
                this.f4335n = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.w, f2);
                this.o = com.jd.lib.flexcube.iwidget.b.b.d(canvasConfig.f4223h, f2);
            } else {
                this.f4335n = 0;
                this.o = 0;
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f4335n, this.o);
            this.p = layoutParams;
            layoutParams.leftMargin = i3;
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(((i5 - i7) - i6) - i4, this.o);
            layoutParams2.topMargin = i2;
            this.f4328g.setLayoutParams(layoutParams2);
            return;
        }
        g();
    }

    @Override // com.jd.lib.flexcube.help.FlexAutoPlayInterface
    public boolean autoPlay(boolean z, boolean z2) {
        CustomSwipeFlingAdapterView customSwipeFlingAdapterView;
        FlexCubeModel flexCubeModel;
        int childCount;
        if (this.r || (customSwipeFlingAdapterView = this.f4328g) == null || (flexCubeModel = this.f4332k) == null || !flexCubeModel.hasVideo || (childCount = customSwipeFlingAdapterView.getChildCount()) <= 0) {
            return false;
        }
        View childAt = this.f4328g.getChildAt(childCount - 1);
        if (childAt instanceof FlexAutoPlayInterface) {
            return ((FlexAutoPlayInterface) childAt).autoPlay(z, z2);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.s = -1L;
        } else if (action == 1 || action == 3) {
            e(this.q);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.f4334m.b(canvas);
        super.draw(canvas);
    }

    public void h(String str) {
    }

    public void i() {
        this.s = System.currentTimeMillis();
        this.t = true;
        this.u.removeCallbacksAndMessages(null);
        this.v.removeCallbacksAndMessages(null);
    }

    public void j() {
        this.t = false;
        e(this.q);
    }

    public void l(CfInfo cfInfo, float f2) {
        this.f4334m.i(cfInfo, f2);
    }

    public void m(@NonNull BabelScope babelScope, @NonNull FlexCubeModel flexCubeModel, List<MaterialModel> list, int i2) {
        CanvasConfig canvasConfig;
        FloorConfig floorConfig;
        if (this.f4328g != null && flexCubeModel != null && flexCubeModel.widgetList != null && (canvasConfig = flexCubeModel.canvasConfig) != null && list != null && (floorConfig = flexCubeModel.floorConfig) != null && floorConfig.w >= 1) {
            this.f4331j = babelScope;
            this.f4332k = flexCubeModel;
            p(floorConfig, canvasConfig, flexCubeModel.getMultiple(), flexCubeModel.getCanvasMultiple());
            n(flexCubeModel.floorConfig.bannerConfig);
            this.f4329h.g(babelScope, flexCubeModel);
            this.f4329h.f(list);
            this.f4329h.h(this.p);
            this.f4328g.v(this.f4332k.hasVideo);
            this.f4328g.setAdapter(this.f4329h);
            o(flexCubeModel.floorConfig.viewStyle, babelScope);
            k();
            return;
        }
        g();
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.f
    public void onAdapterAboutToEmpty(int i2) {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        j();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        this.f4334m.h(canvas);
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.e
    public void onItemClicked(MotionEvent motionEvent, View view, Object obj) {
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.f
    public void onLeftCardExit(Object obj) {
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.f
    public void onRightCardExit(Object obj) {
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.f
    public void onScroll(float f2, float f3) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            j();
        } else {
            i();
        }
    }

    @Override // com.jd.lib.flexcube.layout.floor.banner.swipe.swipecard.SwipeFlingAdapterView.f
    public void removeFirstObjectInAdapter(boolean z) {
        if (z) {
            this.f4329h.e();
        } else {
            this.f4329h.d();
        }
        if (this.r) {
            e(this.q);
        } else {
            FlexCubeModel flexCubeModel = this.f4332k;
            if (flexCubeModel != null && flexCubeModel.hasVideo) {
                this.v.sendMessageDelayed(new Message(), 1L);
            }
        }
        k();
    }
}
