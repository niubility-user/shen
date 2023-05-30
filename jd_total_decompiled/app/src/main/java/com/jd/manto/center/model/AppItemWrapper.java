package com.jd.manto.center.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.Keep;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;

@Keep
/* loaded from: classes17.dex */
public class AppItemWrapper {
    public static final int TYPE_ADD_APP = 4;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_LAST_USED = 1;
    public static final int TYPE_LIKED = 2;
    public static final int TYPE_MORE_APP = 3;
    public PkgHistoryEntity entity;
    public int iconRes;
    public String title;
    public int type;

    public AppItemWrapper(int i2, PkgHistoryEntity pkgHistoryEntity) {
        this.type = 0;
        this.iconRes = 0;
        this.type = i2;
        this.entity = pkgHistoryEntity;
    }

    public boolean equals(Object obj) {
        PkgHistoryEntity pkgHistoryEntity;
        if (this == obj) {
            return true;
        }
        if (obj instanceof AppItemWrapper) {
            AppItemWrapper appItemWrapper = (AppItemWrapper) obj;
            PkgHistoryEntity pkgHistoryEntity2 = this.entity;
            if (pkgHistoryEntity2 != null && (pkgHistoryEntity = appItemWrapper.entity) != null) {
                try {
                    if (pkgHistoryEntity2.appId.equals(pkgHistoryEntity.appId)) {
                        if (this.entity.type.equals(appItemWrapper.entity.type)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception unused) {
                    return false;
                }
            }
            try {
                if (this.type == ((AppItemWrapper) obj).type) {
                    if (this.title.equals(((AppItemWrapper) obj).title)) {
                        return true;
                    }
                }
                return false;
            } catch (Exception unused2) {
                return false;
            }
        }
        return false;
    }

    public int hashCode() {
        PkgHistoryEntity pkgHistoryEntity = this.entity;
        if (pkgHistoryEntity != null) {
            try {
                return pkgHistoryEntity.type.hashCode() + this.entity.appId.hashCode();
            } catch (Exception unused) {
            }
        }
        return super.hashCode();
    }

    public AppItemWrapper(String str) {
        this.type = 0;
        this.iconRes = 0;
        this.type = 0;
        this.title = str;
    }

    public AppItemWrapper(int i2, String str, @DrawableRes int i3) {
        this.type = 0;
        this.iconRes = 0;
        this.iconRes = i3;
        this.title = str;
        this.type = i2;
    }
}
