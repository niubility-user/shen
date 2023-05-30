package com.jingdong.common.widget.custom.pageload;

import android.text.TextUtils;
import com.jingdong.common.widget.custom.pageload.entity.IFloorEntity;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes12.dex */
public class Filter {
    private IExFilter iExFilter;
    private Set<String> oldIds = new HashSet();
    private int startSize = 0;

    /* loaded from: classes12.dex */
    public interface IExFilter {
        boolean canAdd(IFloorEntity iFloorEntity);
    }

    public void clear() {
        this.oldIds.clear();
        this.startSize = 0;
    }

    public int endFiltGetAddSize() {
        return this.oldIds.size() - this.startSize;
    }

    public boolean isFilted(IFloorEntity iFloorEntity) {
        if (iFloorEntity == null || TextUtils.isEmpty(iFloorEntity.id) || this.oldIds.contains(iFloorEntity.id)) {
            return true;
        }
        IExFilter iExFilter = this.iExFilter;
        if (iExFilter == null) {
            this.oldIds.add(iFloorEntity.id);
            return false;
        } else if (iExFilter.canAdd(iFloorEntity)) {
            this.oldIds.add(iFloorEntity.id);
            return false;
        } else {
            return true;
        }
    }

    public void setExFilter(IExFilter iExFilter) {
        this.iExFilter = iExFilter;
    }

    public void startFilt() {
        this.startSize = this.oldIds.size();
    }
}
