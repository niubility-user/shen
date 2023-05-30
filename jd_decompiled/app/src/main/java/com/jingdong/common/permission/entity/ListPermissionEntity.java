package com.jingdong.common.permission.entity;

import android.text.TextUtils;
import com.jingdong.common.ui.ListDialogEntity;

/* loaded from: classes5.dex */
public class ListPermissionEntity extends ListDialogEntity {
    private int resId;

    public ListPermissionEntity() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return TextUtils.equals(getTitle(), ((ListPermissionEntity) obj).getTitle());
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i2) {
        this.resId = i2;
    }

    public ListPermissionEntity(String str, String str2, int i2) {
        this.resId = i2;
    }
}
