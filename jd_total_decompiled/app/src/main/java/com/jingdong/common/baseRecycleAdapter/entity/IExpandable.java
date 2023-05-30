package com.jingdong.common.baseRecycleAdapter.entity;

import java.util.List;

/* loaded from: classes5.dex */
public interface IExpandable<T> {
    int getLevel();

    List<T> getSubItems();

    boolean isExpanded();

    void setExpanded(boolean z);
}
