package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.jingdong.jdreact.plugin.viewshot.ViewShot;
import java.io.ByteArrayInputStream;

/* loaded from: classes.dex */
public class DataFetchProducer extends LocalFetchProducer {
    public static final String PRODUCER_NAME = "DataFetchProducer";

    public DataFetchProducer(PooledByteBufferFactory pooledByteBufferFactory) {
        super(CallerThreadExecutor.getInstance(), pooledByteBufferFactory);
    }

    @VisibleForTesting
    static byte[] getData(String str) {
        Preconditions.checkArgument(str.substring(0, 5).equals("data:"));
        int indexOf = str.indexOf(44);
        String substring = str.substring(indexOf + 1, str.length());
        return isBase64(str.substring(0, indexOf)) ? Base64.decode(substring, 0) : Uri.decode(substring).getBytes();
    }

    @VisibleForTesting
    static boolean isBase64(String str) {
        if (str.contains(";")) {
            return str.split(";")[r2.length - 1].equals(ViewShot.Results.BASE_64);
        }
        return false;
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) {
        byte[] data = getData(imageRequest.getSourceUri().toString());
        return getByteBufferBackedEncodedImage(new ByteArrayInputStream(data), data.length);
    }

    @Override // com.facebook.imagepipeline.producers.LocalFetchProducer
    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
