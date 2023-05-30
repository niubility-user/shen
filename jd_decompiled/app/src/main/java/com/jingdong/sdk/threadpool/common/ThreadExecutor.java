package com.jingdong.sdk.threadpool.common;

import com.jingdong.sdk.threadpool.callback.RunnerTaskCallback;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class ThreadExecutor {
    public static final int TASK_PRIORITY_HIGHER = 8;
    public static final int TASK_PRIORITY_LOW = 1;
    public static final int TASK_PRIORITY_MAX = 10;
    public static final int TASK_PRIORITY_NORMAL = 5;
    private ExecutorService mExecutor;

    public ThreadExecutor(ExecutorService executorService) {
        this.mExecutor = executorService;
    }

    public Disposable post(Runnable runnable) {
        return post(runnable, 5);
    }

    public Disposable postDelay(Runnable runnable, long j2) {
        return postDelay(runnable, j2, 5);
    }

    @Deprecated
    /* loaded from: classes10.dex */
    public static class Task<V> {
        private Runnable a;
        private Callable<V> b;

        /* renamed from: c  reason: collision with root package name */
        private RunnerTaskCallback<V> f15543c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private int f15544e;

        /* renamed from: f  reason: collision with root package name */
        private long f15545f;

        public Task() {
            this.f15544e = 5;
        }

        public Callable<V> getCallable() {
            return this.b;
        }

        public RunnerTaskCallback<V> getCallback() {
            return this.f15543c;
        }

        public long getDelay() {
            return this.f15545f;
        }

        public String getName() {
            return this.d;
        }

        public int getPriority() {
            return this.f15544e;
        }

        public Runnable getRunnable() {
            return this.a;
        }

        public void setCallable(Callable<V> callable) {
            this.b = callable;
        }

        public void setCallback(RunnerTaskCallback<V> runnerTaskCallback) {
            this.f15543c = runnerTaskCallback;
        }

        public void setDelay(long j2) {
            this.f15545f = j2;
        }

        public void setName(String str) {
            this.d = str;
        }

        public void setPriority(int i2) {
            this.f15544e = i2;
        }

        public void setRunnable(Runnable runnable) {
            this.a = runnable;
        }

        public Task(Runnable runnable, RunnerTaskCallback<V> runnerTaskCallback, String str, int i2, long j2) {
            this.f15544e = 5;
            this.a = runnable;
            this.f15543c = runnerTaskCallback;
            this.d = str;
            this.f15544e = i2;
        }

        public Task(Callable<V> callable, RunnerTaskCallback<V> runnerTaskCallback, String str, int i2, long j2) {
            this.f15544e = 5;
            this.b = callable;
            this.f15543c = runnerTaskCallback;
            this.d = str;
            this.f15544e = i2;
        }
    }

    public Disposable post(Runnable runnable, int i2) {
        return post(runnable, "", i2);
    }

    public Disposable postDelay(Runnable runnable, long j2, int i2) {
        return postDelay(runnable, "", j2, i2);
    }

    @Deprecated
    /* loaded from: classes10.dex */
    public static class TaskBuilder<V> {
        private Task<V> a;

        public TaskBuilder(Runnable runnable) {
            Task<V> task = new Task<>();
            this.a = task;
            task.setRunnable(runnable);
        }

        public Task<V> build() {
            return this.a;
        }

        public TaskBuilder<V> callback(RunnerTaskCallback<V> runnerTaskCallback) {
            this.a.setCallback(runnerTaskCallback);
            return this;
        }

        public TaskBuilder<V> delay(long j2) {
            this.a.setDelay(j2);
            return this;
        }

        public TaskBuilder<V> name(String str) {
            this.a.setName(str);
            return this;
        }

        public TaskBuilder<V> priority(int i2) {
            this.a.setPriority(i2);
            return this;
        }

        public TaskBuilder(Callable<V> callable) {
            Task<V> task = new Task<>();
            this.a = task;
            task.setCallable(callable);
        }
    }

    public Disposable post(Runnable runnable, String str) {
        return post(runnable, str, 5);
    }

    public Disposable postDelay(Runnable runnable, String str, long j2) {
        return postDelay(runnable, str, j2, 5);
    }

    public Disposable post(Runnable runnable, String str, int i2) {
        return post(runnable, str, (RunnerTaskCallback) null, i2);
    }

    public Disposable postDelay(Runnable runnable, String str, long j2, int i2) {
        return postDelay(runnable, str, (RunnerTaskCallback) null, j2, i2);
    }

    public Disposable post(Runnable runnable, String str, RunnerTaskCallback runnerTaskCallback) {
        return post(runnable, str, runnerTaskCallback, 5);
    }

    public Disposable postDelay(Runnable runnable, String str, RunnerTaskCallback runnerTaskCallback, long j2) {
        return postDelay(runnable, str, runnerTaskCallback, j2, 5);
    }

    public Disposable post(Runnable runnable, String str, RunnerTaskCallback runnerTaskCallback, int i2) {
        return postDelay(runnable, str, runnerTaskCallback, 0L, i2);
    }

    public Disposable postDelay(Runnable runnable, String str, RunnerTaskCallback runnerTaskCallback, long j2, int i2) {
        return e.a().b(j2, this.mExecutor, new f(runnable, str, runnerTaskCallback, i2));
    }

    public <T> Disposable post(Callable<T> callable) {
        return post(callable, 5);
    }

    public <T> Disposable post(Callable<T> callable, int i2) {
        return post(callable, "", i2);
    }

    public <T> Disposable postDelay(Callable<T> callable, long j2) {
        return postDelay(callable, j2, 5);
    }

    public <T> Disposable post(Callable<T> callable, String str) {
        return post(callable, str, 5);
    }

    public <T> Disposable postDelay(Callable<T> callable, long j2, int i2) {
        return postDelay(callable, "", j2, i2);
    }

    public <T> Disposable post(Callable<T> callable, String str, int i2) {
        return post(callable, str, (RunnerTaskCallback) null, i2);
    }

    public <T> Disposable postDelay(Callable<T> callable, String str, long j2) {
        return postDelay(callable, str, j2, 5);
    }

    public <T> Disposable post(Callable<T> callable, String str, RunnerTaskCallback<T> runnerTaskCallback) {
        return post(callable, str, runnerTaskCallback, 5);
    }

    public <T> Disposable postDelay(Callable<T> callable, String str, long j2, int i2) {
        return postDelay(callable, str, (RunnerTaskCallback) null, j2, i2);
    }

    public <T> Disposable post(Callable<T> callable, String str, RunnerTaskCallback<T> runnerTaskCallback, int i2) {
        return postDelay(callable, str, runnerTaskCallback, 0L, i2);
    }

    public <T> Disposable postDelay(Callable<T> callable, String str, RunnerTaskCallback<T> runnerTaskCallback, long j2) {
        return postDelay(callable, str, runnerTaskCallback, j2, 5);
    }

    @Deprecated
    public <T> Disposable post(Task<T> task) {
        Runnable runnable = task.getRunnable();
        if (runnable != null) {
            return e.a().b(task.getDelay(), this.mExecutor, new f(runnable, task.getName(), task.getCallback(), task.getPriority()));
        }
        Callable<T> callable = task.getCallable();
        if (callable != null) {
            return e.a().b(task.getDelay(), this.mExecutor, new f(callable, task.getName(), task.getCallback(), task.getPriority()));
        }
        return new Disposable();
    }

    public <T> Disposable postDelay(Callable<T> callable, String str, RunnerTaskCallback<T> runnerTaskCallback, long j2, int i2) {
        return e.a().b(j2, this.mExecutor, new f(callable, str, runnerTaskCallback, i2));
    }
}
