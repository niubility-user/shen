package com.jingdong.common.utils.personal.platform.floorframe.isv;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ViewGroup;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.dynamic.ISVDynFloorEntity;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.isv.BaseDynFloor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0006B/\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b$\u0010%J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u000e\u0010\fJ\u000f\u0010\u000f\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u000f\u0010\fJ\u000f\u0010\u0010\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0010\u0010\fJ\u0019\u0010\u000e\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0014\u00a2\u0006\u0004\b\u000e\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0014\u0010\fJ\u000f\u0010\u0015\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\u0015\u0010\fJ\u000f\u0010\u0016\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u0016\u0010\fJ\u000f\u0010\u0017\u001a\u00020\bH\u0014\u00a2\u0006\u0004\b\u0017\u0010\fJ\u0019\u0010\u001a\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u001cH\u0014\u00a2\u0006\u0004\b\u001d\u0010\u001e\u00a8\u0006&"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/isv/ISVDynFloor;", "Lcom/jingdong/sdk/platform/floor/isv/BaseDynFloor;", "Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;", "Lcom/jingdong/common/entity/personal/dynamic/ISVDynFloorEntity;", "Lcom/jingdong/sdk/aac/model/BaseViewModel;", "Lcom/jingdong/sdk/aac/navigator/BaseNavigator;", "Lcom/jingdong/common/utils/personal/platform/floorframe/isv/OnConfigurationChangeListener;", "floorTemplate", "", "showData", "(Lcom/jingdong/common/entity/personal/dynamic/ISVDynFloorEntity;)V", "onDestroyView", "()V", "onPause", "onCreatedView", "onAttachToWindow", "onDetachFromWindow", "Landroid/view/ViewGroup;", "parent", "(Landroid/view/ViewGroup;)Landroid/view/ViewGroup;", "onDestroy", "onResume", "initView", "initData", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "(Landroid/content/res/Configuration;)V", "", "canSetBg", "()Z", "Landroid/content/Context;", AnnoConst.Constructor_Context, "floorData", "", "mId", "<init>", "(Landroid/content/Context;Lcom/jingdong/sdk/platform/floor/entity/BaseFloorData;Ljava/lang/String;Landroid/view/ViewGroup;)V", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class ISVDynFloor extends BaseDynFloor<BaseFloorData, ISVDynFloorEntity, BaseViewModel, BaseNavigator> implements OnConfigurationChangeListener {
    public ISVDynFloor(@Nullable Context context, @Nullable BaseFloorData baseFloorData, @Nullable String str, @Nullable ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public boolean canSetBg() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void initData() {
        super.initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.floor.isv.BaseDynFloor, com.jingdong.sdk.platform.base.BaseViewHolder
    public void initView() {
        super.initView();
        Object obj = this.mFloorData;
        if (!(obj instanceof ISVFloorData)) {
            obj = null;
        }
        ISVFloorData iSVFloorData = (ISVFloorData) obj;
        if (iSVFloorData != null) {
            iSVFloorData.addOnConfigurationChangeListener(this);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onAttachToWindow() {
        super.onAttachToWindow();
    }

    @Override // com.jingdong.common.utils.personal.platform.floorframe.isv.OnConfigurationChangeListener
    public void onConfigurationChanged(@Nullable Configuration newConfig) {
        try {
            ViewGroup viewGroup = this.mRootView;
            if (viewGroup != null) {
                viewGroup.post(new Runnable() { // from class: com.jingdong.common.utils.personal.platform.floorframe.isv.ISVDynFloor$onConfigurationChanged$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ISVDynFloor.this.updateData();
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.floor.isv.BaseDynFloor, com.jingdong.sdk.platform.base.BaseViewHolder
    public void onCreatedView() {
        super.onCreatedView();
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDestroy() {
        super.onDestroy();
        Object obj = this.mFloorData;
        if (!(obj instanceof ISVFloorData)) {
            obj = null;
        }
        ISVFloorData iSVFloorData = (ISVFloorData) obj;
        if (iSVFloorData != null) {
            iSVFloorData.removeOnConfigurationChangeListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.floor.isv.BaseDynFloor, com.jingdong.sdk.platform.base.BaseViewHolder
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDetachFromWindow() {
        super.onDetachFromWindow();
    }

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onPause() {
        super.onPause();
    }

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.floor.isv.BaseDynFloor, com.jingdong.sdk.platform.base.BaseViewHolder
    @NotNull
    public ViewGroup onCreatedView(@Nullable ViewGroup parent) {
        ViewGroup onCreatedView = super.onCreatedView(parent);
        Intrinsics.checkExpressionValueIsNotNull(onCreatedView, "super.onCreatedView(parent)");
        return onCreatedView;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.BaseDynFloor
    public void showData(@Nullable ISVDynFloorEntity floorTemplate) {
        super.showData((ISVDynFloor) floorTemplate);
    }
}
