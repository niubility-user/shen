package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;

/* loaded from: classes.dex */
public class ImageOriginUtils {
    private ImageOriginUtils() {
    }

    public static int mapProducerNameToImageOrigin(String str) {
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1917159454:
                if (str.equals(QualifiedResourceFetchProducer.PRODUCER_NAME)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1914072202:
                if (str.equals(BitmapMemoryCacheGetProducer.PRODUCER_NAME)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1683996557:
                if (str.equals(LocalResourceFetchProducer.PRODUCER_NAME)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1579985851:
                if (str.equals(LocalFileFetchProducer.PRODUCER_NAME)) {
                    c2 = 3;
                    break;
                }
                break;
            case -1307634203:
                if (str.equals(EncodedMemoryCacheProducer.PRODUCER_NAME)) {
                    c2 = 4;
                    break;
                }
                break;
            case -1224383234:
                if (str.equals(NetworkFetchProducer.PRODUCER_NAME)) {
                    c2 = 5;
                    break;
                }
                break;
            case 473552259:
                if (str.equals(LocalVideoThumbnailProducer.PRODUCER_NAME)) {
                    c2 = 6;
                    break;
                }
                break;
            case 656304759:
                if (str.equals(DiskCacheReadProducer.PRODUCER_NAME)) {
                    c2 = 7;
                    break;
                }
                break;
            case 957714404:
                if (str.equals(BitmapMemoryCacheProducer.PRODUCER_NAME)) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1019542023:
                if (str.equals(LocalAssetFetchProducer.PRODUCER_NAME)) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1023071510:
                if (str.equals(PostprocessedBitmapMemoryCacheProducer.PRODUCER_NAME)) {
                    c2 = '\n';
                    break;
                }
                break;
            case 1721672898:
                if (str.equals(DataFetchProducer.PRODUCER_NAME)) {
                    c2 = 11;
                    break;
                }
                break;
            case 1793127518:
                if (str.equals(LocalContentUriThumbnailFetchProducer.PRODUCER_NAME)) {
                    c2 = '\f';
                    break;
                }
                break;
            case 2109593398:
                if (str.equals(PartialDiskCacheProducer.PRODUCER_NAME)) {
                    c2 = '\r';
                    break;
                }
                break;
            case 2113652014:
                if (str.equals(LocalContentUriFetchProducer.PRODUCER_NAME)) {
                    c2 = 14;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 2:
            case 3:
            case 6:
            case '\t':
            case 11:
            case '\f':
            case 14:
                return 6;
            case 1:
            case '\b':
            case '\n':
                return 5;
            case 4:
                return 4;
            case 5:
                return 2;
            case 7:
            case '\r':
                return 3;
            default:
                return 1;
        }
    }

    public static String toString(int i2) {
        return i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? i2 != 6 ? "unknown" : "local" : "memory_bitmap" : "memory_encoded" : "disk" : "network";
    }
}
