package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.Priority;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

/* loaded from: classes.dex */
public class PriorityStarvingThrottlingProducer<T> implements Producer<T> {
    public static final String PRODUCER_NAME = "PriorityStarvingThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private final Queue<Item<T>> mPendingRequests = new PriorityQueue(11, new PriorityComparator());
    @GuardedBy("this")
    private int mNumCurrentRequests = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Item<T> {
        final Consumer<T> consumer;
        final ProducerContext producerContext;
        final long time;

        Item(Consumer<T> consumer, ProducerContext producerContext, long j2) {
            this.consumer = consumer;
            this.producerContext = producerContext;
            this.time = j2;
        }
    }

    /* loaded from: classes.dex */
    static class PriorityComparator<T> implements Comparator<Item<T>> {
        PriorityComparator() {
        }

        public int compare(Item<T> item, Item<T> item2) {
            Priority priority = item.producerContext.getPriority();
            Priority priority2 = item2.producerContext.getPriority();
            return priority == priority2 ? Double.compare(item.time, item2.time) : priority.ordinal() > priority2.ordinal() ? -1 : 1;
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
            return compare((Item) ((Item) obj), (Item) ((Item) obj2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }

        private void onRequestFinished() {
            final Item item;
            synchronized (PriorityStarvingThrottlingProducer.this) {
                item = (Item) PriorityStarvingThrottlingProducer.this.mPendingRequests.poll();
                if (item == null) {
                    PriorityStarvingThrottlingProducer.access$210(PriorityStarvingThrottlingProducer.this);
                }
            }
            if (item != null) {
                PriorityStarvingThrottlingProducer.this.mExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.producers.PriorityStarvingThrottlingProducer.ThrottlerConsumer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        PriorityStarvingThrottlingProducer.this.produceResultsInternal(item);
                    }
                });
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onFailureImpl(Throwable th) {
            getConsumer().onFailure(th);
            onRequestFinished();
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        protected void onNewResultImpl(T t, int i2) {
            getConsumer().onNewResult(t, i2);
            if (BaseConsumer.isLast(i2)) {
                onRequestFinished();
            }
        }
    }

    public PriorityStarvingThrottlingProducer(int i2, Executor executor, Producer<T> producer) {
        this.mMaxSimultaneousRequests = i2;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
    }

    static /* synthetic */ int access$210(PriorityStarvingThrottlingProducer priorityStarvingThrottlingProducer) {
        int i2 = priorityStarvingThrottlingProducer.mNumCurrentRequests;
        priorityStarvingThrottlingProducer.mNumCurrentRequests = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void produceResultsInternal(Item<T> item) {
        item.producerContext.getProducerListener().onProducerFinishWithSuccess(item.producerContext, PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(item.consumer), item.producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean z;
        long nanoTime = System.nanoTime();
        producerContext.getProducerListener().onProducerStart(producerContext, PRODUCER_NAME);
        synchronized (this) {
            int i2 = this.mNumCurrentRequests;
            z = true;
            if (i2 >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(new Item<>(consumer, producerContext, nanoTime));
            } else {
                this.mNumCurrentRequests = i2 + 1;
                z = false;
            }
        }
        if (z) {
            return;
        }
        produceResultsInternal(new Item<>(consumer, producerContext, nanoTime));
    }
}
