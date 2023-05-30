package com.jd.lib.babel.task.view;

import android.view.MotionEvent;

/* loaded from: classes14.dex */
public interface ITask {
    public static final String TASK_PARAM_COMPONENTID = "componentId";
    public static final String TASK_PARAM_TASKPARAM = "taskParam";

    void initTaskConfig(TaskConfig taskConfig);

    void onDestrey();

    void onDestroy();

    void onDispatchTouchEvent(MotionEvent motionEvent);

    void onEndScroll();

    void onPause();

    void onResume();

    void onStartScroll();

    void setBusinessIdTaskParams(String str, String str2, String str3, String str4);
}
