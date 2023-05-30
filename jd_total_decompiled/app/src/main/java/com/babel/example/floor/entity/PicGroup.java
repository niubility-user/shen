package com.babel.example.floor.entity;

import java.util.List;

/* loaded from: classes.dex */
public class PicGroup {
    public List<PicModel> advertList;
    public String groupId;

    public boolean hasData() {
        List<PicModel> list = this.advertList;
        return list != null && list.size() > 0;
    }
}
