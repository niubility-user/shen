package com.jd.lib.babel.task;

import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.iservice.ILanguage;

/* loaded from: classes14.dex */
public class TaskInfo {
    private static String BABEL_LAG = "";
    private static final String BABEL_VERSION = "1.0.4";
    public static final String TASK_TAG = "taskTAG";

    public static String getBabelLag() {
        ILanguage iLanguage = (ILanguage) BabelServer.get().getService(ILanguage.class);
        if (iLanguage != null) {
            BABEL_LAG = iLanguage.getLanguage();
        }
        return BABEL_LAG;
    }

    public static String getBabelVersion() {
        return BABEL_VERSION;
    }
}
