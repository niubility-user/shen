package com.jingdong.common.jdreactFramework.views.task;

import android.view.View;
import androidx.annotation.NonNull;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.jd.lib.babel.task.view.TaskConfig;
import com.jd.lib.babel.task.view.TaskView;

/* loaded from: classes5.dex */
public class JDRNTaskView extends TaskView {
    private final Runnable measureAndLayout;
    private String reactBusinessId;
    private String reactComponentId;
    private ThemedReactContext reactContext;
    private String reactCpUid;
    private String reactTaskParams;

    public JDRNTaskView(@NonNull ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.measureAndLayout = new Runnable() { // from class: com.jingdong.common.jdreactFramework.views.task.JDRNTaskView.1
            @Override // java.lang.Runnable
            public void run() {
                JDRNTaskView jDRNTaskView = JDRNTaskView.this;
                jDRNTaskView.measure(View.MeasureSpec.makeMeasureSpec(jDRNTaskView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(JDRNTaskView.this.getHeight(), 1073741824));
                JDRNTaskView jDRNTaskView2 = JDRNTaskView.this;
                jDRNTaskView2.layout(jDRNTaskView2.getLeft(), JDRNTaskView.this.getTop(), JDRNTaskView.this.getRight(), JDRNTaskView.this.getBottom());
            }
        };
        this.reactContext = themedReactContext;
    }

    private void checkInit() {
        String str;
        String str2 = this.reactBusinessId;
        if (str2 == null || (str = this.reactComponentId) == null || this.reactTaskParams == null || this.reactCpUid == null) {
            return;
        }
        initTaskConfig(new TaskConfig.Builder(str2, str).setTaskParms(this.reactTaskParams).setCpUid(this.reactCpUid).build());
    }

    private void dispatchEvent(Event event) {
        ((UIManagerModule) this.reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
    }

    @Override // com.jd.lib.babel.task.view.TaskView
    protected boolean isNative() {
        return false;
    }

    @Override // com.jd.lib.babel.task.view.TaskView
    protected void onTaskViewAdded(int i2, int i3) {
        dispatchEvent(new ContentSizeChangeEvent(getId(), i2, i3));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    public void setReactBusinessId(String str) {
        this.reactBusinessId = str;
        checkInit();
    }

    public void setReactComponentId(String str) {
        this.reactComponentId = str;
        checkInit();
    }

    public void setReactCpUid(String str) {
        this.reactCpUid = str;
        checkInit();
    }

    public void setReactTaskParams(String str) {
        this.reactTaskParams = str;
        checkInit();
    }
}
