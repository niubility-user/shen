package com.jingdong.discovertask.view;

import android.view.ViewGroup;
import com.jingdong.common.R;
import com.jingdong.common.widget.custom.pageload.BaseTAdapter;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import com.jingdong.common.widget.custom.pageload.viewholder.BaseVH;
import com.jingdong.discovertask.model.inter.OnAcceptAwardClick;
import com.jingdong.discovertask.model.inter.OnInnerTaskClickListener;
import com.jingdong.discovertask.model.inter.OnTaskClickListener;
import com.jingdong.discovertask.view.holder.CommonTaskVH;
import com.jingdong.discovertask.view.holder.TaskDiscloseVH;
import com.jingdong.discovertask.view.holder.TaskSignInfoVH;
import java.util.List;

/* loaded from: classes12.dex */
public class TaskListAdapter extends BaseTAdapter {
    public OnInnerTaskClickListener mBtnClickListener;
    public OnAcceptAwardClick mOnAcceptAwardClick;
    public OnTaskClickListener mOnTaskClickListener;

    public TaskListAdapter(List<IFloorEntity> list) {
        super(list);
    }

    public List<IFloorEntity> getAdapterList() {
        return this.list;
    }

    public int getTaskPosition(IFloorEntity iFloorEntity) {
        if (this.list == null) {
            return 0;
        }
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            if (this.list.get(i2) == iFloorEntity) {
                return this.list.get(0).getFloorType() == 3 ? i2 : i2 + 1;
            }
        }
        return 0;
    }

    @Override // com.jingdong.common.widget.custom.pageload.BaseTAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BaseVH baseVH, int i2) {
        if (baseVH instanceof CommonTaskVH) {
            CommonTaskVH commonTaskVH = (CommonTaskVH) baseVH;
            commonTaskVH.isLastItem(i2 == getItemCount() - 1);
            commonTaskVH.setItemPosition(i2);
        }
        super.onBindViewHolder(baseVH, i2);
    }

    @Override // com.jingdong.common.widget.custom.pageload.BaseTAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public BaseVH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        super.onCreateViewHolder(viewGroup, i2);
        if (i2 == 1) {
            TaskSignInfoVH taskSignInfoVH = new TaskSignInfoVH(this.inflater.inflate(R.layout.item_task_list_sign_info, viewGroup, false));
            taskSignInfoVH.mOnAcceptAwardClick = this.mOnAcceptAwardClick;
            return taskSignInfoVH;
        } else if (i2 == 2) {
            CommonTaskVH commonTaskVH = new CommonTaskVH(this.inflater.inflate(R.layout.item_task_list, viewGroup, false));
            commonTaskVH.mOnTaskClickListener = this.mOnTaskClickListener;
            commonTaskVH.mOnInnerClickListener = this.mBtnClickListener;
            return commonTaskVH;
        } else if (i2 != 3) {
            if (i2 != 4) {
                return new CommonTaskVH(this.inflater.inflate(R.layout.item_task_list, viewGroup, false));
            }
            return new TaskDiscloseVH(this.inflater.inflate(R.layout.item_task_disclose, viewGroup, false));
        } else {
            CommonTaskVH commonTaskVH2 = new CommonTaskVH(this.inflater.inflate(R.layout.item_task_list, viewGroup, false));
            commonTaskVH2.mOnTaskClickListener = this.mOnTaskClickListener;
            commonTaskVH2.mOnInnerClickListener = this.mBtnClickListener;
            return commonTaskVH2;
        }
    }
}
