package com.jingdong.manto.widget.j;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* loaded from: classes16.dex */
public class b implements d {
    private int a;
    private CharSequence b;

    /* renamed from: c  reason: collision with root package name */
    private int f14514c;
    private Drawable d;

    /* renamed from: e  reason: collision with root package name */
    private int f14515e;

    /* renamed from: f  reason: collision with root package name */
    private Context f14516f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f14517g;

    /* renamed from: h  reason: collision with root package name */
    private int f14518h;

    public b(Context context, int i2) {
        this.f14516f = context;
        this.a = i2;
    }

    @Override // com.jingdong.manto.widget.j.d
    public d a(int i2) {
        this.f14515e = i2;
        return this;
    }

    public d a(Drawable drawable) {
        this.d = drawable;
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public d a(CharSequence charSequence) {
        this.b = charSequence;
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public d a(boolean z) {
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public boolean a() {
        return this.f14517g;
    }

    @Override // com.jingdong.manto.widget.j.d
    public int b() {
        return this.a;
    }

    @Override // com.jingdong.manto.widget.j.d
    public d b(int i2) {
        this.f14514c = i2;
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public d b(boolean z) {
        this.f14517g = z;
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public Drawable c() {
        Context context;
        Drawable drawable = this.d;
        if (drawable != null) {
            return drawable;
        }
        if (this.f14515e == 0 || (context = this.f14516f) == null) {
            return null;
        }
        return context.getResources().getDrawable(this.f14515e);
    }

    @Override // com.jingdong.manto.widget.j.d
    public d c(int i2) {
        this.f14518h = i2;
        return this;
    }

    @Override // com.jingdong.manto.widget.j.d
    public int getCount() {
        return this.f14518h;
    }

    @Override // com.jingdong.manto.widget.j.d
    public CharSequence getTitle() {
        Context context;
        CharSequence charSequence = this.b;
        if (charSequence != null) {
            return charSequence;
        }
        int i2 = this.f14514c;
        if (i2 == 0 || (context = this.f14516f) == null) {
            return null;
        }
        return context.getString(i2);
    }
}
