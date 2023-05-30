package com.jd.lib.flexcube.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageLoad;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.iwidget.entity.material.ClickEvent;
import com.jd.lib.flexcube.iwidget.ui.IWidget;
import com.jd.lib.flexcube.iwidget.ui.IWidgetCommunication;
import com.jd.lib.flexcube.widgets.b.e;
import com.jd.lib.flexcube.widgets.b.f;
import com.jd.lib.flexcube.widgets.c.a;
import com.jd.lib.flexcube.widgets.entity.ProcessEntity;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;
import com.jd.lib.flexcube.widgets.entity.common.ProcessInfo;
import com.jd.lib.flexcube.widgets.entity.process.ProcessConfig;
import com.jd.lib.flexcube.widgets.entity.process.ProcessDataPath;
import java.io.Serializable;
import java.util.Map;

/* loaded from: classes15.dex */
public class FlexProcessView extends FrameLayout implements IWidget<ProcessEntity> {
    private int A;
    private a B;
    private Path C;
    private float[] D;
    private float E;

    /* renamed from: g  reason: collision with root package name */
    private Paint f4468g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f4469h;

    /* renamed from: i  reason: collision with root package name */
    float f4470i;

    /* renamed from: j  reason: collision with root package name */
    private int f4471j;

    /* renamed from: k  reason: collision with root package name */
    private int f4472k;

    /* renamed from: l  reason: collision with root package name */
    private int f4473l;

    /* renamed from: m  reason: collision with root package name */
    private float f4474m;

    /* renamed from: n  reason: collision with root package name */
    private float f4475n;
    private int o;
    private ProcessEntity p;
    private float q;
    private final Context r;
    private ProcessConfig s;
    private int t;
    private int u;
    private float v;
    private float w;
    private ImageView x;
    private String y;
    private boolean z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends FrameLayout {

        /* renamed from: g  reason: collision with root package name */
        private final e f4476g;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jd.lib.flexcube.widgets.FlexProcessView$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        public class C0150a implements ValueAnimator.AnimatorUpdateListener {
            C0150a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                FlexProcessView.this.f4470i = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                a.this.invalidate();
            }
        }

        public a(@NonNull Context context) {
            super(context);
            this.f4476g = new e(this);
            setWillNotDraw(false);
        }

        private void d(Canvas canvas) {
            if (FlexProcessView.this.y == null || FlexProcessView.this.f4468g == null) {
                return;
            }
            if (FlexProcessView.this.t == 1) {
                float f2 = FlexProcessView.this.v;
                FlexProcessView flexProcessView = FlexProcessView.this;
                if (f2 * flexProcessView.f4470i <= flexProcessView.f4474m) {
                    canvas.drawText(FlexProcessView.this.y, FlexProcessView.this.f4475n + FlexProcessView.this.E, FlexProcessView.this.o, FlexProcessView.this.f4468g);
                    return;
                }
                String str = FlexProcessView.this.y;
                float f3 = FlexProcessView.this.v;
                FlexProcessView flexProcessView2 = FlexProcessView.this;
                canvas.drawText(str, (f3 * flexProcessView2.f4470i) + flexProcessView2.w, FlexProcessView.this.o, FlexProcessView.this.f4468g);
                return;
            }
            float f4 = FlexProcessView.this.v;
            FlexProcessView flexProcessView3 = FlexProcessView.this;
            if ((f4 * flexProcessView3.f4470i) + flexProcessView3.f4475n > FlexProcessView.this.f4472k) {
                canvas.drawText(FlexProcessView.this.y, ((FlexProcessView.this.f4472k - FlexProcessView.this.f4475n) - FlexProcessView.this.E) - FlexProcessView.this.A, FlexProcessView.this.o, FlexProcessView.this.f4468g);
                return;
            }
            float f5 = FlexProcessView.this.v;
            FlexProcessView flexProcessView4 = FlexProcessView.this;
            if (f5 * flexProcessView4.f4470i > flexProcessView4.f4474m + FlexProcessView.this.E + FlexProcessView.this.A) {
                String str2 = FlexProcessView.this.y;
                float f6 = FlexProcessView.this.v;
                FlexProcessView flexProcessView5 = FlexProcessView.this;
                canvas.drawText(str2, (f6 * flexProcessView5.f4470i) + flexProcessView5.w, FlexProcessView.this.o, FlexProcessView.this.f4468g);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            e eVar;
            if (FlexProcessView.this.p == null || FlexProcessView.this.s == null || (eVar = this.f4476g) == null) {
                return;
            }
            eVar.j(FlexProcessView.this.s.cfInfo, FlexProcessView.this.q, FlexProcessView.this.getLayoutParamsHeight() >> 1);
            setBackgroundColor(com.jd.lib.flexcube.iwidget.b.a.a(FlexProcessView.this.s.progressInfo.barBgColor, 0));
            if (FlexProcessView.this.s.frameInfo != null) {
                int f2 = b.f(FlexProcessView.this.s.frameInfo.size, 0);
                FlexProcessView flexProcessView = FlexProcessView.this;
                flexProcessView.f4471j = b.d(f2, flexProcessView.q);
                if (FlexProcessView.this.f4471j == 0 && f2 > 0) {
                    FlexProcessView.this.f4471j = 1;
                }
            }
            this.f4476g.m(FlexProcessView.this.s.frameInfo, com.jd.lib.flexcube.iwidget.b.a.a(FlexProcessView.this.s.frameInfo.color, 0), FlexProcessView.this.q);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(500L);
            ofFloat.addUpdateListener(new C0150a());
            ofFloat.start();
        }

        public void c(Canvas canvas) {
            if (FlexProcessView.this.s == null || FlexProcessView.this.s.cfInfo == null || FlexProcessView.this.f4469h == null || FlexProcessView.this.D == null) {
                return;
            }
            if (FlexProcessView.this.C == null) {
                FlexProcessView.this.C = new Path();
            }
            FlexProcessView.this.C.reset();
            FlexProcessView.this.C.addRoundRect(new RectF(0.0f, 0.0f, FlexProcessView.this.v * FlexProcessView.this.f4470i, r3.s.getH(FlexProcessView.this.q)), FlexProcessView.this.D, Path.Direction.CCW);
            canvas.drawPath(FlexProcessView.this.C, FlexProcessView.this.f4469h);
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            this.f4476g.b(canvas);
            super.draw(canvas);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            c(canvas);
            d(canvas);
            FlexProcessView.this.B();
        }

        @Override // android.view.View
        public void onDrawForeground(Canvas canvas) {
            super.onDrawForeground(canvas);
            this.f4476g.h(canvas);
        }
    }

    public FlexProcessView(@NonNull Context context) {
        this(context, null);
    }

    private void A() {
        ImageView imageView = this.x;
        if (imageView != null && indexOfChild(imageView) >= 0) {
            removeView(this.x);
        }
        this.x = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        ImageView imageView;
        if (this.u == 0 || this.s == null || (imageView = this.x) == null || imageView.getLayoutParams() == null) {
            return;
        }
        int i2 = ((FrameLayout.LayoutParams) this.x.getLayoutParams()).leftMargin;
        float f2 = this.v;
        float f3 = this.f4470i;
        float f4 = this.f4474m;
        if (f2 * f3 > this.f4472k - f4) {
            ((FrameLayout.LayoutParams) this.x.getLayoutParams()).leftMargin = this.f4472k - ((int) this.f4475n);
        } else if (f2 * f3 < f4) {
            ((FrameLayout.LayoutParams) this.x.getLayoutParams()).leftMargin = 0;
        } else {
            ((FrameLayout.LayoutParams) this.x.getLayoutParams()).leftMargin = (int) ((this.v * this.f4470i) - this.f4474m);
        }
        if (i2 != ((FrameLayout.LayoutParams) this.x.getLayoutParams()).leftMargin) {
            requestLayout();
        }
    }

    private void C(Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        if (iWidgetCommunication.getStateBundle() == null) {
            return;
        }
        Serializable serializable = iWidgetCommunication.getStateBundle().getSerializable(this.p.dataPath.clickEvent);
        ClickEvent clickEvent = serializable instanceof ClickEvent ? (ClickEvent) serializable : null;
        if (clickEvent == null) {
            clickEvent = com.jd.lib.flexcube.widgets.b.b.a(map, this.p.dataPath.clickEvent);
        }
        if (clickEvent != null) {
            a.b bVar = new a.b(getContext(), clickEvent);
            bVar.a(iWidgetCommunication.getBabelScope());
            setOnClickListener(bVar.b());
            iWidgetCommunication.getStateBundle().putSerializable(this.p.dataPath.clickEvent, clickEvent);
            return;
        }
        setClickable(false);
    }

    private int v() {
        Paint.FontMetricsInt fontMetricsInt = this.f4468g.getFontMetricsInt();
        int i2 = fontMetricsInt.bottom;
        return (this.s.getH(this.q) >> 1) + (((i2 - fontMetricsInt.top) / 2) - i2);
    }

    private int w() {
        float f2;
        float f3;
        if (this.t > 0) {
            float f4 = this.v;
            float f5 = this.f4474m;
            int i2 = this.u;
            if (f4 < i2 * f5) {
                f2 = this.f4472k - (this.f4475n * i2);
                f3 = this.E;
            } else {
                return (int) (((this.f4472k - f4) - (f5 * i2)) - this.E);
            }
        } else {
            float f6 = this.v;
            int i3 = this.f4472k;
            float f7 = this.f4474m;
            int i4 = this.u;
            if (f6 > i3 - (i4 * f7)) {
                f2 = i3 - (this.f4475n * i4);
                f3 = this.E;
            } else {
                f2 = f6 - (f7 * i4);
                f3 = this.E;
            }
        }
        return (int) (f2 - f3);
    }

    private void x(float f2) {
        if (this.D == null) {
            this.D = new float[8];
        }
        CfInfo cfInfo = this.s.cfInfo;
        float f3 = cfInfo.radiusLT * f2;
        float f4 = cfInfo.radiusRT * f2;
        float f5 = cfInfo.radiusRB * f2;
        float f6 = cfInfo.radiusLB * f2;
        float[] fArr = this.D;
        fArr[0] = f3;
        fArr[1] = f3;
        fArr[2] = f4;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f5;
        fArr[6] = f6;
        fArr[7] = f6;
    }

    private void y(float f2) {
        if (this.f4469h == null) {
            this.f4469h = new Paint();
        }
        this.f4469h.setAntiAlias(true);
        this.f4469h.setTextSize(this.s.fontInfo.size * f2);
        this.f4469h.setColor(com.jd.lib.flexcube.iwidget.b.a.a(this.s.progressInfo.barColor, 0));
    }

    private void z(String str, float f2) {
        if (this.f4468g == null) {
            Paint paint = new Paint();
            this.f4468g = paint;
            paint.setAntiAlias(true);
        }
        this.f4468g.setTextSize(this.s.fontInfo.size * f2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f4468g.setColor(com.jd.lib.flexcube.iwidget.b.a.a(str, 0));
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    /* renamed from: D  reason: merged with bridge method [inline-methods] */
    public void updateStyle(@NonNull ProcessEntity processEntity, float f2) {
        ProcessConfig processConfig;
        if (processEntity != null && (processConfig = processEntity.config) != null && processConfig.progressInfo != null) {
            clear();
            this.p = processEntity;
            ProcessConfig processConfig2 = processEntity.config;
            this.s = processConfig2;
            this.q = f2;
            this.f4472k = processConfig2.getW(f2);
            this.f4473l = (int) Math.max(this.s.getH(f2), this.s.progressInfo.iconHeight * f2);
            this.E = f2 * 9.0f;
            return;
        }
        clear();
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void clear() {
        this.u = 0;
        this.f4470i = 0.0f;
        this.D = null;
        removeAllViews();
        this.x = null;
        this.B = null;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsHeight() {
        ProcessConfig processConfig = this.s;
        if (processConfig == null || processConfig.progressInfo == null) {
            return 0;
        }
        return (int) Math.max(processConfig.getH(this.q), this.s.progressInfo.iconHeight * this.q);
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public int getLayoutParamsWidth() {
        ProcessConfig processConfig = this.s;
        if (processConfig != null) {
            return processConfig.getW(this.q);
        }
        return 0;
    }

    @Override // com.jd.lib.flexcube.iwidget.ui.IWidget
    public void updateContent(@NonNull Map<String, String> map, IWidgetCommunication iWidgetCommunication) {
        ProcessDataPath processDataPath;
        ProcessEntity processEntity = this.p;
        if (processEntity != null && (processDataPath = processEntity.dataPath) != null && this.s != null) {
            try {
                float parseFloat = Float.parseFloat(com.jd.lib.flexcube.widgets.b.b.d(map, processDataPath.progress));
                this.y = com.jd.lib.flexcube.widgets.b.b.d(map, this.p.dataPath.text);
                if (TextUtils.isEmpty(this.p.dataPath.clickEvent)) {
                    setClickable(false);
                } else {
                    C(map, iWidgetCommunication);
                }
                boolean z = !TextUtils.isEmpty(this.s.progressInfo.iconUrl);
                ProcessConfig processConfig = this.s;
                ProcessInfo processInfo = processConfig.progressInfo;
                int i2 = processInfo.iconWidth;
                if (z & (i2 > 0) & (processInfo.iconHeight > 0)) {
                    this.u = 1;
                    float f2 = i2 * this.q;
                    this.f4475n = f2;
                    this.f4474m = f2 / 2.0f;
                } else {
                    this.u = 0;
                    this.f4474m = 0.0f;
                }
                this.t = parseFloat >= 50.0f ? -1 : 1;
                boolean z2 = "1".equals(processConfig.showNumber) && !TextUtils.isEmpty(this.y);
                this.z = z2;
                this.v = (this.f4472k * parseFloat) / 100.0f;
                if (z2) {
                    z(this.t < 0 ? this.s.fontInfo.color : this.s.progressInfo.barColor, this.q);
                    this.A = f.b(this.f4468g, this.y);
                    if (this.A > w()) {
                        String str = this.y.substring(0, this.f4468g.breakText(this.y, true, r7 - f.b(this.f4468g, "..."), null)) + "...";
                        this.y = str;
                        this.A = f.b(this.f4468g, str);
                    }
                    float f3 = this.f4474m + this.E;
                    int i3 = this.t;
                    this.w = (f3 * i3) + (i3 < 0 ? -this.A : 0);
                    this.o = v();
                }
                y(this.q);
                x(this.q);
                if (this.B == null) {
                    this.B = new a(this.r);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.s.getH(this.q));
                    layoutParams.topMargin = this.u == 1 ? (this.f4473l - this.s.getH(this.q)) >> 1 : 0;
                    addView(this.B, layoutParams);
                    this.B.e();
                }
                if (this.u == 1) {
                    ImageView imageView = this.x;
                    if (imageView == null) {
                        ImageView newImageView = ImageLoad.newImageView(getContext());
                        this.x = newImageView;
                        newImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        View view = this.x;
                        ProcessInfo processInfo2 = this.s.progressInfo;
                        float f4 = this.q;
                        addView(view, new FrameLayout.LayoutParams((int) (processInfo2.iconWidth * f4), (int) (processInfo2.iconHeight * f4)));
                        CommonServiceUtil.displayImage(this.s.progressInfo.iconUrl, this.x);
                    } else {
                        ProcessInfo processInfo3 = this.s.progressInfo;
                        float f5 = this.q;
                        imageView.setLayoutParams(new FrameLayout.LayoutParams((int) (processInfo3.iconWidth * f5), (int) (processInfo3.iconHeight * f5)));
                    }
                } else {
                    A();
                }
                a aVar = this.B;
                if (aVar != null) {
                    aVar.f();
                    return;
                }
                return;
            } catch (Exception unused) {
                clear();
                return;
            }
        }
        clear();
    }

    public FlexProcessView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexProcessView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4470i = 0.0f;
        this.t = 1;
        this.u = 0;
        this.r = context;
    }
}
