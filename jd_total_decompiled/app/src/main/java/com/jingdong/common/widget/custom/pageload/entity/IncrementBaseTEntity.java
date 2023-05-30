package com.jingdong.common.widget.custom.pageload.entity;

import android.text.TextUtils;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import java.util.List;

/* loaded from: classes12.dex */
public class IncrementBaseTEntity<T extends IFloorEntity> {
    public int bilistsize;
    public String code;
    public List<T> list;
    public String offset;

    public T getListItem(int i2) {
        List<T> list = this.list;
        if (list != null) {
            return list.get(i2);
        }
        return null;
    }

    public TopEntity getTopEntity() {
        return null;
    }

    public boolean isSuccess() {
        List<T> list;
        if (TextUtils.equals("0", this.code) && (list = this.list) != null) {
            if (list.contains(null)) {
                int size = this.list.size();
                for (int i2 = 0; i2 < size && this.list.remove((Object) null); i2++) {
                }
                return true;
            }
            return true;
        }
        return false;
    }
}
