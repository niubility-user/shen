package com.jingdong.aura.core.util.l;

/* loaded from: classes.dex */
public class a implements b {
    private final String a;

    public a(String str) {
        this.a = str;
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void a(String str) {
        com.jingdong.aura.a.b.c.E().info(this.a, str);
        if (!b()) {
        }
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void b(String str) {
        com.jingdong.aura.a.b.c.E().debug(this.a, str);
        if (!a()) {
        }
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void c(String str) {
        com.jingdong.aura.a.b.c.E().warn(this.a, str);
        if (!e()) {
        }
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void d(String str) {
        com.jingdong.aura.a.b.c.E().error(this.a, str);
        if (!c()) {
        }
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void e(String str) {
        com.jingdong.aura.a.b.c.E().verbose(this.a, str);
        if (!d()) {
        }
    }

    public a(Class<?> cls) {
        this(cls.getSimpleName());
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void a(String str, Throwable th) {
        com.jingdong.aura.a.b.c.E().error(this.a, str, th);
        if (!c()) {
        }
    }

    @Override // com.jingdong.aura.core.util.l.b
    public void b(String str, Throwable th) {
        com.jingdong.aura.a.b.c.E().warn(this.a, str, th);
        if (!e()) {
        }
    }

    public boolean c() {
        return c.a <= 6;
    }

    public boolean d() {
        return c.a <= 2;
    }

    public boolean e() {
        return c.a <= 5;
    }

    @Override // com.jingdong.aura.core.util.l.b
    public boolean a() {
        return c.a <= 3;
    }

    @Override // com.jingdong.aura.core.util.l.b
    public boolean b() {
        return c.a <= 4;
    }
}
