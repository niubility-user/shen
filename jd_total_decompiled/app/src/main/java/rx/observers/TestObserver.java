package rx.observers;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;

/* loaded from: classes11.dex */
public class TestObserver<T> implements Observer<T> {
    private static Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestObserver.1
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
    private final Observer<T> delegate;
    private final ArrayList<Notification<T>> onCompletedEvents;
    private final ArrayList<Throwable> onErrorEvents;
    private final ArrayList<T> onNextEvents;

    public TestObserver(Observer<T> observer) {
        this.onNextEvents = new ArrayList<>();
        this.onErrorEvents = new ArrayList<>();
        this.onCompletedEvents = new ArrayList<>();
        this.delegate = observer;
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.onNextEvents.size() != list.size()) {
            assertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.onNextEvents.size() + ".\nProvided values: " + list + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + "Actual values: " + this.onNextEvents + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            T t = list.get(i2);
            T t2 = this.onNextEvents.get(i2);
            if (t == null) {
                if (t2 != null) {
                    assertionError("Value at index: " + i2 + " expected to be [null] but was: [" + t2 + "]\n");
                }
            } else if (!t.equals(t2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Value at index: ");
                sb.append(i2);
                sb.append(" expected to be [");
                sb.append(t);
                sb.append("] (");
                sb.append(t.getClass().getSimpleName());
                sb.append(") but was: [");
                sb.append(t2);
                sb.append("] (");
                sb.append(t2 != null ? t2.getClass().getSimpleName() : DYConstants.DY_NULL_STR);
                sb.append(")\n");
                assertionError(sb.toString());
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.onErrorEvents.size() > 1) {
            assertionError("Too many onError events: " + this.onErrorEvents.size());
        }
        if (this.onCompletedEvents.size() > 1) {
            assertionError("Too many onCompleted events: " + this.onCompletedEvents.size());
        }
        if (this.onCompletedEvents.size() == 1 && this.onErrorEvents.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.onCompletedEvents.size() == 0 && this.onErrorEvents.size() == 0) {
            assertionError("No terminal events received.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void assertionError(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int size = this.onCompletedEvents.size();
        sb.append(size);
        sb.append(" completion");
        if (size != 1) {
            sb.append("s");
        }
        sb.append(")");
        if (!this.onErrorEvents.isEmpty()) {
            int size2 = this.onErrorEvents.size();
            sb.append(" (+");
            sb.append(size2);
            sb.append(" error");
            if (size2 != 1) {
                sb.append("s");
            }
            sb.append(")");
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.onErrorEvents.isEmpty()) {
            if (this.onErrorEvents.size() == 1) {
                assertionError.initCause(this.onErrorEvents.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.onErrorEvents));
            }
        }
        throw assertionError;
    }

    public List<Object> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.onNextEvents);
        arrayList.add(this.onErrorEvents);
        arrayList.add(this.onCompletedEvents);
        return Collections.unmodifiableList(arrayList);
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.onCompletedEvents);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.onErrorEvents);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.onNextEvents);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.onCompletedEvents.add(Notification.createOnCompleted());
        this.delegate.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.onErrorEvents.add(th);
        this.delegate.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.onNextEvents.add(t);
        this.delegate.onNext(t);
    }

    public TestObserver() {
        this.onNextEvents = new ArrayList<>();
        this.onErrorEvents = new ArrayList<>();
        this.onCompletedEvents = new ArrayList<>();
        this.delegate = (Observer<T>) INERT;
    }
}
