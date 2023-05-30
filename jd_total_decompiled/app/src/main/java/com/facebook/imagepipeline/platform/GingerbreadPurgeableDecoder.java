package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.MemoryFile;
import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.webp.WebpBitmapFactory;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

@DoNotStrip
/* loaded from: classes.dex */
public class GingerbreadPurgeableDecoder extends DalvikPurgeableDecoder {
    private static Method sGetFileDescriptorMethod;
    @Nullable
    private final WebpBitmapFactory mWebpBitmapFactory = WebpSupportStatus.loadWebpBitmapFactoryIfExists();

    @DoNotStrip
    public GingerbreadPurgeableDecoder() {
    }

    private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> closeableReference, int i2, @Nullable byte[] bArr) {
        PooledByteBufferInputStream pooledByteBufferInputStream;
        LimitedInputStream limitedInputStream;
        OutputStream outputStream = null;
        MemoryFile memoryFile = new MemoryFile(null, (bArr == null ? 0 : bArr.length) + i2);
        memoryFile.allowPurging(false);
        try {
            pooledByteBufferInputStream = new PooledByteBufferInputStream(closeableReference.get());
            try {
                limitedInputStream = new LimitedInputStream(pooledByteBufferInputStream, i2);
            } catch (Throwable th) {
                th = th;
                limitedInputStream = null;
            }
            try {
                outputStream = memoryFile.getOutputStream();
                ByteStreams.copy(limitedInputStream, outputStream);
                if (bArr != null) {
                    memoryFile.writeBytes(bArr, 0, i2, bArr.length);
                }
                CloseableReference.closeSafely(closeableReference);
                Closeables.closeQuietly(pooledByteBufferInputStream);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.close(outputStream, true);
                return memoryFile;
            } catch (Throwable th2) {
                th = th2;
                CloseableReference.closeSafely(closeableReference);
                Closeables.closeQuietly(pooledByteBufferInputStream);
                Closeables.closeQuietly(limitedInputStream);
                Closeables.close(outputStream, true);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            pooledByteBufferInputStream = null;
            limitedInputStream = null;
        }
    }

    private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i2, byte[] bArr, BitmapFactory.Options options) {
        MemoryFile memoryFile = null;
        try {
            try {
                MemoryFile copyToMemoryFile = copyToMemoryFile(closeableReference, i2, bArr);
                try {
                    FileDescriptor memoryFileDescriptor = getMemoryFileDescriptor(copyToMemoryFile);
                    WebpBitmapFactory webpBitmapFactory = this.mWebpBitmapFactory;
                    if (webpBitmapFactory != null) {
                        Bitmap bitmap = (Bitmap) Preconditions.checkNotNull(webpBitmapFactory.decodeFileDescriptor(memoryFileDescriptor, null, options), "BitmapFactory returned null");
                        if (copyToMemoryFile != null) {
                            copyToMemoryFile.close();
                        }
                        return bitmap;
                    }
                    throw new IllegalStateException("WebpBitmapFactory is null");
                } catch (IOException e2) {
                    e = e2;
                    memoryFile = copyToMemoryFile;
                    throw Throwables.propagate(e);
                } catch (Throwable th) {
                    th = th;
                    memoryFile = copyToMemoryFile;
                    if (memoryFile != null) {
                        memoryFile.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    private synchronized Method getFileDescriptorMethod() {
        if (sGetFileDescriptorMethod == null) {
            try {
                sGetFileDescriptorMethod = MemoryFile.class.getDeclaredMethod("getFileDescriptor", new Class[0]);
            } catch (Exception e2) {
                throw Throwables.propagate(e2);
            }
        }
        return sGetFileDescriptorMethod;
    }

    private FileDescriptor getMemoryFileDescriptor(MemoryFile memoryFile) {
        try {
            return (FileDescriptor) getFileDescriptorMethod().invoke(memoryFile, new Object[0]);
        } catch (Exception e2) {
            throw Throwables.propagate(e2);
        }
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, closeableReference.get().size(), null, options);
    }

    @Override // com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i2, BitmapFactory.Options options) {
        return decodeFileDescriptorAsPurgeable(closeableReference, i2, DalvikPurgeableDecoder.endsWithEOI(closeableReference, i2) ? null : DalvikPurgeableDecoder.EOI, options);
    }
}
