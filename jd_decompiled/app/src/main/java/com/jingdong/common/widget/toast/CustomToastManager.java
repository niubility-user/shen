package com.jingdong.common.widget.toast;

import android.os.Handler;
import android.os.Message;
import com.jingdong.common.widget.toast.CustomToast;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class CustomToastManager {
    private static final CustomToastManager INSTANCE = new CustomToastManager();
    private static final int MESSAGE_TIMEOUT = 0;
    private List<CustomToastRecord> mToastQueue = new ArrayList();
    private WorkerHandler mHandler = new WorkerHandler();

    /* loaded from: classes12.dex */
    public static final class CustomToastRecord {
        final CustomToast.ITnCallback callback;
        int duration;

        void update(int i2) {
            this.duration = i2;
        }

        private CustomToastRecord(CustomToast.ITnCallback iTnCallback, int i2) {
            this.callback = iTnCallback;
            this.duration = i2;
        }
    }

    /* loaded from: classes12.dex */
    public final class WorkerHandler extends Handler {
        private WorkerHandler() {
            CustomToastManager.this = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            CustomToastManager.this.safeHandleTimeout(message.obj);
        }
    }

    private CustomToastManager() {
    }

    private void cancelToastLocked(int i2) {
        this.mToastQueue.get(i2).callback.hide();
        this.mToastQueue.remove(i2);
        if (this.mToastQueue.size() > 0) {
            showNextToastLocked();
        }
    }

    public static CustomToastManager getInstance() {
        return INSTANCE;
    }

    private void handleTimeout(CustomToastRecord customToastRecord) {
        synchronized (this.mToastQueue) {
            int indexOfToastLocked = indexOfToastLocked(customToastRecord.callback);
            if (indexOfToastLocked >= 0) {
                cancelToastLocked(indexOfToastLocked);
            }
        }
    }

    private int indexOfToastLocked(CustomToast.ITnCallback iTnCallback) {
        List<CustomToastRecord> list = this.mToastQueue;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2).callback == iTnCallback) {
                return i2;
            }
        }
        return -1;
    }

    public void safeHandleTimeout(Object obj) {
        if (obj instanceof CustomToastRecord) {
            handleTimeout((CustomToastRecord) obj);
        }
    }

    private void scheduleTimeoutLocked(CustomToastRecord customToastRecord) {
        this.mHandler.removeCallbacksAndMessages(customToastRecord);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, customToastRecord), customToastRecord.duration);
    }

    private void showNextToastLocked() {
        CustomToastRecord customToastRecord = this.mToastQueue.get(0);
        customToastRecord.callback.show();
        scheduleTimeoutLocked(customToastRecord);
    }

    public void cancelToast(CustomToast.ITnCallback iTnCallback) {
        synchronized (this.mToastQueue) {
            int indexOfToastLocked = indexOfToastLocked(iTnCallback);
            if (indexOfToastLocked >= 0) {
                cancelToastLocked(indexOfToastLocked);
            }
        }
    }

    public void enqueueToast(CustomToast.ITnCallback iTnCallback, int i2) {
        synchronized (this.mToastQueue) {
            int indexOfToastLocked = indexOfToastLocked(iTnCallback);
            if (indexOfToastLocked >= 0) {
                this.mToastQueue.get(indexOfToastLocked).update(i2);
            } else {
                this.mToastQueue.add(new CustomToastRecord(iTnCallback, i2));
                indexOfToastLocked = this.mToastQueue.size() - 1;
            }
            if (indexOfToastLocked == 0) {
                showNextToastLocked();
            }
        }
    }
}
