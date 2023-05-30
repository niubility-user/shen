package c.t.m.g;

import java.util.Observable;

/* loaded from: classes.dex */
public class z2 {
    public static volatile z2 b;
    public b a = new b();

    /* loaded from: classes.dex */
    public static class b extends Observable {
        public b() {
        }

        @Override // java.util.Observable
        public void notifyObservers(Object obj) {
            setChanged();
            super.notifyObservers(obj);
        }
    }

    public static z2 a() {
        new StringBuilder("getDataBus thread: ").append(Thread.currentThread().getId());
        if (b == null) {
            synchronized (z2.class) {
                if (b == null) {
                    b = new z2();
                }
            }
        }
        new StringBuilder("getDataBus mInstance is null: ").append(b == null);
        return b;
    }

    public void b(p3 p3Var) {
        this.a.addObserver(p3Var);
    }

    public void c(p3 p3Var) {
        this.a.deleteObserver(p3Var);
    }
}
