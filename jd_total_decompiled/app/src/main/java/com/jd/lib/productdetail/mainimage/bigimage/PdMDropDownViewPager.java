package com.jd.lib.productdetail.mainimage.bigimage;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes15.dex */
public class PdMDropDownViewPager extends ViewPager {
    public boolean A;
    public boolean B;
    public float C;

    /* renamed from: g  reason: collision with root package name */
    public int f4587g;

    /* renamed from: h  reason: collision with root package name */
    public int f4588h;

    /* renamed from: i  reason: collision with root package name */
    public int f4589i;

    /* renamed from: j  reason: collision with root package name */
    public float f4590j;

    /* renamed from: k  reason: collision with root package name */
    public float f4591k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public VelocityTracker f4592l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f4593m;
    @Nullable

    /* renamed from: n  reason: collision with root package name */
    public c f4594n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public float u;
    public float v;
    public float w;
    public float x;
    public float y;
    public float z;

    /* loaded from: classes15.dex */
    public class a implements ViewPager.OnPageChangeListener {
        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            PdMDropDownViewPager pdMDropDownViewPager = PdMDropDownViewPager.this;
            pdMDropDownViewPager.f4589i = i2;
            c cVar = pdMDropDownViewPager.f4594n;
            if (cVar != null) {
                ((PdBigImageActivity.c) cVar).getClass();
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f2, int i3) {
            c cVar = PdMDropDownViewPager.this.f4594n;
            if (cVar != null) {
                ((PdBigImageActivity.c) cVar).getClass();
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            c cVar = PdMDropDownViewPager.this.f4594n;
            if (cVar != null) {
                ((PdBigImageActivity.c) cVar).getClass();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager.d(com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager, boolean):boolean
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
            	... 1 more
            */
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            /*
                Method dump skipped, instructions count: 493
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager.b.onGlobalLayout():void");
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
    }

    public PdMDropDownViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4588h = 0;
        this.f4593m = true;
        this.A = true;
        this.B = false;
        this.C = 1.0f;
        b(context);
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    public static /* synthetic */ boolean d(com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager r0, boolean r1) {
        /*
            r0.getClass()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager.d(com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager, boolean):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(float f2) {
        String lowerCase = Integer.toHexString((int) (Math.min(1.0f, Math.max(0.0f, f2)) * 255.0f)).toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(lowerCase.length() < 2 ? "0" : "");
        sb.append(lowerCase);
        sb.append("ffffff");
        setBackgroundColor(Color.parseColor(sb.toString()));
    }

    private void h(float f2) {
        c cVar = this.f4594n;
        if (cVar == null || ((PdBigImageActivity.c) cVar).c() == null) {
            return;
        }
        float min = Math.min(Math.max(f2, 0.25f), 1.0f);
        ViewCompat.setScaleX(((PdBigImageActivity.c) this.f4594n).c(), min);
        ViewCompat.setScaleY(((PdBigImageActivity.c) this.f4594n).c(), min);
    }

    public final void a(float f2, float f3) {
        float f4;
        c cVar = this.f4594n;
        if (cVar == null || ((PdBigImageActivity.c) cVar).c() == null) {
            return;
        }
        this.f4588h = 1;
        float f5 = f2 - this.f4590j;
        float f6 = f3 - this.f4591k;
        float f7 = 1.0f;
        if (f6 > 0.0f) {
            f4 = 1.0f - (Math.abs(f6) / DPIUtil.getHeight());
            f7 = 1.0f - (Math.abs(f6) / (DPIUtil.getHeight() / 2));
        } else {
            f4 = 1.0f;
        }
        ViewCompat.setTranslationX(((PdBigImageActivity.c) this.f4594n).c(), f5);
        ViewCompat.setTranslationY(((PdBigImageActivity.c) this.f4594n).c(), f6);
        h(f4);
        g(f7);
        this.C = f7;
    }

    public final void b(Context context) {
        this.f4587g = ViewConfiguration.get(context).getScaledTouchSlop();
        setBackgroundColor(0);
        addOnPageChangeListener(new a());
        getViewTreeObserver().addOnGlobalLayoutListener(new b());
    }

    public void f(@Nullable c cVar) {
        this.f4594n = cVar;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (this.f4593m) {
                int action = motionEvent.getAction() & 255;
                if (action != 0) {
                    if (action == 2 && this.f4594n != null && motionEvent.getPointerCount() == 1) {
                        float rawY = motionEvent.getRawY() - this.f4591k;
                        float abs = Math.abs(motionEvent.getRawX() - this.f4590j);
                        float abs2 = Math.abs(rawY);
                        if (rawY > 0.0f && Math.sqrt((abs * abs) + (abs2 * abs2)) >= this.f4587g && abs2 > abs && ((PdBigImageActivity.c) this.f4594n).b()) {
                            if (Log.D) {
                                Log.d("DropDownViewPager", "\u4e0b\u6ed1");
                            }
                            ((PdBigImageActivity.c) this.f4594n).d(false);
                            return onTouchEvent(motionEvent);
                        } else if (Math.abs(abs) > 8.0f) {
                            return true;
                        } else {
                            return super.onInterceptTouchEvent(motionEvent);
                        }
                    }
                } else {
                    this.f4590j = motionEvent.getRawX();
                    this.f4591k = motionEvent.getRawY();
                }
                return super.onInterceptTouchEvent(motionEvent);
            }
            return false;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (r0 != 3) goto L76;
     */
    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f2;
        if (this.f4593m) {
            int action = motionEvent.getAction() & 255;
            int i2 = this.f4588h;
            if (i2 == 2) {
                return false;
            }
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        if (this.f4592l == null) {
                            this.f4592l = VelocityTracker.obtain();
                        }
                        this.f4592l.addMovement(motionEvent);
                        int rawY = (int) (motionEvent.getRawY() - this.f4591k);
                        if (rawY <= 50 && this.f4588h != 1) {
                            try {
                                return super.onTouchEvent(motionEvent);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return false;
                            }
                        } else if (this.f4589i != 1 && (rawY > 50 || this.f4588h == 1)) {
                            a(motionEvent.getRawX(), motionEvent.getRawY());
                            return true;
                        }
                    }
                }
                if (i2 != 1) {
                    try {
                        return super.onTouchEvent(motionEvent);
                    } catch (Exception unused) {
                        return false;
                    }
                }
                float rawX = motionEvent.getRawX();
                float rawY2 = motionEvent.getRawY();
                VelocityTracker velocityTracker = this.f4592l;
                if (velocityTracker != null) {
                    velocityTracker.computeCurrentVelocity(1000);
                    f2 = this.f4592l.getYVelocity();
                    VelocityTracker velocityTracker2 = this.f4592l;
                    if (velocityTracker2 != null) {
                        velocityTracker2.clear();
                        this.f4592l.recycle();
                        this.f4592l = null;
                    }
                } else {
                    f2 = 0.0f;
                }
                if (f2 < 500.0f && Math.abs(rawY2 - this.f4591k) <= DPIUtil.getHeight() / 4) {
                    this.f4588h = 2;
                    float f3 = this.f4591k;
                    if (rawY2 != f3) {
                        ValueAnimator ofFloat = ValueAnimator.ofFloat(rawY2, f3);
                        ofFloat.setDuration(300L);
                        ofFloat.addUpdateListener(new o(this, rawY2, rawX));
                        ofFloat.start();
                    } else {
                        float f4 = this.f4590j;
                        if (rawX != f4) {
                            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(rawX, f4);
                            ofFloat2.setDuration(300L);
                            ofFloat2.addUpdateListener(new p(this, rawX, rawY2));
                            ofFloat2.start();
                        }
                    }
                } else {
                    c cVar = this.f4594n;
                    if (cVar != null && this.A && !this.B) {
                        this.B = true;
                        View c2 = ((PdBigImageActivity.c) cVar).c();
                        if (c2 == null) {
                            PdBigImageActivity pdBigImageActivity = PdBigImageActivity.this;
                            if (!pdBigImageActivity.y) {
                                pdBigImageActivity.isDisposeableUnableExitAnim = true;
                                pdBigImageActivity.finish();
                            }
                        } else {
                            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(c2.getScaleX(), 0.0f);
                            ofFloat3.addUpdateListener(new l(this, c2));
                            ValueAnimator ofFloat4 = ValueAnimator.ofFloat(c2.getScaleY(), 0.0f);
                            ofFloat4.addUpdateListener(new m(this, c2));
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(300L);
                            animatorSet.play(ofFloat3).with(ofFloat4);
                            animatorSet.addListener(new n(this, "2"));
                            animatorSet.start();
                        }
                    }
                }
            } else {
                this.f4590j = motionEvent.getRawX();
                this.f4591k = motionEvent.getRawY();
                if (this.f4592l == null) {
                    this.f4592l = VelocityTracker.obtain();
                }
                this.f4592l.addMovement(motionEvent);
            }
            try {
                return super.onTouchEvent(motionEvent);
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }
}
