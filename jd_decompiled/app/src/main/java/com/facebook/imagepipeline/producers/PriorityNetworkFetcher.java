package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class PriorityNetworkFetcher<FETCH_STATE extends FetchState> implements NetworkFetcher<PriorityFetchState<FETCH_STATE>> {
    public static final String TAG = "PriorityNetworkFetcher";
    private final MonotonicClock mClock;
    private final HashSet<PriorityFetchState<FETCH_STATE>> mCurrentlyFetching;
    private final NetworkFetcher<FETCH_STATE> mDelegate;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mHiPriQueue;
    private final boolean mIsHiPriFifo;
    private final Object mLock;
    private final LinkedList<PriorityFetchState<FETCH_STATE>> mLowPriQueue;
    private final int mMaxOutstandingHiPri;
    private final int mMaxOutstandingLowPri;

    /* loaded from: classes.dex */
    public static class PriorityFetchState<FETCH_STATE extends FetchState> extends FetchState {
        NetworkFetcher.Callback callback;
        public final FETCH_STATE delegatedState;
        long dequeuedTimestamp;
        final long enqueuedTimestamp;
        final int hiPriCountWhenCreated;
        final int lowPriCountWhenCreated;

        private PriorityFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext, FETCH_STATE fetch_state, long j2, int i2, int i3) {
            super(consumer, producerContext);
            this.delegatedState = fetch_state;
            this.enqueuedTimestamp = j2;
            this.hiPriCountWhenCreated = i2;
            this.lowPriCountWhenCreated = i3;
        }
    }

    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i2, int i3) {
        this(networkFetcher, z, i2, i3, RealtimeSinceBootClock.get());
    }

    @VisibleForTesting
    public PriorityNetworkFetcher(NetworkFetcher<FETCH_STATE> networkFetcher, boolean z, int i2, int i3, MonotonicClock monotonicClock) {
        this.mLock = new Object();
        this.mHiPriQueue = new LinkedList<>();
        this.mLowPriQueue = new LinkedList<>();
        this.mCurrentlyFetching = new HashSet<>();
        this.mDelegate = networkFetcher;
        this.mIsHiPriFifo = z;
        this.mMaxOutstandingHiPri = i2;
        this.mMaxOutstandingLowPri = i3;
        if (i2 <= i3) {
            throw new IllegalArgumentException("maxOutstandingHiPri should be > maxOutstandingLowPri");
        }
        this.mClock = monotonicClock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changePriority(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        synchronized (this.mLock) {
            if ((z ? this.mLowPriQueue : this.mHiPriQueue).remove(priorityFetchState)) {
                FLog.v(TAG, "change-pri: %s %s", z ? "HIPRI" : "LOWPRI", priorityFetchState.getUri());
                putInQueue(priorityFetchState, z);
                dequeueIfAvailableSlots();
            }
        }
    }

    private void delegateFetch(final PriorityFetchState<FETCH_STATE> priorityFetchState) {
        try {
            this.mDelegate.fetch(priorityFetchState.delegatedState, new NetworkFetcher.Callback() { // from class: com.facebook.imagepipeline.producers.PriorityNetworkFetcher.2
                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onCancellation() {
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                    priorityFetchState.callback.onCancellation();
                }

                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onFailure(Throwable th) {
                    PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "FAIL");
                    priorityFetchState.callback.onFailure(th);
                }

                @Override // com.facebook.imagepipeline.producers.NetworkFetcher.Callback
                public void onResponse(InputStream inputStream, int i2) {
                    priorityFetchState.callback.onResponse(inputStream, i2);
                }
            });
        } catch (Exception unused) {
            removeFromQueue(priorityFetchState, "FAIL");
        }
    }

    private void dequeueIfAvailableSlots() {
        synchronized (this.mLock) {
            int size = this.mCurrentlyFetching.size();
            PriorityFetchState<FETCH_STATE> pollFirst = size < this.mMaxOutstandingHiPri ? this.mHiPriQueue.pollFirst() : null;
            if (pollFirst == null && size < this.mMaxOutstandingLowPri) {
                pollFirst = this.mLowPriQueue.pollFirst();
            }
            if (pollFirst == null) {
                return;
            }
            pollFirst.dequeuedTimestamp = this.mClock.now();
            this.mCurrentlyFetching.add(pollFirst);
            FLog.v(TAG, "fetching: %s (concurrent: %s hi-pri queue: %s low-pri queue: %s)", pollFirst.getUri(), Integer.valueOf(size), Integer.valueOf(this.mHiPriQueue.size()), Integer.valueOf(this.mLowPriQueue.size()));
            delegateFetch(pollFirst);
        }
    }

    private void putInQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, boolean z) {
        LinkedList<PriorityFetchState<FETCH_STATE>> linkedList;
        if (!z) {
            linkedList = this.mLowPriQueue;
        } else if (!this.mIsHiPriFifo) {
            this.mHiPriQueue.addFirst(priorityFetchState);
            return;
        } else {
            linkedList = this.mHiPriQueue;
        }
        linkedList.addLast(priorityFetchState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeFromQueue(PriorityFetchState<FETCH_STATE> priorityFetchState, String str) {
        synchronized (this.mLock) {
            FLog.v(TAG, "remove: %s %s", str, priorityFetchState.getUri());
            this.mCurrentlyFetching.remove(priorityFetchState);
            if (!this.mHiPriQueue.remove(priorityFetchState)) {
                this.mLowPriQueue.remove(priorityFetchState);
            }
        }
        dequeueIfAvailableSlots();
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return createFetchState((Consumer<EncodedImage>) consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public PriorityFetchState<FETCH_STATE> createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new PriorityFetchState<>(consumer, producerContext, this.mDelegate.createFetchState(consumer, producerContext), this.mClock.now(), this.mHiPriQueue.size(), this.mLowPriQueue.size());
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ void fetch(FetchState fetchState, NetworkFetcher.Callback callback) {
        fetch((PriorityFetchState) ((PriorityFetchState) fetchState), callback);
    }

    public void fetch(final PriorityFetchState<FETCH_STATE> priorityFetchState, final NetworkFetcher.Callback callback) {
        priorityFetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.PriorityNetworkFetcher.1
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                PriorityNetworkFetcher.this.removeFromQueue(priorityFetchState, "CANCEL");
                callback.onCancellation();
            }

            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onPriorityChanged() {
                PriorityNetworkFetcher priorityNetworkFetcher = PriorityNetworkFetcher.this;
                PriorityFetchState priorityFetchState2 = priorityFetchState;
                priorityNetworkFetcher.changePriority(priorityFetchState2, priorityFetchState2.getContext().getPriority() == Priority.HIGH);
            }
        });
        synchronized (this.mLock) {
            if (this.mCurrentlyFetching.contains(priorityFetchState)) {
                FLog.e(TAG, "fetch state was enqueued twice: " + priorityFetchState);
                return;
            }
            boolean z = priorityFetchState.getContext().getPriority() == Priority.HIGH;
            FLog.v(TAG, "enqueue: %s %s", z ? "HI-PRI" : "LOW-PRI", priorityFetchState.getUri());
            priorityFetchState.callback = callback;
            putInQueue(priorityFetchState, z);
            dequeueIfAvailableSlots();
        }
    }

    @VisibleForTesting
    HashSet<PriorityFetchState<FETCH_STATE>> getCurrentlyFetching() {
        return this.mCurrentlyFetching;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    @Nullable
    public /* bridge */ /* synthetic */ Map getExtraMap(FetchState fetchState, int i2) {
        return getExtraMap((PriorityFetchState) ((PriorityFetchState) fetchState), i2);
    }

    @Nullable
    public Map<String, String> getExtraMap(PriorityFetchState<FETCH_STATE> priorityFetchState, int i2) {
        Map<String, String> extraMap = this.mDelegate.getExtraMap(priorityFetchState.delegatedState, i2);
        HashMap hashMap = extraMap != null ? new HashMap(extraMap) : new HashMap();
        hashMap.put("pri_queue_time", "" + (priorityFetchState.dequeuedTimestamp - priorityFetchState.enqueuedTimestamp));
        hashMap.put("hipri_queue_size", "" + priorityFetchState.hiPriCountWhenCreated);
        hashMap.put("lowpri_queue_size", "" + priorityFetchState.lowPriCountWhenCreated);
        return hashMap;
    }

    @VisibleForTesting
    List<PriorityFetchState<FETCH_STATE>> getHiPriQueue() {
        return this.mHiPriQueue;
    }

    @VisibleForTesting
    List<PriorityFetchState<FETCH_STATE>> getLowPriQueue() {
        return this.mLowPriQueue;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ void onFetchCompletion(FetchState fetchState, int i2) {
        onFetchCompletion((PriorityFetchState) ((PriorityFetchState) fetchState), i2);
    }

    public void onFetchCompletion(PriorityFetchState<FETCH_STATE> priorityFetchState, int i2) {
        removeFromQueue(priorityFetchState, "SUCCESS");
        this.mDelegate.onFetchCompletion(priorityFetchState.delegatedState, i2);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ boolean shouldPropagate(FetchState fetchState) {
        return shouldPropagate((PriorityFetchState) ((PriorityFetchState) fetchState));
    }

    public boolean shouldPropagate(PriorityFetchState<FETCH_STATE> priorityFetchState) {
        return this.mDelegate.shouldPropagate(priorityFetchState.delegatedState);
    }
}
