package com.jingdong.manto.q;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import com.jingdong.sdk.jweb.JDWebView;

/* loaded from: classes16.dex */
public class a extends FrameLayout {
    View a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    View f13997c;
    FrameLayout d;

    /* renamed from: e  reason: collision with root package name */
    FrameLayout f13998e;

    /* renamed from: f  reason: collision with root package name */
    public boolean f13999f;

    /* renamed from: g  reason: collision with root package name */
    boolean f14000g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f14001h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f14002i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f14003j;

    /* renamed from: k  reason: collision with root package name */
    private int f14004k;

    /* renamed from: l  reason: collision with root package name */
    private int f14005l;

    /* renamed from: m  reason: collision with root package name */
    private int f14006m;

    /* renamed from: n  reason: collision with root package name */
    private ObjectAnimator f14007n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.q.a$a  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0656a implements ValueAnimator.AnimatorUpdateListener {
        C0656a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            a.this.b((int) ((Float) valueAnimator.getAnimatedValue()).floatValue());
        }
    }

    public a(Context context) {
        super(context);
        this.f13999f = true;
        this.f14000g = false;
        this.f14001h = false;
        this.f14002i = false;
        this.f14003j = false;
        this.f14007n = null;
        setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.b = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void a(int i2) {
        int translationY = (int) this.f13998e.getTranslationY();
        if (translationY != i2) {
            ObjectAnimator objectAnimator = this.f14007n;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f13998e, "translationY", translationY, i2);
            ofFloat.setDuration(Math.min((Math.abs(translationY - i2) / getLoadingContentHeight()) * 250.0f, 250L));
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ofFloat.start();
            ofFloat.addUpdateListener(new C0656a());
            this.f14007n = ofFloat;
        }
    }

    private boolean a() {
        View view = this.a;
        return view instanceof JDWebView ? ((JDWebView) view).isOverScrollStart() : view.getScrollY() == 0;
    }

    public final void a(boolean z) {
        this.f13999f = !z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b() {
        a(getLoadingContentHeight());
        if (!this.f14001h) {
            c();
        }
        this.f14002i = true;
        this.f14001h = true;
        this.f14003j = true;
    }

    protected void b(int i2) {
    }

    protected void c() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void d() {
        a(0);
        if (this.f14001h) {
            e();
        }
        this.f14002i = false;
        this.f14001h = false;
        this.f14003j = false;
    }

    protected void e() {
    }

    protected int getLoadingContentHeight() {
        return this.f13997c.getHeight();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f13999f) {
            return this.f14003j;
        }
        int action = motionEvent.getAction();
        if (action != 3 && action != 1) {
            if (this.f14002i) {
                return true;
            }
            if (action != 0) {
                if (action == 2 && a()) {
                    int x = ((int) motionEvent.getX()) - this.f14004k;
                    int y = ((int) motionEvent.getY()) - this.f14005l;
                    if (Math.abs(y) > this.b && Math.abs(y) > Math.abs(x) && y > 0) {
                        this.f14002i = true;
                        return true;
                    }
                }
            } else if (a()) {
                this.f14004k = (int) motionEvent.getX();
                this.f14005l = (int) motionEvent.getY();
                this.f14006m = (int) motionEvent.getY();
            }
        }
        return false;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f13999f) {
            if (this.f14003j) {
                d();
            }
            return this.f14003j;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f14006m = (int) motionEvent.getY();
            return true;
        }
        if (action != 1) {
            if (action == 2) {
                int y = (((int) motionEvent.getY()) - this.f14006m) >> 1;
                int height = getHeight();
                if (y > height) {
                    y = height;
                }
                if (this.f14001h) {
                    y += getLoadingContentHeight();
                }
                int max = Math.max(y, 0);
                this.f13998e.setTranslationY(Math.min(getHeight(), max));
                b(max);
                return true;
            } else if (action != 3) {
                return false;
            }
        }
        if (this.f13998e.getTranslationY() <= this.f13997c.getHeight() || !this.f14000g) {
            d();
        } else {
            b();
        }
        return true;
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i2) {
        this.d.setBackgroundColor(i2);
    }
}
