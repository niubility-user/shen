package com.jd.mobile.image.a.c;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.FLogDefaultLoggingDelegate;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ImageDecoderConfig;
import com.facebook.imagepipeline.transcoder.SimpleImageTranscoderFactory;
import com.jd.mobile.image.a.d.b;
import com.jd.mobile.image.utils.AvifDecoderUtil;
import com.jd.mobile.image.utils.e;
import com.jingdong.JdImageToolKit;
import java.util.HashSet;

/* loaded from: classes17.dex */
public class a {
    public static void a(JdImageToolKit.ImageConfigEngine imageConfigEngine) {
        Context applicationContext = imageConfigEngine.getApplicationContext();
        DiskCacheConfig mainDiskCacheConfig = imageConfigEngine.getMainDiskCacheConfig();
        DiskCacheConfig build = DiskCacheConfig.newBuilder(applicationContext).setBaseDirectoryName("small_image_cache").build();
        ImageDecoderConfig.Builder newBuilder = ImageDecoderConfig.newBuilder();
        newBuilder.addDecodingCapability(e.a, new e.d(), new e.b());
        if (AvifDecoderUtil.isAVIFEnable()) {
            newBuilder.addDecodingCapability(AvifDecoderUtil.AVIF_FORMAT, new AvifDecoderUtil.a(), new AvifDecoderUtil.b());
            AvifDecoderUtil.setHasAddAVIFDecoder(true);
        }
        DraweeConfig.Builder newBuilder2 = DraweeConfig.newBuilder();
        newBuilder2.addCustomDrawableFactory(new e.c());
        ImagePipelineConfig.Builder bitmapsConfig = ImagePipelineConfig.newBuilder(applicationContext).setMainDiskCacheConfig(mainDiskCacheConfig).setImageTranscoderFactory(new SimpleImageTranscoderFactory(2048)).setSmallImageDiskCacheConfig(build).setImageDecoderConfig(newBuilder.build()).setDownsampleEnabled(true).setNetworkFetcher(new com.jd.mobile.image.a.b.a()).setHttpConnectionTimeout(15000).setBitmapsConfig(Bitmap.Config.RGB_565);
        if (imageConfigEngine.getBitmapMemoryCacheParamsSupplier() != null) {
            bitmapsConfig.setBitmapMemoryCacheParamsSupplier(imageConfigEngine.getBitmapMemoryCacheParamsSupplier());
        }
        if (imageConfigEngine.getLoggingLevel() <= 4) {
            HashSet hashSet = new HashSet();
            hashSet.add(new b());
            bitmapsConfig.setRequestListener2s(hashSet);
        }
        Fresco.initialize(applicationContext, bitmapsConfig.build(), newBuilder2.build());
        FLogDefaultLoggingDelegate.getInstance().setApplicationTag("fresco");
        FLog.setMinimumLoggingLevel(imageConfigEngine.getLoggingLevel());
    }
}
