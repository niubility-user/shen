package com.jingdong.app.mall.voice;

import android.text.TextUtils;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class JDVoiceInputUtils {
    private static final String TAG = "com.jingdong.app.mall.voice.JDVoiceInputUtils";
    private EventBus eventBus = EventBus.getDefault();
    private String fromType = "";
    private JDVoiceInputListener listener;

    public void close() {
        if (this.eventBus.isRegistered(this)) {
            this.eventBus.unregister(this);
        }
    }

    public void onEventMainThread(JDVoiceInputEvent jDVoiceInputEvent) {
        if (this.listener != null && TextUtils.equals(this.fromType, jDVoiceInputEvent.fromType)) {
            String str = jDVoiceInputEvent.eventType;
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1838591659:
                    if (str.equals(JDVoiceInputEvent.EVENT_TYPE_CREATE)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1420724106:
                    if (str.equals(JDVoiceInputEvent.EVENT_TYPE_RESULT)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -632948031:
                    if (str.equals(JDVoiceInputEvent.EVENT_TYPE_DESTROY)) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    this.listener.onVoiceInputCreate();
                    return;
                case 1:
                    this.listener.onResult(jDVoiceInputEvent.result, jDVoiceInputEvent.isLast);
                    return;
                case 2:
                    this.listener.onVoiceInputDestroy();
                    return;
                default:
                    return;
            }
        }
    }

    public void setListener(JDVoiceInputListener jDVoiceInputListener) {
        this.listener = jDVoiceInputListener;
        if (this.eventBus.isRegistered(this)) {
            return;
        }
        this.eventBus.register(this);
    }

    public void setListener(JDVoiceInputListener jDVoiceInputListener, String str) {
        this.listener = jDVoiceInputListener;
        if (!TextUtils.isEmpty(str)) {
            this.fromType = str;
        }
        if (this.eventBus.isRegistered(this)) {
            return;
        }
        this.eventBus.register(this);
    }
}
