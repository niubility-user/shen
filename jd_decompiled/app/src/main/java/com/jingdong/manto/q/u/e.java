package com.jingdong.manto.q.u;

import android.app.Presentation;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.m;

/* loaded from: classes16.dex */
public class e {
    private VirtualDisplay a;
    private PresentationC0664e b;

    /* renamed from: c */
    private FrameLayout f14117c;
    private Surface d;

    /* renamed from: e */
    private Context f14118e;

    /* renamed from: f */
    private View f14119f;

    /* renamed from: g */
    private int f14120g;

    /* renamed from: h */
    private int f14121h;

    /* renamed from: i */
    private boolean f14122i;

    /* renamed from: j */
    private int f14123j = 0;

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
            e.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.c();
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        b() {
            e.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.c();
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Runnable {
        final /* synthetic */ float[] a;
        final /* synthetic */ boolean b;

        /* renamed from: c */
        final /* synthetic */ int f14124c;

        c(float[] fArr, boolean z, int i2) {
            e.this = r1;
            this.a = fArr;
            this.b = z;
            this.f14124c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.a(this.a, this.b, this.f14124c);
        }
    }

    /* loaded from: classes16.dex */
    public class d implements Runnable {
        final /* synthetic */ MotionEvent a;

        d(MotionEvent motionEvent) {
            e.this = r1;
            this.a = motionEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (com.jingdong.manto.q.u.d.c()) {
                Matrix matrix = new Matrix();
                float density = 1.0f / MantoDensityUtils.getDensity(com.jingdong.manto.c.a());
                matrix.setScale(density, density);
                this.a.transform(matrix);
            }
            e.this.f14117c.dispatchTouchEvent(this.a);
        }
    }

    /* renamed from: com.jingdong.manto.q.u.e$e */
    /* loaded from: classes16.dex */
    public class PresentationC0664e extends Presentation {
        private View a;
        private boolean b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PresentationC0664e(Context context, Display display) {
            super(context, display);
            e.this = r1;
            this.b = false;
            getWindow().setFlags(8, 8);
        }

        public void a(View view) {
            this.a = view;
        }

        @Override // android.app.Dialog
        public void onCreate(Bundle bundle) {
            if (e.this.f14122i) {
                String a = m.a("canvasEmbedBackground", "");
                try {
                    getWindow().setBackgroundDrawable(new ColorDrawable(TextUtils.isEmpty(a) ? -1 : Color.parseColor(a)));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            e.this.f14117c = new FrameLayout(getContext());
            View view = this.a;
            if (view != null) {
                if (this.b) {
                    view.requestFocus();
                }
                if (this.a.getParent() != null) {
                    ((ViewGroup) this.a.getParent()).removeView(this.a);
                }
                e.this.f14117c.addView(this.a);
            }
            setContentView(e.this.f14117c);
        }
    }

    public e(Context context) {
        this.f14118e = context;
    }

    public void a(float[] fArr, boolean z, int i2) {
        FrameLayout frameLayout = this.f14117c;
        if (frameLayout == null) {
            return;
        }
        if (fArr != null) {
            int i3 = (int) fArr[2];
            int i4 = (int) fArr[3];
            ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
            if (layoutParams.width == i3 && layoutParams.height == i4) {
                return;
            }
            this.f14120g = i3;
            this.f14121h = i4;
            layoutParams.width = i3;
            layoutParams.height = i4;
            this.f14117c.requestLayout();
        }
        if (this.f14123j != i2) {
            this.f14123j = i2;
            this.f14117c.setVisibility(i2);
        }
    }

    public void c() {
        if (this.a != null) {
            return;
        }
        int i2 = this.f14118e.getResources().getDisplayMetrics().densityDpi;
        this.a = ((DisplayManager) this.f14118e.getSystemService(ViewProps.DISPLAY)).createVirtualDisplay("jdweb-vd" + hashCode(), this.f14120g, this.f14121h, i2, this.d, 0);
        PresentationC0664e presentationC0664e = new PresentationC0664e(this.f14118e, this.a.getDisplay());
        this.b = presentationC0664e;
        presentationC0664e.a(this.f14119f);
        this.b.show();
    }

    public void a() {
        PresentationC0664e presentationC0664e = this.b;
        if (presentationC0664e != null && presentationC0664e.isShowing()) {
            this.b.dismiss();
            this.b = null;
        }
        VirtualDisplay virtualDisplay = this.a;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.a = null;
        }
    }

    public void a(Surface surface) {
        this.d = surface;
        if (this.f14119f != null) {
            MantoThreadUtils.runOnUIThread(new a());
        }
    }

    public void a(View view, int i2, int i3, boolean z) {
        this.f14119f = view;
        this.f14120g = i2;
        this.f14121h = i3;
        if (!z || this.d == null) {
            return;
        }
        MantoThreadUtils.runOnUIThread(new b());
    }

    public void a(boolean z) {
        this.f14122i = z;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.a == null) {
            return true;
        }
        if (!this.f14122i) {
            MantoThreadUtils.runOnUIThread(new d(motionEvent));
            return true;
        }
        if (com.jingdong.manto.q.u.d.c()) {
            Matrix matrix = new Matrix();
            float density = 1.0f / MantoDensityUtils.getDensity(com.jingdong.manto.c.a());
            matrix.setScale(density, density);
            motionEvent.transform(matrix);
        }
        this.f14117c.dispatchTouchEvent(motionEvent);
        return true;
    }

    public View b() {
        return this.f14119f;
    }

    public void b(float[] fArr, boolean z, int i2) {
        MantoThreadUtils.runOnUIThread(new c(fArr, z, i2));
    }
}
