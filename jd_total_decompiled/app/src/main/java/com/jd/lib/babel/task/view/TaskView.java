package com.jd.lib.babel.task.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.babel.servicekit.BabelServer;
import com.jd.lib.babel.servicekit.networkkit.BabelError;
import com.jd.lib.babel.servicekit.networkkit.BabelResponse;
import com.jd.lib.babel.task.common.DPIUtil;
import com.jd.lib.babel.task.common.FunctionIds;
import com.jd.lib.babel.task.model.ComponentConfig;
import com.jd.lib.babel.task.servicekit.RequestInner;
import com.jd.lib.babel.task.view.TaskConfig;
import com.jd.lib.babel.task.viewkit.JDVKitFloatEventServiceImpl;
import com.jd.lib.babel.task.viewkit.JDVKitImageServiceImpl;
import com.jd.lib.babel.task.viewkit.JDVKitMtaServiceImpl;
import com.jd.lib.babel.task.viewkit.MtaManager;
import com.jd.viewkit.JDViewKit;
import com.jd.viewkit.helper.JDViewKitCountdownInterface;
import com.jd.viewkit.helper.JDViewKitFloorModel;
import com.jd.viewkit.templates.JDViewKitBaseLayout;
import com.jd.viewkit.thirdinterface.model.JDViewKitParamsModel;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class TaskView extends FrameLayout implements ITask {
    private static final int DEF_MIN_TOP = 576;
    private static final int MIN_MOVE_SLOP = 30;
    private ComponentConfig componentConfig;
    private boolean mAttachToWindow;
    private Context mContext;
    private DragContainer mDragContainer;
    private JDViewKit mJDViewKit;
    private JDViewKitCountdownInterface mJDViewKitCountdownInterface;
    private JDViewKitFloorModel mJDViewKitFloorModel;
    private int mLastDownY;
    private TaskConfig mTaskConfig;
    private boolean mTouchScrollFlag;
    private String mock;
    private MtaManager mtaManager;

    public TaskView(@NonNull Context context) {
        this(context, null);
    }

    private void init(Context context) {
        this.mAttachToWindow = false;
        this.mContext = context;
        setBackgroundColor(0);
    }

    public void initViewKit(String str) {
        try {
            if (this.mJDViewKit == null) {
                this.mJDViewKit = new JDViewKit(DPIUtil.getWidth(getContext()), new JDVKitFloatEventServiceImpl(), JDVKitImageServiceImpl.getInstance(), new JDVKitMtaServiceImpl(this.mtaManager), new JDViewKitParamsModel("", ""));
            }
            this.mJDViewKit.setDslMapByStr(str);
        } catch (Throwable unused) {
        }
    }

    private void mockTaskView() {
        try {
            JSONObject jSONObject = new JSONObject(this.mock);
            initViewKit(jSONObject.optString("dslMap"));
            JSONObject optJSONObject = jSONObject.optJSONObject("componentData");
            if (optJSONObject != null) {
                this.componentConfig = new ComponentConfig(optJSONObject.optJSONObject("componentSetting"));
                this.mJDViewKitFloorModel = this.mJDViewKit.getFollrModelByDsl(optJSONObject.optString("dslRoot"), optJSONObject.optString("dslData"));
                updateView();
            }
        } catch (JSONException unused) {
        }
    }

    private void obtainTaskView() {
        TaskConfig taskConfig;
        if (!this.mAttachToWindow || this.mJDViewKitFloorModel != null || (taskConfig = this.mTaskConfig) == null || TextUtils.isEmpty(taskConfig.mComponentId) || TextUtils.isEmpty(this.mTaskConfig.mBusinessId)) {
            return;
        }
        RequestInner requestInner = new RequestInner();
        requestInner.setFunctionId(FunctionIds.queryVkComponent);
        requestInner.putJsonParam(ITask.TASK_PARAM_COMPONENTID, this.mTaskConfig.mComponentId);
        requestInner.putJsonParam(ITask.TASK_PARAM_TASKPARAM, this.mTaskConfig.mTaskParms);
        requestInner.putJsonParam("businessId", this.mTaskConfig.mBusinessId);
        requestInner.putJsonParam("cpUid", this.mTaskConfig.mcpUid);
        requestInner.setListener(new RequestInner.Listener() { // from class: com.jd.lib.babel.task.view.TaskView.1
            {
                TaskView.this = this;
            }

            @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
            public void onEnd(BabelResponse babelResponse) {
                try {
                    JSONObject jSONObject = new JSONObject(babelResponse.getResultJson());
                    TaskView.this.initViewKit(jSONObject.optString("dslMap"));
                    JSONObject optJSONObject = jSONObject.optJSONObject("componentData");
                    if (optJSONObject != null) {
                        TaskView.this.componentConfig = new ComponentConfig(optJSONObject.optJSONObject("componentSetting"));
                        TaskView taskView = TaskView.this;
                        taskView.mJDViewKitFloorModel = taskView.mJDViewKit.getFollrModelByDsl(optJSONObject.optString("dslRoot"), optJSONObject.optString("dslData"));
                        TaskView.this.updateView();
                    }
                } catch (JSONException unused) {
                    String str = "TaskView - onEnd() called with: babelResponse = [" + babelResponse + "]";
                }
            }

            @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
            public void onError(BabelError babelError) {
                String str = "TaskView - onError() called with: babelError = [" + babelError + "]";
            }

            @Override // com.jd.lib.babel.servicekit.networkkit.Request.Listener
            public void onStart() {
            }
        });
        addRequest(this.mContext, requestInner);
    }

    public void updateView() {
        post(new Runnable() { // from class: com.jd.lib.babel.task.view.TaskView.2
            {
                TaskView.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (TaskView.this.mJDViewKitFloorModel != null && TaskView.this.mTaskConfig != null) {
                        TaskView.this.mJDViewKitCountdownInterface = null;
                        TaskView.this.removeAllViews();
                        JDViewKitBaseLayout rootLayoutByFloorId = TaskView.this.mJDViewKit.getRootLayoutByFloorId(TaskView.this.getContext(), TaskView.this.mJDViewKitFloorModel.getFloorId());
                        if (rootLayoutByFloorId instanceof JDViewKitCountdownInterface) {
                            TaskView.this.mJDViewKitCountdownInterface = (JDViewKitCountdownInterface) rootLayoutByFloorId;
                        }
                        if (rootLayoutByFloorId != null) {
                            rootLayoutByFloorId.setFloorModel(TaskView.this.mJDViewKitFloorModel);
                            if (TaskView.this.mTaskConfig.isFloat && TaskView.this.isNative()) {
                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                                layoutParams.topMargin = DPIUtil.getRealPx(TaskView.this.getContext(), 576);
                                layoutParams.leftMargin = 0;
                                if (TaskView.this.componentConfig != null && TaskView.this.componentConfig.baseSetting != null) {
                                    if (TaskView.this.componentConfig.baseSetting.top >= 0) {
                                        layoutParams.topMargin = DPIUtil.getRealPx(TaskView.this.getContext(), TaskView.this.componentConfig.baseSetting.top);
                                    }
                                    if ("right".equals(TaskView.this.componentConfig.baseSetting.align) && rootLayoutByFloorId.getWidthRealPx() > 0) {
                                        layoutParams.leftMargin = TaskView.this.getWidth() - rootLayoutByFloorId.getWidthRealPx();
                                    }
                                }
                                if (TaskView.this.getHeight() > 0 && layoutParams.topMargin > TaskView.this.getHeight() - rootLayoutByFloorId.getHeighRealPx()) {
                                    layoutParams.topMargin = TaskView.this.getHeight() - rootLayoutByFloorId.getHeighRealPx();
                                }
                                if (layoutParams.leftMargin < 0) {
                                    layoutParams.leftMargin = 0;
                                }
                                if (TaskView.this.componentConfig != null && TaskView.this.componentConfig.dragEnable) {
                                    TaskView.this.mDragContainer = new DragContainer(TaskView.this.getContext());
                                    TaskView.this.mDragContainer.addView(rootLayoutByFloorId, new FrameLayout.LayoutParams(-2, -2));
                                    TaskView taskView = TaskView.this;
                                    taskView.addView(taskView.mDragContainer, layoutParams);
                                } else {
                                    TaskView.this.addView(rootLayoutByFloorId, layoutParams);
                                }
                            } else {
                                TaskView.this.addView(rootLayoutByFloorId);
                            }
                            TaskView.this.onTaskViewAdded(rootLayoutByFloorId.getWidthRealPx(), rootLayoutByFloorId.getHeighRealPx());
                        }
                    }
                } catch (Exception e2) {
                    String str = "TaskView - updateView() called " + e2.getMessage();
                }
            }
        });
    }

    public void addRequest(Context context, RequestInner requestInner) {
        BabelServer.get().getNetWorkKitServer().addRequest(context, requestInner.request());
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void initTaskConfig(TaskConfig taskConfig) {
        try {
            this.mTaskConfig = taskConfig;
            if (taskConfig == null || TextUtils.isEmpty(taskConfig.mBusinessId) || TextUtils.isEmpty(taskConfig.mComponentId) || !this.mAttachToWindow) {
                return;
            }
            obtainTaskView();
        } catch (Exception unused) {
        }
    }

    protected boolean isNative() {
        return true;
    }

    public void mock(String str) {
        this.mock = str;
        this.mTaskConfig = new TaskConfig.Builder("testBiz", "testComId").setCpUid("testCpUid").build();
        mockTaskView();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachToWindow = true;
        obtainTaskView();
    }

    @Override // com.jd.lib.babel.task.view.ITask
    @Deprecated
    public void onDestrey() {
        onDestroy();
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onDestroy() {
        removeAllViews();
        this.mTaskConfig = null;
        this.mJDViewKitCountdownInterface = null;
        this.mJDViewKitFloorModel = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachToWindow = false;
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onDispatchTouchEvent(MotionEvent motionEvent) {
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onEndScroll() {
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onPause() {
        JDViewKitCountdownInterface jDViewKitCountdownInterface = this.mJDViewKitCountdownInterface;
        if (jDViewKitCountdownInterface != null) {
            jDViewKitCountdownInterface.setCountdownLifeCycle(2);
        }
        MtaManager mtaManager = this.mtaManager;
        if (mtaManager != null) {
            Context context = getContext();
            TaskConfig taskConfig = this.mTaskConfig;
            mtaManager.sendAllExpoMta(context, taskConfig != null ? taskConfig.mcpUid : "", taskConfig != null ? taskConfig.mBusinessId : "");
        }
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onResume() {
        JDViewKitCountdownInterface jDViewKitCountdownInterface = this.mJDViewKitCountdownInterface;
        if (jDViewKitCountdownInterface != null) {
            jDViewKitCountdownInterface.setCountdownLifeCycle(1);
        }
    }

    @Override // com.jd.lib.babel.task.view.ITask
    public void onStartScroll() {
    }

    protected void onTaskViewAdded(int i2, int i3) {
    }

    @Override // com.jd.lib.babel.task.view.ITask
    @Deprecated
    public void setBusinessIdTaskParams(String str, String str2, String str3, String str4) {
        initTaskConfig(new TaskConfig.Builder(str, str2).setTaskParms(str3).setCpUid(str4).build());
    }

    public TaskView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public void setBusinessIdTaskParams(String str, String str2, String str3) {
        initTaskConfig(new TaskConfig.Builder(str, str2).setTaskParms(str3).build());
    }

    public TaskView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTouchScrollFlag = false;
        this.mtaManager = new MtaManager();
        init(context);
    }
}
