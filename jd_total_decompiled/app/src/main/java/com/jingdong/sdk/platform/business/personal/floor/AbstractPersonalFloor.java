package com.jingdong.sdk.platform.business.personal.floor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import com.google.common.base.Preconditions;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;

/* loaded from: classes10.dex */
public abstract class AbstractPersonalFloor extends BaseViewHolder<BaseFloorData, BaseTemplateEntity, BaseViewModel, BaseNavigator> {
    private static final String TAG = "AbstractPersonalFloor";
    protected BaseActivity baseActivity;
    private LayoutInflater layoutInflater;

    public AbstractPersonalFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public boolean canSetBg() {
        return false;
    }

    public abstract ViewGroup createRootView(ViewGroup viewGroup);

    @NonNull
    public LayoutInflater getLayoutInflater() {
        return this.layoutInflater;
    }

    public ViewGroup inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup) {
        ViewGroup inflate = inflate(i2, viewGroup, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public boolean isSameEntity(BaseEntity baseEntity) {
        return super.isSameEntity(baseEntity);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected ViewGroup onCreatedView(ViewGroup viewGroup) {
        if (Log.D) {
            Log.d(TAG, "onCreatedView");
        }
        Preconditions.checkNotNull(this.mContext);
        Context context = this.mContext;
        if (context instanceof BaseActivity) {
            this.baseActivity = (BaseActivity) context;
            this.layoutInflater = LayoutInflater.from(context.getApplicationContext());
            return createRootView(viewGroup);
        }
        throw new IllegalArgumentException("Context reference must be instanceof BaseActivity!");
    }

    public ViewGroup inflate(@LayoutRes int i2, @Nullable ViewGroup viewGroup, boolean z) {
        return (ViewGroup) this.layoutInflater.inflate(i2, viewGroup, z);
    }
}
