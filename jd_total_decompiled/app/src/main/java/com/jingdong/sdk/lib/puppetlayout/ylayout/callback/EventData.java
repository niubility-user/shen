package com.jingdong.sdk.lib.puppetlayout.ylayout.callback;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes8.dex */
public class EventData {
    protected static List<EventData> sCache = new LinkedList();
    public String jumpUrl = "";
    public Activity mActivity;
    public MotionEvent mMotionEvent;
    public View mView;
    public HashMap<String, Object> paramMap;

    public static void clear() {
        sCache.clear();
    }

    protected static void recycleData(EventData eventData) {
        if (eventData != null) {
            sCache.add(eventData);
        }
    }
}
