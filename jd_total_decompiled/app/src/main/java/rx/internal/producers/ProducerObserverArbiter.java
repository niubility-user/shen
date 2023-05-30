package rx.internal.producers;

import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;

/* loaded from: classes11.dex */
public final class ProducerObserverArbiter<T> implements Producer, Observer<T> {
    static final Producer NULL_PRODUCER = new Producer() { // from class: rx.internal.producers.ProducerObserverArbiter.1
        @Override // rx.Producer
        public void request(long j2) {
        }
    };
    final Subscriber<? super T> child;
    Producer currentProducer;
    boolean emitting;
    volatile boolean hasError;
    Producer missedProducer;
    long missedRequested;
    Object missedTerminal;
    List<T> queue;
    long requested;

    public ProducerObserverArbiter(Subscriber<? super T> subscriber) {
        this.child = subscriber;
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0009, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void emitLoop() {
        long j2;
        Producer producer;
        Object obj;
        List<T> list;
        boolean z;
        boolean z2;
        long j3;
        Subscriber<? super T> subscriber = this.child;
        Producer producer2 = null;
        long j4 = 0;
        while (true) {
            synchronized (this) {
                j2 = this.missedRequested;
                producer = this.missedProducer;
                obj = this.missedTerminal;
                list = this.queue;
                z = true;
                if (j2 == 0 && producer == null && list == null && obj == null) {
                    this.emitting = false;
                    z2 = true;
                } else {
                    this.missedRequested = 0L;
                    this.missedProducer = null;
                    this.queue = null;
                    this.missedTerminal = null;
                    z2 = false;
                }
            }
            if (z2) {
                if (j4 == 0 || producer2 == null) {
                    return;
                }
                producer2.request(j4);
                return;
            }
            if (list != null && !list.isEmpty()) {
                z = false;
            }
            if (obj != null) {
                if (obj != Boolean.TRUE) {
                    subscriber.onError((Throwable) obj);
                    return;
                } else if (z) {
                    subscriber.onCompleted();
                    return;
                }
            }
            if (list != null) {
                for (T t : list) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    if (this.hasError) {
                        break;
                    }
                    try {
                        subscriber.onNext(t);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, t);
                        return;
                    }
                }
                j3 = list.size() + 0;
            } else {
                j3 = 0;
            }
            long j5 = this.requested;
            if (j5 != Long.MAX_VALUE) {
                if (j2 != 0) {
                    j5 += j2;
                    if (j5 < 0) {
                        j5 = Long.MAX_VALUE;
                    }
                }
                if (j3 != 0 && j5 != Long.MAX_VALUE) {
                    j5 -= j3;
                    if (j5 < 0) {
                        throw new IllegalStateException("More produced than requested");
                    }
                }
                this.requested = j5;
            }
            if (producer != null) {
                if (producer == NULL_PRODUCER) {
                    this.currentProducer = null;
                } else {
                    this.currentProducer = producer;
                    if (j5 != 0) {
                        j4 = BackpressureUtils.addCap(j4, j5);
                        producer2 = producer;
                    }
                }
            } else {
                producer = this.currentProducer;
                if (producer != null && j2 != 0) {
                    j4 = BackpressureUtils.addCap(j4, j2);
                    producer2 = producer;
                }
            }
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = Boolean.TRUE;
                return;
            }
            this.emitting = true;
            this.child.onCompleted();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        boolean z;
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = th;
                z = false;
            } else {
                this.emitting = true;
                z = true;
            }
        }
        if (z) {
            this.child.onError(th);
        } else {
            this.hasError = true;
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        synchronized (this) {
            if (this.emitting) {
                List list = this.queue;
                if (list == null) {
                    list = new ArrayList(4);
                    this.queue = list;
                }
                list.add(t);
                return;
            }
            try {
                this.child.onNext(t);
                long j2 = this.requested;
                if (j2 != Long.MAX_VALUE) {
                    this.requested = j2 - 1;
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j2 == 0) {
            return;
        }
        synchronized (this) {
            if (this.emitting) {
                this.missedRequested += j2;
                return;
            }
            this.emitting = true;
            Producer producer = this.currentProducer;
            try {
                long j3 = this.requested + j2;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
                this.requested = j3;
                emitLoop();
                if (producer != null) {
                    producer.request(j2);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }

    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.emitting) {
                if (producer == null) {
                    producer = NULL_PRODUCER;
                }
                this.missedProducer = producer;
                return;
            }
            this.emitting = true;
            this.currentProducer = producer;
            long j2 = this.requested;
            try {
                emitLoop();
                if (producer == null || j2 == 0) {
                    return;
                }
                producer.request(j2);
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }
}
