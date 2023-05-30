package com.jingdong.manto.m.p0.f.f.c;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes15.dex */
public abstract class a {
    protected f a;
    protected File b;
    private OutputStream d;

    /* renamed from: c  reason: collision with root package name */
    private final ExecutorService f13545c = Executors.newSingleThreadExecutor();

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f13546e = new RunnableC0601a();

    /* renamed from: com.jingdong.manto.m.p0.f.f.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class RunnableC0601a implements Runnable {
        RunnableC0601a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a aVar = a.this;
                aVar.a.a(aVar.d);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            } catch (IllegalStateException e3) {
                throw new RuntimeException("AudioRecord state has uninitialized state", e3);
            }
        }
    }

    private OutputStream a(File file) {
        if (file != null) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e2) {
                throw new RuntimeException("could not build OutputStream from this file " + file.getName(), e2);
            }
        }
        throw new RuntimeException("file is null !");
    }

    public void a() {
        this.a.a().a(false);
    }

    public void a(f fVar) {
        this.a = fVar;
    }

    public void b() {
        this.a.a().a(true);
        this.f13545c.submit(this.f13546e);
    }

    public void b(File file) {
        this.b = file;
    }

    public void c() {
        this.d = a(this.b);
        this.f13545c.submit(this.f13546e);
    }

    public void d() {
        this.a.stop();
        this.d.flush();
        this.d.close();
    }
}
