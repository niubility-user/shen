package com.jingdong.app.mall.bundle.order_center_isv_core.base;

import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes3.dex */
public abstract class OrderDetailISVBaseFloor extends BaseFloor {
    private static final String TAG = "OrderDetailISVBaseFloor";
    protected BaseActivity mBaseActivity;

    public OrderDetailISVBaseFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    public abstract ViewGroup createRootView(ViewGroup viewGroup);

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    @NonNull
    public Resources getResources() {
        BaseActivity baseActivity = this.mBaseActivity;
        if (baseActivity != null) {
            return baseActivity.getResources();
        }
        return this.mContext.getResources();
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class getViewModelClass() {
        return null;
    }

    public ViewGroup inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup) {
        return inflate(i2, viewGroup, false);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected ViewGroup onCreatedView(ViewGroup viewGroup) {
        Context context = this.mContext;
        if (context != null) {
            if (context instanceof BaseActivity) {
                this.mBaseActivity = (BaseActivity) context;
                return createRootView(viewGroup);
            }
            throw new IllegalArgumentException("Context reference must be instanceof BaseActivity!");
        }
        throw new NullPointerException("mContext can not be reference as null");
    }

    public ViewGroup inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup, boolean z) {
        return (ViewGroup) ImageUtil.inflate(this.mBaseActivity, i2, viewGroup, z);
    }
}
