package com.jingdong.common.unification.pagenumswitch.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class PageNumSwitchScenesMessageManager {
    private OnScenesAnimListener listener;
    private Handler mHandler;
    private final List<ScenesMessage> scenesMessageQueue = new ArrayList();

    /* loaded from: classes6.dex */
    public interface OnScenesAnimListener {
        void onChangeTopValue(int i2, int i3);
    }

    private PageNumSwitchScenesMessageManager() {
        initHandler();
    }

    public static PageNumSwitchScenesMessageManager createScenesMessageManager() {
        return new PageNumSwitchScenesMessageManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTimeout(ScenesMessage scenesMessage) {
        synchronized (this.scenesMessageQueue) {
            int indexOf = this.scenesMessageQueue.indexOf(scenesMessage);
            if (indexOf >= 0) {
                removeScenesMessageLocked(indexOf);
            }
        }
    }

    private void initHandler() {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.common.unification.pagenumswitch.utils.PageNumSwitchScenesMessageManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                Object obj = message.obj;
                if (obj instanceof ScenesMessage) {
                    PageNumSwitchScenesMessageManager.this.handleTimeout((ScenesMessage) obj);
                }
            }
        };
    }

    private void removeScenesMessageLocked(int i2) {
        this.scenesMessageQueue.remove(i2);
        if (this.scenesMessageQueue.size() > 0) {
            showNextScenesLocked();
        }
    }

    private void scheduleTimeoutLocked(ScenesMessage scenesMessage) {
        this.mHandler.removeCallbacksAndMessages(scenesMessage);
        this.mHandler.sendMessageDelayed(Message.obtain(this.mHandler, 0, scenesMessage), scenesMessage.duration);
    }

    private void showNextScenesLocked() {
        ScenesMessage scenesMessage = this.scenesMessageQueue.get(0);
        if (this.listener != null && scenesMessage.isNormal()) {
            this.listener.onChangeTopValue(scenesMessage.topValue, scenesMessage.duration);
        }
        scheduleTimeoutLocked(scenesMessage);
    }

    public void clear() {
        synchronized (this.scenesMessageQueue) {
            this.scenesMessageQueue.clear();
        }
    }

    public void enqueueScenesEmptyMessage(int i2) {
        synchronized (this.scenesMessageQueue) {
            this.scenesMessageQueue.add(ScenesMessage.createEmptyMessage(i2));
            if (this.scenesMessageQueue.size() - 1 == 0) {
                showNextScenesLocked();
            }
        }
    }

    public void enqueueScenesMessage(int i2, int i3) {
        synchronized (this.scenesMessageQueue) {
            this.scenesMessageQueue.add(new ScenesMessage(i2, i3));
            if (this.scenesMessageQueue.size() - 1 == 0) {
                showNextScenesLocked();
            }
        }
    }

    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.scenesMessageQueue) {
            isEmpty = this.scenesMessageQueue.isEmpty();
        }
        return isEmpty;
    }

    public void setOnScenesAnimListener(OnScenesAnimListener onScenesAnimListener) {
        this.listener = onScenesAnimListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static final class ScenesMessage {
        private static final byte NORMAL = 0;
        private static final byte UNNORMAL = 1;
        private int duration;
        private int topValue;
        public byte type;

        public ScenesMessage(int i2, int i3) {
            this.type = (byte) 0;
            this.topValue = i2;
            this.duration = i3;
        }

        public static ScenesMessage createEmptyMessage(int i2) {
            return new ScenesMessage((byte) 1, i2);
        }

        public boolean isNormal() {
            return this.type == 0;
        }

        private ScenesMessage(byte b, int i2) {
            this.type = (byte) 0;
            this.duration = i2;
            this.type = b;
        }
    }
}
