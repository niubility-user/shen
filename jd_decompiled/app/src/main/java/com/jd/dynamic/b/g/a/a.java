package com.jd.dynamic.b.g.a;

import com.jd.dynamic.b.g.c;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0019\u0010\tJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001e\u0010\u0011\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0014\u001a\n \u0010*\u0004\u0018\u00010\u00130\u00138\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018\u00a8\u0006\u001a"}, d2 = {"Lcom/jd/dynamic/b/g/a/a;", "Ljava/util/concurrent/LinkedBlockingDeque;", "Ljava/lang/Runnable;", com.jingdong.app.mall.e.a, "", "offer", "(Ljava/lang/Runnable;)Z", "", "tikSelf", "()V", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "highLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "highQueue", "Ljava/util/concurrent/LinkedBlockingDeque;", "Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;", "kotlin.jvm.PlatformType", "highRead", "Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;", "Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;", "highWrite", "Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;", "Lcom/jd/dynamic/lib/dythread/impl/DYEmptyTask;", "task", "Lcom/jd/dynamic/lib/dythread/impl/DYEmptyTask;", "<init>", "lib_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class a extends LinkedBlockingDeque<Runnable> {
    private final ReentrantReadWriteLock a;
    private final ReentrantReadWriteLock.WriteLock b;

    /* renamed from: c  reason: collision with root package name */
    private final ReentrantReadWriteLock.ReadLock f1760c;
    private final LinkedBlockingDeque<Runnable> d;

    /* renamed from: e  reason: collision with root package name */
    private final c f1761e;

    public a() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.a = reentrantReadWriteLock;
        this.b = reentrantReadWriteLock.writeLock();
        this.f1760c = reentrantReadWriteLock.readLock();
        this.d = new LinkedBlockingDeque<>();
        this.f1761e = new c();
    }

    public int a() {
        return super.size();
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.Queue, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public boolean offer(@Nullable Runnable runnable) {
        if (runnable instanceof com.jd.dynamic.b.g.c) {
            if (((com.jd.dynamic.b.g.c) runnable).getPriority() != c.a.LOW) {
                addFirst(runnable);
                return true;
            }
            addLast(runnable);
            return true;
        }
        return super.offer(runnable);
    }

    public boolean b(Runnable runnable) {
        return super.contains(runnable);
    }

    public boolean c(Runnable runnable) {
        return super.remove(runnable);
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
    public final boolean contains(Object obj) {
        if (obj != null ? obj instanceof Runnable : true) {
            return b((Runnable) obj);
        }
        return false;
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, java.util.Deque
    public final boolean remove(Object obj) {
        if (obj != null ? obj instanceof Runnable : true) {
            return c((Runnable) obj);
        }
        return false;
    }

    @Override // java.util.concurrent.LinkedBlockingDeque, java.util.AbstractCollection, java.util.Collection, java.util.concurrent.BlockingDeque, java.util.Deque
    public final int size() {
        return a();
    }
}
