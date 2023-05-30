package com.jingdong.app.mall.home.state.old;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.aips.common.utils.SystemBarTintManager;
import com.jd.mobile.image.ImageRequestListener;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class ElderGuideLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Path f10867g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f10868h;

    /* renamed from: i  reason: collision with root package name */
    private int f10869i;

    /* renamed from: j  reason: collision with root package name */
    private int f10870j;

    /* renamed from: k  reason: collision with root package name */
    private int f10871k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f10872l;

    /* renamed from: m  reason: collision with root package name */
    private ImageView f10873m;

    /* renamed from: n  reason: collision with root package name */
    private AtomicBoolean f10874n;
    private AtomicBoolean o;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ElderGuideLayout.this.c();
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnLayoutChangeListener {
        b() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            if ((i8 <= 0 || i4 == i8) && (i9 <= 0 || i5 == i9)) {
                return;
            }
            ElderGuideLayout.this.c();
        }
    }

    /* loaded from: classes4.dex */
    class c implements ImageRequestListener<ImageInfo> {
        c() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(ImageInfo imageInfo) {
            ElderGuideLayout.this.f10874n.set(true);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes4.dex */
    class d implements ImageRequestListener<ImageInfo> {
        d() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onSuccess(ImageInfo imageInfo) {
            ElderGuideLayout.this.o.set(true);
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onCancel() {
        }

        @Override // com.jd.mobile.image.ImageRequestListener
        public void onFailure(Throwable th) {
        }
    }

    /* loaded from: classes4.dex */
    class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ElderGuideLayout.this.f10874n.get() && ElderGuideLayout.this.o.get()) {
                ElderGuideLayout.this.setVisibility(0);
                ElderGuideLayout.this.setAlpha(1.0f);
                ElderGuideLayout.this.postInvalidate();
                return;
            }
            ElderGuideLayout.this.setVisibility(8);
        }
    }

    public ElderGuideLayout(Context context) {
        super(context);
        this.f10867g = new Path();
        this.f10868h = new Paint(1);
        this.f10874n = new AtomicBoolean(false);
        this.o = new AtomicBoolean(false);
        this.f10868h.setColor(SystemBarTintManager.DEFAULT_TINT_COLOR);
        setAlpha(0.01f);
        setOnClickListener(new a());
        addOnLayoutChangeListener(new b());
    }

    private void d() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f10867g.reset();
            this.f10867g.addRect(0.0f, 0.0f, getWidth(), getHeight(), Path.Direction.CCW);
            Path path = new Path();
            path.addCircle(this.f10869i, this.f10870j, this.f10871k, Path.Direction.CCW);
            this.f10867g.op(path, Path.Op.DIFFERENCE);
            this.f10867g.close();
        }
    }

    public boolean c() {
        if (getVisibility() != 0) {
            return false;
        }
        setVisibility(8);
        return getAlpha() == 1.0f;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.f10871k > 0 && this.f10867g.isEmpty()) {
            d();
        }
        canvas.drawPath(this.f10867g, this.f10868h);
        super.dispatchDraw(canvas);
    }

    public void e(View view, int i2, int i3, int i4) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (height < com.jingdong.app.mall.home.floor.common.d.d(500)) {
            return;
        }
        ((ViewGroup) view).addView(this);
        if (this.f10872l == null) {
            HomeImageView homeImageView = new HomeImageView(getContext());
            this.f10872l = homeImageView;
            homeImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            RelativeLayout.LayoutParams u = new f(500, R2.anim.slide_down).u(this.f10872l);
            u.bottomMargin = (height - i3) + i4 + com.jingdong.app.mall.home.floor.common.d.d(R2.anim.miaosha_dropdown_out);
            u.rightMargin = (width - i2) - i4;
            u.addRule(12);
            u.addRule(11);
            addView(this.f10872l, u);
        }
        if (this.f10873m == null) {
            HomeImageView homeImageView2 = new HomeImageView(getContext());
            this.f10873m = homeImageView2;
            homeImageView2.setScaleType(ImageView.ScaleType.FIT_XY);
            f fVar = new f(40, 132);
            RelativeLayout.LayoutParams u2 = fVar.u(this.f10873m);
            u2.bottomMargin = (height - i3) + i4;
            u2.rightMargin = (width - i2) - (fVar.v() >> 1);
            u2.addRule(12);
            u2.addRule(11);
            addView(this.f10873m, u2);
        }
        com.jingdong.app.mall.home.floor.ctrl.e.b(this.f10872l, "2675", new c());
        com.jingdong.app.mall.home.floor.ctrl.e.b(this.f10873m, "2676", new d());
        this.f10869i = i2;
        this.f10870j = i3;
        this.f10871k = i4;
        postDelayed(new e(), 1000L);
    }
}
