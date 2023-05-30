package com.jingdong.sdk.platform.floor.floors;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.base.ViewHolder;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.manager.ViewHolderManagerProxy;
import com.jingdong.sdk.platform.utils.OnViewHolderHideListener;

/* loaded from: classes10.dex */
public class CommonFloor extends BaseFloor<BaseFloorData, BaseTemplateEntity, BaseViewModel, BaseNavigator> {
    private ViewHolder mBaseViewHolder;
    private OnViewHolderHideListener mHideListener;

    public CommonFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
        this.mIsInit = false;
        ViewHolder createViewHolder = ViewHolderManagerProxy.getInstance().createViewHolder(context, baseFloorData, str, viewGroup);
        this.mBaseViewHolder = createViewHolder;
        if (createViewHolder != null) {
            this.mRootView = (ViewGroup) createViewHolder.getView();
            this.mIsInit = true;
            OnViewHolderHideListener onViewHolderHideListener = new OnViewHolderHideListener() { // from class: com.jingdong.sdk.platform.floor.floors.CommonFloor.1
                {
                    CommonFloor.this = this;
                }

                @Override // com.jingdong.sdk.platform.utils.OnViewHolderHideListener
                public void hideViewHolder() {
                    CommonFloor.this.hideFloor();
                }
            };
            this.mHideListener = onViewHolderHideListener;
            this.mBaseViewHolder.setOnViewHolderHideListener(onViewHolderHideListener);
        }
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public BaseViewModel createViewModel() {
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class<BaseViewModel> getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onAttachToWindow() {
        ViewHolder viewHolder = this.mBaseViewHolder;
        if (viewHolder != null) {
            viewHolder.onAttachToWindow();
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
        ViewHolder viewHolder = this.mBaseViewHolder;
        if (viewHolder != null) {
            viewHolder.onDestroy();
        }
        this.mBaseViewHolder = null;
        this.mHideListener = null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDetachFromWindow() {
        ViewHolder viewHolder = this.mBaseViewHolder;
        if (viewHolder != null) {
            viewHolder.onDetachFromWindow();
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseTemplateEntity baseTemplateEntity) {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public boolean onDataShowed(BaseTemplateEntity baseTemplateEntity, Object obj) {
        this.mFloorEntity = baseTemplateEntity;
        if (baseTemplateEntity != null) {
            ViewHolder viewHolder = this.mBaseViewHolder;
            if (viewHolder != null) {
                return viewHolder.onDataShowed(baseTemplateEntity, obj);
            }
            return false;
        }
        hideFloor();
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public boolean onDataShowed(BaseTemplateEntity baseTemplateEntity) {
        this.mFloorEntity = baseTemplateEntity;
        if (baseTemplateEntity != null) {
            ViewHolder viewHolder = this.mBaseViewHolder;
            if (viewHolder != null) {
                return viewHolder.onDataShowed(baseTemplateEntity);
            }
            return false;
        }
        hideFloor();
        return false;
    }
}
