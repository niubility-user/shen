package com.jingdong.sdk.platform.base;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseEntity;
import com.jingdong.sdk.platform.utils.OnViewHolderHideListener;

/* loaded from: classes9.dex */
public interface ViewHolder<K extends BaseData, V extends BaseEntity> extends SyncEventBus.EventBusListener {
    <T extends View> T findViewById(int i2);

    K getBaseData();

    V getBaseEntity();

    Context getContext();

    Resources getResources();

    View getView();

    void hideViewHolder();

    boolean isAttachToWindow();

    void onAttachToWindow();

    boolean onDataShowed(V v);

    boolean onDataShowed(V v, Object obj);

    void onDestroy();

    void onDetachFromWindow();

    void post(Runnable runnable);

    void post(Runnable runnable, long j2);

    void resetHeight(int i2);

    void runOnAttachToWindow(Runnable runnable);

    void setOnViewHolderHideListener(OnViewHolderHideListener onViewHolderHideListener);
}
