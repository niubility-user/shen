package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes12.dex */
public class EventDispatcher implements LifecycleEventListener {
    private static final Comparator<Event> EVENT_COMPARATOR = new Comparator<Event>() { // from class: com.facebook.react.uimanager.events.EventDispatcher.1
        @Override // java.util.Comparator
        public int compare(Event event, Event event2) {
            if (event == null && event2 == null) {
                return 0;
            }
            if (event == null) {
                return -1;
            }
            if (event2 == null) {
                return 1;
            }
            long timestampMs = event.getTimestampMs() - event2.getTimestampMs();
            if (timestampMs == 0) {
                return 0;
            }
            return timestampMs < 0 ? -1 : 1;
        }
    };
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback;
    private final DispatchEventsRunnable mDispatchEventsRunnable;
    private final ReactApplicationContext mReactContext;
    private volatile ReactEventEmitter mReactEventEmitter;
    private final Object mEventsStagingLock = new Object();
    private final Object mEventsToDispatchLock = new Object();
    private final LongSparseArray<Integer> mEventCookieToLastEventIdx = new LongSparseArray<>();
    private final Map<String, Short> mEventNameToEventId = MapBuilder.newHashMap();
    private final ArrayList<Event> mEventStaging = new ArrayList<>();
    private final ArrayList<EventDispatcherListener> mListeners = new ArrayList<>();
    private final List<BatchEventDispatchedListener> mPostEventDispatchListeners = new ArrayList();
    private final AtomicInteger mHasDispatchScheduledCount = new AtomicInteger();
    private Event[] mEventsToDispatch = new Event[16];
    private int mEventsToDispatchSize = 0;
    private short mNextEventTypeId = 0;
    private volatile boolean mHasDispatchScheduled = false;

    /* loaded from: classes12.dex */
    public class DispatchEventsRunnable implements Runnable {
        private DispatchEventsRunnable() {
            EventDispatcher.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            Systrace.beginSection(0L, "DispatchEventsRunnable");
            try {
                Systrace.endAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcher.this.mHasDispatchScheduledCount.getAndIncrement());
                EventDispatcher.this.mHasDispatchScheduled = false;
                Assertions.assertNotNull(EventDispatcher.this.mReactEventEmitter);
                synchronized (EventDispatcher.this.mEventsToDispatchLock) {
                    if (EventDispatcher.this.mEventsToDispatchSize > 0) {
                        if (EventDispatcher.this.mEventsToDispatchSize > 1) {
                            Arrays.sort(EventDispatcher.this.mEventsToDispatch, 0, EventDispatcher.this.mEventsToDispatchSize, EventDispatcher.EVENT_COMPARATOR);
                        }
                        for (int i2 = 0; i2 < EventDispatcher.this.mEventsToDispatchSize; i2++) {
                            Event event = EventDispatcher.this.mEventsToDispatch[i2];
                            if (event != null) {
                                Systrace.endAsyncFlow(0L, event.getEventName(), event.getUniqueID());
                                event.dispatch(EventDispatcher.this.mReactEventEmitter);
                                event.dispose();
                            }
                        }
                        EventDispatcher.this.clearEventsToDispatch();
                        EventDispatcher.this.mEventCookieToLastEventIdx.clear();
                    }
                }
                Iterator it = EventDispatcher.this.mPostEventDispatchListeners.iterator();
                while (it.hasNext()) {
                    ((BatchEventDispatchedListener) it.next()).onBatchEventDispatched();
                }
            } finally {
                Systrace.endSection(0L);
            }
        }
    }

    /* loaded from: classes12.dex */
    public class ScheduleDispatchFrameCallback extends ChoreographerCompat.FrameCallback {
        private volatile boolean mIsPosted;
        private boolean mShouldStop;

        private ScheduleDispatchFrameCallback() {
            EventDispatcher.this = r1;
            this.mIsPosted = false;
            this.mShouldStop = false;
        }

        private void post() {
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.TIMERS_EVENTS, EventDispatcher.this.mCurrentFrameCallback);
        }

        @Override // com.facebook.react.modules.core.ChoreographerCompat.FrameCallback
        public void doFrame(long j2) {
            UiThreadUtil.assertOnUiThread();
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                post();
            }
            Systrace.beginSection(0L, "ScheduleDispatchFrameCallback");
            try {
                EventDispatcher.this.moveStagedEventsToDispatchQueue();
                if (!EventDispatcher.this.mHasDispatchScheduled) {
                    EventDispatcher.this.mHasDispatchScheduled = true;
                    Systrace.startAsyncFlow(0L, "ScheduleDispatchFrameCallback", EventDispatcher.this.mHasDispatchScheduledCount.get());
                    EventDispatcher.this.mReactContext.runOnJSQueueThread(EventDispatcher.this.mDispatchEventsRunnable);
                }
            } finally {
                Systrace.endSection(0L);
            }
        }

        public void maybePost() {
            if (this.mIsPosted) {
                return;
            }
            this.mIsPosted = true;
            post();
        }

        public void maybePostFromNonUI() {
            if (this.mIsPosted) {
                return;
            }
            if (!EventDispatcher.this.mReactContext.isOnUiQueueThread()) {
                EventDispatcher.this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcher.ScheduleDispatchFrameCallback.1
                    {
                        ScheduleDispatchFrameCallback.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        ScheduleDispatchFrameCallback.this.maybePost();
                    }
                });
            } else {
                maybePost();
            }
        }

        public void stop() {
            this.mShouldStop = true;
        }
    }

    public EventDispatcher(ReactApplicationContext reactApplicationContext) {
        this.mDispatchEventsRunnable = new DispatchEventsRunnable();
        this.mCurrentFrameCallback = new ScheduleDispatchFrameCallback();
        this.mReactContext = reactApplicationContext;
        reactApplicationContext.addLifecycleEventListener(this);
        this.mReactEventEmitter = new ReactEventEmitter(reactApplicationContext);
    }

    private void addEventToEventsToDispatch(Event event) {
        int i2 = this.mEventsToDispatchSize;
        Event[] eventArr = this.mEventsToDispatch;
        if (i2 == eventArr.length) {
            this.mEventsToDispatch = (Event[]) Arrays.copyOf(eventArr, eventArr.length * 2);
        }
        Event[] eventArr2 = this.mEventsToDispatch;
        int i3 = this.mEventsToDispatchSize;
        this.mEventsToDispatchSize = i3 + 1;
        eventArr2[i3] = event;
    }

    public void clearEventsToDispatch() {
        Arrays.fill(this.mEventsToDispatch, 0, this.mEventsToDispatchSize, (Object) null);
        this.mEventsToDispatchSize = 0;
    }

    private long getEventCookie(int i2, String str, short s) {
        short s2;
        Short sh = this.mEventNameToEventId.get(str);
        if (sh != null) {
            s2 = sh.shortValue();
        } else {
            short s3 = this.mNextEventTypeId;
            this.mNextEventTypeId = (short) (s3 + 1);
            this.mEventNameToEventId.put(str, Short.valueOf(s3));
            s2 = s3;
        }
        return getEventCookie(i2, s2, s);
    }

    private static long getEventCookie(int i2, short s, short s2) {
        return ((s & 65535) << 32) | i2 | ((s2 & 65535) << 48);
    }

    private void maybePostFrameCallbackFromNonUI() {
        if (this.mReactEventEmitter != null) {
            this.mCurrentFrameCallback.maybePostFromNonUI();
        }
    }

    public void moveStagedEventsToDispatchQueue() {
        synchronized (this.mEventsStagingLock) {
            synchronized (this.mEventsToDispatchLock) {
                for (int i2 = 0; i2 < this.mEventStaging.size(); i2++) {
                    Event event = this.mEventStaging.get(i2);
                    if (!event.canCoalesce()) {
                        addEventToEventsToDispatch(event);
                    } else {
                        long eventCookie = getEventCookie(event.getViewTag(), event.getEventName(), event.getCoalescingKey());
                        Integer num = this.mEventCookieToLastEventIdx.get(eventCookie);
                        Event event2 = null;
                        if (num == null) {
                            this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                        } else {
                            Event event3 = this.mEventsToDispatch[num.intValue()];
                            Event coalesce = event.coalesce(event3);
                            if (coalesce != event3) {
                                this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                                this.mEventsToDispatch[num.intValue()] = null;
                                event2 = event3;
                                event = coalesce;
                            } else {
                                event2 = event;
                                event = null;
                            }
                        }
                        if (event != null) {
                            addEventToEventsToDispatch(event);
                        }
                        if (event2 != null) {
                            event2.dispose();
                        }
                    }
                }
            }
            this.mEventStaging.clear();
        }
    }

    public void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    public void addBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.add(batchEventDispatchedListener);
    }

    public void addListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.add(eventDispatcherListener);
    }

    public void dispatchAllEvents() {
        maybePostFrameCallbackFromNonUI();
    }

    public void dispatchEvent(Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        Iterator<EventDispatcherListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            Systrace.startAsyncFlow(0L, event.getEventName(), event.getUniqueID());
        }
        maybePostFrameCallbackFromNonUI();
    }

    public void onCatalystInstanceDestroyed() {
        UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.uimanager.events.EventDispatcher.2
            {
                EventDispatcher.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                EventDispatcher.this.stopFrameCallback();
            }
        });
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        stopFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        this.mCurrentFrameCallback.maybePostFromNonUI();
    }

    public void registerEventEmitter(int i2, RCTEventEmitter rCTEventEmitter) {
        this.mReactEventEmitter.register(i2, rCTEventEmitter);
    }

    public void removeBatchEventDispatchedListener(BatchEventDispatchedListener batchEventDispatchedListener) {
        this.mPostEventDispatchListeners.remove(batchEventDispatchedListener);
    }

    public void removeListener(EventDispatcherListener eventDispatcherListener) {
        this.mListeners.remove(eventDispatcherListener);
    }

    public void unregisterEventEmitter(int i2) {
        this.mReactEventEmitter.unregister(i2);
    }
}
