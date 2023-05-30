package com.jd.lib.productdetail.mainimage.bigimage;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r11) {
        /*
            Method dump skipped, instructions count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.mainimage.bigimage.PdMDropDownViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
