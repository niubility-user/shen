package com.jingdong.common.jdreactFramework.views.task;

import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.lib.babel.task.view.ITask;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class JDTaskViewManager extends SimpleViewManager<JDRNTaskView> {
    private static final int COMMAND_ON_PAUSE = 2;
    private static final int COMMAND_ON_RESUME = 1;

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("onResume", 1, "onPause", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "TaskView";
    }

    @ReactProp(name = "businessId")
    public void setBusinessId(JDRNTaskView jDRNTaskView, String str) {
        if (TextUtils.isEmpty(str) || jDRNTaskView == null) {
            return;
        }
        jDRNTaskView.setReactBusinessId(str);
    }

    @ReactProp(name = ITask.TASK_PARAM_COMPONENTID)
    public void setComponentId(JDRNTaskView jDRNTaskView, String str) {
        if (TextUtils.isEmpty(str) || jDRNTaskView == null) {
            return;
        }
        jDRNTaskView.setReactComponentId(str);
    }

    @ReactProp(name = "cpUid")
    public void setCpUid(JDRNTaskView jDRNTaskView, String str) {
        if (str == null) {
            str = "";
        }
        if (jDRNTaskView != null) {
            jDRNTaskView.setReactCpUid(str);
        }
    }

    @ReactProp(name = "taskParams")
    public void setTaskParams(JDRNTaskView jDRNTaskView, String str) {
        if (str == null) {
            str = "";
        }
        if (jDRNTaskView != null) {
            jDRNTaskView.setReactTaskParams(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public JDRNTaskView createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new JDRNTaskView(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(@Nonnull JDRNTaskView jDRNTaskView) {
        jDRNTaskView.onDestroy();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@Nonnull JDRNTaskView jDRNTaskView, int i2, @Nullable ReadableArray readableArray) {
        if (i2 == 1) {
            jDRNTaskView.onResume();
        } else if (i2 != 2) {
        } else {
            jDRNTaskView.onPause();
        }
    }
}
