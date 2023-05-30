package com.jingdong.app.mall.home.floor.minitop.opendoor;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.SystemClock;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.r.d.b;

/* loaded from: classes4.dex */
public class OpenDoorView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f9643g;

    /* renamed from: h  reason: collision with root package name */
    private Path f9644h;

    /* renamed from: i  reason: collision with root package name */
    private Paint f9645i;

    /* renamed from: j  reason: collision with root package name */
    private LinearLayout f9646j;

    /* renamed from: k  reason: collision with root package name */
    private TextView f9647k;

    /* renamed from: l  reason: collision with root package name */
    private int f9648l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f9649m;

    private void a(int i2) {
        float height = getHeight();
        float f2 = this.f9648l;
        float d = d.d(22) + (this.f9648l << 1);
        float f3 = 0.5522848f * f2;
        this.f9644h.reset();
        this.f9644h.moveTo(0.0f, 0.0f);
        g.f(0.0f, f2, f2, f3, this.f9644h);
        float f4 = height - f2;
        this.f9644h.lineTo(f2, f4);
        g.d(f2, height, f2, f3, this.f9644h);
        this.f9644h.lineTo(d, height);
        this.f9644h.lineTo(d, 0.0f);
        this.f9644h.lineTo(0.0f, 0.0f);
        float f5 = i2;
        this.f9644h.moveTo(f5, 0.0f);
        float f6 = f5 - f2;
        g.e(0.0f, f6, f2, f3, this.f9644h);
        this.f9644h.lineTo(f6, f4);
        g.b(f6, height, f2, f3, this.f9644h);
        float f7 = f5 - d;
        this.f9644h.lineTo(f7, height);
        this.f9644h.lineTo(f7, 0.0f);
        this.f9644h.lineTo(f5, 0.0f);
    }

    public void b(boolean z) {
        if (this.f9649m) {
            return;
        }
        this.f9649m = !z;
        ViewPropertyAnimator animate = this.f9646j.animate();
        animate.cancel();
        animate.setStartDelay(z ? 300L : 0L);
        animate.alpha(z ? 1.0f : 0.0f).start();
    }

    public void c(b bVar) {
        this.f9647k.setText(String.valueOf(Math.min(9, (int) ((bVar.f10669c - (SystemClock.elapsedRealtime() - bVar.d)) / 1000))));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        if (this.f9643g != width || this.f9644h.isEmpty()) {
            this.f9643g = width;
            a(width);
        }
        canvas.drawPath(this.f9644h, this.f9645i);
        super.dispatchDraw(canvas);
    }
}
