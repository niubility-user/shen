package com.jingdong.manto.q;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.manto.R;

/* loaded from: classes16.dex */
public final class t extends com.jingdong.manto.q.a {
    c o;
    b p;
    private boolean q;
    private LinearLayout r;
    private View s;
    ImageView t;
    ImageView u;
    ImageView v;
    private long w;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            t.this.d();
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a(int i2);
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    private t(Context context) {
        super(context);
    }

    public t(Context context, r rVar) {
        this(context);
        this.q = false;
        this.w = 0L;
        rVar.f12988c = this;
        rVar.setOnTouchListener();
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.manto_three_point_pull_down_view_layout, (ViewGroup) this, false);
        this.r = linearLayout;
        this.f13997c = linearLayout;
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.d = frameLayout;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.d.addView(this.r);
        this.a = rVar;
        FrameLayout frameLayout2 = new FrameLayout(getContext());
        this.f13998e = frameLayout2;
        frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.f13998e.addView(this.a);
        addView(this.d);
        addView(this.f13998e);
        TextView textView = (TextView) findViewById(R.id.tv_loading_title);
        this.s = findViewById(R.id.ll_points);
        this.t = (ImageView) findViewById(R.id.iv_left_point);
        this.u = (ImageView) findViewById(R.id.iv_middle_point);
        this.v = (ImageView) findViewById(R.id.iv_right_point);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnimationDrawable a(int i2, float[] fArr) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.addFrame(a(i2, fArr[0]), 0);
        animationDrawable.addFrame(a(i2, fArr[1]), 300);
        animationDrawable.addFrame(a(i2, fArr[2]), 300);
        animationDrawable.addFrame(a(i2, fArr[3]), 300);
        return animationDrawable;
    }

    private static Drawable a(int i2, float f2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(32);
        shapeDrawable.setIntrinsicWidth(32);
        shapeDrawable.getPaint().setColor(i2);
        shapeDrawable.getPaint().setAlpha((int) (f2 * 255.0f));
        return shapeDrawable;
    }

    @Override // com.jingdong.manto.q.a
    protected final void b(int i2) {
        b bVar = this.p;
        if (bVar != null) {
            bVar.a(i2);
        }
        if (this.q) {
            if (i2 > this.r.getHeight()) {
                i2 = this.r.getHeight();
            }
            this.r.setTranslationY(i2 - r0.getHeight());
        }
    }

    @Override // com.jingdong.manto.q.a
    protected final void c() {
        ((AnimationDrawable) this.t.getDrawable()).start();
        ((AnimationDrawable) this.u.getDrawable()).start();
        ((AnimationDrawable) this.v.getDrawable()).start();
        c cVar = this.o;
        if (cVar != null) {
            cVar.a();
        }
        this.w = System.currentTimeMillis();
    }

    @Override // com.jingdong.manto.q.a
    protected final void e() {
        f();
    }

    public final void f() {
        ((AnimationDrawable) this.t.getDrawable()).stop();
        ((AnimationDrawable) this.t.getDrawable()).selectDrawable(0);
        ((AnimationDrawable) this.u.getDrawable()).stop();
        ((AnimationDrawable) this.u.getDrawable()).selectDrawable(0);
        ((AnimationDrawable) this.v.getDrawable()).stop();
        ((AnimationDrawable) this.v.getDrawable()).selectDrawable(0);
    }

    public final void g() {
        long currentTimeMillis = System.currentTimeMillis() - this.w;
        if (currentTimeMillis < 1000) {
            postDelayed(new a(), 1000 - currentTimeMillis);
        } else {
            d();
        }
    }

    @Override // com.jingdong.manto.q.a
    protected final int getLoadingContentHeight() {
        return this.r.getHeight();
    }

    public final void setLoadingPointsVisibility(boolean z) {
        this.q = z;
        this.f14000g = z;
        this.s.setVisibility(z ? 0 : 4);
    }
}
