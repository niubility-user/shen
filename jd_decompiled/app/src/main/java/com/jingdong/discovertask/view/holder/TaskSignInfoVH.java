package com.jingdong.discovertask.view.holder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.common.R;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;
import com.jingdong.discovertask.model.entity.TaskEntity;
import com.jingdong.discovertask.model.entity.TaskSignInfoAdapterData;
import com.jingdong.discovertask.model.inter.OnAcceptAwardClick;
import com.jingdong.discovertask.widget.SignGiftView;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes12.dex */
public class TaskSignInfoVH extends BaseVH {
    private TextView acceptAward;
    public OnAcceptAwardClick mOnAcceptAwardClick;
    private SignGiftView signGift;
    private View signInfoParent;
    private TextView subTitle;
    private TextView title;

    public TaskSignInfoVH(View view) {
        super(view);
        this.acceptAward = (TextView) find(R.id.accept_award);
        this.signGift = (SignGiftView) find(R.id.sign_gift);
        this.title = (TextView) find(R.id.title);
        this.subTitle = (TextView) find(R.id.sub_title);
        this.signInfoParent = find(R.id.sign_info_parent);
        if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
            this.signInfoParent.setBackgroundResource(R.drawable.shape_dark_radius_5);
        } else {
            this.signInfoParent.setBackgroundResource(R.drawable.shape_white_radius_5);
        }
    }

    @Override // com.jingdong.common.widget.custom.pageload.viewholder.BaseVH
    public void bind(IFloorEntity iFloorEntity) {
        List<TaskEntity.DiscTaskItemInfoEntity> list;
        boolean z;
        TaskSignInfoAdapterData taskSignInfoAdapterData = iFloorEntity instanceof TaskSignInfoAdapterData ? (TaskSignInfoAdapterData) iFloorEntity : null;
        if (taskSignInfoAdapterData == null) {
            return;
        }
        this.signGift.initWithData(taskSignInfoAdapterData.mDiscBaseTaskEntity, 4).modifyWithStyle(2);
        this.title.setText(taskSignInfoAdapterData.mDiscBaseTaskEntity.taskTitle);
        this.subTitle.setText(taskSignInfoAdapterData.mDiscBaseTaskEntity.taskSubTitle);
        TaskEntity.DiscSignTaskDetailEntity discSignTaskDetailEntity = taskSignInfoAdapterData.mDiscBaseTaskEntity.signDetail;
        if (discSignTaskDetailEntity != null && (list = discSignTaskDetailEntity.discTaskItemInfos) != null) {
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= list.size()) {
                    z = false;
                    break;
                }
                TaskEntity.DiscTaskItemInfoEntity discTaskItemInfoEntity = list.get(i2);
                if (discTaskItemInfoEntity.curStep == 1) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.title.getLayoutParams();
                    if (discTaskItemInfoEntity.itemStatus == 2 && discTaskItemInfoEntity.rewardStatus == 4) {
                        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = DPIUtil.dip2px(17.0f);
                        this.subTitle.setVisibility(8);
                    } else {
                        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = DPIUtil.dip2px(10.0f);
                        this.subTitle.setVisibility(0);
                    }
                }
                if (discTaskItemInfoEntity.curStep == 1 && taskSignInfoAdapterData.mDiscBaseTaskEntity.curSignState == 0) {
                    break;
                }
                i2++;
            }
            this.acceptAward.setVisibility(z ? 0 : 8);
            int width = (DPIUtil.getWidth(this.title.getContext()) - DPIUtil.dip2px(20.0f)) - DPIUtil.dip2px(24.0f);
            if (z) {
                width -= DPIUtil.dip2px(90.0f);
            }
            this.title.setMaxWidth(width);
            this.subTitle.setMaxWidth(width);
        }
        this.acceptAward.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.discovertask.view.holder.TaskSignInfoVH.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OnAcceptAwardClick onAcceptAwardClick = TaskSignInfoVH.this.mOnAcceptAwardClick;
                if (onAcceptAwardClick != null) {
                    onAcceptAwardClick.onClick();
                }
            }
        });
    }
}
