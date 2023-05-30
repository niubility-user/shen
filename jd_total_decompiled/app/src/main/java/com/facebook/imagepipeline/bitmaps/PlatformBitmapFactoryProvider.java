package com.facebook.imagepipeline.bitmaps;

import android.os.Build;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.platform.PlatformDecoder;

/* loaded from: classes.dex */
public class PlatformBitmapFactoryProvider {
    public static PlatformBitmapFactory buildPlatformBitmapFactory(PoolFactory poolFactory, PlatformDecoder platformDecoder, CloseableReferenceFactory closeableReferenceFactory) {
        int i2 = Build.VERSION.SDK_INT;
        return i2 >= 21 ? new ArtBitmapFactory(poolFactory.getBitmapPool(), closeableReferenceFactory) : i2 >= 11 ? new HoneycombBitmapFactory(new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory()), platformDecoder, closeableReferenceFactory) : new GingerbreadBitmapFactory();
    }
}
