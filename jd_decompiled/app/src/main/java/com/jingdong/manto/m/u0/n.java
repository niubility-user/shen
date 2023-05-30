package com.jingdong.manto.m.u0;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import java.io.Serializable;

/* loaded from: classes15.dex */
public class n extends Paint implements Serializable {
    private float b;

    /* renamed from: c  reason: collision with root package name */
    private String f13736c;
    private int d;

    /* renamed from: g  reason: collision with root package name */
    private int f13739g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f13740h;
    public a a = a.NORMAL;

    /* renamed from: e  reason: collision with root package name */
    private float f13737e = Float.MAX_VALUE;

    /* renamed from: f  reason: collision with root package name */
    private int f13738f = Integer.MAX_VALUE;

    /* loaded from: classes15.dex */
    public enum a {
        NORMAL,
        TOP,
        BOTTOM,
        MIDDLE
    }

    public n() {
        a();
    }

    private void a() {
        this.b = 0.0f;
        this.f13739g = 0;
        this.f13736c = null;
        this.d = 0;
        float f2 = this.f13737e;
        if (f2 == Float.MAX_VALUE) {
            float alpha = getAlpha() / 255.0f;
            this.b = alpha;
            this.f13737e = alpha;
        } else {
            this.b = f2;
        }
        int i2 = this.f13738f;
        if (i2 != Integer.MAX_VALUE) {
            this.f13739g = i2;
            return;
        }
        int color = getColor();
        this.f13739g = color;
        this.f13738f = color;
    }

    public final n a(n nVar) {
        nVar.setColor(getColor());
        nVar.setFlags(getFlags());
        nVar.setDither(isDither());
        Shader shader = getShader();
        if (shader != null) {
            nVar.setShader(shader);
        }
        nVar.setStrokeJoin(getStrokeJoin());
        nVar.setStrokeMiter(getStrokeMiter());
        nVar.setStrokeWidth(getStrokeWidth());
        nVar.setStrokeCap(getStrokeCap());
        nVar.setStyle(getStyle());
        nVar.setTextSize(getTextSize());
        nVar.setTextAlign(getTextAlign());
        nVar.setTypeface(getTypeface());
        nVar.a = this.a;
        return nVar;
    }

    public final void a(float f2, boolean z) {
        this.b = f2;
        super.setAlpha((int) (f2 * 255.0f));
        this.f13740h = z;
        setColor(this.f13739g);
    }

    public final void a(int i2) {
        this.d = i2;
        setTypeface(Typeface.create(this.f13736c, i2));
    }

    public final void a(String str) {
        this.f13736c = str;
        setTypeface(Typeface.create(str, this.d));
    }

    public final n b() {
        n nVar = new n();
        nVar.setColor(getColor());
        nVar.setFlags(getFlags());
        nVar.setDither(isDither());
        Shader shader = getShader();
        if (shader != null) {
            nVar.setShader(shader);
        }
        nVar.setStrokeJoin(getStrokeJoin());
        nVar.setStrokeMiter(getStrokeMiter());
        nVar.setStrokeWidth(getStrokeWidth());
        nVar.setStrokeCap(getStrokeCap());
        nVar.setStyle(getStyle());
        nVar.setTextSize(getTextSize());
        nVar.setTextAlign(getTextAlign());
        nVar.setTypeface(getTypeface());
        nVar.a = this.a;
        return nVar;
    }

    public final void c() {
        reset();
        a();
    }

    @Override // android.graphics.Paint
    public final void reset() {
        super.reset();
        this.a = a.NORMAL;
        this.f13739g = -16777216;
        if (this.f13740h) {
            a(this.b, true);
        }
    }

    @Override // android.graphics.Paint
    public final void setColor(int i2) {
        this.f13739g = i2;
        super.setColor((i2 & ViewCompat.MEASURED_SIZE_MASK) | ((((int) (Color.alpha(i2) * this.b)) & 255) << 24));
    }
}
