package com.jd.dynamic.b.g;

import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes13.dex */
public abstract class c implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    private long f1767g;

    /* renamed from: h  reason: collision with root package name */
    private final AtomicBoolean f1768h = new AtomicBoolean(false);
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    private a f1769i = a.LOW;

    /* loaded from: classes13.dex */
    public enum a {
        LOW,
        NORMAL,
        HIGH,
        CRITICAL
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(@NotNull a aVar) {
        this.f1769i = aVar;
    }

    public final void cancel() {
        this.f1768h.set(true);
    }

    public abstract void execute();

    public final long getId() {
        return this.f1767g;
    }

    @NotNull
    public final a getPriority() {
        return this.f1769i;
    }

    public final long getTaskId$lib_release() {
        return this.f1767g;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f1768h.get()) {
            return;
        }
        execute();
    }

    public final void setTaskId$lib_release(long j2) {
        this.f1767g = j2;
    }

    @NotNull
    public String toString() {
        return "id " + this.f1767g + " priority " + this.f1769i;
    }
}
