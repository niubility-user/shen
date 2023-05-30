package c.t.m.g;

import java.util.Observable;
import java.util.Observer;

/* loaded from: classes.dex */
public abstract class p3 implements Observer {
    public abstract void a(h3 h3Var);

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        if (obj instanceof h3) {
            a((h3) obj);
        }
    }
}
