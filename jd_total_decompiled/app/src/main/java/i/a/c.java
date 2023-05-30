package i.a;

import java.util.concurrent.ThreadFactory;

/* loaded from: classes11.dex */
public class c {

    /* loaded from: classes11.dex */
    public class a implements ThreadFactory {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ String f19705g;

        /* renamed from: h  reason: collision with root package name */
        public final /* synthetic */ boolean f19706h;

        public a(String str, boolean z) {
            this.f19705g = str;
            this.f19706h = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f19705g);
            thread.setDaemon(this.f19706h);
            return thread;
        }
    }

    public static ThreadFactory a(String str, boolean z) {
        return new a(str, z);
    }
}
