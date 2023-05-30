package com.jingdong.sdk.platform.floor;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.floor.constant.BaseFloorConstant;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.utils.FloorTools;
import com.jingdong.sdk.platform.utils.PlatformTools;

/* loaded from: classes10.dex */
public abstract class BaseFloor<K extends BaseFloorData, V extends BaseTemplateEntity, VM extends BaseViewModel, N extends BaseNavigator> extends BaseViewHolder<K, V, VM, N> {
    protected String TAG;
    public boolean isUsedByRecycleView;
    private int mFloorPosition;

    /* loaded from: classes10.dex */
    protected abstract class FloorRunnable implements Runnable {
        /* JADX INFO: Access modifiers changed from: protected */
        public FloorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((BaseViewHolder) BaseFloor.this).mIsDestroy) {
                return;
            }
            runs();
        }

        protected abstract void runs();
    }

    public BaseFloor(Context context, K k2, String str, ViewGroup viewGroup) {
        super(context, k2, str, viewGroup);
        this.TAG = "BaseFloor";
        this.isUsedByRecycleView = false;
        this.mFloorPosition = -1;
    }

    public int getFloorPosition() {
        return this.mFloorPosition;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideFloor() {
        if (this.mFloorEntity != 0) {
            Bundle bundle = new Bundle();
            bundle.putString("key", ((BaseTemplateEntity) this.mFloorEntity).mId);
            V v = this.mFloorEntity;
            bundle.putString("key1", v != 0 ? ((BaseTemplateEntity) v).bId : "");
            SyncEventBus.getInstances(((BaseFloorData) this.mFloorData).mManageKey).post(BaseFloorConstant.ACTION_FLOOR_BASE, BaseFloorConstant.EVENT_FLOOR_HIDE_FLOOR, bundle);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void hideViewHolder() {
        hideFloor();
    }

    protected boolean isSupportQuickScroll() {
        return false;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
    }

    public void preDrawFloor(final ViewGroup viewGroup) {
        final View view;
        if (PlatformTools.D) {
            PlatformTools.d(this.TAG, "preDrawFloor =  _ isUsedByRecycleView = " + this.isUsedByRecycleView);
        }
        if (viewGroup != null) {
            if ((FloorTools.isAPI21LevelHight() || isSupportQuickScroll()) && !this.isUsedByRecycleView && (view = getView()) != null && view.getParent() == null && view.getWidth() == 0) {
                if (PlatformTools.D) {
                    PlatformTools.d(this.TAG, "preDrawFloor =  do = " + getClass().getName());
                }
                viewGroup.addView(view);
                viewGroup.invalidate();
                post(new Runnable() { // from class: com.jingdong.sdk.platform.floor.BaseFloor.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (((BaseViewHolder) BaseFloor.this).mIsDestroy) {
                            return;
                        }
                        viewGroup.removeView(view);
                        if (PlatformTools.D) {
                            PlatformTools.d(BaseFloor.this.TAG, "preDrawFloor =  view.getwidth = " + view.getWidth());
                        }
                    }
                }, 10L);
            }
        }
    }

    public void setFloorPosition(int i2) {
        this.mFloorPosition = i2;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public V getBaseEntity() {
        return (V) this.mFloorEntity;
    }
}
