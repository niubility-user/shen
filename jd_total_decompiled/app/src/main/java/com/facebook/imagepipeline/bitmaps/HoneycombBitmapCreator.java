package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;

/* loaded from: classes.dex */
public class HoneycombBitmapCreator implements BitmapCreator {
    private final FlexByteArrayPool mFlexByteArrayPool;
    private final EmptyJpegGenerator mJpegGenerator;

    public HoneycombBitmapCreator(PoolFactory poolFactory) {
        this.mFlexByteArrayPool = poolFactory.getFlexByteArrayPool();
        this.mJpegGenerator = new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory());
    }

    private static BitmapFactory.Options getBitmapFactoryOptions(int i2, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i2;
        if (Build.VERSION.SDK_INT >= 11) {
            options.inMutable = true;
        }
        return options;
    }

    @Override // com.facebook.common.webp.BitmapCreator
    @TargetApi(12)
    public Bitmap createNakedBitmap(int i2, int i3, Bitmap.Config config) {
        Throwable th;
        CloseableReference<byte[]> closeableReference;
        EncodedImage encodedImage;
        CloseableReference<PooledByteBuffer> generate = this.mJpegGenerator.generate((short) i2, (short) i3);
        EncodedImage encodedImage2 = null;
        CloseableReference<byte[]> closeableReference2 = null;
        try {
            encodedImage = new EncodedImage(generate);
        } catch (Throwable th2) {
            th = th2;
            closeableReference = null;
        }
        try {
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            BitmapFactory.Options bitmapFactoryOptions = getBitmapFactoryOptions(encodedImage.getSampleSize(), config);
            int size = generate.get().size();
            closeableReference2 = this.mFlexByteArrayPool.get(size + 2);
            byte[] bArr = closeableReference2.get();
            generate.get().read(0, bArr, 0, size);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, size, bitmapFactoryOptions);
            decodeByteArray.setHasAlpha(true);
            decodeByteArray.eraseColor(0);
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(generate);
            return decodeByteArray;
        } catch (Throwable th3) {
            closeableReference = closeableReference2;
            encodedImage2 = encodedImage;
            th = th3;
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            EncodedImage.closeSafely(encodedImage2);
            CloseableReference.closeSafely(generate);
            throw th;
        }
    }
}
