package com.jd.jdcache.service.impl.net;

import com.coremedia.iso.boxes.FreeSpaceBox;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.InputStream;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001%B\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u0001\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u00a2\u0006\u0004\b#\u0010$J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\u0003\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0007J)\u0010\u0003\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\nJ\u0017\u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0004J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0011H\u0016\u00a2\u0006\u0004\b\u0019\u0010\u0015R\u0019\u0010\u001a\u001a\u00020\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\u00a8\u0006&"}, d2 = {"Lcom/jd/jdcache/service/impl/net/CallbackInputStream;", "Ljava/io/InputStream;", "", "read", "()I", "", "b", "([B)I", DebugKt.DEBUG_PROPERTY_VALUE_OFF, "len", "([BII)I", "", PersonalConstants.ICON_STYLE_N, FreeSpaceBox.TYPE, "(J)J", Constant.KEY_PROMOTION_AVAILABLE, "readlimit", "", "mark", "(I)V", "reset", "()V", "", "markSupported", "()Z", "close", "stream", "Ljava/io/InputStream;", "getStream", "()Ljava/io/InputStream;", "Lcom/jd/jdcache/service/impl/net/CallbackInputStream$StreamCallback;", "callback", "Lcom/jd/jdcache/service/impl/net/CallbackInputStream$StreamCallback;", "getCallback", "()Lcom/jd/jdcache/service/impl/net/CallbackInputStream$StreamCallback;", "<init>", "(Ljava/io/InputStream;Lcom/jd/jdcache/service/impl/net/CallbackInputStream$StreamCallback;)V", "StreamCallback", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class CallbackInputStream extends InputStream {
    @Nullable
    private final StreamCallback callback;
    @NotNull
    private final InputStream stream;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/jdcache/service/impl/net/CallbackInputStream$StreamCallback;", "", "", "onClose", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes13.dex */
    public interface StreamCallback {
        void onClose();
    }

    public CallbackInputStream(@NotNull InputStream inputStream, @Nullable StreamCallback streamCallback) {
        this.stream = inputStream;
        this.callback = streamCallback;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.stream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.stream.close();
        StreamCallback streamCallback = this.callback;
        if (streamCallback != null) {
            streamCallback.onClose();
        }
    }

    @Nullable
    public final StreamCallback getCallback() {
        return this.callback;
    }

    @NotNull
    public final InputStream getStream() {
        return this.stream;
    }

    @Override // java.io.InputStream
    public void mark(int readlimit) {
        this.stream.mark(readlimit);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.stream.markSupported();
    }

    @Override // java.io.InputStream
    public int read() {
        return this.stream.read();
    }

    @Override // java.io.InputStream
    public void reset() {
        this.stream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long n2) {
        return this.stream.skip(n2);
    }

    @Override // java.io.InputStream
    public int read(@Nullable byte[] b) {
        return this.stream.read(b);
    }

    @Override // java.io.InputStream
    public int read(@Nullable byte[] b, int off, int len) {
        return this.stream.read(b, off, len);
    }
}
