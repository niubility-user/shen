package com.tencent.mapsdk.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Hashtable;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class bc {

    /* renamed from: e  reason: collision with root package name */
    public static final int f16322e = 0;
    private int a;
    private a<String> b;

    /* renamed from: c  reason: collision with root package name */
    private Hashtable<String, Integer> f16323c = new Hashtable<>();
    private IntBuffer d;

    /* loaded from: classes9.dex */
    public static class a<E> {
        private int a;
        private Object[] b;
        private int d = 0;

        /* renamed from: c  reason: collision with root package name */
        private int f16324c = 0;

        public a(int i2) {
            this.a = i2;
            this.b = new Object[i2];
        }

        private void e() {
            this.d = 0;
            this.f16324c = 0;
        }

        public void a() {
            e();
            int i2 = 0;
            while (true) {
                Object[] objArr = this.b;
                if (i2 >= objArr.length) {
                    return;
                }
                objArr[i2] = null;
                i2++;
            }
        }

        public boolean a(E e2) {
            if (c()) {
                return false;
            }
            int i2 = this.d % this.a;
            this.d = i2;
            Object[] objArr = this.b;
            this.d = i2 + 1;
            objArr[i2] = e2;
            return true;
        }

        public boolean b() {
            return this.d == this.f16324c;
        }

        public boolean c() {
            return (this.d + 1) % this.a == this.f16324c;
        }

        public E d() {
            if (b()) {
                return null;
            }
            int i2 = this.f16324c % this.a;
            this.f16324c = i2;
            Object[] objArr = this.b;
            E e2 = (E) objArr[i2];
            objArr[i2] = null;
            this.f16324c = i2 + 1;
            return e2;
        }
    }

    public bc(int i2) {
        this.a = i2;
        this.b = new a<>(i2);
        a();
    }

    private synchronized void a() {
        if (this.d == null) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(this.a * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            this.d = allocateDirect.asIntBuffer();
        }
    }

    public synchronized int a(String str) {
        Integer num = this.f16323c.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public synchronized void a(String str, int i2) {
        if (this.f16323c.size() == this.a) {
            a();
            if (this.d == null) {
                return;
            }
            Integer remove = this.f16323c.remove(this.b.d());
            if (this.d.position() < this.a) {
                this.d.put(remove.intValue());
            }
        }
        this.b.a(str);
        this.f16323c.put(str, Integer.valueOf(i2));
    }

    public synchronized void a(GL10 gl10) {
        IntBuffer intBuffer = this.d;
        if (intBuffer == null) {
            return;
        }
        int position = intBuffer.position();
        if (position > 0) {
            this.d.rewind();
            gl10.glDeleteTextures(position, this.d);
            this.d.clear();
        }
    }

    public synchronized void b() {
        this.f16323c.clear();
        this.b.a();
        IntBuffer intBuffer = this.d;
        if (intBuffer != null) {
            intBuffer.clear();
        }
    }

    public synchronized void b(GL10 gl10) {
        if (this.d != null) {
            Iterator<String> it = this.f16323c.keySet().iterator();
            while (it.hasNext()) {
                this.d.put(this.f16323c.get(it.next()).intValue());
            }
            a(gl10);
        }
        this.f16323c.clear();
        this.b.a();
    }
}
