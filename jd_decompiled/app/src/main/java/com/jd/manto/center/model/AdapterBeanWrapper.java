package com.jd.manto.center.model;

import androidx.annotation.Keep;
import com.jingdong.manto.pkg.db.entity.PkgHistoryEntity;

@Keep
/* loaded from: classes17.dex */
public class AdapterBeanWrapper<T> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public T entity;
    public String title;
    public int type;

    public AdapterBeanWrapper(String str) {
        this.type = 0;
        this.title = "";
        this.title = str;
        this.type = 0;
    }

    public boolean equals(Object obj) {
        T t;
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdapterBeanWrapper) {
            AdapterBeanWrapper adapterBeanWrapper = (AdapterBeanWrapper) obj;
            T t2 = this.entity;
            if (t2 != null && (t = adapterBeanWrapper.entity) != null) {
                try {
                    if (t2 instanceof PkgHistoryEntity) {
                        PkgHistoryEntity pkgHistoryEntity = (PkgHistoryEntity) t2;
                        PkgHistoryEntity pkgHistoryEntity2 = (PkgHistoryEntity) t;
                        if (pkgHistoryEntity.appId.equals(pkgHistoryEntity2.appId)) {
                            if (pkgHistoryEntity.type.equals(pkgHistoryEntity2.type)) {
                                return true;
                            }
                        }
                        return false;
                    }
                } catch (Exception unused) {
                    return false;
                }
            }
            return super.equals(obj);
        }
        return false;
    }

    public AdapterBeanWrapper(T t) {
        this.type = 0;
        this.title = "";
        this.entity = t;
        this.type = 1;
    }
}
