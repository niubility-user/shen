package com.jd.framework.network.dialingv2;

import com.jd.framework.network.dialingv2.DialingModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.conn.util.InetAddressUtils;

/* loaded from: classes13.dex */
public abstract class BaseDialingTask implements Runnable {
    public static final String TAG = "DialingTask";
    protected DialingModel available;
    public List<DialingModel> data = Collections.synchronizedList(new ArrayList(2));
    public EventListener eventListener;
    protected RunningStatus status;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.framework.network.dialingv2.BaseDialingTask$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jd$framework$network$dialingv2$BaseDialingTask$RunningStatus;

        static {
            int[] iArr = new int[RunningStatus.values().length];
            $SwitchMap$com$jd$framework$network$dialingv2$BaseDialingTask$RunningStatus = iArr;
            try {
                iArr[RunningStatus.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jd$framework$network$dialingv2$BaseDialingTask$RunningStatus[RunningStatus.START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jd$framework$network$dialingv2$BaseDialingTask$RunningStatus[RunningStatus.COMPLETED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes13.dex */
    public interface EventListener {
        void onComplete();

        void onInitial();

        void onStart();
    }

    /* loaded from: classes13.dex */
    public enum RunningStatus {
        INITIAL,
        START,
        COMPLETED
    }

    public BaseDialingTask(String[] strArr, DialingModel.Source source) {
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                DialingModel newInstance = DialingModel.newInstance();
                newInstance.from = source;
                newInstance.ipAddress = str;
                newInstance.isIPv6 = InetAddressUtils.isIPv6Address(str);
                this.data.add(newInstance);
            }
        }
        setStatus(RunningStatus.INITIAL);
    }

    public void clear() {
        setStatus(RunningStatus.INITIAL);
        this.available = null;
    }

    protected abstract List<DialingModel> filter();

    public DialingModel getCachedModel() {
        return this.available;
    }

    protected boolean isReady() {
        List<DialingModel> list = this.data;
        return (list == null || list.isEmpty()) ? false : true;
    }

    @Override // java.lang.Runnable
    public void run() {
        startDialing();
    }

    protected abstract DialingModel selectModelWithStrategy(List<DialingModel> list);

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setStatus(RunningStatus runningStatus) {
        EventListener eventListener;
        this.status = runningStatus;
        int i2 = AnonymousClass1.$SwitchMap$com$jd$framework$network$dialingv2$BaseDialingTask$RunningStatus[runningStatus.ordinal()];
        if (i2 == 1) {
            EventListener eventListener2 = this.eventListener;
            if (eventListener2 != null) {
                eventListener2.onInitial();
            }
        } else if (i2 != 2) {
            if (i2 == 3 && (eventListener = this.eventListener) != null) {
                eventListener.onComplete();
            }
        } else {
            EventListener eventListener3 = this.eventListener;
            if (eventListener3 != null) {
                eventListener3.onStart();
            }
        }
    }

    protected abstract void startDialing();

    public BaseDialingTask() {
        setStatus(RunningStatus.INITIAL);
    }
}
