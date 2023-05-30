package com.jd.mobile.image.a.d;

import androidx.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.listener.BaseRequestListener2;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes17.dex */
public class b extends BaseRequestListener2 {
    private String a(ProducerContext producerContext) {
        return "requestId=" + producerContext.getId() + ", requestUrl=" + producerContext.getImageRequest().getSourceUri();
    }

    private boolean b(String str) {
        return str.equals(DecodeProducer.PRODUCER_NAME) || str.equals("ResizeAndRotateProducer");
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener2, com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map) {
        if (b(str)) {
            FLog.i("Resizing", str + " Cancellation, " + a(producerContext) + ", extras=" + map);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener2, com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(@NonNull ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        if (b(str)) {
            FLog.i("Resizing", str + " Failure, " + a(producerContext) + ", extras=" + map);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener2, com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(@NonNull ProducerContext producerContext, @NonNull String str, @Nullable Map<String, String> map) {
        if (b(str)) {
            FLog.i("Resizing", str + " Success, " + a(producerContext) + ", extras=" + map);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener2, com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(@NonNull ProducerContext producerContext, @NonNull String str, boolean z) {
        if (b(str)) {
            FLog.i("Resizing", str + " UltimateProducerReached, " + a(producerContext) + ", successful=" + z);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener2, com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(@NonNull ProducerContext producerContext, @NonNull String str) {
        return true;
    }
}
