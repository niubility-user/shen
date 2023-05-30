package com.jingdong.common.web.xrender;

import java.util.ArrayList;

/* loaded from: classes12.dex */
public class LifeCycleDispatcher {
    public static ArrayList<String> pageList = new ArrayList<>();

    public static void dispatchOnPaused(String str) {
        pageList.clear();
    }

    public static void dispatchOnResumed(String str) {
        pageList.add(str);
        XRender.getInstance().checkRender(str);
    }
}
