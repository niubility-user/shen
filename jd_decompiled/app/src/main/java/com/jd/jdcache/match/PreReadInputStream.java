package com.jd.jdcache.match;

import androidx.annotation.Keep;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0005\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\t\u0010\u0004R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u000fR\u0016\u0010\u0014\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\fR\u0016\u0010\u0015\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0012R\u0016\u0010\u0016\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0012R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/jd/jdcache/match/PreReadInputStream;", "Ljava/io/InputStream;", "", "startPreRead", "()V", "finishPreRead", "", "read", "()I", "close", "", "readStreamFinish", "Z", "Ljava/io/BufferedInputStream;", "unreadStream", "Ljava/io/BufferedInputStream;", "Ljava/util/concurrent/atomic/AtomicBoolean;", JshopConst.JSKEY_SHOP_CLOSED, "Ljava/util/concurrent/atomic/AtomicBoolean;", "readStream", "unreadStreamFinish", "preReadStarted", "preReadStopPoint", "Ljava/io/ByteArrayOutputStream;", "readData", "Ljava/io/ByteArrayOutputStream;", "<init>", "(Ljava/io/BufferedInputStream;)V", "Companion", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class PreReadInputStream extends InputStream {
    @NotNull
    public static final String TAG = "PreReadInputStream";
    private ByteArrayOutputStream readData;
    private BufferedInputStream readStream;
    private BufferedInputStream unreadStream;
    private boolean unreadStreamFinish;
    private boolean readStreamFinish = true;
    private final AtomicBoolean preReadStarted = new AtomicBoolean(false);
    private final AtomicBoolean preReadStopPoint = new AtomicBoolean(false);
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public PreReadInputStream(@NotNull BufferedInputStream bufferedInputStream) {
        this.unreadStreamFinish = true;
        this.unreadStream = bufferedInputStream;
        this.unreadStreamFinish = false;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed.compareAndSet(false, true)) {
            this.preReadStopPoint.set(true);
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.d(TAG, "close pre-read stream, readStreamFinish = " + this.readStreamFinish + ", unreadStreamFinish = " + this.unreadStreamFinish);
            }
            BufferedInputStream bufferedInputStream = this.readStream;
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                    this.readStream = null;
                } catch (Throwable th) {
                    th = th;
                    this.readStream = null;
                }
            }
            th = null;
            BufferedInputStream bufferedInputStream2 = this.unreadStream;
            if (bufferedInputStream2 != null) {
                try {
                    bufferedInputStream2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
                this.unreadStream = null;
            }
            this.readData = null;
            if (th != null) {
                JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                if (jDCacheLog2.getCanLog()) {
                    jDCacheLog2.e(TAG, th);
                }
                if (!(th instanceof IOException)) {
                    throw new IOException(th);
                }
                if (th != null) {
                    throw th;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.io.IOException");
            }
        }
    }

    public final void finishPreRead() {
        if (this.preReadStopPoint.compareAndSet(false, true)) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.d(TAG, "Pre-read stream finished.");
            }
            synchronized (this) {
                ByteArrayOutputStream byteArrayOutputStream = this.readData;
                if (byteArrayOutputStream != null) {
                    this.readStream = new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                    this.readStreamFinish = false;
                    if (jDCacheLog.getCanLog()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Pre-read data size=");
                        ByteArrayOutputStream byteArrayOutputStream2 = this.readData;
                        sb.append(byteArrayOutputStream2 != null ? Integer.valueOf(byteArrayOutputStream2.size()) : null);
                        sb.append(", ");
                        sb.append("unreadStreamFinish = ");
                        sb.append(this.unreadStreamFinish);
                        jDCacheLog.d(TAG, sb.toString());
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public int read() {
        int i2;
        try {
            if (this.readStreamFinish) {
                i2 = -1;
            } else {
                BufferedInputStream bufferedInputStream = this.readStream;
                i2 = bufferedInputStream != null ? bufferedInputStream.read() : -1;
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog() && -1 == i2) {
                    jDCacheLog.d(TAG, "Read from readStream finished.");
                }
            }
            if (-1 == i2) {
                this.readStreamFinish = true;
                if (!this.unreadStreamFinish) {
                    BufferedInputStream bufferedInputStream2 = this.unreadStream;
                    i2 = bufferedInputStream2 != null ? bufferedInputStream2.read() : -1;
                    if (-1 == i2) {
                        this.unreadStreamFinish = true;
                        JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                        if (jDCacheLog2.getCanLog()) {
                            jDCacheLog2.d(TAG, "Read from unreadStream finished.");
                        }
                    }
                }
            }
            return i2;
        } catch (Throwable th) {
            JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
            if (jDCacheLog3.getCanLog()) {
                jDCacheLog3.e(TAG, th);
            }
            if (th instanceof IOException) {
                throw th;
            }
            throw new IOException(th);
        }
    }

    public final void startPreRead() {
        BufferedInputStream bufferedInputStream = this.unreadStream;
        if (bufferedInputStream != null) {
            if (!this.preReadStarted.compareAndSet(false, true)) {
                JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
                if (jDCacheLog.getCanLog()) {
                    jDCacheLog.e(TAG, "Pre-read already started, cannot start twice.");
                    return;
                }
                return;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                this.readData = byteArrayOutputStream;
                byte[] bArr = new byte[10240];
                synchronized (this) {
                    JDCacheLog jDCacheLog2 = JDCacheLog.INSTANCE;
                    if (jDCacheLog2.getCanLog()) {
                        jDCacheLog2.d(TAG, "Start to pre-read stream.");
                    }
                    int i2 = 0;
                    while (!this.preReadStopPoint.get() && (i2 = bufferedInputStream.read(bArr)) != -1) {
                        byteArrayOutputStream.write(bArr, 0, i2);
                    }
                    if (-1 == i2) {
                        this.unreadStreamFinish = true;
                        finishPreRead();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            } catch (Exception e2) {
                JDCacheLog jDCacheLog3 = JDCacheLog.INSTANCE;
                if (jDCacheLog3.getCanLog()) {
                    jDCacheLog3.e(TAG, "Pre-read stream error", e2);
                }
            }
        }
    }
}
