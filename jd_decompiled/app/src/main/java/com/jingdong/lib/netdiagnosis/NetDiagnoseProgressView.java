package com.jingdong.lib.netdiagnosis;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.common.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes14.dex */
public class NetDiagnoseProgressView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private Paint f12959g;

    /* renamed from: h  reason: collision with root package name */
    private int f12960h;

    /* renamed from: i  reason: collision with root package name */
    private int f12961i;

    /* renamed from: j  reason: collision with root package name */
    private int f12962j;

    /* renamed from: k  reason: collision with root package name */
    private float f12963k;

    /* renamed from: l  reason: collision with root package name */
    private float f12964l;

    /* renamed from: m  reason: collision with root package name */
    private float f12965m;

    /* renamed from: n  reason: collision with root package name */
    private ImageView f12966n;
    private c o;
    public TextView p;
    public TextView q;
    private int r;
    private int s;
    private float t;
    private Animation u;
    private Animation v;
    private Handler w;
    private int x;
    private float y;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            NetDiagnoseProgressView.this.p.setText(NetDiagnoseProgressView.this.r + "");
        }
    }

    /* loaded from: classes14.dex */
    class b implements Animation.AnimationListener {
        b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            NetDiagnoseProgressView.this.f12966n.setVisibility(4);
            NetDiagnoseProgressView.this.o.startAnimation(NetDiagnoseProgressView.this.v);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c extends View {

        /* renamed from: g  reason: collision with root package name */
        private int f12968g;

        /* renamed from: h  reason: collision with root package name */
        private float f12969h;

        public c(Context context) {
            super(context);
        }

        public synchronized void a(int i2) {
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 > NetDiagnoseProgressView.this.x) {
                i2 = NetDiagnoseProgressView.this.x;
            }
            if (i2 <= NetDiagnoseProgressView.this.x) {
                this.f12968g = i2;
                postInvalidate();
            }
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int width = getWidth() / 2;
            int i2 = (int) ((width - (NetDiagnoseProgressView.this.f12965m / 2.0f)) + ((NetDiagnoseProgressView.this.f12965m - NetDiagnoseProgressView.this.f12963k) / 2.0f));
            NetDiagnoseProgressView.this.f12959g.setStrokeWidth(NetDiagnoseProgressView.this.f12965m);
            NetDiagnoseProgressView.this.f12959g.setColor(NetDiagnoseProgressView.this.f12962j);
            NetDiagnoseProgressView.this.f12959g.setAntiAlias(true);
            float f2 = width - i2;
            float f3 = width + i2;
            RectF rectF = new RectF(NetDiagnoseProgressView.this.f12963k + f2, f2 + NetDiagnoseProgressView.this.f12963k, f3 - NetDiagnoseProgressView.this.f12963k, f3 - NetDiagnoseProgressView.this.f12963k);
            NetDiagnoseProgressView.this.f12959g.setStyle(Paint.Style.STROKE);
            if (this.f12968g != 0) {
                float f4 = (r0 * R2.attr.additionBottom) / (NetDiagnoseProgressView.this.x * 2);
                this.f12969h = f4;
                canvas.drawArc(rectF, -90.0f, f4, false, NetDiagnoseProgressView.this.f12959g);
                canvas.drawArc(rectF, 90.0f, this.f12969h, false, NetDiagnoseProgressView.this.f12959g);
            }
        }
    }

    public NetDiagnoseProgressView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void m() {
        if (OKLog.D) {
            OKLog.d("net-diagnose", "init view");
        }
        setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.f12966n = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f12966n.setLayoutParams(layoutParams);
        this.f12966n.setImageDrawable(getResources().getDrawable(R.drawable.net_diagnose_cursor));
        addView(this.f12966n);
        c cVar = new c(getContext());
        this.o = cVar;
        cVar.setLayoutParams(layoutParams);
        addView(this.o);
        this.p = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.p.setTextSize(this.t);
        this.p.setTextColor(this.s);
        this.p.setText(this.r + "");
        this.p.setLayoutParams(layoutParams2);
        this.p.setId(R.id.netDiagnoseProgressNum);
        this.p.setGravity(14);
        addView(this.p);
        this.q = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(1, this.p.getId());
        layoutParams3.addRule(4, this.p.getId());
        this.q.setLayoutParams(layoutParams3);
        this.q.setText("%");
        this.q.setTextColor(this.s);
        this.q.setTextSize(this.t / 4.0f);
        addView(this.q);
        this.w = new a();
    }

    public Animation j() {
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 359.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000L);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new AccelerateInterpolator());
        return rotateAnimation;
    }

    public Animation k() {
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(1000L);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000L);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        return animationSet;
    }

    public int l() {
        return this.r;
    }

    public synchronized void n(int i2) {
        int i3 = this.x;
        if (i2 > i3) {
            i2 = i3;
        }
        this.r = i2;
        this.o.a(i2);
        Message obtainMessage = this.w.obtainMessage(0);
        obtainMessage.arg1 = i2;
        this.w.sendMessage(obtainMessage);
    }

    public synchronized void o() {
        this.u = k();
        this.v = j();
        this.u.setAnimationListener(new b());
        this.f12966n.startAnimation(this.u);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth() / 2;
        this.f12959g.setColor(this.f12961i);
        this.f12959g.setStyle(Paint.Style.STROKE);
        this.f12959g.setStrokeWidth(this.f12963k);
        this.f12959g.setAntiAlias(true);
        canvas.drawCircle(width, width, (float) (((int) (width - (this.f12963k / 2.0f))) - 2), this.f12959g);
        this.f12959g.setColor(this.f12960h);
        this.f12959g.setStrokeWidth(this.f12964l);
        canvas.drawCircle(width, width, this.y / 2.0f, this.f12959g);
    }

    public synchronized void p() {
        Animation animation = this.u;
        if (animation != null) {
            animation.cancel();
        }
        Animation animation2 = this.v;
        if (animation2 != null) {
            animation2.cancel();
        }
    }

    public NetDiagnoseProgressView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundProgressBar);
        int i3 = R.styleable.RoundProgressBar_roundColor;
        Resources resources = getResources();
        int i4 = R.color.c_FBC0C0;
        this.f12961i = obtainStyledAttributes.getColor(i3, resources.getColor(i4));
        this.f12960h = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_inRoundColor, getResources().getColor(i4));
        int i5 = R.styleable.RoundProgressBar_roundProgressColor;
        Resources resources2 = getResources();
        int i6 = R.color.c_F23030;
        this.f12962j = obtainStyledAttributes.getColor(i5, resources2.getColor(i6));
        this.s = obtainStyledAttributes.getColor(R.styleable.RoundProgressBar_textColor, getResources().getColor(i6));
        this.t = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_textSize, 20.0f);
        this.f12965m = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_circleWidth, 4.0f);
        this.f12963k = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_roundWidth, 4.0f);
        this.f12964l = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_inRoundWidth, 4.0f);
        this.x = obtainStyledAttributes.getInteger(R.styleable.RoundProgressBar_max, 100);
        this.r = obtainStyledAttributes.getInteger(R.styleable.RoundProgressBar_progress, 0);
        this.y = obtainStyledAttributes.getDimension(R.styleable.RoundProgressBar_inRoundRadius, 0.0f);
        obtainStyledAttributes.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        obtainStyledAttributes.getInt(R.styleable.RoundProgressBar_style, 0);
        obtainStyledAttributes.recycle();
        this.f12959g = new Paint();
        m();
    }
}
