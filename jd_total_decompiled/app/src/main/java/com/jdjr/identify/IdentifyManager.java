package com.jdjr.identify;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes18.dex */
public class IdentifyManager {
    private static IdentifyManager mInstance;
    private final List<IdentifyListener> listeners = new ArrayList();
    private String jdPin = "";

    private IdentifyManager() {
    }

    public static IdentifyManager getInstance() {
        if (mInstance == null) {
            synchronized (IdentifyManager.class) {
                if (mInstance == null) {
                    mInstance = new IdentifyManager();
                }
            }
        }
        return mInstance;
    }

    public String addListener(IdentifyListener identifyListener) {
        this.listeners.add(identifyListener);
        return this.jdPin;
    }

    public void updateJdPin(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.jdPin = str;
        try {
            Iterator<IdentifyListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().updateJdPIN(this.jdPin);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
