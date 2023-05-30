package com.jdpay.lib.io;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.listener.ProgressListener;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes18.dex */
public class ProgressInputStream extends FilterInputStream {
    private final boolean isExternalScheduler;
    private ProgressListener listener;
    private final Runnable notify;
    private AtomicLong progress;
    private int progressInterval;
    private final ScheduledExecutorService scheduler;

    public ProgressInputStream(@NonNull InputStream inputStream, @Nullable ProgressListener progressListener, int i2) {
        super(inputStream);
        this.progress = new AtomicLong(-1L);
        Runnable runnable = new Runnable() { // from class: com.jdpay.lib.io.ProgressInputStream.1
            @Override // java.lang.Runnable
            public void run() {
                if (ProgressInputStream.this.listener != null) {
                    ProgressInputStream.this.listener.onProgress(ProgressInputStream.this.progress.get());
                }
            }
        };
        this.notify = runnable;
        this.listener = progressListener;
        this.progressInterval = i2;
        this.isExternalScheduler = false;
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.scheduler = newSingleThreadScheduledExecutor;
        newSingleThreadScheduledExecutor.scheduleAtFixedRate(runnable, 0L, i2, TimeUnit.MILLISECONDS);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.listener = null;
        if (!this.isExternalScheduler) {
            this.scheduler.shutdown();
        }
        super.close();
    }

    public long getProgress() {
        return this.progress.get();
    }

    public int getProgressInterval() {
        return this.progressInterval;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        this.progress.addAndGet(read);
        return read;
    }

    public void setListener(ProgressListener progressListener) {
        this.listener = progressListener;
    }

    public void setProgressInterval(int i2) {
        this.progressInterval = i2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(@NonNull byte[] bArr, int i2, int i3) throws IOException {
        int read = super.read(bArr, i2, i3);
        this.progress.addAndGet(read);
        return read;
    }

    public ProgressInputStream(@NonNull InputStream inputStream, @Nullable ProgressListener progressListener, int i2, @NonNull ScheduledExecutorService scheduledExecutorService) {
        super(inputStream);
        this.progress = new AtomicLong(-1L);
        Runnable runnable = new Runnable() { // from class: com.jdpay.lib.io.ProgressInputStream.1
            @Override // java.lang.Runnable
            public void run() {
                if (ProgressInputStream.this.listener != null) {
                    ProgressInputStream.this.listener.onProgress(ProgressInputStream.this.progress.get());
                }
            }
        };
        this.notify = runnable;
        this.listener = progressListener;
        this.progressInterval = i2;
        this.isExternalScheduler = true;
        this.scheduler = scheduledExecutorService;
        scheduledExecutorService.scheduleAtFixedRate(runnable, 0L, i2, TimeUnit.MILLISECONDS);
    }
}
