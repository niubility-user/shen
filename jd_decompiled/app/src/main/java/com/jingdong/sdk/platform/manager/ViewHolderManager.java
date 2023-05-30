package com.jingdong.sdk.platform.manager;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.base.BaseData;
import com.jingdong.sdk.platform.base.BaseViewHolder;
import com.jingdong.sdk.platform.base.ViewHolder;

/* loaded from: classes10.dex */
public interface ViewHolderManager {
    ViewHolder createViewHolder(Context context, BaseData baseData, String str);

    ViewHolder createViewHolder(Context context, BaseData baseData, String str, ViewGroup viewGroup);

    Class<? extends BaseViewHolder> getClassById(String str);

    Class<? extends BaseViewHolder> getClassByType(int i2);

    String getClassNameById(String str);

    String getClassNameByType(int i2);

    String getIdByType(int i2);

    int getTypeById(String str);

    int getViewHolderCount();

    void register(String str, Class<? extends BaseViewHolder> cls);
}
