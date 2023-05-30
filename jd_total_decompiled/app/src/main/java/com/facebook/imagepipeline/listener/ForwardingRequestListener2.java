package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ForwardingRequestListener2 implements RequestListener2 {
    private static final String TAG = "ForwardingRequestListener2";
    private final List<RequestListener2> mRequestListeners;

    public ForwardingRequestListener2(Set<RequestListener2> set) {
        this.mRequestListeners = new ArrayList(set.size());
        for (RequestListener2 requestListener2 : set) {
            if (requestListener2 != null) {
                this.mRequestListeners.add(requestListener2);
            }
        }
    }

    public ForwardingRequestListener2(RequestListener2... requestListener2Arr) {
        this.mRequestListeners = new ArrayList(requestListener2Arr.length);
        for (RequestListener2 requestListener2 : requestListener2Arr) {
            if (requestListener2 != null) {
                this.mRequestListeners.add(requestListener2);
            }
        }
    }

    private void onException(String str, Throwable th) {
        FLog.e(TAG, str, th);
    }

    public void addRequestListener(RequestListener2 requestListener2) {
        this.mRequestListeners.add(requestListener2);
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onProducerEvent(producerContext, str, str2);
            } catch (Exception e2) {
                onException("InternalListener exception in onIntermediateChunkStart", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onProducerFinishWithCancellation(producerContext, str, map);
            } catch (Exception e2) {
                onException("InternalListener exception in onProducerFinishWithCancellation", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onProducerFinishWithFailure(producerContext, str, th, map);
            } catch (Exception e2) {
                onException("InternalListener exception in onProducerFinishWithFailure", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, @Nullable Map<String, String> map) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onProducerFinishWithSuccess(producerContext, str, map);
            } catch (Exception e2) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(ProducerContext producerContext, String str) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onProducerStart(producerContext, str);
            } catch (Exception e2) {
                onException("InternalListener exception in onProducerStart", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestCancellation(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onRequestCancellation(producerContext);
            } catch (Exception e2) {
                onException("InternalListener exception in onRequestCancellation", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestFailure(ProducerContext producerContext, Throwable th) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onRequestFailure(producerContext, th);
            } catch (Exception e2) {
                onException("InternalListener exception in onRequestFailure", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestStart(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onRequestStart(producerContext);
            } catch (Exception e2) {
                onException("InternalListener exception in onRequestStart", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener2
    public void onRequestSuccess(ProducerContext producerContext) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onRequestSuccess(producerContext);
            } catch (Exception e2) {
                onException("InternalListener exception in onRequestSuccess", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                this.mRequestListeners.get(i2).onUltimateProducerReached(producerContext, str, z);
            } catch (Exception e2) {
                onException("InternalListener exception in onProducerFinishWithSuccess", e2);
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        int size = this.mRequestListeners.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mRequestListeners.get(i2).requiresExtraMap(producerContext, str)) {
                return true;
            }
        }
        return false;
    }
}
