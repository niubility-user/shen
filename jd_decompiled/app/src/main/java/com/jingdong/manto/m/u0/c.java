package com.jingdong.manto.m.u0;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.util.Stack;

/* loaded from: classes15.dex */
public class c implements d {
    public com.jingdong.manto.utils.b b;

    /* renamed from: c */
    public e0 f13728c;

    /* renamed from: f */
    public n f13730f;

    /* renamed from: g */
    private Stack<n> f13731g;

    /* renamed from: h */
    private Stack<n> f13732h;

    /* renamed from: i */
    public Paint f13733i;
    public Paint a = new Paint();
    public boolean d = true;

    /* renamed from: e */
    public n f13729e = com.jingdong.manto.m.u0.o.k0.c0.f.c().b();

    public c(d dVar) {
        this.f13730f = this.d ? com.jingdong.manto.m.u0.o.k0.c0.e.c().b() : new n();
        this.f13729e.setStyle(Paint.Style.STROKE);
        this.f13730f.setStyle(Paint.Style.FILL);
        this.f13729e.setAntiAlias(true);
        this.f13730f.setAntiAlias(true);
        this.f13729e.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        this.f13730f.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        this.f13731g = new Stack<>();
        this.f13732h = new Stack<>();
        this.a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public final void a() {
        this.f13731g.clear();
        this.f13732h.clear();
        this.f13729e.reset();
        this.f13730f.reset();
        this.f13729e.setStyle(Paint.Style.STROKE);
        this.f13730f.setStyle(Paint.Style.FILL);
        this.f13729e.setAntiAlias(true);
        this.f13730f.setAntiAlias(true);
        this.f13729e.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
        this.f13730f.setStrokeWidth(MantoDensityUtils.convertToDeviceSizeByInt(1));
    }

    public final void b() {
        if (this.f13731g.isEmpty()) {
            return;
        }
        n nVar = this.f13729e;
        n nVar2 = this.f13730f;
        this.f13729e = this.f13731g.pop();
        this.f13730f = this.f13732h.pop();
        if (this.d) {
            if (this.f13729e != nVar) {
                com.jingdong.manto.m.u0.o.k0.c0.f.c().a(nVar);
            }
            if (this.f13730f != nVar2) {
                com.jingdong.manto.m.u0.o.k0.c0.e.c().a(nVar2);
            }
        }
    }

    public final void c() {
        n nVar = this.f13729e;
        this.f13731g.push(nVar);
        if (this.d) {
            n b = com.jingdong.manto.m.u0.o.k0.c0.f.c().b();
            this.f13729e = b;
            nVar.a(b);
        } else {
            this.f13729e = nVar.b();
        }
        if (this.f13729e == null) {
            this.f13729e = nVar;
        }
        n nVar2 = this.f13730f;
        this.f13732h.push(nVar2);
        this.f13730f = this.d ? com.jingdong.manto.m.u0.o.k0.c0.e.c().b() : nVar2.b();
        nVar2.a(this.f13730f);
        if (this.f13730f == null) {
            this.f13730f = nVar2;
        }
    }
}
