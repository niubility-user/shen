package com.jingdong.jdreact.plugin.map;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes13.dex */
public class ViewChangesTracker {
    private static ViewChangesTracker instance;
    private LinkedList<JDReactMapMarker> markers = new LinkedList<>();
    private boolean hasScheduledFrame = false;
    private final long fps = 40;
    private LinkedList<JDReactMapMarker> markersToRemove = new LinkedList<>();
    private Handler handler = new Handler(Looper.myLooper());
    private Runnable updateRunnable = new Runnable() { // from class: com.jingdong.jdreact.plugin.map.ViewChangesTracker.1
        {
            ViewChangesTracker.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewChangesTracker.this.hasScheduledFrame = false;
            ViewChangesTracker.this.update();
            if (ViewChangesTracker.this.markers.size() > 0) {
                ViewChangesTracker.this.handler.postDelayed(ViewChangesTracker.this.updateRunnable, 40L);
            }
        }
    };

    private ViewChangesTracker() {
    }

    public static ViewChangesTracker getInstance() {
        if (instance == null) {
            synchronized (ViewChangesTracker.class) {
                instance = new ViewChangesTracker();
            }
        }
        return instance;
    }

    public void addMarker(JDReactMapMarker jDReactMapMarker) {
        this.markers.add(jDReactMapMarker);
        if (this.hasScheduledFrame) {
            return;
        }
        this.hasScheduledFrame = true;
        this.handler.postDelayed(this.updateRunnable, 40L);
    }

    public boolean containsMarker(JDReactMapMarker jDReactMapMarker) {
        return this.markers.contains(jDReactMapMarker);
    }

    public void removeMarker(JDReactMapMarker jDReactMapMarker) {
        this.markers.remove(jDReactMapMarker);
    }

    public void update() {
        Iterator<JDReactMapMarker> it = this.markers.iterator();
        while (it.hasNext()) {
            JDReactMapMarker next = it.next();
            if (!next.updateCustomForTracking()) {
                this.markersToRemove.add(next);
            }
        }
        if (this.markersToRemove.size() > 0) {
            this.markers.removeAll(this.markersToRemove);
            this.markersToRemove.clear();
        }
    }
}
