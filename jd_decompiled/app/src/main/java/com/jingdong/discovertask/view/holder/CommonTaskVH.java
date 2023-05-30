package com.jingdong.discovertask.view.holder;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.R;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;
import com.jingdong.corelib.utils.Log;
import com.jingdong.discovertask.model.SingletonWholeParams;
import com.jingdong.discovertask.model.entity.TaskClickParams;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.entity.TaskScanAdapterData;
import com.jingdong.discovertask.model.inter.OnInnerTaskClickListener;
import com.jingdong.discovertask.model.inter.OnTaskClickListener;
import com.jingdong.discovertask.model.mta.MtaTaskBtnClick;
import com.jingdong.discovertask.widget.MultiImageLinearContainer;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class CommonTaskVH extends BaseVH {
    public static final String TAG = "CommonTaskVH";
    private TextView awardText;
    private TextView btnTask;
    private SimpleDraweeView iconTask;
    private MultiImageLinearContainer imgContainer;
    private View itemDivide;
    private boolean mIsLastItem;
    private int mItemPosition;
    public OnInnerTaskClickListener mOnInnerClickListener;
    public OnTaskClickListener mOnTaskClickListener;
    private View mRootView;
    private TextView tipText;
    private TextView titleF;
    private TextView titleS;

    public CommonTaskVH(View view) {
        super(view);
        this.mRootView = find(R.id.root);
        this.iconTask = (SimpleDraweeView) find(R.id.icon_task);
        this.titleF = (TextView) find(R.id.title_f);
        this.titleS = (TextView) find(R.id.title_s);
        this.awardText = (TextView) find(R.id.award_text);
        this.tipText = (TextView) find(R.id.tip_text);
        this.btnTask = (TextView) find(R.id.btn_task);
        this.imgContainer = (MultiImageLinearContainer) find(R.id.img_container);
        this.itemDivide = find(R.id.item_divide);
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            this.titleF.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            this.titleS.setTextColor(Color.parseColor(JDDarkUtil.COLOR_ECECEC));
            this.awardText.setTextColor(Color.parseColor("#B3848383"));
            return;
        }
        this.titleF.setTextColor(Color.parseColor("#FF353535"));
        this.titleS.setTextColor(Color.parseColor("#FF353535"));
        this.awardText.setTextColor(Color.parseColor("#B3353535"));
    }

    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
        final TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = null;
        TaskScanAdapterData taskScanAdapterData = iFloorEntity instanceof TaskScanAdapterData ? (TaskScanAdapterData) iFloorEntity : null;
        if (taskScanAdapterData == null) {
            return;
        }
        final TaskEntity.DiscBaseTaskEntity discBaseTaskEntity = taskScanAdapterData.mDiscBaseTaskEntity;
        int i2 = discBaseTaskEntity.taskType;
        if (i2 == 2) {
            discTaskItemInfoEntity = discBaseTaskEntity.simpleRecordInfo;
        } else if (i2 == 3) {
            discTaskItemInfoEntity = discBaseTaskEntity.assistTaskDetail;
        }
        if (discTaskItemInfoEntity == null) {
            return;
        }
        JDImageUtils.displayImage(discBaseTaskEntity.iconUrl, this.iconTask);
        this.titleF.setText(discBaseTaskEntity.taskTitle);
        if (!TextUtils.isEmpty(discBaseTaskEntity.taskSubTitle)) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (discBaseTaskEntity.taskSubTitle.contains("{}")) {
                StringBuilder sb = new StringBuilder();
                sb.append(discBaseTaskEntity.times);
                int indexOf = discBaseTaskEntity.taskSubTitle.indexOf("{}");
                spannableStringBuilder.append((CharSequence) discBaseTaskEntity.taskSubTitle.replace("{}", sb));
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff0000")), indexOf, sb.length() + indexOf, 33);
            } else {
                spannableStringBuilder.append((CharSequence) discBaseTaskEntity.taskSubTitle);
            }
            this.titleS.setText(spannableStringBuilder);
        }
        this.awardText.setText(discBaseTaskEntity.taskSubTitleExt);
        if (discTaskItemInfoEntity.itemStatus == 2) {
            int i3 = discTaskItemInfoEntity.rewardStatus;
            if (i3 == 2) {
                this.tipText.setVisibility(0);
                this.btnTask.setVisibility(4);
                this.tipText.setText("\u5df2\u9886\u53d6");
            } else if (i3 == 4) {
                this.tipText.setVisibility(0);
                this.btnTask.setVisibility(4);
                this.tipText.setText("\u5df2\u53d1\u5b8c");
            } else if (i3 == 1) {
                this.tipText.setVisibility(4);
                this.btnTask.setVisibility(0);
                this.btnTask.setText("\u7acb\u5373\u9886\u53d6");
                this.btnTask.setBackgroundResource(R.drawable.shape_purple_gradient_radius_14);
            } else if (i3 == 3) {
                this.tipText.setVisibility(0);
                this.btnTask.setVisibility(4);
                this.tipText.setText("\u4efb\u52a1\u5b8c\u6210");
            } else {
                this.tipText.setVisibility(0);
                this.btnTask.setVisibility(4);
                this.tipText.setText("\u8c46\u6e9c\u8d70\u4e86");
            }
        } else {
            this.tipText.setVisibility(4);
            this.btnTask.setVisibility(0);
            if (discBaseTaskEntity.taskType == 3) {
                if (discBaseTaskEntity.times <= 0) {
                    this.btnTask.setText("\u53bb\u5206\u4eab");
                } else {
                    this.btnTask.setText("\u7ee7\u7eed\u5206\u4eab");
                }
                this.btnTask.setBackgroundResource(R.drawable.shape_red_gradient_radius_14);
            } else {
                if (discBaseTaskEntity.times <= 0) {
                    this.btnTask.setText("\u505a\u4efb\u52a1");
                } else {
                    this.btnTask.setText("\u7ee7\u7eed\u6d4f\u89c8");
                }
                this.btnTask.setBackgroundResource(R.drawable.shape_red_gradient_radius_14);
            }
        }
        final MtaTaskBtnClick mtaTaskBtnClick = new MtaTaskBtnClick();
        mtaTaskBtnClick.mtaTaskPosition = taskScanAdapterData.mtaTaskPosition;
        this.btnTask.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.discovertask.view.holder.CommonTaskVH.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TaskClickParams taskClickParams = new TaskClickParams();
                TaskEntity.DiscBaseTaskEntity discBaseTaskEntity2 = discBaseTaskEntity;
                taskClickParams.taskType = discBaseTaskEntity2.taskType;
                taskClickParams.taskId = discBaseTaskEntity2.taskId;
                TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity2 = discTaskItemInfoEntity;
                taskClickParams.inviteId = discTaskItemInfoEntity2.inviteId;
                List<JumpEntity> list = discTaskItemInfoEntity2.assistJumps;
                if (list != null && !list.isEmpty()) {
                    taskClickParams.jump = discTaskItemInfoEntity.assistJumps.get(0);
                }
                TaskEntity.DiscBaseTaskEntity discBaseTaskEntity3 = discBaseTaskEntity;
                taskClickParams.tipMsg = discBaseTaskEntity3.floatDesc;
                if (discBaseTaskEntity3.taskType == 2) {
                    SingletonWholeParams.getNewInstance().mTaskClickParams = taskClickParams;
                }
                OnTaskClickListener onTaskClickListener = CommonTaskVH.this.mOnTaskClickListener;
                if (onTaskClickListener != null && discTaskItemInfoEntity.itemStatus != 2) {
                    onTaskClickListener.onTaskClick(taskClickParams);
                }
                CommonTaskVH commonTaskVH = CommonTaskVH.this;
                OnInnerTaskClickListener onInnerTaskClickListener = commonTaskVH.mOnInnerClickListener;
                if (onInnerTaskClickListener != null) {
                    onInnerTaskClickListener.onClick(discBaseTaskEntity, mtaTaskBtnClick, commonTaskVH.mItemPosition);
                }
                if (Log.D) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\u4efb\u52a1\u7ec4\u4ef6\u70b9\u51fb-mOnInnerClickListener != null --> ");
                    sb2.append(CommonTaskVH.this.mOnInnerClickListener != null);
                    Log.d("TaskComponent", sb2.toString());
                    Log.d("TaskComponent", "\u4efb\u52a1\u7ec4\u4ef6\u70b9\u51fb-discBaseTaskEntity-json -->" + new Gson().toJson(discBaseTaskEntity));
                    Log.d("TaskComponent", "\u4efb\u52a1\u7ec4\u4ef6\u70b9\u51fb-mTaskClickParams-json -->" + new Gson().toJson(taskClickParams));
                    Log.d("TaskComponent", "\u4efb\u52a1\u7ec4\u4ef6\u70b9\u51fb-SingletonWholeParams-json -->" + new Gson().toJson(SingletonWholeParams.getNewInstance().mTaskClickParams));
                }
            }
        });
        List<TaskEntity.DiscAssistInfoEntity> list = discTaskItemInfoEntity.discAssistInfos;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < discTaskItemInfoEntity.discAssistInfos.size(); i4++) {
                arrayList.add(discTaskItemInfoEntity.discAssistInfos.get(i4).headUrl);
            }
            this.imgContainer.setVisibility(0);
            this.imgContainer.setData(arrayList).show();
        } else {
            this.imgContainer.setVisibility(8);
        }
        if (this.mIsLastItem) {
            this.itemDivide.setVisibility(8);
        } else {
            this.itemDivide.setVisibility(0);
        }
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.titleF.getLayoutParams();
        if (discTaskItemInfoEntity.itemStatus == 2 && discTaskItemInfoEntity.rewardStatus == 4) {
            layoutParams.topToTop = this.iconTask.getId();
            layoutParams.bottomToBottom = this.iconTask.getId();
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 0;
            this.awardText.setVisibility(8);
            return;
        }
        layoutParams.topToTop = this.mRootView.getId();
        layoutParams.bottomToBottom = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = DPIUtil.dip2px(16.0f);
        this.awardText.setVisibility(0);
    }

    public void isLastItem(boolean z) {
        this.mIsLastItem = z;
    }

    public void setItemPosition(int i2) {
        this.mItemPosition = i2;
    }
}
