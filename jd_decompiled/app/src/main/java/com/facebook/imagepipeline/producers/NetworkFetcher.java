package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface NetworkFetcher<FETCH_STATE extends FetchState> {

    /* loaded from: classes.dex */
    public interface Callback {
        void onCancellation();

        void onFailure(Throwable th);

        void onResponse(InputStream inputStream, int i2);
    }

    FETCH_STATE createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext);

    void fetch(FETCH_STATE fetch_state, Callback callback);

    @Nullable
    Map<String, String> getExtraMap(FETCH_STATE fetch_state, int i2);

    void onFetchCompletion(FETCH_STATE fetch_state, int i2);

    boolean shouldPropagate(FETCH_STATE fetch_state);
}
