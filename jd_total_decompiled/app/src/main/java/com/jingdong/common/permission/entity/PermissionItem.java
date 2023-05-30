package com.jingdong.common.permission.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class PermissionItem implements Parcelable {
    private String permission;
    private String permissionName;
    private String permissionTip;

    public PermissionItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPermission() {
        return this.permission;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public String getPermissionTip() {
        return this.permissionTip;
    }

    public void setPermission(String str) {
        this.permission = str;
    }

    public void setPermissionName(String str) {
        this.permissionName = str;
    }

    public void setPermissionTip(String str) {
        this.permissionTip = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }

    public PermissionItem(String str, String str2, String str3) {
        this.permission = str;
        this.permissionName = str2;
        this.permissionTip = str3;
    }
}
