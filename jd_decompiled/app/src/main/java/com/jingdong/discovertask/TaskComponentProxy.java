package com.jingdong.discovertask;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.R;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.discovertask.ITaskContract;
import com.jingdong.discovertask.model.SingletonWholeParams;
import com.jingdong.discovertask.model.TaskConfig;
import com.jingdong.discovertask.model.entity.BaseTaskEntity;
import com.jingdong.discovertask.model.entity.DiscDoTaskEntity;
import com.jingdong.discovertask.model.entity.RequestParams;
import com.jingdong.discovertask.model.entity.TaskClickParams;
import com.jingdong.discovertask.model.entity.TaskDiscloseAdapterData;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.entity.TaskFinishEntity;
import com.jingdong.discovertask.model.entity.TaskScanAdapterData;
import com.jingdong.discovertask.model.entity.TaskSignInfoAdapterData;
import com.jingdong.discovertask.model.inter.BeforeDialogDismiss;
import com.jingdong.discovertask.model.inter.ComponentLifecycleObserver;
import com.jingdong.discovertask.model.inter.OnAcceptAwardClick;
import com.jingdong.discovertask.model.inter.OnInnerTaskClickListener;
import com.jingdong.discovertask.model.inter.OnTaskClickListener;
import com.jingdong.discovertask.model.inter.OnTaskMtaListener;
import com.jingdong.discovertask.model.inter.OnTaskStatusListener;
import com.jingdong.discovertask.model.inter.OnTimeDownFinishListener;
import com.jingdong.discovertask.model.inter.OnTimeStatusChangedListener;
import com.jingdong.discovertask.model.inter.SingleClick;
import com.jingdong.discovertask.model.mta.MtaTaskBtnClick;
import com.jingdong.discovertask.presenter.TaskPresenter;
import com.jingdong.discovertask.util.ClickUtil;
import com.jingdong.discovertask.view.CustomDialog;
import com.jingdong.discovertask.view.TaskListAdapter;
import com.jingdong.discovertask.widget.CustomFloatView;
import com.jingdong.discovertask.widget.MaxHeightRV;
import com.jingdong.discovertask.widget.SignGiftView;
import com.jingdong.discovertask.widget.TaskListTitle;
import com.jingdong.jdsdk.widget.JDToast;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes12.dex */
public class TaskComponentProxy implements ITaskContract.IView, ComponentLifecycleObserver {
    public static final String TAG = "TaskComponentProxy";
    private boolean hasSignInfo;
    private volatile boolean isCanShowTaskListDialog;
    private boolean isForceShowTaskListFlag;
    private boolean isNeedRefreshTaskList;
    private int mClickAwardTaskPosition;
    private Context mContext;
    private CustomFloatView mFloatView;
    private int[] mIconInitCoordinate;
    private Lifecycle mLifecycle;
    private OnTaskClickListener mOnTaskClickListener;
    private OnTaskMtaListener mOnTaskMtaListener;
    private RequestParams mParams;
    private ITaskContract.IPresenter mPresenter;
    private MaxHeightRV mRecyclerView;
    private ViewGroup mRootView;
    private CustomDialog mSignDialog;
    private TaskListAdapter mTaskListAdapter;
    private CustomDialog mTaskListDialog;
    private View.OnClickListener mTimeFloatClickListener;

    public TaskComponentProxy(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptAward(TaskEntity taskEntity) {
        List<TaskEntity.DiscBaseTaskEntity> list = taskEntity.data.discTasks;
        if (list == null || list.isEmpty()) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(NotificationMessageSummary.TASK_ID, taskEntity.data.discTasks.get(0).taskId);
        this.mPresenter.dealStoreAward(bundle);
    }

    private void adapterSetListener(final TaskEntity taskEntity) {
        TaskListAdapter taskListAdapter = this.mTaskListAdapter;
        taskListAdapter.mOnTaskClickListener = this.mOnTaskClickListener;
        taskListAdapter.mBtnClickListener = new OnInnerTaskClickListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.13
            @Override // com.jingdong.discovertask.model.inter.OnInnerTaskClickListener
            public void onClick(TaskEntity.DiscBaseTaskEntity discBaseTaskEntity, MtaTaskBtnClick mtaTaskBtnClick, int i2) {
                if (discBaseTaskEntity != null) {
                    TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = null;
                    int i3 = discBaseTaskEntity.taskType;
                    if (i3 == 2) {
                        discTaskItemInfoEntity = discBaseTaskEntity.simpleRecordInfo;
                    } else if (i3 == 3) {
                        discTaskItemInfoEntity = discBaseTaskEntity.assistTaskDetail;
                    }
                    if (discTaskItemInfoEntity != null && discTaskItemInfoEntity.itemStatus == 2 && discTaskItemInfoEntity.rewardStatus == 1) {
                        if (TaskComponentProxy.this.mPresenter != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString(NotificationMessageSummary.TASK_ID, discBaseTaskEntity.taskId);
                            TaskComponentProxy.this.mPresenter.dealStoreAward(bundle);
                            TaskComponentProxy.this.isNeedRefreshTaskList = true;
                            TaskComponentProxy.this.mClickAwardTaskPosition = i2;
                        }
                    } else if (discTaskItemInfoEntity != null && discTaskItemInfoEntity.itemStatus != 2 && TaskComponentProxy.this.mTaskListDialog != null) {
                        TaskComponentProxy.this.mTaskListDialog.dismiss();
                    }
                    if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                        discBaseTaskEntity.mtaTaskPosition = mtaTaskBtnClick.mtaTaskPosition;
                        TaskComponentProxy.this.mOnTaskMtaListener.onTaskListBtnClick(discBaseTaskEntity);
                    }
                }
            }
        };
        this.mTaskListAdapter.mOnAcceptAwardClick = new OnAcceptAwardClick() { // from class: com.jingdong.discovertask.TaskComponentProxy.14
            @Override // com.jingdong.discovertask.model.inter.OnAcceptAwardClick
            public void onClick() {
                TaskComponentProxy.this.mPresenter.setClickSign(true);
                if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                    TaskComponentProxy.this.mOnTaskMtaListener.onTaskListSignClick();
                }
                if (taskEntity != null) {
                    Bundle bundle = new Bundle();
                    if (TaskComponentProxy.this.mParams != null) {
                        bundle.putInt("needJump", TaskComponentProxy.this.mParams.needJump);
                    }
                    TaskComponentProxy.this.mPresenter.requestTaskList(bundle);
                    TaskComponentProxy.this.isNeedRefreshTaskList = true;
                }
            }
        };
    }

    private void checkHasFloatViewAndClear() {
        int i2 = 0;
        if (this.mRootView != null) {
            while (i2 < this.mRootView.getChildCount()) {
                View childAt = this.mRootView.getChildAt(i2);
                if (childAt instanceof CustomFloatView) {
                    this.mRootView.removeView(childAt);
                }
                i2++;
            }
            return;
        }
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) ((BaseActivity) context).getWindow().getDecorView()).findViewById(16908290);
            while (i2 < viewGroup.getChildCount()) {
                View childAt2 = viewGroup.getChildAt(i2);
                if (childAt2 instanceof CustomFloatView) {
                    viewGroup.removeView(childAt2);
                }
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickFloatView(int i2, JumpEntity jumpEntity, View view) {
        if (Log.D) {
            Log.e(TAG, "clickFloatView: style = " + i2);
        }
        if (i2 != 2) {
            return;
        }
        OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
        if (onTaskMtaListener != null) {
            onTaskMtaListener.onHomeFloatIconClick();
        }
        if (!LoginUserBase.hasLogin()) {
            Context context = this.mContext;
            if (context instanceof BaseActivity) {
                LoginUserHelper.getInstance().executeLoginRunnable((BaseActivity) context, new Runnable() { // from class: com.jingdong.discovertask.TaskComponentProxy.17
                    @Override // java.lang.Runnable
                    public void run() {
                        TaskComponentProxy.this.mPresenter.dealSignInfo(null);
                    }
                });
                return;
            }
            return;
        }
        doRqTaskList();
    }

    private void dealAwardLogic(BaseTaskEntity baseTaskEntity) {
        if (Log.D) {
            Log.e(TAG, "*******dealAwardLogic*******");
        }
        TaskListAdapter taskListAdapter = this.mTaskListAdapter;
        if (taskListAdapter != null) {
            List<IFloorEntity> adapterList = taskListAdapter.getAdapterList();
            if (adapterList.get(this.mClickAwardTaskPosition) instanceof TaskScanAdapterData) {
                TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = ((TaskScanAdapterData) adapterList.get(this.mClickAwardTaskPosition)).mDiscBaseTaskEntity.simpleRecordInfo;
                if (Log.D) {
                    Log.e(TAG, "*******dealAwardLogic******* : entity.busiCode = " + baseTaskEntity.busiCode);
                }
                String str = baseTaskEntity.busiCode;
                str.hashCode();
                char c2 = '\uffff';
                switch (str.hashCode()) {
                    case 1477632:
                        if (str.equals("0000")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 1596803:
                        if (str.equals("4007")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 1656382:
                        if (str.equals(TaskConfig.AWARD_RISK_CONTROL)) {
                            c2 = 2;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        discTaskItemInfoEntity.rewardStatus = 2;
                        break;
                    case 1:
                        discTaskItemInfoEntity.rewardStatus = 4;
                        break;
                    case 2:
                        discTaskItemInfoEntity.rewardStatus = 5;
                        break;
                    default:
                        discTaskItemInfoEntity.rewardStatus = 3;
                        break;
                }
            }
            this.mTaskListAdapter.notifyItemChanged(this.mClickAwardTaskPosition);
        }
    }

    private void doRqTaskList() {
        this.hasSignInfo = false;
        if (this.mParams != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("needJump", this.mParams.needJump);
            this.mPresenter.requestTaskList(bundle);
        }
    }

    @NotNull
    private List<IFloorEntity> getIFloorEntities(TaskEntity taskEntity) {
        TaskEntity.DiscTaskDetailEntity discTaskDetailEntity;
        List<TaskEntity.DiscTaskItemInfoEntity> list;
        ArrayList arrayList = new ArrayList();
        if (taskEntity != null && (discTaskDetailEntity = taskEntity.data) != null) {
            List<TaskEntity.DiscBaseTaskEntity> list2 = discTaskDetailEntity.discTasks;
            if (list2 != null && !list2.isEmpty()) {
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    TaskEntity.DiscBaseTaskEntity discBaseTaskEntity = list2.get(i2);
                    if (discBaseTaskEntity != null) {
                        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity = discBaseTaskEntity.signDetail;
                        if (discSignTaskDetailEntity != null && (list = discSignTaskDetailEntity.discTaskItemInfos) != null && !list.isEmpty()) {
                            this.hasSignInfo = true;
                            TaskSignInfoAdapterData taskSignInfoAdapterData = new TaskSignInfoAdapterData();
                            taskSignInfoAdapterData.mDiscBaseTaskEntity = discBaseTaskEntity;
                            arrayList.add(0, taskSignInfoAdapterData);
                        } else if (discBaseTaskEntity.simpleRecordInfo != null || discBaseTaskEntity.assistTaskDetail != null) {
                            TaskScanAdapterData taskScanAdapterData = new TaskScanAdapterData();
                            taskScanAdapterData.mDiscBaseTaskEntity = discBaseTaskEntity;
                            if (i2 == 0) {
                                taskScanAdapterData.mtaTaskPosition = i2 + 1;
                            } else {
                                taskScanAdapterData.mtaTaskPosition = i2;
                            }
                            arrayList.add(taskScanAdapterData);
                        }
                    }
                }
                if (!this.hasSignInfo && arrayList.size() < list2.size()) {
                    TaskDiscloseAdapterData taskDiscloseAdapterData = new TaskDiscloseAdapterData();
                    taskDiscloseAdapterData.isWholeDisclose = false;
                    arrayList.add(0, taskDiscloseAdapterData);
                }
            } else {
                TaskDiscloseAdapterData taskDiscloseAdapterData2 = new TaskDiscloseAdapterData();
                taskDiscloseAdapterData2.isWholeDisclose = true;
                arrayList.add(taskDiscloseAdapterData2);
            }
        } else {
            TaskDiscloseAdapterData taskDiscloseAdapterData3 = new TaskDiscloseAdapterData();
            taskDiscloseAdapterData3.isWholeDisclose = true;
            arrayList.add(taskDiscloseAdapterData3);
        }
        return arrayList;
    }

    private TaskEntity.DiscTaskItemInfoEntity getSignToday(TaskEntity taskEntity) {
        TaskEntity.DiscTaskDetailEntity discTaskDetailEntity;
        List<TaskEntity.DiscBaseTaskEntity> list;
        List<TaskEntity.DiscTaskItemInfoEntity> list2;
        if (taskEntity == null || (discTaskDetailEntity = taskEntity.data) == null || (list = discTaskDetailEntity.discTasks) == null || list.isEmpty()) {
            return null;
        }
        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity = list.get(0).signDetail;
        if (discSignTaskDetailEntity == null || (list2 = discSignTaskDetailEntity.discTaskItemInfos) == null) {
            return null;
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = list2.get(i2);
            if (discTaskItemInfoEntity.curStep == 1) {
                return discTaskItemInfoEntity;
            }
        }
        return null;
    }

    private boolean hasSignInfo(TaskEntity taskEntity) {
        TaskEntity.DiscTaskDetailEntity discTaskDetailEntity;
        List<TaskEntity.DiscBaseTaskEntity> list;
        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity;
        List<TaskEntity.DiscTaskItemInfoEntity> list2;
        if (taskEntity != null && (discTaskDetailEntity = taskEntity.data) != null && (list = discTaskDetailEntity.discTasks) != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                TaskEntity.DiscBaseTaskEntity discBaseTaskEntity = list.get(0);
                if (discBaseTaskEntity != null && (discSignTaskDetailEntity = discBaseTaskEntity.signDetail) != null && (list2 = discSignTaskDetailEntity.discTaskItemInfos) != null && !list2.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void initFloatView(int i2, String str) {
        initFloatView(i2, str, null);
    }

    private void initSignDialog(final TaskEntity taskEntity) {
        CustomDialog customDialog = this.mSignDialog;
        if (customDialog == null || !customDialog.isShowing()) {
            final TaskEntity.DiscTaskItemInfoEntity signToday = getSignToday(taskEntity);
            if (signToday == null || signToday.jPeas > 0) {
                CustomDialog build = new CustomDialog.Builder(this.mContext).view(R.layout.discover_task_middle_dialog).gravity(17).cancel(true).handleView(R.id.sign_gift, new CustomDialog.OnHandleView() { // from class: com.jingdong.discovertask.TaskComponentProxy.5
                    @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
                    public void onHandle(View view) {
                        List<TaskEntity.DiscBaseTaskEntity> list;
                        SignGiftView signGiftView = (SignGiftView) view;
                        TaskEntity.DiscTaskDetailEntity discTaskDetailEntity = taskEntity.data;
                        if (discTaskDetailEntity == null || (list = discTaskDetailEntity.discTasks) == null || list.isEmpty()) {
                            return;
                        }
                        signGiftView.initWithData(taskEntity.data.discTasks.get(0), 3).modifyWithStyle(1);
                    }
                }).handleView(R.id.sign_title, new CustomDialog.OnHandleView<TextView>() { // from class: com.jingdong.discovertask.TaskComponentProxy.4
                    @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
                    public void onHandle(TextView textView) {
                        textView.setText(taskEntity.data.discTasks.get(0).taskName);
                    }
                }).beforeDismiss(new BeforeDialogDismiss() { // from class: com.jingdong.discovertask.TaskComponentProxy.3
                    @Override // com.jingdong.discovertask.model.inter.BeforeDialogDismiss
                    public void beforeDismiss(View view) {
                        if (TaskComponentProxy.this.mIconInitCoordinate[0] == 0 && TaskComponentProxy.this.mIconInitCoordinate[1] == 0) {
                            view.findViewById(R.id.accept_award).getLocationOnScreen(TaskComponentProxy.this.mIconInitCoordinate);
                        }
                    }
                }).addViewOnClick(R.id.accept_award, new View.OnClickListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (TaskComponentProxy.this.mSignDialog != null) {
                            TaskComponentProxy.this.mSignDialog.dismiss();
                        }
                    }
                }).handleView(R.id.today_award, new CustomDialog.OnHandleView<TextView>() { // from class: com.jingdong.discovertask.TaskComponentProxy.1
                    @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
                    public void onHandle(TextView textView) {
                        TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = signToday;
                        if (discTaskItemInfoEntity != null) {
                            textView.setText(String.valueOf(discTaskItemInfoEntity.jPeas));
                        }
                    }
                }).build();
                this.mSignDialog = build;
                build.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.6
                    @Override // android.content.DialogInterface.OnDismissListener
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (TaskComponentProxy.this.mFloatView != null) {
                            if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                                TaskComponentProxy.this.mOnTaskMtaListener.onHomeFloatIconExposure();
                            }
                            TaskComponentProxy.this.mFloatView.iconAnimToRightBottom(TaskComponentProxy.this.mIconInitCoordinate[0], TaskComponentProxy.this.mIconInitCoordinate[1]);
                        }
                        if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                            TaskComponentProxy.this.mOnTaskMtaListener.onHomeSignAcceptClick();
                        }
                        TaskComponentProxy.this.acceptAward(taskEntity);
                        TaskComponentProxy.this.mSignDialog = null;
                    }
                });
            }
        }
    }

    private void initTaskListDialog(final TaskEntity taskEntity) {
        CustomDialog customDialog = this.mTaskListDialog;
        if (customDialog != null && customDialog.isShowing()) {
            if (this.mTaskListAdapter != null) {
                this.mTaskListAdapter = new TaskListAdapter(getIFloorEntities(taskEntity));
                adapterSetListener(taskEntity);
                this.mRecyclerView.setAdapter(this.mTaskListAdapter);
                return;
            }
            return;
        }
        CustomDialog build = new CustomDialog.Builder(this.mContext).view(R.layout.discover_task_list_dialog).gravity(80).cancel(true).inAnim(R.anim.pop_in).outAnim(R.anim.pop_out).handleView(R.id.recycler, new CustomDialog.OnHandleView() { // from class: com.jingdong.discovertask.TaskComponentProxy.11
            @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
            public void onHandle(View view) {
                TaskComponentProxy.this.initTaskListRecyclerView(view, taskEntity);
            }
        }).addViewOnClick(R.id.course, new View.OnClickListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                    TaskComponentProxy.this.mOnTaskMtaListener.onTaskListCourseClick();
                }
                JumpUtil.execJump(TaskComponentProxy.this.mContext, taskEntity.data.ruleJump, 4);
                TaskComponentProxy.this.isForceShowTaskListFlag = true;
            }
        }).handleView(R.id.title, new CustomDialog.OnHandleView<TextView>() { // from class: com.jingdong.discovertask.TaskComponentProxy.9
            @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
            public void onHandle(TextView textView) {
                textView.setText(taskEntity.data.title);
            }
        }).handleView(R.id.bom_root_view, new CustomDialog.OnHandleView() { // from class: com.jingdong.discovertask.TaskComponentProxy.8
            @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
            public void onHandle(View view) {
                if (Log.D) {
                    Log.e(TaskComponentProxy.TAG, "handleView: \u5904\u7406bom_root_view\u6697\u9ed1\u6a21\u5f0f");
                }
                if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                    view.setBackgroundResource(R.drawable.bg_discover_task_list_dialog_dark);
                } else {
                    view.setBackgroundResource(R.drawable.bg_discover_task_list_dialog);
                }
            }
        }).handleView(R.id.task_list_title, new CustomDialog.OnHandleView() { // from class: com.jingdong.discovertask.TaskComponentProxy.7
            @Override // com.jingdong.discovertask.view.CustomDialog.OnHandleView
            public void onHandle(View view) {
                if (view instanceof TaskListTitle) {
                    ((TaskListTitle) view).dealDarkMode();
                }
            }
        }).build();
        this.mTaskListDialog = build;
        build.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.12
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                    TaskComponentProxy.this.mOnTaskMtaListener.onTaskListDialogDismiss();
                }
                TaskComponentProxy.this.mTaskListDialog = null;
                if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                    TaskComponentProxy.this.mOnTaskMtaListener.onTaskListDialogDismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTaskListRecyclerView(View view, TaskEntity taskEntity) {
        if (view instanceof MaxHeightRV) {
            this.mRecyclerView = (MaxHeightRV) view;
            this.mRecyclerView.setMaxHeight((DPIUtil.getHeight(this.mContext) / 2) - DPIUtil.dip2px(40.0f));
            this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
            this.mTaskListAdapter = new TaskListAdapter(getIFloorEntities(taskEntity));
            adapterSetListener(taskEntity);
            this.mRecyclerView.setAdapter(this.mTaskListAdapter);
        }
    }

    private void signBtnExposure() {
        List<IFloorEntity> adapterList;
        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity;
        List<TaskEntity.DiscTaskItemInfoEntity> list;
        TaskListAdapter taskListAdapter = this.mTaskListAdapter;
        if (taskListAdapter == null || (adapterList = taskListAdapter.getAdapterList()) == null || adapterList.isEmpty()) {
            return;
        }
        IFloorEntity iFloorEntity = adapterList.get(0);
        if (!(iFloorEntity instanceof TaskSignInfoAdapterData) || (discSignTaskDetailEntity = ((TaskSignInfoAdapterData) iFloorEntity).mDiscBaseTaskEntity.signDetail) == null || (list = discSignTaskDetailEntity.discTaskItemInfos) == null) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = list.get(i2);
            if (discTaskItemInfoEntity.curStep == 1 && discTaskItemInfoEntity.rewardStatus != 2) {
                if (Log.D) {
                    Log.e(TAG, "signBtnExposure: \u7b7e\u5230\u6309\u94ae\u663e\u793a \u66dd\u5149\u57cb\u70b9");
                }
                this.mOnTaskMtaListener.onTaskListSignInBtnExposure();
                return;
            }
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void claimTaskDefeat(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity == null || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void claimTaskSuccess(DiscDoTaskEntity discDoTaskEntity) {
        DiscDoTaskEntity.DiscTimeModelConfigEntity discTimeModelConfigEntity = discDoTaskEntity.data.discTimeModelConfigVo;
        if (discTimeModelConfigEntity != null) {
            initFloatView(1, discTimeModelConfigEntity.iconUrl, discTimeModelConfigEntity.iconJump);
            startTimeDown(discTimeModelConfigEntity.waitDuration);
            OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
            if (onTaskMtaListener != null) {
                onTaskMtaListener.onTimeDownExposure();
            }
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void finishTaskDefeat(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity == null || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void finishTaskSuccess(TaskFinishEntity taskFinishEntity) {
        String str;
        if (this.mFloatView != null) {
            if (taskFinishEntity.data != null) {
                StringBuilder sb = new StringBuilder();
                TaskFinishEntity.Data data = taskFinishEntity.data;
                int i2 = data.alreadyBrowseNum;
                int i3 = data.totalBrowseNum;
                if (i2 <= 0 || i3 <= 0) {
                    return;
                }
                sb.append(i2);
                sb.append("/");
                sb.append(i3);
                str = sb.toString();
            } else {
                str = "";
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.mFloatView.getTimeDownView().taskFinishCircleStyle(str);
        }
    }

    public TaskClickParams getGlobalTaskParams() {
        return SingletonWholeParams.getNewInstance().mTaskClickParams;
    }

    public void hideHalf() {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.hideHalf();
        }
    }

    public void hideTaskListDialog() {
        if (this.isForceShowTaskListFlag) {
            return;
        }
        this.isCanShowTaskListDialog = false;
        CustomDialog customDialog = this.mTaskListDialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void initAndShowSignDialog(TaskEntity taskEntity) {
        if (taskEntity != null && taskEntity.data != null) {
            if (hasSignInfo(taskEntity)) {
                initSignDialog(taskEntity);
                showSignDialog();
            }
            initFloatView(2, taskEntity.data.entranceImg);
            CustomDialog customDialog = this.mSignDialog;
            if (customDialog != null && customDialog.isShowing()) {
                CustomFloatView customFloatView = this.mFloatView;
                if (customFloatView != null) {
                    customFloatView.setVisibility(4);
                    return;
                }
                return;
            }
            OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
            if (onTaskMtaListener != null) {
                onTaskMtaListener.onHomeFloatIconExposure();
                return;
            }
            return;
        }
        checkHasFloatViewAndClear();
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void initAndShowTaskDialog(TaskEntity taskEntity) {
        initTaskListDialog(taskEntity);
        showBottomTaskDialog();
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void initIconEntrance(TaskEntity taskEntity) {
        TaskEntity.DiscTaskDetailEntity discTaskDetailEntity;
        if (taskEntity != null && (discTaskDetailEntity = taskEntity.data) != null && !TextUtils.isEmpty(discTaskDetailEntity.entranceImg)) {
            initFloatView(2, taskEntity.data.entranceImg);
            OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
            if (onTaskMtaListener != null) {
                onTaskMtaListener.onHomeFloatIconExposure();
                return;
            }
            return;
        }
        checkHasFloatViewAndClear();
    }

    public void initWithStyle(int i2, RequestParams requestParams) {
        this.mParams = requestParams;
        if (i2 == 2) {
            Bundle bundle = new Bundle();
            bundle.putString("itemId", requestParams.itemId);
            bundle.putString(NotificationMessageSummary.TASK_ID, requestParams.taskId);
            bundle.putInt("mType", requestParams.mType);
            this.mPresenter.requestClaimTask(bundle);
        } else if (i2 == 1) {
            this.mPresenter.dealSignInfo(null);
        } else if (i2 != 3 || requestParams == null) {
        } else {
            initFloatView(i2, requestParams.floatIconUrl, requestParams.iconJump);
        }
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onCreate() {
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onDestroy() {
        Lifecycle lifecycle = this.mLifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        releaseResource();
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onPause() {
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onResume() {
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onStart() {
    }

    @Override // com.jingdong.discovertask.model.inter.ComponentLifecycleObserver
    public void onStop() {
        CustomDialog customDialog = this.mSignDialog;
        if (customDialog != null) {
            customDialog.dismiss();
        }
        if (!this.isForceShowTaskListFlag) {
            CustomDialog customDialog2 = this.mTaskListDialog;
            if (customDialog2 != null) {
                customDialog2.dismiss();
                return;
            }
            return;
        }
        this.isForceShowTaskListFlag = false;
    }

    public void pauseTimeDown() {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.pauseTimeDown();
        }
    }

    public void releaseResource() {
        if (this.mContext != null) {
            this.mContext = null;
        }
        CustomDialog customDialog = this.mSignDialog;
        if (customDialog != null) {
            customDialog.dismiss();
            this.mSignDialog.cancel();
            this.mSignDialog = null;
        }
        CustomDialog customDialog2 = this.mTaskListDialog;
        if (customDialog2 != null) {
            customDialog2.dismiss();
            this.mTaskListDialog.cancel();
            this.mTaskListDialog = null;
        }
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.releaseResource();
            this.mFloatView = null;
        }
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.mRootView = null;
        }
        if (this.mPresenter != null) {
            this.mPresenter = null;
        }
        if (this.mLifecycle != null) {
            this.mLifecycle = null;
        }
    }

    public void restoreTaskListStatus() {
        this.isCanShowTaskListDialog = true;
    }

    public void resumeTimeDown() {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.resumeTimeDown();
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void setBtnContentAndStatus() {
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.mOnTaskClickListener = onTaskClickListener;
    }

    public void setOnTaskMtaListener(OnTaskMtaListener onTaskMtaListener) {
        this.mOnTaskMtaListener = onTaskMtaListener;
    }

    public void setOnTaskStatusListener(OnTaskStatusListener<BaseTaskEntity> onTaskStatusListener) {
        this.mPresenter.setOnTaskStatusListener(onTaskStatusListener);
    }

    public void setOnTimeStatusChangedListener(OnTimeStatusChangedListener onTimeStatusChangedListener) {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.setTimeStatusChangedListener(onTimeStatusChangedListener);
        }
    }

    public void setTimeFloatClickListener(View.OnClickListener onClickListener) {
        this.mTimeFloatClickListener = onClickListener;
    }

    public void showBottomTaskDialog() {
        if (Log.D) {
            Log.e(TAG, "showBottomTaskDialog: isCanShowTaskListDialog = " + this.isCanShowTaskListDialog);
        }
        CustomDialog customDialog = this.mTaskListDialog;
        if (customDialog != null && !customDialog.isShowing() && this.isCanShowTaskListDialog) {
            this.mTaskListDialog.show();
            OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
            if (onTaskMtaListener != null) {
                onTaskMtaListener.onTaskListDialogExposure();
                signBtnExposure();
            }
        }
        this.isCanShowTaskListDialog = true;
    }

    public void showFloatComplete() {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.showComplete();
        }
    }

    public void showSignDialog() {
        CustomDialog customDialog = this.mSignDialog;
        if (customDialog == null || customDialog.isShowing()) {
            return;
        }
        this.mSignDialog.show();
        OnTaskMtaListener onTaskMtaListener = this.mOnTaskMtaListener;
        if (onTaskMtaListener != null) {
            onTaskMtaListener.onTaskSignDialogExposure();
        }
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.setVisibility(4);
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void signDialogDefeat(BaseTaskEntity baseTaskEntity) {
        checkHasFloatViewAndClear();
        if (baseTaskEntity == null || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
    }

    public void startTask() {
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void startTimeDown(long j2) {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.startTimeDown(j2);
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void storeAwardDefeat(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity == null || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
        dealAwardLogic(baseTaskEntity);
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void storeAwardSuccess(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity != null) {
            if (!TextUtils.isEmpty(baseTaskEntity.message)) {
                JDToast jDToast = new JDToast(this.mContext, (byte) 4);
                jDToast.setText(baseTaskEntity.message);
                jDToast.show();
            }
            dealAwardLogic(baseTaskEntity);
        }
    }

    public void switchScreen(boolean z) {
        CustomFloatView customFloatView = this.mFloatView;
        if (customFloatView != null) {
            customFloatView.switchScreen(z);
        }
    }

    @Override // com.jingdong.discovertask.ITaskContract.IView
    public void taskListDefeat(BaseTaskEntity baseTaskEntity) {
        if (baseTaskEntity == null || TextUtils.isEmpty(baseTaskEntity.message)) {
            return;
        }
        JDToast jDToast = new JDToast(this.mContext, (byte) 4);
        jDToast.setText(baseTaskEntity.message);
        jDToast.show();
    }

    public void toggleFloatView(boolean z) {
        OnTaskMtaListener onTaskMtaListener;
        if (this.mFloatView != null) {
            if (z && (onTaskMtaListener = this.mOnTaskMtaListener) != null) {
                onTaskMtaListener.onHomeFloatIconExposure();
            }
            Log.e(TAG, "toggleFloatView: show = " + z);
            this.mFloatView.setVisibility(z ? 0 : 4);
        }
    }

    public TaskComponentProxy(Context context, ViewGroup viewGroup) {
        this.mIconInitCoordinate = new int[2];
        this.isCanShowTaskListDialog = true;
        this.isForceShowTaskListFlag = false;
        this.mContext = context;
        this.mRootView = viewGroup;
        this.mPresenter = new TaskPresenter(this, context);
        if (context instanceof BaseActivity) {
            Lifecycle lifecycle = ((BaseActivity) context).getLifecycle();
            this.mLifecycle = lifecycle;
            lifecycle.addObserver(this);
        }
    }

    private void initFloatView(final int i2, String str, final JumpEntity jumpEntity) {
        if (Log.D) {
            Log.e(TAG, "initFloatView: style =  " + i2 + " iconUrl = " + str);
        }
        checkHasFloatViewAndClear();
        if (TextUtils.isEmpty(str) && i2 == 2) {
            return;
        }
        this.mFloatView = new CustomFloatView(this.mContext).iconUrl(str).timeDownFinishListener(new OnTimeDownFinishListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.16
            @Override // com.jingdong.discovertask.model.inter.OnTimeDownFinishListener
            public void onFinish() {
                if (TaskComponentProxy.this.mOnTaskMtaListener != null) {
                    TaskComponentProxy.this.mOnTaskMtaListener.onDragFloatIconExposure();
                }
                if (TaskComponentProxy.this.mPresenter == null) {
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(NotificationMessageSummary.TASK_ID, TaskComponentProxy.this.mParams.taskId);
                bundle.putString("itemId", TaskComponentProxy.this.mParams.itemId);
                bundle.putString("ruid", TaskComponentProxy.this.mParams.ruid);
                TaskComponentProxy.this.mPresenter.requestFinishTask(bundle);
            }
        }).onClickListener(new View.OnClickListener() { // from class: com.jingdong.discovertask.TaskComponentProxy.15
            @Override // android.view.View.OnClickListener
            @SingleClick
            public void onClick(View view) {
                if (Log.D) {
                    Log.e(TaskComponentProxy.TAG, "initFloatView: *********onClick*******");
                }
                if (ClickUtil.isFastDoubleClick(view, 1000L)) {
                    return;
                }
                TaskComponentProxy.this.clickFloatView(i2, jumpEntity, view);
            }
        }).initWithStyle(i2);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        ViewGroup viewGroup = this.mRootView;
        if (viewGroup != null) {
            viewGroup.addView(this.mFloatView, layoutParams);
            return;
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.mFloatView.setPadding(0, baseActivity.getStatusHeight(), 0, 0);
            ((ViewGroup) ((ViewGroup) baseActivity.getWindow().getDecorView()).findViewById(16908290)).addView(this.mFloatView, layoutParams);
        }
    }
}
