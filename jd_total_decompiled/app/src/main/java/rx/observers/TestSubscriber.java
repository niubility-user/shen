package rx.observers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;

/* loaded from: classes11.dex */
public class TestSubscriber<T> extends Subscriber<T> {
    private static final Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestSubscriber.1
        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
        }
    };
    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private final TestObserver<T> testObserver;

    public TestSubscriber(long j2) {
        this(INERT, j2);
    }

    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    public void assertCompleted() {
        int size = this.testObserver.getOnCompletedEvents().size();
        if (size == 0) {
            this.testObserver.assertionError("Not completed!");
        } else if (size > 1) {
            this.testObserver.assertionError("Completed multiple times: " + size);
        }
    }

    public void assertError(Class<? extends Throwable> cls) {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        if (onErrorEvents.size() == 0) {
            this.testObserver.assertionError("No errors");
        } else if (onErrorEvents.size() <= 1) {
            if (cls.isInstance(onErrorEvents.get(0))) {
                return;
            }
            AssertionError assertionError = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + onErrorEvents.get(0));
            assertionError.initCause(onErrorEvents.get(0));
            throw assertionError;
        } else {
            AssertionError assertionError2 = new AssertionError("Multiple errors: " + onErrorEvents.size());
            assertionError2.initCause(new CompositeException(onErrorEvents));
            throw assertionError2;
        }
    }

    public void assertNoErrors() {
        List<Throwable> onErrorEvents = getOnErrorEvents();
        if (onErrorEvents.size() > 0) {
            AssertionError assertionError = new AssertionError("Unexpected onError events: " + getOnErrorEvents().size());
            if (onErrorEvents.size() == 1) {
                assertionError.initCause(getOnErrorEvents().get(0));
            } else {
                assertionError.initCause(new CompositeException(onErrorEvents));
            }
            throw assertionError;
        }
    }

    public void assertNoTerminalEvent() {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        int size = this.testObserver.getOnCompletedEvents().size();
        if (onErrorEvents.size() > 0 || size > 0) {
            if (onErrorEvents.isEmpty()) {
                this.testObserver.assertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
            } else if (onErrorEvents.size() == 1) {
                AssertionError assertionError = new AssertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
                assertionError.initCause(onErrorEvents.get(0));
                throw assertionError;
            } else {
                AssertionError assertionError2 = new AssertionError("Found " + onErrorEvents.size() + " errors and " + size + " completion events instead of none");
                assertionError2.initCause(new CompositeException(onErrorEvents));
                throw assertionError2;
            }
        }
    }

    public void assertNoValues() {
        int size = this.testObserver.getOnNextEvents().size();
        if (size > 0) {
            this.testObserver.assertionError("No onNext events expected yet some received: " + size);
        }
    }

    public void assertNotCompleted() {
        int size = this.testObserver.getOnCompletedEvents().size();
        if (size == 1) {
            this.testObserver.assertionError("Completed!");
        } else if (size > 1) {
            this.testObserver.assertionError("Completed multiple times: " + size);
        }
    }

    public void assertReceivedOnNext(List<T> list) {
        this.testObserver.assertReceivedOnNext(list);
    }

    public void assertTerminalEvent() {
        this.testObserver.assertTerminalEvent();
    }

    public void assertUnsubscribed() {
        if (isUnsubscribed()) {
            return;
        }
        this.testObserver.assertionError("Not unsubscribed.");
    }

    public void assertValue(T t) {
        assertReceivedOnNext(Collections.singletonList(t));
    }

    public void assertValueCount(int i2) {
        int size = this.testObserver.getOnNextEvents().size();
        if (size != i2) {
            this.testObserver.assertionError("Number of onNext events differ; expected: " + i2 + ", actual: " + size);
        }
    }

    public void assertValues(T... tArr) {
        assertReceivedOnNext(Arrays.asList(tArr));
    }

    public void awaitTerminalEvent() {
        try {
            this.latch.await();
        } catch (InterruptedException e2) {
            throw new RuntimeException("Interrupted", e2);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long j2, TimeUnit timeUnit) {
        try {
            if (this.latch.await(j2, timeUnit)) {
                return;
            }
            unsubscribe();
        } catch (InterruptedException unused) {
            unsubscribe();
        }
    }

    public Thread getLastSeenThread() {
        return this.lastSeenThread;
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return this.testObserver.getOnCompletedEvents();
    }

    public List<Throwable> getOnErrorEvents() {
        return this.testObserver.getOnErrorEvents();
    }

    public List<T> getOnNextEvents() {
        return this.testObserver.getOnNextEvents();
    }

    @Override // rx.Observer
    public void onCompleted() {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.testObserver.onCompleted();
        } finally {
            this.latch.countDown();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.testObserver.onError(th);
        } finally {
            this.latch.countDown();
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.lastSeenThread = Thread.currentThread();
        this.testObserver.onNext(t);
    }

    public void requestMore(long j2) {
        request(j2);
    }

    public TestSubscriber(Observer<T> observer, long j2) {
        this.latch = new CountDownLatch(1);
        observer.getClass();
        this.testObserver = new TestObserver<>(observer);
        if (j2 >= 0) {
            request(j2);
        }
    }

    public static <T> TestSubscriber<T> create(long j2) {
        return new TestSubscriber<>(j2);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer, long j2) {
        return new TestSubscriber<>(observer, j2);
    }

    public void awaitTerminalEvent(long j2, TimeUnit timeUnit) {
        try {
            this.latch.await(j2, timeUnit);
        } catch (InterruptedException e2) {
            throw new RuntimeException("Interrupted", e2);
        }
    }

    public static <T> TestSubscriber<T> create(Subscriber<T> subscriber) {
        return new TestSubscriber<>((Subscriber) subscriber);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer) {
        return new TestSubscriber<>(observer);
    }

    public TestSubscriber(Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }

    public TestSubscriber(Observer<T> observer) {
        this(observer, -1L);
    }

    public TestSubscriber() {
        this(-1L);
    }

    public void assertError(Throwable th) {
        List<Throwable> onErrorEvents = this.testObserver.getOnErrorEvents();
        if (onErrorEvents.size() == 0) {
            this.testObserver.assertionError("No errors");
        } else if (onErrorEvents.size() <= 1) {
            if (th.equals(onErrorEvents.get(0))) {
                return;
            }
            AssertionError assertionError = new AssertionError("Exceptions differ; expected: " + th + ", actual: " + onErrorEvents.get(0));
            assertionError.initCause(onErrorEvents.get(0));
            throw assertionError;
        } else {
            AssertionError assertionError2 = new AssertionError("Multiple errors: " + onErrorEvents.size());
            assertionError2.initCause(new CompositeException(onErrorEvents));
            throw assertionError2;
        }
    }
}
